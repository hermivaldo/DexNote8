package com.samsung.android.lxd.p064a;

import android.app.Activity;
import android.app.DownloadManager;
import android.app.DownloadManager.Query;
import android.content.ComponentName;
import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build.VERSION;
import android.provider.MediaStore.Files;
import com.samsung.android.lxd.C1347a;
import com.samsung.android.lxd.DownloadClickReceiver;
import com.samsung.android.lxd.LxdApplication;
import com.samsung.android.lxd.R;
import com.samsung.android.lxd.fragment.CardViewFragment;
import com.samsung.android.lxd.fragment.CardViewFragment.C0900a;
import com.samsung.android.lxd.fragment.CardViewFragment.C0901b;
import com.samsung.android.lxd.fragment.CardViewFragment.C0903d;
import com.samsung.android.lxd.processor.config.SystemConfigHelper;
import com.samsung.android.lxd.processor.utils.log.Log;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

/* compiled from: DownloadHelper */
/* renamed from: com.samsung.android.lxd.a.d */
public class C0859d {
    /* renamed from: a */
    private static final String f2673a = "d";
    /* renamed from: b */
    private static Timer f2674b;
    /* renamed from: c */
    private static int f2675c;

    /* renamed from: c */
    private static String m3378c(int i) {
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

    /* renamed from: a */
    public static void m3368a(Activity activity, CardViewFragment cardViewFragment) {
        if (C0859d.m3396q()) {
            DownloadClickReceiver.m3339a().m3341a(activity);
            DownloadClickReceiver.m3339a().m3342b();
            C0859d.m3380c(activity, cardViewFragment);
            C0859d.m3382d(activity, cardViewFragment);
        }
    }

    /* renamed from: c */
    private static void m3380c(final Activity activity, final CardViewFragment cardViewFragment) {
        C0859d.m3370a(true);
        C0877o.m3470a(new Runnable() {

            /* compiled from: DownloadHelper */
            /* renamed from: com.samsung.android.lxd.a.d$1$1 */
            class C13391 implements C0900a {
                /* renamed from: a */
                final /* synthetic */ C08561 f4416a;

                C13391(C08561 c08561) {
                    this.f4416a = c08561;
                }

                /* renamed from: a */
                public void mo659a(int i) {
                    C0859d.m3376b(cardViewFragment);
                }
            }

            /* compiled from: DownloadHelper */
            /* renamed from: com.samsung.android.lxd.a.d$1$2 */
            class C13402 implements C0900a {
                /* renamed from: a */
                final /* synthetic */ C08561 f4417a;

                C13402(C08561 c08561) {
                    this.f4417a = c08561;
                }

                /* renamed from: a */
                public void mo659a(int i) {
                    C0859d.m3376b(cardViewFragment);
                }
            }

            /* compiled from: DownloadHelper */
            /* renamed from: com.samsung.android.lxd.a.d$1$3 */
            class C13413 implements C0900a {
                /* renamed from: a */
                final /* synthetic */ C08561 f4418a;

                C13413(C08561 c08561) {
                    this.f4418a = c08561;
                }

                /* renamed from: a */
                public void mo659a(int i) {
                    C0859d.m3374b(activity);
                    C0859d.m3370a((boolean) 0);
                    cardViewFragment.getView().setVisibility(8);
                    C0859d.m3392m();
                }
            }

            /* compiled from: DownloadHelper */
            /* renamed from: com.samsung.android.lxd.a.d$1$4 */
            class C13424 implements C0903d {
                /* renamed from: a */
                final /* synthetic */ C08561 f4419a;

                C13424(C08561 c08561) {
                    this.f4419a = c08561;
                }

                /* renamed from: a */
                public void mo695a() {
                    C0877o.m3445E();
                    activity.startActivity(C0877o.m3446F());
                }

                /* renamed from: b */
                public void mo696b() {
                    C0859d.m3376b(cardViewFragment);
                }
            }

            /* compiled from: DownloadHelper */
            /* renamed from: com.samsung.android.lxd.a.d$1$5 */
            class C13435 implements C0900a {
                /* renamed from: a */
                final /* synthetic */ C08561 f4420a;

                C13435(C08561 c08561) {
                    this.f4420a = c08561;
                }

                /* renamed from: a */
                public void mo659a(int i) {
                    C0859d.m3376b(cardViewFragment);
                }
            }

            /* compiled from: DownloadHelper */
            /* renamed from: com.samsung.android.lxd.a.d$1$6 */
            class C13446 implements C0903d {
                /* renamed from: a */
                final /* synthetic */ C08561 f4421a;

                C13446(C08561 c08561) {
                    this.f4421a = c08561;
                }

                /* renamed from: a */
                public void mo695a() {
                    C0859d.m3376b(cardViewFragment);
                    C0877o.m3449I();
                }

                /* renamed from: b */
                public void mo696b() {
                    C0859d.m3376b(cardViewFragment);
                }
            }

            public void run() {
                if (cardViewFragment.getView() != null) {
                    if ((C0877o.m3508f(activity) || C0877o.m3499d(activity) || !C0877o.m3525l(activity)) && C0859d.m3396q()) {
                        cardViewFragment.getView().setVisibility(0);
                        String e = C0859d.f2673a;
                        StringBuilder stringBuilder = new StringBuilder();
                        stringBuilder.append("updateNotification :");
                        stringBuilder.append(C0859d.m3393n());
                        Log.m3857i(e, stringBuilder.toString());
                        int f = C0859d.m3393n();
                        ArrayList arrayList;
                        StringBuilder stringBuilder2;
                        ArrayList arrayList2;
                        if (f == 4) {
                            Log.m3857i(C0859d.f2673a, "download paused !");
                            arrayList = new ArrayList();
                            stringBuilder2 = new StringBuilder();
                            stringBuilder2.append(activity.getString(R.string.download_pause_title));
                            stringBuilder2.append(C0859d.m3377c());
                            arrayList.add(stringBuilder2.toString());
                            arrayList2 = new ArrayList();
                            arrayList2.add(activity.getString(R.string.download_pause_desc));
                            cardViewFragment.m3611a(C0901b.NOTIFY_WITH_TWO_BUTTON_DESC).m3616a(arrayList, null, arrayList2).m3619b((int) R.string.button_resume_download).m3621b(true).m3612a(new C13424(this)).m3618b();
                        } else if (f == 8) {
                            Log.m3857i(C0859d.f2673a, "download complete !");
                            arrayList = new ArrayList();
                            stringBuilder2 = new StringBuilder();
                            stringBuilder2.append(activity.getString(R.string.downloading_complete_title));
                            stringBuilder2.append(C0859d.m3377c());
                            arrayList.add(stringBuilder2.toString());
                            arrayList2 = new ArrayList();
                            arrayList2.add(activity.getString(R.string.downloading_complete_desc));
                            cardViewFragment.m3611a(C0901b.NOTIFY_WITH_RECT_BUTTON).m3616a(arrayList, null, arrayList2).m3609a((int) R.string.unzip_button).m3621b(true).m3610a(new C13413(this)).m3618b();
                        } else if (f != 16) {
                            switch (f) {
                                case 1:
                                    Log.m3857i(C0859d.f2673a, "pending !");
                                    arrayList = new ArrayList();
                                    stringBuilder2 = new StringBuilder();
                                    stringBuilder2.append(activity.getString(R.string.download_pending_title));
                                    stringBuilder2.append(C0859d.m3377c());
                                    arrayList.add(stringBuilder2.toString());
                                    cardViewFragment.m3611a(C0901b.NOTIFY_WITH_PENDING).m3614a(arrayList).m3621b(true).m3610a(new C13391(this)).m3618b();
                                    break;
                                case 2:
                                    Log.m3857i(C0859d.f2673a, "downloading !");
                                    arrayList = new ArrayList();
                                    stringBuilder2 = new StringBuilder();
                                    stringBuilder2.append(activity.getString(R.string.downloading_title));
                                    stringBuilder2.append(C0859d.m3377c());
                                    arrayList.add(stringBuilder2.toString());
                                    arrayList2 = new ArrayList();
                                    arrayList2.add(activity.getString(R.string.downloading_desc));
                                    cardViewFragment.m3611a(C0901b.NOTIFY_WITH_PROGRESS).m3616a(arrayList, null, arrayList2).m3621b(true).m3610a(new C13402(this)).m3618b();
                                    break;
                                default:
                                    cardViewFragment.getView().setVisibility(8);
                                    break;
                            }
                        } else {
                            f = C0859d.m3394o();
                            C0874l.m3434a(((C1347a) activity).f4424a, String.valueOf(2201), C0859d.m3378c(f));
                            String e2 = C0859d.f2673a;
                            stringBuilder = new StringBuilder();
                            stringBuilder.append("download failed! error reason : ");
                            stringBuilder.append(C0859d.m3378c(f));
                            stringBuilder.append(", download ID : ");
                            stringBuilder.append(C0859d.m3391l());
                            Log.m3855e(e2, stringBuilder.toString());
                            arrayList2 = new ArrayList();
                            stringBuilder = new StringBuilder();
                            stringBuilder.append(activity.getString(R.string.download_fail_title));
                            stringBuilder.append(C0859d.m3377c());
                            arrayList2.add(stringBuilder.toString());
                            if (f == 1006 || f == 1009) {
                                ArrayList arrayList3 = new ArrayList();
                                arrayList3.add(activity.getString(f == 1006 ? R.string.download_fail_insufficient_storage : R.string.download_fail_already_exist));
                                cardViewFragment.m3611a(C0901b.NOTIFY_WITH_ONE_BUTTON).m3616a(arrayList2, null, arrayList3).m3621b(true).m3610a(new C13435(this)).m3618b();
                            } else {
                                new ArrayList().add(activity.getString(R.string.download_pause_desc));
                                cardViewFragment.m3611a(C0901b.NOTIFY_WITH_TWO_BUTTON).m3614a(arrayList2).m3619b((int) R.string.popup_try_again).m3621b(true).m3612a(new C13446(this)).m3618b();
                            }
                        }
                        return;
                    }
                    cardViewFragment.getView().setVisibility(8);
                }
            }
        });
    }

    /* renamed from: d */
    private static void m3382d(final Activity activity, final CardViewFragment cardViewFragment) {
        try {
            if (f2674b != null) {
                f2674b.cancel();
                f2674b = null;
            }
            f2674b = new Timer();
            f2674b.schedule(new TimerTask() {
                public void run() {
                    String str;
                    if (!((C1347a) activity).mo707c()) {
                        C0859d.f2674b.cancel();
                    }
                    Query query = new Query();
                    long[] jArr = new long[1];
                    int i = 0;
                    jArr[0] = C0859d.m3391l();
                    query.setFilterById(jArr);
                    Cursor query2 = ((DownloadManager) activity.getSystemService("download")).query(query);
                    query2.moveToFirst();
                    try {
                        long j = query2.getLong(query2.getColumnIndex("bytes_so_far"));
                        long j2 = query2.getLong(query2.getColumnIndex("total_size"));
                        int i2 = query2.getInt(query2.getColumnIndex("status"));
                        if (i2 != C0859d.f2675c) {
                            C0859d.f2675c = i2;
                            if (i2 == 8) {
                                C0859d.f2674b.cancel();
                            }
                            C0859d.m3380c(activity, cardViewFragment);
                        }
                        i2 = (j2 > 0 ? 1 : (j2 == 0 ? 0 : -1));
                        if (i2 > 0) {
                            i = (int) ((100 * j) / j2);
                        }
                        StringBuilder stringBuilder = new StringBuilder();
                        stringBuilder.append(C0877o.m3459a(j));
                        stringBuilder.append("GB / ");
                        if (i2 <= 0) {
                            str = SystemConfigHelper.CONFIG_OPTION_OFF;
                        } else {
                            str = C0877o.m3459a(j2);
                        }
                        stringBuilder.append(str);
                        stringBuilder.append("GB");
                        str = stringBuilder.toString();
                        C0877o.m3470a(new Runnable(this) {
                            /* renamed from: c */
                            final /* synthetic */ C08582 f2670c;

                            public void run() {
                                cardViewFragment.m3622c(i).m3613a(str).m3620b(String.valueOf(i)).m3618b();
                            }
                        });
                        query2.close();
                    } catch (Exception e) {
                        str = C0859d.f2673a;
                        StringBuilder stringBuilder2 = new StringBuilder();
                        stringBuilder2.append("Stop to get download progress! : ");
                        stringBuilder2.append(e.toString());
                        Log.m3853d(str, stringBuilder2.toString());
                        C0859d.f2674b.cancel();
                        C0859d.m3380c(activity, cardViewFragment);
                    }
                }
            }, 0, 1000);
        } catch (Exception e) {
            C0859d.m3380c(activity, cardViewFragment);
            activity = f2673a;
            cardViewFragment = new StringBuilder();
            cardViewFragment.append("Failed to getStatus! : ");
            cardViewFragment.append(e.toString());
            Log.m3853d(activity, cardViewFragment.toString());
        }
    }

    /* renamed from: l */
    private static long m3391l() {
        if (LxdApplication.m3344a().getSharedPreferences("prefs", 0).contains(String.valueOf("downloadId"))) {
            return LxdApplication.m3344a().getSharedPreferences("prefs", 0).getLong(String.valueOf("downloadId"), -1);
        }
        return -1;
    }

    /* renamed from: m */
    private static boolean m3392m() {
        C0859d.m3370a(false);
        DownloadClickReceiver.m3339a().m3343c();
        if (LxdApplication.m3344a().getSharedPreferences("prefs", 0).contains(String.valueOf("downloadId"))) {
            return LxdApplication.m3344a().getSharedPreferences("prefs", 0).edit().remove(String.valueOf("downloadId")).commit();
        }
        return true;
    }

    /* renamed from: n */
    private static int m3393n() {
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
	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:282)
	at jadx.api.JavaClass.decompile(JavaClass.java:62)
	at jadx.api.JadxDecompiler.lambda$appendSourcesSave$0(JadxDecompiler.java:200)
	at jadx.api.JadxDecompiler$$Lambda$8/1201484275.run(Unknown Source)
*/
        /*
        r0 = com.samsung.android.lxd.p064a.C0859d.m3391l();
        r2 = f2673a;
        r3 = new java.lang.StringBuilder;
        r3.<init>();
        r4 = "getDownloadStatus, downloadId : ";
        r3.append(r4);
        r3.append(r0);
        r3 = r3.toString();
        com.samsung.android.lxd.processor.utils.log.Log.m3853d(r2, r3);
        r2 = -1;
        r2 = (r0 > r2 ? 1 : (r0 == r2 ? 0 : -1));
        r3 = 0;
        if (r2 != 0) goto L_0x0022;
    L_0x0021:
        return r3;
    L_0x0022:
        r2 = new android.app.DownloadManager$Query;	 Catch:{ Exception -> 0x006e }
        r2.<init>();	 Catch:{ Exception -> 0x006e }
        r4 = 1;	 Catch:{ Exception -> 0x006e }
        r4 = new long[r4];	 Catch:{ Exception -> 0x006e }
        r4[r3] = r0;	 Catch:{ Exception -> 0x006e }
        r2.setFilterById(r4);	 Catch:{ Exception -> 0x006e }
        r4 = com.samsung.android.lxd.LxdApplication.m3344a();	 Catch:{ Exception -> 0x006e }
        r5 = "download";	 Catch:{ Exception -> 0x006e }
        r4 = r4.getSystemService(r5);	 Catch:{ Exception -> 0x006e }
        r4 = (android.app.DownloadManager) r4;	 Catch:{ Exception -> 0x006e }
        r2 = r4.query(r2);	 Catch:{ Exception -> 0x006e }
        r2.moveToFirst();	 Catch:{ Exception -> 0x006e }
        r4 = "status";	 Catch:{ Exception -> 0x006e }
        r4 = r2.getColumnIndex(r4);	 Catch:{ Exception -> 0x006e }
        r4 = r2.getInt(r4);	 Catch:{ Exception -> 0x006e }
        r5 = f2673a;	 Catch:{ Exception -> 0x006e }
        r6 = new java.lang.StringBuilder;	 Catch:{ Exception -> 0x006e }
        r6.<init>();	 Catch:{ Exception -> 0x006e }
        r7 = "getDownloadStatus, status : ";	 Catch:{ Exception -> 0x006e }
        r6.append(r7);	 Catch:{ Exception -> 0x006e }
        r6.append(r4);	 Catch:{ Exception -> 0x006e }
        r7 = ", downloadId : ";	 Catch:{ Exception -> 0x006e }
        r6.append(r7);	 Catch:{ Exception -> 0x006e }
        r6.append(r0);	 Catch:{ Exception -> 0x006e }
        r0 = r6.toString();	 Catch:{ Exception -> 0x006e }
        com.samsung.android.lxd.processor.utils.log.Log.m3857i(r5, r0);	 Catch:{ Exception -> 0x006e }
        r2.close();	 Catch:{ Exception -> 0x006e }
        r3 = r4;
    L_0x006e:
        return r3;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.samsung.android.lxd.a.d.n():int");
    }

    /* renamed from: o */
    private static int m3394o() {
        int i;
        Exception e;
        String str;
        StringBuilder stringBuilder;
        if (C0859d.m3391l() == -1) {
            return 0;
        }
        try {
            Query query = new Query();
            query.setFilterById(new long[]{r0});
            Cursor query2 = ((DownloadManager) LxdApplication.m3344a().getSystemService("download")).query(query);
            query2.moveToFirst();
            i = query2.getInt(query2.getColumnIndex("reason"));
            try {
                query2.close();
            } catch (Exception e2) {
                e = e2;
                str = f2673a;
                stringBuilder = new StringBuilder();
                stringBuilder.append("getDownloadError fail! ");
                stringBuilder.append(e.toString());
                Log.m3855e(str, stringBuilder.toString());
                return i;
            }
        } catch (Exception e3) {
            e = e3;
            i = 0;
            str = f2673a;
            stringBuilder = new StringBuilder();
            stringBuilder.append("getDownloadError fail! ");
            stringBuilder.append(e.toString());
            Log.m3855e(str, stringBuilder.toString());
            return i;
        }
        return i;
    }

    /* renamed from: a */
    public static boolean m3371a() {
        int n = C0859d.m3393n();
        if (!(n == 8 || n == 16)) {
            if (n != 0) {
                return false;
            }
        }
        return true;
    }

    /* renamed from: b */
    private static void m3376b(CardViewFragment cardViewFragment) {
        C0859d.m3370a(false);
        C0859d.m3373b();
        cardViewFragment.getView().setVisibility(8);
    }

    /* renamed from: b */
    public static void m3373b() {
        if (C0859d.m3391l() != -1) {
            ((DownloadManager) LxdApplication.m3344a().getSystemService("download")).remove(new long[]{C0859d.m3391l()});
            C0859d.m3392m();
        }
    }

    /* renamed from: b */
    private static void m3374b(Activity activity) {
        if (C0877o.m3473a(activity)) {
            C0859d.m3381d(activity);
        } else {
            C0859d.m3379c(activity);
        }
    }

    /* renamed from: a */
    private static Uri m3365a(Context context, Uri uri, String str) {
        Cursor query;
        ContentResolver contentResolver = context.getContentResolver();
        context = null;
        try {
            Uri uri2 = uri;
            query = contentResolver.query(uri2, new String[]{"_id", "_data"}, "_data= ? COLLATE LOCALIZED", new String[]{str}, null);
            if (query != null) {
                try {
                    if (query.getCount() > 0 && query.moveToFirst() && query.getString(query.getColumnIndex("_data")).equals(str) != null) {
                        context = ContentUris.withAppendedId(uri, query.getLong(query.getColumnIndex("_id")));
                    }
                } catch (String str2) {
                    String str3 = str2;
                    str2 = uri;
                    uri = str3;
                }
            }
            if (query != null) {
                query.close();
            }
        } catch (Uri uri3) {
            str2 = f2673a;
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("SecurityException:");
            stringBuilder.append(uri3.toString());
            Log.m3855e(str2, stringBuilder.toString());
        }
        return context;
        if (query != null) {
            if (str2 != null) {
                try {
                    query.close();
                } catch (Throwable th) {
                    str2.addSuppressed(th);
                }
            } else {
                query.close();
            }
        }
        throw uri3;
        throw uri3;
    }

    /* renamed from: c */
    private static void m3379c(android.app.Activity r5) {
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
	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:282)
	at jadx.api.JavaClass.decompile(JavaClass.java:62)
	at jadx.api.JadxDecompiler.lambda$appendSourcesSave$0(JadxDecompiler.java:200)
	at jadx.api.JadxDecompiler$$Lambda$8/1201484275.run(Unknown Source)
*/
        /*
        r0 = com.samsung.android.lxd.p064a.C0859d.m3395p();
        r1 = new android.content.Intent;
        r2 = "samsung.myfiles.intent.action.LAUNCH_MY_FILES";
        r1.<init>(r2);
        r2 = "file_display";
        r3 = "forwardable";
        r1.putExtra(r2, r3);
        r2 = "samsung.myfiles.intent.extra.START_PATH";
        r1.putExtra(r2, r0);
        r2 = f2673a;	 Catch:{ ActivityNotFoundException -> 0x0031 }
        r3 = new java.lang.StringBuilder;	 Catch:{ ActivityNotFoundException -> 0x0031 }
        r3.<init>();	 Catch:{ ActivityNotFoundException -> 0x0031 }
        r4 = "startMyFiles. zip path : ";	 Catch:{ ActivityNotFoundException -> 0x0031 }
        r3.append(r4);	 Catch:{ ActivityNotFoundException -> 0x0031 }
        r3.append(r0);	 Catch:{ ActivityNotFoundException -> 0x0031 }
        r0 = r3.toString();	 Catch:{ ActivityNotFoundException -> 0x0031 }
        com.samsung.android.lxd.processor.utils.log.Log.m3857i(r2, r0);	 Catch:{ ActivityNotFoundException -> 0x0031 }
        r5.startActivity(r1);	 Catch:{ ActivityNotFoundException -> 0x0031 }
        goto L_0x0038;
    L_0x0031:
        r5 = f2673a;
        r0 = "startMyFiles. Launch myFiles failed!";
        com.samsung.android.lxd.processor.utils.log.Log.m3855e(r5, r0);
    L_0x0038:
        return;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.samsung.android.lxd.a.d.c(android.app.Activity):void");
    }

    /* renamed from: d */
    private static void m3381d(Activity activity) {
        Intent intent = new Intent("android.intent.action.VIEW");
        String p = C0859d.m3395p();
        intent.setDataAndType(C0859d.m3365a(activity.getApplicationContext(), Files.getContentUri("external"), p), "application/zip");
        intent.putExtra("AbsolutePath", p);
        intent.putExtra("from-LoD", true);
        intent.setFlags(1);
        intent.setComponent(new ComponentName("com.sec.android.app.myfiles", VERSION.SDK_INT < 28 ? "com.sec.android.app.myfiles.common.MainActivity" : "com.sec.android.app.myfiles.external.ui.MainActivity"));
        try {
            String str = f2673a;
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("startMyFilesForResult. zip path : ");
            stringBuilder.append(p);
            Log.m3857i(str, stringBuilder.toString());
            activity.startActivityForResult(intent, 6);
        } catch (Activity activity2) {
            String str2 = f2673a;
            StringBuilder stringBuilder2 = new StringBuilder();
            stringBuilder2.append("startMyFilesForResult. Launch myFiles failed!");
            stringBuilder2.append(activity2.toString());
            Log.m3855e(str2, stringBuilder2.toString());
        }
    }

    /* renamed from: p */
    private static String m3395p() {
        if (C0859d.m3391l() == -1) {
            Log.m3853d(f2673a, "getDownloadedFileName: Invalid downloadId!");
            return null;
        }
        String path;
        String str;
        StringBuilder stringBuilder;
        try {
            Query query = new Query();
            query.setFilterById(new long[]{r0});
            Cursor query2 = ((DownloadManager) LxdApplication.m3344a().getApplicationContext().getSystemService("download")).query(query);
            query2.moveToFirst();
            path = Uri.parse(query2.getString(query2.getColumnIndex("local_uri"))).getPath();
        } catch (Exception e) {
            str = f2673a;
            stringBuilder = new StringBuilder();
            stringBuilder.append("getDownloadedFilePath Exception : ");
            stringBuilder.append(e);
            Log.m3853d(str, stringBuilder.toString());
            path = null;
        }
        str = f2673a;
        stringBuilder = new StringBuilder();
        stringBuilder.append("LOCAL FILE NAME : ");
        stringBuilder.append(path);
        Log.m3853d(str, stringBuilder.toString());
        return path;
    }

    /* renamed from: c */
    public static String m3377c() {
        try {
            String string = LxdApplication.m3344a().getSharedPreferences("prefs", 0).getString(String.valueOf("downloadFile"), null);
            String str = f2673a;
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("getDownloadedFileName : ");
            stringBuilder.append(string);
            Log.m3853d(str, stringBuilder.toString());
            return string;
        } catch (Exception e) {
            String str2 = f2673a;
            StringBuilder stringBuilder2 = new StringBuilder();
            stringBuilder2.append("getDownloadedFileName Error ! ");
            stringBuilder2.append(e.toString());
            Log.m3855e(str2, stringBuilder2.toString());
            return null;
        }
    }

    /* renamed from: q */
    private static boolean m3396q() {
        return LxdApplication.m3344a().getSharedPreferences("prefs", 0).getBoolean("needNoti", false);
    }

    /* renamed from: a */
    public static void m3370a(boolean z) {
        LxdApplication.m3344a().getSharedPreferences("prefs", 0).edit().putBoolean("needNoti", z).apply();
    }
}
