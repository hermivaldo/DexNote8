package com.samsung.android.lxd;

import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.f;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.samsung.android.lxd.a.j;
import com.samsung.android.lxd.a.o;
import com.samsung.android.lxd.processor.utils.log.Log;
import java.util.ArrayList;

public class OobeActivity extends a {
    private static final String b = "OobeActivity";
    private ArrayList<Integer> c = new ArrayList();
    private final Integer[] d = new Integer[]{Integer.valueOf(R.drawable.ic_illus_graphic1), Integer.valueOf(R.drawable.ic_illus_graphic2), Integer.valueOf(R.drawable.ic_illus_graphic3)};
    private ArrayList<String> e = new ArrayList();
    private ArrayList<String> f = new ArrayList();
    private final Integer[] g = new Integer[]{Integer.valueOf(R.string.splash_title1), Integer.valueOf(R.string.splash_title2), Integer.valueOf(R.string.splash_title3)};
    private final Integer[] h = new Integer[]{Integer.valueOf(R.string.splash_desc1), Integer.valueOf(R.string.splash_desc2), Integer.valueOf(R.string.splash_desc3)};
    private ViewPager i;
    private int j = 0;
    private float k = 0.0f;
    private boolean l = false;

    public void onCreate(Bundle bundle) {
        i();
        super.onCreate(bundle);
        N();
        O();
    }

    public void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        N();
        O();
    }

    private void N() {
        int i = o.e((Context) this) ? o.k((Context) this) ? R.layout.activity_oobe : R.layout.activity_oobe_land : o.k((Context) this) ? R.layout.activity_oobe_tablet : R.layout.activity_oobe_tablet_land;
        float f = 0.04f;
        float f2 = 0.033f;
        float f3 = 0.074f;
        float f4 = 0.15f;
        float f5 = 0.057f;
        float f6;
        float f7;
        int i2;
        if (o.k((Context) this)) {
            setContentView(i);
            f6 = (float) o.m(this).y;
            f7 = (float) o.m(this).x;
            if (o.f() || o.d((Context) this)) {
                f4 = 0.203f;
            }
            if (!o.f()) {
                boolean d = o.d((Context) this);
            }
            if (o.f() || o.d((Context) this)) {
                f2 = 0.03f;
            }
            if (!(o.f() || o.d((Context) this))) {
                f = 0.057f;
            }
            if (!(o.f() || o.d((Context) this))) {
                f5 = 0.067f;
            }
            findViewById(R.id.splash_layout_empty_view_top).getLayoutParams().height = (int) (f4 * f6);
            findViewById(R.id.splash_layout_empty_view_underTitle).getLayoutParams().height = (int) (0.074f * f6);
            findViewById(R.id.splash_layout_empty_view_underPager).getLayoutParams().height = (int) (f2 * f6);
            findViewById(R.id.splash_layout_empty_view_bottom).getLayoutParams().height = (int) (f6 * f);
            i2 = (int) (f7 * f5);
            findViewById(R.id.splash_layout_empty_view_left).getLayoutParams().width = i2;
            findViewById(R.id.splash_layout_empty_view_right).getLayoutParams().width = i2;
            ((TextView) findViewById(R.id.OOBE_Title)).setText(getString(R.string.app_name));
            return;
        }
        setContentView(i);
        f6 = (float) o.m(this).y;
        f7 = (float) o.m(this).x;
        if (!(o.f() || o.d((Context) this))) {
            f4 = 0.05f;
        }
        if (!(o.f() || o.d((Context) this))) {
            f3 = 0.0f;
        }
        if (o.f() || o.d((Context) this)) {
            f2 = 0.03f;
        }
        if (o.f() || o.d((Context) this)) {
            f5 = 0.04f;
        }
        f = (o.f() || o.d((Context) this)) ? 0.125f : 0.032f;
        findViewById(R.id.splash_layout_empty_view_top).getLayoutParams().height = (int) (f4 * f6);
        if (o.f() || o.d((Context) this)) {
            findViewById(R.id.splash_layout_empty_view_underTitle).getLayoutParams().height = (int) (f3 * f6);
        }
        findViewById(R.id.splash_layout_empty_view_underPager).getLayoutParams().height = (int) (f2 * f6);
        findViewById(R.id.splash_layout_empty_view_bottom).getLayoutParams().height = (int) (f6 * f5);
        i2 = (int) (f7 * f);
        findViewById(R.id.splash_layout_empty_view_left).getLayoutParams().width = i2;
        findViewById(R.id.splash_layout_empty_view_right).getLayoutParams().width = i2;
        if (o.f() || o.d((Context) this)) {
            ((TextView) findViewById(R.id.OOBE_Title)).setText(getString(R.string.app_name));
        }
    }

    private void O() {
        final LinearLayout linearLayout = (LinearLayout) findViewById(R.id.startButton);
        linearLayout.setOnClickListener(new j() {
            public void onClick(View view) {
                super.onClick(view);
                OobeActivity.this.startActivityForResult(new Intent(OobeActivity.this, SigninActivity.class), 5);
            }
        });
        final Button button = (Button) findViewById(R.id.skipButton);
        button.setOnClickListener(new j() {
            public void onClick(View view) {
                super.onClick(view);
                OobeActivity.this.startActivityForResult(new Intent(OobeActivity.this, SigninActivity.class), 5);
            }
        });
        final Button button2 = (Button) findViewById(R.id.nextButton);
        button2.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                OobeActivity.this.i.setCurrentItem((OobeActivity.this.i.getCurrentItem() + 1) % OobeActivity.this.i.getAdapter().a());
            }
        });
        if (this.c.isEmpty()) {
            for (int i = 0; i < this.d.length; i++) {
                this.c.add(this.d[i]);
                this.e.add(getString(this.h[i].intValue()));
                this.f.add(getString(this.g[i].intValue()));
            }
        }
        this.i = (ViewPager) findViewById(R.id.pager);
        this.i.setAdapter(new c(this, this.c, this.e, this.f, (int) (((double) o.m(this).x) * 0.8d)));
        this.i.setCurrentItem(this.j);
        final CircleIndicator circleIndicator = (CircleIndicator) findViewById(R.id.indicator);
        circleIndicator.a(this.i.getAdapter().a());
        circleIndicator.setSelectedItem(this.j);
        final int a = this.i.getAdapter().a() - 1;
        if (this.j == a) {
            button2.setVisibility(4);
            button.setVisibility(4);
            linearLayout.setVisibility(0);
        } else {
            button2.setVisibility(0);
            button.setVisibility(0);
            linearLayout.setVisibility(4);
        }
        this.i.a(new f() {
            public void a(int i, float f, int i2) {
            }

            public void b(int i) {
            }

            public void a(int i) {
                if (i == a) {
                    button2.setVisibility(4);
                    button.setVisibility(4);
                    linearLayout.setVisibility(0);
                } else {
                    button2.setVisibility(0);
                    button.setVisibility(0);
                    linearLayout.setVisibility(4);
                }
                OobeActivity.this.j = i;
                circleIndicator.setSelectedItem(i);
            }
        });
    }

    protected void onActivityResult(int i, int i2, Intent intent) {
        String str = b;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("onActivityResult: ");
        stringBuilder.append(i);
        stringBuilder.append(", ");
        stringBuilder.append(i2);
        Log.d(str, stringBuilder.toString());
        if (i != 5) {
            super.onActivityResult(i, i2, intent);
        } else if (i2 == -1) {
            finish();
        }
    }
}
