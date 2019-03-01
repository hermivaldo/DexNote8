package com.samsung.android.lxd.processor.network.channel.pty.internal;

import android.content.Context;
import android.graphics.Canvas;
import android.view.GestureDetector.SimpleOnGestureListener;
import android.view.KeyEvent;
import android.view.MotionEvent;
import com.samsung.android.lxd.a.o;
import com.samsung.android.lxd.processor.ICommonContext;

public class TerminalViewBase extends SimpleOnGestureListener implements ITerminalView {
    private static final int SCROLLBARS_HORIZONTAL = 256;
    private static final int SCROLLBARS_VERTICAL = 512;
    private int mBottom;
    private final ICommonContext mContext;
    private int mLeft;
    private int mRight;
    private int mTop;
    private int mViewFlags = 768;

    protected int computeHorizontalScrollExtent() {
        return 0;
    }

    protected int computeHorizontalScrollOffset() {
        return 0;
    }

    protected int computeHorizontalScrollRange() {
        return 0;
    }

    protected int computeVerticalScrollExtent() {
        return 0;
    }

    protected int computeVerticalScrollOffset() {
        return 0;
    }

    protected int computeVerticalScrollRange() {
        return 0;
    }

    public boolean onCheckIsTextEditor() {
        return false;
    }

    protected void onDraw(Canvas canvas) {
    }

    public boolean onKeyDown(int i, KeyEvent keyEvent) {
        return false;
    }

    public boolean onKeyPreIme(int i, KeyEvent keyEvent) {
        return false;
    }

    public boolean onKeyUp(int i, KeyEvent keyEvent) {
        return false;
    }

    public void onScale(float f) {
    }

    public void onScaleBegin() {
    }

    public void onScaleEnd() {
    }

    protected void onSizeChanged(int i, int i2, int i3, int i4) {
    }

    public boolean onTouchEvent(MotionEvent motionEvent) {
        return false;
    }

    TerminalViewBase(ICommonContext iCommonContext) {
        this.mContext = iCommonContext;
    }

    public boolean cancelPost(Runnable runnable) {
        o.b(runnable);
        return true;
    }

    public boolean dispatchKeyEvent(KeyEvent keyEvent) {
        switch (keyEvent.getAction()) {
            case 0:
                onKeyDown(keyEvent.getKeyCode(), keyEvent);
                break;
            case 1:
                onKeyUp(keyEvent.getKeyCode(), keyEvent);
                break;
        }
        return true;
    }

    public void draw(Canvas canvas) {
        onDraw(canvas);
    }

    public Context getContext() {
        return this.mContext.getDisplayContext();
    }

    public int getLeft() {
        return this.mLeft;
    }

    public int getTop() {
        return this.mTop;
    }

    public int getRight() {
        return this.mRight;
    }

    public int getBottom() {
        return this.mBottom;
    }

    public int getHeight() {
        return this.mBottom - this.mTop;
    }

    public int getWidth() {
        return this.mRight - this.mLeft;
    }

    public boolean isHorizontalScrollBarEnabled() {
        return (this.mViewFlags & SCROLLBARS_HORIZONTAL) == SCROLLBARS_HORIZONTAL;
    }

    public boolean isVerticalScrollBarEnabled() {
        return (this.mViewFlags & SCROLLBARS_VERTICAL) == SCROLLBARS_VERTICAL;
    }

    public boolean post(Runnable runnable) {
        o.a(runnable, true);
        return true;
    }

    public boolean postDelayed(Runnable runnable, long j) {
        o.a(runnable, j, true);
        return true;
    }

    public void setBottom(int i) {
        this.mBottom = i;
    }

    public void setLeft(int i) {
        this.mLeft = i;
    }

    public void setRight(int i) {
        this.mRight = i;
    }

    public void setHorizontalScrollBarEnabled(boolean z) {
        if (isHorizontalScrollBarEnabled() != z) {
            this.mViewFlags ^= SCROLLBARS_HORIZONTAL;
        }
    }

    public void setVerticalScrollBarEnabled(boolean z) {
        if (isVerticalScrollBarEnabled() != z) {
            this.mViewFlags ^= SCROLLBARS_VERTICAL;
        }
    }

    public void setTop(int i) {
        this.mTop = i;
    }
}
