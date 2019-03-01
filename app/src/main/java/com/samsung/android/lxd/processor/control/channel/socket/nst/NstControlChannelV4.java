package com.samsung.android.lxd.processor.control.channel.socket.nst;

import com.samsung.android.lxd.processor.control.channel.socket.IControlChannelHelper;
import com.samsung.android.lxd.processor.utils.log.Log;
import java.util.Timer;
import java.util.TimerTask;

public class NstControlChannelV4 extends NstControlChannelV3 {
    private final int CGROUP_MEMORY_MONITOR_INTERVAL = 30000;
    private final String PAYLOAD_GET_CGROUP_MEMORY_USAGE = "GET_CGROUP_MEMORY_USAGE";
    private final int VERSION = 4;
    private Timer mCGroupMemoryMonitorTimer = null;
    private int mCurrentCGroupMemoryStage = 0;

    public int getVersion() {
        return 4;
    }

    public NstControlChannelV4(IControlChannelHelper iControlChannelHelper) {
        super(iControlChannelHelper);
    }

    public void closeService() {
        super.closeService();
        resetCGroupMemoryMonitor();
    }

    public void processResponseStartContainer() {
        super.processResponseStartContainer();
        setCGroupMemoryMonitor();
    }

    public void processResponseStopContainer() {
        super.processResponseStopContainer();
        resetCGroupMemoryMonitor();
    }

    public void processResponseGetCGroupMemoryUsage(byte[] bArr, int i) {
        String str = new String(bArr);
        String str2 = this.TAG;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("processResponseGetCGroupMemoryUsage: ");
        stringBuilder.append(i);
        stringBuilder.append(", ");
        stringBuilder.append(str);
        Log.d(str2, stringBuilder.toString());
        if (i == 0) {
            int parseInt = Integer.parseInt(this.mMemorySize) + Integer.parseInt(this.mSwapMemory);
            i = Integer.parseInt(str.replaceAll("M", ""));
            int i2 = parseInt - i;
            String str3 = this.TAG;
            StringBuilder stringBuilder2 = new StringBuilder();
            stringBuilder2.append("memorySize: ");
            stringBuilder2.append(parseInt);
            stringBuilder2.append(", usedMemory: ");
            stringBuilder2.append(i);
            stringBuilder2.append(", available memory: ");
            stringBuilder2.append(i2);
            stringBuilder2.append(", current stage: ");
            stringBuilder2.append(this.mCurrentCGroupMemoryStage);
            Log.i(str3, stringBuilder2.toString());
            parseInt = 0;
            i = 0;
            while (i < 3) {
                int i3 = i + 1;
                if (i2 <= i3 * 200) {
                    parseInt = 3 - i;
                    break;
                }
                i = i3;
            }
            if (parseInt > this.mCurrentCGroupMemoryStage) {
                this.mCurrentCGroupMemoryStage = parseInt;
                this.mChannelHelper.getListener().onMemoryUsageReceived(Integer.toString(i2), this.mCurrentCGroupMemoryStage);
            }
        }
    }

    private void resetCGroupMemoryMonitor() {
        if (this.mCGroupMemoryMonitorTimer != null) {
            Log.d(this.TAG, "resetCGroupMonitor");
            this.mCGroupMemoryMonitorTimer.cancel();
            this.mCGroupMemoryMonitorTimer = null;
        }
        this.mCurrentCGroupMemoryStage = 0;
    }

    private void setCGroupMemoryMonitor() {
        resetCGroupMemoryMonitor();
        Log.d(this.TAG, "setCGroupMemoryMonitor");
        this.mCGroupMemoryMonitorTimer = new Timer();
        this.mCGroupMemoryMonitorTimer.schedule(new TimerTask() {
            public void run() {
                NstControlChannelV4.this.mChannelHelper.postIpcMessage(41, "GET_CGROUP_MEMORY_USAGE", true, true);
            }
        }, 0, 30000);
    }
}
