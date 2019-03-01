package com.samsung.android.lxd.a;

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
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager.NameNotFoundException;
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
import android.support.v4.content.FileProvider;
import android.telephony.TelephonyManager;
import android.text.SpannableString;
import android.text.TextUtils;
import android.text.format.DateFormat;
import android.text.method.LinkMovementMethod;
import android.text.style.StyleSpan;
import android.text.util.Linkify;
import android.util.LruCache;
import android.view.Display;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.webkit.URLUtil;
import android.widget.TextView;
import com.samsung.android.desktopmode.SemDesktopModeManager;
import com.samsung.android.lxd.DesktopReceiver;
import com.samsung.android.lxd.DiagnosticReceiver;
import com.samsung.android.lxd.DownloadClickReceiver;
import com.samsung.android.lxd.LxdApplication;
import com.samsung.android.lxd.R;
import com.samsung.android.lxd.SigninActivity;
import com.samsung.android.lxd.SplashActivity;
import com.samsung.android.lxd.a;
import com.samsung.android.lxd.d;
import com.samsung.android.lxd.f;
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
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.regex.Pattern;

/* compiled from: Utils */
public class o {
    private static final String a = "o";
    private static final Context b = LxdApplication.a();
    private static final Pattern c = Pattern.compile("[^DdMm]*[Yy]+[^DdMm]*");
    private static final Pattern d = Pattern.compile("[^Dd.Mm]*");
    private static final Pattern e = Pattern.compile("[^DdMm]*[de]*[^DdMm]*[de]*[Yy]+");
    private static LruCache<Long, String> f = new LruCache(128);
    private static SimpleDateFormat g;
    private static InputMethodManager h = ((InputMethodManager) LxdApplication.a().getSystemService("input_method"));
    private static String i;
    private static String j;

    public static int b(int i) {
        return i * 1024;
    }

    public static int c(Context context) {
        return -1;
    }

    public static boolean q() {
        return false;
    }

    public static String a(Context context, long j) {
        try {
            SimpleDateFormat simpleDateFormat = (SimpleDateFormat) DateFormat.getTimeFormat(context);
            if (g == null || !g.equals(simpleDateFormat)) {
                f.evictAll();
            }
            g = simpleDateFormat;
            String str = (String) f.get(Long.valueOf(j));
            if (str != null || context == null) {
                return str;
            }
            int i = TextUtils.equals(Locale.ENGLISH.getDisplayLanguage(), Locale.KOREA.getDisplayLanguage()) ? 1 : 2;
            Calendar instance = Calendar.getInstance();
            int i2 = instance.get(1);
            instance.setTimeInMillis(j);
            int i3 = instance.get(1);
            SimpleDateFormat simpleDateFormat2 = (SimpleDateFormat) java.text.DateFormat.getDateInstance(i, Locale.ENGLISH);
            SimpleDateFormat simpleDateFormat3 = (SimpleDateFormat) java.text.DateFormat.getTimeInstance(i, Locale.ENGLISH);
            simpleDateFormat3.applyPattern(g.toLocalizedPattern());
            if (i3 == i2) {
                if (Locale.getDefault().toString().equalsIgnoreCase("pt_BR")) {
                    simpleDateFormat2.applyPattern(e.matcher(simpleDateFormat2.toPattern()).replaceAll(""));
                } else if (Locale.getDefault().toString().equalsIgnoreCase("lv_LV")) {
                    simpleDateFormat2.applyPattern(d.matcher(simpleDateFormat2.toPattern()).replaceAll("").replace(".", " "));
                } else {
                    simpleDateFormat2.applyPattern(c.matcher(simpleDateFormat2.toPattern()).replaceAll(""));
                }
            }
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(simpleDateFormat2.format(Long.valueOf(j)));
            stringBuilder.append(" ‎");
            stringBuilder.append(simpleDateFormat3.format(Long.valueOf(j)));
            str = stringBuilder.toString();
            f.put(Long.valueOf(j), str);
            return str;
        } catch (IllegalArgumentException e) {
            StringBuilder stringBuilder2 = new StringBuilder();
            stringBuilder2.append("IllegalArgumentException");
            stringBuilder2.append(e);
            throw new LxdException(stringBuilder2.toString());
        }
    }

    public static String a(Context context, String str) {
        CharSequence a = a();
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(context.getString(R.string.internal_storage));
        stringBuilder.append(File.separator);
        str = str.replace(a, stringBuilder.toString());
        if (!TextUtils.isEmpty(d())) {
            a = d();
            stringBuilder = new StringBuilder();
            stringBuilder.append(context.getString(R.string.sd_card));
            stringBuilder.append(File.separator);
            str = str.replace(a, stringBuilder.toString());
        }
        return str.replace("/", " > ");
    }

    public static String a(String str) {
        return str.substring(0, str.lastIndexOf(" > "));
    }

    public static String b(String str) {
        return str.substring(str.lastIndexOf(" > ") + 3, str.length());
    }

    public static String c(String str) {
        String str2 = a;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("getWritablePath: origin: ");
        stringBuilder.append(str);
        Log.d(str2, stringBuilder.toString());
        if (VERSION.SDK_INT < 28 || d(str)) {
            return str;
        }
        StringBuilder stringBuilder2 = new StringBuilder();
        stringBuilder2.append("/mnt/media_rw/");
        stringBuilder2.append(str.substring("/storage/".length() - 1));
        File file = new File(stringBuilder2.toString());
        if (!file.exists()) {
            return str;
        }
        str = a;
        StringBuilder stringBuilder3 = new StringBuilder();
        stringBuilder3.append("getWritablePath writable path: ");
        stringBuilder3.append(file.getAbsolutePath());
        Log.i(str, stringBuilder3.toString());
        return file.getAbsolutePath();
    }

