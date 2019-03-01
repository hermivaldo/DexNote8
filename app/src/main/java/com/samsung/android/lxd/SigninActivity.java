package com.samsung.android.lxd;

import android.accounts.Account;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.samsung.android.lxd.a.j;
import com.samsung.android.lxd.a.l;
import com.samsung.android.lxd.a.o;
import com.samsung.android.lxd.processor.utils.log.Log;

public class SigninActivity extends a {
    private static final String b = "SigninActivity";
    private Account[] c = null;
    private boolean d = true;
    private boolean e = false;

    @SuppressLint({"ClickableViewAccessibility"})
    protected void onCreate(Bundle bundle) {
        e();
        super.onCreate(bundle);
        this.a = "002";
        setContentView(R.layout.activity_signin);
        a(bundle);
        P();
    }

    public void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        P();
    }

    protected void onSaveInstanceState(Bundle bundle) {
        bundle.putBoolean("VIEWMORE_FLAG", this.d);
        bundle.putBoolean("CHECKBOX_STATE", this.e);
        String str = b;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("onSaveInstanceState: ");
        stringBuilder.append(bundle.toString());
        Log.d(str, stringBuilder.toString());
        super.onSaveInstanceState(bundle);
    }

    protected void onResume() {
        super.onResume();
        if (O() != o.i("com.osp.app.signin")) {
            Log.i(b, "Account info mismatched");
            P();
        }
    }

    protected void onActivityResult(int i, int i2, Intent intent) {
        String str = b;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("onActivityResult: ");
        stringBuilder.append(i);
        stringBuilder.append(", ");
        stringBuilder.append(i2);
        Log.d(str, stringBuilder.toString());
        if (i != 3) {
            super.onActivityResult(i, i2, intent);
        } else if (i2 == -1) {
            N();
        } else {
            String str2;
            String str3 = b;
            StringBuilder stringBuilder2 = new StringBuilder();
            stringBuilder2.append("SA signIn fail, result: ");
            stringBuilder2.append(i2);
            stringBuilder2.append(", reason: ");
            if (intent == null) {
                str2 = "";
            } else {
                str2 = intent.getStringExtra("error_message");
            }
            stringBuilder2.append(str2);
            Log.i(str3, stringBuilder2.toString());
        }
    }

    public void onBackPressed() {
        f.f();
        super.b();
    }

    private void N() {
        Log.d(b, "signInSuccess: ");
        o.i((Context) this);
        a((Activity) this);
        setResult(-1, new Intent());
        finish();
    }

    private boolean O() {
        return this.c != null && this.c.length > 0;
    }

    private void a(Bundle bundle) {
        if (bundle != null) {
            this.d = bundle.getBoolean("VIEWMORE_FLAG");
            this.e = bundle.getBoolean("CHECKBOX_STATE");
        }
    }

    @SuppressLint({"ClickableViewAccessibility"})
    private void P() {
        TextView textView = (TextView) findViewById(R.id.email_signin);
        TextView textView2 = (TextView) findViewById(R.id.signin_subtitle);
        CheckBox checkBox = (CheckBox) findViewById(R.id.checkBoxSA);
        final TextView textView3 = (TextView) findViewById(R.id.signing_viewmore);
        final LinearLayout linearLayout = (LinearLayout) findViewById(R.id.signIn_button);
        final TextView textView4 = (TextView) findViewById(R.id.signIn_button_textview);
        final ImageView imageView = (ImageView) findViewById(R.id.signIn_button_imageview);
        TextView textView5 = (TextView) findViewById(R.id.signing_context1);
        final TextView textView6 = (TextView) findViewById(R.id.signing_context2);
        this.c = o.k("com.osp.app.signin");
        o.a(textView5, getString(R.string.samsungaccount_description1), 1);
        o.a(textView6, getString(R.string.samsungaccount_description2), 1);
        int i = 0;
        if (O()) {
            textView.setVisibility(0);
            textView.setPaintFlags(textView.getPaintFlags() | 8);
            textView.setText(this.c[0].name);
            textView2.setText(R.string.samsungaccount_subtitle_confirm);
            textView4.setText("CONFIRM");
        } else {
            textView.setVisibility(8);
            textView2.setText(R.string.samsungaccount_subtitle);
            textView4.setText(R.string.samsungaccount_button);
        }
        linearLayout.setOnClickListener(new j() {
            public void onClick(View view) {
                super.onClick(view);
                if (SigninActivity.this.O()) {
                    l.a(SigninActivity.this.a, String.valueOf(203));
                    SigninActivity.this.N();
                    return;
                }
                l.a(SigninActivity.this.a, String.valueOf(202));
                SigninActivity.this.p();
            }
        });
        checkBox.setOnCheckedChangeListener(new OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                if (z) {
                    linearLayout.setClickable(true);
                    textView4.setTextColor(-12895429);
                    imageView.setImageAlpha(255);
                    l.a(SigninActivity.this.a, String.valueOf(201));
                } else {
                    linearLayout.setClickable(false);
                    textView4.setTextColor(1832598331);
                    imageView.setImageAlpha(110);
                }
                SigninActivity.this.e = z;
            }
        });
        textView3.setOnClickListener(new j() {
            public void onClick(View view) {
                super.onClick(view);
                if (SigninActivity.this.d) {
                    textView6.setVisibility(0);
                    textView3.setText(R.string.samsungaccount_viewless);
                    SigninActivity.this.d = false;
                    return;
                }
                textView6.setVisibility(8);
                textView3.setText(R.string.samsungaccount_viewmore);
                SigninActivity.this.d = true;
            }
        });
        if (this.e) {
            checkBox.setChecked(true);
            linearLayout.setClickable(true);
            textView4.setTextColor(-12895429);
            imageView.setImageAlpha(255);
        } else {
            checkBox.setChecked(false);
            linearLayout.setClickable(false);
            textView4.setTextColor(1832598331);
            imageView.setImageAlpha(110);
        }
        if (this.d) {
            textView3.setText(R.string.samsungaccount_viewmore);
            textView6.setVisibility(8);
        } else {
            textView3.setText(R.string.samsungaccount_viewless);
            textView6.setVisibility(0);
        }
        int i2 = (int) ((((double) o.m(this).y) * 2.7d) / 100.0d);
        double d = (double) i2;
        int i3 = (int) (0.5d * d);
        int i4 = (int) (d * 0.25d);
        int i5 = i2 * 2;
        if (o.d((Context) this)) {
            findViewById(R.id.top_margin).getLayoutParams().height = i5;
            ((TextView) findViewById(R.id.signin_title)).setTextSize(30.0f);
        } else {
            findViewById(R.id.top_margin).getLayoutParams().height = i2;
        }
        findViewById(R.id.below_title_margin).getLayoutParams().height = i3;
        LayoutParams layoutParams = findViewById(R.id.below_email_signin_margin).getLayoutParams();
        if (O()) {
            i = i2;
        }
        layoutParams.height = i;
        findViewById(R.id.below_signin_subtitle_margin).getLayoutParams().height = i3;
        findViewById(R.id.below_noticDevider_signin_margin).getLayoutParams().height = i3;
        findViewById(R.id.below_signing_notice_margin).getLayoutParams().height = i4;
        findViewById(R.id.below_signing_context_margin).getLayoutParams().height = i4;
        findViewById(R.id.below_contents_sign_in_margin).getLayoutParams().height = i4;
        findViewById(R.id.below_checkboxDivider_sign_in_margin).getLayoutParams().height = i4;
        findViewById(R.id.below_button_sign_in_margin).getLayoutParams().height = i2;
        findViewById(R.id.below_checkbox_sign_in_margin).getLayoutParams().height = i2;
    }
}
