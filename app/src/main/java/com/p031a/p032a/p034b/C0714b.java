package com.p031a.p032a.p034b;

import java.io.Serializable;
import java.lang.reflect.Array;
import java.lang.reflect.GenericArrayType;
import java.lang.reflect.Modifier;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;
import java.lang.reflect.WildcardType;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Properties;

/* compiled from: $Gson$Types */
/* renamed from: com.a.a.b.b */
public final class C0714b {
    /* renamed from: a */
    static final Type[] f2296a = new Type[0];

    /* compiled from: $Gson$Types */
    /* renamed from: com.a.a.b.b$a */
    private static final class C0711a implements Serializable, GenericArrayType {
        /* renamed from: a */
        private final Type f2290a;

        public C0711a(Type type) {
            this.f2290a = C0714b.m2984d(type);
        }

        public Type getGenericComponentType() {
            return this.f2290a;
        }

        public boolean equals(Object obj) {
            return (obj instanceof GenericArrayType) && C0714b.m2979a((Type) this, (GenericArrayType) obj);
        }

        public int hashCode() {
            return this.f2290a.hashCode();
        }

        public String toString() {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(C0714b.m2986f(this.f2290a));
            stringBuilder.append("[]");
            return stringBuilder.toString();
        }
    }

    /* compiled from: $Gson$Types */
    /* renamed from: com.a.a.b.b$b */
    private static final class C0712b implements Serializable, ParameterizedType {
        /* renamed from: a */
        private final Type f2291a;
        /* renamed from: b */
        private final Type f2292b;
        /* renamed from: c */
        private final Type[] f2293c;

        public C0712b(Type type, Type type2, Type... typeArr) {
            int i = 0;
            if (type2 instanceof Class) {
                int i2;
                Class cls = (Class) type2;
                boolean z = true;
                if (!Modifier.isStatic(cls.getModifiers())) {
                    if (cls.getEnclosingClass() != null) {
                        i2 = 0;
                        if (type == null) {
                            if (i2 != 0) {
                                z = false;
                            }
                        }
                        C0710a.m2967a(z);
                    }
                }
                i2 = true;
                if (type == null) {
                    if (i2 != 0) {
                        z = false;
                    }
                }
                C0710a.m2967a(z);
            }
            if (type == null) {
                type = null;
            } else {
                type = C0714b.m2984d(type);
            }
            this.f2291a = type;
            this.f2292b = C0714b.m2984d(type2);
            this.f2293c = (Type[]) typeArr.clone();
            type = this.f2293c.length;
            while (i < type) {
                C0710a.m2966a(this.f2293c[i]);
                C0714b.m2988h(this.f2293c[i]);
                this.f2293c[i] = C0714b.m2984d(this.f2293c[i]);
                i++;
            }
        }

        public Type[] getActualTypeArguments() {
            return (Type[]) this.f2293c.clone();
        }

        public Type getRawType() {
            return this.f2292b;
        }

        public Type getOwnerType() {
            return this.f2291a;
        }

        public boolean equals(Object obj) {
            return (obj instanceof ParameterizedType) && C0714b.m2979a((Type) this, (ParameterizedType) obj);
        }

        public int hashCode() {
            return C0714b.m2968a(this.f2291a) ^ (Arrays.hashCode(this.f2293c) ^ this.f2292b.hashCode());
        }

        public String toString() {
            int length = this.f2293c.length;
            if (length == 0) {
                return C0714b.m2986f(this.f2292b);
            }
            StringBuilder stringBuilder = new StringBuilder((length + 1) * 30);
            stringBuilder.append(C0714b.m2986f(this.f2292b));
            stringBuilder.append("<");
            stringBuilder.append(C0714b.m2986f(this.f2293c[0]));
            for (int i = 1; i < length; i++) {
                stringBuilder.append(", ");
                stringBuilder.append(C0714b.m2986f(this.f2293c[i]));
            }
            stringBuilder.append(">");
            return stringBuilder.toString();
        }
    }

    /* compiled from: $Gson$Types */
    /* renamed from: com.a.a.b.b$c */
    private static final class C0713c implements Serializable, WildcardType {
        /* renamed from: a */
        private final Type f2294a;
        /* renamed from: b */
        private final Type f2295b;

