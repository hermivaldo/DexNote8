package com.samsung.android.lxd;

import android.content.Context;
import android.util.AttributeSet;
import android.view.ViewGroup.LayoutParams;
import android.widget.ImageView;
import android.widget.LinearLayout;
import com.samsung.android.lxd.a.o;

public class CircleIndicator extends LinearLayout {
    private Context a;
    private ImageView[] b;
    private int c;
    private int d;

    public CircleIndicator(Context context) {
        super(context);
        this.a = context;
    }

    public CircleIndicator(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.a = context;
    }

    public void a(int i) {
        removeAllViews();
        this.b = new ImageView[i];
        this.c = R.drawable.custom_circle_indicator_dim;
        this.d = R.drawable.custom_circle_indicator;
        for (int i2 = 0; i2 < i; i2++) {
            this.b[i2] = new ImageView(this.a);
            LayoutParams layoutParams = new LinearLayout.LayoutParams(-2, -2);
            layoutParams.topMargin = 0;
            layoutParams.bottomMargin = 0;
            layoutParams.leftMargin = 0;
            layoutParams.width = (int) o.a(getContext(), 13.0f);
            layoutParams.height = (int) o.a(getContext(), 13.0f);
            if (i2 == i - 1) {
                layoutParams.rightMargin = 0;
            } else {
                layoutParams.rightMargin = (int) o.a(this.a, 9.0f);
            }
            layoutParams.gravity = 17;
            this.b[i2].setLayoutParams(layoutParams);
            this.b[i2].setImageResource(this.c);
            this.b[i2].setTag(this.b[i2].getId(), Boolean.valueOf(false));
            addView(this.b[i2]);
        }
        setSelectedItem(0);
    }

    public void setSelectedItem(int i) {
        for (int i2 = 0; i2 < this.b.length; i2++) {
            if (i2 == i) {
                this.b[i2].setImageResource(this.d);
            } else {
                this.b[i2].setImageResource(this.c);
            }
        }
    }
}
