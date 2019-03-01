package com.a.a.b.a;

import com.a.a.b.f;
import com.a.a.d.b;
import com.a.a.d.c;
import com.a.a.e;
import com.a.a.g;
import com.a.a.j;
import com.a.a.k;
import com.a.a.l;
import com.a.a.m;
import com.a.a.o;
import com.a.a.r;
import com.a.a.t;
import com.a.a.u;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.net.InetAddress;
import java.net.URI;
import java.net.URL;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.BitSet;
import java.util.Calendar;
import java.util.Currency;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Map.Entry;
import java.util.StringTokenizer;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicIntegerArray;

/* compiled from: TypeAdapters */
public final class n {
    public static final t<String> A = new t<String>() {
        /* renamed from: a */
        public String b(com.a.a.d.a aVar) {
            b f = aVar.f();
            if (f == b.NULL) {
                aVar.j();
                return null;
            } else if (f == b.BOOLEAN) {
                return Boolean.toString(aVar.i());
            } else {
                return aVar.h();
            }
        }

        public void a(c cVar, String str) {
            cVar.b(str);
        }
    };
    public static final t<BigDecimal> B = new t<BigDecimal>() {
        /* renamed from: a */
        public BigDecimal b(com.a.a.d.a aVar) {
            if (aVar.f() == b.NULL) {
                aVar.j();
                return null;
            }
            try {
                return new BigDecimal(aVar.h());
            } catch (Throwable e) {
                throw new r(e);
            }
        }

        public void a(c cVar, BigDecimal bigDecimal) {
            cVar.a((Number) bigDecimal);
        }
    };
    public static final t<BigInteger> C = new t<BigInteger>() {
        /* renamed from: a */
        public BigInteger b(com.a.a.d.a aVar) {
            if (aVar.f() == b.NULL) {
                aVar.j();
                return null;
            }
            try {
                return new BigInteger(aVar.h());
            } catch (Throwable e) {
                throw new r(e);
            }
        }

        public void a(c cVar, BigInteger bigInteger) {
            cVar.a((Number) bigInteger);
        }
    };
    public static final u D = a(String.class, A);
    public static final t<StringBuilder> E = new t<StringBuilder>() {
        /* renamed from: a */
        public StringBuilder b(com.a.a.d.a aVar) {
            if (aVar.f() != b.NULL) {
                return new StringBuilder(aVar.h());
            }
            aVar.j();
            return null;
        }

        public void a(c cVar, StringBuilder stringBuilder) {
            cVar.b(stringBuilder == null ? null : stringBuilder.toString());
        }
    };
    public static final u F = a(StringBuilder.class, E);
    public static final t<StringBuffer> G = new t<StringBuffer>() {
        /* renamed from: a */
        public StringBuffer b(com.a.a.d.a aVar) {
            if (aVar.f() != b.NULL) {
                return new StringBuffer(aVar.h());
            }
            aVar.j();
            return null;
        }

        public void a(c cVar, StringBuffer stringBuffer) {
            cVar.b(stringBuffer == null ? null : stringBuffer.toString());
        }
    };
    public static final u H = a(StringBuffer.class, G);
    public static final t<URL> I = new t<URL>() {
        /* renamed from: a */
        public URL b(com.a.a.d.a aVar) {
            URL url = null;
            if (aVar.f() == b.NULL) {
                aVar.j();
                return null;
            }
            String h = aVar.h();
            if (!"null".equals(h)) {
                url = new URL(h);
            }
            return url;
        }

        public void a(c cVar, URL url) {
            cVar.b(url == null ? null : url.toExternalForm());
        }
    };
    public static final u J = a(URL.class, I);
    public static final t<URI> K = new t<URI>() {
        /* renamed from: a */
        public URI b(com.a.a.d.a aVar) {
            URI uri = null;
            if (aVar.f() == b.NULL) {
                aVar.j();
                return null;
            }
            try {
                String h = aVar.h();
                if (!"null".equals(h)) {
                    uri = new URI(h);
                }
                return uri;
            } catch (Throwable e) {
                throw new k(e);
            }
        }

        public void a(c cVar, URI uri) {
            cVar.b(uri == null ? null : uri.toASCIIString());
        }
    };
    public static final u L = a(URI.class, K);
    public static final t<InetAddress> M = new t<InetAddress>() {
        /* renamed from: a */
        public InetAddress b(com.a.a.d.a aVar) {
            if (aVar.f() != b.NULL) {
                return InetAddress.getByName(aVar.h());
            }
            aVar.j();
            return null;
        }

        public void a(c cVar, InetAddress inetAddress) {
            cVar.b(inetAddress == null ? null : inetAddress.getHostAddress());
        }
    };
    public static final u N = b(InetAddress.class, M);
    public static final t<UUID> O = new t<UUID>() {
        /* renamed from: a */
        public UUID b(com.a.a.d.a aVar) {
            if (aVar.f() != b.NULL) {
                return UUID.fromString(aVar.h());
            }
            aVar.j();
            return null;
        }

        public void a(c cVar, UUID uuid) {
            cVar.b(uuid == null ? null : uuid.toString());
        }
    };
    public static final u P = a(UUID.class, O);
    public static final t<Currency> Q = new t<Currency>() {
        /* renamed from: a */
        public Currency b(com.a.a.d.a aVar) {
            return Currency.getInstance(aVar.h());
        }

        public void a(c cVar, Currency currency) {
            cVar.b(currency.getCurrencyCode());
        }
    }.a();
    public static final u R = a(Currency.class, Q);
    public static final u S = new u() {
        public <T> t<T> a(e eVar, com.a.a.c.a<T> aVar) {
            if (aVar.getRawType() != Timestamp.class) {
                return null;
            }
            final t a = eVar.a(Date.class);
            return new t<Timestamp>() {
                /* renamed from: a */
                public Timestamp b(com.a.a.d.a aVar) {
                    Date date = (Date) a.b(aVar);
                    return date != null ? new Timestamp(date.getTime()) : null;
                }

                public void a(c cVar, Timestamp timestamp) {
                    a.a(cVar, timestamp);
                }
            };
        }
    };
    public static final t<Calendar> T = new t<Calendar>() {
        /* renamed from: a */
        public Calendar b(com.a.a.d.a aVar) {
            if (aVar.f() == b.NULL) {
                aVar.j();
                return null;
            }
            aVar.c();
            int i = 0;
            int i2 = i;
            int i3 = i2;
            int i4 = i3;
            int i5 = i4;
            int i6 = i5;
            while (aVar.f() != b.END_OBJECT) {
                String g = aVar.g();
                int m = aVar.m();
                if ("year".equals(g)) {
                    i = m;
                } else if ("month".equals(g)) {
                    i2 = m;
                } else if ("dayOfMonth".equals(g)) {
                    i3 = m;
                } else if ("hourOfDay".equals(g)) {
                    i4 = m;
                } else if ("minute".equals(g)) {
                    i5 = m;
                } else if ("second".equals(g)) {
                    i6 = m;
                }
            }
            aVar.d();
            return new GregorianCalendar(i, i2, i3, i4, i5, i6);
        }

        public void a(c cVar, Calendar calendar) {
            if (calendar == null) {
                cVar.f();
                return;
            }
            cVar.d();
            cVar.a("year");
            cVar.a((long) calendar.get(1));
            cVar.a("month");
            cVar.a((long) calendar.get(2));
            cVar.a("dayOfMonth");
            cVar.a((long) calendar.get(5));
            cVar.a("hourOfDay");
            cVar.a((long) calendar.get(11));
            cVar.a("minute");
            cVar.a((long) calendar.get(12));
            cVar.a("second");
            cVar.a((long) calendar.get(13));
            cVar.e();
        }
    };
    public static final u U = b(Calendar.class, GregorianCalendar.class, T);
    public static final t<Locale> V = new t<Locale>() {
        /* renamed from: a */
        public Locale b(com.a.a.d.a aVar) {
            String str = null;
            if (aVar.f() == b.NULL) {
                aVar.j();
                return null;
            }
            StringTokenizer stringTokenizer = new StringTokenizer(aVar.h(), "_");
            String nextToken = stringTokenizer.hasMoreElements() ? stringTokenizer.nextToken() : null;
            String nextToken2 = stringTokenizer.hasMoreElements() ? stringTokenizer.nextToken() : null;
            if (stringTokenizer.hasMoreElements()) {
                str = stringTokenizer.nextToken();
            }
            if (nextToken2 == null && str == null) {
                return new Locale(nextToken);
            }
            if (str == null) {
                return new Locale(nextToken, nextToken2);
            }
            return new Locale(nextToken, nextToken2, str);
        }

        public void a(c cVar, Locale locale) {
            cVar.b(locale == null ? null : locale.toString());
        }
    };
    public static final u W = a(Locale.class, V);
    public static final t<j> X = new t<j>() {
        /* renamed from: a */
        public j b(com.a.a.d.a aVar) {
            j gVar;
            switch (aVar.f()) {
                case NUMBER:
                    return new o(new f(aVar.h()));
                case BOOLEAN:
                    return new o(Boolean.valueOf(aVar.i()));
                case STRING:
                    return new o(aVar.h());
                case NULL:
                    aVar.j();
                    return l.a;
                case BEGIN_ARRAY:
                    gVar = new g();
                    aVar.a();
                    while (aVar.e()) {
                        gVar.a(b(aVar));
                    }
                    aVar.b();
                    return gVar;
                case BEGIN_OBJECT:
                    gVar = new m();
                    aVar.c();
                    while (aVar.e()) {
                        gVar.a(aVar.g(), b(aVar));
                    }
                    aVar.d();
                    return gVar;
                default:
                    throw new IllegalArgumentException();
            }
        }

        public void a(c cVar, j jVar) {
            Iterator it;
            if (jVar == null || jVar.j()) {
                cVar.f();
            } else if (jVar.i()) {
                o m = jVar.m();
                if (m.p()) {
                    cVar.a(m.a());
                } else if (m.o()) {
                    cVar.a(m.f());
                } else {
                    cVar.b(m.b());
                }
            } else if (jVar.g()) {
                cVar.b();
                it = jVar.l().iterator();
                while (it.hasNext()) {
                    a(cVar, (j) it.next());
                }
                cVar.c();
            } else if (jVar.h()) {
                cVar.d();
                for (Entry entry : jVar.k().o()) {
                    cVar.a((String) entry.getKey());
                    a(cVar, (j) entry.getValue());
                }
                cVar.e();
            } else {
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append("Couldn't write ");
                stringBuilder.append(jVar.getClass());
                throw new IllegalArgumentException(stringBuilder.toString());
            }
        }
    };
    public static final u Y = b(j.class, X);
    public static final u Z = new u() {
        public <T> t<T> a(e eVar, com.a.a.c.a<T> aVar) {
            Class rawType = aVar.getRawType();
            if (!Enum.class.isAssignableFrom(rawType) || rawType == Enum.class) {
                return null;
            }
            if (!rawType.isEnum()) {
                rawType = rawType.getSuperclass();
            }
            return new a(rawType);
        }
    };
    public static final t<Class> a = new t<Class>() {
        public void a(c cVar, Class cls) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("Attempted to serialize java.lang.Class: ");
            stringBuilder.append(cls.getName());
            stringBuilder.append(". Forgot to register a type adapter?");
            throw new UnsupportedOperationException(stringBuilder.toString());
        }

