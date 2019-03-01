package com.p031a.p032a.p034b.p035a;

import com.p031a.p032a.C0737e;
import com.p031a.p032a.C0746t;
import com.p031a.p032a.C0747u;
import com.p031a.p032a.C1279r;
import com.p031a.p032a.p037c.C0731a;
import com.p031a.p032a.p038d.C0732a;
import com.p031a.p032a.p038d.C0733b;
import com.p031a.p032a.p038d.C0734c;
import java.sql.Time;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

/* compiled from: TimeTypeAdapter */
/* renamed from: com.a.a.b.a.k */
public final class C1233k extends C0746t<Time> {
    /* renamed from: a */
    public static final C0747u f4230a = new C12321();
    /* renamed from: b */
    private final DateFormat f4231b = new SimpleDateFormat("hh:mm:ss a");

    /* compiled from: TimeTypeAdapter */
    /* renamed from: com.a.a.b.a.k$1 */
    static class C12321 implements C0747u {
        C12321() {
        }

        /* renamed from: a */
        public <T> C0746t<T> mo590a(C0737e c0737e, C0731a<T> c0731a) {
            return c0731a.getRawType() == Time.class ? new C1233k() : null;
        }
    }

    /* renamed from: b */
    public /* synthetic */ Object mo592b(C0732a c0732a) {
        return m5776a(c0732a);
    }

    /* renamed from: a */
    public synchronized Time m5776a(C0732a c0732a) {
        if (c0732a.mo599f() == C0733b.NULL) {
            c0732a.mo603j();
            return null;
        }
        try {
            return new Time(this.f4231b.parse(c0732a.mo601h()).getTime());
        } catch (Throwable e) {
            throw new C1279r(e);
        }
    }

    /* renamed from: a */
    public synchronized void m5778a(C0734c c0734c, Time time) {
        c0734c.mo618b(time == null ? null : this.f4231b.format(time));
    }
}
