package com.samsung.a.a.a.a.d;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.text.TextUtils;
import com.samsung.a.a.a.a.i.a;
import com.samsung.a.a.a.a.i.d;
import com.samsung.a.a.a.b;
import java.util.HashMap;
import java.util.Map;

/* compiled from: PolicyUtils */
public class c {
    static boolean a = false;

    public static int a(Context context, int i) {
        SharedPreferences a = com.samsung.a.a.a.a.i.c.a(context);
        int i2 = 0;
        if (i == 1) {
            i = a.getInt("dq-w", 0);
            i2 = a.getInt("wifi_used", 0);
        } else if (i == 0) {
            i = a.getInt("dq-3g", 0);
            i2 = a.getInt("data_used", 0);
        } else {
            i = 0;
        }
        return i - i2;
    }

    public static int a(Context context, int i, int i2) {
        int i3;
        int i4;
        SharedPreferences a = com.samsung.a.a.a.a.i.c.a(context);
        if (i == 1) {
            i = a.getInt("dq-w", 0);
            i3 = a.getInt("wifi_used", 0);
            i4 = a.getInt("oq-w", 0);
        } else if (i == 0) {
            i = a.getInt("dq-3g", 0);
            i3 = a.getInt("data_used", 0);
            i4 = a.getInt("oq-3g", 0);
        } else {
            i4 = 0;
            i = i4;
            i3 = i;
        }
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Quota : ");
        stringBuilder.append(i);
        stringBuilder.append("/ Uploaded : ");
        stringBuilder.append(i3);
        stringBuilder.append("/ limit : ");
        stringBuilder.append(i4);
        stringBuilder.append("/ size : ");
        stringBuilder.append(i2);
        a.a(stringBuilder.toString());
        if (i < i3 + i2) {
            a.a("DLS Sender", "send result fail : Over daily quota");
            return -1;
        } else if (i4 >= i2) {
            return 0;
        } else {
            a.a("DLS Sender", "send result fail : Over once quota");
            return -11;
        }
    }

    public static boolean a(Context context) {
        SharedPreferences a = com.samsung.a.a.a.a.i.c.a(context);
        if (d.a(1, Long.valueOf(a.getLong("quota_reset_date", 0)))) {
            a(a);
        }
        return d.a(a.getInt("rint", 1), Long.valueOf(a.getLong("policy_received_date", 0)));
    }

    public static void a(SharedPreferences sharedPreferences) {
        sharedPreferences.edit().putLong("quota_reset_date", System.currentTimeMillis()).putInt("data_used", 0).putInt("wifi_used", 0).apply();
    }

    public static Map<String, String> a(Context context, com.samsung.a.a.a.a.b.a aVar, b bVar) {
        Map<String, String> hashMap = new HashMap();
        hashMap.put("pkn", context.getPackageName());
        hashMap.put("dm", aVar.e());
        if (!TextUtils.isEmpty(aVar.a())) {
            hashMap.put("mcc", aVar.a());
        }
        if (!TextUtils.isEmpty(aVar.b())) {
            hashMap.put("mnc", aVar.b());
        }
        hashMap.put("uv", bVar.i());
        hashMap.put("sv", "2.01.005");
        return hashMap;
    }

    public static void a(Context context, b bVar, com.samsung.a.a.a.a.c.c cVar, com.samsung.a.a.a.a.b.a aVar, com.samsung.a.a.a.a.a aVar2) {
        cVar.a(a(context, bVar, aVar, aVar2));
    }

    public static void a(Context context, b bVar, com.samsung.a.a.a.a.c.c cVar, com.samsung.a.a.a.a.b.a aVar) {
        cVar.a(a(context, bVar, aVar, null));
    }

    public static a a(Context context, b bVar, com.samsung.a.a.a.a.b.a aVar, com.samsung.a.a.a.a.a aVar2) {
        a aVar3 = new a(com.samsung.a.a.a.a.a.a.GET_POLICY, bVar.a(), a(context, aVar, bVar), com.samsung.a.a.a.a.i.c.a(context), aVar2);
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("trid: ");
        stringBuilder.append(bVar.a().substring(0, 7));
        stringBuilder.append(", uv: ");
        stringBuilder.append(bVar.i());
        a.a(stringBuilder.toString());
        return aVar3;
    }

    public static void b(Context context, int i, int i2) {
        SharedPreferences a = com.samsung.a.a.a.a.i.c.a(context);
        if (i == 1) {
            a.edit().putInt("wifi_used", a.getInt("wifi_used", 0) + i2).apply();
        } else if (i == 0) {
            a.edit().putInt("data_used", com.samsung.a.a.a.a.i.c.a(context).getInt("data_used", 0) + i2).apply();
        }
    }

    public static boolean b(Context context) {
        try {
            PackageInfo packageInfo = context.getPackageManager().getPackageInfo("com.sec.android.diagmonagent", 0);
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("dma pkg:");
            stringBuilder.append(packageInfo.versionCode);
            a.a("Validation", stringBuilder.toString());
            a = packageInfo.versionCode >= 540000000;
        } catch (Exception e) {
            a = false;
            StringBuilder stringBuilder2 = new StringBuilder();
            stringBuilder2.append("DMA not found");
            stringBuilder2.append(e.getMessage());
            a.b(stringBuilder2.toString());
        }
        return a;
    }

    public static String a() {
        String str = null;
        try {
            str = (String) Class.forName("android.os.SystemProperties").getMethod("get", new Class[]{String.class}).invoke(null, new Object[]{"ro.csc.sales_code"});
        } catch (Exception unused) {
            return str;
        }
    }
}
