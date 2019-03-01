package com.samsung.android.lxd.processor.control.channel.socket;

import android.net.LocalSocket;
import android.net.LocalSocketAddress;
import com.samsung.android.lxd.processor.ExecutionType;
import com.samsung.android.lxd.processor.ICommonContext;
import com.samsung.android.lxd.processor.LxdException;
import com.samsung.android.lxd.processor.control.channel.ControlChannelListener;
import com.samsung.android.lxd.processor.control.channel.ControlChannelType;
import com.samsung.android.lxd.processor.control.channel.IControlChannel;
import com.samsung.android.lxd.processor.control.channel.IControlChannel.ICallback;
import com.samsung.android.lxd.processor.control.channel.socket.container.ContainerControlChannelV5;
import com.samsung.android.lxd.processor.control.channel.socket.nst.NstControlChannelV1;
import com.samsung.android.lxd.processor.control.channel.socket.nst.NstControlChannelV2;
import com.samsung.android.lxd.processor.control.channel.socket.nst.NstControlChannelV3;
import com.samsung.android.lxd.processor.control.channel.socket.nst.NstControlChannelV4;
import com.samsung.android.lxd.processor.control.channel.socket.nst.NstControlChannelV5;
import com.samsung.android.lxd.processor.utils.log.Log;
import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Timer;
import java.util.TimerTask;

public class SocketControlChannel implements IControlChannel, IControlChannelHelper {
    private static final int INPUT_BUFFER_SIZE = 16384;
    private static final int MAX_TRY_COUNT = 15;
    private static final int RECONNECT_INTERVAL = 1000;
    private static final int RESPONSE_THRESHOLD = 30000;
    private static final int SYNC_INTERVAL = 300000;
    private boolean mActive = true;
    private IControlChannelImpl mChannelImpl = null;
    private final ControlChannelType mChannelType;
    private ICommonContext mCommonContext = null;
    private LocalSocket mControlSocket = null;
    private Thread mControlThread = null;
    private DataInputStream mInputStream = null;
    private boolean mKeepConnection = false;
    private boolean mKeepRetry = false;
    private ICallback mListener = new ControlChannelListener();
    private OutputStream mOutputStream = null;
    private Timer mRemoteResponseTimer = null;
    private Timer mRemoteSyncTimer = null;
    private LinkedHashMap mRequestMap = new LinkedHashMap();
    private final HashMap<ControlChannelType, VersionInfo> mVersionInfo = new HashMap();
    private boolean mWaitingForResponse = false;

    private class RequestMessage {
        private boolean mNeedResponseTimer;
        private int mRequestCmd;
        private String mRequestMessage;

        public RequestMessage(int i, String str, boolean z) {
            this.mRequestCmd = i;
            this.mRequestMessage = str;
            this.mNeedResponseTimer = z;
        }

        public int getRequestCmd() {
            return this.mRequestCmd;
        }

        public String getRequestMessage() {
            return this.mRequestMessage;
        }

        public boolean getNeedResponseTimer() {
            return this.mNeedResponseTimer;
        }
    }

    private class VersionInfo {
        private final int mBaseVersion;
        private final int mMaxVersion;
        private final int mMinVersion;

        /* synthetic */ VersionInfo(SocketControlChannel socketControlChannel, int i, int i2, int i3, AnonymousClass1 anonymousClass1) {
            this(i, i2, i3);
        }

        private VersionInfo(int i, int i2, int i3) {
            this.mBaseVersion = i;
            this.mMinVersion = i2;
            this.mMaxVersion = i3;
        }

        private int getBaseVersion() {
            return this.mBaseVersion;
        }

        private int getMinVersion() {
            return this.mMinVersion;
        }

        private int getMaxVersion() {
            return this.mMaxVersion;
        }
    }

    public SocketControlChannel(ControlChannelType controlChannelType) {
        this.mVersionInfo.put(ControlChannelType.NST, new VersionInfo(this, 1, 1, 5, null));
        this.mVersionInfo.put(ControlChannelType.CONTAINER, new VersionInfo(this, 5, 5, 5, null));
        this.mChannelType = controlChannelType;
        this.mChannelImpl = getChannelImpl(((VersionInfo) this.mVersionInfo.get(this.mChannelType)).getBaseVersion());
    }

