package com.samsung.android.lxd.processor.control.channel.socket.nst;

import com.samsung.android.lxd.a.o;
import com.samsung.android.lxd.processor.control.channel.socket.IControlChannelHelper;
import com.samsung.android.lxd.processor.utils.log.Log;

public class NstControlChannelV3 extends NstControlChannelV2 {
    private final int VERSION = 3;

    public int getVersion() {
        return 3;
    }

    public NstControlChannelV3(IControlChannelHelper iControlChannelHelper) {
        super(iControlChannelHelper);
    }

    public void processResponseSharedDir() {
        int parseInt = (Integer.parseInt(this.mMemorySize) + Integer.parseInt(this.mSwapMemory)) - 200;
        int i = parseInt - 200;
        int i2 = i - 200;
        IControlChannelHelper iControlChannelHelper = this.mChannelHelper;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(this.mMemorySize);
        stringBuilder.append("|");
        stringBuilder.append(this.mSwapMemory);
        stringBuilder.append("|");
        stringBuilder.append(Integer.toString(i2));
        stringBuilder.append("M");
        stringBuilder.append("|");
        stringBuilder.append(Integer.toString(i));
        stringBuilder.append("M");
        stringBuilder.append("|");
        stringBuilder.append(Integer.toString(parseInt));
        stringBuilder.append("M");
        iControlChannelHelper.postIpcMessage(13, stringBuilder.toString());
    }

    public void changeImageSize(String str, int i, boolean z) {
        String str2;
        StringBuilder stringBuilder;
        int f = o.f(str);
        int b = o.b(i);
        String str3 = this.TAG;
        StringBuilder stringBuilder2 = new StringBuilder();
        stringBuilder2.append("changeImageSize, path: ");
        stringBuilder2.append(str);
        stringBuilder2.append(", request Gb size: ");
        stringBuilder2.append(i);
        stringBuilder2.append(", cur Mb size: ");
        stringBuilder2.append(f);
        stringBuilder2.append(", increasingType: ");
        stringBuilder2.append(z);
        Log.d(str3, stringBuilder2.toString());
        if (z && b >= f + 512) {
            b = f + 256;
            str2 = this.TAG;
            stringBuilder = new StringBuilder();
            stringBuilder.append("updated request Mb: ");
            stringBuilder.append(b);
            stringBuilder.append(", based on MB unit size");
            Log.d(str2, stringBuilder.toString());
        }
        if (b > f && b - f < 129) {
            b = f + 129;
            str2 = this.TAG;
            stringBuilder = new StringBuilder();
            stringBuilder.append("updated request Mb: ");
            stringBuilder.append(b);
            stringBuilder.append(", due to resize2fs min value");
            Log.i(str2, stringBuilder.toString());
        }
        IControlChannelHelper iControlChannelHelper = this.mChannelHelper;
        stringBuilder = new StringBuilder();
        stringBuilder.append(str);
        stringBuilder.append("|");
        stringBuilder.append(Integer.toString(b));
        iControlChannelHelper.postIpcMessage(7, stringBuilder.toString(), false, false);
    }

    public void getDebugLog(String str) {
        String str2 = this.TAG;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("getDebugLog: ");
        stringBuilder.append(str);
        Log.d(str2, stringBuilder.toString());
        this.mChannelHelper.postIpcMessage(35, str, false, true);
    }

    public void rebaseImage(String str) {
        String str2 = this.TAG;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("rebaseImage: ");
        stringBuilder.append(str);
        Log.d(str2, stringBuilder.toString());
        IControlChannelHelper iControlChannelHelper = this.mChannelHelper;
        StringBuilder stringBuilder2 = new StringBuilder();
        stringBuilder2.append(str);
        stringBuilder2.append("|");
        stringBuilder2.append("/data/lxd/lxd_loader");
        iControlChannelHelper.postIpcMessage(37, stringBuilder2.toString(), false, true);
    }

    public void processResponseGetDebugLog(byte[] bArr, int i) {
        this.mChannelHelper.getListener().onDebugLogReceived(new String(bArr), i == 0);
    }

    public void processResponseGetImageVersion(byte[] bArr, int i) {
        i = i != 0 ? i != 13 ? i != 17 ? 1 : 5 : 4 : 0;
        this.mChannelHelper.getListener().onImageVersionReceived(new String(bArr), i);
    }

    public void processNotifyLowMemory(byte[] bArr) {
        String str = new String(bArr);
        String str2 = this.TAG;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("processNotifyLowMemory: ");
        stringBuilder.append(str);
        Log.d(str2, stringBuilder.toString());
        if (Integer.parseInt(str) > 3) {
            Log.e(this.TAG, "processNotifyLowMemory: limit count done");
        }
    }

    public void processResponseRebaseImage(byte[] bArr, int i) {
        this.mChannelHelper.getListener().onImageRebased(new String(bArr), i == 0);
    }
}
