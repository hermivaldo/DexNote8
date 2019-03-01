package com.samsung.android.lxd;

import android.os.FileObserver;
import com.samsung.android.lxd.processor.utils.log.Log;

/* compiled from: LxdFileObserver */
public class g extends FileObserver {
    private static final String a = "g";
    private a b;

    /* compiled from: LxdFileObserver */
    public interface a {
        void a();
    }

    public g(String str, a aVar) {
        super(str, 4092);
        this.b = aVar;
    }

    public void onEvent(int i, String str) {
        if (i == 4 || i == 8 || i == 16 || i == 64 || i == 128 || i == 256 || i == 512 || i == 1024 || i == 2048) {
            this.b.a();
            return;
        }
        String str2 = a;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("event type :");
        stringBuilder.append(i);
        Log.i(str2, stringBuilder.toString());
    }
}
