package com.samsung.android.lxd.processor.network.channel;

import android.view.MotionEvent;
import android.view.ScaleGestureDetector;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputConnection;
import com.samsung.android.lxd.processor.ICommonContext;
import com.samsung.android.lxd.processor.network.channel.INetworkChannel.ICallback;
import com.samsung.android.lxd.processor.utils.log.Log;

public abstract class NetworkChannel implements INetworkChannel {
    private static final String TAG = "NetworkChannel";
    protected NetworkChannelType mChannelType;
    protected ICommonContext mCommonContext;
    protected String mImageVersion;
    protected ICallback mListener;

    public InputConnection getInputConnection(View view, EditorInfo editorInfo) {
        return null;
    }

    public boolean isTextEditor() {
        return false;
    }

    public void notifyDisplaySizeChange(int i, int i2) {
    }

    public void sendCutText(String str) {
    }

    public boolean sendDoubleTapEvent(MotionEvent motionEvent) {
        return false;
    }

    public boolean sendDownEvent(MotionEvent motionEvent) {
        return false;
    }

    public boolean sendFlingEvent(MotionEvent motionEvent, MotionEvent motionEvent2, float f, float f2) {
        return false;
    }

    public boolean sendLongPressEvent(MotionEvent motionEvent) {
        return false;
    }

    public boolean sendScaleBeginEvent(ScaleGestureDetector scaleGestureDetector) {
        return false;
    }

    public boolean sendScaleEndEvent(ScaleGestureDetector scaleGestureDetector) {
        return false;
    }

    public boolean sendScaleEvent(ScaleGestureDetector scaleGestureDetector) {
        return false;
    }

    public boolean sendScrollEvent(MotionEvent motionEvent, MotionEvent motionEvent2, float f, float f2) {
        return false;
    }

    public boolean sendSingleTapUpEvent(MotionEvent motionEvent) {
        return false;
    }

    public void startMonitoring() {
    }

    public void stopMonitoring() {
    }

    public NetworkChannel() {
        this.mChannelType = null;
        this.mCommonContext = null;
        this.mImageVersion = null;
        this.mListener = null;
        this.mChannelType = getChannelType();
    }

    public String getConnectionInfo() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("\nSystem\t: ");
        stringBuilder.append(getSystemName());
        stringBuilder.append("\nScreen\t: ");
        stringBuilder.append(getFramebufferWidth());
        stringBuilder.append("x");
        stringBuilder.append(getFramebufferHeight());
        stringBuilder.append("\nChannel\t: ");
        stringBuilder.append(getChannelType().toString());
        return stringBuilder.toString();
    }

    public INetworkChannel init(ICallback iCallback) {
        Log.d(TAG, "init");
        this.mListener = iCallback;
        return this;
    }

    public INetworkChannel start() {
        Log.d(TAG, "start");
        return this;
    }

    public INetworkChannel stop() {
        Log.d(TAG, "stop");
        return this;
    }

    public INetworkChannel pause() {
        Log.d(TAG, "pause");
        return this;
    }

    public INetworkChannel resume() {
        Log.d(TAG, "resume");
        return this;
    }

    public INetworkChannel setContext(ICommonContext iCommonContext) {
        this.mCommonContext = iCommonContext;
        return this;
    }

    public INetworkChannel setImageVersion(String str) {
        this.mImageVersion = str;
        return this;
    }
}
