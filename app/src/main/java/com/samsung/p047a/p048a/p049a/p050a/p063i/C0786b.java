package com.samsung.p047a.p048a.p049a.p050a.p063i;

import java.util.Map;
import java.util.Map.Entry;

/* compiled from: Delimiter */
/* renamed from: com.samsung.a.a.a.a.i.b */
public class C0786b<K, V> {

    /* compiled from: Delimiter */
    /* renamed from: com.samsung.a.a.a.a.i.b$a */
    public enum C0785a {
        ONE_DEPTH("\u0002", "\u0003"),
        TWO_DEPTH("\u0004", "\u0005"),
        THREE_DEPTH("\u0006", "\u0007");
        
        /* renamed from: d */
        private String f2486d;
        /* renamed from: e */
        private String f2487e;

        private C0785a(String str, String str2) {
            this.f2486d = str;
            this.f2487e = str2;
        }

        /* renamed from: a */
        public String m3257a() {
            return this.f2486d;
        }

        /* renamed from: b */
        public String m3258b() {
            return this.f2487e;
        }
    }

    /* renamed from: a */
    public String m3259a(Map<K, V> map, C0785a c0785a) {
        map = null;
        for (Entry entry : map.entrySet()) {
            StringBuilder stringBuilder;
            if (map == null) {
                map = entry.getKey().toString();
            } else {
                stringBuilder = new StringBuilder();
                stringBuilder.append(map);
                stringBuilder.append(c0785a.m3257a());
                map = stringBuilder.toString();
                stringBuilder = new StringBuilder();
                stringBuilder.append(map);
                stringBuilder.append(entry.getKey());
                map = stringBuilder.toString();
            }
            stringBuilder = new StringBuilder();
            stringBuilder.append(map);
            stringBuilder.append(c0785a.m3258b());
            map = stringBuilder.toString();
            stringBuilder = new StringBuilder();
            stringBuilder.append(map);
            stringBuilder.append(entry.getValue());
            map = stringBuilder.toString();
        }
        return map;
    }
}
