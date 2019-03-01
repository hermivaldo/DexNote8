package com.p031a.p032a.p034b;

import java.io.Serializable;
import java.util.AbstractMap;
import java.util.AbstractSet;
import java.util.Comparator;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.Map.Entry;
import java.util.NoSuchElementException;
import java.util.Set;

/* compiled from: LinkedTreeMap */
/* renamed from: com.a.a.b.g */
public final class C0723g<K, V> extends AbstractMap<K, V> implements Serializable {
    /* renamed from: f */
    static final /* synthetic */ boolean f2314f = true;
    /* renamed from: g */
    private static final Comparator<Comparable> f2315g = new C07181();
    /* renamed from: a */
    Comparator<? super K> f2316a;
    /* renamed from: b */
    C0722d<K, V> f2317b;
    /* renamed from: c */
    int f2318c;
    /* renamed from: d */
    int f2319d;
    /* renamed from: e */
    final C0722d<K, V> f2320e;
    /* renamed from: h */
    private C0719a f2321h;
    /* renamed from: i */
    private C0720b f2322i;

    /* compiled from: LinkedTreeMap */
    /* renamed from: com.a.a.b.g$1 */
    static class C07181 implements Comparator<Comparable> {
        C07181() {
        }

        public /* synthetic */ int compare(Object obj, Object obj2) {
            return m2994a((Comparable) obj, (Comparable) obj2);
        }

        /* renamed from: a */
        public int m2994a(Comparable comparable, Comparable comparable2) {
            return comparable.compareTo(comparable2);
        }
    }

    /* compiled from: LinkedTreeMap */
    /* renamed from: com.a.a.b.g$a */
    class C0719a extends AbstractSet<Entry<K, V>> {
        /* renamed from: a */
        final /* synthetic */ C0723g f2300a;

        /* compiled from: LinkedTreeMap */
        /* renamed from: com.a.a.b.g$a$1 */
        class C12601 extends C0721c<Entry<K, V>> {
            /* renamed from: a */
            final /* synthetic */ C0719a f4295a;

            C12601(C0719a c0719a) {
                this.f4295a = c0719a;
                super(c0719a.f2300a);
            }

            public /* synthetic */ Object next() {
                return m5941a();
            }

            /* renamed from: a */
            public Entry<K, V> m5941a() {
                return m2995b();
            }
        }

        C0719a(C0723g c0723g) {
            this.f2300a = c0723g;
        }

        public int size() {
            return this.f2300a.f2318c;
        }

        public Iterator<Entry<K, V>> iterator() {
            return new C12601(this);
        }

        public boolean contains(Object obj) {
            return (!(obj instanceof Entry) || this.f2300a.m3005a((Entry) obj) == null) ? false : C0723g.f2314f;
        }

        public boolean remove(Object obj) {
            if (!(obj instanceof Entry)) {
                return false;
            }
            C0722d a = this.f2300a.m3005a((Entry) obj);
            if (a == null) {
                return false;
            }
            this.f2300a.m3006a(a, (boolean) C0723g.f2314f);
            return C0723g.f2314f;
        }

        public void clear() {
            this.f2300a.clear();
        }
    }

    /* compiled from: LinkedTreeMap */
    /* renamed from: com.a.a.b.g$b */
    final class C0720b extends AbstractSet<K> {
        /* renamed from: a */
        final /* synthetic */ C0723g f2301a;

        /* compiled from: LinkedTreeMap */
        /* renamed from: com.a.a.b.g$b$1 */
        class C12611 extends C0721c<K> {
            /* renamed from: a */
            final /* synthetic */ C0720b f4296a;

            C12611(C0720b c0720b) {
                this.f4296a = c0720b;
                super(c0720b.f2301a);
            }

            public K next() {
                return m2995b().f2311f;
            }
        }

        C0720b(C0723g c0723g) {
            this.f2301a = c0723g;
        }

        public int size() {
            return this.f2301a.f2318c;
        }

        public Iterator<K> iterator() {
            return new C12611(this);
        }

        public boolean contains(Object obj) {
            return this.f2301a.containsKey(obj);
        }

        public boolean remove(Object obj) {
            return this.f2301a.m3007b(obj) != null ? C0723g.f2314f : false;
        }

        public void clear() {
            this.f2301a.clear();
        }
    }

