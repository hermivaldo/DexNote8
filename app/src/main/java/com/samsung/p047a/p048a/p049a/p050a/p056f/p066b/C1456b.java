package com.samsung.p047a.p048a.p049a.p050a.p056f.p066b;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import com.samsung.p047a.p048a.p049a.C0790b;
import com.samsung.p047a.p048a.p049a.p050a.p053c.C0758a;
import com.samsung.p047a.p048a.p049a.p050a.p054d.C0764c;
import com.samsung.p047a.p048a.p049a.p050a.p054d.C1291a;
import com.samsung.p047a.p048a.p049a.p050a.p056f.C0776c;
import com.samsung.p047a.p048a.p049a.p050a.p056f.C0782e;
import com.samsung.p047a.p048a.p049a.p050a.p056f.C1294a;
import com.samsung.p047a.p048a.p049a.p050a.p063i.C0784a;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.concurrent.LinkedBlockingQueue;

/* compiled from: DLSLogSender */
/* renamed from: com.samsung.a.a.a.a.f.b.b */
public class C1456b extends C1294a {
    public C1456b(Context context, C0790b c0790b) {
        super(context, c0790b);
    }

    /* renamed from: a */
    private int m6953a() {
        NetworkInfo activeNetworkInfo = ((ConnectivityManager) this.a.getSystemService("connectivity")).getActiveNetworkInfo();
        if (activeNetworkInfo != null) {
            if (activeNetworkInfo.isConnected()) {
                return activeNetworkInfo.getType();
            }
        }
        return -4;
    }

    /* renamed from: a */
    private int m6954a(int i) {
        if (i == -4) {
            C0784a.m3254a("DLS Sender", "Network unavailable.");
            return -4;
        } else if (C0764c.m3181a(this.a)) {
            C0784a.m3254a("DLS Sender", "policy expired. request policy");
            return -6;
        } else if (this.b.m3287p() != i) {
            return 0;
        } else {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("Network unavailable by restrict option:");
            stringBuilder.append(i);
            C0784a.m3254a("DLS Sender", stringBuilder.toString());
            return -4;
        }
    }

    /* renamed from: a */
    private void m6958a(int i, C0776c c0776c, Queue<C0782e> queue, int i2, C0758a c0758a) {
        C0764c.m3182b(this.a, i, i2);
        this.f.mo649a(new C1295a(c0776c, queue, this.b.m3269a(), this.b.m3283l(), c0758a));
    }

    /* renamed from: a */
    private int m6955a(int i, C0776c c0776c, Queue<C0782e> queue, C0758a c0758a) {
        List arrayList = new ArrayList();
        Iterator it = queue.iterator();
        while (true) {
            int i2 = 0;
            if (!it.hasNext()) {
                return 0;
            }
            Queue linkedBlockingQueue = new LinkedBlockingQueue();
            int a = C0764c.m3173a(this.a, i);
            if (51200 <= a) {
                a = 51200;
            }
            while (it.hasNext()) {
                C0782e c0782e = (C0782e) it.next();
                if (c0782e.m3247d() == c0776c) {
                    if (c0782e.m3246c().getBytes().length + i2 > a) {
                        break;
                    }
                    i2 += c0782e.m3246c().getBytes().length;
                    linkedBlockingQueue.add(c0782e);
                    it.remove();
                    arrayList.add(c0782e.m3240a());
                    if (queue.isEmpty()) {
                        this.e.m3233a(arrayList);
                        queue = this.e.m3227a(200);
                        it = queue.iterator();
                    }
                }
            }
            if (linkedBlockingQueue.isEmpty()) {
                return -1;
            }
            this.e.m3233a(arrayList);
            m6958a(i, c0776c, linkedBlockingQueue, i2, c0758a);
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("send packet : num(");
            stringBuilder.append(linkedBlockingQueue.size());
            stringBuilder.append(") size(");
            stringBuilder.append(i2);
            stringBuilder.append(")");
            C0784a.m3254a("DLSLogSender", stringBuilder.toString());
        }
    }

    /* renamed from: a */
    private int m6956a(int i, C0782e c0782e, C0758a c0758a, boolean z) {
        if (c0782e == null) {
            return -100;
        }
        int length = c0782e.m3246c().getBytes().length;
        int a = C0764c.m3174a(this.a, i, length);
        if (a != 0) {
            return a;
        }
        C0764c.m3182b(this.a, i, length);
        i = new C1295a(c0782e, this.b.m3269a(), this.b.m3283l(), c0758a);
        if (z) {
            C0784a.m3253a("sync send");
            i.mo650a();
            return i.mo651b();
        }
        this.f.mo649a(i);
        return 0;
    }

    /* renamed from: e */
    public int mo1274e(Map<String, String> map) {
        final int a = m6953a();
        int a2 = m6954a(a);
        if (a2 != 0) {
            m6023c(map);
            if (a2 == -6) {
                C0764c.m3178a(this.a, this.b, this.f, this.c);
                this.e.m3235b();
            }
            return a2;
        }
        C0758a c12961 = new C0758a(this) {
            /* renamed from: b */
            final /* synthetic */ C1456b f4358b;

            /* renamed from: a */
            public void mo647a(int i, String str, String str2, String str3) {
            }

            /* renamed from: b */
            public void mo648b(int i, String str, String str2, String str3) {
                this.f4358b.e.m3228a(Long.valueOf(str).longValue(), str2, str3.equals(C0776c.DEVICE.m3218a()) != null ? C0776c.DEVICE : C0776c.UIX);
                C0764c.m3182b(this.f4358b.a, a, str2.getBytes().length * -1);
            }
        };
        int a3 = m6956a(a, new C0782e(Long.valueOf((String) map.get("ts")).longValue(), m6022b(mo1273a(map)), m6024d(map)), c12961, false);
        if (a3 == -1) {
            return a3;
        }
        Queue a4 = this.e.m3227a(200);
        if (this.e.m3234a()) {
            m6955a(a, C0776c.UIX, a4, c12961);
            m6955a(a, C0776c.DEVICE, a4, c12961);
        } else {
            while (!a4.isEmpty()) {
                a3 = m6956a(a, (C0782e) a4.poll(), c12961, false);
                if (a3 == -1) {
                    return a3;
                }
            }
        }
        return a3;
    }

    /* renamed from: f */
    public int mo1275f(Map<String, String> map) {
        int a = m6953a();
        int a2 = m6954a(a);
        if (a2 != 0) {
            if (a2 != -6) {
                return a2;
            }
            C1291a a3 = C0764c.m3175a(this.a, this.b, this.c, null);
            a3.mo650a();
            a2 = a3.mo651b();
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("get policy sync ");
            stringBuilder.append(a2);
            C0784a.m3253a(stringBuilder.toString());
            if (a2 != 0) {
                return a2;
            }
        }
        return m6956a(a, new C0782e(Long.valueOf((String) map.get("ts")).longValue(), m6022b(mo1273a(map)), m6024d(map)), null, true);
    }
}
