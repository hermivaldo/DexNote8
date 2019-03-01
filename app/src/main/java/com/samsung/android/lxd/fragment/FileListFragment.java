package com.samsung.android.lxd.fragment;

import android.app.Fragment;
import android.database.DataSetObserver;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import com.samsung.android.lxd.R;
import com.samsung.android.lxd.a.d;
import com.samsung.android.lxd.a.g;
import com.samsung.android.lxd.a.j;
import com.samsung.android.lxd.a.l;
import com.samsung.android.lxd.a.o;
import com.samsung.android.lxd.fragment.a.c;
import com.samsung.android.lxd.processor.utils.log.Log;
import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class FileListFragment extends Fragment {
    private static final String a = "FileListFragment";
    private String b = null;
    private List<com.samsung.android.lxd.fragment.a.c.b> c = new ArrayList();
    private c d = null;
    private a e;
    private Menu f = null;
    private boolean g = true;
    private boolean h = false;
    private boolean i = false;
    private OnItemClickListener j = new OnItemClickListener() {
        public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
            l.a(((com.samsung.android.lxd.a) FileListFragment.this.getActivity()).a, String.valueOf(604));
            ((com.samsung.android.lxd.a) FileListFragment.this.getActivity()).e(((com.samsung.android.lxd.fragment.a.c.b) FileListFragment.this.d.getItem(i)).b());
        }
    };
    private OnItemLongClickListener k = new OnItemLongClickListener() {
        public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long j) {
            File file = new File(((com.samsung.android.lxd.fragment.a.c.b) FileListFragment.this.d.getItem(i)).b());
            String i2 = FileListFragment.a;
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("onItemLongClick isFile? ");
            stringBuilder.append(file.isFile());
            Log.d(i2, stringBuilder.toString());
            ((com.samsung.android.lxd.a) FileListFragment.this.getActivity()).a(file);
            return true;
        }
    };

    public static class a extends DataSetObserver {
        private FileListFragment a;

        a(FileListFragment fileListFragment) {
            this.a = fileListFragment;
        }

        public void onChanged() {
            this.a.e().setVisibility(8);
            this.a.d().setVisibility(0);
        }

        public void onInvalidated() {
            this.a.j();
            this.a.e().setVisibility(0);
            this.a.d().setVisibility(8);
        }
    }

    public enum b {
        GRID,
        LIST
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        return layoutInflater.inflate(R.layout.fragment_file_list, viewGroup, false);
    }

    public void onActivityCreated(Bundle bundle) {
        super.onActivityCreated(bundle);
        h();
    }

    public void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
    }

    public void a() {
        this.i = true;
    }

    protected LinearLayout b() {
        return getView() != null ? (LinearLayout) getView().findViewById(R.id.EmptyListLayout) : null;
    }

    protected LinearLayout c() {
        return getView() != null ? (LinearLayout) getView().findViewById(R.id.FileListLayout) : null;
    }

    protected RelativeLayout d() {
        return getView() != null ? (RelativeLayout) getView().findViewById(R.id.contentLayout) : null;
    }

    protected LinearLayout e() {
        return getView() != null ? (LinearLayout) getView().findViewById(R.id.NoSearchListLayout) : null;
    }

    private void a(File file) {
        File[] listFiles = file.listFiles();
        if (listFiles == null) {
            Log.e(a, "Failed to find img file : no files to check with!");
            return;
        }
        try {
            for (File file2 : listFiles) {
                if (file2.isDirectory()) {
                    a(file2);
                } else {
                    if (file2.getName().substring(file2.getName().lastIndexOf(46) + 1).equalsIgnoreCase("img")) {
                        List list = this.c;
                        String name = file2.getName();
                        String absolutePath = file2.getAbsolutePath();
                        String a = o.a(getContext(), file2.lastModified());
                        StringBuilder stringBuilder = new StringBuilder();
                        stringBuilder.append(file2.length() / 1073741824);
                        stringBuilder.append(getString(R.string.gb));
                        list.add(new com.samsung.android.lxd.fragment.a.c.b(name, absolutePath, a, stringBuilder.toString()));
                    }
                }
            }
        } catch (Exception e) {
            String str = a;
            StringBuilder stringBuilder2 = new StringBuilder();
            stringBuilder2.append("EXCEPTION! ");
            stringBuilder2.append(e);
            Log.e(str, stringBuilder2.toString());
        }
    }

    public c f() {
        return this.d;
    }

    public FileListFragment a(String str) {
        this.b = str;
        return this;
    }

    public FileListFragment a(Menu menu) {
        this.f = menu;
        return this;
    }

    public FileListFragment g() {
        if (this.f != null) {
            this.g = true;
            this.h = true;
        }
        return this;
    }

    public FileListFragment h() {
        if (getView() == null) {
            Log.e(a, "update : View had not been created yet!");
            return this;
        }
        j();
        return this;
    }

    private void j() {
        AbsListView absListView = null;
        if (this.g) {
            this.c.clear();
            File file = !this.i ? new File(o.a()) : !TextUtils.isEmpty(o.d()) ? new File(o.d()) : null;
            if (file != null) {
                a(file);
            }
            Collections.sort(this.c, new g());
        }
        this.g = false;
        b valueOf = b.valueOf(k());
        if (this.h) {
            this.h = false;
            valueOf = valueOf.equals(b.GRID) ? b.LIST : b.GRID;
            l.a(((com.samsung.android.lxd.a) getActivity()).a, String.valueOf(603), valueOf.equals(b.GRID) ? "Grid" : "List");
        }
        a(valueOf);
        int i = 8;
        switch (valueOf) {
            case GRID:
                getView().findViewById(R.id.gridView).setVisibility(0);
                getView().findViewById(R.id.listView).setVisibility(8);
                getView().findViewById(R.id.horizontalListColumn).setVisibility(8);
                absListView = (AbsListView) getView().findViewById(R.id.gridView);
                getView().findViewById(R.id.top_margin).getLayoutParams().height = (int) (((double) o.m(getContext()).y) * 0.03d);
                break;
            case LIST:
                getView().findViewById(R.id.gridView).setVisibility(8);
                getView().findViewById(R.id.listView).setVisibility(0);
                absListView = (AbsListView) getView().findViewById(R.id.listView);
                View findViewById = getView().findViewById(R.id.horizontalListColumn);
                if (!o.e(getContext())) {
                    i = 0;
                }
                findViewById.setVisibility(i);
                if (!o.e(getContext())) {
                    getView().findViewById(R.id.horizontalListColumn).getLayoutParams().height = (int) (((double) o.m(getContext()).y) * (o.l(getContext()) ? 0.0375d : 0.0234d));
                    getView().findViewById(R.id.top_margin).getLayoutParams().height = 0;
                    break;
                }
                getView().findViewById(R.id.top_margin).getLayoutParams().height = (int) (((double) o.m(getContext()).y) * 0.03d);
                break;
        }
        if (this.d == null) {
            this.e = new a(this);
            this.d = new c(getContext(), this.c);
            this.d.registerDataSetObserver(this.e);
        } else {
            this.d.a(this.c);
        }
        absListView.setAdapter(this.d);
        this.d.a(valueOf);
        LinearLayout b = b();
        LinearLayout c = c();
        if (this.c.isEmpty()) {
            b.setVisibility(0);
            c.setVisibility(4);
            ((TextButtonFragment) getChildFragmentManager().findFragmentById(R.id.serverLinkButton)).a((int) R.string.server_link_button).a(new j() {
                public void onClick(View view) {
                    super.onClick(view);
                    ((com.samsung.android.lxd.a) FileListFragment.this.getActivity()).q();
                }
            }).a(d.a()).b();
        } else {
            b.setVisibility(4);
            c.setVisibility(0);
            this.d.notifyDataSetChanged();
            absListView.setMinimumHeight(absListView.getHeight() * this.c.size());
            absListView.setFocusable(false);
        }
        l.a(((com.samsung.android.lxd.a) getActivity()).a, String.valueOf(605), (long) this.c.size());
        absListView.setOnItemClickListener(new OnItemClickListener() {
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
                new j().onClick(adapterView);
                FileListFragment.this.j.onItemClick(adapterView, view, i, j);
            }
        });
        absListView.setOnItemLongClickListener(new OnItemLongClickListener() {
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long j) {
                return FileListFragment.this.k.onItemLongClick(adapterView, view, i, j);
            }
        });
    }

    private String k() {
        return getContext().getSharedPreferences("prefs", 0).getString(b.class.getName(), b.LIST.toString());
    }

    private void a(b bVar) {
        if (this.f != null) {
            MenuItem findItem = this.f.findItem(R.id.actionView);
            switch (bVar) {
                case GRID:
                    findItem.setIcon(R.drawable.ic_icon_item_view_list);
                    findItem.setTitle(R.string.tooltip_view_list);
                    getContext().getSharedPreferences("prefs", 0).edit().putString(b.class.getName(), b.GRID.toString()).apply();
                    break;
                case LIST:
                    findItem.setIcon(R.drawable.ic_icon_item_view_grid);
                    findItem.setTitle(R.string.tooltip_view_grid);
                    getContext().getSharedPreferences("prefs", 0).edit().putString(b.class.getName(), b.LIST.toString()).apply();
                    break;
            }
        }
    }
}
