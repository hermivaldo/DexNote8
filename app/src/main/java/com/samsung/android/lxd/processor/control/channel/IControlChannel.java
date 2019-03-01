package com.samsung.android.lxd.processor.control.channel;

import com.samsung.android.lxd.processor.ExecutionType;
import com.samsung.android.lxd.processor.ICommonContext;

public interface IControlChannel {

    public interface ICallback {
        void onContainerClosed();

        void onContainerOpened();

        void onContainerPaused();

        void onContainerResumed();

        void onDebugLogReceived(String str, boolean z);

        void onImageMinSizeReceived(String str, boolean z);

        void onImageRebased(String str, boolean z);

        void onImageSizeUpdated(String str, boolean z);

        void onImageVersionReceived(String str, int i);

        void onMemoryUsageReceived(String str, int i);

        void onMonitoringNotified(String str);

        void onMonitoringStarted();

        void onMonitoringStopped();

        void onServiceClosed();

        void onServiceConnected();

        void onServiceError(Throwable th);

        void onServiceOpened();
    }

    String TAG();

    void changeImageSize(String str, int i, boolean z);

    void closeContainer();

    void closeService();

    void getDebugLog(String str);

    void getImageMinSize(String str);

    void getImageVersion(String str);

    boolean isSupportedVersion(int i);

    void notifySdCardStatus(String str, String str2);

    void openContainer(String str);

    void openService(ICallback iCallback);

    void pauseContainer();

    void pauseService();

    void rebaseImage(String str);

    void resumeContainer();

    void resumeService();

    IControlChannel setConfigId(String str);

    IControlChannel setContext(ICommonContext iCommonContext);

    IControlChannel setExecutionType(ExecutionType executionType);

    void startMonitoring();

    void stopMonitoring();
}
