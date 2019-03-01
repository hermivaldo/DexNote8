package com.samsung.android.lxd.processor.network.channel.pty.internal;

public class BlinkCursorRunnable implements Runnable {
    private static final int CURSOR_BLINK_PERIOD = 500;
    private boolean mEnabled = true;
    private final ITerminalViewHelper mHelper;
    private boolean mRunning = false;
    private boolean mVisible = true;

    BlinkCursorRunnable(ITerminalViewHelper iTerminalViewHelper) {
        this.mHelper = iTerminalViewHelper;
    }

    boolean isVisible() {
        return this.mVisible;
    }

    BlinkCursorRunnable enable(boolean z) {
        this.mEnabled = z;
        return this;
    }

    BlinkCursorRunnable resume() {
        this.mRunning = true;
        this.mHelper.postDelayed(this, 500);
        return this;
    }

    BlinkCursorRunnable pause() {
        this.mRunning = false;
        return this;
    }

    boolean isEnabled() {
        return this.mEnabled;
    }

    public void run() {
        if (this.mRunning) {
            if (this.mEnabled) {
                this.mVisible ^= true;
                this.mHelper.postDelayed(this, 500);
            } else {
                this.mVisible = true;
            }
            this.mHelper.invalidate();
        }
    }
}