        public C0713c(Type[] typeArr, Type[] typeArr2) {
            boolean z = true;
            C0710a.m2967a(typeArr2.length <= 1);
            C0710a.m2967a(typeArr.length == 1);
            if (typeArr2.length == 1) {
                C0710a.m2966a(typeArr2[0]);
                C0714b.m2988h(typeArr2[0]);
                if (typeArr[0] != Object.class) {
                    z = false;
                }
                C0710a.m2967a(z);
                this.f2295b = C0714b.m2984d(typeArr2[0]);
                this.f2294a = Object.class;
                return;
            }
            C0710a.m2966a(typeArr[0]);
            C0714b.m2988h(typeArr[0]);
            this.f2295b = null;
            this.f2294a = C0714b.m2984d(typeArr[0]);
        }

        public Type[] getUpperBounds() {
            return new Type[]{this.f2294a};
        }

        public Type[] getLowerBounds() {
            if (this.f2295b == null) {
                return C0714b.f2296a;
            }
            return new Type[]{this.f2295b};
        }

        public boolean equals(Object obj) {
            return (obj instanceof WildcardType) && C0714b.m2979a((Type) this, (WildcardType) obj);
        }

        public int hashCode() {
            return (this.f2294a.hashCode() + 31) ^ (this.f2295b != null ? this.f2295b.hashCode() + 31 : 1);
        }

        public String toString() {
            StringBuilder stringBuilder;
            if (this.f2295b != null) {
                stringBuilder = new StringBuilder();
                stringBuilder.append("? super ");
                stringBuilder.append(C0714b.m2986f(this.f2295b));
                return stringBuilder.toString();
            } else if (this.f2294a == Object.class) {
                return "?";
            } else {
                stringBuilder = new StringBuilder();
                stringBuilder.append("? extends ");
                stringBuilder.append(C0714b.m2986f(this.f2294a));
                return stringBuilder.toString();
            }
        }
    }

    /* renamed from: a */
    public static ParameterizedType m2972a(Type type, Type type2, Type... typeArr) {
        return new C0712b(type, type2, typeArr);
    }

    /* renamed from: a */
    public static GenericArrayType m2971a(Type type) {
        return new C0711a(type);
    }

    /* renamed from: b */
    public static WildcardType m2981b(Type type) {
        if (type instanceof WildcardType) {
            type = ((WildcardType) type).getUpperBounds();
        } else {
            type = new Type[]{type};
        }
        return new C0713c(type, f2296a);
    }

    /* renamed from: c */
    public static WildcardType m2983c(Type type) {
        if (type instanceof WildcardType) {
            type = ((WildcardType) type).getLowerBounds();
        } else {
            type = new Type[]{type};
        }
        return new C0713c(new Type[]{Object.class}, type);
    }

    /* renamed from: d */
    public static Type m2984d(Type type) {
        if (type instanceof Class) {
            type = (Class) type;
            if (type.isArray()) {
                type = new C0711a(C0714b.m2984d(type.getComponentType()));
            }
            return type;
        } else if (type instanceof ParameterizedType) {
            ParameterizedType parameterizedType = (ParameterizedType) type;
            return new C0712b(parameterizedType.getOwnerType(), parameterizedType.getRawType(), parameterizedType.getActualTypeArguments());
        } else if (type instanceof GenericArrayType) {
            return new C0711a(((GenericArrayType) type).getGenericComponentType());
        } else {
            if (!(type instanceof WildcardType)) {
                return type;
            }
            WildcardType wildcardType = (WildcardType) type;
            return new C0713c(wildcardType.getUpperBounds(), wildcardType.getLowerBounds());
        }
    }

    /* renamed from: e */
    public static Class<?> m2985e(Type type) {
        if (type instanceof Class) {
            return (Class) type;
        }
        if (type instanceof ParameterizedType) {
            type = ((ParameterizedType) type).getRawType();
            C0710a.m2967a(type instanceof Class);
            return (Class) type;
        } else if (type instanceof GenericArrayType) {
            return Array.newInstance(C0714b.m2985e(((GenericArrayType) type).getGenericComponentType()), 0).getClass();
        } else {
            if (type instanceof TypeVariable) {
                return Object.class;
            }
            if (type instanceof WildcardType) {
                return C0714b.m2985e(((WildcardType) type).getUpperBounds()[0]);
            }
            String str;
            if (type == null) {
                str = "null";
            } else {
                str = type.getClass().getName();
            }
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("Expected a Class, ParameterizedType, or GenericArrayType, but <");
            stringBuilder.append(type);
            stringBuilder.append("> is of type ");
            stringBuilder.append(str);
            throw new IllegalArgumentException(stringBuilder.toString());
        }
    }

    /* renamed from: a */
    static boolean m2978a(Object obj, Object obj2) {
        if (obj != obj2) {
            if (obj == null || obj.equals(obj2) == null) {
                return null;
            }
        }
        return true;
    }

