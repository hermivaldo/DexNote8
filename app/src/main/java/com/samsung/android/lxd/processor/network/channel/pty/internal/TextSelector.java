package com.samsung.android.lxd.processor.network.channel.pty.internal;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Point;
import android.graphics.Rect;
import android.text.TextUtils;
import android.view.ActionMode;
import android.view.ActionMode.Callback2;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import com.samsung.android.lxd.R;
import com.samsung.android.lxd.a.a;
import com.samsung.android.lxd.a.o;
import com.samsung.android.lxd.processor.utils.log.Log;

public class TextSelector implements ITextSelector {
    private static final int DELAY_ACTION_MODE = 150;
    private static final int HOLDER_HIT_RANGE = 3;
    private static final int MENU_INDEX_COPY = 0;
    private static final int MENU_INDEX_PASTE = 1;
    private static final String TAG = "TextSelector";
    private static final int TEXT_REVERSE_THRESHOLD = 2;
    private ActionMode mActionMode = null;
    private Runnable mActionModeRunnable = null;
    private int mActiveTranscriptRows = 0;
    private final Context mContext;
    private Point mContextPoint = null;
    private SelectionHolder mEnterHolder = new EnterHolder(this, null);
    private Point mEnterPoint = new Point(-1, -1);
    private boolean mFlinging = false;
    private final ITerminalViewHelper mHelper;
    private SelectionHolder mLeaveHolder = new LeaveHolder(this, null);
    private Point mLeavePoint = new Point(-1, -1);
    private boolean mReversed = false;
    private boolean mSelecting = false;
    private int mTopRow = 0;
    private boolean mTouchDown = false;
    private boolean mWordWrap = false;

    interface OnActionModeListener {
        void onItemClicked();
    }

    private abstract class SelectionHolder {
        protected static final float HOLDER_SCALE_FACTOR_LARGE = 1.5f;
        protected static final float HOLDER_SCALE_FACTOR_NORMAL = 1.0f;
        protected static final float HOLDER_SIZE_RATIO_NON_PHONE = 2.5f;
        protected static final float HOLDER_SIZE_RATIO_PHONE = 3.5f;
        protected static final float MAX_DISTANCE = 1000000.0f;
        protected ImageView mChildDisplay;
        protected float mDragX;
        protected float mDragY;
        protected boolean mDragging;
        protected boolean mEnabled;
        protected final Rect mLocation;
        protected ImageView mPointDisplay;
        protected boolean mShowing;

        protected abstract void draw(Canvas canvas);

        protected abstract int getDisplayId();

        protected abstract int getSelectedX(MotionEvent motionEvent);

        protected abstract int getSelectedY(MotionEvent motionEvent);

        protected abstract void setLocation(int i, int i2, float f);

        /* synthetic */ SelectionHolder(TextSelector textSelector, AnonymousClass1 anonymousClass1) {
            this();
        }

        private SelectionHolder() {
            this.mLocation = new Rect(-1, -1, -1, -1);
            updateDisplay();
            reset();
        }

        protected void draw(Canvas canvas, int i, int i2) {
            if (isEnabled()) {
                if (isDragging()) {
                    i = TextSelector.this.getRawColIndex(this.mDragX);
                }
                if (isDragging()) {
                    i2 = TextSelector.this.getRawRowIndex(this.mDragY);
                }
                setLocation(TextSelector.this.getXPoint(i), TextSelector.this.getYPoint(i2), this.mDragging ? HOLDER_SCALE_FACTOR_LARGE : HOLDER_SCALE_FACTOR_NORMAL);
                ImageView selectDisplay = selectDisplay();
                if (selectDisplay != null) {
                    LayoutParams layoutParams = new RelativeLayout.LayoutParams(selectDisplay.getLayoutParams());
                    layoutParams.width = this.mLocation.width();
                    layoutParams.height = this.mLocation.height();
                    selectDisplay.setLayoutParams(layoutParams);
                    selectDisplay.setX((float) this.mLocation.left);
                    selectDisplay.setY((float) this.mLocation.top);
                }
            }
        }

        private ImageView selectDisplay() {
            updateDisplay();
            if (!isVisible()) {
                this.mChildDisplay.setVisibility(4);
                this.mPointDisplay.setVisibility(4);
                return null;
            } else if (isNeutral()) {
                this.mChildDisplay.setVisibility(4);
                this.mPointDisplay.setVisibility(0);
                return this.mPointDisplay;
            } else {
                this.mChildDisplay.setVisibility(0);
                this.mPointDisplay.setVisibility(4);
                return this.mChildDisplay;
            }
        }

