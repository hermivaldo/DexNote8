package com.a.a.b.a;

import com.a.a.c.a;
import com.a.a.d.b;
import com.a.a.e;
import com.a.a.t;
import com.a.a.u;
import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

/* compiled from: DateTypeAdapter */
public final class c extends t<Date> {
    public static final u a = new u() {
        public <T> t<T> a(e eVar, a<T> aVar) {
            return aVar.getRawType() == Date.class ? new c() : null;
        }
    };
    private final DateFormat b = DateFormat.getDateTimeInstance(2, 2, Locale.US);
    private final DateFormat c = DateFormat.getDateTimeInstance(2, 2);

    /* renamed from: a */
    public Date b(com.a.a.d.a aVar) {
        if (aVar.f() != b.NULL) {
            return a(aVar.h());
        }
        aVar.j();
        return null;
    }

    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Missing block: B:9:0x0012, code:
            return r2.b.parse(r3);
     */
    /* JADX WARNING: Missing block: B:13:0x001e, code:
            return com.a.a.b.a.a.a.a(r3, new java.text.ParsePosition(0));
     */
    /* JADX WARNING: Missing block: B:14:0x001f, code:
            r0 = move-exception;
     */
    /* JADX WARNING: Missing block: B:17:0x0025, code:
            throw new com.a.a.r(r3, r0);
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private synchronized Date a(String str) {
        return this.c.parse(str);
    }

    public synchronized void a(com.a.a.d.c cVar, Date date) {
        if (date == null) {
            cVar.f();
        } else {
            cVar.b(this.b.format(date));
        }
    }
}
