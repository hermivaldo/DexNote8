package com.samsung.p047a.p048a.p049a.p050a.p054d;

import android.content.SharedPreferences;
import com.samsung.p047a.p048a.p049a.p050a.C0754a;
import com.samsung.p047a.p048a.p049a.p050a.p051a.C0750a;
import com.samsung.p047a.p048a.p049a.p050a.p051a.C0751b;
import com.samsung.p047a.p048a.p049a.p050a.p051a.C0752c;
import com.samsung.p047a.p048a.p049a.p050a.p053c.C0759b;
import com.samsung.p047a.p048a.p049a.p050a.p063i.C0784a;
import java.util.Map;
import javax.net.ssl.HttpsURLConnection;
import org.json.JSONException;
import org.json.JSONObject;

/* compiled from: GetPolicyClient */
/* renamed from: com.samsung.a.a.a.a.d.a */
public class C1291a implements C0759b {
    /* renamed from: a */
    private String f4329a;
    /* renamed from: b */
    private Map<String, String> f4330b;
    /* renamed from: c */
    private C0750a f4331c;
    /* renamed from: d */
    private HttpsURLConnection f4332d = null;
    /* renamed from: e */
    private SharedPreferences f4333e;
    /* renamed from: f */
    private C0754a<Void, Boolean> f4334f;

    public C1291a(C0750a c0750a, String str, Map<String, String> map, SharedPreferences sharedPreferences, C0754a<Void, Boolean> c0754a) {
        this.f4329a = str;
        this.f4331c = c0750a;
        this.f4330b = map;
        this.f4333e = sharedPreferences;
        this.f4334f = c0754a;
    }

