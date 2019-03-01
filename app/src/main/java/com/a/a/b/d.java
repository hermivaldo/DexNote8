package com.a.a.b;

import com.a.a.a;
import com.a.a.b;
import com.a.a.d.c;
import com.a.a.e;
import com.a.a.t;
import com.a.a.u;
import com.samsung.android.lxd.processor.network.channel.pty.internal.KeycodeConstants;
import java.lang.reflect.Field;
import java.util.Collections;
import java.util.List;

/* compiled from: Excluder */
public final class d implements u, Cloneable {
    public static final d a = new d();
    private double b = -1.0d;
    private int c = KeycodeConstants.KEYCODE_F6;
    private boolean d = true;
    private boolean e;
    private List<a> f = Collections.emptyList();
    private List<a> g = Collections.emptyList();

    /* renamed from: a */
    protected d clone() {
        try {
            return (d) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new AssertionError(e);
        }
    }

    public <T> t<T> a(e eVar, com.a.a.c.a<T> aVar) {
        Class rawType = aVar.getRawType();
        final boolean a = a(rawType, true);
        final boolean a2 = a(rawType, false);
        if (!a && !a2) {
            return null;
        }
        final e eVar2 = eVar;
        final com.a.a.c.a<T> aVar2 = aVar;
        return new t<T>() {
            private t<T> f;

            public T b(com.a.a.d.a aVar) {
                if (!a2) {
                    return b().b(aVar);
                }
                aVar.n();
                return null;
            }

            public void a(c cVar, T t) {
                if (a) {
                    cVar.f();
                } else {
                    b().a(cVar, t);
                }
            }

            private t<T> b() {
                t<T> tVar = this.f;
                if (tVar != null) {
                    return tVar;
                }
                tVar = eVar2.a(d.this, aVar2);
                this.f = tVar;
                return tVar;
            }
        };
    }

    public boolean a(Field field, boolean z) {
        if ((this.c & field.getModifiers()) != 0) {
            return true;
        }
        if ((this.b != -1.0d && !a((com.a.a.a.d) field.getAnnotation(com.a.a.a.d.class), (com.a.a.a.e) field.getAnnotation(com.a.a.a.e.class))) || field.isSynthetic()) {
            return true;
        }
        if (this.e) {
            com.a.a.a.a aVar = (com.a.a.a.a) field.getAnnotation(com.a.a.a.a.class);
            if (aVar == null || (z ? aVar.a() : aVar.b())) {
                return true;
            }
        }
        if ((!this.d && b(field.getType())) || a(field.getType())) {
            return true;
        }
        List<a> list = z ? this.f : this.g;
        if (!list.isEmpty()) {
            b bVar = new b(field);
            for (a a : list) {
                if (a.a(bVar)) {
                    return true;
                }
            }
        }
        return false;
    }

    public boolean a(Class<?> cls, boolean z) {
        if (this.b != -1.0d && !a((com.a.a.a.d) cls.getAnnotation(com.a.a.a.d.class), (com.a.a.a.e) cls.getAnnotation(com.a.a.a.e.class))) {
            return true;
        }
        if ((!this.d && b(cls)) || a((Class) cls)) {
            return true;
        }
        for (a a : z ? this.f : this.g) {
            if (a.a((Class) cls)) {
                return true;
            }
        }
        return false;
    }

    private boolean a(Class<?> cls) {
        return !Enum.class.isAssignableFrom(cls) && (cls.isAnonymousClass() || cls.isLocalClass());
    }

    private boolean b(Class<?> cls) {
        return cls.isMemberClass() && !c(cls);
    }

    private boolean c(Class<?> cls) {
        return (cls.getModifiers() & 8) != 0;
    }

    private boolean a(com.a.a.a.d dVar, com.a.a.a.e eVar) {
        return a(dVar) && a(eVar);
    }

    private boolean a(com.a.a.a.d dVar) {
        return dVar == null || dVar.a() <= this.b;
    }

    private boolean a(com.a.a.a.e eVar) {
        return eVar == null || eVar.a() > this.b;
    }
}
