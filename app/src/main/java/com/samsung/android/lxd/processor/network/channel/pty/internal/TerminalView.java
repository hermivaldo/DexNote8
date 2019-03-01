package com.samsung.android.lxd.processor.network.channel.pty.internal;

import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.view.ActionMode.Callback2;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputConnection;
import com.samsung.android.lxd.processor.ICommonContext;
import com.samsung.android.lxd.processor.network.channel.pty.internal.TerminalSession.OnStateListener;
import com.samsung.android.lxd.processor.network.display.INetworkDisplay;
import com.samsung.android.lxd.processor.network.display.NetworkDisplayContext;
import com.samsung.android.lxd.processor.utils.Utils;
import com.samsung.android.lxd.processor.utils.log.Log;
import java.util.HashMap;

public class TerminalView extends TerminalViewBase implements ITerminalViewHelper {
    private static final String TAG = "TerminalView";
    private Paint mBackgroundPaint;
    private final BlinkCursorRunnable mBlinkCursor;
    private int mCharacterHeight;
    private float mCharacterWidth;
    private final ICommonContext mCommonContext;
    private final NetworkDisplayContext mDisplayContext;
    private TerminalEmulator mEmulator;
    private final FlingRunnable mFlingRunner;
    private Paint mForegroundPaint;
    private String mImeBuffer;
    private TerminalKeyListener mKeyListener;
    private MotionEvent mLastMotionEvent;
    private int mLeftColumn;
    private boolean mMouseTracking;
    private final FlingRunnable mMouseTrackingFlingRunner;
    private float mNewFontSize;
    private float mOldFontSize;
    private OnUpdateListener mOnUpdateListener;
    private int mScreenColumns;
    private int mScreenRows;
    private float mScrollRemainder;
    private TerminalSession mSession;
    private final TerminalSetting mSetting;
    private State mState;
    private TextRenderer mTextRenderer;
    private final ITextSelector mTextSelector = new TextSelector(this);
    private int mTextSize;
    private int mTopOfScreenMargin;
    private int mTopRow;
    private final HashMap<Integer, ITouchHandler> mTouchHandler;
    private final UrlMatcher mUrlMatcher;
    private int mVisibleHeight;
    private int mVisibleWidth;

    private interface ITouchHandler {
        boolean onDown(MotionEvent motionEvent);

        boolean onFling(MotionEvent motionEvent, MotionEvent motionEvent2, float f, float f2);

        void onLongPress(MotionEvent motionEvent);

        void onScale(float f);

        void onScaleBegin();

        void onScaleEnd();

        boolean onScroll(MotionEvent motionEvent, MotionEvent motionEvent2, float f, float f2);

        void onShowPress(MotionEvent motionEvent);

        boolean onSingleTapUp(MotionEvent motionEvent);

        boolean onTouchEvent(MotionEvent motionEvent);
    }

    public interface OnUpdateListener {
        void onCommit(String str);

        void onContext(Callback2 callback2);

        void onSelect(boolean z);

        void onUpdate(boolean z, boolean z2);
    }

    private enum State {
        INVALID,
        INIT_DELAYED,
        INITIATED
    }

    private class DefaultTouchHandler implements ITouchHandler {
        public void onShowPress(MotionEvent motionEvent) {
        }

        private DefaultTouchHandler() {
        }

        /* synthetic */ DefaultTouchHandler(TerminalView terminalView, AnonymousClass1 anonymousClass1) {
            this();
        }

        public boolean onDown(MotionEvent motionEvent) {
            TerminalView.this.mScrollRemainder = 0.0f;
            return TerminalView.this.mTextSelector.onDown(motionEvent);
        }

        public boolean onFling(MotionEvent motionEvent, MotionEvent motionEvent2, float f, float f2) {
            if (TerminalView.this.mTextSelector.isDraggingHolder() || (!TerminalView.this.isMouseTrackingActive() && !TerminalView.this.mEmulator.getScreen().isMainScreen())) {
                return true;
            }
            TerminalView.this.mScrollRemainder = 0.0f;
            getFlingRunnable().fling(motionEvent, f, f2, new OnUpdateListener() {
                public void onFinish() {
                    TerminalView.this.mTextSelector.onFlingEnd();
                }
            });
            TerminalView.this.mTextSelector.onFling();
            return true;
        }

