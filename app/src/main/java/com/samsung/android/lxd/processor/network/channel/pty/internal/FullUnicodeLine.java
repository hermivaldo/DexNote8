package com.samsung.android.lxd.processor.network.channel.pty.internal;

/* compiled from: UnicodeTranscript */
class FullUnicodeLine {
    private static final float SPARE_CAPACITY_FACTOR = 1.5f;
    private int mColumns;
    private short[] mOffset;
    private char[] mText;

    public FullUnicodeLine(int i) {
        commonConstructor(i);
        char[] cArr = this.mText;
        for (int i2 = 0; i2 < i; i2++) {
            cArr[i2] = ' ';
        }
        this.mOffset[0] = (short) i;
    }

    public FullUnicodeLine(char[] cArr) {
        commonConstructor(cArr.length);
        System.arraycopy(cArr, 0, this.mText, 0, this.mColumns);
        this.mOffset[0] = (short) cArr.length;
    }

    private void commonConstructor(int i) {
        this.mColumns = i;
        this.mOffset = new short[i];
        this.mText = new char[((int) (((float) i) * SPARE_CAPACITY_FACTOR))];
    }

    public int getSpaceUsed() {
        return this.mOffset[0];
    }

    public char[] getLine() {
        return this.mText;
    }

    public int findStartOfColumn(int i) {
        return i == 0 ? 0 : i + this.mOffset[i];
    }

    public boolean getChar(int i, int i2, char[] cArr, int i3) {
        int findStartOfColumn = findStartOfColumn(i);
        i++;
        if (i < this.mColumns) {
            i = findStartOfColumn(i) - findStartOfColumn;
        } else {
            i = getSpaceUsed() - findStartOfColumn;
        }
        if (i2 >= i) {
            throw new IllegalArgumentException();
        }
        cArr[i3] = this.mText[findStartOfColumn + i2];
        if (i2 + 1 < i) {
            return true;
        }
        return false;
    }

