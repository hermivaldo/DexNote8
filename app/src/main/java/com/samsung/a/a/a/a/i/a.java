package com.samsung.a.a.a.a.i;

import android.util.Log;

/* compiled from: Debug */
public class a {
    public static void a(String str) {
        if (d.a()) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("[ENG ONLY] ");
            stringBuilder.append(str);
            Log.d("SamsungAnalytics201005", stringBuilder.toString());
        }
    }

    public static void b(String str) {
        Log.d("SamsungAnalytics201005", str);
    }

    public static void a(String str, String str2) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("[");
        stringBuilder.append(str);
        stringBuilder.append("] ");
        stringBuilder.append(str2);
        b(stringBuilder.toString());
    }

    public static void c(String str) {
        Log.e("SamsungAnalytics201005", str);
    }

    public static void a(Class cls, Exception exception) {
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
