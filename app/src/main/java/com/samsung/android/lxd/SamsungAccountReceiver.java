package com.samsung.android.lxd;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

public class SamsungAccountReceiver extends BroadcastReceiver {
    private static final String a = "SamsungAccountReceiver";

    public void onReceive(Context context, Intent intent) {
        Log.d(a, "onReceive()");
        if (intent == null) {
            Log.e(a, "Invalid params");
        } else if (context == null) {
            Log.e(a, "Invalid context");
        } else {
            if ("com.samsung.account.SAMSUNGACCOUNT_SIGNIN_COMPLETED".equals(intent.getAction())) {
                Log.d(a, "com.samsung.account.SAMSUNGACCOUNT_SIGNIN_COMPLETED");
            } else if ("com.samsung.account.SAMSUNGACCOUNT_SIGNOUT_COMPLETED".equals(intent.getAction())) {
                Log.d(a, "com.samsung.account.SAMSUNGACCOUNT_SIGNOUT_COMPLETED");
            }
        }
    }
}
