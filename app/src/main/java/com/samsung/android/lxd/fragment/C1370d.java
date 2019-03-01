package com.samsung.android.lxd.fragment;

import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.app.Dialog;
import android.app.FragmentTransaction;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.samsung.android.lxd.C1347a;
import com.samsung.android.lxd.R;
import com.samsung.android.lxd.p064a.C0877o;
import com.samsung.android.lxd.processor.network.channel.pty.internal.KeycodeConstants;
import com.samsung.android.lxd.processor.utils.log.Log;

/* compiled from: EditTextDialogFragment */
/* renamed from: com.samsung.android.lxd.fragment.d */
public class C1370d extends C0936a {
    /* renamed from: r */
    private EditTextFragment f4510r;
    /* renamed from: s */
    private String f4511s = null;
    /* renamed from: t */
    private String f4512t = null;
    /* renamed from: u */
    private int f4513u = -1;
    /* renamed from: v */
    private int f4514v = -1;
    /* renamed from: w */
    private int f4515w = -1;

    /* renamed from: j */
    public static C1370d m6287j() {
        return new C1370d();
    }

    public Dialog onCreateDialog(Bundle bundle) {
        this.c = LayoutInflater.from((C1347a) getHost()).inflate(R.layout.fragment_edit_text_dialog, null);
        return new Builder(getContext()).setView(this.c).create();
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        return this.c;
    }

    /* renamed from: a */
    protected void mo767a(DialogInterface dialogInterface) {
        super.mo767a(dialogInterface);
        AlertDialog alertDialog = (AlertDialog) dialogInterface;
        alertDialog.getWindow().setSoftInputMode(5);
        this.f4510r.m3654e().setFocusable(true);
        this.f4510r.m3654e().requestFocus();
        this.f4510r.m3640a(alertDialog.getButton(-1)).m3656f();
    }

    /* renamed from: f */
    protected C0936a mo764f() {
        if (getView() == null) {
            Log.m3855e(a, "update : View had not been created yet!");
            return this;
        }
        String str;
        super.mo764f();
        EditTextFragment a = EditTextFragment.m3631a();
        if (this.f4511s != null) {
            str = this.f4511s;
        } else {
            str = getString(R.string.size_empty);
        }
        a = a.m3650c(str);
        int i = this.f4515w;
        int i2 = KeycodeConstants.KEYCODE_F10;
        a = a.m3653d(C0877o.m3491c(i != -1 ? this.f4515w : KeycodeConstants.KEYCODE_F10));
        if (this.f4512t != null) {
            str = this.f4512t;
        } else {
            str = getString(R.string.invalid_input);
        }
        a = a.m3655e(str).m3639a(2).m3652d(this.f4513u).m3649c(this.f4514v);
        if (this.f4515w != -1) {
            i2 = this.f4515w;
        }
        this.f4510r = a.m3644b(i2).m3656f();
        FragmentTransaction beginTransaction = getChildFragmentManager().beginTransaction();
        beginTransaction.replace(R.id.imageSizeEditPopUpText, this.f4510r);
        beginTransaction.commit();
        return this;
    }

    /* renamed from: k */
    public String m6294k() {
        return this.f4510r.m3647b();
    }

    /* renamed from: a */
    public C1370d m6289a(String str) {
        this.f4511s = str;
        return this;
    }

    /* renamed from: b */
    public C1370d m6291b(String str) {
        this.f4512t = str;
        return this;
    }

    /* renamed from: a */
    public C1370d m6288a(int i, int i2) {
        this.f4513u = i;
        this.f4514v = i2;
        return this;
    }

    /* renamed from: h */
    public C1370d m6293h(int i) {
        this.f4515w = i;
        return this;
    }
}
