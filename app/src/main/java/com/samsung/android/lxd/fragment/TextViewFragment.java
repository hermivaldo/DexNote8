package com.samsung.android.lxd.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.samsung.android.lxd.R;
import com.samsung.android.lxd.processor.utils.log.Log;

public class TextViewFragment extends Fragment {
    private static final String d = "TextViewFragment";
    protected String a = null;
    protected String b = null;
    protected boolean c = false;
    private int e = 0;

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        return layoutInflater.inflate(R.layout.fragment_text_view, viewGroup, false);
    }

    public void onActivityCreated(Bundle bundle) {
        super.onActivityCreated(bundle);
        c();
    }

    public void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
    }

    protected TextView a() {
        return getView() != null ? (TextView) getView().findViewById(R.id.itemTitle) : null;
    }

    protected TextView b() {
        return getView() != null ? (TextView) getView().findViewById(R.id.itemText) : null;
    }

    public TextViewFragment a(String str) {
        this.a = str;
        return this;
    }

    public TextViewFragment b(String str) {
        this.b = str;
        return this;
    }

    public TextViewFragment a(boolean z) {
        this.c = z;
        return this;
    }

    private void d() {
        if (!TextUtils.isEmpty(this.a)) {
            a().setText(this.a);
            a().setVisibility(0);
        }
    }

    private void e() {
        if (TextUtils.isEmpty(this.b)) {
            b().setText(getString(R.string.non_specified));
            b().setTextColor(getResources().getColor(R.color.LightTheme.no_item_text, null));
        } else {
            b().setText(this.b);
            b().setTextColor(getResources().getColor(R.color.LightTheme.primary_text, null));
        }
        b().setVisibility(0);
    }

    public TextViewFragment a(int i) {
        this.e = i;
        return this;
    }

    public TextViewFragment c() {
        if (getView() == null) {
            Log.e(d, "update : View had not been created yet!");
            return this;
        }
        getView().setVisibility(this.e);
        if (this.e == 8) {
            return this;
        }
        a().setVisibility(8);
        b().setVisibility(8);
        d();
        e();
        return this;
    }
}
