package com.samsung.android.lxd.processor;

import android.view.KeyEvent;
import android.view.MotionEvent;
import com.samsung.android.lxd.processor.Processor.OpenContainerInfo;
import com.samsung.android.lxd.processor.Processor.OpenServiceInfo;

public interface INetworkService {

    public interface ICallback {
        void onAudioConnected(String str);

        void onAudioDisconnected(String str);

        void onContainerClosed();

        void onContainerError(String str);

        void onContainerOpened();

        void onContainerPaused();

        void onContainerResumed();

        void onContainerStarted(String str);

        void onContainerStopped();

        void onCutTextReceived(String str);

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

        void onServiceError(String str);

        void onServiceOpened();

        void onTextCommitted(String str);
    }

    void closeContainer();

    void closeService();

    void getDebugLog(String str);

    void getImageMinSize(String str);

    void getImageVersion(String str);

    boolean handleGenericMotion(MotionEvent motionEvent);

    boolean handleKeyDown(int i, KeyEvent keyEvent);

    boolean handleKeyUp(int i, KeyEvent keyEvent);

    boolean handleTouchEvent(MotionEvent motionEvent);

    void notifySdCardStatus(String str, String str2);

    void openContainer(OpenContainerInfo openContainerInfo);

    void openService(OpenServiceInfo openServiceInfo);

    void pauseContainer();

    void rebaseImage(String str);

    void resizeImage(String str, int i, boolean z);

    void resumeContainer();

    void sendCutText(String str);

    void startContainer();

    void startMonitoring();

    void stopContainer();

    void stopMonitoring();
}
