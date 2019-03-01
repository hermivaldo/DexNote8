package com.samsung.android.lxd;

import android.app.Activity;
import android.app.Application.ActivityLifecycleCallbacks;
import android.os.Bundle;
import com.samsung.android.lxd.C0899f.C13501;
import com.samsung.android.lxd.p064a.C0874l;
import com.samsung.android.lxd.p064a.C0877o;
import com.samsung.android.lxd.p064a.C0884p;
import com.samsung.android.lxd.p064a.C0884p.C0883a;
import com.samsung.android.lxd.processor.INetworkService.ICallback;
import com.samsung.android.lxd.processor.Processor;
import com.samsung.android.lxd.processor.Processor.OpenServiceInfo;
import com.samsung.android.lxd.processor.control.channel.ControlChannelType;
import com.samsung.android.lxd.processor.utils.log.Log;
import java.util.ArrayList;
import java.util.Iterator;

/* compiled from: LxdActivityLifecycleManager */
/* renamed from: com.samsung.android.lxd.f */
public class C0899f implements ActivityLifecycleCallbacks {
    /* renamed from: a */
    private static final String f2792a = "f";
    /* renamed from: b */
    private static ArrayList<C0889e> f2793b = null;
    /* renamed from: g */
    private static boolean f2794g = false;
    /* renamed from: h */
    private static boolean f2795h = false;
    /* renamed from: c */
    private final C0884p f2796c;
    /* renamed from: d */
    private final ICallback f2797d;
    /* renamed from: e */
    private boolean f2798e = false;
    /* renamed from: f */
    private boolean f2799f = false;

    /* compiled from: LxdActivityLifecycleManager */
    /* renamed from: com.samsung.android.lxd.f$1 */
    class C13501 implements ICallback {
        /* renamed from: a */
        final /* synthetic */ C0899f f4445a;

        /* compiled from: LxdActivityLifecycleManager */
        /* renamed from: com.samsung.android.lxd.f$1$1 */
        class C08901 implements Runnable {
            /* renamed from: a */
            final /* synthetic */ C13501 f2770a;

            C08901(C13501 c13501) {
                this.f2770a = c13501;
            }

            public void run() {
                Log.m3857i(C0899f.f2792a, "onServiceOpened");
                C0899f.f2795h = true;
                Iterator it = C0899f.f2793b.iterator();
                while (it.hasNext()) {
                    ((C0889e) it.next()).mo720s();
                }
                this.f2770a.f4445a.f2796c.m3546a();
            }
        }

        /* compiled from: LxdActivityLifecycleManager */
        /* renamed from: com.samsung.android.lxd.f$1$5 */
        class C08945 implements Runnable {
            /* renamed from: a */
            final /* synthetic */ C13501 f2784a;

            C08945(C13501 c13501) {
                this.f2784a = c13501;
            }

            public void run() {
                Log.m3857i(C0899f.f2792a, "onMonitoringStarted");
                Iterator it = C0899f.f2793b.iterator();
                while (it.hasNext()) {
                    ((C0889e) it.next()).mo726z();
                }
            }
        }

        /* compiled from: LxdActivityLifecycleManager */
        /* renamed from: com.samsung.android.lxd.f$1$6 */
        class C08956 implements Runnable {
            /* renamed from: a */
            final /* synthetic */ C13501 f2785a;

            C08956(C13501 c13501) {
                this.f2785a = c13501;
            }

            public void run() {
                Log.m3857i(C0899f.f2792a, "onMonitoringStopped");
                Iterator it = C0899f.f2793b.iterator();
                while (it.hasNext()) {
                    ((C0889e) it.next()).mo699A();
                }
            }
        }

        C13501(C0899f c0899f) {
            this.f4445a = c0899f;
        }

        public void onServiceOpened() {
            C0877o.m3470a(new C08901(this));
        }

        public void onServiceClosed() {
            C0877o.m3470a(new Runnable(this) {
                /* renamed from: a */
                final /* synthetic */ C13501 f2755a;

                {
                    this.f2755a = r1;
                }

                public void run() {
                    Log.m3857i(C0899f.f2792a, "onServiceClosed");
                    C0899f.f2795h = false;
                }
            });
        }

