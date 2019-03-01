package com.samsung.android.lxd.p064a;

import android.accounts.Account;
import android.accounts.AccountManager;
import android.app.Activity;
import android.app.ActivityManager;
import android.app.ActivityManager.MemoryInfo;
import android.app.AlertDialog.Builder;
import android.app.DownloadManager;
import android.app.DownloadManager.Request;
import android.app.Notification;
import android.app.Notification.Action;
import android.app.Notification.BigTextStyle;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ResolveInfo;
import android.content.res.AssetManager;
import android.graphics.Point;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkInfo;
import android.net.Uri;
import android.net.wifi.WifiManager;
import android.os.Build.VERSION;
import android.os.Environment;
import android.os.Handler;
import android.os.Looper;
import android.os.Parcelable;
import android.os.Process;
import android.os.SemSystemProperties;
import android.os.storage.StorageManager;
import android.os.storage.StorageVolume;
import android.provider.Settings.System;
import android.support.v4.content.C0309a;
import android.support.v4.content.FileProvider;
import android.telephony.TelephonyManager;
import android.text.SpannableString;
import android.text.TextUtils;
import android.text.format.DateFormat;
import android.text.method.LinkMovementMethod;
import android.text.style.StyleSpan;
import android.text.util.Linkify;
import android.util.LruCache;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.webkit.URLUtil;
import android.widget.TextView;
import com.samsung.android.desktopmode.SemDesktopModeManager;
import com.samsung.android.lxd.C0888d;
import com.samsung.android.lxd.C0899f;
import com.samsung.android.lxd.C1347a;
import com.samsung.android.lxd.DesktopReceiver;
import com.samsung.android.lxd.DiagnosticReceiver;
import com.samsung.android.lxd.DownloadClickReceiver;
import com.samsung.android.lxd.LxdApplication;
import com.samsung.android.lxd.R;
import com.samsung.android.lxd.SigninActivity;
import com.samsung.android.lxd.SplashActivity;
import com.samsung.android.lxd.processor.ExecutionType;
import com.samsung.android.lxd.processor.LxdException;
import com.samsung.android.lxd.processor.network.channel.pty.internal.KeycodeConstants;
import com.samsung.android.lxd.processor.utils.Utils;
import com.samsung.android.lxd.processor.utils.log.Log;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.regex.Pattern;

/* compiled from: Utils */
/* renamed from: com.samsung.android.lxd.a.o */
public class C0877o {
    /* renamed from: a */
    private static final String f2716a = "o";
    /* renamed from: b */
    private static final Context f2717b = LxdApplication.m3344a();
    /* renamed from: c */
    private static final Pattern f2718c = Pattern.compile("[^DdMm]*[Yy]+[^DdMm]*");
    /* renamed from: d */
    private static final Pattern f2719d = Pattern.compile("[^Dd.Mm]*");
    /* renamed from: e */
    private static final Pattern f2720e = Pattern.compile("[^DdMm]*[de]*[^DdMm]*[de]*[Yy]+");
    /* renamed from: f */
    private static LruCache<Long, String> f2721f = new LruCache(128);
    /* renamed from: g */
    private static SimpleDateFormat f2722g;
    /* renamed from: h */
    private static InputMethodManager f2723h = ((InputMethodManager) LxdApplication.m3344a().getSystemService("input_method"));
    /* renamed from: i */
    private static String f2724i;
    /* renamed from: j */
    private static String f2725j;

    /* renamed from: b */
    public static int m3481b(int i) {
        return i * 1024;
    }

    /* renamed from: c */
    public static int m3490c(Context context) {
        return -1;
    }

    /* renamed from: q */
    public static boolean m3532q() {
        return false;
    }

    /* renamed from: a */
    public static String m3460a(Context context, long j) {
        try {
            SimpleDateFormat simpleDateFormat = (SimpleDateFormat) DateFormat.getTimeFormat(context);
            if (f2722g == null || !f2722g.equals(simpleDateFormat)) {
                f2721f.evictAll();
            }
            f2722g = simpleDateFormat;
            String str = (String) f2721f.get(Long.valueOf(j));
            if (str != null || context == null) {
                return str;
            }
            context = TextUtils.equals(Locale.ENGLISH.getDisplayLanguage(), Locale.KOREA.getDisplayLanguage()) != null ? 1 : 2;
            Calendar instance = Calendar.getInstance();
            int i = instance.get(1);
            instance.setTimeInMillis(j);
            int i2 = instance.get(1);
            SimpleDateFormat simpleDateFormat2 = (SimpleDateFormat) java.text.DateFormat.getDateInstance(context, Locale.ENGLISH);
            SimpleDateFormat simpleDateFormat3 = (SimpleDateFormat) java.text.DateFormat.getTimeInstance(context, Locale.ENGLISH);
            simpleDateFormat3.applyPattern(f2722g.toLocalizedPattern());
            if (i2 == i) {
                if (Locale.getDefault().toString().equalsIgnoreCase("pt_BR")) {
                    simpleDateFormat2.applyPattern(f2720e.matcher(simpleDateFormat2.toPattern()).replaceAll(""));
                } else if (Locale.getDefault().toString().equalsIgnoreCase("lv_LV")) {
                    simpleDateFormat2.applyPattern(f2719d.matcher(simpleDateFormat2.toPattern()).replaceAll("").replace(".", " "));
                } else {
                    simpleDateFormat2.applyPattern(f2718c.matcher(simpleDateFormat2.toPattern()).replaceAll(""));
                }
            }
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(simpleDateFormat2.format(Long.valueOf(j)));
            stringBuilder.append(" ‎");
            stringBuilder.append(simpleDateFormat3.format(Long.valueOf(j)));
            str = stringBuilder.toString();
            f2721f.put(Long.valueOf(j), str);
            return str;
        } catch (Context context2) {
            StringBuilder stringBuilder2 = new StringBuilder();
            stringBuilder2.append("IllegalArgumentException");
            stringBuilder2.append(context2);
            throw new LxdException(stringBuilder2.toString());
        }
    }

    /* renamed from: a */
    public static String m3461a(Context context, String str) {
        CharSequence a = C0877o.m3458a();
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(context.getString(R.string.internal_storage));
        stringBuilder.append(File.separator);
        str = str.replace(a, stringBuilder.toString());
        if (!TextUtils.isEmpty(C0877o.m3497d())) {
            a = C0877o.m3497d();
            stringBuilder = new StringBuilder();
            stringBuilder.append(context.getString(R.string.sd_card));
            stringBuilder.append(File.separator);
            str = str.replace(a, stringBuilder.toString());
        }
        return str.replace("/", " > ");
    }

    /* renamed from: a */
    public static String m3462a(String str) {
        return str.substring(0, str.lastIndexOf(" > "));
    }

    /* renamed from: b */
    public static String m3487b(String str) {
        return str.substring(str.lastIndexOf(" > ") + 3, str.length());
    }

    /* renamed from: c */
    public static String m3492c(String str) {
        String str2 = f2716a;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("getWritablePath: origin: ");
        stringBuilder.append(str);
        Log.m3853d(str2, stringBuilder.toString());
        if (VERSION.SDK_INT < 28 || C0877o.m3500d(str)) {
            return str;
        }
        StringBuilder stringBuilder2 = new StringBuilder();
        stringBuilder2.append("/mnt/media_rw/");
        stringBuilder2.append(str.substring("/storage/".length() - 1));
        File file = new File(stringBuilder2.toString());
        if (!file.exists()) {
            return str;
        }
        str = f2716a;
        StringBuilder stringBuilder3 = new StringBuilder();
        stringBuilder3.append("getWritablePath writable path: ");
        stringBuilder3.append(file.getAbsolutePath());
        Log.m3857i(str, stringBuilder3.toString());
        return file.getAbsolutePath();
    }

