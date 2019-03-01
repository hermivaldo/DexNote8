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
import com.samsung.android.lxd.C1347a;
import com.samsung.android.lxd.R;
import com.samsung.android.lxd.p064a.C0877o;
import com.samsung.android.lxd.processor.utils.log.Log;

/* compiled from: BaseDialogFragment */
/* renamed from: com.samsung.android.lxd.fragment.a */
public class C0936a extends DialogFragment {
    /* renamed from: a */
    protected static final String f2946a = "a";
    /* renamed from: b */
    protected C1347a f2947b = null;
    /* renamed from: c */
    protected View f2948c = null;
    /* renamed from: d */
    protected int f2949d = -1;
    /* renamed from: e */
    protected int f2950e = -1;
    /* renamed from: f */
    protected CharSequence f2951f = null;
    /* renamed from: g */
    protected int f2952g = -1;
    /* renamed from: h */
    protected int f2953h = -1;
    /* renamed from: i */
    protected OnClickListener f2954i = null;
    /* renamed from: j */
    protected OnClickListener f2955j = null;
    /* renamed from: k */
    protected OnCancelListener f2956k = null;
    /* renamed from: l */
    protected OnDismissListener f2957l = null;
    /* renamed from: m */
    protected int f2958m = -1;
    /* renamed from: n */
    protected int f2959n = -1;
    /* renamed from: o */
    protected int f2960o = -1;
    /* renamed from: p */
    protected boolean f2961p = true;
    /* renamed from: q */
    protected boolean f2962q = false;
    /* renamed from: r */
    private boolean f2963r = false;
    /* renamed from: s */
    private boolean f2964s = false;

    /* compiled from: BaseDialogFragment */
    /* renamed from: com.samsung.android.lxd.fragment.a$1 */
    class C09211 implements OnClickListener {
        /* renamed from: a */
        final /* synthetic */ C0936a f2915a;

        C09211(C0936a c0936a) {
            this.f2915a = c0936a;
        }

        public void onClick(DialogInterface dialogInterface, int i) {
            dialogInterface.dismiss();
        }
    }

    /* compiled from: BaseDialogFragment */
    /* renamed from: com.samsung.android.lxd.fragment.a$2 */
    class C09222 implements OnClickListener {
        /* renamed from: a */
        final /* synthetic */ C0936a f2916a;

        C09222(C0936a c0936a) {
            this.f2916a = c0936a;
        }

        public void onClick(DialogInterface dialogInterface, int i) {
            dialogInterface.dismiss();
        }
    }

    /* compiled from: BaseDialogFragment */
    /* renamed from: com.samsung.android.lxd.fragment.a$3 */
    class C09233 implements OnShowListener {
        /* renamed from: a */
        final /* synthetic */ C0936a f2917a;

        C09233(C0936a c0936a) {
            this.f2917a = c0936a;
        }

        public void onShow(DialogInterface dialogInterface) {
            this.f2917a.mo767a(dialogInterface);
        }
    }

    /* renamed from: a */
    public static C0936a m3752a() {
        return new C0936a();
    }

    public Dialog onCreateDialog(Bundle bundle) {
        return new Builder(getContext()).create();
    }

    public void onStart() {
        super.onStart();
        this.f2947b.m6155a(((AlertDialog) getDialog()).getWindow());
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        this.f2948c = LayoutInflater.from((C1347a) getHost()).inflate(R.layout.fragment_base_dialog, null);
        return this.f2948c;
    }

    public void onActivityCreated(Bundle bundle) {
        super.onActivityCreated(bundle);
        bundle = f2946a;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("onActivityCreated ShowRequest: ");
        stringBuilder.append(this.f2963r);
        Log.m3853d(bundle, stringBuilder.toString());
        if (this.f2963r == null) {
            super.dismiss();
        } else {
            mo764f();
        }
    }

