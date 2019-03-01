package com.samsung.p047a.p048a.p049a.p050a.p062g;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;
import com.samsung.p047a.p048a.p049a.p050a.p063i.C0786b;
import com.samsung.p047a.p048a.p049a.p050a.p063i.C0786b.C0785a;
import com.samsung.p047a.p048a.p049a.p050a.p063i.C0787c;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

/* compiled from: SettingReader */
/* renamed from: com.samsung.a.a.a.a.g.b */
public class C0783b {
    /* renamed from: a */
    private Set<String> f2477a;
    /* renamed from: b */
    private Context f2478b;
    /* renamed from: c */
    private final String f2479c = C0785a.TWO_DEPTH.m3258b();
    /* renamed from: d */
    private final String f2480d = C0785a.TWO_DEPTH.m3257a();
    /* renamed from: e */
    private final String f2481e = C0785a.THREE_DEPTH.m3257a();

    public C0783b(Context context) {
        this.f2478b = context;
        this.f2477a = C0787c.m3260a(context).getStringSet("AppPrefs", new HashSet());
    }

    /* renamed from: a */
    private SharedPreferences m3248a(String str) {
        return this.f2478b.getSharedPreferences(str, 0);
    }

    /* renamed from: b */
    private Set<String> m3250b(String str) {
        return C0787c.m3260a(this.f2478b).getStringSet(str, new HashSet());
    }

    /* renamed from: b */
    private List<String> m3249b() {
        if (this.f2477a.isEmpty()) {
            return null;
        }
        List<String> arrayList = new ArrayList();
        String str = "";
        for (String str2 : this.f2477a) {
            SharedPreferences a = m3248a(str2);
            Set b = m3250b(str2);
            for (Entry entry : a.getAll().entrySet()) {
                if (b.contains(entry.getKey())) {
                    String stringBuilder;
                    StringBuilder stringBuilder2;
                    String str3 = "";
                    Class cls = entry.getValue().getClass();
                    if (!(cls.equals(Integer.class) || cls.equals(Float.class) || cls.equals(Long.class) || cls.equals(String.class))) {
                        if (!cls.equals(Boolean.class)) {
                            Set<String> set = (Set) entry.getValue();
                            StringBuilder stringBuilder3 = new StringBuilder();
                            stringBuilder3.append(str3);
                            stringBuilder3.append((String) entry.getKey());
                            stringBuilder3.append(this.f2479c);
                            stringBuilder = stringBuilder3.toString();
                            String str4 = null;
                            for (String str5 : set) {
                                StringBuilder stringBuilder4;
                                if (!TextUtils.isEmpty(str4)) {
                                    stringBuilder4 = new StringBuilder();
                                    stringBuilder4.append(str4);
                                    stringBuilder4.append(this.f2481e);
                                    str4 = stringBuilder4.toString();
                                }
                                stringBuilder4 = new StringBuilder();
                                stringBuilder4.append(str4);
                                stringBuilder4.append(str5);
                                str4 = stringBuilder4.toString();
                            }
                            stringBuilder2 = new StringBuilder();
                            stringBuilder2.append(stringBuilder);
                            stringBuilder2.append(str4);
                            stringBuilder = stringBuilder2.toString();
                            if (str.length() + stringBuilder.length() > 512) {
                                arrayList.add(str);
                                str = "";
                            } else if (!TextUtils.isEmpty(str)) {
                                stringBuilder2 = new StringBuilder();
                                stringBuilder2.append(str);
                                stringBuilder2.append(this.f2480d);
                                str = stringBuilder2.toString();
                            }
                            stringBuilder2 = new StringBuilder();
                            stringBuilder2.append(str);
                            stringBuilder2.append(stringBuilder);
                            str = stringBuilder2.toString();
                        }
                    }
                    StringBuilder stringBuilder5 = new StringBuilder();
                    stringBuilder5.append(str3);
                    stringBuilder5.append((String) entry.getKey());
                    stringBuilder5.append(this.f2479c);
                    stringBuilder5.append(entry.getValue());
                    stringBuilder = stringBuilder5.toString();
                    if (str.length() + stringBuilder.length() > 512) {
                        arrayList.add(str);
                        str = "";
                    } else if (TextUtils.isEmpty(str)) {
                        stringBuilder2 = new StringBuilder();
                        stringBuilder2.append(str);
                        stringBuilder2.append(this.f2480d);
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

    /* renamed from: a */
    public List<String> m3251a() {
        List<String> b = m3249b();
        Map all = m3248a("SASettingPref").getAll();
        if (!(all == null || all.isEmpty())) {
            b.add(new C0786b().m3259a(all, C0785a.TWO_DEPTH));
        }
        return b;
    }
}