    /* compiled from: LinkedTreeMap */
    /* renamed from: com.a.a.b.g$c */
    private abstract class C0721c<T> implements Iterator<T> {
        /* renamed from: b */
        C0722d<K, V> f2302b = this.f2305e.f2320e.f2309d;
        /* renamed from: c */
        C0722d<K, V> f2303c = null;
        /* renamed from: d */
        int f2304d = this.f2305e.f2319d;
        /* renamed from: e */
        final /* synthetic */ C0723g f2305e;

        C0721c(C0723g c0723g) {
            this.f2305e = c0723g;
        }

        public final boolean hasNext() {
            return this.f2302b != this.f2305e.f2320e ? C0723g.f2314f : false;
        }

        /* renamed from: b */
        final C0722d<K, V> m2995b() {
            C0722d<K, V> c0722d = this.f2302b;
            if (c0722d == this.f2305e.f2320e) {
                throw new NoSuchElementException();
            } else if (this.f2305e.f2319d != this.f2304d) {
                throw new ConcurrentModificationException();
            } else {
                this.f2302b = c0722d.f2309d;
                this.f2303c = c0722d;
                return c0722d;
            }
        }

        public final void remove() {
            if (this.f2303c == null) {
                throw new IllegalStateException();
            }
            this.f2305e.m3006a(this.f2303c, (boolean) C0723g.f2314f);
            this.f2303c = null;
            this.f2304d = this.f2305e.f2319d;
        }
    }

    /* compiled from: LinkedTreeMap */
    /* renamed from: com.a.a.b.g$d */
    static final class C0722d<K, V> implements Entry<K, V> {
        /* renamed from: a */
        C0722d<K, V> f2306a;
        /* renamed from: b */
        C0722d<K, V> f2307b;
        /* renamed from: c */
        C0722d<K, V> f2308c;
        /* renamed from: d */
        C0722d<K, V> f2309d;
        /* renamed from: e */
        C0722d<K, V> f2310e;
        /* renamed from: f */
        final K f2311f;
        /* renamed from: g */
        V f2312g;
        /* renamed from: h */
        int f2313h;

        C0722d() {
            this.f2311f = null;
            this.f2310e = this;
            this.f2309d = this;
        }

        C0722d(C0722d<K, V> c0722d, K k, C0722d<K, V> c0722d2, C0722d<K, V> c0722d3) {
            this.f2306a = c0722d;
            this.f2311f = k;
            this.f2313h = 1;
            this.f2309d = c0722d2;
            this.f2310e = c0722d3;
            c0722d3.f2309d = this;
            c0722d2.f2310e = this;
        }

        public K getKey() {
            return this.f2311f;
        }

        public V getValue() {
            return this.f2312g;
        }

        public V setValue(V v) {
            V v2 = this.f2312g;
            this.f2312g = v;
            return v2;
        }

        /* JADX WARNING: inconsistent code. */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public boolean equals(java.lang.Object r4) {
            /*
            r3 = this;
            r0 = r4 instanceof java.util.Map.Entry;
            r1 = 0;
            if (r0 == 0) goto L_0x0037;
        L_0x0005:
            r4 = (java.util.Map.Entry) r4;
            r0 = r3.f2311f;
            if (r0 != 0) goto L_0x0012;
        L_0x000b:
            r0 = r4.getKey();
            if (r0 != 0) goto L_0x0036;
        L_0x0011:
            goto L_0x001e;
        L_0x0012:
            r0 = r3.f2311f;
            r2 = r4.getKey();
            r0 = r0.equals(r2);
            if (r0 == 0) goto L_0x0036;
        L_0x001e:
            r0 = r3.f2312g;
            if (r0 != 0) goto L_0x0029;
        L_0x0022:
            r3 = r4.getValue();
            if (r3 != 0) goto L_0x0036;
        L_0x0028:
            goto L_0x0035;
        L_0x0029:
            r3 = r3.f2312g;
            r4 = r4.getValue();
            r3 = r3.equals(r4);
            if (r3 == 0) goto L_0x0036;
        L_0x0035:
            r1 = 1;
        L_0x0036:
            return r1;
        L_0x0037:
            return r1;
            */
            throw new UnsupportedOperationException("Method not decompiled: com.a.a.b.g.d.equals(java.lang.Object):boolean");
        }

        public int hashCode() {
            int i = 0;
            int hashCode = this.f2311f == null ? 0 : this.f2311f.hashCode();
            if (this.f2312g != null) {
                i = this.f2312g.hashCode();
            }
            return hashCode ^ i;
        }

        public String toString() {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(this.f2311f);
            stringBuilder.append("=");
            stringBuilder.append(this.f2312g);
            return stringBuilder.toString();
        }

