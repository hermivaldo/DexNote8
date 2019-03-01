package com.samsung.android.lxd.fragment;

import android.app.Fragment;
import android.app.SearchManager;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MenuItem.OnActionExpandListener;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filterable;
import android.widget.SearchView;
import android.widget.SearchView.OnQueryTextListener;
import android.widget.Toast;
import com.samsung.android.lxd.R;
import com.samsung.android.lxd.a.o;
import com.samsung.android.lxd.processor.utils.log.Log;

public class SearchViewFragment extends Fragment {
    private static final String a = "SearchViewFragment";
    private Filterable b = null;
    private Menu c = null;
    private String d;
    private boolean e = false;
    private int f = -1;

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        return layoutInflater.inflate(R.layout.fragment_search_view, viewGroup, false);
    }

    public void onActivityCreated(Bundle bundle) {
        super.onActivityCreated(bundle);
        a();
    }

    public void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
    }

    public SearchViewFragment a(Filterable filterable) {
        this.b = filterable;
        return this;
    }

    public SearchViewFragment a(Menu menu) {
        this.c = menu;
        return this;
    }

    public SearchViewFragment a(String str) {
        this.d = str;
        return this;
    }

    public SearchViewFragment a(int i) {
        this.f = i;
        return this;
    }

    public SearchViewFragment a() {
        if (getView() == null) {
            Log.e(a, "update : View had not been created yet!");
            return this;
        }
        if (this.c != null) {
            final SearchView searchView = (SearchView) getView().findViewById(R.id.searchView);
            MenuItem findItem = this.c.findItem(R.id.actionSearch);
            if (!this.e) {
                findItem.setOnActionExpandListener(new OnActionExpandListener() {
                    public boolean onMenuItemActionExpand(MenuItem menuItem) {
                        searchView.setIconified(false);
                        SearchViewFragment.this.a(false);
                        return true;
                    }

                    public boolean onMenuItemActionCollapse(MenuItem menuItem) {
                        SearchViewFragment.this.a(true);
                        searchView.setIconified(true);
                        o.b(searchView, 0);
                        return true;
                    }
                });
                searchView.setSearchableInfo(((SearchManager) getActivity().getSystemService("search")).getSearchableInfo(getActivity().getComponentName()));
                searchView.setIconifiedByDefault(false);
                searchView.setIconified(true);
                searchView.setQueryHint(getString(R.string.tooltip_search));
                searchView.setImeOptions(33554435);
                searchView.setOnQueryTextListener(new OnQueryTextListener() {
                    public boolean onQueryTextSubmit(String str) {
                        return false;
                    }

                    public boolean onQueryTextChange(String str) {
                        if (SearchViewFragment.this.f == -1 || SearchViewFragment.this.f >= str.length()) {
                            SearchViewFragment.this.b.getFilter().filter(str);
                            return true;
                        }
                        searchView.setQuery(str.substring(0, SearchViewFragment.this.f), false);
                        Toast.makeText(SearchViewFragment.this.getActivity(), R.string.reach_maximum_search_length, 0).show();
                        return false;
                    }
                });
                this.e = true;
            }
            if (!TextUtils.isEmpty(this.d)) {
                searchView.setQuery(this.d, false);
            }
        }
        return this;
    }

    private void a(boolean z) {
        MenuItem findItem = this.c.findItem(R.id.actionSearch);
        for (int i = 0; i < this.c.size(); i++) {
            MenuItem item = this.c.getItem(i);
            if (item != findItem) {
                item.setVisible(z);
            }
        }
    }
}
