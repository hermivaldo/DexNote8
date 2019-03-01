package com.samsung.a.a.a.a.i;

import java.util.Map;
import java.util.Map.Entry;

/* compiled from: Delimiter */
public class b<K, V> {

    /* compiled from: Delimiter */
    public enum a {
        ONE_DEPTH("\u0002", "\u0003"),
        TWO_DEPTH("\u0004", "\u0005"),
        THREE_DEPTH("\u0006", "\u0007");
        
        private String d;
        private String e;

        private a(String str, String str2) {
            this.d = str;
            this.e = str2;
        }

        public String a() {
            return this.d;
        }

        public String b() {
            return this.e;
        }
    }

    public String a(Map<K, V> map, a aVar) {
        String str = null;
        for (Entry entry : map.entrySet()) {
            StringBuilder stringBuilder;
            if (str == null) {
                str = entry.getKey().toString();
            } else {
                stringBuilder = new StringBuilder();
                stringBuilder.append(str);
                stringBuilder.append(aVar.a());
                str = stringBuilder.toString();
                stringBuilder = new StringBuilder();
                stringBuilder.append(str);
                stringBuilder.append(entry.getKey());
                str = stringBuilder.toString();
            }
            stringBuilder = new StringBuilder();
            stringBuilder.append(str);
            stringBuilder.append(aVar.b());
            str = stringBuilder.toString();
            stringBuilder = new StringBuilder();
            stringBuilder.append(str);
            stringBuilder.append(entry.getValue());
            str = stringBuilder.toString();
        }
        return str;
    }
}
