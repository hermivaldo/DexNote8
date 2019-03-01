package com.samsung.android.lxd.processor.control.channel.socket;

import android.net.LocalSocketAddress.Namespace;
import com.samsung.android.lxd.processor.ExecutionType;

public interface IControlChannelImpl {
    void changeImageSize(String str, int i, boolean z);

    void closeContainer();

    void closeService();

    void getDebugLog(String str);

    void getImageMinSize(String str);

    void getImageVersion(String str);

    String getSocketName();

    Namespace getSocketNamespace();

    int getVersion();

    void handleException();

    void notifySdCardStatus(String str, String str2);

    void openContainer(String str);

    void openService();

    void pauseContainer();

    void processNotifyLowMemory(byte[] bArr);

    void processNotifyMonitoring(byte[] bArr);

    void processResponseAdbTcpIpMode();

    void processResponseCgroupFreezer(byte[] bArr);

    void processResponseCpuBig();

    void processResponseCpuLittle();

    void processResponseGetCGroupMemoryUsage(byte[] bArr, int i);

    void processResponseGetDebugLog(byte[] bArr, int i);

    void processResponseGetImageMinSize(byte[] bArr, int i);

    void processResponseGetImageVersion(byte[] bArr, int i);

    void processResponseGuiMode();

    void processResponseImagePath();

    void processResponseImageSizeUpdate(byte[] bArr, int i);

    void processResponseMemorySize();

    void processResponseRebaseImage(byte[] bArr, int i);

    void processResponseScreenSize();

    void processResponseSetConfig();

    void processResponseSetConfigDone();

    void processResponseSetTimeZone();

    void processResponseSharedDir();

    void processResponseStartContainer();

    void processResponseStartMonitoring();

    void processResponseStopContainer();

    void processResponseStopMonitoring();

    void processResponseTerminalMode();

    void rebaseImage(String str);

    void resumeContainer();

    void setConfigId(String str);

    void setExecutionType(ExecutionType executionType);

    void startMonitoring();

    void stopMonitoring();
}