    public void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
    }

    /* renamed from: a */
    protected void mo767a(DialogInterface dialogInterface) {
        AlertDialog alertDialog = (AlertDialog) dialogInterface;
        alertDialog.getWindow().clearFlags(8);
        Button button = alertDialog.getButton(-1);
        if (this.f2962q) {
            button.setEnabled(false);
        }
        button.setTextColor(this.f2947b.getResources().getColorStateList(R.color.button_text, null));
        alertDialog.getButton(-2).setTextColor(this.f2947b.getResources().getColorStateList(R.color.button_text, null));
    }

    /* renamed from: b */
    protected TextView m3761b() {
        return getView() != null ? (TextView) getView().findViewById(R.id.message) : null;
    }

    /* renamed from: c */
    protected ProgressBar m3765c() {
        return getView() != null ? (ProgressBar) getView().findViewById(R.id.progressBar) : null;
    }

    /* renamed from: d */
    protected ImageView m3768d() {
        return getView() != null ? (ImageView) getView().findViewById(R.id.image) : null;
    }

    /* renamed from: a */
    public C0936a m3757a(C1347a c1347a) {
        this.f2947b = c1347a;
        return this;
    }

    /* renamed from: a */
    public C0936a m3753a(int i) {
        this.f2949d = i;
        return this;
    }

    /* renamed from: b */
    public C0936a m3762b(int i) {
        this.f2950e = i;
        return this;
    }

    /* renamed from: a */
    public C0936a m3758a(CharSequence charSequence) {
        this.f2951f = charSequence;
        return this;
    }

    /* renamed from: c */
    public C0936a m3766c(int i) {
        this.f2952g = i;
        return this;
    }

    /* renamed from: d */
    public C0936a m3769d(int i) {
        this.f2953h = i;
        return this;
    }

    /* renamed from: a */
    public C0936a m3755a(OnClickListener onClickListener) {
        this.f2954i = onClickListener;
        return this;
    }

    /* renamed from: b */
    public C0936a m3763b(OnClickListener onClickListener) {
        this.f2955j = onClickListener;
        return this;
    }

    /* renamed from: a */
    public C0936a m3754a(OnCancelListener onCancelListener) {
        this.f2956k = onCancelListener;
        return this;
    }

    /* renamed from: a */
    public C0936a m3756a(OnDismissListener onDismissListener) {
        this.f2957l = onDismissListener;
        return this;
    }

    /* renamed from: e */
    public C0936a m3771e(int i) {
        this.f2958m = i;
        return this;
    }

    /* renamed from: f */
    public C0936a m3773f(int i) {
        this.f2959n = i;
        return this;
    }

    /* renamed from: g */
    public C0936a m3774g(int i) {
        this.f2960o = i;
        return this;
    }

    /* renamed from: a */
    public C0936a m3759a(boolean z) {
        this.f2961p = z;
        return this;
    }

    /* renamed from: b */
    public C0936a m3764b(boolean z) {
        this.f2964s = z;
        return this;
    }

    /* renamed from: c */
    public C0936a m3767c(boolean z) {
        this.f2962q = z;
        return this;
    }

    /* renamed from: e */
    public C0936a m3770e() {
        if (this.f2947b != null && this.f2947b.m6173b()) {
            this.f2963r = true;
            show(this.f2947b.getFragmentManager(), this.f2947b.getClass().getSimpleName());
        }
        return this;
    }

    public void dismiss() {
        if (this.f2963r) {
            this.f2963r = false;
            super.dismiss();
        }
    }

    public void dismissAllowingStateLoss() {
        if (this.f2963r) {
            this.f2963r = false;
            super.dismissAllowingStateLoss();
        }
    }

    /* renamed from: f */
    protected C0936a mo764f() {
        if (getView() == null) {
            Log.m3855e(f2946a, "update : View had not been created yet!");
            return this;
        }
        final AlertDialog alertDialog = (AlertDialog) getDialog();
        alertDialog.getWindow().setFlags(8, 8);
        this.f2947b.m6155a(alertDialog.getWindow());
        alertDialog.setCancelable(this.f2961p);
        alertDialog.setCanceledOnTouchOutside(this.f2961p);
        View findViewById = alertDialog.getWindow().getDecorView().findViewById(16908290);
        findViewById.setFocusable(true);
        findViewById.setFocusableInTouchMode(true);
        findViewById.requestFocus();
        ProgressBar c = m3765c();
        if (this.f2952g != -1) {
            alertDialog.setButton(-1, getString(this.f2952g), this.f2954i == null ? new C09211(this) : this.f2954i);
        }
        if (this.f2953h != -1) {
            alertDialog.setButton(-2, getString(this.f2953h), this.f2955j == null ? new C09222(this) : this.f2955j);
        }
        if (this.f2949d != -1) {
            alertDialog.setTitle(this.f2949d);
        }
        if (this.f2958m != -1) {
            alertDialog.setView(this.f2948c);
            c.setVisibility(this.f2958m);
            if (this.f2958m == 0) {
                TextView b = m3761b();
                if (m3775g()) {
                    b.setText(m3776h());
                }
                if (this.f2960o == -1) {
                    c.measure(0, 0);
                    b.setHeight(c.getMeasuredWidth());
                }
            }
        } else if (this.f2959n != -1) {
            alertDialog.setView(this.f2948c);
            m3768d().setVisibility(this.f2959n);
            r1 = m3761b();
            if (m3775g()) {
                r1.setText(m3776h());
                r1.setGravity(1);
            }
        } else if (this.f2964s) {
            alertDialog.setView(this.f2948c);
            r1 = m3761b();
            if (m3775g()) {
                r1.setText(m3776h());
                C0877o.m3468a(r1, m3776h(), 1);
            }
        } else if (m3775g()) {
            alertDialog.setMessage(m3776h());
        }
        alertDialog.setOnShowListener(new C09233(this));
        alertDialog.getWindow().getDecorView().setOnSystemUiVisibilityChangeListener(new OnSystemUiVisibilityChangeListener(this) {
            /* renamed from: b */
            final /* synthetic */ C0936a f2919b;

            public void onSystemUiVisibilityChange(int i) {
                String str = C0936a.f2946a;
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append("onSystemUiVisibilityChange: ");
                stringBuilder.append(i);
                Log.m3853d(str, stringBuilder.toString());
                this.f2919b.f2947b.m6155a(alertDialog.getWindow());
            }
        });
        return this;
    }

    /* renamed from: g */
    protected boolean m3775g() {
        if (this.f2950e == -1) {
            if (TextUtils.isEmpty(this.f2951f)) {
                return false;
            }
        }
        return true;
    }

    /* renamed from: h */
    protected CharSequence m3776h() {
        if (this.f2950e != -1) {
            return getString(this.f2950e);
        }
        return !TextUtils.isEmpty(this.f2951f) ? this.f2951f : null;
    }

    public void onDismiss(DialogInterface dialogInterface) {
        if (this.f2957l != null) {
            this.f2957l.onDismiss(dialogInterface);
        }
        super.onDismiss(dialogInterface);
    }

    public void onCancel(DialogInterface dialogInterface) {
        if (this.f2956k != null) {
            this.f2956k.onCancel(dialogInterface);
        }
        super.onCancel(dialogInterface);
    }

    /* renamed from: i */
    public boolean m3777i() {
        return this.f2963r;
    }
}
