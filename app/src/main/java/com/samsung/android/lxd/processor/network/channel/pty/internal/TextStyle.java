package com.samsung.android.lxd.processor.network.channel.pty.internal;

final class TextStyle {
    static final int ciBackground = 257;
    static final int ciColorLength = 260;
    static final int ciCursorBackground = 259;
    static final int ciCursorForeground = 258;
    static final int ciForeground = 256;
    static final int fxBlink = 8;
    static final int fxBold = 1;
    static final int fxInverse = 16;
    static final int fxInvisible = 32;
    static final int fxItalic = 2;
    static final int fxNormal = 0;
    static final int fxUnderline = 4;
    static final int kNormalTextStyle = encode(ciForeground, ciBackground, 0);

    static int decodeBackColor(int i) {
        return i & 511;
    }

    static int decodeEffect(int i) {
        return (i >> 18) & 63;
    }

    static int decodeForeColor(int i) {
        return (i >> 9) & 511;
    }

    static int encode(int i, int i2, int i3) {
        return (((i & 511) << 9) | ((i3 & 63) << 18)) | (i2 & 511);
    }

    private TextStyle() {
        throw new UnsupportedOperationException();
    }
}
