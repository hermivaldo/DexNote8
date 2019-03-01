package com.samsung.p047a.p048a.p049a;

import android.app.Application;
import com.samsung.p047a.p048a.p049a.p050a.C0757b;
import com.samsung.p047a.p048a.p049a.p050a.p054d.C0764c;
import com.samsung.p047a.p048a.p049a.p050a.p054d.C0766d;
import com.samsung.p047a.p048a.p049a.p050a.p063i.C0788d;

/* compiled from: SamsungAnalytics */
/* renamed from: com.samsung.a.a.a.e */
public class C0795e {
    /* renamed from: a */
    private static C0795e f2505a;
    /* renamed from: b */
    private C0757b f2506b = null;

    private C0795e(Application application, C0790b c0790b) {
        C0764c.m3183b(application);
        if (!C0766d.m3187a(application, c0790b)) {
            return;
        }
        if (c0790b.m3275d()) {
            this.f2506b = new C0757b(application, c0790b);
        } else if (C0766d.m3186a()) {
            this.f2506b = new C0757b(application, c0790b);
        }
    }

    /* renamed from: b */
    private static C0795e m3296b(Application application, C0790b c0790b) {
        if (f2505a == null || f2505a.f2506b == null) {
            synchronized (C0795e.class) {
                f2505a = new C0795e(application, c0790b);
            }
        }
        return f2505a;
    }

    /* renamed from: a */
    public static void m3295a(Application application, C0790b c0790b) {
        C0795e.m3296b(application, c0790b);
    }

    /* renamed from: a */
    public static C0795e m3294a() {
        if (f2505a == null) {
            C0788d.m3262a("call after setConfiguration() method");
            if (!C0788d.m3263a()) {
                return C0795e.m3296b(null, null);
            }
        }
        return f2505a;
    }

    /* renamed from: a */
    public int m3297a(java.util.Map<java.lang.String, java.lang.String> r2) {
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
        r1 = this;
        r1 = r1.f2506b;	 Catch:{ NullPointerException -> 0x0008 }
        r0 = 0;	 Catch:{ NullPointerException -> 0x0008 }
        r1 = r1.m3163a(r2, r0);	 Catch:{ NullPointerException -> 0x0008 }
        return r1;
    L_0x0008:
        r1 = -100;
        return r1;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.samsung.a.a.a.e.a(java.util.Map):int");
    }
}
