package com.samsung.a.a.a.a.i;

import android.content.Context;
import android.os.Build;
import android.provider.Settings.System;
import com.samsung.a.a.a.a;
import com.samsung.a.a.a.a.f.c;

/* compiled from: Utils */
public class d {
    public static boolean a() {
        return Build.TYPE.equals("eng");
    }

    public static void a(String str) {
        if (a()) {
            throw new a(str);
        }
        a.c(str);
    }

    public static long a(int i) {
        return Long.valueOf(System.currentTimeMillis()).longValue() - (((long) i) * 86400000);
    }

    public static boolean a(int i, Long l) {
        return Long.valueOf(System.currentTimeMillis()).longValue() > l.longValue() + (((long) i) * 86400000);
    }

    public static c b(String str) {
        return "dl".equals(str) ? c.DEVICE : c.UIX;
    }

    public static boolean a(Context context) {
        return System.getInt(context.getContentResolver(), "samsung_errorlog_agree", 0) == 1;
    }
}
