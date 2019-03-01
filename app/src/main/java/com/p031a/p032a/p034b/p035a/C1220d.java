package com.p031a.p032a.p034b.p035a;

import com.p031a.p032a.C0737e;
import com.p031a.p032a.C0740i;
import com.p031a.p032a.C0744q;
import com.p031a.p032a.C0746t;
import com.p031a.p032a.C0747u;
import com.p031a.p032a.p033a.C0698b;
import com.p031a.p032a.p034b.C0715c;
import com.p031a.p032a.p037c.C0731a;

/* compiled from: JsonAdapterAnnotationTypeAdapterFactory */
/* renamed from: com.a.a.b.a.d */
public final class C1220d implements C0747u {
    /* renamed from: a */
    private final C0715c f4195a;

    public C1220d(C0715c c0715c) {
        this.f4195a = c0715c;
    }

    /* renamed from: a */
    public <T> C0746t<T> mo590a(C0737e c0737e, C0731a<T> c0731a) {
        C0698b c0698b = (C0698b) c0731a.getRawType().getAnnotation(C0698b.class);
        if (c0698b == null) {
            return null;
        }
        return m5712a(this.f4195a, c0737e, c0731a, c0698b);
    }

    /* renamed from: a */
    C0746t<?> m5712a(C0715c c0715c, C0737e c0737e, C0731a<?> c0731a, C0698b c0698b) {
        C0746t<?> c0746t;
        C0715c a = c0715c.m2992a(C0731a.get(c0698b.m2947a())).mo628a();
        if ((a instanceof C0746t) != null) {
            c0746t = (C0746t) a;
        } else if ((a instanceof C0747u) != null) {
            c0746t = ((C0747u) a).mo590a(c0737e, c0731a);
        } else {
            c0715c = a instanceof C0744q;
            if (c0715c == null) {
                if (!(a instanceof C0740i)) {
                    c0737e = new StringBuilder();
                    c0737e.append("Invalid attempt to bind an instance of ");
                    c0737e.append(a.getClass().getName());
                    c0737e.append(" as a @JsonAdapter for ");
                    c0737e.append(c0731a.toString());
                    c0737e.append(". @JsonAdapter value must be a TypeAdapter, TypeAdapterFactory, JsonSerializer or JsonDeserializer.");
                    throw new IllegalArgumentException(c0737e.toString());
                }
            }
            C0740i c0740i = null;
            C0744q c0744q = c0715c != null ? (C0744q) a : null;
            if ((a instanceof C0740i) != null) {
                c0740i = (C0740i) a;
            }
            C0746t<?> c1235l = new C1235l(c0744q, c0740i, c0737e, c0731a, null);
        }
        return (c0746t == null || c0698b.m2948b() == null) ? c0746t : c0746t.m3121a();
    }
}
