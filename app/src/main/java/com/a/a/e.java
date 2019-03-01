package com.a.a;

import com.a.a.b.a.b;
import com.a.a.b.a.g;
import com.a.a.b.a.h;
import com.a.a.b.a.i;
import com.a.a.b.a.j;
import com.a.a.b.a.k;
import com.a.a.b.a.n;
import com.a.a.b.c;
import com.a.a.b.d;
import java.io.Reader;
import java.io.StringReader;
import java.io.StringWriter;
import java.io.Writer;
import java.lang.reflect.Type;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.AtomicLongArray;

/* compiled from: Gson */
public final class e {
    private static final com.a.a.c.a<?> a = com.a.a.c.a.get(Object.class);
    private final ThreadLocal<Map<com.a.a.c.a<?>, a<?>>> b;
    private final Map<com.a.a.c.a<?>, t<?>> c;
    private final List<u> d;
    private final c e;
    private final d f;
    private final d g;
    private final boolean h;
    private final boolean i;
    private final boolean j;
    private final boolean k;
    private final boolean l;
    private final com.a.a.b.a.d m;

    /* compiled from: Gson */
    static class a<T> extends t<T> {
        private t<T> a;

        a() {
        }

        public void a(t<T> tVar) {
            if (this.a != null) {
                throw new AssertionError();
            }
            this.a = tVar;
        }

        public T b(com.a.a.d.a aVar) {
            if (this.a != null) {
                return this.a.b(aVar);
            }
            throw new IllegalStateException();
        }

        public void a(com.a.a.d.c cVar, T t) {
            if (this.a == null) {
                throw new IllegalStateException();
            }
            this.a.a(cVar, t);
        }
    }

    public e() {
        this(d.a, c.IDENTITY, Collections.emptyMap(), false, false, false, true, false, false, false, s.DEFAULT, Collections.emptyList());
    }

    e(d dVar, d dVar2, Map<Type, f<?>> map, boolean z, boolean z2, boolean z3, boolean z4, boolean z5, boolean z6, boolean z7, s sVar, List<u> list) {
        this.b = new ThreadLocal();
        this.c = new ConcurrentHashMap();
        this.e = new c(map);
        this.f = dVar;
        this.g = dVar2;
        this.h = z;
        this.j = z3;
        this.i = z4;
        this.k = z5;
        this.l = z6;
        List arrayList = new ArrayList();
        arrayList.add(n.Y);
        arrayList.add(h.a);
        arrayList.add(dVar);
        arrayList.addAll(list);
        arrayList.add(n.D);
        arrayList.add(n.m);
        arrayList.add(n.g);
        arrayList.add(n.i);
        arrayList.add(n.k);
        t a = a(sVar);
        arrayList.add(n.a(Long.TYPE, Long.class, a));
        arrayList.add(n.a(Double.TYPE, Double.class, a(z7)));
        arrayList.add(n.a(Float.TYPE, Float.class, b(z7)));
        arrayList.add(n.x);
        arrayList.add(n.o);
        arrayList.add(n.q);
        arrayList.add(n.a(AtomicLong.class, a(a)));
        arrayList.add(n.a(AtomicLongArray.class, b(a)));
        arrayList.add(n.s);
        arrayList.add(n.z);
        arrayList.add(n.F);
        arrayList.add(n.H);
        arrayList.add(n.a(BigDecimal.class, n.B));
        arrayList.add(n.a(BigInteger.class, n.C));
        arrayList.add(n.J);
        arrayList.add(n.L);
        arrayList.add(n.P);
        arrayList.add(n.R);
        arrayList.add(n.W);
        arrayList.add(n.N);
        arrayList.add(n.d);
        arrayList.add(com.a.a.b.a.c.a);
        arrayList.add(n.U);
        arrayList.add(k.a);
        arrayList.add(j.a);
        arrayList.add(n.S);
        arrayList.add(com.a.a.b.a.a.a);
        arrayList.add(n.b);
        arrayList.add(new b(this.e));
        arrayList.add(new g(this.e, z2));
        this.m = new com.a.a.b.a.d(this.e);
        arrayList.add(this.m);
        arrayList.add(n.Z);
        arrayList.add(new i(this.e, dVar2, dVar, this.m));
        this.d = Collections.unmodifiableList(arrayList);
    }

    private t<Number> a(boolean z) {
        if (z) {
            return n.v;
        }
        return new t<Number>() {
            /* renamed from: a */
            public Double b(com.a.a.d.a aVar) {
                if (aVar.f() != com.a.a.d.b.NULL) {
                    return Double.valueOf(aVar.k());
                }
                aVar.j();
                return null;
            }

            public void a(com.a.a.d.c cVar, Number number) {
                if (number == null) {
                    cVar.f();
                    return;
                }
                e.a(number.doubleValue());
                cVar.a(number);
            }
        };
    }

