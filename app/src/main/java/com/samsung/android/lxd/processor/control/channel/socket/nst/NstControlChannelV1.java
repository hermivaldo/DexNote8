package com.samsung.android.lxd.processor.control.channel.socket.nst;

import android.net.LocalSocketAddress.Namespace;
import android.os.SemSystemProperties;
import com.samsung.android.lxd.a.i;
import com.samsung.android.lxd.a.o;
import com.samsung.android.lxd.processor.ExecutionType;
import com.samsung.android.lxd.processor.LxdException;
import com.samsung.android.lxd.processor.config.SystemConfig;
import com.samsung.android.lxd.processor.config.SystemConfigHelper;
import com.samsung.android.lxd.processor.config.SystemConfigManager;
import com.samsung.android.lxd.processor.config.SystemConfigType;
import com.samsung.android.lxd.processor.control.channel.socket.IControlChannelHelper;
import com.samsung.android.lxd.processor.control.channel.socket.IControlChannelImpl;
import com.samsung.android.lxd.processor.utils.log.Log;
import java.util.TimeZone;
import java.util.Timer;
import java.util.TimerTask;

public class NstControlChannelV1 implements IControlChannelImpl {
    private static final int INTERVAL_INITIALIZATION = 90000;
    private static final String NST_SOCK = "nst";
    private static final String PAYLOAD_ADB_TCPIP_MODE = "ADB_TCPIP_MODE";
    private static final String PAYLOAD_PAUSE_CONTAINER = "PAUSE";
    private static final String PAYLOAD_RESUME_CONTAINER = "RESUME";
    private static final String PAYLOAD_SET_CONFIG = "SET_CONFIG";
    private static final String PAYLOAD_SET_CONFIG_DONE = "SET_CONFIG_DONE";
    private static final String PAYLOAD_START_CONTAINER = "NST_START";
    private static final String PAYLOAD_STOP_CONTAINER = "NST_STOP";
    protected final String TAG = getClass().getSimpleName();
    private final int VERSION = 1;
    protected final IControlChannelHelper mChannelHelper;
    protected String mCpuBig = null;
    protected String mCpuLittle = null;
    protected boolean mEnableAdbTCPIP = false;
    protected ExecutionType mExecutionType = ExecutionType.INVALID;
    protected String mImagePath = null;
    protected Timer mInitializeTimer = null;
    protected String mMemorySize = null;
    protected String mOpenInfo = null;
    protected String mShareFolder = null;
    protected String mSwapMemory = null;

    public void getDebugLog(String str) {
    }

    public String getSocketName() {
        return NST_SOCK;
    }

    public int getVersion() {
        return 1;
    }

    public void processNotifyLowMemory(byte[] bArr) {
    }

    public void processNotifyMonitoring(byte[] bArr) {
    }

    public void processResponseGetCGroupMemoryUsage(byte[] bArr, int i) {
    }

    public void processResponseGetDebugLog(byte[] bArr, int i) {
    }

    public void processResponseGuiMode() {
    }

    public void processResponseRebaseImage(byte[] bArr, int i) {
    }

    public void processResponseScreenSize() {
    }

    public void processResponseStartMonitoring() {
    }

    public void processResponseStopMonitoring() {
    }

    public void processResponseTerminalMode() {
    }

    public void rebaseImage(String str) {
    }

    public void startMonitoring() {
    }

    public void stopMonitoring() {
    }

    public NstControlChannelV1(IControlChannelHelper iControlChannelHelper) {
        this.mChannelHelper = iControlChannelHelper;
    }

    public Namespace getSocketNamespace() {
        return Namespace.RESERVED;
    }

    public void openService() {
        SemSystemProperties.set("linux_on_dex", "start");
    }

    public void closeService() {
        if (this.mInitializeTimer != null) {
            this.mInitializeTimer.cancel();
            this.mInitializeTimer = null;
        }
    }

    public void handleException() {
        SemSystemProperties.set("linux_on_dex", "stop");
    }

