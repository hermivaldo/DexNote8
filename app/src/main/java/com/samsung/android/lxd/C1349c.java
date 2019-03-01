package com.samsung.android.lxd;

import android.content.Context;
import android.support.v4.view.C0399q;
import android.text.SpannableString;
import android.text.style.ImageSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.TextView.BufferType;
import com.samsung.android.lxd.p064a.C0877o;
import java.util.ArrayList;

/* compiled from: CustomPagerAdapter */
/* renamed from: com.samsung.android.lxd.c */
public class C1349c extends C0399q {
    /* renamed from: a */
    private ArrayList<Integer> f4438a;
    /* renamed from: b */
    private ArrayList<String> f4439b;
    /* renamed from: c */
    private ArrayList<String> f4440c;
    /* renamed from: d */
    private LayoutInflater f4441d;
    /* renamed from: e */
    private Context f4442e;
    /* renamed from: f */
    private float f4443f;

    public C1349c(Context context, ArrayList<Integer> arrayList, ArrayList<String> arrayList2, ArrayList<String> arrayList3, int i) {
        this.f4438a = arrayList;
        this.f4439b = arrayList2;
        this.f4440c = arrayList3;
        this.f4441d = LayoutInflater.from(context);
        this.f4442e = context;
        this.f4443f = (float) i;
    }

    /* renamed from: a */
    public void mo729a(ViewGroup viewGroup, int i, Object obj) {
        viewGroup.removeView((View) obj);
    }

    /* renamed from: a */
    public int mo727a() {
        return this.f4438a.size();
    }

    /* renamed from: a */
    public Object mo728a(ViewGroup viewGroup, int i) {
        View inflate;
        float f;
        int i2 = C0877o.m3504e(this.f4442e) ? C0877o.m3522k(this.f4442e) ? R.layout.custom_slide : R.layout.custom_slide_land : C0877o.m3522k(this.f4442e) ? R.layout.custom_slide_tablet : R.layout.custom_slide_tablet_land;
        float f2 = 0.04f;
        if (C0877o.m3522k(this.f4442e)) {
            inflate = this.f4441d.inflate(i2, viewGroup, false);
            if (!C0877o.m3508f(this.f4442e)) {
                boolean d = C0877o.m3499d(this.f4442e);
            }
            if (!C0877o.m3508f(this.f4442e)) {
                if (!C0877o.m3499d(this.f4442e)) {
                    f = 0.04f;
                    f2 = f;
                    f = 0.04f;
                }
            }
            f = 0.02f;
            f2 = f;
            f = 0.04f;
        } else {
            inflate = this.f4441d.inflate(i2, viewGroup, false);
            if (!C0877o.m3508f(this.f4442e)) {
                if (!C0877o.m3499d(this.f4442e)) {
                    f = 0.04f;
                    if (C0877o.m3508f(this.f4442e) || C0877o.m3499d(this.f4442e)) {
                        f2 = 0.03f;
                    }
                }
            }
            f = 0.06f;
            f2 = 0.03f;
        }
        ((ImageView) inflate.findViewById(R.id.image)).setImageResource(((Integer) this.f4438a.get(i)).intValue());
        viewGroup.addView(inflate, 0);
        TextView textView = (TextView) inflate.findViewById(R.id.carrousel_title);
        textView.setText((CharSequence) this.f4440c.get(i));
        TextView textView2 = (TextView) inflate.findViewById(R.id.carrousel_text);
        if (i == 0) {
            textView2.setText((CharSequence) this.f4439b.get(i));
            i = new ImageSpan(this.f4442e, R.drawable.ic_add_image_oobe);
            CharSequence spannableString = new SpannableString(textView2.getText());
            spannableString.setSpan(i, 13, 14, 0);
            textView2.setText(spannableString, BufferType.SPANNABLE);
        } else {
            textView2.setText((CharSequence) this.f4439b.get(i));
        }
        ScrollView scrollView = (ScrollView) inflate.findViewById(R.id.pager_scroll);
        scrollView.getLayoutParams().width = (int) this.f4443f;
        scrollView.setScrollBarStyle(33554432);
        textView2.getLayoutParams().width = (int) (this.f4443f - 50.0f);
        textView.getLayoutParams().width = (int) (this.f4443f - 1112014848);
        float f3 = (float) C0877o.m3526m(this.f4442e).y;
        inflate.findViewById(R.id.custom_slide_empty_view_under_image).getLayoutParams().height = (int) (f * f3);
        inflate.findViewById(R.id.custom_slide_empty_view_under_title).getLayoutParams().height = (int) (f3 * f2);
        return inflate;
    }

    /* renamed from: a */
    public boolean mo730a(View view, Object obj) {
        return view.equals(obj);
    }
}
