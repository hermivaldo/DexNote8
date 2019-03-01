package com.samsung.android.lxd.fragment;

import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import com.samsung.android.lxd.LxdApplication;
import com.samsung.android.lxd.R;
import com.samsung.android.lxd.a.j;
import com.samsung.android.lxd.processor.utils.log.Log;

public class CommitButtonFragment extends Fragment {
    private static final String a = "CommitButtonFragment";
    private j b = null;
    private String c = null;
    private boolean d = false;
    private int e = 0;
    private int f = -1;

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        return layoutInflater.inflate(R.layout.fragment_commit_button, viewGroup, false);
    }

    public void onActivityCreated(Bundle bundle) {
        super.onActivityCreated(bundle);
        c();
    }

    public void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
    }

    protected Button a() {
        return getView() != null ? (Button) getView().findViewById(R.id.commitButton) : null;
    }

    public boolean b() {
        return this.d;
    }

    public CommitButtonFragment a(boolean z) {
        this.d = z;
        return this;
    }

    public CommitButtonFragment a(String str) {
        this.c = str;
        return this;
    }

    public CommitButtonFragment a(j jVar) {
        this.b = jVar;
        return this;
    }

    public CommitButtonFragment a(int i) {
        this.f = i;
        return this;
    }

    public CommitButtonFragment c() {
        if (getView() == null) {
            Log.e(a, "update : View had not been created yet!");
            return this;
        }
        Button a = a();
        if (a != null) {
            Context a2 = LxdApplication.a();
            a.setEnabled(this.d);
            a.setTextColor(a2.getResources().getColorStateList(R.color.button_text, null));
            if (!TextUtils.isEmpty(this.c)) {
                a.setText(this.c);
            }
            if (this.b != null) {
                a.setOnClickListener(this.b);
            }
            if (this.f != -1) {
                a.setGravity(this.f);
            }
            a.setVisibility(this.e);
        }
        return this;
    }
}
