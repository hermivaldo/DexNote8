package com.samsung.android.lxd.a;

import android.app.Activity;
import android.app.DownloadManager;
import android.app.DownloadManager.Query;
import android.content.ActivityNotFoundException;
import android.content.ComponentName;
import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build.VERSION;
import android.provider.MediaStore.Files;
import com.samsung.android.lxd.DownloadClickReceiver;
import com.samsung.android.lxd.LxdApplication;
import com.samsung.android.lxd.R;
import com.samsung.android.lxd.fragment.CardViewFragment;
import com.samsung.android.lxd.fragment.CardViewFragment.a;
import com.samsung.android.lxd.fragment.CardViewFragment.b;
import com.samsung.android.lxd.processor.config.SystemConfigHelper;
import com.samsung.android.lxd.processor.utils.log.Log;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

/* compiled from: DownloadHelper */
public class d {
    private static final String a = "d";
    private static Timer b;
    private static int c;

    private static String c(int i) {
        switch (i) {
            case 1001:
                return "ERROR_FILE_ERROR";
            case 1002:
                return "ERROR_UNHANDLED_HTTP_CODE";
            case 1004:
                return "ERROR_HTTP_DATA_ERROR";
            case 1005:
                return "ERROR_TOO_MANY_REDIRECTS";
            case 1006:
                return "ERROR_INSUFFICIENT_SPACE";
            case 1007:
                return "ERROR_DEVICE_NOT_FOUND";
            case 1008:
                return "ERROR_CANNOT_RESUME";
            case 1009:
                return "ERROR_FILE_ALREADY_EXISTS";
            default:
                return "ERROR_UNKNOWN";
        }
    }

    public static void a(Activity activity, CardViewFragment cardViewFragment) {
        if (q()) {
            DownloadClickReceiver.a().a(activity);
            DownloadClickReceiver.a().b();
            c(activity, cardViewFragment);
            d(activity, cardViewFragment);
        }
    }

