package com.p031a.p032a.p038d;

import java.io.Closeable;
import java.io.Flushable;
import java.io.IOException;
import java.io.Writer;

/* compiled from: JsonWriter */
/* renamed from: com.a.a.d.c */
public class C0734c implements Closeable, Flushable {
    /* renamed from: a */
    private static final String[] f2356a = new String[128];
    /* renamed from: b */
    private static final String[] f2357b = ((String[]) f2356a.clone());
    /* renamed from: c */
    private final Writer f2358c;
    /* renamed from: d */
    private int[] f2359d = new int[32];
    /* renamed from: e */
    private int f2360e = 0;
    /* renamed from: f */
    private String f2361f;
    /* renamed from: g */
    private String f2362g;
    /* renamed from: h */
    private boolean f2363h;
    /* renamed from: i */
    private boolean f2364i;
    /* renamed from: j */
    private String f2365j;
    /* renamed from: k */
    private boolean f2366k;

    static {
        for (int i = 0; i <= 31; i++) {
            f2356a[i] = String.format("\\u%04x", new Object[]{Integer.valueOf(i)});
        }
        f2356a[34] = "\\\"";
        f2356a[92] = "\\\\";
        f2356a[9] = "\\t";
        f2356a[8] = "\\b";
        f2356a[10] = "\\n";
        f2356a[13] = "\\r";
        f2356a[12] = "\\f";
        f2357b[60] = "\\u003c";
        f2357b[62] = "\\u003e";
        f2357b[38] = "\\u0026";
        f2357b[61] = "\\u003d";
        f2357b[39] = "\\u0027";
    }

    public C0734c(Writer writer) {
        m3055a(6);
        this.f2362g = ":";
        this.f2366k = true;
        if (writer == null) {
            throw new NullPointerException("out == null");
        }
        this.f2358c = writer;
    }

    /* renamed from: c */
    public final void m3071c(String str) {
        if (str.length() == 0) {
            this.f2361f = null;
            this.f2362g = ":";
            return;
        }
        this.f2361f = str;
        this.f2362g = ": ";
    }

    /* renamed from: b */
    public final void m3069b(boolean z) {
        this.f2363h = z;
    }

    /* renamed from: g */
    public boolean m3077g() {
        return this.f2363h;
    }

    /* renamed from: c */
    public final void m3072c(boolean z) {
        this.f2364i = z;
    }

    /* renamed from: h */
    public final boolean m3078h() {
        return this.f2364i;
    }

    /* renamed from: d */
    public final void m3074d(boolean z) {
        this.f2366k = z;
    }

    /* renamed from: i */
    public final boolean m3079i() {
        return this.f2366k;
    }

    /* renamed from: b */
    public C0734c mo617b() {
        m3058j();
        return m3054a(1, "[");
    }

    /* renamed from: c */
    public C0734c mo619c() {
        return m3053a(1, 2, "]");
    }

    /* renamed from: d */
    public C0734c mo621d() {
        m3058j();
        return m3054a(3, "{");
    }

    /* renamed from: e */
    public C0734c mo622e() {
        return m3053a(3, 5, "}");
    }

    /* renamed from: a */
    private C0734c m3054a(int i, String str) {
        m3061m();
        m3055a(i);
        this.f2358c.write(str);
        return this;
    }

    /* renamed from: a */
    private C0734c m3053a(int i, int i2, String str) {
        int a = mo616a();
        if (a != i2 && a != i) {
            throw new IllegalStateException("Nesting problem.");
        } else if (this.f2365j != 0) {
            i2 = new StringBuilder();
            i2.append("Dangling name: ");
            i2.append(this.f2365j);
            throw new IllegalStateException(i2.toString());
        } else {
            this.f2360e--;
            if (a == i2) {
                m3059k();
            }
            this.f2358c.write(str);
            return this;
        }
    }

    /* renamed from: a */
    private void m3055a(int i) {
        if (this.f2360e == this.f2359d.length) {
            Object obj = new int[(this.f2360e * 2)];
            System.arraycopy(this.f2359d, 0, obj, 0, this.f2360e);
            this.f2359d = obj;
        }
        int[] iArr = this.f2359d;
        int i2 = this.f2360e;
        this.f2360e = i2 + 1;
        iArr[i2] = i;
    }

    /* renamed from: a */
    private int mo616a() {
        if (this.f2360e != 0) {
            return this.f2359d[this.f2360e - 1];
        }
        throw new IllegalStateException("JsonWriter is closed.");
    }

    /* renamed from: b */
    private void m3056b(int i) {
        this.f2359d[this.f2360e - 1] = i;
    }

