package com.samsung.p047a.p048a.p049a.p050a.p051a;

import com.samsung.p047a.p048a.p049a.p050a.p063i.C0788d;

/* compiled from: Domain */
/* renamed from: com.samsung.a.a.a.a.a.c */
public enum C0752c {
    ;
    
    /* renamed from: d */
    String f2401d;

    static {
        f2397a = new C0752c("REGISTRATION", 0, C0788d.m3263a() ? "https://stg-api.di.atlas.samsung.com" : "https://regi.di.atlas.samsung.com");
        f2398b = new C0752c("POLICY", 1, C0788d.m3263a() ? "https://stg-api.di.atlas.samsung.com" : "https://dc.di.atlas.samsung.com");
        f2399c = new C0752c("DLS", 2, "");
        f2400e = new C0752c[]{f2397a, f2398b, f2399c};
    }

    private C0752c(String str) {
        this.f2401d = str;
    }

    /* renamed from: a */
    public String m3136a() {
        return this.f2401d;
    }

    /* renamed from: a */
    public void m3137a(String str) {
        this.f2401d = str;
    }
}
