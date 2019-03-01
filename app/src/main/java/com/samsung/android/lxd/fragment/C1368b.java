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
import com.samsung.android.lxd.C1347a;
import com.samsung.android.lxd.R;
import com.samsung.android.lxd.processor.utils.log.Log;

/* compiled from: CheckboxDialogFragment */
/* renamed from: com.samsung.android.lxd.fragment.b */
public class C1368b extends C0936a {
    /* renamed from: r */
    private static final String f4505r = "b";
    /* renamed from: s */
    private int f4506s = -1;
    /* renamed from: t */
    private boolean f4507t = true;

    /* compiled from: CheckboxDialogFragment */
    /* renamed from: com.samsung.android.lxd.fragment.b$1 */
    class C09371 implements OnCheckedChangeListener {
        /* renamed from: a */
        final /* synthetic */ C1368b f2965a;

        C09371(C1368b c1368b) {
            this.f2965a = c1368b;
        }

        public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
            this.f2965a.f4507t = z;
        }
    }

    /* renamed from: j */
    public static C1368b m6278j() {
        return new C1368b();
    }

    /* renamed from: k */
    public boolean m6281k() {
        return this.f4507t;
    }

    public Dialog onCreateDialog(Bundle bundle) {
        this.c = LayoutInflater.from((C1347a) getHost()).inflate(R.layout.fragment_checkbox_dialog, null);
        return new Builder(getContext()).setView(this.c).create();
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        return this.c;
    }

    /* renamed from: h */
    public C1368b m6280h(int i) {
        this.f4506s = i;
        return this;
    }

    /* renamed from: f */
    protected C0936a mo764f() {
        if (getView() == null) {
            Log.m3855e(f4505r, "update : View had not been created yet!");
            return this;
        }
        super.mo764f();
        CheckBox checkBox = (CheckBox) getView().findViewById(R.id.checkBox);
        checkBox.setChecked(true);
        checkBox.setText(getString(this.f4506s));
        checkBox.setOnCheckedChangeListener(new C09371(this));
        return this;
    }
}
