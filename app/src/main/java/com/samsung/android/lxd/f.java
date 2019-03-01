package com.samsung.android.lxd;

import android.app.Activity;
import android.app.Application.ActivityLifecycleCallbacks;
import android.os.Bundle;
import com.samsung.android.lxd.a.l;
import com.samsung.android.lxd.a.o;
import com.samsung.android.lxd.a.p;
import com.samsung.android.lxd.a.p.a;
import com.samsung.android.lxd.processor.INetworkService.ICallback;
import com.samsung.android.lxd.processor.Processor;
import com.samsung.android.lxd.processor.Processor.OpenServiceInfo;
import com.samsung.android.lxd.processor.control.channel.ControlChannelType;
import com.samsung.android.lxd.processor.utils.log.Log;
import java.util.ArrayList;
import java.util.Iterator;

/* compiled from: LxdActivityLifecycleManager */
public class f implements ActivityLifecycleCallbacks {
    private static final String a = "f";
    private static ArrayList<e> b = null;
    private static boolean g = false;
    private static boolean h = false;
    private final p c;
    private final ICallback d;
    private boolean e = false;
    private boolean f = false;

    public void onActivitySaveInstanceState(Activity activity, Bundle bundle) {
    }

    f() {
        b = new ArrayList();
        this.c = new p();
        this.d = new ICallback() {
            public void onServiceOpened() {
                o.a(new Runnable() {
                    public void run() {
                        Log.i(f.a, "onServiceOpened");
                        f.h = true;
                        Iterator it = f.b.iterator();
                        while (it.hasNext()) {
                            ((e) it.next()).s();
                        }
                        f.this.c.a();
                    }
                });
            }

            public void onServiceClosed() {
                o.a(new Runnable() {
                    public void run() {
                        Log.i(f.a, "onServiceClosed");
                        f.h = false;
                    }
                });
            }

            public void onServiceError(final String str) {
                o.a(new Runnable() {
                    public void run() {
                        Log.i(f.a, "onServiceError");
                        for (int size = f.b.size() - 1; size >= 0; size--) {
                            e eVar = (e) f.b.get(size);
                            if (eVar.f(str, AnonymousClass1.this.a(eVar))) {
                                f.this.e = true;
                                return;
                            }
                        }
                        AnonymousClass1.this.a(str);
                    }
                });
            }

            public void onContainerOpened() {
                o.a(new Runnable() {
                    public void run() {
                        Log.i(f.a, "onContainerOpened");
                        Iterator it = f.b.iterator();
                        while (it.hasNext()) {
                            ((e) it.next()).t();
                        }
                    }
                });
            }

            public void onContainerClosed() {
                o.a(new Runnable() {
                    public void run() {
                        Log.i(f.a, "onContainerClosed");
                        f.g = false;
                        Iterator it = f.b.iterator();
                        while (it.hasNext()) {
                            ((e) it.next()).u();
                        }
                    }
                });
            }

            public void onContainerResumed() {
                o.a(new Runnable() {
                    public void run() {
                        Log.i(f.a, "onContainerResumed");
                        Iterator it = f.b.iterator();
                        while (it.hasNext()) {
                            ((e) it.next()).v();
                        }
                    }
                });
            }

            public void onContainerPaused() {
                o.a(new Runnable() {
                    public void run() {
                        Log.i(f.a, "onContainerPaused");
                        Iterator it = f.b.iterator();
                        while (it.hasNext()) {
                            ((e) it.next()).w();
                        }
                    }
                });
            }

            public void onContainerStarted(final String str) {
                o.a(new Runnable() {
                    public void run() {
                        Log.i(f.a, "onContainerStarted");
                        f.g = true;
                        Iterator it = f.b.iterator();
                        while (it.hasNext()) {
                            ((e) it.next()).i(str);
                        }
                    }
                });
            }

            public void onContainerStopped() {
                o.a(new Runnable() {
                    public void run() {
                        Log.i(f.a, "onContainerStopped");
                        f.g = false;
                        Iterator it = f.b.iterator();
                        while (it.hasNext()) {
                            ((e) it.next()).x();
                        }
                    }
                });
            }

            public void onAudioConnected(final String str) {
                o.a(new Runnable() {
                    public void run() {
                        Log.i(f.a, "onAudioConnected");
                        Iterator it = f.b.iterator();
                        while (it.hasNext()) {
                            ((e) it.next()).j(str);
                        }
                    }
                });
            }

            public void onAudioDisconnected(final String str) {
                o.a(new Runnable() {
                    public void run() {
                        Log.i(f.a, "onAudioDisconnected");
                        Iterator it = f.b.iterator();
                        while (it.hasNext()) {
                            ((e) it.next()).k(str);
                        }
                    }
                });
            }

            public void onMemoryUsageReceived(final String str, final int i) {
                o.a(new Runnable() {
                    public void run() {
                        Log.i(f.a, "onMemoryUsageReceived");
                        Iterator it = f.b.iterator();
                        while (it.hasNext()) {
                            ((e) it.next()).c(str, i);
                        }
                    }
                });
            }

            public void onMonitoringStarted() {
                o.a(new Runnable() {
                    public void run() {
                        Log.i(f.a, "onMonitoringStarted");
                        Iterator it = f.b.iterator();
                        while (it.hasNext()) {
                            ((e) it.next()).z();
                        }
                    }
                });
            }

            public void onMonitoringStopped() {
                o.a(new Runnable() {
                    public void run() {
                        Log.i(f.a, "onMonitoringStopped");
                        Iterator it = f.b.iterator();
                        while (it.hasNext()) {
                            ((e) it.next()).A();
                        }
                    }
                });
            }

            public void onMonitoringNotified(final String str) {
                o.a(new Runnable() {
                    public void run() {
                        Log.d(f.a, "onMonitoringNotified");
                        Iterator it = f.b.iterator();
                        while (it.hasNext()) {
                            ((e) it.next()).n(str);
                        }
                    }
                });
            }

            public void onTextCommitted(final String str) {
                o.a(new Runnable() {
                    public void run() {
                        Log.d(f.a, "onTextCommitted");
                        Iterator it = f.b.iterator();
                        while (it.hasNext()) {
                            ((e) it.next()).o(str);
                        }
                    }
                });
            }

            public void onContainerError(final String str) {
                o.a(new Runnable() {
                    public void run() {
                        Log.i(f.a, "onContainerError");
                        f.g = false;
                        Iterator it = f.b.iterator();
                        while (it.hasNext()) {
                            if (((e) it.next()).h(str)) {
                                return;
                            }
                        }
                    }
                });
            }

            public void onDebugLogReceived(final String str, final boolean z) {
                o.a(new Runnable() {
                    public void run() {
                        String g = f.a;
                        StringBuilder stringBuilder = new StringBuilder();
                        stringBuilder.append("onDebugLogReceived: ");
                        stringBuilder.append(z);
                        stringBuilder.append(", ");
                        stringBuilder.append(str);
                        Log.i(g, stringBuilder.toString());
                        Iterator it = f.b.iterator();
                        while (it.hasNext()) {
                            ((e) it.next()).b(str, z);
                        }
                    }
                });
            }

            public void onImageVersionReceived(final String str, final int i) {
                o.a(new Runnable() {
                    public void run() {
                        String g = f.a;
                        StringBuilder stringBuilder = new StringBuilder();
                        stringBuilder.append("onImageVersionReceived: ");
                        stringBuilder.append(i);
                        stringBuilder.append(", ");
                        stringBuilder.append(str);
                        Log.i(g, stringBuilder.toString());
                        Iterator it = f.b.iterator();
                        while (it.hasNext()) {
                            ((e) it.next()).b(str, i);
                        }
                    }
                });
            }

            public void onImageMinSizeReceived(final String str, final boolean z) {
                o.a(new Runnable() {
                    public void run() {
                        String g = f.a;
                        StringBuilder stringBuilder = new StringBuilder();
                        stringBuilder.append("onImageMinSizeReceived: ");
                        stringBuilder.append(z);
                        Log.i(g, stringBuilder.toString());
                        Iterator it = f.b.iterator();
                        while (it.hasNext()) {
                            ((e) it.next()).c(str, z);
                        }
                    }
                });
            }

            public void onImageSizeUpdated(final String str, final boolean z) {
                o.a(new Runnable() {
                    public void run() {
                        String g = f.a;
                        StringBuilder stringBuilder = new StringBuilder();
                        stringBuilder.append("onImageSizeUpdated: ");
                        stringBuilder.append(z);
                        Log.i(g, stringBuilder.toString());
                        Iterator it = f.b.iterator();
                        while (it.hasNext()) {
                            ((e) it.next()).d(str, z);
                        }
                    }
                });
            }

            public void onImageRebased(final String str, final boolean z) {
                o.a(new Runnable() {
                    public void run() {
                        String g = f.a;
                        StringBuilder stringBuilder = new StringBuilder();
                        stringBuilder.append("onImageRebased: ");
                        stringBuilder.append(z);
                        Log.i(g, stringBuilder.toString());
                        Iterator it = f.b.iterator();
                        while (it.hasNext()) {
                            ((e) it.next()).e(str, z);
                        }
                    }
                });
            }

            public void onCutTextReceived(final String str) {
                o.a(new Runnable() {
                    public void run() {
                        Log.i(f.a, "onCutTextReceived");
                        Iterator it = f.b.iterator();
                        while (it.hasNext()) {
                            ((e) it.next()).l(str);
                        }
                    }
                });
            }

            private boolean a(e eVar) {
                return eVar.equals(f.c());
            }

            private void a(String str) {
                f.this.c.a();
                f.this.c.a((int) R.string.unexpected_error, LxdApplication.a().getString(R.string.try_again), f.c(), new a() {
                    public void a() {
                        l.a("015", String.valueOf(1501));
                        Log.e(f.a, "onError: ");
                        f.this.j();
                        f.this.i();
                    }
                });
            }
        };
    }

