package com.samsung.android.lxd;

import android.app.ActionBar;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.TextView;
import com.samsung.android.lxd.a.j;
import com.samsung.android.lxd.a.o;
import com.samsung.android.lxd.processor.utils.log.Log;

public class SettingsActivity extends a {
    private static final String b = "SettingsActivity";

    public boolean onCreateOptionsMenu(Menu menu) {
        return true;
    }

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_settings);
        ActionBar actionBar = getActionBar();
        if (actionBar != null) {
            actionBar.setTitle(R.string.settings_toolbar_title);
        }
        ((TextView) findViewById(R.id.accountId)).setText(o.j("com.osp.app.signin"));
        findViewById(R.id.samsungAccount).setOnClickListener(new j() {
            public void onClick(View view) {
                super.onClick(view);
                if (o.j("com.osp.app.signin") != null) {
                    try {
                        SettingsActivity.this.startActivity(o.x());
                    } catch (Exception e) {
                        String N = SettingsActivity.b;
                        StringBuilder stringBuilder = new StringBuilder();
                        stringBuilder.append("can't open sa settings: ");
                        stringBuilder.append(e.toString());
                        Log.e(N, stringBuilder.toString());
                    }
                }
            }
        });
        findViewById(R.id.about).setOnClickListener(new j() {
            public void onClick(View view) {
                super.onClick(view);
                SettingsActivity.this.startActivity(new Intent(SettingsActivity.this, AboutActivity.class));
            }
        });
    }
}
