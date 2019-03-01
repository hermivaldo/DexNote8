package com.samsung.android.lxd.processor.network.channel.pty;

import android.view.ActionMode.Callback2;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.ScaleGestureDetector;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputConnection;
import com.samsung.android.lxd.a.o;
import com.samsung.android.lxd.processor.ICommonContext;
import com.samsung.android.lxd.processor.network.channel.pty.internal.TerminalSession;
import com.samsung.android.lxd.processor.network.channel.pty.internal.TerminalSession.OnStateListener;
import com.samsung.android.lxd.processor.network.channel.pty.internal.TerminalSetting;
import com.samsung.android.lxd.processor.network.channel.pty.internal.TerminalView;
import com.samsung.android.lxd.processor.network.channel.pty.internal.TerminalView.OnUpdateListener;
import com.samsung.android.lxd.processor.network.display.NetworkDisplayContext;

public class PtyManager implements IPtyManager {
    private static final String TAG = "PtyManager";
    private OnInitListener mOnInitListener = null;
    private OnStartListener mOnStartListener = null;
    private boolean mPendingUpdate = false;
    private TerminalSession mSession = null;
    private TerminalView mView = null;

    interface OnInitListener {
        void onError(Throwable th);

        void onInit(String str);
    }

    interface OnStartListener {
        void onCommit(String str);

        void onContext(Callback2 callback2);

        void onFinish(Throwable th);

        void onSelect(boolean z);

        void onStart();

        void onUpdate(boolean z, boolean z2);
    }

    PtyManager() {
    }

    public NetworkDisplayContext getDisplayContext(boolean z, boolean z2) {
        return this.mView.getDisplayContext(z, z2);
    }

    public InputConnection getInputConnection(View view, EditorInfo editorInfo) {
        return this.mView.getInputConnection(view, editorInfo);
    }

    public void init(final ICommonContext iCommonContext, final OnInitListener onInitListener) {
        o.a(new Runnable() {
            public void run() {
                try {
                    PtyManager.this.mOnInitListener = onInitListener;
                    TerminalSetting terminalSetting = new TerminalSetting(iCommonContext);
                    PtyManager.this.mSession = new TerminalSession(terminalSetting);
                    PtyManager.this.mSession.addOnStateListener(new OnStateListener() {
                        public void onUpdate() {
                        }

                        public void onStart() {
                            if (PtyManager.this.mOnStartListener != null) {
                                PtyManager.this.mOnStartListener.onStart();
                                if (PtyManager.this.mPendingUpdate) {
                                    PtyManager.this.mPendingUpdate = false;
                                    PtyManager.this.mOnStartListener.onUpdate(false, false);
                                }
                            }
                        }

                        public void onFinish(Throwable th) {
                            if (PtyManager.this.mOnStartListener != null) {
                                PtyManager.this.mOnStartListener.onFinish(th);
                            }
                        }
                    });
                    PtyManager.this.mView = new TerminalView(iCommonContext, PtyManager.this.mSession, terminalSetting);
                    PtyManager.this.mView.setOnUpdateListener(new OnUpdateListener() {
                        public void onCommit(String str) {
                            if (PtyManager.this.mOnStartListener != null) {
                                PtyManager.this.mOnStartListener.onCommit(str);
                            }
                        }

                        public void onContext(Callback2 callback2) {
                            if (PtyManager.this.mOnStartListener != null) {
                                PtyManager.this.mOnStartListener.onContext(callback2);
                            }
                        }

                        public void onSelect(boolean z) {
                            if (PtyManager.this.mOnStartListener != null) {
                                PtyManager.this.mOnStartListener.onSelect(z);
                            }
                        }

                        public void onUpdate(boolean z, boolean z2) {
                            PtyManager.this.mPendingUpdate = PtyManager.this.mOnStartListener == null;
                            if (PtyManager.this.mOnStartListener != null) {
                                PtyManager.this.mOnStartListener.onUpdate(z, z2);
                            }
                        }
                    });
                    PtyManager.this.mOnInitListener.onInit(PtyManager.this.mSession.getPtsName());
                } catch (Throwable e) {
                    if (PtyManager.this.mOnInitListener != null) {
                        PtyManager.this.mOnInitListener.onError(e);
                    }
                }
            }
        });
    }

