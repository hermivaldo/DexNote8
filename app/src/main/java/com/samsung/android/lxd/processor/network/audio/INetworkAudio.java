package com.samsung.android.lxd.processor.network.audio;

import com.samsung.android.lxd.processor.ICommonContext;

public interface INetworkAudio {

    public interface ICallback {
        void onAudioConnected(String str);

        void onAudioDisconnected(String str);
    }

    NetworkAudioType getAudioType();

    String getConnectionInfo();

    INetworkAudio init(ICallback iCallback);

    INetworkAudio reStart();

    INetworkAudio setContext(ICommonContext iCommonContext);

    INetworkAudio start();

    INetworkAudio stop();
}