    public static String a() {
        if (i == null) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(Environment.getExternalStorageDirectory());
            stringBuilder.append(File.separator);
            i = stringBuilder.toString();
        }
        return i;
    }

    public static String b() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(a());
        stringBuilder.append("LoD");
        stringBuilder.append(File.separator);
        return stringBuilder.toString();
    }

    public static void c() {
        j = null;
        d();
    }

    public static synchronized String d() {
        synchronized (o.class) {
            String str;
            if (j == null) {
                StorageManager storageManager = (StorageManager) b.getSystemService("storage");
                if (storageManager == null) {
                    Log.e(a, "storageManager null");
                    str = j;
                    return str;
                }
                for (StorageVolume storageVolume : storageManager.getStorageVolumes()) {
                    if (storageVolume.isRemovable()) {
                        String state = storageVolume.getState();
                        String semGetPath = storageVolume.semGetPath();
                        String str2 = a;
                        StringBuilder stringBuilder = new StringBuilder();
                        stringBuilder.append("removable storage path: ");
                        stringBuilder.append(semGetPath);
                        stringBuilder.append(", status: ");
                        stringBuilder.append(state);
                        stringBuilder.append(", ");
                        stringBuilder.append(storageVolume.getDescription(b));
                        stringBuilder.append(", ");
                        stringBuilder.append(storageVolume.semGetSubSystem());
                        Log.i(str2, stringBuilder.toString());
                        if (state != null && state.equals("mounted") && "sd".equals(storageVolume.semGetSubSystem())) {
                            if (!semGetPath.endsWith(File.separator)) {
                                StringBuilder stringBuilder2 = new StringBuilder();
                                stringBuilder2.append(semGetPath);
                                stringBuilder2.append(File.separator);
                                semGetPath = stringBuilder2.toString();
                            }
                            j = semGetPath;
                        }
                    }
                }
            }
            str = j;
            return str;
        }
    }

    public static boolean d(String str) {
        if (!str.endsWith(File.separator)) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(str);
            stringBuilder.append(File.separator);
            str = stringBuilder.toString();
        }
        return str.startsWith(a());
    }

    public static boolean a(Context context) {
        ActivityManager activityManager = (ActivityManager) context.getSystemService("activity");
        MemoryInfo memoryInfo = new MemoryInfo();
        activityManager.getMemoryInfo(memoryInfo);
        return memoryInfo.lowMemory;
    }

    public static long b(Context context) {
        ActivityManager activityManager = (ActivityManager) context.getSystemService("activity");
        MemoryInfo memoryInfo = new MemoryInfo();
        activityManager.getMemoryInfo(memoryInfo);
        return memoryInfo.totalMem;
    }

    public static void a(Context context, String str, String str2, OnClickListener onClickListener, boolean z, OnClickListener onClickListener2) {
        Builder builder = new Builder(context);
        builder.setTitle(str);
        builder.setIcon(17301659);
        builder.setMessage(str2);
        builder.setCancelable(false);
        CharSequence charSequence = "OK";
        if (z) {
            builder.setNegativeButton("No", onClickListener2);
            charSequence = "Yes";
        }
        builder.setPositiveButton(charSequence, onClickListener);
        builder.show();
    }

    public static boolean e() {
        return SemSystemProperties.getBoolean("lxd.vmdebugmode", false);
    }

    public static int a(File file) {
        int ceil = (int) Math.ceil(e(file.getAbsolutePath()) / 1.073741824E9d);
        int length = (int) (file.length() / 1073741824);
        String str = a;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("retrieveMinImageSize: minFsSize: ");
        stringBuilder.append(ceil);
        stringBuilder.append(", fileSize: ");
        stringBuilder.append(length);
        Log.d(str, stringBuilder.toString());
        if (ceil > 0 && length >= ceil) {
            return ceil;
        }
        Log.e(a, "Failed to retrieve minimum image size!");
        return length;
    }

    public static int b(File file) {
        return ((int) ((file.length() + file.getUsableSpace()) / 1073741824)) - 1;
    }

    public static boolean a(MotionEvent motionEvent) {
        return motionEvent.getSource() == 20482;
    }

    public static boolean d(Context context) {
        return context.getResources().getConfiguration().semDesktopModeEnabled == 1;
    }

    public static boolean e(Context context) {
        return (f() || d(context)) ? false : true;
    }

    public static boolean f() {
        String str = SemSystemProperties.get("ro.build.characteristics");
        return str != null && str.contains("tablet");
    }

    public static boolean f(Context context) {
        return f() && !d(context);
    }

    public static boolean g(Context context) {
        return ((SemDesktopModeManager) context.getSystemService(SemDesktopModeManager.class)).getDesktopModeState().getDisplayType() == 101;
    }

    public static boolean a(Activity activity) {
        String str;
        try {
            PackageInfo packageInfo = activity.getPackageManager().getPackageInfo("com.sec.android.app.myfiles", 0);
            str = packageInfo.versionName;
            int i = packageInfo.versionCode;
            String substring = str.substring(0, str.indexOf("."));
            String str2 = a;
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("isMyFilesSupportIntentResult : ");
            stringBuilder.append(str);
            stringBuilder.append(", ");
            stringBuilder.append(i);
            stringBuilder.append(", osPrefix : ");
            stringBuilder.append(substring);
            Log.d(str2, stringBuilder.toString());
            if ((substring.equalsIgnoreCase("5") && i >= 500502472) || VERSION.SDK_INT == 28) {
                return true;
            }
        } catch (NameNotFoundException e) {
            str = a;
            StringBuilder stringBuilder2 = new StringBuilder();
            stringBuilder2.append("isMyFilesSupportIntentResult failed to get version!");
            stringBuilder2.append(e.toString());
            Log.e(str, stringBuilder2.toString());
        }
        return false;
    }

    public static String h(Context context) {
        if (g(context)) {
            return "Stand alone";
        }
        if (d(context)) {
            return "DeX monitor";
        }
        if (e(context)) {
            return "Phone";
        }
        return f(context) ? "Tablet" : null;
    }

    public static boolean g() {
        return LxdApplication.a().getSharedPreferences("prefs", 0).getBoolean("authorized", false);
    }

    public static void i(Context context) {
        if (context instanceof SigninActivity) {
            LxdApplication.a().getSharedPreferences("prefs", 0).edit().putBoolean("authorized", true).apply();
        }
    }

    public static boolean h() {
        return b.getSharedPreferences("prefs", 0).getBoolean("agreeErrorReport", false);
    }

    public static void i() {
        b.getSharedPreferences("prefs", 0).edit().putBoolean("agreeErrorReport", true).apply();
    }

    public static boolean j() {
        return b.getSharedPreferences("prefs", 0).getBoolean("storagePermission", false);
    }

    public static void k() {
        b.getSharedPreferences("prefs", 0).edit().putBoolean("storagePermission", true).apply();
    }

    public static Class<?> l() {
        return SplashActivity.class;
    }

    public static Intent j(Context context) {
        Intent intent = new Intent(context, l());
        intent.setAction("android.intent.action.MAIN");
        intent.addCategory("android.intent.category.LAUNCHER");
        intent.setFlags(270532608);
        return intent;
    }

    public static ArrayList<String> a(String[] strArr) {
        ArrayList<String> arrayList = new ArrayList();
        String str;
        StringBuilder stringBuilder;
        try {
            Process exec = Runtime.getRuntime().exec(strArr);
            exec.waitFor();
            str = a;
            stringBuilder = new StringBuilder();
            stringBuilder.append("runExecCmd: ");
            stringBuilder.append(exec.toString());
            Log.i(str, stringBuilder.toString());
            if (exec.exitValue() == 0) {
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(exec.getInputStream()));
                while (true) {
                    String readLine = bufferedReader.readLine();
                    if (readLine == null) {
                        break;
                    }
                    arrayList.add(readLine);
                }
            }
        } catch (Exception e) {
            str = a;
            stringBuilder = new StringBuilder();
            stringBuilder.append("runExecCmd: ");
            stringBuilder.append(e.toString());
            Log.e(str, stringBuilder.toString());
        }
        return arrayList;
    }

    public static double e(String str) {
        String str2 = a;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("getMinFsSize: ");
        stringBuilder.append(str);
        Log.d(str2, stringBuilder.toString());
        try {
            str = c(str);
            CharSequence charSequence = "Estimated minimum size of the filesystem:";
            Iterator it = a(new String[]{"resize2fs", "-P", str}).iterator();
            while (it.hasNext()) {
                String str3 = (String) it.next();
                String str4 = a;
                StringBuilder stringBuilder2 = new StringBuilder();
                stringBuilder2.append("s: ");
                stringBuilder2.append(str3);
                Log.d(str4, stringBuilder2.toString());
                if (str3.contains(charSequence)) {
                    return (double) (((long) Integer.parseInt(str3.replace(charSequence, "").trim())) * 4096);
                }
            }
            Log.e(a, "can't find prefix, ");
        } catch (Exception e) {
            str2 = a;
            stringBuilder = new StringBuilder();
            stringBuilder.append("getMinFsSize: Exception ");
            stringBuilder.append(e.toString());
            Log.e(str2, stringBuilder.toString());
            e.printStackTrace();
        }
        return 0.0d;
    }

    public static int f(String str) {
        return (int) (new File(str).length() / 1048576);
    }

    public static int g(String str) {
        return (int) (new File(str).length() / 1073741824);
    }

    public static String a(long j) {
        double d = ((double) j) / 1.073741824E9d;
        NumberFormat instance = NumberFormat.getInstance();
        instance.setMaximumFractionDigits(2);
        instance.setGroupingUsed(true);
        return instance.format(d);
    }

    public static int a(int i) {
        return i / 1024;
    }

    public static String c(int i) {
        Context a = LxdApplication.a();
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(a.getResources().getString(R.string.exceeds_max_length_prefix));
        stringBuilder.append(" ");
        stringBuilder.append(String.valueOf(i));
        stringBuilder.append(" ");
        stringBuilder.append(a.getResources().getString(R.string.exceeds_max_length_postfix));
        return stringBuilder.toString();
    }

    public static String b(Context context, String str) {
        try {
            return context.getPackageManager().getPackageInfo(str, 1).versionName;
        } catch (NameNotFoundException unused) {
            return null;
        }
    }

    public static int a(String str, int i, boolean z) {
        String str2 = a;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("checkImageInfo: ");
        stringBuilder.append(str);
        stringBuilder.append(", requestSize: ");
        stringBuilder.append(i);
        stringBuilder.append(", ");
        stringBuilder.append(z);
        Log.d(str2, stringBuilder.toString());
        File file = new File(str);
        StringBuilder stringBuilder2;
        if (file == null || !file.isFile()) {
            String str3 = a;
            stringBuilder2 = new StringBuilder();
            stringBuilder2.append("no file path: : ");
            stringBuilder2.append(str);
            Log.e(str3, stringBuilder2.toString());
            return 2;
        } else if (z) {
            return 0;
        } else {
            if (i <= b(file)) {
                return 1;
            }
            str = a;
            stringBuilder2 = new StringBuilder();
            stringBuilder2.append("no space: request: ");
            stringBuilder2.append(i);
            stringBuilder2.append(", max: ");
            stringBuilder2.append(b(file));
            Log.e(str, stringBuilder2.toString());
            return 3;
        }
    }

    public static boolean c(Context context, String str) {
        try {
            context.getPackageManager().getPackageInfo(str, 1);
            return true;
        } catch (NameNotFoundException unused) {
            return false;
        }
    }

    public static boolean a(Context context, String str, int i) {
        boolean z = false;
        try {
            if (context.getPackageManager().getPackageInfo(str, 0).versionCode >= i) {
                z = true;
            }
            return z;
        } catch (NameNotFoundException unused) {
            return false;
        }
    }

    public static boolean m() {
        boolean z = true;
        if (g()) {
            return true;
        }
        int nstVersion = Utils.getNstVersion();
        int i = f() ? 5 : 4;
        String str = a;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("isSupportedBinary: binary version: ");
        stringBuilder.append(nstVersion);
        stringBuilder.append(", required version: ");
        stringBuilder.append(i);
        Log.i(str, stringBuilder.toString());
        if (nstVersion < i) {
            z = false;
        }
        return z;
    }

    public static boolean n() {
        return Utils.getNstVersion() > 5;
    }

    public static boolean o() {
        int usableSpace = (int) LxdApplication.a().getApplicationContext().getFilesDir().getUsableSpace();
        if (KeycodeConstants.META_CAPS_LOCK_ON >= usableSpace) {
            return true;
        }
        String str = a;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("isNoSpace usableSpace: ");
        stringBuilder.append(usableSpace);
        Log.e(str, stringBuilder.toString());
        return false;
    }

    public static String d(Context context, String str) {
        StringBuilder stringBuilder = new StringBuilder();
        BufferedReader bufferedReader;
        try {
            bufferedReader = new BufferedReader(new InputStreamReader(context.getAssets().open(str), "UTF-8"));
            for (str = bufferedReader.readLine(); str != null; str = bufferedReader.readLine()) {
                stringBuilder.append(10);
                stringBuilder.append(str);
            }
            if (bufferedReader != null) {
                bufferedReader.close();
            }
        } catch (IOException e) {
            str = a;
            StringBuilder stringBuilder2 = new StringBuilder();
            stringBuilder2.append("IOException:");
            stringBuilder2.append(e.toString());
            Log.e(str, stringBuilder2.toString());
        } catch (Throwable th) {
            r3.addSuppressed(th);
        }
        return stringBuilder.toString();
    }

    public static boolean k(Context context) {
        Display defaultDisplay = ((WindowManager) context.getSystemService("window")).getDefaultDisplay();
        Point point = new Point();
        defaultDisplay.getSize(point);
        return (point.y > point.x ? 0 : 1) ^ true;
    }

    public static boolean l(Context context) {
        return k(context) ^ 1;
    }

    public static Point m(Context context) {
        Display defaultDisplay = ((WindowManager) context.getSystemService("window")).getDefaultDisplay();
        Point point = new Point();
        defaultDisplay.getSize(point);
        return point;
    }

    public static float a(Context context, float f) {
        return f * context.getResources().getDisplayMetrics().density;
    }

    public static void a(Runnable runnable) {
        a(runnable, 0, false);
    }

    public static void a(Runnable runnable, boolean z) {
        a(runnable, 0, z);
    }

    public static void a(Runnable runnable, long j, boolean z) {
        if (z || Thread.currentThread() != Looper.getMainLooper().getThread()) {
            new Handler(Looper.getMainLooper()).postDelayed(runnable, j);
        } else {
            runnable.run();
        }
    }

    public static void b(Runnable runnable) {
        new Handler(Looper.getMainLooper()).removeCallbacks(runnable);
    }

    public static void p() {
        Log.i(a, "killSelf");
        f.f();
        DesktopReceiver.a().b();
        DiagnosticReceiver.a().c();
        d.a().c();
        a.a().e();
        DownloadClickReceiver.a().c();
        Log.flushLogger();
        Process.killProcess(Process.myPid());
        System.exit(1);
    }

    public static boolean a(View view, int i) {
        if (view != null) {
            return h.showSoftInput(view, i);
        }
        Log.e(a, "showSoftInput: currentFocusView is null");
        return false;
    }

    public static boolean b(View view, int i) {
        if (view != null) {
            return h.hideSoftInputFromWindow(view.getWindowToken(), i);
        }
        Log.e(a, "hideSoftInputFromWindow: currentFocusView is null");
        return false;
    }

    public static void a(int i, int i2) {
        h.toggleSoftInput(i, i2);
    }

    private static NotificationManager L() {
        return (NotificationManager) LxdApplication.a().getSystemService("notification");
    }

    public static void a(Activity activity, ExecutionType executionType) {
        String str = a;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("showDesktopNotification: ");
        stringBuilder.append(executionType);
        Log.d(str, stringBuilder.toString());
        if (executionType == ExecutionType.INVALID) {
            Log.e(a, "showDesktopNotification: invalid execute type");
            return;
        }
        boolean c = ((a) activity).c();
        boolean z = executionType == ExecutionType.CLI;
        if (z && c) {
            Log.i(a, "showDesktopNotification: no need to show ");
            return;
        }
        Intent intent;
        l.a("018", String.valueOf(1801), z ? 0 : 1);
        CharSequence string = activity.getString(z ? R.string.undock_cli_notice_msg : R.string.undock_gui_notice_msg);
        NotificationChannel notificationChannel = new NotificationChannel(b.getPackageName(), b.getPackageName(), 4);
        notificationChannel.setDescription(LxdApplication.a().getPackageName());
        notificationChannel.enableLights(true);
        notificationChannel.setLightColor(-16711936);
        notificationChannel.enableVibration(true);
        notificationChannel.setVibrationPattern(new long[]{100, 200, 100, 200});
        notificationChannel.setLockscreenVisibility(0);
        L().createNotificationChannel(notificationChannel);
        Notification.Builder autoCancel = new Notification.Builder(LxdApplication.a(), b.getPackageName()).setSmallIcon(R.drawable.ic_icon_mono).setShowWhen(true).setWhen(System.currentTimeMillis()).setOngoing(true).setContentText(string).setColor(activity.getColor(R.color.LightTheme.primary)).setStyle(new BigTextStyle().bigText(string)).setAutoCancel(false);
        if (z) {
            intent = new Intent(activity, DesktopReceiver.class);
            intent.putExtra("unDockNotifyActionId", 1);
            autoCancel.addAction(new Action.Builder(null, activity.getString(R.string.dismiss), PendingIntent.getBroadcast(activity, 1, intent, 134217728)).build());
        } else {
            intent = new Intent(activity, DesktopReceiver.class);
            intent.putExtra("unDockNotifyActionId", 2);
            autoCancel.addAction(new Action.Builder(null, activity.getString(R.string.close_app), PendingIntent.getBroadcast(activity, 2, intent, 134217728)).build());
        }
        intent = new Intent(activity, l());
        intent.setAction("android.intent.action.MAIN");
        intent.addCategory("android.intent.category.LAUNCHER");
        intent.setFlags(270532608);
        autoCancel.setContentIntent(PendingIntent.getActivity(activity, 0, intent, 134217728));
        L().notify(6600, autoCancel.build());
    }

    public static void r() {
        Log.d(a, "hideDesktopNotification");
        L().cancel(6600);
    }

    public static boolean s() {
        return SemSystemProperties.getBoolean("lxd.adbwifi", false);
    }

    public static void a(File file, List<File> list) {
        File[] listFiles = file.listFiles();
        if (listFiles == null) {
            Log.e(a, "Failed to find img file : no files to check with!");
            return;
        }
        try {
            for (File file2 : listFiles) {
                if (file2.isDirectory()) {
                    a(file2, (List) list);
                } else if (file2.canWrite()) {
                    list.add(file2);
                } else {
                    String str = a;
                    StringBuilder stringBuilder = new StringBuilder();
                    stringBuilder.append("can't write: ");
                    stringBuilder.append(file2.getAbsolutePath());
                    Log.i(str, stringBuilder.toString());
                }
            }
        } catch (Exception e) {
            String str2 = a;
            StringBuilder stringBuilder2 = new StringBuilder();
            stringBuilder2.append("findFileList ");
            stringBuilder2.append(e.toString());
            Log.e(str2, stringBuilder2.toString());
        }
    }

    public static boolean c(File file) {
        try {
            if (file.isDirectory()) {
                String[] list = file.list();
                for (String file2 : list) {
                    if (!c(new File(file, file2))) {
                        return false;
                    }
                }
            }
            return file.delete();
        } catch (Exception e) {
            String str = a;
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("deleteDir: ");
            stringBuilder.append(e.toString());
            Log.e(str, stringBuilder.toString());
            return false;
        }
    }

    public static Uri d(File file) {
        Context a = LxdApplication.a();
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(LxdApplication.a().getPackageName());
        stringBuilder.append(".FileProvider");
        return FileProvider.a(a, stringBuilder.toString(), file);
    }

    public static boolean a(String[] strArr, String str, String str2, Uri uri) {
        Intent intent = new Intent("android.intent.action.SEND");
        intent.addFlags(1);
        intent.putExtra("android.intent.extra.STREAM", uri);
        intent.setType("message/rfc822");
        intent.putExtra("android.intent.extra.EMAIL", strArr);
        intent.putExtra("android.intent.extra.SUBJECT", str);
        intent.putExtra("android.intent.extra.TEXT", str2);
        Intent a = a(intent);
        if (a != null) {
            a.addFlags(268435456);
            LxdApplication.a().startActivity(a);
        } else {
            Log.e(a, "sendMail: can't find email client");
        }
        return true;
    }

    public static Intent a(Intent intent) {
        ArrayList arrayList = new ArrayList();
        for (ResolveInfo resolveInfo : LxdApplication.a().getPackageManager().queryIntentActivities(new Intent("android.intent.action.SENDTO", Uri.fromParts("mailto", "lod.sec@samsung.com", null)), 0)) {
            Intent intent2 = new Intent(intent);
            intent2.setPackage(resolveInfo.activityInfo.packageName);
            arrayList.add(intent2);
        }
        if (arrayList.isEmpty()) {
            return null;
        }
        return Intent.createChooser((Intent) arrayList.remove(0), LxdApplication.a().getString(R.string.report_via_email)).putExtra("android.intent.extra.INITIAL_INTENTS", (Parcelable[]) arrayList.toArray(new Parcelable[arrayList.size()]));
    }

    /* JADX WARNING: Removed duplicated region for block: B:44:0x00ef A:{SYNTHETIC, Splitter: B:44:0x00ef} */
    /* JADX WARNING: Removed duplicated region for block: B:78:0x011d A:{SYNTHETIC} */
    /* JADX WARNING: Removed duplicated region for block: B:49:0x0110 A:{SYNTHETIC, Splitter: B:49:0x0110} */
    /* JADX WARNING: Removed duplicated region for block: B:44:0x00ef A:{SYNTHETIC, Splitter: B:44:0x00ef} */
    /* JADX WARNING: Removed duplicated region for block: B:49:0x0110 A:{SYNTHETIC, Splitter: B:49:0x0110} */
    /* JADX WARNING: Removed duplicated region for block: B:78:0x011d A:{SYNTHETIC} */
    /* JADX WARNING: Removed duplicated region for block: B:61:0x0145 A:{SYNTHETIC, Splitter: B:61:0x0145} */
    /* JADX WARNING: Removed duplicated region for block: B:56:0x0124 A:{SYNTHETIC, Splitter: B:56:0x0124} */
    /* JADX WARNING: Removed duplicated region for block: B:61:0x0145 A:{SYNTHETIC, Splitter: B:61:0x0145} */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static boolean a(String str, String str2) {
        InputStream inputStream;
        String str3;
        IOException e;
        IOException e2;
        String str4;
        StringBuilder stringBuilder;
        String str5 = a;
        StringBuilder stringBuilder2 = new StringBuilder();
        stringBuilder2.append("copyAssets: ");
        stringBuilder2.append(str);
        stringBuilder2.append(", path: ");
        stringBuilder2.append(str2);
        Log.d(str5, stringBuilder2.toString());
        AssetManager assets = LxdApplication.a().getAssets();
        int i = 0;
        try {
            StringBuilder stringBuilder3;
            InputStream open;
            OutputStream fileOutputStream;
            Throwable th;
            String[] list = assets.list(str);
            if (list == null) {
                Log.i(a, "no file in asset");
                return false;
            }
            File file = new File(str2);
            if (file.exists()) {
                c(file);
            }
            file.mkdir();
            int length = list.length;
            while (i < length) {
                String str6 = list[i];
                inputStream = null;
                try {
                    stringBuilder3 = new StringBuilder();
                    stringBuilder3.append(str);
                    stringBuilder3.append(File.separator);
                    stringBuilder3.append(str6);
                    open = assets.open(stringBuilder3.toString());
                    try {
                        fileOutputStream = new FileOutputStream(new File(str2, str6));
                        try {
                            a(open, fileOutputStream);
                            if (open != null) {
                                try {
                                    open.close();
                                } catch (IOException e3) {
                                    str3 = a;
                                    stringBuilder3 = new StringBuilder();
                                    stringBuilder3.append("in close: ");
                                    stringBuilder3.append(e3.toString());
                                    Log.d(str3, stringBuilder3.toString());
                                }
                            }
                            if (fileOutputStream != null) {
                                try {
                                    fileOutputStream.close();
                                } catch (IOException e4) {
                                    e3 = e4;
                                    str3 = a;
                                    stringBuilder3 = new StringBuilder();
                                }
                            }
                        } catch (IOException e5) {
                            InputStream inputStream2 = open;
                            e2 = e5;
                            inputStream = inputStream2;
                            try {
                                str4 = a;
                                stringBuilder = new StringBuilder();
                                stringBuilder.append("Failed to copy asset file: ");
                                stringBuilder.append(str6);
                                stringBuilder.append(", ");
                                stringBuilder.append(e2.toString());
                                Log.e(str4, stringBuilder.toString());
                                if (inputStream != null) {
                                    try {
                                        inputStream.close();
                                    } catch (IOException e32) {
                                        str3 = a;
                                        stringBuilder3 = new StringBuilder();
                                        stringBuilder3.append("in close: ");
                                        stringBuilder3.append(e32.toString());
                                        Log.d(str3, stringBuilder3.toString());
                                    }
                                }
                                if (fileOutputStream != null) {
                                    try {
                                        fileOutputStream.close();
                                    } catch (IOException e6) {
                                        e32 = e6;
                                        str3 = a;
                                        stringBuilder3 = new StringBuilder();
                                    }
                                }
                                i++;
                            } catch (Throwable th2) {
                                th = th2;
                            }
                        } catch (Throwable th3) {
                            th = th3;
                        }
                    } catch (IOException e7) {
                        fileOutputStream = null;
                        inputStream = open;
                        e2 = e7;
                        str4 = a;
                        stringBuilder = new StringBuilder();
                        stringBuilder.append("Failed to copy asset file: ");
                        stringBuilder.append(str6);
                        stringBuilder.append(", ");
                        stringBuilder.append(e2.toString());
                        Log.e(str4, stringBuilder.toString());
                        if (inputStream != null) {
                        }
                        if (fileOutputStream != null) {
                        }
                        i++;
                    } catch (Throwable th4) {
                        th = th4;
                        fileOutputStream = null;
                    }
                } catch (IOException e8) {
                    e2 = e8;
                    fileOutputStream = null;
                    str4 = a;
                    stringBuilder = new StringBuilder();
                    stringBuilder.append("Failed to copy asset file: ");
                    stringBuilder.append(str6);
                    stringBuilder.append(", ");
                    stringBuilder.append(e2.toString());
                    Log.e(str4, stringBuilder.toString());
                    if (inputStream != null) {
                    }
                    if (fileOutputStream != null) {
                    }
                    i++;
                } catch (Throwable th5) {
                    th = th5;
                    fileOutputStream = null;
                }
                i++;
            }
            return true;
            stringBuilder3.append("out close: ");
            stringBuilder3.append(e32.toString());
            Log.d(str3, stringBuilder3.toString());
            i++;
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e9) {
                    str5 = a;
                    stringBuilder2 = new StringBuilder();
                    stringBuilder2.append("in close: ");
                    stringBuilder2.append(e9.toString());
                    Log.d(str5, stringBuilder2.toString());
                }
            }
            if (fileOutputStream != null) {
                try {
                    fileOutputStream.close();
                } catch (IOException e92) {
                    str5 = a;
                    stringBuilder2 = new StringBuilder();
                    stringBuilder2.append("out close: ");
                    stringBuilder2.append(e92.toString());
                    Log.d(str5, stringBuilder2.toString());
                }
            }
            throw th;
            if (fileOutputStream != null) {
            }
            throw th;
            throw th;
            inputStream = open;
            if (inputStream != null) {
            }
            if (fileOutputStream != null) {
            }
            throw th;
        } catch (IOException e10) {
            str2 = a;
            StringBuilder stringBuilder4 = new StringBuilder();
            stringBuilder4.append("copyAssets: ");
            stringBuilder4.append(e10.toString());
            Log.e(str2, stringBuilder4.toString());
            return false;
        }
    }

    public static boolean a(InputStream inputStream, OutputStream outputStream) {
        byte[] bArr = new byte[1024];
        while (true) {
            int read = inputStream.read(bArr);
            if (read == -1) {
                return true;
            }
            outputStream.write(bArr, 0, read);
        }
    }

    public static boolean t() {
        return SemSystemProperties.getBoolean("lxd.planD_enabled", false);
    }

    public static boolean h(String str) {
        if (str.contains("planD") || Utils.isDebug()) {
            return true;
        }
        return false;
    }

    public static String u() {
        try {
            return b.getPackageManager().getPackageInfo(b.getPackageName(), 64).versionName;
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }

    public static int v() {
        try {
            return b.getPackageManager().getPackageInfo(b.getPackageName(), 64).versionCode;
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    public static String a(SimpleDateFormat simpleDateFormat) {
        String format = simpleDateFormat.format(new Date(System.currentTimeMillis()));
        String str = a;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("getTime: ");
        stringBuilder.append(format);
        Log.d(str, stringBuilder.toString());
        return format;
    }

    public static boolean i(String str) {
        Account[] k = k(str);
        return k != null && k.length > 0;
    }

    public static String j(String str) {
        Account[] k = k(str);
        return k.length > 0 ? k[0].name : null;
    }

    public static Account[] k(String str) {
        return AccountManager.get(b).getAccountsByType(str);
    }

    public static Intent w() {
        Intent intent = new Intent("com.msc.action.samsungaccount.SIGNIN_POPUP");
        intent.putExtra("client_id", "4vsm90v979");
        intent.putExtra("client_secret", "34EB46F6E1A2B5D69E3F57DC04397DFE");
        intent.putExtra("mypackage", b.getPackageName());
        intent.putExtra("OSP_VER", "OSP_02");
        intent.putExtra("theme", "dark");
        return intent;
    }

    public static Intent x() {
        return new Intent("com.msc.action.samsungaccount.accountsetting");
    }

    public static boolean n(Context context) {
        boolean z = true;
        if (System.getInt(context.getContentResolver(), "samsung_errorlog_agree", 0) != 1) {
            z = false;
        }
        String str = a;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Report Diagnostic Agreement (");
        stringBuilder.append(z);
        stringBuilder.append(")");
        Log.d(str, stringBuilder.toString());
        return z;
    }

    public static boolean y() {
        return SemSystemProperties.getBoolean("lxd.debug_monitor", true);
    }

    public static void z() {
        Log.w(a, "clearSharedPreferences");
        try {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(b.getFilesDir().getParent());
            stringBuilder.append("/shared_prefs/");
            File file = new File(stringBuilder.toString());
            String[] list = file.list();
            if (list != null) {
                int i = 0;
                for (String replace : list) {
                    b.getSharedPreferences(replace.replace(".xml", ""), 0).edit().clear().commit();
                }
                Thread.sleep(1000);
                while (true) {
                    try {
                    } catch (InterruptedException unused) {
                        if (i < list.length) {
                            new File(file, list[i]).delete();
                            i++;
                        } else {
                            return;
                        }
                    }
                }
            }
        } catch (Throwable e) {
            Log.e(a, e.getMessage(), e);
        }
    }

    public static void A() {
        Log.w(a, "clearApplicationData");
        try {
            if (b.getCacheDir() != null && b.getCacheDir().getParent() != null) {
                File file = new File(b.getCacheDir().getParent());
                String str = a;
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append("clearApplicationData: appDir = ");
                stringBuilder.append(file.toString());
                Log.d(str, stringBuilder.toString());
                if (file.exists() && file.isDirectory() && file.list() != null) {
                    for (String str2 : file.list()) {
                        if (!(str2 == null || str2.equals("lib") || str2.equals("analytics_cache"))) {
                            c(new File(file, str2));
                            String str3 = a;
                            StringBuilder stringBuilder2 = new StringBuilder();
                            stringBuilder2.append("File Deleted: /data/data/APP_PACKAGE/");
                            stringBuilder2.append(str2);
                            Log.d(str3, stringBuilder2.toString());
                        }
                    }
                }
            }
        } catch (Throwable e) {
            Log.e(a, e.getMessage(), e);
        }
    }

    public static boolean B() {
        Log.d(a, "needToReset: ");
        if (!g() || i("com.osp.app.signin")) {
            return false;
        }
        Log.i(a, "sa removed after authorized");
        return true;
    }

    public static boolean C() {
        Log.i(a, "resetApp: ");
        d.b();
        z();
        A();
        return true;
    }

    public static boolean D() {
        ConnectivityManager connectivityManager = (ConnectivityManager) b.getSystemService("connectivity");
        for (Network networkInfo : connectivityManager.getAllNetworks()) {
            NetworkInfo networkInfo2 = connectivityManager.getNetworkInfo(networkInfo);
            if (networkInfo2 != null) {
                String str = a;
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append("networkType: ");
                stringBuilder.append(networkInfo2.getType());
                stringBuilder.append(", connected: ");
                stringBuilder.append(networkInfo2.isConnected());
                Log.d(str, stringBuilder.toString());
                if (networkInfo2.isConnected() && networkInfo2.getType() == 1) {
                    Log.i(a, "WIFI connected");
                    return true;
                }
            }
        }
        return false;
    }

    public static void E() {
        WifiManager wifiManager = (WifiManager) b.getSystemService("wifi");
        int semGetWifiApState = wifiManager.semGetWifiApState();
        String str = a;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("enable wifi needed. is hotspot enabled ");
        stringBuilder.append(semGetWifiApState);
        Log.i(str, stringBuilder.toString());
        if (11 == wifiManager.semGetWifiApState()) {
            wifiManager.setWifiEnabled(true);
        }
    }

    public static Intent F() {
        Intent intent = new Intent();
        intent.setAction("android.net.wifi.PICK_WIFI_NETWORK");
        intent.putExtra("wifi_enable_next_on_connect", true);
        intent.putExtra("extra_prefs_show_button_bar", true);
        return intent;
    }

    public static boolean G() {
        return android.support.v4.content.a.b(b, "android.permission.WRITE_EXTERNAL_STORAGE") == 0;
    }

    public static SpannableString b(String str, String str2) {
        SpannableString spannableString = new SpannableString(str);
        if (!str.contains(str2)) {
            return spannableString;
        }
        int indexOf = str.indexOf(str2);
        spannableString.setSpan(new StyleSpan(1), indexOf, str2.length() + indexOf, 0);
        return spannableString;
    }

    public static File e(File file) {
        if (!file.exists()) {
            return file;
        }
        File parentFile = file.getParentFile();
        if (parentFile.listFiles() == null) {
            return file;
        }
        for (File file2 : parentFile.listFiles()) {
            if (file.getName().equalsIgnoreCase(file2.getName())) {
                String str = a;
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append("find File name: ");
                stringBuilder.append(file2.getName());
                Log.i(str, stringBuilder.toString());
                return file2;
            }
        }
        return file;
    }

    public static String f(File file) {
        Exception e;
        String str = null;
        String replace;
        try {
            replace = file.getAbsolutePath().replace("/storage/emulated/", "/data/media/");
            String str2 = a;
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("checkLabel: ");
            stringBuilder.append(file.getAbsolutePath());
            Log.i(str2, stringBuilder.toString());
            Class cls = Class.forName("android.os.SELinux");
            String str3 = (String) cls.getMethod("getFileContext", new Class[]{String.class}).invoke(cls, new Object[]{replace});
            try {
                str = a;
                StringBuilder stringBuilder2 = new StringBuilder();
                stringBuilder2.append("fileContext:  ");
                stringBuilder2.append(str3);
                Log.i(str, stringBuilder2.toString());
                return str3;
            } catch (Exception e2) {
                Exception exception = e2;
                str = str3;
                e = exception;
            }
        } catch (Exception e3) {
            e = e3;
            replace = a;
            StringBuilder stringBuilder3 = new StringBuilder();
            stringBuilder3.append("exception : ");
            stringBuilder3.append(e.toString());
            Log.e(replace, stringBuilder3.toString());
            return str;
        }
    }

    public static String H() {
        return SemSystemProperties.get("lxd.debugurl", "");
    }

    public static void I() {
        String string;
        StringBuilder stringBuilder;
        try {
            SharedPreferences sharedPreferences = LxdApplication.a().getSharedPreferences("prefs", 0);
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
            Long valueOf2 = Long.valueOf(((DownloadManager) LxdApplication.a().getApplicationContext().getSystemService("download")).enqueue(request));
            LxdApplication.a().getSharedPreferences("prefs", 0).edit().putLong(String.valueOf("downloadId"), valueOf2.longValue()).apply();
            string = a;
            stringBuilder = new StringBuilder();
            stringBuilder.append("start Download : ");
            stringBuilder.append(valueOf2);
            Log.i(string, stringBuilder.toString());
        } catch (Exception e) {
            string = a;
            stringBuilder = new StringBuilder();
            stringBuilder.append("getDownloadedFileName Error ! ");
            stringBuilder.append(e.toString());
            Log.e(string, stringBuilder.toString());
        }
    }

    public static void a(TextView textView, CharSequence charSequence, int i) {
        try {
            String str = a;
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("addLink: ");
            stringBuilder.append(i);
            Log.d(str, stringBuilder.toString());
            CharSequence spannableString = new SpannableString(charSequence);
            Linkify.addLinks(spannableString, i);
            textView.setText(spannableString);
            textView.setMovementMethod(LinkMovementMethod.getInstance());
        } catch (Exception e) {
            String str2 = a;
            StringBuilder stringBuilder2 = new StringBuilder();
            stringBuilder2.append("fail to addLink: ");
            stringBuilder2.append(e.toString());
            Log.e(str2, stringBuilder2.toString());
        }
    }

    public static String J() {
        String simOperator = ((TelephonyManager) b.getSystemService("phone")).getSimOperator();
        return (simOperator == null || simOperator.length() <= 3) ? "" : simOperator.substring(0, 3);
    }

    public static String K() {
        String simOperator = ((TelephonyManager) b.getSystemService("phone")).getSimOperator();
        return (simOperator == null || simOperator.length() <= 3) ? "" : simOperator.substring(3);
    }
}
