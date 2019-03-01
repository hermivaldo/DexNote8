package com.samsung.p047a.p048a.p049a.p050a.p056f.p059d.p061b;

import com.samsung.p047a.p048a.p049a.p050a.p056f.C0782e;
import com.samsung.p047a.p048a.p049a.p050a.p063i.C0784a;
import java.util.Queue;
import java.util.concurrent.LinkedBlockingQueue;

/* compiled from: QueueManager */
/* renamed from: com.samsung.a.a.a.a.f.d.b.a */
public class C0780a {
    /* renamed from: a */
    protected LinkedBlockingQueue<C0782e> f2471a;

    public C0780a() {
        this.f2471a = new LinkedBlockingQueue(25);
    }

    public C0780a(int i) {
        if (i < 25) {
            this.f2471a = new LinkedBlockingQueue(25);
        } else if (i > 100) {
            this.f2471a = new LinkedBlockingQueue(100);
        } else {
            this.f2471a = new LinkedBlockingQueue(i);
        }
    }

    /* renamed from: a */
    public void m3238a(C0782e c0782e) {
        if (!this.f2471a.offer(c0782e)) {
            C0784a.m3254a("QueueManager", "queue size over. remove oldest log");
            this.f2471a.poll();
            this.f2471a.offer(c0782e);
        }
    }

    /* renamed from: a */
    public Queue<C0782e> m3237a() {
        return this.f2471a;
    }
}
