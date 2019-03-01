package com.p031a.p032a.p034b.p035a;

import com.p031a.p032a.C0737e;
import com.p031a.p032a.C0746t;
import com.p031a.p032a.C0747u;
import com.p031a.p032a.p037c.C0731a;
import com.p031a.p032a.p038d.C0732a;
import com.p031a.p032a.p038d.C0733b;
import com.p031a.p032a.p038d.C0734c;
import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

/* compiled from: DateTypeAdapter */
/* renamed from: com.a.a.b.a.c */
public final class C1219c extends C0746t<Date> {
    /* renamed from: a */
    public static final C0747u f4192a = new C12181();
    /* renamed from: b */
    private final DateFormat f4193b = DateFormat.getDateTimeInstance(2, 2, Locale.US);
    /* renamed from: c */
    private final DateFormat f4194c = DateFormat.getDateTimeInstance(2, 2);

    /* compiled from: DateTypeAdapter */
    /* renamed from: com.a.a.b.a.c$1 */
    static class C12181 implements C0747u {
        C12181() {
        }

        /* renamed from: a */
        public <T> C0746t<T> mo590a(C0737e c0737e, C0731a<T> c0731a) {
            return c0731a.getRawType() == Date.class ? new C1219c() : null;
        }
    }

    /* renamed from: b */
    public /* synthetic */ Object mo592b(C0732a c0732a) {
        return m5708a(c0732a);
    }

    /* renamed from: a */
    public Date m5708a(C0732a c0732a) {
        if (c0732a.mo599f() != C0733b.NULL) {
            return m5707a(c0732a.mo601h());
        }
        c0732a.mo603j();
        return null;
    }

    /* renamed from: a */
    private synchronized java.util.Date m5707a(java.lang.String r3) {
        /* JADX: method processing error */
/*
Error: java.lang.NullPointerException
	at jadx.core.dex.visitors.regions.ProcessTryCatchRegions.searchTryCatchDominators(ProcessTryCatchRegions.java:75)
	at jadx.core.dex.visitors.regions.ProcessTryCatchRegions.process(ProcessTryCatchRegions.java:45)
	at jadx.core.dex.visitors.regions.RegionMakerVisitor.postProcessRegions(RegionMakerVisitor.java:63)
	at jadx.core.dex.visitors.regions.RegionMakerVisitor.visit(RegionMakerVisitor.java:58)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:31)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:17)
	at jadx.core.ProcessClass.process(ProcessClass.java:34)
	at jadx.core.ProcessClass.processDependencies(ProcessClass.java:56)
	at jadx.core.ProcessClass.process(ProcessClass.java:39)
	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:282)
	at jadx.api.JavaClass.decompile(JavaClass.java:62)
	at jadx.api.JadxDecompiler.lambda$appendSourcesSave$0(JadxDecompiler.java:200)
	at jadx.api.JadxDecompiler$$Lambda$8/1201484275.run(Unknown Source)
*/
        /*
        r2 = this;
        monitor-enter(r2);
        r0 = r2.f4194c;	 Catch:{ ParseException -> 0x000b }
        r0 = r0.parse(r3);	 Catch:{ ParseException -> 0x000b }
        monitor-exit(r2);
        return r0;
    L_0x0009:
        r3 = move-exception;
        goto L_0x0026;
    L_0x000b:
        r0 = r2.f4193b;	 Catch:{ ParseException -> 0x0013 }
        r0 = r0.parse(r3);	 Catch:{ ParseException -> 0x0013 }
        monitor-exit(r2);
        return r0;
    L_0x0013:
        r0 = new java.text.ParsePosition;	 Catch:{ ParseException -> 0x001f }
        r1 = 0;	 Catch:{ ParseException -> 0x001f }
        r0.<init>(r1);	 Catch:{ ParseException -> 0x001f }
        r0 = com.p031a.p032a.p034b.p035a.p036a.C0703a.m2957a(r3, r0);	 Catch:{ ParseException -> 0x001f }
        monitor-exit(r2);
        return r0;
    L_0x001f:
        r0 = move-exception;
        r1 = new com.a.a.r;	 Catch:{ all -> 0x0009 }
        r1.<init>(r3, r0);	 Catch:{ all -> 0x0009 }
        throw r1;	 Catch:{ all -> 0x0009 }
    L_0x0026:
        monitor-exit(r2);
        throw r3;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.a.a.b.a.c.a(java.lang.String):java.util.Date");
    }

    /* renamed from: a */
    public synchronized void m5710a(C0734c c0734c, Date date) {
        if (date == null) {
            c0734c.mo623f();
        } else {
            c0734c.mo618b(this.f4193b.format(date));
        }
    }
}
