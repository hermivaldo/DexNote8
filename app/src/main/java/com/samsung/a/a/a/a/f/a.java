package com.samsung.a.a.a.a.f;

import android.content.Context;
import android.text.TextUtils;
import com.samsung.a.a.a.a.c.c;
import com.samsung.a.a.a.a.c.d;
import com.samsung.a.a.a.b;
import com.samsung.android.lxd.processor.config.SystemConfigHelper;
import java.util.Map;

/* compiled from: BaseLogSender */
public abstract class a implements b {
    protected Context a;
    protected b b;
    protected com.samsung.a.a.a.a.b.a c;
    protected com.samsung.a.a.a.a.i.b<String, String> d;
    protected com.samsung.a.a.a.a.f.d.a e;
    protected c f = d.a();

    public a(Context context, b bVar) {
        this.a = context.getApplicationContext();
        this.b = bVar;
        this.c = new com.samsung.a.a.a.a.b.a(context);
        this.d = new com.samsung.a.a.a.a.i.b();
        this.e = com.samsung.a.a.a.a.f.d.a.a(context, bVar);
    }

    protected Map<String, String> a(Map<String, String> map) {
        if (com.samsung.a.a.a.a.b.a.b() != com.samsung.a.a.a.a.f.d.a.DMA) {
            map.put("la", this.c.c());
            if (!TextUtils.isEmpty(this.c.a())) {
                map.put("mcc", this.c.a());
            }
            if (!TextUtils.isEmpty(this.c.b())) {
                map.put("mnc", this.c.b());
            }
            map.put("dm", this.c.e());
            map.put("auid", this.b.b());
            map.put("do", this.c.d());
            map.put("av", this.c.f());
            map.put("uv", this.b.i());
            map.put("at", String.valueOf(this.b.o()));
            map.put("fv", this.c.h());
            map.put("tid", this.b.a());
        }
        map.put("v", "2.01.005");
        map.put("tz", this.c.g());
        if (this.b.g()) {
            map.put("aip", SystemConfigHelper.CONFIG_OPTION_ON);
            String j = this.b.j();
            if (j != null) {
                map.put("oip", j);
            }
        }
        return map;
    }

    protected String b(Map<String, String> map) {
        return this.d.a(map, com.samsung.a.a.a.a.i.b.a.ONE_DEPTH);
    }

    protected void c(Map<String, String> map) {
        this.e.a(new e((String) map.get("t"), Long.valueOf((String) map.get("ts")).longValue(), b(a(map)), d(map)));
    }

    protected c d(Map<String, String> map) {
        return com.samsung.a.a.a.a.i.d.b((String) map.get("t"));
    }
}
