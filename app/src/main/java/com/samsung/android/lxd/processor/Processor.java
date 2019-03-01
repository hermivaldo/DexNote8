package com.samsung.android.lxd.processor;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.view.KeyEvent;
import android.view.MotionEvent;
import com.samsung.android.lxd.LxdApplication;
import com.samsung.android.lxd.processor.INetworkService.ICallback;
import com.samsung.android.lxd.processor.control.channel.ControlChannelType;
import com.samsung.android.lxd.processor.network.audio.NetworkAudioType;
import com.samsung.android.lxd.processor.network.display.NetworkDisplayType;
import com.samsung.android.lxd.processor.network.display.NetworkScreenType;
import com.samsung.android.lxd.processor.utils.log.Log;
import java.util.HashMap;

public class Processor implements INetworkService {
    private static final String TAG = "Processor";
    private static Processor sInstance;
    private ServiceConnection mConnection = new ServiceConnection() {
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            Log.i(Processor.TAG, "Connected to NetworkService!");
            Processor.this.mNetworkService = (INetworkService) iBinder;
            if (Processor.this.mPendingCommandMap.containsKey(Integer.valueOf(1))) {
                Processor.this.mNetworkService.openService((OpenServiceInfo) Processor.this.mPendingCommandMap.get(Integer.valueOf(1)));
                Processor.this.mPendingCommandMap.remove(Integer.valueOf(1));
            }
        }

