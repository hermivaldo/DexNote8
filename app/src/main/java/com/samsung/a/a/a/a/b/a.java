package com.samsung.a.a.a.a.b;

import android.content.Context;
import android.os.Build;
import android.os.Build.VERSION;
import android.telephony.TelephonyManager;
import java.util.Locale;
import java.util.TimeZone;
import java.util.concurrent.TimeUnit;

/* compiled from: DeviceInfo */
public class a {
    private String a = "";
    private String b = "";
    private String c = "";
    private String d = "";
    private String e = "";
    private String f = "";
    private String g = "";
    private String h = "";
    private String i = "";
    private String j = "";

    public a(Context context) {
        Locale locale = context.getResources().getConfiguration().locale;
        this.a = locale.getDisplayCountry();
        TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService("phone");
        if (telephonyManager != null) {
            String simOperator = telephonyManager.getSimOperator();
            if (simOperator != null && simOperator.length() >= 3) {
                this.g = simOperator.substring(0, 3);
                this.h = simOperator.substring(3);
            }
        }
        this.b = locale.getLanguage();
        this.c = VERSION.RELEASE;
        this.d = Build.BRAND;
        this.e = Build.MODEL;
        this.j = VERSION.INCREMENTAL;
        this.i = String.valueOf(TimeUnit.MILLISECONDS.toMinutes((long) TimeZone.getDefault().getRawOffset()));
        try {
            this.f = context.getPackageManager().getPackageInfo(context.getPackageName(), 0).versionName;
        } catch (Exception e) {
            com.samsung.a.a.a.a.i.a.a(getClass(), e);
        }
    }

    public String a() {
        return this.g;
    }

    public String b() {
        return this.h;
    }

    public String c() {
        return this.b;
    }

    public String d() {
        return this.c;
    }

    public String e() {
        return this.e;
    }

    public String f() {
        return this.f;
    }

    public String g() {
        return this.i;
    }

    public String h() {
        return this.j;
    }
}
