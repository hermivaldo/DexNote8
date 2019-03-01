package com.samsung.android.lxd.a;

import com.samsung.android.lxd.fragment.a.c.b;
import com.samsung.android.lxd.processor.utils.log.Log;
import java.io.File;
import java.util.Comparator;

/* compiled from: LastModifiedCompare */
public class g implements Comparator {
    private static final String a = "g";

    public int compare(Object obj, Object obj2) {
        String b;
        try {
            String b2 = ((b) obj).b();
            b = ((b) obj2).b();
            File file = new File(b2);
            File file2 = new File(b);
            if (file.lastModified() > file2.lastModified()) {
                return -1;
            }
            if (file.lastModified() < file2.lastModified()) {
                return 1;
            }
            return 0;
        } catch (NullPointerException e) {
            b = a;
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("compare: ");
            stringBuilder.append(e.toString());
            Log.e(b, stringBuilder.toString());
            return 0;
        }
    }
}
