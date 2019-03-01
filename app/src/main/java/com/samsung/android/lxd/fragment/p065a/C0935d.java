package com.samsung.android.lxd.fragment.p065a;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.samsung.android.lxd.R;
import com.samsung.android.lxd.p064a.C0869j;
import java.io.File;
import java.util.List;

/* compiled from: ImageListAdapter */
/* renamed from: com.samsung.android.lxd.fragment.a.d */
public class C0935d extends ArrayAdapter<C0932a> {
    /* renamed from: a */
    private Context f2943a;
    /* renamed from: b */
    private int f2944b;
    /* renamed from: c */
    private C0933b f2945c;

    /* compiled from: ImageListAdapter */
    /* renamed from: com.samsung.android.lxd.fragment.a.d$a */
    public static class C0932a {
        /* renamed from: a */
        private final String f2937a;
        /* renamed from: b */
        private final String f2938b;
        /* renamed from: c */
        private final String f2939c;

        public C0932a(String str, String str2, String str3) {
            this.f2937a = str;
            this.f2938b = str2;
            this.f2939c = str3;
        }
    }

    /* compiled from: ImageListAdapter */
    /* renamed from: com.samsung.android.lxd.fragment.a.d$b */
    public interface C0933b {
        /* renamed from: a */
        void mo754a(int i);
    }

    /* compiled from: ImageListAdapter */
    /* renamed from: com.samsung.android.lxd.fragment.a.d$c */
    private class C0934c {
        /* renamed from: a */
        final /* synthetic */ C0935d f2940a;
        /* renamed from: b */
        private TextView f2941b;
        /* renamed from: c */
        private TextView f2942c;

        private C0934c(C0935d c0935d) {
            this.f2940a = c0935d;
        }
    }

    public C0935d(Context context, List<C0932a> list, int i, C0933b c0933b) {
        super(context, 0, list);
        this.f2943a = context;
        this.f2944b = i;
        this.f2945c = c0933b;
    }

    public View getView(final int i, View view, ViewGroup viewGroup) {
        if (view == null) {
            view = new C0934c();
            viewGroup = LayoutInflater.from(getContext()).inflate(R.layout.item_list_row_fragment_image_list, viewGroup, false);
            view.f2941b = (TextView) viewGroup.findViewById(R.id.title);
            view.f2942c = (TextView) viewGroup.findViewById(R.id.desc);
            viewGroup.setTag(view);
        } else {
            viewGroup = view;
            view = (C0934c) view.getTag();
        }
        C0932a c0932a = (C0932a) getItem(i);
        if (c0932a != null) {
            view.f2941b.setText(c0932a.f2937a);
            LinearLayout linearLayout = (LinearLayout) viewGroup.findViewById(R.id.faultImageLayout);
            if (new File(c0932a.f2939c).isFile()) {
                linearLayout.setVisibility(8);
                view.f2942c.setText(c0932a.f2938b);
                view.f2942c.setTextColor(this.f2943a.getResources().getColor(R.color.LightTheme.description_secondary_text_control_off, null));
            } else {
                linearLayout.setVisibility(0);
                view.f2942c.setText(R.string.fault_image_title);
                view.f2942c.setTextColor(this.f2943a.getResources().getColor(R.color.LightTheme.error, null));
                ((ImageView) linearLayout.findViewById(R.id.imageButton)).setOnClickListener(new C0869j(this) {
                    /* renamed from: b */
                    final /* synthetic */ C0935d f4504b;

                    public void onClick(View view) {
                        super.onClick(view);
                        this.f4504b.f2945c.mo754a(i);
                    }
                });
            }
        }
        if (i == this.f2944b) {
            viewGroup.setBackgroundColor(this.f2943a.getResources().getColor(R.color.LightTheme.card_view_background, null));
        } else {
            viewGroup.setBackgroundColor(0);
        }
        return viewGroup;
    }

    /* renamed from: a */
    public void m3751a(int i) {
        this.f2944b = i;
    }
}
