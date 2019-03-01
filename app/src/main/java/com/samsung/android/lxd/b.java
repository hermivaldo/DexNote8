package com.samsung.android.lxd;

import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewTreeObserver.OnGlobalLayoutListener;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import com.samsung.android.lxd.a.j;
import com.samsung.android.lxd.a.o;
import com.samsung.android.lxd.fragment.a;
import com.samsung.android.lxd.fragment.g;
import com.samsung.android.lxd.processor.utils.log.Log;

/* compiled from: CreateEditActivity */
public class b extends a {
    protected static int b = -1;
    private static final String c = "b";
    private a d;
    private int e;
    private int f;
    private boolean g;
    private boolean h;

    public void onCreate(Bundle bundle) {
        if (!o.d((Context) this)) {
            e();
        }
        super.onCreate(bundle);
        String str = c;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("onCreate: progress: ");
        stringBuilder.append(b);
        Log.d(str, stringBuilder.toString());
        if (b != -1) {
            a(bundle);
        } else {
            Q();
        }
    }

    protected void onSaveInstanceState(Bundle bundle) {
        String str = c;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("onSaveInstanceState: mIncreasingType: ");
        stringBuilder.append(this.g);
        Log.d(str, stringBuilder.toString());
        bundle.putInt("ORIGIN_FILE_SIZE", this.e);
        bundle.putInt("REQUEST_FILE_SIZE", this.f);
        bundle.putBoolean("INCREASE_TYPE", this.g);
        bundle.putBoolean("CANCEL_REQUESTED", this.h);
        str = c;
        stringBuilder = new StringBuilder();
        stringBuilder.append("onSaveInstanceState: ");
        stringBuilder.append(bundle.toString());
        Log.d(str, stringBuilder.toString());
        super.onSaveInstanceState(bundle);
    }

    public boolean f(String str, boolean z) {
        super.f(str, z);
        N();
        return true;
    }

    private void a(Bundle bundle) {
        if (bundle != null) {
            this.e = bundle.getInt("ORIGIN_FILE_SIZE");
            this.f = bundle.getInt("REQUEST_FILE_SIZE");
            this.g = bundle.getBoolean("INCREASE_TYPE");
            this.h = bundle.getBoolean("CANCEL_REQUESTED");
            String str = c;
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("OriginMbFileSize: ");
            stringBuilder.append(this.e);
            stringBuilder.append("RequestGbFileSize: ");
            stringBuilder.append(this.f);
            stringBuilder.append("IncreasingType: ");
            stringBuilder.append(this.g);
            stringBuilder.append("CancelRequested: ");
            stringBuilder.append(this.h);
            Log.d(str, stringBuilder.toString());
        }
        a(this.f);
    }

    protected void N() {
        b = -1;
        Q();
    }

    protected boolean O() {
        return b != -1;
    }

    private void Q() {
        if (this.d != null) {
            this.d.dismissAllowingStateLoss();
        }
        this.d = null;
        this.f = -1;
        this.e = -1;
        this.g = false;
        this.h = false;
    }

    protected boolean a(boolean z, String str, int i, int i2) {
        if (z) {
            if (i == i2) {
                return false;
            }
            this.f = i2;
            this.e = o.f(str);
            if (!TextUtils.isEmpty(o.d()) && str.contains(o.d()) && i2 > i) {
                this.g = true;
            }
            b = 0;
            a(i2);
        } else if (this.g) {
            b = ((o.f(str) - this.e) * 100) / (o.b(i2) - this.e);
            if (this.d != null) {
                ((g) this.d).h(b);
            }
        }
        if (i == i2 || this.h) {
            return false;
        }
        StringBuilder stringBuilder;
        if (this.g) {
            int i3 = i + 1;
            if (i2 > i3) {
                String str2 = c;
                stringBuilder = new StringBuilder();
                stringBuilder.append("handleResizeImage updated requestGbSize: ");
                stringBuilder.append(i3);
                Log.d(str2, stringBuilder.toString());
                i2 = i3;
            }
        }
        String str3 = c;
        stringBuilder = new StringBuilder();
        stringBuilder.append("requestResizeImage initialRequest: ");
        stringBuilder.append(z);
        stringBuilder.append(", mIncreasingType: ");
        stringBuilder.append(this.g);
        stringBuilder.append(", origin: ");
        stringBuilder.append(o.a(this.e));
        stringBuilder.append(", cur: ");
        stringBuilder.append(i);
        stringBuilder.append(", requestSize: ");
        stringBuilder.append(i2);
        Log.d(str3, stringBuilder.toString());
        a(str, i2, this.g);
        return true;
    }

    protected void a(final RelativeLayout relativeLayout, final ScrollView scrollView) {
        relativeLayout.getViewTreeObserver().addOnGlobalLayoutListener(new OnGlobalLayoutListener() {
            int a = 0;

            public void onGlobalLayout() {
                if (((float) (relativeLayout.getRootView().getHeight() - relativeLayout.getHeight())) > TypedValue.applyDimension(1, 200.0f, b.this.getResources().getDisplayMetrics())) {
                    this.a = scrollView.getScrollY();
                    return;
                }
                if (this.a > -1) {
                    scrollView.smoothScrollTo(0, this.a);
                }
                this.a = -1;
            }
        });
    }

    private void a(int i) {
        String str = c;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("initializeProgressDialog: ");
        stringBuilder.append(i);
        Log.d(str, stringBuilder.toString());
        if (this.g) {
            a a = g.j().a(new j() {
                public void onClick(View view) {
                    super.onClick(view);
                    Log.d(b.c, "cancel requested");
                    ((g) b.this.d).l();
                    b.this.h = true;
                }
            }).d(this.h).a((a) this).a((int) R.string.storage_expansion_title);
            StringBuilder stringBuilder2 = new StringBuilder();
            stringBuilder2.append(getString(R.string.storage_expansion_message));
            stringBuilder2.append(" ");
            stringBuilder2.append(i);
            stringBuilder2.append("GB");
            this.d = a.a(stringBuilder2.toString()).g(b).e(0).c((int) R.string.storage_expansion_button).a(false).e();
        } else {
            this.d = a.a().a((a) this).b((int) R.string.processing).e(0).a(false).e();
        }
        if (b == -1) {
            this.d.dismiss();
            this.d = null;
            return;
        }
        getWindow().setSoftInputMode(2);
    }
}
