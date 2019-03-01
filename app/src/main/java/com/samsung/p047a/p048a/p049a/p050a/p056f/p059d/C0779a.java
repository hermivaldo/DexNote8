package com.samsung.p047a.p048a.p049a.p050a.p056f.p059d;

import android.content.Context;
import com.samsung.p047a.p048a.p049a.C0790b;
import com.samsung.p047a.p048a.p049a.C0791c;
import com.samsung.p047a.p048a.p049a.p050a.C0757b;
import com.samsung.p047a.p048a.p049a.p050a.p056f.C0776c;
import com.samsung.p047a.p048a.p049a.p050a.p056f.C0782e;
import com.samsung.p047a.p048a.p049a.p050a.p056f.p059d.p060a.C0778a;
import com.samsung.p047a.p048a.p049a.p050a.p056f.p059d.p061b.C0780a;
import com.samsung.p047a.p048a.p049a.p050a.p063i.C0784a;
import com.samsung.p047a.p048a.p049a.p050a.p063i.C0787c;
import com.samsung.p047a.p048a.p049a.p050a.p063i.C0788d;
import java.util.List;
import java.util.Queue;

/* compiled from: Manager */
/* renamed from: com.samsung.a.a.a.a.f.d.a */
public class C0779a {
    /* renamed from: d */
    private static C0779a f2467d;
    /* renamed from: a */
    private C0778a f2468a;
    /* renamed from: b */
    private C0780a f2469b;
    /* renamed from: c */
    private boolean f2470c;

    private C0779a(Context context, boolean z, int i) {
        if (z) {
            this.f2468a = new C0778a(context);
        }
        this.f2469b = new C0780a(i);
        this.f2470c = z;
    }

    private C0779a(C0791c c0791c, int i) {
        this.f2468a = new C0778a(c0791c);
        this.f2469b = new C0780a(i);
        this.f2470c = true;
    }

    /* renamed from: a */
    public static C0779a m3225a(Context context, C0790b c0790b) {
        if (f2467d == null) {
            synchronized (C0779a.class) {
                int m = c0790b.m3284m();
                if (!C0757b.f2417a.m3171c()) {
                    f2467d = new C0779a(context, false, m);
                } else if (C0787c.m3260a(context).getString("lgt", "").equals("rtb")) {
                    c0790b = c0790b.m3285n();
                    if (c0790b != null) {
                        f2467d = new C0779a(c0790b, m);
                    } else {
                        f2467d = new C0779a(context, true, m);
                    }
                } else {
                    f2467d = new C0779a(context, false, m);
                }
            }
        }
        return f2467d;
    }

    /* renamed from: a */
    public void m3229a(Context context) {
        m3230a(new C0778a(context));
    }

    /* renamed from: a */
    public void m3232a(C0791c c0791c) {
        m3230a(new C0778a(c0791c));
    }

    /* renamed from: a */
    public void m3230a(C0778a c0778a) {
        this.f2470c = true;
        this.f2468a = c0778a;
        m3226d();
    }

    /* renamed from: d */
    private void m3226d() {
        if (!this.f2469b.m3237a().isEmpty()) {
            for (C0782e a : this.f2469b.m3237a()) {
                this.f2468a.m3223a(a);
            }
            this.f2469b.m3237a().clear();
        }
    }

    /* renamed from: a */
    public boolean m3234a() {
        return this.f2470c;
    }

    /* renamed from: a */
    public void m3231a(C0782e c0782e) {
        if (this.f2470c) {
            this.f2468a.m3223a(c0782e);
        } else {
            this.f2469b.m3238a(c0782e);
        }
    }

    /* renamed from: a */
    public void m3228a(long j, String str, C0776c c0776c) {
        m3231a(new C0782e(j, str, c0776c));
    }

    /* renamed from: b */
    public void m3235b() {
        if (this.f2470c) {
            this.f2468a.m3222a(C0788d.m3261a(5));
        }
    }

    /* renamed from: c */
    public Queue<C0782e> m3236c() {
        return m3227a(0);
    }

    /* renamed from: a */
    public Queue<C0782e> m3227a(int i) {
        if (this.f2470c) {
            m3235b();
            if (i <= 0) {
                i = this.f2468a.m3220a();
            } else {
                i = this.f2468a.m3221a(i);
            }
        } else {
            i = this.f2469b.m3237a();
        }
        if (!i.isEmpty()) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("get log from ");
            stringBuilder.append(this.f2470c ? "Database " : "Queue ");
            stringBuilder.append("(");
            stringBuilder.append(i.size());
            stringBuilder.append(")");
            C0784a.m3253a(stringBuilder.toString());
        }
        return i;
    }

    /* renamed from: a */
    public void m3233a(List<String> list) {
        if (!list.isEmpty() && this.f2470c) {
            this.f2468a.m3224a((List) list);
        }
    }
}
