package com.p031a.p032a.p037c;

import com.p031a.p032a.p034b.C0710a;
import com.p031a.p032a.p034b.C0714b;
import java.lang.reflect.GenericArrayType;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;
import java.util.HashMap;
import java.util.Map;

/* compiled from: TypeToken */
/* renamed from: com.a.a.c.a */
public class C0731a<T> {
    final int hashCode;
    final Class<? super T> rawType;
    final Type type;

    protected C0731a() {
        this.type = C0731a.getSuperclassTypeParameter(getClass());
        this.rawType = C0714b.m2985e(this.type);
        this.hashCode = this.type.hashCode();
    }

    C0731a(Type type) {
        this.type = C0714b.m2984d((Type) C0710a.m2966a((Object) type));
        this.rawType = C0714b.m2985e(this.type);
        this.hashCode = this.type.hashCode();
    }

    static Type getSuperclassTypeParameter(Class<?> cls) {
        cls = cls.getGenericSuperclass();
        if (!(cls instanceof Class)) {
            return C0714b.m2984d(((ParameterizedType) cls).getActualTypeArguments()[0]);
        }
        throw new RuntimeException("Missing type parameter.");
    }

    public final Class<? super T> getRawType() {
        return this.rawType;
    }

    public final Type getType() {
        return this.type;
    }

    @Deprecated
    public boolean isAssignableFrom(Class<?> cls) {
        return isAssignableFrom((Type) cls);
    }

    @Deprecated
    public boolean isAssignableFrom(Type type) {
        boolean z = false;
        if (type == null) {
            return false;
        }
        if (this.type.equals(type)) {
            return true;
        }
        if (this.type instanceof Class) {
            return this.rawType.isAssignableFrom(C0714b.m2985e(type));
        }
        if (this.type instanceof ParameterizedType) {
            return C0731a.isAssignableFrom(type, (ParameterizedType) this.type, new HashMap());
        }
        if (this.type instanceof GenericArrayType) {
            if (this.rawType.isAssignableFrom(C0714b.m2985e(type)) && C0731a.isAssignableFrom(type, (GenericArrayType) this.type)) {
                z = true;
            }
            return z;
        }
        throw C0731a.buildUnexpectedTypeError(this.type, new Class[]{Class.class, ParameterizedType.class, GenericArrayType.class});
    }

    @Deprecated
    public boolean isAssignableFrom(C0731a<?> c0731a) {
        return isAssignableFrom(c0731a.getType());
    }

    private static boolean isAssignableFrom(Type type, GenericArrayType genericArrayType) {
        genericArrayType = genericArrayType.getGenericComponentType();
        if (!(genericArrayType instanceof ParameterizedType)) {
            return true;
        }
        if (type instanceof GenericArrayType) {
            type = ((GenericArrayType) type).getGenericComponentType();
        } else if (type instanceof Class) {
            type = (Class) type;
            while (type.isArray()) {
                type = type.getComponentType();
            }
        }
        return C0731a.isAssignableFrom(type, (ParameterizedType) genericArrayType, new HashMap());
    }

    private static boolean isAssignableFrom(Type type, ParameterizedType parameterizedType, Map<String, Type> map) {
        int i = 0;
        if (type == null) {
            return false;
        }
        if (parameterizedType.equals(type)) {
            return true;
        }
        Class e = C0714b.m2985e(type);
        ParameterizedType parameterizedType2 = null;
        if (type instanceof ParameterizedType) {
            parameterizedType2 = (ParameterizedType) type;
        }
        if (parameterizedType2 != null) {
            type = parameterizedType2.getActualTypeArguments();
            TypeVariable[] typeParameters = e.getTypeParameters();
            for (int i2 = 0; i2 < type.length; i2++) {
                Object obj = type[i2];
                TypeVariable typeVariable = typeParameters[i2];
                while (obj instanceof TypeVariable) {
                    Type type2 = (Type) map.get(((TypeVariable) obj).getName());
                }
                map.put(typeVariable.getName(), obj);
            }
            if (C0731a.typeEquals(parameterizedType2, parameterizedType, map) != null) {
                return true;
            }
        }
        type = e.getGenericInterfaces();
        int length = type.length;
        while (i < length) {
            if (C0731a.isAssignableFrom(type[i], parameterizedType, new HashMap(map))) {
                return true;
            }
            i++;
        }
        return C0731a.isAssignableFrom(e.getGenericSuperclass(), parameterizedType, new HashMap(map));
    }

    private static boolean typeEquals(ParameterizedType parameterizedType, ParameterizedType parameterizedType2, Map<String, Type> map) {
        if (!parameterizedType.getRawType().equals(parameterizedType2.getRawType())) {
            return false;
        }
        parameterizedType = parameterizedType.getActualTypeArguments();
        parameterizedType2 = parameterizedType2.getActualTypeArguments();
        for (int i = 0; i < parameterizedType.length; i++) {
            if (!C0731a.matches(parameterizedType[i], parameterizedType2[i], map)) {
                return false;
            }
        }
        return true;
    }

    private static AssertionError buildUnexpectedTypeError(Type type, Class<?>... clsArr) {
        StringBuilder stringBuilder = new StringBuilder("Unexpected type. Expected one of: ");
        for (Class name : clsArr) {
            stringBuilder.append(name.getName());
            stringBuilder.append(", ");
        }
        stringBuilder.append("but got: ");
        stringBuilder.append(type.getClass().getName());
        stringBuilder.append(", for type token: ");
        stringBuilder.append(type.toString());
        stringBuilder.append('.');
        return new AssertionError(stringBuilder.toString());
    }

    private static boolean matches(Type type, Type type2, Map<String, Type> map) {
        if (!type2.equals(type)) {
            if (!(type instanceof TypeVariable) || type2.equals(map.get(((TypeVariable) type).getName())) == null) {
                return null;
            }
        }
        return true;
    }

    public final int hashCode() {
        return this.hashCode;
    }

    public final boolean equals(Object obj) {
        return (obj instanceof C0731a) && C0714b.m2979a(this.type, ((C0731a) obj).type);
    }

    public final String toString() {
        return C0714b.m2986f(this.type);
    }

    public static C0731a<?> get(Type type) {
        return new C0731a(type);
    }

    public static <T> C0731a<T> get(Class<T> cls) {
        return new C0731a(cls);
    }

    public static C0731a<?> getParameterized(Type type, Type... typeArr) {
        return new C0731a(C0714b.m2972a(null, type, typeArr));
    }

    public static C0731a<?> getArray(Type type) {
        return new C0731a(C0714b.m2971a(type));
    }
}