    /* JADX WARNING: Removed duplicated region for block: B:101:? A:{SYNTHETIC, RETURN, ORIG_RETURN} */
    /* JADX WARNING: Removed duplicated region for block: B:95:0x0187  */
    /* JADX WARNING: Removed duplicated region for block: B:25:0x005a  */
    /* JADX WARNING: Removed duplicated region for block: B:28:0x0060  */
    /* JADX WARNING: Removed duplicated region for block: B:34:0x008b  */
    /* JADX WARNING: Removed duplicated region for block: B:33:0x0087  */
    /* JADX WARNING: Removed duplicated region for block: B:36:0x0092  */
    /* JADX WARNING: Removed duplicated region for block: B:38:0x009e  */
    /* JADX WARNING: Removed duplicated region for block: B:53:0x00c4  */
    /* JADX WARNING: Removed duplicated region for block: B:47:0x00b5  */
    /* JADX WARNING: Removed duplicated region for block: B:56:0x00d2  */
    /* JADX WARNING: Removed duplicated region for block: B:55:0x00c7  */
    /* JADX WARNING: Removed duplicated region for block: B:58:0x00de  */
    /* JADX WARNING: Removed duplicated region for block: B:62:0x00f4  */
    /* JADX WARNING: Removed duplicated region for block: B:61:0x00eb  */
    /* JADX WARNING: Removed duplicated region for block: B:73:0x0115  */
    /* JADX WARNING: Removed duplicated region for block: B:71:0x0111  */
    /* JADX WARNING: Removed duplicated region for block: B:95:0x0187  */
    /* JADX WARNING: Removed duplicated region for block: B:101:? A:{SYNTHETIC, RETURN, ORIG_RETURN} */
    /* JADX WARNING: Missing block: B:72:0x0112, code:
            if (r13 != 2) goto L_0x0116;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void setChar(int i, int i2) {
        int i3 = i;
        int i4 = this.mColumns;
        if (i3 < 0 || i3 >= i4) {
            throw new IllegalArgumentException();
        }
        int i5;
        int i6;
        int charCount;
        int i7;
        int i8;
        Object obj;
        int i9;
        char[] cArr = this.mText;
        short[] sArr = this.mOffset;
        int i10 = sArr[0];
        int findStartOfColumn = findStartOfColumn(i);
        int charWidth = UnicodeTranscript.charWidth(i2);
        int charWidth2 = UnicodeTranscript.charWidth(cArr, findStartOfColumn);
        if (charWidth == 2 && i3 == i4 - 1) {
            charWidth = 32;
            i5 = 1;
        } else {
            i5 = charWidth;
            charWidth = i2;
        }
        int i11 = (charWidth2 == 2 && i3 > 0 && findStartOfColumn(i3 - 1) == findStartOfColumn) ? 1 : 0;
        if (i11 != 0) {
            i6 = i3 + 1;
            if (i6 < i4) {
                i6 = findStartOfColumn(i6) - findStartOfColumn;
                charCount = Character.charCount(charWidth);
                if (i5 == 0) {
                    charCount += i6;
                }
                i7 = charCount - i6;
                if (i7 > 0) {
                    if (i10 + i7 > cArr.length) {
                        Object obj2 = new char[(cArr.length + i4)];
                        System.arraycopy(cArr, 0, obj2, 0, findStartOfColumn);
                        System.arraycopy(cArr, findStartOfColumn + i6, obj2, findStartOfColumn + charCount, (i10 - findStartOfColumn) - i6);
                        this.mText = obj2;
                        cArr = obj2;
                    } else {
                        System.arraycopy(cArr, findStartOfColumn + i6, cArr, findStartOfColumn + charCount, (i10 - findStartOfColumn) - i6);
                    }
                }
                if (i5 <= 0) {
                    Character.toChars(charWidth, cArr, findStartOfColumn);
                } else {
                    Character.toChars(charWidth, cArr, findStartOfColumn + i6);
                }
                if (i7 < 0) {
                    System.arraycopy(cArr, findStartOfColumn + i6, cArr, findStartOfColumn + charCount, (i10 - findStartOfColumn) - i6);
                }
                if (i7 != 0) {
                    i10 += i7;
                    sArr[0] = (short) i10;
                }
                if ((charWidth2 == 2 && i5 == 1) || (i11 != 0 && i5 == 2)) {
                    i8 = findStartOfColumn + charCount;
                    if (i10 + 1 <= cArr.length) {
                        obj = new char[(cArr.length + i4)];
                        System.arraycopy(cArr, 0, obj, 0, i11 != 0 ? findStartOfColumn : i8);
                    } else {
                        obj = cArr;
                    }
                    if (i11 == 0) {
                        System.arraycopy(cArr, findStartOfColumn, obj, findStartOfColumn + 1, i10 - findStartOfColumn);
                        obj[findStartOfColumn] = ' ';
                    } else {
                        System.arraycopy(cArr, i8, obj, i8 + 1, i10 - i8);
                        obj[i8] = ' ';
                    }
                    if (obj != cArr) {
                        this.mText = obj;
                        cArr = obj;
                    }
                    i10 = (short) (sArr[0] + 1);
                    sArr[0] = i10;
                    if (i11 == 0) {
                        sArr[i3] = (short) (sArr[i3] + 1);
                        findStartOfColumn++;
                    } else {
                        if (i3 == 0) {
                            sArr[1] = (short) (charCount - 1);
                        } else {
                            i8 = i3 + 1;
                            if (i8 < i4) {
                                sArr[i8] = (short) ((sArr[i3] + charCount) - 1);
                            }
                        }
                        i3++;
                    }
                    i7++;
                }
                i8 = i7;
                charWidth = charWidth2 != 1 ? 2 : 2;
                if (i11 == 0 || i5 != charWidth) {
                    i9 = 1;
                    if (i8 != 0) {
                        for (i3 += i9; i3 < i4; i3++) {
                            sArr[i3] = (short) (sArr[i3] + i8);
                        }
                        return;
                    }
                    return;
                }
                if (i3 == i4 - 2) {
                    sArr[i3 + 1] = (short) (sArr[i3] - 1);
                    sArr[0] = (short) (findStartOfColumn + charCount);
                    i9 = 1;
                    i8 = 0;
                } else {
                    findStartOfColumn += charCount;
                    charWidth2 = UnicodeTranscript.charWidth(cArr, findStartOfColumn);
                    int i12 = (i3 + charWidth2) + 1;
                    int findStartOfColumn2 = i12 < i4 ? (findStartOfColumn(i12) + i8) - findStartOfColumn : i10 - findStartOfColumn;
                    if (charWidth2 == 2) {
                        cArr[findStartOfColumn] = ' ';
                        if (findStartOfColumn2 > 1) {
                            System.arraycopy(cArr, findStartOfColumn + findStartOfColumn2, cArr, findStartOfColumn + 1, (i10 - findStartOfColumn) - findStartOfColumn2);
                            findStartOfColumn2--;
                            i8 -= findStartOfColumn2;
                            sArr[0] = (short) (sArr[0] - findStartOfColumn2);
                        }
                    } else {
                        System.arraycopy(cArr, findStartOfColumn + findStartOfColumn2, cArr, findStartOfColumn, (i10 - findStartOfColumn) - findStartOfColumn2);
                        i8 -= findStartOfColumn2;
                        sArr[0] = (short) (sArr[0] - findStartOfColumn2);
                    }
                    if (i3 == 0) {
                        i9 = 1;
                        sArr[1] = (short) -1;
                    } else {
                        i9 = 1;
                        sArr[i3 + 1] = (short) (sArr[i3] - 1);
                    }
                    i3++;
                }
                if (i8 != 0) {
                }
            }
        }
        i6 = i3 + charWidth2;
        i6 = i6 < i4 ? findStartOfColumn(i6) - findStartOfColumn : i10 - findStartOfColumn;
        charCount = Character.charCount(charWidth);
        if (i5 == 0) {
        }
        i7 = charCount - i6;
        if (i7 > 0) {
        }
        if (i5 <= 0) {
        }
        if (i7 < 0) {
        }
        if (i7 != 0) {
        }
        i8 = findStartOfColumn + charCount;
        if (i10 + 1 <= cArr.length) {
        }
        if (i11 == 0) {
        }
        if (obj != cArr) {
        }
        i10 = (short) (sArr[0] + 1);
        sArr[0] = i10;
        if (i11 == 0) {
        }
        i7++;
        i8 = i7;
        if (charWidth2 != 1) {
        }
        i9 = 1;
        if (i8 != 0) {
        }
    }
}
