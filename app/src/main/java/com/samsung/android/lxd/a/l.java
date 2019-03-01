package com.samsung.android.lxd.a;

import android.app.Application;
import android.os.AsyncTask;
import android.os.Build.VERSION;
import android.text.TextUtils;
import com.samsung.a.a.a.d.b;
import com.samsung.a.a.a.d.d;
import com.samsung.a.a.a.e;
import com.samsung.android.lxd.LxdApplication;
import com.samsung.android.lxd.processor.config.SystemConfigHelper;
import com.samsung.android.lxd.processor.utils.log.Log;
import java.util.HashMap;
import java.util.Map;

/* compiled from: SamsungAnalyticsUtils */
public class l {
    private static final String a = "l";
    private static boolean b = false;

    /* compiled from: SamsungAnalyticsUtils */
    private static class a extends AsyncTask<Void, Void, Void> {
        final int a;
        final String b;
        final String c;
        final String d;
        final long e;

        a(int i, String str, String str2, String str3, long j) {
            while (str2.length() < 4) {
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append(SystemConfigHelper.CONFIG_OPTION_OFF);
                stringBuilder.append(str2);
                str2 = stringBuilder.toString();
            }
            this.a = i;
            this.b = str;
            this.c = str2;
            this.d = str3;
            this.e = j;
        }

        /* renamed from: a */
        protected Void doInBackground(Void... voidArr) {
            switch (this.a) {
                case 0:
                    e.a().a(((d) new d().b(this.b)).b());
                    break;
                case 1:
                    e.a().a(((com.samsung.a.a.a.d.a) new com.samsung.a.a.a.d.a().b(this.b)).a(this.c).b());
                    break;
                case 2:
                    Map hashMap = new HashMap();
                    hashMap.put("detail", this.d);
                    e.a().a(((com.samsung.a.a.a.d.a) ((com.samsung.a.a.a.d.a) new com.samsung.a.a.a.d.a().b(this.b)).a(this.c).a(hashMap)).b());
                    break;
                case 3:
                    e.a().a(((com.samsung.a.a.a.d.a) new com.samsung.a.a.a.d.a().b(this.b)).a(this.c).a(this.e).b());
                    break;
                case 4:
                    e.a().a(new b().a(this.d).a(false).b());
                    break;
                default:
                    Log.e(l.a, "Wrong caseNumber!");
                    break;
            }
            String a = l.a;
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("sendSALog screenId : ");
            stringBuilder.append(this.b);
            stringBuilder.append(", eventName : ");
            stringBuilder.append(this.c);
            stringBuilder.append(", detail : ");
            stringBuilder.append(this.d);
            stringBuilder.append(", value : ");
            stringBuilder.append(this.e);
            stringBuilder.append(", caseNumber : ");
            stringBuilder.append(this.a);
            Log.d(a, stringBuilder.toString());
            return null;
        }
    }

    private static void b() {
        Application a = LxdApplication.a();
        com.samsung.a.a.a.b a2 = new com.samsung.a.a.a.b().a("4E3-399-525254");
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("SA_1.7_SDK_");
        stringBuilder.append(VERSION.SDK_INT);
        e.a(a, a2.c(stringBuilder.toString()).c());
    }

    private static void c() {
        if (!b) {
            b();
            b = true;
        }
    }

    public static void a(String str, String str2) {
        c();
        if (!TextUtils.isEmpty(str) && !TextUtils.isEmpty(str2)) {
            new a(1, str, str2, null, 0).execute(new Void[0]);
        }
    }

    public static void a(String str, String str2, String str3) {
        c();
        if (!TextUtils.isEmpty(str) && !TextUtils.isEmpty(str2) && !TextUtils.isEmpty(str3)) {
            new a(2, str, str2, str3, 0).execute(new Void[0]);
        }
    }

    public static void a(String str, String str2, long j) {
        c();
        if (!TextUtils.isEmpty(str) && !TextUtils.isEmpty(str2)) {
            new a(3, str, str2, null, j).execute(new Void[0]);
        }
    }
}
