package com.samsung.p047a.p048a.p049a;

import android.text.TextUtils;
import com.samsung.p047a.p048a.p049a.p050a.p054d.C0766d;
import com.samsung.p047a.p048a.p049a.p050a.p063i.C0786b;
import com.samsung.p047a.p048a.p049a.p050a.p063i.C0786b.C0785a;
import com.samsung.p047a.p048a.p049a.p050a.p063i.C0788d;
import java.util.HashMap;
import java.util.Map;

/* compiled from: LogBuilders */
/* renamed from: com.samsung.a.a.a.d */
public class C0794d {

    /* compiled from: LogBuilders */
    /* renamed from: com.samsung.a.a.a.d$c */
    protected static abstract class C0793c<T extends C0793c> {
        /* renamed from: a */
        protected Map<String, String> f2504a;

        /* renamed from: d */
        protected abstract T mo656d();

        private C0793c() {
            this.f2504a = new HashMap();
        }

        /* renamed from: a */
        public final T m3288a(String str, String str2) {
            if (str != null) {
                this.f2504a.put(str, str2);
            }
            return mo656d();
        }

        /* renamed from: c */
        public long mo655c() {
            return System.currentTimeMillis();
        }

        /* renamed from: b */
        public Map<String, String> mo654b() {
            m3288a("ts", String.valueOf(mo655c()));
            return this.f2504a;
        }

        /* renamed from: b */
        public T m3290b(String str) {
            m3288a("pn", str);
            return mo656d();
        }

        /* renamed from: a */
        public T m3289a(Map<String, String> map) {
            m3288a("cd", new C0786b().m3259a(C0766d.m3185a((Map) map), C0785a.TWO_DEPTH));
            return mo656d();
        }
    }

    /* compiled from: LogBuilders */
    /* renamed from: com.samsung.a.a.a.d$a */
    public static class C1302a extends C0793c<C1302a> {
        /* renamed from: a */
        protected C1302a m6044a() {
            return this;
        }

        public C1302a() {
            super();
        }

        /* renamed from: c */
        public /* bridge */ /* synthetic */ long mo655c() {
            return super.mo655c();
        }

        /* renamed from: d */
        protected /* synthetic */ C0793c mo656d() {
            return m6044a();
        }

        /* renamed from: a */
        public C1302a m6046a(String str) {
            if (TextUtils.isEmpty(str)) {
                C0788d.m3262a("Failure to build Log : Event name cannot be null");
            }
            m3288a("en", str);
            return this;
        }

        /* renamed from: a */
        public C1302a m6045a(long j) {
            m3288a("ev", String.valueOf(j));
            return this;
        }

        /* renamed from: b */
        public Map<String, String> mo654b() {
            if (!this.a.containsKey("en")) {
                C0788d.m3262a("Failure to build Log : Event name cannot be null");
            }
            m3288a("t", "ev");
            return super.mo654b();
        }
    }

    @Deprecated
    /* compiled from: LogBuilders */
    /* renamed from: com.samsung.a.a.a.d$b */
    public static class C1303b extends C0793c<C1303b> {
        @Deprecated
        /* renamed from: a */
        protected C1303b m6050a() {
            return this;
        }

        @Deprecated
        /* renamed from: a */
        public C1303b m6052a(boolean z) {
            return this;
        }

        /* renamed from: c */
        public /* bridge */ /* synthetic */ long mo655c() {
            return super.mo655c();
        }

        @Deprecated
        /* renamed from: d */
        protected /* synthetic */ C0793c mo656d() {
            return m6050a();
        }

        public C1303b() {
            super();
        }

        @Deprecated
        /* renamed from: a */
        public C1303b m6051a(String str) {
            if (!TextUtils.isEmpty(str)) {
                String str2 = "exm";
                if (str.length() >= 100) {
                    str = str.substring(0, 100);
                }
                m3288a(str2, str);
            }
            return this;
        }

        @Deprecated
        /* renamed from: b */
        public Map<String, String> mo654b() {
            m3288a("t", "ex");
            m3288a("ext", "ex");
            return super.mo654b();
        }
    }

    /* compiled from: LogBuilders */
    /* renamed from: com.samsung.a.a.a.d$d */
    public static class C1304d extends C0793c<C1304d> {
        /* renamed from: a */
        protected C1304d m6056a() {
            return this;
        }

        public C1304d() {
            super();
        }

        /* renamed from: c */
        public /* bridge */ /* synthetic */ long mo655c() {
            return super.mo655c();
        }

        /* renamed from: d */
        protected /* synthetic */ C0793c mo656d() {
            return m6056a();
        }

        /* renamed from: b */
        public Map<String, String> mo654b() {
            if (TextUtils.isEmpty((CharSequence) this.a.get("pn"))) {
                C0788d.m3262a("Failure to build Log : Screen name cannot be null");
            } else {
                m3288a("t", "pv");
            }
            return super.mo654b();
        }
    }
}
