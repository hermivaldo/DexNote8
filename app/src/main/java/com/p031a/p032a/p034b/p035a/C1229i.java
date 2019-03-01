package com.p031a.p032a.p034b.p035a;

import com.p031a.p032a.C0736d;
import com.p031a.p032a.C0737e;
import com.p031a.p032a.C0746t;
import com.p031a.p032a.C0747u;
import com.p031a.p032a.C1279r;
import com.p031a.p032a.p033a.C0698b;
import com.p031a.p032a.p033a.C0699c;
import com.p031a.p032a.p034b.C0714b;
import com.p031a.p032a.p034b.C0715c;
import com.p031a.p032a.p034b.C0724h;
import com.p031a.p032a.p034b.C0725i;
import com.p031a.p032a.p034b.C1259d;
import com.p031a.p032a.p037c.C0731a;
import com.p031a.p032a.p038d.C0732a;
import com.p031a.p032a.p038d.C0733b;
import com.p031a.p032a.p038d.C0734c;
import java.lang.reflect.Field;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/* compiled from: ReflectiveTypeAdapterFactory */
/* renamed from: com.a.a.b.a.i */
public final class C1229i implements C0747u {
    /* renamed from: a */
    private final C0715c f4224a;
    /* renamed from: b */
    private final C0736d f4225b;
    /* renamed from: c */
    private final C1259d f4226c;
    /* renamed from: d */
    private final C1220d f4227d;

    /* compiled from: ReflectiveTypeAdapterFactory */
    /* renamed from: com.a.a.b.a.i$b */
    static abstract class C0707b {
        /* renamed from: h */
        final String f2234h;
        /* renamed from: i */
        final boolean f2235i;
        /* renamed from: j */
        final boolean f2236j;

        /* renamed from: a */
        abstract void mo625a(C0732a c0732a, Object obj);

        /* renamed from: a */
        abstract void mo626a(C0734c c0734c, Object obj);

        /* renamed from: a */
        abstract boolean mo627a(Object obj);

        protected C0707b(String str, boolean z, boolean z2) {
            this.f2234h = str;
            this.f2235i = z;
            this.f2236j = z2;
        }
    }

    /* compiled from: ReflectiveTypeAdapterFactory */
    /* renamed from: com.a.a.b.a.i$a */
    public static final class C1228a<T> extends C0746t<T> {
        /* renamed from: a */
        private final C0724h<T> f4222a;
        /* renamed from: b */
        private final Map<String, C0707b> f4223b;

        C1228a(C0724h<T> c0724h, Map<String, C0707b> map) {
            this.f4222a = c0724h;
            this.f4223b = map;
        }

        /* renamed from: b */
        public T mo592b(C0732a c0732a) {
            if (c0732a.mo599f() == C0733b.NULL) {
                c0732a.mo603j();
                return null;
            }
            Object a = this.f4222a.mo628a();
            try {
                c0732a.mo595c();
                while (c0732a.mo598e()) {
                    C0707b c0707b = (C0707b) this.f4223b.get(c0732a.mo600g());
                    if (c0707b != null) {
                        if (c0707b.f2236j) {
                            c0707b.mo625a(c0732a, a);
                        }
                    }
                    c0732a.mo607n();
                }
                c0732a.mo597d();
                return a;
            } catch (Throwable e) {
                throw new C1279r(e);
            } catch (IllegalAccessException e2) {
                throw new AssertionError(e2);
            }
        }

        /* renamed from: a */
        public void mo591a(C0734c c0734c, T t) {
            if (t == null) {
                c0734c.mo623f();
                return;
            }
            c0734c.mo621d();
            try {
                for (C0707b c0707b : this.f4223b.values()) {
                    if (c0707b.mo627a(t)) {
                        c0734c.mo614a(c0707b.f2234h);
                        c0707b.mo626a(c0734c, (Object) t);
                    }
                }
                c0734c.mo622e();
            } catch (IllegalAccessException e) {
                throw new AssertionError(e);
            }
        }
    }

    public C1229i(C0715c c0715c, C0736d c0736d, C1259d c1259d, C1220d c1220d) {
        this.f4224a = c0715c;
        this.f4225b = c0736d;
        this.f4226c = c1259d;
        this.f4227d = c1220d;
    }

    /* renamed from: a */
    public boolean m5769a(Field field, boolean z) {
        return C1229i.m5767a(field, z, this.f4226c);
    }

    /* renamed from: a */
    static boolean m5767a(Field field, boolean z, C1259d c1259d) {
        return (c1259d.m5939a(field.getType(), z) || c1259d.m5940a(field, z) != null) ? null : true;
    }

    /* renamed from: a */
    private List<String> m5765a(Field field) {
        C0699c c0699c = (C0699c) field.getAnnotation(C0699c.class);
        if (c0699c == null) {
            return Collections.singletonList(this.f4225b.mo1272a(field));
        }
        String a = c0699c.m2949a();
        field = c0699c.m2950b();
        if (field.length == 0) {
            return Collections.singletonList(a);
        }
        List<String> arrayList = new ArrayList(field.length + 1);
        arrayList.add(a);
        for (Object add : field) {
            arrayList.add(add);
        }
        return arrayList;
    }

