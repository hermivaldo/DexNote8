package com.samsung.android.lxd.processor.network.channel.pty.internal;

interface Screen {
    void blockCopy(int i, int i2, int i3, int i4, int i5, int i6);

    void blockSet(int i, int i2, int i3, int i4, int i5, int i6);

    boolean fastResize(int i, int i2, int[] iArr);

    int getActiveRows();

    String getSelectedText(GrowableIntArray growableIntArray, int i, int i2, int i3, int i4);

    String getSelectedText(GrowableIntArray growableIntArray, int i, int i2, int i3, int i4, boolean z);

    String getTranscriptText();

    String getTranscriptText(GrowableIntArray growableIntArray);

    void resize(int i, int i2, int i3);

    void scroll(int i, int i2, int i3);

    void set(int i, int i2, byte b, int i3);

    void set(int i, int i2, int i3, int i4);

    void setLineWrap(int i);
}
