package com.samsung.android.lxd;

import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewTreeObserver.OnGlobalLayoutListener;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import com.samsung.android.lxd.fragment.C0936a;
import com.samsung.android.lxd.fragment.C1379g;
import com.samsung.android.lxd.p064a.C0869j;
import com.samsung.android.lxd.p064a.C0877o;
import com.samsung.android.lxd.processor.utils.log.Log;

/* compiled from: CreateEditActivity */
/* renamed from: com.samsung.android.lxd.b */
public class C1458b extends C1347a {
    /* renamed from: b */
    protected static int f4958b = -1;
    /* renamed from: c */
    private static final String f4959c = "b";
    /* renamed from: d */
    private C0936a f4960d;
    /* renamed from: e */
    private int f4961e;
    /* renamed from: f */
    private int f4962f;
    /* renamed from: g */
    private boolean f4963g;
    /* renamed from: h */
    private boolean f4964h;

    /* compiled from: CreateEditActivity */
    /* renamed from: com.samsung.android.lxd.b$2 */
    class C13482 extends C0869j {
        /* renamed from: a */
        final /* synthetic */ C1458b f4437a;

        C13482(C1458b c1458b) {
            this.f4437a = c1458b;
        }

        public void onClick(View view) {
            super.onClick(view);
            Log.m3853d(C1458b.f4959c, "cancel requested");
            ((C1379g) this.f4437a.f4960d).m6311l();
            this.f4437a.f4964h = true;
        }
    }

    public void onCreate(Bundle bundle) {
        if (!C0877o.m3499d((Context) this)) {
            m6183e();
        }
        super.onCreate(bundle);
        String str = f4959c;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("onCreate: progress: ");
        stringBuilder.append(f4958b);
        Log.m3853d(str, stringBuilder.toString());
        if (f4958b != -1) {
            m7072a(bundle);
        } else {
            m7069Q();
        }
    }

    protected void onSaveInstanceState(Bundle bundle) {
        String str = f4959c;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("onSaveInstanceState: mIncreasingType: ");
        stringBuilder.append(this.f4963g);
        Log.m3853d(str, stringBuilder.toString());
        bundle.putInt("ORIGIN_FILE_SIZE", this.f4961e);
        bundle.putInt("REQUEST_FILE_SIZE", this.f4962f);
        bundle.putBoolean("INCREASE_TYPE", this.f4963g);
        bundle.putBoolean("CANCEL_REQUESTED", this.f4964h);
        str = f4959c;
        stringBuilder = new StringBuilder();
        stringBuilder.append("onSaveInstanceState: ");
        stringBuilder.append(bundle.toString());
        Log.m3853d(str, stringBuilder.toString());
        super.onSaveInstanceState(bundle);
    }

    /* renamed from: f */
    public boolean mo712f(String str, boolean z) {
        super.mo712f(str, z);
        mo1303N();
        return true;
    }

    /* renamed from: a */
    private void m7072a(Bundle bundle) {
        if (bundle != null) {
            this.f4961e = bundle.getInt("ORIGIN_FILE_SIZE");
            this.f4962f = bundle.getInt("REQUEST_FILE_SIZE");
            this.f4963g = bundle.getBoolean("INCREASE_TYPE");
            this.f4964h = bundle.getBoolean("CANCEL_REQUESTED");
            bundle = f4959c;
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("OriginMbFileSize: ");
            stringBuilder.append(this.f4961e);
            stringBuilder.append("RequestGbFileSize: ");
            stringBuilder.append(this.f4962f);
            stringBuilder.append("IncreasingType: ");
            stringBuilder.append(this.f4963g);
            stringBuilder.append("CancelRequested: ");
            stringBuilder.append(this.f4964h);
            Log.m3853d(bundle, stringBuilder.toString());
        }
        m7071a(this.f4962f);
    }

    /* renamed from: N */
    protected void mo1303N() {
        f4958b = -1;
        m7069Q();
    }

    /* renamed from: O */
    protected boolean mo1306O() {
        return f4958b != -1;
    }

