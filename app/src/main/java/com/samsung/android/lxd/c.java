package com.samsung.android.lxd;

import android.content.Context;
import android.support.v4.view.q;
import android.text.SpannableString;
import android.text.style.ImageSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.TextView.BufferType;
import com.samsung.android.lxd.a.o;
import java.util.ArrayList;

/* compiled from: CustomPagerAdapter */
public class c extends q {
    private ArrayList<Integer> a;
    private ArrayList<String> b;
    private ArrayList<String> c;
    private LayoutInflater d;
    private Context e;
    private float f;

    public c(Context context, ArrayList<Integer> arrayList, ArrayList<String> arrayList2, ArrayList<String> arrayList3, int i) {
        this.a = arrayList;
        this.b = arrayList2;
        this.c = arrayList3;
        this.d = LayoutInflater.from(context);
        this.e = context;
        this.f = (float) i;
    }

    public void a(ViewGroup viewGroup, int i, Object obj) {
        viewGroup.removeView((View) obj);
    }

    public int a() {
        return this.a.size();
    }

    public Object a(ViewGroup viewGroup, int i) {
        View inflate;
        float f;
        int i2 = o.e(this.e) ? o.k(this.e) ? R.layout.custom_slide : R.layout.custom_slide_land : o.k(this.e) ? R.layout.custom_slide_tablet : R.layout.custom_slide_tablet_land;
        float f2 = 0.04f;
        if (o.k(this.e)) {
            inflate = this.d.inflate(i2, viewGroup, false);
            if (!o.f(this.e)) {
                boolean d = o.d(this.e);
            }
            f = (o.f(this.e) || o.d(this.e)) ? 0.02f : 0.04f;
            f2 = f;
            f = 0.04f;
        } else {
            inflate = this.d.inflate(i2, viewGroup, false);
            f = (o.f(this.e) || o.d(this.e)) ? 0.06f : 0.04f;
            if (o.f(this.e) || o.d(this.e)) {
                f2 = 0.03f;
            }
        }
        ((ImageView) inflate.findViewById(R.id.image)).setImageResource(((Integer) this.a.get(i)).intValue());
        viewGroup.addView(inflate, 0);
        TextView textView = (TextView) inflate.findViewById(R.id.carrousel_title);
        textView.setText((CharSequence) this.c.get(i));
        TextView textView2 = (TextView) inflate.findViewById(R.id.carrousel_text);
        if (i == 0) {
            textView2.setText((CharSequence) this.b.get(i));
            ImageSpan imageSpan = new ImageSpan(this.e, R.drawable.ic_add_image_oobe);
            CharSequence spannableString = new SpannableString(textView2.getText());
            spannableString.setSpan(imageSpan, 13, 14, 0);
            textView2.setText(spannableString, BufferType.SPANNABLE);
        } else {
            textView2.setText((CharSequence) this.b.get(i));
        }
        ScrollView scrollView = (ScrollView) inflate.findViewById(R.id.pager_scroll);
        scrollView.getLayoutParams().width = (int) this.f;
        scrollView.setScrollBarStyle(33554432);
        textView2.getLayoutParams().width = (int) (this.f - 50.0f);
        textView.getLayoutParams().width = (int) (this.f - 50.0f);
        float f3 = (float) o.m(this.e).y;
        inflate.findViewById(R.id.custom_slide_empty_view_under_image).getLayoutParams().height = (int) (f * f3);
        inflate.findViewById(R.id.custom_slide_empty_view_under_title).getLayoutParams().height = (int) (f3 * f2);
        return inflate;
    }

    public boolean a(View view, Object obj) {
        return view.equals(obj);
    }
}
