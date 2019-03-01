package com.a.a.b.a;

import com.a.a.d.a;
import com.a.a.d.c;
import com.a.a.e;
import com.a.a.t;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;

/* compiled from: TypeAdapterRuntimeTypeWrapper */
final class m<T> extends t<T> {
    private final e a;
    private final t<T> b;
    private final Type c;

    m(e eVar, t<T> tVar, Type type) {
        this.a = eVar;
        this.b = tVar;
        this.c = type;
    }

    public T b(a aVar) {
        return this.b.b(aVar);
    }

    public void a(c cVar, T t) {
        t tVar = this.b;
        Type a = a(this.c, (Object) t);
        if (a != this.c) {
            tVar = this.a.a(com.a.a.c.a.get(a));
            if ((tVar instanceof i.a) && !(this.b instanceof i.a)) {
                tVar = this.b;
            }
        }
        tVar.a(cVar, t);
    }

    private Type a(Type type, Object obj) {
        if (obj != null) {
            return (type == Object.class || (type instanceof TypeVariable) || (type instanceof Class)) ? obj.getClass() : type;
        } else {
            return type;
        }
    }
}
