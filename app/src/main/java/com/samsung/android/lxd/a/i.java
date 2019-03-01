package com.samsung.android.lxd.a;

import android.os.Build;
import android.os.SemSystemProperties;
import android.text.TextUtils;
import com.samsung.android.lxd.LxdApplication;
import com.samsung.android.lxd.processor.utils.log.Log;

/* compiled from: ModelTypeUtils */
public class i {
    public static final String[] a = new String[]{"N960"};
    public static final String[] b = new String[]{"T830", "T835", "T837"};
    private static final String c = "i";

    public static String b() {
        return "1024";
    }

    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static String a() {
        Object obj;
        StringBuilder stringBuilder;
        String str = "2662";
        int b = (int) (o.b(LxdApplication.a()) / 1073741824);
        String d = d();
        switch (d.hashCode()) {
            case -1734413249:
                if (d.equals("WINNER")) {
                    obj = 3;
                    break;
                }
            case 64397463:
                if (d.equals("CROWN")) {
                    obj = null;
                    break;
                }
            case 79219744:
                if (d.equals("STAR2")) {
                    obj = 1;
                    break;
                }
            case 79578230:
                if (d.equals("TABS4")) {
                    obj = 4;
                    break;
                }
            case 1955976719:
                if (d.equals("BEYOND")) {
                    obj = 2;
                    break;
                }
            default:
                obj = -1;
                break;
        }
        switch (obj) {
            case null:
            case 1:
            case 2:
            case 3:
                if (b > 6) {
                    str = "4710";
                    break;
                }
                str = "2662";
                break;
            case 4:
                if (b > 4) {
                    str = "2355";
                    break;
                }
                str = "1332";
                break;
            default:
                d = c;
                stringBuilder = new StringBuilder();
                stringBuilder.append("getCgroupMemLimit: undefined model + ");
                stringBuilder.append(Build.MODEL);
                Log.e(d, stringBuilder.toString());
                break;
        }
        d = c;
        stringBuilder = new StringBuilder();
        stringBuilder.append("getCgroupMemLimit: totalMem: ");
        stringBuilder.append(b);
        stringBuilder.append(", ");
        stringBuilder.append("CGroup Mem: ");
        stringBuilder.append(str);
        Log.d(d, stringBuilder.toString());
        return str;
    }

    public static boolean c() {
        return !TextUtils.isEmpty(d());
    }

    private static String d() {
        String str = c;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("getDeviceName: model: ");
        stringBuilder.append(Build.MODEL);
        stringBuilder.append(", device: ");
        stringBuilder.append(Build.DEVICE);
        Log.i(str, stringBuilder.toString());
        str = a(Build.DEVICE);
        if (str != null) {
            return str;
        }
        str = SemSystemProperties.get("ro.product.vendor.device");
        if (TextUtils.isEmpty(str)) {
            Log.i(c, "vendorDeviceName is empty");
            str = SemSystemProperties.get("ro.vendor.product.device");
        }
        String str2 = c;
        StringBuilder stringBuilder2 = new StringBuilder();
        stringBuilder2.append("vendorDeviceName: ");
        stringBuilder2.append(str);
        Log.i(str2, stringBuilder2.toString());
        str = a(str);
        if (str != null) {
            return str;
        }
        str = e();
        if (str != null) {
            return str;
        }
        return o.e() ? "CROWN" : "";
    }

    private static String a(String str) {
        String str2 = c;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("getDeviceNameByDevice: ");
        stringBuilder.append(str);
        Log.d(str2, stringBuilder.toString());
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
        if (str.startsWith("winner")) {
            return "WINNER";
        }
        return null;
    }

    private static String e() {
        Log.d(c, "getDeviceNameByModel: ");
        if (a(a)) {
            return "CROWN";
        }
        return a(b) ? "TABS4" : null;
    }

    private static boolean a(String[] strArr) {
        String str = Build.MODEL;
        for (CharSequence contains : strArr) {
            if (str.contains(contains)) {
                return true;
            }
        }
        return false;
    }
}
