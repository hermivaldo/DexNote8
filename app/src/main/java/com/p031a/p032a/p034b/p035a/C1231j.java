package com.p031a.p032a.p034b.p035a;

import com.p031a.p032a.C0737e;
import com.p031a.p032a.C0746t;
import com.p031a.p032a.C0747u;
import com.p031a.p032a.C1279r;
import com.p031a.p032a.p037c.C0731a;
import com.p031a.p032a.p038d.C0732a;
import com.p031a.p032a.p038d.C0733b;
import com.p031a.p032a.p038d.C0734c;
import java.sql.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

/* compiled from: SqlDateTypeAdapter */
/* renamed from: com.a.a.b.a.j */
public final class C1231j extends C0746t<Date> {
    /* renamed from: a */
    public static final C0747u f4228a = new C12301();
    /* renamed from: b */
    private final DateFormat f4229b = new SimpleDateFormat("MMM d, yyyy");

    /* compiled from: SqlDateTypeAdapter */
    /* renamed from: com.a.a.b.a.j$1 */
    static class C12301 implements C0747u {
        C12301() {
        }

        /* renamed from: a */
        public <T> C0746t<T> mo590a(C0737e c0737e, C0731a<T> c0731a) {
            return c0731a.getRawType() == Date.class ? new C1231j() : null;
        }
    }

    /* renamed from: b */
    public /* synthetic */ Object mo592b(C0732a c0732a) {
        return m5771a(c0732a);
    }

    /* renamed from: a */
    public synchronized Date m5771a(C0732a c0732a) {
        if (c0732a.mo599f() == C0733b.NULL) {
            c0732a.mo603j();
            return null;
        }
        try {
            return new Date(this.f4229b.parse(c0732a.mo601h()).getTime());
        } catch (Throwable e) {
            throw new C1279r(e);
        }
    }

    /* renamed from: a */
    public synchronized void m5773a(C0734c c0734c, Date date) {
        c0734c.mo618b(date == null ? null : this.f4229b.format(date));
    }
}
