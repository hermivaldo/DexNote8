package com.samsung.a.a.a.a.f.b;

import android.net.Uri;
import android.net.Uri.Builder;
import android.text.TextUtils;
import com.samsung.a.a.a.a.c.b;
import com.samsung.a.a.a.a.d.d;
import com.samsung.a.a.a.a.f.c;
import com.samsung.a.a.a.a.f.e;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.IOException;
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
public class a implements b {
    private static final com.samsung.a.a.a.a.a.a a = com.samsung.a.a.a.a.a.a.SEND_LOG;
    private static final com.samsung.a.a.a.a.a.a b = com.samsung.a.a.a.a.a.a.SEND_BUFFERED_LOG;
    private Queue<e> c;
    private e d;
    private c e;
    private String f;
    private HttpsURLConnection g = null;
    private com.samsung.a.a.a.a.c.a h;
    private Boolean i = Boolean.valueOf(false);
    private int j;

    private int a(int i) {
        if (i == 0) {
            return 3000;
        }
        int i2 = 15000;
        if (i <= 15000) {
            i2 = i;
        }
        return i2;
    }

    public a(e eVar, String str, int i, com.samsung.a.a.a.a.c.a aVar) {
        this.d = eVar;
        this.f = str;
        this.h = aVar;
        this.j = a(i);
        this.e = eVar.d();
    }

    public a(c cVar, Queue<e> queue, String str, int i, com.samsung.a.a.a.a.c.a aVar) {
        this.c = queue;
        this.f = str;
        this.h = aVar;
        this.i = Boolean.valueOf(true);
        this.j = a(i);
        this.e = cVar;
    }

    private String c() {
        if (!this.i.booleanValue()) {
            return this.d.c();
        }
        Iterator it = this.c.iterator();
        String c = ((e) it.next()).c();
        while (it.hasNext()) {
            e eVar = (e) it.next();
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(c);
            stringBuilder.append("\u000e");
            stringBuilder.append(eVar.c());
            c = stringBuilder.toString();
        }
        return c;
    }

    public void a() {
        try {
            com.samsung.a.a.a.a.a.a aVar = this.i.booleanValue() ? b : a;
            Builder buildUpon = Uri.parse(aVar.a()).buildUpon();
            String format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm").format(new Date());
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(this.f);
            stringBuilder.append(format);
            stringBuilder.append(d.a);
            buildUpon.appendQueryParameter("ts", format).appendQueryParameter("type", this.e.a()).appendQueryParameter("tid", this.f).appendQueryParameter("hc", d.a(stringBuilder.toString()));
            this.g = (HttpsURLConnection) new URL(buildUpon.build().toString()).openConnection();
            this.g.setSSLSocketFactory(com.samsung.a.a.a.a.e.a.a().b().getSocketFactory());
            this.g.setRequestMethod(aVar.b());
            this.g.addRequestProperty("Content-Encoding", this.i.booleanValue() ? "gzip" : "text");
            this.g.setConnectTimeout(this.j);
            String c = c();
            if (!TextUtils.isEmpty(c)) {
                OutputStream bufferedOutputStream;
                this.g.setDoOutput(true);
                if (this.i.booleanValue()) {
                    bufferedOutputStream = new BufferedOutputStream(new GZIPOutputStream(this.g.getOutputStream()));
                } else {
                    bufferedOutputStream = new BufferedOutputStream(this.g.getOutputStream());
                }
                bufferedOutputStream.write(c.getBytes());
                bufferedOutputStream.flush();
                bufferedOutputStream.close();
            }
            StringBuilder stringBuilder2 = new StringBuilder();
            stringBuilder2.append("[DLS Client] Send to DLS : ");
            stringBuilder2.append(c);
            com.samsung.a.a.a.a.i.a.a(stringBuilder2.toString());
        } catch (Exception e) {
            com.samsung.a.a.a.a.i.a.c("[DLS Client] Send fail.");
            StringBuilder stringBuilder3 = new StringBuilder();
            stringBuilder3.append("[DLS Client] ");
            stringBuilder3.append(e.getMessage());
            com.samsung.a.a.a.a.i.a.a(stringBuilder3.toString());
        }
    }

    public int b() {
        BufferedReader bufferedReader;
        int i;
        Exception e;
        StringBuilder stringBuilder;
        Throwable th;
        try {
            int responseCode = this.g.getResponseCode();
            bufferedReader = new BufferedReader(new InputStreamReader(this.g.getInputStream()));
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
                    com.samsung.a.a.a.a.i.a.b(stringBuilder2.toString());
                } else {
                    i = -7;
                    stringBuilder2 = new StringBuilder();
                    stringBuilder2.append("[DLS Sender] send result fail : ");
                    stringBuilder2.append(responseCode);
                    stringBuilder2.append(" ");
                    stringBuilder2.append(string);
                    com.samsung.a.a.a.a.i.a.b(stringBuilder2.toString());
                }
                a(responseCode, string);
            } catch (Exception e2) {
                e = e2;
                try {
                    com.samsung.a.a.a.a.i.a.c("[DLS Client] Send fail.");
                    stringBuilder = new StringBuilder();
                    stringBuilder.append("[DLS Client] ");
                    stringBuilder.append(e.getMessage());
                    com.samsung.a.a.a.a.i.a.a(stringBuilder.toString());
                    i = -41;
                    a(0, "");
                    a(bufferedReader);
                    return i;
                } catch (Throwable th2) {
                    th = th2;
                    a(bufferedReader);
                    throw th;
                }
            }
        } catch (Exception e3) {
            bufferedReader = null;
            e = e3;
            com.samsung.a.a.a.a.i.a.c("[DLS Client] Send fail.");
            stringBuilder = new StringBuilder();
            stringBuilder.append("[DLS Client] ");
            stringBuilder.append(e.getMessage());
            com.samsung.a.a.a.a.i.a.a(stringBuilder.toString());
            i = -41;
            a(0, "");
            a(bufferedReader);
            return i;
        } catch (Throwable th3) {
            bufferedReader = null;
            th = th3;
            a(bufferedReader);
            throw th;
        }
        a(bufferedReader);
        return i;
    }

    private void a(int i, String str) {
        if (this.h != null) {
            if (!(i == 200 && str.equalsIgnoreCase("1000"))) {
                if (this.i.booleanValue()) {
                    while (!this.c.isEmpty()) {
                        e eVar = (e) this.c.poll();
                        com.samsung.a.a.a.a.c.a aVar = this.h;
                        StringBuilder stringBuilder = new StringBuilder();
                        stringBuilder.append(eVar.b());
                        stringBuilder.append("");
                        aVar.b(i, stringBuilder.toString(), eVar.c(), eVar.d().a());
                    }
                } else {
                    com.samsung.a.a.a.a.c.a aVar2 = this.h;
                    StringBuilder stringBuilder2 = new StringBuilder();
                    stringBuilder2.append(this.d.b());
                    stringBuilder2.append("");
                    aVar2.b(i, stringBuilder2.toString(), this.d.c(), this.d.d().a());
                }
            }
        }
    }

    private void a(BufferedReader bufferedReader) {
        if (bufferedReader != null) {
            try {
                bufferedReader.close();
            } catch (IOException unused) {
                return;
            }
        }
        if (this.g != null) {
            this.g.disconnect();
        }
    }
}
