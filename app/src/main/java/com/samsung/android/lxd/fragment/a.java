package com.samsung.android.lxd.fragment;

import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.content.DialogInterface.OnCancelListener;
import android.content.DialogInterface.OnClickListener;
import android.content.DialogInterface.OnDismissListener;
import android.content.DialogInterface.OnShowListener;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnSystemUiVisibilityChangeListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import com.samsung.android.lxd.R;
import com.samsung.android.lxd.a.o;
import com.samsung.android.lxd.processor.utils.log.Log;

/* compiled from: BaseDialogFragment */
public class a extends DialogFragment {
    protected static final String a = "a";
    protected com.samsung.android.lxd.a b = null;
    protected View c = null;
    protected int d = -1;
    protected int e = -1;
    protected CharSequence f = null;
    protected int g = -1;
    protected int h = -1;
    protected OnClickListener i = null;
    protected OnClickListener j = null;
    protected OnCancelListener k = null;
    protected OnDismissListener l = null;
    protected int m = -1;
    protected int n = -1;
    protected int o = -1;
    protected boolean p = true;
    protected boolean q = false;
    private boolean r = false;
    private boolean s = false;

    public static a a() {
        return new a();
    }

    public Dialog onCreateDialog(Bundle bundle) {
        return new Builder(getContext()).create();
    }

    public void onStart() {
        super.onStart();
        this.b.a(((AlertDialog) getDialog()).getWindow());
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        this.c = LayoutInflater.from((com.samsung.android.lxd.a) getHost()).inflate(R.layout.fragment_base_dialog, null);
        return this.c;
    }

    public void onActivityCreated(Bundle bundle) {
        super.onActivityCreated(bundle);
        String str = a;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("onActivityCreated ShowRequest: ");
        stringBuilder.append(this.r);
        Log.d(str, stringBuilder.toString());
        if (this.r) {
            f();
        } else {
            super.dismiss();
        }
    }