    /* renamed from: a */
    public C0734c mo614a(String str) {
        if (str == null) {
            throw new NullPointerException("name == null");
        } else if (this.f2365j != null) {
            throw new IllegalStateException();
        } else if (this.f2360e == 0) {
            throw new IllegalStateException("JsonWriter is closed.");
        } else {
            this.f2365j = str;
            return this;
        }
    }

    /* renamed from: j */
    private void m3058j() {
        if (this.f2365j != null) {
            m3060l();
            m3057d(this.f2365j);
            this.f2365j = null;
        }
    }

    /* renamed from: b */
    public C0734c mo618b(String str) {
        if (str == null) {
            return mo623f();
        }
        m3058j();
        m3061m();
        m3057d(str);
        return this;
    }

    /* renamed from: f */
    public C0734c mo623f() {
        if (this.f2365j != null) {
            if (this.f2366k) {
                m3058j();
            } else {
                this.f2365j = null;
                return this;
            }
        }
        m3061m();
        this.f2358c.write("null");
        return this;
    }

    /* renamed from: a */
    public C0734c mo615a(boolean z) {
        m3058j();
        m3061m();
        this.f2358c.write(z ? "true" : "false");
        return this;
    }

    /* renamed from: a */
    public C0734c mo612a(Boolean bool) {
        if (bool == null) {
            return mo623f();
        }
        m3058j();
        m3061m();
        this.f2358c.write(bool.booleanValue() != null ? "true" : "false");
        return this;
    }

    /* renamed from: a */
    public C0734c mo611a(long j) {
        m3058j();
        m3061m();
        this.f2358c.write(Long.toString(j));
        return this;
    }

    /* renamed from: a */
    public C0734c mo613a(Number number) {
        if (number == null) {
            return mo623f();
        }
        m3058j();
        CharSequence obj = number.toString();
        if (this.f2363h || !(obj.equals("-Infinity") || obj.equals("Infinity") || obj.equals("NaN"))) {
            m3061m();
            this.f2358c.append(obj);
            return this;
        }
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Numeric values must be finite, but was ");
        stringBuilder.append(number);
        throw new IllegalArgumentException(stringBuilder.toString());
    }

    public void flush() {
        if (this.f2360e == 0) {
            throw new IllegalStateException("JsonWriter is closed.");
        }
        this.f2358c.flush();
    }

    public void close() {
        this.f2358c.close();
        int i = this.f2360e;
        if (i <= 1) {
            if (i != 1 || this.f2359d[i - 1] == 7) {
                this.f2360e = 0;
                return;
            }
        }
        throw new IOException("Incomplete document");
    }

    /* renamed from: d */
    private void m3057d(String str) {
        String[] strArr = this.f2364i ? f2357b : f2356a;
        this.f2358c.write("\"");
        int length = str.length();
        int i = 0;
        int i2 = 0;
        while (i < length) {
            String str2;
            char charAt = str.charAt(i);
            if (charAt < '') {
                str2 = strArr[charAt];
                if (str2 == null) {
                    i++;
                }
            } else if (charAt == ' ') {
                str2 = "\\u2028";
            } else if (charAt == ' ') {
                str2 = "\\u2029";
            } else {
                i++;
            }
            if (i2 < i) {
                this.f2358c.write(str, i2, i - i2);
            }
            this.f2358c.write(str2);
            i2 = i + 1;
            i++;
        }
        if (i2 < length) {
            this.f2358c.write(str, i2, length - i2);
        }
        this.f2358c.write("\"");
    }

    /* renamed from: k */
    private void m3059k() {
        if (this.f2361f != null) {
            this.f2358c.write("\n");
            int i = this.f2360e;
            for (int i2 = 1; i2 < i; i2++) {
                this.f2358c.write(this.f2361f);
            }
        }
    }

    /* renamed from: l */
    private void m3060l() {
        int a = mo616a();
        if (a == 5) {
            this.f2358c.write(44);
        } else if (a != 3) {
            throw new IllegalStateException("Nesting problem.");
        }
        m3059k();
        m3056b(4);
    }

    /* renamed from: m */
    private void m3061m() {
        switch (mo616a()) {
            case 1:
                m3056b(2);
                m3059k();
                return;
            case 2:
                this.f2358c.append(',');
                m3059k();
                return;
            case 4:
                this.f2358c.append(this.f2362g);
                m3056b(5);
                return;
            case 6:
                break;
            case 7:
                if (!this.f2363h) {
                    throw new IllegalStateException("JSON must have only one top-level value.");
                }
                break;
            default:
                throw new IllegalStateException("Nesting problem.");
        }
        m3056b(7);
    }
}
