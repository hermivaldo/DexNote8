package com.samsung.android.lxd.p064a;

import android.view.View;
import android.view.View.OnClickListener;
import java.util.Timer;
import java.util.TimerTask;

/* compiled from: OnClickListenerHelper */
/* renamed from: com.samsung.android.lxd.a.j */
public class C0869j implements OnClickListener {
    /* renamed from: a */
    private static final String f2699a = "j";

    public void onClick(final View view) {
        view.setEnabled(false);
        new Timer().schedule(new TimerTask(this) {
            /* renamed from: b */
            final /* synthetic */ C0869j f2698b;

            /* compiled from: OnClickListenerHelper */
            /* renamed from: com.samsung.android.lxd.a.j$1$1 */
            class C08671 implements Runnable {
                /* renamed from: a */
                final /* synthetic */ C08681 f2696a;

                C08671(C08681 c08681) {
                    this.f2696a = c08681;
                }

                public void run() {
                    view.setEnabled(true);
                }
            }

            public void run() {
                C0877o.m3470a(new C08671(this));
            }
        }, 500);
    }
}