    private static void c(final Activity activity, final CardViewFragment cardViewFragment) {
        a(true);
        o.a(new Runnable() {
            public void run() {
                if (cardViewFragment.getView() != null) {
                    if ((o.f(activity) || o.d(activity) || !o.l(activity)) && d.q()) {
                        cardViewFragment.getView().setVisibility(0);
                        String e = d.a;
                        StringBuilder stringBuilder = new StringBuilder();
                        stringBuilder.append("updateNotification :");
                        stringBuilder.append(d.n());
                        Log.i(e, stringBuilder.toString());
                        int f = d.n();
                        ArrayList arrayList;
                        StringBuilder stringBuilder2;
                        ArrayList arrayList2;
                        if (f == 4) {
                            Log.i(d.a, "download paused !");
                            arrayList = new ArrayList();
                            stringBuilder2 = new StringBuilder();
                            stringBuilder2.append(activity.getString(R.string.download_pause_title));
                            stringBuilder2.append(d.c());
                            arrayList.add(stringBuilder2.toString());
                            arrayList2 = new ArrayList();
                            arrayList2.add(activity.getString(R.string.download_pause_desc));
                            cardViewFragment.a(b.NOTIFY_WITH_TWO_BUTTON_DESC).a(arrayList, null, arrayList2).b((int) R.string.button_resume_download).b(true).a(new com.samsung.android.lxd.fragment.CardViewFragment.d() {
                                public void a() {
                                    o.E();
                                    activity.startActivity(o.F());
                                }

                                public void b() {
                                    d.b(cardViewFragment);
                                }
                            }).b();
                        } else if (f == 8) {
                            Log.i(d.a, "download complete !");
                            arrayList = new ArrayList();
                            stringBuilder2 = new StringBuilder();
                            stringBuilder2.append(activity.getString(R.string.downloading_complete_title));
                            stringBuilder2.append(d.c());
                            arrayList.add(stringBuilder2.toString());
                            arrayList2 = new ArrayList();
                            arrayList2.add(activity.getString(R.string.downloading_complete_desc));
                            cardViewFragment.a(b.NOTIFY_WITH_RECT_BUTTON).a(arrayList, null, arrayList2).a((int) R.string.unzip_button).b(true).a(new a() {
                                public void a(int i) {
                                    d.b(activity);
                                    d.a(false);
                                    cardViewFragment.getView().setVisibility(8);
                                    d.m();
                                }
                            }).b();
                        } else if (f != 16) {
                            switch (f) {
                                case 1:
                                    Log.i(d.a, "pending !");
                                    arrayList = new ArrayList();
                                    stringBuilder2 = new StringBuilder();
                                    stringBuilder2.append(activity.getString(R.string.download_pending_title));
                                    stringBuilder2.append(d.c());
                                    arrayList.add(stringBuilder2.toString());
                                    cardViewFragment.a(b.NOTIFY_WITH_PENDING).a(arrayList).b(true).a(new a() {
                                        public void a(int i) {
                                            d.b(cardViewFragment);
                                        }
                                    }).b();
                                    break;
                                case 2:
                                    Log.i(d.a, "downloading !");
                                    arrayList = new ArrayList();
                                    stringBuilder2 = new StringBuilder();
                                    stringBuilder2.append(activity.getString(R.string.downloading_title));
                                    stringBuilder2.append(d.c());
                                    arrayList.add(stringBuilder2.toString());
                                    arrayList2 = new ArrayList();
                                    arrayList2.add(activity.getString(R.string.downloading_desc));
                                    cardViewFragment.a(b.NOTIFY_WITH_PROGRESS).a(arrayList, null, arrayList2).b(true).a(new a() {
                                        public void a(int i) {
                                            d.b(cardViewFragment);
                                        }
                                    }).b();
                                    break;
                                default:
                                    cardViewFragment.getView().setVisibility(8);
                                    break;
                            }
                        } else {
                            f = d.o();
                            l.a(((com.samsung.android.lxd.a) activity).a, String.valueOf(2201), d.c(f));
                            String e2 = d.a;
                            stringBuilder = new StringBuilder();
                            stringBuilder.append("download failed! error reason : ");
                            stringBuilder.append(d.c(f));
                            stringBuilder.append(", download ID : ");
                            stringBuilder.append(d.l());
                            Log.e(e2, stringBuilder.toString());
                            arrayList2 = new ArrayList();
                            stringBuilder = new StringBuilder();
                            stringBuilder.append(activity.getString(R.string.download_fail_title));
                            stringBuilder.append(d.c());
                            arrayList2.add(stringBuilder.toString());
                            if (f == 1006 || f == 1009) {
                                ArrayList arrayList3 = new ArrayList();
                                arrayList3.add(activity.getString(f == 1006 ? R.string.download_fail_insufficient_storage : R.string.download_fail_already_exist));
                                cardViewFragment.a(b.NOTIFY_WITH_ONE_BUTTON).a(arrayList2, null, arrayList3).b(true).a(new a() {
                                    public void a(int i) {
                                        d.b(cardViewFragment);
                                    }
                                }).b();
                            } else {
                                new ArrayList().add(activity.getString(R.string.download_pause_desc));
                                cardViewFragment.a(b.NOTIFY_WITH_TWO_BUTTON).a(arrayList2).b((int) R.string.popup_try_again).b(true).a(new com.samsung.android.lxd.fragment.CardViewFragment.d() {
                                    public void a() {
                                        d.b(cardViewFragment);
                                        o.I();
                                    }

                                    public void b() {
                                        d.b(cardViewFragment);
                                    }
                                }).b();
                            }
                        }
                        return;
                    }
                    cardViewFragment.getView().setVisibility(8);
                }
            }
        });
    }

    private static void d(final Activity activity, final CardViewFragment cardViewFragment) {
        try {
            if (b != null) {
                b.cancel();
                b = null;
            }
            b = new Timer();
            b.schedule(new TimerTask() {
                public void run() {
                    if (!((com.samsung.android.lxd.a) activity).c()) {
                        d.b.cancel();
                    }
                    Query query = new Query();
                    long[] jArr = new long[1];
                    int i = 0;
                    jArr[0] = d.l();
                    query.setFilterById(jArr);
                    Cursor query2 = ((DownloadManager) activity.getSystemService("download")).query(query);
                    query2.moveToFirst();
                    String str;
                    try {
                        long j = query2.getLong(query2.getColumnIndex("bytes_so_far"));
                        long j2 = query2.getLong(query2.getColumnIndex("total_size"));
                        int i2 = query2.getInt(query2.getColumnIndex("status"));
                        if (i2 != d.c) {
                            d.c = i2;
                            if (i2 == 8) {
                                d.b.cancel();
                            }
                            d.c(activity, cardViewFragment);
                        }
                        i2 = (j2 > 0 ? 1 : (j2 == 0 ? 0 : -1));
                        if (i2 > 0) {
                            i = (int) ((100 * j) / j2);
                        }
                        StringBuilder stringBuilder = new StringBuilder();
                        stringBuilder.append(o.a(j));
                        stringBuilder.append("GB / ");
                        if (i2 <= 0) {
                            str = SystemConfigHelper.CONFIG_OPTION_OFF;
                        } else {
                            str = o.a(j2);
                        }
                        stringBuilder.append(str);
                        stringBuilder.append("GB");
                        str = stringBuilder.toString();
                        o.a(new Runnable() {
                            public void run() {
                                cardViewFragment.c(i).a(str).b(String.valueOf(i)).b();
                            }
                        });
                        query2.close();
                    } catch (Exception e) {
                        str = d.a;
                        StringBuilder stringBuilder2 = new StringBuilder();
                        stringBuilder2.append("Stop to get download progress! : ");
                        stringBuilder2.append(e.toString());
                        Log.d(str, stringBuilder2.toString());
                        d.b.cancel();
                        d.c(activity, cardViewFragment);
                    }
                }
            }, 0, 1000);
        } catch (Exception e) {
            c(activity, cardViewFragment);
            String str = a;
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("Failed to getStatus! : ");
            stringBuilder.append(e.toString());
            Log.d(str, stringBuilder.toString());
        }
    }

