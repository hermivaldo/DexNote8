package com.samsung.android.lxd.fragment;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import com.samsung.android.lxd.C1347a;
import com.samsung.android.lxd.R;
import com.samsung.android.lxd.p064a.C0869j;
import com.samsung.android.lxd.processor.utils.log.Log;

/* compiled from: IncrementalDialogFragment */
/* renamed from: com.samsung.android.lxd.fragment.g */
public class C1379g extends C0936a {
    /* renamed from: r */
    protected TextView f4525r = null;
    /* renamed from: s */
    protected C0869j f4526s = null;
    /* renamed from: t */
    protected boolean f4527t = false;

    /* renamed from: j */
    public static C1379g m6304j() {
        return new C1379g();
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        this.c = LayoutInflater.from((C1347a) getHost()).inflate(R.layout.fragment_incremental_dialog, null);
        return this.c;
    }

    /* renamed from: f */
    protected C0936a mo764f() {
        if (getView() == null) {
            Log.m3855e(a, "update : View had not been created yet!");
            return this;
        }
        super.mo764f();
        if (this.o != -1) {
            this.f4525r = m6310k();
            TextView textView = this.f4525r;
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(this.o);
            stringBuilder.append("%");
            textView.setText(stringBuilder.toString());
        }
        return this;
    }

    /* renamed from: a */
    protected void mo767a(DialogInterface dialogInterface) {
        super.mo767a(dialogInterface);
        ((AlertDialog) dialogInterface).getButton(-1).setOnClickListener(this.f4526s);
        if (this.f4527t != null) {
            m6311l();
        }
    }

    /* renamed from: k */
    protected TextView m6310k() {
        return getView() != null ? (TextView) getView().findViewById(R.id.percent) : null;
    }

    /* renamed from: a */
    public C1379g m6305a(C0869j c0869j) {
        this.f4526s = c0869j;
        return this;
    }

    /* renamed from: d */
    public C1379g m6307d(boolean z) {
        this.f4527t = z;
        return this;
    }

    /* renamed from: h */
    public void m6309h(int i) {
        this.o = i;
        i = this.f4525r;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(this.o);
        stringBuilder.append("%");
        i.setText(stringBuilder.toString());
        m3765c().setProgress(this.o);
    }

    /* renamed from: l */
    public void m6311l() {
        Log.m3853d(a, "setDisableButton: ");
        Button button = ((AlertDialog) getDialog()).getButton(-1);
        button.setEnabled(false);
        button.setClickable(false);
        button.setTextColor(this.b.getResources().getColor(R.color.LightTheme.primary_dim, null));
    }
}
