package com.samsung.android.lxd.processor.network.channel.pty.internal;

import android.graphics.Canvas;

interface TextRenderer {
    void drawTextRun(Canvas canvas, float f, float f2, int i, int i2, char[] cArr, int i3, int i4, boolean z, int i5, int i6, int i7, int i8, int i9);

    int getCharacterHeight();

    float getCharacterWidth();

    int getTopMargin();

    void setReverseVideo(boolean z);
}
