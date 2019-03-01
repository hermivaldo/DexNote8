package com.samsung.a.a.a.a.g;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;
import com.samsung.a.a.a.a.i.b.a;
import com.samsung.a.a.a.a.i.c;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

/* compiled from: SettingReader */
public class b {
    private Set<String> a;
    private Context b;
    private final String c = a.TWO_DEPTH.b();
    private final String d = a.TWO_DEPTH.a();
    private final String e = a.THREE_DEPTH.a();

    public b(Context context) {
        this.b = context;
        this.a = c.a(context).getStringSet("AppPrefs", new HashSet());
    }

    private SharedPreferences a(String str) {
        return this.b.getSharedPreferences(str, 0);
    }

    private Set<String> b(String str) {
        return c.a(this.b).getStringSet(str, new HashSet());
    }

    private List<String> b() {
        if (this.a.isEmpty()) {
            return null;
        }
        List<String> arrayList = new ArrayList();
        String str = "";
        for (String str2 : this.a) {
            SharedPreferences a = a(str2);
            Set b = b(str2);
            for (Entry entry : a.getAll().entrySet()) {
                if (b.contains(entry.getKey())) {
                    String stringBuilder;
                    StringBuilder stringBuilder2;
                    String str3 = "";
                    Class cls = entry.getValue().getClass();
                    if (cls.equals(Integer.class) || cls.equals(Float.class) || cls.equals(Long.class) || cls.equals(String.class) || cls.equals(Boolean.class)) {
                        StringBuilder stringBuilder3 = new StringBuilder();
                        stringBuilder3.append(str3);
                        stringBuilder3.append((String) entry.getKey());
                        stringBuilder3.append(this.c);
                        stringBuilder3.append(entry.getValue());
                        stringBuilder = stringBuilder3.toString();
                    } else {
                        Set<String> set = (Set) entry.getValue();
                        StringBuilder stringBuilder4 = new StringBuilder();
                        stringBuilder4.append(str3);
                        stringBuilder4.append((String) entry.getKey());
                        stringBuilder4.append(this.c);
                        stringBuilder = stringBuilder4.toString();
                        String str4 = null;
                        for (String str5 : set) {
                            StringBuilder stringBuilder5;
                            if (!TextUtils.isEmpty(str4)) {
                                stringBuilder5 = new StringBuilder();
                                stringBuilder5.append(str4);
                                stringBuilder5.append(this.e);
                                str4 = stringBuilder5.toString();
                            }
                            stringBuilder5 = new StringBuilder();
                            stringBuilder5.append(str4);
                            stringBuilder5.append(str5);
                            str4 = stringBuilder5.toString();
                        }
                        stringBuilder2 = new StringBuilder();
                        stringBuilder2.append(stringBuilder);
                        stringBuilder2.append(str4);
                        stringBuilder = stringBuilder2.toString();
                    }
                    if (str.length() + stringBuilder.length() > 512) {
                        arrayList.add(str);
                        str = "";
                    } else if (!TextUtils.isEmpty(str)) {
                        stringBuilder2 = new StringBuilder();
                        stringBuilder2.append(str);
                        stringBuilder2.append(this.d);
                        str = stringBuilder2.toString();
                    }
                    stringBuilder2 = new StringBuilder();
                    stringBuilder2.append(str);
                    stringBuilder2.append(stringBuilder);
                    str = stringBuilder2.toString();
                }
            }
        }
        if (str.length() != 0) {
            arrayList.add(str);
        }
        return arrayList;
    }

    public List<String> a() {
        List<String> b = b();
        Map all = a("SASettingPref").getAll();
        if (!(all == null || all.isEmpty())) {
            b.add(new com.samsung.a.a.a.a.i.b().a(all, a.TWO_DEPTH));
        }
        return b;
    }
}
