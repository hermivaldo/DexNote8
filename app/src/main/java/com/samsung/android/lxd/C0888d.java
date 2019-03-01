package com.samsung.android.lxd;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Build.VERSION;
import com.samsung.android.lxd.p064a.C0877o;
import com.samsung.android.lxd.processor.Processor;
import com.samsung.android.lxd.processor.utils.log.Log;
import java.util.ArrayList;
import java.util.List;

/* compiled from: ExtSDCardStatusReceiver */
/* renamed from: com.samsung.android.lxd.d */
public class C0888d extends BroadcastReceiver {
    /* renamed from: a */
    private static final String f2745a = "d";
    /* renamed from: b */
    private static C0888d f2746b;
    /* renamed from: c */
    private static List<C0887a> f2747c;
    /* renamed from: d */
    private boolean f2748d = false;

    /* compiled from: ExtSDCardStatusReceiver */
    /* renamed from: com.samsung.android.lxd.d$a */
    public interface C0887a {
        /* renamed from: a */
        void mo697a(String str);
    }

    /* renamed from: d */
    private IntentFilter m3553d() {
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

    /* renamed from: a */
    public static synchronized C0888d m3552a() {
        C0888d c0888d;
        synchronized (C0888d.class) {
            if (f2746b == null) {
                f2746b = new C0888d();
                f2747c = new ArrayList();
            }
            c0888d = f2746b;
        }
        return c0888d;
    }

    /* renamed from: b */
    public void m3555b() {
        String str = f2745a;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("registerReceiver: ");
        stringBuilder.append(this.f2748d);
        Log.m3853d(str, stringBuilder.toString());
        if (!this.f2748d) {
            LxdApplication.m3344a().registerReceiver(f2746b, m3553d());
            this.f2748d = true;
            C0877o.m3493c();
        }
    }

    /* renamed from: c */
    public void m3557c() {
        String str = f2745a;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("unregisterReceiver: ");
        stringBuilder.append(this.f2748d);
        Log.m3853d(str, stringBuilder.toString());
        if (this.f2748d) {
            LxdApplication.m3344a().unregisterReceiver(f2746b);
            this.f2748d = false;
        }
    }

    public void onReceive(Context context, Intent intent) {
        if (!(intent == null || intent.getAction() == null)) {
            if (this.f2748d != null) {
                StringBuilder stringBuilder;
                String str = null;
                context = intent.getAction();
                int hashCode = context.hashCode();
                if (hashCode != -1514214344) {
                    if (hashCode != -1410684549) {
                        if (hashCode != -963871873) {
                            if (hashCode == -625887599) {
                                if (context.equals("android.intent.action.MEDIA_EJECT") != null) {
                                    context = true;
                                    switch (context) {
                                        case null:
                                            str = f2745a;
                                            context = new StringBuilder();
                                            context.append("LxD Detect External SDCard Mounted at ");
                                            context.append(intent.getDataString());
                                            Log.m3857i(str, context.toString());
                                            str = "android.intent.action.MEDIA_MOUNTED";
                                            break;
                                        case 1:
                                        case 2:
                                            str = f2745a;
                                            context = new StringBuilder();
                                            context.append("LxD Detect External SDCard Unmounted: ");
                                            context.append(intent.getAction());
                                            Log.m3857i(str, context.toString());
                                            str = "android.intent.action.MEDIA_UNMOUNTED";
                                            break;
                                        case 3:
                                            context = intent.getIntExtra("android.os.storage.extra.VOLUME_STATE", -1);
                                            intent = f2745a;
                                            stringBuilder = new StringBuilder();
                                            stringBuilder.append("LxD Detect External SDCard Changed: ");
                                            stringBuilder.append(context);
                                            Log.m3857i(intent, stringBuilder.toString());
                                            if (context == null && context != 5) {
                                                switch (context) {
                                                    case 2:
                                                    case 3:
                                                        str = "android.intent.action.MEDIA_MOUNTED";
                                                        break;
                                                    default:
                                                        break;
                                                }
                                            }
                                            str = "android.intent.action.MEDIA_UNMOUNTED";
                                            break;
                                        default:
                                            Log.m3855e(f2745a, "can't find action");
                                            break;
                                    }
                                    if (str != null) {
                                        context = C0899f.m3592c();
                                        if (context != null) {
                                            context.mo1396y();
                                        }
                                        C0877o.m3493c();
                                        Processor.getInstance().notifySdCardStatus(str, C0877o.m3497d() == null ? "" : C0877o.m3497d());
                                        for (C0887a a : f2747c) {
                                            a.mo697a(str);
                                        }
                                    }
                                    return;
                                }
                            }
                        } else if (context.equals("android.intent.action.MEDIA_UNMOUNTED") != null) {
                            context = 2;
                            switch (context) {
                                case null:
                                    str = f2745a;
                                    context = new StringBuilder();
                                    context.append("LxD Detect External SDCard Mounted at ");
                                    context.append(intent.getDataString());
                                    Log.m3857i(str, context.toString());
                                    str = "android.intent.action.MEDIA_MOUNTED";
                                    break;
                                case 1:
                                case 2:
                                    str = f2745a;
                                    context = new StringBuilder();
                                    context.append("LxD Detect External SDCard Unmounted: ");
                                    context.append(intent.getAction());
                                    Log.m3857i(str, context.toString());
                                    str = "android.intent.action.MEDIA_UNMOUNTED";
                                    break;
                                case 3:
                                    context = intent.getIntExtra("android.os.storage.extra.VOLUME_STATE", -1);
                                    intent = f2745a;
                                    stringBuilder = new StringBuilder();
                                    stringBuilder.append("LxD Detect External SDCard Changed: ");
                                    stringBuilder.append(context);
                                    Log.m3857i(intent, stringBuilder.toString());
                                    if (context == null) {
                                        break;
                                    }
                                    str = "android.intent.action.MEDIA_UNMOUNTED";
                                    break;
                                default:
                                    Log.m3855e(f2745a, "can't find action");
                                    break;
                            }
                            if (str != null) {
                                context = C0899f.m3592c();
                                if (context != null) {
                                    context.mo1396y();
                                }
                                C0877o.m3493c();
                                if (C0877o.m3497d() == null) {
                                }
                                Processor.getInstance().notifySdCardStatus(str, C0877o.m3497d() == null ? "" : C0877o.m3497d());
                                while (context.hasNext() != null) {
                                    a.mo697a(str);
                                }
                            }
                            return;
                        }
                    } else if (context.equals("android.os.storage.action.VOLUME_STATE_CHANGED") != null) {
                        context = 3;
                        switch (context) {
                            case null:
                                str = f2745a;
                                context = new StringBuilder();
                                context.append("LxD Detect External SDCard Mounted at ");
                                context.append(intent.getDataString());
                                Log.m3857i(str, context.toString());
                                str = "android.intent.action.MEDIA_MOUNTED";
                                break;
                            case 1:
                            case 2:
                                str = f2745a;
                                context = new StringBuilder();
                                context.append("LxD Detect External SDCard Unmounted: ");
                                context.append(intent.getAction());
                                Log.m3857i(str, context.toString());
                                str = "android.intent.action.MEDIA_UNMOUNTED";
                                break;
                            case 3:
                                context = intent.getIntExtra("android.os.storage.extra.VOLUME_STATE", -1);
                                intent = f2745a;
                                stringBuilder = new StringBuilder();
                                stringBuilder.append("LxD Detect External SDCard Changed: ");
                                stringBuilder.append(context);
                                Log.m3857i(intent, stringBuilder.toString());
                                if (context == null) {
                                    break;
                                }
                                str = "android.intent.action.MEDIA_UNMOUNTED";
                                break;
                            default:
                                Log.m3855e(f2745a, "can't find action");
                                break;
                        }
                        if (str != null) {
                            context = C0899f.m3592c();
                            if (context != null) {
                                context.mo1396y();
                            }
                            C0877o.m3493c();
                            if (C0877o.m3497d() == null) {
                            }
                            Processor.getInstance().notifySdCardStatus(str, C0877o.m3497d() == null ? "" : C0877o.m3497d());
                            while (context.hasNext() != null) {
                                a.mo697a(str);
                            }
                        }
                        return;
                    }
                } else if (context.equals("android.intent.action.MEDIA_MOUNTED") != null) {
                    context = null;
                    switch (context) {
                        case null:
                            str = f2745a;
                            context = new StringBuilder();
                            context.append("LxD Detect External SDCard Mounted at ");
                            context.append(intent.getDataString());
                            Log.m3857i(str, context.toString());
                            str = "android.intent.action.MEDIA_MOUNTED";
                            break;
                        case 1:
                        case 2:
                            str = f2745a;
                            context = new StringBuilder();
                            context.append("LxD Detect External SDCard Unmounted: ");
                            context.append(intent.getAction());
                            Log.m3857i(str, context.toString());
                            str = "android.intent.action.MEDIA_UNMOUNTED";
                            break;
                        case 3:
                            context = intent.getIntExtra("android.os.storage.extra.VOLUME_STATE", -1);
                            intent = f2745a;
                            stringBuilder = new StringBuilder();
                            stringBuilder.append("LxD Detect External SDCard Changed: ");
                            stringBuilder.append(context);
                            Log.m3857i(intent, stringBuilder.toString());
                            if (context == null) {
                                break;
                            }
                            str = "android.intent.action.MEDIA_UNMOUNTED";
                            break;
                        default:
                            Log.m3855e(f2745a, "can't find action");
                            break;
                    }
                    if (str != null) {
                        context = C0899f.m3592c();
                        if (context != null) {
                            context.mo1396y();
                        }
                        C0877o.m3493c();
                        if (C0877o.m3497d() == null) {
                        }
                        Processor.getInstance().notifySdCardStatus(str, C0877o.m3497d() == null ? "" : C0877o.m3497d());
                        while (context.hasNext() != null) {
                            a.mo697a(str);
                        }
                    }
                    return;
                }
                context = -1;
                switch (context) {
                    case null:
                        str = f2745a;
                        context = new StringBuilder();
                        context.append("LxD Detect External SDCard Mounted at ");
                        context.append(intent.getDataString());
                        Log.m3857i(str, context.toString());
                        str = "android.intent.action.MEDIA_MOUNTED";
                        break;
                    case 1:
                    case 2:
                        str = f2745a;
                        context = new StringBuilder();
                        context.append("LxD Detect External SDCard Unmounted: ");
                        context.append(intent.getAction());
                        Log.m3857i(str, context.toString());
                        str = "android.intent.action.MEDIA_UNMOUNTED";
                        break;
                    case 3:
                        context = intent.getIntExtra("android.os.storage.extra.VOLUME_STATE", -1);
                        intent = f2745a;
                        stringBuilder = new StringBuilder();
                        stringBuilder.append("LxD Detect External SDCard Changed: ");
                        stringBuilder.append(context);
                        Log.m3857i(intent, stringBuilder.toString());
                        if (context == null) {
                            break;
                        }
                        str = "android.intent.action.MEDIA_UNMOUNTED";
                        break;
                    default:
                        Log.m3855e(f2745a, "can't find action");
                        break;
                }
                if (str != null) {
                    context = C0899f.m3592c();
                    if (context != null) {
                        context.mo1396y();
                    }
                    C0877o.m3493c();
                    if (C0877o.m3497d() == null) {
                    }
                    Processor.getInstance().notifySdCardStatus(str, C0877o.m3497d() == null ? "" : C0877o.m3497d());
                    while (context.hasNext() != null) {
                        a.mo697a(str);
                    }
                }
                return;
            }
        }
        context = f2745a;
        intent = new StringBuilder();
        intent.append("onReceive null: ");
        intent.append(this.f2748d);
        Log.m3857i(context, intent.toString());
    }

    /* renamed from: a */
    public void m3554a(C0887a c0887a) {
        f2747c.add(c0887a);
    }

    /* renamed from: b */
    public void m3556b(C0887a c0887a) {
        f2747c.remove(c0887a);
    }
}
