package com.samsung.p047a.p048a.p049a.p050a.p056f.p066b;

import android.net.Uri;
import android.net.Uri.Builder;
import android.text.TextUtils;
import com.samsung.p047a.p048a.p049a.p050a.p051a.C0750a;
import com.samsung.p047a.p048a.p049a.p050a.p053c.C0758a;
import com.samsung.p047a.p048a.p049a.p050a.p053c.C0759b;
import com.samsung.p047a.p048a.p049a.p050a.p054d.C0766d;
import com.samsung.p047a.p048a.p049a.p050a.p055e.C0769a;
import com.samsung.p047a.p048a.p049a.p050a.p056f.C0776c;
import com.samsung.p047a.p048a.p049a.p050a.p056f.C0782e;
import com.samsung.p047a.p048a.p049a.p050a.p063i.C0784a;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.Queue;
import java.util.zip.GZIPOutputStream;
import javax.net.ssl.HttpsURLConnection;
import org.json.JSONObject;

/* compiled from: DLSAPIClient */
/* renamed from: com.samsung.a.a.a.a.f.b.a */
public class C1295a implements C0759b {
    /* renamed from: a */
    private static final C0750a f4347a = C0750a.SEND_LOG;
    /* renamed from: b */
    private static final C0750a f4348b = C0750a.SEND_BUFFERED_LOG;
    /* renamed from: c */
    private Queue<C0782e> f4349c;
    /* renamed from: d */
    private C0782e f4350d;
    /* renamed from: e */
    private C0776c f4351e;
    /* renamed from: f */
    private String f4352f;
    /* renamed from: g */
    private HttpsURLConnection f4353g = null;
    /* renamed from: h */
    private C0758a f4354h;
    /* renamed from: i */
    private Boolean f4355i = Boolean.valueOf(false);
    /* renamed from: j */
    private int f4356j;

    /* renamed from: a */
    private int m6025a(int i) {
        if (i == 0) {
            return 3000;
        }
        int i2 = 15000;
        if (i <= 15000) {
            i2 = i;
        }
        return i2;
    }

    public C1295a(C0782e c0782e, String str, int i, C0758a c0758a) {
        this.f4350d = c0782e;
        this.f4352f = str;
        this.f4354h = c0758a;
        this.f4356j = m6025a(i);
        this.f4351e = c0782e.m3247d();
    }

    public C1295a(C0776c c0776c, Queue<C0782e> queue, String str, int i, C0758a c0758a) {
        this.f4349c = queue;
        this.f4352f = str;
        this.f4354h = c0758a;
        this.f4355i = Boolean.valueOf(true);
        this.f4356j = m6025a(i);
        this.f4351e = c0776c;
    }

    /* renamed from: c */
    private String m6028c() {
        if (!this.f4355i.booleanValue()) {
            return this.f4350d.m3246c();
        }
        Iterator it = this.f4349c.iterator();
        String c = ((C0782e) it.next()).m3246c();
        while (it.hasNext()) {
            C0782e c0782e = (C0782e) it.next();
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(c);
            stringBuilder.append("\u000e");
            stringBuilder.append(c0782e.m3246c());
            c = stringBuilder.toString();
        }
        return c;
    }

    /* renamed from: a */
    public void mo650a() {
        try {
            C0750a c0750a = this.f4355i.booleanValue() ? f4348b : f4347a;
            Builder buildUpon = Uri.parse(c0750a.m3132a()).buildUpon();
            String format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm").format(new Date());
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(this.f4352f);
            stringBuilder.append(format);
            stringBuilder.append(C0766d.f2435a);
            buildUpon.appendQueryParameter("ts", format).appendQueryParameter("type", this.f4351e.m3218a()).appendQueryParameter("tid", this.f4352f).appendQueryParameter("hc", C0766d.m3184a(stringBuilder.toString()));
            this.f4353g = (HttpsURLConnection) new URL(buildUpon.build().toString()).openConnection();
            this.f4353g.setSSLSocketFactory(C0769a.m3190a().m3192b().getSocketFactory());
            this.f4353g.setRequestMethod(c0750a.m3133b());
            this.f4353g.addRequestProperty("Content-Encoding", this.f4355i.booleanValue() ? "gzip" : "text");
            this.f4353g.setConnectTimeout(this.f4356j);
            String c = m6028c();
            if (!TextUtils.isEmpty(c)) {
                OutputStream bufferedOutputStream;
                this.f4353g.setDoOutput(true);
                if (this.f4355i.booleanValue()) {
                    bufferedOutputStream = new BufferedOutputStream(new GZIPOutputStream(this.f4353g.getOutputStream()));
                } else {
                    bufferedOutputStream = new BufferedOutputStream(this.f4353g.getOutputStream());
                }
                bufferedOutputStream.write(c.getBytes());
                bufferedOutputStream.flush();
                bufferedOutputStream.close();
            }
            StringBuilder stringBuilder2 = new StringBuilder();
            stringBuilder2.append("[DLS Client] Send to DLS : ");
            stringBuilder2.append(c);
            C0784a.m3253a(stringBuilder2.toString());
        } catch (Exception e) {
            C0784a.m3256c("[DLS Client] Send fail.");
            StringBuilder stringBuilder3 = new StringBuilder();
            stringBuilder3.append("[DLS Client] ");
            stringBuilder3.append(e.getMessage());
            C0784a.m3253a(stringBuilder3.toString());
        }
    }

