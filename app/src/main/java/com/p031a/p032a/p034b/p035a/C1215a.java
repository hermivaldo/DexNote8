package com.p031a.p032a.p034b.p035a;

import com.p031a.p032a.C0737e;
import com.p031a.p032a.C0746t;
import com.p031a.p032a.C0747u;
import com.p031a.p032a.p034b.C0714b;
import com.p031a.p032a.p037c.C0731a;
import com.p031a.p032a.p038d.C0732a;
import com.p031a.p032a.p038d.C0733b;
import com.p031a.p032a.p038d.C0734c;
import java.lang.reflect.Array;
import java.lang.reflect.GenericArrayType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/* compiled from: ArrayTypeAdapter */
/* renamed from: com.a.a.b.a.a */
public final class C1215a<E> extends C0746t<Object> {
    /* renamed from: a */
    public static final C0747u f4186a = new C12141();
    /* renamed from: b */
    private final Class<E> f4187b;
    /* renamed from: c */
    private final C0746t<E> f4188c;

    /* compiled from: ArrayTypeAdapter */
    /* renamed from: com.a.a.b.a.a$1 */
    static class C12141 implements C0747u {
        C12141() {
        }

        /* renamed from: a */
        public <T> C0746t<T> mo590a(C0737e c0737e, C0731a<T> c0731a) {
            Type type = c0731a.getType();
            if ((type instanceof GenericArrayType) == null && ((type instanceof Class) == null || ((Class) type).isArray() == null)) {
                return null;
            }
            type = C0714b.m2987g(type);
            return new C1215a(c0737e, c0737e.m3090a(C0731a.get(type)), C0714b.m2985e(type));
        }
    }

    public C1215a(C0737e c0737e, C0746t<E> c0746t, Class<E> cls) {
        this.f4188c = new C1236m(c0737e, c0746t, cls);
        this.f4187b = cls;
    }

    /* renamed from: b */
    public Object mo592b(C0732a c0732a) {
        if (c0732a.mo599f() == C0733b.NULL) {
            c0732a.mo603j();
            return null;
        }
        List arrayList = new ArrayList();
        c0732a.mo593a();
        while (c0732a.mo598e()) {
            arrayList.add(this.f4188c.mo592b(c0732a));
        }
        c0732a.mo594b();
        c0732a = arrayList.size();
        Object newInstance = Array.newInstance(this.f4187b, c0732a);
        for (int i = 0; i < c0732a; i++) {
            Array.set(newInstance, i, arrayList.get(i));
        }
        return newInstance;
    }

    /* renamed from: a */
    public void mo591a(C0734c c0734c, Object obj) {
        if (obj == null) {
            c0734c.mo623f();
            return;
        }
        c0734c.mo617b();
        int length = Array.getLength(obj);
        for (int i = 0; i < length; i++) {
            this.f4188c.mo591a(c0734c, Array.get(obj, i));
        }
        c0734c.mo619c();
    }
}