    private static long l() {
        if (LxdApplication.a().getSharedPreferences("prefs", 0).contains(String.valueOf("downloadId"))) {
            return LxdApplication.a().getSharedPreferences("prefs", 0).getLong(String.valueOf("downloadId"), -1);
        }
        return -1;
    }

    private static boolean m() {
        a(false);
        DownloadClickReceiver.a().c();
        if (LxdApplication.a().getSharedPreferences("prefs", 0).contains(String.valueOf("downloadId"))) {
            return LxdApplication.a().getSharedPreferences("prefs", 0).edit().remove(String.valueOf("downloadId")).commit();
        }
        return true;
    }

    private static int n() {
        long l = l();
        String str = a;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("getDownloadStatus, downloadId : ");
        stringBuilder.append(l);
        Log.d(str, stringBuilder.toString());
        int i = 0;
        if (l == -1) {
            return 0;
        }
        try {
            Query query = new Query();
            query.setFilterById(new long[]{l});
            Cursor query2 = ((DownloadManager) LxdApplication.a().getSystemService("download")).query(query);
            query2.moveToFirst();
            int i2 = query2.getInt(query2.getColumnIndex("status"));
            String str2 = a;
            StringBuilder stringBuilder2 = new StringBuilder();
            stringBuilder2.append("getDownloadStatus, status : ");
            stringBuilder2.append(i2);
            stringBuilder2.append(", downloadId : ");
            stringBuilder2.append(l);
            Log.i(str2, stringBuilder2.toString());
            query2.close();
            i = i2;
        } catch (Exception unused) {
            return i;
        }
    }

    private static int o() {
        int i;
        Exception e;
        if (l() == -1) {
            return 0;
        }
        try {
            Query query = new Query();
            query.setFilterById(new long[]{r0});
            Cursor query2 = ((DownloadManager) LxdApplication.a().getSystemService("download")).query(query);
            query2.moveToFirst();
            i = query2.getInt(query2.getColumnIndex("reason"));
            try {
                query2.close();
            } catch (Exception e2) {
                e = e2;
            }
        } catch (Exception e3) {
            e = e3;
            i = 0;
            String str = a;
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("getDownloadError fail! ");
            stringBuilder.append(e.toString());
            Log.e(str, stringBuilder.toString());
            return i;
        }
        return i;
    }

    public static boolean a() {
        int n = n();
        return n == 8 || n == 16 || n == 0;
    }

    private static void b(CardViewFragment cardViewFragment) {
        a(false);
        b();
        cardViewFragment.getView().setVisibility(8);
    }

    public static void b() {
        if (l() != -1) {
            ((DownloadManager) LxdApplication.a().getSystemService("download")).remove(new long[]{l()});
            m();
        }
    }

    private static void b(Activity activity) {
        if (o.a(activity)) {
            d(activity);
        } else {
            c(activity);
        }
    }

