package com.samsung.android.lxd.processor.network.channel.pty.internal;

class GrowableIntArray {
    int[] mData;
    int mLength = 0;

    GrowableIntArray(int i) {
        this.mData = new int[i];
    }

    void append(int i) {
        if (this.mLength + 1 > this.mData.length) {
            Object obj = new int[Math.max((this.mData.length * 3) >> 1, 16)];
            System.arraycopy(this.mData, 0, obj, 0, this.mLength);
            this.mData = obj;
        }
        int[] iArr = this.mData;
        int i2 = this.mLength;
        this.mLength = i2 + 1;
        iArr[i2] = i;
    }

    int length() {
        return this.mLength;
    }

    int at(int i) {
        return this.mData[i];
    }
}