        /* renamed from: a */
        public C0722d<K, V> m2996a() {
            C0722d<K, V> c0722d = this.f2307b;
            while (true) {
                C0722d<K, V> c0722d2 = c0722d;
                c0722d = r2;
                C0722d<K, V> c0722d3 = c0722d2;
                if (c0722d3 == null) {
                    return c0722d;
                }
                c0722d = c0722d3.f2307b;
            }
        }

        /* renamed from: b */
        public C0722d<K, V> m2997b() {
            C0722d<K, V> c0722d = this.f2308c;
            while (true) {
                C0722d<K, V> c0722d2 = c0722d;
                c0722d = r2;
                C0722d<K, V> c0722d3 = c0722d2;
                if (c0722d3 == null) {
                    return c0722d;
                }
                c0722d = c0722d3.f2308c;
            }
        }
    }

    public C0723g() {
        this(f2315g);
    }

    public C0723g(Comparator<? super K> comparator) {
        this.f2318c = 0;
        this.f2319d = 0;
        this.f2320e = new C0722d();
        if (comparator == null) {
            comparator = f2315g;
        }
        this.f2316a = comparator;
    }

    public int size() {
        return this.f2318c;
    }

    public V get(Object obj) {
        C0722d a = m3003a(obj);
        return a != null ? a.f2312g : null;
    }

    public boolean containsKey(Object obj) {
        return m3003a(obj) != null ? f2314f : false;
    }

    public V put(K k, V v) {
        if (k == null) {
            throw new NullPointerException("key == null");
        }
        C0722d a = m3004a((Object) k, (boolean) f2314f);
        k = a.f2312g;
        a.f2312g = v;
        return k;
    }

    public void clear() {
        this.f2317b = null;
        this.f2318c = 0;
        this.f2319d++;
        C0722d c0722d = this.f2320e;
        c0722d.f2310e = c0722d;
        c0722d.f2309d = c0722d;
    }

    public V remove(Object obj) {
        C0722d b = m3007b(obj);
        return b != null ? b.f2312g : null;
    }

    /* renamed from: a */
    C0722d<K, V> m3004a(K k, boolean z) {
        int compareTo;
        Comparator comparator = this.f2316a;
        C0722d c0722d = this.f2317b;
        if (c0722d != null) {
            Comparable comparable = comparator == f2315g ? (Comparable) k : null;
            while (true) {
                if (comparable != null) {
                    compareTo = comparable.compareTo(c0722d.f2311f);
                } else {
                    compareTo = comparator.compare(k, c0722d.f2311f);
                }
                if (compareTo == 0) {
                    return c0722d;
                }
                C0722d c0722d2;
                if (compareTo < 0) {
                    c0722d2 = c0722d.f2307b;
                } else {
                    c0722d2 = c0722d.f2308c;
                }
                if (c0722d2 == null) {
                    break;
                }
                c0722d = c0722d2;
            }
        } else {
            compareTo = 0;
        }
        if (!z) {
            return null;
        }
        C0722d<K, V> c0722d3;
        z = this.f2320e;
        if (c0722d != null) {
            c0722d3 = new C0722d(c0722d, k, z, z.f2310e);
            if (compareTo < 0) {
                c0722d.f2307b = c0722d3;
            } else {
                c0722d.f2308c = c0722d3;
            }
            m3002b(c0722d, f2314f);
        } else if (comparator != f2315g || (k instanceof Comparable)) {
            c0722d3 = new C0722d(c0722d, k, z, z.f2310e);
            this.f2317b = c0722d3;
        } else {
            z = new StringBuilder();
            z.append(k.getClass().getName());
            z.append(" is not Comparable");
            throw new ClassCastException(z.toString());
        }
        this.f2318c += 1;
        this.f2319d += 1;
        return c0722d3;
    }

