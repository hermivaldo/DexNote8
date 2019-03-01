package com.samsung.android.lxd.processor.control.channel;

import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import com.samsung.android.lxd.processor.ExecutionType;
import com.samsung.android.lxd.processor.ICommonContext;
import com.samsung.android.lxd.processor.control.channel.IControlChannel.ICallback;
import com.samsung.android.lxd.processor.control.channel.socket.SocketControlChannel;
import com.samsung.android.lxd.processor.utils.log.Log;

public class ControlChannelManager implements IControlChannel {
    private static final int STATE_CONTAINER_CLOSED = 14;
    private static final int STATE_CONTAINER_CLOSING = 13;
    private static final int STATE_CONTAINER_OPENED = 8;
    private static final int STATE_CONTAINER_OPENING = 7;
    private static final int STATE_CONTAINER_PAUSED = 12;
    private static final int STATE_CONTAINER_PAUSING = 11;
    private static final int STATE_CONTAINER_RESUMED = 10;
    private static final int STATE_CONTAINER_RESUMING = 9;
    private static final int STATE_MAX = 15;
    private static final int STATE_SERVICE_CLOSED = 2;
    private static final int STATE_SERVICE_CLOSING = 1;
    private static final int STATE_SERVICE_CONNECTED = 4;
    private static final int STATE_SERVICE_CONNECTING = 3;
    private static final int STATE_SERVICE_OPENED = 6;
    private static final int STATE_SERVICE_OPENING = 5;
    private final IControlChannel mControlChannel;
    private final Handler mControlHandler = new Handler(getLooper());
    private final ICallback mControlListener;
    private final ControlState[] mControlStates;
    private ControlState mCurrentState;
    private ICallback mDelayedListener;
    private String mDelayedOpenInfo;
    private ICallback mListener;

    private abstract class ControlState implements IControlChannel, ICallback {
        protected abstract int getState();

        public boolean isSupportedVersion(int i) {
            return false;
        }

        protected void leaveState() {
        }

        private ControlState() {
        }

        protected void enterState(int i) {
            String TAG = TAG();
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(ControlChannelManager.this.INF());
            stringBuilder.append("***** ENTER STATE *****");
            Log.d(TAG, stringBuilder.toString());
        }

        public String TAG() {
            return ControlChannelManager.this.TAG();
        }

        public void openService(ICallback iCallback) {
            ControlChannelManager.this.printLog("openService : not allowed in this state!");
        }

        public void closeService() {
            ControlChannelManager.this.mControlChannel.closeService();
            ControlChannelManager.this.changeState(1);
        }

        public void pauseService() {
            ControlChannelManager.this.mControlChannel.pauseService();
        }

        public void resumeService() {
            ControlChannelManager.this.mControlChannel.resumeService();
        }

        public void openContainer(String str) {
            ControlChannelManager.this.printLog("openContainer : not allowed in this state!");
        }

        public void closeContainer() {
            ControlChannelManager.this.printLog("closeContainer : not allowed in this state!");
        }

        public void pauseContainer() {
            ControlChannelManager.this.printLog("pauseContainer : not allowed in this state!");
        }

        public void resumeContainer() {
            ControlChannelManager.this.printLog("resumeContainer : not allowed in this state!");
        }

        public void getImageVersion(String str) {
            ControlChannelManager.this.printLog("getImageVersion : not allowed in this state!");
        }

        public void getImageMinSize(String str) {
            ControlChannelManager.this.printLog("getImageMinSize : not allowed in this state!");
        }

        public void rebaseImage(String str) {
            ControlChannelManager.this.printLog("rebaseImage : not allowed in this state!");
        }

        public void changeImageSize(String str, int i, boolean z) {
            ControlChannelManager.this.printLog("changeImageSize : not allowed in this state!");
        }

        public void notifySdCardStatus(String str, String str2) {
            ControlChannelManager.this.printLog("notifySdCardStatus : not allowed in this state!");
        }

        public void getDebugLog(String str) {
            ControlChannelManager.this.printLog("getDebugLog : not allowed in this state!");
        }

        public void startMonitoring() {
            ControlChannelManager.this.printLog("startMonitoring : not allowed in this state!");
        }

        public void stopMonitoring() {
            ControlChannelManager.this.printLog("stopMonitoring : not allowed in this state!");
        }

