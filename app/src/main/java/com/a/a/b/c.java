package com.a.a.b;

import com.a.a.c.a;
import com.a.a.f;
import com.a.a.k;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
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
public final class c {
    private final Map<Type, f<?>> a;

    public c(Map<Type, f<?>> map) {
        this.a = map;
    }

    public <T> h<T> a(a<T> aVar) {
        final Type type = aVar.getType();
        Class rawType = aVar.getRawType();
        final f fVar = (f) this.a.get(type);
        if (fVar != null) {
            return new h<T>() {
                public T a() {
                    return fVar.a(type);
                }
            };
        }
        fVar = (f) this.a.get(rawType);
        if (fVar != null) {
            return new h<T>() {
                public T a() {
                    return fVar.a(type);
                }
            };
        }
        h<T> a = a(rawType);
        if (a != null) {
            return a;
        }
        a = a(type, rawType);
        if (a != null) {
            return a;
        }
        return b(type, rawType);
    }

    private <T> h<T> a(Class<? super T> cls) {
        try {
            final Constructor declaredConstructor = cls.getDeclaredConstructor(new Class[0]);
            if (!declaredConstructor.isAccessible()) {
                declaredConstructor.setAccessible(true);
            }
            return new h<T>() {
                public T a() {
                    StringBuilder stringBuilder;
                    try {
                        return declaredConstructor.newInstance(null);
                    } catch (Throwable e) {
                        stringBuilder = new StringBuilder();
                        stringBuilder.append("Failed to invoke ");
                        stringBuilder.append(declaredConstructor);
                        stringBuilder.append(" with no args");
                        throw new RuntimeException(stringBuilder.toString(), e);
                    } catch (InvocationTargetException e2) {
                        stringBuilder = new StringBuilder();
                        stringBuilder.append("Failed to invoke ");
                        stringBuilder.append(declaredConstructor);
                        stringBuilder.append(" with no args");
                        throw new RuntimeException(stringBuilder.toString(), e2.getTargetException());
                    } catch (IllegalAccessException e3) {
                        throw new AssertionError(e3);
                    }
                }
            };
        } catch (NoSuchMethodException unused) {
            return null;
        }
    }

    private <T> h<T> a(final Type type, Class<? super T> cls) {
        if (Collection.class.isAssignableFrom(cls)) {
            if (SortedSet.class.isAssignableFrom(cls)) {
                return new h<T>() {
                    public T a() {
                        return new TreeSet();
                    }
                };
            }
            if (EnumSet.class.isAssignableFrom(cls)) {
                return new h<T>() {
                    public T a() {
                        StringBuilder stringBuilder;
                        if (type instanceof ParameterizedType) {
                            Type type = ((ParameterizedType) type).getActualTypeArguments()[0];
                            if (type instanceof Class) {
                                return EnumSet.noneOf((Class) type);
                            }
                            stringBuilder = new StringBuilder();
                            stringBuilder.append("Invalid EnumSet type: ");
                            stringBuilder.append(type.toString());
                            throw new k(stringBuilder.toString());
                        }
                        stringBuilder = new StringBuilder();
                        stringBuilder.append("Invalid EnumSet type: ");
                        stringBuilder.append(type.toString());
                        throw new k(stringBuilder.toString());
                    }
                };
            }
            if (Set.class.isAssignableFrom(cls)) {
                return new h<T>() {
                    public T a() {
                        return new LinkedHashSet();
                    }
                };
            }
            if (Queue.class.isAssignableFrom(cls)) {
                return new h<T>() {
                    public T a() {
                        return new ArrayDeque();
                    }
                };
            }
            return new h<T>() {
                public T a() {
                    return new ArrayList();
                }
            };
        } else if (!Map.class.isAssignableFrom(cls)) {
            return null;
        } else {
            if (ConcurrentNavigableMap.class.isAssignableFrom(cls)) {
                return new h<T>() {
                    public T a() {
                        return new ConcurrentSkipListMap();
                    }
                };
            }
            if (ConcurrentMap.class.isAssignableFrom(cls)) {
                return new h<T>() {
                    public T a() {
                        return new ConcurrentHashMap();
                    }
                };
            }
            if (SortedMap.class.isAssignableFrom(cls)) {
                return new h<T>() {
                    public T a() {
                        return new TreeMap();
                    }
                };
            }
            if (!(type instanceof ParameterizedType) || String.class.isAssignableFrom(a.get(((ParameterizedType) type).getActualTypeArguments()[0]).getRawType())) {
                return new h<T>() {
                    public T a() {
                        return new g();
                    }
                };
            }
            return new h<T>() {
                public T a() {
                    return new LinkedHashMap();
                }
            };
        }
    }

    private <T> h<T> b(final Type type, final Class<? super T> cls) {
        return new h<T>() {
            private final k d = k.a();

            public T a() {
                try {
                    return this.d.a(cls);
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
        return this.a.toString();
    }
}
