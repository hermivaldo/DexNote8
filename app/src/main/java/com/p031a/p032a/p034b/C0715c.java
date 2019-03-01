package com.p031a.p032a.p034b;

import com.p031a.p032a.C0738f;
import com.p031a.p032a.C1275k;
import com.p031a.p032a.p034b.C0715c;
import com.p031a.p032a.p037c.C0731a;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collection;
import java.util.EnumSet;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Queue;
import java.util.Set;
import java.util.SortedMap;
import java.util.SortedSet;
import java.util.TreeMap;
import java.util.TreeSet;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.ConcurrentNavigableMap;
import java.util.concurrent.ConcurrentSkipListMap;

/* compiled from: ConstructorConstructor */
/* renamed from: com.a.a.b.c */
public final class C0715c {
    /* renamed from: a */
    private final Map<Type, C0738f<?>> f2297a;

    /* compiled from: ConstructorConstructor */
    /* renamed from: com.a.a.b.c$2 */
    class C12502 implements C0724h<T> {
        /* renamed from: a */
        final /* synthetic */ C0715c f4268a;

        C12502(C0715c c0715c) {
            this.f4268a = c0715c;
        }

        /* renamed from: a */
        public T mo628a() {
            return new ConcurrentHashMap();
        }
    }

    /* compiled from: ConstructorConstructor */
    /* renamed from: com.a.a.b.c$3 */
    class C12513 implements C0724h<T> {
        /* renamed from: a */
        final /* synthetic */ C0715c f4269a;

        C12513(C0715c c0715c) {
            this.f4269a = c0715c;
        }

        /* renamed from: a */
        public T mo628a() {
            return new TreeMap();
        }
    }

    /* compiled from: ConstructorConstructor */
    /* renamed from: com.a.a.b.c$4 */
    class C12524 implements C0724h<T> {
        /* renamed from: a */
        final /* synthetic */ C0715c f4270a;

        C12524(C0715c c0715c) {
            this.f4270a = c0715c;
        }

        /* renamed from: a */
        public T mo628a() {
            return new LinkedHashMap();
        }
    }

    /* compiled from: ConstructorConstructor */
    /* renamed from: com.a.a.b.c$5 */
    class C12535 implements C0724h<T> {
        /* renamed from: a */
        final /* synthetic */ C0715c f4271a;

        C12535(C0715c c0715c) {
            this.f4271a = c0715c;
        }

        /* renamed from: a */
        public T mo628a() {
            return new C0723g();
        }
    }

    /* compiled from: ConstructorConstructor */
    /* renamed from: com.a.a.b.c$9 */
    class C12579 implements C0724h<T> {
        /* renamed from: a */
        final /* synthetic */ C0715c f4281a;

        C12579(C0715c c0715c) {
            this.f4281a = c0715c;
        }

        /* renamed from: a */
        public T mo628a() {
            return new TreeSet();
        }
    }

    public C0715c(Map<Type, C0738f<?>> map) {
        this.f2297a = map;
    }

    /* renamed from: a */
    public <T> C0724h<T> m2992a(C0731a<T> c0731a) {
        final Type type = c0731a.getType();
        Class rawType = c0731a.getRawType();
        final C0738f c0738f = (C0738f) this.f2297a.get(type);
        if (c0738f != null) {
            return new C0724h<T>(this) {
                /* renamed from: c */
                final /* synthetic */ C0715c f4267c;

                /* renamed from: a */
                public T mo628a() {
                    return c0738f.m3103a(type);
                }
            };
        }
        c0738f = (C0738f) this.f2297a.get(rawType);
        if (c0738f != null) {
            return new C0724h<T>(this) {
                /* renamed from: c */
                final /* synthetic */ C0715c f4278c;

                /* renamed from: a */
                public T mo628a() {
                    return c0738f.m3103a(type);
                }
            };
        }
        C0724h<T> a = m2989a(rawType);
        if (a != null) {
            return a;
        }
        a = m2990a(type, rawType);
        if (a != null) {
            return a;
        }
        return m2991b(type, rawType);
    }