        public boolean onSingleTapUp(MotionEvent motionEvent) {
            if (TerminalView.this.mTextSelector.onSingleTapUp(motionEvent)) {
                TerminalView.this.mOnUpdateListener.onSelect(false);
            }
            if (TerminalView.this.isMouseTrackingActive()) {
                TerminalView.this.sendMouseEventCode(motionEvent, 0);
                TerminalView.this.sendMouseEventCode(motionEvent, 3);
            }
            return true;
        }

        public void onLongPress(MotionEvent motionEvent) {
            if (TerminalView.this.mTextSelector.onLongPress(motionEvent)) {
                TerminalView.this.mOnUpdateListener.onSelect(true);
            }
        }

        public void onScale(float f) {
            if (!TerminalView.this.isSelectingText()) {
                TerminalView.this.updateFontSize(f);
            }
        }

        public void onScaleBegin() {
            if (!TerminalView.this.isSelectingText()) {
                TerminalView.this.mOldFontSize = TerminalView.this.mSetting.getFontSize();
            }
        }

        public void onScaleEnd() {
            if (!TerminalView.this.isSelectingText()) {
                TerminalView.this.mSetting.setFontSize(TerminalView.this.mNewFontSize);
            }
        }

        public boolean onScroll(MotionEvent motionEvent, MotionEvent motionEvent2, float f, float f2) {
            return (TerminalView.this.mTextSelector.isDraggingHolder() || !(TerminalView.this.isMouseTrackingActive() || TerminalView.this.mEmulator.getScreen().isMainScreen())) ? true : TerminalView.this.scroll(motionEvent, motionEvent2, f, f2);
        }

        public boolean onTouchEvent(MotionEvent motionEvent) {
            switch (motionEvent.getAction()) {
                case 1:
                    if (TerminalView.this.mTextSelector.onUp(motionEvent)) {
                        return true;
                    }
                    break;
                case 2:
                    if (TerminalView.this.mTextSelector.onMove(motionEvent)) {
                        return true;
                    }
                    break;
            }
            return false;
        }

        private FlingRunnable getFlingRunnable() {
            return TerminalView.this.isMouseTrackingActive() ? TerminalView.this.mMouseTrackingFlingRunner : TerminalView.this.mFlingRunner;
        }
    }

    private class MouseTouchHandler extends DefaultTouchHandler {
        public boolean onFling(MotionEvent motionEvent, MotionEvent motionEvent2, float f, float f2) {
            return false;
        }

        public void onScale(float f) {
        }

        public void onScaleBegin() {
        }

        public void onScaleEnd() {
        }

        public boolean onScroll(MotionEvent motionEvent, MotionEvent motionEvent2, float f, float f2) {
            return false;
        }

        private MouseTouchHandler() {
            super(TerminalView.this, null);
        }

        /* synthetic */ MouseTouchHandler(TerminalView terminalView, AnonymousClass1 anonymousClass1) {
            this();
        }

        public void onLongPress(MotionEvent motionEvent) {
            if (!Utils.isDexMode(TerminalView.this.getContext()) && TerminalView.this.mTextSelector.isOnCursor((int) motionEvent.getX(), (int) motionEvent.getY())) {
                super.onLongPress(motionEvent);
            }
        }

        public boolean onSingleTapUp(MotionEvent motionEvent) {
            if (Utils.isDexMode(TerminalView.this.getContext())) {
                return false;
            }
            return super.onSingleTapUp(motionEvent);
        }

