package com.samsung.p047a.p048a.p049a.p050a.p054d;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;
import com.samsung.p047a.p048a.p049a.C0790b;
import com.samsung.p047a.p048a.p049a.p050a.C0754a;
import com.samsung.p047a.p048a.p049a.p050a.p051a.C0750a;
import com.samsung.p047a.p048a.p049a.p050a.p052b.C0756a;
import com.samsung.p047a.p048a.p049a.p050a.p053c.C0760c;
import com.samsung.p047a.p048a.p049a.p050a.p063i.C0784a;
import com.samsung.p047a.p048a.p049a.p050a.p063i.C0787c;
import com.samsung.p047a.p048a.p049a.p050a.p063i.C0788d;
import java.util.HashMap;
import java.util.Map;

/* compiled from: PolicyUtils */
/* renamed from: com.samsung.a.a.a.a.d.c */
public class C0764c {
    /* renamed from: a */
    static boolean f2432a = false;

    /* renamed from: a */
    public static int m3173a(Context context, int i) {
        context = C0787c.m3260a(context);
        int i2 = 0;
        if (i == 1) {
            i = context.getInt("dq-w", 0);
            i2 = context.getInt("wifi_used", 0);
        } else if (i == 0) {
            i = context.getInt("dq-3g", 0);
            i2 = context.getInt("data_used", 0);
        } else {
            i = 0;
        }
        return i - i2;
    }

    /* renamed from: a */
    public static int m3174a(Context context, int i, int i2) {
        int i3;
        context = C0787c.m3260a(context);
        if (i == 1) {
            i = context.getInt("dq-w", 0);
            i3 = context.getInt("wifi_used", 0);
            context = context.getInt("oq-w", 0);
        } else if (i == 0) {
            i = context.getInt("dq-3g", 0);
            i3 = context.getInt("data_used", 0);
            context = context.getInt("oq-3g", 0);
        } else {
            context = null;
            i = context;
            i3 = i;
        }
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Quota : ");
        stringBuilder.append(i);
        stringBuilder.append("/ Uploaded : ");
        stringBuilder.append(i3);
        stringBuilder.append("/ limit : ");
        stringBuilder.append(context);
        stringBuilder.append("/ size : ");
        stringBuilder.append(i2);
        C0784a.m3253a(stringBuilder.toString());
        if (i < i3 + i2) {
            C0784a.m3254a("DLS Sender", "send result fail : Over daily quota");
            return -1;
        } else if (context >= i2) {
            return 0;
        } else {
            C0784a.m3254a("DLS Sender", "send result fail : Over once quota");
            return -11;
        }
    }

    /* renamed from: a */
    public static boolean m3181a(Context context) {
        SharedPreferences a = C0787c.m3260a(context);
        if (C0788d.m3264a(1, Long.valueOf(a.getLong("quota_reset_date", 0)))) {
            C0764c.m3180a(a);
        }
        return C0788d.m3264a(a.getInt("rint", 1), Long.valueOf(a.getLong("policy_received_date", 0)));
    }

    /* renamed from: a */
    public static void m3180a(SharedPreferences sharedPreferences) {
        sharedPreferences.edit().putLong("quota_reset_date", System.currentTimeMillis()).putInt("data_used", 0).putInt("wifi_used", 0).apply();
    }

    /* renamed from: a */
    public static Map<String, String> m3177a(Context context, C0756a c0756a, C0790b c0790b) {
        Map<String, String> hashMap = new HashMap();
        hashMap.put("pkn", context.getPackageName());
        hashMap.put("dm", c0756a.m3144e());
        if (TextUtils.isEmpty(c0756a.m3140a()) == null) {
            hashMap.put("mcc", c0756a.m3140a());
        }
        if (TextUtils.isEmpty(c0756a.m3141b()) == null) {
            hashMap.put("mnc", c0756a.m3141b());
        }
        hashMap.put("uv", c0790b.m3280i());
        hashMap.put("sv", "2.01.005");
        return hashMap;
    }

    /* renamed from: a */
    public static void m3179a(Context context, C0790b c0790b, C0760c c0760c, C0756a c0756a, C0754a c0754a) {
        c0760c.mo649a(C0764c.m3175a(context, c0790b, c0756a, c0754a));
    }

