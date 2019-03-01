package com.samsung.a.a.a.a.d;

import android.app.Application;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageInfo;
import android.os.Build.VERSION;
import android.os.UserManager;
import android.text.TextUtils;
import com.samsung.a.a.a.a.i.a;
import com.samsung.a.a.a.b;
import com.samsung.a.a.a.e;
import com.samsung.android.lxd.processor.network.channel.pty.internal.KeycodeConstants;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Map.Entry;

/* compiled from: Validation */
public class d {
    public static String a = "RSSAV1wsc2s314SAamk";

    private d() {
    }

    public static Map<String, String> a(Map<String, String> map) {
        Map<String, String> hashMap = new HashMap();
        for (Entry entry : map.entrySet()) {
            StringBuilder stringBuilder;
            Object obj = (String) entry.getKey();
            Object obj2 = (String) entry.getValue();
            if (obj.length() > 40) {
                stringBuilder = new StringBuilder();
                stringBuilder.append("cd key length over:");
                stringBuilder.append(obj);
                a.a(stringBuilder.toString());
                obj = obj.substring(0, 40);
            }
            if (obj2 != null && obj2.length() > 1024) {
                stringBuilder = new StringBuilder();
                stringBuilder.append("cd value length over:");
                stringBuilder.append(obj2);
                a.a(stringBuilder.toString());
                obj2 = obj2.substring(0, 1024);
            }
            hashMap.put(obj, obj2);
        }
        return hashMap;
    }

    public static boolean a(final Application application, final b bVar) {
        if (application == null) {
            com.samsung.a.a.a.a.i.d.a("context cannot be null");
            return false;
        } else if (bVar == null) {
            com.samsung.a.a.a.a.i.d.a("Configuration cannot be null");
            return false;
        } else if (!TextUtils.isEmpty(bVar.b()) || bVar.e()) {
            if (bVar.d()) {
                if (bVar.h() == null) {
                    com.samsung.a.a.a.a.i.d.a("If you want to use In App Logging, you should implement UserAgreement interface");
                    return false;
                } else if (c.a && !a(application, "com.sec.spp.permission.TOKEN", false)) {
                    com.samsung.a.a.a.a.i.d.a("SamsungAnalytics2 need to define 'com.sec.spp.permission.TOKEN_XXXX' permission in AndroidManifest");
                    return false;
                }
            } else if (!a(application, "com.sec.spp.permission.TOKEN", false)) {
                com.samsung.a.a.a.a.i.d.a("If you want to use DLC Logger, define 'com.sec.spp.permission.TOKEN_XXXX' permission in AndroidManifest");
                return false;
            } else if (!TextUtils.isEmpty(bVar.b())) {
                com.samsung.a.a.a.a.i.d.a("This mode is not allowed to set device Id");
                return false;
            } else if (!TextUtils.isEmpty(bVar.f())) {
                com.samsung.a.a.a.a.i.d.a("This mode is not allowed to set user Id");
                return false;
            }
            if (bVar.i() == null) {
                com.samsung.a.a.a.a.i.d.a("you should set the UI version");
                return false;
            }
            if (VERSION.SDK_INT >= 24) {
                UserManager userManager = (UserManager) application.getSystemService("user");
                if (!(userManager == null || userManager.isUserUnlocked())) {
                    a.c("The user has not unlocked the device.");
                    BroadcastReceiver anonymousClass1 = new BroadcastReceiver() {
                        public void onReceive(Context context, Intent intent) {
                            StringBuilder stringBuilder = new StringBuilder();
                            stringBuilder.append("receive ");
                            stringBuilder.append(intent.getAction());
                            a.b(stringBuilder.toString());
                            e.a(application, bVar);
                        }
                    };
                    IntentFilter intentFilter = new IntentFilter();
                    intentFilter.addAction("android.intent.action.BOOT_COMPLETED");
                    intentFilter.addAction("android.intent.action.USER_UNLOCKED");
                    application.registerReceiver(anonymousClass1, intentFilter);
                    return false;
                }
            }
            return true;
        } else {
            com.samsung.a.a.a.a.i.d.a("Device Id is empty, set Device Id or enable auto device id");
            return false;
        }
    }

    public static boolean a() {
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
                a.b("cf feature is supported");
            } else {
                a.b("feature is not supported");
            }
            return booleanValue;
        } catch (Exception e) {
            a.b("Floating feature is not supported (non-samsung device)");
            a.a(d.class, e);
            return false;
        }
    }

    public static boolean a(Context context, String str, boolean z) {
        try {
            PackageInfo packageInfo = context.getPackageManager().getPackageInfo(context.getPackageName(), KeycodeConstants.META_CTRL_ON);
            if (packageInfo.requestedPermissions != null) {
                for (String str2 : packageInfo.requestedPermissions) {
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
            a.a(d.class, e);
        }
        return false;
    }

    /* JADX WARNING: Removed duplicated region for block: B:5:0x002b A:{Splitter: B:3:0x0004, ExcHandler: java.security.NoSuchAlgorithmException (r7_4 'e' java.lang.Exception)} */
    /* JADX WARNING: Missing block: B:5:0x002b, code:
            r7 = move-exception;
     */
    /* JADX WARNING: Missing block: B:6:0x002c, code:
            com.samsung.a.a.a.a.i.a.a(com.samsung.a.a.a.a.d.d.class, r7);
            r7 = null;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static String a(String str) {
        if (str == null) {
            return null;
        }
        try {
            MessageDigest.getInstance("SHA-256").update(str.getBytes("UTF-8"));
            str = String.format(Locale.US, "%064x", new Object[]{new BigInteger(1, r1.digest())});
        } catch (Exception e) {
        }
        return str;
    }
}
