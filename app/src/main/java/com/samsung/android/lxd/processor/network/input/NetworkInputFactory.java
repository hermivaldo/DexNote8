package com.samsung.android.lxd.processor.network.input;

import com.samsung.android.lxd.processor.network.input.pty.PtyNetworkInput;
import com.samsung.android.lxd.processor.network.input.rfb.RfbNetworkInput;
import java.util.HashMap;
import java.util.Map;

public class NetworkInputFactory {
    private static Map<NetworkInputType, INetworkInput> mInputList = new HashMap();

    static {
        mInputList.put(NetworkInputType.PTY, new PtyNetworkInput());
        mInputList.put(NetworkInputType.RFB, new RfbNetworkInput());
    }

    public static INetworkInput getInstance(NetworkInputType networkInputType) {
        return (INetworkInput) mInputList.get(networkInputType);
    }
}
