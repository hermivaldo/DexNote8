package com.samsung.android.lxd.processor.utils;

import com.samsung.android.lxd.a.o;
import com.samsung.android.lxd.processor.utils.log.Log;
import java.lang.Thread.UncaughtExceptionHandler;

public class LxDUncaughtExceptionHandler implements UncaughtExceptionHandler {
    private static final String TAG = "LxDUncaughtExceptionHandler";

    public void uncaughtException(Thread thread, Throwable th) {
        String str = TAG;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("uncaughtException: classs = ");
        stringBuilder.append(th.getClass());
        stringBuilder.append(", uncaughtException = ");
        stringBuilder.append(th);
        Log.e(str, stringBuilder.toString());
        Log.e(TAG, "StackTrace : ", th);
        o.p();
    }
}
