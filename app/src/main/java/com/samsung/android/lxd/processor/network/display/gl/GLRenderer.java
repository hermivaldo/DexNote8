package com.samsung.android.lxd.processor.network.display.gl;

import android.content.Context;
import android.opengl.GLES20;
import android.opengl.GLSurfaceView.Renderer;
import com.samsung.android.lxd.processor.utils.Utils;
import com.samsung.android.lxd.processor.utils.log.Log;
import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

public class GLRenderer implements Renderer {
    private static final String TAG = "GLRenderer";
    private Context mContext;
    private final GLDrawable mDrawable;
    private float mFps;
    private long mFpsStartTime;
    private int mFrameCnt;
    private double mTimeElapsed;

    public GLRenderer(Context context) {
        this.mTimeElapsed = 0.0d;
        this.mFrameCnt = 0;
        this.mFpsStartTime = 0;
        this.mFpsStartTime = System.currentTimeMillis();
        this.mContext = context;
        this.mDrawable = new GLDrawable(this.mContext);
    }

    private void CalculateFrameRate() {
        float currentTimeMillis = ((float) (System.currentTimeMillis() - this.mFpsStartTime)) * 0.001f;
        this.mFrameCnt++;
        this.mTimeElapsed += (double) currentTimeMillis;
        if (this.mTimeElapsed >= 1.0d) {
            this.mFps = (float) (((double) this.mFrameCnt) / this.mTimeElapsed);
            this.mDrawable.setFps(this.mFps);
            String str = TAG;
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("[ ");
            stringBuilder.append(this.mFps);
            stringBuilder.append(" ] fps");
            Log.d(str, stringBuilder.toString());
            this.mFrameCnt = 0;
            this.mTimeElapsed = 0.0d;
        }
        this.mFpsStartTime = System.currentTimeMillis();
    }

    public void setBitmapPixels(int[] iArr, int i, int i2) {
        if (this.mDrawable != null) {
            this.mDrawable.setBitmapPixels(iArr, i, i2);
        }
    }

    public void onDrawFrame(GL10 gl10) {
        if (Utils.isDebug()) {
            CalculateFrameRate();
        }
        this.mDrawable.draw();
    }

    public void onSurfaceCreated(GL10 gl10, EGLConfig eGLConfig) {
        Log.d(TAG, "onSurfaceCreated");
        GLES20.glClearColor(1.0f, 1.0f, 1.0f, 1.0f);
        this.mDrawable.init();
    }

    public void onSurfaceChanged(GL10 gl10, int i, int i2) {
        String str = TAG;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("onSurfaceChanged , width : ");
        stringBuilder.append(i);
        stringBuilder.append(", height : ");
        stringBuilder.append(i2);
        Log.d(str, stringBuilder.toString());
        GLES20.glViewport(0, 0, i, i2);
    }

    public void destroy() {
        Log.d(TAG, "destroy");
        this.mDrawable.destroy();
    }
}
