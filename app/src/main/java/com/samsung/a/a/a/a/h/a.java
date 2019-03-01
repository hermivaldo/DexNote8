package com.samsung.a.a.a.a.h;

import android.net.Uri;
import android.net.Uri.Builder;
import android.text.TextUtils;
import com.samsung.a.a.a.a.c.b;
import com.samsung.a.a.a.a.d.d;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.net.ssl.HttpsURLConnection;
import org.json.JSONException;
import org.json.JSONObject;

/* compiled from: RegisterTask */
public class a implements b {
    private final com.samsung.a.a.a.a.a.a a = com.samsung.a.a.a.a.a.a.DATA_DELETE;
    private HttpsURLConnection b = null;
    private String c = "";
    private String d = "";
    private long e;
    private com.samsung.a.a.a.a.c.a f;

    public a(String str, String str2, long j, com.samsung.a.a.a.a.c.a aVar) {
        this.c = str;
        this.d = str2;
        this.e = j;
        this.f = aVar;
    }

    private String c() {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("tid", this.c);
            jSONObject.put("lid", this.d);
            jSONObject.put("ts", this.e);
        } catch (JSONException unused) {
            return jSONObject.toString();
        }
    }

    public void a() {
        try {
            Builder buildUpon = Uri.parse(this.a.a()).buildUpon();
            String format = SimpleDateFormat.getTimeInstance(2).format(new Date());
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(format);
            stringBuilder.append(d.a);
            buildUpon.appendQueryParameter("ts", format).appendQueryParameter("hc", d.a(stringBuilder.toString()));
            this.b = (HttpsURLConnection) new URL(buildUpon.build().toString()).openConnection();
            this.b.setSSLSocketFactory(com.samsung.a.a.a.a.e.a.a().b().getSocketFactory());
            this.b.setRequestMethod(this.a.b());
            this.b.setConnectTimeout(3000);
            this.b.setRequestProperty("Content-Type", "application/json");
            Object c = c();
            if (!TextUtils.isEmpty(c)) {
                this.b.setDoOutput(true);
                OutputStream bufferedOutputStream = new BufferedOutputStream(this.b.getOutputStream());
                bufferedOutputStream.write(c.getBytes());
                bufferedOutputStream.flush();
                bufferedOutputStream.close();
            }
        } catch (Exception unused) {
        }
    }

    /* JADX WARNING: Missing block: B:16:?, code:
            a(0, "");
     */
    /* JADX WARNING: Missing block: B:19:0x0092, code:
            a(null);
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public int b() {
        BufferedReader bufferedReader;
        BufferedReader bufferedReader2 = null;
        int responseCode = this.b.getResponseCode();
        if (responseCode >= 400) {
            bufferedReader = new BufferedReader(new InputStreamReader(this.b.getErrorStream()));
        } else {
            bufferedReader = new BufferedReader(new InputStreamReader(this.b.getInputStream()));
        }
        bufferedReader2 = bufferedReader;
        String string = new JSONObject(bufferedReader2.readLine()).getString("rc");
        StringBuilder stringBuilder;
        if (responseCode == 200 && string.equalsIgnoreCase("1000")) {
            stringBuilder = new StringBuilder();
            stringBuilder.append("Success : ");
            stringBuilder.append(responseCode);
            stringBuilder.append(" ");
            stringBuilder.append(string);
            com.samsung.a.a.a.a.i.a.a(stringBuilder.toString());
        } else {
            stringBuilder = new StringBuilder();
            stringBuilder.append("Fail : ");
            stringBuilder.append(responseCode);
            stringBuilder.append(" ");
            stringBuilder.append(string);
            com.samsung.a.a.a.a.i.a.a(stringBuilder.toString());
        }
        a(responseCode, string);
        a(bufferedReader2);
        return 0;
    }

    private void a(int i, String str) {
        if (this.f != null) {
            if (i == 200 && str.equalsIgnoreCase("1000")) {
                this.f.a(0, "", "", "");
            } else {
                this.f.b(i, str, "", "");
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
        if (this.b != null) {
            this.b.disconnect();
        }
    }
}
