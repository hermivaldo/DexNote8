package com.samsung.android.lxd;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import com.samsung.android.lxd.a.o;
import com.samsung.android.lxd.processor.ExecutionType;
import com.samsung.android.lxd.processor.config.SystemConfigManager;
import com.samsung.android.lxd.processor.network.channel.pty.internal.KeycodeConstants;
import com.samsung.android.lxd.processor.utils.log.Log;

public class SplashThemeActivity extends a {
    private static final String b = "SplashThemeActivity";

    public boolean g() {
        return false;
    }

    public boolean h() {
        return false;
    }

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        Log.i(b, "onCreate");
        if (ExecutionType.QUICK_GUI.ordinal() == getIntent().getIntExtra("executionType", -1) && N()) {
            Intent intent = new Intent(this, ListActivity.class);
            intent.putExtra("executionType", ExecutionType.QUICK_GUI.ordinal());
            intent.putExtra("configId", O());
            startActivity(intent);
            finish();
            return;
        }
        setContentView(R.layout.activity_splash);
        Animation alphaAnimation = new AlphaAnimation(0.0f, 1.0f);
        alphaAnimation.setDuration(300);
        alphaAnimation.setFillAfter(true);
        findViewById(R.id.textView).startAnimation(alphaAnimation);
        new Handler().postDelayed(new Runnable() {
            public void run() {
                SplashThemeActivity.this.startActivity(new Intent(SplashThemeActivity.this, EntryActivity.class).setFlags(KeycodeConstants.META_META_ON));
            }
        }, 700);
        new Handler().postDelayed(new Runnable() {
            public void run() {
                SplashThemeActivity.this.finish();
            }
        }, 1500);
    }

    private boolean N() {
        Log.d(b, "checkQuickGUI");
        if (o.G() && O() != null && ((o.d((Context) this) || o.e()) && o.i("com.osp.app.signin"))) {
            return true;
        }
        String str = b;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("configId: ");
        stringBuilder.append(O());
        Log.d(str, stringBuilder.toString());
        return false;
    }

    private String O() {
        SharedPreferences sharedPreferences = getSharedPreferences("prefs", 0);
        if (sharedPreferences != null) {
            String string = sharedPreferences.getString("configId", "");
            try {
                if (SystemConfigManager.contains(Long.parseLong(string))) {
                    String str = b;
                    StringBuilder stringBuilder = new StringBuilder();
                    stringBuilder.append("getConfigId: ");
                    stringBuilder.append(string);
                    Log.d(str, stringBuilder.toString());
                    return string;
                }
            } catch (NumberFormatException unused) {
                return null;
            }
        }
    }
}
