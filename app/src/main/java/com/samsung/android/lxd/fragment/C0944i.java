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
import com.samsung.android.lxd.C1347a;
import com.samsung.android.lxd.R;
import com.samsung.android.lxd.p064a.C0869j;
import com.samsung.android.lxd.p064a.C0874l;
import com.samsung.android.lxd.processor.config.SystemConfig;
import com.samsung.android.lxd.processor.config.SystemConfigType;
import com.samsung.android.lxd.processor.utils.log.Log;

/* compiled from: SeekBarFragment */
/* renamed from: com.samsung.android.lxd.fragment.i */
public class C0944i extends Fragment {
    /* renamed from: a */
    private static final String f2977a = "i";
    /* renamed from: b */
    private String f2978b = null;
    /* renamed from: c */
    private String f2979c = null;
    /* renamed from: d */
    private int f2980d = -1;
    /* renamed from: e */
    private int f2981e = -1;
    /* renamed from: f */
    private int f2982f = -1;
    /* renamed from: g */
    private boolean f2983g = true;

    /* compiled from: SeekBarFragment */
    /* renamed from: com.samsung.android.lxd.fragment.i$1 */
    class C09431 implements OnSeekBarChangeListener {
        /* renamed from: a */
        final /* synthetic */ C0944i f2976a;

        public void onStartTrackingTouch(SeekBar seekBar) {
        }

        C09431(C0944i c0944i) {
            this.f2976a = c0944i;
        }

        public void onStopTrackingTouch(SeekBar seekBar) {
            C0874l.m3434a(((C1347a) this.f2976a.getActivity()).f4424a, String.valueOf(805), this.f2976a.m3810h().getText().toString());
        }

        public void onProgressChanged(SeekBar seekBar, int i, boolean z) {
            seekBar = this.f2976a.m3810h();
            z = new StringBuilder();
            z.append(String.valueOf(i));
            z.append(String.valueOf(this.f2976a.f2979c));
            seekBar.setText(z.toString());
        }
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        return layoutInflater.inflate(R.layout.fragment_seek_bar, viewGroup, null);
    }

    public void onActivityCreated(Bundle bundle) {
        super.onActivityCreated(bundle);
        mo757c();
    }

    public void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
    }

    /* renamed from: e */
    protected TextView m3807e() {
        return getView() != null ? (TextView) getView().findViewById(R.id.itemDescriptionText) : null;
    }

    /* renamed from: f */
    protected TextView m3808f() {
        return getView() != null ? (TextView) getView().findViewById(R.id.minItemValueText) : null;
    }

    /* renamed from: g */
    protected TextView m3809g() {
        return getView() != null ? (TextView) getView().findViewById(R.id.maxItemValueText) : null;
    }

    /* renamed from: h */
    protected TextView m3810h() {
        return getView() != null ? (TextView) getView().findViewById(R.id.itemValueText) : null;
    }

    /* renamed from: i */
    protected SeekBar m3811i() {
        if (getView() == null) {
            return null;
        }
        if (this.f2983g) {
            return (SeekBar) getView().findViewById(R.id.itemSeekBar);
        }
        return (SeekBar) getView().findViewById(R.id.itemSeekBarDimmed);
    }

    /* renamed from: a */
    private void mo756a() {
        m3811i().setOnSeekBarChangeListener(new C09431(this));
    }

    /* renamed from: j */
    public int m3812j() {
        return m3811i().getProgress();
    }

    /* renamed from: b */
    public C0944i m3802b(String str) {
        this.f2978b = str;
        return this;
    }

    /* renamed from: c */
    public C0944i m3805c(String str) {
        this.f2979c = str;
        return this;
    }

    /* renamed from: a */
    public C0944i m3798a(int i) {
        this.f2981e = i;
        return this;
    }

    /* renamed from: b */
    public C0944i m3801b(int i) {
        this.f2982f = i;
        return this;
    }

    /* renamed from: a */
    public C0944i mo755a(boolean z) {
        this.f2983g = z;
        return this;
    }

    /* renamed from: c */
    public C0944i m3804c(int i) {
        this.f2980d = i;
        return this;
    }

    /* renamed from: a */
    public C0944i m3799a(C0869j c0869j) {
        m3810h().setOnClickListener(c0869j);
        return this;
    }

    /* renamed from: d */
    protected C0944i m3806d(int i) {
        m3804c(i);
        if (this.f2980d != -1) {
            m3811i().setProgress(this.f2980d);
            i = m3810h();
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(String.valueOf(this.f2980d));
            stringBuilder.append(String.valueOf(this.f2979c));
            i.setText(stringBuilder.toString());
        }
        return this;
    }

    /* renamed from: c */
    public C0944i mo757c() {
        if (getView() == null) {
            Log.m3855e(f2977a, "update : View had not been created yet!");
            return this;
        }
        if (!this.f2983g) {
            m3810h().setEnabled(false);
            m3811i().setEnabled(false);
            m3807e().setTextColor(getContext().getColor(R.color.LightTheme.no_item_text));
            m3810h().setTextColor(getContext().getColor(R.color.LightTheme.no_item_text));
            m3808f().setTextColor(getContext().getColor(R.color.LightTheme.no_item_text));
            m3809g().setTextColor(getContext().getColor(R.color.LightTheme.no_item_text));
            getView().findViewById(R.id.itemSeekBar).setVisibility(8);
            m3811i().setVisibility(0);
        }
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(SystemConfig.getMinimum(SystemConfigType.IMAGE_SIZE));
        stringBuilder.append(String.valueOf(this.f2979c));
        CharSequence stringBuilder2 = stringBuilder.toString();
        StringBuilder stringBuilder3 = new StringBuilder();
        stringBuilder3.append(SystemConfig.getMaximum(SystemConfigType.IMAGE_SIZE));
        stringBuilder3.append(String.valueOf(this.f2979c));
        CharSequence stringBuilder4 = stringBuilder3.toString();
        m3808f().setText(stringBuilder2);
        m3809g().setText(stringBuilder4);
        mo756a();
        if (!TextUtils.isEmpty(this.f2978b)) {
            m3807e().setText(this.f2978b);
        }
        if (this.f2981e != -1) {
            m3811i().setMin(this.f2981e);
            stringBuilder = new StringBuilder();
            stringBuilder.append(String.valueOf(this.f2981e));
            stringBuilder.append(String.valueOf(this.f2979c));
            m3808f().setText(stringBuilder.toString());
        }
        if (this.f2982f != -1) {
            m3811i().setMax(this.f2982f);
            stringBuilder = new StringBuilder();
            stringBuilder.append(String.valueOf(this.f2982f));
            stringBuilder.append(String.valueOf(this.f2979c));
            m3809g().setText(stringBuilder.toString());
        }
        if (this.f2980d != -1) {
            m3811i().setProgress(this.f2980d);
            TextView h = m3810h();
            stringBuilder3 = new StringBuilder();
            stringBuilder3.append(String.valueOf(this.f2980d));
            stringBuilder3.append(String.valueOf(this.f2979c));
            h.setText(stringBuilder3.toString());
        }
        return this;
    }
}
