package com.samsung.android.lxd.processor.network.input;

import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.PointerIcon;
import android.view.ScaleGestureDetector;
import com.samsung.android.lxd.processor.ICommonContext;

public interface INetworkInput {

    public interface ICallback {
        boolean onDoubleTap(MotionEvent motionEvent);

        boolean onDown(MotionEvent motionEvent);

        boolean onFling(MotionEvent motionEvent, MotionEvent motionEvent2, float f, float f2);

        boolean onGenericMotion(MotionEvent motionEvent);

        boolean onKeyDown(int i, KeyEvent keyEvent);

        boolean onKeyUp(int i, KeyEvent keyEvent);

        boolean onLongPress(MotionEvent motionEvent);

        boolean onScale(ScaleGestureDetector scaleGestureDetector);

        boolean onScaleBegin(ScaleGestureDetector scaleGestureDetector);

        boolean onScaleEnd(ScaleGestureDetector scaleGestureDetector);

        boolean onScroll(MotionEvent motionEvent, MotionEvent motionEvent2, float f, float f2);

        boolean onSingleTapUp(MotionEvent motionEvent);

        void onTextCut(String str);

        boolean onTouchEvent(MotionEvent motionEvent);

        boolean onTouchEvent(MotionEvent motionEvent, boolean z);
    }

    INetworkInput enableSoftInput(boolean z);

    PointerIcon getCursorInfo();

    boolean handleGenericMotion(MotionEvent motionEvent);

    boolean handleKeyDown(int i, KeyEvent keyEvent);

    boolean handleKeyUp(int i, KeyEvent keyEvent);

    void handleSendCutText(String str);

    boolean handleTouchEvent(MotionEvent motionEvent);

    INetworkInput init(ICallback iCallback);

    void notifyInputConnectionReady();

    INetworkInput setContext(ICommonContext iCommonContext);

    INetworkInput start();

    INetworkInput stop();

    void updateCursorType(PointerIcon pointerIcon, boolean z);
}
