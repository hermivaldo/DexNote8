package com.samsung.android.lxd.processor.control.channel.socket;

import com.samsung.android.lxd.processor.ICommonContext;
import com.samsung.android.lxd.processor.control.channel.IControlChannel.ICallback;

public interface IControlChannelHelper {
    ICommonContext getCommonContext();

    ICallback getListener();

    boolean keepConnection();

    void postIpcMessage(int i, String str);

    void postIpcMessage(int i, String str, boolean z, boolean z2);
}
