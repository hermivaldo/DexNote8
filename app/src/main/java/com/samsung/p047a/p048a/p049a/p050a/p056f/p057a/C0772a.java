package com.samsung.p047a.p048a.p049a.p050a.p056f.p057a;

import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.ServiceConnection;
import android.os.IBinder;
import com.p039b.p043b.p044a.p045a.p046a.C0749a;
import com.p039b.p043b.p044a.p045a.p046a.C0749a.C1286a;
import com.samsung.p047a.p048a.p049a.p050a.C0754a;
import com.samsung.p047a.p048a.p049a.p050a.p063i.C0784a;

/* compiled from: DLCBinder */
/* renamed from: com.samsung.a.a.a.a.f.a.a */
public class C0772a {
    /* renamed from: a */
    private static String f2440a = "com.sec.spp.push";
    /* renamed from: b */
    private static String f2441b = "com.sec.spp.push.dlc.writer.WriterService";
    /* renamed from: c */
    private Context f2442c;
    /* renamed from: d */
    private BroadcastReceiver f2443d;
    /* renamed from: e */
    private String f2444e;
    /* renamed from: f */
    private C0754a f2445f;
    /* renamed from: g */
    private boolean f2446g;
    /* renamed from: h */
    private boolean f2447h;
    /* renamed from: i */
    private C0749a f2448i;
    /* renamed from: j */
    private ServiceConnection f2449j;

    /* compiled from: DLCBinder */
    /* renamed from: com.samsung.a.a.a.a.f.a.a$1 */
    class C07701 implements ServiceConnection {
        /* renamed from: a */
        final /* synthetic */ C0772a f2438a;

        C07701(C0772a c0772a) {
            this.f2438a = c0772a;
        }

        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            C0784a.m3254a("DLC Sender", "DLC Client ServiceConnected");
            this.f2438a.f2448i = C1286a.m6005a(iBinder);
            if (this.f2438a.f2443d != null) {
                this.f2438a.f2442c.unregisterReceiver(this.f2438a.f2443d);
                this.f2438a.f2443d = null;
            }
            if (this.f2438a.f2445f != null) {
                this.f2438a.f2445f.mo646a(null);
            }
        }

        public void onServiceDisconnected(ComponentName componentName) {
            C0784a.m3254a("DLC Sender", "Client ServiceDisconnected");
            this.f2438a.f2448i = null;
            this.f2438a.f2446g = null;
        }
    }

    /* compiled from: DLCBinder */
    /* renamed from: com.samsung.a.a.a.a.f.a.a$2 */
    class C07712 extends BroadcastReceiver {
        /* renamed from: a */
        final /* synthetic */ C0772a f2439a;

        C07712(C0772a c0772a) {
            this.f2439a = c0772a;
        }

        public void onReceive(Context context, Intent intent) {
            this.f2439a.f2447h = false;
            if (intent == null) {
                C0784a.m3254a("DLC Sender", "dlc register reply fail");
                return;
            }
            context = intent.getAction();
            intent = intent.getExtras();
            if (context != null) {
                if (intent != null) {
                    if (context.equals(this.f2439a.f2444e) != null) {
                        context = intent.getString("EXTRA_STR");
                        int i = intent.getInt("EXTRA_RESULT_CODE");
                        StringBuilder stringBuilder = new StringBuilder();
                        stringBuilder.append("register DLC result:");
                        stringBuilder.append(context);
                        C0784a.m3254a("DLC Sender", stringBuilder.toString());
                        if (i < 0) {
                            intent = new StringBuilder();
                            intent.append("register DLC result fail:");
                            intent.append(context);
                            C0784a.m3254a("DLC Sender", intent.toString());
                        } else {
                            this.f2439a.m3197a(intent.getString("EXTRA_STR_ACTION"));
                        }
                    }
                    return;
                }
            }
            C0784a.m3254a("DLC Sender", "dlc register reply fail");
        }
    }

    public C0772a(Context context) {
        this.f2446g = false;
        this.f2447h = false;
        this.f2449j = new C07701(this);
        this.f2442c = context;
        this.f2444e = context.getPackageName();
        context = new StringBuilder();
        context.append(this.f2444e);
        context.append(".REGISTER_FILTER");
        this.f2444e = context.toString();
    }

    public C0772a(Context context, C0754a c0754a) {
        this(context);
        this.f2445f = c0754a;
    }

    /* renamed from: a */
    public void m3204a() {
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(this.f2444e);
        if (this.f2443d == null) {
            this.f2443d = new C07712(this);
        }
        this.f2442c.registerReceiver(this.f2443d, intentFilter);
    }

    /* renamed from: b */
    public void m3205b() {
        if (this.f2443d == null) {
            m3204a();
        }
        if (this.f2447h) {
            C0784a.m3254a("DLCBinder", "already send register request");
            return;
        }
        Intent intent = new Intent("com.sec.spp.push.REQUEST_REGISTER");
        intent.putExtra("EXTRA_PACKAGENAME", this.f2442c.getPackageName());
        intent.putExtra("EXTRA_INTENTFILTER", this.f2444e);
        intent.setPackage("com.sec.spp.push");
        this.f2442c.sendBroadcast(intent);
        this.f2447h = true;
        C0784a.m3254a("DLCBinder", "send register Request");
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("send register Request:");
        stringBuilder.append(this.f2442c.getPackageName());
        C0784a.m3253a(stringBuilder.toString());
    }

    /* renamed from: a */
    private void m3197a(String str) {
        if (this.f2446g) {
            m3203e();
        }
        try {
            Intent intent = new Intent(str);
            intent.setClassName(f2440a, f2441b);
            this.f2446g = this.f2442c.bindService(intent, this.f2449j, 1);
            C0784a.m3254a("DLCBinder", "bind");
        } catch (Exception e) {
            C0784a.m3252a(getClass(), e);
        }
    }

    /* renamed from: e */
    private void m3203e() {
        if (this.f2446g) {
            try {
                C0784a.m3254a("DLCBinder", "unbind");
                this.f2442c.unbindService(this.f2449j);
                this.f2446g = false;
            } catch (Exception e) {
                C0784a.m3252a(getClass(), e);
            }
        }
    }

    /* renamed from: c */
    public boolean m3206c() {
        return this.f2446g;
    }

    /* renamed from: d */
    public C0749a m3207d() {
        return this.f2448i;
    }
}
