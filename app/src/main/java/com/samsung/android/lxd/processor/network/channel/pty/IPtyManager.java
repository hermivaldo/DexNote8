package com.samsung.android.lxd.processor.network.channel.pty;

import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.ScaleGestureDetector;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputConnection;
import com.samsung.android.lxd.processor.ICommonContext;
import com.samsung.android.lxd.processor.network.display.NetworkDisplayContext;

public interface IPtyManager {
    NetworkDisplayContext getDisplayContext(boolean z, boolean z2);

    InputConnection getInputConnection(View view, EditorInfo editorInfo);

    void init(ICommonContext iCommonContext, OnInitListener onInitListener);

    boolean isReady();

    boolean isTextEditor();

    void notifyDisplaySizeChange(int i, int i2);

    boolean notifyDoubleTapEvent(MotionEvent motionEvent);

    boolean notifyDownEvent(MotionEvent motionEvent);

    boolean notifyFlingEvent(MotionEvent motionEvent, MotionEvent motionEvent2, float f, float f2);

    boolean notifyKeyEvent(int i, KeyEvent keyEvent);

    boolean notifyLongPressEvent(MotionEvent motionEvent);

    boolean notifyMouseEvent(MotionEvent motionEvent);

    boolean notifyScaleBeginEvent(ScaleGestureDetector scaleGestureDetector);

    boolean notifyScaleEndEvent(ScaleGestureDetector scaleGestureDetector);

    boolean notifyScaleEvent(ScaleGestureDetector scaleGestureDetector);

    boolean notifyScrollEvent(MotionEvent motionEvent, MotionEvent motionEvent2, float f, float f2);

    boolean notifySingleTapUpEvent(MotionEvent motionEvent);

    void resume();

    void start(OnStartListener onStartListener);

    void stop();
}
