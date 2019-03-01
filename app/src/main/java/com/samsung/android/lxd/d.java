package com.samsung.android.lxd;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Build.VERSION;
import com.samsung.android.lxd.a.o;
import com.samsung.android.lxd.processor.Processor;
import com.samsung.android.lxd.processor.utils.log.Log;
import java.util.ArrayList;
import java.util.List;

/* compiled from: ExtSDCardStatusReceiver */
public class d extends BroadcastReceiver {
    private static final String a = "d";
    private static d b;
    private static List<a> c;
    private boolean d = false;

    /* compiled from: ExtSDCardStatusReceiver */
    public interface a {
        void a(String str);
    }

    private IntentFilter d() {
        IntentFilter intentFilter = new IntentFilter();
        if (VERSION.SDK_INT >= 28) {
            intentFilter.addAction("android.os.storage.action.VOLUME_STATE_CHANGED");
        } else {
            intentFilter.addAction("android.intent.action.MEDIA_EJECT");
            intentFilter.addAction("android.intent.action.MEDIA_UNMOUNTED");
            intentFilter.addAction("android.intent.action.MEDIA_MOUNTED");
            intentFilter.addDataScheme("file");
        }
        return intentFilter;
    }

    public static synchronized d a() {
        d dVar;
        synchronized (d.class) {
            if (b == null) {
                b = new d();
                c = new ArrayList();
            }
            dVar = b;
        }
        return dVar;
    }

    public void b() {
        String str = a;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("registerReceiver: ");
        stringBuilder.append(this.d);
        Log.d(str, stringBuilder.toString());
        if (!this.d) {
            LxdApplication.a().registerReceiver(b, d());
            this.d = true;
            o.c();
        }
    }

    public void c() {
        String str = a;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("unregisterReceiver: ");
        stringBuilder.append(this.d);
        Log.d(str, stringBuilder.toString());
        if (this.d) {
            LxdApplication.a().unregisterReceiver(b);
            this.d = false;
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:27:0x0059  */
    /* JADX WARNING: Removed duplicated region for block: B:36:0x00a9  */
    /* JADX WARNING: Removed duplicated region for block: B:35:0x008c  */
    /* JADX WARNING: Removed duplicated region for block: B:28:0x0061  */
    /* JADX WARNING: Removed duplicated region for block: B:38:0x00c7  */
    /* JADX WARNING: Removed duplicated region for block: B:27:0x0059  */
    /* JADX WARNING: Removed duplicated region for block: B:36:0x00a9  */
    /* JADX WARNING: Removed duplicated region for block: B:35:0x008c  */
    /* JADX WARNING: Removed duplicated region for block: B:28:0x0061  */
    /* JADX WARNING: Removed duplicated region for block: B:38:0x00c7  */
    /* JADX WARNING: Removed duplicated region for block: B:27:0x0059  */
    /* JADX WARNING: Removed duplicated region for block: B:36:0x00a9  */
    /* JADX WARNING: Removed duplicated region for block: B:35:0x008c  */
    /* JADX WARNING: Removed duplicated region for block: B:28:0x0061  */
    /* JADX WARNING: Removed duplicated region for block: B:38:0x00c7  */
    /* JADX WARNING: Removed duplicated region for block: B:27:0x0059  */
    /* JADX WARNING: Removed duplicated region for block: B:36:0x00a9  */
    /* JADX WARNING: Removed duplicated region for block: B:35:0x008c  */
    /* JADX WARNING: Removed duplicated region for block: B:28:0x0061  */
    /* JADX WARNING: Removed duplicated region for block: B:38:0x00c7  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void onReceive(Context context, Intent intent) {
        String str;
        if (intent == null || intent.getAction() == null || !this.d) {
            str = a;
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("onReceive null: ");
            stringBuilder.append(this.d);
            Log.i(str, stringBuilder.toString());
            return;
        }
        int i;
        String str2 = null;
        str = intent.getAction();
        int hashCode = str.hashCode();
        if (hashCode != -1514214344) {
            if (hashCode != -1410684549) {
                if (hashCode != -963871873) {
                    if (hashCode == -625887599 && str.equals("android.intent.action.MEDIA_EJECT")) {
                        i = 1;
                        StringBuilder stringBuilder2;
                        switch (i) {
                            case 0:
                                str2 = a;
                                stringBuilder2 = new StringBuilder();
                                stringBuilder2.append("LxD Detect External SDCard Mounted at ");
                                stringBuilder2.append(intent.getDataString());
                                Log.i(str2, stringBuilder2.toString());
                                str2 = "android.intent.action.MEDIA_MOUNTED";
                                break;
                            case 1:
                            case 2:
                                str2 = a;
                                stringBuilder2 = new StringBuilder();
                                stringBuilder2.append("LxD Detect External SDCard Unmounted: ");
                                stringBuilder2.append(intent.getAction());
                                Log.i(str2, stringBuilder2.toString());
                                str2 = "android.intent.action.MEDIA_UNMOUNTED";
                                break;
                            case 3:
                                i = intent.getIntExtra("android.os.storage.extra.VOLUME_STATE", -1);
                                String str3 = a;
                                StringBuilder stringBuilder3 = new StringBuilder();
                                stringBuilder3.append("LxD Detect External SDCard Changed: ");
                                stringBuilder3.append(i);
                                Log.i(str3, stringBuilder3.toString());
                                if (i != 0 && i != 5) {
                                    switch (i) {
                                        case 2:
                                        case 3:
                                            str2 = "android.intent.action.MEDIA_MOUNTED";
                                            break;
                                    }
                                }
                                str2 = "android.intent.action.MEDIA_UNMOUNTED";
                                break;
                                break;
                            default:
                                Log.e(a, "can't find action");
                                break;
                        }
                        if (str2 != null) {
                            a c = f.c();
                            if (c != null) {
                                c.y();
                            }
                            o.c();
                            Processor.getInstance().notifySdCardStatus(str2, o.d() == null ? "" : o.d());
                            for (a a : c) {
                                a.a(str2);
                            }
                        }
                    }
                } else if (str.equals("android.intent.action.MEDIA_UNMOUNTED")) {
                    i = 2;
                    switch (i) {
                        case 0:
                            break;
                        case 1:
                        case 2:
                            break;
                        case 3:
                            break;
                        default:
                            break;
                    }
                    if (str2 != null) {
                    }
                }
            } else if (str.equals("android.os.storage.action.VOLUME_STATE_CHANGED")) {
                i = 3;
                switch (i) {
                    case 0:
                        break;
                    case 1:
                    case 2:
                        break;
                    case 3:
                        break;
                    default:
                        break;
                }
                if (str2 != null) {
                }
            }
        } else if (str.equals("android.intent.action.MEDIA_MOUNTED")) {
            i = 0;
            switch (i) {
                case 0:
                    break;
                case 1:
                case 2:
                    break;
                case 3:
                    break;
                default:
                    break;
            }
            if (str2 != null) {
            }
        }
        i = -1;
        switch (i) {
            case 0:
                break;
            case 1:
            case 2:
                break;
            case 3:
                break;
            default:
                break;
        }
        if (str2 != null) {
        }
    }

    public void a(a aVar) {
        c.add(aVar);
    }

    public void b(a aVar) {
        c.remove(aVar);
    }
}
