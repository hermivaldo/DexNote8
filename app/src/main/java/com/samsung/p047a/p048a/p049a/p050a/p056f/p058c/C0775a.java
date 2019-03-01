package com.samsung.p047a.p048a.p049a.p050a.p056f.p058c;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import com.p039b.p040a.p041a.p042a.C0748a;
import com.p039b.p040a.p041a.p042a.C0748a.C1284a;
import com.samsung.p047a.p048a.p049a.p050a.C0754a;
import com.samsung.p047a.p048a.p049a.p050a.p063i.C0784a;

/* compiled from: DMABinder */
/* renamed from: com.samsung.a.a.a.a.f.c.a */
public class C0775a {
    /* renamed from: a */
    private Context f2452a;
    /* renamed from: b */
    private C0748a f2453b;
    /* renamed from: c */
    private ServiceConnection f2454c;
    /* renamed from: d */
    private boolean f2455d = false;
    /* renamed from: e */
    private boolean f2456e = false;

    public C0775a(Context context, final C0754a<Void, String> c0754a) {
        this.f2452a = context;
        this.f2454c = new ServiceConnection(this) {
            /* renamed from: b */
            final /* synthetic */ C0775a f2451b;

            public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
                try {
                    this.f2451b.f2453b = C1284a.m6000a(iBinder);
                    iBinder = this.f2451b.f2453b.mo640a();
                    if (iBinder == null) {
                        this.f2451b.m3215c();
                        this.f2451b.f2455d = true;
                        C0784a.m3254a("DMABinder", "Token failed");
                        return;
                    }
                    this.f2451b.f2455d = false;
                    c0754a.mo646a(iBinder);
                    C0784a.m3254a("DMABinder", "DMA connected");
                } catch (Exception e) {
                    this.f2451b.m3215c();
                    this.f2451b.f2455d = true;
                    C0784a.m3252a(e.getClass(), e);
                    e.printStackTrace();
                }
            }

            public void onServiceDisconnected(ComponentName componentName) {
                this.f2451b.f2453b = null;
            }
        };
    }

    /* renamed from: a */
    public boolean m3213a() {
        if (!(this.f2456e || this.f2455d)) {
            try {
                Intent intent = new Intent();
                intent.setClassName("com.sec.android.diagmonagent", "com.sec.android.diagmonagent.sa.receiver.SALogReceiverService");
                this.f2456e = this.f2452a.bindService(intent, this.f2454c, 1);
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append("bind ");
                stringBuilder.append(this.f2456e);
                C0784a.m3254a("DMABinder", stringBuilder.toString());
            } catch (Exception e) {
                C0784a.m3252a(e.getClass(), e);
            }
        }
        return this.f2455d;
    }

    /* renamed from: b */
    public boolean m3214b() {
        return this.f2455d;
    }

    /* renamed from: c */
    public void m3215c() {
        if (this.f2453b != null && this.f2456e) {
            try {
                this.f2452a.unbindService(this.f2454c);
                this.f2456e = false;
                C0784a.m3254a("DMABinder", "unbind");
            } catch (Exception e) {
                C0784a.m3252a(e.getClass(), e);
            }
        }
    }

    /* renamed from: d */
    public C0748a m3216d() {
        return this.f2453b;
    }

    /* renamed from: e */
    public boolean m3217e() {
        return this.f2456e;
    }
}
