package com.samsung.android.lxd.processor.network.display.gl;

import android.opengl.GLES20;
import com.samsung.android.lxd.processor.utils.Utils;
import com.samsung.android.lxd.processor.utils.log.Log;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;

public class GLDebugRect {
    static float[] fpsSquareCoords = new float[]{0.8f, 0.9f, 0.8f, 0.7f, 0.9f, 0.7f, 0.9f, 0.9f};
    private static GLDebugRect mGLDebugRect;
    private final String TAG = GLDebugRect.class.getSimpleName();
    private int mColorHandle;
    private float mFpsToColor = 0.0f;
    private int mPositionHandle;
    private int mProgram;
    private FloatBuffer mVertexBuffer;
    final String testFSHADER_FILENAME = "fShader_fpsRect.glsl";
    final String testVSHADER_FILENAME = "vShader_fpsRect.glsl";

    public static synchronized GLDebugRect getGLDebugRect() {
        synchronized (GLDebugRect.class) {
            if (Utils.isDebug()) {
                if (mGLDebugRect == null) {
                    mGLDebugRect = new GLDebugRect();
                }
                GLDebugRect gLDebugRect = mGLDebugRect;
                return gLDebugRect;
            }
            return null;
        }
    }

    public static void destroy() {
        mGLDebugRect = null;
    }

    private GLDebugRect() {
        String stringFromFile;
        Object e;
        ByteBuffer allocateDirect;
        int loadShader;
        int loadShader2;
        String str = "";
        String str2 = "";
        try {
            stringFromFile = GLDrawable.getStringFromFile("vShader_fpsRect.glsl");
            try {
                str = GLDrawable.getStringFromFile("fShader_fpsRect.glsl");
            } catch (Exception e2) {
                e = e2;
            }
        } catch (Exception e3) {
            Exception exception = e3;
            stringFromFile = str;
            e = exception;
            String str3 = this.TAG;
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("getStringFromFile Error! : ");
            stringBuilder.append(e);
            Log.d(str3, stringBuilder.toString());
            str = str2;
            allocateDirect = ByteBuffer.allocateDirect(fpsSquareCoords.length * 4);
            allocateDirect.order(ByteOrder.nativeOrder());
            this.mVertexBuffer = allocateDirect.asFloatBuffer();
            this.mVertexBuffer.put(fpsSquareCoords);
            this.mVertexBuffer.position(0);
            this.mProgram = GLES20.glCreateProgram();
            loadShader = GLDrawable.loadShader(35633, stringFromFile);
            loadShader2 = GLDrawable.loadShader(35632, str);
            GLES20.glAttachShader(this.mProgram, loadShader);
            GLES20.glAttachShader(this.mProgram, loadShader2);
            GLES20.glLinkProgram(this.mProgram);
        }
        allocateDirect = ByteBuffer.allocateDirect(fpsSquareCoords.length * 4);
        allocateDirect.order(ByteOrder.nativeOrder());
        this.mVertexBuffer = allocateDirect.asFloatBuffer();
        this.mVertexBuffer.put(fpsSquareCoords);
        this.mVertexBuffer.position(0);
        this.mProgram = GLES20.glCreateProgram();
        loadShader = GLDrawable.loadShader(35633, stringFromFile);
        loadShader2 = GLDrawable.loadShader(35632, str);
        GLES20.glAttachShader(this.mProgram, loadShader);
        GLES20.glAttachShader(this.mProgram, loadShader2);
        GLES20.glLinkProgram(this.mProgram);
    }

    public void draw() {
        GLES20.glUseProgram(this.mProgram);
        this.mPositionHandle = GLES20.glGetAttribLocation(this.mProgram, "fpsRectPosition");
        GLES20.glEnableVertexAttribArray(this.mPositionHandle);
        GLES20.glVertexAttribPointer(this.mPositionHandle, GLDrawable.COORDS_PER_VERTEX, 5126, false, GLDrawable.vertexStride, this.mVertexBuffer);
        this.mColorHandle = GLES20.glGetUniformLocation(this.mProgram, "fpsRectColor");
        GLES20.glUniform1f(this.mColorHandle, this.mFpsToColor);
        GLES20.glDrawElements(4, GLDrawable.drawOrder.length, 5123, GLDrawable.drawListBuffer);
    }

    public void setFps(float f) {
        this.mFpsToColor = f / 60.0f;
    }
}