    public boolean isReady() {
        return this.mSession != null && this.mSession.isStarted();
    }

    public boolean isTextEditor() {
        return this.mView.onCheckIsTextEditor();
    }

    public void notifyDisplaySizeChange(final int i, final int i2) {
        o.a(new Runnable() {
            public void run() {
                PtyManager.this.mView.notifyDisplaySizeChange(i, i2);
            }
        });
    }

    public boolean notifyMouseEvent(final MotionEvent motionEvent) {
        o.a(new Runnable() {
            public void run() {
                PtyManager.this.mView.onTouchEvent(motionEvent);
            }
        });
        return true;
    }

    public boolean notifyKeyEvent(final int i, final KeyEvent keyEvent) {
        o.a(new Runnable() {
            public void run() {
                switch (keyEvent.getAction()) {
                    case 0:
                        PtyManager.this.mView.onKeyDown(i, keyEvent);
                        return;
                    case 1:
                        PtyManager.this.mView.onKeyUp(i, keyEvent);
                        return;
                    default:
                        return;
                }
            }
        });
        return true;
    }

    public boolean notifyDownEvent(final MotionEvent motionEvent) {
        o.a(new Runnable() {
            public void run() {
                PtyManager.this.mView.onDown(motionEvent);
            }
        });
        return true;
    }

    public boolean notifyLongPressEvent(final MotionEvent motionEvent) {
        o.a(new Runnable() {
            public void run() {
                PtyManager.this.mView.onLongPress(motionEvent);
            }
        });
        return true;
    }

    public boolean notifySingleTapUpEvent(final MotionEvent motionEvent) {
        o.a(new Runnable() {
            public void run() {
                PtyManager.this.mView.onSingleTapUp(motionEvent);
            }
        });
        return true;
    }

    public boolean notifyDoubleTapEvent(final MotionEvent motionEvent) {
        o.a(new Runnable() {
            public void run() {
                PtyManager.this.mView.onDoubleTap(motionEvent);
            }
        });
        return true;
    }

    public boolean notifyScrollEvent(MotionEvent motionEvent, MotionEvent motionEvent2, float f, float f2) {
        final MotionEvent motionEvent3 = motionEvent;
        final MotionEvent motionEvent4 = motionEvent2;
        final float f3 = f;
        final float f4 = f2;
        o.a(new Runnable() {
            public void run() {
                PtyManager.this.mView.onScroll(motionEvent3, motionEvent4, f3, f4);
            }
        });
        return true;
    }

    public boolean notifyFlingEvent(MotionEvent motionEvent, MotionEvent motionEvent2, float f, float f2) {
        final MotionEvent motionEvent3 = motionEvent;
        final MotionEvent motionEvent4 = motionEvent2;
        final float f3 = f;
        final float f4 = f2;
        o.a(new Runnable() {
            public void run() {
                PtyManager.this.mView.onFling(motionEvent3, motionEvent4, f3, f4);
            }
        });
        return true;
    }

    public boolean notifyScaleEvent(ScaleGestureDetector scaleGestureDetector) {
        final float scaleFactor = scaleGestureDetector.getScaleFactor();
        o.a(new Runnable() {
            public void run() {
                PtyManager.this.mView.onScale(scaleFactor);
            }
        });
        return false;
    }

    public boolean notifyScaleBeginEvent(ScaleGestureDetector scaleGestureDetector) {
        o.a(new Runnable() {
            public void run() {
                PtyManager.this.mView.onScaleBegin();
            }
        });
        return false;
    }

    public boolean notifyScaleEndEvent(ScaleGestureDetector scaleGestureDetector) {
        o.a(new Runnable() {
            public void run() {
                PtyManager.this.mView.onScaleEnd();
            }
        });
        return false;
    }

    public void resume() {
        o.a(new Runnable() {
            public void run() {
                PtyManager.this.mView.updateFontSize();
            }
        });
    }

    public void start(final OnStartListener onStartListener) {
        o.a(new Runnable() {
            public void run() {
                PtyManager.this.mOnStartListener = onStartListener;
                PtyManager.this.mView.updateFontSize();
            }
        });
    }

    public void stop() {
        o.a(new Runnable() {
            public void run() {
                if (PtyManager.this.mSession != null) {
                    PtyManager.this.mSession.onFinish();
                }
            }
        });
    }
}
