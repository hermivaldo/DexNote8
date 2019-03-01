package com.samsung.a.a.a.a.f.a;

import com.samsung.a.a.a.a.c.a;
import com.samsung.a.a.a.a.c.b;
import com.samsung.a.a.a.a.f.e;
import com.samsung.android.lxd.processor.config.SystemConfigHelper;

/* compiled from: SendLogTask */
public class c implements b {
    private a a;
    private com.samsung.a.a.a.b b;
    private e c;
    private a d;
    private int e = -1;

    public c(a aVar, com.samsung.a.a.a.b bVar, e eVar, a aVar2) {
        this.a = aVar;
        this.b = bVar;
        this.c = eVar;
        this.d = aVar2;
    }

    public void a() {
        try {
            this.e = this.a.d().a(this.c.d().a(), this.b.a().substring(0, 3), this.c.b(), this.c.a(), SystemConfigHelper.CONFIG_OPTION_OFF, "", "2.01.005", this.c.c());
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("send to DLC : ");
            stringBuilder.append(this.c.c());
            com.samsung.a.a.a.a.i.a.a(stringBuilder.toString());
        } catch (Exception e) {
            com.samsung.a.a.a.a.i.a.a(getClass(), e);
        }
    }

    public int b() {
        StringBuilder stringBuilder;
        if (this.e == 0) {
            stringBuilder = new StringBuilder();
            stringBuilder.append("send result success : ");
            stringBuilder.append(this.e);
            com.samsung.a.a.a.a.i.a.a("DLC Sender", stringBuilder.toString());
            return 1;
        }
        stringBuilder = new StringBuilder();
        stringBuilder.append("send result fail : ");
        stringBuilder.append(this.e);
        com.samsung.a.a.a.a.i.a.a("DLC Sender", stringBuilder.toString());
        return -7;
    }
}
