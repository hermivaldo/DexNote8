package com.samsung.a.a.a.a.a;

import com.samsung.a.a.a.a.i.d;

/* compiled from: Domain */
public enum c {
    ;
    
    String d;

    static {
        a = new c("REGISTRATION", 0, d.a() ? "https://stg-api.di.atlas.samsung.com" : "https://regi.di.atlas.samsung.com");
        b = new c("POLICY", 1, d.a() ? "https://stg-api.di.atlas.samsung.com" : "https://dc.di.atlas.samsung.com");
        c = new c("DLS", 2, "");
        e = new c[]{a, b, c};
    }

    private c(String str) {
        this.d = str;
    }

    public String a() {
        return this.d;
    }

    public void a(String str) {
        this.d = str;
    }
}
