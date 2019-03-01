package com.a.a.b.a;

import com.a.a.d.c;
import com.a.a.e;
import com.a.a.h;
import com.a.a.i;
import com.a.a.j;
import com.a.a.p;
import com.a.a.q;
import com.a.a.t;
import com.a.a.u;

/* compiled from: TreeTypeAdapter */
public final class l<T> extends t<T> {
    final e a;
    private final q<T> b;
    private final i<T> c;
    private final com.a.a.c.a<T> d;
    private final u e;
    private final a f = new a();
    private t<T> g;

    /* compiled from: TreeTypeAdapter */
    private final class a implements h, p {
        private a() {
        }
    }

    public l(q<T> qVar, i<T> iVar, e eVar, com.a.a.c.a<T> aVar, u uVar) {
        this.b = qVar;
        this.c = iVar;
        this.a = eVar;
        this.d = aVar;
        this.e = uVar;
    }

    public T b(com.a.a.d.a aVar) {
        if (this.c == null) {
            return b().b(aVar);
        }
        j a = com.a.a.b.j.a(aVar);
        if (a.j()) {
            return null;
        }
        return this.c.a(a, this.d.getType(), this.f);
    }

    public void a(c cVar, T t) {
        if (this.b == null) {
            b().a(cVar, t);
        } else if (t == null) {
            cVar.f();
        } else {
            com.a.a.b.j.a(this.b.a(t, this.d.getType(), this.f), cVar);
        }
    }

    private t<T> b() {
        t<T> tVar = this.g;
        if (tVar != null) {
            return tVar;
        }
        tVar = this.a.a(this.e, this.d);
        this.g = tVar;
        return tVar;
    }
}
