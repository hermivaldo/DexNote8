package com.a.a.d;

import com.a.a.b.e;
import com.samsung.android.lxd.processor.network.channel.pty.internal.KeycodeConstants;
import java.io.Closeable;
import java.io.EOFException;
import java.io.IOException;
import java.io.Reader;

/* compiled from: JsonReader */
public class a implements Closeable {
    private static final char[] b = ")]}'\n".toCharArray();
    int a = 0;
    private final Reader c;
    private boolean d = false;
    private final char[] e = new char[1024];
    private int f = 0;
    private int g = 0;
    private int h = 0;
    private int i = 0;
    private long j;
    private int k;
    private String l;
    private int[] m = new int[32];
    private int n = 0;
    private String[] o;
    private int[] p;

    static {
        e.a = new e() {
            public void a(a aVar) {
                if (aVar instanceof com.a.a.b.a.e) {
                    ((com.a.a.b.a.e) aVar).o();
                    return;
                }
                int i = aVar.a;
                if (i == 0) {
                    i = aVar.r();
                }
                if (i == 13) {
                    aVar.a = 9;
                } else if (i == 12) {
                    aVar.a = 8;
                } else if (i == 14) {
                    aVar.a = 10;
                } else {
                    StringBuilder stringBuilder = new StringBuilder();
                    stringBuilder.append("Expected a name but was ");
                    stringBuilder.append(aVar.f());
                    stringBuilder.append(aVar.s());
                    throw new IllegalStateException(stringBuilder.toString());
                }
            }
        };
    }

    public a(Reader reader) {
        int[] iArr = this.m;
        int i = this.n;
        this.n = i + 1;
        iArr[i] = 6;
        this.o = new String[32];
        this.p = new int[32];
        if (reader == null) {
            throw new NullPointerException("in == null");
        }
        this.c = reader;
    }

    public final void a(boolean z) {
        this.d = z;
    }

    public final boolean q() {
        return this.d;
    }

    public void a() {
        int i = this.a;
        if (i == 0) {
            i = r();
        }
        if (i == 3) {
            a(1);
            this.p[this.n - 1] = 0;
            this.a = 0;
            return;
        }
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Expected BEGIN_ARRAY but was ");
        stringBuilder.append(f());
        stringBuilder.append(s());
        throw new IllegalStateException(stringBuilder.toString());
    }

    public void b() {
        int i = this.a;
        if (i == 0) {
            i = r();
        }
        if (i == 4) {
            this.n--;
            int[] iArr = this.p;
            int i2 = this.n - 1;
            iArr[i2] = iArr[i2] + 1;
            this.a = 0;
            return;
        }
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Expected END_ARRAY but was ");
        stringBuilder.append(f());
        stringBuilder.append(s());
        throw new IllegalStateException(stringBuilder.toString());
    }

    public void c() {
        int i = this.a;
        if (i == 0) {
            i = r();
        }
        if (i == 1) {
            a(3);
            this.a = 0;
            return;
        }
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Expected BEGIN_OBJECT but was ");
        stringBuilder.append(f());
        stringBuilder.append(s());
        throw new IllegalStateException(stringBuilder.toString());
    }

    public void d() {
        int i = this.a;
        if (i == 0) {
            i = r();
        }
        if (i == 2) {
            this.n--;
            this.o[this.n] = null;
            int[] iArr = this.p;
            int i2 = this.n - 1;
            iArr[i2] = iArr[i2] + 1;
            this.a = 0;
            return;
        }
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Expected END_OBJECT but was ");
        stringBuilder.append(f());
        stringBuilder.append(s());
        throw new IllegalStateException(stringBuilder.toString());
    }

    public boolean e() {
        int i = this.a;
        if (i == 0) {
            i = r();
        }
        return (i == 2 || i == 4) ? false : true;
    }

