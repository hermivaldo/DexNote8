package com.samsung.android.lxd.processor.network.display.gl;

import android.content.Context;
import android.util.AttributeSet;
import android.view.ActionMode.Callback2;
import android.view.MotionEvent;
import android.view.PointerIcon;
import com.samsung.android.lxd.LxdApplication;
import com.samsung.android.lxd.processor.ICommonContext;
import com.samsung.android.lxd.processor.network.display.INetworkDisplay;
import com.samsung.android.lxd.processor.network.display.INetworkDisplay.ICallback;
import com.samsung.android.lxd.processor.network.display.NetworkDisplayContext;
import com.samsung.android.lxd.processor.network.display.NetworkDisplayType;
import com.samsung.android.lxd.processor.network.display.NetworkScreenType;
import com.samsung.android.lxd.processor.utils.log.Log;

public class GLCanvas extends GLSurfaceViewHelper implements INetworkDisplay {
    private static final String TAG = "GLCanvas";
    private ICommonContext mCommonContext = null;
    private boolean mIsDrawEnabled = true;
    private ICallback mListener = null;
    private PointerIcon mPointerIcon = PointerIcon.getSystemIcon(LxdApplication.a(), 1000);
    private NetworkScreenType mScreenType = null;

    public INetworkDisplay showActionMode(Callback2 callback2) {
        return null;
    }

    public GLCanvas(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    protected void onSizeChanged(int i, int i2, int i3, int i4) {
        super.onSizeChanged(i, i2, i3, i4);
    }

    public INetworkDisplay init(ICallback iCallback) {
        Log.d(TAG, "init");
        this.mListener = iCallback;
        return this;
    }

    public INetworkDisplay init(INetworkDisplay iNetworkDisplay) {
        return iNetworkDisplay.duplicate(this);
    }

    public INetworkDisplay start() {
        Log.d(TAG, "start");
        return this;
    }

    public INetworkDisplay stop() {
        Log.d(TAG, "stop");
        destroy();
        return this;
    }

    public void draw(NetworkDisplayContext networkDisplayContext) {
        if (this.mIsDrawEnabled) {
            updateView(networkDisplayContext.getBitmapPixels(), this.mCommonContext.getRemoteWidth(), this.mCommonContext.getRemoteHeight());
        }
    }

    public void updateCursorType(PointerIcon pointerIcon) {
        setPointerIcon(pointerIcon);
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
        return NetworkDisplayType.GLES;
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

    public PointerIcon onResolvePointerIcon(MotionEvent motionEvent, int i) {
        return this.mPointerIcon;
    }

    public void setPointerIcon(PointerIcon pointerIcon) {
        this.mPointerIcon = pointerIcon;
        super.setPointerIcon(this.mPointerIcon);
    }
}
