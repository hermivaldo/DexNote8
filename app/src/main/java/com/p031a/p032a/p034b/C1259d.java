package com.p031a.p032a.p034b;

import com.p031a.p032a.C0702a;
import com.p031a.p032a.C0737e;
import com.p031a.p032a.C0746t;
import com.p031a.p032a.C0747u;
import com.p031a.p032a.p033a.C0700d;
import com.p031a.p032a.p033a.C0701e;
import com.p031a.p032a.p037c.C0731a;
import com.p031a.p032a.p038d.C0732a;
import com.p031a.p032a.p038d.C0734c;
import com.samsung.android.lxd.processor.network.channel.pty.internal.KeycodeConstants;
import java.util.Collections;
import java.util.List;

/* compiled from: Excluder */
/* renamed from: com.a.a.b.d */
public final class C1259d implements C0747u, Cloneable {
    /* renamed from: a */
    public static final C1259d f4288a = new C1259d();
    /* renamed from: b */
    private double f4289b = -1.0d;
    /* renamed from: c */
    private int f4290c = KeycodeConstants.KEYCODE_F6;
    /* renamed from: d */
    private boolean f4291d = true;
    /* renamed from: e */
    private boolean f4292e;
    /* renamed from: f */
    private List<C0702a> f4293f = Collections.emptyList();
    /* renamed from: g */
    private List<C0702a> f4294g = Collections.emptyList();

    protected /* synthetic */ Object clone() {
        return m5937a();
    }

    /* renamed from: a */
    protected C1259d m5937a() {
        try {
            return (C1259d) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new AssertionError(e);
        }
    }

