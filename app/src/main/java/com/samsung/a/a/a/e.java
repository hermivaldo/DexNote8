package com.samsung.a.a.a;

import android.app.Application;
import com.samsung.a.a.a.a.b;
import com.samsung.a.a.a.a.d.c;
import com.samsung.a.a.a.a.d.d;
import java.util.Map;

/* compiled from: SamsungAnalytics */
public class e {
    private static e a;
    private b b = null;

    private e(Application application, b bVar) {
        c.b(application);
        if (!d.a(application, bVar)) {
            return;
        }
        if (bVar.d()) {
            this.b = new b(application, bVar);
        } else if (d.a()) {
            this.b = new b(application, bVar);
        }
    }

    private static e b(Application application, b bVar) {
        if (a == null || a.b == null) {
            synchronized (e.class) {
                a = new e(application, bVar);
            }
        }
        return a;
    }

    public static void a(Application application, b bVar) {
        b(application, bVar);
    }

    public static e a() {
        if (a == null) {
            com.samsung.a.a.a.a.i.d.a("call after setConfiguration() method");
            if (!com.samsung.a.a.a.a.i.d.a()) {
                return b(null, null);
            }
        }
        return a;
    }

    public int a(Map<String, String> map) {
        try {
            return this.b.a((Map) map, false);
        } catch (NullPointerException unused) {
            return -100;
        }
    }
}
