package com.samsung.android.lxd.processor;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.HandlerThread;
import android.os.IBinder;
import android.view.KeyEvent;
import android.view.MotionEvent;
import com.samsung.android.lxd.a.o;
import com.samsung.android.lxd.processor.Processor.OpenContainerInfo;
import com.samsung.android.lxd.processor.Processor.OpenServiceInfo;
import com.samsung.android.lxd.processor.handler.LxdMessage;
import com.samsung.android.lxd.processor.handler.MainHandler;
import com.samsung.android.lxd.processor.utils.log.Log;

public class NetworkService extends Service {
    private static final String TAG = "NetworkService";
    private MainHandler mMainHandler;

    class ServiceBinder extends Binder implements INetworkService {
        ServiceBinder() {
        }

        public boolean handleGenericMotion(MotionEvent motionEvent) {
            return NetworkService.this.mMainHandler.sendMessage(LxdMessage.obtain(52, motionEvent, null));
        }

        public boolean handleKeyDown(int i, KeyEvent keyEvent) {
            return NetworkService.this.mMainHandler.sendMessage(LxdMessage.obtain(49, Integer.valueOf(i), keyEvent, null));
        }

        public boolean handleKeyUp(int i, KeyEvent keyEvent) {
            return NetworkService.this.mMainHandler.sendMessage(LxdMessage.obtain(50, Integer.valueOf(i), keyEvent, null));
        }

        public boolean handleTouchEvent(MotionEvent motionEvent) {
            return NetworkService.this.mMainHandler.sendMessage(LxdMessage.obtain(51, motionEvent, null));
        }

        public void getDebugLog(String str) {
            NetworkService.this.mMainHandler.sendMessage(LxdMessage.obtain(64, str, null));
        }

        public void getImageVersion(String str) {
            NetworkService.this.mMainHandler.sendMessage(LxdMessage.obtain(54, str, null));
        }

        public void getImageMinSize(String str) {
            NetworkService.this.mMainHandler.sendMessage(LxdMessage.obtain(55, str, null));
        }

        public void rebaseImage(String str) {
            NetworkService.this.mMainHandler.sendMessage(LxdMessage.obtain(65, str, null));
        }

        public void resizeImage(String str, int i, boolean z) {
            NetworkService.this.mMainHandler.sendMessage(LxdMessage.obtain(56, str, Integer.valueOf(i), Boolean.valueOf(z), null));
        }

        public void sendCutText(String str) {
            NetworkService.this.mMainHandler.sendMessage(LxdMessage.obtain(53, str, null));
        }

        public void openService(OpenServiceInfo openServiceInfo) {
            NetworkService.this.mMainHandler.sendMessage(LxdMessage.obtain(1, openServiceInfo.getControlChannelType(), openServiceInfo.getCallback()));
        }

        public void closeService() {
            NetworkService.this.mMainHandler.sendMessage(LxdMessage.obtain(2, null));
        }

        public void openContainer(OpenContainerInfo openContainerInfo) {
            NetworkService.this.mMainHandler.sendMessage(LxdMessage.obtain(3, openContainerInfo.getConfigId(), openContainerInfo.getImageVersion(), openContainerInfo.getExecutionType(), openContainerInfo.getNetworkDisplayType(), openContainerInfo.getNetworkAudioType(), openContainerInfo.getNetworkScreenType(), null));
        }

        public void closeContainer() {
            NetworkService.this.mMainHandler.sendMessage(LxdMessage.obtain(4, null));
        }

        public void pauseContainer() {
            NetworkService.this.mMainHandler.sendMessage(LxdMessage.obtain(5, null));
        }

        public void resumeContainer() {
            NetworkService.this.mMainHandler.sendMessage(LxdMessage.obtain(6, null));
        }

        public void startContainer() {
            NetworkService.this.mMainHandler.sendMessage(LxdMessage.obtain(33, null));
        }

        public void stopContainer() {
            NetworkService.this.mMainHandler.sendMessage(LxdMessage.obtain(34, null));
        }

        public void notifySdCardStatus(String str, String str2) {
            NetworkService.this.mMainHandler.sendMessage(LxdMessage.obtain(57, str, str2, null));
        }

        public void startMonitoring() {
            NetworkService.this.mMainHandler.sendMessage(LxdMessage.obtain(66, null));
        }

        public void stopMonitoring() {
            NetworkService.this.mMainHandler.sendMessage(LxdMessage.obtain(67, null));
        }
    }

    public void onCreate() {
        Log.i(TAG, "onCreate");
        HandlerThread handlerThread = new HandlerThread(getClass().getSimpleName());
        handlerThread.start();
        this.mMainHandler = new MainHandler(handlerThread.getLooper());
    }

    public IBinder onBind(Intent intent) {
        Log.i(TAG, "onBind");
        return new ServiceBinder();
    }

    public void onDestroy() {
        Log.i(TAG, "onDestroy");
    }

    public void onTaskRemoved(Intent intent) {
        super.onTaskRemoved(intent);
        Log.d(TAG, "onTaskRemoved");
        stopSelf();
        o.p();
    }
}
