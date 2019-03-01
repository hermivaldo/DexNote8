package com.samsung.android.lxd.processor.network.channel.pty.internal;

import android.graphics.Canvas;
import java.util.Arrays;

class TranscriptScreen implements Screen {
    private int mColumns;
    private UnicodeTranscript mData;
    private final boolean mMainScreen;
    private int mScreenRows;
    private int mTotalRows;

    public TranscriptScreen(int i, int i2, int i3) {
        this(i, i2, i3, false);
    }

    public TranscriptScreen(int i, int i2, int i3, boolean z) {
        this.mMainScreen = z;
        init(i, i2, i3, TextStyle.kNormalTextStyle);
    }

    private void init(int i, int i2, int i3, int i4) {
        this.mColumns = i;
        this.mTotalRows = i2;
        this.mScreenRows = i3;
        this.mData = new UnicodeTranscript(i, i2, i3, i4);
        initScreen(i4);
    }

    TranscriptScreen initScreen(int i) {
        this.mData.blockSet(0, 0, this.mColumns, this.mScreenRows, 32, i);
        return this;
    }

    boolean isMainScreen() {
        return this.mMainScreen;
    }

    public void setColorScheme(ColorScheme colorScheme) {
        this.mData.setDefaultStyle(TextStyle.kNormalTextStyle);
    }

    public void finish() {
        this.mData = null;
    }

    public void setLineWrap(int i) {
        this.mData.setLineWrap(i);
    }

    public void set(int i, int i2, int i3, int i4) {
        this.mData.setChar(i, i2, i3, i4);
    }

    public void set(int i, int i2, byte b, int i3) {
        this.mData.setChar(i, i2, b, i3);
    }

    public void scroll(int i, int i2, int i3) {
        this.mData.scroll(i, i2, i3);
    }

    public void blockCopy(int i, int i2, int i3, int i4, int i5, int i6) {
        this.mData.blockCopy(i, i2, i3, i4, i5, i6);
    }

    public void blockSet(int i, int i2, int i3, int i4, int i5, int i6) {
        this.mData.blockSet(i, i2, i3, i4, i5, i6);
    }

