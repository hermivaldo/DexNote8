package com.a.a.b.a;

import com.a.a.b.c;
import com.a.a.b.h;
import com.a.a.d;
import com.a.a.e;
import com.a.a.r;
import com.a.a.t;
import com.a.a.u;
import java.lang.reflect.Field;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/* compiled from: ReflectiveTypeAdapterFactory */
public final class i implements u {
    private final c a;
    private final d b;
    private final com.a.a.b.d c;
    private final d d;

    /* compiled from: ReflectiveTypeAdapterFactory */
    static abstract class b {
        final String h;
        final boolean i;
        final boolean j;

        abstract void a(com.a.a.d.a aVar, Object obj);

        abstract void a(com.a.a.d.c cVar, Object obj);

        abstract boolean a(Object obj);

        protected b(String str, boolean z, boolean z2) {
            this.h = str;
            this.i = z;
            this.j = z2;
        }
    }

    /* compiled from: ReflectiveTypeAdapterFactory */
    public static final class a<T> extends t<T> {
        private final h<T> a;
        private final Map<String, b> b;

        a(h<T> hVar, Map<String, b> map) {
            this.a = hVar;
            this.b = map;
        }

        public T b(com.a.a.d.a aVar) {
            if (aVar.f() == com.a.a.d.b.NULL) {
                aVar.j();
                return null;
            }
            Object a = this.a.a();
            try {
                aVar.c();
                while (aVar.e()) {
                    b bVar = (b) this.b.get(aVar.g());
                    if (bVar == null || !bVar.j) {
                        aVar.n();
                    } else {
                        bVar.a(aVar, a);
                    }
                }
                aVar.d();
                return a;
            } catch (Throwable e) {
                throw new r(e);
            } catch (IllegalAccessException e2) {
                throw new AssertionError(e2);
            }
        }

        public void a(com.a.a.d.c cVar, T t) {
            if (t == null) {
                cVar.f();
                return;
            }
            cVar.d();
            try {
                for (b bVar : this.b.values()) {
                    if (bVar.a(t)) {
                        cVar.a(bVar.h);
                        bVar.a(cVar, (Object) t);
                    }
                }
                cVar.e();
            } catch (IllegalAccessException e) {
                throw new AssertionError(e);
            }
        }
    }

    public i(c cVar, d dVar, com.a.a.b.d dVar2, d dVar3) {
        this.a = cVar;
        this.b = dVar;
        this.c = dVar2;
        this.d = dVar3;
    }

    public boolean a(Field field, boolean z) {
        return a(field, z, this.c);
    }

    static boolean a(Field field, boolean z, com.a.a.b.d dVar) {
        return (dVar.a(field.getType(), z) || dVar.a(field, z)) ? false : true;
    }

    private List<String> a(Field field) {
        com.a.a.a.c cVar = (com.a.a.a.c) field.getAnnotation(com.a.a.a.c.class);
        if (cVar == null) {
            return Collections.singletonList(this.b.a(field));
        }
        String a = cVar.a();
        String[] b = cVar.b();
        if (b.length == 0) {
            return Collections.singletonList(a);
        }
        List<String> arrayList = new ArrayList(b.length + 1);
        arrayList.add(a);
        for (Object add : b) {
            arrayList.add(add);
        }
        return arrayList;
    }

    public <T> t<T> a(e eVar, com.a.a.c.a<T> aVar) {
        Class rawType = aVar.getRawType();
        if (Object.class.isAssignableFrom(rawType)) {
            return new a(this.a.a((com.a.a.c.a) aVar), a(eVar, (com.a.a.c.a) aVar, rawType));
        }
        return null;
    }

    private b a(e eVar, Field field, String str, com.a.a.c.a<?> aVar, boolean z, boolean z2) {
        final e eVar2 = eVar;
        final com.a.a.c.a aVar2 = aVar;
        final boolean a = com.a.a.b.i.a(aVar.getRawType());
        final Field field2 = field;
        com.a.a.a.b bVar = (com.a.a.a.b) field2.getAnnotation(com.a.a.a.b.class);
        t a2 = bVar != null ? this.d.a(this.a, eVar2, aVar2, bVar) : null;
        final boolean z3 = a2 != null;
        if (a2 == null) {
            a2 = eVar2.a(aVar2);
        }
        final t tVar = a2;
        return new b(str, z, z2) {
            void a(com.a.a.d.c cVar, Object obj) {
                t tVar;
                obj = field2.get(obj);
                if (z3) {
                    tVar = tVar;
                } else {
                    tVar = new m(eVar2, tVar, aVar2.getType());
                }
                tVar.a(cVar, obj);
            }

            void a(com.a.a.d.a aVar, Object obj) {
                Object b = tVar.b(aVar);
                if (b != null || !a) {
                    field2.set(obj, b);
                }
            }

            public boolean a(Object obj) {
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

    private Map<String, b> a(e eVar, com.a.a.c.a<?> aVar, Class<?> cls) {
        i iVar = this;
        Map<String, b> linkedHashMap = new LinkedHashMap();
        if (cls.isInterface()) {
            return linkedHashMap;
        }
        Type type = aVar.getType();
        com.a.a.c.a aVar2 = aVar;
        Class cls2 = cls;
        while (cls2 != Object.class) {
            Field[] declaredFields = cls2.getDeclaredFields();
            int length = declaredFields.length;
            boolean z = false;
            int i = 0;
            while (i < length) {
                Field field = declaredFields[i];
                boolean a = iVar.a(field, true);
                boolean a2 = iVar.a(field, z);
                if (a || a2) {
                    b bVar;
                    field.setAccessible(true);
                    Type a3 = com.a.a.b.b.a(aVar2.getType(), cls2, field.getGenericType());
                    List a4 = iVar.a(field);
                    int size = a4.size();
                    int i2 = z;
                    boolean z2 = a;
                    b bVar2 = null;
                    boolean z3 = z2;
                    while (i2 < size) {
                        String str = (String) a4.get(i2);
                        boolean z4 = i2 != 0 ? false : z3;
                        i iVar2 = iVar;
                        bVar = bVar2;
                        int i3 = i2;
                        int i4 = size;
                        List list = a4;
                        Type type2 = a3;
                        Field field2 = field;
                        bVar2 = bVar == null ? (b) linkedHashMap.put(str, iVar2.a(eVar, field, str, com.a.a.c.a.get(a3), z4, a2)) : bVar;
                        i2 = i3 + 1;
                        z3 = z4;
                        a3 = type2;
                        size = i4;
                        a4 = list;
                        field = field2;
                        iVar = this;
                    }
                    bVar = bVar2;
                    if (bVar != null) {
                        StringBuilder stringBuilder = new StringBuilder();
                        stringBuilder.append(type);
                        stringBuilder.append(" declares multiple JSON fields named ");
                        stringBuilder.append(bVar.h);
                        throw new IllegalArgumentException(stringBuilder.toString());
                    }
                }
                i++;
                iVar = this;
                z = false;
            }
            aVar2 = com.a.a.c.a.get(com.a.a.b.b.a(aVar2.getType(), cls2, cls2.getGenericSuperclass()));
            cls2 = aVar2.getRawType();
            iVar = this;
        }
        return linkedHashMap;
    }
}