        private void updateDisplay() {
            View rootView = ((View) TextSelector.this.mHelper.getLocalDisplay()).getRootView();
            if (rootView != null) {
                this.mPointDisplay = (ImageView) rootView.findViewById(R.id.pointSelector);
                this.mChildDisplay = (ImageView) rootView.findViewById(getDisplayId());
            }
        }

        private void reset() {
            this.mLocation.set(-1, -1, -1, -1);
            hide();
            disable();
            stopDragging();
        }

        private void enable() {
            this.mEnabled = true;
        }

        private void disable() {
            this.mEnabled = false;
        }

        private void show() {
            this.mShowing = TextSelector.this.isHolderInvisible() ^ 1;
        }

        private void hide() {
            this.mShowing = false;
            selectDisplay();
        }

        private boolean isEnabled() {
            return this.mEnabled;
        }

        private boolean isShowing() {
            return this.mShowing;
        }

        protected boolean isNeutral() {
            return TextSelector.this.isOnCursor() && !isDragging() && TextSelector.this.comparePoints(TextSelector.this.mEnterPoint, TextSelector.this.mLeavePoint) == 0;
        }

        protected boolean isVisible() {
            return isEnabled() && isShowing() && (isDragging() || (TextSelector.this.getRawRowIndex((float) this.mLocation.centerY()) >= 0 && TextSelector.this.getRawRowIndex((float) this.mLocation.centerY()) <= TextSelector.this.mHelper.getScreenRows()));
        }

        private boolean canDragTo(MotionEvent motionEvent) {
            boolean z = false;
            if (!isDragging() || TextSelector.this.isOnCursor()) {
                return false;
            }
            if (TextSelector.this.isInVerticalEdge(motionEvent.getY())) {
                return true;
            }
            int access$2600 = TextSelector.this.getRawColIndex(this.mDragX);
            int access$2700 = TextSelector.this.getRawRowIndex(this.mDragY);
            if (Math.abs(TextSelector.this.getRawColIndex(motionEvent.getX()) - access$2600) + Math.abs(TextSelector.this.getRawRowIndex(motionEvent.getY()) - access$2700) > 0) {
                z = true;
            }
            return z;
        }

        private boolean dragTo(MotionEvent motionEvent) {
            if (!canDragTo(motionEvent) || TextSelector.this.getDraggingPoint() == null) {
                return false;
            }
            TextSelector.this.getDraggingPoint().set(getSelectedX(motionEvent), getSelectedY(motionEvent));
            this.mDragX = motionEvent.getX();
            this.mDragY = motionEvent.getY();
            return true;
        }

        private void startDragging(float f, float f2) {
            if (!this.mDragging) {
                Log.d(TextSelector.TAG, "startDragging");
                this.mDragging = true;
                this.mDragX = f;
                this.mDragY = f2;
            }
        }

        private void stopDragging() {
            if (this.mDragging) {
                Log.d(TextSelector.TAG, "stopDragging");
                this.mDragging = false;
                this.mDragX = -1.0f;
                this.mDragY = -1.0f;
            }
        }

        protected boolean isDragging() {
            return this.mDragging;
        }

        private float getDistance(float f, float f2) {
            if (isEnabled()) {
                return getDistance(((float) (this.mLocation.right + this.mLocation.left)) / 2.0f, ((float) (this.mLocation.top + this.mLocation.bottom)) / 2.0f, f, f2);
            }
            return MAX_DISTANCE;
        }

        private float getDistance(float f, float f2, float f3, float f4) {
            return (float) Math.sqrt(Math.pow((double) (f3 - f), 2.0d) + Math.pow((double) (f4 - f2), 2.0d));
        }

        protected int getWidth() {
            return getWidth(HOLDER_SCALE_FACTOR_NORMAL);
        }

        protected int getWidth(float f) {
            return Math.round((TextSelector.this.mHelper.getMinFontSize() * f) * (o.e(TextSelector.this.mContext) ? HOLDER_SIZE_RATIO_PHONE : HOLDER_SIZE_RATIO_NON_PHONE));
        }

        protected int getHeight() {
            return getWidth();
        }

        protected int getHeight(float f) {
            return getWidth(f);
        }
    }

    private class EnterHolder extends SelectionHolder {
        protected int getDisplayId() {
            return R.id.enterSelector;
        }

