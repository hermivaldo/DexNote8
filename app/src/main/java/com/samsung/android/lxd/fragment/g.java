package com.samsung.android.lxd.fragment;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import com.samsung.android.lxd.R;
import com.samsung.android.lxd.a;
import com.samsung.android.lxd.a.j;
import com.samsung.android.lxd.processor.utils.log.Log;

/* compiled from: IncrementalDialogFragment */
public class g extends a {
    protected TextView r = null;
    protected j s = null;
    protected boolean t = false;

    public static g j() {
        return new g();
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        this.c = LayoutInflater.from((a) getHost()).inflate(R.layout.fragment_incremental_dialog, null);
        return this.c;
    }

    protected a f() {
        if (getView() == null) {
            Log.e(a, "update : View had not been created yet!");
            return this;
        }
        super.f();
        if (this.o != -1) {
            this.r = k();
            TextView textView = this.r;
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(this.o);
            stringBuilder.append("%");
            textView.setText(stringBuilder.toString());
        }
        return this;
    }

    protected void a(DialogInterface dialogInterface) {
        super.a(dialogInterface);
        ((AlertDialog) dialogInterface).getButton(-1).setOnClickListener(this.s);
        if (this.t) {
            l();
        }
    }

    protected TextView k() {
        return getView() != null ? (TextView) getView().findViewById(R.id.percent) : null;
    }

    public g a(j jVar) {
        this.s = jVar;
        return this;
    }

    public g d(boolean z) {
        this.t = z;
        return this;
    }

    public void h(int i) {
        this.o = i;
        TextView textView = this.r;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(this.o);
        stringBuilder.append("%");
        textView.setText(stringBuilder.toString());
        c().setProgress(this.o);
    }

    public void l() {
        Log.d(a, "setDisableButton: ");
        Button button = ((AlertDialog) getDialog()).getButton(-1);
        button.setEnabled(false);
        button.setClickable(false);
        button.setTextColor(this.b.getResources().getColor(R.color.LightTheme.primary_dim, null));
    }
}
