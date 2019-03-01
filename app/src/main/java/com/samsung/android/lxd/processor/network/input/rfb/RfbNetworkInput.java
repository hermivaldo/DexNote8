package com.samsung.android.lxd.processor.network.input.rfb;

import android.view.MotionEvent;
import android.view.PointerIcon;
import android.view.ScaleGestureDetector;
import com.samsung.android.lxd.a.o;
import com.samsung.android.lxd.processor.network.input.NetworkInput;
import com.samsung.android.lxd.processor.utils.Utils;
import com.samsung.android.lxd.processor.utils.log.Log;

public class RfbNetworkInput extends NetworkInput {
    private static final String TAG = "RfbNetworkInput";
    private MotionEvent mMotionEvent = null;
    private float mPrevX = -1.0f;
    private float mPrevY = -1.0f;
    private ScrollType mScrollType = null;

    private enum ScrollType {
        NONE,
        SINGLE,
        DOUBLE
    }

    protected boolean handleGenericMotionInternal(MotionEvent motionEvent) {
        return skipGenericMotionEvent(motionEvent);
    }

    protected boolean handleTouchEventInternal(MotionEvent motionEvent) {
        this.mMotionEvent = motionEvent;
        if (motionEvent.getAction() == 1 && this.mScrollType == ScrollType.SINGLE) {
            this.mScrollType = ScrollType.NONE;
            String str = TAG;
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("scroll up: ");
            stringBuilder.append(motionEvent.getAction());
            stringBuilder.append(", count: ");
            stringBuilder.append(motionEvent.getPointerCount());
            stringBuilder.append(", state: ");
            stringBuilder.append(motionEvent.getButtonState());
            Log.d(str, stringBuilder.toString());
        } else {
            this.mGestureDetector.onTouchEvent(motionEvent);
            if (handleEventUsingGesture(motionEvent)) {
                this.mScaleGestureDetector.onTouchEvent(motionEvent);
                return true;
            }
        }
        return false;
    }

    public void updateCursorType(PointerIcon pointerIcon, boolean z) {
        super.updateCursorType(pointerIcon, z);
        this.mTextInputType = z ? SoftInputMode.SHOW : SoftInputMode.HIDE;
    }

    private boolean handleEventUsingGesture(MotionEvent motionEvent) {
        return Utils.isSourceTouchScreen(motionEvent);
    }

    protected boolean handleGestureOnDown(MotionEvent motionEvent) {
        if (!handleEventUsingGesture(motionEvent) || this.mListener == null) {
            return false;
        }
        Log.d(TAG, "onDown: ");
        this.mScrollType = ScrollType.NONE;
        motionEvent.setAction(7);
        this.mListener.onTouchEvent(motionEvent);
        return true;
    }

    protected boolean handleGestureOnLongPress(MotionEvent motionEvent) {
        if (!handleEventUsingGesture(motionEvent) || this.mListener == null) {
            return false;
        }
        Log.d(TAG, "onLongPress: ");
        motionEvent.setAction(0);
        this.mListener.onTouchEvent(motionEvent, true);
        motionEvent.setAction(1);
        this.mListener.onTouchEvent(motionEvent, true);
        return true;
    }

    protected boolean handleGestureOnSingleTapUp(MotionEvent motionEvent) {
        postSoftInputMessage();
        if (!handleEventUsingGesture(motionEvent) || this.mListener == null) {
            return false;
        }
        Log.d(TAG, "onSingleTapUp: ");
        motionEvent.setAction(0);
        this.mListener.onTouchEvent(motionEvent);
        motionEvent.setAction(1);
        return this.mListener.onTouchEvent(motionEvent);
    }

    protected boolean handleGestureOnDoubleTap(MotionEvent motionEvent) {
        if (!handleEventUsingGesture(motionEvent) || this.mListener == null) {
            return false;
        }
        Log.d(TAG, "onDoubleTap: ");
        motionEvent.setAction(0);
        this.mListener.onTouchEvent(motionEvent);
        motionEvent.setAction(1);
        return this.mListener.onTouchEvent(motionEvent);
    }

    protected boolean handleGestureOnScroll(MotionEvent motionEvent, MotionEvent motionEvent2, float f, float f2) {
        if (!handleEventUsingGesture(motionEvent2) || this.mListener == null) {
            return false;
        }
        if (motionEvent2.getPointerCount() > 1) {
            this.mScrollType = ScrollType.DOUBLE;
            return this.mListener.onScroll(motionEvent, motionEvent2, f, f2);
        } else if (this.mScrollType == ScrollType.DOUBLE) {
            return true;
        } else {
            this.mScrollType = ScrollType.SINGLE;
            return this.mListener.onTouchEvent(this.mMotionEvent);
        }
    }

    protected boolean handleGestureOnFling(MotionEvent motionEvent, MotionEvent motionEvent2, float f, float f2) {
        return handleEventUsingGesture(motionEvent2) && this.mListener != null;
    }

    protected boolean handleScaleGestureOnScale(ScaleGestureDetector scaleGestureDetector) {
        Log.d(TAG, "handleScaleGestureOnScale: ");
        if (this.mListener == null) {
            return false;
        }
        return this.mListener.onScale(scaleGestureDetector);
    }

    protected boolean handleScaleGestureOnScaleBegin(ScaleGestureDetector scaleGestureDetector) {
        Log.d(TAG, "handleScaleGestureOnScaleBegin: ");
        if (this.mListener == null) {
            return false;
        }
        return this.mListener.onScaleBegin(scaleGestureDetector);
    }

    protected boolean handleOnScaleEnd(ScaleGestureDetector scaleGestureDetector) {
        Log.d(TAG, "handleOnScaleEnd: ");
        if (this.mListener == null) {
            return false;
        }
        return this.mListener.onScaleEnd(scaleGestureDetector);
    }

    private boolean skipGenericMotionEvent(MotionEvent motionEvent) {
        if (o.a(motionEvent)) {
            float x = motionEvent.getX();
            float y = motionEvent.getY();
            if (Math.abs(x - this.mPrevX) < 5.0f && Math.abs(y - this.mPrevY) < 5.0f) {
                return true;
            }
            this.mPrevX = x;
            this.mPrevY = y;
        }
        return false;
    }
}
