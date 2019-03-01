package com.samsung.android.lxd.processor.network.channel.pty.internal;

import android.graphics.Canvas;
import android.graphics.Point;
import android.view.ActionMode.Callback2;
import android.view.MotionEvent;

public interface ITextSelector {
    Callback2 getActionModeCallback(OnActionModeListener onActionModeListener);

    Point getClosingPoint();

    int getMaxHolderWidth();

    Point getOpeningPoint();

    boolean isDraggingHolder();

    boolean isOnCursor();

    boolean isOnCursor(int i, int i2);

    boolean isSelecting();

    boolean onDown(MotionEvent motionEvent);

    void onDraw(Canvas canvas);

    void onFling();

    void onFlingEnd();

    boolean onLongPress(MotionEvent motionEvent);

    boolean onMove(MotionEvent motionEvent);

    boolean onSingleTapUp(MotionEvent motionEvent);

    boolean onUp(MotionEvent motionEvent);

    void toggleActionMode(int i, int i2);

    boolean updateSelection(int i, int i2);
}
