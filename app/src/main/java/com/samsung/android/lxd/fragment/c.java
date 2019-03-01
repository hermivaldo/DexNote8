package com.samsung.android.lxd.fragment;

import android.app.AlertDialog.Builder;
import android.app.Dialog;
import android.os.Bundle;
import android.widget.SimpleAdapter;
import com.samsung.android.lxd.R;
import com.samsung.android.lxd.a.o;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;

/* compiled from: DetailInfoDialogFragment */
public class c extends a {
    protected static final String r = "c";
    private ArrayList<File> s = null;

    public c() {
        setRetainInstance(true);
    }

    public static c j() {
        return new c();
    }

    public Dialog onCreateDialog(Bundle bundle) {
        Dialog create = new Builder(getActivity()).setTitle(this.d).setAdapter(new SimpleAdapter(this.b, k(), R.layout.fragment_detail_info_dialog, new String[]{"detail_name", "detail_info"}, new int[]{R.id.details_item_name, R.id.details_item_info}), null).setPositiveButton(this.g, this.i).create();
        create.getListView().setFocusable(false);
        return create;
    }

    private ArrayList<HashMap<String, String>> k() {
        ArrayList<HashMap<String, String>> arrayList = new ArrayList();
        if (this.s.size() <= 1) {
            File file = (File) this.s.get(0);
            if (file.exists()) {
                a(arrayList, getString(R.string.detail_name), file.getName());
                String string = getString(R.string.size);
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append(Integer.toString(o.g(file.getAbsolutePath())));
                stringBuilder.append(" GB");
                a(arrayList, string, stringBuilder.toString());
                a(arrayList, getString(R.string.last_modified), o.a(getActivity(), file.lastModified()));
                a(arrayList, getString(R.string.path), a(file.getParent()));
            } else {
                a(arrayList, getString(R.string.detail_name), file.getName());
            }
        }
        return arrayList;
    }

    private HashMap<String, String> a(ArrayList<HashMap<String, String>> arrayList, String str, String str2) {
        HashMap<String, String> hashMap = new HashMap();
        hashMap.put("detail_name", str);
        hashMap.put("detail_info", str2);
        arrayList.add(hashMap);
        return hashMap;
    }

    public c a(ArrayList<File> arrayList) {
        this.s = arrayList;
        return this;
    }

    private String a(String str) {
        String a;
        CharSequence string;
        if (o.d(str)) {
            a = o.a();
            string = getActivity().getString(R.string.internal_storage);
        } else {
            a = o.d();
            string = getActivity().getString(R.string.sd_card);
        }
        return str.replace(a.substring(1, a.length() - 1), string);
    }
}