    public b f() {
        int i = this.a;
        if (i == 0) {
            i = r();
        }
        switch (i) {
            case 1:
                return b.BEGIN_OBJECT;
            case 2:
                return b.END_OBJECT;
            case 3:
                return b.BEGIN_ARRAY;
            case 4:
                return b.END_ARRAY;
            case 5:
            case 6:
                return b.BOOLEAN;
            case 7:
                return b.NULL;
            case 8:
            case 9:
            case 10:
            case 11:
                return b.STRING;
            case 12:
            case 13:
            case 14:
                return b.NAME;
            case 15:
            case 16:
                return b.NUMBER;
            case 17:
                return b.END_DOCUMENT;
            default:
                throw new AssertionError();
        }
    }

    int r() {
        int b;
        int i = this.m[this.n - 1];
        if (i == 1) {
            this.m[this.n - 1] = 2;
        } else if (i == 2) {
            b = b(true);
            if (b != 44) {
                if (b == 59) {
                    w();
                } else if (b != 93) {
                    throw b("Unterminated array");
                } else {
                    this.a = 4;
                    return 4;
                }
            }
        } else if (i == 3 || i == 5) {
            int b2;
            this.m[this.n - 1] = 4;
            if (i == 5) {
                b2 = b(true);
                if (b2 != 44) {
                    if (b2 == 59) {
                        w();
                    } else if (b2 != KeycodeConstants.KEYCODE_FORWARD) {
                        throw b("Unterminated object");
                    } else {
                        this.a = 2;
                        return 2;
                    }
                }
            }
            b2 = b(true);
            if (b2 == 34) {
                this.a = 13;
                return 13;
            } else if (b2 == 39) {
                w();
                this.a = 12;
                return 12;
            } else if (b2 != KeycodeConstants.KEYCODE_FORWARD) {
                w();
                this.f--;
                if (a((char) b2)) {
                    this.a = 14;
                    return 14;
                }
                throw b("Expected name");
            } else if (i != 5) {
                this.a = 2;
                return 2;
            } else {
                throw b("Expected name");
            }
        } else if (i == 4) {
            this.m[this.n - 1] = 5;
            b = b(true);
            if (b != 58) {
                if (b != 61) {
                    throw b("Expected ':'");
                }
                w();
                if ((this.f < this.g || b(1)) && this.e[this.f] == '>') {
                    this.f++;
                }
            }
        } else if (i == 6) {
            if (this.d) {
                z();
            }
            this.m[this.n - 1] = 7;
        } else if (i == 7) {
            if (b(false) == -1) {
                this.a = 17;
                return 17;
            }
            w();
            this.f--;
        } else if (i == 8) {
            throw new IllegalStateException("JsonReader is closed");
        }
        b = b(true);
        if (b == 34) {
            this.a = 9;
            return 9;
        } else if (b != 39) {
            if (!(b == 44 || b == 59)) {
                if (b == 91) {
                    this.a = 3;
                    return 3;
                } else if (b != 93) {
                    if (b != KeycodeConstants.KEYCODE_MOVE_END) {
                        this.f--;
                        i = o();
                        if (i != 0) {
                            return i;
                        }
                        i = t();
                        if (i != 0) {
                            return i;
                        }
                        if (a(this.e[this.f])) {
                            w();
                            this.a = 10;
                            return 10;
                        }
                        throw b("Expected value");
                    }
                    this.a = 1;
                    return 1;
                } else if (i == 1) {
                    this.a = 4;
                    return 4;
                }
            }
            if (i == 1 || i == 2) {
                w();
                this.f--;
                this.a = 7;
                return 7;
            }
            throw b("Unexpected value");
        } else {
            w();
            this.a = 8;
            return 8;
        }
    }

