package com.samsung.a.a.a;

import android.text.TextUtils;
import java.util.HashMap;
import java.util.Map;

/* compiled from: LogBuilders */
public class d {

    /* compiled from: LogBuilders */
    protected static abstract class c<T extends c> {
        protected Map<String, String> a;

        protected abstract T d();

        private c() {
            this.a = new HashMap();
        }

        public final T a(String str, String str2) {
            if (str != null) {
                this.a.put(str, str2);
            }
            return d();
        }

        public long c() {
            return System.currentTimeMillis();
        }

        public Map<String, String> b() {
            a("ts", String.valueOf(c()));
            return this.a;
        }

        public T b(String str) {
            a("pn", str);
            return d();
        }

        public T a(Map<String, String> map) {
            a("cd", new com.samsung.a.a.a.a.i.b().a(com.samsung.a.a.a.a.d.d.a((Map) map), com.samsung.a.a.a.a.i.b.a.TWO_DEPTH));
            return d();
        }
    }

    /* compiled from: LogBuilders */
    public static class a extends c<a> {
        /* renamed from: a */
        protected a d() {
            return this;
        }

        public a() {
            super();
        }

        public a a(String str) {
            if (TextUtils.isEmpty(str)) {
                com.samsung.a.a.a.a.i.d.a("Failure to build Log : Event name cannot be null");
            }
            a("en", str);
            return this;
        }

        public a a(long j) {
            a("ev", String.valueOf(j));
            return this;
        }

        public Map<String, String> b() {
            if (!this.a.containsKey("en")) {
                com.samsung.a.a.a.a.i.d.a("Failure to build Log : Event name cannot be null");
            }
            a("t", "ev");
            return super.b();
        }
    }

    @Deprecated
    /* compiled from: LogBuilders */
    public static class b extends c<b> {
        @Deprecated
        /* renamed from: a */
        protected b d() {
            return this;
        }

        @Deprecated
        public b a(boolean z) {
            return this;
        }

        public b() {
            super();
        }

        @Deprecated
        public b a(String str) {
            if (!TextUtils.isEmpty(str)) {
                String str2 = "exm";
                if (str.length() >= 100) {
                    str = str.substring(0, 100);
                }
                a(str2, str);
            }
            return this;
        }

        @Deprecated
        public Map<String, String> b() {
            a("t", "ex");
            a("ext", "ex");
            return super.b();
        }
    }

    /* compiled from: LogBuilders */
    public static class d extends c<d> {
        /* renamed from: a */
        protected d d() {
            return this;
        }

        public d() {
            super();
        }

        public Map<String, String> b() {
            if (TextUtils.isEmpty((CharSequence) this.a.get("pn"))) {
                com.samsung.a.a.a.a.i.d.a("Failure to build Log : Screen name cannot be null");
            } else {
                a("t", "pv");
            }
            return super.b();
        }
    }
}
