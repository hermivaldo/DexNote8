package com.samsung.android.lxd.fragment;

import android.app.AlertDialog.Builder;
import android.app.Dialog;
import android.os.Bundle;
import android.widget.SimpleAdapter;
import com.samsung.android.lxd.R;
import com.samsung.android.lxd.p064a.C0877o;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;

/* compiled from: DetailInfoDialogFragment */
/* renamed from: com.samsung.android.lxd.fragment.c */
public class C1369c extends C0936a {
    /* renamed from: r */
    protected static final String f4508r = "c";
    /* renamed from: s */
    private ArrayList<File> f4509s = null;

    public C1369c() {
        setRetainInstance(true);
    }

    /* renamed from: j */
    public static C1369c m6284j() {
        return new C1369c();
    }

    public Dialog onCreateDialog(Bundle bundle) {
        Dialog create = new Builder(getActivity()).setTitle(this.d).setAdapter(new SimpleAdapter(this.b, m6285k(), R.layout.fragment_detail_info_dialog, new String[]{"detail_name", "detail_info"}, new int[]{R.id.details_item_name, R.id.details_item_info}), null).setPositiveButton(this.g, this.i).create();
        create.getListView().setFocusable(false);
        return create;
    }

    /* renamed from: k */
    private ArrayList<HashMap<String, String>> m6285k() {
        ArrayList<HashMap<String, String>> arrayList = new ArrayList();
        if (this.f4509s.size() <= 1) {
            File file = (File) this.f4509s.get(0);
            if (file.exists()) {
                m6283a(arrayList, getString(R.string.detail_name), file.getName());
                String string = getString(R.string.size);
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append(Integer.toString(C0877o.m3509g(file.getAbsolutePath())));
                stringBuilder.append(" GB");
                m6283a(arrayList, string, stringBuilder.toString());
                m6283a(arrayList, getString(R.string.last_modified), C0877o.m3460a(getActivity(), file.lastModified()));
                m6283a(arrayList, getString(R.string.path), m6282a(file.getParent()));
            } else {
                m6283a(arrayList, getString(R.string.detail_name), file.getName());
            }
        }
        return arrayList;
    }

    /* renamed from: a */
    private HashMap<String, String> m6283a(ArrayList<HashMap<String, String>> arrayList, String str, String str2) {
        HashMap<String, String> hashMap = new HashMap();
        hashMap.put("detail_name", str);
        hashMap.put("detail_info", str2);
        arrayList.add(hashMap);
        return hashMap;
    }

    /* renamed from: a */
    public C1369c m6286a(ArrayList<File> arrayList) {
        this.f4509s = arrayList;
        return this;
    }

    /* renamed from: a */
    private String m6282a(String str) {
        String a;
        CharSequence string;
        if (C0877o.m3500d(str)) {
            a = C0877o.m3458a();
            string = getActivity().getString(R.string.internal_storage);
        } else {
            a = C0877o.m3497d();
            string = getActivity().getString(R.string.sd_card);
        }
        return str.replace(a.substring(1, a.length() - 1), string);
    }
}
