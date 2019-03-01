package com.samsung.android.lxd.processor.control.channel.socket.nst;

import com.samsung.android.lxd.processor.config.SystemConfigHelper;
import com.samsung.android.lxd.processor.control.channel.socket.IControlChannelHelper;
import com.samsung.android.lxd.processor.utils.log.Log;

public class NstControlChannelV5 extends NstControlChannelV4 {
    private final int VERSION = 5;

    public NstControlChannelV5(IControlChannelHelper iControlChannelHelper) {
        super(iControlChannelHelper);
    }

    public void setConfigId(String str) {
        Log.d(this.TAG, "setConfigId: ");
        super.setConfigId(str);
        this.mShareFolder = SystemConfigHelper.CONFIG_OPTION_ON;
    }
}
