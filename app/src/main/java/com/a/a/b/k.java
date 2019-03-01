package com.a.a.b;

import java.io.ObjectStreamClass;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

/* compiled from: UnsafeAllocator */
public abstract class k {
    public abstract <T> T a(Class<T> cls);

    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Missing block: B:9:?, code:
            r0 = java.io.ObjectInputStream.class.getDeclaredMethod("newInstance", new java.lang.Class[]{java.lang.Class.class, java.lang.Class.class});
            r0.setAccessible(true);
     */
    /* JADX WARNING: Missing block: B:10:0x007f, code:
            return new com.a.a.b.k.AnonymousClass3();
     */
    /* JADX WARNING: Missing block: B:12:0x0085, code:
            return new com.a.a.b.k.AnonymousClass4();
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static k a() {
        try {
            Class cls = Class.forName("sun.misc.Unsafe");
            Field declaredField = cls.getDeclaredField("theUnsafe");
            declaredField.setAccessible(true);
            final Object obj = declaredField.get(null);
            final Method method = cls.getMethod("allocateInstance", new Class[]{Class.class});
            return new k() {
                public <T> T a(Class<T> cls) {
                    k.b(cls);
                    return method.invoke(obj, new Object[]{cls});
                }
            };
        } catch (Exception unused) {
            Method declaredMethod = ObjectStreamClass.class.getDeclaredMethod("getConstructorId", new Class[]{Class.class});
            declaredMethod.setAccessible(true);
            final int intValue = ((Integer) declaredMethod.invoke(null, new Object[]{Object.class})).intValue();
            declaredMethod = ObjectStreamClass.class.getDeclaredMethod("newInstance", new Class[]{Class.class, Integer.TYPE});
            declaredMethod.setAccessible(true);
            return new k() {
                public <T> T a(Class<T> cls) {
                    k.b(cls);
                    return declaredMethod.invoke(null, new Object[]{cls, Integer.valueOf(intValue)});
                }
            };
        }
    }

    static void b(Class<?> cls) {
        int modifiers = cls.getModifiers();
        StringBuilder stringBuilder;
        if (Modifier.isInterface(modifiers)) {
            stringBuilder = new StringBuilder();
            stringBuilder.append("Interface can't be instantiated! Interface name: ");
            stringBuilder.append(cls.getName());
            throw new UnsupportedOperationException(stringBuilder.toString());
        } else if (Modifier.isAbstract(modifiers)) {
            stringBuilder = new StringBuilder();
            stringBuilder.append("Abstract class can't be instantiated! Class name: ");
            stringBuilder.append(cls.getName());
            throw new UnsupportedOperationException(stringBuilder.toString());
        }
    }
}
