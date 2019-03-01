package com.p031a.p032a;

import com.p031a.p032a.p034b.C0728j;
import com.p031a.p032a.p038d.C0734c;
import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;

/* compiled from: JsonElement */
/* renamed from: com.a.a.j */
public abstract class C0741j {
    /* renamed from: g */
    public boolean m3111g() {
        return this instanceof C1274g;
    }

    /* renamed from: h */
    public boolean m3112h() {
        return this instanceof C1277m;
    }

    /* renamed from: i */
    public boolean m3113i() {
        return this instanceof C1278o;
    }

    /* renamed from: j */
    public boolean m3114j() {
        return this instanceof C1276l;
    }

    /* renamed from: k */
    public C1277m m3115k() {
        if (m3112h()) {
            return (C1277m) this;
        }
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Not a JSON Object: ");
        stringBuilder.append(this);
        throw new IllegalStateException(stringBuilder.toString());
    }

    /* renamed from: l */
    public C1274g m3116l() {
        if (m3111g()) {
            return (C1274g) this;
        }
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Not a JSON Array: ");
        stringBuilder.append(this);
        throw new IllegalStateException(stringBuilder.toString());
    }

    /* renamed from: m */
    public C1278o m3117m() {
        if (m3113i()) {
            return (C1278o) this;
        }
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Not a JSON Primitive: ");
        stringBuilder.append(this);
        throw new IllegalStateException(stringBuilder.toString());
    }

    /* renamed from: f */
    public boolean mo636f() {
        throw new UnsupportedOperationException(getClass().getSimpleName());
    }

    /* renamed from: n */
    Boolean mo637n() {
        throw new UnsupportedOperationException(getClass().getSimpleName());
    }

    /* renamed from: a */
    public Number mo631a() {
        throw new UnsupportedOperationException(getClass().getSimpleName());
    }

    /* renamed from: b */
    public String mo632b() {
        throw new UnsupportedOperationException(getClass().getSimpleName());
    }

    /* renamed from: c */
    public double mo633c() {
        throw new UnsupportedOperationException(getClass().getSimpleName());
    }

    /* renamed from: d */
    public long mo634d() {
        throw new UnsupportedOperationException(getClass().getSimpleName());
    }

    /* renamed from: e */
    public int mo635e() {
        throw new UnsupportedOperationException(getClass().getSimpleName());
    }

    public String toString() {
        try {
            Writer stringWriter = new StringWriter();
            C0734c c0734c = new C0734c(stringWriter);
            c0734c.m3069b(true);
            C0728j.m3013a(this, c0734c);
            return stringWriter.toString();
        } catch (IOException e) {
            throw new AssertionError(e);
        }
    }
}