        public void onServiceDisconnected(ComponentName componentName) {
            Log.i(Processor.TAG, "Disconnected from NetworkService!");
            Processor.this.mNetworkService = new DummyNetworkService(Processor.this, null);
        }
    };
    private INetworkService mNetworkService = new DummyNetworkService(this, null);
    private final HashMap<Integer, CommandInfoBase> mPendingCommandMap = new HashMap();

    private static abstract class CommandInfoBase {
        private CommandInfoBase() {
        }

        /* synthetic */ CommandInfoBase(AnonymousClass1 anonymousClass1) {
            this();
        }
    }

    private class DummyNetworkService implements INetworkService {
        private DummyNetworkService() {
        }

        /* synthetic */ DummyNetworkService(Processor processor, AnonymousClass1 anonymousClass1) {
            this();
        }

        public boolean handleGenericMotion(MotionEvent motionEvent) {
            Log.e(Processor.TAG, "Not connected to Network Service!");
            return false;
        }

        public boolean handleKeyDown(int i, KeyEvent keyEvent) {
            Log.e(Processor.TAG, "Not connected to Network Service!");
            return false;
        }

        public boolean handleKeyUp(int i, KeyEvent keyEvent) {
            Log.e(Processor.TAG, "Not connected to Network Service!");
            return false;
        }

        public boolean handleTouchEvent(MotionEvent motionEvent) {
            Log.e(Processor.TAG, "Not connected to Network Service!");
            return false;
        }

        public void getDebugLog(String str) {
            Log.e(Processor.TAG, "Not connected to Network Service!");
        }

        public void getImageVersion(String str) {
            Log.e(Processor.TAG, "Not connected to Network Service!");
        }

        public void getImageMinSize(String str) {
            Log.e(Processor.TAG, "Not connected to Network Service!");
        }

        public void rebaseImage(String str) {
            Log.e(Processor.TAG, "Not connected to Network Service!");
        }

        public void resizeImage(String str, int i, boolean z) {
            Log.e(Processor.TAG, "Not connected to Network Service!");
        }

        public void sendCutText(String str) {
            Log.e(Processor.TAG, "Not connected to Network Service!");
        }

        public void openService(OpenServiceInfo openServiceInfo) {
            Log.e(Processor.TAG, "Not connected to Network Service!");
        }

        public void closeService() {
            Log.e(Processor.TAG, "Not connected to Network Service!");
        }

        public void openContainer(OpenContainerInfo openContainerInfo) {
            Log.e(Processor.TAG, "Not connected to Network Service!");
        }

        public void closeContainer() {
            Log.e(Processor.TAG, "Not connected to Network Service!");
        }

        public void pauseContainer() {
            Log.e(Processor.TAG, "Not connected to Network Service!");
        }

        public void resumeContainer() {
            Log.e(Processor.TAG, "Not connected to Network Service!");
        }

        public void startContainer() {
            Log.e(Processor.TAG, "Not connected to Network Service!");
        }

        public void stopContainer() {
            Log.e(Processor.TAG, "Not connected to Network Service!");
        }

        public void notifySdCardStatus(String str, String str2) {
            Log.e(Processor.TAG, "Not connected to Network Service!");
        }

        public void startMonitoring() {
            Log.e(Processor.TAG, "Not connected to Network Service!");
        }

        public void stopMonitoring() {
            Log.e(Processor.TAG, "Not connected to Network Service!");
        }
    }

    public static class OpenContainerInfo extends CommandInfoBase {
        private final String mConfigId;
        private final ExecutionType mExecutionType;
        private final String mImageVersion;
        private final NetworkAudioType mNetworkAudioType;
        private final NetworkDisplayType mNetworkDisplayType;
        private final NetworkScreenType mNetworkScreenType;

        public OpenContainerInfo(String str, String str2, ExecutionType executionType, NetworkDisplayType networkDisplayType, NetworkAudioType networkAudioType, NetworkScreenType networkScreenType) {
            super();
            this.mConfigId = str;
            this.mImageVersion = str2;
            this.mExecutionType = executionType;
            this.mNetworkDisplayType = networkDisplayType;
            this.mNetworkAudioType = networkAudioType;
            this.mNetworkScreenType = networkScreenType;
        }

        public String getConfigId() {
            return this.mConfigId;
        }

        public String getImageVersion() {
            return this.mImageVersion;
        }

        public ExecutionType getExecutionType() {
            return this.mExecutionType;
        }

        public NetworkDisplayType getNetworkDisplayType() {
            return this.mNetworkDisplayType;
        }

        public NetworkAudioType getNetworkAudioType() {
            return this.mNetworkAudioType;
        }

        public NetworkScreenType getNetworkScreenType() {
            return this.mNetworkScreenType;
        }
    }

    public static class OpenServiceInfo extends CommandInfoBase {
        private final ICallback mCallback;
        private final ControlChannelType mControlChannelType;

        public OpenServiceInfo(ControlChannelType controlChannelType, ICallback iCallback) {
            super();
            this.mControlChannelType = controlChannelType;
            this.mCallback = iCallback;
        }

        public ControlChannelType getControlChannelType() {
            return this.mControlChannelType;
        }

        public ICallback getCallback() {
            return this.mCallback;
        }
    }

    public static synchronized Processor getInstance() {
        Processor processor;
        synchronized (Processor.class) {
            if (sInstance == null) {
                sInstance = new Processor();
            }
            processor = sInstance;
        }
        return processor;
    }

    private Processor() {
        Context a = LxdApplication.a();
        a.startService(new Intent(a, NetworkService.class));
        a.bindService(new Intent(a, NetworkService.class), this.mConnection, 1);
    }

    public boolean handleGenericMotion(MotionEvent motionEvent) {
        return this.mNetworkService.handleGenericMotion(MotionEvent.obtain(motionEvent));
    }

    public boolean handleKeyDown(int i, KeyEvent keyEvent) {
        return this.mNetworkService.handleKeyDown(i, keyEvent);
    }

    public boolean handleKeyUp(int i, KeyEvent keyEvent) {
        return this.mNetworkService.handleKeyUp(i, keyEvent);
    }

    public boolean handleTouchEvent(MotionEvent motionEvent) {
        return this.mNetworkService.handleTouchEvent(MotionEvent.obtain(motionEvent));
    }

    public void getDebugLog(String str) {
        this.mNetworkService.getDebugLog(str);
    }

    public void getImageVersion(String str) {
        this.mNetworkService.getImageVersion(str);
    }

    public void getImageMinSize(String str) {
        this.mNetworkService.getImageMinSize(str);
    }

    public void rebaseImage(String str) {
        this.mNetworkService.rebaseImage(str);
    }

    public void resizeImage(String str, int i, boolean z) {
        this.mNetworkService.resizeImage(str, i, z);
    }

    public void sendCutText(String str) {
        this.mNetworkService.sendCutText(str);
    }

    public void openService(OpenServiceInfo openServiceInfo) {
        Log.i(TAG, "openService");
        if (this.mNetworkService instanceof DummyNetworkService) {
            this.mPendingCommandMap.put(Integer.valueOf(1), openServiceInfo);
        } else {
            this.mNetworkService.openService(openServiceInfo);
        }
    }

    public void closeService() {
        Log.i(TAG, "closeService");
        this.mNetworkService.closeService();
    }

    public void openContainer(OpenContainerInfo openContainerInfo) {
        Log.i(TAG, "openContainer");
        this.mNetworkService.openContainer(openContainerInfo);
    }

    public void closeContainer() {
        Log.i(TAG, "closeContainer");
        this.mNetworkService.closeContainer();
    }

    public void pauseContainer() {
        Log.i(TAG, "pauseContainer");
        this.mNetworkService.pauseContainer();
    }

    public void resumeContainer() {
        Log.i(TAG, "resumeContainer");
        this.mNetworkService.resumeContainer();
    }

    public void startContainer() {
        Log.i(TAG, "startContainer");
        this.mNetworkService.startContainer();
    }

    public void stopContainer() {
        Log.i(TAG, "stopContainer");
        this.mNetworkService.stopContainer();
    }

    public void notifySdCardStatus(String str, String str2) {
        String str3 = TAG;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("notifySdCardStatus ");
        stringBuilder.append(str);
        stringBuilder.append(" ");
        stringBuilder.append(str2);
        Log.i(str3, stringBuilder.toString());
        this.mNetworkService.notifySdCardStatus(str, str2);
    }

    public void startMonitoring() {
        Log.i(TAG, "startMonitoring");
        this.mNetworkService.startMonitoring();
    }

    public void stopMonitoring() {
        Log.i(TAG, "stopMonitoring");
        this.mNetworkService.stopMonitoring();
    }
}
