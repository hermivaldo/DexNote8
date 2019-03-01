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
import com.samsung.android.lxd.C1347a;
import com.samsung.android.lxd.R;
import com.samsung.android.lxd.processor.utils.log.Log;

/* compiled from: ErrorReportDialogFragment */
/* renamed from: com.samsung.android.lxd.fragment.e */
public class C1371e extends C0936a {
    /* renamed from: r */
    private static final String f4516r = "e";
    /* renamed from: s */
    private C0940a f4517s = null;

    /* compiled from: ErrorReportDialogFragment */
    /* renamed from: com.samsung.android.lxd.fragment.e$a */
    public interface C0940a {
        /* renamed from: a */
        void mo694a(boolean z);
    }

    /* renamed from: j */
    public static C1371e m6298j() {
        return new C1371e();
    }

    public Dialog onCreateDialog(Bundle bundle) {
        this.c = LayoutInflater.from((C1347a) getHost()).inflate(R.layout.fragment_error_report_dialog, null);
        return new Builder(getContext()).setView(this.c).create();
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        return this.c;
    }

    /* renamed from: a */
    protected void mo767a(DialogInterface dialogInterface) {
        m6297d(false);
        super.mo767a(dialogInterface);
    }

    /* renamed from: a */
    public C1371e m6299a(C0940a c0940a) {
        this.f4517s = c0940a;
        return this;
    }

    /* renamed from: f */
    protected C0936a mo764f() {
        if (getView() == null) {
            Log.m3855e(f4516r, "update : View had not been created yet!");
            return this;
        }
        super.mo764f();
        final CheckBox checkBox = (CheckBox) getView().findViewById(R.id.checkBox1);
        final CheckBox checkBox2 = (CheckBox) getView().findViewById(R.id.checkBox2);
        checkBox.setChecked(false);
        checkBox.setOnCheckedChangeListener(new OnCheckedChangeListener(this) {
            /* renamed from: b */
            final /* synthetic */ C1371e f2967b;

            public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                this.f2967b.f4517s.mo694a(z);
                if (z) {
                    checkBox2.setChecked(false);
                }
                this.f2967b.m6297d(checkBox2.isChecked() | z);
            }
        });
        checkBox2.setChecked(false);
        checkBox2.setOnCheckedChangeListener(new OnCheckedChangeListener(this) {
            /* renamed from: b */
            final /* synthetic */ C1371e f2969b;

            public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                if (z) {
                    checkBox.setChecked(false);
                }
                this.f2969b.m6297d(checkBox.isChecked() | z);
            }
        });
        return this;
    }

    /* renamed from: d */
    private void m6297d(boolean z) {
        ((AlertDialog) getDialog()).getButton(-1).setEnabled(z);
    }
}
