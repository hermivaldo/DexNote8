package com.samsung.android.lxd.processor.network.channel;

import com.samsung.android.lxd.processor.network.channel.pty.PtyNetworkChannel;
import com.samsung.android.lxd.processor.network.channel.rfb.RfbNetworkChannel;
import java.util.HashMap;
import java.util.Map;

public class NetworkChannelFactory {
    private static Map<NetworkChannelType, INetworkChannel> mChannelList = new HashMap();

    static {
        mChannelList.put(NetworkChannelType.PTY, new PtyNetworkChannel());
        mChannelList.put(NetworkChannelType.RFB, new RfbNetworkChannel());
    }

    public static INetworkChannel getInstance(NetworkChannelType networkChannelType) {
        return (INetworkChannel) mChannelList.get(networkChannelType);
    }
}
