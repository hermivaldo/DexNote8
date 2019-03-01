package com.a.a.b.a;

import com.a.a.a.b;
import com.a.a.b.c;
import com.a.a.c.a;
import com.a.a.e;
import com.a.a.i;
import com.a.a.q;
import com.a.a.t;
import com.a.a.u;

/* compiled from: JsonAdapterAnnotationTypeAdapterFactory */
public final class d implements u {
    private final c a;

    public d(c cVar) {
        this.a = cVar;
    }

    public <T> t<T> a(e eVar, a<T> aVar) {
        b bVar = (b) aVar.getRawType().getAnnotation(b.class);
        if (bVar == null) {
            return null;
        }
        return a(this.a, eVar, aVar, bVar);
    }

    t<?> a(c cVar, e eVar, a<?> aVar, b bVar) {
        t<?> tVar;
        Object a = cVar.a(a.get(bVar.a())).a();
        if (a instanceof t) {
            tVar = (t) a;
        } else if (a instanceof u) {
            tVar = ((u) a).a(eVar, aVar);
        } else {
            boolean z = a instanceof q;
            if (z || (a instanceof i)) {
                i iVar = null;
                q qVar = z ? (q) a : null;
                if (a instanceof i) {
                    iVar = (i) a;
                }
                t<?> lVar = new l(qVar, iVar, eVar, aVar, null);
            } else {
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append("Invalid attempt to bind an instance of ");
                stringBuilder.append(a.getClass().getName());
                stringBuilder.append(" as a @JsonAdapter for ");
                stringBuilder.append(aVar.toString());
                stringBuilder.append(". @JsonAdapter value must be a TypeAdapter, TypeAdapterFactory, JsonSerializer or JsonDeserializer.");
                throw new IllegalArgumentException(stringBuilder.toString());
            }
        }
        return (tVar == null || !bVar.b()) ? tVar : tVar.a();
    }
}
