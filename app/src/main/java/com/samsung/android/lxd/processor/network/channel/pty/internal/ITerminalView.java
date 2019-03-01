package com.samsung.android.lxd.processor.network.channel.pty.internal;

import android.content.Context;
import android.graphics.Canvas;
import android.view.KeyEvent;
import android.view.MotionEvent;

public interface ITerminalView {
    boolean cancelPost(Runnable runnable);

    boolean dispatchKeyEvent(KeyEvent keyEvent);

    void draw(Canvas canvas);

    int getBottom();

    Context getContext();

    int getHeight();

    int getLeft();

    int getRight();

    int getTop();

    int getWidth();

    boolean isHorizontalScrollBarEnabled();

    boolean isVerticalScrollBarEnabled();

    boolean onCheckIsTextEditor();

    boolean onKeyDown(int i, KeyEvent keyEvent);

    boolean onKeyPreIme(int i, KeyEvent keyEvent);

    boolean onKeyUp(int i, KeyEvent keyEvent);

    void onScale(float f);

    void onScaleBegin();

    void onScaleEnd();

    boolean onTouchEvent(MotionEvent motionEvent);

    boolean post(Runnable runnable);

    boolean postDelayed(Runnable runnable, long j);

    void setBottom(int i);

    void setHorizontalScrollBarEnabled(boolean z);

    void setLeft(int i);

    void setRight(int i);

    void setTop(int i);

    void setVerticalScrollBarEnabled(boolean z);
}