    /* renamed from: a */
    public <T> C0746t<T> mo590a(C0737e c0737e, C0731a<T> c0731a) {
        Class rawType = c0731a.getRawType();
        if (Object.class.isAssignableFrom(rawType)) {
            return new C1228a(this.f4224a.m2992a((C0731a) c0731a), m5766a(c0737e, (C0731a) c0731a, rawType));
        }
        return null;
    }

    /* renamed from: a */
    private C0707b m5764a(C0737e c0737e, Field field, String str, C0731a<?> c0731a, boolean z, boolean z2) {
        C1229i c1229i = this;
        final C0737e c0737e2 = c0737e;
        final C0731a c0731a2 = c0731a;
        final boolean a = C0725i.m3010a(c0731a.getRawType());
        final Field field2 = field;
        C0698b c0698b = (C0698b) field2.getAnnotation(C0698b.class);
        C0746t a2 = c0698b != null ? c1229i.f4227d.m5712a(c1229i.f4224a, c0737e2, c0731a2, c0698b) : null;
        final boolean z3 = a2 != null;
        if (a2 == null) {
            a2 = c0737e2.m3090a(c0731a2);
        }
        final C0746t c0746t = a2;
        return new C0707b(c1229i, str, z, z2) {
            /* renamed from: g */
            final /* synthetic */ C1229i f4221g;

            /* renamed from: a */
            void mo626a(C0734c c0734c, Object obj) {
                C0746t c0746t;
                obj = field2.get(obj);
                if (z3) {
                    c0746t = c0746t;
                } else {
                    c0746t = new C1236m(c0737e2, c0746t, c0731a2.getType());
                }
                c0746t.mo591a(c0734c, obj);
            }

            /* renamed from: a */
            void mo625a(C0732a c0732a, Object obj) {
                c0732a = c0746t.mo592b(c0732a);
                if (c0732a != null || !a) {
                    field2.set(obj, c0732a);
                }
            }

            /* renamed from: a */
            public boolean mo627a(Object obj) {
                boolean z = false;
                if (!this.i) {
                    return false;
                }
                if (field2.get(obj) != obj) {
                    z = true;
                }
                return z;
            }
        };
    }

    /* renamed from: a */
    private Map<String, C0707b> m5766a(C0737e c0737e, C0731a<?> c0731a, Class<?> cls) {
        C1229i c1229i = this;
        Map<String, C0707b> linkedHashMap = new LinkedHashMap();
        if (cls.isInterface()) {
            return linkedHashMap;
        }
        Type type = c0731a.getType();
        C0731a c0731a2 = c0731a;
        Class cls2 = cls;
        while (cls2 != Object.class) {
            Field[] declaredFields = cls2.getDeclaredFields();
            int length = declaredFields.length;
            boolean z = false;
            int i = 0;
            while (i < length) {
                Field field = declaredFields[i];
                boolean a = c1229i.m5769a(field, true);
                boolean a2 = c1229i.m5769a(field, z);
                if (a || a2) {
                    C0707b c0707b;
                    field.setAccessible(true);
                    Type a3 = C0714b.m2975a(c0731a2.getType(), cls2, field.getGenericType());
                    List a4 = c1229i.m5765a(field);
                    int size = a4.size();
                    int i2 = z;
                    boolean z2 = a;
                    C0707b c0707b2 = null;
                    boolean z3 = z2;
                    while (i2 < size) {
                        String str = (String) a4.get(i2);
                        boolean z4 = i2 != 0 ? false : z3;
                        C1229i c1229i2 = c1229i;
                        c0707b = c0707b2;
                        int i3 = i2;
                        int i4 = size;
                        List list = a4;
                        Type type2 = a3;
                        Field field2 = field;
                        c0707b2 = c0707b == null ? (C0707b) linkedHashMap.put(str, c1229i2.m5764a(c0737e, field, str, C0731a.get(a3), z4, a2)) : c0707b;
                        i2 = i3 + 1;
                        z3 = z4;
                        a3 = type2;
                        size = i4;
                        a4 = list;
                        field = field2;
                        c1229i = this;
                    }
                    c0707b = c0707b2;
                    if (c0707b != null) {
                        StringBuilder stringBuilder = new StringBuilder();
                        stringBuilder.append(type);
                        stringBuilder.append(" declares multiple JSON fields named ");
                        stringBuilder.append(c0707b.f2234h);
                        throw new IllegalArgumentException(stringBuilder.toString());
                    }
                }
                i++;
                c1229i = this;
                z = false;
            }
            c0731a2 = C0731a.get(C0714b.m2975a(c0731a2.getType(), cls2, cls2.getGenericSuperclass()));
            cls2 = c0731a2.getRawType();
            c1229i = this;
        }
        return linkedHashMap;
    }
}
