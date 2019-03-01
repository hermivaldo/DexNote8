package com.a.a.b;

import java.io.Serializable;
import java.lang.reflect.Array;
import java.lang.reflect.GenericArrayType;
import java.lang.reflect.GenericDeclaration;
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
public final class b {
    static final Type[] a = new Type[0];

    /* compiled from: $Gson$Types */
    private static final class a implements Serializable, GenericArrayType {
        private final Type a;

        public a(Type type) {
            this.a = b.d(type);
        }

        public Type getGenericComponentType() {
            return this.a;
        }

        public boolean equals(Object obj) {
            return (obj instanceof GenericArrayType) && b.a((Type) this, (GenericArrayType) obj);
        }

        public int hashCode() {
            return this.a.hashCode();
        }

        public String toString() {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(b.f(this.a));
            stringBuilder.append("[]");
            return stringBuilder.toString();
        }
    }

    /* compiled from: $Gson$Types */
    private static final class b implements Serializable, ParameterizedType {
        private final Type a;
        private final Type b;
        private final Type[] c;

        public b(Type type, Type type2, Type... typeArr) {
            int i = 0;
            if (type2 instanceof Class) {
                Class cls = (Class) type2;
                boolean z = true;
                int i2 = (Modifier.isStatic(cls.getModifiers()) || cls.getEnclosingClass() == null) ? true : 0;
                if (type == null && i2 == 0) {
                    z = false;
                }
                a.a(z);
            }
            if (type == null) {
                type = null;
            } else {
                type = b.d(type);
            }
            this.a = type;
            this.b = b.d(type2);
            this.c = (Type[]) typeArr.clone();
            int length = this.c.length;
            while (i < length) {
                a.a(this.c[i]);
                b.h(this.c[i]);
                this.c[i] = b.d(this.c[i]);
                i++;
            }
        }

        public Type[] getActualTypeArguments() {
            return (Type[]) this.c.clone();
        }

        public Type getRawType() {
            return this.b;
        }

        public Type getOwnerType() {
            return this.a;
        }

        public boolean equals(Object obj) {
            return (obj instanceof ParameterizedType) && b.a((Type) this, (ParameterizedType) obj);
        }

        public int hashCode() {
            return b.a(this.a) ^ (Arrays.hashCode(this.c) ^ this.b.hashCode());
        }

        public String toString() {
            int length = this.c.length;
            if (length == 0) {
                return b.f(this.b);
            }
            StringBuilder stringBuilder = new StringBuilder((length + 1) * 30);
            stringBuilder.append(b.f(this.b));
            stringBuilder.append("<");
            stringBuilder.append(b.f(this.c[0]));
            for (int i = 1; i < length; i++) {
                stringBuilder.append(", ");
                stringBuilder.append(b.f(this.c[i]));
            }
            stringBuilder.append(">");
            return stringBuilder.toString();
        }
    }

    /* compiled from: $Gson$Types */
    private static final class c implements Serializable, WildcardType {
        private final Type a;
        private final Type b;

        public c(Type[] typeArr, Type[] typeArr2) {
            boolean z = true;
            a.a(typeArr2.length <= 1);
            a.a(typeArr.length == 1);
            if (typeArr2.length == 1) {
                a.a(typeArr2[0]);
                b.h(typeArr2[0]);
                if (typeArr[0] != Object.class) {
                    z = false;
                }
                a.a(z);
                this.b = b.d(typeArr2[0]);
                this.a = Object.class;
                return;
            }
            a.a(typeArr[0]);
            b.h(typeArr[0]);
            this.b = null;
            this.a = b.d(typeArr[0]);
        }

        public Type[] getUpperBounds() {
            return new Type[]{this.a};
        }

        public Type[] getLowerBounds() {
            if (this.b == null) {
                return b.a;
            }
            return new Type[]{this.b};
        }

        public boolean equals(Object obj) {
            return (obj instanceof WildcardType) && b.a((Type) this, (WildcardType) obj);
        }

        public int hashCode() {
            return (this.a.hashCode() + 31) ^ (this.b != null ? this.b.hashCode() + 31 : 1);
        }

        public String toString() {
            StringBuilder stringBuilder;
            if (this.b != null) {
                stringBuilder = new StringBuilder();
                stringBuilder.append("? super ");
                stringBuilder.append(b.f(this.b));
                return stringBuilder.toString();
            } else if (this.a == Object.class) {
                return "?";
            } else {
                stringBuilder = new StringBuilder();
                stringBuilder.append("? extends ");
                stringBuilder.append(b.f(this.a));
                return stringBuilder.toString();
            }
        }
    }

    public static ParameterizedType a(Type type, Type type2, Type... typeArr) {
        return new b(type, type2, typeArr);
    }