    private int o() {
        String str;
        int i;
        char c = this.e[this.f];
        String str2;
        if (c == 't' || c == 'T') {
            str = "true";
            str2 = "TRUE";
            i = 5;
        } else if (c == 'f' || c == 'F') {
            str = "false";
            str2 = "FALSE";
            i = 6;
        } else if (c != 'n' && c != 'N') {
            return 0;
        } else {
            str = "null";
            str2 = "NULL";
            i = 7;
        }
        int length = str.length();
        int i2 = 1;
        while (i2 < length) {
            if (this.f + i2 >= this.g && !b(i2 + 1)) {
                return 0;
            }
            char c2 = this.e[this.f + i2];
            if (c2 != str.charAt(i2) && c2 != str2.charAt(i2)) {
                return 0;
            }
            i2++;
        }
        if ((this.f + length < this.g || b(length + 1)) && a(this.e[this.f + length])) {
            return 0;
        }
        this.f += length;
        this.a = i;
        return i;
    }

    /* Code decompiled incorrectly, please refer to instructions dump. */
    private int t() {
        char c;
        char[] cArr = this.e;
        int i = this.f;
        int i2 = 0;
        int i3 = this.g;
        int i4 = 1;
        int i5 = 0;
        int i6 = i5;
        int i7 = i6;
        long j = 0;
        while (true) {
            if (i + i5 == i3) {
                if (i5 == cArr.length) {
                    return i2;
                }
                if (b(i5 + 1)) {
                    i = this.f;
                    i3 = this.g;
                }
            }
            c = cArr[i + i5];
            int i8 = 3;
            if (c != '+') {
                if (c != 'E' && c != 'e') {
                    switch (c) {
                        case '-':
                            i8 = 6;
                            i2 = 0;
                            if (i6 == 0) {
                                i6 = 1;
                                i7 = i6;
                                break;
                            } else if (i6 != 5) {
                                return 0;
                            }
                        case '.':
                            i2 = 0;
                            if (i6 != 2) {
                                return 0;
                            }
                            i6 = i8;
                            break;
                        default:
                            if (c < '0' || c > '9') {
                                break;
                            }
                            if (i6 != 1 && i6 != 0) {
                                if (i6 != 2) {
                                    if (i6 == 3) {
                                        i2 = 0;
                                        i6 = 4;
                                    } else if (i6 == 5 || i6 == 6) {
                                        i2 = 0;
                                        i6 = 7;
                                    }
                                    break;
                                } else if (j == 0) {
                                    return 0;
                                } else {
                                    long j2 = (10 * j) - ((long) (c - 48));
                                    i2 = (j > -922337203685477580L ? 1 : (j == -922337203685477580L ? 0 : -1));
                                    i2 = (i2 > 0 || (i2 == 0 && j2 < j)) ? 1 : 0;
                                    j = j2;
                                    i4 = i2 & i4;
                                }
                            } else {
                                j = (long) (-(c - 48));
                                i6 = 2;
                            }
                            i2 = 0;
                            break;
                    }
                }
                i2 = 0;
                if (i6 != 2 && i6 != 4) {
                    return 0;
                }
                i6 = 5;
                i5++;
            } else {
                i8 = 6;
                i2 = 0;
                if (i6 != 5) {
                    return 0;
                }
            }
            i6 = i8;
            i5++;
        }
        if (a(c)) {
            return 0;
        }
        if (i6 == 2 && i4 != 0 && ((j != Long.MIN_VALUE || i7 != 0) && (j != 0 || i7 == 0))) {
            if (i7 == 0) {
                j = -j;
            }
            this.j = j;
            this.f += i5;
            this.a = 15;
            return 15;
        } else if (i6 != 2 && i6 != 4 && i6 != 7) {
            return 0;
        } else {
            this.k = i5;
            this.a = 16;
            return 16;
        }
    }

    private boolean a(char c) {
        switch (c) {
            case 9:
            case 10:
            case 12:
            case 13:
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
                w();
                break;
            default:
                return true;
        }
        return false;
    }

