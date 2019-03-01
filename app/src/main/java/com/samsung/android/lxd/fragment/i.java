package com.samsung.android.lxd.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.TextView;
import com.samsung.android.lxd.R;
import com.samsung.android.lxd.a;
import com.samsung.android.lxd.a.j;
import com.samsung.android.lxd.a.l;
import com.samsung.android.lxd.processor.config.SystemConfig;
import com.samsung.android.lxd.processor.config.SystemConfigType;
import com.samsung.android.lxd.processor.utils.log.Log;

/* compiled from: SeekBarFragment */
public class i extends Fragment {
    private static final String a = "i";
    private String b = null;
    private String c = null;
    private int d = -1;
    private int e = -1;
    private int f = -1;
    private boolean g = true;

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        return layoutInflater.inflate(R.layout.fragment_seek_bar, viewGroup, false);
    }

    public void onActivityCreated(Bundle bundle) {
        super.onActivityCreated(bundle);
        c();
    }

    public void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
    }

    protected TextView e() {
        return getView() != null ? (TextView) getView().findViewById(R.id.itemDescriptionText) : null;
    }

    protected TextView f() {
        return getView() != null ? (TextView) getView().findViewById(R.id.minItemValueText) : null;
    }

    protected TextView g() {
        return getView() != null ? (TextView) getView().findViewById(R.id.maxItemValueText) : null;
    }

    protected TextView h() {
        return getView() != null ? (TextView) getView().findViewById(R.id.itemValueText) : null;
    }

    protected SeekBar i() {
        if (getView() == null) {
            return null;
        }
        if (this.g) {
            return (SeekBar) getView().findViewById(R.id.itemSeekBar);
        }
        return (SeekBar) getView().findViewById(R.id.itemSeekBarDimmed);
    }

    private void a() {
        i().setOnSeekBarChangeListener(new OnSeekBarChangeListener() {
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            public void onStopTrackingTouch(SeekBar seekBar) {
                l.a(((a) i.this.getActivity()).a, String.valueOf(805), i.this.h().getText().toString());
            }

            public void onProgressChanged(SeekBar seekBar, int i, boolean z) {
                TextView h = i.this.h();
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append(String.valueOf(i));
                stringBuilder.append(String.valueOf(i.this.c));
                h.setText(stringBuilder.toString());
            }
        });
    }

    public int j() {
        return i().getProgress();
    }

    public i b(String str) {
        this.b = str;
        return this;
    }

    public i c(String str) {
        this.c = str;
        return this;
    }

    public i a(int i) {
        this.e = i;
        return this;
    }

    public i b(int i) {
        this.f = i;
        return this;
    }

    public i a(boolean z) {
        this.g = z;
        return this;
    }

    public i c(int i) {
        this.d = i;
        return this;
    }

    public i a(j jVar) {
        h().setOnClickListener(jVar);
        return this;
    }

    protected i d(int i) {
        c(i);
        if (this.d != -1) {
            i().setProgress(this.d);
            TextView h = h();
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(String.valueOf(this.d));
            stringBuilder.append(String.valueOf(this.c));
            h.setText(stringBuilder.toString());
        }
        return this;
    }

    public i c() {
        if (getView() == null) {
            Log.e(a, "update : View had not been created yet!");
            return this;
        }
        if (!this.g) {
            h().setEnabled(false);
            i().setEnabled(false);
            e().setTextColor(getContext().getColor(R.color.LightTheme.no_item_text));
            h().setTextColor(getContext().getColor(R.color.LightTheme.no_item_text));
            f().setTextColor(getContext().getColor(R.color.LightTheme.no_item_text));
            g().setTextColor(getContext().getColor(R.color.LightTheme.no_item_text));
            getView().findViewById(R.id.itemSeekBar).setVisibility(8);
            i().setVisibility(0);
        }
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(SystemConfig.getMinimum(SystemConfigType.IMAGE_SIZE));
        stringBuilder.append(String.valueOf(this.c));
        CharSequence stringBuilder2 = stringBuilder.toString();
        StringBuilder stringBuilder3 = new StringBuilder();
        stringBuilder3.append(SystemConfig.getMaximum(SystemConfigType.IMAGE_SIZE));
        stringBuilder3.append(String.valueOf(this.c));
        CharSequence stringBuilder4 = stringBuilder3.toString();
        f().setText(stringBuilder2);
        g().setText(stringBuilder4);
        a();
        if (!TextUtils.isEmpty(this.b)) {
            e().setText(this.b);
        }
        if (this.e != -1) {
            i().setMin(this.e);
            stringBuilder = new StringBuilder();
            stringBuilder.append(String.valueOf(this.e));
            stringBuilder.append(String.valueOf(this.c));
            f().setText(stringBuilder.toString());
        }
        if (this.f != -1) {
            i().setMax(this.f);
            stringBuilder = new StringBuilder();
            stringBuilder.append(String.valueOf(this.f));
            stringBuilder.append(String.valueOf(this.c));
            g().setText(stringBuilder.toString());
        }
        if (this.d != -1) {
            i().setProgress(this.d);
            TextView h = h();
            stringBuilder3 = new StringBuilder();
            stringBuilder3.append(String.valueOf(this.d));
            stringBuilder3.append(String.valueOf(this.c));
            h.setText(stringBuilder3.toString());
        }
        return this;
    }
}