    /* renamed from: a */
    public static void m3178a(Context context, C0790b c0790b, C0760c c0760c, C0756a c0756a) {
        c0760c.mo649a(C0764c.m3175a(context, c0790b, c0756a, null));
    }

    /* renamed from: a */
    public static C1291a m3175a(Context context, C0790b c0790b, C0756a c0756a, C0754a c0754a) {
        C1291a c1291a = new C1291a(C0750a.GET_POLICY, c0790b.m3269a(), C0764c.m3177a(context, c0756a, c0790b), C0787c.m3260a(context), c0754a);
        context = new StringBuilder();
        context.append("trid: ");
        context.append(c0790b.m3269a().substring(null, 7));
        context.append(", uv: ");
        context.append(c0790b.m3280i());
        C0784a.m3253a(context.toString());
        return c1291a;
    }

    /* renamed from: b */
    public static void m3182b(Context context, int i, int i2) {
        SharedPreferences a = C0787c.m3260a(context);
        if (i == 1) {
            a.edit().putInt("wifi_used", a.getInt("wifi_used", 0) + i2).apply();
        } else if (i == 0) {
            a.edit().putInt("data_used", C0787c.m3260a(context).getInt("data_used", 0) + i2).apply();
        }
    }

    /* renamed from: b */
    public static boolean m3183b(Context context) {
        try {
            context = context.getPackageManager().getPackageInfo("com.sec.android.diagmonagent", 0);
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("dma pkg:");
            stringBuilder.append(context.versionCode);
            C0784a.m3254a("Validation", stringBuilder.toString());
            f2432a = context.versionCode >= 540000000 ? true : null;
        } catch (Context context2) {
            f2432a = false;
            StringBuilder stringBuilder2 = new StringBuilder();
            stringBuilder2.append("DMA not found");
            stringBuilder2.append(context2.getMessage());
            C0784a.m3255b(stringBuilder2.toString());
        }
        return f2432a;
    }

    /* renamed from: a */
    public static java.lang.String m3176a() {
        /* JADX: method processing error */
/*
Error: java.lang.NullPointerException
	at jadx.core.dex.visitors.regions.ProcessTryCatchRegions.searchTryCatchDominators(ProcessTryCatchRegions.java:75)
	at jadx.core.dex.visitors.regions.ProcessTryCatchRegions.process(ProcessTryCatchRegions.java:45)
	at jadx.core.dex.visitors.regions.RegionMakerVisitor.postProcessRegions(RegionMakerVisitor.java:63)
	at jadx.core.dex.visitors.regions.RegionMakerVisitor.visit(RegionMakerVisitor.java:58)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:31)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:17)
	at jadx.core.ProcessClass.process(ProcessClass.java:34)
	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:282)
	at jadx.api.JavaClass.decompile(JavaClass.java:62)
	at jadx.api.JadxDecompiler.lambda$appendSourcesSave$0(JadxDecompiler.java:200)
	at jadx.api.JadxDecompiler$$Lambda$8/1201484275.run(Unknown Source)
*/
        /*
        r0 = 0;
        r1 = "android.os.SystemProperties";	 Catch:{ Exception -> 0x0022 }
        r1 = java.lang.Class.forName(r1);	 Catch:{ Exception -> 0x0022 }
        r2 = "get";	 Catch:{ Exception -> 0x0022 }
        r3 = 1;	 Catch:{ Exception -> 0x0022 }
        r4 = new java.lang.Class[r3];	 Catch:{ Exception -> 0x0022 }
        r5 = java.lang.String.class;	 Catch:{ Exception -> 0x0022 }
        r6 = 0;	 Catch:{ Exception -> 0x0022 }
        r4[r6] = r5;	 Catch:{ Exception -> 0x0022 }
        r1 = r1.getMethod(r2, r4);	 Catch:{ Exception -> 0x0022 }
        r2 = new java.lang.Object[r3];	 Catch:{ Exception -> 0x0022 }
        r3 = "ro.csc.sales_code";	 Catch:{ Exception -> 0x0022 }
        r2[r6] = r3;	 Catch:{ Exception -> 0x0022 }
        r1 = r1.invoke(r0, r2);	 Catch:{ Exception -> 0x0022 }
        r1 = (java.lang.String) r1;	 Catch:{ Exception -> 0x0022 }
        r0 = r1;
    L_0x0022:
        return r0;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.samsung.a.a.a.a.d.c.a():java.lang.String");
    }
}
