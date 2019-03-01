package com.samsung.android.lxd.a;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Build.VERSION;
import android.os.SemSystemProperties;
import com.samsung.android.lxd.ContactUsActivity;
import com.samsung.android.lxd.R;
import com.samsung.android.lxd.processor.utils.log.Log;
import java.util.Locale;

/* compiled from: ContactUsHelper */
public class b {
    private static final String a = "b";

    public static void a(Activity activity) {
        if (o.c(activity, "com.samsung.android.voc") && activity.getPackageManager() != null && o.a((Context) activity, "com.samsung.android.voc", 170001000)) {
            Intent intent = new Intent("android.intent.action.VIEW", Uri.parse("voc://view/contactUs"));
            intent.putExtra("packageName", activity.getPackageName());
            intent.putExtra("appName", R.string.app_name);
            intent.putExtra("appId", "7g14ay655z");
            intent.putExtra("faqUrl", b("/faq/searchFaq.do"));
            try {
                if (intent.resolveActivity(activity.getPackageManager()) != null) {
                    activity.startActivity(intent);
                    return;
                }
            } catch (ActivityNotFoundException unused) {
                Log.e(a, "do not find samsung members package");
            }
        }
        activity.startActivity(new Intent(activity, ContactUsActivity.class));
    }

    public static Intent a(String str) {
        str = b(str);
        String str2 = a;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("getMuseIntent :: ");
        stringBuilder.append(str);
        Log.d(str2, stringBuilder.toString());
        return new Intent("android.intent.action.VIEW", Uri.parse(str));
    }

    public static String b(String str) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("http://help.content.samsung.com:8080/csweb/auth/gosupport.do?");
        stringBuilder.append(c(str));
        return stringBuilder.toString();
    }

    public static String c(String str) {
        String str2 = "US";
        if ("KR".equals(SemSystemProperties.get("ro.csc.countryiso_code"))) {
            str2 = "KR";
        }
        String str3 = "en_us";
        if ("ko".equals(Locale.getDefault().getDisplayLanguage())) {
            str3 = "ko";
        }
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("serviceCd=lodex&targetUrl=");
        stringBuilder.append(str);
        stringBuilder.append("&");
        stringBuilder.append("chnlCd");
        stringBuilder.append("=");
        stringBuilder.append("ODC");
        stringBuilder.append("&");
        stringBuilder.append("_common_country");
        stringBuilder.append("=");
        stringBuilder.append(str2);
        stringBuilder.append("&");
        stringBuilder.append("_common_lang");
        stringBuilder.append("=");
        stringBuilder.append(str3);
        stringBuilder.append("&");
        stringBuilder.append("dvcModelCd");
        stringBuilder.append("=");
        stringBuilder.append(Build.MODEL);
        stringBuilder.append("&");
        stringBuilder.append("odcVersion");
        stringBuilder.append("=");
        stringBuilder.append(o.u());
        stringBuilder.append("&");
        stringBuilder.append("mcc");
        stringBuilder.append("=");
        stringBuilder.append(o.J());
        stringBuilder.append("&");
        stringBuilder.append("mnc");
        stringBuilder.append("=");
        stringBuilder.append(o.K());
        stringBuilder.append("&");
        stringBuilder.append("dvcOSVersion");
        stringBuilder.append("=");
        stringBuilder.append(VERSION.SDK_INT);
        stringBuilder.append("&");
        stringBuilder.append("saccountID");
        stringBuilder.append("=");
        stringBuilder.append(o.j("com.osp.app.signin"));
        return stringBuilder.toString();
    }
}
