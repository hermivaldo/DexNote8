package com.p031a.p032a.p034b.p035a;

import com.p031a.p032a.C0737e;
import com.p031a.p032a.C0741j;
import com.p031a.p032a.C0746t;
import com.p031a.p032a.C0747u;
import com.p031a.p032a.C1278o;
import com.p031a.p032a.C1279r;
import com.p031a.p032a.p034b.C0714b;
import com.p031a.p032a.p034b.C0715c;
import com.p031a.p032a.p034b.C0716e;
import com.p031a.p032a.p034b.C0724h;
import com.p031a.p032a.p034b.C0728j;
import com.p031a.p032a.p037c.C0731a;
import com.p031a.p032a.p038d.C0732a;
import com.p031a.p032a.p038d.C0733b;
import com.p031a.p032a.p038d.C0734c;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

/* compiled from: MapTypeAdapterFactory */
/* renamed from: com.a.a.b.a.g */
public final class C1224g implements C0747u {
    /* renamed from: a */
    final boolean f4211a;
    /* renamed from: b */
    private final C0715c f4212b;

    /* compiled from: MapTypeAdapterFactory */
    /* renamed from: com.a.a.b.a.g$a */
    private final class C1223a<K, V> extends C0746t<Map<K, V>> {
        /* renamed from: a */
        final /* synthetic */ C1224g f4207a;
        /* renamed from: b */
        private final C0746t<K> f4208b;
        /* renamed from: c */
        private final C0746t<V> f4209c;
        /* renamed from: d */
        private final C0724h<? extends Map<K, V>> f4210d;

        /* renamed from: b */
        public /* synthetic */ Object mo592b(C0732a c0732a) {
            return m5750a(c0732a);
        }

        public C1223a(C1224g c1224g, C0737e c0737e, Type type, C0746t<K> c0746t, Type type2, C0746t<V> c0746t2, C0724h<? extends Map<K, V>> c0724h) {
            this.f4207a = c1224g;
            this.f4208b = new C1236m(c0737e, c0746t, type);
            this.f4209c = new C1236m(c0737e, c0746t2, type2);
            this.f4210d = c0724h;
        }

        /* renamed from: a */
        public Map<K, V> m5750a(C0732a c0732a) {
            C0733b f = c0732a.mo599f();
            if (f == C0733b.NULL) {
                c0732a.mo603j();
                return null;
            }
            Map<K, V> map = (Map) this.f4210d.mo628a();
            Object b;
            if (f == C0733b.BEGIN_ARRAY) {
                c0732a.mo593a();
                while (c0732a.mo598e()) {
                    c0732a.mo593a();
                    b = this.f4208b.mo592b(c0732a);
                    if (map.put(b, this.f4209c.mo592b(c0732a)) != null) {
                        c0732a = new StringBuilder();
                        c0732a.append("duplicate key: ");
                        c0732a.append(b);
                        throw new C1279r(c0732a.toString());
                    }
                    c0732a.mo594b();
                }
                c0732a.mo594b();
            } else {
                c0732a.mo595c();
                while (c0732a.mo598e()) {
                    C0716e.f2298a.mo630a(c0732a);
                    b = this.f4208b.mo592b(c0732a);
                    if (map.put(b, this.f4209c.mo592b(c0732a)) != null) {
                        c0732a = new StringBuilder();
                        c0732a.append("duplicate key: ");
                        c0732a.append(b);
                        throw new C1279r(c0732a.toString());
                    }
                }
                c0732a.mo597d();
            }
            return map;
        }

        /* renamed from: a */
        public void m5752a(C0734c c0734c, Map<K, V> map) {
            if (map == null) {
                c0734c.mo623f();
            } else if (this.f4207a.f4211a) {
                List arrayList = new ArrayList(map.size());
                List arrayList2 = new ArrayList(map.size());
                map = map.entrySet().iterator();
                int i = 0;
                int i2 = 0;
                while (map.hasNext()) {
                    int i3;
                    Entry entry = (Entry) map.next();
                    C0741j a = this.f4208b.m3120a(entry.getKey());
                    arrayList.add(a);
                    arrayList2.add(entry.getValue());
                    if (!a.m3111g()) {
                        if (!a.m3112h()) {
                            i3 = 0;
                            i2 |= i3;
                        }
                    }
                    i3 = 1;
                    i2 |= i3;
                }
                if (i2 != 0) {
                    c0734c.mo617b();
                    map = arrayList.size();
                    while (i < map) {
                        c0734c.mo617b();
                        C0728j.m3013a((C0741j) arrayList.get(i), c0734c);
                        this.f4209c.mo591a(c0734c, arrayList2.get(i));
                        c0734c.mo619c();
                        i++;
                    }
                    c0734c.mo619c();
                } else {
                    c0734c.mo621d();
                    map = arrayList.size();
                    while (i < map) {
                        c0734c.mo614a(m5749a((C0741j) arrayList.get(i)));
                        this.f4209c.mo591a(c0734c, arrayList2.get(i));
                        i++;
                    }
                    c0734c.mo622e();
                }
            } else {
                c0734c.mo621d();
                map = map.entrySet().iterator();
                while (map.hasNext()) {
                    Entry entry2 = (Entry) map.next();
                    c0734c.mo614a(String.valueOf(entry2.getKey()));
                    this.f4209c.mo591a(c0734c, entry2.getValue());
                }
                c0734c.mo622e();
            }
        }

        /* renamed from: a */
        private String m5749a(C0741j c0741j) {
            if (c0741j.m3113i()) {
                C1278o m = c0741j.m3117m();
                if (m.m5993p() != null) {
                    return String.valueOf(m.mo631a());
                }
                if (m.m5992o() != null) {
                    return Boolean.toString(m.mo636f());
                }
                if (m.m5994q() != null) {
                    return m.mo632b();
                }
                throw new AssertionError();
            } else if (c0741j.m3114j()) {
                return "null";
            } else {
                throw new AssertionError();
            }
        }
    }

    public C1224g(C0715c c0715c, boolean z) {
        this.f4212b = c0715c;
        this.f4211a = z;
    }

    /* renamed from: a */
    public <T> C0746t<T> mo590a(C0737e c0737e, C0731a<T> c0731a) {
        Type type = c0731a.getType();
        if (!Map.class.isAssignableFrom(c0731a.getRawType())) {
            return null;
        }
        Type[] b = C0714b.m2982b(type, C0714b.m2985e(type));
        return new C1223a(this, c0737e, b[0], m5754a(c0737e, b[0]), b[1], c0737e.m3090a(C0731a.get(b[1])), this.f4212b.m2992a((C0731a) c0731a));
    }

    /* renamed from: a */
    private C0746t<?> m5754a(C0737e c0737e, Type type) {
        if (type != Boolean.TYPE) {
            if (type != Boolean.class) {
                return c0737e.m3090a(C0731a.get(type));
            }
        }
        return C0709n.f2269f;
    }
}
