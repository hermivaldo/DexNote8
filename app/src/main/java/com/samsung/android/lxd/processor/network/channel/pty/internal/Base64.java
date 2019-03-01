package com.samsung.android.lxd.processor.network.channel.pty.internal;

import java.io.UnsupportedEncodingException;

public class Base64 {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    public static final int CRLF = 4;
    public static final int DEFAULT = 0;
    public static final int NO_CLOSE = 16;
    public static final int NO_PADDING = 1;
    public static final int NO_WRAP = 2;
    public static final int URL_SAFE = 8;

    static abstract class Coder {
        public int op;
        public byte[] output;

        public abstract int maxOutputSize(int i);

        public abstract boolean process(byte[] bArr, int i, int i2, boolean z);

        Coder() {
        }
    }

    static class Decoder extends Coder {
        private static final int[] DECODE = new int[]{SKIP, SKIP, SKIP, SKIP, SKIP, SKIP, SKIP, SKIP, SKIP, SKIP, SKIP, SKIP, SKIP, SKIP, SKIP, SKIP, SKIP, SKIP, SKIP, SKIP, SKIP, SKIP, SKIP, SKIP, SKIP, SKIP, SKIP, SKIP, SKIP, SKIP, SKIP, SKIP, SKIP, SKIP, SKIP, SKIP, SKIP, SKIP, SKIP, SKIP, SKIP, SKIP, SKIP, 62, SKIP, SKIP, SKIP, 63, 52, 53, 54, 55, 56, 57, 58, 59, 60, 61, SKIP, SKIP, SKIP, EQUALS, SKIP, SKIP, SKIP, 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, SKIP, SKIP, SKIP, SKIP, SKIP, SKIP, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35, 36, 37, 38, 39, 40, 41, 42, 43, 44, 45, 46, 47, 48, 49, 50, 51, SKIP, SKIP, SKIP, SKIP, SKIP, SKIP, SKIP, SKIP, SKIP, SKIP, SKIP, SKIP, SKIP, SKIP, SKIP, SKIP, SKIP, SKIP, SKIP, SKIP, SKIP, SKIP, SKIP, SKIP, SKIP, SKIP, SKIP, SKIP, SKIP, SKIP, SKIP, SKIP, SKIP, SKIP, SKIP, SKIP, SKIP, SKIP, SKIP, SKIP, SKIP, SKIP, SKIP, SKIP, SKIP, SKIP, SKIP, SKIP, SKIP, SKIP, SKIP, SKIP, SKIP, SKIP, SKIP, SKIP, SKIP, SKIP, SKIP, SKIP, SKIP, SKIP, SKIP, SKIP, SKIP, SKIP, SKIP, SKIP, SKIP, SKIP, SKIP, SKIP, SKIP, SKIP, SKIP, SKIP, SKIP, SKIP, SKIP, SKIP, SKIP, SKIP, SKIP, SKIP, SKIP, SKIP, SKIP, SKIP, SKIP, SKIP, SKIP, SKIP, SKIP, SKIP, SKIP, SKIP, SKIP, SKIP, SKIP, SKIP, SKIP, SKIP, SKIP, SKIP, SKIP, SKIP, SKIP, SKIP, SKIP, SKIP, SKIP, SKIP, SKIP, SKIP, SKIP, SKIP, SKIP, SKIP, SKIP, SKIP, SKIP, SKIP, SKIP, SKIP, SKIP, SKIP, SKIP, SKIP, SKIP, SKIP, SKIP, SKIP, SKIP};
        private static final int[] DECODE_WEBSAFE = new int[]{SKIP, SKIP, SKIP, SKIP, SKIP, SKIP, SKIP, SKIP, SKIP, SKIP, SKIP, SKIP, SKIP, SKIP, SKIP, SKIP, SKIP, SKIP, SKIP, SKIP, SKIP, SKIP, SKIP, SKIP, SKIP, SKIP, SKIP, SKIP, SKIP, SKIP, SKIP, SKIP, SKIP, SKIP, SKIP, SKIP, SKIP, SKIP, SKIP, SKIP, SKIP, SKIP, SKIP, SKIP, SKIP, 62, SKIP, SKIP, 52, 53, 54, 55, 56, 57, 58, 59, 60, 61, SKIP, SKIP, SKIP, EQUALS, SKIP, SKIP, SKIP, 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, SKIP, SKIP, SKIP, SKIP, 63, SKIP, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35, 36, 37, 38, 39, 40, 41, 42, 43, 44, 45, 46, 47, 48, 49, 50, 51, SKIP, SKIP, SKIP, SKIP, SKIP, SKIP, SKIP, SKIP, SKIP, SKIP, SKIP, SKIP, SKIP, SKIP, SKIP, SKIP, SKIP, SKIP, SKIP, SKIP, SKIP, SKIP, SKIP, SKIP, SKIP, SKIP, SKIP, SKIP, SKIP, SKIP, SKIP, SKIP, SKIP, SKIP, SKIP, SKIP, SKIP, SKIP, SKIP, SKIP, SKIP, SKIP, SKIP, SKIP, SKIP, SKIP, SKIP, SKIP, SKIP, SKIP, SKIP, SKIP, SKIP, SKIP, SKIP, SKIP, SKIP, SKIP, SKIP, SKIP, SKIP, SKIP, SKIP, SKIP, SKIP, SKIP, SKIP, SKIP, SKIP, SKIP, SKIP, SKIP, SKIP, SKIP, SKIP, SKIP, SKIP, SKIP, SKIP, SKIP, SKIP, SKIP, SKIP, SKIP, SKIP, SKIP, SKIP, SKIP, SKIP, SKIP, SKIP, SKIP, SKIP, SKIP, SKIP, SKIP, SKIP, SKIP, SKIP, SKIP, SKIP, SKIP, SKIP, SKIP, SKIP, SKIP, SKIP, SKIP, SKIP, SKIP, SKIP, SKIP, SKIP, SKIP, SKIP, SKIP, SKIP, SKIP, SKIP, SKIP, SKIP, SKIP, SKIP, SKIP, SKIP, SKIP, SKIP, SKIP, SKIP, SKIP, SKIP, SKIP, SKIP};
        private static final int EQUALS = -2;
        private static final int SKIP = -1;
        private final int[] alphabet;
        private int state;
        private int value;