    public static GenericArrayType a(Type type) {
        return new a(type);
    }

    public static WildcardType b(Type type) {
        Type[] upperBounds;
        if (type instanceof WildcardType) {
            upperBounds = ((WildcardType) type).getUpperBounds();
        } else {
            upperBounds = new Type[]{type};
        }
        return new c(upperBounds, a);
    }

    public static WildcardType c(Type type) {
        Type[] lowerBounds;
        if (type instanceof WildcardType) {
            lowerBounds = ((WildcardType) type).getLowerBounds();
        } else {
            lowerBounds = new Type[]{type};
        }
        return new c(new Type[]{Object.class}, lowerBounds);
    }

    public static Type d(Type type) {
        if (type instanceof Class) {
            Class cls = (Class) type;
            if (cls.isArray()) {
                cls = new a(d(cls.getComponentType()));
            }
            return cls;
        } else if (type instanceof ParameterizedType) {
            ParameterizedType parameterizedType = (ParameterizedType) type;
            return new b(parameterizedType.getOwnerType(), parameterizedType.getRawType(), parameterizedType.getActualTypeArguments());
        } else if (type instanceof GenericArrayType) {
            return new a(((GenericArrayType) type).getGenericComponentType());
        } else {
            if (!(type instanceof WildcardType)) {
                return type;
            }
            WildcardType wildcardType = (WildcardType) type;
            return new c(wildcardType.getUpperBounds(), wildcardType.getLowerBounds());
        }
    }

