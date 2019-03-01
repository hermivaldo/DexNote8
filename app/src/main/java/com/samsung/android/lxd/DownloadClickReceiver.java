package com.samsung.android.lxd;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import com.samsung.android.lxd.a.o;
import com.samsung.android.lxd.processor.utils.log.Log;

public class DownloadClickReceiver extends BroadcastReceiver {
    private static final String a = "DownloadClickReceiver";
    private static DownloadClickReceiver d;
    private Activity b = null;
    private boolean c = false;

    public static synchronized DownloadClickReceiver a() {
        DownloadClickReceiver downloadClickReceiver;
        synchronized (DownloadClickReceiver.class) {
            if (d == null) {
                d = new DownloadClickReceiver();
            }
            downloadClickReceiver = d;
        }
        return downloadClickReceiver;
    }

    public void a(Activity activity) {
        this.b = activity;
    }

    public void onReceive(Context context, Intent intent) {
        Log.i(a, "onReceive click intent ");
        try {
            this.b.startActivity(o.j(this.b));
        } catch (Exception e) {
            String str = a;
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("Resume activity error !");
            stringBuilder.append(e);
            Log.e(str, stringBuilder.toString());
        }
    }

    public void b() {
        String str = a;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("registerReceiver: ");
        stringBuilder.append(this.c);
        Log.d(str, stringBuilder.toString());
        if (!this.c) {
            LxdApplication.a().registerReceiver(d, d());
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
            LxdApplication.a().unregisterReceiver(d);
            this.c = false;
        }
    }

    private IntentFilter d() {
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("android.intent.action.DOWNLOAD_NOTIFICATION_CLICKED");
        intentFilter.setPriority(100);
        return intentFilter;
    }
}
