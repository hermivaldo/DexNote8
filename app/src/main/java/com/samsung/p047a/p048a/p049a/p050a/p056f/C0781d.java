package com.samsung.p047a.p048a.p049a.p050a.p056f;

import android.content.Context;
import com.samsung.p047a.p048a.p049a.C0790b;
import com.samsung.p047a.p048a.p049a.p050a.p056f.p057a.C1455b;
import com.samsung.p047a.p048a.p049a.p050a.p056f.p058c.C1457b;
import com.samsung.p047a.p048a.p049a.p050a.p056f.p066b.C1456b;

/* compiled from: Sender */
/* renamed from: com.samsung.a.a.a.a.f.d */
public class C0781d {
    /* renamed from: a */
    private static C0773b f2472a;

    /* compiled from: Sender */
    /* renamed from: com.samsung.a.a.a.a.f.d$a */
    public enum C0777a {
        DLC,
        DLS,
        DMA
    }

    private C0781d() {
    }

    /* renamed from: a */
    public static C0773b m3239a(Context context, C0777a c0777a, C0790b c0790b) {
        if (c0777a == null) {
            c0777a = c0790b.m3275d() != null ? C0777a.DLS : C0777a.DLC;
        }
        if (f2472a == null) {
            synchronized (C0781d.class) {
                if (c0777a.equals(C0777a.DLC)) {
                    f2472a = new C1455b(context, c0790b);
                } else if (c0777a.equals(C0777a.DLS)) {
                    f2472a = new C1456b(context, c0790b);
                } else if (c0777a.equals(C0777a.DMA) != null) {
                    f2472a = new C1457b(context, c0790b);
                }
            }
        }
        return f2472a;
    }
}
