package com.samsung.android.lxd.processor.network.channel.pty.internal;

import android.os.Handler;
import android.os.Handler.Callback;
import android.os.Looper;
import android.os.Message;
import android.os.ParcelFileDescriptor;
import android.os.ParcelFileDescriptor.AutoCloseInputStream;
import android.os.ParcelFileDescriptor.AutoCloseOutputStream;
import android.text.TextUtils;
import com.samsung.android.lxd.a.o;
import com.samsung.android.lxd.processor.LxdException;
import com.samsung.android.lxd.processor.network.channel.pty.PtyJni;
import com.samsung.android.lxd.processor.utils.log.Log;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.Charset;
import java.nio.charset.CharsetEncoder;
import java.nio.charset.CodingErrorAction;
import java.util.ArrayList;
import java.util.List;

public class TerminalSession {
    private static final int BUFFER_SIZE = 4096;
    private static final String CONNECT_ERROR_MESSAGE = "Failed to connect!";
    private static final int MSG_HANDLER_EOF = 2;
    private static final int MSG_HANDLER_NEW_INPUT = 1;
    private static final int MSG_WRITER_FINISH = 4;
    private static final int MSG_WRITER_NEW_OUTPUT = 3;
    private static final String PSEUDO_TERMINAL_PATH = "/dev/ptmx";
    private static final String START_ERROR_MESSAGE = "Failed to start!";
    private static final int START_TIMEOUT_DELAY = 25000;
    private static final String TAG = "TerminalSession";
    private static final int WRITE_BYTE_BUFFER_SIZE = 4;
    private static final int WRITE_CHAR_BUFFER_SIZE = 2;
    private final boolean mDefaultUTF8Mode = this.mSetting.defaultToUTF8Mode();
    private TerminalEmulator mEmulator;
    private final Handler mHandler;
    private TerminalKeyListener mKeyListener;
    private final List<OnStateListener> mOnStateListener = new ArrayList();
    private final ParcelFileDescriptor mPtmFd = ParcelFileDescriptor.open(new File(PSEUDO_TERMINAL_PATH), 805306368);
    private final String mPtsName;
    private ByteQueue mReadQueue;
    private final Thread mReaderThread;
    private byte[] mReceiveBuffer;
    private final TerminalSetting mSetting;
    private State mState;
    private InputStream mTerminalInput;
    private OutputStream mTerminalOutput;
    private ByteBuffer mWriteByteBuffer;
    private CharBuffer mWriteCharBuffer;
    private ByteQueue mWriteQueue;
    private CharsetEncoder mWriteUTF8Encoder;
    private Handler mWriterHandler;
    private final Thread mWriterThread;

    public interface OnStateListener {
        void onFinish(Throwable th);

        void onStart();

        void onUpdate();
    }

    private class ReaderThread extends Thread {
        private byte[] mBuffer;

        private ReaderThread() {
            this.mBuffer = new byte[4096];
        }

        /* synthetic */ ReaderThread(TerminalSession terminalSession, AnonymousClass1 anonymousClass1) {
            this();
        }

        /* JADX WARNING: Removed duplicated region for block: B:0:0x0000 A:{LOOP_START, SYNTHETIC, Splitter: B:0:0x0000, EDGE_INSN: B:0:0x0000->B:1:? ?: BREAK  } */
        /* JADX WARNING: Removed duplicated region for block: B:3:0x0010 A:{Catch:{ Exception -> 0x0036 }} */
        /* JADX WARNING: Removed duplicated region for block: B:9:0x0036 A:{SYNTHETIC} */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public void run() {
            while (true) {
                try {
                    int read = TerminalSession.this.mTerminalInput.read(this.mBuffer);
                    if (read == -1) {
                        int i = 0;
                        while (true) {
                            if (read > 0) {
                                int write = TerminalSession.this.mReadQueue.write(this.mBuffer, i, read);
                                i += write;
                                read -= write;
                                TerminalSession.this.mHandler.sendMessage(TerminalSession.this.mHandler.obtainMessage(1));
                            }
                        }
                        int read2 = TerminalSession.this.mTerminalInput.read(this.mBuffer);
                        if (read2 == -1) {
                        }
                    }
                } catch (Exception unused) {
                    TerminalSession.this.mHandler.sendMessage(TerminalSession.this.mHandler.obtainMessage(2));
                    Log.i(TerminalSession.TAG, "Terminates ReaderThread!");
                    return;
                }
            }
        }
    }