    public final void drawText(int i, Canvas canvas, float f, float f2, TextRenderer textRenderer, int i2, int i3, int i4, String str) {
        int i5 = i;
        int i6 = i2;
        int i7 = i3;
        int i8 = i4;
        try {
            char[] line = this.mData.getLine(i5);
            StyleRow lineColor = this.mData.getLineColor(i5);
            int defaultStyle = this.mData.getDefaultStyle();
            int i9;
            int i10;
            if (line == null) {
                char[] cArr;
                if (i7 != i8) {
                    i9 = i8 - i7;
                    cArr = new char[i9];
                    Arrays.fill(cArr, ' ');
                    i10 = i7;
                    i7 = 1;
                    int i11 = -1;
                    textRenderer.drawTextRun(canvas, f, f2, i10, i9, cArr, 0, 1, true, defaultStyle, i6, 0, 1, 1);
                    i5 = -1;
                } else {
                    i5 = -1;
                }
                if (i6 != i5) {
                    cArr = new char[1];
                    Arrays.fill(cArr, ' ');
                    textRenderer.drawTextRun(canvas, f, f2, i6, 1, cArr, 0, 1, true, defaultStyle, i6, 0, 1, 1);
                }
                return;
            }
            i5 = -1;
            int i12 = 1;
            if (lineColor != null) {
                int i13;
                int i14;
                char[] cArr2;
                int i15 = this.mColumns;
                int length = line.length;
                i10 = i5;
                defaultStyle = i10;
                int i16 = i12;
                int i17 = 0;
                i5 = 0;
                int i18 = 0;
                boolean z = false;
                i9 = 0;
                int i19 = 0;
                int i20 = 0;
                int i21 = 0;
                int i22 = 0;
                Object obj = null;
                while (i17 < i15 && i19 < length && line[i19] != 0) {
                    int charWidth;
                    int i23;
                    int i24;
                    int i25;
                    int i26;
                    Object obj2;
                    StyleRow styleRow;
                    if (Character.isHighSurrogate(line[i19])) {
                        charWidth = UnicodeTranscript.charWidth(line, i19);
                        i23 = 2;
                    } else {
                        charWidth = UnicodeTranscript.charWidth(line[i19]);
                        i23 = 1;
                    }
                    if (charWidth > 0) {
                        i17 = charWidth;
                        i5 = i22;
                    } else {
                        int i27 = i5;
                        i5 = i17;
                        i17 = i27;
                    }
                    i6 = lineColor.get(i5);
                    boolean z2 = (i5 >= i7 || (i17 == 2 && i5 == i7 - 1)) && i5 <= i8;
                    if (i6 == i18 && z2 == z && (charWidth <= 0 || obj == null)) {
                        i24 = i17;
                        i6 = i5;
                        i13 = i19;
                        i25 = charWidth;
                        i26 = length;
                        i14 = i15;
                        cArr2 = line;
                        obj2 = obj;
                        i7 = i2;
                        styleRow = lineColor;
                    } else {
                        boolean z3;
                        if (i10 >= 0) {
                            i24 = i17;
                            int i28 = i6;
                            i6 = i5;
                            i13 = i19;
                            i25 = charWidth;
                            i26 = length;
                            i14 = i15;
                            z3 = z2;
                            styleRow = lineColor;
                            defaultStyle = i28;
                            cArr2 = line;
                            textRenderer.drawTextRun(canvas, f, f2, i10, i9, line, defaultStyle, i19 - defaultStyle, z, i18, i2, i20, i21, i16);
                        } else {
                            i24 = i17;
                            i13 = i19;
                            i25 = charWidth;
                            i26 = length;
                            i14 = i15;
                            z3 = z2;
                            styleRow = lineColor;
                            cArr2 = line;
                            defaultStyle = i6;
                            i6 = i5;
                        }
                        i7 = i2;
                        i10 = i6;
                        i18 = defaultStyle;
                        z = z3;
                        defaultStyle = i13;
                        i9 = 0;
                        obj2 = null;
                    }
                    if (i7 == i6) {
                        charWidth = i25;
                        if (charWidth > 0) {
                            i16 = charWidth;
                            i21 = i23;
                            i20 = i13;
                        } else {
                            i21 += i23;
                        }
                    } else {
                        charWidth = i25;
                    }
                    i9 += charWidth;
                    i22 += charWidth;
                    i19 = i13 + i23;
                    if (charWidth > 1) {
                        obj2 = 1;
                    }
                    i8 = i4;
                    i12 = 1;
                    i17 = i6;
                    lineColor = styleRow;
                    length = i26;
                    line = cArr2;
                    i5 = i24;
                    obj = obj2;
                    i6 = i7;
                    i15 = i14;
                    i7 = i3;
                }
                int i29 = i18;
                boolean z4 = z;
                i13 = i19;
                i14 = i15;
                cArr2 = line;
                i7 = i6;
                if (i10 >= 0) {
                    i6 = i7;
                    textRenderer.drawTextRun(canvas, f, f2, i10, i9, cArr2, defaultStyle, i13 - defaultStyle, z4, i29, i7, i20, i21, i16);
                } else {
                    i6 = i7;
                }
                if (i6 >= 0 && str.length() > 0) {
                    i5 = i14;
                    i12 = Math.min(i5, str.length());
                    textRenderer.drawTextRun(canvas, f, f2, Math.min(i6, i5 - i12), i12, str.toCharArray(), str.length() - i12, i12, true, TextStyle.encode(15, 0, 0), -1, 0, 0, 0);
                }
            }
        } catch (IllegalArgumentException unused) {
        } catch (NullPointerException unused2) {
        }
    }

    public int getActiveRows() {
        return this.mData.getActiveRows();
    }

    public int getActiveTranscriptRows() {
        return this.mData.getActiveTranscriptRows();
    }

    public String getTranscriptText() {
        return internalGetTranscriptText(null, 0, -this.mData.getActiveTranscriptRows(), this.mColumns, this.mScreenRows);
    }

    public String getTranscriptText(GrowableIntArray growableIntArray) {
        return internalGetTranscriptText(growableIntArray, 0, -this.mData.getActiveTranscriptRows(), this.mColumns, this.mScreenRows);
    }

    public String getSelectedText(GrowableIntArray growableIntArray, int i, int i2, int i3, int i4) {
        return getSelectedText(growableIntArray, i, i2, i3, i4, true);
    }

    public String getSelectedText(GrowableIntArray growableIntArray, int i, int i2, int i3, int i4, boolean z) {
        return internalGetTranscriptText(growableIntArray, i, i2, i3, i4, z);
    }

    private String internalGetTranscriptText(GrowableIntArray growableIntArray, int i, int i2, int i3, int i4) {
        return internalGetTranscriptText(growableIntArray, i, i2, i3, i4, true);
    }