        public boolean onTouchEvent(MotionEvent motionEvent) {
            boolean z = (motionEvent.getToolType(0) == 1 && motionEvent.getButtonState() != 2) || (motionEvent.getToolType(0) == 3 && motionEvent.getButtonState() == 1);
            boolean z2 = (motionEvent.getToolType(0) == 1 && motionEvent.getButtonState() == 2) || (motionEvent.getToolType(0) == 3 && motionEvent.getButtonState() == 2);
            int action = motionEvent.getAction();
            if (action != 8) {
                switch (action) {
                    case 0:
                        if (Utils.isDexMode(TerminalView.this.getContext())) {
                            if (!z) {
                                if (z2) {
                                    TerminalView.this.mTextSelector.toggleActionMode((int) motionEvent.getX(), (int) motionEvent.getY());
                                    break;
                                }
                            }
                            super.onSingleTapUp(motionEvent);
                            break;
                        }
                        break;
                    case 1:
                        if ((!Utils.isDexMode(TerminalView.this.getContext()) || TerminalView.this.mTextSelector.isSelecting()) && TerminalView.this.mTextSelector.onUp(motionEvent)) {
                            return true;
                        }
                        break;
                    case 2:
                        if (z) {
                            if (!(TerminalView.this.mTextSelector.isSelecting() || TerminalView.this.mTextSelector.isOnCursor((int) motionEvent.getX(), (int) motionEvent.getY()))) {
                                super.onLongPress(motionEvent);
                            }
                            if (TerminalView.this.mTextSelector.onMove(motionEvent)) {
                                return true;
                            }
                        }
                        break;
                }
                return false;
            }
            int i = motionEvent.getAxisValue(9) > 0.0f ? -1 : 1;
            action = TerminalView.this.getCharacterHeight() * 3;
            String access$1800 = TerminalView.TAG;
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("Wheel + ");
            stringBuilder.append(i == -1 ? "Up" : "Dn");
            Log.d(access$1800, stringBuilder.toString());
            if (TerminalView.this.isControlPressed()) {
                super.onScaleBegin();
                super.onScale(i == -1 ? 1.05f : 0.95f);
                super.onScaleEnd();
            } else {
                super.onScroll(motionEvent, motionEvent, 0.0f, (float) (i * action));
            }
            return true;
        }
    }

    private class StylusTouchHandler extends DefaultTouchHandler {
        private StylusTouchHandler() {
            super(TerminalView.this, null);
        }

        /* synthetic */ StylusTouchHandler(TerminalView terminalView, AnonymousClass1 anonymousClass1) {
            this();
        }

        public boolean onTouchEvent(MotionEvent motionEvent) {
            switch (motionEvent.getAction()) {
                case 211:
                    onLongPress(motionEvent);
                    break;
                case 212:
                    if (TerminalView.this.mTextSelector.onUp(motionEvent)) {
                        return true;
                    }
                    break;
                case 213:
                    if (TerminalView.this.mTextSelector.onMove(motionEvent)) {
                        return true;
                    }
                    break;
                default:
                    super.onTouchEvent(motionEvent);
                    break;
            }
            return false;
        }
    }

    public boolean onCheckIsTextEditor() {
        return true;
    }

    public TerminalView(ICommonContext iCommonContext, TerminalSession terminalSession, TerminalSetting terminalSetting) {
        super(iCommonContext);
        this.mCommonContext = iCommonContext;
        this.mSetting = terminalSetting;
        this.mDisplayContext = new NetworkDisplayContext();
        this.mBlinkCursor = new BlinkCursorRunnable(this);
        this.mFlingRunner = new FlingRunnable(this);
        this.mMouseTrackingFlingRunner = new MouseTrackingFlingRunnable(this);
        this.mUrlMatcher = new UrlMatcher(this);
        this.mTouchHandler = new HashMap();
        this.mTouchHandler.put(Integer.valueOf(4098), new DefaultTouchHandler(this, null));
        this.mTouchHandler.put(Integer.valueOf(8194), new MouseTouchHandler(this, null));
        this.mTouchHandler.put(Integer.valueOf(16386), new StylusTouchHandler(this, null));
        this.mLastMotionEvent = null;
        this.mState = State.INVALID;
        this.mImeBuffer = "";
        this.mOnUpdateListener = null;
        this.mOldFontSize = 0.0f;
        this.mNewFontSize = 0.0f;
        this.mTextSize = 10;
        attachSession(terminalSession);
    }

    private void attachSession(TerminalSession terminalSession) {
        this.mTextRenderer = null;
        this.mForegroundPaint = new Paint();
        this.mBackgroundPaint = new Paint();
        this.mTopRow = 0;
        this.mLeftColumn = 0;
        this.mSession = terminalSession;
        this.mKeyListener = new TerminalKeyListener(terminalSession);
        terminalSession.setKeyListener(this.mKeyListener);
        updateSetting();
        if (this.mState == State.INIT_DELAYED) {
            this.mState = State.INITIATED;
            initialize();
        }
    }

    public TerminalKeyListener getKeyListener() {
        return this.mKeyListener;
    }

    public boolean getKeypadApplicationMode() {
        return this.mEmulator.getKeypadApplicationMode();
    }

    public INetworkDisplay getLocalDisplay() {
        return this.mCommonContext.getLocalDisplay();
    }

