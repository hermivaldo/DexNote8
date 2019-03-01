package com.p031a.p032a;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* compiled from: JsonArray */
/* renamed from: com.a.a.g */
public final class C1274g extends C0741j implements Iterable<C0741j> {
    /* renamed from: a */
    private final List<C0741j> f4313a = new ArrayList();

    /* renamed from: a */
    public void m5974a(C0741j c0741j) {
        if (c0741j == null) {
            c0741j = C1276l.f4314a;
        }
        this.f4313a.add(c0741j);
    }

    public Iterator<C0741j> iterator() {
        return this.f4313a.iterator();
    }

    /* renamed from: a */
    public Number mo631a() {
        if (this.f4313a.size() == 1) {
            return ((C0741j) this.f4313a.get(0)).mo631a();
        }
        throw new IllegalStateException();
    }

    /* renamed from: b */
    public String mo632b() {
        if (this.f4313a.size() == 1) {
            return ((C0741j) this.f4313a.get(0)).mo632b();
        }
        throw new IllegalStateException();
    }

    /* renamed from: c */
    public double mo633c() {
        if (this.f4313a.size() == 1) {
            return ((C0741j) this.f4313a.get(0)).mo633c();
        }
        throw new IllegalStateException();
    }

    /* renamed from: d */
    public long mo634d() {
        if (this.f4313a.size() == 1) {
            return ((C0741j) this.f4313a.get(0)).mo634d();
        }
        throw new IllegalStateException();
    }

    /* renamed from: e */
    public int mo635e() {
        if (this.f4313a.size() == 1) {
            return ((C0741j) this.f4313a.get(0)).mo635e();
        }
        throw new IllegalStateException();
    }

    /* renamed from: f */
    public boolean mo636f() {
        if (this.f4313a.size() == 1) {
            return ((C0741j) this.f4313a.get(0)).mo636f();
        }
        throw new IllegalStateException();
    }

    public boolean equals(Object obj) {
        if (obj != this) {
            if (!(obj instanceof C1274g) || !((C1274g) obj).f4313a.equals(this.f4313a)) {
                return false;
            }
        }
        return true;
    }

    public int hashCode() {
        return this.f4313a.hashCode();
    }
}
