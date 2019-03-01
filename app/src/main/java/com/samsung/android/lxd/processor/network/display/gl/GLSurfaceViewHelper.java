package com.samsung.android.lxd.processor.network.display.gl;

import android.content.Context;
import android.opengl.GLSurfaceView;
import android.os.Handler;
import android.os.HandlerThread;
import android.util.AttributeSet;
import android.view.Choreographer;
import android.view.Choreographer.FrameCallback;

public class GLSurfaceViewHelper extends GLSurfaceView {
    private FrameCallback mFrameCallback = null;
    private Handler mHandler = null;
    private GLRenderer mRenderer = null;

    public GLSurfaceViewHelper(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        initialize(context);
    }

    public void initialize(Context context) {
        this.mRenderer = new GLRenderer(context);
        setEGLContextClientVersion(2);
        setRenderer(this.mRenderer);
        setRenderMode(0);
        this.mFrameCallback = new FrameCallback() {
            public void doFrame(long j) {
                GLSurfaceViewHelper.this.requestRender();
            }
        };
        HandlerThread handlerThread = new HandlerThread(getClass().getSimpleName());
        handlerThread.start();
        this.mHandler = new Handler(handlerThread.getLooper());
    }

    public void updateView(int[] iArr, int i, int i2) {
        this.mRenderer.setBitmapPixels(iArr, i, i2);
        this.mHandler.post(new Runnable() {
            public void run() {
                Choreographer.getInstance().postFrameCallback(GLSurfaceViewHelper.this.mFrameCallback);
            }
        });
    }

    public void destroy() {
        this.mHandler.post(new Runnable() {
            public void run() {
                Choreographer.getInstance().removeFrameCallback(GLSurfaceViewHelper.this.mFrameCallback);
            }
        });
        this.mRenderer.destroy();
    }
}
