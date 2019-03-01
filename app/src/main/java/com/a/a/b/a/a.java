package com.a.a.b.a;

import com.a.a.b.b;
import com.a.a.d.c;
import com.a.a.e;
import com.a.a.t;
import com.a.a.u;
import java.lang.reflect.Array;
import java.lang.reflect.GenericArrayType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/* compiled from: ArrayTypeAdapter */
public final class a<E> extends t<Object> {
    public static final u a = new u() {
        public <T> t<T> a(e eVar, com.a.a.c.a<T> aVar) {
            Type type = aVar.getType();
            if (!(type instanceof GenericArrayType) && (!(type instanceof Class) || !((Class) type).isArray())) {
                return null;
            }
            type = b.g(type);
            return new a(eVar, eVar.a(com.a.a.c.a.get(type)), b.e(type));
        }
    };
    private final Class<E> b;
    private final t<E> c;

    public a(e eVar, t<E> tVar, Class<E> cls) {
        this.c = new m(eVar, tVar, cls);
        this.b = cls;
    }

    public Object b(com.a.a.d.a aVar) {
        if (aVar.f() == com.a.a.d.b.NULL) {
            aVar.j();
            return null;
        }
        List arrayList = new ArrayList();
        aVar.a();
        while (aVar.e()) {
            arrayList.add(this.c.b(aVar));
        }
        aVar.b();
        int size = arrayList.size();
        Object newInstance = Array.newInstance(this.b, size);
        for (int i = 0; i < size; i++) {
            Array.set(newInstance, i, arrayList.get(i));
        }
        return newInstance;
    }

    public void a(c cVar, Object obj) {
        if (obj == null) {
            cVar.f();
            return;
        }
        cVar.b();
        int length = Array.getLength(obj);
        for (int i = 0; i < length; i++) {
            this.c.a(cVar, Array.get(obj, i));
        }
        cVar.c();
    }
}