        private EnterHolder() {
            super(TextSelector.this, null);
        }

        /* synthetic */ EnterHolder(TextSelector textSelector, AnonymousClass1 anonymousClass1) {
            this();
        }

        protected void draw(Canvas canvas) {
            if (isNeutral()) {
                draw(canvas, TextSelector.this.mEnterPoint.x + 0, TextSelector.this.mEnterPoint.y + 1);
            } else if (TextSelector.this.getOpeningPoint() == TextSelector.this.mEnterPoint) {
                draw(canvas, TextSelector.this.mEnterPoint.x + 0, TextSelector.this.mEnterPoint.y + 1);
            } else {
                draw(canvas, TextSelector.this.mEnterPoint.x + 1, TextSelector.this.mEnterPoint.y + 1);
            }
        }

        protected int getSelectedX(MotionEvent motionEvent) {
            int i = (!this.mShowing || TextSelector.this.getOpeningPoint() == TextSelector.this.mEnterPoint) ? 0 : -1;
            return Math.min(TextSelector.this.mHelper.getScreenCols(), Math.max(0, TextSelector.this.getRawColIndex(motionEvent.getX()) + i));
        }

        protected int getSelectedY(MotionEvent motionEvent) {
            return Math.min(TextSelector.this.mHelper.getScreenRows() - 1, Math.max(0, TextSelector.this.getRawRowIndex(motionEvent.getY()) + (this.mShowing ? -1 : 0)));
        }

        protected void setLocation(int i, int i2, float f) {
            float f2;
            if (isNeutral()) {
                f2 = (float) i;
                this.mLocation.set((int) (((TextSelector.this.mHelper.getCharacterWidth() / 2.0f) + f2) - ((float) (getWidth() / 2))), i2, (int) ((f2 + (TextSelector.this.mHelper.getCharacterWidth() / 2.0f)) + ((float) (getWidth() / 2))), getHeight() + i2);
            } else if (isDragging()) {
                f2 = (float) i;
                float f3 = (float) i2;
                this.mLocation.set(Math.round(f2 - (((float) getWidth(f)) * 0.95f)), Math.round(f3 - (((float) getHeight(f)) * 0.05f)), Math.round(f2 + (((float) getWidth(f)) * 0.05f)), Math.round(f3 + (((float) getHeight(f)) * 0.95f)));
            } else {
                this.mLocation.set(i - getWidth(f), i2, i, getHeight(f) + i2);
            }
        }
    }

    private class LeaveHolder extends SelectionHolder {
        protected int getDisplayId() {
            return R.id.leaveSelector;
        }

        private LeaveHolder() {
            super(TextSelector.this, null);
        }

        /* synthetic */ LeaveHolder(TextSelector textSelector, AnonymousClass1 anonymousClass1) {
            this();
        }

        protected void draw(Canvas canvas) {
            if (isNeutral()) {
                draw(canvas, TextSelector.this.mLeavePoint.x + 0, TextSelector.this.mLeavePoint.y + 1);
            } else if (TextSelector.this.getOpeningPoint() == TextSelector.this.mEnterPoint) {
                draw(canvas, TextSelector.this.mLeavePoint.x + 1, TextSelector.this.mLeavePoint.y + 1);
            } else {
                draw(canvas, TextSelector.this.mLeavePoint.x + 0, TextSelector.this.mLeavePoint.y + 1);
            }
        }

        protected int getSelectedX(MotionEvent motionEvent) {
            int i = (!this.mShowing || TextSelector.this.getOpeningPoint() == TextSelector.this.mLeavePoint) ? 0 : -1;
            return Math.min(TextSelector.this.mHelper.getScreenCols(), Math.max(0, TextSelector.this.getRawColIndex(motionEvent.getX()) + i));
        }

        protected int getSelectedY(MotionEvent motionEvent) {
            return Math.min(TextSelector.this.mHelper.getScreenRows() - 1, Math.max(0, TextSelector.this.getRawRowIndex(motionEvent.getY()) + (this.mShowing ? -1 : 0)));
        }