        /* renamed from: a */
        public Class b(com.a.a.d.a aVar) {
            throw new UnsupportedOperationException("Attempted to deserialize a java.lang.Class. Forgot to register a type adapter?");
        }
    }.a();
    public static final u b = a(Class.class, a);
    public static final t<BitSet> c = new t<BitSet>() {
        /* JADX WARNING: Missing block: B:10:0x003d, code:
            if (java.lang.Integer.parseInt(r0) != 0) goto L_0x0064;
     */
        /* JADX WARNING: Missing block: B:11:0x0040, code:
            r4 = false;
     */
        /* JADX WARNING: Missing block: B:16:0x0062, code:
            if (r6.m() != 0) goto L_0x0064;
     */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        /* renamed from: a */
        public BitSet b(com.a.a.d.a aVar) {
            StringBuilder stringBuilder;
            BitSet bitSet = new BitSet();
            aVar.a();
            b f = aVar.f();
            int i = 0;
            while (f != b.END_ARRAY) {
                boolean z = true;
                switch (f) {
                    case NUMBER:
                        break;
                    case BOOLEAN:
                        z = aVar.i();
                        break;
                    case STRING:
                        String h = aVar.h();
                        try {
                            break;
                        } catch (NumberFormatException unused) {
                            stringBuilder = new StringBuilder();
                            stringBuilder.append("Error: Expecting: bitset number value (1, 0), Found: ");
                            stringBuilder.append(h);
                            throw new r(stringBuilder.toString());
                        }
                    default:
                        stringBuilder = new StringBuilder();
                        stringBuilder.append("Invalid bitset value type: ");
                        stringBuilder.append(f);
                        throw new r(stringBuilder.toString());
                }
                if (z) {
                    bitSet.set(i);
                }
                i++;
                f = aVar.f();
            }
            aVar.b();
            return bitSet;
        }

        public void a(c cVar, BitSet bitSet) {
            cVar.b();
            int length = bitSet.length();
            for (int i = 0; i < length; i++) {
                cVar.a((long) bitSet.get(i));
            }
            cVar.c();
        }
    }.a();
    public static final u d = a(BitSet.class, c);
    public static final t<Boolean> e = new t<Boolean>() {
        /* renamed from: a */
        public Boolean b(com.a.a.d.a aVar) {
            if (aVar.f() == b.NULL) {
                aVar.j();
                return null;
            } else if (aVar.f() == b.STRING) {
                return Boolean.valueOf(Boolean.parseBoolean(aVar.h()));
            } else {
                return Boolean.valueOf(aVar.i());
            }
        }

        public void a(c cVar, Boolean bool) {
            cVar.a(bool);
        }
    };
    public static final t<Boolean> f = new t<Boolean>() {
        /* renamed from: a */
        public Boolean b(com.a.a.d.a aVar) {
            if (aVar.f() != b.NULL) {
                return Boolean.valueOf(aVar.h());
            }
            aVar.j();
            return null;
        }

        public void a(c cVar, Boolean bool) {
            cVar.b(bool == null ? "null" : bool.toString());
        }
    };
    public static final u g = a(Boolean.TYPE, Boolean.class, e);
    public static final t<Number> h = new t<Number>() {
        /* renamed from: a */
        public Number b(com.a.a.d.a aVar) {
            if (aVar.f() == b.NULL) {
                aVar.j();
                return null;
            }
            try {
                return Byte.valueOf((byte) aVar.m());
            } catch (Throwable e) {
                throw new r(e);
            }
        }

        public void a(c cVar, Number number) {
            cVar.a(number);
        }
    };
    public static final u i = a(Byte.TYPE, Byte.class, h);
    public static final t<Number> j = new t<Number>() {
        /* renamed from: a */
        public Number b(com.a.a.d.a aVar) {
            if (aVar.f() == b.NULL) {
                aVar.j();
                return null;
            }
            try {
                return Short.valueOf((short) aVar.m());
            } catch (Throwable e) {
                throw new r(e);
            }
        }

        public void a(c cVar, Number number) {
            cVar.a(number);
        }
    };
    public static final u k = a(Short.TYPE, Short.class, j);
    public static final t<Number> l = new t<Number>() {
        /* renamed from: a */
        public Number b(com.a.a.d.a aVar) {
            if (aVar.f() == b.NULL) {
                aVar.j();
                return null;
            }
            try {
                return Integer.valueOf(aVar.m());
            } catch (Throwable e) {
                throw new r(e);
            }
        }

        public void a(c cVar, Number number) {
            cVar.a(number);
        }
    };
    public static final u m = a(Integer.TYPE, Integer.class, l);
    public static final t<AtomicInteger> n = new t<AtomicInteger>() {
        /* renamed from: a */
        public AtomicInteger b(com.a.a.d.a aVar) {
            try {
                return new AtomicInteger(aVar.m());
            } catch (Throwable e) {
                throw new r(e);
            }
        }

        public void a(c cVar, AtomicInteger atomicInteger) {
            cVar.a((long) atomicInteger.get());
        }
    }.a();
    public static final u o = a(AtomicInteger.class, n);
    public static final t<AtomicBoolean> p = new t<AtomicBoolean>() {
        /* renamed from: a */
        public AtomicBoolean b(com.a.a.d.a aVar) {
            return new AtomicBoolean(aVar.i());
        }

        public void a(c cVar, AtomicBoolean atomicBoolean) {
            cVar.a(atomicBoolean.get());
        }
    }.a();
    public static final u q = a(AtomicBoolean.class, p);
    public static final t<AtomicIntegerArray> r = new t<AtomicIntegerArray>() {
        /* renamed from: a */
        public AtomicIntegerArray b(com.a.a.d.a aVar) {
            List arrayList = new ArrayList();
            aVar.a();
            while (aVar.e()) {
                try {
                    arrayList.add(Integer.valueOf(aVar.m()));
                } catch (Throwable e) {
                    throw new r(e);
                }
            }
            aVar.b();
            int size = arrayList.size();
            AtomicIntegerArray atomicIntegerArray = new AtomicIntegerArray(size);
            for (int i = 0; i < size; i++) {
                atomicIntegerArray.set(i, ((Integer) arrayList.get(i)).intValue());
            }
            return atomicIntegerArray;
        }

        public void a(c cVar, AtomicIntegerArray atomicIntegerArray) {
            cVar.b();
            int length = atomicIntegerArray.length();
            for (int i = 0; i < length; i++) {
                cVar.a((long) atomicIntegerArray.get(i));
            }
            cVar.c();
        }
    }.a();
    public static final u s = a(AtomicIntegerArray.class, r);
    public static final t<Number> t = new t<Number>() {
        /* renamed from: a */
        public Number b(com.a.a.d.a aVar) {
            if (aVar.f() == b.NULL) {
                aVar.j();
                return null;
            }
            try {
                return Long.valueOf(aVar.l());
            } catch (Throwable e) {
                throw new r(e);
            }
        }

        public void a(c cVar, Number number) {
            cVar.a(number);
        }
    };
    public static final t<Number> u = new t<Number>() {
        /* renamed from: a */
        public Number b(com.a.a.d.a aVar) {
            if (aVar.f() != b.NULL) {
                return Float.valueOf((float) aVar.k());
            }
            aVar.j();
            return null;
        }

        public void a(c cVar, Number number) {
            cVar.a(number);
        }
    };
    public static final t<Number> v = new t<Number>() {
        /* renamed from: a */
        public Number b(com.a.a.d.a aVar) {
            if (aVar.f() != b.NULL) {
                return Double.valueOf(aVar.k());
            }
            aVar.j();
            return null;
        }

        public void a(c cVar, Number number) {
            cVar.a(number);
        }
    };
    public static final t<Number> w = new t<Number>() {
        /* renamed from: a */
        public Number b(com.a.a.d.a aVar) {
            b f = aVar.f();
            int i = AnonymousClass29.a[f.ordinal()];
            if (i != 1) {
                switch (i) {
                    case 3:
                        break;
                    case 4:
                        aVar.j();
                        return null;
                    default:
                        StringBuilder stringBuilder = new StringBuilder();
                        stringBuilder.append("Expecting number, got: ");
                        stringBuilder.append(f);
                        throw new r(stringBuilder.toString());
                }
            }
            return new f(aVar.h());
        }

        public void a(c cVar, Number number) {
            cVar.a(number);
        }
    };
    public static final u x = a(Number.class, w);
    public static final t<Character> y = new t<Character>() {
        /* renamed from: a */
        public Character b(com.a.a.d.a aVar) {
            if (aVar.f() == b.NULL) {
                aVar.j();
                return null;
            }
            String h = aVar.h();
            if (h.length() == 1) {
                return Character.valueOf(h.charAt(0));
            }
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("Expecting character, got: ");
            stringBuilder.append(h);
            throw new r(stringBuilder.toString());
        }

        public void a(c cVar, Character ch) {
            cVar.b(ch == null ? null : String.valueOf(ch));
        }
    };
    public static final u z = a(Character.TYPE, Character.class, y);

    /* compiled from: TypeAdapters */
    private static final class a<T extends Enum<T>> extends t<T> {
        private final Map<String, T> a = new HashMap();
        private final Map<T, String> b = new HashMap();

        public a(Class<T> cls) {
            try {
                for (Enum enumR : (Enum[]) cls.getEnumConstants()) {
                    Object name = enumR.name();
                    com.a.a.a.c cVar = (com.a.a.a.c) cls.getField(name).getAnnotation(com.a.a.a.c.class);
                    if (cVar != null) {
                        name = cVar.a();
                        for (Object put : cVar.b()) {
                            this.a.put(put, enumR);
                        }
                    }
                    this.a.put(name, enumR);
                    this.b.put(enumR, name);
                }
            } catch (NoSuchFieldException e) {
                throw new AssertionError(e);
            }
        }

        /* renamed from: a */
        public T b(com.a.a.d.a aVar) {
            if (aVar.f() != b.NULL) {
                return (Enum) this.a.get(aVar.h());
            }
            aVar.j();
            return null;
        }

        public void a(c cVar, T t) {
            cVar.b(t == null ? null : (String) this.b.get(t));
        }
    }

    public static <TT> u a(final Class<TT> cls, final t<TT> tVar) {
        return new u() {
            public <T> t<T> a(e eVar, com.a.a.c.a<T> aVar) {
                return aVar.getRawType() == cls ? tVar : null;
            }

            public String toString() {
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append("Factory[type=");
                stringBuilder.append(cls.getName());
                stringBuilder.append(",adapter=");
                stringBuilder.append(tVar);
                stringBuilder.append("]");
                return stringBuilder.toString();
            }
        };
    }

    public static <TT> u a(final Class<TT> cls, final Class<TT> cls2, final t<? super TT> tVar) {
        return new u() {
            public <T> t<T> a(e eVar, com.a.a.c.a<T> aVar) {
                Class rawType = aVar.getRawType();
                return (rawType == cls || rawType == cls2) ? tVar : null;
            }

            public String toString() {
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append("Factory[type=");
                stringBuilder.append(cls2.getName());
                stringBuilder.append("+");
                stringBuilder.append(cls.getName());
                stringBuilder.append(",adapter=");
                stringBuilder.append(tVar);
                stringBuilder.append("]");
                return stringBuilder.toString();
            }
        };
    }

    public static <TT> u b(final Class<TT> cls, final Class<? extends TT> cls2, final t<? super TT> tVar) {
        return new u() {
            public <T> t<T> a(e eVar, com.a.a.c.a<T> aVar) {
                Class rawType = aVar.getRawType();
                return (rawType == cls || rawType == cls2) ? tVar : null;
            }

            public String toString() {
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append("Factory[type=");
                stringBuilder.append(cls.getName());
                stringBuilder.append("+");
                stringBuilder.append(cls2.getName());
                stringBuilder.append(",adapter=");
                stringBuilder.append(tVar);
                stringBuilder.append("]");
                return stringBuilder.toString();
            }
        };
    }

    public static <T1> u b(final Class<T1> cls, final t<T1> tVar) {
        return new u() {
            public <T2> t<T2> a(e eVar, com.a.a.c.a<T2> aVar) {
                final Class rawType = aVar.getRawType();
                if (cls.isAssignableFrom(rawType)) {
                    return new t<T1>() {
                        public void a(c cVar, T1 t1) {
                            tVar.a(cVar, t1);
                        }

                        public T1 b(com.a.a.d.a aVar) {
                            T1 b = tVar.b(aVar);
                            if (b == null || rawType.isInstance(b)) {
                                return b;
                            }
                            StringBuilder stringBuilder = new StringBuilder();
                            stringBuilder.append("Expected a ");
                            stringBuilder.append(rawType.getName());
                            stringBuilder.append(" but was ");
                            stringBuilder.append(b.getClass().getName());
                            throw new r(stringBuilder.toString());
                        }
                    };
                }
                return null;
            }

            public String toString() {
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append("Factory[typeHierarchy=");
                stringBuilder.append(cls.getName());
                stringBuilder.append(",adapter=");
                stringBuilder.append(tVar);
                stringBuilder.append("]");
                return stringBuilder.toString();
            }
        };
    }
}
