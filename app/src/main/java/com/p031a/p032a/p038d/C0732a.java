package com.p031a.p032a.p038d;

import com.p031a.p032a.p034b.C0716e;
import com.p031a.p032a.p034b.p035a.C1221e;
import com.samsung.android.lxd.processor.network.channel.pty.internal.KeycodeConstants;
import java.io.Closeable;
import java.io.EOFException;
import java.io.IOException;
import java.io.Reader;

/* compiled from: JsonReader */
/* renamed from: com.a.a.d.a */
public class C0732a implements Closeable {
    /* renamed from: b */
    private static final char[] f2329b = ")]}'\n".toCharArray();
    /* renamed from: a */
    int f2330a = 0;
    /* renamed from: c */
    private final Reader f2331c;
    /* renamed from: d */
    private boolean f2332d = false;
    /* renamed from: e */
    private final char[] f2333e = new char[1024];
    /* renamed from: f */
    private int f2334f = 0;
    /* renamed from: g */
    private int f2335g = 0;
    /* renamed from: h */
    private int f2336h = 0;
    /* renamed from: i */
    private int f2337i = 0;
    /* renamed from: j */
    private long f2338j;
    /* renamed from: k */
    private int f2339k;
    /* renamed from: l */
    private String f2340l;
    /* renamed from: m */
    private int[] f2341m = new int[32];
    /* renamed from: n */
    private int f2342n = 0;
    /* renamed from: o */
    private String[] f2343o;
    /* renamed from: p */
    private int[] f2344p;

    /* compiled from: JsonReader */
    /* renamed from: com.a.a.d.a$1 */
    static class C12671 extends C0716e {
        C12671() {
        }

        /* renamed from: a */
        public void mo630a(C0732a c0732a) {
            if (c0732a instanceof C1221e) {
                ((C1221e) c0732a).mo608o();
                return;
            }
            int i = c0732a.f2330a;
            if (i == 0) {
                i = c0732a.m3050r();
            }
            if (i == 13) {
                c0732a.f2330a = 9;
            } else if (i == 12) {
                c0732a.f2330a = 8;
            } else if (i == 14) {
                c0732a.f2330a = 10;
            } else {
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append("Expected a name but was ");
                stringBuilder.append(c0732a.mo599f());
                stringBuilder.append(c0732a.m3051s());
                throw new IllegalStateException(stringBuilder.toString());
            }
        }
    }

    static {
        C0716e.f2298a = new C12671();
    }

    public C0732a(Reader reader) {
        int[] iArr = this.f2341m;
        int i = this.f2342n;
        this.f2342n = i + 1;
        iArr[i] = 6;
        this.f2343o = new String[32];
        this.f2344p = new int[32];
        if (reader == null) {
            throw new NullPointerException("in == null");
        }
        this.f2331c = reader;
    }

    /* renamed from: a */
    public final void m3034a(boolean z) {
        this.f2332d = z;
    }

    /* renamed from: q */
    public final boolean m3049q() {
        return this.f2332d;
    }

    /* renamed from: a */
    public void mo593a() {
        int i = this.f2330a;
        if (i == 0) {
            i = m3050r();
        }
        if (i == 3) {
            m3017a(1);
            this.f2344p[this.f2342n - 1] = 0;
            this.f2330a = 0;
            return;
        }
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Expected BEGIN_ARRAY but was ");
        stringBuilder.append(mo599f());
        stringBuilder.append(m3051s());
        throw new IllegalStateException(stringBuilder.toString());
    }

    /* renamed from: b */
    public void mo594b() {
        int i = this.f2330a;
        if (i == 0) {
            i = m3050r();
        }
        if (i == 4) {
            this.f2342n--;
            int[] iArr = this.f2344p;
            int i2 = this.f2342n - 1;
            iArr[i2] = iArr[i2] + 1;
            this.f2330a = 0;
            return;
        }
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Expected END_ARRAY but was ");
        stringBuilder.append(mo599f());
        stringBuilder.append(m3051s());
        throw new IllegalStateException(stringBuilder.toString());
    }

    /* renamed from: c */
    public void mo595c() {
        int i = this.f2330a;
        if (i == 0) {
            i = m3050r();
        }
        if (i == 1) {
            m3017a(3);
            this.f2330a = 0;
            return;
        }
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Expected BEGIN_OBJECT but was ");
        stringBuilder.append(mo599f());
        stringBuilder.append(m3051s());
        throw new IllegalStateException(stringBuilder.toString());
    }

    /* renamed from: d */
    public void mo597d() {
        int i = this.f2330a;
        if (i == 0) {
            i = m3050r();
        }
        if (i == 2) {
            this.f2342n--;
            this.f2343o[this.f2342n] = null;
            int[] iArr = this.f2344p;
            int i2 = this.f2342n - 1;
            iArr[i2] = iArr[i2] + 1;
            this.f2330a = 0;
            return;
        }
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Expected END_OBJECT but was ");
        stringBuilder.append(mo599f());
        stringBuilder.append(m3051s());
        throw new IllegalStateException(stringBuilder.toString());
    }

    /* renamed from: e */
    public boolean mo598e() {
        int i = this.f2330a;
        if (i == 0) {
            i = m3050r();
        }
        return (i == 2 || i == 4) ? false : true;
    }

    /* renamed from: f */
    public C0733b mo599f() {
        int i = this.f2330a;
        if (i == 0) {
            i = m3050r();
        }
        switch (i) {
            case 1:
                return C0733b.BEGIN_OBJECT;
            case 2:
                return C0733b.END_OBJECT;
            case 3:
                return C0733b.BEGIN_ARRAY;
            case 4:
                return C0733b.END_ARRAY;
            case 5:
            case 6:
                return C0733b.BOOLEAN;
            case 7:
                return C0733b.NULL;
            case 8:
            case 9:
            case 10:
            case 11:
                return C0733b.STRING;
            case 12:
            case 13:
            case 14:
                return C0733b.NAME;
            case 15:
            case 16:
                return C0733b.NUMBER;
            case 17:
                return C0733b.END_DOCUMENT;
            default:
                throw new AssertionError();
        }
    }

