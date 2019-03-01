package com.a.a.b.a;

import com.a.a.d.a;
import com.a.a.d.b;
import com.a.a.g;
import com.a.a.l;
import com.a.a.m;
import com.a.a.o;
import java.io.Reader;
import java.util.Iterator;
import java.util.Map.Entry;

/* compiled from: JsonTreeReader */
public final class e extends a {
    private static final Reader b = new Reader() {
        public int read(char[] cArr, int i, int i2) {
            throw new AssertionError();
        }

        public void close() {
            throw new AssertionError();
        }
    };
    private static final Object c = new Object();
    private Object[] d;
    private int e;
    private String[] f;
    private int[] g;

    public void a() {
        a(b.BEGIN_ARRAY);
        a(((g) t()).iterator());
        this.g[this.e - 1] = 0;
    }

    public void b() {
        a(b.END_ARRAY);
        u();
        u();
        if (this.e > 0) {
            int[] iArr = this.g;
            int i = this.e - 1;
            iArr[i] = iArr[i] + 1;
        }
    }

    public void c() {
        a(b.BEGIN_OBJECT);
        a(((m) t()).o().iterator());
    }

    public void d() {
        a(b.END_OBJECT);
        u();
        u();
        if (this.e > 0) {
            int[] iArr = this.g;
            int i = this.e - 1;
            iArr[i] = iArr[i] + 1;
        }
    }

    public boolean e() {
        b f = f();
        return (f == b.END_OBJECT || f == b.END_ARRAY) ? false : true;
    }

    public b f() {
        if (this.e == 0) {
            return b.END_DOCUMENT;
        }
        Object t = t();
        if (t instanceof Iterator) {
            boolean z = this.d[this.e - 2] instanceof m;
            Iterator it = (Iterator) t;
            if (!it.hasNext()) {
                return z ? b.END_OBJECT : b.END_ARRAY;
            } else if (z) {
                return b.NAME;
            } else {
                a(it.next());
                return f();
            }
        } else if (t instanceof m) {
            return b.BEGIN_OBJECT;
        } else {
            if (t instanceof g) {
                return b.BEGIN_ARRAY;
            }
            if (t instanceof o) {
                o oVar = (o) t;
                if (oVar.q()) {
                    return b.STRING;
                }
                if (oVar.o()) {
                    return b.BOOLEAN;
                }
                if (oVar.p()) {
                    return b.NUMBER;
                }
                throw new AssertionError();
            } else if (t instanceof l) {
                return b.NULL;
            } else {
                if (t == c) {
                    throw new IllegalStateException("JsonReader is closed");
                }
                throw new AssertionError();
            }
        }
    }

    private Object t() {
        return this.d[this.e - 1];
    }

    private Object u() {
        Object[] objArr = this.d;
        int i = this.e - 1;
        this.e = i;
        Object obj = objArr[i];
        this.d[this.e] = null;
        return obj;
    }

