package com.samsung.p047a.p048a.p049a.p050a.p063i;

import android.util.Log;

/* compiled from: Debug */
/* renamed from: com.samsung.a.a.a.a.i.a */
public class C0784a {
    /* renamed from: a */
    public static void m3253a(String str) {
        if (C0788d.m3263a()) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("[ENG ONLY] ");
            stringBuilder.append(str);
            Log.d("SamsungAnalytics201005", stringBuilder.toString());
        }
    }

    /* renamed from: b */
    public static void m3255b(String str) {
        Log.d("SamsungAnalytics201005", str);
    }

    /* renamed from: a */
    public static void m3254a(String str, String str2) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("[");
        stringBuilder.append(str);
        stringBuilder.append("] ");
        stringBuilder.append(str2);
        C0784a.m3255b(stringBuilder.toString());
    }

    /* renamed from: c */
    public static void m3256c(String str) {
        Log.e("SamsungAnalytics201005", str);
    }

    /* renamed from: a */
    public static void m3252a(Class cls, Exception exception) {
        if (exception != null) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("[");
            stringBuilder.append(cls.getSimpleName());
            stringBuilder.append("] ");
            stringBuilder.append(exception.getClass().getSimpleName());
            stringBuilder.append(" ");
            stringBuilder.append(exception.getMessage());
            Log.w("SamsungAnalytics201005", stringBuilder.toString());
        }
    }
}