        public Decoder(int i, byte[] bArr) {
            this.output = bArr;
            this.alphabet = (i & 8) == 0 ? DECODE : DECODE_WEBSAFE;
            this.state = 0;
            this.value = 0;
        }

        public int maxOutputSize(int i) {
            return ((i * 3) / 4) + 10;
        }

        /* JADX WARNING: Removed duplicated region for block: B:54:0x00ec  */
        /* JADX WARNING: Removed duplicated region for block: B:52:0x00e5  */
        /* JADX WARNING: Missing block: B:20:0x0074, code:
            r0 = r13;
     */
        /* JADX WARNING: Missing block: B:45:0x00d8, code:
            r6 = r13;
     */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public boolean process(byte[] bArr, int i, int i2, boolean z) {
            if (this.state == 6) {
                return Base64.$assertionsDisabled;
            }
            i2 += i;
            int i3 = this.state;
            int i4 = this.value;
            byte[] bArr2 = this.output;
            int[] iArr = this.alphabet;
            int i5 = i4;
            i4 = 0;
            while (i < i2) {
                int i6;
                if (i3 == 0) {
                    while (true) {
                        i6 = i + 4;
                        if (i6 <= i2) {
                            i5 = (((iArr[bArr[i] & 255] << 18) | (iArr[bArr[i + 1] & 255] << 12)) | (iArr[bArr[i + 2] & 255] << 6)) | iArr[bArr[i + 3] & 255];
                            if (i5 >= 0) {
                                bArr2[i4 + 2] = (byte) i5;
                                bArr2[i4 + 1] = (byte) (i5 >> 8);
                                bArr2[i4] = (byte) (i5 >> 16);
                                i4 += 3;
                                i = i6;
                            }
                        }
                    }
                    if (i >= i2) {
                        if (z) {
                            this.state = i3;
                            this.value = i5;
                            this.op = i4;
                            return true;
                        }
                        switch (i3) {
                            case 1:
                                this.state = 6;
                                return Base64.$assertionsDisabled;
                            case 2:
                                i = i4 + 1;
                                bArr2[i4] = (byte) (i5 >> 4);
                                i4 = i;
                                break;
                            case 3:
                                i = i4 + 1;
                                bArr2[i4] = (byte) (i5 >> 10);
                                i4 = i + 1;
                                bArr2[i] = (byte) (i5 >> 2);
                                break;
                            case 4:
                                this.state = 6;
                                return Base64.$assertionsDisabled;
                        }
                        this.state = i3;
                        this.op = i4;
                        return true;
                    }
                }
                i6 = i + 1;
                i = iArr[bArr[i] & 255];
                switch (i3) {
                    case 0:
                        if (i < 0) {
                            if (i == SKIP) {
                                break;
                            }
                            this.state = 6;
                            return Base64.$assertionsDisabled;
                        }
                        i3++;
                    case 1:
                        if (i < 0) {
                            if (i == SKIP) {
                                break;
                            }
                            this.state = 6;
                            return Base64.$assertionsDisabled;
                        }
                        i |= i5 << 6;
                        i3++;
                    case 2:
                        if (i < 0) {
                            if (i != EQUALS) {
                                if (i == SKIP) {
                                    break;
                                }
                                this.state = 6;
                                return Base64.$assertionsDisabled;
                            }
                            i = i4 + 1;
                            bArr2[i4] = (byte) (i5 >> 4);
                            i4 = i;
                            i3 = 4;
                            break;
                        }
                        i |= i5 << 6;
                        i3++;
                    case 3:
                        if (i < 0) {
                            if (i != EQUALS) {
                                if (i == SKIP) {
                                    break;
                                }
                                this.state = 6;
                                return Base64.$assertionsDisabled;
                            }
                            bArr2[i4 + 1] = (byte) (i5 >> 2);
                            bArr2[i4] = (byte) (i5 >> 10);
                            i4 += 2;
                            i = 5;
                        } else {
                            i |= i5 << 6;
                            bArr2[i4 + 2] = (byte) i;
                            bArr2[i4 + 1] = (byte) (i >> 8);
                            bArr2[i4] = (byte) (i >> 16);
                            i4 += 3;
                            i5 = i;
                            i3 = 0;
                            break;
                        }
                    case 4:
                        if (i != EQUALS) {
                            if (i == SKIP) {
                                break;
                            }
                            this.state = 6;
                            return Base64.$assertionsDisabled;
                        }
                        i = i3 + 1;
                    case 5:
                        if (i == SKIP) {
                            break;
                        }
                        this.state = 6;
                        return Base64.$assertionsDisabled;
                    default:
                        break;
                }
                i = i6;
            }
            if (z) {
            }
        }
    }

