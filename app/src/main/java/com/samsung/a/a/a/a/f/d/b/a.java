package com.samsung.a.a.a.a.f.d.b;

import com.samsung.a.a.a.a.f.e;
import java.util.Queue;
import java.util.concurrent.LinkedBlockingQueue;

/* compiled from: QueueManager */
public class a {
    protected LinkedBlockingQueue<e> a;

    public a() {
        this.a = new LinkedBlockingQueue(25);
    }

    public a(int i) {
        if (i < 25) {
            this.a = new LinkedBlockingQueue(25);
        } else if (i > 100) {
            this.a = new LinkedBlockingQueue(100);
        } else {
            this.a = new LinkedBlockingQueue(i);
        }
    }

    public void a(e eVar) {
        if (!this.a.offer(eVar)) {
            com.samsung.a.a.a.a.i.a.a("QueueManager", "queue size over. remove oldest log");
            this.a.poll();
            this.a.offer(eVar);
        }
    }

    public Queue<e> a() {
        return this.a;
    }
}
