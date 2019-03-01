package com.samsung.android.lxd.processor.network.audio.pulse;

import com.samsung.android.lxd.processor.network.audio.INetworkAudio;

public interface IPulseNetworkAudio extends INetworkAudio {

    public interface ICallback {
        void onAudioConnected(String str);

        void onAudioDisconnected(String str);
    }
}