    protected int computeVerticalScrollRange() {
        return (this.mEmulator == null || this.mEmulator.getScreen() == null) ? 0 : this.mEmulator.getScreen().getActiveRows();
    }

    protected int computeVerticalScrollExtent() {
        return (this.mEmulator == null || this.mEmulator.getScreen() == null) ? 0 : this.mScreenRows;
    }

    protected int computeVerticalScrollOffset() {
        return (this.mEmulator == null || this.mEmulator.getScreen() == null) ? 0 : (this.mEmulator.getScreen().getActiveRows() + this.mTopRow) - this.mScreenRows;
    }

    private void initialize() {
        Log.d(TAG, "initialize");
        updateText();
        this.mEmulator = this.mSession.getEmulator();
        this.mSession.addOnStateListener(new OnStateListener() {
            public void onStart() {
                TerminalView.this.updateSize(false);
                if (TerminalView.this.mBlinkCursor.isEnabled()) {
                    TerminalView.this.mBlinkCursor.resume();
                }
                if (TerminalView.this.mKeyListener != null) {
                    TerminalView.this.mKeyListener.onResume();
                }
            }

            public void onUpdate() {
                TerminalView.this.mTextSelector.updateSelection(0, TerminalView.this.mEmulator.getScrollCounter());
                TerminalView.this.mEmulator.clearScrollCounter();
                TerminalView.this.ensureCursorVisible();
                TerminalView.this.invalidate();
            }

            public void onFinish(Throwable th) {
                if (TerminalView.this.mBlinkCursor.isEnabled()) {
                    TerminalView.this.mBlinkCursor.pause();
                }
                if (TerminalView.this.mKeyListener != null) {
                    TerminalView.this.mKeyListener.onPause();
                }
            }
        });
    }

    private void page(int i) {
        this.mTopRow = Math.min(0, Math.max(-this.mEmulator.getScreen().getActiveTranscriptRows(), this.mTopRow + (this.mScreenRows * i)));
        invalidate();
    }

    private void setTextSize(float f) {
        this.mTextSize = Math.round(f * Utils.getScreenDensity(getContext()));
        updateText();
    }

    public boolean isMouseTrackingActive() {
        return this.mEmulator.getMouseTrackingMode() != 0 && this.mMouseTracking;
    }

    public boolean isOnMainScreen() {
        return this.mEmulator.getScreen().isMainScreen();
    }

    public boolean isUsingMouse() {
        return this.mLastMotionEvent != null && this.mLastMotionEvent.isFromSource(8194);
    }

    public boolean isUsingPen() {
        return isUsingStylus() && isPenEvent(this.mLastMotionEvent);
    }

    public boolean isUsingStylus() {
        return this.mLastMotionEvent != null && this.mLastMotionEvent.isFromSource(16386);
    }

    private boolean isPenEvent(MotionEvent motionEvent) {
        int action = motionEvent.getAction();
        return action == 211 || action == 213 || action == 212;
    }

    public void showActionMode() {
        Log.d(TAG, "showActionMode");
        this.mOnUpdateListener.onContext(this.mTextSelector.getActionModeCallback(new OnActionModeListener() {
            public void onItemClicked() {
                TerminalView.this.mOnUpdateListener.onSelect(false);
            }
        }));
    }

    public void sendMouseEventCode(MotionEvent motionEvent, int i) {
        int x = ((int) (motionEvent.getX() / this.mCharacterWidth)) + 1;
        int y = ((int) ((motionEvent.getY() - ((float) this.mTopOfScreenMargin)) / ((float) this.mCharacterHeight))) + 1;
        int i2 = (x < 1 || y < 1 || x > this.mScreenColumns || y > this.mScreenRows || x > 223 || y > 223) ? 1 : 0;
        if (i < 0 || i > 223) {
            String str = TAG;
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("mouse button_code out of range: ");
            stringBuilder.append(i);
            Log.e(str, stringBuilder.toString());
            return;
        }
        if (i2 == 0) {
            byte[] bArr = new byte[]{(byte) 27, (byte) 91, (byte) 77, (byte) (i + 32), (byte) (x + 32), (byte) (y + 32)};
            this.mSession.write(bArr, 0, bArr.length);
        }
    }

    public boolean scroll(float f, float f2) {
        return scroll(null, null, f, f2);
    }