        public IControlChannel setContext(ICommonContext iCommonContext) {
            return ControlChannelManager.this.mControlChannel.setContext(iCommonContext);
        }

        public IControlChannel setConfigId(String str) {
            ControlChannelManager.this.printLog("setConfigId : not allowed in this state!");
            return null;
        }

        public IControlChannel setExecutionType(ExecutionType executionType) {
            ControlChannelManager.this.printLog("setExecutionType : not allowed in this state!");
            return null;
        }

        public void onServiceConnected() {
            ControlChannelManager.this.printLog("onServiceConnected : not allowed in this state!");
        }

        public void onServiceOpened() {
            ControlChannelManager.this.printLog("onServiceOpened : not allowed in this state!");
        }

        public void onServiceClosed() {
            ControlChannelManager.this.mListener.onServiceClosed();
            ControlChannelManager.this.changeState(2);
        }

        public void onServiceError(Throwable th) {
            ControlChannelManager.this.mListener.onServiceError(th);
        }

        public void onContainerOpened() {
            ControlChannelManager.this.printLog("onContainerOpened : not allowed in this state!");
        }

        public void onContainerResumed() {
            ControlChannelManager.this.printLog("onContainerResumed : not allowed in this state!");
        }

        public void onContainerPaused() {
            ControlChannelManager.this.printLog("onContainerPaused : not allowed in this state!");
        }

        public void onContainerClosed() {
            ControlChannelManager.this.printLog("onContainerClosed : not allowed in this state!");
        }

        public void onDebugLogReceived(String str, boolean z) {
            ControlChannelManager.this.printLog("onDebugLogReceived : not allowed in this state!");
        }

        public void onImageVersionReceived(String str, int i) {
            ControlChannelManager.this.printLog("onImageVersionReceived : not allowed in this state!");
        }

        public void onImageMinSizeReceived(String str, boolean z) {
            ControlChannelManager.this.printLog("onImageMinSizeReceived : not allowed in this state!");
        }

        public void onImageSizeUpdated(String str, boolean z) {
            ControlChannelManager.this.printLog("onImageSizeUpdated : not allowed in this state!");
        }

        public void onImageRebased(String str, boolean z) {
            ControlChannelManager.this.printLog("onImageRebased : not allowed in this state!");
        }

        public void onMemoryUsageReceived(String str, int i) {
            ControlChannelManager.this.printLog("onMemoryUsageReceived : not allowed in this state!");
        }

        public void onMonitoringStarted() {
            ControlChannelManager.this.printLog("onMonitoringStarted : not allowed in this state!");
        }

        public void onMonitoringStopped() {
            ControlChannelManager.this.printLog("onMonitoringStopped : not allowed in this state!");
        }

        public void onMonitoringNotified(String str) {
            ControlChannelManager.this.printLog("onMonitoringNotified : not allowed in this state!");
        }
    }

    private class InnerCallback implements ICallback {
        private InnerCallback() {
        }

        public void onServiceConnected() {
            ControlChannelManager.this.mControlHandler.post(new Runnable() {
                public void run() {
                    ControlChannelManager.this.printLog("onServiceConnected");
                    ControlChannelManager.this.mCurrentState.onServiceConnected();
                }
            });
        }

        public void onServiceOpened() {
            ControlChannelManager.this.mControlHandler.post(new Runnable() {
                public void run() {
                    ControlChannelManager.this.printLog("onServiceOpened");
                    ControlChannelManager.this.mCurrentState.onServiceOpened();
                }
            });
        }

        public void onServiceClosed() {
            ControlChannelManager.this.mControlHandler.post(new Runnable() {
                public void run() {
                    ControlChannelManager.this.printLog("onServiceClosed");
                    ControlChannelManager.this.mCurrentState.onServiceClosed();
                }
            });
        }

        public void onServiceError(final Throwable th) {
            ControlChannelManager.this.mControlHandler.post(new Runnable() {
                public void run() {
                    ControlChannelManager.this.printLog("onServiceError");
                    ControlChannelManager.this.mCurrentState.onServiceError(th);
                }
            });
        }

        public void onContainerOpened() {
            ControlChannelManager.this.mControlHandler.post(new Runnable() {
                public void run() {
                    ControlChannelManager.this.printLog("onContainerOpened");
                    ControlChannelManager.this.mCurrentState.onContainerOpened();
                }
            });
        }

