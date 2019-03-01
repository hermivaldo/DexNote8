package com.samsung.android.lxd.processor.network.channel.pty;

import android.view.ActionMode.Callback2;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.ScaleGestureDetector;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputConnection;
import com.samsung.android.lxd.processor.network.channel.INetworkChannel;
import com.samsung.android.lxd.processor.network.channel.INetworkChannel.ICallback;
import com.samsung.android.lxd.processor.network.channel.NetworkChannel;
import com.samsung.android.lxd.processor.network.channel.NetworkChannelType;

public class PtyNetworkChannel extends NetworkChannel implements IPtyNetworkChannel {
    private static final String TAG = "PtyNetworkChannel";
    private IPtyManager mManager = null;

    public String getSystemName() {
        return null;
    }

    public boolean sendMouseEvent(MotionEvent motionEvent, boolean z) {
        return false;
    }

    public InputConnection getInputConnection(View view, EditorInfo editorInfo) {
        return this.mManager.getInputConnection(view, editorInfo);
    }

    public int getFramebufferHeight() {
        return this.mCommonContext.getLocalHeight();
    }

    public int getFramebufferWidth() {
        return this.mCommonContext.getLocalWidth();
    }

    public INetworkChannel init(ICallback iCallback) {
        super.init(iCallback);
        this.mManager = new PtyManager();
        this.mManager.init(this.mCommonContext, new OnInitListener() {
            public void onInit(String str) {
                PtyNetworkChannel.this.mListener.onInit(str);
            }

            public void onError(Throwable th) {
                PtyNetworkChannel.this.mListener.onContainerError(th);
            }
        });
        return this;
    }

    public boolean isTextEditor() {
        return this.mManager.isTextEditor();
    }

    public void notifyDisplaySizeChange(int i, int i2) {
        this.mManager.notifyDisplaySizeChange(i, i2);
    }

    public INetworkChannel start() {
        super.start();
        this.mManager.start(new OnStartListener() {
            public void onCommit(String str) {
                if (PtyNetworkChannel.this.mListener != null) {
                    PtyNetworkChannel.this.mListener.onTextCommitted(str);
                }
            }

            public void onContext(Callback2 callback2) {
                PtyNetworkChannel.this.mCommonContext.enableSoftInput(false);
                if (PtyNetworkChannel.this.mListener != null) {
                    PtyNetworkChannel.this.mListener.onShowActionMode(callback2);
                }
            }

            public void onSelect(boolean z) {
                PtyNetworkChannel.this.mCommonContext.enableSoftInput(z ^ 1);
            }

            public void onStart() {
                PtyNetworkChannel.this.mCommonContext.enableSoftInput(true);
                if (PtyNetworkChannel.this.mListener != null) {
                    PtyNetworkChannel.this.mListener.onContainerStarted("");
                }
            }

            public void onUpdate(boolean z, boolean z2) {
                if (PtyNetworkChannel.this.mListener != null) {
                    PtyNetworkChannel.this.mListener.onContainerDraw(PtyNetworkChannel.this.mManager.getDisplayContext(z, z2));
                }
            }

            public void onFinish(Throwable th) {
                if (PtyNetworkChannel.this.mListener != null) {
                    PtyNetworkChannel.this.mListener.onContainerError(th);
                }
            }
        });
        return this;
    }

    public INetworkChannel stop() {
        super.stop();
        this.mManager.stop();
        this.mCommonContext.enableSoftInput(false);
        this.mListener.onContainerStopped();
        return this;
    }

    public INetworkChannel resume() {
        super.resume();
        this.mManager.resume();
        return this;
    }

    public NetworkChannelType getChannelType() {
        return NetworkChannelType.PTY;
    }

    public boolean isReady() {
        return this.mManager != null && this.mManager.isReady();
    }

    public boolean sendMouseEvent(MotionEvent motionEvent) {
        return this.mManager.notifyMouseEvent(motionEvent);
    }

    public boolean sendKeyEvent(int i, KeyEvent keyEvent) {
        return this.mManager.notifyKeyEvent(i, keyEvent);
    }

    public boolean sendDownEvent(MotionEvent motionEvent) {
        return this.mManager.notifyDownEvent(motionEvent);
    }

    public boolean sendLongPressEvent(MotionEvent motionEvent) {
        return this.mManager.notifyLongPressEvent(motionEvent);
    }

    public boolean sendSingleTapUpEvent(MotionEvent motionEvent) {
        return this.mManager.notifySingleTapUpEvent(motionEvent);
    }

    public boolean sendDoubleTapEvent(MotionEvent motionEvent) {
        return this.mManager.notifyDoubleTapEvent(motionEvent);
    }

    public boolean sendScrollEvent(MotionEvent motionEvent, MotionEvent motionEvent2, float f, float f2) {
        return this.mManager.notifyScrollEvent(motionEvent, motionEvent2, f, f2);
    }

    public boolean sendFlingEvent(MotionEvent motionEvent, MotionEvent motionEvent2, float f, float f2) {
        return this.mManager.notifyFlingEvent(motionEvent, motionEvent2, f, f2);
    }

    public boolean sendScaleEvent(ScaleGestureDetector scaleGestureDetector) {
        return this.mManager.notifyScaleEvent(scaleGestureDetector);
    }

    public boolean sendScaleBeginEvent(ScaleGestureDetector scaleGestureDetector) {
        return this.mManager.notifyScaleBeginEvent(scaleGestureDetector);
    }

    public boolean sendScaleEndEvent(ScaleGestureDetector scaleGestureDetector) {
        return this.mManager.notifyScaleEndEvent(scaleGestureDetector);
    }
}