    /* renamed from: a */
    public <T> C0746t<T> mo590a(C0737e c0737e, C0731a<T> c0731a) {
        Class rawType = c0731a.getRawType();
        final boolean a = m5939a(rawType, true);
        final boolean a2 = m5939a(rawType, false);
        if (!a && !a2) {
            return null;
        }
        final C0737e c0737e2 = c0737e;
        final C0731a<T> c0731a2 = c0731a;
        return new C0746t<T>(this) {
            /* renamed from: e */
            final /* synthetic */ C1259d f4286e;
            /* renamed from: f */
            private C0746t<T> f4287f;

            /* renamed from: b */
            public T mo592b(C0732a c0732a) {
                if (!a2) {
                    return m5928b().mo592b(c0732a);
                }
                c0732a.mo607n();
                return null;
            }

            /* renamed from: a */
            public void mo591a(C0734c c0734c, T t) {
                if (a) {
                    c0734c.mo623f();
                } else {
                    m5928b().mo591a(c0734c, t);
                }
            }

            /* renamed from: b */
            private C0746t<T> m5928b() {
                C0746t<T> c0746t = this.f4287f;
                if (c0746t != null) {
                    return c0746t;
                }
                c0746t = c0737e2.m3091a(this.f4286e, c0731a2);
                this.f4287f = c0746t;
                return c0746t;
            }
        };
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    /* renamed from: a */
    public boolean m5940a(java.lang.reflect.Field r7, boolean r8) {
        /*
        r6 = this;
        r0 = r6.f4290c;
        r1 = r7.getModifiers();
        r0 = r0 & r1;
        r1 = 1;
        if (r0 == 0) goto L_0x000b;
    L_0x000a:
        return r1;
    L_0x000b:
        r2 = r6.f4289b;
        r4 = -4616189618054758400; // 0xbff0000000000000 float:0.0 double:-1.0;
        r0 = (r2 > r4 ? 1 : (r2 == r4 ? 0 : -1));
        if (r0 == 0) goto L_0x002a;
    L_0x0013:
        r0 = com.p031a.p032a.p033a.C0700d.class;
        r0 = r7.getAnnotation(r0);
        r0 = (com.p031a.p032a.p033a.C0700d) r0;
        r2 = com.p031a.p032a.p033a.C0701e.class;
        r2 = r7.getAnnotation(r2);
        r2 = (com.p031a.p032a.p033a.C0701e) r2;
        r0 = r6.m5932a(r0, r2);
        if (r0 != 0) goto L_0x002a;
    L_0x0029:
        return r1;
    L_0x002a:
        r0 = r7.isSynthetic();
        if (r0 == 0) goto L_0x0031;
    L_0x0030:
        return r1;
    L_0x0031:
        r0 = r6.f4292e;
        if (r0 == 0) goto L_0x004f;
    L_0x0035:
        r0 = com.p031a.p032a.p033a.C0697a.class;
        r0 = r7.getAnnotation(r0);
        r0 = (com.p031a.p032a.p033a.C0697a) r0;
        if (r0 == 0) goto L_0x004e;
    L_0x003f:
        if (r8 == 0) goto L_0x0048;
    L_0x0041:
        r0 = r0.m2945a();
        if (r0 != 0) goto L_0x004f;
    L_0x0047:
        goto L_0x004e;
    L_0x0048:
        r0 = r0.m2946b();
        if (r0 != 0) goto L_0x004f;
    L_0x004e:
        return r1;
    L_0x004f:
        r0 = r6.f4291d;
        if (r0 != 0) goto L_0x005e;
    L_0x0053:
        r0 = r7.getType();
        r0 = r6.m5935b(r0);
        if (r0 == 0) goto L_0x005e;
    L_0x005d:
        return r1;
    L_0x005e:
        r0 = r7.getType();
        r0 = r6.m5934a(r0);
        if (r0 == 0) goto L_0x0069;
    L_0x0068:
        return r1;
    L_0x0069:
        if (r8 == 0) goto L_0x006e;
    L_0x006b:
        r6 = r6.f4293f;
        goto L_0x0070;
    L_0x006e:
        r6 = r6.f4294g;
    L_0x0070:
        r8 = r6.isEmpty();
        if (r8 != 0) goto L_0x0092;
    L_0x0076:
        r8 = new com.a.a.b;
        r8.<init>(r7);
        r6 = r6.iterator();
    L_0x007f:
        r7 = r6.hasNext();
        if (r7 == 0) goto L_0x0092;
    L_0x0085:
        r7 = r6.next();
        r7 = (com.p031a.p032a.C0702a) r7;
        r7 = r7.m2953a(r8);
        if (r7 == 0) goto L_0x007f;
    L_0x0091:
        return r1;
    L_0x0092:
        r6 = 0;
        return r6;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.a.a.b.d.a(java.lang.reflect.Field, boolean):boolean");
    }

    /* renamed from: a */
    public boolean m5939a(Class<?> cls, boolean z) {
        if (this.f4289b != -1.0d && !m5932a((C0700d) cls.getAnnotation(C0700d.class), (C0701e) cls.getAnnotation(C0701e.class))) {
            return true;
        }
        if ((!this.f4291d && m5935b(cls)) || m5934a((Class) cls)) {
            return true;
        }
        for (C0702a a : z ? this.f4293f : this.f4294g) {
            if (a.m2954a((Class) cls)) {
                return true;
            }
        }
        return false;
    }

    /* renamed from: a */
    private boolean m5934a(Class<?> cls) {
        return !Enum.class.isAssignableFrom(cls) && (cls.isAnonymousClass() || cls.isLocalClass());
    }

    /* renamed from: b */
    private boolean m5935b(Class<?> cls) {
        return cls.isMemberClass() && !m5936c(cls);
    }

    /* renamed from: c */
    private boolean m5936c(Class<?> cls) {
        return (cls.getModifiers() & 8) != 0;
    }

    /* renamed from: a */
    private boolean m5932a(C0700d c0700d, C0701e c0701e) {
        return m5931a(c0700d) != null && m5933a(c0701e);
    }

    /* renamed from: a */
    private boolean m5931a(C0700d c0700d) {
        return c0700d == null || c0700d.m2951a() <= this.f4289b;
    }

    /* renamed from: a */
    private boolean m5933a(C0701e c0701e) {
        return c0701e == null || c0701e.m2952a() > this.f4289b;
    }
}
