package com.samsung.p047a.p048a.p049a.p050a.p054d;

import com.samsung.p047a.p048a.p049a.p050a.p056f.C0781d.C0777a;

/* compiled from: PolicyType */
/* renamed from: com.samsung.a.a.a.a.d.b */
public enum C0763b {
    DIAGNOSTIC_TERMS(C0777a.DLC, false, false),
    CUSTOM_TERMS(C0777a.DLS, true, true);
    
    /* renamed from: c */
    private C0777a f2429c;
    /* renamed from: d */
    private boolean f2430d;
    /* renamed from: e */
    private boolean f2431e;

    private C0763b(C0777a c0777a, boolean z, boolean z2) {
        this.f2429c = c0777a;
        this.f2430d = z;
        this.f2431e = z2;
    }

    /* renamed from: a */
    public boolean m3169a() {
        return C0764c.f2432a ? false : this.f2430d;
    }

    /* renamed from: b */
    public C0777a m3170b() {
        return C0764c.f2432a ? C0777a.DMA : this.f2429c;
    }

    /* renamed from: c */
    public boolean m3171c() {
        return C0764c.f2432a ? false : this.f2431e;
    }

    /* renamed from: d */
    public boolean m3172d() {
        return m3170b() != C0777a.DLC;
    }
}
