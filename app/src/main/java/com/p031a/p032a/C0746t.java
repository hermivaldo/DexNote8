package com.p031a.p032a;

import com.p031a.p032a.p034b.p035a.C1222f;
import com.p031a.p032a.p038d.C0732a;
import com.p031a.p032a.p038d.C0733b;
import com.p031a.p032a.p038d.C0734c;

/* compiled from: TypeAdapter */
/* renamed from: com.a.a.t */
public abstract class C0746t<T> {

    /* compiled from: TypeAdapter */
    /* renamed from: com.a.a.t$1 */
    class C12821 extends C0746t<T> {
        /* renamed from: a */
        final /* synthetic */ C0746t f4318a;

        C12821(C0746t c0746t) {
            this.f4318a = c0746t;
        }

        /* renamed from: a */
        public void mo591a(C0734c c0734c, T t) {
            if (t == null) {
                c0734c.mo623f();
            } else {
                this.f4318a.mo591a(c0734c, t);
            }
        }

        /* renamed from: b */
        public T mo592b(C0732a c0732a) {
            if (c0732a.mo599f() != C0733b.NULL) {
                return this.f4318a.mo592b(c0732a);
            }
            c0732a.mo603j();
            return null;
        }
    }

    /* renamed from: a */
    public abstract void mo591a(C0734c c0734c, T t);

    /* renamed from: b */
    public abstract T mo592b(C0732a c0732a);

    /* renamed from: a */
    public final C0746t<T> m3121a() {
        return new C12821(this);
    }

    /* renamed from: a */
    public final C0741j m3120a(T t) {
        try {
            C0734c c1222f = new C1222f();
            mo591a(c1222f, t);
            return c1222f.mo616a();
        } catch (Throwable e) {
            throw new C1275k(e);
        }
    }
}
