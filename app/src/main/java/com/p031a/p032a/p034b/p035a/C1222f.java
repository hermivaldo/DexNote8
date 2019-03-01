package com.p031a.p032a.p034b.p035a;

import com.p031a.p032a.C0741j;
import com.p031a.p032a.C1274g;
import com.p031a.p032a.C1276l;
import com.p031a.p032a.C1277m;
import com.p031a.p032a.C1278o;
import com.p031a.p032a.p038d.C0734c;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

/* compiled from: JsonTreeWriter */
/* renamed from: com.a.a.b.a.f */
public final class C1222f extends C0734c {
    /* renamed from: a */
    private static final Writer f4202a = new C07051();
    /* renamed from: b */
    private static final C1278o f4203b = new C1278o("closed");
    /* renamed from: c */
    private final List<C0741j> f4204c = new ArrayList();
    /* renamed from: d */
    private String f4205d;
    /* renamed from: e */
    private C0741j f4206e = C1276l.f4314a;

    /* compiled from: JsonTreeWriter */
    /* renamed from: com.a.a.b.a.f$1 */
    static class C07051 extends Writer {
        C07051() {
        }

        public void write(char[] cArr, int i, int i2) {
            throw new AssertionError();
        }

        public void flush() {
            throw new AssertionError();
        }

        public void close() {
            throw new AssertionError();
        }
    }

    public void flush() {
    }

    public C1222f() {
        super(f4202a);
    }

    /* renamed from: a */
    public C0741j mo616a() {
        if (this.f4204c.isEmpty()) {
            return this.f4206e;
        }
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Expected one JSON element but was ");
        stringBuilder.append(this.f4204c);
        throw new IllegalStateException(stringBuilder.toString());
    }

    /* renamed from: j */
    private C0741j m5736j() {
        return (C0741j) this.f4204c.get(this.f4204c.size() - 1);
    }

    /* renamed from: a */
    private void m5735a(C0741j c0741j) {
        if (this.f4205d != null) {
            if (!c0741j.m3114j() || m3079i()) {
                ((C1277m) m5736j()).m5980a(this.f4205d, c0741j);
            }
            this.f4205d = null;
        } else if (this.f4204c.isEmpty()) {
            this.f4206e = c0741j;
        } else {
            C0741j j = m5736j();
            if (j instanceof C1274g) {
                ((C1274g) j).m5974a(c0741j);
                return;
            }
            throw new IllegalStateException();
        }
    }

    /* renamed from: b */
    public C0734c mo617b() {
        C0741j c1274g = new C1274g();
        m5735a(c1274g);
        this.f4204c.add(c1274g);
        return this;
    }

    /* renamed from: c */
    public C0734c mo619c() {
        if (!this.f4204c.isEmpty()) {
            if (this.f4205d == null) {
                if (m5736j() instanceof C1274g) {
                    this.f4204c.remove(this.f4204c.size() - 1);
                    return this;
                }
                throw new IllegalStateException();
            }
        }
        throw new IllegalStateException();
    }

    /* renamed from: d */
    public C0734c mo621d() {
        C0741j c1277m = new C1277m();
        m5735a(c1277m);
        this.f4204c.add(c1277m);
        return this;
    }

    /* renamed from: e */
    public C0734c mo622e() {
        if (!this.f4204c.isEmpty()) {
            if (this.f4205d == null) {
                if (m5736j() instanceof C1277m) {
                    this.f4204c.remove(this.f4204c.size() - 1);
                    return this;
                }
                throw new IllegalStateException();
            }
        }
        throw new IllegalStateException();
    }

    /* renamed from: a */
    public C0734c mo614a(String str) {
        if (!this.f4204c.isEmpty()) {
            if (this.f4205d == null) {
                if (m5736j() instanceof C1277m) {
                    this.f4205d = str;
                    return this;
                }
                throw new IllegalStateException();
            }
        }
        throw new IllegalStateException();
    }

    /* renamed from: b */
    public C0734c mo618b(String str) {
        if (str == null) {
            return mo623f();
        }
        m5735a(new C1278o(str));
        return this;
    }

    /* renamed from: f */
    public C0734c mo623f() {
        m5735a(C1276l.f4314a);
        return this;
    }

    /* renamed from: a */
    public C0734c mo615a(boolean z) {
        m5735a(new C1278o(Boolean.valueOf(z)));
        return this;
    }

    /* renamed from: a */
    public C0734c mo612a(Boolean bool) {
        if (bool == null) {
            return mo623f();
        }
        m5735a(new C1278o(bool));
        return this;
    }

    /* renamed from: a */
    public C0734c mo611a(long j) {
        m5735a(new C1278o(Long.valueOf(j)));
        return this;
    }

    /* renamed from: a */
    public C0734c mo613a(Number number) {
        if (number == null) {
            return mo623f();
        }
        if (!m3077g()) {
            double doubleValue = number.doubleValue();
            if (Double.isNaN(doubleValue) || Double.isInfinite(doubleValue)) {
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append("JSON forbids NaN and infinities: ");
                stringBuilder.append(number);
                throw new IllegalArgumentException(stringBuilder.toString());
            }
        }
        m5735a(new C1278o(number));
        return this;
    }

    public void close() {
        if (this.f4204c.isEmpty()) {
            this.f4204c.add(f4203b);
            return;
        }
        throw new IOException("Incomplete document");
    }
}