    private enum State {
        INVALID,
        INITIATED,
        STARTED,
        STOPPED
    }

    private class WriterThread extends Thread {
        private byte[] mBuffer;

        private WriterThread() {
            this.mBuffer = new byte[4096];
        }

        /* synthetic */ WriterThread(TerminalSession terminalSession, AnonymousClass1 anonymousClass1) {
            this();
        }

        public void run() {
            Looper.prepare();
            TerminalSession.this.mWriterHandler = new Handler(new Callback() {
                public boolean handleMessage(Message message) {
                    String access$300 = TerminalSession.TAG;
                    StringBuilder stringBuilder = new StringBuilder();
                    stringBuilder.append("handleMessage : ");
                    stringBuilder.append(Integer.toHexString(message.what));
                    Log.d(access$300, stringBuilder.toString());
                    switch (message.what) {
                        case 3:
                            WriterThread.this.writeToOutput();
                            break;
                        case 4:
                            Looper myLooper = Looper.myLooper();
                            if (myLooper != null) {
                                myLooper.quit();
                                break;
                            }
                            break;
                    }
                    return true;
                }
            });
            writeToOutput();
            Looper.loop();
            TerminalSession.this.mWriterHandler = null;
            Log.i(TerminalSession.TAG, "Terminates WriterThread!");
        }

        private void writeToOutput() {
            ByteQueue access$1400 = TerminalSession.this.mWriteQueue;
            byte[] bArr = this.mBuffer;
            OutputStream access$1500 = TerminalSession.this.mTerminalOutput;
            int min = Math.min(access$1400.getBytesAvailable(), bArr.length);
            if (min != 0) {
                try {
                    access$1400.read(bArr, 0, min);
                    access$1500.write(bArr, 0, min);
                    access$1500.flush();
                } catch (Exception e) {
                    Log.e(TerminalSession.TAG, e.toString());
                }
            }
        }
    }

    public TerminalSession(TerminalSetting terminalSetting) {
        this.mSetting = terminalSetting;
        setTerminalOutput(new AutoCloseOutputStream(this.mPtmFd));
        setTerminalInput(new AutoCloseInputStream(this.mPtmFd));
        this.mPtsName = (String) PtyJni.createSubprocess(this.mPtmFd.getFd())[1];
        this.mState = State.INVALID;
        this.mReceiveBuffer = new byte[4096];
        this.mReadQueue = new ByteQueue(4096);
        this.mReaderThread = new ReaderThread(this, null);
        this.mWriteByteBuffer = ByteBuffer.allocate(4);
        this.mWriteCharBuffer = CharBuffer.allocate(2);
        this.mWriteUTF8Encoder = Charset.forName("UTF-8").newEncoder().onMalformedInput(CodingErrorAction.REPLACE).onUnmappableCharacter(CodingErrorAction.REPLACE);
        this.mWriteQueue = new ByteQueue(4096);
        this.mWriterThread = new WriterThread(this, null);
        final Runnable anonymousClass1 = new Runnable() {
            public void run() {
                TerminalSession.this.onFinish(new Throwable(TerminalSession.START_ERROR_MESSAGE));
            }
        };
        this.mHandler = new Handler(new Callback() {
            public boolean handleMessage(Message message) {
                String access$300 = TerminalSession.TAG;
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append("handleMessage : ");
                stringBuilder.append(Integer.toHexString(message.what));
                Log.d(access$300, stringBuilder.toString());
                if (!TerminalSession.this.isRunning()) {
                    return true;
                }
                switch (message.what) {
                    case 1:
                        TerminalSession.this.onReceive(anonymousClass1);
                        break;
                    case 2:
                        TerminalSession.this.onFinish();
                        break;
                }
                return true;
            }
        });
        this.mHandler.postDelayed(anonymousClass1, 25000);
    }