    static class Encoder extends Coder {
        static final /* synthetic */ boolean $assertionsDisabled = false;
        private static final byte[] ENCODE = new byte[]{(byte) 65, (byte) 66, (byte) 67, (byte) 68, (byte) 69, (byte) 70, (byte) 71, (byte) 72, (byte) 73, (byte) 74, (byte) 75, (byte) 76, (byte) 77, (byte) 78, (byte) 79, (byte) 80, (byte) 81, (byte) 82, (byte) 83, (byte) 84, (byte) 85, (byte) 86, (byte) 87, (byte) 88, (byte) 89, (byte) 90, (byte) 97, (byte) 98, (byte) 99, (byte) 100, (byte) 101, (byte) 102, (byte) 103, (byte) 104, (byte) 105, (byte) 106, (byte) 107, (byte) 108, (byte) 109, (byte) 110, (byte) 111, (byte) 112, (byte) 113, (byte) 114, (byte) 115, (byte) 116, (byte) 117, (byte) 118, (byte) 119, (byte) 120, (byte) 121, (byte) 122, (byte) 48, (byte) 49, (byte) 50, (byte) 51, (byte) 52, (byte) 53, (byte) 54, (byte) 55, (byte) 56, (byte) 57, (byte) 43, (byte) 47};
        private static final byte[] ENCODE_WEBSAFE = new byte[]{(byte) 65, (byte) 66, (byte) 67, (byte) 68, (byte) 69, (byte) 70, (byte) 71, (byte) 72, (byte) 73, (byte) 74, (byte) 75, (byte) 76, (byte) 77, (byte) 78, (byte) 79, (byte) 80, (byte) 81, (byte) 82, (byte) 83, (byte) 84, (byte) 85, (byte) 86, (byte) 87, (byte) 88, (byte) 89, (byte) 90, (byte) 97, (byte) 98, (byte) 99, (byte) 100, (byte) 101, (byte) 102, (byte) 103, (byte) 104, (byte) 105, (byte) 106, (byte) 107, (byte) 108, (byte) 109, (byte) 110, (byte) 111, (byte) 112, (byte) 113, (byte) 114, (byte) 115, (byte) 116, (byte) 117, (byte) 118, (byte) 119, (byte) 120, (byte) 121, (byte) 122, (byte) 48, (byte) 49, (byte) 50, (byte) 51, (byte) 52, (byte) 53, (byte) 54, (byte) 55, (byte) 56, (byte) 57, (byte) 45, (byte) 95};
        public static final int LINE_GROUPS = 19;
        private final byte[] alphabet;
        private int count;
        public final boolean do_cr;
        public final boolean do_newline;
        public final boolean do_padding;
        private final byte[] tail;
        int tailLen;

        static {
            Class cls = Base64.class;
        }