        public void onContainerPaused() {
            ControlChannelManager.this.mControlHandler.post(new Runnable() {
                public void run() {
                    ControlChannelManager.this.printLog("onContainerPaused");
                    ControlChannelManager.this.mCurrentState.onContainerPaused();
                }
            });
        }

        public void onContainerResumed() {
            ControlChannelManager.this.mControlHandler.post(new Runnable() {
                public void run() {
                    ControlChannelManager.this.printLog("onContainerResumed");
                    ControlChannelManager.this.mCurrentState.onContainerResumed();
                }
            });
        }

        public void onContainerClosed() {
            ControlChannelManager.this.mControlHandler.post(new Runnable() {
                public void run() {
                    ControlChannelManager.this.printLog("onContainerClosed");
                    ControlChannelManager.this.mCurrentState.onContainerClosed();
                }
            });
        }

        public void onDebugLogReceived(final String str, final boolean z) {
            ControlChannelManager.this.mControlHandler.post(new Runnable() {
                public void run() {
                    ControlChannelManager.this.printLog("onDebugLogReceived");
                    ControlChannelManager.this.mCurrentState.onDebugLogReceived(str, z);
                }
            });
        }

        public void onImageVersionReceived(final String str, final int i) {
            ControlChannelManager.this.mControlHandler.post(new Runnable() {
                public void run() {
                    ControlChannelManager.this.printLog("onImageVersionReceived");
                    ControlChannelManager.this.mCurrentState.onImageVersionReceived(str, i);
                }
            });
        }

        public void onImageMinSizeReceived(final String str, final boolean z) {
            ControlChannelManager.this.mControlHandler.post(new Runnable() {
                public void run() {
                    ControlChannelManager.this.printLog("onImageMinSizeReceived");
                    ControlChannelManager.this.mCurrentState.onImageMinSizeReceived(str, z);
                }
            });
        }

        public void onImageSizeUpdated(final String str, final boolean z) {
            ControlChannelManager.this.mControlHandler.post(new Runnable() {
                public void run() {
                    ControlChannelManager.this.printLog("onImageSizeUpdated");
                    ControlChannelManager.this.mCurrentState.onImageSizeUpdated(str, z);
                }
            });
        }

        public void onImageRebased(final String str, final boolean z) {
            ControlChannelManager.this.mControlHandler.post(new Runnable() {
                public void run() {
                    ControlChannelManager.this.printLog("onImageRebased");
                    ControlChannelManager.this.mCurrentState.onImageRebased(str, z);
                }
            });
        }

        public void onMemoryUsageReceived(final String str, final int i) {
            ControlChannelManager.this.mControlHandler.post(new Runnable() {
                public void run() {
                    ControlChannelManager.this.printLog("onMemoryUsageReceived");
                    ControlChannelManager.this.mCurrentState.onMemoryUsageReceived(str, i);
                }
            });
        }

        public void onMonitoringStarted() {
            ControlChannelManager.this.mControlHandler.post(new Runnable() {
                public void run() {
                    ControlChannelManager.this.printLog("onMonitoringStarted");
                    ControlChannelManager.this.mCurrentState.onMonitoringStarted();
                }
            });
        }

        public void onMonitoringStopped() {
            ControlChannelManager.this.mControlHandler.post(new Runnable() {
                public void run() {
                    ControlChannelManager.this.printLog("onMonitoringStopped");
                    ControlChannelManager.this.mCurrentState.onMonitoringStopped();
                }
            });
        }

        public void onMonitoringNotified(final String str) {
            ControlChannelManager.this.mControlHandler.post(new Runnable() {
                public void run() {
                    ControlChannelManager.this.printLog("onMonitoringNotified");
                    ControlChannelManager.this.mCurrentState.onMonitoringNotified(str);
                }
            });
        }
    }

    private class ServiceClosedState extends ControlState {
        protected int getState() {
            return 2;
        }

        private ServiceClosedState() {
            super();
        }

        protected void enterState(int i) {
            super.enterState(i);
            if (ControlChannelManager.this.mDelayedListener != null) {
                ControlChannelManager.this.printLog("enterState : handle delayed openService");
                openService(ControlChannelManager.this.mDelayedListener);
                ControlChannelManager.this.mDelayedListener = null;
            }
        }

        public void openService(ICallback iCallback) {
            ControlChannelManager.this.mListener = iCallback;
            ControlChannelManager.this.mControlChannel.openService(ControlChannelManager.this.mControlListener);
            ControlChannelManager.this.changeState(3);
        }

