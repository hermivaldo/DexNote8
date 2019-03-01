package com.samsung.p047a.p048a.p049a.p050a.p056f.p058c;

import android.content.Context;
import android.text.TextUtils;
import com.samsung.p047a.p048a.p049a.C0790b;
import com.samsung.p047a.p048a.p049a.p050a.C0754a;
import com.samsung.p047a.p048a.p049a.p050a.C0757b;
import com.samsung.p047a.p048a.p049a.p050a.p056f.C0782e;
import com.samsung.p047a.p048a.p049a.p050a.p056f.C1294a;
import com.samsung.p047a.p048a.p049a.p050a.p063i.C0784a;
import com.samsung.p047a.p048a.p049a.p050a.p063i.C0786b;
import com.samsung.p047a.p048a.p049a.p050a.p063i.C0786b.C0785a;
import java.util.HashMap;
import java.util.Map;
import java.util.Queue;

/* compiled from: DMALogSender */
/* renamed from: com.samsung.a.a.a.a.f.c.b */
public class C1457b extends C1294a {
    /* renamed from: g */
    private C0775a f4906g;
    /* renamed from: h */
    private boolean f4907h = false;
    /* renamed from: i */
    private int f4908i = 0;

    /* compiled from: DMALogSender */
    /* renamed from: com.samsung.a.a.a.a.f.c.b$1 */
    class C12971 implements C0754a<Void, String> {
        /* renamed from: a */
        final /* synthetic */ C1457b f4359a;

        C12971(C1457b c1457b) {
            this.f4359a = c1457b;
        }

        /* renamed from: a */
        public Void m6034a(String str) {
            this.f4359a.m6964b();
            this.f4359a.m6962a();
            return null;
        }
    }

    public C1457b(Context context, C0790b c0790b) {
        super(context, c0790b);
        this.f4906g = new C0775a(context, new C12971(this));
        this.f4906g.m3213a();
    }

    /* renamed from: e */
    public int mo1274e(Map<String, String> map) {
        if (this.f4906g.m3214b()) {
            return -8;
        }
        if (this.f4908i != 0) {
            return this.f4908i;
        }
        m6023c(map);
        if (this.f4906g.m3217e() == null) {
            this.f4906g.m3213a();
        } else if (this.f4906g.m3216d() != null) {
            m6962a();
            if (this.f4907h != null) {
                m6964b();
                this.f4907h = null;
            }
        }
        return this.f4908i;
    }

    /* renamed from: f */
    public int mo1275f(Map<String, String> map) {
        return mo1274e(map);
    }

    /* renamed from: a */
    private void m6962a() {
        if (this.f4908i == 0) {
            Queue c = this.e.m3236c();
            while (!c.isEmpty()) {
                this.f.mo649a(new C1298c(this.f4906g.m3216d(), this.b, (C0782e) c.poll()));
            }
        }
    }

    /* renamed from: b */
    private void m6964b() {
        String str;
        C0786b c0786b = new C0786b();
        Map hashMap = new HashMap();
        hashMap.put("av", this.c.m3145f());
        hashMap.put("uv", this.b.m3280i());
        String a = c0786b.m3259a(hashMap, C0785a.ONE_DEPTH);
        Map hashMap2 = new HashMap();
        if (TextUtils.isEmpty(this.b.m3272b())) {
            str = null;
        } else {
            hashMap2.put("auid", this.b.m3272b());
            hashMap2.put("at", String.valueOf(this.b.m3286o()));
            str = c0786b.m3259a(hashMap2, C0785a.ONE_DEPTH);
        }
        try {
            this.f4908i = this.f4906g.m3216d().mo639a(C0757b.f2417a.ordinal(), this.b.m3269a(), a, str);
        } catch (Exception e) {
            C0784a.m3252a(e.getClass(), e);
            this.f4908i = -9;
        }
    }
}
