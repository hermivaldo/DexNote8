package com.p031a.p032a.p034b.p035a;

import com.a.a.b.a.n.AnonymousClass19;
import com.a.a.b.a.n.AnonymousClass28;
import com.a.a.b.a.n.AnonymousClass29;
import com.p031a.p032a.C0737e;
import com.p031a.p032a.C0741j;
import com.p031a.p032a.C0746t;
import com.p031a.p032a.C0747u;
import com.p031a.p032a.C1274g;
import com.p031a.p032a.C1275k;
import com.p031a.p032a.C1276l;
import com.p031a.p032a.C1277m;
import com.p031a.p032a.C1278o;
import com.p031a.p032a.C1279r;
import com.p031a.p032a.p033a.C0699c;
import com.p031a.p032a.p034b.C0717f;
import com.p031a.p032a.p034b.p035a.C0709n.C1248a;
import com.p031a.p032a.p037c.C0731a;
import com.p031a.p032a.p038d.C0732a;
import com.p031a.p032a.p038d.C0733b;
import com.p031a.p032a.p038d.C0734c;
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
/* renamed from: com.a.a.b.a.n */
public final class C0709n {
    /* renamed from: A */
    public static final C0746t<String> f2238A = new C12468();
    /* renamed from: B */
    public static final C0746t<BigDecimal> f2239B = new C12479();
    /* renamed from: C */
    public static final C0746t<BigInteger> f2240C = new C0746t<BigInteger>() {
        /* renamed from: b */
        public /* synthetic */ Object mo592b(C0732a c0732a) {
            return m5786a(c0732a);
        }

        /* renamed from: a */
        public BigInteger m5786a(C0732a c0732a) {
            if (c0732a.mo599f() == C0733b.NULL) {
                c0732a.mo603j();
                return null;
            }
            try {
                return new BigInteger(c0732a.mo601h());
            } catch (Throwable e) {
                throw new C1279r(e);
            }
        }

        /* renamed from: a */
        public void m5788a(C0734c c0734c, BigInteger bigInteger) {
            c0734c.mo613a((Number) bigInteger);
        }
    };
    /* renamed from: D */
    public static final C0747u f2241D = C0709n.m2962a(String.class, f2238A);
    /* renamed from: E */
    public static final C0746t<StringBuilder> f2242E = new C0746t<StringBuilder>() {
        /* renamed from: b */
        public /* synthetic */ Object mo592b(C0732a c0732a) {
            return m5790a(c0732a);
        }

        /* renamed from: a */
        public StringBuilder m5790a(C0732a c0732a) {
            if (c0732a.mo599f() != C0733b.NULL) {
                return new StringBuilder(c0732a.mo601h());
            }
            c0732a.mo603j();
            return null;
        }

        /* renamed from: a */
        public void m5792a(C0734c c0734c, StringBuilder stringBuilder) {
            c0734c.mo618b(stringBuilder == null ? null : stringBuilder.toString());
        }
    };
    /* renamed from: F */
    public static final C0747u f2243F = C0709n.m2962a(StringBuilder.class, f2242E);
    /* renamed from: G */
    public static final C0746t<StringBuffer> f2244G = new C0746t<StringBuffer>() {
        /* renamed from: b */
        public /* synthetic */ Object mo592b(C0732a c0732a) {
            return m5798a(c0732a);
        }

        /* renamed from: a */
        public StringBuffer m5798a(C0732a c0732a) {
            if (c0732a.mo599f() != C0733b.NULL) {
                return new StringBuffer(c0732a.mo601h());
            }
            c0732a.mo603j();
            return null;
        }

        /* renamed from: a */
        public void m5800a(C0734c c0734c, StringBuffer stringBuffer) {
            c0734c.mo618b(stringBuffer == null ? null : stringBuffer.toString());
        }
    };
    /* renamed from: H */
    public static final C0747u f2245H = C0709n.m2962a(StringBuffer.class, f2244G);
    /* renamed from: I */
    public static final C0746t<URL> f2246I = new C0746t<URL>() {
        /* renamed from: b */
        public /* synthetic */ Object mo592b(C0732a c0732a) {
            return m5802a(c0732a);
        }

        /* renamed from: a */
        public URL m5802a(C0732a c0732a) {
            URL url = null;
            if (c0732a.mo599f() == C0733b.NULL) {
                c0732a.mo603j();
                return null;
            }
            String h = c0732a.mo601h();
            if ("null".equals(h) == null) {
                url = new URL(h);
            }
            return url;
        }

        /* renamed from: a */
        public void m5804a(C0734c c0734c, URL url) {
            c0734c.mo618b(url == null ? null : url.toExternalForm());
        }
    };
    /* renamed from: J */
    public static final C0747u f2247J = C0709n.m2962a(URL.class, f2246I);
    /* renamed from: K */
    public static final C0746t<URI> f2248K = new C0746t<URI>() {
        /* renamed from: b */
        public /* synthetic */ Object mo592b(C0732a c0732a) {
            return m5806a(c0732a);
        }

        /* renamed from: a */
        public URI m5806a(C0732a c0732a) {
            URI uri = null;
            if (c0732a.mo599f() == C0733b.NULL) {
                c0732a.mo603j();
                return null;
            }
            try {
                String h = c0732a.mo601h();
                if ("null".equals(h) == null) {
                    uri = new URI(h);
                }
                return uri;
            } catch (Throwable e) {
                throw new C1275k(e);
            }
        }

        /* renamed from: a */
        public void m5808a(C0734c c0734c, URI uri) {
            c0734c.mo618b(uri == null ? null : uri.toASCIIString());
        }
    };
    /* renamed from: L */
    public static final C0747u f2249L = C0709n.m2962a(URI.class, f2248K);
    /* renamed from: M */
    public static final C0746t<InetAddress> f2250M = new C0746t<InetAddress>() {
        /* renamed from: b */
        public /* synthetic */ Object mo592b(C0732a c0732a) {
            return m5810a(c0732a);
        }

        /* renamed from: a */
        public InetAddress m5810a(C0732a c0732a) {
            if (c0732a.mo599f() != C0733b.NULL) {
                return InetAddress.getByName(c0732a.mo601h());
            }
            c0732a.mo603j();
            return null;
        }

        /* renamed from: a */
        public void m5812a(C0734c c0734c, InetAddress inetAddress) {
            c0734c.mo618b(inetAddress == null ? null : inetAddress.getHostAddress());
        }
    };
    /* renamed from: N */
    public static final C0747u f2251N = C0709n.m2964b(InetAddress.class, f2250M);
    /* renamed from: O */
    public static final C0746t<UUID> f2252O = new C0746t<UUID>() {
        /* renamed from: b */
        public /* synthetic */ Object mo592b(C0732a c0732a) {
            return m5814a(c0732a);
        }

        /* renamed from: a */
        public UUID m5814a(C0732a c0732a) {
            if (c0732a.mo599f() != C0733b.NULL) {
                return UUID.fromString(c0732a.mo601h());
            }
            c0732a.mo603j();
            return null;
        }

        /* renamed from: a */
        public void m5816a(C0734c c0734c, UUID uuid) {
            c0734c.mo618b(uuid == null ? null : uuid.toString());
        }
    };
    /* renamed from: P */
    public static final C0747u f2253P = C0709n.m2962a(UUID.class, f2252O);
    /* renamed from: Q */
    public static final C0746t<Currency> f2254Q = new C0746t<Currency>() {
        /* renamed from: b */
        public /* synthetic */ Object mo592b(C0732a c0732a) {
            return m5818a(c0732a);
        }

        /* renamed from: a */
        public Currency m5818a(C0732a c0732a) {
            return Currency.getInstance(c0732a.mo601h());
        }

        /* renamed from: a */
        public void m5820a(C0734c c0734c, Currency currency) {
            c0734c.mo618b(currency.getCurrencyCode());
        }
    }.m3121a();
    /* renamed from: R */
    public static final C0747u f2255R = C0709n.m2962a(Currency.class, f2254Q);
    /* renamed from: S */
    public static final C0747u f2256S = new C0747u() {
        /* renamed from: a */
        public <T> C0746t<T> mo590a(C0737e c0737e, C0731a<T> c0731a) {
            if (c0731a.getRawType() != Timestamp.class) {
                return null;
            }
            c0737e = c0737e.m3092a(Date.class);
            return new C0746t<Timestamp>(this) {
                /* renamed from: b */
                final /* synthetic */ AnonymousClass19 f4244b;

                /* renamed from: b */
                public /* synthetic */ Object mo592b(C0732a c0732a) {
                    return m5822a(c0732a);
                }

                /* renamed from: a */
                public Timestamp m5822a(C0732a c0732a) {
                    Date date = (Date) c0737e.mo592b(c0732a);
                    return date != null ? new Timestamp(date.getTime()) : null;
                }

                /* renamed from: a */
                public void m5824a(C0734c c0734c, Timestamp timestamp) {
                    c0737e.mo591a(c0734c, timestamp);
                }
            };
        }
    };
    /* renamed from: T */
    public static final C0746t<Calendar> f2257T = new C0746t<Calendar>() {
        /* renamed from: b */
        public /* synthetic */ Object mo592b(C0732a c0732a) {
            return m5831a(c0732a);
        }

        /* renamed from: a */
        public Calendar m5831a(C0732a c0732a) {
            if (c0732a.mo599f() == C0733b.NULL) {
                c0732a.mo603j();
                return null;
            }
            c0732a.mo595c();
            int i = 0;
            int i2 = i;
            int i3 = i2;
            int i4 = i3;
            int i5 = i4;
            int i6 = i5;
            while (c0732a.mo599f() != C0733b.END_OBJECT) {
                String g = c0732a.mo600g();
                int m = c0732a.mo606m();
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
            c0732a.mo597d();
            return new GregorianCalendar(i, i2, i3, i4, i5, i6);
        }

        /* renamed from: a */
        public void m5833a(C0734c c0734c, Calendar calendar) {
            if (calendar == null) {
                c0734c.mo623f();
                return;
            }
            c0734c.mo621d();
            c0734c.mo614a("year");
            c0734c.mo611a((long) calendar.get(1));
            c0734c.mo614a("month");
            c0734c.mo611a((long) calendar.get(2));
            c0734c.mo614a("dayOfMonth");
            c0734c.mo611a((long) calendar.get(5));
            c0734c.mo614a("hourOfDay");
            c0734c.mo611a((long) calendar.get(11));
            c0734c.mo614a("minute");
            c0734c.mo611a((long) calendar.get(12));
            c0734c.mo614a("second");
            c0734c.mo611a((long) calendar.get(13));
            c0734c.mo622e();
        }
    };
    /* renamed from: U */
    public static final C0747u f2258U = C0709n.m2965b(Calendar.class, GregorianCalendar.class, f2257T);
    /* renamed from: V */
    public static final C0746t<Locale> f2259V = new C0746t<Locale>() {
        /* renamed from: b */
        public /* synthetic */ Object mo592b(C0732a c0732a) {
            return m5835a(c0732a);
        }

        /* renamed from: a */
        public Locale m5835a(C0732a c0732a) {
            String str = null;
            if (c0732a.mo599f() == C0733b.NULL) {
                c0732a.mo603j();
                return null;
            }
            c0732a = new StringTokenizer(c0732a.mo601h(), "_");
            String nextToken = c0732a.hasMoreElements() ? c0732a.nextToken() : null;
            String nextToken2 = c0732a.hasMoreElements() ? c0732a.nextToken() : null;
            if (c0732a.hasMoreElements()) {
                str = c0732a.nextToken();
            }
            if (nextToken2 == null && str == null) {
                return new Locale(nextToken);
            }
            if (str == null) {
                return new Locale(nextToken, nextToken2);
            }
            return new Locale(nextToken, nextToken2, str);
        }

        /* renamed from: a */
        public void m5837a(C0734c c0734c, Locale locale) {
            c0734c.mo618b(locale == null ? null : locale.toString());
        }
    };
    /* renamed from: W */
    public static final C0747u f2260W = C0709n.m2962a(Locale.class, f2259V);
    /* renamed from: X */
    public static final C0746t<C0741j> f2261X = new C0746t<C0741j>() {
        /* renamed from: b */
        public /* synthetic */ Object mo592b(C0732a c0732a) {
            return m5839a(c0732a);
        }

        /* renamed from: a */
        public C0741j m5839a(C0732a c0732a) {
            C0741j c1274g;
            switch (c0732a.mo599f()) {
                case NUMBER:
                    return new C1278o(new C0717f(c0732a.mo601h()));
                case BOOLEAN:
                    return new C1278o(Boolean.valueOf(c0732a.mo602i()));
                case STRING:
                    return new C1278o(c0732a.mo601h());
                case NULL:
                    c0732a.mo603j();
                    return C1276l.f4314a;
                case BEGIN_ARRAY:
                    c1274g = new C1274g();
                    c0732a.mo593a();
                    while (c0732a.mo598e()) {
                        c1274g.m5974a(m5839a(c0732a));
                    }
                    c0732a.mo594b();
                    return c1274g;
                case BEGIN_OBJECT:
                    c1274g = new C1277m();
                    c0732a.mo595c();
                    while (c0732a.mo598e()) {
                        c1274g.m5980a(c0732a.mo600g(), m5839a(c0732a));
                    }
                    c0732a.mo597d();
                    return c1274g;
                default:
                    throw new IllegalArgumentException();
            }
        }

        /* renamed from: a */
        public void m5840a(C0734c c0734c, C0741j c0741j) {
            if (c0741j != null) {
                if (!c0741j.m3114j()) {
                    if (c0741j.m3113i()) {
                        C1278o m = c0741j.m3117m();
                        if (m.m5993p() != null) {
                            c0734c.mo613a(m.mo631a());
                            return;
                        } else if (m.m5992o() != null) {
                            c0734c.mo615a(m.mo636f());
                            return;
                        } else {
                            c0734c.mo618b(m.mo632b());
                            return;
                        }
                    } else if (c0741j.m3111g()) {
                        c0734c.mo617b();
                        c0741j = c0741j.m3116l().iterator();
                        while (c0741j.hasNext()) {
                            m5840a(c0734c, (C0741j) c0741j.next());
                        }
                        c0734c.mo619c();
                        return;
                    } else if (c0741j.m3112h()) {
                        c0734c.mo621d();
                        for (Entry entry : c0741j.m3115k().m5981o()) {
                            c0734c.mo614a((String) entry.getKey());
                            m5840a(c0734c, (C0741j) entry.getValue());
                        }
                        c0734c.mo622e();
                        return;
                    } else {
                        c0734c = new StringBuilder();
                        c0734c.append("Couldn't write ");
                        c0734c.append(c0741j.getClass());
                        throw new IllegalArgumentException(c0734c.toString());
                    }
                }
            }
            c0734c.mo623f();
        }
    };
    /* renamed from: Y */
    public static final C0747u f2262Y = C0709n.m2964b(C0741j.class, f2261X);
    /* renamed from: Z */
    public static final C0747u f2263Z = new C0747u() {
        /* renamed from: a */
        public <T> C0746t<T> mo590a(C0737e c0737e, C0731a<T> c0731a) {
            Class rawType = c0731a.getRawType();
            if (Enum.class.isAssignableFrom(rawType) != null) {
                if (rawType != Enum.class) {
                    if (rawType.isEnum() == null) {
                        rawType = rawType.getSuperclass();
                    }
                    return new C1248a(rawType);
                }
            }
            return null;
        }
    };
    /* renamed from: a */
    public static final C0746t<Class> f2264a = new C12381().m3121a();
    /* renamed from: b */
    public static final C0747u f2265b = C0709n.m2962a(Class.class, f2264a);
    /* renamed from: c */
    public static final C0746t<BitSet> f2266c = new C0746t<BitSet>() {
        /* renamed from: b */
        public /* synthetic */ Object mo592b(C0732a c0732a) {
            return m5794a(c0732a);
        }

        /* JADX WARNING: inconsistent code. */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        /* renamed from: a */
        public java.util.BitSet m5794a(com.p031a.p032a.p038d.C0732a r6) {
            /* JADX: method processing error */
/*
Error: java.lang.NullPointerException
	at jadx.core.dex.visitors.regions.ProcessTryCatchRegions.searchTryCatchDominators(ProcessTryCatchRegions.java:75)
	at jadx.core.dex.visitors.regions.ProcessTryCatchRegions.process(ProcessTryCatchRegions.java:45)
	at jadx.core.dex.visitors.regions.RegionMakerVisitor.postProcessRegions(RegionMakerVisitor.java:63)
	at jadx.core.dex.visitors.regions.RegionMakerVisitor.visit(RegionMakerVisitor.java:58)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:31)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:17)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:14)
	at jadx.core.ProcessClass.process(ProcessClass.java:34)
	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:282)
	at jadx.api.JavaClass.decompile(JavaClass.java:62)
	at jadx.api.JadxDecompiler.lambda$appendSourcesSave$0(JadxDecompiler.java:200)
	at jadx.api.JadxDecompiler$$Lambda$8/1201484275.run(Unknown Source)
*/
            /*
            r5 = this;
            r5 = new java.util.BitSet;
            r5.<init>();
            r6.mo593a();
            r0 = r6.mo599f();
            r1 = 0;
            r2 = r1;
        L_0x000e:
            r3 = com.p031a.p032a.p038d.C0733b.END_ARRAY;
            if (r0 == r3) goto L_0x0070;
        L_0x0012:
            r3 = com.a.a.b.a.n.AnonymousClass29.f2237a;
            r4 = r0.ordinal();
            r3 = r3[r4];
            r4 = 1;
            switch(r3) {
                case 1: goto L_0x005e;
                case 2: goto L_0x0059;
                case 3: goto L_0x0035;
                default: goto L_0x001e;
            };
        L_0x001e:
            r5 = new com.a.a.r;
            r6 = new java.lang.StringBuilder;
            r6.<init>();
            r1 = "Invalid bitset value type: ";
            r6.append(r1);
            r6.append(r0);
            r6 = r6.toString();
            r5.<init>(r6);
            throw r5;
        L_0x0035:
            r0 = r6.mo601h();
            r3 = java.lang.Integer.parseInt(r0);	 Catch:{ NumberFormatException -> 0x0042 }
            if (r3 == 0) goto L_0x0040;
        L_0x003f:
            goto L_0x0064;
        L_0x0040:
            r4 = r1;
            goto L_0x0064;
        L_0x0042:
            r5 = new com.a.a.r;
            r6 = new java.lang.StringBuilder;
            r6.<init>();
            r1 = "Error: Expecting: bitset number value (1, 0), Found: ";
            r6.append(r1);
            r6.append(r0);
            r6 = r6.toString();
            r5.<init>(r6);
            throw r5;
        L_0x0059:
            r4 = r6.mo602i();
            goto L_0x0064;
        L_0x005e:
            r0 = r6.mo606m();
            if (r0 == 0) goto L_0x0040;
        L_0x0064:
            if (r4 == 0) goto L_0x0069;
        L_0x0066:
            r5.set(r2);
        L_0x0069:
            r2 = r2 + 1;
            r0 = r6.mo599f();
            goto L_0x000e;
        L_0x0070:
            r6.mo594b();
            return r5;
            */
            throw new UnsupportedOperationException("Method not decompiled: com.a.a.b.a.n.12.a(com.a.a.d.a):java.util.BitSet");
        }

        /* renamed from: a */
        public void m5796a(C0734c c0734c, BitSet bitSet) {
            c0734c.mo617b();
            int length = bitSet.length();
            for (int i = 0; i < length; i++) {
                c0734c.mo611a((long) bitSet.get(i));
            }
            c0734c.mo619c();
        }
    }.m3121a();
    /* renamed from: d */
    public static final C0747u f2267d = C0709n.m2962a(BitSet.class, f2266c);
    /* renamed from: e */
    public static final C0746t<Boolean> f2268e = new C0746t<Boolean>() {
        /* renamed from: b */
        public /* synthetic */ Object mo592b(C0732a c0732a) {
            return m5843a(c0732a);
        }

        /* renamed from: a */
        public Boolean m5843a(C0732a c0732a) {
            if (c0732a.mo599f() == C0733b.NULL) {
                c0732a.mo603j();
                return null;
            } else if (c0732a.mo599f() == C0733b.STRING) {
                return Boolean.valueOf(Boolean.parseBoolean(c0732a.mo601h()));
            } else {
                return Boolean.valueOf(c0732a.mo602i());
            }
        }

        /* renamed from: a */
        public void m5844a(C0734c c0734c, Boolean bool) {
            c0734c.mo612a(bool);
        }
    };
    /* renamed from: f */
    public static final C0746t<Boolean> f2269f = new C0746t<Boolean>() {
        /* renamed from: b */
        public /* synthetic */ Object mo592b(C0732a c0732a) {
            return m5858a(c0732a);
        }

        /* renamed from: a */
        public Boolean m5858a(C0732a c0732a) {
            if (c0732a.mo599f() != C0733b.NULL) {
                return Boolean.valueOf(c0732a.mo601h());
            }
            c0732a.mo603j();
            return null;
        }

        /* renamed from: a */
        public void m5859a(C0734c c0734c, Boolean bool) {
            c0734c.mo618b(bool == null ? "null" : bool.toString());
        }
    };
    /* renamed from: g */
    public static final C0747u f2270g = C0709n.m2963a(Boolean.TYPE, Boolean.class, f2268e);
    /* renamed from: h */
    public static final C0746t<Number> f2271h = new C0746t<Number>() {
        /* renamed from: b */
        public /* synthetic */ Object mo592b(C0732a c0732a) {
            return m5862a(c0732a);
        }

        /* renamed from: a */
        public Number m5862a(C0732a c0732a) {
            if (c0732a.mo599f() == C0733b.NULL) {
                c0732a.mo603j();
                return null;
            }
            try {
                return Byte.valueOf((byte) c0732a.mo606m());
            } catch (Throwable e) {
                throw new C1279r(e);
            }
        }

        /* renamed from: a */
        public void m5863a(C0734c c0734c, Number number) {
            c0734c.mo613a(number);
        }
    };
    /* renamed from: i */
    public static final C0747u f2272i = C0709n.m2963a(Byte.TYPE, Byte.class, f2271h);
    /* renamed from: j */
    public static final C0746t<Number> f2273j = new C0746t<Number>() {
        /* renamed from: b */
        public /* synthetic */ Object mo592b(C0732a c0732a) {
            return m5866a(c0732a);
        }

        /* renamed from: a */
        public Number m5866a(C0732a c0732a) {
            if (c0732a.mo599f() == C0733b.NULL) {
                c0732a.mo603j();
                return null;
            }
            try {
                return Short.valueOf((short) c0732a.mo606m());
            } catch (Throwable e) {
                throw new C1279r(e);
            }
        }

        /* renamed from: a */
        public void m5867a(C0734c c0734c, Number number) {
            c0734c.mo613a(number);
        }
    };
    /* renamed from: k */
    public static final C0747u f2274k = C0709n.m2963a(Short.TYPE, Short.class, f2273j);
    /* renamed from: l */
    public static final C0746t<Number> f2275l = new C0746t<Number>() {
        /* renamed from: b */
        public /* synthetic */ Object mo592b(C0732a c0732a) {
            return m5870a(c0732a);
        }

        /* renamed from: a */
        public Number m5870a(C0732a c0732a) {
            if (c0732a.mo599f() == C0733b.NULL) {
                c0732a.mo603j();
                return null;
            }
            try {
                return Integer.valueOf(c0732a.mo606m());
            } catch (Throwable e) {
                throw new C1279r(e);
            }
        }

        /* renamed from: a */
        public void m5871a(C0734c c0734c, Number number) {
            c0734c.mo613a(number);
        }
    };
    /* renamed from: m */
    public static final C0747u f2276m = C0709n.m2963a(Integer.TYPE, Integer.class, f2275l);
    /* renamed from: n */
    public static final C0746t<AtomicInteger> f2277n = new C0746t<AtomicInteger>() {
        /* renamed from: b */
        public /* synthetic */ Object mo592b(C0732a c0732a) {
            return m5874a(c0732a);
        }

        /* renamed from: a */
        public AtomicInteger m5874a(C0732a c0732a) {
            try {
                return new AtomicInteger(c0732a.mo606m());
            } catch (Throwable e) {
                throw new C1279r(e);
            }
        }

        /* renamed from: a */
        public void m5876a(C0734c c0734c, AtomicInteger atomicInteger) {
            c0734c.mo611a((long) atomicInteger.get());
        }
    }.m3121a();
    /* renamed from: o */
    public static final C0747u f2278o = C0709n.m2962a(AtomicInteger.class, f2277n);
    /* renamed from: p */
    public static final C0746t<AtomicBoolean> f2279p = new C0746t<AtomicBoolean>() {
        /* renamed from: b */
        public /* synthetic */ Object mo592b(C0732a c0732a) {
            return m5878a(c0732a);
        }

        /* renamed from: a */
        public AtomicBoolean m5878a(C0732a c0732a) {
            return new AtomicBoolean(c0732a.mo602i());
        }

        /* renamed from: a */
        public void m5880a(C0734c c0734c, AtomicBoolean atomicBoolean) {
            c0734c.mo615a(atomicBoolean.get());
        }
    }.m3121a();
    /* renamed from: q */
    public static final C0747u f2280q = C0709n.m2962a(AtomicBoolean.class, f2279p);
    /* renamed from: r */
    public static final C0746t<AtomicIntegerArray> f2281r = new C12402().m3121a();
    /* renamed from: s */
    public static final C0747u f2282s = C0709n.m2962a(AtomicIntegerArray.class, f2281r);
    /* renamed from: t */
    public static final C0746t<Number> f2283t = new C12413();
    /* renamed from: u */
    public static final C0746t<Number> f2284u = new C12424();
    /* renamed from: v */
    public static final C0746t<Number> f2285v = new C12435();
    /* renamed from: w */
    public static final C0746t<Number> f2286w = new C12446();
    /* renamed from: x */
    public static final C0747u f2287x = C0709n.m2962a(Number.class, f2286w);
    /* renamed from: y */
    public static final C0746t<Character> f2288y = new C12457();
    /* renamed from: z */
    public static final C0747u f2289z = C0709n.m2963a(Character.TYPE, Character.class, f2288y);

    /* compiled from: TypeAdapters */
    /* renamed from: com.a.a.b.a.n$1 */
    static class C12381 extends C0746t<Class> {
        C12381() {
        }

        /* renamed from: b */
        public /* synthetic */ Object mo592b(C0732a c0732a) {
            return m5827a(c0732a);
        }

        /* renamed from: a */
        public void m5828a(C0734c c0734c, Class cls) {
            c0734c = new StringBuilder();
            c0734c.append("Attempted to serialize java.lang.Class: ");
            c0734c.append(cls.getName());
            c0734c.append(". Forgot to register a type adapter?");
            throw new UnsupportedOperationException(c0734c.toString());
        }

        /* renamed from: a */
        public Class m5827a(C0732a c0732a) {
            throw new UnsupportedOperationException("Attempted to deserialize a java.lang.Class. Forgot to register a type adapter?");
        }
    }

    /* compiled from: TypeAdapters */
    /* renamed from: com.a.a.b.a.n$2 */
    static class C12402 extends C0746t<AtomicIntegerArray> {
        C12402() {
        }

        /* renamed from: b */
        public /* synthetic */ Object mo592b(C0732a c0732a) {
            return m5854a(c0732a);
        }

        /* renamed from: a */
        public AtomicIntegerArray m5854a(C0732a c0732a) {
            List arrayList = new ArrayList();
            c0732a.mo593a();
            while (c0732a.mo598e()) {
                try {
                    arrayList.add(Integer.valueOf(c0732a.mo606m()));
                } catch (Throwable e) {
                    throw new C1279r(e);
                }
            }
            c0732a.mo594b();
            c0732a = arrayList.size();
            AtomicIntegerArray atomicIntegerArray = new AtomicIntegerArray(c0732a);
            for (int i = 0; i < c0732a; i++) {
                atomicIntegerArray.set(i, ((Integer) arrayList.get(i)).intValue());
            }
            return atomicIntegerArray;
        }

        /* renamed from: a */
        public void m5856a(C0734c c0734c, AtomicIntegerArray atomicIntegerArray) {
            c0734c.mo617b();
            int length = atomicIntegerArray.length();
            for (int i = 0; i < length; i++) {
                c0734c.mo611a((long) atomicIntegerArray.get(i));
            }
            c0734c.mo619c();
        }
    }

    /* compiled from: TypeAdapters */
    /* renamed from: com.a.a.b.a.n$3 */
    static class C12413 extends C0746t<Number> {
        C12413() {
        }

        /* renamed from: b */
        public /* synthetic */ Object mo592b(C0732a c0732a) {
            return m5882a(c0732a);
        }

        /* renamed from: a */
        public Number m5882a(C0732a c0732a) {
            if (c0732a.mo599f() == C0733b.NULL) {
                c0732a.mo603j();
                return null;
            }
            try {
                return Long.valueOf(c0732a.mo605l());
            } catch (Throwable e) {
                throw new C1279r(e);
            }
        }

        /* renamed from: a */
        public void m5883a(C0734c c0734c, Number number) {
            c0734c.mo613a(number);
        }
    }

    /* compiled from: TypeAdapters */
    /* renamed from: com.a.a.b.a.n$4 */
    static class C12424 extends C0746t<Number> {
        C12424() {
        }

        /* renamed from: b */
        public /* synthetic */ Object mo592b(C0732a c0732a) {
            return m5886a(c0732a);
        }

        /* renamed from: a */
        public Number m5886a(C0732a c0732a) {
            if (c0732a.mo599f() != C0733b.NULL) {
                return Float.valueOf((float) c0732a.mo604k());
            }
            c0732a.mo603j();
            return null;
        }

        /* renamed from: a */
        public void m5887a(C0734c c0734c, Number number) {
            c0734c.mo613a(number);
        }
    }

    /* compiled from: TypeAdapters */
    /* renamed from: com.a.a.b.a.n$5 */
    static class C12435 extends C0746t<Number> {
        C12435() {
        }

        /* renamed from: b */
        public /* synthetic */ Object mo592b(C0732a c0732a) {
            return m5890a(c0732a);
        }

        /* renamed from: a */
        public Number m5890a(C0732a c0732a) {
            if (c0732a.mo599f() != C0733b.NULL) {
                return Double.valueOf(c0732a.mo604k());
            }
            c0732a.mo603j();
            return null;
        }

        /* renamed from: a */
        public void m5891a(C0734c c0734c, Number number) {
            c0734c.mo613a(number);
        }
    }

    /* compiled from: TypeAdapters */
    /* renamed from: com.a.a.b.a.n$6 */
    static class C12446 extends C0746t<Number> {
        C12446() {
        }

        /* renamed from: b */
        public /* synthetic */ Object mo592b(C0732a c0732a) {
            return m5894a(c0732a);
        }

        /* renamed from: a */
        public Number m5894a(C0732a c0732a) {
            C0733b f = c0732a.mo599f();
            int i = AnonymousClass29.f2237a[f.ordinal()];
            if (i != 1) {
                switch (i) {
                    case 3:
                        break;
                    case 4:
                        c0732a.mo603j();
                        return null;
                    default:
                        StringBuilder stringBuilder = new StringBuilder();
                        stringBuilder.append("Expecting number, got: ");
                        stringBuilder.append(f);
                        throw new C1279r(stringBuilder.toString());
                }
            }
            return new C0717f(c0732a.mo601h());
        }

        /* renamed from: a */
        public void m5895a(C0734c c0734c, Number number) {
            c0734c.mo613a(number);
        }
    }

    /* compiled from: TypeAdapters */
    /* renamed from: com.a.a.b.a.n$7 */
    static class C12457 extends C0746t<Character> {
        C12457() {
        }

        /* renamed from: b */
        public /* synthetic */ Object mo592b(C0732a c0732a) {
            return m5898a(c0732a);
        }

        /* renamed from: a */
        public Character m5898a(C0732a c0732a) {
            if (c0732a.mo599f() == C0733b.NULL) {
                c0732a.mo603j();
                return null;
            }
            String h = c0732a.mo601h();
            if (h.length() == 1) {
                return Character.valueOf(h.charAt(null));
            }
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("Expecting character, got: ");
            stringBuilder.append(h);
            throw new C1279r(stringBuilder.toString());
        }

        /* renamed from: a */
        public void m5899a(C0734c c0734c, Character ch) {
            c0734c.mo618b(ch == null ? null : String.valueOf(ch));
        }
    }

    /* compiled from: TypeAdapters */
    /* renamed from: com.a.a.b.a.n$8 */
    static class C12468 extends C0746t<String> {
        C12468() {
        }

        /* renamed from: b */
        public /* synthetic */ Object mo592b(C0732a c0732a) {
            return m5902a(c0732a);
        }

        /* renamed from: a */
        public String m5902a(C0732a c0732a) {
            C0733b f = c0732a.mo599f();
            if (f == C0733b.NULL) {
                c0732a.mo603j();
                return null;
            } else if (f == C0733b.BOOLEAN) {
                return Boolean.toString(c0732a.mo602i());
            } else {
                return c0732a.mo601h();
            }
        }

        /* renamed from: a */
        public void m5904a(C0734c c0734c, String str) {
            c0734c.mo618b(str);
        }
    }

    /* compiled from: TypeAdapters */
    /* renamed from: com.a.a.b.a.n$9 */
    static class C12479 extends C0746t<BigDecimal> {
        C12479() {
        }

        /* renamed from: b */
        public /* synthetic */ Object mo592b(C0732a c0732a) {
            return m5906a(c0732a);
        }

        /* renamed from: a */
        public BigDecimal m5906a(C0732a c0732a) {
            if (c0732a.mo599f() == C0733b.NULL) {
                c0732a.mo603j();
                return null;
            }
            try {
                return new BigDecimal(c0732a.mo601h());
            } catch (Throwable e) {
                throw new C1279r(e);
            }
        }

        /* renamed from: a */
        public void m5908a(C0734c c0734c, BigDecimal bigDecimal) {
            c0734c.mo613a((Number) bigDecimal);
        }
    }

    /* compiled from: TypeAdapters */
    /* renamed from: com.a.a.b.a.n$a */
    private static final class C1248a<T extends Enum<T>> extends C0746t<T> {
        /* renamed from: a */
        private final Map<String, T> f4257a = new HashMap();
        /* renamed from: b */
        private final Map<T, String> f4258b = new HashMap();

        /* renamed from: b */
        public /* synthetic */ Object mo592b(C0732a c0732a) {
            return m5910a(c0732a);
        }

        public C1248a(Class<T> cls) {
            try {
                for (Enum enumR : (Enum[]) cls.getEnumConstants()) {
                    Object name = enumR.name();
                    C0699c c0699c = (C0699c) cls.getField(name).getAnnotation(C0699c.class);
                    if (c0699c != null) {
                        name = c0699c.m2949a();
                        for (Object put : c0699c.m2950b()) {
                            this.f4257a.put(put, enumR);
                        }
                    }
                    this.f4257a.put(name, enumR);
                    this.f4258b.put(enumR, name);
                }
            } catch (NoSuchFieldException e) {
                throw new AssertionError(e);
            }
        }

        /* renamed from: a */
        public T m5910a(C0732a c0732a) {
            if (c0732a.mo599f() != C0733b.NULL) {
                return (Enum) this.f4257a.get(c0732a.mo601h());
            }
            c0732a.mo603j();
            return null;
        }

        /* renamed from: a */
        public void m5911a(C0734c c0734c, T t) {
            c0734c.mo618b(t == null ? null : (String) this.f4258b.get(t));
        }
    }

    /* renamed from: a */
    public static <TT> C0747u m2962a(final Class<TT> cls, final C0746t<TT> c0746t) {
        return new C0747u() {
            /* renamed from: a */
            public <T> C0746t<T> mo590a(C0737e c0737e, C0731a<T> c0731a) {
                return c0731a.getRawType() == cls ? c0746t : null;
            }

            public String toString() {
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append("Factory[type=");
                stringBuilder.append(cls.getName());
                stringBuilder.append(",adapter=");
                stringBuilder.append(c0746t);
                stringBuilder.append("]");
                return stringBuilder.toString();
            }
        };
    }

    /* renamed from: a */
    public static <TT> C0747u m2963a(final Class<TT> cls, final Class<TT> cls2, final C0746t<? super TT> c0746t) {
        return new C0747u() {
            /* renamed from: a */
            public <T> C0746t<T> mo590a(C0737e c0737e, C0731a<T> c0731a) {
                c0737e = c0731a.getRawType();
                if (c0737e != cls) {
                    if (c0737e != cls2) {
                        return null;
                    }
                }
                return c0746t;
            }

            public String toString() {
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append("Factory[type=");
                stringBuilder.append(cls2.getName());
                stringBuilder.append("+");
                stringBuilder.append(cls.getName());
                stringBuilder.append(",adapter=");
                stringBuilder.append(c0746t);
                stringBuilder.append("]");
                return stringBuilder.toString();
            }
        };
    }

    /* renamed from: b */
    public static <TT> C0747u m2965b(final Class<TT> cls, final Class<? extends TT> cls2, final C0746t<? super TT> c0746t) {
        return new C0747u() {
            /* renamed from: a */
            public <T> C0746t<T> mo590a(C0737e c0737e, C0731a<T> c0731a) {
                c0737e = c0731a.getRawType();
                if (c0737e != cls) {
                    if (c0737e != cls2) {
                        return null;
                    }
                }
                return c0746t;
            }

            public String toString() {
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append("Factory[type=");
                stringBuilder.append(cls.getName());
                stringBuilder.append("+");
                stringBuilder.append(cls2.getName());
                stringBuilder.append(",adapter=");
                stringBuilder.append(c0746t);
                stringBuilder.append("]");
                return stringBuilder.toString();
            }
        };
    }

    /* renamed from: b */
    public static <T1> C0747u m2964b(final Class<T1> cls, final C0746t<T1> c0746t) {
        return new C0747u() {
            /* renamed from: a */
            public <T2> C0746t<T2> mo590a(C0737e c0737e, C0731a<T2> c0731a) {
                c0737e = c0731a.getRawType();
                if (cls.isAssignableFrom(c0737e) == null) {
                    return null;
                }
                return new C0746t<T1>(this) {
                    /* renamed from: b */
                    final /* synthetic */ AnonymousClass28 f4254b;

                    /* renamed from: a */
                    public void mo591a(C0734c c0734c, T1 t1) {
                        c0746t.mo591a(c0734c, t1);
                    }

                    /* renamed from: b */
                    public T1 mo592b(C0732a c0732a) {
                        c0732a = c0746t.mo592b(c0732a);
                        if (c0732a == null || c0737e.isInstance(c0732a)) {
                            return c0732a;
                        }
                        StringBuilder stringBuilder = new StringBuilder();
                        stringBuilder.append("Expected a ");
                        stringBuilder.append(c0737e.getName());
                        stringBuilder.append(" but was ");
                        stringBuilder.append(c0732a.getClass().getName());
                        throw new C1279r(stringBuilder.toString());
                    }
                };
            }

            public String toString() {
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append("Factory[typeHierarchy=");
                stringBuilder.append(cls.getName());
                stringBuilder.append(",adapter=");
                stringBuilder.append(c0746t);
                stringBuilder.append("]");
                return stringBuilder.toString();
            }
        };
    }
}