    /* JADX WARNING: Missing block: B:15:0x0038, code:
            if (r12 > r4) goto L_0x003a;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private String internalGetTranscriptText(GrowableIntArray growableIntArray, int i, int i2, int i3, int i4, boolean z) {
        TranscriptScreen transcriptScreen = this;
        GrowableIntArray growableIntArray2 = growableIntArray;
        StringBuilder stringBuilder = new StringBuilder();
        UnicodeTranscript unicodeTranscript = transcriptScreen.mData;
        int i5 = transcriptScreen.mColumns;
        int i6 = i2;
        int i7 = i6 < (-unicodeTranscript.getActiveTranscriptRows()) ? -unicodeTranscript.getActiveTranscriptRows() : i6;
        int i8 = i4;
        if (i8 >= transcriptScreen.mScreenRows) {
            i8 = transcriptScreen.mScreenRows - 1;
        }
        StyleRow styleRow = null;
        i6 = i7;
        while (i6 <= i8) {
            int i9;
            int i10;
            int i11 = 0;
            int i12 = i6 == i7 ? i : 0;
            if (i6 == i8) {
                i9 = i3 + 1;
            }
            i9 = i5;
            char[] line = unicodeTranscript.getLine(i6, i12, i9);
            if (growableIntArray2 != null) {
                styleRow = unicodeTranscript.getLineColor(i6, i12, i9);
            }
            if (line == null) {
                if (!unicodeTranscript.getLineWrap(i6) && i6 < i8 && i6 < transcriptScreen.mScreenRows - 1) {
                    stringBuilder.append(10);
                    if (growableIntArray2 != null) {
                        growableIntArray2.append(0);
                    }
                }
                i10 = i7;
            } else {
                int defaultStyle = transcriptScreen.mData.getDefaultStyle();
                int length = line.length;
                i10 = i7;
                i7 = 0;
                i12 = -1;
                while (i11 < length) {
                    char c = line[i11];
                    if (c == 0) {
                        break;
                    }
                    int i13;
                    int i14;
                    if (styleRow != null) {
                        try {
                            i13 = styleRow.get(i7);
                        } catch (ArrayIndexOutOfBoundsException unused) {
                            i14 = length;
                            length = defaultStyle;
                        }
                    } else {
                        i13 = defaultStyle;
                    }
                    i14 = length;
                    length = i13;
                    if (!(z && c == ' ' && r15 == defaultStyle)) {
                        i12 = i11;
                    }
                    if (!Character.isLowSurrogate(c)) {
                        i7 += UnicodeTranscript.charWidth(line, i11);
                    }
                    i11++;
                    length = i14;
                }
                if (unicodeTranscript.getLineWrap(i6) && r10 > -1 && i9 == i5) {
                    i12 = i11 - 1;
                }
                stringBuilder.append(line, 0, i12 + 1);
                if (growableIntArray2 != null) {
                    int i15;
                    if (styleRow != null) {
                        i15 = 0;
                        i7 = 0;
                        while (i15 <= i12) {
                            growableIntArray2.append(styleRow.get(i7));
                            i7 += UnicodeTranscript.charWidth(line, i15);
                            if (Character.isHighSurrogate(line[i15])) {
                                i15++;
                            }
                            i15++;
                        }
                    } else {
                        i15 = 0;
                        while (i15 <= i12) {
                            growableIntArray2.append(defaultStyle);
                            if (Character.isHighSurrogate(line[i15])) {
                                i15++;
                            }
                            i15++;
                        }
                    }
                }
                if (unicodeTranscript.getLineWrap(i6) || i6 >= i8) {
                    transcriptScreen = this;
                } else {
                    transcriptScreen = this;
                    if (i6 < transcriptScreen.mScreenRows - 1) {
                        stringBuilder.append(10);
                        if (growableIntArray2 != null) {
                            growableIntArray2.append(0);
                        }
                    }
                }
            }
            i6++;
            i7 = i10;
        }
        return stringBuilder.toString();
    }

    public boolean fastResize(int i, int i2, int[] iArr) {
        if (this.mData == null) {
            return true;
        }
        if (!this.mData.resize(i, i2, iArr)) {
            return false;
        }
        this.mColumns = i;
        this.mScreenRows = i2;
        return true;
    }

    public void resize(int i, int i2, int i3) {
        if (i2 > this.mTotalRows) {
            this.mTotalRows = i2;
        }
        init(i, this.mTotalRows, i2, i3);
    }

    char[] getScriptLine(int i) {
        try {
            return this.mData.getLine(i);
        } catch (IllegalArgumentException unused) {
            return null;
        } catch (NullPointerException unused2) {
            return null;
        }
    }

    boolean getScriptLineWrap(int i) {
        return this.mData.getLineWrap(i);
    }

    void setScriptLineWrap(int i, boolean z) {
        this.mData.setLineWrap(i, z);
    }

    boolean isBasicLine(int i) {
        return this.mData != null ? this.mData.isBasicLine(i) : true;
    }
}
