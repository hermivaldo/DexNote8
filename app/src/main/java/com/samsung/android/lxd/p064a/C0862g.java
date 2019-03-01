package com.samsung.android.lxd.p064a;

import com.samsung.android.lxd.fragment.p065a.C0931c.C0929b;
import com.samsung.android.lxd.processor.utils.log.Log;
import java.io.File;
import java.util.Comparator;

/* compiled from: LastModifiedCompare */
/* renamed from: com.samsung.android.lxd.a.g */
public class C0862g implements Comparator {
    /* renamed from: a */
    private static final String f2685a = "g";

    public int compare(Object obj, Object obj2) {
        try {
            obj = ((C0929b) obj).m3727b();
            obj2 = ((C0929b) obj2).m3727b();
            File file = new File(obj);
            obj = new File(obj2);
            if (file.lastModified() > obj.lastModified()) {
                return -1;
            }
            if (file.lastModified() < obj.lastModified()) {
                return 1;
            }
            return 0;
        } catch (Object obj3) {
            obj2 = f2685a;
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("compare: ");
            stringBuilder.append(obj3.toString());
            Log.m3855e(obj2, stringBuilder.toString());
            return 0;
        }
    }
}
