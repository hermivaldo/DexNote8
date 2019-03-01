package com.samsung.a.a.a.a.f;

import android.content.Context;
import com.samsung.a.a.a.b;

/* compiled from: Sender */
public class d {
    private static b a;

    /* compiled from: Sender */
    public enum a {
        DLC,
        DLS,
        DMA
    }

    private d() {
    }

    public static b a(Context context, a aVar, b bVar) {
        if (aVar == null) {
            aVar = bVar.d() ? a.DLS : a.DLC;
        }
        if (a == null) {
            synchronized (d.class) {
                if (aVar.equals(a.DLC)) {
                    a = new com.samsung.a.a.a.a.f.a.b(context, bVar);
                } else if (aVar.equals(a.DLS)) {
                    a = new com.samsung.a.a.a.a.f.b.b(context, bVar);
                } else if (aVar.equals(a.DMA)) {
                    a = new com.samsung.a.a.a.a.f.c.b(context, bVar);
                }
            }
        }
        return a;
    }
}
