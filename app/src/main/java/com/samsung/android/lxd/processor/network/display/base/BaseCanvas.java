package com.samsung.android.lxd.processor.network.display.base;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.ActionMode.Callback2;
import android.view.PointerIcon;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputConnection;
import com.samsung.android.lxd.a.o;
import com.samsung.android.lxd.processor.ICommonContext;
import com.samsung.android.lxd.processor.network.display.INetworkDisplay;
import com.samsung.android.lxd.processor.network.display.INetworkDisplay.ICallback;
import com.samsung.android.lxd.processor.network.display.NetworkDisplayContext;
import com.samsung.android.lxd.processor.network.display.NetworkDisplayType;
import com.samsung.android.lxd.processor.network.display.NetworkScreenType;
import com.samsung.android.lxd.processor.utils.log.Log;

public class BaseCanvas extends View implements INetworkDisplay {
    private static final String TAG = "BaseCanvas";
    private ICommonContext mCommonContext = null;
    private boolean mIsDrawEnabled = true;
    private ICallback mListener = null;
    private NetworkDisplayContext mNetworkDisplayContext = new NetworkDisplayContext();
    private NetworkScreenType mScreenType = null;
    private State mState = State.INVALID;

    private enum State {
        INVALID,
        INITIATED,
        STARTED,
        STOPPED
    }

    public void updateCursorType(PointerIcon pointerIcon) {
    }

    public BaseCanvas(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    protected int computeHorizontalScrollRange() {
        if (this.mState != State.STARTED || this.mCommonContext == null) {
            return super.computeHorizontalScrollRange();
        }
        return this.mNetworkDisplayContext.getHorizontalScrollRange();
    }

    protected int computeHorizontalScrollOffset() {
        if (this.mState != State.STARTED || this.mCommonContext == null) {
            return super.computeHorizontalScrollOffset();
        }
        return this.mNetworkDisplayContext.getHorizontalScrollOffset();
    }

    protected int computeHorizontalScrollExtent() {
        if (this.mState != State.STARTED || this.mCommonContext == null) {
            return super.computeHorizontalScrollExtent();
        }
        return this.mNetworkDisplayContext.getHorizontalScrollExtent();
    }

    protected int computeVerticalScrollRange() {
        if (this.mState != State.STARTED || this.mCommonContext == null) {
            return super.computeVerticalScrollRange();
        }
        return this.mNetworkDisplayContext.getVerticalScrollRange();
    }

    protected int computeVerticalScrollOffset() {
        if (this.mState != State.STARTED || this.mCommonContext == null) {
            return super.computeVerticalScrollOffset();
        }
        return this.mNetworkDisplayContext.getVerticalScrollOffset();
    }

    protected int computeVerticalScrollExtent() {
        if (this.mState != State.STARTED || this.mCommonContext == null) {
            return super.computeVerticalScrollExtent();
        }
        return this.mNetworkDisplayContext.getVerticalScrollExtent();
    }

    public boolean onCheckIsTextEditor() {
        if (this.mState == State.INVALID || this.mCommonContext == null) {
            return super.onCheckIsTextEditor();
        }
        return this.mCommonContext.isTextEditor();
    }

    public InputConnection onCreateInputConnection(EditorInfo editorInfo) {
        if (this.mState == State.INVALID || this.mCommonContext == null) {
            return super.onCreateInputConnection(editorInfo);
        }
        this.mState = State.STARTED;
        o.a(new Runnable() {
            public void run() {
                BaseCanvas.this.mListener.onInputConnectionReady();
            }
        }, 50, true);
        return this.mCommonContext.getInputConnection(this, editorInfo);
    }

    protected void onSizeChanged(int i, int i2, int i3, int i4) {
        String str = TAG;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("onSizeChanged : ");
        stringBuilder.append(i);
        stringBuilder.append("x");
        stringBuilder.append(i2);
        Log.d(str, stringBuilder.toString());
        if (this.mState == State.STARTED) {
            notifySizeChange();
        }
    }

    public INetworkDisplay init(ICallback iCallback) {
        Log.d(TAG, "init");
        this.mListener = iCallback;
        this.mState = State.INITIATED;
        return this;
    }

    public INetworkDisplay init(INetworkDisplay iNetworkDisplay) {
        return iNetworkDisplay.duplicate(this);
    }

    public INetworkDisplay start() {
        Log.d(TAG, "start");
        post(new Runnable() {
            public void run() {
                BaseCanvas.this.notifySizeChange();
                BaseCanvas.this.setFocusable(true);
                BaseCanvas.this.setFocusableInTouchMode(true);
                BaseCanvas.this.requestFocus();
            }
        });
        return this;
    }

    public INetworkDisplay stop() {
        Log.d(TAG, "stop");
        this.mState = State.STOPPED;
        return this;
    }

    public void draw(NetworkDisplayContext networkDisplayContext) {
        if (this.mIsDrawEnabled) {
            this.mNetworkDisplayContext = networkDisplayContext;
            if (!this.mNetworkDisplayContext.showHorizontalScroll() && !this.mNetworkDisplayContext.showVerticalScroll()) {
                invalidate();
            } else if (!awakenScrollBars()) {
                invalidate();
            }
        }
    }

    protected void onDraw(Canvas canvas) {
        if (this.mNetworkDisplayContext.getDrawable() != null) {
            this.mNetworkDisplayContext.getDrawable().draw(canvas);
        }
    }

    public INetworkDisplay duplicate(INetworkDisplay iNetworkDisplay) {
        return iNetworkDisplay.setContext(this.mCommonContext).init(this.mListener);
    }

    public void disableUpdate() {
        this.mIsDrawEnabled = false;
    }

    public void enableUpdate() {
        this.mIsDrawEnabled = true;
    }

    public NetworkDisplayType getDisplayType() {
        return NetworkDisplayType.BASE;
    }

    public NetworkScreenType getScreenType() {
        return this.mScreenType;
    }

    public INetworkDisplay setContext(ICommonContext iCommonContext) {
        this.mCommonContext = iCommonContext;
        return this;
    }

    public INetworkDisplay setScreenType(NetworkScreenType networkScreenType) {
        this.mScreenType = networkScreenType;
        return this;
    }

    public INetworkDisplay showActionMode(Callback2 callback2) {
        startActionMode(callback2, 1);
        return this;
    }

    private void notifySizeChange() {
        int width = getWidth();
        int height = getHeight();
        String str = TAG;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("notifySizeChange : ");
        stringBuilder.append(width);
        stringBuilder.append("x");
        stringBuilder.append(height);
        Log.d(str, stringBuilder.toString());
        if (this.mListener != null && width > 0 && height > 0) {
            this.mListener.onSizeChanged(width, height);
        }
    }
}