        public Encoder(int i, byte[] bArr) {
            this.output = bArr;
            boolean z = true;
            this.do_padding = (i & 1) == 0 ? true : $assertionsDisabled;
            this.do_newline = (i & 2) == 0 ? true : $assertionsDisabled;
            if ((i & 4) == 0) {
                z = $assertionsDisabled;
            }
            this.do_cr = z;
            this.alphabet = (i & 8) == 0 ? ENCODE : ENCODE_WEBSAFE;
            this.tail = new byte[2];
            this.tailLen = 0;
            this.count = this.do_newline ? 19 : -1;
        }

        public int maxOutputSize(int i) {
            return ((i * 8) / 5) + 10;
        }

        /* Code decompiled incorrectly, please refer to instructions dump. */
        public boolean process(byte[] bArr, int i, int i2, boolean z) {
            int i3;
            int i4;
            byte[] bArr2 = this.alphabet;
            byte[] bArr3 = this.output;
            int i5 = this.count;
            int i6 = i2 + i;
            int i7 = 0;
            switch (this.tailLen) {
                case 1:
                    if (i + 2 <= i6) {
                        int i8 = i + 1;
                        i3 = ((bArr[i] & 255) << 8) | ((this.tail[0] & 255) << 16);
                        i4 = i8 + 1;
                        i3 |= bArr[i8] & 255;
                        this.tailLen = 0;
                        break;
                    }
                case 2:
                    i4 = i + 1;
                    if (i4 <= i6) {
                        i3 = (bArr[i] & 255) | (((this.tail[0] & 255) << 16) | ((this.tail[1] & 255) << 8));
                        this.tailLen = 0;
                        break;
                    }
                default:
                    i4 = i;
                    i3 = -1;
                    break;
            }
            if (i3 != -1) {
                bArr3[0] = bArr2[(i3 >> 18) & 63];
                bArr3[1] = bArr2[(i3 >> 12) & 63];
                bArr3[2] = bArr2[(i3 >> 6) & 63];
                bArr3[3] = bArr2[i3 & 63];
                i5--;
                if (i5 == 0) {
                    if (this.do_cr) {
                        i3 = 5;
                        bArr3[4] = (byte) 13;
                    } else {
                        i3 = 4;
                    }
                    i5 = i3 + 1;
                    bArr3[i3] = (byte) 10;
                    i3 = 19;
                } else {
                    i3 = i5;
                    i5 = 4;
                }
            } else {
                i3 = i5;
                i5 = 0;
            }
            while (true) {
                int i9 = i4 + 3;
                if (i9 <= i6) {
                    i4 = (bArr[i4 + 2] & 255) | (((bArr[i4 + 1] & 255) << 8) | ((bArr[i4] & 255) << 16));
                    bArr3[i5] = bArr2[(i4 >> 18) & 63];
                    bArr3[i5 + 1] = bArr2[(i4 >> 12) & 63];
                    bArr3[i5 + 2] = bArr2[(i4 >> 6) & 63];
                    bArr3[i5 + 3] = bArr2[i4 & 63];
                    i5 += 4;
                    i3--;
                    if (i3 == 0) {
                        if (this.do_cr) {
                            i3 = i5 + 1;
                            bArr3[i5] = (byte) 13;
                        } else {
                            i3 = i5;
                        }
                        i5 = i3 + 1;
                        bArr3[i3] = (byte) 10;
                        i4 = i9;
                        i3 = 19;
                    } else {
                        i4 = i9;
                    }
                } else {
                    boolean z2;
                    int i10;
                    if (z) {
                        int i11;
                        int i12;
                        if (i4 - this.tailLen == i6 - 1) {
                            if (this.tailLen > 0) {
                                i11 = this.tail[0];
                                i7 = 1;
                            } else {
                                i11 = bArr[i4];
                            }
                            i11 = (i11 & 255) << 4;
                            this.tailLen -= i7;
                            i6 = i5 + 1;
                            bArr3[i5] = bArr2[(i11 >> 6) & 63];
                            i5 = i6 + 1;
                            bArr3[i6] = bArr2[i11 & 63];
                            if (this.do_padding) {
                                i11 = i5 + 1;
                                bArr3[i5] = (byte) 61;
                                i5 = i11 + 1;
                                bArr3[i11] = (byte) 61;
                            }
                            if (this.do_newline) {
                                if (this.do_cr) {
                                    i11 = i5 + 1;
                                    bArr3[i5] = (byte) 13;
                                } else {
                                    i11 = i5;
                                }
                                i5 = i11 + 1;
                                bArr3[i11] = (byte) 10;
                            }
                        } else if (i4 - this.tailLen == i6 - 2) {
                            if (this.tailLen > 1) {
                                i6 = this.tail[0];
                                i7 = 1;
                            } else {
                                i6 = i4 + 1;
                                byte b = bArr[i4];
                                i4 = i6;
                                i6 = b;
                            }
                            i6 = (i6 & 255) << 10;
                            if (this.tailLen > 0) {
                                i4 = i7 + 1;
                                i11 = this.tail[i7];
                                i7 = i4;
                            } else {
                                i11 = bArr[i4];
                            }
                            i11 = ((i11 & 255) << 2) | i6;
                            this.tailLen -= i7;
                            i6 = i5 + 1;
                            bArr3[i5] = bArr2[(i11 >> 12) & 63];
                            i5 = i6 + 1;
                            bArr3[i6] = bArr2[(i11 >> 6) & 63];
                            i6 = i5 + 1;
                            bArr3[i5] = bArr2[i11 & 63];
                            if (this.do_padding) {
                                i11 = i6 + 1;
                                bArr3[i6] = (byte) 61;
                            } else {
                                i11 = i6;
                            }
                            if (this.do_newline) {
                                if (this.do_cr) {
                                    i12 = i11 + 1;
                                    bArr3[i11] = (byte) 13;
                                    i11 = i12;
                                }
                                i12 = i11 + 1;
                                bArr3[i11] = (byte) 10;
                                i11 = i12;
                            }
                            i5 = i11;
                        } else if (this.do_newline && i5 > 0 && i3 != 19) {
                            if (this.do_cr) {
                                i11 = i5 + 1;
                                bArr3[i5] = (byte) 13;
                            } else {
                                i11 = i5;
                            }
                            i12 = i11 + 1;
                            bArr3[i11] = (byte) 10;
                            i5 = i12;
                        }
                    } else if (i4 == i6 - 1) {
                        bArr2 = this.tail;
                        i10 = this.tailLen;
                        this.tailLen = i10 + 1;
                        bArr2[i10] = bArr[i4];
                    } else if (i4 == i6 - 2) {
                        bArr2 = this.tail;
                        i10 = this.tailLen;
                        this.tailLen = i10 + 1;
                        bArr2[i10] = bArr[i4];
                        bArr2 = this.tail;
                        i10 = this.tailLen;
                        this.tailLen = i10 + 1;
                        z2 = true;
                        bArr2[i10] = bArr[i4 + 1];
                        this.op = i5;
                        this.count = i3;
                        return z2;
                    }
                    z2 = true;
                    this.op = i5;
                    this.count = i3;
                    return z2;
                }
            }
        }
    }

