package com.samsung.android.lxd.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout.LayoutParams;
import com.samsung.android.lxd.R;
import com.samsung.android.lxd.a.j;
import com.samsung.android.lxd.a.o;
import com.samsung.android.lxd.processor.utils.log.Log;

public class TextButtonFragment extends Fragment {
    private static final String d = "TextButtonFragment";
    protected j a = null;
    protected int b = -1;
    protected int c = -1;
    private boolean e = true;

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        return layoutInflater.inflate(R.layout.item_text_button, viewGroup, false);
    }

    public void onActivityCreated(Bundle bundle) {
        super.onActivityCreated(bundle);
        b();
    }

    public void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
    }

    protected Button a() {
        return getView() != null ? (Button) getView().findViewById(R.id.button) : null;
    }

    public TextButtonFragment a(j jVar) {
        this.a = jVar;
        return this;
    }

    public TextButtonFragment a(int i) {
        this.b = i;
        return this;
    }

    public TextButtonFragment b(int i) {
        this.c = i;
        return this;
    }

    public TextButtonFragment a(boolean z) {
        this.e = z;
        return this;
    }

    public TextButtonFragment b() {
        if (getView() == null) {
            Log.e(d, "update : View had not been created yet!");
            return this;
        }
        LayoutParams layoutParams = (LayoutParams) a().getLayoutParams();
        layoutParams.width = (int) o.a(getActivity(), 200.0f);
        a().setLayoutParams(layoutParams);
        if (this.b != -1) {
            a().setText(this.b);
        }
        if (this.c != -1) {
            layoutParams.addRule(this.c);
            a().setLayoutParams(layoutParams);
        }
        if (this.a != null) {
            a().setOnClickListener(this.a);
        }
        if (!this.e) {
            a().setEnabled(false);
            a().setTextColor(getResources().getColor(R.color.LightTheme.no_item_text, null));
        }
        return this;
    }
}