    private void i() {
        this.f = true;
        if (!(c() == null || c().o())) {
            this.c.a((int) R.string.app_name, (int) R.string.wait, c(), new a() {
                public void a() {
                    Log.e(f.a, "onActivityCreated: canceled");
                    Iterator it = f.b.iterator();
                    while (it.hasNext()) {
                        ((Activity) ((e) it.next())).finish();
                    }
                }
            });
        }
        Processor.getInstance().openService(new OpenServiceInfo(ControlChannelType.NST, this.d));
    }

    private void j() {
        h = false;
        g = false;
        this.f = false;
        this.e = false;
        Processor.getInstance().closeService();
    }

    private static void k() {
        String str = a;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("dump of ");
        stringBuilder.append(b.size());
        stringBuilder.append(" activities");
        Log.i(str, stringBuilder.toString());
        Iterator it = b.iterator();
        while (it.hasNext()) {
            ((e) it.next()).a();
        }
    }

    private void l() {
        if (!this.f) {
            i();
        }
    }

    private void m() {
        j();
    }

    public static boolean a() {
        Iterator it = b.iterator();
        while (it.hasNext()) {
            e eVar = (e) it.next();
            if (!eVar.c()) {
                if (eVar.d()) {
                }
            }
            return false;
        }
        return true;
    }

