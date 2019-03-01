package com.samsung.android.lxd.fragment;

import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import com.samsung.android.lxd.R;
import com.samsung.android.lxd.processor.utils.log.Log;

/* compiled from: ErrorReportDialogFragment */
public class e extends a {
    private static final String r = "e";
    private a s = null;

    /* compiled from: ErrorReportDialogFragment */
    public interface a {
        void a(boolean z);
    }

    public static e j() {
        return new e();
    }

    public Dialog onCreateDialog(Bundle bundle) {
        this.c = LayoutInflater.from((com.samsung.android.lxd.a) getHost()).inflate(R.layout.fragment_error_report_dialog, null);
        return new Builder(getContext()).setView(this.c).create();
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        return this.c;
    }

    protected void a(DialogInterface dialogInterface) {
        d(false);
        super.a(dialogInterface);
    }

    public e a(a aVar) {
        this.s = aVar;
        return this;
    }

    protected a f() {
        if (getView() == null) {
            Log.e(r, "update : View had not been created yet!");
            return this;
        }
        super.f();
        final CheckBox checkBox = (CheckBox) getView().findViewById(R.id.checkBox1);
        final CheckBox checkBox2 = (CheckBox) getView().findViewById(R.id.checkBox2);
        checkBox.setChecked(false);
        checkBox.setOnCheckedChangeListener(new OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                e.this.s.a(z);
                if (z) {
                    checkBox2.setChecked(false);
                }
                e.this.d(checkBox2.isChecked() | z);
            }
        });
        checkBox2.setChecked(false);
        checkBox2.setOnCheckedChangeListener(new OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                if (z) {
                    checkBox.setChecked(false);
                }
                e.this.d(checkBox.isChecked() | z);
            }
        });
        return this;
    }

    private void d(boolean z) {
        ((AlertDialog) getDialog()).getButton(-1).setEnabled(z);
    }
}