        public void closeService() {
            ControlChannelManager.this.printLog("closeService : not allowed in this state!");
        }
    }

    private class ServiceClosingState extends ControlState {
        protected int getState() {
            return 1;
        }

        private ServiceClosingState() {
            super();
        }

        public void closeService() {
            ControlChannelManager.this.printLog("closeService : not allowed in this state!");
        }

        public void openService(ICallback iCallback) {
            ControlChannelManager.this.printLog("openService : handle openService after serviceClosed");
            ControlChannelManager.this.mDelayedListener = iCallback;
        }
    }

    private class ServiceConnectedState extends ControlState {
        protected int getState() {
            return 4;
        }

        private ServiceConnectedState() {
            super();
        }

        protected void enterState(int i) {
            super.enterState(i);
            if (i == 3 && getState() == 4) {
                ControlChannelManager.this.changeState(5);
            }
        }
    }

    private class ServiceConnectingState extends ControlState {
        protected int getState() {
            return 3;
        }

        private ServiceConnectingState() {
            super();
        }

        public void onServiceConnected() {
            ControlChannelManager.this.mListener.onServiceConnected();
            ControlChannelManager.this.changeState(4);
        }
    }

    private class ServiceOpenedState extends ControlState {
        protected int getState() {
            return 6;
        }

        private ServiceOpenedState() {
            super();
        }

        protected void enterState(int i) {
            super.enterState(i);
            if (ControlChannelManager.this.mDelayedOpenInfo != null && ControlChannelManager.this.mCurrentState.getState() == 6) {
                ControlChannelManager.this.printLog("enterState : handle delayed openContainer");
                openContainer(ControlChannelManager.this.mDelayedOpenInfo);
                ControlChannelManager.this.mDelayedOpenInfo = null;
            }
        }

        public void openContainer(String str) {
            if (ControlChannelManager.this.mCurrentState.getState() == 6) {
                ControlChannelManager.this.mControlChannel.openContainer(str);
                ControlChannelManager.this.changeState(7);
            } else if (ControlChannelManager.this.mCurrentState.getState() == 13 || ControlChannelManager.this.mCurrentState.getState() == 14) {
                ControlChannelManager.this.printLog("openContainer : handle openContainer after ServiceOpened");
                ControlChannelManager.this.mDelayedOpenInfo = str;
            } else {
                super.openContainer(str);
            }
        }

        public void getImageVersion(String str) {
            ControlChannelManager.this.mControlChannel.getImageVersion(str);
        }

        public void getImageMinSize(String str) {
            ControlChannelManager.this.mControlChannel.getImageMinSize(str);
        }

        public void rebaseImage(String str) {
            ControlChannelManager.this.mControlChannel.rebaseImage(str);
        }

        public void changeImageSize(String str, int i, boolean z) {
            ControlChannelManager.this.mControlChannel.changeImageSize(str, i, z);
        }

        public void getDebugLog(String str) {
            ControlChannelManager.this.mControlChannel.getDebugLog(str);
        }

        public IControlChannel setConfigId(String str) {
            return ControlChannelManager.this.mControlChannel.setConfigId(str);
        }

        public IControlChannel setExecutionType(ExecutionType executionType) {
            return ControlChannelManager.this.mControlChannel.setExecutionType(executionType);
        }

        public void onDebugLogReceived(String str, boolean z) {
            ControlChannelManager.this.mListener.onDebugLogReceived(str, z);
        }

        public void onImageVersionReceived(String str, int i) {
            ControlChannelManager.this.mListener.onImageVersionReceived(str, i);
        }

        public void onImageMinSizeReceived(String str, boolean z) {
            ControlChannelManager.this.mListener.onImageMinSizeReceived(str, z);
        }

        public void onImageSizeUpdated(String str, boolean z) {
            ControlChannelManager.this.mListener.onImageSizeUpdated(str, z);
        }

        public void onImageRebased(String str, boolean z) {
            ControlChannelManager.this.mListener.onImageRebased(str, z);
        }

        public void startMonitoring() {
            ControlChannelManager.this.mControlChannel.startMonitoring();
        }

        public void stopMonitoring() {
            ControlChannelManager.this.mControlChannel.stopMonitoring();
        }

