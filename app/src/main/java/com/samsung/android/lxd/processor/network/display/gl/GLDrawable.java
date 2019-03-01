package com.samsung.android.lxd.processor.network.display.gl;

import android.content.Context;
import android.opengl.GLES20;
import com.samsung.android.lxd.processor.utils.log.Log;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;
import java.nio.IntBuffer;
import java.nio.ShortBuffer;

public class GLDrawable {
    static int COORDS_PER_VERTEX = 2;
    private static final String TAG = "GLDrawable";
    static ShortBuffer drawListBuffer;
    static final short[] drawOrder = new short[]{(short) 0, (short) 1, (short) 2, (short) 0, (short) 2, (short) 3};
    private static Context mContext;
    static final float[] squareCoords = new float[]{-1.0f, 1.0f, -1.0f, -1.0f, 1.0f, -1.0f, 1.0f, 1.0f};
    static final float[] texcoord = new float[]{0.0f, 0.0f, 0.0f, 1.0f, 1.0f, 1.0f, 1.0f, 0.0f};
    static final int vertexStride = (COORDS_PER_VERTEX * 4);
    final String FSHADER_FILENAME = "fShader.glsl";
    final String VSHADER_FILENAME = "vShader.glsl";
    private int mBasetextureHandle;
    private int[] mBitmap = null;
    private int mFrameBufferHeight;
    private int mFrameBufferWidth;
    private int mPositionHandle;
    private int mProgram;
    private int mTexcoordHandle;
    private int mTextureID;
    private FloatBuffer texBuffer;
    private FloatBuffer vertexBuffer;

    public GLDrawable(Context context) {
        mContext = context;
    }

    public void init() {
        String stringFromFile;
        Object e;
        int loadShader;
        int loadShader2;
        StringBuilder stringBuilder;
        Log.i(TAG, "GLDrawable START !");
        loadTexture();
        ByteBuffer allocateDirect = ByteBuffer.allocateDirect(squareCoords.length * 4);
        allocateDirect.order(ByteOrder.nativeOrder());
        this.vertexBuffer = allocateDirect.asFloatBuffer();
        this.vertexBuffer.put(squareCoords);
        this.vertexBuffer.position(0);
        allocateDirect = ByteBuffer.allocateDirect(drawOrder.length * 2);
        allocateDirect.order(ByteOrder.nativeOrder());
        drawListBuffer = allocateDirect.asShortBuffer();
        drawListBuffer.put(drawOrder);
        drawListBuffer.position(0);
        allocateDirect = ByteBuffer.allocateDirect(texcoord.length * 4);
        allocateDirect.order(ByteOrder.nativeOrder());
        this.texBuffer = allocateDirect.asFloatBuffer();
        this.texBuffer.put(texcoord);
        this.texBuffer.position(0);
        String str = "";
        String str2 = "";
        try {
            stringFromFile = getStringFromFile("vShader.glsl");
            try {
                str = getStringFromFile("fShader.glsl");
            } catch (Exception e2) {
                e = e2;
            }
        } catch (Exception e3) {
            Exception exception = e3;
            stringFromFile = str;
            e = exception;
            String str3 = TAG;
            StringBuilder stringBuilder2 = new StringBuilder();
            stringBuilder2.append("getStringFromFile Error! : ");
            stringBuilder2.append(e);
            Log.d(str3, stringBuilder2.toString());
            str = str2;
            loadShader = loadShader(35633, stringFromFile);
            loadShader2 = loadShader(35632, str);
            stringFromFile = TAG;
            stringBuilder = new StringBuilder();
            stringBuilder.append("vertexShader, fragmentShader:");
            stringBuilder.append(loadShader);
            stringBuilder.append(", ");
            stringBuilder.append(loadShader2);
            Log.i(stringFromFile, stringBuilder.toString());
            this.mProgram = GLES20.glCreateProgram();
            GLES20.glAttachShader(this.mProgram, loadShader);
            GLES20.glAttachShader(this.mProgram, loadShader2);
            GLES20.glLinkProgram(this.mProgram);
            this.mPositionHandle = GLES20.glGetAttribLocation(this.mProgram, "vPosition");
            GLES20.glEnableVertexAttribArray(this.mPositionHandle);
            GLES20.glVertexAttribPointer(this.mPositionHandle, COORDS_PER_VERTEX, 5126, false, vertexStride, this.vertexBuffer);
            Log.i(TAG, "GLDrawable END !");
        }
        loadShader = loadShader(35633, stringFromFile);
        loadShader2 = loadShader(35632, str);
        stringFromFile = TAG;
        stringBuilder = new StringBuilder();
        stringBuilder.append("vertexShader, fragmentShader:");
        stringBuilder.append(loadShader);
        stringBuilder.append(", ");
        stringBuilder.append(loadShader2);
        Log.i(stringFromFile, stringBuilder.toString());
        this.mProgram = GLES20.glCreateProgram();
        GLES20.glAttachShader(this.mProgram, loadShader);
        GLES20.glAttachShader(this.mProgram, loadShader2);
        GLES20.glLinkProgram(this.mProgram);
        this.mPositionHandle = GLES20.glGetAttribLocation(this.mProgram, "vPosition");
        GLES20.glEnableVertexAttribArray(this.mPositionHandle);
        GLES20.glVertexAttribPointer(this.mPositionHandle, COORDS_PER_VERTEX, 5126, false, vertexStride, this.vertexBuffer);
        Log.i(TAG, "GLDrawable END !");
    }

