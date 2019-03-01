package com.a.a.b.a;

import com.a.a.c.a;
import com.a.a.d.b;
import com.a.a.d.c;
import com.a.a.e;
import com.a.a.r;
import com.a.a.t;
import com.a.a.u;
import java.sql.Time;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

/* compiled from: TimeTypeAdapter */
public final class k extends t<Time> {
    public static final u a = new u() {
        public <T> t<T> a(e eVar, a<T> aVar) {
            return aVar.getRawType() == Time.class ? new k() : null;
        }
    };
    private final DateFormat b = new SimpleDateFormat("hh:mm:ss a");

    /* renamed from: a */
    public synchronized Time b(com.a.a.d.a aVar) {
        if (aVar.f() == b.NULL) {
            aVar.j();
            return null;
        }
        try {
            return new Time(this.b.parse(aVar.h()).getTime());
        } catch (Throwable e) {
            throw new r(e);
        }
    }

    public synchronized void a(c cVar, Time time) {
        cVar.b(time == null ? null : this.b.format(time));
    }
}