    public String TAG() {
        Object thisR;
        if (this.mChannelImpl != null) {
            thisR = this.mChannelImpl;
        }
        return thisR.getClass().getSimpleName();
    }

    public boolean isSupportedVersion(int i) {
        return i >= ((VersionInfo) this.mVersionInfo.get(this.mChannelType)).getMinVersion() && i <= ((VersionInfo) this.mVersionInfo.get(this.mChannelType)).getMaxVersion();
    }

    public void openService(ICallback iCallback) {
        closeService();
        this.mChannelImpl.openService();
        if (iCallback == null) {
            iCallback = this.mListener;
        }
        this.mListener = iCallback;
        this.mKeepConnection = true;
        this.mControlThread = new Thread() {
            public void run() {
                try {
                    SocketControlChannel.this.connect();
                    SocketControlChannel.this.initialize();
                    SocketControlChannel.this.process();
                } catch (Throwable th) {
                    SocketControlChannel.this.mControlThread = null;
                }
                SocketControlChannel.this.mControlThread = null;
            }
        };
        this.mControlThread.start();
    }

    public IControlChannel setContext(ICommonContext iCommonContext) {
        this.mCommonContext = iCommonContext;
        return this;
    }

    public void closeService() {
        this.mKeepConnection = false;
        this.mKeepRetry = false;
        this.mActive = true;
        this.mWaitingForResponse = false;
        this.mRequestMap.clear();
        close();
        try {
            if (this.mControlThread != null) {
                this.mControlThread.join();
                this.mControlThread = null;
            }
            if (this.mRemoteSyncTimer != null) {
                this.mRemoteSyncTimer.cancel();
                this.mRemoteSyncTimer = null;
            }
            if (this.mRemoteResponseTimer != null) {
                this.mRemoteResponseTimer.cancel();
                this.mRemoteResponseTimer = null;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        this.mChannelImpl.closeService();
    }

    public void pauseService() {
        synchronized (this) {
            this.mActive = false;
            this.mWaitingForResponse = false;
            this.mRequestMap.clear();
        }
    }

    public void resumeService() {
        synchronized (this) {
            this.mActive = true;
        }
    }

    public void openContainer(String str) {
        this.mChannelImpl.openContainer(str);
    }

    public void closeContainer() {
        this.mChannelImpl.closeContainer();
    }

    public void pauseContainer() {
        this.mChannelImpl.pauseContainer();
    }

    public void resumeContainer() {
        this.mChannelImpl.resumeContainer();
    }

    public void getImageVersion(String str) {
        this.mChannelImpl.getImageVersion(str);
    }

    public void getImageMinSize(String str) {
        this.mChannelImpl.getImageMinSize(str);
    }

    public void rebaseImage(String str) {
        this.mChannelImpl.rebaseImage(str);
    }

    public void changeImageSize(String str, int i, boolean z) {
        this.mChannelImpl.changeImageSize(str, i, z);
    }

    public void notifySdCardStatus(String str, String str2) {
        this.mChannelImpl.notifySdCardStatus(str, str2);
    }

    public void getDebugLog(String str) {
        this.mChannelImpl.getDebugLog(str);
    }

    public void startMonitoring() {
        this.mChannelImpl.startMonitoring();
    }

    public void stopMonitoring() {
        this.mChannelImpl.stopMonitoring();
    }

    public IControlChannel setConfigId(String str) {
        this.mChannelImpl.setConfigId(str);
        return this;
    }

    public IControlChannel setExecutionType(ExecutionType executionType) {
        this.mChannelImpl.setExecutionType(executionType);
        return this;
    }

    private void connect() {
        this.mControlSocket = new LocalSocket();
        this.mKeepRetry = true;
        int i = 0;
        while (i < 15 && this.mKeepRetry) {
            try {
                this.mControlSocket.connect(new LocalSocketAddress(this.mChannelImpl.getSocketName(), this.mChannelImpl.getSocketNamespace()));
                if (this.mControlSocket.isConnected()) {
                    break;
                }
            } catch (IOException e) {
                String TAG = TAG();
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append("Not connected to server!: ");
                stringBuilder.append(e.toString());
                Log.d(TAG, stringBuilder.toString());
            }
            try {
                this.mChannelImpl.openService();
                Thread.sleep(1000);
                String TAG2 = TAG();
                StringBuilder stringBuilder2 = new StringBuilder();
                stringBuilder2.append("Reconnecting to server...");
                stringBuilder2.append(i);
                Log.d(TAG2, stringBuilder2.toString());
                i++;
            } catch (Exception unused) {
                Log.d(TAG(), "Failed to put thread to sleep!");
            }
        }
        if (this.mControlSocket.isConnected()) {
            Log.d(TAG(), "connected to server!");
            try {
                this.mInputStream = new DataInputStream(new BufferedInputStream(this.mControlSocket.getInputStream(), INPUT_BUFFER_SIZE));
                this.mOutputStream = this.mControlSocket.getOutputStream();
                this.mListener.onServiceConnected();
                return;
            } catch (Exception e2) {
                String TAG3 = TAG();
                StringBuilder stringBuilder3 = new StringBuilder();
                stringBuilder3.append("Failed to retrieve IO stream : ");
                stringBuilder3.append(e2.toString());
                Log.e(TAG3, stringBuilder3.toString());
                stringBuilder3 = new StringBuilder();
                stringBuilder3.append("Failed to retrieve IO stream : ");
                stringBuilder3.append(e2.toString());
                throw new LxdException(stringBuilder3.toString());
            }
        }
        Log.e(TAG(), "Failed to connect to server!");
        throw new LxdException("Failed to connect to server!");
    }

    private void initialize() {
        postIpcMessage(0, null);
    }

    private void process() {
        while (this.mKeepConnection) {
            try {
                byte[] bArr;
                int readUnsignedByte = this.mInputStream.readUnsignedByte();
                int readUnsignedByte2 = this.mInputStream.readUnsignedByte();
                int readInt = this.mInputStream.readInt();
                if (readInt > 0) {
                    bArr = new byte[readInt];
                    if (readInt != this.mInputStream.read(bArr, 0, readInt)) {
                        StringBuilder stringBuilder = new StringBuilder();
                        stringBuilder.append("Failed to read body of length ");
                        stringBuilder.append(readInt);
                        throw new LxdException(stringBuilder.toString());
                    }
                }
                bArr = "no message".getBytes();
                String TAG = TAG();
                StringBuilder stringBuilder2 = new StringBuilder();
                stringBuilder2.append("cmd: ");
                stringBuilder2.append(Integer.toHexString(readUnsignedByte));
                stringBuilder2.append(", status: ");
                stringBuilder2.append(Integer.toHexString(readUnsignedByte2));
                stringBuilder2.append(", ");
                stringBuilder2.append(new String(bArr));
                Log.d(TAG, stringBuilder2.toString());
                if (readUnsignedByte >= 128) {
                    processNotify(readUnsignedByte, readUnsignedByte2, bArr);
                } else if (128 > readUnsignedByte2 || readUnsignedByte2 >= IpcMessage.STATUS_TYPE_BASE_RES_2) {
                    processResponse(readUnsignedByte, readUnsignedByte2, bArr);
                } else {
                    processRequest(readUnsignedByte, readUnsignedByte2, bArr);
                }
            } catch (Exception e) {
                String TAG2 = TAG();
                StringBuilder stringBuilder3 = new StringBuilder();
                stringBuilder3.append("process: ");
                stringBuilder3.append(e.toString());
                Log.e(TAG2, stringBuilder3.toString());
                e.printStackTrace();
                stringBuilder3 = new StringBuilder();
                stringBuilder3.append("Socket Control Channel disconnected: ");
                stringBuilder3.append(e.toString());
                throw new LxdException(stringBuilder3.toString());
            } catch (Throwable th) {
                close();
            }
        }
        close();
    }

    private void processNotify(int i, int i2, byte[] bArr) {
        switch (i) {
            case 128:
                this.mChannelImpl.processNotifyLowMemory(bArr);
                return;
            case 129:
                this.mChannelImpl.processNotifyMonitoring(bArr);
                return;
            default:
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append("not defined cmd: ");
                stringBuilder.append(i);
                stringBuilder.append(", ");
                stringBuilder.append(new String(bArr));
                throw new LxdException(stringBuilder.toString());
        }
    }

    private void processRequest(int i, int i2, byte[] bArr) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("not defined cmd: ");
        stringBuilder.append(i);
        stringBuilder.append(", ");
        stringBuilder.append(new String(bArr));
        throw new LxdException(stringBuilder.toString());
    }

    private void processResponse(int i, int i2, byte[] bArr) {
        if (i2 == 0 || i == 33 || i == 34 || i == 7 || i == 8 || i == 9 || i == 37 || i == 41) {
            if (i != 37) {
                switch (i) {
                    case 0:
                        processResponseGetVersion(bArr, i2);
                        break;
                    case 1:
                        this.mChannelImpl.processResponseStartContainer();
                        break;
                    default:
                        switch (i) {
                            case 3:
                                this.mChannelImpl.processResponseCgroupFreezer(bArr);
                                break;
                            case 4:
                                this.mChannelImpl.processResponseStopContainer();
                                break;
                            case 5:
                            case 6:
                            case 8:
                            case 9:
                                break;
                            case 7:
                                this.mChannelImpl.processResponseImageSizeUpdate(bArr, i2);
                                break;
                            case 10:
                                this.mChannelImpl.processResponseSetConfig();
                                break;
                            case 11:
                                this.mChannelImpl.processResponseImagePath();
                                break;
                            case 12:
                                this.mChannelImpl.processResponseSharedDir();
                                break;
                            case 13:
                                this.mChannelImpl.processResponseMemorySize();
                                break;
                            case 14:
                                this.mChannelImpl.processResponseCpuBig();
                                break;
                            case 15:
                                this.mChannelImpl.processResponseCpuLittle();
                                break;
                            default:
                                switch (i) {
                                    case 17:
                                        this.mChannelImpl.processResponseGuiMode();
                                        break;
                                    case 18:
                                        this.mChannelImpl.processResponseTerminalMode();
                                        break;
                                    case 19:
                                        this.mChannelImpl.processResponseAdbTcpIpMode();
                                        break;
                                    case 20:
                                        this.mChannelImpl.processResponseSetTimeZone();
                                        break;
                                    case 21:
                                        this.mChannelImpl.processResponseScreenSize();
                                        break;
                                    default:
                                        switch (i) {
                                            case 32:
                                                this.mChannelImpl.processResponseSetConfigDone();
                                                break;
                                            case 33:
                                                this.mChannelImpl.processResponseGetImageVersion(bArr, i2);
                                                break;
                                            case 34:
                                                this.mChannelImpl.processResponseGetImageMinSize(bArr, i2);
                                                break;
                                            case 35:
                                                this.mChannelImpl.processResponseGetDebugLog(bArr, i2);
                                                break;
                                            default:
                                                switch (i) {
                                                    case 41:
                                                        this.mChannelImpl.processResponseGetCGroupMemoryUsage(bArr, i2);
                                                        break;
                                                    case 42:
                                                        this.mChannelImpl.processResponseStartMonitoring();
                                                        break;
                                                    case 43:
                                                        this.mChannelImpl.processResponseStopMonitoring();
                                                        break;
                                                    default:
                                                        StringBuilder stringBuilder = new StringBuilder();
                                                        stringBuilder.append("not defined cmd: ");
                                                        stringBuilder.append(i);
                                                        stringBuilder.append(", ");
                                                        stringBuilder.append(new String(bArr));
                                                        throw new LxdException(stringBuilder.toString());
                                                }
                                        }
                                }
                        }
                }
            }
            this.mChannelImpl.processResponseRebaseImage(bArr, i2);
            if (this.mRemoteResponseTimer != null) {
                this.mRemoteResponseTimer.cancel();
                this.mRemoteResponseTimer = null;
            }
            this.mWaitingForResponse = false;
            sendIpcMessage();
            return;
        }
        StringBuilder stringBuilder2 = new StringBuilder();
        stringBuilder2.append(i2);
        stringBuilder2.append(", cmd: ");
        stringBuilder2.append(Integer.toHexString(i));
        stringBuilder2.append(", ");
        stringBuilder2.append(new String(bArr));
        throw new LxdException(stringBuilder2.toString());
    }

    private synchronized void close() {
        if (this.mControlSocket != null) {
            try {
                this.mControlSocket.shutdownInput();
                this.mControlSocket.shutdownOutput();
                this.mControlSocket.close();
                this.mControlSocket = null;
            } catch (Exception e) {
                e.printStackTrace();
            }
            this.mListener.onServiceClosed();
        }
    }

    private void handleException(Throwable th) {
        if (this.mKeepConnection) {
            String TAG = TAG();
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("onServiceError ");
            stringBuilder.append(th.toString());
            Log.i(TAG, stringBuilder.toString());
            this.mChannelImpl.handleException();
            this.mListener.onServiceError(th);
        }
    }

    private IControlChannelImpl getChannelImpl(int i) {
        String TAG = TAG();
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("getControlChannel: type ");
        stringBuilder.append(this.mChannelType);
        stringBuilder.append(" version ");
        stringBuilder.append(i);
        Log.i(TAG, stringBuilder.toString());
        StringBuilder stringBuilder2;
        switch (this.mChannelType) {
            case NST:
                switch (i) {
                    case 1:
                        return new NstControlChannelV1(this);
                    case 2:
                        return new NstControlChannelV2(this);
                    case 3:
                        return new NstControlChannelV3(this);
                    case 4:
                        return new NstControlChannelV4(this);
                    case 5:
                        return new NstControlChannelV5(this);
                    default:
                        stringBuilder2 = new StringBuilder();
                        stringBuilder2.append("Invalid version : ");
                        stringBuilder2.append(i);
                        throw new LxdException(stringBuilder2.toString());
                }
            case CONTAINER:
                if (i == 5) {
                    return new ContainerControlChannelV5(this);
                }
                stringBuilder2 = new StringBuilder();
                stringBuilder2.append("Invalid version : ");
                stringBuilder2.append(i);
                throw new LxdException(stringBuilder2.toString());
            default:
                stringBuilder2 = new StringBuilder();
                stringBuilder2.append("Invalid type : ");
                stringBuilder2.append(this.mChannelType);
                throw new LxdException(stringBuilder2.toString());
        }
    }

    private void processResponseGetVersion(byte[] bArr, int i) {
        Log.e(TAG(), "processResponseGetVersion");
        this.mChannelImpl = getChannelImpl(Integer.valueOf(new String(bArr)).intValue());
        this.mRemoteSyncTimer = new Timer();
        this.mRemoteSyncTimer.schedule(new TimerTask() {
            public void run() {
                SocketControlChannel.this.postIpcMessage(5, null, true, true);
            }
        }, 0, 300000);
        this.mListener.onServiceOpened();
    }

    public ICommonContext getCommonContext() {
        return this.mCommonContext;
    }

    public ICallback getListener() {
        return this.mListener;
    }

    public boolean keepConnection() {
        return this.mKeepConnection;
    }

    public void postIpcMessage(int i, String str) {
        postIpcMessage(i, str, true, false);
    }

    /* JADX WARNING: Missing block: B:8:0x0043, code:
            r0 = new com.samsung.android.lxd.processor.control.channel.socket.SocketControlChannel.RequestMessage(r3, r4, r5, r6);
     */
    /* JADX WARNING: Missing block: B:9:0x0048, code:
            if (r7 == false) goto L_0x0054;
     */
    /* JADX WARNING: Missing block: B:10:0x004a, code:
            r3.mRequestMap.put(java.lang.Integer.valueOf(r4), r0);
     */
    /* JADX WARNING: Missing block: B:11:0x0054, code:
            r3.mRequestMap.put(r0, r0);
     */
    /* JADX WARNING: Missing block: B:12:0x0059, code:
            sendIpcMessage();
     */
    /* JADX WARNING: Missing block: B:13:0x005c, code:
            return;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void postIpcMessage(int i, String str, boolean z, boolean z2) {
        String TAG = TAG();
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("postIpcMessage: ");
        stringBuilder.append(Integer.toHexString(i));
        stringBuilder.append(", ");
        stringBuilder.append(str);
        stringBuilder.append(", ");
        stringBuilder.append(z);
        stringBuilder.append(", ");
        stringBuilder.append(z2);
        Log.d(TAG, stringBuilder.toString());
        synchronized (this) {
            if (this.mControlSocket == null || !this.mControlSocket.isConnected()) {
                String TAG2 = TAG();
                StringBuilder stringBuilder2 = new StringBuilder();
                stringBuilder2.append("no connection. don't postIpcMessage: socket status: ");
                stringBuilder2.append(this.mWaitingForResponse);
                Log.i(TAG2, stringBuilder2.toString());
            }
        }
    }

    private synchronized void sendIpcMessage() {
        if (this.mWaitingForResponse || this.mRequestMap.isEmpty() || !this.mActive) {
            String TAG = TAG();
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("don't sendIpcMessage: request: ");
            stringBuilder.append(this.mWaitingForResponse);
            stringBuilder.append(", size: ");
            stringBuilder.append(this.mRequestMap.size());
            stringBuilder.append(", active: ");
            stringBuilder.append(this.mActive);
            Log.d(TAG, stringBuilder.toString());
            return;
        }
        this.mWaitingForResponse = true;
        Object obj = this.mRequestMap.keySet().toArray()[0];
        RequestMessage requestMessage = (RequestMessage) this.mRequestMap.remove(obj);
        String TAG2;
        StringBuilder stringBuilder2;
        if (requestMessage == null) {
            TAG2 = TAG();
            stringBuilder2 = new StringBuilder();
            stringBuilder2.append("don't sendIpcMessage: requestMessage null ");
            stringBuilder2.append(obj);
            Log.e(TAG2, stringBuilder2.toString());
            stringBuilder2 = new StringBuilder();
            stringBuilder2.append("don't sendIpcMessage: requestMessage null ");
            stringBuilder2.append(obj);
            throw new LxdException(stringBuilder2.toString());
        }
        byte[] bArr;
        if (requestMessage.getNeedResponseTimer()) {
            this.mRemoteResponseTimer = new Timer();
            this.mRemoteResponseTimer.schedule(new TimerTask() {
                public void run() {
                    if (SocketControlChannel.this.mActive) {
                        Log.e(SocketControlChannel.this.TAG(), "no response!");
                        SocketControlChannel.this.handleException(new LxdException("no response!!"));
                    }
                }
            }, 30000);
        }
        int requestCmd = requestMessage.getRequestCmd();
        if (requestMessage.getRequestMessage() == null) {
            bArr = null;
        } else {
            bArr = requestMessage.getRequestMessage().getBytes();
        }
        IpcMessage ipcMessage = new IpcMessage(requestCmd, bArr);
        String TAG3 = TAG();
        StringBuilder stringBuilder3 = new StringBuilder();
        stringBuilder3.append("sendIpcMessage: cmd: : ");
        stringBuilder3.append(Integer.toHexString(requestMessage.getRequestCmd()));
        stringBuilder3.append(", msg: ");
        stringBuilder3.append(requestMessage.getRequestMessage());
        stringBuilder3.append(", remaining: ");
        stringBuilder3.append(this.mRequestMap.size());
        Log.d(TAG3, stringBuilder3.toString());
        try {
            this.mOutputStream.write(ipcMessage.createIpcMessage());
        } catch (Exception e) {
            TAG2 = TAG();
            stringBuilder2 = new StringBuilder();
            stringBuilder2.append("Failed to send ipc message : ");
            stringBuilder2.append(e.toString());
            Log.e(TAG2, stringBuilder2.toString());
            stringBuilder2 = new StringBuilder();
            stringBuilder2.append("Failed to send ipc message : ");
            stringBuilder2.append(e.toString());
            throw new LxdException(stringBuilder2.toString());
        }
    }
}
