package com.samsung.a.a.a.a.a;

/* compiled from: API */
public enum a {
    DATA_DELETE(c.a, b.DATA_DELETE, d.POST),
    GET_POLICY(c.b, b.DEVICE_CONTROLLER_DIR, d.GET),
    SEND_LOG(c.c, b.DLS_DIR, d.POST),
    SEND_BUFFERED_LOG(c.c, b.DLS_DIR_BAT, d.POST);
    
    c e;
    b f;
    d g;

    private a(c cVar, b bVar, d dVar) {
        this.e = cVar;
        this.f = bVar;
        this.g = dVar;
    }

    public String a() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(this.e.a());
        stringBuilder.append(this.f.a());
        return stringBuilder.toString();
    }

    public String b() {
        return this.g.a();
    }
}