    /* renamed from: a */
    private <T> com.p031a.p032a.p034b.C0724h<T> m2989a(java.lang.Class<? super T> r2) {
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
        r1 = this;
        r0 = 0;
        r0 = new java.lang.Class[r0];	 Catch:{ NoSuchMethodException -> 0x0017 }
        r2 = r2.getDeclaredConstructor(r0);	 Catch:{ NoSuchMethodException -> 0x0017 }
        r0 = r2.isAccessible();	 Catch:{ NoSuchMethodException -> 0x0017 }
        if (r0 != 0) goto L_0x0011;	 Catch:{ NoSuchMethodException -> 0x0017 }
    L_0x000d:
        r0 = 1;	 Catch:{ NoSuchMethodException -> 0x0017 }
        r2.setAccessible(r0);	 Catch:{ NoSuchMethodException -> 0x0017 }
    L_0x0011:
        r0 = new com.a.a.b.c$8;	 Catch:{ NoSuchMethodException -> 0x0017 }
        r0.<init>(r1, r2);	 Catch:{ NoSuchMethodException -> 0x0017 }
        return r0;
    L_0x0017:
        r1 = 0;
        return r1;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.a.a.b.c.a(java.lang.Class):com.a.a.b.h<T>");
    }

    /* renamed from: a */
    private <T> C0724h<T> m2990a(final Type type, Class<? super T> cls) {
        if (Collection.class.isAssignableFrom(cls)) {
            if (SortedSet.class.isAssignableFrom(cls)) {
                return new C12579(this);
            }
            if (EnumSet.class.isAssignableFrom(cls)) {
                return new C0724h<T>(this) {
                    /* renamed from: b */
                    final /* synthetic */ C0715c f4260b;

                    /* renamed from: a */
                    public T mo628a() {
                        if (type instanceof ParameterizedType) {
                            Type type = ((ParameterizedType) type).getActualTypeArguments()[0];
                            if (type instanceof Class) {
                                return EnumSet.noneOf((Class) type);
                            }
                            StringBuilder stringBuilder = new StringBuilder();
                            stringBuilder.append("Invalid EnumSet type: ");
                            stringBuilder.append(type.toString());
                            throw new C1275k(stringBuilder.toString());
                        }
                        stringBuilder = new StringBuilder();
                        stringBuilder.append("Invalid EnumSet type: ");
                        stringBuilder.append(type.toString());
                        throw new C1275k(stringBuilder.toString());
                    }
                };
            }
            if (Set.class.isAssignableFrom(cls) != null) {
                return new C0724h<T>(this) {
                    /* renamed from: a */
                    final /* synthetic */ C0715c f4261a;

                    {
                        this.f4261a = r1;
                    }

                    /* renamed from: a */
                    public T mo628a() {
                        return new LinkedHashSet();
                    }
                };
            }
            if (Queue.class.isAssignableFrom(cls) != null) {
                return new C0724h<T>(this) {
                    /* renamed from: a */
                    final /* synthetic */ C0715c f4262a;

                    {
                        this.f4262a = r1;
                    }

                    /* renamed from: a */
                    public T mo628a() {
                        return new ArrayDeque();
                    }
                };
            }
            return new C0724h<T>(this) {
                /* renamed from: a */
                final /* synthetic */ C0715c f4263a;

                {
                    this.f4263a = r1;
                }

                /* renamed from: a */
                public T mo628a() {
                    return new ArrayList();
                }
            };
        } else if (!Map.class.isAssignableFrom(cls)) {
            return null;
        } else {
            if (ConcurrentNavigableMap.class.isAssignableFrom(cls)) {
                return new C0724h<T>(this) {
                    /* renamed from: a */
                    final /* synthetic */ C0715c f4264a;

                    {
                        this.f4264a = r1;
                    }

                    /* renamed from: a */
                    public T mo628a() {
                        return new ConcurrentSkipListMap();
                    }
                };
            }
            if (ConcurrentMap.class.isAssignableFrom(cls)) {
                return new C12502(this);
            }
            if (SortedMap.class.isAssignableFrom(cls) != null) {
                return new C12513(this);
            }
            if ((type instanceof ParameterizedType) == null || String.class.isAssignableFrom(C0731a.get(((ParameterizedType) type).getActualTypeArguments()[0]).getRawType()) != null) {
                return new C12535(this);
            }
            return new C12524(this);
        }
    }

    /* renamed from: b */
    private <T> C0724h<T> m2991b(final Type type, final Class<? super T> cls) {
        return new C0724h<T>(this) {
            /* renamed from: c */
            final /* synthetic */ C0715c f4274c;
            /* renamed from: d */
            private final C0729k f4275d = C0729k.m3014a();

            /* renamed from: a */
            public T mo628a() {
                try {
                    return this.f4275d.mo629a(cls);
                } catch (Throwable e) {
                    StringBuilder stringBuilder = new StringBuilder();
                    stringBuilder.append("Unable to invoke no-args constructor for ");
                    stringBuilder.append(type);
                    stringBuilder.append(". Registering an InstanceCreator with Gson for this type may fix this problem.");
                    throw new RuntimeException(stringBuilder.toString(), e);
                }
            }
        };
    }

    public String toString() {
        return this.f2297a.toString();
    }
}
