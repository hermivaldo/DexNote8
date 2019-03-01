package com.samsung.android.lxd.processor.network.channel;

import android.view.ActionMode.Callback2;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.PointerIcon;
import android.view.ScaleGestureDetector;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputConnection;
import com.samsung.android.lxd.processor.ICommonContext;
import com.samsung.android.lxd.processor.network.display.NetworkDisplayContext;

public interface INetworkChannel {

    public interface ICallback {
        void onContainerDraw(NetworkDisplayContext networkDisplayContext);

        void onContainerError(Throwable th);

        void onContainerStarted(String str);

        void onContainerStopped();

        void onCursorUpdated(PointerIcon pointerIcon, boolean z);

        void onCutTextReceived(String str);

        void onInit(String str);

        void onMonitoringNotified(String str);

        void onMonitoringStarted();

        void onMonitoringStopped();

        void onShowActionMode(Callback2 callback2);

        void onTextCommitted(String str);
    }

    NetworkChannelType getChannelType();

    String getConnectionInfo();

    int getFramebufferHeight();

    int getFramebufferWidth();

    InputConnection getInputConnection(View view, EditorInfo editorInfo);

    String getSystemName();

    INetworkChannel init(ICallback iCallback);

    boolean isReady();

    boolean isTextEditor();

    void notifyDisplaySizeChange(int i, int i2);

    INetworkChannel pause();

    INetworkChannel resume();

    void sendCutText(String str);

    boolean sendDoubleTapEvent(MotionEvent motionEvent);

    boolean sendDownEvent(MotionEvent motionEvent);

    boolean sendFlingEvent(MotionEvent motionEvent, MotionEvent motionEvent2, float f, float f2);

    boolean sendKeyEvent(int i, KeyEvent keyEvent);

    boolean sendLongPressEvent(MotionEvent motionEvent);

    boolean sendMouseEvent(MotionEvent motionEvent);

    boolean sendMouseEvent(MotionEvent motionEvent, boolean z);

    boolean sendScaleBeginEvent(ScaleGestureDetector scaleGestureDetector);

    boolean sendScaleEndEvent(ScaleGestureDetector scaleGestureDetector);

    boolean sendScaleEvent(ScaleGestureDetector scaleGestureDetector);

    boolean sendScrollEvent(MotionEvent motionEvent, MotionEvent motionEvent2, float f, float f2);

    boolean sendSingleTapUpEvent(MotionEvent motionEvent);

    INetworkChannel setContext(ICommonContext iCommonContext);

    INetworkChannel setImageVersion(String str);

    INetworkChannel start();

    void startMonitoring();

    INetworkChannel stop();

    void stopMonitoring();
}