        public void onServiceError(final String str) {
            C0877o.m3470a(new Runnable(this) {
                /* renamed from: b */
                final /* synthetic */ C13501 f2768b;

                public void run() {
                    Log.m3857i(C0899f.f2792a, "onServiceError");
                    for (int size = C0899f.f2793b.size() - 1; size >= 0; size--) {
                        C0889e c0889e = (C0889e) C0899f.f2793b.get(size);
                        if (c0889e.mo712f(str, this.f2768b.m6225a(c0889e))) {
                            this.f2768b.f4445a.f2798e = true;
                            return;
                        }
                    }
                    this.f2768b.m6224a(str);
                }
            });
        }

        public void onContainerOpened() {
            C0877o.m3470a(new Runnable(this) {
                /* renamed from: a */
                final /* synthetic */ C13501 f2769a;

                {
                    this.f2769a = r1;
                }

                public void run() {
                    Log.m3857i(C0899f.f2792a, "onContainerOpened");
                    Iterator it = C0899f.f2793b.iterator();
                    while (it.hasNext()) {
                        ((C0889e) it.next()).mo721t();
                    }
                }
            });
        }

        public void onContainerClosed() {
            C0877o.m3470a(new Runnable(this) {
                /* renamed from: a */
                final /* synthetic */ C13501 f2771a;

                {
                    this.f2771a = r1;
                }

                public void run() {
                    Log.m3857i(C0899f.f2792a, "onContainerClosed");
                    C0899f.f2794g = false;
                    Iterator it = C0899f.f2793b.iterator();
                    while (it.hasNext()) {
                        ((C0889e) it.next()).mo722u();
                    }
                }
            });
        }

        public void onContainerResumed() {
            C0877o.m3470a(new Runnable(this) {
                /* renamed from: a */
                final /* synthetic */ C13501 f2772a;

                {
                    this.f2772a = r1;
                }

                public void run() {
                    Log.m3857i(C0899f.f2792a, "onContainerResumed");
                    Iterator it = C0899f.f2793b.iterator();
                    while (it.hasNext()) {
                        ((C0889e) it.next()).mo723v();
                    }
                }
            });
        }

        public void onContainerPaused() {
            C0877o.m3470a(new Runnable(this) {
                /* renamed from: a */
                final /* synthetic */ C13501 f2773a;

                {
                    this.f2773a = r1;
                }

                public void run() {
                    Log.m3857i(C0899f.f2792a, "onContainerPaused");
                    Iterator it = C0899f.f2793b.iterator();
                    while (it.hasNext()) {
                        ((C0889e) it.next()).mo724w();
                    }
                }
            });
        }

        public void onContainerStarted(final String str) {
            C0877o.m3470a(new Runnable(this) {
                /* renamed from: b */
                final /* synthetic */ C13501 f2775b;

                public void run() {
                    Log.m3857i(C0899f.f2792a, "onContainerStarted");
                    C0899f.f2794g = true;
                    Iterator it = C0899f.f2793b.iterator();
                    while (it.hasNext()) {
                        ((C0889e) it.next()).mo714i(str);
                    }
                }
            });
        }

        public void onContainerStopped() {
            C0877o.m3470a(new Runnable(this) {
                /* renamed from: a */
                final /* synthetic */ C13501 f2776a;

                {
                    this.f2776a = r1;
                }

                public void run() {
                    Log.m3857i(C0899f.f2792a, "onContainerStopped");
                    C0899f.f2794g = false;
                    Iterator it = C0899f.f2793b.iterator();
                    while (it.hasNext()) {
                        ((C0889e) it.next()).mo725x();
                    }
                }
            });
        }

        public void onAudioConnected(final String str) {
            C0877o.m3470a(new Runnable(this) {
                /* renamed from: b */
                final /* synthetic */ C13501 f2778b;

                public void run() {
                    Log.m3857i(C0899f.f2792a, "onAudioConnected");
                    Iterator it = C0899f.f2793b.iterator();
                    while (it.hasNext()) {
                        ((C0889e) it.next()).mo715j(str);
                    }
                }
            });
        }

