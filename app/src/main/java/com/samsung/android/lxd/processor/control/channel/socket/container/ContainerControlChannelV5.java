package com.samsung.android.lxd.processor.control.channel.socket.container;

import android.net.LocalSocketAddress.Namespace;
import com.samsung.android.lxd.processor.ExecutionType;
import com.samsung.android.lxd.processor.control.channel.socket.IControlChannelHelper;
import com.samsung.android.lxd.processor.control.channel.socket.IControlChannelImpl;

public class ContainerControlChannelV5 implements IControlChannelImpl {
    private static final String SOCKET_ADDRESS = "run/lod_control_android.usk";
    protected final String TAG = getClass().getSimpleName();
    private final int VERSION = 5;
    protected final IControlChannelHelper mChannelHelper;

    public void changeImageSize(String str, int i, boolean z) {
    }

    public void closeContainer() {
    }

    public void closeService() {
    }

    public void getDebugLog(String str) {
    }

    public void getImageMinSize(String str) {
    }

    public void getImageVersion(String str) {
    }

    public String getSocketName() {
        return "/data/lxd/run/lod_control_android.usk";
    }

    public int getVersion() {
        return 5;
    }

    public void handleException() {
    }

    public void notifySdCardStatus(String str, String str2) {
    }

    public void openContainer(String str) {
    }

    public void openService() {
    }

    public void pauseContainer() {
    }

    public void processNotifyLowMemory(byte[] bArr) {
    }

    public void processResponseAdbTcpIpMode() {
    }

    public void processResponseCgroupFreezer(byte[] bArr) {
    }

    public void processResponseCpuBig() {
    }

    public void processResponseCpuLittle() {
    }

    public void processResponseGetCGroupMemoryUsage(byte[] bArr, int i) {
    }

    public void processResponseGetDebugLog(byte[] bArr, int i) {
    }

    public void processResponseGetImageMinSize(byte[] bArr, int i) {
    }

    public void processResponseGetImageVersion(byte[] bArr, int i) {
    }

    public void processResponseGuiMode() {
    }

    public void processResponseImagePath() {
    }

    public void processResponseImageSizeUpdate(byte[] bArr, int i) {
    }

    public void processResponseMemorySize() {
    }

    public void processResponseRebaseImage(byte[] bArr, int i) {
    }

    public void processResponseScreenSize() {
    }

    public void processResponseSetConfig() {
    }

    public void processResponseSetConfigDone() {
    }

    public void processResponseSetTimeZone() {
    }

    public void processResponseSharedDir() {
    }

    public void processResponseStartContainer() {
    }

    public void processResponseStopContainer() {
    }

    public void processResponseTerminalMode() {
    }

    public void rebaseImage(String str) {
    }

    public void resumeContainer() {
    }

    public void setConfigId(String str) {
    }

    public void setExecutionType(ExecutionType executionType) {
    }

    public ContainerControlChannelV5(IControlChannelHelper iControlChannelHelper) {
        this.mChannelHelper = iControlChannelHelper;
    }

    public Namespace getSocketNamespace() {
        return Namespace.FILESYSTEM;
    }

    public void startMonitoring() {
        this.mChannelHelper.postIpcMessage(42, null);
    }

    public void stopMonitoring() {
        this.mChannelHelper.postIpcMessage(43, null);
    }

    public void processResponseStartMonitoring() {
        this.mChannelHelper.getListener().onMonitoringStarted();
    }

    public void processResponseStopMonitoring() {
        this.mChannelHelper.getListener().onMonitoringStopped();
    }

    public void processNotifyMonitoring(byte[] bArr) {
        this.mChannelHelper.getListener().onMonitoringNotified(new String(bArr));
    }
}
