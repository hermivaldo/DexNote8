package com.samsung.a.a.a.a.f.a;

import android.content.Context;
import com.samsung.a.a.a.a.f.a;
import com.samsung.a.a.a.a.f.e;
import java.util.Map;
import java.util.Queue;

/* compiled from: DLCLogSender */
public class b extends a {
    private a g;

    public b(Context context, com.samsung.a.a.a.b bVar) {
        super(context, bVar);
        this.g = new a(context, new com.samsung.a.a.a.a.a<Void, Void>() {
            public Void a(Void voidR) {
                b.this.a();
                return null;
            }
        });
        this.g.b();
    }

    private void a() {
        Queue c = this.e.c();
        while (!c.isEmpty()) {
            this.f.a(new c(this.g, this.b, (e) c.poll(), null));
        }
    }

    protected Map<String, String> a(Map<String, String> map) {
        Map<String, String> a = super.a(map);
        a.remove("do");
        a.remove("dm");
        a.remove("v");
        return a;
    }

    public int e(Map<String, String> map) {
        c(map);
        if (!this.g.c()) {
            this.g.b();
        } else if (this.g.d() != null) {
            a();
        }
        return 0;
    }

    public int f(Map<String, String> map) {
        com.samsung.a.a.a.a.i.a.a("DLCLogSender", "not support sync api");
        e(map);
        return -100;
    }
}