    public void onFinish() {
        onFinish(new Throwable("normalExit"));
    }

    private void onFinish(final Throwable th) {
        if (this.mState != State.STOPPED) {
            changeState(State.STOPPED);
            o.a(new Runnable() {
                public void run() {
                    TerminalSession.this.finish();
                    TerminalSession.this.notifyFinish(th);
                }
            }, true);
        }
    }

    private void onReceive(Runnable runnable) {
        if (ensureStarted()) {
            this.mHandler.removeCallbacks(runnable);
            notifyStart();
        }
        readFromProcess();
        notifyUpdate();
    }

    private void changeState(State state) {
        String str = TAG;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("changeState ");
        stringBuilder.append(this.mState);
        stringBuilder.append(" => ");
        stringBuilder.append(state);
        Log.i(str, stringBuilder.toString());
        this.mState = state;
    }

    private boolean ensureStarted() {
        if (this.mState == State.STARTED) {
            return false;
        }
        changeState(State.STARTED);
        return true;
    }

    public boolean isStarted() {
        return this.mState == State.STARTED;
    }

    private boolean isRunning() {
        return this.mState == State.INITIATED || this.mState == State.STARTED;
    }

    private void initializeEmulator(int i, int i2) {
        this.mEmulator = new TerminalEmulator(this, i, i2, this.mSetting.getColorScheme()).setDefaultUTF8Mode(this.mDefaultUTF8Mode).setKeyListener(this.mKeyListener).setUTF8ModeListener(new OnTerminalListener() {
            public void onUpdate() {
                TerminalSession.this.setPtyUTF8Mode(TerminalSession.this.getUTF8Mode());
            }
        });
        changeState(State.INITIATED);
        this.mReaderThread.start();
        this.mWriterThread.start();
        setPtyUTF8Mode(getUTF8Mode());
        if (!TextUtils.isEmpty(this.mSetting.getInitialCommand())) {
            sendInitialCommand(this.mSetting.getInitialCommand());
        }
    }

    public void write(byte[] bArr, int i, int i2) {
        while (i2 > 0) {
            try {
                int write = this.mWriteQueue.write(bArr, i, i2);
                i += write;
                i2 -= write;
                if (this.mWriterHandler != null) {
                    this.mWriterHandler.sendEmptyMessage(3);
                }
            } catch (InterruptedException unused) {
                return;
            }
        }
    }

    public void write(String str) {
        try {
            byte[] bytes = str.getBytes("UTF-8");
            write(bytes, 0, bytes.length);
        } catch (UnsupportedEncodingException unused) {
        }
    }

    public void write(int i) {
        ByteBuffer byteBuffer = this.mWriteByteBuffer;
        if (i < 128) {
            byte[] array = byteBuffer.array();
            array[0] = (byte) i;
            write(array, 0, 1);
            return;
        }
        CharBuffer charBuffer = this.mWriteCharBuffer;
        CharsetEncoder charsetEncoder = this.mWriteUTF8Encoder;
        charBuffer.clear();
        byteBuffer.clear();
        Character.toChars(i, charBuffer.array(), 0);
        charsetEncoder.reset();
        charsetEncoder.encode(charBuffer, byteBuffer, true);
        charsetEncoder.flush(byteBuffer);
        write(byteBuffer.array(), 0, byteBuffer.position() - 1);
    }

    void setKeyListener(TerminalKeyListener terminalKeyListener) {
        this.mKeyListener = terminalKeyListener;
    }

