package com.samsung.android.lxd.fragment;

import android.app.Fragment;
import android.graphics.PorterDuff.Mode;
import android.os.Bundle;
import android.text.InputFilter;
import android.text.InputFilter.LengthFilter;
import android.text.Spanned;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnFocusChangeListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import com.samsung.android.lxd.R;
import com.samsung.android.lxd.a.e;
import com.samsung.android.lxd.processor.utils.log.Log;

public class EditTextFragment extends Fragment {
    private static final String a = "EditTextFragment";
    private CommitButtonFragment b = null;
    private Button c;
    private String d;
    private String e;
    private String f = null;
    private String g = null;
    private String h = null;
    private String i = null;
    private boolean j = false;
    private boolean k = true;
    private int l = -1;
    private int m = -1;
    private int n = -1;
    private int o = -1;

    public static EditTextFragment a() {
        return new EditTextFragment();
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        return layoutInflater.inflate(R.layout.fragment_edit_text, viewGroup, false);
    }

    public void onActivityCreated(Bundle bundle) {
        super.onActivityCreated(bundle);
        f();
    }

    public void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
    }

    public String b() {
        return e().getText().toString();
    }

    protected TextView c() {
        return getView() != null ? (TextView) getView().findViewById(R.id.itemTitleTextView) : null;
    }

    protected TextView d() {
        return getView() != null ? (TextView) getView().findViewById(R.id.itemErrorTextView) : null;
    }

    protected EditText e() {
        return getView() != null ? (EditText) getView().findViewById(R.id.itemEditText) : null;
    }

    public EditTextFragment a(String str) {
        this.d = str;
        return this;
    }

    public EditTextFragment b(String str) {
        this.e = str;
        return this;
    }

    public EditTextFragment c(String str) {
        this.g = str;
        return this;
    }

    public EditTextFragment d(String str) {
        this.h = str;
        return this;
    }

    public EditTextFragment e(String str) {
        this.i = str;
        return this;
    }

    public EditTextFragment a(boolean z) {
        this.k = z;
        return this;
    }

    public EditTextFragment a(int i) {
        this.o = i;
        return this;
    }

    public EditTextFragment f(String str) {
        this.f = str;
        return this;
    }

    public EditTextFragment b(int i) {
        this.n = i;
        return this;
    }

    public EditTextFragment a(CommitButtonFragment commitButtonFragment) {
        this.b = commitButtonFragment;
        return this;
    }

    public EditTextFragment a(Button button) {
        this.c = button;
        return this;
    }

    public EditTextFragment c(int i) {
        this.m = i;
        return this;
    }

    public EditTextFragment d(int i) {
        this.l = i;
        return this;
    }

    public EditTextFragment b(boolean z) {
        this.j = z;
        return this;
    }

    public EditTextFragment f() {
        if (getView() == null) {
            Log.e(a, "update : View had not been created yet!");
            return this;
        }
        String str = null;
        if (!this.k) {
            c().setEnabled(this.k);
            c().setFocusable(this.k);
            c().setTextColor(getResources().getColor(R.color.LightTheme.no_item_text, null));
            e().setEnabled(this.k);
            e().setFocusable(this.k);
            e().setTextColor(getResources().getColor(R.color.LightTheme.no_item_text, null));
        }
        if (this.j) {
            e().requestFocus();
        }
        if (!TextUtils.isEmpty(this.d)) {
            c().setVisibility(0);
            c().setText(this.d);
        }
        if (!TextUtils.isEmpty(this.e)) {
            e().setText(this.e);
            e().setSelection(this.e.length());
        }
        if (!(this.n == -1 || a(e()) == this.n)) {
            a(e(), this.n);
        }
        if (this.o != -1) {
            e().setInputType(this.o);
        }
        e().setOnFocusChangeListener(new OnFocusChangeListener() {
            public void onFocusChange(View view, boolean z) {
                if (!TextUtils.isEmpty(EditTextFragment.this.f)) {
                    if (z) {
                        EditTextFragment.this.e().setHint(EditTextFragment.this.f);
                    } else {
                        EditTextFragment.this.e().setHint("");
                    }
                }
                EditTextFragment.this.j = z;
                EditTextFragment.this.g();
            }
        });
        if (!TextUtils.isEmpty(e().getText())) {
            str = e().getText().toString();
        }
        g(str);
        return this;
    }

    public e g(String str) {
        e eVar = e.INVALID;
        if (getView() == null) {
            Log.e(a, "updateModeOnTextChange : View had not been created yet!");
            return eVar;
        }
        boolean z = false;
        if (str == null || TextUtils.isEmpty(str.replaceAll("\\s+", ""))) {
            if (str == null || TextUtils.isEmpty(this.g)) {
                d().setVisibility(4);
            } else {
                d().setText(this.g);
                d().setVisibility(0);
            }
            eVar = e.EMPTY;
        } else if (str.length() > a(e())) {
            d().setText(this.h);
            d().setVisibility(0);
            eVar = h(str.substring(0, a(e()))) ? e.FULL : e.INVALID;
        } else if (h(str)) {
            d().setVisibility(4);
            eVar = e.NORMAL;
        } else {
            d().setText(this.i);
            d().setVisibility(0);
            eVar = e.INVALID;
        }
        g();
        if (!(eVar.equals(e.EMPTY) || eVar.equals(e.INVALID))) {
            z = true;
        }
        c(z);
        String str2 = a;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("updateModeOnTextChange : ");
        stringBuilder.append(str);
        stringBuilder.append(", ");
        stringBuilder.append(eVar);
        Log.d(str2, stringBuilder.toString());
        return eVar;
    }

    private void c(boolean z) {
        if (this.b != null) {
            this.b.a(z).c();
        } else if (this.c != null) {
            this.c.setEnabled(z);
        } else {
            Log.d(a, "Failed to find commit button!");
        }
    }

    private int a(EditText editText) {
        for (InputFilter inputFilter : editText.getFilters()) {
            if (inputFilter instanceof LengthFilter) {
                return ((LengthFilter) inputFilter).getMax();
            }
        }
        return -1;
    }

    private void a(EditText editText, int i) {
        editText.setFilters(new InputFilter[]{new LengthFilter(i) {
            public CharSequence filter(CharSequence charSequence, int i, int i2, Spanned spanned, int i3, int i4) {
                String substring = charSequence.toString().substring(i, i2);
                String obj = spanned.toString();
                int length = spanned.length();
                String substring2 = i3 > 0 ? obj.substring(0, i3) : "";
                obj = i4 < length ? obj.substring(i4, length) : "";
                if (charSequence.length() == 0 && spanned.length() == 0) {
                    EditTextFragment.this.g(null);
                } else {
                    EditTextFragment editTextFragment = EditTextFragment.this;
                    StringBuilder stringBuilder = new StringBuilder();
                    stringBuilder.append(substring2);
                    stringBuilder.append(substring);
                    stringBuilder.append(obj);
                    editTextFragment.g(stringBuilder.toString());
                }
                return super.filter(charSequence, i, i2, spanned, i3, i4);
            }
        }});
    }

    private void g() {
        if (getView() == null) {
            Log.e(a, "updateHintAndLineColor : View had not been created yet!");
            return;
        }
        int color;
        if (!TextUtils.isEmpty(this.f)) {
            e().setHint(this.j ? "" : this.f);
        }
        if (d().getVisibility() == 0) {
            color = getResources().getColor(R.color.LightTheme.error, null);
        } else if (this.j) {
            color = getResources().getColor(R.color.LightTheme.primary, null);
        } else {
            color = getResources().getColor(R.color.LightTheme.divider, null);
        }
        e().getBackground().setColorFilter(color, Mode.SRC_ATOP);
    }

    private boolean h(String str) {
        if (this.l == -1 && this.m == -1) {
            return true;
        }
        boolean z = false;
        try {
            int parseInt = Integer.parseInt(str);
            if (parseInt <= this.m && parseInt >= this.l) {
                z = true;
            }
            return z;
        } catch (NumberFormatException unused) {
            return false;
        }
    }
}
