package com.p031a.p032a.p034b.p035a;

import com.p031a.p032a.C0737e;
import com.p031a.p032a.C0746t;
import com.p031a.p032a.p034b.p035a.C1229i.C1228a;
import com.p031a.p032a.p037c.C0731a;
import com.p031a.p032a.p038d.C0732a;
import com.p031a.p032a.p038d.C0734c;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;

/* compiled from: TypeAdapterRuntimeTypeWrapper */
/* renamed from: com.a.a.b.a.m */
final class C1236m<T> extends C0746t<T> {
    /* renamed from: a */
    private final C0737e f4240a;
    /* renamed from: b */
    private final C0746t<T> f4241b;
    /* renamed from: c */
    private final Type f4242c;

    C1236m(C0737e c0737e, C0746t<T> c0746t, Type type) {
        this.f4240a = c0737e;
        this.f4241b = c0746t;
        this.f4242c = type;
    }

    /* renamed from: b */
    public T mo592b(C0732a c0732a) {
        return this.f4241b.mo592b(c0732a);
    }

    /* renamed from: a */
    public void mo591a(C0734c c0734c, T t) {
        C0746t c0746t = this.f4241b;
        Type a = m5783a(this.f4242c, (Object) t);
        if (a != this.f4242c) {
            c0746t = this.f4240a.m3090a(C0731a.get(a));
            if (c0746t instanceof C1228a) {
                if (!(this.f4241b instanceof C1228a)) {
                    c0746t = this.f4241b;
                }
            }
        }
        c0746t.mo591a(c0734c, t);
    }

    /* renamed from: a */
    private Type m5783a(Type type, Object obj) {
        if (obj != null) {
            return (type == Object.class || (type instanceof TypeVariable) || (type instanceof Class)) ? obj.getClass() : type;
        } else {
            return type;
        }
    }
}
