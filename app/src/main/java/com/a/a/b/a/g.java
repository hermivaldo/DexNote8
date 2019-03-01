package com.a.a.b.a;

import com.a.a.b.c;
import com.a.a.b.h;
import com.a.a.d.b;
import com.a.a.e;
import com.a.a.j;
import com.a.a.o;
import com.a.a.r;
import com.a.a.t;
import com.a.a.u;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

/* compiled from: MapTypeAdapterFactory */
public final class g implements u {
    final boolean a;
    private final c b;

    /* compiled from: MapTypeAdapterFactory */
    private final class a<K, V> extends t<Map<K, V>> {
        private final t<K> b;
        private final t<V> c;
        private final h<? extends Map<K, V>> d;

        public a(e eVar, Type type, t<K> tVar, Type type2, t<V> tVar2, h<? extends Map<K, V>> hVar) {
            this.b = new m(eVar, tVar, type);
            this.c = new m(eVar, tVar2, type2);
            this.d = hVar;
        }

        /* renamed from: a */
        public Map<K, V> b(com.a.a.d.a aVar) {
            b f = aVar.f();
            if (f == b.NULL) {
                aVar.j();
                return null;
            }
            Map<K, V> map = (Map) this.d.a();
            Object b;
            StringBuilder stringBuilder;
            if (f == b.BEGIN_ARRAY) {
                aVar.a();
                while (aVar.e()) {
                    aVar.a();
                    b = this.b.b(aVar);
                    if (map.put(b, this.c.b(aVar)) != null) {
                        stringBuilder = new StringBuilder();
                        stringBuilder.append("duplicate key: ");
                        stringBuilder.append(b);
                        throw new r(stringBuilder.toString());
                    }
                    aVar.b();
                }
                aVar.b();
            } else {
                aVar.c();
                while (aVar.e()) {
                    com.a.a.b.e.a.a(aVar);
                    b = this.b.b(aVar);
                    if (map.put(b, this.c.b(aVar)) != null) {
                        stringBuilder = new StringBuilder();
                        stringBuilder.append("duplicate key: ");
                        stringBuilder.append(b);
                        throw new r(stringBuilder.toString());
                    }
                }
                aVar.d();
            }
            return map;
        }

        public void a(com.a.a.d.c cVar, Map<K, V> map) {
            if (map == null) {
                cVar.f();
            } else if (g.this.a) {
                List arrayList = new ArrayList(map.size());
                List arrayList2 = new ArrayList(map.size());
                int i = 0;
                int i2 = 0;
                for (Entry entry : map.entrySet()) {
                    j a = this.b.a(entry.getKey());
                    arrayList.add(a);
                    arrayList2.add(entry.getValue());
                    int i3 = (a.g() || a.h()) ? 1 : 0;
                    i2 |= i3;
                }
                int size;
                if (i2 != 0) {
                    cVar.b();
                    size = arrayList.size();
                    while (i < size) {
                        cVar.b();
                        com.a.a.b.j.a((j) arrayList.get(i), cVar);
                        this.c.a(cVar, arrayList2.get(i));
                        cVar.c();
                        i++;
                    }
                    cVar.c();
                } else {
                    cVar.d();
                    size = arrayList.size();
                    while (i < size) {
                        cVar.a(a((j) arrayList.get(i)));
                        this.c.a(cVar, arrayList2.get(i));
                        i++;
                    }
                    cVar.e();
                }
            } else {
                cVar.d();
                for (Entry entry2 : map.entrySet()) {
                    cVar.a(String.valueOf(entry2.getKey()));
                    this.c.a(cVar, entry2.getValue());
                }
                cVar.e();
            }
        }

        private String a(j jVar) {
            if (jVar.i()) {
                o m = jVar.m();
                if (m.p()) {
                    return String.valueOf(m.a());
                }
                if (m.o()) {
                    return Boolean.toString(m.f());
                }
                if (m.q()) {
                    return m.b();
                }
                throw new AssertionError();
            } else if (jVar.j()) {
                return "null";
            } else {
                throw new AssertionError();
            }
        }
    }

    public g(c cVar, boolean z) {
        this.b = cVar;
        this.a = z;
    }

    public <T> t<T> a(e eVar, com.a.a.c.a<T> aVar) {
        Type type = aVar.getType();
        if (!Map.class.isAssignableFrom(aVar.getRawType())) {
            return null;
        }
        Type[] b = com.a.a.b.b.b(type, com.a.a.b.b.e(type));
        return new a(eVar, b[0], a(eVar, b[0]), b[1], eVar.a(com.a.a.c.a.get(b[1])), this.b.a((com.a.a.c.a) aVar));
    }

    private t<?> a(e eVar, Type type) {
        if (type == Boolean.TYPE || type == Boolean.class) {
            return n.f;
        }
        return eVar.a(com.a.a.c.a.get(type));
    }
}
