package com.samsung.android.lxd.processor.network.channel.pty.internal;

import android.content.Context;
import android.view.KeyEvent;
import android.view.MotionEvent;
import com.samsung.android.lxd.processor.network.display.INetworkDisplay;

public interface ITerminalViewHelper {
    boolean cancelPost(Runnable runnable);

    boolean dispatchKeyEvent(KeyEvent keyEvent);

    int getActiveTranscriptRows();

    int getBottom();

    int getCharacterHeight();

    float getCharacterWidth();

    Context getContext();

    int getCursorCol();

    int getCursorRow();

    int getHeight();

    String getImeBuffer();

    TerminalKeyListener getKeyListener();

    boolean getKeypadApplicationMode();

    int getLeft();

    INetworkDisplay getLocalDisplay();

    float getMinFontSize();

    TranscriptScreen getScreen();

    int getScreenCols();

    int getScreenRows();

    String getSelectedText(int i, int i2, int i3, int i4);

    TerminalSession getSession();

    int getTop();

    int getTopOfScreenMargin();

    int getTopRow();

    int getTotalCols();

    int getTotalRows();

    int getWidth();

    void invalidate();

    boolean isMouseTrackingActive();

    boolean isOnMainScreen();

    boolean isUsingMouse();

    boolean isUsingPen();

    boolean isUsingStylus();

    boolean post(Runnable runnable);

    boolean postDelayed(Runnable runnable, long j);

    boolean scroll(float f, float f2);

    void sendMouseEventCode(MotionEvent motionEvent, int i);

    void setImeBuffer(String str);

    void setTopRow(int i);

    void showActionMode();

    void write(String str);
}
