package com.samsung.p047a.p048a.p049a.p050a.p063i;

import android.content.Context;
import android.os.Build;
import android.provider.Settings.System;
import com.samsung.p047a.p048a.p049a.C0789a;
import com.samsung.p047a.p048a.p049a.p050a.p056f.C0776c;

/* compiled from: Utils */
/* renamed from: com.samsung.a.a.a.a.i.d */
public class C0788d {
    /* renamed from: a */
    public static boolean m3263a() {
        return Build.TYPE.equals("eng");
    }

    /* renamed from: a */
    public static void m3262a(String str) {
        if (C0788d.m3263a()) {
            throw new C0789a(str);
        }
        C0784a.m3256c(str);
    }

    /* renamed from: a */
    public static long m3261a(int i) {
        return Long.valueOf(System.currentTimeMillis()).longValue() - (((long) i) * 86400000);
    }

    /* renamed from: a */
    public static boolean m3264a(int i, Long l) {
        return Long.valueOf(System.currentTimeMillis()).longValue() > l.longValue() + (((long) i) * 86400000);
    }

    /* renamed from: b */
    public static C0776c m3266b(String str) {
        return "dl".equals(str) != null ? C0776c.DEVICE : C0776c.UIX;
    }

    /* renamed from: a */
    public static boolean m3265a(Context context) {
        return System.getInt(context.getContentResolver(), "samsung_errorlog_agree", 0) == 1;
    }
}
