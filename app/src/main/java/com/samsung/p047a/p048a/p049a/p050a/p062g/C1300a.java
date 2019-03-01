package com.samsung.p047a.p048a.p049a.p050a.p062g;

import android.content.Context;
import com.samsung.p047a.p048a.p049a.C0790b;
import com.samsung.p047a.p048a.p049a.p050a.C0757b;
import com.samsung.p047a.p048a.p049a.p050a.p053c.C0759b;
import com.samsung.p047a.p048a.p049a.p050a.p056f.C0781d;
import com.samsung.p047a.p048a.p049a.p050a.p063i.C0784a;
import com.samsung.p047a.p048a.p049a.p050a.p063i.C0787c;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/* compiled from: BuildClient */
/* renamed from: com.samsung.a.a.a.a.g.a */
public class C1300a implements C0759b {
    /* renamed from: a */
    private static boolean f4363a = true;
    /* renamed from: b */
    private Context f4364b;
    /* renamed from: c */
    private C0790b f4365c;
    /* renamed from: d */
    private List<String> f4366d;

    public C1300a(Context context, C0790b c0790b) {
        this.f4364b = context;
        this.f4365c = c0790b;
    }

    /* renamed from: a */
    public void mo650a() {
        this.f4366d = new C0783b(this.f4364b).m3251a();
    }

    /* renamed from: b */
    public int mo651b() {
        if (this.f4366d.isEmpty()) {
            C0784a.m3254a("Setting Sender", "No status log");
            return 0;
        }
        Map hashMap = new HashMap();
        hashMap.put("ts", String.valueOf(System.currentTimeMillis()));
        hashMap.put("t", "st");
        long j = 0;
        for (String put : this.f4366d) {
            hashMap.put("sti", put);
            if (C0781d.m3239a(this.f4364b, C0757b.f2417a.m3170b(), this.f4365c).mo1274e(hashMap) == 0) {
                C0784a.m3254a("Setting Sender", "Send success");
                j = System.currentTimeMillis();
            } else {
                C0784a.m3254a("Setting Sender", "Send fail");
            }
        }
        if (j != 0) {
            C0787c.m3260a(this.f4364b).edit().putLong("status_sent_date", j).apply();
        }
        return 0;
    }
}
