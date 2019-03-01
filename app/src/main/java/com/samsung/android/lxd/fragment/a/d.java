package com.samsung.android.lxd.fragment.a;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.samsung.android.lxd.R;
import com.samsung.android.lxd.a.j;
import java.io.File;
import java.util.List;

/* compiled from: ImageListAdapter */
public class d extends ArrayAdapter<a> {
    private Context a;
    private int b;
    private b c;

    /* compiled from: ImageListAdapter */
    public static class a {
        private final String a;
        private final String b;
        private final String c;

        public a(String str, String str2, String str3) {
            this.a = str;
            this.b = str2;
            this.c = str3;
        }
    }

    /* compiled from: ImageListAdapter */
    public interface b {
        void a(int i);
    }

    /* compiled from: ImageListAdapter */
    private class c {
        private TextView b;
        private TextView c;

        private c() {
        }

        /* synthetic */ c(d dVar, AnonymousClass1 anonymousClass1) {
            this();
        }
    }

    public d(Context context, List<a> list, int i, b bVar) {
        super(context, 0, list);
        this.a = context;
        this.b = i;
        this.c = bVar;
    }

    public View getView(final int i, View view, ViewGroup viewGroup) {
        c cVar;
        View inflate;
        if (view == null) {
            cVar = new c(this, null);
            inflate = LayoutInflater.from(getContext()).inflate(R.layout.item_list_row_fragment_image_list, viewGroup, false);
            cVar.b = (TextView) inflate.findViewById(R.id.title);
            cVar.c = (TextView) inflate.findViewById(R.id.desc);
            inflate.setTag(cVar);
        } else {
            inflate = view;
            cVar = (c) view.getTag();
        }
        a aVar = (a) getItem(i);
        if (aVar != null) {
            cVar.b.setText(aVar.a);
            LinearLayout linearLayout = (LinearLayout) inflate.findViewById(R.id.faultImageLayout);
            if (new File(aVar.c).isFile()) {
                linearLayout.setVisibility(8);
                cVar.c.setText(aVar.b);
                cVar.c.setTextColor(this.a.getResources().getColor(R.color.LightTheme.description_secondary_text_control_off, null));
            } else {
                linearLayout.setVisibility(0);
                cVar.c.setText(R.string.fault_image_title);
                cVar.c.setTextColor(this.a.getResources().getColor(R.color.LightTheme.error, null));
                ((ImageView) linearLayout.findViewById(R.id.imageButton)).setOnClickListener(new j() {
                    public void onClick(View view) {
                        super.onClick(view);
                        d.this.c.a(i);
                    }
                });
            }
        }
        if (i == this.b) {
            inflate.setBackgroundColor(this.a.getResources().getColor(R.color.LightTheme.card_view_background, null));
        } else {
            inflate.setBackgroundColor(0);
        }
        return inflate;
    }

    public void a(int i) {
        this.b = i;
    }
}
