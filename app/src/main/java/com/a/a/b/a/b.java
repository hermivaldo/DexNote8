package com.a.a.b.a;

import com.a.a.b.c;
import com.a.a.b.h;
import com.a.a.e;
import com.a.a.t;
import com.a.a.u;
import java.lang.reflect.Type;
import java.util.Collection;

/* compiled from: CollectionTypeAdapterFactory */
public final class b implements u {
    private final c a;

    /* compiled from: CollectionTypeAdapterFactory */
    private static final class a<E> extends t<Collection<E>> {
        private final t<E> a;
        private final h<? extends Collection<E>> b;

        public a(e eVar, Type type, t<E> tVar, h<? extends Collection<E>> hVar) {
            this.a = new m(eVar, tVar, type);
            this.b = hVar;
        }

        /* renamed from: a */
        public Collection<E> b(com.a.a.d.a aVar) {
            if (aVar.f() == com.a.a.d.b.NULL) {
                aVar.j();
                return null;
            }
            Collection<E> collection = (Collection) this.b.a();
            aVar.a();
            while (aVar.e()) {
                collection.add(this.a.b(aVar));
            }
            aVar.b();
            return collection;
        }

        public void a(com.a.a.d.c cVar, Collection<E> collection) {
            if (collection == null) {
                cVar.f();
                return;
            }
            cVar.b();
            for (E a : collection) {
                this.a.a(cVar, a);
            }
            cVar.c();
        }
    }

    public b(c cVar) {
        this.a = cVar;
    }

    public <T> t<T> a(e eVar, com.a.a.c.a<T> aVar) {
        Type type = aVar.getType();
        Class rawType = aVar.getRawType();
        if (!Collection.class.isAssignableFrom(rawType)) {
            return null;
        }
        type = com.a.a.b.b.a(type, rawType);
        return new a(eVar, type, eVar.a(com.a.a.c.a.get(type)), this.a.a((com.a.a.c.a) aVar));
    }
}