    public static byte[] decode(String str, int i) {
        return decode(str.getBytes(), i);
    }

    public static byte[] decode(byte[] bArr, int i) {
        return decode(bArr, 0, bArr.length, i);
    }

    public static byte[] decode(byte[] bArr, int i, int i2, int i3) {
        Decoder decoder = new Decoder(i3, new byte[((i2 * 3) / 4)]);
        if (!decoder.process(bArr, i, i2, true)) {
            throw new IllegalArgumentException("bad base-64");
        } else if (decoder.op == decoder.output.length) {
            return decoder.output;
        } else {
            Object obj = new byte[decoder.op];
            System.arraycopy(decoder.output, 0, obj, 0, decoder.op);
            return obj;
        }
    }

    public static String encodeToString(byte[] bArr, int i) {
        try {
            return new String(encode(bArr, i), "US-ASCII");
        } catch (UnsupportedEncodingException e) {
            throw new AssertionError(e);
        }
    }

    public static String encodeToString(byte[] bArr, int i, int i2, int i3) {
        try {
            return new String(encode(bArr, i, i2, i3), "US-ASCII");
        } catch (UnsupportedEncodingException e) {
            throw new AssertionError(e);
        }
    }

    public static byte[] encode(byte[] bArr, int i) {
        return encode(bArr, 0, bArr.length, i);
    }

    public static byte[] encode(byte[] bArr, int i, int i2, int i3) {
        Encoder encoder = new Encoder(i3, null);
        i3 = (i2 / 3) * 4;
        if (!encoder.do_padding) {
            switch (i2 % 3) {
                case 1:
                    i3 += 2;
                    break;
                case 2:
                    i3 += 3;
                    break;
            }
        } else if (i2 % 3 > 0) {
            i3 += 4;
        }
        if (encoder.do_newline && i2 > 0) {
            i3 += (((i2 - 1) / 57) + 1) * (encoder.do_cr ? 2 : 1);
        }
        encoder.output = new byte[i3];
        encoder.process(bArr, i, i2, true);
        return encoder.output;
    }

    private Base64() {
    }
}