    public static Class<?> e(Type type) {
        if (type instanceof Class) {
            return (Class) type;
        }
        if (type instanceof ParameterizedType) {
            type = ((ParameterizedType) type).getRawType();
            a.a(type instanceof Class);
            return (Class) type;
        } else if (type instanceof GenericArrayType) {
            return Array.newInstance(e(((GenericArrayType) type).getGenericComponentType()), 0).getClass();
        } else {
            if (type instanceof TypeVariable) {
                return Object.class;
            }
            if (type instanceof WildcardType) {
                return e(((WildcardType) type).getUpperBounds()[0]);
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

    static boolean a(Object obj, Object obj2) {
        return obj == obj2 || (obj != null && obj.equals(obj2));
    }

    public static boolean a(Type type, Type type2) {
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
            if (!(a(parameterizedType.getOwnerType(), parameterizedType2.getOwnerType()) && parameterizedType.getRawType().equals(parameterizedType2.getRawType()) && Arrays.equals(parameterizedType.getActualTypeArguments(), parameterizedType2.getActualTypeArguments()))) {
                z = false;
            }
            return z;
        } else if (type instanceof GenericArrayType) {
            if (!(type2 instanceof GenericArrayType)) {
                return false;
            }
            return a(((GenericArrayType) type).getGenericComponentType(), ((GenericArrayType) type2).getGenericComponentType());
        } else if (type instanceof WildcardType) {
            if (!(type2 instanceof WildcardType)) {
                return false;
            }
            WildcardType wildcardType = (WildcardType) type;
            WildcardType wildcardType2 = (WildcardType) type2;
            if (!(Arrays.equals(wildcardType.getUpperBounds(), wildcardType2.getUpperBounds()) && Arrays.equals(wildcardType.getLowerBounds(), wildcardType2.getLowerBounds()))) {
                z = false;
            }
            return z;
        } else if (!(type instanceof TypeVariable) || !(type2 instanceof TypeVariable)) {
            return false;
        } else {
            TypeVariable typeVariable = (TypeVariable) type;
            TypeVariable typeVariable2 = (TypeVariable) type2;
            if (!(typeVariable.getGenericDeclaration() == typeVariable2.getGenericDeclaration() && typeVariable.getName().equals(typeVariable2.getName()))) {
                z = false;
            }
            return z;
        }
    }

    static int a(Object obj) {
        return obj != null ? obj.hashCode() : 0;
    }

    public static String f(Type type) {
        return type instanceof Class ? ((Class) type).getName() : type.toString();
    }

    static Type a(Type type, Class<?> cls, Class<?> cls2) {
        if (cls2 == cls) {
            return type;
        }
        if (cls2.isInterface()) {
            Class[] interfaces = cls.getInterfaces();
            int length = interfaces.length;
            for (int i = 0; i < length; i++) {
                if (interfaces[i] == cls2) {
                    return cls.getGenericInterfaces()[i];
                }
                if (cls2.isAssignableFrom(interfaces[i])) {
                    return a(cls.getGenericInterfaces()[i], interfaces[i], (Class) cls2);
                }
            }
        }
        if (!cls.isInterface()) {
            Class cls3;
            while (cls3 != Object.class) {
                Class superclass = cls3.getSuperclass();
                if (superclass == cls2) {
                    return cls3.getGenericSuperclass();
                }
                if (cls2.isAssignableFrom(superclass)) {
                    return a(cls3.getGenericSuperclass(), superclass, (Class) cls2);
                }
                cls3 = superclass;
            }
        }
        return cls2;
    }

    static Type b(Type type, Class<?> cls, Class<?> cls2) {
        a.a(cls2.isAssignableFrom(cls));
        return a(type, (Class) cls, a(type, (Class) cls, (Class) cls2));
    }

    public static Type g(Type type) {
        if (type instanceof GenericArrayType) {
            return ((GenericArrayType) type).getGenericComponentType();
        }
        return ((Class) type).getComponentType();
    }

    public static Type a(Type type, Class<?> cls) {
        type = b(type, cls, Collection.class);
        if (type instanceof WildcardType) {
            type = ((WildcardType) type).getUpperBounds()[0];
        }
        if (type instanceof ParameterizedType) {
            return ((ParameterizedType) type).getActualTypeArguments()[0];
        }
        return Object.class;
    }

    public static Type[] b(Type type, Class<?> cls) {
        if (type == Properties.class) {
            return new Type[]{String.class, String.class};
        }
        type = b(type, cls, Map.class);
        if (type instanceof ParameterizedType) {
            return ((ParameterizedType) type).getActualTypeArguments();
        }
        return new Type[]{Object.class, Object.class};
    }

    public static Type a(Type type, Class<?> cls, Type type2) {
        return a(type, cls, type2, new HashSet());
    }

    private static Type a(Type type, Class<?> cls, Type type2, Collection<TypeVariable> collection) {
        Type type3;
        while (type2 instanceof TypeVariable) {
            type3 = (TypeVariable) type2;
            if (collection.contains(type3)) {
                return type2;
            }
            collection.add(type3);
            type2 = a(type, (Class) cls, (TypeVariable) type3);
            if (type2 == type3) {
                return type2;
            }
        }
        if (type2 instanceof Class) {
            type3 = (Class) type2;
            if (type3.isArray()) {
                type2 = type3.getComponentType();
                type = a(type, cls, type2, collection);
                if (type2 != type) {
                    type3 = a(type);
                }
                return type3;
            }
        }
        if (type2 instanceof GenericArrayType) {
            type2 = (GenericArrayType) type2;
            type3 = type2.getGenericComponentType();
            type = a(type, cls, type3, collection);
            if (type3 != type) {
                type2 = a(type);
            }
            return type2;
        }
        int i = 0;
        if (type2 instanceof ParameterizedType) {
            type2 = (ParameterizedType) type2;
            type3 = type2.getOwnerType();
            Type a = a(type, cls, type3, collection);
            int i2 = a != type3 ? 1 : 0;
            Type[] actualTypeArguments = type2.getActualTypeArguments();
            int length = actualTypeArguments.length;
            while (i < length) {
                Type a2 = a(type, cls, actualTypeArguments[i], collection);
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
                type2 = a(a, type2.getRawType(), actualTypeArguments);
            }
            return type2;
        } else if (!(type2 instanceof WildcardType)) {
            return type2;
        } else {
            WildcardType wildcardType = (WildcardType) type2;
            Type[] lowerBounds = wildcardType.getLowerBounds();
            Type[] upperBounds = wildcardType.getUpperBounds();
            if (lowerBounds.length == 1) {
                type = a(type, cls, lowerBounds[0], collection);
                if (type != lowerBounds[0]) {
                    return c(type);
                }
            } else if (upperBounds.length == 1) {
                type = a(type, cls, upperBounds[0], collection);
                if (type != upperBounds[0]) {
                    return b(type);
                }
            }
            return wildcardType;
        }
    }

    static Type a(Type type, Class<?> cls, TypeVariable<?> typeVariable) {
        Class a = a((TypeVariable) typeVariable);
        if (a == null) {
            return typeVariable;
        }
        type = a(type, (Class) cls, a);
        if (!(type instanceof ParameterizedType)) {
            return typeVariable;
        }
        return ((ParameterizedType) type).getActualTypeArguments()[a(a.getTypeParameters(), (Object) typeVariable)];
    }

    private static int a(Object[] objArr, Object obj) {
        int length = objArr.length;
        for (int i = 0; i < length; i++) {
            if (obj.equals(objArr[i])) {
                return i;
            }
        }
        throw new NoSuchElementException();
    }

    private static Class<?> a(TypeVariable<?> typeVariable) {
        GenericDeclaration genericDeclaration = typeVariable.getGenericDeclaration();
        return genericDeclaration instanceof Class ? (Class) genericDeclaration : null;
    }

    static void h(Type type) {
        boolean z = ((type instanceof Class) && ((Class) type).isPrimitive()) ? false : true;
        a.a(z);
    }
}
