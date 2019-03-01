package com.samsung.android.lxd.processor.network.display;

import java.util.HashMap;
import java.util.Map;

public class NetworkDisplayFactory {
    private static Map<NetworkDisplayType, INetworkDisplay> mDisplayList = new HashMap();

    public static INetworkDisplay setInstance(NetworkDisplayType networkDisplayType, INetworkDisplay iNetworkDisplay) {
        mDisplayList.put(networkDisplayType, iNetworkDisplay);
        return getInstance(networkDisplayType);
    }

    public static INetworkDisplay getInstance(NetworkDisplayType networkDisplayType) {
        return (INetworkDisplay) mDisplayList.get(networkDisplayType);
    }

    public static INetworkDisplay duplicate(INetworkDisplay iNetworkDisplay) {
        return ((INetworkDisplay) mDisplayList.get(iNetworkDisplay.getDisplayType())).init(iNetworkDisplay);
    }
}