    /* renamed from: a */
    public void mo650a() {
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
	at jadx.core.ProcessClass.processDependencies(ProcessClass.java:56)
	at jadx.core.ProcessClass.process(ProcessClass.java:39)
	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:282)
	at jadx.api.JavaClass.decompile(JavaClass.java:62)
	at jadx.api.JadxDecompiler.lambda$appendSourcesSave$0(JadxDecompiler.java:200)
	at jadx.api.JadxDecompiler$$Lambda$8/1201484275.run(Unknown Source)
*/
        /*
        r6 = this;
        r0 = r6.f4331c;	 Catch:{ Exception -> 0x00b5 }
        r0 = r0.m3132a();	 Catch:{ Exception -> 0x00b5 }
        r0 = android.net.Uri.parse(r0);	 Catch:{ Exception -> 0x00b5 }
        r0 = r0.buildUpon();	 Catch:{ Exception -> 0x00b5 }
        r1 = r6.f4330b;	 Catch:{ Exception -> 0x00b5 }
        r1 = r1.keySet();	 Catch:{ Exception -> 0x00b5 }
        r1 = r1.iterator();	 Catch:{ Exception -> 0x00b5 }
    L_0x0018:
        r2 = r1.hasNext();	 Catch:{ Exception -> 0x00b5 }
        if (r2 == 0) goto L_0x0030;	 Catch:{ Exception -> 0x00b5 }
    L_0x001e:
        r2 = r1.next();	 Catch:{ Exception -> 0x00b5 }
        r2 = (java.lang.String) r2;	 Catch:{ Exception -> 0x00b5 }
        r3 = r6.f4330b;	 Catch:{ Exception -> 0x00b5 }
        r3 = r3.get(r2);	 Catch:{ Exception -> 0x00b5 }
        r3 = (java.lang.String) r3;	 Catch:{ Exception -> 0x00b5 }
        r0.appendQueryParameter(r2, r3);	 Catch:{ Exception -> 0x00b5 }
        goto L_0x0018;	 Catch:{ Exception -> 0x00b5 }
    L_0x0030:
        r1 = 2;	 Catch:{ Exception -> 0x00b5 }
        r2 = java.util.Locale.US;	 Catch:{ Exception -> 0x00b5 }
        r1 = java.text.SimpleDateFormat.getTimeInstance(r1, r2);	 Catch:{ Exception -> 0x00b5 }
        r2 = new java.util.Date;	 Catch:{ Exception -> 0x00b5 }
        r2.<init>();	 Catch:{ Exception -> 0x00b5 }
        r1 = r1.format(r2);	 Catch:{ Exception -> 0x00b5 }
        r2 = "ts";	 Catch:{ Exception -> 0x00b5 }
        r2 = r0.appendQueryParameter(r2, r1);	 Catch:{ Exception -> 0x00b5 }
        r3 = "tid";	 Catch:{ Exception -> 0x00b5 }
        r4 = r6.f4329a;	 Catch:{ Exception -> 0x00b5 }
        r2 = r2.appendQueryParameter(r3, r4);	 Catch:{ Exception -> 0x00b5 }
        r3 = "hc";	 Catch:{ Exception -> 0x00b5 }
        r4 = new java.lang.StringBuilder;	 Catch:{ Exception -> 0x00b5 }
        r4.<init>();	 Catch:{ Exception -> 0x00b5 }
        r5 = r6.f4329a;	 Catch:{ Exception -> 0x00b5 }
        r4.append(r5);	 Catch:{ Exception -> 0x00b5 }
        r4.append(r1);	 Catch:{ Exception -> 0x00b5 }
        r1 = com.samsung.p047a.p048a.p049a.p050a.p054d.C0766d.f2435a;	 Catch:{ Exception -> 0x00b5 }
        r4.append(r1);	 Catch:{ Exception -> 0x00b5 }
        r1 = r4.toString();	 Catch:{ Exception -> 0x00b5 }
        r1 = com.samsung.p047a.p048a.p049a.p050a.p054d.C0766d.m3184a(r1);	 Catch:{ Exception -> 0x00b5 }
        r2.appendQueryParameter(r3, r1);	 Catch:{ Exception -> 0x00b5 }
        r1 = com.samsung.p047a.p048a.p049a.p050a.p054d.C0764c.m3176a();	 Catch:{ Exception -> 0x00b5 }
        r2 = android.text.TextUtils.isEmpty(r1);	 Catch:{ Exception -> 0x00b5 }
        if (r2 != 0) goto L_0x007c;	 Catch:{ Exception -> 0x00b5 }
    L_0x0077:
        r2 = "csc";	 Catch:{ Exception -> 0x00b5 }
        r0.appendQueryParameter(r2, r1);	 Catch:{ Exception -> 0x00b5 }
    L_0x007c:
        r1 = new java.net.URL;	 Catch:{ Exception -> 0x00b5 }
        r0 = r0.build();	 Catch:{ Exception -> 0x00b5 }
        r0 = r0.toString();	 Catch:{ Exception -> 0x00b5 }
        r1.<init>(r0);	 Catch:{ Exception -> 0x00b5 }
        r0 = r1.openConnection();	 Catch:{ Exception -> 0x00b5 }
        r0 = (javax.net.ssl.HttpsURLConnection) r0;	 Catch:{ Exception -> 0x00b5 }
        r6.f4332d = r0;	 Catch:{ Exception -> 0x00b5 }
        r0 = r6.f4332d;	 Catch:{ Exception -> 0x00b5 }
        r1 = com.samsung.p047a.p048a.p049a.p050a.p055e.C0769a.m3190a();	 Catch:{ Exception -> 0x00b5 }
        r1 = r1.m3192b();	 Catch:{ Exception -> 0x00b5 }
        r1 = r1.getSocketFactory();	 Catch:{ Exception -> 0x00b5 }
        r0.setSSLSocketFactory(r1);	 Catch:{ Exception -> 0x00b5 }
        r0 = r6.f4332d;	 Catch:{ Exception -> 0x00b5 }
        r1 = r6.f4331c;	 Catch:{ Exception -> 0x00b5 }
        r1 = r1.m3133b();	 Catch:{ Exception -> 0x00b5 }
        r0.setRequestMethod(r1);	 Catch:{ Exception -> 0x00b5 }
        r6 = r6.f4332d;	 Catch:{ Exception -> 0x00b5 }
        r0 = 3000; // 0xbb8 float:4.204E-42 double:1.482E-320;	 Catch:{ Exception -> 0x00b5 }
        r6.setConnectTimeout(r0);	 Catch:{ Exception -> 0x00b5 }
        goto L_0x00ba;
    L_0x00b5:
        r6 = "Fail to get Policy";
        com.samsung.p047a.p048a.p049a.p050a.p063i.C0784a.m3256c(r6);
    L_0x00ba:
        return;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.samsung.a.a.a.a.d.a.a():void");
    }

    /* renamed from: b */
    public int mo651b() {
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
	at jadx.core.ProcessClass.processDependencies(ProcessClass.java:56)
	at jadx.core.ProcessClass.process(ProcessClass.java:39)
	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:282)
	at jadx.api.JavaClass.decompile(JavaClass.java:62)
	at jadx.api.JadxDecompiler.lambda$appendSourcesSave$0(JadxDecompiler.java:200)
	at jadx.api.JadxDecompiler$$Lambda$8/1201484275.run(Unknown Source)
*/
        /*
        r7 = this;
        r0 = -61;
        r1 = 0;
        r2 = r7.f4332d;	 Catch:{ Exception -> 0x00b2 }
        r2 = r2.getResponseCode();	 Catch:{ Exception -> 0x00b2 }
        r3 = 200; // 0xc8 float:2.8E-43 double:9.9E-322;	 Catch:{ Exception -> 0x00b2 }
        if (r2 == r3) goto L_0x0029;	 Catch:{ Exception -> 0x00b2 }
    L_0x000d:
        r2 = new java.lang.StringBuilder;	 Catch:{ Exception -> 0x00b2 }
        r2.<init>();	 Catch:{ Exception -> 0x00b2 }
        r3 = "Fail to get Policy. Response code : ";	 Catch:{ Exception -> 0x00b2 }
        r2.append(r3);	 Catch:{ Exception -> 0x00b2 }
        r3 = r7.f4332d;	 Catch:{ Exception -> 0x00b2 }
        r3 = r3.getResponseCode();	 Catch:{ Exception -> 0x00b2 }
        r2.append(r3);	 Catch:{ Exception -> 0x00b2 }
        r2 = r2.toString();	 Catch:{ Exception -> 0x00b2 }
        com.samsung.p047a.p048a.p049a.p050a.p063i.C0784a.m3256c(r2);	 Catch:{ Exception -> 0x00b2 }
        r2 = r0;	 Catch:{ Exception -> 0x00b2 }
        goto L_0x002a;	 Catch:{ Exception -> 0x00b2 }
    L_0x0029:
        r2 = 0;	 Catch:{ Exception -> 0x00b2 }
    L_0x002a:
        r3 = new java.io.BufferedReader;	 Catch:{ Exception -> 0x00b2 }
        r4 = new java.io.InputStreamReader;	 Catch:{ Exception -> 0x00b2 }
        r5 = r7.f4332d;	 Catch:{ Exception -> 0x00b2 }
        r5 = r5.getInputStream();	 Catch:{ Exception -> 0x00b2 }
        r4.<init>(r5);	 Catch:{ Exception -> 0x00b2 }
        r3.<init>(r4);	 Catch:{ Exception -> 0x00b2 }
        r1 = r3.readLine();	 Catch:{ Exception -> 0x00ad, all -> 0x00ab }
        com.samsung.p047a.p048a.p049a.p050a.p063i.C0784a.m3253a(r1);	 Catch:{ Exception -> 0x00ad, all -> 0x00ab }
        r4 = new org.json.JSONObject;	 Catch:{ Exception -> 0x00ad, all -> 0x00ab }
        r4.<init>(r1);	 Catch:{ Exception -> 0x00ad, all -> 0x00ab }
        r1 = "rc";	 Catch:{ Exception -> 0x00ad, all -> 0x00ab }
        r1 = r4.getInt(r1);	 Catch:{ Exception -> 0x00ad, all -> 0x00ab }
        r5 = 1000; // 0x3e8 float:1.401E-42 double:4.94E-321;	 Catch:{ Exception -> 0x00ad, all -> 0x00ab }
        if (r1 == r5) goto L_0x0066;	 Catch:{ Exception -> 0x00ad, all -> 0x00ab }
    L_0x0050:
        r2 = new java.lang.StringBuilder;	 Catch:{ Exception -> 0x00ad, all -> 0x00ab }
        r2.<init>();	 Catch:{ Exception -> 0x00ad, all -> 0x00ab }
        r4 = "Fail to get Policy; Invalid Message. Result code : ";	 Catch:{ Exception -> 0x00ad, all -> 0x00ab }
        r2.append(r4);	 Catch:{ Exception -> 0x00ad, all -> 0x00ab }
        r2.append(r1);	 Catch:{ Exception -> 0x00ad, all -> 0x00ab }
        r1 = r2.toString();	 Catch:{ Exception -> 0x00ad, all -> 0x00ab }
        com.samsung.p047a.p048a.p049a.p050a.p063i.C0784a.m3256c(r1);	 Catch:{ Exception -> 0x00ad, all -> 0x00ab }
        r2 = r0;	 Catch:{ Exception -> 0x00ad, all -> 0x00ab }
        goto L_0x009e;	 Catch:{ Exception -> 0x00ad, all -> 0x00ab }
    L_0x0066:
        r1 = "GetPolicyClient";	 Catch:{ Exception -> 0x00ad, all -> 0x00ab }
        r5 = "Get Policy Success";	 Catch:{ Exception -> 0x00ad, all -> 0x00ab }
        com.samsung.p047a.p048a.p049a.p050a.p063i.C0784a.m3254a(r1, r5);	 Catch:{ Exception -> 0x00ad, all -> 0x00ab }
        r1 = r7.f4333e;	 Catch:{ Exception -> 0x00ad, all -> 0x00ab }
        r5 = "lgt";	 Catch:{ Exception -> 0x00ad, all -> 0x00ab }
        r6 = "";	 Catch:{ Exception -> 0x00ad, all -> 0x00ab }
        r1 = r1.getString(r5, r6);	 Catch:{ Exception -> 0x00ad, all -> 0x00ab }
        r1 = android.text.TextUtils.isEmpty(r1);	 Catch:{ Exception -> 0x00ad, all -> 0x00ab }
        if (r1 == 0) goto L_0x009b;	 Catch:{ Exception -> 0x00ad, all -> 0x00ab }
    L_0x007d:
        r1 = r7.f4334f;	 Catch:{ Exception -> 0x00ad, all -> 0x00ab }
        if (r1 == 0) goto L_0x009b;	 Catch:{ Exception -> 0x00ad, all -> 0x00ab }
    L_0x0081:
        r1 = "lgt";	 Catch:{ Exception -> 0x00ad, all -> 0x00ab }
        r1 = r4.getString(r1);	 Catch:{ Exception -> 0x00ad, all -> 0x00ab }
        if (r1 == 0) goto L_0x009b;	 Catch:{ Exception -> 0x00ad, all -> 0x00ab }
    L_0x0089:
        r5 = "rtb";	 Catch:{ Exception -> 0x00ad, all -> 0x00ab }
        r1 = r1.equals(r5);	 Catch:{ Exception -> 0x00ad, all -> 0x00ab }
        if (r1 == 0) goto L_0x009b;	 Catch:{ Exception -> 0x00ad, all -> 0x00ab }
    L_0x0091:
        r1 = r7.f4334f;	 Catch:{ Exception -> 0x00ad, all -> 0x00ab }
        r5 = 1;	 Catch:{ Exception -> 0x00ad, all -> 0x00ab }
        r5 = java.lang.Boolean.valueOf(r5);	 Catch:{ Exception -> 0x00ad, all -> 0x00ab }
        r1.mo646a(r5);	 Catch:{ Exception -> 0x00ad, all -> 0x00ab }
    L_0x009b:
        r7.m6015a(r4);	 Catch:{ Exception -> 0x00ad, all -> 0x00ab }
    L_0x009e:
        r1 = r7.f4332d;	 Catch:{ Exception -> 0x00ad, all -> 0x00ab }
        if (r1 == 0) goto L_0x00a7;	 Catch:{ Exception -> 0x00ad, all -> 0x00ab }
    L_0x00a2:
        r1 = r7.f4332d;	 Catch:{ Exception -> 0x00ad, all -> 0x00ab }
        r1.disconnect();	 Catch:{ Exception -> 0x00ad, all -> 0x00ab }
    L_0x00a7:
        r7.m6013a(r3);
        goto L_0x00bb;
    L_0x00ab:
        r0 = move-exception;
        goto L_0x00e1;
    L_0x00ad:
        r1 = r3;
        goto L_0x00b2;
    L_0x00af:
        r0 = move-exception;
        r3 = r1;
        goto L_0x00e1;
    L_0x00b2:
        r2 = "Fail to get Policy";	 Catch:{ all -> 0x00af }
        com.samsung.p047a.p048a.p049a.p050a.p063i.C0784a.m3256c(r2);	 Catch:{ all -> 0x00af }
        r7.m6013a(r1);
        r2 = r0;
    L_0x00bb:
        r1 = r7.f4333e;
        r3 = "dom";
        r4 = "";
        r1 = r1.getString(r3, r4);
        r1 = android.text.TextUtils.isEmpty(r1);
        if (r2 != r0) goto L_0x00e0;
    L_0x00cb:
        if (r1 != 0) goto L_0x00e0;
    L_0x00cd:
        r7 = r7.f4333e;
        r7 = r7.edit();
        r0 = "policy_received_date";
        r3 = java.lang.System.currentTimeMillis();
        r7 = r7.putLong(r0, r3);
        r7.apply();
    L_0x00e0:
        return r2;
    L_0x00e1:
        r7.m6013a(r3);
        throw r0;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.samsung.a.a.a.a.d.a.b():int");
    }

    /* renamed from: a */
    private void m6013a(java.io.BufferedReader r1) {
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
	at jadx.core.ProcessClass.processDependencies(ProcessClass.java:56)
	at jadx.core.ProcessClass.process(ProcessClass.java:39)
	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:282)
	at jadx.api.JavaClass.decompile(JavaClass.java:62)
	at jadx.api.JadxDecompiler.lambda$appendSourcesSave$0(JadxDecompiler.java:200)
	at jadx.api.JadxDecompiler$$Lambda$8/1201484275.run(Unknown Source)
*/
        /*
        r0 = this;
        if (r1 == 0) goto L_0x0005;
    L_0x0002:
        r1.close();	 Catch:{ IOException -> 0x000e }
    L_0x0005:
        r1 = r0.f4332d;	 Catch:{ IOException -> 0x000e }
        if (r1 == 0) goto L_0x000e;	 Catch:{ IOException -> 0x000e }
    L_0x0009:
        r0 = r0.f4332d;	 Catch:{ IOException -> 0x000e }
        r0.disconnect();	 Catch:{ IOException -> 0x000e }
    L_0x000e:
        return;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.samsung.a.a.a.a.d.a.a(java.io.BufferedReader):void");
    }

    /* renamed from: a */
    public void m6015a(JSONObject jSONObject) {
        try {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("https://");
            stringBuilder.append(jSONObject.getString("dom"));
            this.f4333e.edit().putInt("oq-3g", jSONObject.getInt("oq-3g") * 1024).putInt("dq-3g", jSONObject.getInt("dq-3g") * 1024).putInt("oq-w", jSONObject.getInt("oq-w") * 1024).putInt("dq-w", jSONObject.getInt("dq-w") * 1024).putString("dom", stringBuilder.toString()).putString("uri", jSONObject.getString("uri")).putString("bat-uri", jSONObject.getString("bat-uri")).putString("lgt", jSONObject.getString("lgt")).putInt("rint", jSONObject.getInt("rint")).putLong("policy_received_date", System.currentTimeMillis()).apply();
            C0752c c0752c = C0752c.f2399c;
            StringBuilder stringBuilder2 = new StringBuilder();
            stringBuilder2.append("https://");
            stringBuilder2.append(jSONObject.getString("dom"));
            c0752c.m3137a(stringBuilder2.toString());
            C0751b.DLS_DIR.m3135a(jSONObject.getString("uri"));
            C0751b.DLS_DIR_BAT.m3135a(jSONObject.getString("bat-uri"));
            StringBuilder stringBuilder3 = new StringBuilder();
            stringBuilder3.append("dq-3g: ");
            stringBuilder3.append(jSONObject.getInt("dq-3g") * 1024);
            stringBuilder3.append(", dq-w: ");
            stringBuilder3.append(jSONObject.getInt("dq-w") * 1024);
            stringBuilder3.append(", oq-3g: ");
            stringBuilder3.append(jSONObject.getInt("oq-3g") * 1024);
            stringBuilder3.append(", oq-w: ");
            stringBuilder3.append(jSONObject.getInt("oq-w") * 1024);
            C0784a.m3253a(stringBuilder3.toString());
        } catch (JSONException e) {
            C0784a.m3256c("Fail to get Policy");
            jSONObject = new StringBuilder();
            jSONObject.append("[GetPolicyClient] ");
            jSONObject.append(e.getMessage());
            C0784a.m3253a(jSONObject.toString());
        }
    }
}
