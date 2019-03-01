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
import com.samsung.android.lxd.R;
import com.samsung.android.lxd.a;
import com.samsung.android.lxd.a.o;
import com.samsung.android.lxd.processor.network.channel.pty.internal.KeycodeConstants;
import com.samsung.android.lxd.processor.utils.log.Log;

/* compiled from: EditTextDialogFragment */
public class d extends a {
    private EditTextFragment r;
    private String s = null;
    private String t = null;
    private int u = -1;
    private int v = -1;
    private int w = -1;

    public static d j() {
        return new d();
    }

    public Dialog onCreateDialog(Bundle bundle) {
        this.c = LayoutInflater.from((a) getHost()).inflate(R.layout.fragment_edit_text_dialog, null);
        return new Builder(getContext()).setView(this.c).create();
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        return this.c;
    }

    protected void a(DialogInterface dialogInterface) {
        super.a(dialogInterface);
        AlertDialog alertDialog = (AlertDialog) dialogInterface;
        alertDialog.getWindow().setSoftInputMode(5);
        this.r.e().setFocusable(true);
        this.r.e().requestFocus();
        this.r.a(alertDialog.getButton(-1)).f();
    }

    protected a f() {
        if (getView() == null) {
            Log.e(a, "update : View had not been created yet!");
            return this;
        }
        String str;
        super.f();
        EditTextFragment a = EditTextFragment.a();
        if (this.s != null) {
            str = this.s;
        } else {
            str = getString(R.string.size_empty);
        }
        a = a.c(str);
        int i = this.w;
        int i2 = KeycodeConstants.KEYCODE_F10;
        a = a.d(o.c(i != -1 ? this.w : KeycodeConstants.KEYCODE_F10));
        if (this.t != null) {
            str = this.t;
        } else {
            str = getString(R.string.invalid_input);
        }
        a = a.e(str).a(2).d(this.u).c(this.v);
        if (this.w != -1) {
            i2 = this.w;
        }
        this.r = a.b(i2).f();
        FragmentTransaction beginTransaction = getChildFragmentManager().beginTransaction();
        beginTransaction.replace(R.id.imageSizeEditPopUpText, this.r);
        beginTransaction.commit();
        return this;
    }

    public String k() {
        return this.r.b();
    }

    public d a(String str) {
        this.s = str;
        return this;
    }

    public d b(String str) {
        this.t = str;
        return this;
    }

    public d a(int i, int i2) {
        this.u = i;
        this.v = i2;
        return this;
    }

    public d h(int i) {
        this.w = i;
        return this;
    }
}
