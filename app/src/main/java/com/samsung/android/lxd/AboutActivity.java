package com.samsung.android.lxd;

import android.app.ActionBar;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import com.samsung.android.lxd.a.f;
import com.samsung.android.lxd.a.j;
import com.samsung.android.lxd.a.o;

public class AboutActivity extends a {
    private static final String b = "AboutActivity";

    public boolean onCreateOptionsMenu(Menu menu) {
        return true;
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        N();
    }

    public void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        N();
    }

    private void N() {
        int i;
        if (o.k((Context) this)) {
            setContentView(R.layout.activity_about);
            float f = (float) o.m(this).y;
            findViewById(R.id.about_app_link_layout_empty_view_bottom).getLayoutParams().height = (int) (0.05f * f);
            findViewById(R.id.about_layout_empty_view_top).getLayoutParams().height = (int) (o.q() ? f * 0.0763f : f * 0.1456f);
            findViewById(R.id.about_layout_empty_view_bottom).getLayoutParams().height = (int) (o.q() ? f * 0.0763f : f * 0.1456f);
        } else {
            setContentView(R.layout.activity_about_land);
            i = (int) (((float) o.m(this).y) * 0.036f);
            findViewById(R.id.about_layout_empty_view_top).getLayoutParams().height = i;
            findViewById(R.id.about_layout_empty_view_bottom).getLayoutParams().height = i;
        }
        ActionBar actionBar = getActionBar();
        if (actionBar != null) {
            actionBar.setTitle(R.string.about_linux_on_dex);
        }
        ((ImageView) findViewById(R.id.image)).setImageResource(R.drawable.ic_launcher);
        findViewById(R.id.about_app_info).setOnClickListener(new j() {
            public void onClick(View view) {
                super.onClick(view);
                AboutActivity.this.p(LxdApplication.a().getPackageName());
            }
        });
        findViewById(R.id.about_disclamer_button).setOnClickListener(new j() {
            public void onClick(View view) {
                super.onClick(view);
                AboutActivity.this.a(f.DISCLAIMER);
            }
        });
        findViewById(R.id.about_privacy_policy).setOnClickListener(new j() {
            public void onClick(View view) {
                super.onClick(view);
                AboutActivity.this.a(f.PRIVACY_POLICY);
            }
        });
        findViewById(R.id.about_open_source_licenses_button).setOnClickListener(new j() {
            public void onClick(View view) {
                super.onClick(view);
                AboutActivity.this.a(f.OPEN_SOURCE_LICENCES);
            }
        });
        TextView textView = (TextView) findViewById(R.id.about_version);
        String string = getString(R.string.version);
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(string);
        stringBuilder.append(" ");
        string = stringBuilder.toString();
        stringBuilder = new StringBuilder();
        stringBuilder.append(string);
        stringBuilder.append(o.b((Context) this, LxdApplication.a().getPackageName()));
        textView.setText(stringBuilder.toString());
        textView = (TextView) findViewById(R.id.about_version_info);
        Button button = (Button) findViewById(R.id.about_update_button);
        if (o.q()) {
            textView.setText(getString(R.string.new_version_info));
            button.setVisibility(0);
            i = o.m(this).x;
            int i2 = o.m(this).y;
            int i3 = (o.f((Context) this) || o.d((Context) this)) ? 4 : 2;
            button.getLayoutParams().width = i > i2 ? i2 / i3 : i / i3;
            return;
        }
        textView.setText(getString(R.string.latest_version_info));
        button.setVisibility(8);
    }

    private void p(String str) {
        Intent intent = new Intent();
        intent.setAction("android.settings.APPLICATION_DETAILS_SETTINGS");
        intent.addCategory("android.intent.category.DEFAULT");
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("package:");
        stringBuilder.append(str);
        intent.setData(Uri.parse(stringBuilder.toString()));
        intent.setFlags(335544320);
        getApplicationContext().startActivity(intent);
    }

    private void a(f fVar) {
        Intent intent = new Intent(this, InfoActivity.class);
        intent.putExtra("infoActivityType", fVar);
        startActivity(intent);
    }
}