    /* renamed from: b */
    public int mo651b() {
        BufferedReader bufferedReader;
        int i;
        Exception e;
        StringBuilder stringBuilder;
        Throwable th;
        try {
            int responseCode = this.f4353g.getResponseCode();
            bufferedReader = new BufferedReader(new InputStreamReader(this.f4353g.getInputStream()));
            try {
                String string = new JSONObject(bufferedReader.readLine()).getString("rc");
                StringBuilder stringBuilder2;
                if (responseCode == 200 && string.equalsIgnoreCase("1000")) {
                    i = 1;
                    stringBuilder2 = new StringBuilder();
                    stringBuilder2.append("[DLS Sender] send result success : ");
                    stringBuilder2.append(responseCode);
                    stringBuilder2.append(" ");
                    stringBuilder2.append(string);
                    C0784a.m3255b(stringBuilder2.toString());
                } else {
                    i = -7;
                    stringBuilder2 = new StringBuilder();
                    stringBuilder2.append("[DLS Sender] send result fail : ");
                    stringBuilder2.append(responseCode);
                    stringBuilder2.append(" ");
                    stringBuilder2.append(string);
                    C0784a.m3255b(stringBuilder2.toString());
                }
                m6026a(responseCode, string);
            } catch (Exception e2) {
                e = e2;
                try {
                    C0784a.m3256c("[DLS Client] Send fail.");
                    stringBuilder = new StringBuilder();
                    stringBuilder.append("[DLS Client] ");
                    stringBuilder.append(e.getMessage());
                    C0784a.m3253a(stringBuilder.toString());
                    i = -41;
                    m6026a(0, "");
                    m6027a(bufferedReader);
                    return i;
                } catch (Throwable th2) {
                    th = th2;
                    m6027a(bufferedReader);
                    throw th;
                }
            }
        } catch (Exception e3) {
            bufferedReader = null;
            e = e3;
            C0784a.m3256c("[DLS Client] Send fail.");
            stringBuilder = new StringBuilder();
            stringBuilder.append("[DLS Client] ");
            stringBuilder.append(e.getMessage());
            C0784a.m3253a(stringBuilder.toString());
            i = -41;
            m6026a(0, "");
            m6027a(bufferedReader);
            return i;
        } catch (Throwable th3) {
            bufferedReader = null;
            th = th3;
            m6027a(bufferedReader);
            throw th;
        }
        m6027a(bufferedReader);
        return i;
    }

    /* renamed from: a */
    private void m6026a(int i, String str) {
        if (this.f4354h != null) {
            if (i != 200 || str.equalsIgnoreCase("1000") == null) {
                if (this.f4355i.booleanValue() != null) {
                    while (this.f4349c.isEmpty() == null) {
                        C0782e c0782e = (C0782e) this.f4349c.poll();
                        C0758a c0758a = this.f4354h;
                        StringBuilder stringBuilder = new StringBuilder();
                        stringBuilder.append(c0782e.m3244b());
                        stringBuilder.append("");
                        c0758a.mo648b(i, stringBuilder.toString(), c0782e.m3246c(), c0782e.m3247d().m3218a());
                    }
                } else {
                    str = this.f4354h;
                    StringBuilder stringBuilder2 = new StringBuilder();
                    stringBuilder2.append(this.f4350d.m3244b());
                    stringBuilder2.append("");
                    str.mo648b(i, stringBuilder2.toString(), this.f4350d.m3246c(), this.f4350d.m3247d().m3218a());
                }
            }
        }
    }

    /* renamed from: a */
    private void m6027a(java.io.BufferedReader r1) {
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
        r1 = r0.f4353g;	 Catch:{ IOException -> 0x000e }
        if (r1 == 0) goto L_0x000e;	 Catch:{ IOException -> 0x000e }
    L_0x0009:
        r0 = r0.f4353g;	 Catch:{ IOException -> 0x000e }
        r0.disconnect();	 Catch:{ IOException -> 0x000e }
    L_0x000e:
        return;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.samsung.a.a.a.a.f.b.a.a(java.io.BufferedReader):void");
    }
}
