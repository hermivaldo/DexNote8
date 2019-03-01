package com.samsung.p047a.p048a.p049a.p050a.p051a;

/* compiled from: API */
/* renamed from: com.samsung.a.a.a.a.a.a */
public enum C0750a {
    DATA_DELETE(C0752c.f2397a, C0751b.DATA_DELETE, C0753d.POST),
    GET_POLICY(C0752c.f2398b, C0751b.DEVICE_CONTROLLER_DIR, C0753d.GET),
    SEND_LOG(C0752c.f2399c, C0751b.DLS_DIR, C0753d.POST),
    SEND_BUFFERED_LOG(C0752c.f2399c, C0751b.DLS_DIR_BAT, C0753d.POST);
    
    /* renamed from: e */
    C0752c f2388e;
    /* renamed from: f */
    C0751b f2389f;
    /* renamed from: g */
    C0753d f2390g;

    private C0750a(C0752c c0752c, C0751b c0751b, C0753d c0753d) {
        this.f2388e = c0752c;
        this.f2389f = c0751b;
        this.f2390g = c0753d;
    }

    /* renamed from: a */
    public String m3132a() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(this.f2388e.m3136a());
        stringBuilder.append(this.f2389f.m3134a());
        return stringBuilder.toString();
    }

    /* renamed from: b */
    public String m3133b() {
        return this.f2390g.m3138a();
    }
}
