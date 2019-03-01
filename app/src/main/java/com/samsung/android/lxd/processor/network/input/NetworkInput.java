package com.samsung.android.lxd.processor.network.input;

import android.os.Handler;
import android.os.Handler.Callback;
import android.os.Message;
import android.view.GestureDetector;
import android.view.GestureDetector.SimpleOnGestureListener;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.PointerIcon;
import android.view.ScaleGestureDetector;
import android.view.ScaleGestureDetector.SimpleOnScaleGestureListener;
import com.samsung.android.lxd.LxdApplication;
import com.samsung.android.lxd.a;
import com.samsung.android.lxd.a.o;
import com.samsung.android.lxd.f;
import com.samsung.android.lxd.processor.ICommonContext;
import com.samsung.android.lxd.processor.network.input.INetworkInput.ICallback;
import com.samsung.android.lxd.processor.utils.log.Log;

public abstract class NetworkInput implements INetworkInput {
    protected static final int MESSAGE_HANDLE_SOFT_INPUT = 1;
    protected static final float MIN_DISTANCE = 5.0f;
    private static final String TAG = "NetworkInput";
    protected ICommonContext mCommonContext = null;
    protected GestureDetector mGestureDetector = new GestureDetector(LxdApplication.a(), new SimpleOnGestureListener() {
        public boolean onDown(MotionEvent motionEvent) {
            if (NetworkInput.this.handleGestureOnDown(motionEvent)) {
                return true;
            }
            return super.onDown(motionEvent);
        }

        public void onLongPress(MotionEvent motionEvent) {
            if (!NetworkInput.this.handleGestureOnLongPress(motionEvent)) {
                super.onLongPress(motionEvent);
            }
        }

        public boolean onSingleTapUp(MotionEvent motionEvent) {
            if (NetworkInput.this.handleGestureOnSingleTapUp(motionEvent)) {
                return true;
            }
            return super.onSingleTapUp(motionEvent);
        }

        public boolean onDoubleTap(MotionEvent motionEvent) {
            if (NetworkInput.this.handleGestureOnDoubleTap(motionEvent)) {
                return true;
            }
            return super.onDoubleTap(motionEvent);
        }

        public boolean onScroll(MotionEvent motionEvent, MotionEvent motionEvent2, float f, float f2) {
            if (NetworkInput.this.handleGestureOnScroll(motionEvent, motionEvent2, f, f2)) {
                return true;
            }
            return super.onScroll(motionEvent, motionEvent2, f, f2);
        }

        public boolean onFling(MotionEvent motionEvent, MotionEvent motionEvent2, float f, float f2) {
            if (NetworkInput.this.handleGestureOnFling(motionEvent, motionEvent2, f, f2)) {
                return true;
            }
            return super.onFling(motionEvent, motionEvent2, f, f2);
        }
    });
    private Handler mInputHandler = new Handler(new Callback() {
        public boolean handleMessage(Message message) {
            a c = f.c();
            if (c != null && message.what == 1) {
                try {
                    switch (AnonymousClass4.$SwitchMap$com$samsung$android$lxd$processor$network$input$NetworkInput$SoftInputMode[((SoftInputMode) message.obj).ordinal()]) {
                        case 1:
                            o.a(c.getCurrentFocus(), 0);
                            break;
                        case 2:
                            o.b(c.getCurrentFocus(), 0);
                            break;
                        case 3:
                            o.a(2, 0);
                            break;
                    }
                } catch (NullPointerException e) {
                    String access$000 = NetworkInput.TAG;
                    StringBuilder stringBuilder = new StringBuilder();
                    stringBuilder.append("handleMessage: ");
                    stringBuilder.append(e.toString());
                    Log.i(access$000, stringBuilder.toString());
                }
            }
            return false;
        }
    });
    protected ICallback mListener = null;
    protected PointerIcon mPointerIcon = null;
    protected ScaleGestureDetector mScaleGestureDetector = new ScaleGestureDetector(LxdApplication.a(), new SimpleOnScaleGestureListener() {
        public boolean onScale(ScaleGestureDetector scaleGestureDetector) {
            if (NetworkInput.this.handleScaleGestureOnScale(scaleGestureDetector)) {
                return true;
            }
            return super.onScale(scaleGestureDetector);
        }

        public boolean onScaleBegin(ScaleGestureDetector scaleGestureDetector) {
            if (NetworkInput.this.handleScaleGestureOnScaleBegin(scaleGestureDetector)) {
                return true;
            }
            return super.onScaleBegin(scaleGestureDetector);
        }

        public void onScaleEnd(ScaleGestureDetector scaleGestureDetector) {
            if (!NetworkInput.this.handleOnScaleEnd(scaleGestureDetector)) {
                super.onScaleEnd(scaleGestureDetector);
            }
        }
    });
    protected SoftInputMode mTextInputType = null;

