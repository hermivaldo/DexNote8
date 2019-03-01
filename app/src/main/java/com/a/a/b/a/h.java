package com.a.a.b.a;

import com.a.a.b.g;
import com.a.a.c.a;
import com.a.a.d.c;
import com.a.a.e;
import com.a.a.t;
import com.a.a.u;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/* compiled from: ObjectTypeAdapter */
public final class h extends t<Object> {
    public static final u a = new u() {
        public <T> t<T> a(e eVar, a<T> aVar) {
            return aVar.getRawType() == Object.class ? new h(eVar) : null;
        }
    };
    private final e b;

    h(e eVar) {
        this.b = eVar;
    }

    public Object b(com.a.a.d.a aVar) {
        switch (aVar.f()) {
            case BEGIN_ARRAY:
                List arrayList = new ArrayList();
                aVar.a();
                while (aVar.e()) {
                    arrayList.add(b(aVar));
                }
                aVar.b();
                return arrayList;
            case BEGIN_OBJECT:
                Map gVar = new g();
                aVar.c();
                while (aVar.e()) {
                    gVar.put(aVar.g(), b(aVar));
                }
                aVar.d();
                return gVar;
            case STRING:
                return aVar.h();
            case NUMBER:
                return Double.valueOf(aVar.k());
            case BOOLEAN:
                return Boolean.valueOf(aVar.i());
            case NULL:
                aVar.j();
                return null;
            default:
                throw new IllegalStateException();
        }
    }

    public void a(c cVar, Object obj) {
        if (obj == null) {
            cVar.f();
            return;
        }
        t a = this.b.a(obj.getClass());
        if (a instanceof h) {
            cVar.d();
            cVar.e();
            return;
        }
        a.a(cVar, obj);
    }
}
