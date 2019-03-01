package com.samsung.a.a.a.a.f.d;

import android.content.Context;
import com.samsung.a.a.a.a.f.e;
import com.samsung.a.a.a.a.i.d;
import com.samsung.a.a.a.b;
import com.samsung.a.a.a.c;
import java.util.List;
import java.util.Queue;

/* compiled from: Manager */
public class a {
    private static a d;
    private com.samsung.a.a.a.a.f.d.a.a a;
    private com.samsung.a.a.a.a.f.d.b.a b;
    private boolean c;

    private a(Context context, boolean z, int i) {
        if (z) {
            this.a = new com.samsung.a.a.a.a.f.d.a.a(context);
        }
        this.b = new com.samsung.a.a.a.a.f.d.b.a(i);
        this.c = z;
    }

    private a(c cVar, int i) {
        this.a = new com.samsung.a.a.a.a.f.d.a.a(cVar);
        this.b = new com.samsung.a.a.a.a.f.d.b.a(i);
        this.c = true;
    }

    public static a a(Context context, b bVar) {
        if (d == null) {
            synchronized (a.class) {
                int m = bVar.m();
                if (!com.samsung.a.a.a.a.b.a.c()) {
                    d = new a(context, false, m);
                } else if (com.samsung.a.a.a.a.i.c.a(context).getString("lgt", "").equals("rtb")) {
                    c n = bVar.n();
                    if (n != null) {
                        d = new a(n, m);
                    } else {
                        d = new a(context, true, m);
                    }
                } else {
                    d = new a(context, false, m);
                }
            }
        }
        return d;
    }

    public void a(Context context) {
        a(new com.samsung.a.a.a.a.f.d.a.a(context));
    }

    public void a(c cVar) {
        a(new com.samsung.a.a.a.a.f.d.a.a(cVar));
    }

    public void a(com.samsung.a.a.a.a.f.d.a.a aVar) {
        this.c = true;
        this.a = aVar;
        d();
    }

    private void d() {
        if (!this.b.a().isEmpty()) {
            for (e a : this.b.a()) {
                this.a.a(a);
            }
            this.b.a().clear();
        }
    }

    public boolean a() {
        return this.c;
    }

    public void a(e eVar) {
        if (this.c) {
            this.a.a(eVar);
        } else {
            this.b.a(eVar);
        }
    }

    public void a(long j, String str, com.samsung.a.a.a.a.f.c cVar) {
        a(new e(j, str, cVar));
    }

    public void b() {
        if (this.c) {
            this.a.a(d.a(5));
        }
    }

    public Queue<e> c() {
        return a(0);
    }

    public Queue<e> a(int i) {
        Queue<e> a;
        if (this.c) {
            b();
            if (i <= 0) {
                a = this.a.a();
            } else {
                a = this.a.a(i);
            }
        } else {
            a = this.b.a();
        }
        if (!a.isEmpty()) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("get log from ");
            stringBuilder.append(this.c ? "Database " : "Queue ");
            stringBuilder.append("(");
            stringBuilder.append(a.size());
            stringBuilder.append(")");
            com.samsung.a.a.a.a.i.a.a(stringBuilder.toString());
        }
        return a;
    }

    public void a(List<String> list) {
        if (!list.isEmpty() && this.c) {
            this.a.a((List) list);
        }
    }
}
