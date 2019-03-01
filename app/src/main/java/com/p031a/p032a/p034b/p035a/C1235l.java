package com.p031a.p032a.p034b.p035a;

import com.p031a.p032a.C0737e;
import com.p031a.p032a.C0739h;
import com.p031a.p032a.C0740i;
import com.p031a.p032a.C0743p;
import com.p031a.p032a.C0744q;
import com.p031a.p032a.C0746t;
import com.p031a.p032a.C0747u;
import com.p031a.p032a.p034b.C0728j;
import com.p031a.p032a.p037c.C0731a;
import com.p031a.p032a.p038d.C0732a;
import com.p031a.p032a.p038d.C0734c;

/* compiled from: TreeTypeAdapter */
/* renamed from: com.a.a.b.a.l */
public final class C1235l<T> extends C0746t<T> {
    /* renamed from: a */
    final C0737e f4233a;
    /* renamed from: b */
    private final C0744q<T> f4234b;
    /* renamed from: c */
    private final C0740i<T> f4235c;
    /* renamed from: d */
    private final C0731a<T> f4236d;
    /* renamed from: e */
    private final C0747u f4237e;
    /* renamed from: f */
    private final C1234a f4238f = new C1234a();
    /* renamed from: g */
    private C0746t<T> f4239g;

    /* compiled from: TreeTypeAdapter */
    /* renamed from: com.a.a.b.a.l$a */
    private final class C1234a implements C0739h, C0743p {
        /* renamed from: a */
        final /* synthetic */ C1235l f4232a;

        private C1234a(C1235l c1235l) {
            this.f4232a = c1235l;
        }
    }

    public C1235l(C0744q<T> c0744q, C0740i<T> c0740i, C0737e c0737e, C0731a<T> c0731a, C0747u c0747u) {
        this.f4234b = c0744q;
        this.f4235c = c0740i;
        this.f4233a = c0737e;
        this.f4236d = c0731a;
        this.f4237e = c0747u;
    }

    /* renamed from: b */
    public T mo592b(C0732a c0732a) {
        if (this.f4235c == null) {
            return m5780b().mo592b(c0732a);
        }
        c0732a = C0728j.m3011a(c0732a);
        if (c0732a.m3114j()) {
            return null;
        }
        return this.f4235c.m3104a(c0732a, this.f4236d.getType(), this.f4238f);
    }

    /* renamed from: a */
    public void mo591a(C0734c c0734c, T t) {
        if (this.f4234b == null) {
            m5780b().mo591a(c0734c, t);
        } else if (t == null) {
            c0734c.mo623f();
        } else {
            C0728j.m3013a(this.f4234b.m3119a(t, this.f4236d.getType(), this.f4238f), c0734c);
        }
    }

    /* renamed from: b */
    private C0746t<T> m5780b() {
        C0746t<T> c0746t = this.f4239g;
        if (c0746t != null) {
            return c0746t;
        }
        c0746t = this.f4233a.m3091a(this.f4237e, this.f4236d);
        this.f4239g = c0746t;
        return c0746t;
    }
}