        protected void setLocation(int i, int i2, float f) {
            float f2;
            if (isNeutral()) {
                f2 = (float) i;
                this.mLocation.set((int) (((TextSelector.this.mHelper.getCharacterWidth() / 2.0f) + f2) - ((float) (getWidth() / 2))), i2, (int) ((f2 + (TextSelector.this.mHelper.getCharacterWidth() / 2.0f)) + ((float) (getWidth() / 2))), getHeight() + i2);
            } else if (isDragging()) {
                f2 = (float) i;
                float f3 = (float) i2;
                this.mLocation.set(Math.round(f2 - (((float) getWidth(f)) * 0.05f)), Math.round(f3 - (((float) getHeight(f)) * 0.05f)), Math.round(f2 + (((float) getWidth(f)) * 0.95f)), Math.round(f3 + (((float) getHeight(f)) * 0.95f)));
            } else {
                this.mLocation.set(i, i2, getWidth(f) + i, getHeight(f) + i2);
            }
        }
    }

    TextSelector(ITerminalViewHelper iTerminalViewHelper) {
        this.mHelper = iTerminalViewHelper;
        this.mContext = iTerminalViewHelper.getContext();
    }

    public boolean isDraggingHolder() {
        return getDraggingHolder() != null;
    }

    public boolean isOnCursor(int i, int i2) {
        return isPointOnCursor(getColIndex((float) i), getRowIndex((float) i2));
    }

    public boolean isOnCursor() {
        return isPointOnCursor(this.mEnterPoint.x, this.mEnterPoint.y) && isPointOnCursor(this.mLeavePoint.x, this.mLeavePoint.y);
    }

    public boolean onDown(MotionEvent motionEvent) {
        this.mTouchDown = true;
        return resume(motionEvent);
    }

    public void onFling() {
        this.mFlinging = true;
    }

    public void onFlingEnd() {
        this.mFlinging = false;
        this.mHelper.invalidate();
    }

    public boolean onLongPress(MotionEvent motionEvent) {
        if (isDraggingHolder()) {
            return false;
        }
        return start(motionEvent);
    }

    public boolean onMove(MotionEvent motionEvent) {
        if (isSelecting()) {
            return run(motionEvent);
        }
        return false;
    }

    public boolean onSingleTapUp(MotionEvent motionEvent) {
        return stop();
    }

    public boolean onUp(MotionEvent motionEvent) {
        this.mTouchDown = false;
        if (pause()) {
            if (isSelecting()) {
                this.mEnterHolder.show();
                this.mLeaveHolder.show();
            }
            return true;
        }
        stop();
        return false;
    }

    public Callback2 getActionModeCallback(final OnActionModeListener onActionModeListener) {
        return new Callback2() {
            public boolean onCreateActionMode(ActionMode actionMode, Menu menu) {
                actionMode.getMenuInflater().inflate(R.menu.menu_terminal_mode, menu);
                TextSelector.this.mActionMode = actionMode;
                return true;
            }

            public void onGetContentRect(ActionMode actionMode, View view, Rect rect) {
                Point rect2 = getRect();
                if (rect2 != null) {
                    rect.set(rect2.x, rect2.y, rect2.x, rect2.y);
                } else {
                    rect.set(0, 0, 0, 0);
                }
            }

            public boolean onPrepareActionMode(ActionMode actionMode, Menu menu) {
                menu.getItem(0).setEnabled(canCopy());
                menu.getItem(1).setEnabled(canPaste());
                return false;
            }

            public boolean onActionItemClicked(ActionMode actionMode, MenuItem menuItem) {
                switch (menuItem.getItemId()) {
                    case R.id.actionCopy:
                        a.a().a(TextSelector.this.getSelectedText());
                        actionMode.finish();
                        onActionModeListener.onItemClicked();
                        TextSelector.this.stop();
                        return true;
                    case R.id.actionPaste:
                        CharSequence b = a.a().b();
                        if (!TextUtils.isEmpty(b)) {
                            TextSelector.this.mHelper.write(b.toString());
                        }
                        actionMode.finish();
                        onActionModeListener.onItemClicked();
                        TextSelector.this.stop();
                        return true;
                    default:
                        return false;
                }
            }

            public void onDestroyActionMode(ActionMode actionMode) {
                TextSelector.this.mActionMode = null;
            }

            private boolean canCopy() {
                return (TextSelector.this.isOnCursor() || !TextSelector.this.isSelectionVisible() || TextUtils.isEmpty(TextSelector.this.getSelectedText())) ? false : true;
            }

            private boolean canPaste() {
                boolean access$1200 = TextSelector.this.hasContextPoint() ? TextSelector.this.isPointOnCursor(TextSelector.this.getColIndex((float) TextSelector.this.mContextPoint.x), TextSelector.this.getRowIndex((float) TextSelector.this.mContextPoint.y)) : TextSelector.this.isSelectionVisible() && TextSelector.this.isOnCursor();
                if (access$1200 && a.a().c() && !TextSelector.this.hasEmojis(a.a().b())) {
                    return true;
                }
                return false;
            }

            private Point getRect() {
                return TextSelector.this.hasContextPoint() ? TextSelector.this.mContextPoint : TextSelector.this.getSelectedPoint();
            }
        };
    }

