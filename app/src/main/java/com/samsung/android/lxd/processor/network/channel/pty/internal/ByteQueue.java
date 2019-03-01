package com.samsung.android.lxd.processor.network.channel.pty.internal;

class ByteQueue {
    private byte[] mBuffer;
    private int mHead;
    private int mStoredBytes;

    public ByteQueue(int i) {
        this.mBuffer = new byte[i];
    }

    public int getBytesAvailable() {
        int i;
        synchronized (this) {
            i = this.mStoredBytes;
        }
        return i;
    }

    public int read(byte[] bArr, int i, int i2) {
        if (i2 + i > bArr.length) {
            throw new IllegalArgumentException("length + offset > buffer.length");
        } else if (i2 < 0) {
            throw new IllegalArgumentException("length < 0");
        } else if (i2 == 0) {
            return 0;
        } else {
            synchronized (this) {
                while (this.mStoredBytes == 0) {
                    wait();
                }
                int length = this.mBuffer.length;
                int i3 = length == this.mStoredBytes ? 1 : 0;
                int i4 = i;
                i = 0;
                while (i2 > 0 && this.mStoredBytes > 0) {
                    int min = Math.min(i2, Math.min(length - this.mHead, this.mStoredBytes));
                    System.arraycopy(this.mBuffer, this.mHead, bArr, i4, min);
                    this.mHead += min;
                    if (this.mHead >= length) {
                        this.mHead = 0;
                    }
                    this.mStoredBytes -= min;
                    i2 -= min;
                    i4 += min;
                    i += min;
                }
                if (i3 != 0) {
                    notify();
                }
            }
            return i;
        }
    }

    public int write(byte[] bArr, int i, int i2) {
        if (i2 + i > bArr.length) {
            throw new IllegalArgumentException("length + offset > buffer.length");
        } else if (i2 < 0) {
            throw new IllegalArgumentException("length < 0");
        } else {
            int i3 = 0;
            if (i2 == 0) {
                return 0;
            }
            synchronized (this) {
                int length = this.mBuffer.length;
                if (this.mStoredBytes == 0) {
                    i3 = 1;
                }
                while (length == this.mStoredBytes) {
                    wait();
                }
                int i4 = this.mHead + this.mStoredBytes;
                if (i4 >= length) {
                    i4 -= length;
                    length = this.mHead - i4;
                } else {
                    length -= i4;
                }
                i2 = Math.min(length, i2);
                System.arraycopy(bArr, i, this.mBuffer, i4, i2);
                this.mStoredBytes += i2;
                if (i3 != 0) {
                    notify();
                }
            }
            return i2;
        }
    }
}
