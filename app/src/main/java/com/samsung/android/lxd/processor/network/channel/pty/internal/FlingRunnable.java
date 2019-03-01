package com.samsung.android.lxd.processor.network.channel.pty.internal;

import android.view.MotionEvent;
import android.widget.Scroller;

public class FlingRunnable implements Runnable {
    private static final float SCALE = 0.25f;
    protected final ITerminalViewHelper mHelper;
    protected OnUpdateListener mListener;
    protected final float mScale;
    protected final Scroller mScroller;

    interface OnUpdateListener {
        void onFinish();
    }

    FlingRunnable(ITerminalViewHelper iTerminalViewHelper) {
        this(iTerminalViewHelper, SCALE);
    }

    FlingRunnable(ITerminalViewHelper iTerminalViewHelper, float f) {
        this.mHelper = iTerminalViewHelper;
        this.mListener = null;
        this.mScroller = new Scroller(this.mHelper.getContext());
        this.mScale = f;
    }

    public void fling(MotionEvent motionEvent, float f, float f2, OnUpdateListener onUpdateListener) {
        this.mScroller.fling(0, this.mHelper.getTopRow(), -((int) (f * this.mScale)), -((int) (f2 * this.mScale)), 0, 0, -this.mHelper.getActiveTranscriptRows(), 0);
        this.mListener = onUpdateListener;
        this.mHelper.post(this);
    }

    public void run() {
        if (this.mScroller.isFinished()) {
            this.mListener.onFinish();
        } else if (!this.mHelper.isMouseTrackingActive()) {
            boolean computeScrollOffset = this.mScroller.computeScrollOffset();
            int currY = this.mScroller.getCurrY();
            if (currY != this.mHelper.getTopRow()) {
                this.mHelper.setTopRow(currY);
                this.mHelper.invalidate();
            }
            if (computeScrollOffset) {
                this.mHelper.post(this);
            }
        }
    }
}
