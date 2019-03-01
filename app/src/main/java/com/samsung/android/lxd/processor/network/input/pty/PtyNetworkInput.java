package com.samsung.android.lxd.processor.network.input.pty;

import android.view.MotionEvent;
import com.samsung.android.lxd.a.o;
import com.samsung.android.lxd.processor.network.input.INetworkInput;
import com.samsung.android.lxd.processor.network.input.INetworkInput.ICallback;
import com.samsung.android.lxd.processor.network.input.NetworkInput;
import com.samsung.android.lxd.processor.utils.log.Log;

public class PtyNetworkInput extends NetworkInput {
    private static final String TAG = "PtyNetworkInput";
    private boolean mInputConnectionReady;
    private SoftInputMode mTextInputTypeOnPress;

    private enum PostReason {
        CLICK,
        READY,
        TOUCH
    }

    public INetworkInput init(ICallback iCallback) {
        super.init(iCallback);
        setTextInputTypeOnPress(SoftInputMode.HIDE);
        setInputConnectionReady(false);
        return this;
    }

    protected boolean handleGestureOnSingleTapUp(MotionEvent motionEvent) {
        tryPostMessage(PostReason.TOUCH);
        return super.handleGestureOnSingleTapUp(motionEvent);
    }

    protected boolean handleGenericMotionInternal(MotionEvent motionEvent) {
        if (!processTouchEvent(motionEvent)) {
            super.handleGenericMotionInternal(motionEvent);
        }
        return false;
    }

    protected boolean handleTouchEventInternal(MotionEvent motionEvent) {
        if (!processTouchEvent(motionEvent)) {
            super.handleTouchEventInternal(motionEvent);
        }
        return false;
    }

    public void notifyInputConnectionReady() {
        setInputConnectionReady(true);
        tryPostMessage(PostReason.READY);
    }

    private boolean processTouchEvent(MotionEvent motionEvent) {
        if (this.mCommonContext.getDisplayContext() == null || !o.d(this.mCommonContext.getDisplayContext()) || !motionEvent.isFromSource(8194)) {
            return false;
        }
        switch (motionEvent.getAction()) {
            case 0:
                setTextInputTypeOnPress(motionEvent.getButtonState() != 2 ? this.mTextInputType : SoftInputMode.HIDE);
                break;
            case 1:
                tryPostMessage(PostReason.CLICK);
                break;
        }
        return true;
    }

    private boolean getInputConnectionReady() {
        return this.mInputConnectionReady;
    }

    private void setInputConnectionReady(boolean z) {
        String str = TAG;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("setInputConnectionReady : ");
        stringBuilder.append(z);
        Log.i(str, stringBuilder.toString());
        this.mInputConnectionReady = z;
    }

    private SoftInputMode getTextInputTypeOnPress() {
        return this.mTextInputTypeOnPress;
    }

    private void setTextInputTypeOnPress(SoftInputMode softInputMode) {
        String str = TAG;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("setTextInputTypeOnPress : ");
        stringBuilder.append(softInputMode);
        Log.i(str, stringBuilder.toString());
        this.mTextInputTypeOnPress = softInputMode;
    }

    private void tryPostMessage(PostReason postReason) {
        String str = TAG;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("tryPostMessage(");
        stringBuilder.append(postReason);
        stringBuilder.append(")");
        Log.i(str, stringBuilder.toString());
        if (getInputConnectionReady()) {
            switch (postReason) {
                case CLICK:
                    if (getTextInputTypeOnPress() != SoftInputMode.SHOW) {
                        Log.i(TAG, "Mode change detected!");
                        break;
                    }
                case TOUCH:
                case READY:
                    if (this.mTextInputType == SoftInputMode.SHOW) {
                        postSoftInputMessage();
                        break;
                    }
                    break;
            }
            return;
        }
        Log.i(TAG, "Connection not ready!");
    }
}
