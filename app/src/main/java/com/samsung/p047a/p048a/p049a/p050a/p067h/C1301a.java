package com.samsung.p047a.p048a.p049a.p050a.p067h;

import com.samsung.p047a.p048a.p049a.p050a.p051a.C0750a;
import com.samsung.p047a.p048a.p049a.p050a.p053c.C0758a;
import com.samsung.p047a.p048a.p049a.p050a.p053c.C0759b;
import javax.net.ssl.HttpsURLConnection;

/* compiled from: RegisterTask */
/* renamed from: com.samsung.a.a.a.a.h.a */
public class C1301a implements C0759b {
    /* renamed from: a */
    private final C0750a f4367a = C0750a.DATA_DELETE;
    /* renamed from: b */
    private HttpsURLConnection f4368b = null;
    /* renamed from: c */
    private String f4369c = "";
    /* renamed from: d */
    private String f4370d = "";
    /* renamed from: e */
    private long f4371e;
    /* renamed from: f */
    private C0758a f4372f;

    public C1301a(String str, String str2, long j, C0758a c0758a) {
        this.f4369c = str;
        this.f4370d = str2;
        this.f4371e = j;
        this.f4372f = c0758a;
    }

    /* renamed from: c */
    private java.lang.String m6041c() {
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
        r4 = this;
        r0 = new org.json.JSONObject;
        r0.<init>();
        r1 = "tid";	 Catch:{ JSONException -> 0x001a }
        r2 = r4.f4369c;	 Catch:{ JSONException -> 0x001a }
        r0.put(r1, r2);	 Catch:{ JSONException -> 0x001a }
        r1 = "lid";	 Catch:{ JSONException -> 0x001a }
        r2 = r4.f4370d;	 Catch:{ JSONException -> 0x001a }
        r0.put(r1, r2);	 Catch:{ JSONException -> 0x001a }
        r1 = "ts";	 Catch:{ JSONException -> 0x001a }
        r2 = r4.f4371e;	 Catch:{ JSONException -> 0x001a }
        r0.put(r1, r2);	 Catch:{ JSONException -> 0x001a }
    L_0x001a:
        r4 = r0.toString();
        return r4;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.samsung.a.a.a.a.h.a.c():java.lang.String");
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
	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:282)
	at jadx.api.JavaClass.decompile(JavaClass.java:62)
	at jadx.api.JadxDecompiler.lambda$appendSourcesSave$0(JadxDecompiler.java:200)
	at jadx.api.JadxDecompiler$$Lambda$8/1201484275.run(Unknown Source)
*/
        /*
        r5 = this;
        r0 = r5.f4367a;	 Catch:{ Exception -> 0x00a5 }
        r0 = r0.m3132a();	 Catch:{ Exception -> 0x00a5 }
        r0 = android.net.Uri.parse(r0);	 Catch:{ Exception -> 0x00a5 }
        r0 = r0.buildUpon();	 Catch:{ Exception -> 0x00a5 }
        r1 = 2;	 Catch:{ Exception -> 0x00a5 }
        r1 = java.text.SimpleDateFormat.getTimeInstance(r1);	 Catch:{ Exception -> 0x00a5 }
        r2 = new java.util.Date;	 Catch:{ Exception -> 0x00a5 }
        r2.<init>();	 Catch:{ Exception -> 0x00a5 }
        r1 = r1.format(r2);	 Catch:{ Exception -> 0x00a5 }
        r2 = "ts";	 Catch:{ Exception -> 0x00a5 }
        r2 = r0.appendQueryParameter(r2, r1);	 Catch:{ Exception -> 0x00a5 }
        r3 = "hc";	 Catch:{ Exception -> 0x00a5 }
        r4 = new java.lang.StringBuilder;	 Catch:{ Exception -> 0x00a5 }
        r4.<init>();	 Catch:{ Exception -> 0x00a5 }
        r4.append(r1);	 Catch:{ Exception -> 0x00a5 }
        r1 = com.samsung.p047a.p048a.p049a.p050a.p054d.C0766d.f2435a;	 Catch:{ Exception -> 0x00a5 }
        r4.append(r1);	 Catch:{ Exception -> 0x00a5 }
        r1 = r4.toString();	 Catch:{ Exception -> 0x00a5 }
        r1 = com.samsung.p047a.p048a.p049a.p050a.p054d.C0766d.m3184a(r1);	 Catch:{ Exception -> 0x00a5 }
        r2.appendQueryParameter(r3, r1);	 Catch:{ Exception -> 0x00a5 }
        r1 = new java.net.URL;	 Catch:{ Exception -> 0x00a5 }
        r0 = r0.build();	 Catch:{ Exception -> 0x00a5 }
        r0 = r0.toString();	 Catch:{ Exception -> 0x00a5 }
        r1.<init>(r0);	 Catch:{ Exception -> 0x00a5 }
        r0 = r1.openConnection();	 Catch:{ Exception -> 0x00a5 }
        r0 = (javax.net.ssl.HttpsURLConnection) r0;	 Catch:{ Exception -> 0x00a5 }
        r5.f4368b = r0;	 Catch:{ Exception -> 0x00a5 }
        r0 = r5.f4368b;	 Catch:{ Exception -> 0x00a5 }
        r1 = com.samsung.p047a.p048a.p049a.p050a.p055e.C0769a.m3190a();	 Catch:{ Exception -> 0x00a5 }
        r1 = r1.m3192b();	 Catch:{ Exception -> 0x00a5 }
        r1 = r1.getSocketFactory();	 Catch:{ Exception -> 0x00a5 }
        r0.setSSLSocketFactory(r1);	 Catch:{ Exception -> 0x00a5 }
        r0 = r5.f4368b;	 Catch:{ Exception -> 0x00a5 }
        r1 = r5.f4367a;	 Catch:{ Exception -> 0x00a5 }
        r1 = r1.m3133b();	 Catch:{ Exception -> 0x00a5 }
        r0.setRequestMethod(r1);	 Catch:{ Exception -> 0x00a5 }
        r0 = r5.f4368b;	 Catch:{ Exception -> 0x00a5 }
        r1 = 3000; // 0xbb8 float:4.204E-42 double:1.482E-320;	 Catch:{ Exception -> 0x00a5 }
        r0.setConnectTimeout(r1);	 Catch:{ Exception -> 0x00a5 }
        r0 = r5.f4368b;	 Catch:{ Exception -> 0x00a5 }
        r1 = "Content-Type";	 Catch:{ Exception -> 0x00a5 }
        r2 = "application/json";	 Catch:{ Exception -> 0x00a5 }
        r0.setRequestProperty(r1, r2);	 Catch:{ Exception -> 0x00a5 }
        r0 = r5.m6041c();	 Catch:{ Exception -> 0x00a5 }
        r1 = android.text.TextUtils.isEmpty(r0);	 Catch:{ Exception -> 0x00a5 }
        if (r1 != 0) goto L_0x00a5;	 Catch:{ Exception -> 0x00a5 }
    L_0x0087:
        r1 = r5.f4368b;	 Catch:{ Exception -> 0x00a5 }
        r2 = 1;	 Catch:{ Exception -> 0x00a5 }
        r1.setDoOutput(r2);	 Catch:{ Exception -> 0x00a5 }
        r1 = new java.io.BufferedOutputStream;	 Catch:{ Exception -> 0x00a5 }
        r5 = r5.f4368b;	 Catch:{ Exception -> 0x00a5 }
        r5 = r5.getOutputStream();	 Catch:{ Exception -> 0x00a5 }
        r1.<init>(r5);	 Catch:{ Exception -> 0x00a5 }
        r5 = r0.getBytes();	 Catch:{ Exception -> 0x00a5 }
        r1.write(r5);	 Catch:{ Exception -> 0x00a5 }
        r1.flush();	 Catch:{ Exception -> 0x00a5 }
        r1.close();	 Catch:{ Exception -> 0x00a5 }
    L_0x00a5:
        return;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.samsung.a.a.a.a.h.a.a():void");
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
	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:282)
	at jadx.api.JavaClass.decompile(JavaClass.java:62)
	at jadx.api.JadxDecompiler.lambda$appendSourcesSave$0(JadxDecompiler.java:200)
	at jadx.api.JadxDecompiler$$Lambda$8/1201484275.run(Unknown Source)
*/
        /*
        r6 = this;
        r0 = 0;
        r1 = 0;
        r2 = r6.f4368b;	 Catch:{ Exception -> 0x0089 }
        r2 = r2.getResponseCode();	 Catch:{ Exception -> 0x0089 }
        r3 = 400; // 0x190 float:5.6E-43 double:1.976E-321;	 Catch:{ Exception -> 0x0089 }
        if (r2 < r3) goto L_0x001e;	 Catch:{ Exception -> 0x0089 }
    L_0x000c:
        r3 = new java.io.BufferedReader;	 Catch:{ Exception -> 0x0089 }
        r4 = new java.io.InputStreamReader;	 Catch:{ Exception -> 0x0089 }
        r5 = r6.f4368b;	 Catch:{ Exception -> 0x0089 }
        r5 = r5.getErrorStream();	 Catch:{ Exception -> 0x0089 }
        r4.<init>(r5);	 Catch:{ Exception -> 0x0089 }
        r3.<init>(r4);	 Catch:{ Exception -> 0x0089 }
    L_0x001c:
        r1 = r3;	 Catch:{ Exception -> 0x0089 }
        goto L_0x002f;	 Catch:{ Exception -> 0x0089 }
    L_0x001e:
        r3 = new java.io.BufferedReader;	 Catch:{ Exception -> 0x0089 }
        r4 = new java.io.InputStreamReader;	 Catch:{ Exception -> 0x0089 }
        r5 = r6.f4368b;	 Catch:{ Exception -> 0x0089 }
        r5 = r5.getInputStream();	 Catch:{ Exception -> 0x0089 }
        r4.<init>(r5);	 Catch:{ Exception -> 0x0089 }
        r3.<init>(r4);	 Catch:{ Exception -> 0x0089 }
        goto L_0x001c;	 Catch:{ Exception -> 0x0089 }
    L_0x002f:
        r3 = new org.json.JSONObject;	 Catch:{ Exception -> 0x0089 }
        r4 = r1.readLine();	 Catch:{ Exception -> 0x0089 }
        r3.<init>(r4);	 Catch:{ Exception -> 0x0089 }
        r4 = "rc";	 Catch:{ Exception -> 0x0089 }
        r3 = r3.getString(r4);	 Catch:{ Exception -> 0x0089 }
        r4 = 200; // 0xc8 float:2.8E-43 double:9.9E-322;	 Catch:{ Exception -> 0x0089 }
        if (r2 != r4) goto L_0x0067;	 Catch:{ Exception -> 0x0089 }
    L_0x0042:
        r4 = "1000";	 Catch:{ Exception -> 0x0089 }
        r4 = r3.equalsIgnoreCase(r4);	 Catch:{ Exception -> 0x0089 }
        if (r4 == 0) goto L_0x0067;	 Catch:{ Exception -> 0x0089 }
    L_0x004a:
        r4 = new java.lang.StringBuilder;	 Catch:{ Exception -> 0x0089 }
        r4.<init>();	 Catch:{ Exception -> 0x0089 }
        r5 = "Success : ";	 Catch:{ Exception -> 0x0089 }
        r4.append(r5);	 Catch:{ Exception -> 0x0089 }
        r4.append(r2);	 Catch:{ Exception -> 0x0089 }
        r5 = " ";	 Catch:{ Exception -> 0x0089 }
        r4.append(r5);	 Catch:{ Exception -> 0x0089 }
        r4.append(r3);	 Catch:{ Exception -> 0x0089 }
        r4 = r4.toString();	 Catch:{ Exception -> 0x0089 }
        com.samsung.p047a.p048a.p049a.p050a.p063i.C0784a.m3253a(r4);	 Catch:{ Exception -> 0x0089 }
        goto L_0x0083;	 Catch:{ Exception -> 0x0089 }
    L_0x0067:
        r4 = new java.lang.StringBuilder;	 Catch:{ Exception -> 0x0089 }
        r4.<init>();	 Catch:{ Exception -> 0x0089 }
        r5 = "Fail : ";	 Catch:{ Exception -> 0x0089 }
        r4.append(r5);	 Catch:{ Exception -> 0x0089 }
        r4.append(r2);	 Catch:{ Exception -> 0x0089 }
        r5 = " ";	 Catch:{ Exception -> 0x0089 }
        r4.append(r5);	 Catch:{ Exception -> 0x0089 }
        r4.append(r3);	 Catch:{ Exception -> 0x0089 }
        r4 = r4.toString();	 Catch:{ Exception -> 0x0089 }
        com.samsung.p047a.p048a.p049a.p050a.p063i.C0784a.m3253a(r4);	 Catch:{ Exception -> 0x0089 }
    L_0x0083:
        r6.m6039a(r2, r3);	 Catch:{ Exception -> 0x0089 }
        goto L_0x008e;
    L_0x0087:
        r0 = move-exception;
        goto L_0x0092;
    L_0x0089:
        r2 = "";	 Catch:{ all -> 0x0087 }
        r6.m6039a(r0, r2);	 Catch:{ all -> 0x0087 }
    L_0x008e:
        r6.m6040a(r1);
        return r0;
    L_0x0092:
        r6.m6040a(r1);
        throw r0;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.samsung.a.a.a.a.h.a.b():int");
    }

    /* renamed from: a */
    private void m6039a(int i, String str) {
        if (this.f4372f != null) {
            if (i == 200 && str.equalsIgnoreCase("1000")) {
                this.f4372f.mo647a(0, "", "", "");
            } else {
                this.f4372f.mo648b(i, str, "", "");
            }
        }
    }

    /* renamed from: a */
    private void m6040a(java.io.BufferedReader r1) {
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
        r0 = this;
        if (r1 == 0) goto L_0x0005;
    L_0x0002:
        r1.close();	 Catch:{ IOException -> 0x000e }
    L_0x0005:
        r1 = r0.f4368b;	 Catch:{ IOException -> 0x000e }
        if (r1 == 0) goto L_0x000e;	 Catch:{ IOException -> 0x000e }
    L_0x0009:
        r0 = r0.f4368b;	 Catch:{ IOException -> 0x000e }
        r0.disconnect();	 Catch:{ IOException -> 0x000e }
    L_0x000e:
        return;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.samsung.a.a.a.a.h.a.a(java.io.BufferedReader):void");
    }
}
