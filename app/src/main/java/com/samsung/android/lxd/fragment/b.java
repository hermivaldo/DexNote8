package com.samsung.android.lxd.fragment;

import android.app.AlertDialog.Builder;
import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import com.samsung.android.lxd.R;
import com.samsung.android.lxd.a;
import com.samsung.android.lxd.processor.utils.log.Log;

/* compiled from: CheckboxDialogFragment */
public class b extends a {
    private static final String r = "b";
    private int s = -1;
    private boolean t = true;

    public static b j() {
        return new b();
    }

    public boolean k() {
        return this.t;
    }

    public Dialog onCreateDialog(Bundle bundle) {
        this.c = LayoutInflater.from((a) getHost()).inflate(R.layout.fragment_checkbox_dialog, null);
        return new Builder(getContext()).setView(this.c).create();
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        return this.c;
    }

    public b h(int i) {
        this.s = i;
        return this;
    }

    protected a f() {
        if (getView() == null) {
            Log.e(r, "update : View had not been created yet!");
            return this;
        }
        super.f();
        CheckBox checkBox = (CheckBox) getView().findViewById(R.id.checkBox);
        checkBox.setChecked(true);
        checkBox.setText(getString(this.s));
        checkBox.setOnCheckedChangeListener(new OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                b.this.t = z;
            }
        });
        return this;
    }
}