        public void onMonitoringStarted() {
            ControlChannelManager.this.mListener.onMonitoringStarted();
        }

        public void onMonitoringStopped() {
            ControlChannelManager.this.mListener.onMonitoringStopped();
        }

        public void onMonitoringNotified(String str) {
            ControlChannelManager.this.mListener.onMonitoringNotified(str);
        }
    }

    private class ServiceOpeningState extends ControlState {
        protected int getState() {
            return 5;
        }

        private ServiceOpeningState() {
            super();
        }

        public void onServiceOpened() {
            ControlChannelManager.this.mListener.onServiceOpened();
            ControlChannelManager.this.changeState(6);
        }
    }

    private class ContainerClosedState extends ServiceOpenedState {
        protected int getState() {
            return 14;
        }

        private ContainerClosedState() {
            super();
        }

        protected void enterState(int i) {
            super.enterState(i);
            if (i == 13 && getState() == 14) {
                ControlChannelManager.this.changeState(6);
            }
        }
    }

    private class ContainerClosingState extends ServiceOpenedState {
        protected int getState() {
            return 13;
        }

        private ContainerClosingState() {
            super();
        }

        public void onContainerClosed() {
            ControlChannelManager.this.mListener.onContainerClosed();
            ControlChannelManager.this.changeState(14);
        }
    }

    private class ContainerOpenedState extends ServiceOpenedState {
        protected int getState() {
            return 8;
        }

        private ContainerOpenedState() {
            super();
        }

        public void notifySdCardStatus(String str, String str2) {
            ControlChannelManager.this.mControlChannel.notifySdCardStatus(str, str2);
        }

        public void onMemoryUsageReceived(String str, int i) {
            ControlChannelManager.this.mListener.onMemoryUsageReceived(str, i);
        }

        protected void enterState(int i) {
            super.enterState(i);
            if (i == 7 && getState() == 8) {
                ControlChannelManager.this.changeState(9);
            }
        }
    }

    private class ContainerOpeningState extends ServiceOpenedState {
        protected int getState() {
            return 7;
        }

        private ContainerOpeningState() {
            super();
        }

        public void onContainerOpened() {
            ControlChannelManager.this.mListener.onContainerOpened();
            ControlChannelManager.this.changeState(8);
        }
    }

    private class ContainerPausedState extends ContainerOpenedState {
        protected int getState() {
            return 12;
        }

        private ContainerPausedState() {
            super();
        }

        public void closeContainer() {
            ControlChannelManager.this.mControlChannel.closeContainer();
            ControlChannelManager.this.changeState(13);
        }

        public void resumeContainer() {
            ControlChannelManager.this.mControlChannel.resumeContainer();
            ControlChannelManager.this.changeState(9);
        }
    }

    private class ContainerPausingState extends ContainerOpenedState {
        protected int getState() {
            return 11;
        }

        private ContainerPausingState() {
            super();
        }

        public void onContainerPaused() {
            ControlChannelManager.this.mListener.onContainerPaused();
            ControlChannelManager.this.changeState(12);
        }
    }

    private class ContainerResumedState extends ContainerOpenedState {
        protected int getState() {
            return 10;
        }

        private ContainerResumedState() {
            super();
        }

        public void closeContainer() {
            ControlChannelManager.this.mControlChannel.closeContainer();
            ControlChannelManager.this.changeState(13);
        }

        public void pauseContainer() {
            ControlChannelManager.this.mControlChannel.pauseContainer();
            ControlChannelManager.this.changeState(11);
        }
    }

    private class ContainerResumingState extends ContainerOpenedState {
        protected int getState() {
            return 9;
        }

        private ContainerResumingState() {
            super();
        }

        protected void enterState(int i) {
            super.enterState(i);
            if (i == 8 && getState() == 9) {
                ControlChannelManager.this.changeState(10);
            }
        }

        public void onContainerResumed() {
            ControlChannelManager.this.mListener.onContainerResumed();
            ControlChannelManager.this.changeState(10);
        }
    }

