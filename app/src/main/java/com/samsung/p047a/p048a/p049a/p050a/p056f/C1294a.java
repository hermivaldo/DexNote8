package com.samsung.p047a.p048a.p049a.p050a.p056f;

import android.content.Context;
import android.text.TextUtils;
import com.samsung.android.lxd.processor.config.SystemConfigHelper;
import com.samsung.p047a.p048a.p049a.C0790b;
import com.samsung.p047a.p048a.p049a.p050a.C0757b;
import com.samsung.p047a.p048a.p049a.p050a.p052b.C0756a;
import com.samsung.p047a.p048a.p049a.p050a.p053c.C0760c;
import com.samsung.p047a.p048a.p049a.p050a.p053c.C1290d;
import com.samsung.p047a.p048a.p049a.p050a.p056f.C0781d.C0777a;
import com.samsung.p047a.p048a.p049a.p050a.p056f.p059d.C0779a;
import com.samsung.p047a.p048a.p049a.p050a.p063i.C0786b;
import com.samsung.p047a.p048a.p049a.p050a.p063i.C0786b.C0785a;
import com.samsung.p047a.p048a.p049a.p050a.p063i.C0788d;
import java.util.Map;

/* compiled from: BaseLogSender */
/* renamed from: com.samsung.a.a.a.a.f.a */
public abstract class C1294a implements C0773b {
    /* renamed from: a */
    protected Context f4341a;
    /* renamed from: b */
    protected C0790b f4342b;
    /* renamed from: c */
    protected C0756a f4343c;
    /* renamed from: d */
    protected C0786b<String, String> f4344d;
    /* renamed from: e */
    protected C0779a f4345e;
    /* renamed from: f */
    protected C0760c f4346f = C1290d.m6011a();

    public C1294a(Context context, C0790b c0790b) {
        this.f4341a = context.getApplicationContext();
        this.f4342b = c0790b;
        this.f4343c = new C0756a(context);
        this.f4344d = new C0786b();
        this.f4345e = C0779a.m3225a(context, c0790b);
    }

    /* renamed from: a */
    protected Map<String, String> mo1273a(Map<String, String> map) {
        if (C0757b.f2417a.m3170b() != C0777a.DMA) {
            map.put("la", this.f4343c.m3142c());
            if (!TextUtils.isEmpty(this.f4343c.m3140a())) {
                map.put("mcc", this.f4343c.m3140a());
            }
            if (!TextUtils.isEmpty(this.f4343c.m3141b())) {
                map.put("mnc", this.f4343c.m3141b());
            }
            map.put("dm", this.f4343c.m3144e());
            map.put("auid", this.f4342b.m3272b());
            map.put("do", this.f4343c.m3143d());
            map.put("av", this.f4343c.m3145f());
            map.put("uv", this.f4342b.m3280i());
            map.put("at", String.valueOf(this.f4342b.m3286o()));
            map.put("fv", this.f4343c.m3147h());
            map.put("tid", this.f4342b.m3269a());
        }
        map.put("v", "2.01.005");
        map.put("tz", this.f4343c.m3146g());
        if (this.f4342b.m3278g()) {
            map.put("aip", SystemConfigHelper.CONFIG_OPTION_ON);
            String j = this.f4342b.m3281j();
            if (j != null) {
                map.put("oip", j);
            }
        }
        return map;
    }

    /* renamed from: b */
    protected String m6022b(Map<String, String> map) {
        return this.f4344d.m3259a(map, C0785a.ONE_DEPTH);
    }

    /* renamed from: c */
    protected void m6023c(Map<String, String> map) {
        this.f4345e.m3231a(new C0782e((String) map.get("t"), Long.valueOf((String) map.get("ts")).longValue(), m6022b(mo1273a(map)), m6024d(map)));
    }

    /* renamed from: d */
    protected C0776c m6024d(Map<String, String> map) {
        return C0788d.m3266b((String) map.get("t"));
    }
}