    public void setConfigId(String str) {
        SystemConfig load = SystemConfigManager.load(Long.parseLong(str));
        if (load == null) {
            Log.e(this.TAG, "Failed to get system config from given id!");
            throw new LxdException("Failed to get system config from given id!");
        }
        String str2 = this.TAG;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("System Config : ");
        stringBuilder.append(load.dump());
        Log.d(str2, stringBuilder.toString());
        this.mMemorySize = i.a();
        this.mSwapMemory = i.b();
        this.mImagePath = load.get(SystemConfigType.IMAGE_PATH);
        this.mCpuBig = load.get(SystemConfigType.CPU_BIG);
        this.mCpuLittle = load.get(SystemConfigType.CPU_LITTLE);
        this.mShareFolder = load.get(SystemConfigType.SHARE_FOLDER);
        this.mEnableAdbTCPIP = o.s() ? SystemConfigHelper.isConfigOptionOn(load, SystemConfigType.ADB_WiFi) : false;
    }

    public void setExecutionType(ExecutionType executionType) {
        this.mExecutionType = executionType;
    }

    public void notifySdCardStatus(String str, String str2) {
        Log.d(this.TAG, "notifySdCardStatus");
        if (str.equals("android.intent.action.MEDIA_MOUNTED")) {
            this.mChannelHelper.postIpcMessage(8, str2);
        } else if (str.equals("android.intent.action.MEDIA_UNMOUNTED")) {
            this.mChannelHelper.postIpcMessage(9, str2);
        } else {
            Log.e(this.TAG, "Unknown intent action for notifySdCardStatus");
        }
    }

    public void openContainer(String str) {
        this.mOpenInfo = str;
        this.mChannelHelper.postIpcMessage(10, PAYLOAD_SET_CONFIG);
        try {
            this.mInitializeTimer = new Timer();
            this.mInitializeTimer.schedule(new TimerTask() {
                public void run() {
                    Log.d(NstControlChannelV1.this.TAG, "mInitializeTimer");
                    if (NstControlChannelV1.this.mChannelHelper.keepConnection()) {
                        Log.e(NstControlChannelV1.this.TAG, "Failed to initialize!");
                        NstControlChannelV1.this.mChannelHelper.getListener().onServiceError(new LxdException("Failed to initialize!"));
                    }
                }
            }, 90000);
        } catch (Exception e) {
            String str2 = this.TAG;
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("Failed to initialize : ");
            stringBuilder.append(e.toString());
            Log.e(str2, stringBuilder.toString());
            stringBuilder = new StringBuilder();
            stringBuilder.append("Failed to initialize : ");
            stringBuilder.append(e.toString());
            throw new LxdException(stringBuilder.toString());
        }
    }

    public void closeContainer() {
        if (this.mInitializeTimer != null) {
            this.mInitializeTimer.cancel();
            this.mInitializeTimer = null;
        }
        this.mChannelHelper.postIpcMessage(4, PAYLOAD_STOP_CONTAINER);
    }

    public void pauseContainer() {
        Log.d(this.TAG, "pauseContainer: ");
        this.mChannelHelper.postIpcMessage(3, PAYLOAD_PAUSE_CONTAINER, true, true);
    }

    public void resumeContainer() {
        Log.d(this.TAG, "resumeContainer: ");
        this.mChannelHelper.postIpcMessage(3, PAYLOAD_RESUME_CONTAINER, true, true);
    }

    public void getImageVersion(String str) {
        String str2 = this.TAG;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("getImageVersion, path: ");
        stringBuilder.append(str);
        Log.d(str2, stringBuilder.toString());
        if (o.d(str)) {
            this.mChannelHelper.postIpcMessage(33, str);
        } else {
            this.mChannelHelper.postIpcMessage(33, str, false, false);
        }
    }

