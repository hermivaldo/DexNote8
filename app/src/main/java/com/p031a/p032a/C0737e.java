package com.p031a.p032a;

import com.p031a.p032a.p034b.C0715c;
import com.p031a.p032a.p034b.C0728j;
import com.p031a.p032a.p034b.C1259d;
import com.p031a.p032a.p034b.p035a.C0709n;
import com.p031a.p032a.p034b.p035a.C1215a;
import com.p031a.p032a.p034b.p035a.C1217b;
import com.p031a.p032a.p034b.p035a.C1219c;
import com.p031a.p032a.p034b.p035a.C1220d;
import com.p031a.p032a.p034b.p035a.C1224g;
import com.p031a.p032a.p034b.p035a.C1226h;
import com.p031a.p032a.p034b.p035a.C1229i;
import com.p031a.p032a.p034b.p035a.C1231j;
import com.p031a.p032a.p034b.p035a.C1233k;
import com.p031a.p032a.p037c.C0731a;
import com.p031a.p032a.p038d.C0732a;
import com.p031a.p032a.p038d.C0733b;
import com.p031a.p032a.p038d.C0734c;
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
/* renamed from: com.a.a.e */
public final class C0737e {
    /* renamed from: a */
    private static final C0731a<?> f2367a = C0731a.get(Object.class);
    /* renamed from: b */
    private final ThreadLocal<Map<C0731a<?>, C1273a<?>>> f2368b;
    /* renamed from: c */
    private final Map<C0731a<?>, C0746t<?>> f2369c;
    /* renamed from: d */
    private final List<C0747u> f2370d;
    /* renamed from: e */
    private final C0715c f2371e;
    /* renamed from: f */
    private final C1259d f2372f;
    /* renamed from: g */
    private final C0736d f2373g;
    /* renamed from: h */
    private final boolean f2374h;
    /* renamed from: i */
    private final boolean f2375i;
    /* renamed from: j */
    private final boolean f2376j;
    /* renamed from: k */
    private final boolean f2377k;
    /* renamed from: l */
    private final boolean f2378l;
    /* renamed from: m */
    private final C1220d f2379m;

    /* compiled from: Gson */
    /* renamed from: com.a.a.e$1 */
    class C12681 extends C0746t<Number> {
        /* renamed from: a */
        final /* synthetic */ C0737e f4308a;

        C12681(C0737e c0737e) {
            this.f4308a = c0737e;
        }

        /* renamed from: b */
        public /* synthetic */ Object mo592b(C0732a c0732a) {
            return m5950a(c0732a);
        }

        /* renamed from: a */
        public Double m5950a(C0732a c0732a) {
            if (c0732a.mo599f() != C0733b.NULL) {
                return Double.valueOf(c0732a.mo604k());
            }
            c0732a.mo603j();
            return null;
        }

        /* renamed from: a */
        public void m5951a(C0734c c0734c, Number number) {
            if (number == null) {
                c0734c.mo623f();
                return;
            }
            C0737e.m3084a(number.doubleValue());
            c0734c.mo613a(number);
        }
    }

    /* compiled from: Gson */
    /* renamed from: com.a.a.e$2 */
    class C12692 extends C0746t<Number> {
        /* renamed from: a */
        final /* synthetic */ C0737e f4309a;

        C12692(C0737e c0737e) {
            this.f4309a = c0737e;
        }

        /* renamed from: b */
        public /* synthetic */ Object mo592b(C0732a c0732a) {
            return m5954a(c0732a);
        }

        /* renamed from: a */
        public Float m5954a(C0732a c0732a) {
            if (c0732a.mo599f() != C0733b.NULL) {
                return Float.valueOf((float) c0732a.mo604k());
            }
            c0732a.mo603j();
            return null;
        }

        /* renamed from: a */
        public void m5955a(C0734c c0734c, Number number) {
            if (number == null) {
                c0734c.mo623f();
                return;
            }
            C0737e.m3084a((double) number.floatValue());
            c0734c.mo613a(number);
        }
    }

    /* compiled from: Gson */
    /* renamed from: com.a.a.e$3 */
    static class C12703 extends C0746t<Number> {
        C12703() {
        }

        /* renamed from: b */
        public /* synthetic */ Object mo592b(C0732a c0732a) {
            return m5958a(c0732a);
        }

        /* renamed from: a */
        public Number m5958a(C0732a c0732a) {
            if (c0732a.mo599f() != C0733b.NULL) {
                return Long.valueOf(c0732a.mo605l());
            }
            c0732a.mo603j();
            return null;
        }

        /* renamed from: a */
        public void m5959a(C0734c c0734c, Number number) {
            if (number == null) {
                c0734c.mo623f();
            } else {
                c0734c.mo618b(number.toString());
            }
        }
    }

