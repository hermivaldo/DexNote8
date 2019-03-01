package com.a.a.b.a;

import com.a.a.d.c;
import com.a.a.g;
import com.a.a.j;
import com.a.a.l;
import com.a.a.m;
import com.a.a.o;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

/* compiled from: JsonTreeWriter */
public final class f extends c {
    private static final Writer a = new Writer() {
        public void write(char[] cArr, int i, int i2) {
            throw new AssertionError();
        }

        public void flush() {
            throw new AssertionError();
        }

        public void close() {
            throw new AssertionError();
        }
    };
    private static final o b = new o("closed");
    private final List<j> c = new ArrayList();
    private String d;
    private j e = l.a;

    public void flush() {
    }

    public f() {
        super(a);
    }

    public j a() {
        if (this.c.isEmpty()) {
            return this.e;
        }
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Expected one JSON element but was ");
        stringBuilder.append(this.c);
        throw new IllegalStateException(stringBuilder.toString());
    }

    private j j() {
        return (j) this.c.get(this.c.size() - 1);
    }

    private void a(j jVar) {
        if (this.d != null) {
            if (!jVar.j() || i()) {
                ((m) j()).a(this.d, jVar);
            }
            this.d = null;
        } else if (this.c.isEmpty()) {
            this.e = jVar;
        } else {
            j j = j();
            if (j instanceof g) {
                ((g) j).a(jVar);
                return;
            }
            throw new IllegalStateException();
        }
    }

    public c b() {
        j gVar = new g();
        a(gVar);
        this.c.add(gVar);
        return this;
    }

    public c c() {
        if (this.c.isEmpty() || this.d != null) {
            throw new IllegalStateException();
        } else if (j() instanceof g) {
            this.c.remove(this.c.size() - 1);
            return this;
        } else {
            throw new IllegalStateException();
        }
    }

    public c d() {
        j mVar = new m();
        a(mVar);
        this.c.add(mVar);
        return this;
    }

    public c e() {
        if (this.c.isEmpty() || this.d != null) {
            throw new IllegalStateException();
        } else if (j() instanceof m) {
            this.c.remove(this.c.size() - 1);
            return this;
        } else {
            throw new IllegalStateException();
        }
    }

    public c a(String str) {
        if (this.c.isEmpty() || this.d != null) {
            throw new IllegalStateException();
        } else if (j() instanceof m) {
            this.d = str;
            return this;
        } else {
            throw new IllegalStateException();
        }
    }

    public c b(String str) {
        if (str == null) {
            return f();
        }
        a(new o(str));
        return this;
    }

    public c f() {
        a(l.a);
        return this;
    }

    public c a(boolean z) {
        a(new o(Boolean.valueOf(z)));
        return this;
    }

    public c a(Boolean bool) {
        if (bool == null) {
            return f();
        }
        a(new o(bool));
        return this;
    }

    public c a(long j) {
        a(new o(Long.valueOf(j)));
        return this;
    }

    public c a(Number number) {
        if (number == null) {
            return f();
        }
        if (!g()) {
            double doubleValue = number.doubleValue();
            if (Double.isNaN(doubleValue) || Double.isInfinite(doubleValue)) {
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append("JSON forbids NaN and infinities: ");
                stringBuilder.append(number);
                throw new IllegalArgumentException(stringBuilder.toString());
            }
        }
        a(new o(number));
        return this;
    }

    public void close() {
        if (this.c.isEmpty()) {
            this.c.add(b);
            return;
        }
        throw new IOException("Incomplete document");
    }
}
