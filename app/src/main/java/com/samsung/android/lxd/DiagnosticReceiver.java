package com.samsung.android.lxd;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import com.samsung.android.lxd.a.o;
import com.samsung.android.lxd.processor.utils.log.Log;

public class DiagnosticReceiver extends BroadcastReceiver {
    private static final String a = "DiagnosticReceiver";
    private static DiagnosticReceiver b;
    private boolean c = false;
    private Activity d = null;

    public static synchronized DiagnosticReceiver a() {
        DiagnosticReceiver diagnosticReceiver;
        synchronized (DiagnosticReceiver.class) {
            if (b == null) {
                b = new DiagnosticReceiver();
            }
            diagnosticReceiver = b;
        }
        return diagnosticReceiver;
    }

    public void a(Activity activity) {
        this.d = activity;
    }

    /* JADX WARNING: Missing block: B:9:0x003b, code:
            return;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void onReceive(Context context, Intent intent) {
        if (!(context == null || intent == null || intent.getAction() != "android.settings.DIAGNOSTIC_INFO_CHANGED")) {
            boolean n = o.n(this.d);
            String str = a;
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("on Receive isAgreed : ");
            stringBuilder.append(n);
            Log.i(str, stringBuilder.toString());
            if (n) {
                ((e) this.d).F();
            } else {
                ((e) this.d).G();
            }
        }
    }

    public void b() {
        String str = a;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("registerReceiver: ");
        stringBuilder.append(this.c);
        Log.d(str, stringBuilder.toString());
        if (!this.c) {
            LxdApplication.a().registerReceiver(b, d());
            this.c = true;
        }
    }

    public void c() {
        String str = a;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("unregisterReceiver: ");
        stringBuilder.append(this.c);
        Log.d(str, stringBuilder.toString());
        if (this.c) {
            LxdApplication.a().unregisterReceiver(b);
            this.c = false;
        }
    }

    private IntentFilter d() {
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("android.settings.DIAGNOSTIC_INFO_CHANGED");
        return intentFilter;
    }
}