    public int getMaxHolderWidth() {
        return this.mEnterHolder.getWidth(1.0f);
    }

    public void onDraw(Canvas canvas) {
        updateActionMode(adjustLocation());
        drawHolder(canvas);
    }

    public boolean updateSelection(int i, int i2) {
        if (!isSelecting()) {
            return false;
        }
        i = -i;
        i2 = -i2;
        setEnterPointOffset(i, i2);
        setLeavePointOffset(i, i2);
        this.mHelper.invalidate();
        return true;
    }

    public Point getOpeningPoint() {
        return this.mReversed ? this.mLeavePoint : this.mEnterPoint;
    }

    public Point getClosingPoint() {
        return this.mReversed ? this.mEnterPoint : this.mLeavePoint;
    }

    public boolean isSelecting() {
        return this.mSelecting;
    }

    public void toggleActionMode(int i, int i2) {
        if (this.mActionMode == null) {
            this.mContextPoint = new Point(i, i2);
            showActionMode();
            return;
        }
        this.mContextPoint = null;
        hideActionMode();
    }

    private boolean adjustLocation() {
        boolean z;
        if (this.mHelper.getTopRow() != getTopRow()) {
            setEnterPointOffset(0, getTopRow() - this.mHelper.getTopRow());
            setLeavePointOffset(0, getTopRow() - this.mHelper.getTopRow());
            z = true;
        } else {
            z = false;
        }
        if (this.mHelper.getActiveTranscriptRows() != getActiveTranscriptRows()) {
            setEnterPointOffset(0, getActiveTranscriptRows() - this.mHelper.getActiveTranscriptRows());
            setLeavePointOffset(0, getActiveTranscriptRows() - this.mHelper.getActiveTranscriptRows());
            z = true;
        }
        setActiveTranscriptRows(this.mHelper.getActiveTranscriptRows());
        setTopRow(this.mHelper.getTopRow());
        return z;
    }

    private void adjustSelectionOnMove() {
        if (getPointDistance(getClosingPoint(), getOpeningPoint()) >= 2) {
            int i = 1;
            this.mReversed ^= true;
            setEnterPointOffset(this.mReversed ? -1 : 1, 0);
            if (!this.mReversed) {
                i = -1;
            }
            setLeavePointOffset(i, 0);
        }
    }

    private boolean adjustSelectionOnPause() {
        boolean z = true;
        if (!isSelectionVisible() || isFlinging()) {
            return true;
        }
        doNormalize();
        if (!(this.mHelper.isUsingMouse() || this.mHelper.isUsingPen())) {
            doWordWrap();
        }
        if (!isOnCursor() && TextUtils.isEmpty(getSelectedText(this.mEnterPoint, this.mLeavePoint))) {
            z = false;
        }
        return z;
    }

    private int comparePoints(Point point, Point point2) {
        if (point.equals(point2)) {
            return 0;
        }
        return (point.y < point2.y || (point.y == point2.y && point.x < point2.x)) ? -1 : 1;
    }

    private void doNormalize() {
        if (getOpeningPoint() == this.mLeavePoint) {
            Point point = this.mEnterPoint;
            this.mEnterPoint = this.mLeavePoint;
            this.mLeavePoint = point;
        }
        this.mEnterPoint.x = getColIndex((float) getXPoint(this.mEnterPoint.x));
        this.mEnterPoint.y = getRowIndex((float) getYPoint(this.mEnterPoint.y));
        this.mLeavePoint.x = getColIndex((float) getXPoint(this.mLeavePoint.x));
        this.mLeavePoint.y = getRowIndex((float) getYPoint(this.mLeavePoint.y));
        this.mReversed = false;
    }

