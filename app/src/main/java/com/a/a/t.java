package com.a.a;

import com.a.a.b.a.f;
import com.a.a.d.a;
import com.a.a.d.b;
import com.a.a.d.c;

/* compiled from: TypeAdapter */
public abstract class t<T> {
    public abstract void a(c cVar, T t);

    public abstract T b(a aVar);

    public final t<T> a() {
        return new t<T>() {
            public void a(c cVar, T t) {
                if (t == null) {
                    cVar.f();
                } else {
                    t.this.a(cVar, t);
                }
            }

            public T b(a aVar) {
                if (aVar.f() != b.NULL) {
                    return t.this.b(aVar);
                }
                aVar.j();
                return null;
            }
        };
    }

    public final j a(T t) {
        try {
            c fVar = new f();
            a(fVar, t);
            return fVar.a();
        } catch (Throwable e) {
            throw new k(e);
        }
    }
}
