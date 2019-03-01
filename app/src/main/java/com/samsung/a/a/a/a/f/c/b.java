package com.samsung.a.a.a.a.f.c;

import android.content.Context;
import android.text.TextUtils;
import com.samsung.a.a.a.a.f.a;
import com.samsung.a.a.a.a.f.e;
import java.util.HashMap;
import java.util.Map;
import java.util.Queue;

/* compiled from: DMALogSender */
public class b extends a {
    private a g;
    private boolean h = false;
    private int i = 0;

    public b(Context context, com.samsung.a.a.a.b bVar) {
        super(context, bVar);
        this.g = new a(context, new com.samsung.a.a.a.a.a<Void, String>() {
            public Void a(String str) {
                b.this.b();
                b.this.a();
                return null;
            }
        });
        this.g.a();
    }

    public int e(Map<String, String> map) {
        if (this.g.b()) {
            return -8;
        }
        if (this.i != 0) {
            return this.i;
        }
        c(map);
        if (!this.g.e()) {
            this.g.a();
        } else if (this.g.d() != null) {
            a();
            if (this.h) {
                b();
                this.h = false;
            }
        }
        return this.i;
    }

    public int f(Map<String, String> map) {
        return e(map);
    }

    private void a() {
        if (this.i == 0) {
            Queue c = this.e.c();
            while (!c.isEmpty()) {
                this.f.a(new c(this.g.d(), this.b, (e) c.poll()));
            }
        }
    }

    private void b() {
        String str;
        com.samsung.a.a.a.a.i.b bVar = new com.samsung.a.a.a.a.i.b();
        Map hashMap = new HashMap();
        hashMap.put("av", this.c.f());
        hashMap.put("uv", this.b.i());
        String a = bVar.a(hashMap, com.samsung.a.a.a.a.i.b.a.ONE_DEPTH);
        Map hashMap2 = new HashMap();
        if (TextUtils.isEmpty(this.b.b())) {
            str = null;
        } else {
            hashMap2.put("auid", this.b.b());
            hashMap2.put("at", String.valueOf(this.b.o()));
            str = bVar.a(hashMap2, com.samsung.a.a.a.a.i.b.a.ONE_DEPTH);
        }
        try {
            this.i = this.g.d().a(com.samsung.a.a.a.a.b.a.ordinal(), this.b.a(), a, str);
        } catch (Exception e) {
            com.samsung.a.a.a.a.i.a.a(e.getClass(), e);
            this.i = -9;
        }
    }
}
