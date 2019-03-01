package com.samsung.android.lxd;

import android.app.Activity;
import android.app.UiModeManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Build.VERSION;
import com.samsung.android.lxd.a.l;
import com.samsung.android.lxd.a.o;
import com.samsung.android.lxd.processor.ExecutionType;
import com.samsung.android.lxd.processor.utils.log.Log;

public class DesktopReceiver extends BroadcastReceiver {
    private static final String a = "DesktopReceiver";
    private static DesktopReceiver b;
    private static ExecutionType c = ExecutionType.INVALID;
    private boolean d = false;
    private Activity e = null;

    public static synchronized DesktopReceiver a() {
        DesktopReceiver desktopReceiver;
        synchronized (DesktopReceiver.class) {
            if (b == null) {
                b = new DesktopReceiver();
            }
            desktopReceiver = b;
        }
        return desktopReceiver;
    }

    public void a(Activity activity) {
        this.e = activity;
    }

    public void onReceive(Context context, Intent intent) {
        Log.d(a, "onReceive: ");
        if (context != null && intent != null) {
            int i = 0;
            int intExtra = intent.getIntExtra("unDockNotifyActionId", 0);
            if (intExtra != 0) {
                l.a("018", String.valueOf(1803));
                String str = a;
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append("DesktopReceiver action: ");
                stringBuilder.append(intExtra);
                Log.i(str, stringBuilder.toString());
                if (intExtra == 2) {
                    o.p();
                } else if (intExtra == 1) {
                    o.r();
                }
                return;
            }
            String action = intent.getAction();
            if (action != null) {
                String str2 = a;
                StringBuilder stringBuilder2 = new StringBuilder();
                stringBuilder2.append("onReceive: ");
                stringBuilder2.append(action);
                Log.d(str2, stringBuilder2.toString());
                if (this.d && f.d()) {
                    if (action.equals(VERSION.SDK_INT >= 28 ? UiModeManager.SEM_ACTION_EXIT_DESKTOP_MODE : UiModeManager.SEM_ACTION_EXIT_KNOX_DESKTOP_MODE)) {
                        i = 1;
                    }
                }
                if (i != 0) {
                    o.a(this.e, c);
                }
            }
        }
    }

    public void a(ExecutionType executionType) {
        String str = a;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("registerReceiver: ");
        stringBuilder.append(this.d);
        Log.d(str, stringBuilder.toString());
        if (!this.d) {
            this.d = true;
            c = executionType;
            LxdApplication.a().registerReceiver(b, c());
        }
    }

    public void b() {
        String str = a;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("unregisterReceiver: ");
        stringBuilder.append(this.d);
        Log.d(str, stringBuilder.toString());
        o.r();
        if (this.d) {
            this.d = false;
            c = ExecutionType.INVALID;
            LxdApplication.a().unregisterReceiver(b);
        }
    }

    private IntentFilter c() {
        IntentFilter intentFilter = new IntentFilter();
        if (VERSION.SDK_INT >= 28) {
            intentFilter.addAction(UiModeManager.SEM_ACTION_ENTER_DESKTOP_MODE);
            intentFilter.addAction(UiModeManager.SEM_ACTION_EXIT_DESKTOP_MODE);
        } else {
            intentFilter.addAction(UiModeManager.SEM_ACTION_ENTER_KNOX_DESKTOP_MODE);
            intentFilter.addAction(UiModeManager.SEM_ACTION_EXIT_KNOX_DESKTOP_MODE);
        }
        return intentFilter;
    }
}
