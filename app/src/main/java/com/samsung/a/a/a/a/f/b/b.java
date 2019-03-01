package com.samsung.a.a.a.a.f.b;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import com.samsung.a.a.a.a.d.c;
import com.samsung.a.a.a.a.f.a;
import com.samsung.a.a.a.a.f.e;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.concurrent.LinkedBlockingQueue;

/* compiled from: DLSLogSender */
public class b extends a {
    public b(Context context, com.samsung.a.a.a.b bVar) {
        super(context, bVar);
    }

    private int a() {
        NetworkInfo activeNetworkInfo = ((ConnectivityManager) this.a.getSystemService("connectivity")).getActiveNetworkInfo();
        return (activeNetworkInfo == null || !activeNetworkInfo.isConnected()) ? -4 : activeNetworkInfo.getType();
    }

    private int a(int i) {
        if (i == -4) {
            com.samsung.a.a.a.a.i.a.a("DLS Sender", "Network unavailable.");
            return -4;
        } else if (c.a(this.a)) {
            com.samsung.a.a.a.a.i.a.a("DLS Sender", "policy expired. request policy");
            return -6;
        } else if (this.b.p() != i) {
            return 0;
        } else {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("Network unavailable by restrict option:");
            stringBuilder.append(i);
            com.samsung.a.a.a.a.i.a.a("DLS Sender", stringBuilder.toString());
            return -4;
        }
    }

    private void a(int i, com.samsung.a.a.a.a.f.c cVar, Queue<e> queue, int i2, com.samsung.a.a.a.a.c.a aVar) {
        c.b(this.a, i, i2);
        this.f.a(new a(cVar, queue, this.b.a(), this.b.l(), aVar));
    }

    private int a(int i, com.samsung.a.a.a.a.f.c cVar, Queue<e> queue, com.samsung.a.a.a.a.c.a aVar) {
        List arrayList = new ArrayList();
        Iterator it = queue.iterator();
        while (true) {
            int i2 = 0;
            if (!it.hasNext()) {
                return 0;
            }
            Queue linkedBlockingQueue = new LinkedBlockingQueue();
            int a = c.a(this.a, i);
            if (51200 <= a) {
                a = 51200;
            }
            while (it.hasNext()) {
                e eVar = (e) it.next();
                if (eVar.d() == cVar) {
                    if (eVar.c().getBytes().length + i2 > a) {
                        break;
                    }
                    i2 += eVar.c().getBytes().length;
                    linkedBlockingQueue.add(eVar);
                    it.remove();
                    arrayList.add(eVar.a());
                    if (queue.isEmpty()) {
                        this.e.a(arrayList);
                        queue = this.e.a(200);
                        it = queue.iterator();
                    }
                }
            }
            if (linkedBlockingQueue.isEmpty()) {
                return -1;
            }
            this.e.a(arrayList);
            a(i, cVar, linkedBlockingQueue, i2, aVar);
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("send packet : num(");
            stringBuilder.append(linkedBlockingQueue.size());
            stringBuilder.append(") size(");
            stringBuilder.append(i2);
            stringBuilder.append(")");
            com.samsung.a.a.a.a.i.a.a("DLSLogSender", stringBuilder.toString());
        }
    }

    private int a(int i, e eVar, com.samsung.a.a.a.a.c.a aVar, boolean z) {
        if (eVar == null) {
            return -100;
        }
        int length = eVar.c().getBytes().length;
        int a = c.a(this.a, i, length);
        if (a != 0) {
            return a;
        }
        c.b(this.a, i, length);
        com.samsung.a.a.a.a.c.b aVar2 = new a(eVar, this.b.a(), this.b.l(), aVar);
        if (z) {
            com.samsung.a.a.a.a.i.a.a("sync send");
            aVar2.a();
            return aVar2.b();
        }
        this.f.a(aVar2);
        return 0;
    }

    public int e(Map<String, String> map) {
        final int a = a();
        int a2 = a(a);
        if (a2 != 0) {
            c(map);
            if (a2 == -6) {
                c.a(this.a, this.b, this.f, this.c);
                this.e.b();
            }
            return a2;
        }
        com.samsung.a.a.a.a.c.a anonymousClass1 = new com.samsung.a.a.a.a.c.a() {
            public void a(int i, String str, String str2, String str3) {
            }

            public void b(int i, String str, String str2, String str3) {
                b.this.e.a(Long.valueOf(str).longValue(), str2, str3.equals(com.samsung.a.a.a.a.f.c.DEVICE.a()) ? com.samsung.a.a.a.a.f.c.DEVICE : com.samsung.a.a.a.a.f.c.UIX);
                c.b(b.this.a, a, str2.getBytes().length * -1);
            }
        };
        int a3 = a(a, new e(Long.valueOf((String) map.get("ts")).longValue(), b(a(map)), d(map)), anonymousClass1, false);
        if (a3 == -1) {
            return a3;
        }
        Queue a4 = this.e.a(200);
        if (this.e.a()) {
            a(a, com.samsung.a.a.a.a.f.c.UIX, a4, anonymousClass1);
            a(a, com.samsung.a.a.a.a.f.c.DEVICE, a4, anonymousClass1);
        } else {
            while (!a4.isEmpty()) {
                a3 = a(a, (e) a4.poll(), anonymousClass1, false);
                if (a3 == -1) {
                    return a3;
                }
            }
        }
        return a3;
    }

    public int f(Map<String, String> map) {
        int a = a();
        int a2 = a(a);
        if (a2 != 0) {
            if (a2 != -6) {
                return a2;
            }
            com.samsung.a.a.a.a.d.a a3 = c.a(this.a, this.b, this.c, null);
            a3.a();
            a2 = a3.b();
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("get policy sync ");
            stringBuilder.append(a2);
            com.samsung.a.a.a.a.i.a.a(stringBuilder.toString());
            if (a2 != 0) {
                return a2;
            }
        }
        return a(a, new e(Long.valueOf((String) map.get("ts")).longValue(), b(a(map)), d(map)), null, true);
    }
}
