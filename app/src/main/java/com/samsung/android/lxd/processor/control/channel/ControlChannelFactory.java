package com.samsung.android.lxd.processor.control.channel;

import java.util.HashMap;
import java.util.Map;

public class ControlChannelFactory {
    private static Map<ControlChannelType, IControlChannel> mControlChannelList = new HashMap();

    static {
        mControlChannelList.put(ControlChannelType.NST, new ControlChannelManager(ControlChannelType.NST));
        mControlChannelList.put(ControlChannelType.CONTAINER, new ControlChannelManager(ControlChannelType.CONTAINER));
    }

    public static IControlChannel getInstance(ControlChannelType controlChannelType) {
        return (IControlChannel) mControlChannelList.get(controlChannelType);
    }
}
