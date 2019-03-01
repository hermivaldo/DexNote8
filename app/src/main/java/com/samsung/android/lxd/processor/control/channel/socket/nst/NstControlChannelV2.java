package com.samsung.android.lxd.processor.control.channel.socket.nst;

import com.samsung.android.lxd.processor.ExecutionType;
import com.samsung.android.lxd.processor.control.channel.socket.IControlChannelHelper;
import java.util.TimeZone;

public class NstControlChannelV2 extends NstControlChannelV1 {
    private final int VERSION = 2;

    public int getVersion() {
        return 2;
    }

    public NstControlChannelV2(IControlChannelHelper iControlChannelHelper) {
        super(iControlChannelHelper);
    }

    public void processResponseCpuLittle() {
        this.mChannelHelper.postIpcMessage(21, this.mChannelHelper.getCommonContext().getScreenType().getSize());
    }

    public void processResponseScreenSize() {
        this.mChannelHelper.postIpcMessage(20, TimeZone.getDefault().getID());
    }

    public void processResponseSetTimeZone() {
        if (this.mExecutionType == ExecutionType.CLI) {
            this.mChannelHelper.postIpcMessage(18, this.mOpenInfo);
        } else {
            this.mChannelHelper.postIpcMessage(17, this.mOpenInfo);
        }
    }

    public void processResponseGuiMode() {
        super.processResponseSetTimeZone();
    }

    public void processResponseTerminalMode() {
        super.processResponseSetTimeZone();
    }
}