    /* compiled from: Gson */
    /* renamed from: com.a.a.e$a */
    static class C1273a<T> extends C0746t<T> {
        /* renamed from: a */
        private C0746t<T> f4312a;

        C1273a() {
        }

        /* renamed from: a */
        public void m5971a(C0746t<T> c0746t) {
            if (this.f4312a != null) {
                throw new AssertionError();
            }
            this.f4312a = c0746t;
        }

        /* renamed from: b */
        public T mo592b(C0732a c0732a) {
            if (this.f4312a != null) {
                return this.f4312a.mo592b(c0732a);
            }
            throw new IllegalStateException();
        }

        /* renamed from: a */
        public void mo591a(C0734c c0734c, T t) {
            if (this.f4312a == null) {
                throw new IllegalStateException();
            }
            this.f4312a.mo591a(c0734c, t);
        }
    }

    public C0737e() {
        this(C1259d.f4288a, C1266c.IDENTITY, Collections.emptyMap(), false, false, false, true, false, false, false, C0745s.DEFAULT, Collections.emptyList());
    }

    C0737e(C1259d c1259d, C0736d c0736d, Map<Type, C0738f<?>> map, boolean z, boolean z2, boolean z3, boolean z4, boolean z5, boolean z6, boolean z7, C0745s c0745s, List<C0747u> list) {
        this.f2368b = new ThreadLocal();
        this.f2369c = new ConcurrentHashMap();
        this.f2371e = new C0715c(map);
        this.f2372f = c1259d;
        this.f2373g = c0736d;
        this.f2374h = z;
        this.f2376j = z3;
        this.f2375i = z4;
        this.f2377k = z5;
        this.f2378l = z6;
        map = new ArrayList();
        map.add(C0709n.f2262Y);
        map.add(C1226h.f4213a);
        map.add(c1259d);
        map.addAll(list);
        map.add(C0709n.f2241D);
        map.add(C0709n.f2276m);
        map.add(C0709n.f2270g);
        map.add(C0709n.f2272i);
        map.add(C0709n.f2274k);
        C0746t a = C0737e.m3081a(c0745s);
        map.add(C0709n.m2963a(Long.TYPE, Long.class, a));
        map.add(C0709n.m2963a(Double.TYPE, Double.class, m3083a(z7)));
        map.add(C0709n.m2963a(Float.TYPE, Float.class, m3087b(z7)));
        map.add(C0709n.f2287x);
        map.add(C0709n.f2278o);
        map.add(C0709n.f2280q);
        map.add(C0709n.m2962a(AtomicLong.class, C0737e.m3082a(a)));
        map.add(C0709n.m2962a(AtomicLongArray.class, C0737e.m3086b(a)));
        map.add(C0709n.f2282s);
        map.add(C0709n.f2289z);
        map.add(C0709n.f2243F);
        map.add(C0709n.f2245H);
        map.add(C0709n.m2962a(BigDecimal.class, C0709n.f2239B));
        map.add(C0709n.m2962a(BigInteger.class, C0709n.f2240C));
        map.add(C0709n.f2247J);
        map.add(C0709n.f2249L);
        map.add(C0709n.f2253P);
        map.add(C0709n.f2255R);
        map.add(C0709n.f2260W);
        map.add(C0709n.f2251N);
        map.add(C0709n.f2267d);
        map.add(C1219c.f4192a);
        map.add(C0709n.f2258U);
        map.add(C1233k.f4230a);
        map.add(C1231j.f4228a);
        map.add(C0709n.f2256S);
        map.add(C1215a.f4186a);
        map.add(C0709n.f2265b);
        map.add(new C1217b(this.f2371e));
        map.add(new C1224g(this.f2371e, z2));
        this.f2379m = new C1220d(this.f2371e);
        map.add(this.f2379m);
        map.add(C0709n.f2263Z);
        map.add(new C1229i(this.f2371e, c0736d, c1259d, this.f2379m));
        this.f2370d = Collections.unmodifiableList(map);
    }

    /* renamed from: a */
    private C0746t<Number> m3083a(boolean z) {
        if (z) {
            return C0709n.f2285v;
        }
        return new C12681(this);
    }

    /* renamed from: b */
    private C0746t<Number> m3087b(boolean z) {
        if (z) {
            return C0709n.f2284u;
        }
        return new C12692(this);
    }

    /* renamed from: a */
    static void m3084a(double d) {
        if (!Double.isNaN(d)) {
            if (!Double.isInfinite(d)) {
                return;
            }
        }
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(d);
        stringBuilder.append(" is not a valid double value as per JSON specification. To override this behavior, use GsonBuilder.serializeSpecialFloatingPointValues() method.");
        throw new IllegalArgumentException(stringBuilder.toString());
    }