    public String g() {
        String u;
        int i = this.a;
        if (i == 0) {
            i = r();
        }
        if (i == 14) {
            u = u();
        } else if (i == 12) {
            u = b('\'');
        } else if (i == 13) {
            u = b('\"');
        } else {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("Expected a name but was ");
            stringBuilder.append(f());
            stringBuilder.append(s());
            throw new IllegalStateException(stringBuilder.toString());
        }
        this.a = 0;
        this.o[this.n - 1] = u;
        return u;
    }

    public String h() {
        String u;
        int i = this.a;
        if (i == 0) {
            i = r();
        }
        if (i == 10) {
            u = u();
        } else if (i == 8) {
            u = b('\'');
        } else if (i == 9) {
            u = b('\"');
        } else if (i == 11) {
            u = this.l;
            this.l = null;
        } else if (i == 15) {
            u = Long.toString(this.j);
        } else if (i == 16) {
            u = new String(this.e, this.f, this.k);
            this.f += this.k;
        } else {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("Expected a string but was ");
            stringBuilder.append(f());
            stringBuilder.append(s());
            throw new IllegalStateException(stringBuilder.toString());
        }
        this.a = 0;
        int[] iArr = this.p;
        int i2 = this.n - 1;
        iArr[i2] = iArr[i2] + 1;
        return u;
    }

    public boolean i() {
        int i = this.a;
        if (i == 0) {
            i = r();
        }
        int[] iArr;
        int i2;
        if (i == 5) {
            this.a = 0;
            iArr = this.p;
            i2 = this.n - 1;
            iArr[i2] = iArr[i2] + 1;
            return true;
        } else if (i == 6) {
            this.a = 0;
            iArr = this.p;
            i2 = this.n - 1;
            iArr[i2] = iArr[i2] + 1;
            return false;
        } else {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("Expected a boolean but was ");
            stringBuilder.append(f());
            stringBuilder.append(s());
            throw new IllegalStateException(stringBuilder.toString());
        }
    }

    public void j() {
        int i = this.a;
        if (i == 0) {
            i = r();
        }
        if (i == 7) {
            this.a = 0;
            int[] iArr = this.p;
            int i2 = this.n - 1;
            iArr[i2] = iArr[i2] + 1;
            return;
        }
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Expected null but was ");
        stringBuilder.append(f());
        stringBuilder.append(s());
        throw new IllegalStateException(stringBuilder.toString());
    }