    public void getImageMinSize(String str) {
        String str2 = this.TAG;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("getImageMinSize, path: ");
        stringBuilder.append(str);
        Log.d(str2, stringBuilder.toString());
        this.mChannelHelper.postIpcMessage(34, str);
    }

    public void changeImageSize(String str, int i, boolean z) {
        String str2 = this.TAG;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("changeImageSize, path: ");
        stringBuilder.append(str);
        stringBuilder.append(", size: ");
        stringBuilder.append(i);
        Log.d(str2, stringBuilder.toString());
        IControlChannelHelper iControlChannelHelper = this.mChannelHelper;
        StringBuilder stringBuilder2 = new StringBuilder();
        stringBuilder2.append(str);
        stringBuilder2.append("|");
        stringBuilder2.append(Integer.toString(i));
        iControlChannelHelper.postIpcMessage(7, stringBuilder2.toString(), false, false);
    }

    public void processResponseGetImageVersion(byte[] bArr, int i) {
        this.mChannelHelper.getListener().onImageVersionReceived(new String(bArr), i == 0 ? 0 : 4);
    }

    public void processResponseGetImageMinSize(byte[] bArr, int i) {
        this.mChannelHelper.getListener().onImageMinSizeReceived(new String(bArr), i == 0);
    }

    public void processResponseImageSizeUpdate(byte[] bArr, int i) {
        this.mChannelHelper.getListener().onImageSizeUpdated(new String(bArr), i == 0);
    }

    public void processResponseSetConfig() {
        this.mChannelHelper.postIpcMessage(11, this.mImagePath);
    }

    public void processResponseImagePath() {
        this.mChannelHelper.postIpcMessage(12, this.mShareFolder);
    }

    public void processResponseSharedDir() {
        IControlChannelHelper iControlChannelHelper = this.mChannelHelper;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(this.mMemorySize);
        stringBuilder.append("|");
        stringBuilder.append(this.mSwapMemory);
        iControlChannelHelper.postIpcMessage(13, stringBuilder.toString());
    }

    public void processResponseMemorySize() {
        this.mChannelHelper.postIpcMessage(14, this.mCpuBig);
    }

    public void processResponseCpuBig() {
        this.mChannelHelper.postIpcMessage(15, this.mCpuLittle);
    }

    public void processResponseCpuLittle() {
        this.mChannelHelper.postIpcMessage(20, TimeZone.getDefault().getID());
    }

    public void processResponseSetTimeZone() {
        if (this.mEnableAdbTCPIP && this.mExecutionType == ExecutionType.GUI) {
            this.mChannelHelper.postIpcMessage(19, PAYLOAD_ADB_TCPIP_MODE);
        } else {
            this.mChannelHelper.postIpcMessage(32, PAYLOAD_SET_CONFIG_DONE);
        }
    }

    public void processResponseAdbTcpIpMode() {
        this.mChannelHelper.postIpcMessage(32, PAYLOAD_SET_CONFIG_DONE);
    }

    public void processResponseSetConfigDone() {
        this.mChannelHelper.postIpcMessage(1, PAYLOAD_START_CONTAINER);
    }

    public void processResponseStartContainer() {
        if (this.mInitializeTimer != null) {
            this.mInitializeTimer.cancel();
            this.mInitializeTimer = null;
        }
        this.mChannelHelper.getListener().onContainerOpened();
    }

    public void processResponseStopContainer() {
        this.mChannelHelper.getListener().onContainerClosed();
    }

    public void processResponseCgroupFreezer(byte[] bArr) {
        if (PAYLOAD_PAUSE_CONTAINER.equals(new String(bArr))) {
            this.mChannelHelper.getListener().onContainerPaused();
        } else if (PAYLOAD_RESUME_CONTAINER.equals(new String(bArr))) {
            this.mChannelHelper.getListener().onContainerResumed();
        } else {
            String str = this.TAG;
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("Invalid message : ");
            stringBuilder.append(new String(bArr));
            Log.e(str, stringBuilder.toString());
        }
    }
}