    public boolean scroll(MotionEvent motionEvent, MotionEvent motionEvent2, float f, float f2) {
        f2 += this.mScrollRemainder;
        int i = (int) (f2 / ((float) this.mCharacterHeight));
        this.mScrollRemainder = f2 - ((float) (this.mCharacterHeight * i));
        if (!isMouseTrackingActive()) {
            this.mTopRow = Math.min(0, Math.max(this.mEmulator.getScreen().getActiveTranscriptRows() * -1, this.mTopRow + i));
            awakenScrollBars();
        } else if (!(motionEvent == null || motionEvent2 == null)) {
            while (i > 0) {
                sendMouseEventCode(motionEvent, 65);
                i--;
            }
            while (i < 0) {
                sendMouseEventCode(motionEvent, 64);
                i++;
            }
        }
        return true;
    }

    public boolean onSingleTapUp(MotionEvent motionEvent) {
        return getTouchHandler(motionEvent.getSource()).onSingleTapUp(motionEvent);
    }

    public void onLongPress(MotionEvent motionEvent) {
        getTouchHandler(motionEvent.getSource()).onLongPress(motionEvent);
    }

    public boolean onScroll(MotionEvent motionEvent, MotionEvent motionEvent2, float f, float f2) {
        return getTouchHandler(motionEvent2.getSource()).onScroll(motionEvent, motionEvent2, f, f2);
    }

    private boolean awakenScrollBars() {
        if (!isHorizontalScrollBarEnabled() && !isVerticalScrollBarEnabled()) {
            return false;
        }
        if (this.mOnUpdateListener != null) {
            this.mOnUpdateListener.onUpdate(isHorizontalScrollBarEnabled(), isVerticalScrollBarEnabled());
        }
        return true;
    }

    public boolean onFling(MotionEvent motionEvent, MotionEvent motionEvent2, float f, float f2) {
        return getTouchHandler(motionEvent2.getSource()).onFling(motionEvent, motionEvent2, f, f2);
    }

    public void onShowPress(MotionEvent motionEvent) {
        getTouchHandler(motionEvent.getSource()).onShowPress(motionEvent);
    }

    public boolean onDown(MotionEvent motionEvent) {
        return getTouchHandler(motionEvent.getSource()).onDown(motionEvent);
    }

    public void onScale(float f) {
        getTouchHandler().onScale(f);
    }

    public void onScaleBegin() {
        getTouchHandler().onScaleBegin();
    }

    public void onScaleEnd() {
        getTouchHandler().onScaleEnd();
    }

    private ITouchHandler getTouchHandler() {
        return getTouchHandler(4098);
    }

    private ITouchHandler getTouchHandler(int i) {
        if ((i & 8194) == 8194) {
            return (ITouchHandler) this.mTouchHandler.get(Integer.valueOf(8194));
        }
        if ((i & 16386) == 16386) {
            return (ITouchHandler) this.mTouchHandler.get(Integer.valueOf(16386));
        }
        return (ITouchHandler) this.mTouchHandler.get(Integer.valueOf(4098));
    }

    public boolean onTouchEvent(MotionEvent motionEvent) {
        this.mLastMotionEvent = motionEvent;
        return getTouchHandler(motionEvent.getSource()).onTouchEvent(motionEvent) || super.onTouchEvent(motionEvent);
    }

    private boolean isControlPressed() {
        return this.mKeyListener.isControlPressed();
    }

    public boolean onKeyDown(int i, KeyEvent keyEvent) {
        int characterHeight;
        String str = TAG;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("onKeyDown ");
        stringBuilder.append(i);
        Log.p(str, stringBuilder.toString());
        if (keyEvent.isShiftPressed()) {
            characterHeight = getCharacterHeight() * 3;
            switch (i) {
                case 19:
                    Log.d(TAG, "Shift + Up");
                    scroll(0.0f, (float) (characterHeight * -1));
                    return true;
                case 20:
                    Log.d(TAG, "Shift + Dn");
                    scroll(0.0f, (float) (characterHeight * 1));
                    return true;
                case 92:
                    Log.d(TAG, "Shift + PageUp");
                    page(-1);
                    return true;
                case 93:
                    Log.d(TAG, "Shift + PageDn");
                    page(1);
                    return true;
            }
        }
        if (handleControlKey(i, true) || handleFnKey(i, true)) {
            return true;
        }
        if (isSystemKey(i, keyEvent)) {
            return super.onKeyDown(i, keyEvent);
        }
        characterHeight = this.mKeyListener.getCombiningAccent();
        this.mKeyListener.keyDown(i, keyEvent, getKeypadApplicationMode());
        if (this.mKeyListener.getCombiningAccent() != characterHeight) {
            invalidate();
        }
        return true;
    }