    public void draw() {
        if (this.mBitmap != null) {
            GLES20.glClear(16384);
            GLES20.glUseProgram(this.mProgram);
            this.mTexcoordHandle = GLES20.glGetAttribLocation(this.mProgram, "inputtexcoord");
            GLES20.glEnableVertexAttribArray(this.mTexcoordHandle);
            GLES20.glVertexAttribPointer(this.mTexcoordHandle, 2, 5126, false, 0, this.texBuffer);
            this.mBasetextureHandle = GLES20.glGetUniformLocation(this.mProgram, "basetexture");
            GLES20.glEnableVertexAttribArray(this.mBasetextureHandle);
            GLES20.glUniform1i(this.mBasetextureHandle, 0);
            GLES20.glTexImage2D(3553, 0, 6408, this.mFrameBufferWidth, this.mFrameBufferHeight, 0, 6408, 5121, IntBuffer.wrap(this.mBitmap));
            GLES20.glBindTexture(3553, this.mTextureID);
            GLES20.glActiveTexture(33984);
            GLES20.glDrawElements(4, drawOrder.length, 5123, drawListBuffer);
            if (GLDebugRect.getGLDebugRect() != null) {
                GLDebugRect.getGLDebugRect().draw();
            }
        }
    }

    public static int loadShader(int i, String str) {
        i = GLES20.glCreateShader(i);
        GLES20.glShaderSource(i, str);
        GLES20.glCompileShader(i);
        return i;
    }

    public void loadTexture() {
        Log.d(TAG, "loadTexture BEGIN !");
        int[] iArr = new int[1];
        GLES20.glEnable(3553);
        GLES20.glGenTextures(1, iArr, 0);
        this.mTextureID = iArr[0];
        GLES20.glBindTexture(3553, this.mTextureID);
        GLES20.glTexParameterf(3553, 10241, 9729.0f);
        GLES20.glTexParameterf(3553, 10240, 9729.0f);
        GLES20.glTexParameterf(3553, 10242, 33071.0f);
        GLES20.glTexParameterf(3553, 10243, 33071.0f);
        Log.d(TAG, "loadTexture END !");
    }

    public void setBitmapPixels(int[] iArr, int i, int i2) {
        this.mFrameBufferWidth = i;
        this.mFrameBufferHeight = i2;
        if (iArr != null) {
            this.mBitmap = iArr;
        }
    }

    public void setFps(float f) {
        if (GLDebugRect.getGLDebugRect() != null) {
            GLDebugRect.getGLDebugRect().setFps(f);
        }
    }

    public void destroy() {
        GLDebugRect.destroy();
    }

    static String getStringFromFile(String str) {
        InputStream open = mContext.getAssets().open(str);
        String convertStreamToString = convertStreamToString(open);
        open.close();
        return convertStreamToString;
    }

    static String convertStreamToString(InputStream inputStream) {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
        StringBuilder stringBuilder = new StringBuilder();
        while (true) {
            String readLine = bufferedReader.readLine();
            if (readLine != null) {
                stringBuilder.append(readLine);
                stringBuilder.append("\n");
            } else {
                bufferedReader.close();
                return stringBuilder.toString();
            }
        }
    }
}
