package com.samsung.a.a.a.a.f.c;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;

/* compiled from: DMABinder */
public class a {
    private Context a;
    private com.b.a.a.a.a b;
    private ServiceConnection c;
    private boolean d = false;
    private boolean e = false;

    public a(Context context, final com.samsung.a.a.a.a.a<Void, String> aVar) {
        this.a = context;
        this.c = new ServiceConnection() {
            public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
                try {
                    a.this.b = com.b.a.a.a.a.a.a(iBinder);
                    String a = a.this.b.a();
                    if (a == null) {
                        a.this.c();
                        a.this.d = true;
                        com.samsung.a.a.a.a.i.a.a("DMABinder", "Token failed");
                        return;
                    }
                    a.this.d = false;
                    aVar.a(a);
                    com.samsung.a.a.a.a.i.a.a("DMABinder", "DMA connected");
                } catch (Exception e) {
                    a.this.c();
                    a.this.d = true;
                    com.samsung.a.a.a.a.i.a.a(e.getClass(), e);
                    e.printStackTrace();
                }
            }

            public void onServiceDisconnected(ComponentName componentName) {
                a.this.b = null;
            }
        };
    }

    public boolean a() {
        if (!(this.e || this.d)) {
            try {
                Intent intent = new Intent();
                intent.setClassName("com.sec.android.diagmonagent", "com.sec.android.diagmonagent.sa.receiver.SALogReceiverService");
                this.e = this.a.bindService(intent, this.c, 1);
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append("bind ");
                stringBuilder.append(this.e);
                com.samsung.a.a.a.a.i.a.a("DMABinder", stringBuilder.toString());
            } catch (Exception e) {
                com.samsung.a.a.a.a.i.a.a(e.getClass(), e);
            }
        }
        return this.d;
    }

    public boolean b() {
        return this.d;
    }

    public void c() {
        if (this.b != null && this.e) {
            try {
                this.a.unbindService(this.c);
                this.e = false;
                com.samsung.a.a.a.a.i.a.a("DMABinder", "unbind");
            } catch (Exception e) {
                com.samsung.a.a.a.a.i.a.a(e.getClass(), e);
            }
        }
    }

    public com.b.a.a.a.a d() {
        return this.b;
    }

    public boolean e() {
        return this.e;
    }
}