    private String INF() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("[ ");
        stringBuilder.append(this.mCurrentState.getClass().getSimpleName());
        stringBuilder.append(" ] ");
        return stringBuilder.toString();
    }

    ControlChannelManager(ControlChannelType controlChannelType) {
        this.mControlChannel = new SocketControlChannel(controlChannelType);
        this.mControlListener = new InnerCallback();
        this.mControlStates = new ControlState[16];
        this.mListener = new ControlChannelListener();
        this.mDelayedListener = null;
        this.mDelayedOpenInfo = null;
        initStates();
    }

    private Looper getLooper() {
        Looper myLooper = Looper.myLooper();
        if (myLooper != null) {
            return myLooper;
        }
        HandlerThread handlerThread = new HandlerThread(getClass().getName());
        handlerThread.start();
        return handlerThread.getLooper();
    }

    private void initStates() {
        this.mControlStates[1] = new ServiceClosingState();
        this.mControlStates[2] = new ServiceClosedState();
        this.mControlStates[3] = new ServiceConnectingState();
        this.mControlStates[4] = new ServiceConnectedState();
        this.mControlStates[5] = new ServiceOpeningState();
        this.mControlStates[6] = new ServiceOpenedState();
        this.mControlStates[7] = new ContainerOpeningState();
        this.mControlStates[8] = new ContainerOpenedState();
        this.mControlStates[9] = new ContainerResumingState();
        this.mControlStates[10] = new ContainerResumedState();
        this.mControlStates[11] = new ContainerPausingState();
        this.mControlStates[12] = new ContainerPausedState();
        this.mControlStates[13] = new ContainerClosingState();
        this.mControlStates[14] = new ContainerClosedState();
        this.mCurrentState = this.mControlStates[2];
    }

    private void changeState(int i) {
        int state = this.mCurrentState.getState();
        this.mCurrentState.leaveState();
        this.mCurrentState = this.mControlStates[i];
        this.mCurrentState.enterState(state);
    }

    private void printLog(String str) {
        String TAG = TAG();
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(INF());
        stringBuilder.append(str);
        Log.d(TAG, stringBuilder.toString());
    }

    public String TAG() {
        return this.mControlChannel.TAG();
    }

    public boolean isSupportedVersion(int i) {
        return this.mControlChannel.isSupportedVersion(i);
    }

    public void openService(ICallback iCallback) {
        printLog("openService");
        this.mCurrentState.openService(iCallback);
    }

    public void closeService() {
        printLog("closeService");
        this.mCurrentState.closeService();
    }

    public void pauseService() {
        printLog("pauseService");
        this.mCurrentState.pauseService();
    }

    public void resumeService() {
        printLog("resumeService");
        this.mCurrentState.resumeService();
    }

    public void openContainer(String str) {
        printLog("openContainer");
        this.mCurrentState.openContainer(str);
    }

    public void closeContainer() {
        printLog("closeContainer");
        this.mCurrentState.closeContainer();
    }

    public void pauseContainer() {
        printLog("pauseContainer");
        this.mCurrentState.pauseContainer();
    }

    public void resumeContainer() {
        printLog("resumeContainer");
        this.mCurrentState.resumeContainer();
    }

    public void getImageVersion(String str) {
        printLog("getImageVersion");
        this.mCurrentState.getImageVersion(str);
    }

    public void getImageMinSize(String str) {
        printLog("getImageMinSize");
        this.mCurrentState.getImageMinSize(str);
    }

    public void rebaseImage(String str) {
        printLog("rebaseImage");
        this.mCurrentState.rebaseImage(str);
    }

    public void changeImageSize(String str, int i, boolean z) {
        printLog("changeImageSize");
        this.mCurrentState.changeImageSize(str, i, z);
    }

    public void notifySdCardStatus(String str, String str2) {
        printLog("notifySdCardStatus");
        this.mCurrentState.notifySdCardStatus(str, str2);
    }

    public void getDebugLog(String str) {
        printLog("getDebugLog");
        this.mCurrentState.getDebugLog(str);
    }

    public void startMonitoring() {
        printLog("startMonitoring");
        this.mCurrentState.startMonitoring();
    }

    public void stopMonitoring() {
        printLog("stopMonitoring");
        this.mCurrentState.stopMonitoring();
    }

    public IControlChannel setContext(ICommonContext iCommonContext) {
        printLog("setContext");
        this.mCurrentState.setContext(iCommonContext);
        return this;
    }

    public IControlChannel setConfigId(String str) {
        printLog("setConfigId");
        this.mCurrentState.setConfigId(str);
        return this;
    }

    public IControlChannel setExecutionType(ExecutionType executionType) {
        printLog("setExecutionType");
        this.mCurrentState.setExecutionType(executionType);
        return this;
    }
}