    /* renamed from: com.samsung.android.lxd.processor.network.input.NetworkInput$4 */
    static /* synthetic */ class AnonymousClass4 {
        static final /* synthetic */ int[] $SwitchMap$com$samsung$android$lxd$processor$network$input$NetworkInput$SoftInputMode = new int[SoftInputMode.values().length];

        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing block: B:6:?, code:
            $SwitchMap$com$samsung$android$lxd$processor$network$input$NetworkInput$SoftInputMode[com.samsung.android.lxd.processor.network.input.NetworkInput.SoftInputMode.TOGGLE.ordinal()] = 3;
     */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        static {
            $SwitchMap$com$samsung$android$lxd$processor$network$input$NetworkInput$SoftInputMode[SoftInputMode.SHOW.ordinal()] = 1;
            try {
            } catch (NoSuchFieldError unused) {
                $SwitchMap$com$samsung$android$lxd$processor$network$input$NetworkInput$SoftInputMode[SoftInputMode.HIDE.ordinal()] = 2;
            }
            try {
            } catch (NoSuchFieldError unused2) {
            }
        }
    }

    protected enum SoftInputMode {
        SHOW,
        HIDE,
        TOGGLE
    }

    protected boolean handleGenericMotionInternal(MotionEvent motionEvent) {
        return false;
    }

    public void notifyInputConnectionReady() {
    }

    public boolean handleGenericMotion(MotionEvent motionEvent) {
        motionEvent = adjustCoordinates(motionEvent);
        boolean z = true;
        if (handleGenericMotionInternal(motionEvent)) {
            return true;
        }
        if (this.mListener == null || !this.mListener.onGenericMotion(motionEvent)) {
            z = false;
        }
        return z;
    }

    public boolean handleKeyDown(int i, KeyEvent keyEvent) {
        return this.mListener != null && this.mListener.onKeyDown(i, keyEvent);
    }

    public boolean handleKeyUp(int i, KeyEvent keyEvent) {
        return this.mListener != null && this.mListener.onKeyUp(i, keyEvent);
    }

    public boolean handleTouchEvent(MotionEvent motionEvent) {
        motionEvent = adjustCoordinates(motionEvent);
        boolean z = true;
        if (handleTouchEventInternal(motionEvent)) {
            return true;
        }
        if (this.mListener == null || !this.mListener.onTouchEvent(motionEvent)) {
            z = false;
        }
        return z;
    }

    public void handleSendCutText(String str) {
        if (this.mListener != null) {
            this.mListener.onTextCut(str);
        }
    }

    public INetworkInput init(ICallback iCallback) {
        Log.d(TAG, "init");
        this.mListener = iCallback;
        this.mTextInputType = SoftInputMode.HIDE;
        return this;
    }

    public PointerIcon getCursorInfo() {
        return this.mPointerIcon;
    }

    public void updateCursorType(PointerIcon pointerIcon, boolean z) {
        this.mPointerIcon = pointerIcon;
    }