    public void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
    }

    protected void a(DialogInterface dialogInterface) {
        AlertDialog alertDialog = (AlertDialog) dialogInterface;
        alertDialog.getWindow().clearFlags(8);
        Button button = alertDialog.getButton(-1);
        if (this.q) {
            button.setEnabled(false);
        }
        button.setTextColor(this.b.getResources().getColorStateList(R.color.button_text, null));
        alertDialog.getButton(-2).setTextColor(this.b.getResources().getColorStateList(R.color.button_text, null));
    }

    protected TextView b() {
        return getView() != null ? (TextView) getView().findViewById(R.id.message) : null;
    }

    protected ProgressBar c() {
        return getView() != null ? (ProgressBar) getView().findViewById(R.id.progressBar) : null;
    }

    protected ImageView d() {
        return getView() != null ? (ImageView) getView().findViewById(R.id.image) : null;
    }

    public a a(com.samsung.android.lxd.a aVar) {
        this.b = aVar;
        return this;
    }

    public a a(int i) {
        this.d = i;
        return this;
    }

    public a b(int i) {
        this.e = i;
        return this;
    }

    public a a(CharSequence charSequence) {
        this.f = charSequence;
        return this;
    }

    public a c(int i) {
        this.g = i;
        return this;
    }

    public a d(int i) {
        this.h = i;
        return this;
    }

    public a a(OnClickListener onClickListener) {
        this.i = onClickListener;
        return this;
    }

    public a b(OnClickListener onClickListener) {
        this.j = onClickListener;
        return this;
    }

    public a a(OnCancelListener onCancelListener) {
        this.k = onCancelListener;
        return this;
    }

    public a a(OnDismissListener onDismissListener) {
        this.l = onDismissListener;
        return this;
    }

    public a e(int i) {
        this.m = i;
        return this;
    }

    public a f(int i) {
        this.n = i;
        return this;
    }

    public a g(int i) {
        this.o = i;
        return this;
    }

    public a a(boolean z) {
        this.p = z;
        return this;
    }

    public a b(boolean z) {
        this.s = z;
        return this;
    }

    public a c(boolean z) {
        this.q = z;
        return this;
    }

    public a e() {
        if (this.b != null && this.b.b()) {
            this.r = true;
            show(this.b.getFragmentManager(), this.b.getClass().getSimpleName());
        }
        return this;
    }

    public void dismiss() {
        if (this.r) {
            this.r = false;
            super.dismiss();
        }
    }

    public void dismissAllowingStateLoss() {
        if (this.r) {
            this.r = false;
            super.dismissAllowingStateLoss();
        }
    }

    protected a f() {
        if (getView() == null) {
            Log.e(a, "update : View had not been created yet!");
            return this;
        }
        final AlertDialog alertDialog = (AlertDialog) getDialog();
        alertDialog.getWindow().setFlags(8, 8);
        this.b.a(alertDialog.getWindow());
        alertDialog.setCancelable(this.p);
        alertDialog.setCanceledOnTouchOutside(this.p);
        View findViewById = alertDialog.getWindow().getDecorView().findViewById(16908290);
        findViewById.setFocusable(true);
        findViewById.setFocusableInTouchMode(true);
        findViewById.requestFocus();
        ProgressBar c = c();
        if (this.g != -1) {
            alertDialog.setButton(-1, getString(this.g), this.i == null ? new OnClickListener() {
                public void onClick(DialogInterface dialogInterface, int i) {
                    dialogInterface.dismiss();
                }
            } : this.i);
        }
        if (this.h != -1) {
            alertDialog.setButton(-2, getString(this.h), this.j == null ? new OnClickListener() {
                public void onClick(DialogInterface dialogInterface, int i) {
                    dialogInterface.dismiss();
                }
            } : this.j);
        }
        if (this.d != -1) {
            alertDialog.setTitle(this.d);
        }
        TextView b;
        if (this.m != -1) {
            alertDialog.setView(this.c);
            c.setVisibility(this.m);
            if (this.m == 0) {
                TextView b2 = b();
                if (g()) {
                    b2.setText(h());
                }
                if (this.o == -1) {
                    c.measure(0, 0);
                    b2.setHeight(c.getMeasuredWidth());
                }
            }
        } else if (this.n != -1) {
            alertDialog.setView(this.c);
            d().setVisibility(this.n);
            b = b();
            if (g()) {
                b.setText(h());
                b.setGravity(1);
            }
        } else if (this.s) {
            alertDialog.setView(this.c);
            b = b();
            if (g()) {
                b.setText(h());
                o.a(b, h(), 1);
            }
        } else if (g()) {
            alertDialog.setMessage(h());
        }
        alertDialog.setOnShowListener(new OnShowListener() {
            public void onShow(DialogInterface dialogInterface) {
                a.this.a(dialogInterface);
            }
        });
        alertDialog.getWindow().getDecorView().setOnSystemUiVisibilityChangeListener(new OnSystemUiVisibilityChangeListener() {
            public void onSystemUiVisibilityChange(int i) {
                String str = a.a;
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append("onSystemUiVisibilityChange: ");
                stringBuilder.append(i);
                Log.d(str, stringBuilder.toString());
                a.this.b.a(alertDialog.getWindow());
            }
        });
        return this;
    }

    protected boolean g() {
        return (this.e == -1 && TextUtils.isEmpty(this.f)) ? false : true;
    }

    protected CharSequence h() {
        if (this.e != -1) {
            return getString(this.e);
        }
        return !TextUtils.isEmpty(this.f) ? this.f : null;
    }

    public void onDismiss(DialogInterface dialogInterface) {
        if (this.l != null) {
            this.l.onDismiss(dialogInterface);
        }
        super.onDismiss(dialogInterface);
    }

    public void onCancel(DialogInterface dialogInterface) {
        if (this.k != null) {
            this.k.onCancel(dialogInterface);
        }
        super.onCancel(dialogInterface);
    }

    public boolean i() {
        return this.r;
    }
}
