package com.samsung.android.lxd.fragment;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.app.ListFragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.RelativeLayout;
import com.samsung.android.lxd.CreateActivity;
import com.samsung.android.lxd.DetailActivity;
import com.samsung.android.lxd.ListActivity;
import com.samsung.android.lxd.R;
import com.samsung.android.lxd.a.j;
import com.samsung.android.lxd.a.l;
import com.samsung.android.lxd.a.o;
import com.samsung.android.lxd.fragment.a.d;
import com.samsung.android.lxd.fragment.a.d.a;
import com.samsung.android.lxd.fragment.a.d.b;
import com.samsung.android.lxd.processor.config.SystemConfig;
import com.samsung.android.lxd.processor.config.SystemConfigManager;
import com.samsung.android.lxd.processor.config.SystemConfigType;
import com.samsung.android.lxd.processor.utils.log.Log;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map.Entry;

public class ImageListFragment extends ListFragment {
    private static final String a = "ImageListFragment";
    private int b = 0;
    private boolean c = false;
    private List<String> d = new ArrayList();
    private List<a> e = new ArrayList();
    private d f = null;
    private String g = null;

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        return layoutInflater.inflate(R.layout.fragment_image_list, viewGroup, false);
    }

    public void onActivityCreated(Bundle bundle) {
        super.onActivityCreated(bundle);
        if (bundle != null) {
            this.b = bundle.getInt("currentIndex");
        }
        d();
    }

    public void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
        bundle.putInt("currentIndex", this.b);
    }

    public void onListItemClick(ListView listView, View view, int i, long j) {
        b(i);
    }

    private void b(int i) {
        a(i, true);
        l.a(((com.samsung.android.lxd.a) getActivity()).a, String.valueOf(404));
        if (o.d(getContext()) || o.f(getContext())) {
            ((ListActivity) getActivity()).N();
            return;
        }
        Intent intent = new Intent(getActivity(), DetailActivity.class);
        intent.putExtra("configId", (String) this.d.get(i));
        startActivity(intent);
    }

    protected ImageButton a() {
        return getView() != null ? (ImageButton) getView().findViewById(R.id.myFAB) : null;
    }

    protected RelativeLayout b() {
        return getView() != null ? (RelativeLayout) getView().findViewById(R.id.emptyListLayout) : null;
    }

    protected RelativeLayout c() {
        return getView() != null ? (RelativeLayout) getView().findViewById(R.id.imageListLayout) : null;
    }

    protected void a(int i) {
        String str;
        int i2 = 0;
        while (true) {
            str = null;
            if (i2 >= getListView().getChildCount()) {
                break;
            }
            if (i2 == i) {
                getListView().getChildAt(i2).setBackgroundColor(getResources().getColor(R.color.LightTheme.card_view_background, null));
            } else {
                getListView().getChildAt(i2).setBackgroundColor(0);
            }
            i2++;
        }
        f a = f.a();
        if (this.d.size() != 0) {
            str = (String) this.d.get(i);
        }
        Fragment a2 = a.a(str);
        FragmentTransaction beginTransaction = getFragmentManager().beginTransaction();
        beginTransaction.replace(R.id.details, a2);
        beginTransaction.setTransition(4099);
        beginTransaction.commit();
    }

    public ImageListFragment a(String str) {
        this.g = str;
        return this;
    }

    public ImageListFragment a(boolean z) {
        this.c = z;
        return this;
    }

    public ImageListFragment d() {
        if (getView() == null) {
            Log.e(a, "update : View had not been created yet!");
            return this;
        }
        ImageButton a = a();
        a.semSetHoverPopupType(0);
        a.setTooltipText(getActivity().getString(R.string.tooltip_create_a_lod_container));
        a.setOnClickListener(new j() {
            public void onClick(View view) {
                super.onClick(view);
                l.a(((com.samsung.android.lxd.a) ImageListFragment.this.getActivity()).a, String.valueOf(402));
                ImageListFragment.this.startActivity(new Intent(ImageListFragment.this.getActivity(), CreateActivity.class));
            }
        });
        if (this.f == null) {
            this.f = new d(getContext(), this.e, this.b, new b() {
                public void a(int i) {
                    ImageListFragment.this.b(i);
                }
            });
            setListAdapter(this.f);
        }
        this.e.clear();
        this.d.clear();
        for (Entry key : SystemConfigManager.loadAll().entrySet()) {
            this.d.add(key.getKey());
        }
        Collections.sort(this.d, Collections.reverseOrder());
        for (String parseLong : this.d) {
            String string;
            SystemConfig load = SystemConfigManager.load(Long.parseLong(parseLong));
            List list = this.e;
            String str = load.get(SystemConfigType.IMAGE_NAME);
            if (load.get(SystemConfigType.IMAGE_DESC).isEmpty()) {
                string = getString(R.string.no_description);
            } else {
                string = load.get(SystemConfigType.IMAGE_DESC);
            }
            list.add(new a(str, string, load.get(SystemConfigType.IMAGE_PATH)));
        }
        a(0, this.c);
        this.c = false;
        getListView().setChoiceMode(1);
        getListView().setItemChecked(this.b, true);
        if (this.f != null) {
            this.f.a(this.b);
        }
        if (o.d(getContext()) || o.f(getContext())) {
            a(this.b);
        }
        if (this.d.size() == 0) {
            b().setVisibility(0);
            c().setVisibility(4);
        } else {
            b().setVisibility(4);
            c().setVisibility(0);
            this.f.notifyDataSetChanged();
        }
        l.a(((com.samsung.android.lxd.a) getActivity()).a, String.valueOf(405), (long) this.e.size());
        return this;
    }

    private void a(int i, boolean z) {
        if (z) {
            this.b = i;
            return;
        }
        if (this.g != null && this.d.contains(this.g)) {
            this.b = this.d.indexOf(this.g);
        }
    }
}