    private void doWordWrap() {
        if (this.mWordWrap) {
            int i;
            Object selectedText;
            Point point = new Point(this.mEnterPoint);
            do {
                selectedText = getSelectedText(point, point);
                if (TextUtils.isEmpty(selectedText) || Character.isWhitespace(selectedText.codePointAt(0))) {
                    i = 0;
                    continue;
                } else {
                    setEnterPoint(point.x, point.y);
                    point = getPrevPoint(this.mEnterPoint);
                    i = this.mEnterPoint.equals(point) ^ 1;
                    continue;
                }
            } while (i != 0);
            point = new Point(this.mLeavePoint);
            do {
                selectedText = getSelectedText(point, point);
                if (TextUtils.isEmpty(selectedText) || Character.isWhitespace(selectedText.codePointAt(0))) {
                    i = 0;
                    continue;
                } else {
                    setLeavePoint(point.x, point.y);
                    point = getNextPoint(this.mLeavePoint);
                    i = this.mLeavePoint.equals(point) ^ 1;
                    continue;
                }
            } while (i != 0);
            this.mWordWrap = false;
        }
    }

    private void drawHolder(Canvas canvas) {
        this.mEnterHolder.draw(canvas);
        this.mLeaveHolder.draw(canvas);
    }

    private SelectionHolder getDraggingHolder() {
        if (this.mEnterHolder.isDragging()) {
            return this.mEnterHolder;
        }
        return this.mLeaveHolder.isDragging() ? this.mLeaveHolder : null;
    }

    private Point getDraggingPoint() {
        if (this.mEnterHolder.isDragging()) {
            return this.mEnterPoint;
        }
        return this.mLeaveHolder.isDragging() ? this.mLeavePoint : null;
    }

    private int getPointDistance(Point point, Point point2) {
        return ((point2.y - point.y) * this.mHelper.getScreenCols()) + (point2.x - point.x);
    }

    private Point getSelectedPoint() {
        Point point = isPointVisible(this.mEnterPoint) ? this.mEnterPoint : isPointVisible(this.mLeavePoint) ? this.mLeavePoint : null;
        if (point != null) {
            return new Point(getXPoint(point.x), getYPoint(point.y));
        }
        return null;
    }

    private String getSelectedText() {
        return getSelectedText(getOpeningPoint(), getClosingPoint());
    }

    private String getSelectedText(Point point, Point point2) {
        return this.mHelper.getSelectedText(point.x, point.y, point2.x, point2.y);
    }

    private int getXPoint(int i) {
        return this.mHelper.getLeft() + Math.round(this.mHelper.getCharacterWidth() * ((float) i));
    }

    private int getYPoint(int i) {
        return (this.mHelper.getTop() + this.mHelper.getTopOfScreenMargin()) + Math.round((float) (this.mHelper.getCharacterHeight() * i));
    }

    private int getColIndex(float f) {
        return Math.min(this.mHelper.getTotalCols() - 1, Math.max(0, getRawColIndex(f)));
    }

    private int getRowIndex(float f) {
        return Math.min(this.mHelper.getTotalRows() - 1, Math.max(getRawRowIndex(f), this.mHelper.getActiveTranscriptRows() * -1));
    }

    private int getRawColIndex(float f) {
        return (int) ((f - ((float) this.mHelper.getLeft())) / this.mHelper.getCharacterWidth());
    }

    private int getRawRowIndex(float f) {
        return (int) ((f - ((float) (this.mHelper.getTop() + this.mHelper.getTopOfScreenMargin()))) / ((float) this.mHelper.getCharacterHeight()));
    }

    private Point getPrevPoint(Point point) {
        Point point2 = new Point(point);
        if (point.x > 0) {
            point2.offset(-1, 0);
        } else if (point.y > 0) {
            point2.set(this.mHelper.getScreenCols() - 1, point.y - 1);
        }
        return point2;
    }

    private Point getNextPoint(Point point) {
        Point point2 = new Point(point);
        if (point.x + 1 < this.mHelper.getScreenCols()) {
            point2.offset(1, 0);
        } else if (point.y + 1 < this.mHelper.getScreenRows()) {
            point2.set(0, point.y + 1);
        }
        return point2;
    }

    private boolean hasContextPoint() {
        return isHolderInvisible() && this.mContextPoint != null;
    }

    private boolean hasEmojis(CharSequence charSequence) {
        if (TextUtils.isEmpty(charSequence)) {
            return false;
        }
        String charSequence2 = charSequence.toString();
        for (int i = 0; i < charSequence2.length(); i++) {
            int codePointAt = charSequence2.codePointAt(i);
            if ((127744 <= codePointAt && codePointAt <= 128591) || (128640 <= codePointAt && codePointAt <= 128767)) {
                return true;
            }
        }
        return false;
    }

    private boolean isFlinging() {
        return this.mFlinging;
    }

    private boolean isHolderInvisible() {
        return o.d(this.mContext) && (this.mHelper.isUsingMouse() || this.mHelper.isUsingStylus());
    }

