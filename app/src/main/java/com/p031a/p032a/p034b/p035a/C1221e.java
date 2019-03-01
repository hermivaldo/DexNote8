package com.p031a.p032a.p034b.p035a;

import com.p031a.p032a.C1274g;
import com.p031a.p032a.C1276l;
import com.p031a.p032a.C1277m;
import com.p031a.p032a.C1278o;
import com.p031a.p032a.p038d.C0732a;
import com.p031a.p032a.p038d.C0733b;
import java.io.Reader;
import java.util.Iterator;
import java.util.Map.Entry;

/* compiled from: JsonTreeReader */
/* renamed from: com.a.a.b.a.e */
public final class C1221e extends C0732a {
    /* renamed from: b */
    private static final Reader f4196b = new C07041();
    /* renamed from: c */
    private static final Object f4197c = new Object();
    /* renamed from: d */
    private Object[] f4198d;
    /* renamed from: e */
    private int f4199e;
    /* renamed from: f */
    private String[] f4200f;
    /* renamed from: g */
    private int[] f4201g;

    /* compiled from: JsonTreeReader */
    /* renamed from: com.a.a.b.a.e$1 */
    static class C07041 extends Reader {
        C07041() {
        }

        public int read(char[] cArr, int i, int i2) {
            throw new AssertionError();
        }

        public void close() {
            throw new AssertionError();
        }
    }

    /* renamed from: a */
    public void mo593a() {
        m5714a(C0733b.BEGIN_ARRAY);
        m5715a(((C1274g) m5716t()).iterator());
        this.f4201g[this.f4199e - 1] = 0;
    }

    /* renamed from: b */
    public void mo594b() {
        m5714a(C0733b.END_ARRAY);
        m5717u();
        m5717u();
        if (this.f4199e > 0) {
            int[] iArr = this.f4201g;
            int i = this.f4199e - 1;
            iArr[i] = iArr[i] + 1;
        }
    }

    /* renamed from: c */
    public void mo595c() {
        m5714a(C0733b.BEGIN_OBJECT);
        m5715a(((C1277m) m5716t()).m5981o().iterator());
    }

    /* renamed from: d */
    public void mo597d() {
        m5714a(C0733b.END_OBJECT);
        m5717u();
        m5717u();
        if (this.f4199e > 0) {
            int[] iArr = this.f4201g;
            int i = this.f4199e - 1;
            iArr[i] = iArr[i] + 1;
        }
    }

    /* renamed from: e */
    public boolean mo598e() {
        C0733b f = mo599f();
        return (f == C0733b.END_OBJECT || f == C0733b.END_ARRAY) ? false : true;
    }

    /* renamed from: f */
    public C0733b mo599f() {
        if (this.f4199e == 0) {
            return C0733b.END_DOCUMENT;
        }
        Object t = m5716t();
        if (t instanceof Iterator) {
            boolean z = this.f4198d[this.f4199e - 2] instanceof C1277m;
            Iterator it = (Iterator) t;
            if (!it.hasNext()) {
                return z ? C0733b.END_OBJECT : C0733b.END_ARRAY;
            } else if (z) {
                return C0733b.NAME;
            } else {
                m5715a(it.next());
                return mo599f();
            }
        } else if (t instanceof C1277m) {
            return C0733b.BEGIN_OBJECT;
        } else {
            if (t instanceof C1274g) {
                return C0733b.BEGIN_ARRAY;
            }
            if (t instanceof C1278o) {
                C1278o c1278o = (C1278o) t;
                if (c1278o.m5994q()) {
                    return C0733b.STRING;
                }
                if (c1278o.m5992o()) {
                    return C0733b.BOOLEAN;
                }
                if (c1278o.m5993p()) {
                    return C0733b.NUMBER;
                }
                throw new AssertionError();
            } else if (t instanceof C1276l) {
                return C0733b.NULL;
            } else {
                if (t == f4197c) {
                    throw new IllegalStateException("JsonReader is closed");
                }
                throw new AssertionError();
            }
        }
    }

    /* renamed from: t */
    private Object m5716t() {
        return this.f4198d[this.f4199e - 1];
    }

    /* renamed from: u */
    private Object m5717u() {
        Object[] objArr = this.f4198d;
        int i = this.f4199e - 1;
        this.f4199e = i;
        Object obj = objArr[i];
        this.f4198d[this.f4199e] = null;
        return obj;
    }

