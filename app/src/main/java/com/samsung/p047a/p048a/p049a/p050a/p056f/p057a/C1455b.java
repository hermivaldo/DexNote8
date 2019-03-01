package com.samsung.p047a.p048a.p049a.p050a.p056f.p057a;

import android.content.Context;
import com.samsung.p047a.p048a.p049a.C0790b;
import com.samsung.p047a.p048a.p049a.p050a.C0754a;
import com.samsung.p047a.p048a.p049a.p050a.p056f.C0782e;
import com.samsung.p047a.p048a.p049a.p050a.p056f.C1294a;
import com.samsung.p047a.p048a.p049a.p050a.p063i.C0784a;
import java.util.Map;
import java.util.Queue;

/* compiled from: DLCLogSender */
/* renamed from: com.samsung.a.a.a.a.f.a.b */
public class C1455b extends C1294a {
    /* renamed from: g */
    private C0772a f4905g;

    /* compiled from: DLCLogSender */
    /* renamed from: com.samsung.a.a.a.a.f.a.b$1 */
    class C12921 implements C0754a<Void, Void> {
        /* renamed from: a */
        final /* synthetic */ C1455b f4335a;

        C12921(C1455b c1455b) {
            this.f4335a = c1455b;
        }

        /* renamed from: a */
        public Void m6018a(Void voidR) {
            this.f4335a.m6948a();
            return null;
        }
    }

    public C1455b(Context context, C0790b c0790b) {
        super(context, c0790b);
        this.f4905g = new C0772a(context, new C12921(this));
        this.f4905g.m3205b();
    }

    /* renamed from: a */
    private void m6948a() {
        Queue c = this.e.m3236c();
        while (!c.isEmpty()) {
            this.f.mo649a(new C1293c(this.f4905g, this.b, (C0782e) c.poll(), null));
        }
    }

    /* renamed from: a */
    protected Map<String, String> mo1273a(Map<String, String> map) {
        Map<String, String> a = super.mo1273a(map);
        a.remove("do");
        a.remove("dm");
        a.remove("v");
        return a;
    }

    /* renamed from: e */
    public int mo1274e(Map<String, String> map) {
        m6023c(map);
        if (this.f4905g.m3206c() == null) {
            this.f4905g.m3205b();
        } else if (this.f4905g.m3207d() != null) {
            m6948a();
        }
        return 0;
    }

    /* renamed from: f */
    public int mo1275f(Map<String, String> map) {
        C0784a.m3254a("DLCLogSender", "not support sync api");
        mo1274e(map);
        return -100;
    }
}
