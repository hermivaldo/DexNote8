package com.samsung.android.lxd.fragment;

import android.app.Fragment;
import android.graphics.Canvas;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.h;
import android.support.v7.widget.RecyclerView.t;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.samsung.android.lxd.R;
import com.samsung.android.lxd.processor.utils.log.Log;
import java.util.ArrayList;
import java.util.List;

public class CardViewFragment extends Fragment {
    private static final String a = "CardViewFragment";
    private a b = null;
    private int c;
    private com.samsung.android.lxd.fragment.a.a d = null;
    private com.samsung.android.lxd.fragment.a.b e = null;
    private b f = b.TEXT_ONLY;
    private ArrayList<String> g = new ArrayList();
    private ArrayList<String> h = new ArrayList();
    private c i = null;
    private String j;
    private int k;
    private boolean l = false;
    private boolean m = false;
    private d n;
    private int o;
    private String p;
    private ArrayList<String> q = new ArrayList();

    public interface a {
        void a(int i);
    }

    public enum b {
        TEXT_ONLY,
        WITH_UNMOUNT_IMAGE,
        WITH_TEXT_BUTTON_PHONE,
        WITH_TEXT_BUTTON,
        NOTIFY_WITH_PENDING,
        NOTIFY_WITH_PROGRESS,
        NOTIFY_WITH_RECT_BUTTON,
        NOTIFY_WITH_ONE_BUTTON,
        NOTIFY_WITH_TWO_BUTTON,
        NOTIFY_WITH_TWO_BUTTON_DESC
    }

    public class c {
        public String a;
        public String b;
        public int c;

        public c(String str, String str2, int i) {
            this.a = str;
            this.b = str2;
            this.c = i;
        }
    }

    public interface d {
        void a();

        void b();
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        return layoutInflater.inflate(R.layout.fragment_card_view, viewGroup, false);
    }

    public void onActivityCreated(Bundle bundle) {
        super.onActivityCreated(bundle);
        b();
    }

    public void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
    }

    protected RecyclerView a() {
        return getView() != null ? (RecyclerView) getView().findViewById(R.id.cardViewItem) : null;
    }

    public CardViewFragment a(b bVar) {
        this.f = bVar;
        return this;
    }

    public CardViewFragment a(ArrayList<String> arrayList) {
        return a(arrayList, null);
    }

    public CardViewFragment a(ArrayList<String> arrayList, List<String> list) {
        return a(arrayList, list, null);
    }

    public CardViewFragment a(ArrayList<String> arrayList, List<String> list, ArrayList<String> arrayList2) {
        this.q.clear();
        this.q.addAll(arrayList);
        if (!(list == null || list.isEmpty())) {
            this.g.clear();
            this.g.addAll(list);
        }
        this.h.clear();
        if (!(arrayList2 == null || arrayList2.isEmpty())) {
            this.h.addAll(arrayList2);
        }
        return this;
    }

    public CardViewFragment a(int i) {
        this.c = i;
        return this;
    }

    public CardViewFragment b(int i) {
        this.o = i;
        return this;
    }

    public CardViewFragment a(a aVar) {
        this.b = aVar;
        return this;
    }

    public CardViewFragment a(d dVar) {
        this.n = dVar;
        return this;
    }

    public CardViewFragment a(boolean z) {
        this.l = z;
        return this;
    }

    public CardViewFragment b(boolean z) {
        this.m = z;
        return this;
    }

    public CardViewFragment c(int i) {
        this.k = i;
        return this;
    }

    public CardViewFragment a(String str) {
        this.j = str;
        return this;
    }

    public CardViewFragment b(String str) {
        this.p = str;
        return this;
    }

    public CardViewFragment b() {
        if (getView() == null) {
            Log.e(a, "update : View had not been created yet!");
            return this;
        }
        a().setLayoutManager(new LinearLayoutManager(getContext()));
        a().setFocusable(this.l);
        a().setHasFixedSize(this.m);
        if (this.e != null) {
            while (a().getItemDecorationCount() > 0) {
                h b = a().b(0);
                if (b instanceof android.support.v7.widget.a.a) {
                    ((android.support.v7.widget.a.a) b).a(null);
                } else {
                    a().c(0);
                }
            }
            new android.support.v7.widget.a.a(this.e).a(a());
            a().a(new h() {
                public void b(Canvas canvas, RecyclerView recyclerView, t tVar) {
                    CardViewFragment.this.e.a(canvas, CardViewFragment.this.getContext());
                }
            });
        }
        if (this.f == b.NOTIFY_WITH_PENDING || this.f == b.NOTIFY_WITH_PROGRESS) {
            this.i = new c(this.p, this.j, this.k);
        }
        this.d = new com.samsung.android.lxd.fragment.a.a(getContext(), getActivity(), this.g, this.q, this.f, this.b, this.c, this.i, this.h, this.o, this.n);
        a().setAdapter(this.d);
        a().getAdapter().f();
        return this;
    }
}