    public double k() {
        int i = this.a;
        if (i == 0) {
            i = r();
        }
        if (i == 15) {
            this.a = 0;
            int[] iArr = this.p;
            int i2 = this.n - 1;
            iArr[i2] = iArr[i2] + 1;
            return (double) this.j;
        }
        if (i == 16) {
            this.l = new String(this.e, this.f, this.k);
            this.f += this.k;
        } else if (i == 8 || i == 9) {
            this.l = b(i == 8 ? '\'' : '\"');
        } else if (i == 10) {
            this.l = u();
        } else if (i != 11) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("Expected a double but was ");
            stringBuilder.append(f());
            stringBuilder.append(s());
            throw new IllegalStateException(stringBuilder.toString());
        }
        this.a = 11;
        double parseDouble = Double.parseDouble(this.l);
        if (this.d || !(Double.isNaN(parseDouble) || Double.isInfinite(parseDouble))) {
            this.l = null;
            this.a = 0;
            int[] iArr2 = this.p;
            int i3 = this.n - 1;
            iArr2[i3] = iArr2[i3] + 1;
            return parseDouble;
        }
        StringBuilder stringBuilder2 = new StringBuilder();
        stringBuilder2.append("JSON forbids NaN and infinities: ");
        stringBuilder2.append(parseDouble);
        stringBuilder2.append(s());
        throw new d(stringBuilder2.toString());
    }

    public long l() {
        StringBuilder stringBuilder;
        int i = this.a;
        if (i == 0) {
            i = r();
        }
        int[] iArr;
        if (i == 15) {
            this.a = 0;
            iArr = this.p;
            int i2 = this.n - 1;
            iArr[i2] = iArr[i2] + 1;
            return this.j;
        } else if (i == 16) {
            this.l = new String(this.e, this.f, this.k);
            this.f += this.k;
        } else if (i == 8 || i == 9 || i == 10) {
            if (i == 10) {
                this.l = u();
            } else {
                this.l = b(i == 8 ? '\'' : '\"');
            }
            try {
                long parseLong = Long.parseLong(this.l);
                this.a = 0;
                int[] iArr2 = this.p;
                int i3 = this.n - 1;
                iArr2[i3] = iArr2[i3] + 1;
                return parseLong;
            } catch (NumberFormatException unused) {
                this.a = 11;
                double parseDouble = Double.parseDouble(this.l);
                long j = (long) parseDouble;
                if (((double) j) != parseDouble) {
                    stringBuilder = new StringBuilder();
                    stringBuilder.append("Expected a long but was ");
                    stringBuilder.append(this.l);
                    stringBuilder.append(s());
                    throw new NumberFormatException(stringBuilder.toString());
                }
                this.l = null;
                this.a = 0;
                iArr = this.p;
                int i4 = this.n - 1;
                iArr[i4] = iArr[i4] + 1;
                return j;
            }
        } else {
            stringBuilder = new StringBuilder();
            stringBuilder.append("Expected a long but was ");
            stringBuilder.append(f());
            stringBuilder.append(s());
            throw new IllegalStateException(stringBuilder.toString());
        }
    }

    /* JADX WARNING: Missing block: B:23:0x005b, code:
            if (r1 != null) goto L_0x006b;
     */
    /* JADX WARNING: Missing block: B:24:0x005d, code:
            r1 = new java.lang.StringBuilder(java.lang.Math.max((r2 - r4) * 2, 16));
     */
    /* JADX WARNING: Missing block: B:25:0x006b, code:
            r1.append(r0, r4, r2 - r4);
            r9.f = r2;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private String b(char c) {
        char[] cArr = this.e;
        StringBuilder stringBuilder = null;
        do {
            int i = this.f;
            int i2 = this.g;
            while (true) {
                int i3 = i;
                while (i < i2) {
                    int i4 = i + 1;
                    char c2 = cArr[i];
                    if (c2 == c) {
                        this.f = i4;
                        i4 = (i4 - i3) - 1;
                        if (stringBuilder == null) {
                            return new String(cArr, i3, i4);
                        }
                        stringBuilder.append(cArr, i3, i4);
                        return stringBuilder.toString();
                    } else if (c2 == '\\') {
                        this.f = i4;
                        i4 = (i4 - i3) - 1;
                        if (stringBuilder == null) {
                            stringBuilder = new StringBuilder(Math.max((i4 + 1) * 2, 16));
                        }
                        stringBuilder.append(cArr, i3, i4);
                        stringBuilder.append(y());
                        i = this.f;
                        i2 = this.g;
                    } else {
                        if (c2 == 10) {
                            this.h++;
                            this.i = i4;
                        }
                        i = i4;
                    }
                }
                break;
            }
        } while (b(1));
        throw b("Unterminated string");
    }

    /* JADX WARNING: Removed duplicated region for block: B:20:0x0058  */
    /* JADX WARNING: Removed duplicated region for block: B:19:0x004e  */
    /* JADX WARNING: Missing block: B:13:0x002a, code:
            r0 = r1;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private String u() {
        String str;
        int i = 0;
        StringBuilder stringBuilder = null;
        do {
            int i2 = 0;
            while (true) {
                if (this.f + i2 < this.g) {
                    switch (this.e[this.f + i2]) {
                        case 9:
                        case 10:
                        case 12:
                        case 13:
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
                            w();
                            break;
                        default:
                            i2++;
                            break;
                    }
                } else if (i2 >= this.e.length) {
                    if (stringBuilder == null) {
                        stringBuilder = new StringBuilder(Math.max(i2, 16));
                    }
                    stringBuilder.append(this.e, this.f, i2);
                    this.f += i2;
                } else if (b(i2 + 1)) {
                }
            }
            if (stringBuilder != null) {
                str = new String(this.e, this.f, i);
            } else {
                stringBuilder.append(this.e, this.f, i);
                str = stringBuilder.toString();
            }
            this.f += i;
            return str;
        } while (b(1));
        if (stringBuilder != null) {
        }
        this.f += i;
        return str;
    }

    private void c(char c) {
        char[] cArr = this.e;
        do {
            int i = this.f;
            int i2 = this.g;
            while (i < i2) {
                int i3 = i + 1;
                char c2 = cArr[i];
                if (c2 == c) {
                    this.f = i3;
                    return;
                } else if (c2 == '\\') {
                    this.f = i3;
                    y();
                    i = this.f;
                    i2 = this.g;
                } else {
                    if (c2 == 10) {
                        this.h++;
                        this.i = i3;
                    }
                    i = i3;
                }
            }
            this.f = i;
        } while (b(1));
        throw b("Unterminated string");
    }

    private void v() {
        do {
            int i = 0;
            while (this.f + i < this.g) {
                switch (this.e[this.f + i]) {
                    case 9:
                    case 10:
                    case 12:
                    case 13:
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
                        w();
                        break;
                    default:
                        i++;
                }
                this.f += i;
                return;
            }
            this.f += i;
        } while (b(1));
    }

    public int m() {
        int i = this.a;
        if (i == 0) {
            i = r();
        }
        StringBuilder stringBuilder;
        int[] iArr;
        int i2;
        if (i == 15) {
            i = (int) this.j;
            if (this.j != ((long) i)) {
                stringBuilder = new StringBuilder();
                stringBuilder.append("Expected an int but was ");
                stringBuilder.append(this.j);
                stringBuilder.append(s());
                throw new NumberFormatException(stringBuilder.toString());
            }
            this.a = 0;
            iArr = this.p;
            i2 = this.n - 1;
            iArr[i2] = iArr[i2] + 1;
            return i;
        } else if (i == 16) {
            this.l = new String(this.e, this.f, this.k);
            this.f += this.k;
        } else if (i == 8 || i == 9 || i == 10) {
            if (i == 10) {
                this.l = u();
            } else {
                this.l = b(i == 8 ? '\'' : '\"');
            }
            int i3;
            try {
                i = Integer.parseInt(this.l);
                this.a = 0;
                iArr = this.p;
                i3 = this.n - 1;
                iArr[i3] = iArr[i3] + 1;
                return i;
            } catch (NumberFormatException unused) {
                this.a = 11;
                double parseDouble = Double.parseDouble(this.l);
                i3 = (int) parseDouble;
                if (((double) i3) != parseDouble) {
                    stringBuilder = new StringBuilder();
                    stringBuilder.append("Expected an int but was ");
                    stringBuilder.append(this.l);
                    stringBuilder.append(s());
                    throw new NumberFormatException(stringBuilder.toString());
                }
                this.l = null;
                this.a = 0;
                int[] iArr2 = this.p;
                i2 = this.n - 1;
                iArr2[i2] = iArr2[i2] + 1;
                return i3;
            }
        } else {
            stringBuilder = new StringBuilder();
            stringBuilder.append("Expected an int but was ");
            stringBuilder.append(f());
            stringBuilder.append(s());
            throw new IllegalStateException(stringBuilder.toString());
        }
    }

    public void close() {
        this.a = 0;
        this.m[0] = 8;
        this.n = 1;
        this.c.close();
    }

    public void n() {
        int i = 0;
        do {
            int i2 = this.a;
            if (i2 == 0) {
                i2 = r();
            }
            if (i2 == 3) {
                a(1);
                i++;
            } else if (i2 == 1) {
                a(3);
                i++;
            } else if (i2 == 4) {
                this.n--;
                i--;
            } else if (i2 == 2) {
                this.n--;
                i--;
            } else if (i2 == 14 || i2 == 10) {
                v();
            } else if (i2 == 8 || i2 == 12) {
                c('\'');
            } else if (i2 == 9 || i2 == 13) {
                c('\"');
            } else if (i2 == 16) {
                this.f += this.k;
            }
            this.a = 0;
        } while (i != 0);
        int[] iArr = this.p;
        i = this.n - 1;
        iArr[i] = iArr[i] + 1;
        this.o[this.n - 1] = "null";
    }

    private void a(int i) {
        if (this.n == this.m.length) {
            Object obj = new int[(this.n * 2)];
            Object obj2 = new int[(this.n * 2)];
            Object obj3 = new String[(this.n * 2)];
            System.arraycopy(this.m, 0, obj, 0, this.n);
            System.arraycopy(this.p, 0, obj2, 0, this.n);
            System.arraycopy(this.o, 0, obj3, 0, this.n);
            this.m = obj;
            this.p = obj2;
            this.o = obj3;
        }
        int[] iArr = this.m;
        int i2 = this.n;
        this.n = i2 + 1;
        iArr[i2] = i;
    }

    private boolean b(int i) {
        Object obj = this.e;
        this.i -= this.f;
        if (this.g != this.f) {
            this.g -= this.f;
            System.arraycopy(obj, this.f, obj, 0, this.g);
        } else {
            this.g = 0;
        }
        this.f = 0;
        do {
            int read = this.c.read(obj, this.g, obj.length - this.g);
            if (read == -1) {
                return false;
            }
            this.g += read;
            if (this.h == 0 && this.i == 0 && this.g > 0 && obj[0] == 65279) {
                this.f++;
                this.i++;
                i++;
            }
        } while (this.g < i);
        return true;
    }

    private int b(boolean z) {
        char[] cArr = this.e;
        int i = this.f;
        int i2 = this.g;
        while (true) {
            if (i == i2) {
                this.f = i;
                if (b(1)) {
                    i = this.f;
                    i2 = this.g;
                } else if (!z) {
                    return -1;
                } else {
                    StringBuilder stringBuilder = new StringBuilder();
                    stringBuilder.append("End of input");
                    stringBuilder.append(s());
                    throw new EOFException(stringBuilder.toString());
                }
            }
            int i3 = i + 1;
            char c = cArr[i];
            if (c == 10) {
                this.h++;
                this.i = i3;
            } else if (!(c == ' ' || c == 13 || c == 9)) {
                if (c == '/') {
                    this.f = i3;
                    if (i3 == i2) {
                        this.f--;
                        boolean b = b(2);
                        this.f++;
                        if (!b) {
                            return c;
                        }
                    }
                    w();
                    char c2 = cArr[this.f];
                    if (c2 == '*') {
                        this.f++;
                        if (a("*/")) {
                            i = this.f + 2;
                            i2 = this.g;
                        } else {
                            throw b("Unterminated comment");
                        }
                    } else if (c2 != '/') {
                        return c;
                    } else {
                        this.f++;
                        x();
                        i = this.f;
                        i2 = this.g;
                    }
                } else if (c == '#') {
                    this.f = i3;
                    w();
                    x();
                    i = this.f;
                    i2 = this.g;
                } else {
                    this.f = i3;
                    return c;
                }
            }
            i = i3;
        }
    }

    private void w() {
        if (!this.d) {
            throw b("Use JsonReader.setLenient(true) to accept malformed JSON");
        }
    }

    private void x() {
        char c;
        do {
            if (this.f < this.g || b(1)) {
                char[] cArr = this.e;
                int i = this.f;
                this.f = i + 1;
                c = cArr[i];
                if (c == 10) {
                    this.h++;
                    this.i = this.f;
                    return;
                }
            } else {
                return;
            }
        } while (c != 13);
    }

    private boolean a(String str) {
        int length = str.length();
        while (true) {
            int i = 0;
            if (this.f + length > this.g && !b(length)) {
                return false;
            }
            if (this.e[this.f] == 10) {
                this.h++;
                this.i = this.f + 1;
            } else {
                while (i < length) {
                    if (this.e[this.f + i] == str.charAt(i)) {
                        i++;
                    }
                }
                return true;
            }
            this.f++;
        }
    }

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(getClass().getSimpleName());
        stringBuilder.append(s());
        return stringBuilder.toString();
    }

    String s() {
        int i = this.h + 1;
        int i2 = (this.f - this.i) + 1;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(" at line ");
        stringBuilder.append(i);
        stringBuilder.append(" column ");
        stringBuilder.append(i2);
        stringBuilder.append(" path ");
        stringBuilder.append(p());
        return stringBuilder.toString();
    }

    public String p() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append('$');
        int i = this.n;
        for (int i2 = 0; i2 < i; i2++) {
            switch (this.m[i2]) {
                case 1:
                case 2:
                    stringBuilder.append('[');
                    stringBuilder.append(this.p[i2]);
                    stringBuilder.append(']');
                    break;
                case 3:
                case 4:
                case 5:
                    stringBuilder.append('.');
                    if (this.o[i2] == null) {
                        break;
                    }
                    stringBuilder.append(this.o[i2]);
                    break;
                default:
                    break;
            }
        }
        return stringBuilder.toString();
    }

    private char y() {
        if (this.f != this.g || b(1)) {
            char[] cArr = this.e;
            int i = this.f;
            this.f = i + 1;
            char c = cArr[i];
            if (c == 10) {
                this.h++;
                this.i = this.f;
            } else if (!(c == '\"' || c == '\'' || c == '/' || c == '\\')) {
                if (c == 'b') {
                    return 8;
                }
                if (c == 'f') {
                    return 12;
                }
                if (c == 'n') {
                    return 10;
                }
                if (c == 'r') {
                    return 13;
                }
                switch (c) {
                    case 't':
                        return 9;
                    case 'u':
                        if (this.f + 4 <= this.g || b(4)) {
                            c = 0;
                            int i2 = this.f;
                            int i3 = i2 + 4;
                            while (i2 < i3) {
                                int i4;
                                char c2 = this.e[i2];
                                c = (char) (c << 4);
                                if (c2 >= '0' && c2 <= '9') {
                                    i4 = c2 - 48;
                                } else if (c2 >= 'a' && c2 <= 'f') {
                                    i4 = (c2 - 97) + 10;
                                } else if (c2 < 'A' || c2 > 'F') {
                                    StringBuilder stringBuilder = new StringBuilder();
                                    stringBuilder.append("\\u");
                                    stringBuilder.append(new String(this.e, this.f, 4));
                                    throw new NumberFormatException(stringBuilder.toString());
                                } else {
                                    i4 = (c2 - 65) + 10;
                                }
                                c = (char) (c + i4);
                                i2++;
                            }
                            this.f += 4;
                            return c;
                        }
                        throw b("Unterminated escape sequence");
                    default:
                        throw b("Invalid escape sequence");
                }
            }
            return c;
        }
        throw b("Unterminated escape sequence");
    }

    private IOException b(String str) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(str);
        stringBuilder.append(s());
        throw new d(stringBuilder.toString());
    }

    private void z() {
        b(true);
        this.f--;
        if (this.f + b.length <= this.g || b(b.length)) {
            int i = 0;
            while (i < b.length) {
                if (this.e[this.f + i] == b[i]) {
                    i++;
                } else {
                    return;
                }
            }
            this.f += b.length;
        }
    }
}