        public void onAudioDisconnected(final String str) {
            C0877o.m3470a(new Runnable(this) {
                /* renamed from: b */
                final /* synthetic */ C13501 f2780b;

                public void run() {
                    Log.m3857i(C0899f.f2792a, "onAudioDisconnected");
                    Iterator it = C0899f.f2793b.iterator();
                    while (it.hasNext()) {
                        ((C0889e) it.next()).mo716k(str);
                    }
                }
            });
        }

        public void onMemoryUsageReceived(final String str, final int i) {
            C0877o.m3470a(new Runnable(this) {
                /* renamed from: c */
                final /* synthetic */ C13501 f2783c;

                public void run() {
                    Log.m3857i(C0899f.f2792a, "onMemoryUsageReceived");
                    Iterator it = C0899f.f2793b.iterator();
                    while (it.hasNext()) {
                        ((C0889e) it.next()).mo705c(str, i);
                    }
                }
            });
        }

        public void onMonitoringStarted() {
            C0877o.m3470a(new C08945(this));
        }

        public void onMonitoringStopped() {
            C0877o.m3470a(new C08956(this));
        }

        public void onMonitoringNotified(final String str) {
            C0877o.m3470a(new Runnable(this) {
                /* renamed from: b */
                final /* synthetic */ C13501 f2787b;

                public void run() {
                    Log.m3853d(C0899f.f2792a, "onMonitoringNotified");
                    Iterator it = C0899f.f2793b.iterator();
                    while (it.hasNext()) {
                        ((C0889e) it.next()).mo718n(str);
                    }
                }
            });
        }

        public void onTextCommitted(final String str) {
            C0877o.m3470a(new Runnable(this) {
                /* renamed from: b */
                final /* synthetic */ C13501 f2789b;

                public void run() {
                    Log.m3853d(C0899f.f2792a, "onTextCommitted");
                    Iterator it = C0899f.f2793b.iterator();
                    while (it.hasNext()) {
                        ((C0889e) it.next()).mo719o(str);
                    }
                }
            });
        }

        public void onContainerError(final String str) {
            C0877o.m3470a(new Runnable(this) {
                /* renamed from: b */
                final /* synthetic */ C13501 f2791b;

                public void run() {
                    Log.m3857i(C0899f.f2792a, "onContainerError");
                    C0899f.f2794g = false;
                    Iterator it = C0899f.f2793b.iterator();
                    while (it.hasNext()) {
                        if (((C0889e) it.next()).mo713h(str)) {
                            return;
                        }
                    }
                }
            });
        }

        public void onDebugLogReceived(final String str, final boolean z) {
            C0877o.m3470a(new Runnable(this) {
                /* renamed from: c */
                final /* synthetic */ C13501 f2751c;

                public void run() {
                    String g = C0899f.f2792a;
                    StringBuilder stringBuilder = new StringBuilder();
                    stringBuilder.append("onDebugLogReceived: ");
                    stringBuilder.append(z);
                    stringBuilder.append(", ");
                    stringBuilder.append(str);
                    Log.m3857i(g, stringBuilder.toString());
                    Iterator it = C0899f.f2793b.iterator();
                    while (it.hasNext()) {
                        ((C0889e) it.next()).mo704b(str, z);
                    }
                }
            });
        }

        public void onImageVersionReceived(final String str, final int i) {
            C0877o.m3470a(new Runnable(this) {
                /* renamed from: c */
                final /* synthetic */ C13501 f2754c;

                public void run() {
                    String g = C0899f.f2792a;
                    StringBuilder stringBuilder = new StringBuilder();
                    stringBuilder.append("onImageVersionReceived: ");
                    stringBuilder.append(i);
                    stringBuilder.append(", ");
                    stringBuilder.append(str);
                    Log.m3857i(g, stringBuilder.toString());
                    Iterator it = C0899f.f2793b.iterator();
                    while (it.hasNext()) {
                        ((C0889e) it.next()).mo703b(str, i);
                    }
                }
            });
        }

        public void onImageMinSizeReceived(final String str, final boolean z) {
            C0877o.m3470a(new Runnable(this) {
                /* renamed from: c */
                final /* synthetic */ C13501 f2758c;

                public void run() {
                    String g = C0899f.f2792a;
                    StringBuilder stringBuilder = new StringBuilder();
                    stringBuilder.append("onImageMinSizeReceived: ");
                    stringBuilder.append(z);
                    Log.m3857i(g, stringBuilder.toString());
                    Iterator it = C0899f.f2793b.iterator();
                    while (it.hasNext()) {
                        ((C0889e) it.next()).mo706c(str, z);
                    }
                }
            });
        }

