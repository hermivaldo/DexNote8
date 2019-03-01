package com.p031a.p032a.p034b;

import java.lang.reflect.Type;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/* compiled from: Primitives */
/* renamed from: com.a.a.b.i */
public final class C0725i {
    /* renamed from: a */
    private static final Map<Class<?>, Class<?>> f2323a;
    /* renamed from: b */
    private static final Map<Class<?>, Class<?>> f2324b;

    static {
        Map hashMap = new HashMap(16);
        Map hashMap2 = new HashMap(16);
        C0725i.m3009a(hashMap, hashMap2, Boolean.TYPE, Boolean.class);
        C0725i.m3009a(hashMap, hashMap2, Byte.TYPE, Byte.class);
        C0725i.m3009a(hashMap, hashMap2, Character.TYPE, Character.class);
        C0725i.m3009a(hashMap, hashMap2, Double.TYPE, Double.class);
        C0725i.m3009a(hashMap, hashMap2, Float.TYPE, Float.class);
        C0725i.m3009a(hashMap, hashMap2, Integer.TYPE, Integer.class);
        C0725i.m3009a(hashMap, hashMap2, Long.TYPE, Long.class);
        C0725i.m3009a(hashMap, hashMap2, Short.TYPE, Short.class);
        C0725i.m3009a(hashMap, hashMap2, Void.TYPE, Void.class);
        f2323a = Collections.unmodifiableMap(hashMap);
        f2324b = Collections.unmodifiableMap(hashMap2);
    }

    /* renamed from: a */
    private static void m3009a(Map<Class<?>, Class<?>> map, Map<Class<?>, Class<?>> map2, Class<?> cls, Class<?> cls2) {
        map.put(cls, cls2);
        map2.put(cls2, cls);
    }

    /* renamed from: a */
    public static boolean m3010a(Type type) {
        return f2323a.containsKey(type);
    }
}
