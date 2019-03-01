package com.samsung.a.a.a.a.d;

import com.samsung.a.a.a.a.f.d.a;

/* compiled from: PolicyType */
public enum b {
    DIAGNOSTIC_TERMS(a.DLC, false, false),
    CUSTOM_TERMS(a.DLS, true, true);
    
    private a c;
    private boolean d;
    private boolean e;

    private b(a aVar, boolean z, boolean z2) {
        this.c = aVar;
        this.d = z;
        this.e = z2;
    }

    public boolean a() {
        return c.a ? false : this.d;
    }

    public a b() {
        return c.a ? a.DMA : this.c;
    }

    public boolean c() {
        return c.a ? false : this.e;
    }

    public boolean d() {
        return b() != a.DLC;
    }
}