    /* renamed from: a */
    com.p031a.p032a.p034b.C0723g.C0722d<K, V> m3003a(java.lang.Object r3) {
        /* JADX: method processing error */
/*
Error: java.lang.NullPointerException
	at jadx.core.dex.visitors.regions.ProcessTryCatchRegions.searchTryCatchDominators(ProcessTryCatchRegions.java:75)
	at jadx.core.dex.visitors.regions.ProcessTryCatchRegions.process(ProcessTryCatchRegions.java:45)
	at jadx.core.dex.visitors.regions.RegionMakerVisitor.postProcessRegions(RegionMakerVisitor.java:63)
	at jadx.core.dex.visitors.regions.RegionMakerVisitor.visit(RegionMakerVisitor.java:58)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:31)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:17)
	at jadx.core.ProcessClass.process(ProcessClass.java:34)
	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:282)
	at jadx.api.JavaClass.decompile(JavaClass.java:62)
	at jadx.api.JadxDecompiler.lambda$appendSourcesSave$0(JadxDecompiler.java:200)
	at jadx.api.JadxDecompiler$$Lambda$8/1201484275.run(Unknown Source)
*/
        /*
        r2 = this;
        r0 = 0;
        if (r3 == 0) goto L_0x000a;
    L_0x0003:
        r1 = 0;
        r2 = r2.m3004a(r3, r1);	 Catch:{ ClassCastException -> 0x0009 }
        goto L_0x000b;
    L_0x0009:
        return r0;
    L_0x000a:
        r2 = r0;
    L_0x000b:
        return r2;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.a.a.b.g.a(java.lang.Object):com.a.a.b.g$d<K, V>");
    }

    /* renamed from: a */
    C0722d<K, V> m3005a(Entry<?, ?> entry) {
        C0722d<K, V> a = m3003a(entry.getKey());
        Object obj = (a == null || !m3000a(a.f2312g, entry.getValue())) ? null : 1;
        return obj != null ? a : null;
    }

    /* renamed from: a */
    private boolean m3000a(Object obj, Object obj2) {
        if (obj != obj2) {
            if (obj == null || !obj.equals(obj2)) {
                return false;
            }
        }
        return f2314f;
    }

    /* renamed from: a */
    void m3006a(C0722d<K, V> c0722d, boolean z) {
        if (z) {
            c0722d.f2310e.f2309d = c0722d.f2309d;
            c0722d.f2309d.f2310e = c0722d.f2310e;
        }
        C0722d c0722d2 = c0722d.f2307b;
        C0722d c0722d3 = c0722d.f2308c;
        C0722d c0722d4 = c0722d.f2306a;
        int i = 0;
        if (!c0722d2 != false || c0722d3 == null) {
            if (c0722d2 == true) {
                m2999a((C0722d) c0722d, c0722d2);
                c0722d.f2307b = null;
            } else if (c0722d3 != null) {
                m2999a((C0722d) c0722d, c0722d3);
                c0722d.f2308c = null;
            } else {
                m2999a((C0722d) c0722d, null);
            }
            m3002b(c0722d4, false);
            this.f2318c--;
            this.f2319d++;
            return;
        }
        int i2;
        c0722d2 = c0722d2.f2313h > c0722d3.f2313h ? c0722d2.m2997b() : c0722d3.m2996a();
        m3006a(c0722d2, false);
        c0722d3 = c0722d.f2307b;
        if (c0722d3 != null) {
            i2 = c0722d3.f2313h;
            c0722d2.f2307b = c0722d3;
            c0722d3.f2306a = c0722d2;
            c0722d.f2307b = null;
        } else {
            i2 = 0;
        }
        c0722d3 = c0722d.f2308c;
        if (c0722d3 != null) {
            i = c0722d3.f2313h;
            c0722d2.f2308c = c0722d3;
            c0722d3.f2306a = c0722d2;
            c0722d.f2308c = null;
        }
        c0722d2.f2313h = Math.max(i2, i) + 1;
        m2999a((C0722d) c0722d, c0722d2);
    }

    /* renamed from: b */
    C0722d<K, V> m3007b(Object obj) {
        C0722d a = m3003a(obj);
        if (a != null) {
            m3006a(a, (boolean) f2314f);
        }
        return a;
    }

    /* renamed from: a */
    private void m2999a(C0722d<K, V> c0722d, C0722d<K, V> c0722d2) {
        C0722d c0722d3 = c0722d.f2306a;
        c0722d.f2306a = null;
        if (c0722d2 != null) {
            c0722d2.f2306a = c0722d3;
        }
        if (c0722d3 == null) {
            this.f2317b = c0722d2;
        } else if (c0722d3.f2307b == c0722d) {
            c0722d3.f2307b = c0722d2;
        } else if (f2314f || c0722d3.f2308c == c0722d) {
            c0722d3.f2308c = c0722d2;
        } else {
            throw new AssertionError();
        }
    }

    /* renamed from: b */
    private void m3002b(C0722d<K, V> c0722d, boolean z) {
        C0722d c0722d2;
        while (c0722d2 != null) {
            C0722d c0722d3 = c0722d2.f2307b;
            C0722d c0722d4 = c0722d2.f2308c;
            int i = 0;
            int i2 = c0722d3 != null ? c0722d3.f2313h : 0;
            int i3 = c0722d4 != null ? c0722d4.f2313h : 0;
            int i4 = i2 - i3;
            C0722d c0722d5;
            if (i4 == -2) {
                c0722d3 = c0722d4.f2307b;
                c0722d5 = c0722d4.f2308c;
                i2 = c0722d5 != null ? c0722d5.f2313h : 0;
                if (c0722d3 != null) {
                    i = c0722d3.f2313h;
                }
                i -= i2;
                if (i != -1) {
                    if (i != 0 || z) {
                        if (f2314f || i == 1) {
                            m3001b(c0722d4);
                            m2998a(c0722d2);
                            if (z) {
                                return;
                            }
                        } else {
                            throw new AssertionError();
                        }
                    }
                }
                m2998a(c0722d2);
                if (z) {
                    return;
                }
            } else if (i4 == 2) {
                c0722d4 = c0722d3.f2307b;
                c0722d5 = c0722d3.f2308c;
                i2 = c0722d5 != null ? c0722d5.f2313h : 0;
                if (c0722d4 != null) {
                    i = c0722d4.f2313h;
                }
                i -= i2;
                if (i != 1) {
                    if (i != 0 || z) {
                        if (f2314f || i == -1) {
                            m2998a(c0722d3);
                            m3001b(c0722d2);
                            if (z) {
                                return;
                            }
                        } else {
                            throw new AssertionError();
                        }
                    }
                }
                m3001b(c0722d2);
                if (z) {
                    return;
                }
            } else if (i4 == 0) {
                c0722d2.f2313h = i2 + 1;
                if (z) {
                    return;
                }
            } else if (f2314f || i4 == -1 || i4 == 1) {
                c0722d2.f2313h = Math.max(i2, i3) + 1;
                if (!z) {
                    return;
                }
            } else {
                throw new AssertionError();
            }
            c0722d2 = c0722d2.f2306a;
        }
    }

    /* renamed from: a */
    private void m2998a(C0722d<K, V> c0722d) {
        C0722d c0722d2 = c0722d.f2307b;
        C0722d c0722d3 = c0722d.f2308c;
        C0722d c0722d4 = c0722d3.f2307b;
        C0722d c0722d5 = c0722d3.f2308c;
        c0722d.f2308c = c0722d4;
        if (c0722d4 != null) {
            c0722d4.f2306a = c0722d;
        }
        m2999a((C0722d) c0722d, c0722d3);
        c0722d3.f2307b = c0722d;
        c0722d.f2306a = c0722d3;
        int i = 0;
        c0722d.f2313h = Math.max(c0722d2 != null ? c0722d2.f2313h : 0, c0722d4 != null ? c0722d4.f2313h : 0) + 1;
        c0722d = c0722d.f2313h;
        if (c0722d5 != null) {
            i = c0722d5.f2313h;
        }
        c0722d3.f2313h = Math.max(c0722d, i) + 1;
    }

    /* renamed from: b */
    private void m3001b(C0722d<K, V> c0722d) {
        C0722d c0722d2 = c0722d.f2307b;
        C0722d c0722d3 = c0722d.f2308c;
        C0722d c0722d4 = c0722d2.f2307b;
        C0722d c0722d5 = c0722d2.f2308c;
        c0722d.f2307b = c0722d5;
        if (c0722d5 != null) {
            c0722d5.f2306a = c0722d;
        }
        m2999a((C0722d) c0722d, c0722d2);
        c0722d2.f2308c = c0722d;
        c0722d.f2306a = c0722d2;
        int i = 0;
        c0722d.f2313h = Math.max(c0722d3 != null ? c0722d3.f2313h : 0, c0722d5 != null ? c0722d5.f2313h : 0) + 1;
        c0722d = c0722d.f2313h;
        if (c0722d4 != null) {
            i = c0722d4.f2313h;
        }
        c0722d2.f2313h = Math.max(c0722d, i) + 1;
    }

    public Set<Entry<K, V>> entrySet() {
        Set<Entry<K, V>> set = this.f2321h;
        if (set != null) {
            return set;
        }
        Set c0719a = new C0719a(this);
        this.f2321h = c0719a;
        return c0719a;
    }

    public Set<K> keySet() {
        Set<K> set = this.f2322i;
        if (set != null) {
            return set;
        }
        Set c0720b = new C0720b(this);
        this.f2322i = c0720b;
        return c0720b;
    }
}
