package com.samsung.android.lxd.fragment.a;

import android.content.Context;
import android.text.Spannable.Factory;
import android.text.TextUtils;
import android.text.style.ForegroundColorSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Filter;
import android.widget.Filter.FilterResults;
import android.widget.GridView;
import android.widget.TextView;
import com.samsung.android.lxd.R;
import com.samsung.android.lxd.a.o;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/* compiled from: FileListAdapter */
public class c extends ArrayAdapter<b> {
    private a a = new a(this, null);
    private List<b> b;
    private com.samsung.android.lxd.fragment.FileListFragment.b c;

    /* compiled from: FileListAdapter */
    /* renamed from: com.samsung.android.lxd.fragment.a.c$1 */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] a = new int[com.samsung.android.lxd.fragment.FileListFragment.b.values().length];

        static {
            try {
                a[com.samsung.android.lxd.fragment.FileListFragment.b.GRID.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
        }
    }

    /* compiled from: FileListAdapter */
    private class a extends Filter {
        private String b;

        /* synthetic */ a(c cVar, AnonymousClass1 anonymousClass1) {
            this();
        }

        private a() {
            this.b = null;
        }

        protected FilterResults performFiltering(CharSequence charSequence) {
            FilterResults filterResults = new FilterResults();
            List arrayList = new ArrayList(c.this.b);
            this.b = charSequence.toString().toUpperCase(Locale.getDefault());
            if (!TextUtils.isEmpty(charSequence)) {
                for (b bVar : c.this.b) {
                    if (!bVar.a().toUpperCase(Locale.getDefault()).contains(this.b)) {
                        arrayList.remove(bVar);
                    }
                }
            }
            filterResults.count = arrayList.size();
            filterResults.values = arrayList;
            return filterResults;
        }

        protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
            List list = (List) filterResults.values;
            c.this.clear();
            c.this.addAll(list);
            if (filterResults.count > 0 || charSequence.length() == 0) {
                c.this.notifyDataSetChanged();
            } else {
                c.this.notifyDataSetInvalidated();
            }
        }
    }

    /* compiled from: FileListAdapter */
    public static class b implements Comparable<b> {
        private String a;
        private String b;
        private String c;
        private String d;

        public b(String str, String str2, String str3, String str4) {
            this.a = str;
            this.b = str2;
            this.c = str3;
            this.d = str4;
        }

        /* renamed from: a */
        public int compareTo(b bVar) {
            if (this.a != null) {
                return this.a.toLowerCase().compareTo(this.a.toLowerCase());
            }
            throw new IllegalArgumentException();
        }

        public String a() {
            return this.a;
        }

        public String b() {
            return this.b;
        }
    }

    /* compiled from: FileListAdapter */
    private class c {
        private TextView b;
        private TextView c;
        private TextView d;
        private TextView e;

        private c() {
        }

        /* synthetic */ c(c cVar, AnonymousClass1 anonymousClass1) {
            this();
        }
    }

    public c(Context context, List<b> list) {
        super(context, 0, new ArrayList(list));
        this.b = new ArrayList(list);
        this.c = null;
    }

    public c a(com.samsung.android.lxd.fragment.FileListFragment.b bVar) {
        this.c = bVar;
        return this;
    }

    public void a(List<b> list) {
        this.b = new ArrayList(list);
    }

    private CharSequence a(CharSequence charSequence) {
        String toUpperCase = charSequence.toString().toUpperCase();
        String toUpperCase2 = !TextUtils.isEmpty(this.a.b) ? this.a.b.toUpperCase() : null;
        if (TextUtils.isEmpty(toUpperCase) || TextUtils.isEmpty(toUpperCase2) || !toUpperCase.contains(toUpperCase2)) {
            return charSequence;
        }
        int indexOf = toUpperCase.indexOf(toUpperCase2);
        int length = toUpperCase2.length() + indexOf;
        charSequence = Factory.getInstance().newSpannable(charSequence);
        charSequence.setSpan(new ForegroundColorSpan(getContext().getResources().getColor(R.color.LightTheme.primary, null)), indexOf, length, 33);
        return charSequence;
    }

    public View getView(int i, View view, ViewGroup viewGroup) {
        c cVar;
        View inflate;
        if (view == null) {
            cVar = new c(this, null);
            if (AnonymousClass1.a[this.c.ordinal()] != 1) {
                inflate = LayoutInflater.from(getContext()).inflate(o.e(getContext()) ? R.layout.item_list_row_fragment_file_list : R.layout.item_list_row_fragment_file_list_land, viewGroup, false);
                a(inflate);
            } else {
                View inflate2 = LayoutInflater.from(getContext()).inflate(R.layout.item_grid_row_fragment_file_list, viewGroup, false);
                a(inflate2, viewGroup);
                inflate = inflate2;
            }
            cVar.b = (TextView) inflate.findViewById(R.id.name_text);
            cVar.c = (TextView) inflate.findViewById(R.id.path_text);
            cVar.d = (TextView) inflate.findViewById(R.id.date_text);
            cVar.e = (TextView) inflate.findViewById(R.id.size_text);
            inflate.setTag(cVar);
        } else {
            inflate = view;
            cVar = (c) view.getTag();
        }
        b bVar = (b) getItem(i);
        if (bVar != null) {
            cVar.b.setText(bVar.a);
            cVar.d.setText(bVar.c);
            cVar.e.setText(bVar.d);
            cVar.c.setText(o.a(o.a(getContext(), bVar.b)));
            int i2 = 8;
            if (com.samsung.android.lxd.fragment.FileListFragment.b.GRID.equals(this.c)) {
                cVar.c.setVisibility(8);
                cVar.d.setVisibility(8);
                cVar.e.setVisibility(8);
            } else {
                TextView d = cVar.c;
                if (!o.e(getContext())) {
                    i2 = 0;
                }
                d.setVisibility(i2);
                cVar.d.setVisibility(0);
                cVar.e.setVisibility(0);
            }
        }
        cVar.b.setText(a(cVar.b.getText()));
        return inflate;
    }

    public Filter getFilter() {
        return this.a;
    }

    private void a(View view, ViewGroup viewGroup) {
        int i;
        int i2;
        int i3;
        int i4 = o.m(getContext()).y;
        int i5 = o.m(getContext()).x;
        double d;
        if (o.e(getContext())) {
            if (o.k(getContext())) {
                i = (int) (((double) i5) * 0.21d);
                d = (double) i4;
                i2 = (int) (0.105d * d);
                i3 = (int) (0.073d * d);
                i4 = (int) (d * 0.031d);
            } else {
                i = (int) (((double) i5) * 0.119d);
                d = (double) i4;
                i2 = (int) (0.25d * d);
                i4 = (int) (d * 0.074d);
                i3 = (int) (0.176d * d);
            }
        } else if (!o.f(getContext())) {
            i = (int) (((double) i5) * 0.068d);
            d = (double) i4;
            i2 = (int) (0.109d * d);
            i3 = (int) (0.074d * d);
            i4 = (int) (d * 0.033d);
        } else if (o.k(getContext())) {
            i = (int) (((double) i5) * 0.116d);
            d = (double) i4;
            i2 = (int) (0.0714d * d);
            i3 = (int) (0.051d * d);
            i4 = (int) (d * 0.0202d);
        } else {
            i = (int) (((double) i5) * 0.073d);
            d = (double) i4;
            i2 = (int) (0.116d * d);
            i3 = (int) (0.08d * d);
            i4 = (int) (d * 0.036d);
        }
        ((GridView) viewGroup).setColumnWidth(i);
        view.findViewById(R.id.grid_item).getLayoutParams().height = i2;
        view.findViewById(R.id.icon_layout).getLayoutParams().height = i3;
        view.findViewById(R.id.text_layout).getLayoutParams().height = i4;
    }

    private void a(View view) {
        if (o.e(getContext())) {
            view.findViewById(R.id.above_title_margin).getLayoutParams().height = (int) (((double) o.m(getContext()).y) * 0.016d);
            view.findViewById(R.id.below_title_margin).getLayoutParams().height = (int) (((double) o.m(getContext()).y) * 0.008d);
            view.findViewById(R.id.below_date_margin).getLayoutParams().height = (int) (((double) o.m(getContext()).y) * 0.016d);
        } else if (o.k(getContext())) {
            view.findViewById(R.id.above_list_margin).getLayoutParams().height = (int) (((double) o.m(getContext()).y) * 0.016d);
            view.findViewById(R.id.below_list_margin).getLayoutParams().height = (int) (((double) o.m(getContext()).y) * 0.016d);
        } else {
            view.findViewById(R.id.above_list_margin).getLayoutParams().height = (int) (((double) o.m(getContext()).y) * 0.025d);
            view.findViewById(R.id.below_list_margin).getLayoutParams().height = (int) (((double) o.m(getContext()).y) * 0.025d);
        }
    }
}
