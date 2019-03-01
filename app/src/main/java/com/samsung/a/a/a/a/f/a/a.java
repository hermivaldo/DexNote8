package com.samsung.a.a.a.a.f.a;

import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;

/* compiled from: DLCBinder */
public class a {
    private static String a = "com.sec.spp.push";
    private static String b = "com.sec.spp.push.dlc.writer.WriterService";
    private Context c;
    private BroadcastReceiver d;
    private String e;
    private com.samsung.a.a.a.a.a f;
    private boolean g;
    private boolean h;
    private com.b.b.a.a.a.a i;
    private ServiceConnection j;

    public a(Context context) {
        this.g = false;
        this.h = false;
        this.j = new ServiceConnection() {
            public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
                com.samsung.a.a.a.a.i.a.a("DLC Sender", "DLC Client ServiceConnected");
                a.this.i = com.b.b.a.a.a.a.a.a(iBinder);
                if (a.this.d != null) {
                    a.this.c.unregisterReceiver(a.this.d);
                    a.this.d = null;
                }
                if (a.this.f != null) {
                    a.this.f.a(null);
                }
            }

            public void onServiceDisconnected(ComponentName componentName) {
                com.samsung.a.a.a.a.i.a.a("DLC Sender", "Client ServiceDisconnected");
                a.this.i = null;
                a.this.g = false;
            }
        };
        this.c = context;
        this.e = context.getPackageName();
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(this.e);
        stringBuilder.append(".REGISTER_FILTER");
        this.e = stringBuilder.toString();
    }

    public a(Context context, com.samsung.a.a.a.a.a aVar) {
        this(context);
        this.f = aVar;
    }

    public void a() {
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(this.e);
        if (this.d == null) {
            this.d = new BroadcastReceiver() {
                public void onReceive(Context context, Intent intent) {
                    a.this.h = false;
                    if (intent == null) {
                        com.samsung.a.a.a.a.i.a.a("DLC Sender", "dlc register reply fail");
                        return;
                    }
                    String action = intent.getAction();
                    Bundle extras = intent.getExtras();
                    if (action == null || extras == null) {
                        com.samsung.a.a.a.a.i.a.a("DLC Sender", "dlc register reply fail");
                        return;
                    }
                    if (action.equals(a.this.e)) {
                        action = extras.getString("EXTRA_STR");
                        int i = extras.getInt("EXTRA_RESULT_CODE");
                        StringBuilder stringBuilder = new StringBuilder();
                        stringBuilder.append("register DLC result:");
                        stringBuilder.append(action);
                        com.samsung.a.a.a.a.i.a.a("DLC Sender", stringBuilder.toString());
                        if (i < 0) {
                            StringBuilder stringBuilder2 = new StringBuilder();
                            stringBuilder2.append("register DLC result fail:");
                            stringBuilder2.append(action);
                            com.samsung.a.a.a.a.i.a.a("DLC Sender", stringBuilder2.toString());
                        } else {
                            a.this.a(extras.getString("EXTRA_STR_ACTION"));
                        }
                    }
                }
            };
        }
        this.c.registerReceiver(this.d, intentFilter);
    }

    public void b() {
        if (this.d == null) {
            a();
        }
        if (this.h) {
            com.samsung.a.a.a.a.i.a.a("DLCBinder", "already send register request");
            return;
        }
        Intent intent = new Intent("com.sec.spp.push.REQUEST_REGISTER");
        intent.putExtra("EXTRA_PACKAGENAME", this.c.getPackageName());
        intent.putExtra("EXTRA_INTENTFILTER", this.e);
        intent.setPackage("com.sec.spp.push");
        this.c.sendBroadcast(intent);
        this.h = true;
        com.samsung.a.a.a.a.i.a.a("DLCBinder", "send register Request");
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("send register Request:");
        stringBuilder.append(this.c.getPackageName());
        com.samsung.a.a.a.a.i.a.a(stringBuilder.toString());
    }

    private void a(String str) {
        if (this.g) {
            e();
        }
        try {
            Intent intent = new Intent(str);
            intent.setClassName(a, b);
            this.g = this.c.bindService(intent, this.j, 1);
            com.samsung.a.a.a.a.i.a.a("DLCBinder", "bind");
        } catch (Exception e) {
            com.samsung.a.a.a.a.i.a.a(getClass(), e);
        }
    }

    private void e() {
        if (this.g) {
            try {
                com.samsung.a.a.a.a.i.a.a("DLCBinder", "unbind");
                this.c.unbindService(this.j);
                this.g = false;
            } catch (Exception e) {
                com.samsung.a.a.a.a.i.a.a(getClass(), e);
            }
        }
    }

    public boolean c() {
        return this.g;
    }

    public com.b.b.a.a.a.a d() {
        return this.i;
    }
}
