package com.samsung.android.lxd.processor.network.channel.pty;

public class PtyJni {
    public static native Object[] createSubprocess(int i);

    public static native void sendSignal(int i, int i2);

    public static native void setPtyUTF8Mode(int i, boolean z);

    public static native void setPtyWindowSize(int i, int i2, int i3, int i4, int i5);

    public static native int waitFor(int i);

    static {
        System.loadLibrary("lxdjni");
    }
}