    /* renamed from: a */
    private static C0746t<Number> m3081a(C0745s c0745s) {
        if (c0745s == C0745s.DEFAULT) {
            return C0709n.f2283t;
        }
        return new C12703();
    }

    /* renamed from: a */
    private static C0746t<AtomicLong> m3082a(final C0746t<Number> c0746t) {
        return new C0746t<AtomicLong>() {
            /* renamed from: b */
            public /* synthetic */ Object mo592b(C0732a c0732a) {
                return m5962a(c0732a);
            }

            /* renamed from: a */
            public void m5964a(C0734c c0734c, AtomicLong atomicLong) {
                c0746t.mo591a(c0734c, Long.valueOf(atomicLong.get()));
            }

            /* renamed from: a */
            public AtomicLong m5962a(C0732a c0732a) {
                return new AtomicLong(((Number) c0746t.mo592b(c0732a)).longValue());
            }
        }.m3121a();
    }

    /* renamed from: b */
    private static C0746t<AtomicLongArray> m3086b(final C0746t<Number> c0746t) {
        return new C0746t<AtomicLongArray>() {
            /* renamed from: b */
            public /* synthetic */ Object mo592b(C0732a c0732a) {
                return m5966a(c0732a);
            }

            /* renamed from: a */
            public void m5968a(C0734c c0734c, AtomicLongArray atomicLongArray) {
                c0734c.mo617b();
                int length = atomicLongArray.length();
                for (int i = 0; i < length; i++) {
                    c0746t.mo591a(c0734c, Long.valueOf(atomicLongArray.get(i)));
                }
                c0734c.mo619c();
            }

            /* renamed from: a */
            public AtomicLongArray m5966a(C0732a c0732a) {
                List arrayList = new ArrayList();
                c0732a.mo593a();
                while (c0732a.mo598e()) {
                    arrayList.add(Long.valueOf(((Number) c0746t.mo592b(c0732a)).longValue()));
                }
                c0732a.mo594b();
                int size = arrayList.size();
                c0732a = new AtomicLongArray(size);
                for (int i = 0; i < size; i++) {
                    c0732a.set(i, ((Long) arrayList.get(i)).longValue());
                }
                return c0732a;
            }
        }.m3121a();
    }

    /* renamed from: a */
    public <T> C0746t<T> m3090a(C0731a<T> c0731a) {
        C0746t<T> c0746t = (C0746t) this.f2369c.get(c0731a == null ? f2367a : c0731a);
        if (c0746t != null) {
            return c0746t;
        }
        Map map = (Map) this.f2368b.get();
        Object obj = null;
        if (map == null) {
            map = new HashMap();
            this.f2368b.set(map);
            obj = 1;
        }
        C1273a c1273a = (C1273a) map.get(c0731a);
        if (c1273a != null) {
            return c1273a;
        }
        try {
            C0746t<T> hasNext;
            c1273a = new C1273a();
            map.put(c0731a, c1273a);
            Iterator it = this.f2370d.iterator();
            while (true) {
                hasNext = it.hasNext();
                if (hasNext != null) {
                    hasNext = ((C0747u) it.next()).mo590a(this, c0731a);
                    if (hasNext != null) {
                        break;
                    }
                }
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append("GSON cannot handle ");
                stringBuilder.append(c0731a);
                throw new IllegalArgumentException(stringBuilder.toString());
            }
            c1273a.m5971a(hasNext);
            this.f2369c.put(c0731a, hasNext);
            return hasNext;
        } finally {
            map.remove(c0731a);
            if (obj != null) {
                this.f2368b.remove();
            }
        }
    }

    /* renamed from: a */
    public <T> C0746t<T> m3091a(C0747u c0747u, C0731a<T> c0731a) {
        if (!this.f2370d.contains(c0747u)) {
            c0747u = this.f2379m;
        }
        Object obj = null;
        for (C0747u c0747u2 : this.f2370d) {
            if (obj != null) {
                C0746t<T> a = c0747u2.mo590a(this, c0731a);
                if (a != null) {
                    return a;
                }
            } else if (c0747u2 == c0747u) {
                obj = 1;
            }
        }
        c0747u = new StringBuilder();
        c0747u.append("GSON cannot serialize ");
        c0747u.append(c0731a);
        throw new IllegalArgumentException(c0747u.toString());
    }

    /* renamed from: a */
    public <T> C0746t<T> m3092a(Class<T> cls) {
        return m3090a(C0731a.get((Class) cls));
    }

    /* renamed from: a */
    public String m3097a(Object obj) {
        if (obj == null) {
            return m3096a(C1276l.f4314a);
        }
        return m3098a(obj, obj.getClass());
    }

    /* renamed from: a */
    public String m3098a(Object obj, Type type) {
        Appendable stringWriter = new StringWriter();
        m3102a(obj, type, stringWriter);
        return stringWriter.toString();
    }

