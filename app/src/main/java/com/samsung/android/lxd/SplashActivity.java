package com.samsung.android.lxd;

import android.content.Intent;
import android.os.Bundle;
import com.samsung.android.lxd.processor.utils.log.Log;

public class SplashActivity extends a {
    private static final String b = "SplashActivity";

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        Log.i(b, "onCreate");
        if (isTaskRoot()) {
            Intent intent = getIntent();
            String str = b;
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("Root Activity: ");
            stringBuilder.append(intent.toString());
            Log.i(str, stringBuilder.toString());
            intent.setClass(this, SplashThemeActivity.class);
            intent.setFlags(536870912);
            startActivity(intent);
        }
        finish();
    }
}