    public boolean onKeyUp(int i, KeyEvent keyEvent) {
        String str = TAG;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("onKeyUp ");
        stringBuilder.append(i);
        Log.p(str, stringBuilder.toString());
        if (keyEvent.getUnicodeChar() != 0) {
            this.mOnUpdateListener.onCommit(String.valueOf((char) keyEvent.getUnicodeChar()));
        }
        if (handleControlKey(i, false) || handleFnKey(i, false)) {
            return true;
        }
        if (isSystemKey(i, keyEvent)) {
            return super.onKeyUp(i, keyEvent);
        }
        this.mKeyListener.keyUp(i, keyEvent);
        return true;
    }

    private boolean handleControlKey(int i, boolean z) {
        if (i != 113 && i != 114) {
            return false;
        }
        String str = TAG;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("handleVirtualControlKey ");
        stringBuilder.append(i);
        Log.p(str, stringBuilder.toString());
        this.mKeyListener.handleVirtualControlKey(z);
        invalidate();
        return true;
    }

    private boolean handleFnKey(int i, boolean z) {
        if (i != 119) {
            return false;
        }
        String str = TAG;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("handleVirtualFunctionKey ");
        stringBuilder.append(i);
        Log.p(str, stringBuilder.toString());
        this.mKeyListener.handleVirtualFunctionKey(z);
        invalidate();
        return true;
    }

    private boolean isSystemKey(int i, KeyEvent keyEvent) {
        return keyEvent.isSystem();
    }

    private void updateText() {
        if (this.mTextSize > 0) {
            this.mTextRenderer = new PaintRenderer(getTypeface(), this.mTextSize, this.mSetting.getColorScheme());
        }
        this.mForegroundPaint.setColor(this.mSetting.getColorScheme().getForeColor());
        this.mBackgroundPaint.setColor(this.mSetting.getColorScheme().getBackColor());
        this.mCharacterWidth = this.mTextRenderer.getCharacterWidth();
        this.mCharacterHeight = this.mTextRenderer.getCharacterHeight();
        updateSize(true);
    }

    protected void onSizeChanged(int i, int i2, int i3, int i4) {
        if (this.mSession == null) {
            this.mState = State.INIT_DELAYED;
            return;
        }
        if (this.mState != State.INITIATED) {
            this.mState = State.INITIATED;
            initialize();
        } else {
            updateSize(false);
        }
    }

