package com.samsung.android.lxd.fragment.p065a;

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
import com.samsung.android.lxd.fragment.FileListFragment.C0912b;
import com.samsung.android.lxd.p064a.C0877o;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/* compiled from: FileListAdapter */
/* renamed from: com.samsung.android.lxd.fragment.a.c */
public class C0931c extends ArrayAdapter<C0929b> {
    /* renamed from: a */
    private C0928a f2934a = new C0928a();
    /* renamed from: b */
    private List<C0929b> f2935b;
    /* renamed from: c */
    private C0912b f2936c;

    /* compiled from: FileListAdapter */
    /* renamed from: com.samsung.android.lxd.fragment.a.c$1 */
    static /* synthetic */ class C09271 {
        /* renamed from: a */
        static final /* synthetic */ int[] f2922a = new int[C0912b.values().length];

        static {
            /* JADX: method processing error */
/*
Error: java.lang.NullPointerException
	at jadx.core.dex.visitors.regions.ProcessTryCatchRegions.searchTryCatchDominators(ProcessTryCatchRegions.java:75)
	at jadx.core.dex.visitors.regions.ProcessTryCatchRegions.process(ProcessTryCatchRegions.java:45)
	at jadx.core.dex.visitors.regions.RegionMakerVisitor.postProcessRegions(RegionMakerVisitor.java:63)
	at jadx.core.dex.visitors.regions.RegionMakerVisitor.visit(RegionMakerVisitor.java:58)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:31)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:17)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:14)
	at jadx.core.ProcessClass.process(ProcessClass.java:34)
	at jadx.core.ProcessClass.processDependencies(ProcessClass.java:56)
	at jadx.core.ProcessClass.process(ProcessClass.java:39)
	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:282)
	at jadx.api.JavaClass.decompile(JavaClass.java:62)
	at jadx.api.JadxDecompiler.lambda$appendSourcesSave$0(JadxDecompiler.java:200)
	at jadx.api.JadxDecompiler$$Lambda$8/1201484275.run(Unknown Source)
*/
            /*
            r0 = com.samsung.android.lxd.fragment.FileListFragment.C0912b.values();
            r0 = r0.length;
            r0 = new int[r0];
            f2922a = r0;
            r0 = f2922a;	 Catch:{ NoSuchFieldError -> 0x0014 }
            r1 = com.samsung.android.lxd.fragment.FileListFragment.C0912b.GRID;	 Catch:{ NoSuchFieldError -> 0x0014 }
            r1 = r1.ordinal();	 Catch:{ NoSuchFieldError -> 0x0014 }
            r2 = 1;	 Catch:{ NoSuchFieldError -> 0x0014 }
            r0[r1] = r2;	 Catch:{ NoSuchFieldError -> 0x0014 }
        L_0x0014:
            return;
            */
            throw new UnsupportedOperationException("Method not decompiled: com.samsung.android.lxd.fragment.a.c.1.<clinit>():void");
        }
    }

    /* compiled from: FileListAdapter */
    /* renamed from: com.samsung.android.lxd.fragment.a.c$a */
    private class C0928a extends Filter {
        /* renamed from: a */
        final /* synthetic */ C0931c f2923a;
        /* renamed from: b */
        private String f2924b;

        private C0928a(C0931c c0931c) {
            this.f2923a = c0931c;
            this.f2924b = null;
        }

        protected FilterResults performFiltering(CharSequence charSequence) {
            FilterResults filterResults = new FilterResults();
            List arrayList = new ArrayList(this.f2923a.f2935b);
            this.f2924b = charSequence.toString().toUpperCase(Locale.getDefault());
            if (TextUtils.isEmpty(charSequence) == null) {
                for (C0929b c0929b : this.f2923a.f2935b) {
                    if (!c0929b.m3726a().toUpperCase(Locale.getDefault()).contains(this.f2924b)) {
                        arrayList.remove(c0929b);
                    }
                }
            }
            filterResults.count = arrayList.size();
            filterResults.values = arrayList;
            return filterResults;
        }

        protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
            List list = (List) filterResults.values;
            this.f2923a.clear();
            this.f2923a.addAll(list);
            if (filterResults.count <= null) {
                if (charSequence.length() != null) {
                    this.f2923a.notifyDataSetInvalidated();
                    return;
                }
            }
            this.f2923a.notifyDataSetChanged();
        }
    }

    /* compiled from: FileListAdapter */
    /* renamed from: com.samsung.android.lxd.fragment.a.c$b */
    public static class C0929b implements Comparable<C0929b> {
        /* renamed from: a */
        private String f2925a;
        /* renamed from: b */
        private String f2926b;
        /* renamed from: c */
        private String f2927c;
        /* renamed from: d */
        private String f2928d;

        public /* synthetic */ int compareTo(Object obj) {
            return m3725a((C0929b) obj);
        }

        public C0929b(String str, String str2, String str3, String str4) {
            this.f2925a = str;
            this.f2926b = str2;
            this.f2927c = str3;
            this.f2928d = str4;
        }

        /* renamed from: a */
        public int m3725a(C0929b c0929b) {
            if (this.f2925a != null) {
                return this.f2925a.toLowerCase().compareTo(this.f2925a.toLowerCase());
            }
            throw new IllegalArgumentException();
        }

        /* renamed from: a */
        public String m3726a() {
            return this.f2925a;
        }

        /* renamed from: b */
        public String m3727b() {
            return this.f2926b;
        }
    }

    /* compiled from: FileListAdapter */
    /* renamed from: com.samsung.android.lxd.fragment.a.c$c */
    private class C0930c {
        /* renamed from: a */
        final /* synthetic */ C0931c f2929a;
        /* renamed from: b */
        private TextView f2930b;
        /* renamed from: c */
        private TextView f2931c;
        /* renamed from: d */
        private TextView f2932d;
        /* renamed from: e */
        private TextView f2933e;

        private C0930c(C0931c c0931c) {
            this.f2929a = c0931c;
        }
    }

    public C0931c(Context context, List<C0929b> list) {
        super(context, 0, new ArrayList(list));
        this.f2935b = new ArrayList(list);
        this.f2936c = null;
    }

    /* renamed from: a */
    public C0931c m3740a(C0912b c0912b) {
        this.f2936c = c0912b;
        return this;
    }

    /* renamed from: a */
    public void m3741a(List<C0929b> list) {
        this.f2935b = new ArrayList(list);
    }

    /* renamed from: a */
    private CharSequence m3736a(CharSequence charSequence) {
        String toUpperCase = charSequence.toString().toUpperCase();
        String toUpperCase2 = !TextUtils.isEmpty(this.f2934a.f2924b) ? this.f2934a.f2924b.toUpperCase() : null;
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
        if (view == null) {
            view = new C0930c();
            if (C09271.f2922a[this.f2936c.ordinal()] != 1) {
                viewGroup = LayoutInflater.from(getContext()).inflate(C0877o.m3504e(getContext()) ? R.layout.item_list_row_fragment_file_list : R.layout.item_list_row_fragment_file_list_land, viewGroup, false);
                m3738a((View) viewGroup);
            } else {
                View inflate = LayoutInflater.from(getContext()).inflate(R.layout.item_grid_row_fragment_file_list, viewGroup, false);
                m3739a(inflate, viewGroup);
                viewGroup = inflate;
            }
            view.f2930b = (TextView) viewGroup.findViewById(R.id.name_text);
            view.f2931c = (TextView) viewGroup.findViewById(R.id.path_text);
            view.f2932d = (TextView) viewGroup.findViewById(R.id.date_text);
            view.f2933e = (TextView) viewGroup.findViewById(R.id.size_text);
            viewGroup.setTag(view);
        } else {
            viewGroup = view;
            view = (C0930c) view.getTag();
        }
        C0929b c0929b = (C0929b) getItem(i);
        if (c0929b != null) {
            view.f2930b.setText(c0929b.f2925a);
            view.f2932d.setText(c0929b.f2927c);
            view.f2933e.setText(c0929b.f2928d);
            view.f2931c.setText(C0877o.m3462a(C0877o.m3461a(getContext(), c0929b.f2926b)));
            int i2 = 8;
            if (C0912b.GRID.equals(this.f2936c) != 0) {
                view.f2931c.setVisibility(8);
                view.f2932d.setVisibility(8);
                view.f2933e.setVisibility(8);
            } else {
                i = view.f2931c;
                if (!C0877o.m3504e(getContext())) {
                    i2 = 0;
                }
                i.setVisibility(i2);
                view.f2932d.setVisibility(0);
                view.f2933e.setVisibility(0);
            }
        }
        view.f2930b.setText(m3736a(view.f2930b.getText()));
        return viewGroup;
    }

    public Filter getFilter() {
        return this.f2934a;
    }

    /* renamed from: a */
    private void m3739a(View view, ViewGroup viewGroup) {
        int i;
        int i2;
        int i3;
        int i4 = C0877o.m3526m(getContext()).y;
        int i5 = C0877o.m3526m(getContext()).x;
        double d;
        if (C0877o.m3504e(getContext())) {
            if (C0877o.m3522k(getContext())) {
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
        } else if (!C0877o.m3508f(getContext())) {
            i = (int) (((double) i5) * 0.068d);
            d = (double) i4;
            i2 = (int) (0.109d * d);
            i3 = (int) (0.074d * d);
            i4 = (int) (d * 0.033d);
        } else if (C0877o.m3522k(getContext())) {
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

    /* renamed from: a */
    private void m3738a(View view) {
        if (C0877o.m3504e(getContext())) {
            view.findViewById(R.id.above_title_margin).getLayoutParams().height = (int) (((double) C0877o.m3526m(getContext()).y) * 0.016d);
            view.findViewById(R.id.below_title_margin).getLayoutParams().height = (int) (((double) C0877o.m3526m(getContext()).y) * 0.008d);
            view.findViewById(R.id.below_date_margin).getLayoutParams().height = (int) (((double) C0877o.m3526m(getContext()).y) * 0.016d);
        } else if (C0877o.m3522k(getContext())) {
            view.findViewById(R.id.above_list_margin).getLayoutParams().height = (int) (((double) C0877o.m3526m(getContext()).y) * 0.016d);
            view.findViewById(R.id.below_list_margin).getLayoutParams().height = (int) (((double) C0877o.m3526m(getContext()).y) * 0.016d);
        } else {
            view.findViewById(R.id.above_list_margin).getLayoutParams().height = (int) (((double) C0877o.m3526m(getContext()).y) * 0.025d);
            view.findViewById(R.id.below_list_margin).getLayoutParams().height = (int) (((double) C0877o.m3526m(getContext()).y) * 0.025d);
        }
    }
}
