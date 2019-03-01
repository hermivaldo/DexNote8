package com.samsung.android.lxd.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.Switch;
import android.widget.TextView;
import com.samsung.android.lxd.R;
import com.samsung.android.lxd.processor.utils.log.Log;

public class SwitchFragment extends Fragment {
    private static final String c = "SwitchFragment";
    protected boolean a = false;
    protected String b = null;
    private int d = 0;

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        return layoutInflater.inflate(R.layout.fragment_switch, viewGroup, false);
    }

    public void onActivityCreated(Bundle bundle) {
        super.onActivityCreated(bundle);
        e();
    }

    public void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
    }

    protected TextView a() {
        return getView() != null ? (TextView) getView().findViewById(R.id.itemDescriptionText) : null;
    }

    protected TextView b() {
        return getView() != null ? (TextView) getView().findViewById(R.id.itemStateText) : null;
    }

    protected Switch c() {
        return getView() != null ? (Switch) getView().findViewById(R.id.itemSwitch) : null;
    }

    public boolean d() {
        return c().isChecked();
    }

    public SwitchFragment a(boolean z) {
        this.a = z;
        return this;
    }

    public SwitchFragment a(String str) {
        this.b = str;
        return this;
    }

    public SwitchFragment a(int i) {
        this.d = i;
        return this;
    }

    public SwitchFragment e() {
        if (getView() == null) {
            Log.e(c, "update : View had not been created yet!");
            return this;
        }
        getView().setVisibility(this.d);
        if (this.d == 8) {
            return this;
        }
        c().setOnCheckedChangeListener(new OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                if (z) {
                    SwitchFragment.this.b().setText(SwitchFragment.this.getString(R.string.share_folder_enabled));
                } else {
                    SwitchFragment.this.b().setText(SwitchFragment.this.getString(R.string.share_folder_disabled));
                }
            }
        });
        if (this.a) {
            c().setChecked(true);
            b().setText(getString(R.string.share_folder_enabled));
        } else {
            c().setChecked(false);
            b().setText(getString(R.string.share_folder_disabled));
        }
        a().setText(this.b);
        return this;
    }
}