    private t<Number> b(boolean z) {
        if (z) {
            return n.u;
        }
        return new t<Number>() {
            /* renamed from: a */
            public Float b(com.a.a.d.a aVar) {
                if (aVar.f() != com.a.a.d.b.NULL) {
                    return Float.valueOf((float) aVar.k());
                }
                aVar.j();
                return null;
            }

            public void a(com.a.a.d.c cVar, Number number) {
                if (number == null) {
                    cVar.f();
                    return;
                }
                e.a((double) number.floatValue());
                cVar.a(number);
            }
        };
    }

    static void a(double d) {
        if (Double.isNaN(d) || Double.isInfinite(d)) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(d);
            stringBuilder.append(" is not a valid double value as per JSON specification. To override this behavior, use GsonBuilder.serializeSpecialFloatingPointValues() method.");
            throw new IllegalArgumentException(stringBuilder.toString());
        }
    }

    private static t<Number> a(s sVar) {
        if (sVar == s.DEFAULT) {
            return n.t;
        }
        return new t<Number>() {
            /* renamed from: a */
            public Number b(com.a.a.d.a aVar) {
                if (aVar.f() != com.a.a.d.b.NULL) {
                    return Long.valueOf(aVar.l());
                }
                aVar.j();
                return null;
            }

            public void a(com.a.a.d.c cVar, Number number) {
                if (number == null) {
                    cVar.f();
                } else {
                    cVar.b(number.toString());
                }
            }
        };
    }

    private static t<AtomicLong> a(final t<Number> tVar) {
        return new t<AtomicLong>() {
            public void a(com.a.a.d.c cVar, AtomicLong atomicLong) {
                tVar.a(cVar, Long.valueOf(atomicLong.get()));
            }

            /* renamed from: a */
            public AtomicLong b(com.a.a.d.a aVar) {
                return new AtomicLong(((Number) tVar.b(aVar)).longValue());
            }
        }.a();
    }

    private static t<AtomicLongArray> b(final t<Number> tVar) {
        return new t<AtomicLongArray>() {
            public void a(com.a.a.d.c cVar, AtomicLongArray atomicLongArray) {
                cVar.b();
                int length = atomicLongArray.length();
                for (int i = 0; i < length; i++) {
                    tVar.a(cVar, Long.valueOf(atomicLongArray.get(i)));
                }
                cVar.c();
            }

            /* renamed from: a */
            public AtomicLongArray b(com.a.a.d.a aVar) {
                List arrayList = new ArrayList();
                aVar.a();
                while (aVar.e()) {
                    arrayList.add(Long.valueOf(((Number) tVar.b(aVar)).longValue()));
                }
                aVar.b();
                int size = arrayList.size();
                AtomicLongArray atomicLongArray = new AtomicLongArray(size);
                for (int i = 0; i < size; i++) {
                    atomicLongArray.set(i, ((Long) arrayList.get(i)).longValue());
                }
                return atomicLongArray;
            }
        }.a();
    }

    /* JADX WARNING: Missing block: B:19:0x0050, code:
            r2.a(r4);
            r5.c.put(r6, r4);
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public <T> t<T> a(com.a.a.c.a<T> aVar) {
        t<T> tVar = (t) this.c.get(aVar == null ? a : aVar);
        if (tVar != null) {
            return tVar;
        }
        Map map = (Map) this.b.get();
        Object obj = null;
        if (map == null) {
            map = new HashMap();
            this.b.set(map);
            obj = 1;
        }
        a aVar2 = (a) map.get(aVar);
        if (aVar2 != null) {
            return aVar2;
        }
        try {
            t<T> hasNext;
            aVar2 = new a();
            map.put(aVar, aVar2);
            Iterator it = this.d.iterator();
            while (true) {
                hasNext = it.hasNext();
                if (hasNext != null) {
                    hasNext = ((u) it.next()).a(this, aVar);
                    if (hasNext != null) {
                        break;
                    }
                }
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append("GSON cannot handle ");
                stringBuilder.append(aVar);
                throw new IllegalArgumentException(stringBuilder.toString());
            }
            return hasNext;
        } finally {
            map.remove(aVar);
            if (obj != null) {
                this.b.remove();
            }
        }
    }

    public <T> t<T> a(u uVar, com.a.a.c.a<T> aVar) {
        if (!this.d.contains(uVar)) {
            uVar = this.m;
        }
        Object obj = null;
        for (u uVar2 : this.d) {
            if (obj != null) {
                t<T> a = uVar2.a(this, aVar);
                if (a != null) {
                    return a;
                }
            } else if (uVar2 == uVar) {
                obj = 1;
            }
        }
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("GSON cannot serialize ");
        stringBuilder.append(aVar);
        throw new IllegalArgumentException(stringBuilder.toString());
    }

    public <T> t<T> a(Class<T> cls) {
        return a(com.a.a.c.a.get((Class) cls));
    }

    public String a(Object obj) {
        if (obj == null) {
            return a(l.a);
        }
        return a(obj, obj.getClass());
    }

    public String a(Object obj, Type type) {
        Appendable stringWriter = new StringWriter();
        a(obj, type, stringWriter);
        return stringWriter.toString();
    }

    public void a(Object obj, Type type, Appendable appendable) {
        try {
            a(obj, type, a(com.a.a.b.j.a(appendable)));
        } catch (Throwable e) {
            throw new k(e);
        }
    }

    public void a(Object obj, Type type, com.a.a.d.c cVar) {
        t a = a(com.a.a.c.a.get(type));
        boolean g = cVar.g();
        cVar.b(true);
        boolean h = cVar.h();
        cVar.c(this.i);
        boolean i = cVar.i();
        cVar.d(this.h);
        try {
            a.a(cVar, obj);
            cVar.b(g);
            cVar.c(h);
            cVar.d(i);
        } catch (Throwable e) {
            throw new k(e);
        } catch (Throwable th) {
            cVar.b(g);
            cVar.c(h);
            cVar.d(i);
        }
    }

    public String a(j jVar) {
        Appendable stringWriter = new StringWriter();
        a(jVar, stringWriter);
        return stringWriter.toString();
    }

    public void a(j jVar, Appendable appendable) {
        try {
            a(jVar, a(com.a.a.b.j.a(appendable)));
        } catch (Throwable e) {
            throw new k(e);
        }
    }

    public com.a.a.d.c a(Writer writer) {
        if (this.j) {
            writer.write(")]}'\n");
        }
        com.a.a.d.c cVar = new com.a.a.d.c(writer);
        if (this.k) {
            cVar.c("  ");
        }
        cVar.d(this.h);
        return cVar;
    }

    public com.a.a.d.a a(Reader reader) {
        com.a.a.d.a aVar = new com.a.a.d.a(reader);
        aVar.a(this.l);
        return aVar;
    }

    public void a(j jVar, com.a.a.d.c cVar) {
        boolean g = cVar.g();
        cVar.b(true);
        boolean h = cVar.h();
        cVar.c(this.i);
        boolean i = cVar.i();
        cVar.d(this.h);
        try {
            com.a.a.b.j.a(jVar, cVar);
            cVar.b(g);
            cVar.c(h);
            cVar.d(i);
        } catch (Throwable e) {
            throw new k(e);
        } catch (Throwable th) {
            cVar.b(g);
            cVar.c(h);
            cVar.d(i);
        }
    }

    public <T> T a(String str, Type type) {
        if (str == null) {
            return null;
        }
        return a(new StringReader(str), type);
    }

    public <T> T a(Reader reader, Type type) {
        com.a.a.d.a a = a(reader);
        Object a2 = a(a, type);
        a(a2, a);
        return a2;
    }

    private static void a(Object obj, com.a.a.d.a aVar) {
        if (obj != null) {
            try {
                if (aVar.f() != com.a.a.d.b.END_DOCUMENT) {
                    throw new k("JSON document was not fully consumed.");
                }
            } catch (Throwable e) {
                throw new r(e);
            } catch (Throwable e2) {
                throw new k(e2);
            }
        }
    }

    public <T> T a(com.a.a.d.a aVar, Type type) {
        boolean q = aVar.q();
        boolean z = true;
        aVar.a(true);
        try {
            aVar.f();
            z = false;
            T b = a(com.a.a.c.a.get(type)).b(aVar);
            aVar.a(q);
            return b;
        } catch (Throwable e) {
            if (z) {
                aVar.a(q);
                return null;
            }
            throw new r(e);
        } catch (Throwable e2) {
            throw new r(e2);
        } catch (Throwable e22) {
            throw new r(e22);
        } catch (Throwable th) {
            aVar.a(q);
        }
    }

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder("{serializeNulls:");
        stringBuilder.append(this.h);
        stringBuilder.append(",factories:");
        stringBuilder.append(this.d);
        stringBuilder.append(",instanceCreators:");
        stringBuilder.append(this.e);
        stringBuilder.append("}");
        return stringBuilder.toString();
    }
}