        public void onImageSizeUpdated(final String str, final boolean z) {
            C0877o.m3470a(new Runnable(this) {
                /* renamed from: c */
                final /* synthetic */ C13501 f2761c;

                public void run() {
                    String g = C0899f.f2792a;
                    StringBuilder stringBuilder = new StringBuilder();
                    stringBuilder.append("onImageSizeUpdated: ");
                    stringBuilder.append(z);
                    Log.m3857i(g, stringBuilder.toString());
                    Iterator it = C0899f.f2793b.iterator();
                    while (it.hasNext()) {
                        ((C0889e) it.next()).mo708d(str, z);
                    }
                }
            });
        }

        public void onImageRebased(final String str, final boolean z) {
            C0877o.m3470a(new Runnable(this) {
                /* renamed from: c */
                final /* synthetic */ C13501 f2764c;

                public void run() {
                    String g = C0899f.f2792a;
                    StringBuilder stringBuilder = new StringBuilder();
                    stringBuilder.append("onImageRebased: ");
                    stringBuilder.append(z);
                    Log.m3857i(g, stringBuilder.toString());
                    Iterator it = C0899f.f2793b.iterator();
                    while (it.hasNext()) {
                        ((C0889e) it.next()).mo711e(str, z);
                    }
                }
            });
        }

        public void onCutTextReceived(final String str) {
            C0877o.m3470a(new Runnable(this) {
                /* renamed from: b */
                final /* synthetic */ C13501 f2766b;

                public void run() {
                    Log.m3857i(C0899f.f2792a, "onCutTextReceived");
                    Iterator it = C0899f.f2793b.iterator();
                    while (it.hasNext()) {
                        ((C0889e) it.next()).mo717l(str);
                    }
                }
            });
        }

        /* renamed from: a */
        private boolean m6225a(C0889e c0889e) {
            return c0889e.equals(C0899f.m3592c());
        }

        /* renamed from: a */
        private void m6224a(String str) {
            this.f4445a.f2796c.m3546a();
            this.f4445a.f2796c.m3548a((int) R.string.unexpected_error, LxdApplication.m3344a().getString(R.string.try_again), C0899f.m3592c(), new C0883a(this) {
                /* renamed from: a */
                final /* synthetic */ C13501 f4444a;

                {
                    this.f4444a = r1;
                }

                /* renamed from: a */
                public void mo658a() {
                    C0874l.m3432a("015", String.valueOf(1501));
                    Log.m3855e(C0899f.f2792a, "onError: ");
                    this.f4444a.f4445a.m3600j();
                    this.f4444a.f4445a.m3599i();
                }
            });
        }
    }

    /* compiled from: LxdActivityLifecycleManager */
    /* renamed from: com.samsung.android.lxd.f$2 */
    class C13512 implements C0883a {
        /* renamed from: a */
        final /* synthetic */ C0899f f4446a;

        C13512(C0899f c0899f) {
            this.f4446a = c0899f;
        }

        /* renamed from: a */
        public void mo658a() {
            Log.m3855e(C0899f.f2792a, "onActivityCreated: canceled");
            Iterator it = C0899f.f2793b.iterator();
            while (it.hasNext()) {
                ((Activity) ((C0889e) it.next())).finish();
            }
        }
    }

    public void onActivitySaveInstanceState(Activity activity, Bundle bundle) {
    }

    C0899f() {
        f2793b = new ArrayList();
        this.f2796c = new C0884p();
        this.f2797d = new C13501(this);
    }

    /* renamed from: i */
    private void m3599i() {
        this.f2799f = true;
        if (!(C0899f.m3592c() == null || C0899f.m3592c().mo1298o())) {
            this.f2796c.m3547a((int) R.string.app_name, (int) R.string.wait, C0899f.m3592c(), new C13512(this));
        }
        Processor.getInstance().openService(new OpenServiceInfo(ControlChannelType.NST, this.f2797d));
    }