    /* renamed from: a */
    public static String m3458a() {
        if (f2724i == null) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(Environment.getExternalStorageDirectory());
            stringBuilder.append(File.separator);
            f2724i = stringBuilder.toString();
        }
        return f2724i;
    }

    /* renamed from: b */
    public static String m3485b() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(C0877o.m3458a());
        stringBuilder.append("LoD");
        stringBuilder.append(File.separator);
        return stringBuilder.toString();
    }

    /* renamed from: c */
    public static void m3493c() {
        f2725j = null;
        C0877o.m3497d();
    }

    /* renamed from: d */
    public static synchronized String m3497d() {
        synchronized (C0877o.class) {
            String str;
            if (f2725j == null) {
                StorageManager storageManager = (StorageManager) f2717b.getSystemService("storage");
                if (storageManager == null) {
                    Log.m3855e(f2716a, "storageManager null");
                    str = f2725j;
                    return str;
                }
                for (StorageVolume storageVolume : storageManager.getStorageVolumes()) {
                    if (storageVolume.isRemovable()) {
                        String state = storageVolume.getState();
                        String semGetPath = storageVolume.semGetPath();
                        String str2 = f2716a;
                        StringBuilder stringBuilder = new StringBuilder();
                        stringBuilder.append("removable storage path: ");
                        stringBuilder.append(semGetPath);
                        stringBuilder.append(", status: ");
                        stringBuilder.append(state);
                        stringBuilder.append(", ");
                        stringBuilder.append(storageVolume.getDescription(f2717b));
                        stringBuilder.append(", ");
                        stringBuilder.append(storageVolume.semGetSubSystem());
                        Log.m3857i(str2, stringBuilder.toString());
                        if (state != null && state.equals("mounted") && "sd".equals(storageVolume.semGetSubSystem())) {
                            if (!semGetPath.endsWith(File.separator)) {
                                StringBuilder stringBuilder2 = new StringBuilder();
                                stringBuilder2.append(semGetPath);
                                stringBuilder2.append(File.separator);
                                semGetPath = stringBuilder2.toString();
                            }
                            f2725j = semGetPath;
                        }
                    }
                }
            }
            str = f2725j;
            return str;
        }
    }

    /* renamed from: d */
    public static boolean m3500d(String str) {
        if (!str.endsWith(File.separator)) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(str);
            stringBuilder.append(File.separator);
            str = stringBuilder.toString();
        }
        return str.startsWith(C0877o.m3458a()) != null ? true : null;
    }

    /* renamed from: a */
    public static boolean m3474a(Context context) {
        ActivityManager activityManager = (ActivityManager) context.getSystemService("activity");
        MemoryInfo memoryInfo = new MemoryInfo();
        activityManager.getMemoryInfo(memoryInfo);
        return memoryInfo.lowMemory;
    }

    /* renamed from: b */
    public static long m3483b(Context context) {
        ActivityManager activityManager = (ActivityManager) context.getSystemService("activity");
        MemoryInfo memoryInfo = new MemoryInfo();
        activityManager.getMemoryInfo(memoryInfo);
        return memoryInfo.totalMem;
    }

    /* renamed from: a */
    public static void m3467a(Context context, String str, String str2, OnClickListener onClickListener, boolean z, OnClickListener onClickListener2) {
        Builder builder = new Builder(context);
        builder.setTitle(str);
        builder.setIcon(17301659);
        builder.setMessage(str2);
        builder.setCancelable(null);
        context = "OK";
        if (z) {
            builder.setNegativeButton("No", onClickListener2);
            context = "Yes";
        }
        builder.setPositiveButton(context, onClickListener);
        builder.show();
    }

    /* renamed from: e */
    public static boolean m3503e() {
        return SemSystemProperties.getBoolean("lxd.vmdebugmode", false);
    }

    /* renamed from: a */
    public static int m3455a(File file) {
        int ceil = (int) Math.ceil(C0877o.m3501e(file.getAbsolutePath()) / 1.073741824E9d);
        file = (int) (file.length() / 1073741824);
        String str = f2716a;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("retrieveMinImageSize: minFsSize: ");
        stringBuilder.append(ceil);
        stringBuilder.append(", fileSize: ");
        stringBuilder.append(file);
        Log.m3853d(str, stringBuilder.toString());
        if (ceil > 0 && file >= ceil) {
            return ceil;
        }
        Log.m3855e(f2716a, "Failed to retrieve minimum image size!");
        return file;
    }

    /* renamed from: b */
    public static int m3482b(File file) {
        return ((int) ((file.length() + file.getUsableSpace()) / 1073741824)) - 1;
    }

    /* renamed from: a */
    public static boolean m3476a(MotionEvent motionEvent) {
        return motionEvent.getSource() == 20482 ? true : null;
    }

    /* renamed from: d */
    public static boolean m3499d(Context context) {
        return context.getResources().getConfiguration().semDesktopModeEnabled == 1;
    }

    /* renamed from: e */
    public static boolean m3504e(Context context) {
        return (C0877o.m3507f() || C0877o.m3499d(context) != null) ? null : true;
    }

    /* renamed from: f */
    public static boolean m3507f() {
        String str = SemSystemProperties.get("ro.build.characteristics");
        return str != null && str.contains("tablet");
    }

    /* renamed from: f */
    public static boolean m3508f(Context context) {
        return (C0877o.m3507f() && C0877o.m3499d(context) == null) ? true : null;
    }

    /* renamed from: g */
    public static boolean m3511g(Context context) {
        return ((SemDesktopModeManager) context.getSystemService(SemDesktopModeManager.class)).getDesktopModeState().getDisplayType() == 101 ? true : null;
    }

    /* renamed from: a */
    public static boolean m3473a(Activity activity) {
        String str;
        try {
            activity = activity.getPackageManager().getPackageInfo("com.sec.android.app.myfiles", 0);
            str = activity.versionName;
            activity = activity.versionCode;
            String substring = str.substring(0, str.indexOf("."));
            String str2 = f2716a;
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("isMyFilesSupportIntentResult : ");
            stringBuilder.append(str);
            stringBuilder.append(", ");
            stringBuilder.append(activity);
            stringBuilder.append(", osPrefix : ");
            stringBuilder.append(substring);
            Log.m3853d(str2, stringBuilder.toString());
            if ((substring.equalsIgnoreCase("5") && activity >= 500502472) || VERSION.SDK_INT == 28) {
                return true;
            }
        } catch (Activity activity2) {
            str = f2716a;
            StringBuilder stringBuilder2 = new StringBuilder();
            stringBuilder2.append("isMyFilesSupportIntentResult failed to get version!");
            stringBuilder2.append(activity2.toString());
            Log.m3855e(str, stringBuilder2.toString());
        }
        return false;
    }

    /* renamed from: h */
    public static String m3512h(Context context) {
        if (C0877o.m3511g(context)) {
            return "Stand alone";
        }
        if (C0877o.m3499d(context)) {
            return "DeX monitor";
        }
        if (C0877o.m3504e(context)) {
            return "Phone";
        }
        return C0877o.m3508f(context) != null ? "Tablet" : null;
    }

    /* renamed from: g */
    public static boolean m3510g() {
        return LxdApplication.m3344a().getSharedPreferences("prefs", 0).getBoolean("authorized", false);
    }

    /* renamed from: i */
    public static void m3516i(Context context) {
        if ((context instanceof SigninActivity) != null) {
            LxdApplication.m3344a().getSharedPreferences("prefs", 0).edit().putBoolean("authorized", true).apply();
        }
    }

    /* renamed from: h */
    public static boolean m3513h() {
        return f2717b.getSharedPreferences("prefs", 0).getBoolean("agreeErrorReport", false);
    }

    /* renamed from: i */
    public static void m3515i() {
        f2717b.getSharedPreferences("prefs", 0).edit().putBoolean("agreeErrorReport", true).apply();
    }

    /* renamed from: j */
    public static boolean m3520j() {
        return f2717b.getSharedPreferences("prefs", 0).getBoolean("storagePermission", false);
    }

    /* renamed from: k */
    public static void m3521k() {
        f2717b.getSharedPreferences("prefs", 0).edit().putBoolean("storagePermission", true).apply();
    }

    /* renamed from: l */
    public static Class<?> m3524l() {
        return SplashActivity.class;
    }

    /* renamed from: j */
    public static Intent m3518j(Context context) {
        Intent intent = new Intent(context, C0877o.m3524l());
        intent.setAction("android.intent.action.MAIN");
        intent.addCategory("android.intent.category.LAUNCHER");
        intent.setFlags(270532608);
        return intent;
    }

    /* renamed from: a */
    public static ArrayList<String> m3464a(String[] strArr) {
        String str;
        StringBuilder stringBuilder;
        ArrayList<String> arrayList = new ArrayList();
        try {
            strArr = Runtime.getRuntime().exec(strArr);
            strArr.waitFor();
            str = f2716a;
            stringBuilder = new StringBuilder();
            stringBuilder.append("runExecCmd: ");
            stringBuilder.append(strArr.toString());
            Log.m3857i(str, stringBuilder.toString());
            if (strArr.exitValue() == 0) {
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(strArr.getInputStream()));
                while (true) {
                    strArr = bufferedReader.readLine();
                    if (strArr == null) {
                        break;
                    }
                    arrayList.add(strArr);
                }
            }
        } catch (String[] strArr2) {
            str = f2716a;
            stringBuilder = new StringBuilder();
            stringBuilder.append("runExecCmd: ");
            stringBuilder.append(strArr2.toString());
            Log.m3855e(str, stringBuilder.toString());
        }
        return arrayList;
    }

    /* renamed from: e */
    public static double m3501e(String str) {
        String str2 = f2716a;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("getMinFsSize: ");
        stringBuilder.append(str);
        Log.m3853d(str2, stringBuilder.toString());
        try {
            str = C0877o.m3492c(str);
            CharSequence charSequence = "Estimated minimum size of the filesystem:";
            str = C0877o.m3464a(new String[]{"resize2fs", "-P", str}).iterator();
            while (str.hasNext()) {
                String str3 = (String) str.next();
                String str4 = f2716a;
                StringBuilder stringBuilder2 = new StringBuilder();
                stringBuilder2.append("s: ");
                stringBuilder2.append(str3);
                Log.m3853d(str4, stringBuilder2.toString());
                if (str3.contains(charSequence)) {
                    return (double) (((long) Integer.parseInt(str3.replace(charSequence, "").trim())) * 4096);
                }
            }
            Log.m3855e(f2716a, "can't find prefix, ");
        } catch (String str5) {
            str2 = f2716a;
            stringBuilder = new StringBuilder();
            stringBuilder.append("getMinFsSize: Exception ");
            stringBuilder.append(str5.toString());
            Log.m3855e(str2, stringBuilder.toString());
            str5.printStackTrace();
        }
        return 0.0d;
    }

    /* renamed from: f */
    public static int m3505f(String str) {
        return (int) (new File(str).length() / 1048576);
    }

    /* renamed from: g */
    public static int m3509g(String str) {
        return (int) (new File(str).length() / 1073741824);
    }

    /* renamed from: a */
    public static String m3459a(long j) {
        j = ((double) j) / 4742290407621132288L;
        NumberFormat instance = NumberFormat.getInstance();
        instance.setMaximumFractionDigits(2);
        instance.setGroupingUsed(true);
        return instance.format(j);
    }

    /* renamed from: a */
    public static int m3454a(int i) {
        return i / 1024;
    }

    /* renamed from: c */
    public static String m3491c(int i) {
        Context a = LxdApplication.m3344a();
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(a.getResources().getString(R.string.exceeds_max_length_prefix));
        stringBuilder.append(" ");
        stringBuilder.append(String.valueOf(i));
        stringBuilder.append(" ");
        stringBuilder.append(a.getResources().getString(R.string.exceeds_max_length_postfix));
        return stringBuilder.toString();
    }

    /* renamed from: b */
    public static java.lang.String m3486b(android.content.Context r1, java.lang.String r2) {
        /* JADX: method processing error */
/*
Error: java.lang.NullPointerException
	at jadx.core.dex.visitors.regions.ProcessTryCatchRegions.searchTryCatchDominators(ProcessTryCatchRegions.java:75)
	at jadx.core.dex.visitors.regions.ProcessTryCatchRegions.process(ProcessTryCatchRegions.java:45)
	at jadx.core.dex.visitors.regions.RegionMakerVisitor.postProcessRegions(RegionMakerVisitor.java:63)
	at jadx.core.dex.visitors.regions.RegionMakerVisitor.visit(RegionMakerVisitor.java:58)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:31)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:17)
	at jadx.core.ProcessClass.process(ProcessClass.java:34)
	at jadx.core.ProcessClass.processDependencies(ProcessClass.java:56)
	at jadx.core.ProcessClass.process(ProcessClass.java:39)
	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:282)
	at jadx.api.JavaClass.decompile(JavaClass.java:62)
	at jadx.api.JadxDecompiler.lambda$appendSourcesSave$0(JadxDecompiler.java:200)
	at jadx.api.JadxDecompiler$$Lambda$8/1201484275.run(Unknown Source)
*/
        /*
        r1 = r1.getPackageManager();	 Catch:{ NameNotFoundException -> 0x000c }
        r0 = 1;	 Catch:{ NameNotFoundException -> 0x000c }
        r1 = r1.getPackageInfo(r2, r0);	 Catch:{ NameNotFoundException -> 0x000c }
        r1 = r1.versionName;	 Catch:{ NameNotFoundException -> 0x000c }
        return r1;
    L_0x000c:
        r1 = 0;
        return r1;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.samsung.android.lxd.a.o.b(android.content.Context, java.lang.String):java.lang.String");
    }

    /* renamed from: a */
    public static int m3456a(String str, int i, boolean z) {
        String str2 = f2716a;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("checkImageInfo: ");
        stringBuilder.append(str);
        stringBuilder.append(", requestSize: ");
        stringBuilder.append(i);
        stringBuilder.append(", ");
        stringBuilder.append(z);
        Log.m3853d(str2, stringBuilder.toString());
        File file = new File(str);
        if (file != null) {
            if (file.isFile()) {
                if (z) {
                    return null;
                }
                if (i <= C0877o.m3482b(file)) {
                    return 1;
                }
                str = f2716a;
                z = new StringBuilder();
                z.append("no space: request: ");
                z.append(i);
                z.append(", max: ");
                z.append(C0877o.m3482b(file));
                Log.m3855e(str, z.toString());
                return 3;
            }
        }
        i = f2716a;
        z = new StringBuilder();
        z.append("no file path: : ");
        z.append(str);
        Log.m3855e(i, z.toString());
        return 2;
    }

    /* renamed from: c */
    public static boolean m3494c(android.content.Context r1, java.lang.String r2) {
        /* JADX: method processing error */
/*
Error: java.lang.NullPointerException
	at jadx.core.dex.visitors.regions.ProcessTryCatchRegions.searchTryCatchDominators(ProcessTryCatchRegions.java:75)
	at jadx.core.dex.visitors.regions.ProcessTryCatchRegions.process(ProcessTryCatchRegions.java:45)
	at jadx.core.dex.visitors.regions.RegionMakerVisitor.postProcessRegions(RegionMakerVisitor.java:63)
	at jadx.core.dex.visitors.regions.RegionMakerVisitor.visit(RegionMakerVisitor.java:58)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:31)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:17)
	at jadx.core.ProcessClass.process(ProcessClass.java:34)
	at jadx.core.ProcessClass.processDependencies(ProcessClass.java:56)
	at jadx.core.ProcessClass.process(ProcessClass.java:39)
	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:282)
	at jadx.api.JavaClass.decompile(JavaClass.java:62)
	at jadx.api.JadxDecompiler.lambda$appendSourcesSave$0(JadxDecompiler.java:200)
	at jadx.api.JadxDecompiler$$Lambda$8/1201484275.run(Unknown Source)
*/
        /*
        r1 = r1.getPackageManager();
        r0 = 1;
        r1.getPackageInfo(r2, r0);	 Catch:{ NameNotFoundException -> 0x0009 }
        return r0;
    L_0x0009:
        r1 = 0;
        return r1;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.samsung.android.lxd.a.o.c(android.content.Context, java.lang.String):boolean");
    }

    /* renamed from: a */
    public static boolean m3475a(android.content.Context r1, java.lang.String r2, int r3) {
        /* JADX: method processing error */
/*
Error: java.lang.NullPointerException
	at jadx.core.dex.visitors.regions.ProcessTryCatchRegions.searchTryCatchDominators(ProcessTryCatchRegions.java:75)
	at jadx.core.dex.visitors.regions.ProcessTryCatchRegions.process(ProcessTryCatchRegions.java:45)
	at jadx.core.dex.visitors.regions.RegionMakerVisitor.postProcessRegions(RegionMakerVisitor.java:63)
	at jadx.core.dex.visitors.regions.RegionMakerVisitor.visit(RegionMakerVisitor.java:58)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:31)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:17)
	at jadx.core.ProcessClass.process(ProcessClass.java:34)
	at jadx.core.ProcessClass.processDependencies(ProcessClass.java:56)
	at jadx.core.ProcessClass.process(ProcessClass.java:39)
	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:282)
	at jadx.api.JavaClass.decompile(JavaClass.java:62)
	at jadx.api.JadxDecompiler.lambda$appendSourcesSave$0(JadxDecompiler.java:200)
	at jadx.api.JadxDecompiler$$Lambda$8/1201484275.run(Unknown Source)
*/
        /*
        r0 = 0;
        r1 = r1.getPackageManager();	 Catch:{ NameNotFoundException -> 0x000f }
        r1 = r1.getPackageInfo(r2, r0);	 Catch:{ NameNotFoundException -> 0x000f }
        r1 = r1.versionCode;	 Catch:{ NameNotFoundException -> 0x000f }
        if (r1 < r3) goto L_0x000e;
    L_0x000d:
        r0 = 1;
    L_0x000e:
        return r0;
    L_0x000f:
        return r0;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.samsung.android.lxd.a.o.a(android.content.Context, java.lang.String, int):boolean");
    }

    /* renamed from: m */
    public static boolean m3527m() {
        boolean z = true;
        if (C0877o.m3510g()) {
            return true;
        }
        int nstVersion = Utils.getNstVersion();
        int i = C0877o.m3507f() ? 5 : 4;
        String str = f2716a;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("isSupportedBinary: binary version: ");
        stringBuilder.append(nstVersion);
        stringBuilder.append(", required version: ");
        stringBuilder.append(i);
        Log.m3857i(str, stringBuilder.toString());
        if (nstVersion < i) {
            z = false;
        }
        return z;
    }

    /* renamed from: n */
    public static boolean m3528n() {
        return Utils.getNstVersion() > 5;
    }

    /* renamed from: o */
    public static boolean m3530o() {
        int usableSpace = (int) LxdApplication.m3344a().getApplicationContext().getFilesDir().getUsableSpace();
        if (KeycodeConstants.META_CAPS_LOCK_ON >= usableSpace) {
            return true;
        }
        String str = f2716a;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("isNoSpace usableSpace: ");
        stringBuilder.append(usableSpace);
        Log.m3855e(str, stringBuilder.toString());
        return false;
    }

    /* renamed from: d */
    public static String m3498d(Context context, String str) {
        BufferedReader bufferedReader;
        StringBuilder stringBuilder = new StringBuilder();
        try {
            bufferedReader = new BufferedReader(new InputStreamReader(context.getAssets().open(str), "UTF-8"));
            for (str = bufferedReader.readLine(); str != null; str = bufferedReader.readLine()) {
                stringBuilder.append('\n');
                stringBuilder.append(str);
            }
            if (bufferedReader != null) {
                bufferedReader.close();
            }
        } catch (Context context2) {
            str = f2716a;
            StringBuilder stringBuilder2 = new StringBuilder();
            stringBuilder2.append("IOException:");
            stringBuilder2.append(context2.toString());
            Log.m3855e(str, stringBuilder2.toString());
        } catch (Throwable th) {
            context2.addSuppressed(th);
        }
        return stringBuilder.toString();
    }

    /* renamed from: k */
    public static boolean m3522k(Context context) {
        context = ((WindowManager) context.getSystemService("window")).getDefaultDisplay();
        Point point = new Point();
        context.getSize(point);
        return (point.y > point.x ? null : 1) ^ 1;
    }

    /* renamed from: l */
    public static boolean m3525l(Context context) {
        return C0877o.m3522k(context) ^ 1;
    }

    /* renamed from: m */
    public static Point m3526m(Context context) {
        context = ((WindowManager) context.getSystemService("window")).getDefaultDisplay();
        Point point = new Point();
        context.getSize(point);
        return point;
    }

    /* renamed from: a */
    public static float m3453a(Context context, float f) {
        return f * context.getResources().getDisplayMetrics().density;
    }

    /* renamed from: a */
    public static void m3470a(Runnable runnable) {
        C0877o.m3471a(runnable, 0, false);
    }

    /* renamed from: a */
    public static void m3472a(Runnable runnable, boolean z) {
        C0877o.m3471a(runnable, 0, z);
    }

    /* renamed from: a */
    public static void m3471a(Runnable runnable, long j, boolean z) {
        if (z || Thread.currentThread() != Looper.getMainLooper().getThread()) {
            new Handler(Looper.getMainLooper()).postDelayed(runnable, j);
        } else {
            runnable.run();
        }
    }

    /* renamed from: b */
    public static void m3488b(Runnable runnable) {
        new Handler(Looper.getMainLooper()).removeCallbacks(runnable);
    }

    /* renamed from: p */
    public static void m3531p() {
        Log.m3857i(f2716a, "killSelf");
        C0899f.m3596f();
        DesktopReceiver.m3300a().m3304b();
        DiagnosticReceiver.m3305a().m3309c();
        C0888d.m3552a().m3557c();
        C0853a.m3348a().m3355e();
        DownloadClickReceiver.m3339a().m3343c();
        Log.flushLogger();
        Process.killProcess(Process.myPid());
        System.exit(1);
    }

    /* renamed from: a */
    public static boolean m3477a(View view, int i) {
        if (view != null) {
            return f2723h.showSoftInput(view, i);
        }
        Log.m3855e(f2716a, "showSoftInput: currentFocusView is null");
        return null;
    }

    /* renamed from: b */
    public static boolean m3489b(View view, int i) {
        if (view != null) {
            return f2723h.hideSoftInputFromWindow(view.getWindowToken(), i);
        }
        Log.m3855e(f2716a, "hideSoftInputFromWindow: currentFocusView is null");
        return null;
    }

    /* renamed from: a */
    public static void m3465a(int i, int i2) {
        f2723h.toggleSoftInput(i, i2);
    }

    /* renamed from: L */
    private static NotificationManager m3452L() {
        return (NotificationManager) LxdApplication.m3344a().getSystemService("notification");
    }

    /* renamed from: a */
    public static void m3466a(Activity activity, ExecutionType executionType) {
        String str = f2716a;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("showDesktopNotification: ");
        stringBuilder.append(executionType);
        Log.m3853d(str, stringBuilder.toString());
        if (executionType == ExecutionType.INVALID) {
            Log.m3855e(f2716a, "showDesktopNotification: invalid execute type");
            return;
        }
        boolean c = ((C1347a) activity).mo707c();
        executionType = executionType == ExecutionType.CLI ? 1 : null;
        if (executionType == null || !c) {
            C0874l.m3433a("018", String.valueOf(1801), executionType != null ? 0 : 1);
            CharSequence string = activity.getString(executionType != null ? R.string.undock_cli_notice_msg : R.string.undock_gui_notice_msg);
            NotificationChannel notificationChannel = new NotificationChannel(f2717b.getPackageName(), f2717b.getPackageName(), 4);
            notificationChannel.setDescription(LxdApplication.m3344a().getPackageName());
            notificationChannel.enableLights(true);
            notificationChannel.setLightColor(-16711936);
            notificationChannel.enableVibration(true);
            notificationChannel.setVibrationPattern(new long[]{100, 200, 100, 200});
            notificationChannel.setLockscreenVisibility(0);
            C0877o.m3452L().createNotificationChannel(notificationChannel);
            Notification.Builder autoCancel = new Notification.Builder(LxdApplication.m3344a(), f2717b.getPackageName()).setSmallIcon(R.drawable.ic_icon_mono).setShowWhen(true).setWhen(System.currentTimeMillis()).setOngoing(true).setContentText(string).setColor(activity.getColor(R.color.LightTheme.primary)).setStyle(new BigTextStyle().bigText(string)).setAutoCancel(false);
            if (executionType != null) {
                executionType = new Intent(activity, DesktopReceiver.class);
                executionType.putExtra("unDockNotifyActionId", 1);
                autoCancel.addAction(new Action.Builder(null, activity.getString(R.string.dismiss), PendingIntent.getBroadcast(activity, 1, executionType, 134217728)).build());
            } else {
                executionType = new Intent(activity, DesktopReceiver.class);
                executionType.putExtra("unDockNotifyActionId", 2);
                autoCancel.addAction(new Action.Builder(null, activity.getString(R.string.close_app), PendingIntent.getBroadcast(activity, 2, executionType, 134217728)).build());
            }
            executionType = new Intent(activity, C0877o.m3524l());
            executionType.setAction("android.intent.action.MAIN");
            executionType.addCategory("android.intent.category.LAUNCHER");
            executionType.setFlags(270532608);
            autoCancel.setContentIntent(PendingIntent.getActivity(activity, 0, executionType, 134217728));
            C0877o.m3452L().notify(6600, autoCancel.build());
            return;
        }
        Log.m3857i(f2716a, "showDesktopNotification: no need to show ");
    }

    /* renamed from: r */
    public static void m3533r() {
        Log.m3853d(f2716a, "hideDesktopNotification");
        C0877o.m3452L().cancel(6600);
    }

    /* renamed from: s */
    public static boolean m3534s() {
        return SemSystemProperties.getBoolean("lxd.adbwifi", false);
    }

    /* renamed from: a */
    public static void m3469a(File file, List<File> list) {
        file = file.listFiles();
        if (file == null) {
            Log.m3855e(f2716a, "Failed to find img file : no files to check with!");
            return;
        }
        try {
            for (File file2 : file) {
                if (file2.isDirectory()) {
                    C0877o.m3469a(file2, (List) list);
                } else if (file2.canWrite()) {
                    list.add(file2);
                } else {
                    String str = f2716a;
                    StringBuilder stringBuilder = new StringBuilder();
                    stringBuilder.append("can't write: ");
                    stringBuilder.append(file2.getAbsolutePath());
                    Log.m3857i(str, stringBuilder.toString());
                }
            }
        } catch (File file3) {
            list = f2716a;
            StringBuilder stringBuilder2 = new StringBuilder();
            stringBuilder2.append("findFileList ");
            stringBuilder2.append(file3.toString());
            Log.m3855e(list, stringBuilder2.toString());
        }
    }

    /* renamed from: c */
    public static boolean m3495c(File file) {
        try {
            if (file.isDirectory()) {
                String[] list = file.list();
                for (String file2 : list) {
                    if (!C0877o.m3495c(new File(file, file2))) {
                        return false;
                    }
                }
            }
            return file.delete();
        } catch (File file3) {
            String str = f2716a;
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("deleteDir: ");
            stringBuilder.append(file3.toString());
            Log.m3855e(str, stringBuilder.toString());
            return false;
        }
    }

    /* renamed from: d */
    public static Uri m3496d(File file) {
        Context a = LxdApplication.m3344a();
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(LxdApplication.m3344a().getPackageName());
        stringBuilder.append(".FileProvider");
        return FileProvider.m866a(a, stringBuilder.toString(), file);
    }

    /* renamed from: a */
    public static boolean m3480a(String[] strArr, String str, String str2, Uri uri) {
        Intent intent = new Intent("android.intent.action.SEND");
        intent.addFlags(1);
        intent.putExtra("android.intent.extra.STREAM", uri);
        intent.setType("message/rfc822");
        intent.putExtra("android.intent.extra.EMAIL", strArr);
        intent.putExtra("android.intent.extra.SUBJECT", str);
        intent.putExtra("android.intent.extra.TEXT", str2);
        strArr = C0877o.m3457a(intent);
        if (strArr != null) {
            strArr.addFlags(268435456);
            LxdApplication.m3344a().startActivity(strArr);
        } else {
            Log.m3855e(f2716a, "sendMail: can't find email client");
        }
        return true;
    }

    /* renamed from: a */
    public static Intent m3457a(Intent intent) {
        ArrayList arrayList = new ArrayList();
        for (ResolveInfo resolveInfo : LxdApplication.m3344a().getPackageManager().queryIntentActivities(new Intent("android.intent.action.SENDTO", Uri.fromParts("mailto", "lod.sec@samsung.com", null)), 0)) {
            Intent intent2 = new Intent(intent);
            intent2.setPackage(resolveInfo.activityInfo.packageName);
            arrayList.add(intent2);
        }
        if (arrayList.isEmpty() == null) {
            return Intent.createChooser((Intent) arrayList.remove(0), LxdApplication.m3344a().getString(R.string.report_via_email)).putExtra("android.intent.extra.INITIAL_INTENTS", (Parcelable[]) arrayList.toArray(new Parcelable[arrayList.size()]));
        }
        return null;
    }

    /* renamed from: a */
    public static boolean m3479a(String str, String str2) {
        InputStream open;
        String str3;
        IOException e;
        IOException iOException;
        String str4;
        StringBuilder stringBuilder;
        String str5 = f2716a;
        StringBuilder stringBuilder2 = new StringBuilder();
        stringBuilder2.append("copyAssets: ");
        stringBuilder2.append(str);
        stringBuilder2.append(", path: ");
        stringBuilder2.append(str2);
        Log.m3853d(str5, stringBuilder2.toString());
        AssetManager assets = LxdApplication.m3344a().getAssets();
        int i = 0;
        try {
            InputStream inputStream;
            OutputStream fileOutputStream;
            String[] list = assets.list(str);
            if (list == null) {
                Log.m3857i(f2716a, "no file in asset");
                return false;
            }
            File file = new File(str2);
            if (file.exists()) {
                C0877o.m3495c(file);
            }
            file.mkdir();
            int length = list.length;
            while (i < length) {
                String str6 = list[i];
                inputStream = null;
                try {
                    StringBuilder stringBuilder3 = new StringBuilder();
                    stringBuilder3.append(str);
                    stringBuilder3.append(File.separator);
                    stringBuilder3.append(str6);
                    open = assets.open(stringBuilder3.toString());
                    try {
                        fileOutputStream = new FileOutputStream(new File(str2, str6));
                        try {
                            C0877o.m3478a(open, fileOutputStream);
                            if (open != null) {
                                try {
                                    open.close();
                                } catch (IOException e2) {
                                    str3 = f2716a;
                                    stringBuilder3 = new StringBuilder();
                                    stringBuilder3.append("in close: ");
                                    stringBuilder3.append(e2.toString());
                                    Log.m3853d(str3, stringBuilder3.toString());
                                }
                            }
                            if (fileOutputStream != null) {
                                try {
                                    fileOutputStream.close();
                                } catch (IOException e3) {
                                    e2 = e3;
                                    str3 = f2716a;
                                    stringBuilder3 = new StringBuilder();
                                    stringBuilder3.append("out close: ");
                                    stringBuilder3.append(e2.toString());
                                    Log.m3853d(str3, stringBuilder3.toString());
                                    i++;
                                }
                            }
                        } catch (IOException e4) {
                            InputStream inputStream2 = open;
                            iOException = e4;
                            inputStream = inputStream2;
                            try {
                                str4 = f2716a;
                                stringBuilder = new StringBuilder();
                                stringBuilder.append("Failed to copy asset file: ");
                                stringBuilder.append(str6);
                                stringBuilder.append(", ");
                                stringBuilder.append(iOException.toString());
                                Log.m3855e(str4, stringBuilder.toString());
                                if (inputStream != null) {
                                    try {
                                        inputStream.close();
                                    } catch (IOException e22) {
                                        str3 = f2716a;
                                        stringBuilder3 = new StringBuilder();
                                        stringBuilder3.append("in close: ");
                                        stringBuilder3.append(e22.toString());
                                        Log.m3853d(str3, stringBuilder3.toString());
                                    }
                                }
                                if (fileOutputStream == null) {
                                    try {
                                        fileOutputStream.close();
                                    } catch (IOException e5) {
                                        e22 = e5;
                                        str3 = f2716a;
                                        stringBuilder3 = new StringBuilder();
                                        stringBuilder3.append("out close: ");
                                        stringBuilder3.append(e22.toString());
                                        Log.m3853d(str3, stringBuilder3.toString());
                                        i++;
                                    }
                                }
                                i++;
                            } catch (Throwable th) {
                                str = th;
                            }
                        } catch (Throwable th2) {
                            str = th2;
                        }
                    } catch (IOException e6) {
                        fileOutputStream = null;
                        inputStream = open;
                        iOException = e6;
                        str4 = f2716a;
                        stringBuilder = new StringBuilder();
                        stringBuilder.append("Failed to copy asset file: ");
                        stringBuilder.append(str6);
                        stringBuilder.append(", ");
                        stringBuilder.append(iOException.toString());
                        Log.m3855e(str4, stringBuilder.toString());
                        if (inputStream != null) {
                            inputStream.close();
                        }
                        if (fileOutputStream == null) {
                            fileOutputStream.close();
                        }
                        i++;
                    } catch (Throwable th3) {
                        str = th3;
                        fileOutputStream = null;
                    }
                } catch (IOException e7) {
                    iOException = e7;
                    fileOutputStream = null;
                    str4 = f2716a;
                    stringBuilder = new StringBuilder();
                    stringBuilder.append("Failed to copy asset file: ");
                    stringBuilder.append(str6);
                    stringBuilder.append(", ");
                    stringBuilder.append(iOException.toString());
                    Log.m3855e(str4, stringBuilder.toString());
                    if (inputStream != null) {
                        inputStream.close();
                    }
                    if (fileOutputStream == null) {
                        fileOutputStream.close();
                    }
                    i++;
                } catch (Throwable th4) {
                    str = th4;
                    fileOutputStream = null;
                }
                i++;
            }
            return true;
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (String str22) {
                    str5 = f2716a;
                    stringBuilder2 = new StringBuilder();
                    stringBuilder2.append("in close: ");
                    stringBuilder2.append(str22.toString());
                    Log.m3853d(str5, stringBuilder2.toString());
                }
            }
            if (fileOutputStream != null) {
                try {
                    fileOutputStream.close();
                } catch (String str222) {
                    str5 = f2716a;
                    stringBuilder2 = new StringBuilder();
                    stringBuilder2.append("out close: ");
                    stringBuilder2.append(str222.toString());
                    Log.m3853d(str5, stringBuilder2.toString());
                }
            }
            throw str;
            if (fileOutputStream != null) {
                fileOutputStream.close();
            }
            throw str;
            throw str;
            inputStream = open;
            if (inputStream != null) {
                inputStream.close();
            }
            if (fileOutputStream != null) {
                fileOutputStream.close();
            }
            throw str;
        } catch (String str7) {
            str222 = f2716a;
            StringBuilder stringBuilder4 = new StringBuilder();
            stringBuilder4.append("copyAssets: ");
            stringBuilder4.append(str7.toString());
            Log.m3855e(str222, stringBuilder4.toString());
            return false;
        }
    }

    /* renamed from: a */
    public static boolean m3478a(InputStream inputStream, OutputStream outputStream) {
        byte[] bArr = new byte[1024];
        while (true) {
            int read = inputStream.read(bArr);
            if (read == -1) {
                return true;
            }
            outputStream.write(bArr, 0, read);
        }
    }

    /* renamed from: t */
    public static boolean m3535t() {
        return SemSystemProperties.getBoolean("lxd.planD_enabled", false);
    }

    /* renamed from: h */
    public static boolean m3514h(String str) {
        if (str.contains("planD") == null && Utils.isDebug() == null) {
            return null;
        }
        return true;
    }

    /* renamed from: u */
    public static String m3536u() {
        try {
            return f2717b.getPackageManager().getPackageInfo(f2717b.getPackageName(), 64).versionName;
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }

    /* renamed from: v */
    public static int m3537v() {
        try {
            return f2717b.getPackageManager().getPackageInfo(f2717b.getPackageName(), 64).versionCode;
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    /* renamed from: a */
    public static String m3463a(SimpleDateFormat simpleDateFormat) {
        simpleDateFormat = simpleDateFormat.format(new Date(System.currentTimeMillis()));
        String str = f2716a;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("getTime: ");
        stringBuilder.append(simpleDateFormat);
        Log.m3853d(str, stringBuilder.toString());
        return simpleDateFormat;
    }

    /* renamed from: i */
    public static boolean m3517i(String str) {
        str = C0877o.m3523k(str);
        return (str == null || str.length <= null) ? null : true;
    }

    /* renamed from: j */
    public static String m3519j(String str) {
        str = C0877o.m3523k(str);
        return str.length > 0 ? str[0].name : null;
    }

    /* renamed from: k */
    public static Account[] m3523k(String str) {
        return AccountManager.get(f2717b).getAccountsByType(str);
    }

    /* renamed from: w */
    public static Intent m3538w() {
        Intent intent = new Intent("com.msc.action.samsungaccount.SIGNIN_POPUP");
        intent.putExtra("client_id", "4vsm90v979");
        intent.putExtra("client_secret", "34EB46F6E1A2B5D69E3F57DC04397DFE");
        intent.putExtra("mypackage", f2717b.getPackageName());
        intent.putExtra("OSP_VER", "OSP_02");
        intent.putExtra("theme", "dark");
        return intent;
    }

    /* renamed from: x */
    public static Intent m3539x() {
        return new Intent("com.msc.action.samsungaccount.accountsetting");
    }

    /* renamed from: n */
    public static boolean m3529n(Context context) {
        boolean z = true;
        if (System.getInt(context.getContentResolver(), "samsung_errorlog_agree", 0) != 1) {
            z = false;
        }
        context = f2716a;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Report Diagnostic Agreement (");
        stringBuilder.append(z);
        stringBuilder.append(")");
        Log.m3853d(context, stringBuilder.toString());
        return z;
    }

    /* renamed from: y */
    public static boolean m3540y() {
        return SemSystemProperties.getBoolean("lxd.debug_monitor", true);
    }

    /* renamed from: z */
    public static void m3541z() {
        /* JADX: method processing error */
/*
Error: java.lang.NullPointerException
	at jadx.core.dex.visitors.regions.ProcessTryCatchRegions.searchTryCatchDominators(ProcessTryCatchRegions.java:75)
	at jadx.core.dex.visitors.regions.ProcessTryCatchRegions.process(ProcessTryCatchRegions.java:45)
	at jadx.core.dex.visitors.regions.RegionMakerVisitor.postProcessRegions(RegionMakerVisitor.java:63)
	at jadx.core.dex.visitors.regions.RegionMakerVisitor.visit(RegionMakerVisitor.java:58)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:31)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:17)
	at jadx.core.ProcessClass.process(ProcessClass.java:34)
	at jadx.core.ProcessClass.processDependencies(ProcessClass.java:56)
	at jadx.core.ProcessClass.process(ProcessClass.java:39)
	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:282)
	at jadx.api.JavaClass.decompile(JavaClass.java:62)
	at jadx.api.JadxDecompiler.lambda$appendSourcesSave$0(JadxDecompiler.java:200)
	at jadx.api.JadxDecompiler$$Lambda$8/1201484275.run(Unknown Source)
*/
        /*
        r0 = f2716a;
        r1 = "clearSharedPreferences";
        com.samsung.android.lxd.processor.utils.log.Log.m3864w(r0, r1);
        r0 = new java.io.File;	 Catch:{ Exception -> 0x0065 }
        r1 = new java.lang.StringBuilder;	 Catch:{ Exception -> 0x0065 }
        r1.<init>();	 Catch:{ Exception -> 0x0065 }
        r2 = f2717b;	 Catch:{ Exception -> 0x0065 }
        r2 = r2.getFilesDir();	 Catch:{ Exception -> 0x0065 }
        r2 = r2.getParent();	 Catch:{ Exception -> 0x0065 }
        r1.append(r2);	 Catch:{ Exception -> 0x0065 }
        r2 = "/shared_prefs/";	 Catch:{ Exception -> 0x0065 }
        r1.append(r2);	 Catch:{ Exception -> 0x0065 }
        r1 = r1.toString();	 Catch:{ Exception -> 0x0065 }
        r0.<init>(r1);	 Catch:{ Exception -> 0x0065 }
        r1 = r0.list();	 Catch:{ Exception -> 0x0065 }
        if (r1 == 0) goto L_0x006f;	 Catch:{ Exception -> 0x0065 }
    L_0x002d:
        r2 = 0;	 Catch:{ Exception -> 0x0065 }
        r3 = r2;	 Catch:{ Exception -> 0x0065 }
    L_0x002f:
        r4 = r1.length;	 Catch:{ Exception -> 0x0065 }
        if (r3 >= r4) goto L_0x0050;	 Catch:{ Exception -> 0x0065 }
    L_0x0032:
        r4 = f2717b;	 Catch:{ Exception -> 0x0065 }
        r5 = r1[r3];	 Catch:{ Exception -> 0x0065 }
        r6 = ".xml";	 Catch:{ Exception -> 0x0065 }
        r7 = "";	 Catch:{ Exception -> 0x0065 }
        r5 = r5.replace(r6, r7);	 Catch:{ Exception -> 0x0065 }
        r4 = r4.getSharedPreferences(r5, r2);	 Catch:{ Exception -> 0x0065 }
        r4 = r4.edit();	 Catch:{ Exception -> 0x0065 }
        r4 = r4.clear();	 Catch:{ Exception -> 0x0065 }
        r4.commit();	 Catch:{ Exception -> 0x0065 }
        r3 = r3 + 1;
        goto L_0x002f;
    L_0x0050:
        r3 = 1000; // 0x3e8 float:1.401E-42 double:4.94E-321;
        java.lang.Thread.sleep(r3);	 Catch:{ InterruptedException -> 0x0055 }
    L_0x0055:
        r3 = r1.length;	 Catch:{ Exception -> 0x0065 }
        if (r2 >= r3) goto L_0x006f;	 Catch:{ Exception -> 0x0065 }
    L_0x0058:
        r3 = new java.io.File;	 Catch:{ Exception -> 0x0065 }
        r4 = r1[r2];	 Catch:{ Exception -> 0x0065 }
        r3.<init>(r0, r4);	 Catch:{ Exception -> 0x0065 }
        r3.delete();	 Catch:{ Exception -> 0x0065 }
        r2 = r2 + 1;
        goto L_0x0055;
    L_0x0065:
        r0 = move-exception;
        r1 = f2716a;
        r2 = r0.getMessage();
        com.samsung.android.lxd.processor.utils.log.Log.m3856e(r1, r2, r0);
    L_0x006f:
        return;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.samsung.android.lxd.a.o.z():void");
    }

    /* renamed from: A */
    public static void m3441A() {
        Log.m3864w(f2716a, "clearApplicationData");
        try {
            if (f2717b.getCacheDir() != null && f2717b.getCacheDir().getParent() != null) {
                File file = new File(f2717b.getCacheDir().getParent());
                String str = f2716a;
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append("clearApplicationData: appDir = ");
                stringBuilder.append(file.toString());
                Log.m3853d(str, stringBuilder.toString());
                if (file.exists() && file.isDirectory() && file.list() != null) {
                    for (String str2 : file.list()) {
                        if (!(str2 == null || str2.equals("lib") || str2.equals("analytics_cache"))) {
                            C0877o.m3495c(new File(file, str2));
                            String str3 = f2716a;
                            StringBuilder stringBuilder2 = new StringBuilder();
                            stringBuilder2.append("File Deleted: /data/data/APP_PACKAGE/");
                            stringBuilder2.append(str2);
                            Log.m3853d(str3, stringBuilder2.toString());
                        }
                    }
                }
            }
        } catch (Throwable e) {
            Log.m3856e(f2716a, e.getMessage(), e);
        }
    }

    /* renamed from: B */
    public static boolean m3442B() {
        Log.m3853d(f2716a, "needToReset: ");
        if (!C0877o.m3510g() || C0877o.m3517i("com.osp.app.signin")) {
            return false;
        }
        Log.m3857i(f2716a, "sa removed after authorized");
        return true;
    }

    /* renamed from: C */
    public static boolean m3443C() {
        Log.m3857i(f2716a, "resetApp: ");
        C0859d.m3373b();
        C0877o.m3541z();
        C0877o.m3441A();
        return true;
    }

    /* renamed from: D */
    public static boolean m3444D() {
        ConnectivityManager connectivityManager = (ConnectivityManager) f2717b.getSystemService("connectivity");
        for (Network networkInfo : connectivityManager.getAllNetworks()) {
            NetworkInfo networkInfo2 = connectivityManager.getNetworkInfo(networkInfo);
            if (networkInfo2 != null) {
                String str = f2716a;
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append("networkType: ");
                stringBuilder.append(networkInfo2.getType());
                stringBuilder.append(", connected: ");
                stringBuilder.append(networkInfo2.isConnected());
                Log.m3853d(str, stringBuilder.toString());
                if (networkInfo2.isConnected() && networkInfo2.getType() == 1) {
                    Log.m3857i(f2716a, "WIFI connected");
                    return true;
                }
            }
        }
        return false;
    }

    /* renamed from: E */
    public static void m3445E() {
        WifiManager wifiManager = (WifiManager) f2717b.getSystemService("wifi");
        int semGetWifiApState = wifiManager.semGetWifiApState();
        String str = f2716a;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("enable wifi needed. is hotspot enabled ");
        stringBuilder.append(semGetWifiApState);
        Log.m3857i(str, stringBuilder.toString());
        if (11 == wifiManager.semGetWifiApState()) {
            wifiManager.setWifiEnabled(true);
        }
    }

    /* renamed from: F */
    public static Intent m3446F() {
        Intent intent = new Intent();
        intent.setAction("android.net.wifi.PICK_WIFI_NETWORK");
        intent.putExtra("wifi_enable_next_on_connect", true);
        intent.putExtra("extra_prefs_show_button_bar", true);
        return intent;
    }

    /* renamed from: G */
    public static boolean m3447G() {
        return C0309a.m931b(f2717b, "android.permission.WRITE_EXTERNAL_STORAGE") == 0;
    }

    /* renamed from: b */
    public static SpannableString m3484b(String str, String str2) {
        SpannableString spannableString = new SpannableString(str);
        if (!str.contains(str2)) {
            return spannableString;
        }
        str = str.indexOf(str2);
        spannableString.setSpan(new StyleSpan(1), str, str2.length() + str, 0);
        return spannableString;
    }

    /* renamed from: e */
    public static File m3502e(File file) {
        if (!file.exists()) {
            return file;
        }
        File parentFile = file.getParentFile();
        if (parentFile.listFiles() == null) {
            return file;
        }
        for (File file2 : parentFile.listFiles()) {
            if (file.getName().equalsIgnoreCase(file2.getName())) {
                file = f2716a;
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append("find File name: ");
                stringBuilder.append(file2.getName());
                Log.m3857i(file, stringBuilder.toString());
                return file2;
            }
        }
        return file;
    }

    /* renamed from: f */
    public static String m3506f(File file) {
        StringBuilder stringBuilder;
        String str = null;
        String replace;
        try {
            replace = file.getAbsolutePath().replace("/storage/emulated/", "/data/media/");
            String str2 = f2716a;
            StringBuilder stringBuilder2 = new StringBuilder();
            stringBuilder2.append("checkLabel: ");
            stringBuilder2.append(file.getAbsolutePath());
            Log.m3857i(str2, stringBuilder2.toString());
            file = Class.forName("android.os.SELinux");
            String str3 = (String) file.getMethod("getFileContext", new Class[]{String.class}).invoke(file, new Object[]{replace});
            try {
                str = f2716a;
                StringBuilder stringBuilder3 = new StringBuilder();
                stringBuilder3.append("fileContext:  ");
                stringBuilder3.append(str3);
                Log.m3857i(str, stringBuilder3.toString());
                return str3;
            } catch (Exception e) {
                Exception exception = e;
                str = str3;
                file = exception;
                replace = f2716a;
                stringBuilder = new StringBuilder();
                stringBuilder.append("exception : ");
                stringBuilder.append(file.toString());
                Log.m3855e(replace, stringBuilder.toString());
                return str;
            }
        } catch (Exception e2) {
            file = e2;
            replace = f2716a;
            stringBuilder = new StringBuilder();
            stringBuilder.append("exception : ");
            stringBuilder.append(file.toString());
            Log.m3855e(replace, stringBuilder.toString());
            return str;
        }
    }

    /* renamed from: H */
    public static String m3448H() {
        return SemSystemProperties.get("lxd.debugurl", "");
    }

    /* renamed from: I */
    public static void m3449I() {
        String string;
        StringBuilder stringBuilder;
        try {
            SharedPreferences sharedPreferences = LxdApplication.m3344a().getSharedPreferences("prefs", 0);
            string = sharedPreferences.getString(String.valueOf("downloadUrl"), null);
            String string2 = sharedPreferences.getString(String.valueOf("downloadPos"), null);
            Boolean valueOf = Boolean.valueOf(sharedPreferences.getBoolean(String.valueOf("downloadWifiOnly"), false));
            Request request = new Request(Uri.parse(string));
            request.setMimeType(null);
            StringBuilder stringBuilder2 = new StringBuilder();
            stringBuilder2.append("LinuxOnDeX - ");
            stringBuilder2.append(URLUtil.guessFileName(string, string2, null));
            request.setTitle(stringBuilder2.toString());
            request.allowScanningByMediaScanner();
            request.setAllowedOverMetered(valueOf.booleanValue() ^ true);
            request.setNotificationVisibility(1);
            request.setDestinationInExternalPublicDir("LoD", URLUtil.guessFileName(string, string2, null));
            Long valueOf2 = Long.valueOf(((DownloadManager) LxdApplication.m3344a().getApplicationContext().getSystemService("download")).enqueue(request));
            LxdApplication.m3344a().getSharedPreferences("prefs", 0).edit().putLong(String.valueOf("downloadId"), valueOf2.longValue()).apply();
            string = f2716a;
            stringBuilder = new StringBuilder();
            stringBuilder.append("start Download : ");
            stringBuilder.append(valueOf2);
            Log.m3857i(string, stringBuilder.toString());
        } catch (Exception e) {
            string = f2716a;
            stringBuilder = new StringBuilder();
            stringBuilder.append("getDownloadedFileName Error ! ");
            stringBuilder.append(e.toString());
            Log.m3855e(string, stringBuilder.toString());
        }
    }

    /* renamed from: a */
    public static void m3468a(TextView textView, CharSequence charSequence, int i) {
        try {
            String str = f2716a;
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("addLink: ");
            stringBuilder.append(i);
            Log.m3853d(str, stringBuilder.toString());
            CharSequence spannableString = new SpannableString(charSequence);
            Linkify.addLinks(spannableString, i);
            textView.setText(spannableString);
            textView.setMovementMethod(LinkMovementMethod.getInstance());
        } catch (TextView textView2) {
            charSequence = f2716a;
            i = new StringBuilder();
            i.append("fail to addLink: ");
            i.append(textView2.toString());
            Log.m3855e(charSequence, i.toString());
        }
    }

    /* renamed from: J */
    public static String m3450J() {
        String simOperator = ((TelephonyManager) f2717b.getSystemService("phone")).getSimOperator();
        return (simOperator == null || simOperator.length() <= 3) ? "" : simOperator.substring(0, 3);
    }

    /* renamed from: K */
    public static String m3451K() {
        String simOperator = ((TelephonyManager) f2717b.getSystemService("phone")).getSimOperator();
        return (simOperator == null || simOperator.length() <= 3) ? "" : simOperator.substring(3);
    }
}
