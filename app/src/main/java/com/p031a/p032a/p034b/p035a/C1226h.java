package com.p031a.p032a.p034b.p035a;

import com.p031a.p032a.C0737e;
import com.p031a.p032a.C0746t;
import com.p031a.p032a.C0747u;
import com.p031a.p032a.p034b.C0723g;
import com.p031a.p032a.p037c.C0731a;
import com.p031a.p032a.p038d.C0732a;
import com.p031a.p032a.p038d.C0734c;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/* compiled from: ObjectTypeAdapter */
/* renamed from: com.a.a.b.a.h */
public final class C1226h extends C0746t<Object> {
    /* renamed from: a */
    public static final C0747u f4213a = new C12251();
    /* renamed from: b */
    private final C0737e f4214b;

    /* compiled from: ObjectTypeAdapter */
    /* renamed from: com.a.a.b.a.h$1 */
    static class C12251 implements C0747u {
        C12251() {
        }

        /* renamed from: a */
        public <T> C0746t<T> mo590a(C0737e c0737e, C0731a<T> c0731a) {
            return c0731a.getRawType() == Object.class ? new C1226h(c0737e) : null;
        }
    }

    C1226h(C0737e c0737e) {
        this.f4214b = c0737e;
    }

    /* renamed from: b */
    public Object mo592b(C0732a c0732a) {
        switch (c0732a.mo599f()) {
            case BEGIN_ARRAY:
                List arrayList = new ArrayList();
                c0732a.mo593a();
                while (c0732a.mo598e()) {
                    arrayList.add(mo592b(c0732a));
                }
                c0732a.mo594b();
                return arrayList;
            case BEGIN_OBJECT:
                Map c0723g = new C0723g();
                c0732a.mo595c();
                while (c0732a.mo598e()) {
                    c0723g.put(c0732a.mo600g(), mo592b(c0732a));
                }
                c0732a.mo597d();
                return c0723g;
            case STRING:
                return c0732a.mo601h();
            case NUMBER:
                return Double.valueOf(c0732a.mo604k());
            case BOOLEAN:
                return Boolean.valueOf(c0732a.mo602i());
            case NULL:
                c0732a.mo603j();
                return null;
            default:
                throw new IllegalStateException();
        }
    }

    /* renamed from: a */
    public void mo591a(C0734c c0734c, Object obj) {
        if (obj == null) {
            c0734c.mo623f();
            return;
        }
        C0746t a = this.f4214b.m3092a(obj.getClass());
        if (a instanceof C1226h) {
            c0734c.mo621d();
            c0734c.mo622e();
            return;
        }
        a.mo591a(c0734c, obj);
    }
}
