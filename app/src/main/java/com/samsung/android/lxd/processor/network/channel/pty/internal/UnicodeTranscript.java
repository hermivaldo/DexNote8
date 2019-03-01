package com.samsung.android.lxd.processor.network.channel.pty.internal;

import android.os.Build.VERSION;
import com.samsung.android.lxd.processor.utils.log.Log;

class UnicodeTranscript {
    static final int HANGUL_CONJOINING_MIN_SDK = 16;
    private static final String TAG = "UnicodeTranscript";
    private int mActiveTranscriptRows = 0;
    private StyleRow[] mColor;
    private int mColumns;
    private int mDefaultStyle = 0;
    private boolean[] mLineWrap;
    private Object[] mLines;
    private int mScreenFirstRow = 0;
    private int mScreenRows;
    private int mTotalRows;
    private StyleRow tmpColor;
    private char[] tmpLine;

    public UnicodeTranscript(int i, int i2, int i3, int i4) {
        this.mColumns = i;
        this.mTotalRows = i2;
        this.mScreenRows = i3;
        this.mLines = new Object[i2];
        this.mColor = new StyleRow[i2];
        this.mLineWrap = new boolean[i2];
        this.tmpColor = new StyleRow(i4, this.mColumns);
        this.mDefaultStyle = i4;
    }

    public void setDefaultStyle(int i) {
        this.mDefaultStyle = i;
    }

    public int getDefaultStyle() {
        return this.mDefaultStyle;
    }

    public int getActiveTranscriptRows() {
        return this.mActiveTranscriptRows;
    }

    public int getActiveRows() {
        return this.mActiveTranscriptRows + this.mScreenRows;
    }

