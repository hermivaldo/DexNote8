package com.a.a.b.a;

import com.a.a.c.a;
import com.a.a.d.b;
import com.a.a.d.c;
import com.a.a.e;
import com.a.a.r;
import com.a.a.t;
import com.a.a.u;
import java.sql.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

/* compiled from: SqlDateTypeAdapter */
public final class j extends t<Date> {
    public static final u a = new u() {
        public <T> t<T> a(e eVar, a<T> aVar) {
            return aVar.getRawType() == Date.class ? new j() : null;
        }
    };
    private final DateFormat b = new SimpleDateFormat("MMM d, yyyy");

    /* renamed from: a */
    public synchronized Date b(com.a.a.d.a aVar) {
        if (aVar.f() == b.NULL) {
            aVar.j();
            return null;
        }
        try {
            return new Date(this.b.parse(aVar.h()).getTime());
        } catch (Throwable e) {
            throw new r(e);
        }
    }

    public synchronized void a(c cVar, Date date) {
        cVar.b(date == null ? null : this.b.format(date));
    }
}