    private static Uri a(Context context, Uri uri, String str) {
        Cursor query;
        Throwable th;
        Throwable th2;
        ContentResolver contentResolver = context.getContentResolver();
        Uri uri2 = null;
        try {
            Uri uri3 = uri;
            query = contentResolver.query(uri3, new String[]{"_id", "_data"}, "_data= ? COLLATE LOCALIZED", new String[]{str}, null);
            if (query != null) {
                try {
                    if (query.getCount() > 0 && query.moveToFirst() && query.getString(query.getColumnIndex("_data")).equals(str)) {
                        uri2 = ContentUris.withAppendedId(uri, query.getLong(query.getColumnIndex("_id")));
                    }
                } catch (Throwable th22) {
                    Throwable th3 = th22;
                    th22 = th;
                    th = th3;
                }
            }
            if (query != null) {
                query.close();
            }
        } catch (SecurityException e) {
            str = a;
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("SecurityException:");
            stringBuilder.append(e.toString());
            Log.e(str, stringBuilder.toString());
        }
        return uri2;
        if (query != null) {
            if (th22 != null) {
                try {
                    query.close();
                } catch (Throwable th4) {
                    th22.addSuppressed(th4);
                }
            } else {
                query.close();
            }
        }
        throw th;
        throw th;
    }

    private static void c(Activity activity) {
        String p = p();
        Intent intent = new Intent("samsung.myfiles.intent.action.LAUNCH_MY_FILES");
        intent.putExtra("file_display", "forwardable");
        intent.putExtra("samsung.myfiles.intent.extra.START_PATH", p);
        try {
            String str = a;
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("startMyFiles. zip path : ");
            stringBuilder.append(p);
            Log.i(str, stringBuilder.toString());
            activity.startActivity(intent);
        } catch (ActivityNotFoundException unused) {
            Log.e(a, "startMyFiles. Launch myFiles failed!");
        }
    }

    private static void d(Activity activity) {
        Intent intent = new Intent("android.intent.action.VIEW");
        String p = p();
        intent.setDataAndType(a(activity.getApplicationContext(), Files.getContentUri("external"), p), "application/zip");
        intent.putExtra("AbsolutePath", p);
        intent.putExtra("from-LoD", true);
        intent.setFlags(1);
        intent.setComponent(new ComponentName("com.sec.android.app.myfiles", VERSION.SDK_INT < 28 ? "com.sec.android.app.myfiles.common.MainActivity" : "com.sec.android.app.myfiles.external.ui.MainActivity"));
        try {
            String str = a;
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("startMyFilesForResult. zip path : ");
            stringBuilder.append(p);
            Log.i(str, stringBuilder.toString());
            activity.startActivityForResult(intent, 6);
        } catch (ActivityNotFoundException e) {
            String str2 = a;
            StringBuilder stringBuilder2 = new StringBuilder();
            stringBuilder2.append("startMyFilesForResult. Launch myFiles failed!");
            stringBuilder2.append(e.toString());
            Log.e(str2, stringBuilder2.toString());
        }
    }

    private static String p() {
        String str;
        StringBuilder stringBuilder;
        if (l() == -1) {
            Log.d(a, "getDownloadedFileName: Invalid downloadId!");
            return null;
        }
        String path;
        try {
            Query query = new Query();
            query.setFilterById(new long[]{r0});
            Cursor query2 = ((DownloadManager) LxdApplication.a().getApplicationContext().getSystemService("download")).query(query);
            query2.moveToFirst();
            path = Uri.parse(query2.getString(query2.getColumnIndex("local_uri"))).getPath();
        } catch (Exception e) {
            str = a;
            stringBuilder = new StringBuilder();
            stringBuilder.append("getDownloadedFilePath Exception : ");
            stringBuilder.append(e);
            Log.d(str, stringBuilder.toString());
            path = null;
        }
        str = a;
        stringBuilder = new StringBuilder();
        stringBuilder.append("LOCAL FILE NAME : ");
        stringBuilder.append(path);
        Log.d(str, stringBuilder.toString());
        return path;
    }

    public static String c() {
        try {
            String string = LxdApplication.a().getSharedPreferences("prefs", 0).getString(String.valueOf("downloadFile"), null);
            String str = a;
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("getDownloadedFileName : ");
            stringBuilder.append(string);
            Log.d(str, stringBuilder.toString());
            return string;
        } catch (Exception e) {
            String str2 = a;
            StringBuilder stringBuilder2 = new StringBuilder();
            stringBuilder2.append("getDownloadedFileName Error ! ");
            stringBuilder2.append(e.toString());
            Log.e(str2, stringBuilder2.toString());
            return null;
        }
    }

    private static boolean q() {
        return LxdApplication.a().getSharedPreferences("prefs", 0).getBoolean("needNoti", false);
    }

    public static void a(boolean z) {
        LxdApplication.a().getSharedPreferences("prefs", 0).edit().putBoolean("needNoti", z).apply();
    }
}
