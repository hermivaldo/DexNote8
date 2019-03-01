package com.samsung.android.lxd.processor.network.channel.pty.internal;

import android.view.MotionEvent;

public class MouseTrackingFlingRunnable extends FlingRunnable {
    private static final float SCALE = 0.15f;
    private int mLastY;
    private MotionEvent mMotionEvent;

    MouseTrackingFlingRunnable(ITerminalViewHelper iTerminalViewHelper) {
        super(iTerminalViewHelper, SCALE);
    }

    public void fling(MotionEvent motionEvent, float f, float f2, OnUpdateListener onUpdateListener) {
        this.mScroller.fling(0, 0, -((int) (f * SCALE)), -((int) (f2 * SCALE)), 0, 0, -100, 100);
        this.mLastY = 0;
        this.mMotionEvent = motionEvent;
        this.mListener = onUpdateListener;
        this.mHelper.post(this);
    }

    public void run() {
        if (this.mScroller.isFinished()) {
            this.mListener.onFinish();
        } else if (this.mHelper.isMouseTrackingActive()) {
            boolean computeScrollOffset = this.mScroller.computeScrollOffset();
            int currY = this.mScroller.getCurrY();
            while (this.mLastY < currY) {
                this.mHelper.sendMouseEventCode(this.mMotionEvent, 65);
                this.mLastY++;
            }
            while (this.mLastY > currY) {
                this.mHelper.sendMouseEventCode(this.mMotionEvent, 64);
                this.mLastY--;
            }
            if (computeScrollOffset) {
                this.mHelper.post(this);
            }
        }
    }
}
