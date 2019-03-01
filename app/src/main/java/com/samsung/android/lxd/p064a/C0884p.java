package com.samsung.android.lxd.p064a;

import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import com.samsung.android.lxd.C1347a;
import com.samsung.android.lxd.R;
import com.samsung.android.lxd.fragment.C0936a;

/* compiled from: WindowHelper */
/* renamed from: com.samsung.android.lxd.a.p */
public class C0884p {
    /* renamed from: a */
    private static final String f2737a = "p";
    /* renamed from: b */
    private C0936a f2738b = null;
    /* renamed from: c */
    private C1347a f2739c = null;

    /* compiled from: WindowHelper */
    /* renamed from: com.samsung.android.lxd.a.p$2 */
    class C08802 implements Runnable {
        /* renamed from: a */
        final /* synthetic */ C0884p f2730a;

        C08802(C0884p c0884p) {
            this.f2730a = c0884p;
        }

        public void run() {
            if (!(this.f2730a.f2738b == null || this.f2730a.f2738b.getFragmentManager() == null)) {
                this.f2730a.f2738b.dismissAllowingStateLoss();
            }
            this.f2730a.f2738b = null;
        }
    }

    /* compiled from: WindowHelper */
    /* renamed from: com.samsung.android.lxd.a.p$a */
    public interface C0883a {
        /* renamed from: a */
        void mo658a();
    }

    /* renamed from: a */
    public void m3547a(int i, final int i2, Context context, final C0883a c0883a) {
        this.f2739c = (C1347a) context;
        C0877o.m3470a(new Runnable(this) {
            /* renamed from: c */
            final /* synthetic */ C0884p f2729c;

            /* compiled from: WindowHelper */
            /* renamed from: com.samsung.android.lxd.a.p$1$1 */
            class C08781 implements OnClickListener {
                /* renamed from: a */
                final /* synthetic */ C08791 f2726a;

                C08781(C08791 c08791) {
                    this.f2726a = c08791;
                }

                public void onClick(DialogInterface dialogInterface, int i) {
                    c0883a.mo658a();
                    this.f2726a.f2729c.f2738b.dismissAllowingStateLoss();
                }
            }

            public void run() {
                this.f2729c.f2738b = C0936a.m3752a().m3757a(this.f2729c.f2739c).m3762b(i2).m3771e(0).m3759a(false);
                if (c0883a != null) {
                    this.f2729c.f2738b.m3766c((int) R.string.popup_cancel).m3755a(new C08781(this));
                }
                this.f2729c.f2738b.m3770e();
            }
        });
    }

    /* renamed from: a */
    public void m3546a() {
        C0877o.m3470a(new C08802(this));
    }

    /* renamed from: a */
    public void m3548a(int i, String str, Context context, C0883a c0883a) {
        final Context context2 = context;
        final int i2 = i;
        final String str2 = str;
        final C0883a c0883a2 = c0883a;
        C0877o.m3470a(new Runnable(this) {
            /* renamed from: e */
            final /* synthetic */ C0884p f2736e;

            /* compiled from: WindowHelper */
            /* renamed from: com.samsung.android.lxd.a.p$3$1 */
            class C08811 implements OnClickListener {
                /* renamed from: a */
                final /* synthetic */ C08823 f2731a;

                C08811(C08823 c08823) {
                    this.f2731a = c08823;
                }

                public void onClick(DialogInterface dialogInterface, int i) {
                    if (c0883a2 != null) {
                        c0883a2.mo658a();
                    }
                    this.f2731a.f2736e.f2738b.dismissAllowingStateLoss();
                }
            }

            public void run() {
                this.f2736e.f2738b = C0936a.m3752a().m3757a((C1347a) context2).m3753a(i2).m3758a(str2).m3766c((int) R.string.ok).m3755a(new C08811(this)).m3759a(false).m3770e();
            }
        });
    }

    /* renamed from: b */
    public boolean m3549b() {
        return this.f2738b != null && this.f2738b.m3777i();
    }
}