    /* renamed from: r */
    int m3050r() {
        int b;
        int i = this.f2341m[this.f2342n - 1];
        if (i == 1) {
            this.f2341m[this.f2342n - 1] = 2;
        } else if (i == 2) {
            b = m3020b(true);
            if (b != 44) {
                if (b == 59) {
                    m3029w();
                } else if (b != 93) {
                    throw m3021b("Unterminated array");
                } else {
                    this.f2330a = 4;
                    return 4;
                }
            }
        } else {
            int b2;
            if (i != 3) {
                if (i != 5) {
                    if (i == 4) {
                        this.f2341m[this.f2342n - 1] = 5;
                        b = m3020b(true);
                        if (b != 58) {
                            if (b != 61) {
                                throw m3021b("Expected ':'");
                            }
                            m3029w();
                            if ((this.f2334f < this.f2335g || m3023b(1)) && this.f2333e[this.f2334f] == '>') {
                                this.f2334f++;
                            }
                        }
                    } else if (i == 6) {
                        if (this.f2332d) {
                            m3032z();
                        }
                        this.f2341m[this.f2342n - 1] = 7;
                    } else if (i == 7) {
                        if (m3020b(false) == -1) {
                            this.f2330a = 17;
                            return 17;
                        }
                        m3029w();
                        this.f2334f--;
                    } else if (i == 8) {
                        throw new IllegalStateException("JsonReader is closed");
                    }
                }
            }
            this.f2341m[this.f2342n - 1] = 4;
            if (i == 5) {
                b2 = m3020b(true);
                if (b2 != 44) {
                    if (b2 == 59) {
                        m3029w();
                    } else if (b2 != KeycodeConstants.KEYCODE_FORWARD) {
                        throw m3021b("Unterminated object");
                    } else {
                        this.f2330a = 2;
                        return 2;
                    }
                }
            }
            b2 = m3020b(true);
            if (b2 == 34) {
                this.f2330a = 13;
                return 13;
            } else if (b2 == 39) {
                m3029w();
                this.f2330a = 12;
                return 12;
            } else if (b2 != KeycodeConstants.KEYCODE_FORWARD) {
                m3029w();
                this.f2334f--;
                if (m3018a((char) b2)) {
                    this.f2330a = 14;
                    return 14;
                }
                throw m3021b("Expected name");
            } else if (i != 5) {
                this.f2330a = 2;
                return 2;
            } else {
                throw m3021b("Expected name");
            }
        }
        b = m3020b(true);
        if (b == 34) {
            this.f2330a = 9;
            return 9;
        } else if (b != 39) {
            if (!(b == 44 || b == 59)) {
                if (b == 91) {
                    this.f2330a = 3;
                    return 3;
                } else if (b != 93) {
                    if (b != KeycodeConstants.KEYCODE_MOVE_END) {
                        this.f2334f--;
                        i = mo608o();
                        if (i != 0) {
                            return i;
                        }
                        i = m3026t();
                        if (i != 0) {
                            return i;
                        }
                        if (m3018a(this.f2333e[this.f2334f])) {
                            m3029w();
                            this.f2330a = 10;
                            return 10;
                        }
                        throw m3021b("Expected value");
                    }
                    this.f2330a = 1;
                    return 1;
                } else if (i == 1) {
                    this.f2330a = 4;
                    return 4;
                }
            }
            if (i != 1) {
                if (i != 2) {
                    throw m3021b("Unexpected value");
                }
            }
            m3029w();
            this.f2334f--;
            this.f2330a = 7;
            return 7;
        } else {
            m3029w();
            this.f2330a = 8;
            return 8;
        }
    }