    /* renamed from: Q */
    private void m7069Q() {
        if (this.f4960d != null) {
            this.f4960d.dismissAllowingStateLoss();
        }
        this.f4960d = null;
        this.f4962f = -1;
        this.f4961e = -1;
        this.f4963g = false;
        this.f4964h = false;
    }

    /* renamed from: a */
    protected boolean m7077a(boolean z, String str, int i, int i2) {
        if (z) {
            if (i == i2) {
                return false;
            }
            this.f4962f = i2;
            this.f4961e = C0877o.m3505f(str);
            if (!TextUtils.isEmpty(C0877o.m3497d()) && str.contains(C0877o.m3497d()) && i2 > i) {
                this.f4963g = true;
            }
            f4958b = 0;
            m7071a(i2);
        } else if (this.f4963g) {
            f4958b = ((C0877o.m3505f(str) - this.f4961e) * 100) / (C0877o.m3481b(i2) - this.f4961e);
            if (this.f4960d != null) {
                ((C1379g) this.f4960d).m6309h(f4958b);
            }
        }
        if (i != i2) {
            if (!this.f4964h) {
                StringBuilder stringBuilder;
                if (this.f4963g) {
                    int i3 = i + 1;
                    if (i2 > i3) {
                        i2 = f4959c;
                        stringBuilder = new StringBuilder();
                        stringBuilder.append("handleResizeImage updated requestGbSize: ");
                        stringBuilder.append(i3);
                        Log.m3853d(i2, stringBuilder.toString());
                        i2 = i3;
                    }
                }
                String str2 = f4959c;
                stringBuilder = new StringBuilder();
                stringBuilder.append("requestResizeImage initialRequest: ");
                stringBuilder.append(z);
                stringBuilder.append(", mIncreasingType: ");
                stringBuilder.append(this.f4963g);
                stringBuilder.append(", origin: ");
                stringBuilder.append(C0877o.m3454a(this.f4961e));
                stringBuilder.append(", cur: ");
                stringBuilder.append(i);
                stringBuilder.append(", requestSize: ");
                stringBuilder.append(i2);
                Log.m3853d(str2, stringBuilder.toString());
                m6161a(str, i2, this.f4963g);
                return true;
            }
        }
        return false;
    }

    /* renamed from: a */
    protected void m7076a(final RelativeLayout relativeLayout, final ScrollView scrollView) {
        relativeLayout.getViewTreeObserver().addOnGlobalLayoutListener(new OnGlobalLayoutListener(this) {
            /* renamed from: a */
            int f2741a = null;
            /* renamed from: d */
            final /* synthetic */ C1458b f2744d;

            public void onGlobalLayout() {
                if (((float) (relativeLayout.getRootView().getHeight() - relativeLayout.getHeight())) > TypedValue.applyDimension(1, 200.0f, this.f2744d.getResources().getDisplayMetrics())) {
                    this.f2741a = scrollView.getScrollY();
                    return;
                }
                if (this.f2741a > -1) {
                    scrollView.smoothScrollTo(0, this.f2741a);
                }
                this.f2741a = -1;
            }
        });
    }

    /* renamed from: a */
    private void m7071a(int i) {
        String str = f4959c;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("initializeProgressDialog: ");
        stringBuilder.append(i);
        Log.m3853d(str, stringBuilder.toString());
        if (this.f4963g) {
            C0936a a = C1379g.m6304j().m6305a(new C13482(this)).m6307d(this.f4964h).m3757a((C1347a) this).m3753a((int) R.string.storage_expansion_title);
            StringBuilder stringBuilder2 = new StringBuilder();
            stringBuilder2.append(getString(R.string.storage_expansion_message));
            stringBuilder2.append(" ");
            stringBuilder2.append(i);
            stringBuilder2.append("GB");
            this.f4960d = a.m3758a(stringBuilder2.toString()).m3774g(f4958b).m3771e(0).m3766c((int) R.string.storage_expansion_button).m3759a(false).m3770e();
        } else {
            this.f4960d = C0936a.m3752a().m3757a((C1347a) this).m3762b((int) R.string.processing).m3771e(0).m3759a(false).m3770e();
        }
        if (f4958b == -1) {
            this.f4960d.dismiss();
            this.f4960d = 0;
            return;
        }
        getWindow().setSoftInputMode(2);
    }
}