    public static int b() {
        return b.size();
    }

    public static a c() {
        Iterator it = b.iterator();
        while (it.hasNext()) {
            e eVar = (e) it.next();
            if (eVar.c()) {
                return (a) eVar;
            }
        }
        return null;
    }

    public static boolean d() {
        return g;
    }

    public static boolean e() {
        return h;
    }

    public static boolean f() {
        String str = a;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("finishAndRemoveTask: ");
        stringBuilder.append(c());
        stringBuilder.append(", ");
        stringBuilder.append(b.size());
        Log.i(str, stringBuilder.toString());
        if (c() != null) {
            c().finishAffinity();
            c().finishAndRemoveTask();
            return true;
        }
        if (b.size() > 0) {
            Iterator it = b.iterator();
            while (it.hasNext()) {
                Activity activity = (Activity) ((e) it.next());
                activity.finishAffinity();
                activity.finishAndRemoveTask();
            }
        }
        return false;
    }

    public void onActivityCreated(Activity activity, Bundle bundle) {
        String str = a;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("onActivityCreated : ");
        stringBuilder.append(activity);
        Log.i(str, stringBuilder.toString());
        b.add((e) activity);
        k();
    }

    public void onActivityDestroyed(Activity activity) {
        String str = a;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("onActivityDestroyed : ");
        stringBuilder.append(activity);
        Log.i(str, stringBuilder.toString());
        b.remove((e) activity);
        if (b.isEmpty()) {
            m();
        } else if (this.e) {
            this.e = false;
            j();
            i();
        }
        k();
    }

    public void onActivityStarted(Activity activity) {
        String str = a;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("onActivityStarted : ");
        stringBuilder.append(activity);
        Log.i(str, stringBuilder.toString());
        if (o.g()) {
            l();
        }
        k();
    }

    public void onActivityStopped(Activity activity) {
        String str = a;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("onActivityStopped : ");
        stringBuilder.append(activity);
        Log.i(str, stringBuilder.toString());
        k();
    }

    public void onActivityResumed(Activity activity) {
        String str = a;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("onActivityResumed : ");
        stringBuilder.append(activity);
        Log.i(str, stringBuilder.toString());
        k();
    }

    public void onActivityPaused(Activity activity) {
        String str = a;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("onActivityPaused : ");
        stringBuilder.append(activity);
        Log.i(str, stringBuilder.toString());
        k();
    }
}
