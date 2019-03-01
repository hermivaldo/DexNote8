package com.samsung.a.a.a.a.g;

import android.content.Context;
import com.samsung.a.a.a.a.c.b;
import com.samsung.a.a.a.a.f.d;
import com.samsung.a.a.a.a.i.c;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/* compiled from: BuildClient */
public class a implements b {
    private static boolean a = true;
    private Context b;
    private com.samsung.a.a.a.b c;
    private List<String> d;

    public a(Context context, com.samsung.a.a.a.b bVar) {
        this.b = context;
        this.c = bVar;
    }

    public void a() {
        this.d = new b(this.b).a();
    }

    public int b() {
        if (this.d.isEmpty()) {
            com.samsung.a.a.a.a.i.a.a("Setting Sender", "No status log");
            return 0;
        }
        Map hashMap = new HashMap();
        hashMap.put("ts", String.valueOf(System.currentTimeMillis()));
        hashMap.put("t", "st");
        long j = 0;
        for (String put : this.d) {
            hashMap.put("sti", put);
            if (d.a(this.b, com.samsung.a.a.a.a.b.a.b(), this.c).e(hashMap) == 0) {
                com.samsung.a.a.a.a.i.a.a("Setting Sender", "Send success");
                j = System.currentTimeMillis();
            } else {
                com.samsung.a.a.a.a.i.a.a("Setting Sender", "Send fail");
            }
        }
        if (j != 0) {
            c.a(this.b).edit().putLong("status_sent_date", j).apply();
        }
        return 0;
    }
}