    private void setTerminalOutput(OutputStream outputStream) {
        this.mTerminalOutput = outputStream;
    }

    private void setTerminalInput(InputStream inputStream) {
        this.mTerminalInput = inputStream;
    }

    TerminalEmulator getEmulator() {
        return this.mEmulator;
    }

    public TerminalSession addOnStateListener(OnStateListener onStateListener) {
        this.mOnStateListener.add(onStateListener);
        return this;
    }

    private void notifyFinish(Throwable th) {
        Log.i(TAG, "notifyFinish");
        for (OnStateListener onFinish : this.mOnStateListener) {
            onFinish.onFinish(th);
        }
    }

    private void notifyStart() {
        Log.i(TAG, "notifyStart");
        for (OnStateListener onStart : this.mOnStateListener) {
            onStart.onStart();
        }
    }

    private void notifyUpdate() {
        Log.d(TAG, "notifyUpdate");
        for (OnStateListener onUpdate : this.mOnStateListener) {
            onUpdate.onUpdate();
        }
    }

    void setTitle(String str) {
        String str2 = TAG;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("setTitle : ");
        stringBuilder.append(str);
        Log.d(str2, stringBuilder.toString());
        notifyUpdate();
    }

    void updateSize(int i, int i2) {
        setPtyWindowSize(i2, i);
        if (this.mEmulator == null) {
            initializeEmulator(i, i2);
        } else {
            this.mEmulator.updateSize(i, i2);
        }
    }

    private void readFromProcess() {
        try {
            processInput(this.mReceiveBuffer, 0, this.mReadQueue.read(this.mReceiveBuffer, 0, Math.min(this.mReadQueue.getBytesAvailable(), this.mReceiveBuffer.length)));
        } catch (InterruptedException unused) {
        }
    }

    private void processInput(byte[] bArr, int i, int i2) {
        this.mEmulator.append(bArr, i, i2);
    }

    private boolean getUTF8Mode() {
        if (this.mEmulator == null) {
            return this.mDefaultUTF8Mode;
        }
        return this.mEmulator.getUTF8Mode();
    }

    public void reset() {
        this.mEmulator.reset();
        notifyUpdate();
    }

    public void finish() {
        try {
            this.mPtmFd.close();
        } catch (IOException unused) {
            if (this.mEmulator != null) {
                this.mEmulator.finish();
            }
            if (this.mWriterHandler != null) {
                this.mWriterHandler.sendEmptyMessage(4);
            }
            try {
                this.mTerminalInput.close();
                this.mTerminalOutput.close();
            } catch (IOException unused2) {
            }
        }
    }

    public String getPtsName() {
        if (this.mPtsName != null) {
            return this.mPtsName;
        }
        throw new LxdException(CONNECT_ERROR_MESSAGE);
    }

    private void sendInitialCommand(String str) {
        if (str.length() > 0) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(str);
            stringBuilder.append(13);
            write(stringBuilder.toString());
        }
    }

    private void setPtyWindowSize(int i, int i2) {
        if (this.mPtmFd.getFileDescriptor().valid()) {
            try {
                PtyJni.setPtyWindowSize(this.mPtmFd.getFd(), i, i2, 0, 0);
            } catch (Throwable e) {
                String str = TAG;
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append("Failed to set window size: ");
                stringBuilder.append(e.getMessage());
                Log.e(str, stringBuilder.toString());
                throw new IllegalStateException(e);
            }
        }
    }

    private void setPtyUTF8Mode(boolean z) {
        if (this.mPtmFd.getFileDescriptor().valid()) {
            try {
                PtyJni.setPtyUTF8Mode(this.mPtmFd.getFd(), z);
            } catch (Throwable e) {
                String str = TAG;
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append("Failed to set UTF mode: ");
                stringBuilder.append(e.getMessage());
                Log.e(str, stringBuilder.toString());
                throw new IllegalStateException(e);
            }
        }
    }
}
