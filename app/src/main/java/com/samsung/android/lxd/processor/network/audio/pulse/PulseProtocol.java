package com.samsung.android.lxd.processor.network.audio.pulse;

import android.media.AudioAttributes;
import android.media.AudioFormat;
import android.media.AudioTrack;
import android.media.AudioTrack.Builder;
import android.net.LocalSocket;
import android.net.LocalSocketAddress;
import android.net.LocalSocketAddress.Namespace;
import com.samsung.android.lxd.processor.utils.Utils;
import com.samsung.android.lxd.processor.utils.log.Log;
import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.File;
import java.io.IOException;

public class PulseProtocol {
    private static final int MAX_TRY_COUNT = 2;
    private static final int RECONNECT_INTERVAL = 1000;
    private static final int SAMPLE_RATE = 44100;
    private static final String SOCKET_ADDRESS = "run/lodaudio.usk";
    private static final String TAG = "PulseProtocol";
    private boolean mClosed = false;
    private DataInputStream mInputStream;
    private boolean mKeepRetryingToConnect = false;
    private LocalSocket mPulseSocket = new LocalSocket();

    public static boolean isReadyToConnect() {
        return new File("/data/lxd/run/lodaudio.usk").exists();
    }

    synchronized void close() {
        if (!this.mClosed) {
            try {
                this.mKeepRetryingToConnect = false;
                this.mPulseSocket.shutdownInput();
                this.mPulseSocket.close();
                this.mPulseSocket = null;
                this.mInputStream = null;
                this.mClosed = true;
                Log.d(TAG, "Closed successfully");
            } catch (Exception e) {
                String str = TAG;
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append("close: ");
                stringBuilder.append(e.toString());
                Log.d(str, stringBuilder.toString());
                e.printStackTrace();
            }
        } else {
            return;
        }
        return;
    }

    void connect() {
        File file = new File("/data/lxd/run/lodaudio.usk");
        LocalSocketAddress localSocketAddress = new LocalSocketAddress(file.getPath(), Namespace.FILESYSTEM);
        Log.i(TAG, "Connecting...");
        this.mKeepRetryingToConnect = true;
        int i = 0;
        while (i < 2 && this.mKeepRetryingToConnect) {
            if (file.exists()) {
                try {
                    this.mPulseSocket.connect(localSocketAddress);
                    if (this.mPulseSocket.isConnected()) {
                        break;
                    }
                } catch (IOException unused) {
                    Log.d(TAG, "Not connected to server!");
                }
            }
            try {
                Thread.sleep(1000);
                String str = TAG;
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append("Reconnecting to server...");
                stringBuilder.append(i);
                Log.d(str, stringBuilder.toString());
                i++;
            } catch (Exception unused2) {
                Log.e(TAG, "Failed to put thread to sleep!");
            }
        }
        if (this.mPulseSocket.isConnected()) {
            initializeIoStream();
        } else {
            Log.d(TAG, "Failed to connect to server!");
            throw new Exception("Failed to connect to server!");
        }
    }

    boolean isBufferEmpty(byte[] bArr) {
        for (byte b : bArr) {
            if (b != (byte) 0) {
                return false;
            }
        }
        return true;
    }

    void process() {
        int minBufferSize = AudioTrack.getMinBufferSize(SAMPLE_RATE, 12, 2);
        AudioTrack build = new Builder().setAudioAttributes(new AudioAttributes.Builder().setUsage(1).setContentType(2).build()).setAudioFormat(new AudioFormat.Builder().setEncoding(2).setSampleRate(SAMPLE_RATE).setChannelMask(12).build()).setBufferSizeInBytes(minBufferSize).setTransferMode(1).build();
        build.play();
        byte[] bArr = new byte[minBufferSize];
        while (this.mKeepRetryingToConnect) {
            int read = this.mInputStream.read(bArr, 0, minBufferSize);
            if (!isBufferEmpty(bArr)) {
                read = build.write(bArr, 0, read);
                if (Utils.isDebug()) {
                    String str = TAG;
                    StringBuilder stringBuilder = new StringBuilder();
                    stringBuilder.append("sizeWrite: ");
                    stringBuilder.append(read);
                    Log.d(str, stringBuilder.toString());
                }
            }
        }
        Log.i(TAG, "connect end...");
        build.stop();
    }

    private void initializeIoStream() {
        this.mInputStream = new DataInputStream(new BufferedInputStream(this.mPulseSocket.getInputStream()));
    }
}