    private void updateSize(boolean z) {
        this.mUrlMatcher.clear();
        if (this.mState == State.INITIATED) {
            int width = getWidth();
            int height = getHeight();
            if (z || width != this.mVisibleWidth || height != this.mVisibleHeight) {
                this.mVisibleWidth = width;
                this.mVisibleHeight = height;
                this.mScreenColumns = Math.max(1, (int) (((float) this.mVisibleWidth) / this.mCharacterWidth));
                this.mTopOfScreenMargin = this.mTextRenderer.getTopMargin();
                this.mScreenRows = Math.max(1, (this.mVisibleHeight - this.mTopOfScreenMargin) / this.mCharacterHeight);
                this.mSession.updateSize(this.mScreenColumns, this.mScreenRows);
                this.mTopRow = 0;
                this.mLeftColumn = 0;
                invalidate();
            }
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:43:0x0100  */
    /* JADX WARNING: Removed duplicated region for block: B:47:0x0116  */
    /* JADX WARNING: Removed duplicated region for block: B:46:0x010e  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    protected void onDraw(Canvas canvas) {
        updateSize(false);
        if (this.mEmulator != null) {
            boolean reverseVideo = this.mEmulator.getReverseVideo();
            this.mTextRenderer.setReverseVideo(reverseVideo);
            canvas.drawRect((float) getLeft(), (float) getTop(), (float) getWidth(), (float) getHeight(), reverseVideo ? this.mForegroundPaint : this.mBackgroundPaint);
            float left = ((float) getLeft()) + (((float) (-this.mLeftColumn)) * this.mCharacterWidth);
            float top = (float) ((getTop() + this.mCharacterHeight) + this.mTopOfScreenMargin);
            int cursorCol = this.mEmulator.getCursorCol();
            int cursorRow = this.mEmulator.getCursorRow();
            boolean z = this.mBlinkCursor.isVisible() && this.mEmulator.getShowCursor();
            int combiningAccent = this.mKeyListener.getCombiningAccent();
            if (combiningAccent != 0) {
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append(this.mImeBuffer);
                stringBuilder.append(String.valueOf((char) combiningAccent));
                this.mImeBuffer = stringBuilder.toString();
            }
            int i = 0;
            int i2 = this.mTopRow;
            while (i2 < this.mTopRow + this.mScreenRows) {
                int i3;
                int i4;
                int i5 = (i2 == cursorRow && z) ? cursorCol : -1;
                if (!this.mTextSelector.isOnCursor()) {
                    Point openingPoint = this.mTextSelector.getOpeningPoint();
                    Point closingPoint = this.mTextSelector.getClosingPoint();
                    if (openingPoint.y + this.mTopRow <= i2 && i2 <= closingPoint.y + this.mTopRow) {
                        int i6;
                        combiningAccent = i2 == openingPoint.y + this.mTopRow ? openingPoint.x : -1;
                        if (i2 == closingPoint.y + this.mTopRow) {
                            i6 = closingPoint.x;
                        } else {
                            i6 = this.mScreenColumns;
                        }
                        i3 = combiningAccent;
                        i4 = i6;
                        this.mEmulator.getScreen().drawText(i2, canvas, left, top, this.mTextRenderer, i5, i3, i4, this.mImeBuffer);
                        top += (float) this.mCharacterHeight;
                        if (i == 0) {
                            i = this.mUrlMatcher.createLinks(i2);
                        }
                        i--;
                        if (isSelectingText()) {
                            Canvas canvas2 = canvas;
                        } else {
                            this.mTextSelector.onDraw(canvas);
                        }
                        i2++;
                    }
                }
                i3 = -1;
                i4 = i3;
                this.mEmulator.getScreen().drawText(i2, canvas, left, top, this.mTextRenderer, i5, i3, i4, this.mImeBuffer);
                top += (float) this.mCharacterHeight;
                if (i == 0) {
                }
                i--;
                if (isSelectingText()) {
                }
                i2++;
            }
        }
    }

    public String getImeBuffer() {
        return this.mImeBuffer;
    }

    public void setImeBuffer(String str) {
        this.mImeBuffer = str;
    }

    private void ensureCursorVisible() {
        this.mTopRow = 0;
        if (this.mScreenColumns > 0) {
            int cursorCol = this.mEmulator.getCursorCol();
            int cursorCol2 = this.mEmulator.getCursorCol() - this.mLeftColumn;
            if (cursorCol2 < 0) {
                this.mLeftColumn = cursorCol;
            } else if (cursorCol2 >= this.mScreenColumns) {
                this.mLeftColumn = (cursorCol - this.mScreenColumns) + 1;
            }
        }
    }

    private boolean isSelectingText() {
        return this.mTextSelector.isSelecting();
    }

    private void setAltSendsEsc(boolean z) {
        this.mKeyListener.setAltSendsEsc(z);
    }

    private void setTerminalType(String str) {
        this.mKeyListener.setTerminalType(str);
    }

    private void setMouseTracking(boolean z) {
        this.mMouseTracking = z;
    }

    private void updateSetting() {
        setTextSize(this.mSetting.getFontSize());
        setAltSendsEsc(this.mSetting.getAltSendsEscFlag());
        setCursorBlink(this.mSetting.getCursorBlink());
        setTerminalType(this.mSetting.getTermType());
        setMouseTracking(this.mSetting.getMouseTrackingFlag());
    }

    public void write(String str) {
        this.mSession.write(str);
    }

    public int getActiveTranscriptRows() {
        return this.mEmulator.getScreen().getActiveTranscriptRows();
    }

    public float getCharacterWidth() {
        return this.mCharacterWidth;
    }

    public int getCharacterHeight() {
        return this.mCharacterHeight;
    }

    public float getMinFontSize() {
        return this.mSetting.getMinFontSize();
    }

    public TranscriptScreen getScreen() {
        return this.mEmulator.getScreen();
    }

    public int getTopRow() {
        return this.mTopRow;
    }

    public int getTopOfScreenMargin() {
        return this.mTopOfScreenMargin;
    }

    public int getScreenCols() {
        return this.mScreenColumns;
    }

    public int getScreenRows() {
        return this.mScreenRows;
    }

    public int getTotalCols() {
        return this.mScreenColumns;
    }

    public int getTotalRows() {
        return this.mEmulator.getScreen().getActiveRows();
    }

    public int getCursorCol() {
        return this.mEmulator.getCursorCol();
    }

    public int getCursorRow() {
        return this.mEmulator.getCursorRow();
    }

    public String getSelectedText(int i, int i2, int i3, int i4) {
        return this.mEmulator.getSelectedText(i, i2 + this.mTopRow, i3, i4 + this.mTopRow);
    }

    public TerminalSession getSession() {
        return this.mSession;
    }

    protected Typeface getTypeface() {
        return Typeface.createFromAsset(getContext().getAssets(), "font/UbuntuMono-Regular.ttf");
    }

    private void setCursorBlink(boolean z) {
        this.mBlinkCursor.enable(z);
    }

    public TerminalView setOnUpdateListener(OnUpdateListener onUpdateListener) {
        this.mOnUpdateListener = onUpdateListener;
        return this;
    }

    public void invalidate() {
        if (this.mSession.isStarted() && this.mOnUpdateListener != null) {
            this.mOnUpdateListener.onUpdate(false, false);
        }
    }

    public void setTopRow(int i) {
        this.mTopRow = i;
    }

    public void updateFontSize() {
        this.mOldFontSize = this.mSetting.getFontSize();
        updateFontSize(1.0f);
    }

    private void updateFontSize(float f) {
        float round = (float) Math.round(Math.max(this.mSetting.getMinFontSize(), Math.min(this.mSetting.getMaxFontSize(), this.mOldFontSize * f)));
        if (this.mNewFontSize != round) {
            this.mNewFontSize = round;
            String str = TAG;
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("updateFontSize : scaleFactor : ");
            stringBuilder.append(f);
            stringBuilder.append(" ");
            stringBuilder.append("newFontSize : ");
            stringBuilder.append(this.mNewFontSize);
            Log.d(str, stringBuilder.toString());
            setTextSize(this.mNewFontSize);
        }
    }

    public InputConnection getInputConnection(View view, EditorInfo editorInfo) {
        editorInfo.inputType = 524433;
        editorInfo.imeOptions = 1107296256;
        editorInfo.privateImeOptions = "disablePrediction=true;disableEmoticonInput=true;disableSticker=true;disableGifKeyboard=true;disableVoiceInput=true;disableLiveMessage=true;disableHWRInput=true;disableClipboard=true;";
        return new TerminalInputConnection(this, view, true) {
            public boolean commitText(CharSequence charSequence, int i) {
                if (TerminalView.this.mOnUpdateListener != null) {
                    TerminalView.this.mOnUpdateListener.onCommit(String.valueOf(charSequence));
                }
                return super.commitText(charSequence, i);
            }
        };
    }

    public void notifyDisplaySizeChange(int i, int i2) {
        if (i <= 0 || i2 <= 0) {
            String str = TAG;
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("notifyDisplaySizeChange : Invalid size (");
            stringBuilder.append(i);
            stringBuilder.append(", ");
            stringBuilder.append(i2);
            stringBuilder.append(")");
            Log.d(str, stringBuilder.toString());
            return;
        }
        int maxHolderWidth = this.mTextSelector.getMaxHolderWidth();
        setTop(maxHolderWidth);
        setLeft(maxHolderWidth);
        setBottom(i2 - maxHolderWidth);
        setRight(i - maxHolderWidth);
        onSizeChanged(getWidth(), getHeight(), getWidth(), getHeight());
    }

    public NetworkDisplayContext getDisplayContext(boolean z, boolean z2) {
        return this.mDisplayContext.setDrawable(new Drawable() {
            public int getOpacity() {
                return -1;
            }

            public void setAlpha(int i) {
            }

            public void setColorFilter(ColorFilter colorFilter) {
            }

            public void draw(Canvas canvas) {
                TerminalView.this.draw(canvas);
            }
        }).setShowHorizontalScroll(z).setHorizontalScrollRange(computeHorizontalScrollRange()).setHorizontalScrollOffset(computeHorizontalScrollOffset()).setHorizontalScrollExtent(computeHorizontalScrollExtent()).setShowVerticalScroll(z2).setVerticalScrollRange(computeVerticalScrollRange()).setVerticalScrollOffset(computeVerticalScrollOffset()).setVerticalScrollExtent(computeVerticalScrollExtent());
    }
}