    /* renamed from: j */
    private void m3600j() {
        f2795h = false;
        f2794g = false;
        this.f2799f = false;
        this.f2798e = false;
        Processor.getInstance().closeService();
    }

    /* renamed from: k */
    private static void m3601k() {
        String str = f2792a;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("dump of ");
        stringBuilder.append(f2793b.size());
        stringBuilder.append(" activities");
        Log.m3857i(str, stringBuilder.toString());
        Iterator it = f2793b.iterator();
        while (it.hasNext()) {
            ((C0889e) it.next()).mo702a();
        }
    }

    /* renamed from: l */
    private void m3602l() {
        if (!this.f2799f) {
            m3599i();
        }
    }

    /* renamed from: m */
    private void m3603m() {
        m3600j();
    }

    /* renamed from: a */
    public static boolean m3586a() {
        Iterator it = f2793b.iterator();
        while (it.hasNext()) {
            C0889e c0889e = (C0889e) it.next();
            if (!c0889e.mo707c()) {
                if (c0889e.mo709d()) {
                }
            }
            return false;
        }
        return true;
    }

    /* renamed from: b */
    public static int m3589b() {
        return f2793b.size();
    }

    /* renamed from: c */
    public static C1347a m3592c() {
        Iterator it = f2793b.iterator();
        while (it.hasNext()) {
            C0889e c0889e = (C0889e) it.next();
            if (c0889e.mo707c()) {
                return (C1347a) c0889e;
            }
        }
        return null;
    }

    /* renamed from: d */
    public static boolean m3594d() {
        return f2794g;
    }

    /* renamed from: e */
    public static boolean m3595e() {
        return f2795h;
    }

    /* renamed from: f */
    public static boolean m3596f() {
        String str = f2792a;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("finishAndRemoveTask: ");
        stringBuilder.append(C0899f.m3592c());
        stringBuilder.append(", ");
        stringBuilder.append(f2793b.size());
        Log.m3857i(str, stringBuilder.toString());
        if (C0899f.m3592c() != null) {
            C0899f.m3592c().finishAffinity();
            C0899f.m3592c().finishAndRemoveTask();
            return true;
        }
        if (f2793b.size() > 0) {
            Iterator it = f2793b.iterator();
            while (it.hasNext()) {
                Activity activity = (Activity) ((C0889e) it.next());
                activity.finishAffinity();
                activity.finishAndRemoveTask();
            }
        }
        return false;
    }

    public void onActivityCreated(Activity activity, Bundle bundle) {
        String str = f2792a;
        bundle = new StringBuilder();
        bundle.append("onActivityCreated : ");
        bundle.append(activity);
        Log.m3857i(str, bundle.toString());
        f2793b.add((C0889e) activity);
        C0899f.m3601k();
    }

    public void onActivityDestroyed(Activity activity) {
        String str = f2792a;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("onActivityDestroyed : ");
        stringBuilder.append(activity);
        Log.m3857i(str, stringBuilder.toString());
        f2793b.remove((C0889e) activity);
        if (f2793b.isEmpty() != null) {
            m3603m();
        } else if (this.f2798e != null) {
            this.f2798e = null;
            m3600j();
            m3599i();
        }
        C0899f.m3601k();
    }

    public void onActivityStarted(Activity activity) {
        String str = f2792a;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("onActivityStarted : ");
        stringBuilder.append(activity);
        Log.m3857i(str, stringBuilder.toString());
        if (C0877o.m3510g() != null) {
            m3602l();
        }
        C0899f.m3601k();
    }

    public void onActivityStopped(Activity activity) {
        String str = f2792a;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("onActivityStopped : ");
        stringBuilder.append(activity);
        Log.m3857i(str, stringBuilder.toString());
        C0899f.m3601k();
    }

    public void onActivityResumed(Activity activity) {
        String str = f2792a;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("onActivityResumed : ");
        stringBuilder.append(activity);
        Log.m3857i(str, stringBuilder.toString());
        C0899f.m3601k();
    }

    public void onActivityPaused(Activity activity) {
        String str = f2792a;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("onActivityPaused : ");
        stringBuilder.append(activity);
        Log.m3857i(str, stringBuilder.toString());
        C0899f.m3601k();
    }
}
