package com.samsung.android.lxd.a;

import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import com.samsung.android.lxd.R;

/* compiled from: WindowHelper */
public class p {
    private static final String a = "p";
    private com.samsung.android.lxd.fragment.a b = null;
    private com.samsung.android.lxd.a c = null;

    /* compiled from: WindowHelper */
    public interface a {
        void a();
    }

    public void a(int i, final int i2, Context context, final a aVar) {
        this.c = (com.samsung.android.lxd.a) context;
        o.a(new Runnable() {
            public void run() {
                p.this.b = com.samsung.android.lxd.fragment.a.a().a(p.this.c).b(i2).e(0).a(false);
                if (aVar != null) {
                    p.this.b.c((int) R.string.popup_cancel).a(new OnClickListener() {
                        public void onClick(DialogInterface dialogInterface, int i) {
                            aVar.a();
                            p.this.b.dismissAllowingStateLoss();
                        }
                    });
                }
                p.this.b.e();
            }
        });
    }

    public void a() {
        o.a(new Runnable() {
            public void run() {
                if (!(p.this.b == null || p.this.b.getFragmentManager() == null)) {
                    p.this.b.dismissAllowingStateLoss();
                }
                p.this.b = null;
            }
        });
    }

    public void a(int i, String str, Context context, a aVar) {
        final Context context2 = context;
        final int i2 = i;
        final String str2 = str;
        final a aVar2 = aVar;
        o.a(new Runnable() {
            public void run() {
                p.this.b = com.samsung.android.lxd.fragment.a.a().a((com.samsung.android.lxd.a) context2).a(i2).a(str2).c((int) R.string.ok).a(new OnClickListener() {
                    public void onClick(DialogInterface dialogInterface, int i) {
                        if (aVar2 != null) {
                            aVar2.a();
                        }
                        p.this.b.dismissAllowingStateLoss();
                    }
                }).a(false).e();
            }
        });
    }

    public boolean b() {
        return this.b != null && this.b.i();
    }
}
