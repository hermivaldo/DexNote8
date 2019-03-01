package com.samsung.a.a.a.a.c;

import com.samsung.a.a.a.a.i.a;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;

/* compiled from: SingleThreadExecutor */
public class d implements c {
    private static ExecutorService a;
    private static d b;

    public d() {
        a = Executors.newSingleThreadExecutor(new ThreadFactory() {
            public Thread newThread(Runnable runnable) {
                Thread thread = new Thread(runnable);
                thread.setPriority(1);
                thread.setDaemon(true);
                a.b("newThread on Executor");
                return thread;
            }
        });
    }

    public void a(final b bVar) {
        a.submit(new Runnable() {
            public void run() {
                bVar.a();
                bVar.b();
            }
        });
    }

    public static c a() {
        if (b == null) {
            b = new d();
        }
        return b;
    }
}