    private int externalToInternalRow(int i) {
        if (i < (-this.mActiveTranscriptRows) || i > this.mScreenRows) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("externalToInternalRow ");
            stringBuilder.append(i);
            stringBuilder.append(" ");
            stringBuilder.append(this.mScreenRows);
            stringBuilder.append(" ");
            stringBuilder.append(this.mActiveTranscriptRows);
            String stringBuilder2 = stringBuilder.toString();
            Log.e(TAG, stringBuilder2);
            throw new IllegalArgumentException(stringBuilder2);
        } else if (i >= 0) {
            return (this.mScreenFirstRow + i) % this.mTotalRows;
        } else {
            if ((-i) > this.mScreenFirstRow) {
                return (this.mTotalRows + this.mScreenFirstRow) + i;
            }
            return this.mScreenFirstRow + i;
        }
    }

    public void setLineWrap(int i) {
        setLineWrap(i, true);
    }

    void setLineWrap(int i, boolean z) {
        this.mLineWrap[externalToInternalRow(i)] = z;
    }

    public boolean getLineWrap(int i) {
        return this.mLineWrap[externalToInternalRow(i)];
    }

    public boolean resize(int i, int i2, int[] iArr) {
        if (i != this.mColumns || i2 > this.mTotalRows) {
            return false;
        }
        i = this.mScreenRows;
        int i3 = i - i2;
        if (i3 < (-this.mActiveTranscriptRows)) {
            return false;
        }
        if (i3 > 0 && iArr != null) {
            i--;
            if (iArr[1] != i) {
                Object[] objArr = this.mLines;
                while (i > iArr[1]) {
                    int externalToInternalRow = externalToInternalRow(i);
                    if (objArr[externalToInternalRow] != null) {
                        char[] cArr;
                        if (objArr[externalToInternalRow] instanceof char[]) {
                            cArr = (char[]) objArr[externalToInternalRow];
                        } else {
                            cArr = ((FullUnicodeLine) objArr[externalToInternalRow]).getLine();
                        }
                        int length = cArr.length;
                        int i4 = 0;
                        while (i4 < length) {
                            if (cArr[i4] == 0) {
                                i4 = length;
                                break;
                            } else if (cArr[i4] != ' ') {
                                break;
                            } else {
                                i4++;
                            }
                        }
                        if (i4 != length) {
                            break;
                        }
                        i3--;
                        if (i3 == 0) {
                            break;
                        }
                    } else {
                        i3--;
                        if (i3 == 0) {
                            break;
                        }
                    }
                    i--;
                }
            }
        }
        if (i3 > 0 || (i3 < 0 && this.mScreenFirstRow >= (-i3))) {
            this.mScreenFirstRow = (this.mScreenFirstRow + i3) % this.mTotalRows;
        } else if (i3 < 0) {
            this.mScreenFirstRow = (this.mTotalRows + this.mScreenFirstRow) + i3;
        }
        if (this.mActiveTranscriptRows + i3 < 0) {
            this.mActiveTranscriptRows = 0;
        } else {
            this.mActiveTranscriptRows += i3;
        }
        if (iArr != null) {
            iArr[1] = iArr[1] - i3;
        }
        this.mScreenRows = i2;
        return true;
    }

    private void blockCopyLines(int i, int i2, int i3) {
        int i4 = this.mTotalRows;
        int i5 = i + i3;
        i5 = i5 >= 0 ? i5 % i4 : (i4 + i) + i3;
        if (i + i2 > i4 || i5 + i2 > i4) {
            if (i3 < 0) {
                for (i3 = 0; i3 < i2; i3++) {
                    int i6 = (i5 + i3) % i4;
                    int i7 = (i + i3) % i4;
                    this.mLines[i6] = this.mLines[i7];
                    this.mColor[i6] = this.mColor[i7];
                    this.mLineWrap[i6] = this.mLineWrap[i7];
                }
            } else {
                for (i2--; i2 >= 0; i2--) {
                    int i8 = (i5 + i2) % i4;
                    int i9 = (i + i2) % i4;
                    this.mLines[i8] = this.mLines[i9];
                    this.mColor[i8] = this.mColor[i9];
                    this.mLineWrap[i8] = this.mLineWrap[i9];
                }
            }
            return;
        }
        System.arraycopy(this.mLines, i, this.mLines, i5, i2);
        System.arraycopy(this.mColor, i, this.mColor, i5, i2);
        System.arraycopy(this.mLineWrap, i, this.mLineWrap, i5, i2);
    }

    public void scroll(int i, int i2, int i3) {
        int i4 = i;
        int i5 = i2;
        int i6 = i3;
        int i7 = i5 - 1;
        if (i4 > i7) {
            throw new IllegalArgumentException();
        } else if (i4 < 0) {
            throw new IllegalArgumentException();
        } else if (i5 > this.mScreenRows) {
            throw new IllegalArgumentException();
        } else {
            int i8 = this.mScreenRows;
            int i9 = this.mTotalRows;
            if (i4 == 0 && i5 == i8) {
                this.mScreenFirstRow = (this.mScreenFirstRow + 1) % i9;
                if (this.mActiveTranscriptRows < i9 - i8) {
                    this.mActiveTranscriptRows++;
                }
                i4 = externalToInternalRow(i7);
                this.mLines[i4] = null;
                this.mColor[i4] = new StyleRow(i6, this.mColumns);
                this.mLineWrap[i4] = false;
                return;
            }
            int i10 = this.mScreenFirstRow;
            int externalToInternalRow = externalToInternalRow(i);
            int externalToInternalRow2 = externalToInternalRow(i5);
            Object[] objArr = this.mLines;
            StyleRow[] styleRowArr = this.mColor;
            boolean[] zArr = this.mLineWrap;
            Object obj = objArr[externalToInternalRow];
            StyleRow styleRow = styleRowArr[externalToInternalRow];
            boolean z = zArr[externalToInternalRow];
            blockCopyLines(i10, i4, 1);
            blockCopyLines(externalToInternalRow2, i8 - i5, 1);
            objArr[i10] = obj;
            styleRowArr[i10] = styleRow;
            zArr[i10] = z;
            this.mScreenFirstRow = (i10 + 1) % i9;
            if (this.mActiveTranscriptRows < i9 - i8) {
                this.mActiveTranscriptRows++;
            }
            i4 = externalToInternalRow(i7);
            objArr[i4] = null;
            styleRowArr[i4] = new StyleRow(i6, this.mColumns);
            zArr[i4] = false;
        }
    }

    public void blockCopy(int i, int i2, int i3, int i4, int i5, int i6) {
        int i7 = i;
        int i8 = i2;
        int i9 = i3;
        int i10 = i4;
        int i11 = i5;
        int i12 = i6;
        if (i7 >= 0) {
            int i13 = i7 + i9;
            if (i13 <= this.mColumns && i8 >= 0 && i8 + i10 <= this.mScreenRows && i11 >= 0 && i11 + i9 <= this.mColumns && i12 >= 0 && i12 + i10 <= this.mScreenRows) {
                Object[] objArr = this.mLines;
                StyleRow[] styleRowArr = this.mColor;
                int i14;
                int i15;
                int externalToInternalRow;
                int i16;
                int externalToInternalRow2;
                StyleRow[] styleRowArr2;
                int i17;
                Object[] objArr2;
                int i18;
                int i19;
                int i20;
                if (i8 > i12) {
                    i14 = 0;
                    while (i14 < i10) {
                        int i21;
                        i15 = i8 + i14;
                        externalToInternalRow = externalToInternalRow(i15);
                        i16 = i12 + i14;
                        externalToInternalRow2 = externalToInternalRow(i16);
                        if ((objArr[externalToInternalRow] instanceof char[]) && (objArr[externalToInternalRow2] instanceof char[])) {
                            System.arraycopy(objArr[externalToInternalRow], i7, objArr[externalToInternalRow2], i11, i9);
                            i21 = i14;
                            styleRowArr2 = styleRowArr;
                            i17 = i13;
                            objArr2 = objArr;
                        } else {
                            char[] line = getLine(i15, i7, i13, true);
                            if (line == null) {
                                i21 = i14;
                                styleRowArr2 = styleRowArr;
                                blockSet(i11, i16, i9, 1, 32, this.mDefaultStyle);
                                i17 = i13;
                                objArr2 = objArr;
                                i14 = i21 + 1;
                                styleRowArr = styleRowArr2;
                                i13 = i17;
                                objArr = objArr2;
                            } else {
                                i21 = i14;
                                styleRowArr2 = styleRowArr;
                                i14 = this.mColumns;
                                i17 = i13;
                                objArr2 = objArr;
                                i18 = 0;
                                i19 = i18;
                                char c = i19;
                                while (i18 < line.length && line[i18] != 0) {
                                    i20 = i11 + i19;
                                    if (i20 >= i14) {
                                        break;
                                    }
                                    int i22 = i14;
                                    if (Character.isHighSurrogate(line[i18])) {
                                        c = line[i18];
                                    } else if (Character.isLowSurrogate(line[i18])) {
                                        i14 = Character.toCodePoint(c, line[i18]);
                                        setChar(i20, i16, i14);
                                        i19 += charWidth(i14);
                                    } else {
                                        setChar(i20, i16, line[i18]);
                                        i19 += charWidth(line[i18]);
                                    }
                                    i18++;
                                    i14 = i22;
                                }
                            }
                        }
                        styleRowArr2[externalToInternalRow].copy(i7, styleRowArr2[externalToInternalRow2], i11, i9);
                        i14 = i21 + 1;
                        styleRowArr = styleRowArr2;
                        i13 = i17;
                        objArr = objArr2;
                    }
                    return;
                }
                styleRowArr2 = styleRowArr;
                i17 = i13;
                objArr2 = objArr;
                i15 = 0;
                while (i15 < i10) {
                    i13 = i15 + 1;
                    i15 = i10 - i13;
                    externalToInternalRow = i8 + i15;
                    i16 = externalToInternalRow(externalToInternalRow);
                    externalToInternalRow2 = i12 + i15;
                    i15 = externalToInternalRow(externalToInternalRow2);
                    if ((objArr2[i16] instanceof char[]) && (objArr2[i15] instanceof char[])) {
                        System.arraycopy(objArr2[i16], i7, objArr2[i15], i11, i9);
                        i20 = i17;
                    } else {
                        i20 = i17;
                        char[] line2 = getLine(externalToInternalRow, i7, i20, true);
                        boolean z;
                        if (line2 == null) {
                            z = true;
                            blockSet(i11, externalToInternalRow2, i9, 1, 32, this.mDefaultStyle);
                            i15 = i13;
                            i17 = i20;
                            i8 = i2;
                            i10 = i4;
                        } else {
                            z = true;
                            i14 = this.mColumns;
                            i18 = 0;
                            i19 = i18;
                            char c2 = i19;
                            while (i18 < line2.length && line2[i18] != 0) {
                                i10 = i11 + i19;
                                if (i10 >= i14) {
                                    break;
                                }
                                int i23 = i14;
                                if (Character.isHighSurrogate(line2[i18])) {
                                    c2 = line2[i18];
                                } else if (Character.isLowSurrogate(line2[i18])) {
                                    i14 = Character.toCodePoint(c2, line2[i18]);
                                    setChar(i10, externalToInternalRow2, i14);
                                    i19 += charWidth(i14);
                                } else {
                                    setChar(i10, externalToInternalRow2, line2[i18]);
                                    i19 += charWidth(line2[i18]);
                                }
                                i18++;
                                i14 = i23;
                            }
                        }
                    }
                    styleRowArr2[i16].copy(i7, styleRowArr2[i15], i11, i9);
                    i15 = i13;
                    i17 = i20;
                    i8 = i2;
                    i10 = i4;
                }
                return;
            }
        }
        throw new IllegalArgumentException();
    }

    public void blockSet(int i, int i2, int i3, int i4, int i5, int i6) {
        if (i < 0 || i + i3 > this.mColumns || i2 < 0 || i2 + i4 > this.mScreenRows) {
            String str = TAG;
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("illegal arguments! ");
            stringBuilder.append(i);
            stringBuilder.append(" ");
            stringBuilder.append(i2);
            stringBuilder.append(" ");
            stringBuilder.append(i3);
            stringBuilder.append(" ");
            stringBuilder.append(i4);
            stringBuilder.append(" ");
            stringBuilder.append(i5);
            stringBuilder.append(" ");
            stringBuilder.append(this.mColumns);
            stringBuilder.append(" ");
            stringBuilder.append(this.mScreenRows);
            Log.e(str, stringBuilder.toString());
            throw new IllegalArgumentException();
        }
        for (int i7 = 0; i7 < i4; i7++) {
            for (int i8 = 0; i8 < i3; i8++) {
                setChar(i + i8, i2 + i7, i5, i6);
            }
        }
    }

    public static int charWidth(int i) {
        if ((i > 31 && i < KeycodeConstants.KEYCODE_MEDIA_PAUSE) || i == 27) {
            return 1;
        }
        switch (Character.getType(i)) {
            case 6:
            case 7:
            case 15:
            case 16:
                return 0;
            default:
                if ((i >= 4448 && i <= 4607) || (i >= 55216 && i <= 55295)) {
                    return VERSION.SDK_INT >= 16 ? 0 : 2;
                } else {
                    if (Character.charCount(i) == 1) {
                        i = AndroidCharacterCompat.getEastAsianWidth((char) i);
                        if (i == 3 || i == 5) {
                            return 2;
                        }
                    }
                    switch ((i >> 16) & 15) {
                        case 2:
                        case 3:
                            return 2;
                    }
                    return 1;
                }
        }
    }

    public static int charWidth(char c, char c2) {
        return charWidth(Character.toCodePoint(c, c2));
    }

    public static int charWidth(char[] cArr, int i) {
        char c = cArr[i];
        if (Character.isHighSurrogate(c)) {
            return charWidth(c, cArr[i + 1]);
        }
        return charWidth(c);
    }

    public char[] getLine(int i, int i2, int i3) {
        return getLine(i, i2, i3, false);
    }

    public char[] getLine(int i) {
        return getLine(i, 0, this.mColumns, true);
    }

    private char[] getLine(int i, int i2, int i3, boolean z) {
        if (i < (-this.mActiveTranscriptRows) || i > this.mScreenRows - 1) {
            throw new IllegalArgumentException();
        }
        int i4 = this.mColumns;
        i = externalToInternalRow(i);
        if (this.mLines[i] == null) {
            return null;
        }
        if (!(this.mLines[i] instanceof char[])) {
            FullUnicodeLine fullUnicodeLine = (FullUnicodeLine) this.mLines[i];
            Object line = fullUnicodeLine.getLine();
            if (i2 == 0 && i3 == i4) {
                int spaceUsed = fullUnicodeLine.getSpaceUsed();
                if (spaceUsed < line.length) {
                    line[spaceUsed] = null;
                }
                return line;
            }
            int findStartOfColumn;
            i2 = fullUnicodeLine.findStartOfColumn(i2);
            if (i3 < i4) {
                findStartOfColumn = fullUnicodeLine.findStartOfColumn(i3);
                if (!z && i3 > 0 && i3 < i4 - 1 && findStartOfColumn == fullUnicodeLine.findStartOfColumn(i3 - 1)) {
                    findStartOfColumn = fullUnicodeLine.findStartOfColumn(i3 + 1);
                }
            } else {
                findStartOfColumn = fullUnicodeLine.getSpaceUsed();
            }
            findStartOfColumn -= i2;
            if (this.tmpLine == null || this.tmpLine.length < findStartOfColumn + 1) {
                this.tmpLine = new char[(findStartOfColumn + 1)];
            }
            System.arraycopy(line, i2, this.tmpLine, 0, findStartOfColumn);
            this.tmpLine[findStartOfColumn] = 0;
            return this.tmpLine;
        } else if (i2 == 0 && i3 == i4) {
            return (char[]) this.mLines[i];
        } else {
            if (this.tmpLine == null || this.tmpLine.length < i4 + 1) {
                this.tmpLine = new char[(i4 + 1)];
            }
            i3 -= i2;
            System.arraycopy(this.mLines[i], i2, this.tmpLine, 0, i3);
            this.tmpLine[i3] = 0;
            return this.tmpLine;
        }
    }

    public StyleRow getLineColor(int i, int i2, int i3) {
        return getLineColor(i, i2, i3, false);
    }

    public StyleRow getLineColor(int i) {
        return getLineColor(i, 0, this.mColumns, true);
    }

    private StyleRow getLineColor(int i, int i2, int i3, boolean z) {
        if (i < (-this.mActiveTranscriptRows) || i > this.mScreenRows - 1) {
            throw new IllegalArgumentException();
        }
        i = externalToInternalRow(i);
        StyleRow styleRow = this.mColor[i];
        StyleRow styleRow2 = this.tmpColor;
        if (styleRow == null) {
            return null;
        }
        int i4 = this.mColumns;
        if (!(z || this.mLines[i] == null || !(this.mLines[i] instanceof FullUnicodeLine))) {
            FullUnicodeLine fullUnicodeLine = (FullUnicodeLine) this.mLines[i];
            if (i2 > 0 && fullUnicodeLine.findStartOfColumn(i2 - 1) == fullUnicodeLine.findStartOfColumn(i2)) {
                i2--;
            }
            if (i3 < i4 - 1) {
                i = i3 + 1;
                if (fullUnicodeLine.findStartOfColumn(i) == fullUnicodeLine.findStartOfColumn(i3)) {
                    i3 = i;
                }
            }
        }
        if (i2 == 0 && i3 == i4) {
            return styleRow;
        }
        styleRow.copy(i2, styleRow2, 0, i3 - i2);
        return styleRow2;
    }

    boolean isBasicLine(int i) {
        if (i >= (-this.mActiveTranscriptRows) && i <= this.mScreenRows - 1) {
            return this.mLines[externalToInternalRow(i)] instanceof char[];
        }
        throw new IllegalArgumentException();
    }

    public boolean getChar(int i, int i2) {
        return getChar(i, i2, 0);
    }

    public boolean getChar(int i, int i2, int i3) {
        return getChar(i, i2, i3, new char[1], 0);
    }

    public boolean getChar(int i, int i2, int i3, char[] cArr, int i4) {
        if (i < (-this.mActiveTranscriptRows) || i > this.mScreenRows - 1) {
            throw new IllegalArgumentException();
        }
        i = externalToInternalRow(i);
        if (!(this.mLines[i] instanceof char[])) {
            return ((FullUnicodeLine) this.mLines[i]).getChar(i2, i3, cArr, i4);
        }
        cArr[i4] = ((char[]) this.mLines[i])[i2];
        return false;
    }

    private boolean isBasicChar(int i) {
        return charWidth(i) == 1 && Character.charCount(i) == 1;
    }

    private char[] allocateBasicLine(int i, int i2) {
        char[] cArr = new char[i2];
        for (int i3 = 0; i3 < i2; i3++) {
            cArr[i3] = ' ';
        }
        this.mLines[i] = cArr;
        if (this.mColor[i] == null) {
            this.mColor[i] = new StyleRow(0, i2);
        }
        return cArr;
    }

    private FullUnicodeLine allocateFullLine(int i, int i2) {
        FullUnicodeLine fullUnicodeLine = new FullUnicodeLine(i2);
        this.mLines[i] = fullUnicodeLine;
        if (this.mColor[i] == null) {
            this.mColor[i] = new StyleRow(0, i2);
        }
        return fullUnicodeLine;
    }

    public boolean setChar(int i, int i2, int i3, int i4) {
        if (!setChar(i, i2, i3)) {
            return false;
        }
        this.mColor[externalToInternalRow(i2)].set(i, i4);
        return true;
    }

    public boolean setChar(int i, int i2, int i3) {
        if (i2 >= this.mScreenRows || i >= this.mColumns) {
            String str = TAG;
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("illegal arguments! ");
            stringBuilder.append(i2);
            stringBuilder.append(" ");
            stringBuilder.append(i);
            stringBuilder.append(" ");
            stringBuilder.append(this.mScreenRows);
            stringBuilder.append(" ");
            stringBuilder.append(this.mColumns);
            Log.e(str, stringBuilder.toString());
            throw new IllegalArgumentException();
        }
        boolean z;
        i2 = externalToInternalRow(i2);
        if (this.mLines[i2] != null) {
            z = true;
        } else if (isBasicChar(i3)) {
            allocateBasicLine(i2, this.mColumns);
            z = true;
        } else {
            allocateFullLine(i2, this.mColumns);
            z = false;
        }
        if (this.mLines[i2] instanceof char[]) {
            char[] cArr = (char[]) this.mLines[i2];
            if (z) {
                z = isBasicChar(i3);
            }
            if (z) {
                cArr[i] = (char) i3;
                return true;
            }
            this.mLines[i2] = new FullUnicodeLine(cArr);
        }
        ((FullUnicodeLine) this.mLines[i2]).setChar(i, i3);
        return true;
    }
}
