package com.samsung.p047a.p048a.p049a.p050a.p054d;

import android.app.Application;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Build.VERSION;
import android.os.UserManager;
import android.text.TextUtils;
import com.samsung.android.lxd.processor.network.channel.pty.internal.KeycodeConstants;
import com.samsung.p047a.p048a.p049a.C0790b;
import com.samsung.p047a.p048a.p049a.C0795e;
import com.samsung.p047a.p048a.p049a.p050a.p063i.C0784a;
import com.samsung.p047a.p048a.p049a.p050a.p063i.C0788d;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Map.Entry;

/* compiled from: Validation */
/* renamed from: com.samsung.a.a.a.a.d.d */
public class C0766d {
    /* renamed from: a */
    public static String f2435a = "RSSAV1wsc2s314SAamk";

    private C0766d() {
    }

    /* renamed from: a */
    public static Map<String, String> m3185a(Map<String, String> map) {
        Map<String, String> hashMap = new HashMap();
        map = map.entrySet().iterator();
        while (map.hasNext()) {
            Entry entry = (Entry) map.next();
            Object obj = (String) entry.getKey();
            Object obj2 = (String) entry.getValue();
            if (obj.length() > 40) {
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append("cd key length over:");
                stringBuilder.append(obj);
                C0784a.m3253a(stringBuilder.toString());
                obj = obj.substring(0, 40);
            }
            if (obj2 != null && obj2.length() > 1024) {
                stringBuilder = new StringBuilder();
                stringBuilder.append("cd value length over:");
                stringBuilder.append(obj2);
                C0784a.m3253a(stringBuilder.toString());
                obj2 = obj2.substring(0, 1024);
            }
            hashMap.put(obj, obj2);
        }
        return hashMap;
    }

    /* renamed from: a */
    public static boolean m3187a(final Application application, final C0790b c0790b) {
        if (application == null) {
            C0788d.m3262a("context cannot be null");
            return false;
        } else if (c0790b == null) {
            C0788d.m3262a("Configuration cannot be null");
            return false;
        } else if (!TextUtils.isEmpty(c0790b.m3272b()) || c0790b.m3276e()) {
            if (c0790b.m3275d()) {
                if (c0790b.m3279h() == null) {
                    C0788d.m3262a("If you want to use In App Logging, you should implement UserAgreement interface");
                    return false;
                } else if (C0764c.f2432a && !C0766d.m3188a(application, "com.sec.spp.permission.TOKEN", false)) {
                    C0788d.m3262a("SamsungAnalytics2 need to define 'com.sec.spp.permission.TOKEN_XXXX' permission in AndroidManifest");
                    return false;
                }
            } else if (!C0766d.m3188a(application, "com.sec.spp.permission.TOKEN", false)) {
                C0788d.m3262a("If you want to use DLC Logger, define 'com.sec.spp.permission.TOKEN_XXXX' permission in AndroidManifest");
                return false;
            } else if (!TextUtils.isEmpty(c0790b.m3272b())) {
                C0788d.m3262a("This mode is not allowed to set device Id");
                return false;
            } else if (!TextUtils.isEmpty(c0790b.m3277f())) {
                C0788d.m3262a("This mode is not allowed to set user Id");
                return false;
            }
            if (c0790b.m3280i() == null) {
                C0788d.m3262a("you should set the UI version");
                return false;
            }
            if (VERSION.SDK_INT >= 24) {
                UserManager userManager = (UserManager) application.getSystemService("user");
                if (!(userManager == null || userManager.isUserUnlocked())) {
                    C0784a.m3256c("The user has not unlocked the device.");
                    BroadcastReceiver c07651 = new BroadcastReceiver() {
                        public void onReceive(Context context, Intent intent) {
                            context = new StringBuilder();
                            context.append("receive ");
                            context.append(intent.getAction());
                            C0784a.m3255b(context.toString());
                            C0795e.m3295a(application, c0790b);
                        }
                    };
                    c0790b = new IntentFilter();
                    c0790b.addAction("android.intent.action.BOOT_COMPLETED");
                    c0790b.addAction("android.intent.action.USER_UNLOCKED");
                    application.registerReceiver(c07651, c0790b);
                    return false;
                }
            }
            return true;
        } else {
            C0788d.m3262a("Device Id is empty, set Device Id or enable auto device id");
            return false;
        }
    }

    /* renamed from: a */
    public static boolean m3186a() {
        String str;
        String str2;
        if (VERSION.SDK_INT > 23) {
            str = "com.samsung.android.feature.SemFloatingFeature";
            str2 = "getBoolean";
        } else {
            str = "com.samsung.android.feature.FloatingFeature";
            str2 = "getEnableStatus";
        }
        try {
            Class cls = Class.forName(str);
            Object invoke = cls.getMethod("getInstance", null).invoke(null, new Object[0]);
            boolean booleanValue = ((Boolean) cls.getMethod(str2, new Class[]{String.class}).invoke(invoke, new Object[]{"SEC_FLOATING_FEATURE_CONTEXTSERVICE_ENABLE_SURVEY_MODE"})).booleanValue();
            if (booleanValue) {
                C0784a.m3255b("cf feature is supported");
            } else {
                C0784a.m3255b("feature is not supported");
            }
            return booleanValue;
        } catch (Exception e) {
            C0784a.m3255b("Floating feature is not supported (non-samsung device)");
            C0784a.m3252a(C0766d.class, e);
            return false;
        }
    }

    /* renamed from: a */
    public static boolean m3188a(Context context, String str, boolean z) {
        try {
            context = context.getPackageManager().getPackageInfo(context.getPackageName(), KeycodeConstants.META_CTRL_ON);
            if (context.requestedPermissions != null) {
                for (String str2 : context.requestedPermissions) {
                    if (z) {
                        if (str2.equalsIgnoreCase(str)) {
                            return true;
                        }
                    } else if (str2.startsWith(str)) {
                        return true;
                    }
                }
            }
        } catch (Exception e) {
            C0784a.m3252a(C0766d.class, e);
        }
        return false;
    }

    /* renamed from: a */
    public static String m3184a(String str) {
        if (str == null) {
            return null;
        }
        try {
            MessageDigest.getInstance("SHA-256").update(str.getBytes("UTF-8"));
            str = String.format(Locale.US, "%064x", new Object[]{new BigInteger(1, r1.digest())});
        } catch (Exception e) {
            C0784a.m3252a(C0766d.class, e);
            str = null;
        }
        return str;
    }
}