    public INetworkInput setContext(ICommonContext iCommonContext) {
        this.mCommonContext = iCommonContext;
        return this;
    }

    public INetworkInput start() {
        Log.d(TAG, "start");
        this.mPointerIcon = PointerIcon.getSystemIcon(LxdApplication.a(), 1000);
        return this;
    }

    public INetworkInput stop() {
        Log.d(TAG, "stop");
        return this;
    }

    public INetworkInput enableSoftInput(boolean z) {
        this.mTextInputType = z ? SoftInputMode.SHOW : SoftInputMode.HIDE;
        return this;
    }

    protected boolean handleTouchEventInternal(MotionEvent motionEvent) {
        this.mGestureDetector.onTouchEvent(motionEvent);
        this.mScaleGestureDetector.onTouchEvent(motionEvent);
        return false;
    }

    protected boolean postSoftInputMessage() {
        this.mInputHandler.removeMessages(1);
        this.mInputHandler.sendMessageDelayed(this.mInputHandler.obtainMessage(1, this.mTextInputType), 50);
        return true;
    }

    protected MotionEvent adjustCoordinates(MotionEvent motionEvent) {
        int[] iArr = new int[2];
        this.mCommonContext.getLocationInWindow(iArr);
        float x = motionEvent.getX() - ((float) iArr[0]);
        float y = motionEvent.getY() - ((float) iArr[1]);
        float remoteWidth = ((float) this.mCommonContext.getRemoteWidth()) / ((float) this.mCommonContext.getLocalWidth());
        float remoteHeight = ((float) this.mCommonContext.getRemoteHeight()) / ((float) this.mCommonContext.getLocalHeight());
        x *= remoteWidth;
        y *= remoteHeight;
        String str = TAG;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("adjustCoordinates: event: ");
        stringBuilder.append(motionEvent.toString());
        stringBuilder.append("\n");
        stringBuilder.append("scaleX: ");
        stringBuilder.append(remoteWidth);
        stringBuilder.append(", scaleY: ");
        stringBuilder.append(remoteHeight);
        Log.p(str, stringBuilder.toString());
        motionEvent.setLocation(x, y);
        str = TAG;
        StringBuilder stringBuilder2 = new StringBuilder();
        stringBuilder2.append("after adjustCoordinates : ");
        stringBuilder2.append(motionEvent.toString());
        Log.p(str, stringBuilder2.toString());
        return motionEvent;
    }

    protected boolean handleGestureOnDown(MotionEvent motionEvent) {
        return this.mListener.onDown(motionEvent);
    }

    protected boolean handleGestureOnLongPress(MotionEvent motionEvent) {
        return this.mListener.onLongPress(motionEvent);
    }

    protected boolean handleGestureOnSingleTapUp(MotionEvent motionEvent) {
        return this.mListener.onSingleTapUp(motionEvent);
    }

    protected boolean handleGestureOnDoubleTap(MotionEvent motionEvent) {
        return this.mListener.onDoubleTap(motionEvent);
    }

    protected boolean handleGestureOnScroll(MotionEvent motionEvent, MotionEvent motionEvent2, float f, float f2) {
        return this.mListener.onScroll(motionEvent, motionEvent2, f, f2);
    }

    protected boolean handleGestureOnFling(MotionEvent motionEvent, MotionEvent motionEvent2, float f, float f2) {
        return this.mListener.onFling(motionEvent, motionEvent2, f, f2);
    }

    protected boolean handleScaleGestureOnScale(ScaleGestureDetector scaleGestureDetector) {
        return this.mListener.onScale(scaleGestureDetector);
    }

    protected boolean handleScaleGestureOnScaleBegin(ScaleGestureDetector scaleGestureDetector) {
        return this.mListener.onScaleBegin(scaleGestureDetector);
    }

    protected boolean handleOnScaleEnd(ScaleGestureDetector scaleGestureDetector) {
        return this.mListener.onScaleEnd(scaleGestureDetector);
    }
}