    private void a(b bVar) {
        if (f() != bVar) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("Expected ");
            stringBuilder.append(bVar);
            stringBuilder.append(" but was ");
            stringBuilder.append(f());
            stringBuilder.append(v());
            throw new IllegalStateException(stringBuilder.toString());
        }
    }

    public String g() {
        a(b.NAME);
        Entry entry = (Entry) ((Iterator) t()).next();
        String str = (String) entry.getKey();
        this.f[this.e - 1] = str;
        a(entry.getValue());
        return str;
    }

    public String h() {
        b f = f();
        if (f == b.STRING || f == b.NUMBER) {
            String b = ((o) u()).b();
            if (this.e > 0) {
                int[] iArr = this.g;
                int i = this.e - 1;
                iArr[i] = iArr[i] + 1;
            }
            return b;
        }
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Expected ");
        stringBuilder.append(b.STRING);
        stringBuilder.append(" but was ");
        stringBuilder.append(f);
        stringBuilder.append(v());
        throw new IllegalStateException(stringBuilder.toString());
    }

    public boolean i() {
        a(b.BOOLEAN);
        boolean f = ((o) u()).f();
        if (this.e > 0) {
            int[] iArr = this.g;
            int i = this.e - 1;
            iArr[i] = iArr[i] + 1;
        }
        return f;
    }

    public void j() {
        a(b.NULL);
        u();
        if (this.e > 0) {
            int[] iArr = this.g;
            int i = this.e - 1;
            iArr[i] = iArr[i] + 1;
        }
    }

    public double k() {
        b f = f();
        StringBuilder stringBuilder;
        if (f == b.NUMBER || f == b.STRING) {
            double c = ((o) t()).c();
            if (q() || !(Double.isNaN(c) || Double.isInfinite(c))) {
                u();
                if (this.e > 0) {
                    int[] iArr = this.g;
                    int i = this.e - 1;
                    iArr[i] = iArr[i] + 1;
                }
                return c;
            }
            stringBuilder = new StringBuilder();
            stringBuilder.append("JSON forbids NaN and infinities: ");
            stringBuilder.append(c);
            throw new NumberFormatException(stringBuilder.toString());
        }
        stringBuilder = new StringBuilder();
        stringBuilder.append("Expected ");
        stringBuilder.append(b.NUMBER);
        stringBuilder.append(" but was ");
        stringBuilder.append(f);
        stringBuilder.append(v());
        throw new IllegalStateException(stringBuilder.toString());
    }

    public long l() {
        b f = f();
        if (f == b.NUMBER || f == b.STRING) {
            long d = ((o) t()).d();
            u();
            if (this.e > 0) {
                int[] iArr = this.g;
                int i = this.e - 1;
                iArr[i] = iArr[i] + 1;
            }
            return d;
        }
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Expected ");
        stringBuilder.append(b.NUMBER);
        stringBuilder.append(" but was ");
        stringBuilder.append(f);
        stringBuilder.append(v());
        throw new IllegalStateException(stringBuilder.toString());
    }

    public int m() {
        b f = f();
        if (f == b.NUMBER || f == b.STRING) {
            int e = ((o) t()).e();
            u();
            if (this.e > 0) {
                int[] iArr = this.g;
                int i = this.e - 1;
                iArr[i] = iArr[i] + 1;
            }
            return e;
        }
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Expected ");
        stringBuilder.append(b.NUMBER);
        stringBuilder.append(" but was ");
        stringBuilder.append(f);
        stringBuilder.append(v());
        throw new IllegalStateException(stringBuilder.toString());
    }

    public void close() {
        this.d = new Object[]{c};
        this.e = 1;
    }

    public void n() {
        if (f() == b.NAME) {
            g();
            this.f[this.e - 2] = "null";
        } else {
            u();
            if (this.e > 0) {
                this.f[this.e - 1] = "null";
            }
        }
        if (this.e > 0) {
            int[] iArr = this.g;
            int i = this.e - 1;
            iArr[i] = iArr[i] + 1;
        }
    }

    public String toString() {
        return getClass().getSimpleName();
    }

    public void o() {
        a(b.NAME);
        Entry entry = (Entry) ((Iterator) t()).next();
        a(entry.getValue());
        a(new o((String) entry.getKey()));
    }

    private void a(Object obj) {
        if (this.e == this.d.length) {
            Object obj2 = new Object[(this.e * 2)];
            Object obj3 = new int[(this.e * 2)];
            Object obj4 = new String[(this.e * 2)];
            System.arraycopy(this.d, 0, obj2, 0, this.e);
            System.arraycopy(this.g, 0, obj3, 0, this.e);
            System.arraycopy(this.f, 0, obj4, 0, this.e);
            this.d = obj2;
            this.g = obj3;
            this.f = obj4;
        }
        Object[] objArr = this.d;
        int i = this.e;
        this.e = i + 1;
        objArr[i] = obj;
    }

    public String p() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append('$');
        int i = 0;
        while (i < this.e) {
            if (this.d[i] instanceof g) {
                i++;
                if (this.d[i] instanceof Iterator) {
                    stringBuilder.append('[');
                    stringBuilder.append(this.g[i]);
                    stringBuilder.append(']');
                }
            } else if (this.d[i] instanceof m) {
                i++;
                if (this.d[i] instanceof Iterator) {
                    stringBuilder.append('.');
                    if (this.f[i] != null) {
                        stringBuilder.append(this.f[i]);
                    }
                }
            }
            i++;
        }
        return stringBuilder.toString();
    }

    private String v() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(" at path ");
        stringBuilder.append(p());
        return stringBuilder.toString();
    }
}