    /* renamed from: a */
    public static boolean m2979a(Type type, Type type2) {
        boolean z = true;
        if (type == type2) {
            return true;
        }
        if (type instanceof Class) {
            return type.equals(type2);
        }
        if (type instanceof ParameterizedType) {
            if (!(type2 instanceof ParameterizedType)) {
                return false;
            }
            ParameterizedType parameterizedType = (ParameterizedType) type;
            ParameterizedType parameterizedType2 = (ParameterizedType) type2;
            if (!C0714b.m2978a(parameterizedType.getOwnerType(), parameterizedType2.getOwnerType()) || !parameterizedType.getRawType().equals(parameterizedType2.getRawType()) || Arrays.equals(parameterizedType.getActualTypeArguments(), parameterizedType2.getActualTypeArguments()) == null) {
                z = false;
            }
            return z;
        } else if (type instanceof GenericArrayType) {
            if (!(type2 instanceof GenericArrayType)) {
                return false;
            }
            return C0714b.m2979a(((GenericArrayType) type).getGenericComponentType(), ((GenericArrayType) type2).getGenericComponentType());
        } else if (type instanceof WildcardType) {
            if (!(type2 instanceof WildcardType)) {
                return false;
            }
            WildcardType wildcardType = (WildcardType) type;
            WildcardType wildcardType2 = (WildcardType) type2;
            if (!Arrays.equals(wildcardType.getUpperBounds(), wildcardType2.getUpperBounds()) || Arrays.equals(wildcardType.getLowerBounds(), wildcardType2.getLowerBounds()) == null) {
                z = false;
            }
            return z;
        } else if (!(type instanceof TypeVariable) || !(type2 instanceof TypeVariable)) {
            return false;
        } else {
            TypeVariable typeVariable = (TypeVariable) type;
            TypeVariable typeVariable2 = (TypeVariable) type2;
            if (typeVariable.getGenericDeclaration() != typeVariable2.getGenericDeclaration() || typeVariable.getName().equals(typeVariable2.getName()) == null) {
                z = false;
            }
            return z;
        }
    }

    /* renamed from: a */
    static int m2968a(Object obj) {
        return obj != null ? obj.hashCode() : null;
    }

    /* renamed from: f */
    public static String m2986f(Type type) {
        return type instanceof Class ? ((Class) type).getName() : type.toString();
    }

    /* renamed from: a */
    static Type m2974a(Type type, Class<?> cls, Class<?> cls2) {
        if (cls2 == cls) {
            return type;
        }
        if (cls2.isInterface() != null) {
            type = cls.getInterfaces();
            int length = type.length;
            for (int i = 0; i < length; i++) {
                if (type[i] == cls2) {
                    return cls.getGenericInterfaces()[i];
                }
                if (cls2.isAssignableFrom(type[i])) {
                    return C0714b.m2974a(cls.getGenericInterfaces()[i], type[i], (Class) cls2);
                }
            }
        }
        if (cls.isInterface() == null) {
            while (cls != Object.class) {
                Class<?> superclass = cls.getSuperclass();
                if (superclass == cls2) {
                    return cls.getGenericSuperclass();
                }
                if (cls2.isAssignableFrom(superclass)) {
                    return C0714b.m2974a(cls.getGenericSuperclass(), (Class) superclass, (Class) cls2);
                }
                cls = superclass;
            }
        }
        return cls2;
    }

    /* renamed from: b */
    static Type m2980b(Type type, Class<?> cls, Class<?> cls2) {
        C0710a.m2967a(cls2.isAssignableFrom(cls));
        return C0714b.m2975a(type, (Class) cls, C0714b.m2974a(type, (Class) cls, (Class) cls2));
    }

    /* renamed from: g */
    public static Type m2987g(Type type) {
        if (type instanceof GenericArrayType) {
            return ((GenericArrayType) type).getGenericComponentType();
        }
        return ((Class) type).getComponentType();
    }

    /* renamed from: a */
    public static Type m2973a(Type type, Class<?> cls) {
        type = C0714b.m2980b(type, cls, Collection.class);
        if ((type instanceof WildcardType) != null) {
            type = ((WildcardType) type).getUpperBounds()[0];
        }
        if ((type instanceof ParameterizedType) != null) {
            return ((ParameterizedType) type).getActualTypeArguments()[0];
        }
        return Object.class;
    }

