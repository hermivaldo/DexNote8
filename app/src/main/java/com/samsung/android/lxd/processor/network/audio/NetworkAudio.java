package com.samsung.android.lxd.processor.network.audio;

import com.samsung.android.lxd.processor.ICommonContext;
import com.samsung.android.lxd.processor.network.audio.INetworkAudio.ICallback;
import com.samsung.android.lxd.processor.utils.log.Log;

public abstract class NetworkAudio implements INetworkAudio {
    private static final String TAG = "NetworkAudio";
    protected NetworkAudioType mAudioType;
    protected ICommonContext mCommonContext;
    protected ICallback mListener;

    public NetworkAudio() {
        this.mAudioType = null;
        this.mCommonContext = null;
        this.mListener = null;
        this.mAudioType = getAudioType();
    }

    public String getConnectionInfo() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Audio: ");
        stringBuilder.append(getAudioType().toString());
        return stringBuilder.toString();
    }

    public INetworkAudio init(ICallback iCallback) {
        Log.d(TAG, "init");
        this.mListener = iCallback;
        return this;
    }

    public INetworkAudio start() {
        Log.d(TAG, "start");
        return this;
    }

    public INetworkAudio stop() {
        Log.d(TAG, "stop");
        return this;
    }

    public INetworkAudio setContext(ICommonContext iCommonContext) {
        this.mCommonContext = iCommonContext;
        return this;
    }
}