    /* renamed from: a */
    private void m5714a(C0733b c0733b) {
        if (mo599f() != c0733b) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("Expected ");
            stringBuilder.append(c0733b);
            stringBuilder.append(" but was ");
            stringBuilder.append(mo599f());
            stringBuilder.append(m5718v());
            throw new IllegalStateException(stringBuilder.toString());
        }
    }

    /* renamed from: g */
    public String mo600g() {
        m5714a(C0733b.NAME);
        Entry entry = (Entry) ((Iterator) m5716t()).next();
        String str = (String) entry.getKey();
        this.f4200f[this.f4199e - 1] = str;
        m5715a(entry.getValue());
        return str;
    }

    /* renamed from: h */
    public String mo601h() {
        C0733b f = mo599f();
        if (f == C0733b.STRING || f == C0733b.NUMBER) {
            String b = ((C1278o) m5717u()).mo632b();
            if (this.f4199e > 0) {
                int[] iArr = this.f4201g;
                int i = this.f4199e - 1;
                iArr[i] = iArr[i] + 1;
            }
            return b;
        }
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Expected ");
        stringBuilder.append(C0733b.STRING);
        stringBuilder.append(" but was ");
        stringBuilder.append(f);
        stringBuilder.append(m5718v());
        throw new IllegalStateException(stringBuilder.toString());
    }

    /* renamed from: i */
    public boolean mo602i() {
        m5714a(C0733b.BOOLEAN);
        boolean f = ((C1278o) m5717u()).mo636f();
        if (this.f4199e > 0) {
            int[] iArr = this.f4201g;
            int i = this.f4199e - 1;
            iArr[i] = iArr[i] + 1;
        }
        return f;
    }

    /* renamed from: j */
    public void mo603j() {
        m5714a(C0733b.NULL);
        m5717u();
        if (this.f4199e > 0) {
            int[] iArr = this.f4201g;
            int i = this.f4199e - 1;
            iArr[i] = iArr[i] + 1;
        }
    }

    /* renamed from: k */
    public double mo604k() {
        C0733b f = mo599f();
        if (f == C0733b.NUMBER || f == C0733b.STRING) {
            double c = ((C1278o) m5716t()).mo633c();
            if (m3049q() || !(Double.isNaN(c) || Double.isInfinite(c))) {
                m5717u();
                if (this.f4199e > 0) {
                    int[] iArr = this.f4201g;
                    int i = this.f4199e - 1;
                    iArr[i] = iArr[i] + 1;
                }
                return c;
            }
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("JSON forbids NaN and infinities: ");
            stringBuilder.append(c);
            throw new NumberFormatException(stringBuilder.toString());
        }
        stringBuilder = new StringBuilder();
        stringBuilder.append("Expected ");
        stringBuilder.append(C0733b.NUMBER);
        stringBuilder.append(" but was ");
        stringBuilder.append(f);
        stringBuilder.append(m5718v());
        throw new IllegalStateException(stringBuilder.toString());
    }

    /* renamed from: l */
    public long mo605l() {
        C0733b f = mo599f();
        if (f == C0733b.NUMBER || f == C0733b.STRING) {
            long d = ((C1278o) m5716t()).mo634d();
            m5717u();
            if (this.f4199e > 0) {
                int[] iArr = this.f4201g;
                int i = this.f4199e - 1;
                iArr[i] = iArr[i] + 1;
            }
            return d;
        }
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Expected ");
        stringBuilder.append(C0733b.NUMBER);
        stringBuilder.append(" but was ");
        stringBuilder.append(f);
        stringBuilder.append(m5718v());
        throw new IllegalStateException(stringBuilder.toString());
    }

    /* renamed from: m */
    public int mo606m() {
        C0733b f = mo599f();
        if (f == C0733b.NUMBER || f == C0733b.STRING) {
            int e = ((C1278o) m5716t()).mo635e();
            m5717u();
            if (this.f4199e > 0) {
                int[] iArr = this.f4201g;
                int i = this.f4199e - 1;
                iArr[i] = iArr[i] + 1;
            }
            return e;
        }
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Expected ");
        stringBuilder.append(C0733b.NUMBER);
        stringBuilder.append(" but was ");
        stringBuilder.append(f);
        stringBuilder.append(m5718v());
        throw new IllegalStateException(stringBuilder.toString());
    }

    public void close() {
        this.f4198d = new Object[]{f4197c};
        this.f4199e = 1;
    }

    /* renamed from: n */
    public void mo607n() {
        if (mo599f() == C0733b.NAME) {
            mo600g();
            this.f4200f[this.f4199e - 2] = "null";
        } else {
            m5717u();
            if (this.f4199e > 0) {
                this.f4200f[this.f4199e - 1] = "null";
            }
        }
        if (this.f4199e > 0) {
            int[] iArr = this.f4201g;
            int i = this.f4199e - 1;
            iArr[i] = iArr[i] + 1;
        }
    }

    public String toString() {
        return getClass().getSimpleName();
    }

    /* renamed from: o */
    public void mo608o() {
        m5714a(C0733b.NAME);
        Entry entry = (Entry) ((Iterator) m5716t()).next();
        m5715a(entry.getValue());
        m5715a(new C1278o((String) entry.getKey()));
    }

    /* renamed from: a */
    private void m5715a(Object obj) {
        if (this.f4199e == this.f4198d.length) {
            Object obj2 = new Object[(this.f4199e * 2)];
            Object obj3 = new int[(this.f4199e * 2)];
            Object obj4 = new String[(this.f4199e * 2)];
            System.arraycopy(this.f4198d, 0, obj2, 0, this.f4199e);
            System.arraycopy(this.f4201g, 0, obj3, 0, this.f4199e);
            System.arraycopy(this.f4200f, 0, obj4, 0, this.f4199e);
            this.f4198d = obj2;
            this.f4201g = obj3;
            this.f4200f = obj4;
        }
        Object[] objArr = this.f4198d;
        int i = this.f4199e;
        this.f4199e = i + 1;
        objArr[i] = obj;
    }

    /* renamed from: p */
    public String mo609p() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append('$');
        int i = 0;
        while (i < this.f4199e) {
            if (this.f4198d[i] instanceof C1274g) {
                i++;
                if (this.f4198d[i] instanceof Iterator) {
                    stringBuilder.append('[');
                    stringBuilder.append(this.f4201g[i]);
                    stringBuilder.append(']');
                }
            } else if (this.f4198d[i] instanceof C1277m) {
                i++;
                if (this.f4198d[i] instanceof Iterator) {
                    stringBuilder.append('.');
                    if (this.f4200f[i] != null) {
                        stringBuilder.append(this.f4200f[i]);
                    }
                }
            }
            i++;
        }
        return stringBuilder.toString();
    }

    /* renamed from: v */
    private String m5718v() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(" at path ");
        stringBuilder.append(mo609p());
        return stringBuilder.toString();
    }
}