    /* renamed from: o */
    private int mo608o() {
        String str;
        String str2;
        int i;
        int length;
        int i2;
        char c;
        char c2 = this.f2333e[this.f2334f];
        if (c2 != 't') {
            if (c2 != 'T') {
                if (c2 != 'f') {
                    if (c2 != 'F') {
                        if (c2 != 'n') {
                            if (c2 != 'N') {
                                return 0;
                            }
                        }
                        str = "null";
                        str2 = "NULL";
                        i = 7;
                        length = str.length();
                        i2 = 1;
                        while (i2 < length) {
                            if (this.f2334f + i2 < this.f2335g && !m3023b(i2 + 1)) {
                                return 0;
                            }
                            c = this.f2333e[this.f2334f + i2];
                            if (c != str.charAt(i2) && c != r2.charAt(i2)) {
                                return 0;
                            }
                            i2++;
                        }
                        if ((this.f2334f + length >= this.f2335g || m3023b(length + 1)) && m3018a(this.f2333e[this.f2334f + length])) {
                            return 0;
                        }
                        this.f2334f += length;
                        this.f2330a = i;
                        return i;
                    }
                }
                str = "false";
                str2 = "FALSE";
                i = 6;
                length = str.length();
                i2 = 1;
                while (i2 < length) {
                    if (this.f2334f + i2 < this.f2335g) {
                    }
                    c = this.f2333e[this.f2334f + i2];
                    if (c != str.charAt(i2)) {
                    }
                    i2++;
                }
                if (this.f2334f + length >= this.f2335g) {
                }
                return 0;
            }
        }
        str = "true";
        str2 = "TRUE";
        i = 5;
        length = str.length();
        i2 = 1;
        while (i2 < length) {
            if (this.f2334f + i2 < this.f2335g) {
            }
            c = this.f2333e[this.f2334f + i2];
            if (c != str.charAt(i2)) {
            }
            i2++;
        }
        if (this.f2334f + length >= this.f2335g) {
        }
        return 0;
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    /* renamed from: t */
    private int m3026t() {
        /*
        r19 = this;
        r0 = r19;
        r1 = r0.f2333e;
        r2 = r0.f2334f;
        r3 = r0.f2335g;
        r6 = 1;
        r7 = 0;
        r8 = r3;
        r10 = r6;
        r3 = r7;
        r9 = r3;
        r13 = r9;
        r11 = 0;
    L_0x0011:
        r14 = r2 + r3;
        r15 = 2;
        if (r14 != r8) goto L_0x0028;
    L_0x0016:
        r2 = r1.length;
        if (r3 != r2) goto L_0x001a;
    L_0x0019:
        return r7;
    L_0x001a:
        r2 = r3 + 1;
        r2 = r0.m3023b(r2);
        if (r2 != 0) goto L_0x0024;
    L_0x0022:
        goto L_0x0091;
    L_0x0024:
        r2 = r0.f2334f;
        r8 = r0.f2335g;
    L_0x0028:
        r14 = r2 + r3;
        r14 = r1[r14];
        r7 = 43;
        r4 = 3;
        r5 = 5;
        if (r14 == r7) goto L_0x00e9;
    L_0x0032:
        r7 = 69;
        if (r14 == r7) goto L_0x00dd;
    L_0x0036:
        r7 = 101; // 0x65 float:1.42E-43 double:5.0E-322;
        if (r14 == r7) goto L_0x00dd;
    L_0x003a:
        switch(r14) {
            case 45: goto L_0x00d0;
            case 46: goto L_0x00c9;
            default: goto L_0x003d;
        };
    L_0x003d:
        r7 = 48;
        if (r14 < r7) goto L_0x008b;
    L_0x0041:
        r7 = 57;
        if (r14 <= r7) goto L_0x0046;
    L_0x0045:
        goto L_0x008b;
    L_0x0046:
        if (r9 == r6) goto L_0x0080;
    L_0x0048:
        if (r9 != 0) goto L_0x004b;
    L_0x004a:
        goto L_0x0080;
    L_0x004b:
        if (r9 != r15) goto L_0x0073;
    L_0x004d:
        r16 = 0;
        r4 = (r11 > r16 ? 1 : (r11 == r16 ? 0 : -1));
        if (r4 != 0) goto L_0x0055;
    L_0x0053:
        r4 = 0;
        return r4;
    L_0x0055:
        r4 = 10;
        r4 = r4 * r11;
        r14 = r14 + -48;
        r14 = (long) r14;
        r4 = r4 - r14;
        r14 = -922337203685477580; // 0xf333333333333334 float:4.1723254E-8 double:-8.390303882365713E246;
        r7 = (r11 > r14 ? 1 : (r11 == r14 ? 0 : -1));
        if (r7 > 0) goto L_0x006e;
    L_0x0065:
        if (r7 != 0) goto L_0x006c;
    L_0x0067:
        r7 = (r4 > r11 ? 1 : (r4 == r11 ? 0 : -1));
        if (r7 >= 0) goto L_0x006c;
    L_0x006b:
        goto L_0x006e;
    L_0x006c:
        r7 = 0;
        goto L_0x006f;
    L_0x006e:
        r7 = r6;
    L_0x006f:
        r7 = r7 & r10;
        r11 = r4;
        r10 = r7;
        goto L_0x0086;
    L_0x0073:
        if (r9 != r4) goto L_0x0078;
    L_0x0075:
        r7 = 0;
        r9 = 4;
        goto L_0x0087;
    L_0x0078:
        if (r9 == r5) goto L_0x007d;
    L_0x007a:
        r4 = 6;
        if (r9 != r4) goto L_0x0086;
    L_0x007d:
        r7 = 0;
        r9 = 7;
        goto L_0x0087;
    L_0x0080:
        r14 = r14 + -48;
        r4 = -r14;
        r4 = (long) r4;
        r11 = r4;
        r9 = r15;
    L_0x0086:
        r7 = 0;
    L_0x0087:
        r16 = 0;
        goto L_0x00f0;
    L_0x008b:
        r1 = r0.m3018a(r14);
        if (r1 != 0) goto L_0x00c7;
    L_0x0091:
        if (r9 != r15) goto L_0x00b5;
    L_0x0093:
        if (r10 == 0) goto L_0x00b5;
    L_0x0095:
        r1 = -9223372036854775808;
        r1 = (r11 > r1 ? 1 : (r11 == r1 ? 0 : -1));
        if (r1 != 0) goto L_0x009d;
    L_0x009b:
        if (r13 == 0) goto L_0x00b5;
    L_0x009d:
        r16 = 0;
        r1 = (r11 > r16 ? 1 : (r11 == r16 ? 0 : -1));
        if (r1 != 0) goto L_0x00a5;
    L_0x00a3:
        if (r13 != 0) goto L_0x00b5;
    L_0x00a5:
        if (r13 == 0) goto L_0x00a8;
    L_0x00a7:
        goto L_0x00a9;
    L_0x00a8:
        r11 = -r11;
    L_0x00a9:
        r0.f2338j = r11;
        r1 = r0.f2334f;
        r1 = r1 + r3;
        r0.f2334f = r1;
        r1 = 15;
        r0.f2330a = r1;
        return r1;
    L_0x00b5:
        if (r9 == r15) goto L_0x00c0;
    L_0x00b7:
        r1 = 4;
        if (r9 == r1) goto L_0x00c0;
    L_0x00ba:
        r1 = 7;
        if (r9 != r1) goto L_0x00be;
    L_0x00bd:
        goto L_0x00c0;
    L_0x00be:
        r7 = 0;
        return r7;
    L_0x00c0:
        r0.f2339k = r3;
        r1 = 16;
        r0.f2330a = r1;
        return r1;
    L_0x00c7:
        r7 = 0;
        return r7;
    L_0x00c9:
        r7 = 0;
        r16 = 0;
        if (r9 != r15) goto L_0x00cf;
    L_0x00ce:
        goto L_0x00ef;
    L_0x00cf:
        return r7;
    L_0x00d0:
        r4 = 6;
        r7 = 0;
        r16 = 0;
        if (r9 != 0) goto L_0x00d9;
    L_0x00d6:
        r9 = r6;
        r13 = r9;
        goto L_0x00f0;
    L_0x00d9:
        if (r9 != r5) goto L_0x00dc;
    L_0x00db:
        goto L_0x00ef;
    L_0x00dc:
        return r7;
    L_0x00dd:
        r7 = 0;
        r16 = 0;
        if (r9 == r15) goto L_0x00e7;
    L_0x00e2:
        r4 = 4;
        if (r9 != r4) goto L_0x00e6;
    L_0x00e5:
        goto L_0x00e7;
    L_0x00e6:
        return r7;
    L_0x00e7:
        r9 = r5;
        goto L_0x00f0;
    L_0x00e9:
        r4 = 6;
        r7 = 0;
        r16 = 0;
        if (r9 != r5) goto L_0x00f4;
    L_0x00ef:
        r9 = r4;
    L_0x00f0:
        r3 = r3 + 1;
        goto L_0x0011;
    L_0x00f4:
        return r7;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.a.a.d.a.t():int");
    }

    /* renamed from: a */
    private boolean m3018a(char c) {
        switch (c) {
            case '\t':
            case '\n':
            case '\f':
            case '\r':
            case ' ':
            case ',':
            case ':':
            case '[':
            case ']':
            case KeycodeConstants.KEYCODE_MOVE_END /*123*/:
            case KeycodeConstants.KEYCODE_FORWARD /*125*/:
                break;
            case '#':
            case '/':
            case ';':
            case '=':
            case '\\':
                m3029w();
                break;
            default:
                return true;
        }
        return false;
    }

    /* renamed from: g */
    public String mo600g() {
        String u;
        int i = this.f2330a;
        if (i == 0) {
            i = m3050r();
        }
        if (i == 14) {
            u = m3027u();
        } else if (i == 12) {
            u = m3022b('\'');
        } else if (i == 13) {
            u = m3022b('\"');
        } else {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("Expected a name but was ");
            stringBuilder.append(mo599f());
            stringBuilder.append(m3051s());
            throw new IllegalStateException(stringBuilder.toString());
        }
        this.f2330a = 0;
        this.f2343o[this.f2342n - 1] = u;
        return u;
    }

    /* renamed from: h */
    public String mo601h() {
        String u;
        int i = this.f2330a;
        if (i == 0) {
            i = m3050r();
        }
        if (i == 10) {
            u = m3027u();
        } else if (i == 8) {
            u = m3022b('\'');
        } else if (i == 9) {
            u = m3022b('\"');
        } else if (i == 11) {
            u = this.f2340l;
            this.f2340l = null;
        } else if (i == 15) {
            u = Long.toString(this.f2338j);
        } else if (i == 16) {
            u = new String(this.f2333e, this.f2334f, this.f2339k);
            this.f2334f += this.f2339k;
        } else {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("Expected a string but was ");
            stringBuilder.append(mo599f());
            stringBuilder.append(m3051s());
            throw new IllegalStateException(stringBuilder.toString());
        }
        this.f2330a = 0;
        int[] iArr = this.f2344p;
        int i2 = this.f2342n - 1;
        iArr[i2] = iArr[i2] + 1;
        return u;
    }

    /* renamed from: i */
    public boolean mo602i() {
        int i = this.f2330a;
        if (i == 0) {
            i = m3050r();
        }
        int[] iArr;
        int i2;
        if (i == 5) {
            this.f2330a = 0;
            iArr = this.f2344p;
            i2 = this.f2342n - 1;
            iArr[i2] = iArr[i2] + 1;
            return true;
        } else if (i == 6) {
            this.f2330a = 0;
            iArr = this.f2344p;
            i2 = this.f2342n - 1;
            iArr[i2] = iArr[i2] + 1;
            return false;
        } else {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("Expected a boolean but was ");
            stringBuilder.append(mo599f());
            stringBuilder.append(m3051s());
            throw new IllegalStateException(stringBuilder.toString());
        }
    }

    /* renamed from: j */
    public void mo603j() {
        int i = this.f2330a;
        if (i == 0) {
            i = m3050r();
        }
        if (i == 7) {
            this.f2330a = 0;
            int[] iArr = this.f2344p;
            int i2 = this.f2342n - 1;
            iArr[i2] = iArr[i2] + 1;
            return;
        }
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Expected null but was ");
        stringBuilder.append(mo599f());
        stringBuilder.append(m3051s());
        throw new IllegalStateException(stringBuilder.toString());
    }

    /* renamed from: k */
    public double mo604k() {
        int i = this.f2330a;
        if (i == 0) {
            i = m3050r();
        }
        if (i == 15) {
            this.f2330a = 0;
            int[] iArr = this.f2344p;
            int i2 = this.f2342n - 1;
            iArr[i2] = iArr[i2] + 1;
            return (double) this.f2338j;
        }
        if (i == 16) {
            this.f2340l = new String(this.f2333e, this.f2334f, this.f2339k);
            this.f2334f += this.f2339k;
        } else {
            if (i != 8) {
                if (i != 9) {
                    if (i == 10) {
                        this.f2340l = m3027u();
                    } else if (i != 11) {
                        StringBuilder stringBuilder = new StringBuilder();
                        stringBuilder.append("Expected a double but was ");
                        stringBuilder.append(mo599f());
                        stringBuilder.append(m3051s());
                        throw new IllegalStateException(stringBuilder.toString());
                    }
                }
            }
            this.f2340l = m3022b(i == 8 ? '\'' : '\"');
        }
        this.f2330a = 11;
        double parseDouble = Double.parseDouble(this.f2340l);
        if (this.f2332d || !(Double.isNaN(parseDouble) || Double.isInfinite(parseDouble))) {
            this.f2340l = null;
            this.f2330a = 0;
            int[] iArr2 = this.f2344p;
            int i3 = this.f2342n - 1;
            iArr2[i3] = iArr2[i3] + 1;
            return parseDouble;
        }
        StringBuilder stringBuilder2 = new StringBuilder();
        stringBuilder2.append("JSON forbids NaN and infinities: ");
        stringBuilder2.append(parseDouble);
        stringBuilder2.append(m3051s());
        throw new C0735d(stringBuilder2.toString());
    }

    /* renamed from: l */
    public long mo605l() {
        /* JADX: method processing error */
/*
Error: java.lang.NullPointerException
	at jadx.core.dex.visitors.regions.ProcessTryCatchRegions.searchTryCatchDominators(ProcessTryCatchRegions.java:75)
	at jadx.core.dex.visitors.regions.ProcessTryCatchRegions.process(ProcessTryCatchRegions.java:45)
	at jadx.core.dex.visitors.regions.RegionMakerVisitor.postProcessRegions(RegionMakerVisitor.java:63)
	at jadx.core.dex.visitors.regions.RegionMakerVisitor.visit(RegionMakerVisitor.java:58)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:31)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:17)
	at jadx.core.ProcessClass.process(ProcessClass.java:34)
	at jadx.core.ProcessClass.processDependencies(ProcessClass.java:56)
	at jadx.core.ProcessClass.process(ProcessClass.java:39)
	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:282)
	at jadx.api.JavaClass.decompile(JavaClass.java:62)
	at jadx.api.JadxDecompiler.lambda$appendSourcesSave$0(JadxDecompiler.java:200)
	at jadx.api.JadxDecompiler$$Lambda$8/1201484275.run(Unknown Source)
*/
        /*
        r7 = this;
        r0 = r7.f2330a;
        if (r0 != 0) goto L_0x0008;
    L_0x0004:
        r0 = r7.m3050r();
    L_0x0008:
        r1 = 15;
        r2 = 0;
        if (r0 != r1) goto L_0x001e;
    L_0x000d:
        r7.f2330a = r2;
        r0 = r7.f2344p;
        r1 = r7.f2342n;
        r1 = r1 + -1;
        r2 = r0[r1];
        r2 = r2 + 1;
        r0[r1] = r2;
        r0 = r7.f2338j;
        return r0;
    L_0x001e:
        r1 = 16;
        if (r0 != r1) goto L_0x0037;
    L_0x0022:
        r0 = new java.lang.String;
        r1 = r7.f2333e;
        r3 = r7.f2334f;
        r4 = r7.f2339k;
        r0.<init>(r1, r3, r4);
        r7.f2340l = r0;
        r0 = r7.f2334f;
        r1 = r7.f2339k;
        r0 = r0 + r1;
        r7.f2334f = r0;
        goto L_0x0091;
    L_0x0037:
        r1 = 10;
        r3 = 8;
        if (r0 == r3) goto L_0x0066;
    L_0x003d:
        r4 = 9;
        if (r0 == r4) goto L_0x0066;
    L_0x0041:
        if (r0 != r1) goto L_0x0044;
    L_0x0043:
        goto L_0x0066;
    L_0x0044:
        r0 = new java.lang.IllegalStateException;
        r1 = new java.lang.StringBuilder;
        r1.<init>();
        r2 = "Expected a long but was ";
        r1.append(r2);
        r2 = r7.mo599f();
        r1.append(r2);
        r7 = r7.m3051s();
        r1.append(r7);
        r7 = r1.toString();
        r0.<init>(r7);
        throw r0;
    L_0x0066:
        if (r0 != r1) goto L_0x006f;
    L_0x0068:
        r0 = r7.m3027u();
        r7.f2340l = r0;
        goto L_0x007c;
    L_0x006f:
        if (r0 != r3) goto L_0x0074;
    L_0x0071:
        r0 = 39;
        goto L_0x0076;
    L_0x0074:
        r0 = 34;
    L_0x0076:
        r0 = r7.m3022b(r0);
        r7.f2340l = r0;
    L_0x007c:
        r0 = r7.f2340l;	 Catch:{ NumberFormatException -> 0x0091 }
        r0 = java.lang.Long.parseLong(r0);	 Catch:{ NumberFormatException -> 0x0091 }
        r7.f2330a = r2;	 Catch:{ NumberFormatException -> 0x0091 }
        r3 = r7.f2344p;	 Catch:{ NumberFormatException -> 0x0091 }
        r4 = r7.f2342n;	 Catch:{ NumberFormatException -> 0x0091 }
        r4 = r4 + -1;	 Catch:{ NumberFormatException -> 0x0091 }
        r5 = r3[r4];	 Catch:{ NumberFormatException -> 0x0091 }
        r5 = r5 + 1;	 Catch:{ NumberFormatException -> 0x0091 }
        r3[r4] = r5;	 Catch:{ NumberFormatException -> 0x0091 }
        return r0;
    L_0x0091:
        r0 = 11;
        r7.f2330a = r0;
        r0 = r7.f2340l;
        r0 = java.lang.Double.parseDouble(r0);
        r3 = (long) r0;
        r5 = (double) r3;
        r0 = (r5 > r0 ? 1 : (r5 == r0 ? 0 : -1));
        if (r0 == 0) goto L_0x00c1;
    L_0x00a1:
        r0 = new java.lang.NumberFormatException;
        r1 = new java.lang.StringBuilder;
        r1.<init>();
        r2 = "Expected a long but was ";
        r1.append(r2);
        r2 = r7.f2340l;
        r1.append(r2);
        r7 = r7.m3051s();
        r1.append(r7);
        r7 = r1.toString();
        r0.<init>(r7);
        throw r0;
    L_0x00c1:
        r0 = 0;
        r7.f2340l = r0;
        r7.f2330a = r2;
        r0 = r7.f2344p;
        r7 = r7.f2342n;
        r7 = r7 + -1;
        r1 = r0[r7];
        r1 = r1 + 1;
        r0[r7] = r1;
        return r3;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.a.a.d.a.l():long");
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    /* renamed from: b */
    private java.lang.String m3022b(char r10) {
        /*
        r9 = this;
        r0 = r9.f2333e;
        r1 = 0;
    L_0x0003:
        r2 = r9.f2334f;
        r3 = r9.f2335g;
    L_0x0007:
        r4 = r2;
    L_0x0008:
        r5 = 16;
        r6 = 1;
        if (r2 >= r3) goto L_0x005b;
    L_0x000d:
        r7 = r2 + 1;
        r2 = r0[r2];
        if (r2 != r10) goto L_0x0027;
    L_0x0013:
        r9.f2334f = r7;
        r7 = r7 - r4;
        r7 = r7 - r6;
        if (r1 != 0) goto L_0x001f;
    L_0x0019:
        r9 = new java.lang.String;
        r9.<init>(r0, r4, r7);
        return r9;
    L_0x001f:
        r1.append(r0, r4, r7);
        r9 = r1.toString();
        return r9;
    L_0x0027:
        r8 = 92;
        if (r2 != r8) goto L_0x004e;
    L_0x002b:
        r9.f2334f = r7;
        r7 = r7 - r4;
        r7 = r7 - r6;
        if (r1 != 0) goto L_0x003f;
    L_0x0031:
        r1 = r7 + 1;
        r1 = r1 * 2;
        r2 = new java.lang.StringBuilder;
        r1 = java.lang.Math.max(r1, r5);
        r2.<init>(r1);
        r1 = r2;
    L_0x003f:
        r1.append(r0, r4, r7);
        r2 = r9.m3031y();
        r1.append(r2);
        r2 = r9.f2334f;
        r3 = r9.f2335g;
        goto L_0x0007;
    L_0x004e:
        r5 = 10;
        if (r2 != r5) goto L_0x0059;
    L_0x0052:
        r2 = r9.f2336h;
        r2 = r2 + r6;
        r9.f2336h = r2;
        r9.f2337i = r7;
    L_0x0059:
        r2 = r7;
        goto L_0x0008;
    L_0x005b:
        if (r1 != 0) goto L_0x006b;
    L_0x005d:
        r1 = r2 - r4;
        r1 = r1 * 2;
        r3 = new java.lang.StringBuilder;
        r1 = java.lang.Math.max(r1, r5);
        r3.<init>(r1);
        r1 = r3;
    L_0x006b:
        r3 = r2 - r4;
        r1.append(r0, r4, r3);
        r9.f2334f = r2;
        r2 = r9.m3023b(r6);
        if (r2 != 0) goto L_0x0003;
    L_0x0078:
        r10 = "Unterminated string";
        r9 = r9.m3021b(r10);
        throw r9;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.a.a.d.a.b(char):java.lang.String");
    }

    /* renamed from: u */
    private String m3027u() {
        String str;
        int i = 0;
        StringBuilder stringBuilder = null;
        do {
            int i2 = 0;
            while (true) {
                if (this.f2334f + i2 < this.f2335g) {
                    switch (this.f2333e[this.f2334f + i2]) {
                        case '\t':
                        case '\n':
                        case '\f':
                        case '\r':
                        case ' ':
                        case ',':
                        case ':':
                        case '[':
                        case ']':
                        case KeycodeConstants.KEYCODE_MOVE_END /*123*/:
                        case KeycodeConstants.KEYCODE_FORWARD /*125*/:
                            break;
                        case '#':
                        case '/':
                        case ';':
                        case '=':
                        case '\\':
                            m3029w();
                            break;
                        default:
                            i2++;
                            break;
                    }
                } else if (i2 >= this.f2333e.length) {
                    if (stringBuilder == null) {
                        stringBuilder = new StringBuilder(Math.max(i2, 16));
                    }
                    stringBuilder.append(this.f2333e, this.f2334f, i2);
                    this.f2334f += i2;
                } else if (m3023b(i2 + 1)) {
                }
                i = i2;
                if (stringBuilder != null) {
                    str = new String(this.f2333e, this.f2334f, i);
                } else {
                    stringBuilder.append(this.f2333e, this.f2334f, i);
                    str = stringBuilder.toString();
                }
                this.f2334f += i;
                return str;
            }
        } while (m3023b(1));
        if (stringBuilder != null) {
            stringBuilder.append(this.f2333e, this.f2334f, i);
            str = stringBuilder.toString();
        } else {
            str = new String(this.f2333e, this.f2334f, i);
        }
        this.f2334f += i;
        return str;
    }

    /* renamed from: c */
    private void m3024c(char c) {
        char[] cArr = this.f2333e;
        do {
            int i = this.f2334f;
            int i2 = this.f2335g;
            while (i < i2) {
                int i3 = i + 1;
                char c2 = cArr[i];
                if (c2 == c) {
                    this.f2334f = i3;
                    return;
                } else if (c2 == '\\') {
                    this.f2334f = i3;
                    m3031y();
                    i = this.f2334f;
                    i2 = this.f2335g;
                } else {
                    if (c2 == '\n') {
                        this.f2336h++;
                        this.f2337i = i3;
                    }
                    i = i3;
                }
            }
            this.f2334f = i;
        } while (m3023b(1));
        throw m3021b("Unterminated string");
    }

    /* renamed from: v */
    private void m3028v() {
        do {
            int i = 0;
            while (this.f2334f + i < this.f2335g) {
                switch (this.f2333e[this.f2334f + i]) {
                    case '\t':
                    case '\n':
                    case '\f':
                    case '\r':
                    case ' ':
                    case ',':
                    case ':':
                    case '[':
                    case ']':
                    case KeycodeConstants.KEYCODE_MOVE_END /*123*/:
                    case KeycodeConstants.KEYCODE_FORWARD /*125*/:
                        break;
                    case '#':
                    case '/':
                    case ';':
                    case '=':
                    case '\\':
                        m3029w();
                        break;
                    default:
                        i++;
                }
                this.f2334f += i;
                return;
            }
            this.f2334f += i;
        } while (m3023b(1));
    }

    /* renamed from: m */
    public int mo606m() {
        /* JADX: method processing error */
/*
Error: java.lang.NullPointerException
	at jadx.core.dex.visitors.regions.ProcessTryCatchRegions.searchTryCatchDominators(ProcessTryCatchRegions.java:75)
	at jadx.core.dex.visitors.regions.ProcessTryCatchRegions.process(ProcessTryCatchRegions.java:45)
	at jadx.core.dex.visitors.regions.RegionMakerVisitor.postProcessRegions(RegionMakerVisitor.java:63)
	at jadx.core.dex.visitors.regions.RegionMakerVisitor.visit(RegionMakerVisitor.java:58)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:31)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:17)
	at jadx.core.ProcessClass.process(ProcessClass.java:34)
	at jadx.core.ProcessClass.processDependencies(ProcessClass.java:56)
	at jadx.core.ProcessClass.process(ProcessClass.java:39)
	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:282)
	at jadx.api.JavaClass.decompile(JavaClass.java:62)
	at jadx.api.JadxDecompiler.lambda$appendSourcesSave$0(JadxDecompiler.java:200)
	at jadx.api.JadxDecompiler$$Lambda$8/1201484275.run(Unknown Source)
*/
        /*
        r7 = this;
        r0 = r7.f2330a;
        if (r0 != 0) goto L_0x0008;
    L_0x0004:
        r0 = r7.m3050r();
    L_0x0008:
        r1 = 15;
        r2 = 0;
        if (r0 != r1) goto L_0x0046;
    L_0x000d:
        r0 = r7.f2338j;
        r0 = (int) r0;
        r3 = r7.f2338j;
        r5 = (long) r0;
        r1 = (r3 > r5 ? 1 : (r3 == r5 ? 0 : -1));
        if (r1 == 0) goto L_0x0037;
    L_0x0017:
        r0 = new java.lang.NumberFormatException;
        r1 = new java.lang.StringBuilder;
        r1.<init>();
        r2 = "Expected an int but was ";
        r1.append(r2);
        r2 = r7.f2338j;
        r1.append(r2);
        r7 = r7.m3051s();
        r1.append(r7);
        r7 = r1.toString();
        r0.<init>(r7);
        throw r0;
    L_0x0037:
        r7.f2330a = r2;
        r1 = r7.f2344p;
        r7 = r7.f2342n;
        r7 = r7 + -1;
        r2 = r1[r7];
        r2 = r2 + 1;
        r1[r7] = r2;
        return r0;
    L_0x0046:
        r1 = 16;
        if (r0 != r1) goto L_0x005f;
    L_0x004a:
        r0 = new java.lang.String;
        r1 = r7.f2333e;
        r3 = r7.f2334f;
        r4 = r7.f2339k;
        r0.<init>(r1, r3, r4);
        r7.f2340l = r0;
        r0 = r7.f2334f;
        r1 = r7.f2339k;
        r0 = r0 + r1;
        r7.f2334f = r0;
        goto L_0x00b9;
    L_0x005f:
        r1 = 10;
        r3 = 8;
        if (r0 == r3) goto L_0x008e;
    L_0x0065:
        r4 = 9;
        if (r0 == r4) goto L_0x008e;
    L_0x0069:
        if (r0 != r1) goto L_0x006c;
    L_0x006b:
        goto L_0x008e;
    L_0x006c:
        r0 = new java.lang.IllegalStateException;
        r1 = new java.lang.StringBuilder;
        r1.<init>();
        r2 = "Expected an int but was ";
        r1.append(r2);
        r2 = r7.mo599f();
        r1.append(r2);
        r7 = r7.m3051s();
        r1.append(r7);
        r7 = r1.toString();
        r0.<init>(r7);
        throw r0;
    L_0x008e:
        if (r0 != r1) goto L_0x0097;
    L_0x0090:
        r0 = r7.m3027u();
        r7.f2340l = r0;
        goto L_0x00a4;
    L_0x0097:
        if (r0 != r3) goto L_0x009c;
    L_0x0099:
        r0 = 39;
        goto L_0x009e;
    L_0x009c:
        r0 = 34;
    L_0x009e:
        r0 = r7.m3022b(r0);
        r7.f2340l = r0;
    L_0x00a4:
        r0 = r7.f2340l;	 Catch:{ NumberFormatException -> 0x00b9 }
        r0 = java.lang.Integer.parseInt(r0);	 Catch:{ NumberFormatException -> 0x00b9 }
        r7.f2330a = r2;	 Catch:{ NumberFormatException -> 0x00b9 }
        r1 = r7.f2344p;	 Catch:{ NumberFormatException -> 0x00b9 }
        r3 = r7.f2342n;	 Catch:{ NumberFormatException -> 0x00b9 }
        r3 = r3 + -1;	 Catch:{ NumberFormatException -> 0x00b9 }
        r4 = r1[r3];	 Catch:{ NumberFormatException -> 0x00b9 }
        r4 = r4 + 1;	 Catch:{ NumberFormatException -> 0x00b9 }
        r1[r3] = r4;	 Catch:{ NumberFormatException -> 0x00b9 }
        return r0;
    L_0x00b9:
        r0 = 11;
        r7.f2330a = r0;
        r0 = r7.f2340l;
        r0 = java.lang.Double.parseDouble(r0);
        r3 = (int) r0;
        r4 = (double) r3;
        r0 = (r4 > r0 ? 1 : (r4 == r0 ? 0 : -1));
        if (r0 == 0) goto L_0x00e9;
    L_0x00c9:
        r0 = new java.lang.NumberFormatException;
        r1 = new java.lang.StringBuilder;
        r1.<init>();
        r2 = "Expected an int but was ";
        r1.append(r2);
        r2 = r7.f2340l;
        r1.append(r2);
        r7 = r7.m3051s();
        r1.append(r7);
        r7 = r1.toString();
        r0.<init>(r7);
        throw r0;
    L_0x00e9:
        r0 = 0;
        r7.f2340l = r0;
        r7.f2330a = r2;
        r0 = r7.f2344p;
        r7 = r7.f2342n;
        r7 = r7 + -1;
        r1 = r0[r7];
        r1 = r1 + 1;
        r0[r7] = r1;
        return r3;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.a.a.d.a.m():int");
    }

    public void close() {
        this.f2330a = 0;
        this.f2341m[0] = 8;
        this.f2342n = 1;
        this.f2331c.close();
    }

    /* renamed from: n */
    public void mo607n() {
        int i = 0;
        do {
            int i2 = this.f2330a;
            if (i2 == 0) {
                i2 = m3050r();
            }
            if (i2 == 3) {
                m3017a(1);
                i++;
            } else if (i2 == 1) {
                m3017a(3);
                i++;
            } else if (i2 == 4) {
                this.f2342n--;
                i--;
            } else if (i2 == 2) {
                this.f2342n--;
                i--;
            } else {
                if (i2 != 14) {
                    if (i2 != 10) {
                        if (i2 != 8) {
                            if (i2 != 12) {
                                if (i2 != 9) {
                                    if (i2 != 13) {
                                        if (i2 == 16) {
                                            this.f2334f += this.f2339k;
                                        }
                                    }
                                }
                                m3024c('\"');
                            }
                        }
                        m3024c('\'');
                    }
                }
                m3028v();
            }
            this.f2330a = 0;
        } while (i != 0);
        int[] iArr = this.f2344p;
        i = this.f2342n - 1;
        iArr[i] = iArr[i] + 1;
        this.f2343o[this.f2342n - 1] = "null";
    }

    /* renamed from: a */
    private void m3017a(int i) {
        if (this.f2342n == this.f2341m.length) {
            Object obj = new int[(this.f2342n * 2)];
            Object obj2 = new int[(this.f2342n * 2)];
            Object obj3 = new String[(this.f2342n * 2)];
            System.arraycopy(this.f2341m, 0, obj, 0, this.f2342n);
            System.arraycopy(this.f2344p, 0, obj2, 0, this.f2342n);
            System.arraycopy(this.f2343o, 0, obj3, 0, this.f2342n);
            this.f2341m = obj;
            this.f2344p = obj2;
            this.f2343o = obj3;
        }
        int[] iArr = this.f2341m;
        int i2 = this.f2342n;
        this.f2342n = i2 + 1;
        iArr[i2] = i;
    }

    /* renamed from: b */
    private boolean m3023b(int i) {
        Object obj = this.f2333e;
        this.f2337i -= this.f2334f;
        if (this.f2335g != this.f2334f) {
            this.f2335g -= this.f2334f;
            System.arraycopy(obj, this.f2334f, obj, 0, this.f2335g);
        } else {
            this.f2335g = 0;
        }
        this.f2334f = 0;
        do {
            int read = this.f2331c.read(obj, this.f2335g, obj.length - this.f2335g);
            if (read == -1) {
                return false;
            }
            this.f2335g += read;
            if (this.f2336h == 0 && this.f2337i == 0 && this.f2335g > 0 && obj[0] == '﻿') {
                this.f2334f++;
                this.f2337i++;
                i++;
            }
        } while (this.f2335g < i);
        return true;
    }

    /* renamed from: b */
    private int m3020b(boolean z) {
        char[] cArr = this.f2333e;
        int i = this.f2334f;
        int i2 = this.f2335g;
        while (true) {
            if (i == i2) {
                this.f2334f = i;
                if (!m3023b(1)) {
                    break;
                }
                i = this.f2334f;
                i2 = this.f2335g;
            }
            int i3 = i + 1;
            char c = cArr[i];
            if (c == '\n') {
                this.f2336h++;
                this.f2337i = i3;
            } else if (!(c == ' ' || c == '\r')) {
                if (c != '\t') {
                    if (c == '/') {
                        this.f2334f = i3;
                        if (i3 == i2) {
                            this.f2334f--;
                            boolean b = m3023b(2);
                            this.f2334f++;
                            if (!b) {
                                return c;
                            }
                        }
                        m3029w();
                        char c2 = cArr[this.f2334f];
                        if (c2 == '*') {
                            this.f2334f++;
                            if (m3019a("*/")) {
                                i = this.f2334f + 2;
                                i2 = this.f2335g;
                            } else {
                                throw m3021b("Unterminated comment");
                            }
                        } else if (c2 != '/') {
                            return c;
                        } else {
                            this.f2334f++;
                            m3030x();
                            i = this.f2334f;
                            i2 = this.f2335g;
                        }
                    } else if (c == '#') {
                        this.f2334f = i3;
                        m3029w();
                        m3030x();
                        i = this.f2334f;
                        i2 = this.f2335g;
                    } else {
                        this.f2334f = i3;
                        return c;
                    }
                }
            }
            i = i3;
        }
        if (!z) {
            return -1;
        }
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("End of input");
        stringBuilder.append(m3051s());
        throw new EOFException(stringBuilder.toString());
    }

    /* renamed from: w */
    private void m3029w() {
        if (!this.f2332d) {
            throw m3021b("Use JsonReader.setLenient(true) to accept malformed JSON");
        }
    }

    /* renamed from: x */
    private void m3030x() {
        char c;
        do {
            if (this.f2334f < this.f2335g || m3023b(1)) {
                char[] cArr = this.f2333e;
                int i = this.f2334f;
                this.f2334f = i + 1;
                c = cArr[i];
                if (c == '\n') {
                    this.f2336h++;
                    this.f2337i = this.f2334f;
                    return;
                }
            } else {
                return;
            }
        } while (c != '\r');
    }

    /* renamed from: a */
    private boolean m3019a(String str) {
        int length = str.length();
        while (true) {
            int i = 0;
            if (this.f2334f + length > this.f2335g) {
                if (!m3023b(length)) {
                    return false;
                }
            }
            if (this.f2333e[this.f2334f] == '\n') {
                this.f2336h++;
                this.f2337i = this.f2334f + 1;
            } else {
                while (i < length) {
                    if (this.f2333e[this.f2334f + i] == str.charAt(i)) {
                        i++;
                    }
                }
                return true;
            }
            this.f2334f++;
        }
    }

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(getClass().getSimpleName());
        stringBuilder.append(m3051s());
        return stringBuilder.toString();
    }

    /* renamed from: s */
    String m3051s() {
        int i = this.f2336h + 1;
        int i2 = (this.f2334f - this.f2337i) + 1;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(" at line ");
        stringBuilder.append(i);
        stringBuilder.append(" column ");
        stringBuilder.append(i2);
        stringBuilder.append(" path ");
        stringBuilder.append(mo609p());
        return stringBuilder.toString();
    }

    /* renamed from: p */
    public String mo609p() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append('$');
        int i = this.f2342n;
        for (int i2 = 0; i2 < i; i2++) {
            switch (this.f2341m[i2]) {
                case 1:
                case 2:
                    stringBuilder.append('[');
                    stringBuilder.append(this.f2344p[i2]);
                    stringBuilder.append(']');
                    break;
                case 3:
                case 4:
                case 5:
                    stringBuilder.append('.');
                    if (this.f2343o[i2] == null) {
                        break;
                    }
                    stringBuilder.append(this.f2343o[i2]);
                    break;
                default:
                    break;
            }
        }
        return stringBuilder.toString();
    }

    /* renamed from: y */
    private char m3031y() {
        if (this.f2334f != this.f2335g || m3023b(1)) {
            char[] cArr = this.f2333e;
            int i = this.f2334f;
            this.f2334f = i + 1;
            char c = cArr[i];
            if (c == '\n') {
                this.f2336h++;
                this.f2337i = this.f2334f;
            } else if (!(c == '\"' || c == '\'' || c == '/' || c == '\\')) {
                if (c == 'b') {
                    return '\b';
                }
                if (c == 'f') {
                    return '\f';
                }
                if (c == 'n') {
                    return '\n';
                }
                if (c == 'r') {
                    return '\r';
                }
                switch (c) {
                    case 't':
                        return '\t';
                    case 'u':
                        if (this.f2334f + 4 <= this.f2335g || m3023b(4)) {
                            c = '\u0000';
                            int i2 = this.f2334f;
                            int i3 = i2 + 4;
                            while (i2 < i3) {
                                char c2 = this.f2333e[i2];
                                c = (char) (c << 4);
                                if (c2 >= '0' && c2 <= '9') {
                                    c = (char) (c + (c2 - 48));
                                } else if (c2 >= 'a' && c2 <= 'f') {
                                    c = (char) (c + ((c2 - 97) + 10));
                                } else if (c2 < 'A' || c2 > 'F') {
                                    StringBuilder stringBuilder = new StringBuilder();
                                    stringBuilder.append("\\u");
                                    stringBuilder.append(new String(this.f2333e, this.f2334f, 4));
                                    throw new NumberFormatException(stringBuilder.toString());
                                } else {
                                    c = (char) (c + ((c2 - 65) + 10));
                                }
                                i2++;
                            }
                            this.f2334f += 4;
                            return c;
                        }
                        throw m3021b("Unterminated escape sequence");
                    default:
                        throw m3021b("Invalid escape sequence");
                }
            }
            return c;
        }
        throw m3021b("Unterminated escape sequence");
    }

    /* renamed from: b */
    private IOException m3021b(String str) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(str);
        stringBuilder.append(m3051s());
        throw new C0735d(stringBuilder.toString());
    }

    /* renamed from: z */
    private void m3032z() {
        m3020b(true);
        this.f2334f--;
        if (this.f2334f + f2329b.length <= this.f2335g || m3023b(f2329b.length)) {
            int i = 0;
            while (i < f2329b.length) {
                if (this.f2333e[this.f2334f + i] == f2329b[i]) {
                    i++;
                } else {
                    return;
                }
            }
            this.f2334f += f2329b.length;
        }
    }
}
