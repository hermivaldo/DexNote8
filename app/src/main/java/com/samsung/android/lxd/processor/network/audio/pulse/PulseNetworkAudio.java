package com.samsung.android.lxd.processor.network.audio.pulse;

import android.os.FileObserver;
import com.samsung.android.lxd.processor.LxdException;
import com.samsung.android.lxd.processor.network.audio.INetworkAudio;
import com.samsung.android.lxd.processor.network.audio.NetworkAudio;
import com.samsung.android.lxd.processor.network.audio.NetworkAudioType;
import com.samsung.android.lxd.processor.network.audio.pulse.IPulseNetworkAudio.ICallback;
import com.samsung.android.lxd.processor.utils.log.Log;

public class PulseNetworkAudio extends NetworkAudio {
    private static final String SOCKET_DIR = "run";
    private static final String SOCKET_NAME = "lodaudio.usk";
    private static final String TAG = "PulseNetworkAudio";
    private ICallback mInnerListener = new DummyCallback(this, null);
    private Thread mInnerThread = null;
    private boolean mKeepConnected = true;
    private PulseProtocol mProtocol = null;
    private FileObserver mSocketObserver = new FileObserver("/data/lxd/run", 768) {
        public void onEvent(int i, String str) {
            if (!PulseNetworkAudio.SOCKET_NAME.equals(str)) {
                return;
            }
            if (i == 256) {
                PulseNetworkAudio.this.notifyProcessingThread();
            } else if (i == 512 && PulseNetworkAudio.this.mProtocol != null) {
                PulseNetworkAudio.this.mProtocol.close();
            }
        }
    };
    private boolean mThreadNotified = false;

    private class DummyCallback implements ICallback {
        public void onAudioConnected(String str) {
        }

        public void onAudioDisconnected(String str) {
        }

        private DummyCallback() {
        }

        /* synthetic */ DummyCallback(PulseNetworkAudio pulseNetworkAudio, AnonymousClass1 anonymousClass1) {
            this();
        }
    }

    private synchronized void notifyProcessingThread() {
        this.mThreadNotified = true;
        notifyAll();
    }

    private void attachListener() {
        this.mInnerListener = new ICallback() {
            public void onAudioConnected(String str) {
                PulseNetworkAudio.this.mListener.onAudioConnected(str);
            }

            public void onAudioDisconnected(String str) {
                PulseNetworkAudio.this.mListener.onAudioDisconnected(str);
            }
        };
    }

    private void detachListener() {
        this.mInnerListener = new DummyCallback(this, null);
    }

    public NetworkAudioType getAudioType() {
        return NetworkAudioType.PULSE;
    }

    private synchronized void waitForNotification() {
        while (true) {
            try {
            } catch (InterruptedException unused) {
                if (!this.mThreadNotified) {
                    wait();
                    Log.d(TAG, "Got notification about audio socket");
                } else {
                    return;
                }
            }
        }
    }

    public INetworkAudio init(INetworkAudio.ICallback iCallback) {
        super.init(iCallback);
        return this;
    }

    public INetworkAudio start() {
        stop();
        super.start();
        attachListener();
        this.mKeepConnected = true;
        this.mInnerThread = new Thread() {
            public void run() {
                PulseNetworkAudio.this.processingThreadAction();
            }
        };
        this.mInnerThread.start();
        this.mSocketObserver.startWatching();
        return this;
    }

    public INetworkAudio reStart() {
        stop();
        this.mKeepConnected = true;
        this.mInnerThread = new Thread() {
            public void run() {
                PulseNetworkAudio.this.processingThreadAction();
            }
        };
        this.mInnerThread.start();
        this.mSocketObserver.startWatching();
        return this;
    }

    public INetworkAudio stop() {
        Log.d(TAG, "stop");
        super.stop();
        this.mKeepConnected = false;
        if (this.mProtocol != null) {
            this.mProtocol.close();
        }
        try {
            if (this.mInnerThread != null) {
                this.mThreadNotified = true;
                this.mInnerThread.interrupt();
                Log.i(TAG, "before join");
                this.mInnerThread.join(300);
                Log.i(TAG, "after join");
                this.mInnerThread = null;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        detachListener();
        this.mSocketObserver.stopWatching();
        return this;
    }

    private void processingThreadAction() {
        while (this.mKeepConnected) {
            if (!PulseProtocol.isReadyToConnect()) {
                this.mThreadNotified = false;
                Log.d(TAG, "Waiting for a socket notification");
                waitForNotification();
            }
            try {
                if (this.mKeepConnected) {
                    Log.d(TAG, "Trying to connect");
                    connect(this.mInnerListener);
                    process();
                }
                if (this.mProtocol == null) {
                }
            } catch (Throwable th) {
                if (this.mProtocol != null) {
                    this.mProtocol.close();
                }
            }
            this.mProtocol.close();
        }
    }

    private void connect(ICallback iCallback) {
        this.mProtocol = new PulseProtocol();
        this.mProtocol.connect();
        iCallback.onAudioConnected(getConnectionInfo());
    }

    private void process() {
        try {
            this.mProtocol.process();
            Log.d(TAG, "Closing connection");
            this.mProtocol.close();
        } catch (Exception e) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("process : ");
            stringBuilder.append(e.toString());
            throw new LxdException(stringBuilder.toString());
        } catch (Throwable th) {
            Log.d(TAG, "Closing connection");
            this.mProtocol.close();
        }
    }
}