    private boolean isPointOnCursor(int i, int i2) {
        boolean z = false;
        if (this.mHelper.isOnMainScreen()) {
            if (this.mHelper.getTopRow() == 0 && ((i >= this.mHelper.getCursorCol() && i2 == this.mHelper.getCursorRow()) || i2 > this.mHelper.getCursorRow())) {
                z = true;
            }
            return z;
        }
        if (this.mHelper.getTopRow() == 0 && i >= this.mHelper.getCursorCol() && i2 == this.mHelper.getCursorRow()) {
            z = true;
        }
        return z;
    }

    private boolean isPointVisible(Point point) {
        return point.x >= 0 && point.x < this.mHelper.getScreenCols() && point.y >= 0 && point.y < this.mHelper.getScreenRows();
    }

    private boolean isSelectable(int i, int i2) {
        return i2 >= 0 && i2 < this.mHelper.getScreenRows() && i >= 0 && i < this.mHelper.getScreenCols();
    }

    private boolean isSelectionVisible() {
        return isPointVisible(this.mEnterPoint) || isPointVisible(this.mLeavePoint);
    }

    private boolean isTouchDown() {
        return this.mTouchDown;
    }

    private boolean isInVerticalEdge(float f) {
        return f < ((float) this.mHelper.getTop()) || f > ((float) (this.mHelper.getTop() + this.mHelper.getHeight()));
    }

    private void showActionMode() {
        if (this.mActionMode == null && this.mActionModeRunnable == null) {
            this.mActionModeRunnable = new Runnable() {
                public void run() {
                    if (TextSelector.this.mActionMode == null) {
                        Log.i(TextSelector.TAG, "showActionMode");
                        TextSelector.this.mHelper.showActionMode();
                    }
                }
            };
            this.mHelper.postDelayed(this.mActionModeRunnable, 150);
        }
    }

    private void hideActionMode() {
        if (this.mActionModeRunnable != null) {
            this.mHelper.cancelPost(this.mActionModeRunnable);
            this.mActionModeRunnable = null;
        }
        if (this.mActionMode != null) {
            Log.i(TAG, "hideActionMode");
            this.mActionMode.finish();
            this.mActionMode = null;
        }
    }

    private void updateActionMode(boolean z) {
        if (!isHolderInvisible()) {
            if (z || isTouchDown() || isDraggingHolder() || isFlinging() || !isSelectionVisible()) {
                hideActionMode();
            } else {
                showActionMode();
            }
        }
    }

    private void scrollInVerticalEdge(MotionEvent motionEvent) {
        int i = motionEvent.getY() < ((float) this.mHelper.getTop()) ? -1 : motionEvent.getY() > ((float) (this.mHelper.getTop() + this.mHelper.getHeight())) ? 1 : 0;
        if (i != 0) {
            i *= this.mHelper.getCharacterHeight();
            this.mHelper.post(new Runnable() {
                public void run() {
                    TextSelector.this.mHelper.scroll(0.0f, (float) i);
                }
            });
            return;
        }
        this.mHelper.invalidate();
    }

    private int getActiveTranscriptRows() {
        return this.mActiveTranscriptRows;
    }

    private void setActiveTranscriptRows(int i) {
        if (this.mActiveTranscriptRows != i) {
            this.mActiveTranscriptRows = i;
            String str = TAG;
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("setActiveTranscriptRows : ");
            stringBuilder.append(this.mActiveTranscriptRows);
            Log.d(str, stringBuilder.toString());
        }
    }

    private int getTopRow() {
        return this.mTopRow;
    }

    private void setTopRow(int i) {
        if (this.mTopRow != i) {
            this.mTopRow = i;
            String str = TAG;
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("setTopRow : ");
            stringBuilder.append(this.mTopRow);
            Log.d(str, stringBuilder.toString());
        }
    }

    private void setEnterPoint(int i, int i2) {
        if (this.mEnterPoint.x != i || this.mEnterPoint.y != i2) {
            this.mEnterPoint.set(i, i2);
            String str = TAG;
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("setEnter");
            stringBuilder.append(this.mEnterPoint);
            Log.d(str, stringBuilder.toString());
        }
    }

