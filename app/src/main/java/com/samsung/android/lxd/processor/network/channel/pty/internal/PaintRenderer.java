package com.samsung.android.lxd.processor.network.channel.pty.internal;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Typeface;

class PaintRenderer extends BaseTextRenderer {
    private static final char[] EXAMPLE_CHAR = new char[]{'X'};
    private int mCharAscent;
    private int mCharDescent;
    private int mCharHeight;
    private float mCharWidth;
    private Paint mTextPaint = new Paint();

    public PaintRenderer(Typeface typeface, int i, ColorScheme colorScheme) {
        super(colorScheme);
        this.mTextPaint.setTypeface(typeface);
        this.mTextPaint.setAntiAlias(true);
        this.mTextPaint.setTextSize((float) i);
        this.mCharHeight = (int) Math.ceil((double) this.mTextPaint.getFontSpacing());
        this.mCharAscent = (int) Math.ceil((double) this.mTextPaint.ascent());
        this.mCharDescent = this.mCharHeight + this.mCharAscent;
        this.mCharWidth = this.mTextPaint.measureText(EXAMPLE_CHAR, 0, 1);
    }

    public void drawTextRun(Canvas canvas, float f, float f2, int i, int i2, char[] cArr, int i3, int i4, boolean z, int i5, int i6, int i7, int i8, int i9) {
        int i10;
        int i11;
        int i12;
        int i13 = i;
        int i14 = i2;
        int i15 = i6;
        int i16 = i9;
        int decodeForeColor = TextStyle.decodeForeColor(i5);
        int decodeBackColor = TextStyle.decodeBackColor(i5);
        int decodeEffect = TextStyle.decodeEffect(i5);
        if ((this.mReverseVideo ^ ((decodeEffect & 18) != 0 ? 1 : 0)) != 0) {
            i10 = decodeBackColor;
            decodeBackColor = decodeForeColor;
        } else {
            i10 = decodeForeColor;
        }
        if (z) {
            decodeBackColor = 259;
        }
        if (((decodeEffect & 8) != 0 ? 1 : null) != null && decodeBackColor < 8) {
            decodeBackColor += 8;
        }
        this.mTextPaint.setColor(this.mPalette[decodeBackColor]);
        float f3 = f + (((float) i13) * this.mCharWidth);
        int i17 = 8;
        decodeForeColor = i10;
        boolean z2 = true;
        canvas.drawRect(f3, (f2 + ((float) this.mCharAscent)) - ((float) this.mCharDescent), f3 + (((float) i14) * this.mCharWidth), f2, this.mTextPaint);
        boolean z3 = (i13 > i15 || i15 >= i13 + i14) ? false : z2;
        float f4 = 0.0f;
        if (z3) {
            float f5 = f + (((float) i15) * this.mCharWidth);
            i11 = decodeForeColor;
            i12 = i16;
            drawCursorImp(canvas, (float) ((int) f5), f2, ((float) i16) * this.mCharWidth, (float) this.mCharHeight);
            f4 = f5;
        } else {
            i11 = decodeForeColor;
            i12 = i16;
        }
        if (!((decodeEffect & 32) != 0 ? z2 : false)) {
            boolean z4;
            boolean z5 = (decodeEffect & 1) != 0 ? z2 : false;
            boolean z6 = (decodeEffect & 4) != 0 ? z2 : false;
            if (z5) {
                this.mTextPaint.setFakeBoldText(z2);
            }
            if (z6) {
                this.mTextPaint.setUnderlineText(z2);
            }
            if (i11 >= 8 || !z5) {
                i15 = this.mPalette[i11];
            } else {
                i15 = this.mPalette[i11 + 8];
            }
            this.mTextPaint.setColor(i15);
            float f6 = f2 - ((float) this.mCharDescent);
            if (z3) {
                int i18 = i7 - i3;
                decodeEffect = i4 - (i18 + i8);
                if (i18 > 0) {
                    canvas.drawText(cArr, i3, i18, f3, f6, this.mTextPaint);
                }
                this.mTextPaint.setColor(this.mPalette[258]);
                i16 = i12;
                canvas.drawText(cArr, i7, i8, f4, f6, this.mTextPaint);
                if (decodeEffect > 0) {
                    this.mTextPaint.setColor(i15);
                    canvas.drawText(cArr, i7 + i8, decodeEffect, f4 + (((float) i16) * this.mCharWidth), f6, this.mTextPaint);
                }
            } else {
                canvas.drawText(cArr, i3, i4, f3, f6, this.mTextPaint);
            }
            if (z5) {
                z4 = false;
                this.mTextPaint.setFakeBoldText(false);
            } else {
                z4 = false;
            }
            if (z6) {
                this.mTextPaint.setUnderlineText(z4);
            }
        }
    }

    public int getCharacterHeight() {
        return this.mCharHeight;
    }

    public float getCharacterWidth() {
        return this.mCharWidth;
    }

    public int getTopMargin() {
        return this.mCharDescent;
    }
}
