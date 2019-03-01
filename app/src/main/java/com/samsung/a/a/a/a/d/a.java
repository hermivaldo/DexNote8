package com.samsung.a.a.a.a.d;

import android.content.SharedPreferences;
import android.net.Uri;
import android.net.Uri.Builder;
import android.text.TextUtils;
import com.samsung.a.a.a.a.a.c;
import com.samsung.a.a.a.a.c.b;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Map;
import javax.net.ssl.HttpsURLConnection;
import org.json.JSONException;
import org.json.JSONObject;

/* compiled from: GetPolicyClient */
public class a implements b {
    private String a;
    private Map<String, String> b;
    private com.samsung.a.a.a.a.a.a c;
    private HttpsURLConnection d = null;
    private SharedPreferences e;
    private com.samsung.a.a.a.a.a<Void, Boolean> f;

    public a(com.samsung.a.a.a.a.a.a aVar, String str, Map<String, String> map, SharedPreferences sharedPreferences, com.samsung.a.a.a.a.a<Void, Boolean> aVar2) {
        this.a = str;
        this.c = aVar;
        this.b = map;
        this.e = sharedPreferences;
        this.f = aVar2;
    }

    public void a() {
        try {
            Builder buildUpon = Uri.parse(this.c.a()).buildUpon();
            for (String str : this.b.keySet()) {
                buildUpon.appendQueryParameter(str, (String) this.b.get(str));
            }
            String format = SimpleDateFormat.getTimeInstance(2, Locale.US).format(new Date());
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(this.a);
            stringBuilder.append(format);
            stringBuilder.append(d.a);
            buildUpon.appendQueryParameter("ts", format).appendQueryParameter("tid", this.a).appendQueryParameter("hc", d.a(stringBuilder.toString()));
            Object a = c.a();
            if (!TextUtils.isEmpty(a)) {
                buildUpon.appendQueryParameter("csc", a);
            }
            this.d = (HttpsURLConnection) new URL(buildUpon.build().toString()).openConnection();
            this.d.setSSLSocketFactory(com.samsung.a.a.a.a.e.a.a().b().getSocketFactory());
            this.d.setRequestMethod(this.c.b());
            this.d.setConnectTimeout(3000);
        } catch (Exception unused) {
            com.samsung.a.a.a.a.i.a.c("Fail to get Policy");
        }
    }

    public int b() {
        StringBuilder stringBuilder;
        int i;
        Throwable th;
        BufferedReader bufferedReader = null;
        if (this.d.getResponseCode() != 200) {
            stringBuilder = new StringBuilder();
            stringBuilder.append("Fail to get Policy. Response code : ");
            stringBuilder.append(this.d.getResponseCode());
            com.samsung.a.a.a.a.i.a.c(stringBuilder.toString());
            i = -61;
        } else {
            i = 0;
        }
        BufferedReader bufferedReader2 = new BufferedReader(new InputStreamReader(this.d.getInputStream()));
        try {
            String readLine = bufferedReader2.readLine();
            com.samsung.a.a.a.a.i.a.a(readLine);
            JSONObject jSONObject = new JSONObject(readLine);
            int i2 = jSONObject.getInt("rc");
            if (i2 != 1000) {
                stringBuilder = new StringBuilder();
                stringBuilder.append("Fail to get Policy; Invalid Message. Result code : ");
                stringBuilder.append(i2);
                com.samsung.a.a.a.a.i.a.c(stringBuilder.toString());
                i = -61;
            } else {
                com.samsung.a.a.a.a.i.a.a("GetPolicyClient", "Get Policy Success");
                if (TextUtils.isEmpty(this.e.getString("lgt", "")) && this.f != null) {
                    readLine = jSONObject.getString("lgt");
                    if (readLine != null && readLine.equals("rtb")) {
                        this.f.a(Boolean.valueOf(true));
                    }
                }
                a(jSONObject);
            }
            if (this.d != null) {
                this.d.disconnect();
            }
            a(bufferedReader2);
            boolean isEmpty = TextUtils.isEmpty(this.e.getString("dom", ""));
            if (i == -61 && !isEmpty) {
                this.e.edit().putLong("policy_received_date", System.currentTimeMillis()).apply();
            }
            return i;
        } catch (Exception unused) {
            bufferedReader = bufferedReader2;
            try {
            } catch (Exception unused2) {
                com.samsung.a.a.a.a.i.a.c("Fail to get Policy");
                a(bufferedReader);
                i = -61;
            } catch (Throwable th2) {
                th = th2;
                bufferedReader2 = bufferedReader;
                a(bufferedReader2);
                throw th;
            }
        } catch (Throwable th3) {
            th = th3;
            a(bufferedReader2);
            throw th;
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
        if (this.d != null) {
            this.d.disconnect();
        }
    }

    public void a(JSONObject jSONObject) {
        try {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("https://");
            stringBuilder.append(jSONObject.getString("dom"));
            this.e.edit().putInt("oq-3g", jSONObject.getInt("oq-3g") * 1024).putInt("dq-3g", jSONObject.getInt("dq-3g") * 1024).putInt("oq-w", jSONObject.getInt("oq-w") * 1024).putInt("dq-w", jSONObject.getInt("dq-w") * 1024).putString("dom", stringBuilder.toString()).putString("uri", jSONObject.getString("uri")).putString("bat-uri", jSONObject.getString("bat-uri")).putString("lgt", jSONObject.getString("lgt")).putInt("rint", jSONObject.getInt("rint")).putLong("policy_received_date", System.currentTimeMillis()).apply();
            c cVar = c.c;
            StringBuilder stringBuilder2 = new StringBuilder();
            stringBuilder2.append("https://");
            stringBuilder2.append(jSONObject.getString("dom"));
            cVar.a(stringBuilder2.toString());
            com.samsung.a.a.a.a.a.b.DLS_DIR.a(jSONObject.getString("uri"));
            com.samsung.a.a.a.a.a.b.DLS_DIR_BAT.a(jSONObject.getString("bat-uri"));
            StringBuilder stringBuilder3 = new StringBuilder();
            stringBuilder3.append("dq-3g: ");
            stringBuilder3.append(jSONObject.getInt("dq-3g") * 1024);
            stringBuilder3.append(", dq-w: ");
            stringBuilder3.append(jSONObject.getInt("dq-w") * 1024);
            stringBuilder3.append(", oq-3g: ");
            stringBuilder3.append(jSONObject.getInt("oq-3g") * 1024);
            stringBuilder3.append(", oq-w: ");
            stringBuilder3.append(jSONObject.getInt("oq-w") * 1024);
            com.samsung.a.a.a.a.i.a.a(stringBuilder3.toString());
        } catch (JSONException e) {
            com.samsung.a.a.a.a.i.a.c("Fail to get Policy");
            StringBuilder stringBuilder4 = new StringBuilder();
            stringBuilder4.append("[GetPolicyClient] ");
            stringBuilder4.append(e.getMessage());
            com.samsung.a.a.a.a.i.a.a(stringBuilder4.toString());
        }
    }
}
