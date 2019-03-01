package com.samsung.android.lxd.p064a;

import android.os.Build;
import android.os.SemSystemProperties;
import android.text.TextUtils;
import com.samsung.android.lxd.processor.utils.log.Log;

/* compiled from: ModelTypeUtils */
/* renamed from: com.samsung.android.lxd.a.i */
public class C0866i {
    /* renamed from: a */
    public static final String[] f2693a = new String[]{"N960"};
    /* renamed from: b */
    public static final String[] f2694b = new String[]{"T830", "T835", "T837"};
    /* renamed from: c */
    private static final String f2695c = "i";

    /* renamed from: b */
    public static String m3415b() {
        return "1024";
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    /* renamed from: a */
    public static java.lang.String m3412a() {
        /*
        r0 = "2662";
        r1 = com.samsung.android.lxd.LxdApplication.m3344a();
        r1 = com.samsung.android.lxd.p064a.C0877o.m3483b(r1);
        r3 = 1073741824; // 0x40000000 float:2.0 double:5.304989477E-315;
        r1 = r1 / r3;
        r1 = (int) r1;
        r2 = com.samsung.android.lxd.p064a.C0866i.m3417d();
        r3 = r2.hashCode();
        r4 = 4;
        switch(r3) {
            case -1734413249: goto L_0x0044;
            case 64397463: goto L_0x003a;
            case 79219744: goto L_0x0030;
            case 79578230: goto L_0x0026;
            case 1955976719: goto L_0x001c;
            default: goto L_0x001b;
        };
    L_0x001b:
        goto L_0x004e;
    L_0x001c:
        r3 = "BEYOND";
        r2 = r2.equals(r3);
        if (r2 == 0) goto L_0x004e;
    L_0x0024:
        r2 = 2;
        goto L_0x004f;
    L_0x0026:
        r3 = "TABS4";
        r2 = r2.equals(r3);
        if (r2 == 0) goto L_0x004e;
    L_0x002e:
        r2 = r4;
        goto L_0x004f;
    L_0x0030:
        r3 = "STAR2";
        r2 = r2.equals(r3);
        if (r2 == 0) goto L_0x004e;
    L_0x0038:
        r2 = 1;
        goto L_0x004f;
    L_0x003a:
        r3 = "CROWN";
        r2 = r2.equals(r3);
        if (r2 == 0) goto L_0x004e;
    L_0x0042:
        r2 = 0;
        goto L_0x004f;
    L_0x0044:
        r3 = "WINNER";
        r2 = r2.equals(r3);
        if (r2 == 0) goto L_0x004e;
    L_0x004c:
        r2 = 3;
        goto L_0x004f;
    L_0x004e:
        r2 = -1;
    L_0x004f:
        switch(r2) {
            case 0: goto L_0x0073;
            case 1: goto L_0x0073;
            case 2: goto L_0x0073;
            case 3: goto L_0x0073;
            case 4: goto L_0x006b;
            default: goto L_0x0052;
        };
    L_0x0052:
        r2 = f2695c;
        r3 = new java.lang.StringBuilder;
        r3.<init>();
        r4 = "getCgroupMemLimit: undefined model + ";
        r3.append(r4);
        r4 = android.os.Build.MODEL;
        r3.append(r4);
        r3 = r3.toString();
        com.samsung.android.lxd.processor.utils.log.Log.m3855e(r2, r3);
        goto L_0x007b;
    L_0x006b:
        if (r1 > r4) goto L_0x0070;
    L_0x006d:
        r0 = "1332";
        goto L_0x007b;
    L_0x0070:
        r0 = "2355";
        goto L_0x007b;
    L_0x0073:
        r0 = 6;
        if (r1 > r0) goto L_0x0079;
    L_0x0076:
        r0 = "2662";
        goto L_0x007b;
    L_0x0079:
        r0 = "4710";
    L_0x007b:
        r2 = f2695c;
        r3 = new java.lang.StringBuilder;
        r3.<init>();
        r4 = "getCgroupMemLimit: totalMem: ";
        r3.append(r4);
        r3.append(r1);
        r1 = ", ";
        r3.append(r1);
        r1 = "CGroup Mem: ";
        r3.append(r1);
        r3.append(r0);
        r1 = r3.toString();
        com.samsung.android.lxd.processor.utils.log.Log.m3853d(r2, r1);
        return r0;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.samsung.android.lxd.a.i.a():java.lang.String");
    }

    /* renamed from: c */
    public static boolean m3416c() {
        return !TextUtils.isEmpty(C0866i.m3417d());
    }

    /* renamed from: d */
    private static String m3417d() {
        String str = f2695c;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("getDeviceName: model: ");
        stringBuilder.append(Build.MODEL);
        stringBuilder.append(", device: ");
        stringBuilder.append(Build.DEVICE);
        Log.m3857i(str, stringBuilder.toString());
        str = C0866i.m3413a(Build.DEVICE);
        if (str != null) {
            return str;
        }
        str = SemSystemProperties.get("ro.product.vendor.device");
        if (TextUtils.isEmpty(str)) {
            Log.m3857i(f2695c, "vendorDeviceName is empty");
            str = SemSystemProperties.get("ro.vendor.product.device");
        }
        String str2 = f2695c;
        StringBuilder stringBuilder2 = new StringBuilder();
        stringBuilder2.append("vendorDeviceName: ");
        stringBuilder2.append(str);
        Log.m3857i(str2, stringBuilder2.toString());
        str = C0866i.m3413a(str);
        if (str != null) {
            return str;
        }
        str = C0866i.m3418e();
        if (str != null) {
            return str;
        }
        return C0877o.m3503e() ? "CROWN" : "";
    }

    /* renamed from: a */
    private static String m3413a(String str) {
        String str2 = f2695c;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("getDeviceNameByDevice: ");
        stringBuilder.append(str);
        Log.m3853d(str2, stringBuilder.toString());
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        if (str.startsWith("crown")) {
            return "CROWN";
        }
        if (str.startsWith("gts4")) {
            return "TABS4";
        }
        if (str.startsWith("star2")) {
            return "STAR2";
        }
        if (str.startsWith("beyond")) {
            return "BEYOND";
        }
        if (str.startsWith("winner") != null) {
            return "WINNER";
        }
        return null;
    }

    /* renamed from: e */
    private static String m3418e() {
        Log.m3853d(f2695c, "getDeviceNameByModel: ");
        if (C0866i.m3414a(f2693a)) {
            return "CROWN";
        }
        return C0866i.m3414a(f2694b) ? "TABS4" : null;
    }

    /* renamed from: a */
    private static boolean m3414a(String[] strArr) {
        String str = Build.MODEL;
        for (CharSequence contains : strArr) {
            if (str.contains(contains)) {
                return 1;
            }
        }
        return false;
    }
}
