package com.p031a.p032a.p034b;

/* compiled from: LazilyParsedNumber */
/* renamed from: com.a.a.b.f */
public final class C0717f extends Number {
    /* renamed from: a */
    private final String f2299a;

    public C0717f(String str) {
        this.f2299a = str;
    }

    public int intValue() {
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
	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:282)
	at jadx.api.JavaClass.decompile(JavaClass.java:62)
	at jadx.api.JadxDecompiler.lambda$appendSourcesSave$0(JadxDecompiler.java:200)
	at jadx.api.JadxDecompiler$$Lambda$8/1201484275.run(Unknown Source)
*/
        /*
        r2 = this;
        r0 = r2.f2299a;	 Catch:{ NumberFormatException -> 0x0007 }
        r0 = java.lang.Integer.parseInt(r0);	 Catch:{ NumberFormatException -> 0x0007 }
        return r0;
    L_0x0007:
        r0 = r2.f2299a;	 Catch:{ NumberFormatException -> 0x000f }
        r0 = java.lang.Long.parseLong(r0);	 Catch:{ NumberFormatException -> 0x000f }
        r2 = (int) r0;
        return r2;
    L_0x000f:
        r0 = new java.math.BigDecimal;
        r2 = r2.f2299a;
        r0.<init>(r2);
        r2 = r0.intValue();
        return r2;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.a.a.b.f.intValue():int");
    }

    public long longValue() {
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
	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:282)
	at jadx.api.JavaClass.decompile(JavaClass.java:62)
	at jadx.api.JadxDecompiler.lambda$appendSourcesSave$0(JadxDecompiler.java:200)
	at jadx.api.JadxDecompiler$$Lambda$8/1201484275.run(Unknown Source)
*/
        /*
        r2 = this;
        r0 = r2.f2299a;	 Catch:{ NumberFormatException -> 0x0007 }
        r0 = java.lang.Long.parseLong(r0);	 Catch:{ NumberFormatException -> 0x0007 }
        return r0;
    L_0x0007:
        r0 = new java.math.BigDecimal;
        r2 = r2.f2299a;
        r0.<init>(r2);
        r0 = r0.longValue();
        return r0;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.a.a.b.f.longValue():long");
    }

    public float floatValue() {
        return Float.parseFloat(this.f2299a);
    }

    public double doubleValue() {
        return Double.parseDouble(this.f2299a);
    }

    public String toString() {
        return this.f2299a;
    }

    public int hashCode() {
        return this.f2299a.hashCode();
    }

    public boolean equals(Object obj) {
        boolean z = true;
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof C0717f)) {
            return false;
        }
        C0717f c0717f = (C0717f) obj;
        if (this.f2299a != c0717f.f2299a) {
            if (!this.f2299a.equals(c0717f.f2299a)) {
                z = false;
            }
        }
        return z;
    }
}
