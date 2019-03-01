package com.samsung.p047a.p048a.p049a.p050a.p056f.p057a;

import com.samsung.android.lxd.processor.config.SystemConfigHelper;
import com.samsung.p047a.p048a.p049a.C0790b;
import com.samsung.p047a.p048a.p049a.p050a.p053c.C0758a;
import com.samsung.p047a.p048a.p049a.p050a.p053c.C0759b;
import com.samsung.p047a.p048a.p049a.p050a.p056f.C0782e;
import com.samsung.p047a.p048a.p049a.p050a.p063i.C0784a;

/* compiled from: SendLogTask */
/* renamed from: com.samsung.a.a.a.a.f.a.c */
public class C1293c implements C0759b {
    /* renamed from: a */
    private C0772a f4336a;
    /* renamed from: b */
    private C0790b f4337b;
    /* renamed from: c */
    private C0782e f4338c;
    /* renamed from: d */
    private C0758a f4339d;
    /* renamed from: e */
    private int f4340e = -1;

    public C1293c(C0772a c0772a, C0790b c0790b, C0782e c0782e, C0758a c0758a) {
        this.f4336a = c0772a;
        this.f4337b = c0790b;
        this.f4338c = c0782e;
        this.f4339d = c0758a;
    }

    /* renamed from: a */
    public void mo650a() {
        try {
            this.f4340e = this.f4336a.m3207d().mo641a(this.f4338c.m3247d().m3218a(), this.f4337b.m3269a().substring(0, 3), this.f4338c.m3244b(), this.f4338c.m3240a(), SystemConfigHelper.CONFIG_OPTION_OFF, "", "2.01.005", this.f4338c.m3246c());
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("send to DLC : ");
            stringBuilder.append(this.f4338c.m3246c());
            C0784a.m3253a(stringBuilder.toString());
        } catch (Exception e) {
            C0784a.m3252a(getClass(), e);
        }
    }

    /* renamed from: b */
    public int mo651b() {
        if (this.f4340e == 0) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("send result success : ");
            stringBuilder.append(this.f4340e);
            C0784a.m3254a("DLC Sender", stringBuilder.toString());
            return 1;
        }
        stringBuilder = new StringBuilder();
        stringBuilder.append("send result fail : ");
        stringBuilder.append(this.f4340e);
        C0784a.m3254a("DLC Sender", stringBuilder.toString());
        return -7;
    }
}
