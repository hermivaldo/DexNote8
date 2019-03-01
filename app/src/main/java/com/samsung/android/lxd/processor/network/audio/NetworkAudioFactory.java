package com.samsung.android.lxd.processor.network.audio;

import com.samsung.android.lxd.processor.network.audio.pulse.PulseNetworkAudio;
import java.util.HashMap;
import java.util.Map;

public class NetworkAudioFactory {
    private static Map<NetworkAudioType, INetworkAudio> mAudioList = new HashMap();

    static {
        mAudioList.put(NetworkAudioType.PULSE, new PulseNetworkAudio());
    }

    public static INetworkAudio getInstance(NetworkAudioType networkAudioType) {
        return (INetworkAudio) mAudioList.get(networkAudioType);
    }
}
