package com.samsung.android.lxd.p064a;

import android.app.Application;
import android.os.AsyncTask;
import android.os.Build.VERSION;
import android.text.TextUtils;
import com.samsung.android.lxd.LxdApplication;
import com.samsung.android.lxd.processor.config.SystemConfigHelper;
import com.samsung.android.lxd.processor.utils.log.Log;
import com.samsung.p047a.p048a.p049a.C0790b;
import com.samsung.p047a.p048a.p049a.C0794d.C1302a;
import com.samsung.p047a.p048a.p049a.C0794d.C1303b;
import com.samsung.p047a.p048a.p049a.C0794d.C1304d;
import com.samsung.p047a.p048a.p049a.C0795e;
import java.util.HashMap;

/* compiled from: SamsungAnalyticsUtils */
/* renamed from: com.samsung.android.lxd.a.l */
public class C0874l {
    /* renamed from: a */
    private static final String f2711a = "l";
    /* renamed from: b */
    private static boolean f2712b = false;

    /* compiled from: SamsungAnalyticsUtils */
    /* renamed from: com.samsung.android.lxd.a.l$a */
    private static class C0873a extends AsyncTask<Void, Void, Void> {
        /* renamed from: a */
        final int f2706a;
        /* renamed from: b */
        final String f2707b;
        /* renamed from: c */
        final String f2708c;
        /* renamed from: d */
        final String f2709d;
        /* renamed from: e */
        final long f2710e;

        protected /* synthetic */ Object doInBackground(Object[] objArr) {
            return m3430a((Void[]) objArr);
        }

        C0873a(int i, String str, String str2, String str3, long j) {
            while (str2.length() < 4) {
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append(SystemConfigHelper.CONFIG_OPTION_OFF);
                stringBuilder.append(str2);
                str2 = stringBuilder.toString();
            }
            this.f2706a = i;
            this.f2707b = str;
            this.f2708c = str2;
            this.f2709d = str3;
            this.f2710e = j;
        }

        /* renamed from: a */
        protected Void m3430a(Void... voidArr) {
            switch (this.f2706a) {
                case null:
                    C0795e.m3294a().m3297a(((C1304d) new C1304d().m3290b(this.f2707b)).mo654b());
                    break;
                case 1:
                    C0795e.m3294a().m3297a(((C1302a) new C1302a().m3290b(this.f2707b)).m6046a(this.f2708c).mo654b());
                    break;
                case 2:
                    voidArr = new HashMap();
                    voidArr.put("detail", this.f2709d);
                    C0795e.m3294a().m3297a(((C1302a) ((C1302a) new C1302a().m3290b(this.f2707b)).m6046a(this.f2708c).m3289a(voidArr)).mo654b());
                    break;
                case 3:
                    C0795e.m3294a().m3297a(((C1302a) new C1302a().m3290b(this.f2707b)).m6046a(this.f2708c).m6045a(this.f2710e).mo654b());
                    break;
                case 4:
                    C0795e.m3294a().m3297a(new C1303b().m6051a(this.f2709d).m6052a(false).mo654b());
                    break;
                default:
                    Log.m3855e(C0874l.f2711a, "Wrong caseNumber!");
                    break;
            }
            voidArr = C0874l.f2711a;
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("sendSALog screenId : ");
            stringBuilder.append(this.f2707b);
            stringBuilder.append(", eventName : ");
            stringBuilder.append(this.f2708c);
            stringBuilder.append(", detail : ");
            stringBuilder.append(this.f2709d);
            stringBuilder.append(", value : ");
            stringBuilder.append(this.f2710e);
            stringBuilder.append(", caseNumber : ");
            stringBuilder.append(this.f2706a);
            Log.m3853d(voidArr, stringBuilder.toString());
            return null;
        }
    }

    /* renamed from: b */
    private static void m3435b() {
        Application a = LxdApplication.m3344a();
        C0790b a2 = new C0790b().m3268a("4E3-399-525254");
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("SA_1.7_SDK_");
        stringBuilder.append(VERSION.SDK_INT);
        C0795e.m3295a(a, a2.m3274c(stringBuilder.toString()).m3273c());
    }

    /* renamed from: c */
    private static void m3436c() {
        if (!f2712b) {
            C0874l.m3435b();
            f2712b = true;
        }
    }

    /* renamed from: a */
    public static void m3432a(String str, String str2) {
        C0874l.m3436c();
        if (!TextUtils.isEmpty(str)) {
            if (!TextUtils.isEmpty(str2)) {
                new C0873a(1, str, str2, null, 0).execute(new Void[null]);
            }
        }
    }

    /* renamed from: a */
    public static void m3434a(String str, String str2, String str3) {
        C0874l.m3436c();
        if (!(TextUtils.isEmpty(str) || TextUtils.isEmpty(str2))) {
            if (!TextUtils.isEmpty(str3)) {
                new C0873a(2, str, str2, str3, 0).execute(new Void[null]);
            }
        }
    }

    /* renamed from: a */
    public static void m3433a(String str, String str2, long j) {
        C0874l.m3436c();
        if (!TextUtils.isEmpty(str)) {
            if (!TextUtils.isEmpty(str2)) {
                new C0873a(3, str, str2, null, j).execute(new Void[null]);
            }
        }
    }
}
