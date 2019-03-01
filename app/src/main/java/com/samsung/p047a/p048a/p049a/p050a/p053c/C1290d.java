package com.samsung.p047a.p048a.p049a.p050a.p053c;

import com.samsung.p047a.p048a.p049a.p050a.p063i.C0784a;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;

/* compiled from: SingleThreadExecutor */
/* renamed from: com.samsung.a.a.a.a.c.d */
public class C1290d implements C0760c {
    /* renamed from: a */
    private static ExecutorService f4327a;
    /* renamed from: b */
    private static C1290d f4328b;

    /* compiled from: SingleThreadExecutor */
    /* renamed from: com.samsung.a.a.a.a.c.d$1 */
    class C07611 implements ThreadFactory {
        /* renamed from: a */
        final /* synthetic */ C1290d f2423a;

        C07611(C1290d c1290d) {
            this.f2423a = c1290d;
        }

        public Thread newThread(Runnable runnable) {
            Thread thread = new Thread(runnable);
            thread.setPriority(1);
            thread.setDaemon(true);
            C0784a.m3255b("newThread on Executor");
            return thread;
        }
    }

    public C1290d() {
        f4327a = Executors.newSingleThreadExecutor(new C07611(this));
    }

    /* renamed from: a */
    public void mo649a(final C0759b c0759b) {
        f4327a.submit(new Runnable(this) {
            /* renamed from: b */
            final /* synthetic */ C1290d f2425b;

            public void run() {
                c0759b.mo650a();
                c0759b.mo651b();
            }
        });
    }

    /* renamed from: a */
    public static C0760c m6011a() {
        if (f4328b == null) {
            f4328b = new C1290d();
        }
        return f4328b;
    }
}
