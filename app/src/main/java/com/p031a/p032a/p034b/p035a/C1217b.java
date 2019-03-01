package com.p031a.p032a.p034b.p035a;

import com.p031a.p032a.C0737e;
import com.p031a.p032a.C0746t;
import com.p031a.p032a.C0747u;
import com.p031a.p032a.p034b.C0714b;
import com.p031a.p032a.p034b.C0715c;
import com.p031a.p032a.p034b.C0724h;
import com.p031a.p032a.p037c.C0731a;
import com.p031a.p032a.p038d.C0732a;
import com.p031a.p032a.p038d.C0733b;
import com.p031a.p032a.p038d.C0734c;
import java.lang.reflect.Type;
import java.util.Collection;

/* compiled from: CollectionTypeAdapterFactory */
/* renamed from: com.a.a.b.a.b */
public final class C1217b implements C0747u {
    /* renamed from: a */
    private final C0715c f4191a;

    /* compiled from: CollectionTypeAdapterFactory */
    /* renamed from: com.a.a.b.a.b$a */
    private static final class C1216a<E> extends C0746t<Collection<E>> {
        /* renamed from: a */
        private final C0746t<E> f4189a;
        /* renamed from: b */
        private final C0724h<? extends Collection<E>> f4190b;

        /* renamed from: b */
        public /* synthetic */ Object mo592b(C0732a c0732a) {
            return m5701a(c0732a);
        }

        public C1216a(C0737e c0737e, Type type, C0746t<E> c0746t, C0724h<? extends Collection<E>> c0724h) {
            this.f4189a = new C1236m(c0737e, c0746t, type);
            this.f4190b = c0724h;
        }

        /* renamed from: a */
        public Collection<E> m5701a(C0732a c0732a) {
            if (c0732a.mo599f() == C0733b.NULL) {
                c0732a.mo603j();
                return null;
            }
            Collection<E> collection = (Collection) this.f4190b.mo628a();
            c0732a.mo593a();
            while (c0732a.mo598e()) {
                collection.add(this.f4189a.mo592b(c0732a));
            }
            c0732a.mo594b();
            return collection;
        }

        /* renamed from: a */
        public void m5703a(C0734c c0734c, Collection<E> collection) {
            if (collection == null) {
                c0734c.mo623f();
                return;
            }
            c0734c.mo617b();
            for (E a : collection) {
                this.f4189a.mo591a(c0734c, a);
            }
            c0734c.mo619c();
        }
    }

    public C1217b(C0715c c0715c) {
        this.f4191a = c0715c;
    }

    /* renamed from: a */
    public <T> C0746t<T> mo590a(C0737e c0737e, C0731a<T> c0731a) {
        Type type = c0731a.getType();
        Class rawType = c0731a.getRawType();
        if (!Collection.class.isAssignableFrom(rawType)) {
            return null;
        }
        type = C0714b.m2973a(type, rawType);
        return new C1216a(c0737e, type, c0737e.m3090a(C0731a.get(type)), this.f4191a.m2992a((C0731a) c0731a));
    }
}
