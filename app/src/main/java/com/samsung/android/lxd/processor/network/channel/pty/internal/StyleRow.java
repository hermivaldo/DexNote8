package com.samsung.android.lxd.processor.network.channel.pty.internal;

final class StyleRow {
    private int mColumns;
    private byte[] mData;
    private int mStyle;

    StyleRow(int i, int i2) {
        this.mStyle = i;
        this.mColumns = i2;
    }

    void set(int i, int i2) {
        if (i2 != this.mStyle || this.mData != null) {
            ensureData();
            setStyle(i, i2);
        }
    }

    int get(int i) {
        if (this.mData == null) {
            return this.mStyle;
        }
        return getStyle(i);
    }

    boolean isSolidStyle() {
        return this.mData == null;
    }

    int getSolidStyle() {
        if (this.mData == null) {
            return this.mStyle;
        }
        throw new IllegalArgumentException("Not a solid style");
    }

    void copy(int i, StyleRow styleRow, int i2, int i3) {
        if (this.mData == null && styleRow.mData == null && i == 0 && i2 == 0 && i3 == this.mColumns) {
            styleRow.mStyle = this.mStyle;
            return;
        }
        ensureData();
        styleRow.ensureData();
        System.arraycopy(this.mData, i * 3, styleRow.mData, i2 * 3, i3 * 3);
    }

    void ensureData() {
        if (this.mData == null) {
            allocate();
        }
    }

    private void allocate() {
        this.mData = new byte[(((this.mColumns + 1) * 3) + 1)];
        for (int i = 0; i < this.mColumns; i++) {
            setStyle(i, this.mStyle);
        }
    }

    private int getStyle(int i) {
        i *= 3;
        byte[] bArr = this.mData;
        return ((bArr[i + 2] & 255) << 16) | ((bArr[i] & 255) | ((bArr[i + 1] & 255) << 8));
    }

    private void setStyle(int i, int i2) {
        i *= 3;
        byte[] bArr = this.mData;
        bArr[i] = (byte) (i2 & 255);
        bArr[i + 1] = (byte) ((i2 >> 8) & 255);
        bArr[i + 2] = (byte) ((i2 >> 16) & 255);
    }
}
