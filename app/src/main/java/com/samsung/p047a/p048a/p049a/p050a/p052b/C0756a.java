package com.samsung.p047a.p048a.p049a.p050a.p052b;

import android.content.Context;
import android.os.Build;
import android.os.Build.VERSION;
import android.telephony.TelephonyManager;
import com.samsung.p047a.p048a.p049a.p050a.p063i.C0784a;
import java.util.Locale;
import java.util.TimeZone;
import java.util.concurrent.TimeUnit;

/* compiled from: DeviceInfo */
/* renamed from: com.samsung.a.a.a.a.b.a */
public class C0756a {
    /* renamed from: a */
    private String f2407a = "";
    /* renamed from: b */
    private String f2408b = "";
    /* renamed from: c */
    private String f2409c = "";
    /* renamed from: d */
    private String f2410d = "";
    /* renamed from: e */
    private String f2411e = "";
    /* renamed from: f */
    private String f2412f = "";
    /* renamed from: g */
    private String f2413g = "";
    /* renamed from: h */
    private String f2414h = "";
    /* renamed from: i */
    private String f2415i = "";
    /* renamed from: j */
    private String f2416j = "";

    public C0756a(Context context) {
        Locale locale = context.getResources().getConfiguration().locale;
        this.f2407a = locale.getDisplayCountry();
        TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService("phone");
        if (telephonyManager != null) {
            String simOperator = telephonyManager.getSimOperator();
            if (simOperator != null && simOperator.length() >= 3) {
                this.f2413g = simOperator.substring(0, 3);
                this.f2414h = simOperator.substring(3);
            }
        }
        this.f2408b = locale.getLanguage();
        this.f2409c = VERSION.RELEASE;
        this.f2410d = Build.BRAND;
        this.f2411e = Build.MODEL;
        this.f2416j = VERSION.INCREMENTAL;
        this.f2415i = String.valueOf(TimeUnit.MILLISECONDS.toMinutes((long) TimeZone.getDefault().getRawOffset()));
        try {
            this.f2412f = context.getPackageManager().getPackageInfo(context.getPackageName(), 0).versionName;
        } catch (Exception e) {
            C0784a.m3252a(getClass(), e);
        }
    }

    /* renamed from: a */
    public String m3140a() {
        return this.f2413g;
    }

    /* renamed from: b */
    public String m3141b() {
        return this.f2414h;
    }

    /* renamed from: c */
    public String m3142c() {
        return this.f2408b;
    }

    /* renamed from: d */
    public String m3143d() {
        return this.f2409c;
    }

    /* renamed from: e */
    public String m3144e() {
        return this.f2411e;
    }

    /* renamed from: f */
    public String m3145f() {
        return this.f2412f;
    }

    /* renamed from: g */
    public String m3146g() {
        return this.f2415i;
    }

    /* renamed from: h */
    public String m3147h() {
        return this.f2416j;
    }
}