    /* renamed from: b */
    public static Type[] m2982b(Type type, Class<?> cls) {
        if (type == Properties.class) {
            return new Type[]{String.class, String.class};
        }
        type = C0714b.m2980b(type, cls, Map.class);
        if ((type instanceof ParameterizedType) != null) {
            return ((ParameterizedType) type).getActualTypeArguments();
        }
        return new Type[]{Object.class, Object.class};
    }

    /* renamed from: a */
    public static Type m2975a(Type type, Class<?> cls, Type type2) {
        return C0714b.m2976a(type, cls, type2, new HashSet());
    }

    /* renamed from: a */
    private static Type m2976a(Type type, Class<?> cls, Type type2, Collection<TypeVariable> collection) {
        while (type2 instanceof TypeVariable) {
            Type type3 = (TypeVariable) type2;
            if (collection.contains(type3)) {
                return type2;
            }
            collection.add(type3);
            type2 = C0714b.m2977a(type, (Class) cls, (TypeVariable) type3);
            if (type2 == type3) {
                return type2;
            }
        }
        if (type2 instanceof Class) {
            type3 = (Class) type2;
            if (type3.isArray()) {
                type2 = type3.getComponentType();
                type = C0714b.m2976a(type, cls, type2, collection);
                if (type2 != type) {
                    type3 = C0714b.m2971a(type);
                }
                return type3;
            }
        }
        if (type2 instanceof GenericArrayType) {
            type2 = (GenericArrayType) type2;
            type3 = type2.getGenericComponentType();
            type = C0714b.m2976a(type, cls, type3, collection);
            if (type3 != type) {
                type2 = C0714b.m2971a(type);
            }
            return type2;
        }
        int i = 0;
        if (type2 instanceof ParameterizedType) {
            type2 = (ParameterizedType) type2;
            type3 = type2.getOwnerType();
            Type a = C0714b.m2976a(type, cls, type3, collection);
            int i2 = a != type3 ? 1 : 0;
            Type[] actualTypeArguments = type2.getActualTypeArguments();
            int length = actualTypeArguments.length;
            while (i < length) {
                Type a2 = C0714b.m2976a(type, cls, actualTypeArguments[i], collection);
                if (a2 != actualTypeArguments[i]) {
                    if (i2 == 0) {
                        actualTypeArguments = (Type[]) actualTypeArguments.clone();
                        i2 = 1;
                    }
                    actualTypeArguments[i] = a2;
                }
                i++;
            }
            if (i2 != 0) {
                type2 = C0714b.m2972a(a, type2.getRawType(), actualTypeArguments);
            }
            return type2;
        } else if (!(type2 instanceof WildcardType)) {
            return type2;
        } else {
            WildcardType wildcardType = (WildcardType) type2;
            Type[] lowerBounds = wildcardType.getLowerBounds();
            Type[] upperBounds = wildcardType.getUpperBounds();
            if (lowerBounds.length == 1) {
                type = C0714b.m2976a(type, cls, lowerBounds[0], collection);
                if (type != lowerBounds[0]) {
                    return C0714b.m2983c(type);
                }
            } else if (upperBounds.length == 1) {
                type = C0714b.m2976a(type, cls, upperBounds[0], collection);
                if (type != upperBounds[0]) {
                    return C0714b.m2981b(type);
                }
            }
            return wildcardType;
        }
    }

    /* renamed from: a */
    static Type m2977a(Type type, Class<?> cls, TypeVariable<?> typeVariable) {
        Class a = C0714b.m2970a((TypeVariable) typeVariable);
        if (a == null) {
            return typeVariable;
        }
        type = C0714b.m2974a(type, (Class) cls, a);
        if ((type instanceof ParameterizedType) == null) {
            return typeVariable;
        }
        return ((ParameterizedType) type).getActualTypeArguments()[C0714b.m2969a(a.getTypeParameters(), (Object) typeVariable)];
    }

    /* renamed from: a */
    private static int m2969a(Object[] objArr, Object obj) {
        int length = objArr.length;
        for (int i = 0; i < length; i++) {
            if (obj.equals(objArr[i])) {
                return i;
            }
        }
        throw new NoSuchElementException();
    }

    /* renamed from: a */
    private static Class<?> m2970a(TypeVariable<?> typeVariable) {
        typeVariable = typeVariable.getGenericDeclaration();
        return typeVariable instanceof Class ? (Class) typeVariable : null;
    }

    /* renamed from: h */
    static void m2988h(Type type) {
        boolean z;
        if (type instanceof Class) {
            if (((Class) type).isPrimitive() != null) {
                z = null;
                C0710a.m2967a(z);
            }
        }
        z = true;
        C0710a.m2967a(z);
    }
}