    /* renamed from: a */
    public void m3102a(Object obj, Type type, Appendable appendable) {
        try {
            m3101a(obj, type, m3089a(C0728j.m3012a(appendable)));
        } catch (Throwable e) {
            throw new C1275k(e);
        }
    }

    /* renamed from: a */
    public void m3101a(Object obj, Type type, C0734c c0734c) {
        type = m3090a(C0731a.get(type));
        boolean g = c0734c.m3077g();
        c0734c.m3069b(true);
        boolean h = c0734c.m3078h();
        c0734c.m3072c(this.f2375i);
        boolean i = c0734c.m3079i();
        c0734c.m3074d(this.f2374h);
        try {
            type.mo591a(c0734c, obj);
            c0734c.m3069b(g);
            c0734c.m3072c(h);
            c0734c.m3074d(i);
        } catch (Throwable e) {
            throw new C1275k(e);
        } catch (Throwable th) {
            c0734c.m3069b(g);
            c0734c.m3072c(h);
            c0734c.m3074d(i);
        }
    }

    /* renamed from: a */
    public String m3096a(C0741j c0741j) {
        Appendable stringWriter = new StringWriter();
        m3100a(c0741j, stringWriter);
        return stringWriter.toString();
    }

    /* renamed from: a */
    public void m3100a(C0741j c0741j, Appendable appendable) {
        try {
            m3099a(c0741j, m3089a(C0728j.m3012a(appendable)));
        } catch (Throwable e) {
            throw new C1275k(e);
        }
    }

    /* renamed from: a */
    public C0734c m3089a(Writer writer) {
        if (this.f2376j) {
            writer.write(")]}'\n");
        }
        C0734c c0734c = new C0734c(writer);
        if (this.f2377k != null) {
            c0734c.m3071c("  ");
        }
        c0734c.m3074d(this.f2374h);
        return c0734c;
    }

    /* renamed from: a */
    public C0732a m3088a(Reader reader) {
        C0732a c0732a = new C0732a(reader);
        c0732a.m3034a(this.f2378l);
        return c0732a;
    }

    /* renamed from: a */
    public void m3099a(C0741j c0741j, C0734c c0734c) {
        boolean g = c0734c.m3077g();
        c0734c.m3069b(true);
        boolean h = c0734c.m3078h();
        c0734c.m3072c(this.f2375i);
        boolean i = c0734c.m3079i();
        c0734c.m3074d(this.f2374h);
        try {
            C0728j.m3013a(c0741j, c0734c);
            c0734c.m3069b(g);
            c0734c.m3072c(h);
            c0734c.m3074d(i);
        } catch (Throwable e) {
            throw new C1275k(e);
        } catch (Throwable th) {
            c0734c.m3069b(g);
            c0734c.m3072c(h);
            c0734c.m3074d(i);
        }
    }

    /* renamed from: a */
    public <T> T m3095a(String str, Type type) {
        if (str == null) {
            return null;
        }
        return m3094a(new StringReader(str), type);
    }

    /* renamed from: a */
    public <T> T m3094a(Reader reader, Type type) {
        C0732a a = m3088a(reader);
        Object a2 = m3093a(a, type);
        C0737e.m3085a(a2, a);
        return a2;
    }

    /* renamed from: a */
    private static void m3085a(Object obj, C0732a c0732a) {
        if (obj != null) {
            try {
                if (c0732a.mo599f() != C0733b.END_DOCUMENT) {
                    throw new C1275k("JSON document was not fully consumed.");
                }
            } catch (Throwable e) {
                throw new C1279r(e);
            } catch (Throwable e2) {
                throw new C1275k(e2);
            }
        }
    }

    /* renamed from: a */
    public <T> T m3093a(C0732a c0732a, Type type) {
        boolean q = c0732a.m3049q();
        boolean z = true;
        c0732a.m3034a(true);
        try {
            c0732a.mo599f();
            z = false;
            T b = m3090a(C0731a.get(type)).mo592b(c0732a);
            c0732a.m3034a(q);
            return b;
        } catch (Throwable e) {
            if (z) {
                c0732a.m3034a(q);
                return null;
            }
            throw new C1279r(e);
        } catch (Throwable e2) {
            throw new C1279r(e2);
        } catch (Throwable e22) {
            throw new C1279r(e22);
        } catch (Throwable th) {
            c0732a.m3034a(q);
        }
    }

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder("{serializeNulls:");
        stringBuilder.append(this.f2374h);
        stringBuilder.append(",factories:");
        stringBuilder.append(this.f2370d);
        stringBuilder.append(",instanceCreators:");
        stringBuilder.append(this.f2371e);
        stringBuilder.append("}");
        return stringBuilder.toString();
    }
}
