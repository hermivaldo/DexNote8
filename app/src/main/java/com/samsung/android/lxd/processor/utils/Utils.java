package com.samsung.android.lxd.processor.utils;

import android.content.Context;
import android.os.Build.VERSION;
import android.os.SemSystemProperties;
import android.view.MotionEvent;
import com.samsung.android.lxd.a.o;
import com.samsung.android.lxd.processor.utils.log.Log;

public class Utils {
    private static final String TAG = "Utils";

    private static boolean isShip() {
        return SemSystemProperties.getBoolean("ro.product_ship", true);
    }

    public static boolean isDebug() {
        return SemSystemProperties.getBoolean("lxd.debug_enable", false);
    }

    public static boolean isSourceTouchScreen(MotionEvent motionEvent) {
        return motionEvent.getSource() == 4098 || motionEvent.getSource() == 20482;
    }

    public static boolean isTouchPad(MotionEvent motionEvent) {
        if (motionEvent.getToolType(0) == 1 || motionEvent.getToolType(0) == 2) {
            return true;
        }
        return false;
    }

    public static boolean isMultiTouch(MotionEvent motionEvent) {
        if (VERSION.SEM_INT >= 2801) {
            if (motionEvent.semGetFlags() != 268435456) {
                return false;
            }
        } else if (!(motionEvent.getFlags() == 335544322 || motionEvent.getFlags() == 335544320)) {
            return false;
        }
        return true;
    }

    public static int getNstVersion() {
        return SemSystemProperties.getInt("linux_on_dex_version", 1);
    }

    public static int getImageVersionNumber(String str) {
        if (str != null && str.startsWith("version : ")) {
            int length = "version : ".length();
            while (true) {
                if (str.charAt(length) < '0' || str.charAt(length) > '9') {
                    length++;
                } else {
                    try {
                        return Integer.valueOf(str.substring(length, length + 3)).intValue();
                    } catch (Exception unused) {
                        String str2 = TAG;
                        StringBuilder stringBuilder = new StringBuilder();
                        stringBuilder.append("Failed to get image version number : ");
                        stringBuilder.append(str);
                        Log.e(str2, stringBuilder.toString());
                    }
                }
            }
        }
        return 0;
    }

    public static String bytesToString(byte[] bArr, int i, int i2) {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i3 = 0; i3 < i2; i3++) {
            byte b = bArr[i + i3];
            if (b < (byte) 32 || b > (byte) 126) {
                stringBuilder.append(String.format("\\x%02x", new Object[]{Byte.valueOf(b)}));
            } else {
                stringBuilder.append((char) b);
            }
        }
        return stringBuilder.toString();
    }

    public static float getScreenDensity(Context context) {
        return context.getApplicationContext().getResources().getDisplayMetrics().xdpi / 160.0f;
    }

    public static boolean isDexMode(Context context) {
        return o.d(context);
    }
}
