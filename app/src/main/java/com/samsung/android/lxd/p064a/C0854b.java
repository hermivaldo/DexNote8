package com.samsung.android.lxd.p064a;

import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Build.VERSION;
import android.os.SemSystemProperties;
import com.samsung.android.lxd.processor.utils.log.Log;
import java.util.Locale;

/* compiled from: ContactUsHelper */
/* renamed from: com.samsung.android.lxd.a.b */
public class C0854b {
    /* renamed from: a */
    private static final String f2664a = "b";

    /* renamed from: a */
    public static void m3357a(android.app.Activity r3) {
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
        r0 = "com.samsung.android.voc";
        r0 = com.samsung.android.lxd.p064a.C0877o.m3494c(r3, r0);
        if (r0 == 0) goto L_0x005e;
    L_0x0008:
        r0 = r3.getPackageManager();
        if (r0 == 0) goto L_0x005e;
    L_0x000e:
        r0 = "com.samsung.android.voc";
        r1 = 170001000; // 0xa220268 float:7.800469E-33 double:8.3991654E-316;
        r0 = com.samsung.android.lxd.p064a.C0877o.m3475a(r3, r0, r1);
        if (r0 == 0) goto L_0x005e;
    L_0x0019:
        r0 = new android.content.Intent;
        r1 = "android.intent.action.VIEW";
        r2 = "voc://view/contactUs";
        r2 = android.net.Uri.parse(r2);
        r0.<init>(r1, r2);
        r1 = "packageName";
        r2 = r3.getPackageName();
        r0.putExtra(r1, r2);
        r1 = "appName";
        r2 = 2131623981; // 0x7f0e002d float:1.8875129E38 double:1.053162179E-314;
        r0.putExtra(r1, r2);
        r1 = "appId";
        r2 = "7g14ay655z";
        r0.putExtra(r1, r2);
        r1 = "faqUrl";
        r2 = "/faq/searchFaq.do";
        r2 = com.samsung.android.lxd.p064a.C0854b.m3358b(r2);
        r0.putExtra(r1, r2);
        r1 = r3.getPackageManager();	 Catch:{ ActivityNotFoundException -> 0x0057 }
        r1 = r0.resolveActivity(r1);	 Catch:{ ActivityNotFoundException -> 0x0057 }
        if (r1 == 0) goto L_0x005e;	 Catch:{ ActivityNotFoundException -> 0x0057 }
    L_0x0053:
        r3.startActivity(r0);	 Catch:{ ActivityNotFoundException -> 0x0057 }
        return;
    L_0x0057:
        r0 = f2664a;
        r1 = "do not find samsung members package";
        com.samsung.android.lxd.processor.utils.log.Log.m3855e(r0, r1);
    L_0x005e:
        r0 = new android.content.Intent;
        r1 = com.samsung.android.lxd.ContactUsActivity.class;
        r0.<init>(r3, r1);
        r3.startActivity(r0);
        return;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.samsung.android.lxd.a.b.a(android.app.Activity):void");
    }

    /* renamed from: a */
    public static Intent m3356a(String str) {
        str = C0854b.m3358b(str);
        String str2 = f2664a;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("getMuseIntent :: ");
        stringBuilder.append(str);
        Log.m3853d(str2, stringBuilder.toString());
        return new Intent("android.intent.action.VIEW", Uri.parse(str));
    }

    /* renamed from: b */
    public static String m3358b(String str) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("http://help.content.samsung.com:8080/csweb/auth/gosupport.do?");
        stringBuilder.append(C0854b.m3359c(str));
        return stringBuilder.toString();
    }

    /* renamed from: c */
    public static String m3359c(String str) {
        String str2 = "US";
        if ("KR".equals(SemSystemProperties.get("ro.csc.countryiso_code"))) {
            str2 = "KR";
        }
        String str3 = "en_us";
        if ("ko".equals(Locale.getDefault().getDisplayLanguage())) {
            str3 = "ko";
        }
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("serviceCd=lodex&targetUrl=");
        stringBuilder.append(str);
        stringBuilder.append("&");
        stringBuilder.append("chnlCd");
        stringBuilder.append("=");
        stringBuilder.append("ODC");
        stringBuilder.append("&");
        stringBuilder.append("_common_country");
        stringBuilder.append("=");
        stringBuilder.append(str2);
        stringBuilder.append("&");
        stringBuilder.append("_common_lang");
        stringBuilder.append("=");
        stringBuilder.append(str3);
        stringBuilder.append("&");
        stringBuilder.append("dvcModelCd");
        stringBuilder.append("=");
        stringBuilder.append(Build.MODEL);
        stringBuilder.append("&");
        stringBuilder.append("odcVersion");
        stringBuilder.append("=");
        stringBuilder.append(C0877o.m3536u());
        stringBuilder.append("&");
        stringBuilder.append("mcc");
        stringBuilder.append("=");
        stringBuilder.append(C0877o.m3450J());
        stringBuilder.append("&");
        stringBuilder.append("mnc");
        stringBuilder.append("=");
        stringBuilder.append(C0877o.m3451K());
        stringBuilder.append("&");
        stringBuilder.append("dvcOSVersion");
        stringBuilder.append("=");
        stringBuilder.append(VERSION.SDK_INT);
        stringBuilder.append("&");
        stringBuilder.append("saccountID");
        stringBuilder.append("=");
        stringBuilder.append(C0877o.m3519j("com.osp.app.signin"));
        return stringBuilder.toString();
    }
}