    private void setEnterPointOffset(int i, int i2) {
        if (i != 0 || i2 != 0) {
            this.mEnterPoint.offset(i, i2);
            String str = TAG;
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("setEnter");
            stringBuilder.append(this.mEnterPoint);
            stringBuilder.append(" with (");
            stringBuilder.append(i);
            stringBuilder.append(", ");
            stringBuilder.append(i2);
            stringBuilder.append(")");
            Log.d(str, stringBuilder.toString());
        }
    }

    private void setLeavePoint(int i, int i2) {
        if (this.mLeavePoint.x != i || this.mLeavePoint.y != i2) {
            this.mLeavePoint.set(i, i2);
            String str = TAG;
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("setLeave");
            stringBuilder.append(this.mEnterPoint);
            Log.d(str, stringBuilder.toString());
        }
    }

    private void setLeavePointOffset(int i, int i2) {
        if (i != 0 || i2 != 0) {
            this.mLeavePoint.offset(i, i2);
            String str = TAG;
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("setLeave");
            stringBuilder.append(this.mEnterPoint);
            stringBuilder.append(" with (");
            stringBuilder.append(i);
            stringBuilder.append(", ");
            stringBuilder.append(i2);
            stringBuilder.append(")");
            Log.d(str, stringBuilder.toString());
        }
    }

    private boolean resume(MotionEvent motionEvent) {
        if (!isSelecting() || isOnCursor()) {
            return false;
        }
        Log.i(TAG, "resume");
        float x = motionEvent.getX();
        float y = motionEvent.getY();
        float access$1600 = this.mEnterHolder.getDistance(x, y);
        float access$16002 = this.mLeaveHolder.getDistance(x, y);
        float characterWidth = (this.mEnterHolder.isShowing() && this.mLeaveHolder.isShowing()) ? this.mHelper.getCharacterWidth() * 3.0f : access$1600 + access$16002;
        if (access$1600 <= characterWidth && access$1600 < access$16002) {
            this.mEnterHolder.startDragging(x, y);
        } else if (access$16002 <= characterWidth) {
            this.mLeaveHolder.startDragging(x, y);
        }
        return true;
    }

    private boolean run(MotionEvent motionEvent) {
        if (!isSelecting() || getDraggingHolder() == null || !getDraggingHolder().dragTo(motionEvent)) {
            return false;
        }
        adjustSelectionOnMove();
        scrollInVerticalEdge(motionEvent);
        return true;
    }

    private boolean pause() {
        if (!isSelecting()) {
            return false;
        }
        Log.i(TAG, "pause");
        if (!this.mEnterHolder.isEnabled()) {
            this.mEnterHolder.enable();
        }
        if (!this.mLeaveHolder.isEnabled()) {
            this.mLeaveHolder.enable();
        }
        if (this.mEnterHolder.isDragging()) {
            this.mEnterHolder.stopDragging();
        }
        if (this.mLeaveHolder.isDragging()) {
            this.mLeaveHolder.stopDragging();
        }
        if (!adjustSelectionOnPause()) {
            return false;
        }
        this.mHelper.invalidate();
        return true;
    }

    private boolean start(MotionEvent motionEvent) {
        Log.i(TAG, "start");
        this.mWordWrap = true;
        this.mReversed = false;
        this.mContextPoint = null;
        if (!init(motionEvent)) {
            stop();
            return false;
        } else if (pause()) {
            resume(motionEvent);
            return true;
        } else {
            stop();
            return false;
        }
    }

    private boolean stop() {
        Log.i(TAG, "stop");
        this.mSelecting = false;
        hideActionMode();
        setEnterPoint(-1, -1);
        setLeavePoint(-1, -1);
        this.mEnterHolder.reset();
        this.mLeaveHolder.reset();
        this.mHelper.invalidate();
        return true;
    }

    private boolean init(MotionEvent motionEvent) {
        if (isDraggingHolder() || motionEvent == null) {
            return false;
        }
        int colIndex = getColIndex(motionEvent.getX());
        int rowIndex = getRowIndex(motionEvent.getY());
        if (!isPointOnCursor(colIndex, rowIndex) && !isSelectable(colIndex, rowIndex)) {
            return false;
        }
        Log.i(TAG, "init");
        this.mSelecting = true;
        if (isPointOnCursor(colIndex, rowIndex)) {
            colIndex = this.mHelper.getCursorCol();
            rowIndex = this.mHelper.getCursorRow();
        }
        setEnterPoint(colIndex, rowIndex);
        setLeavePoint(colIndex, rowIndex);
        setActiveTranscriptRows(this.mHelper.getActiveTranscriptRows());
        setTopRow(this.mHelper.getTopRow());
        return true;
    }
}
