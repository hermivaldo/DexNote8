package com.samsung.android.lxd;

import android.os.FileObserver;
import com.samsung.android.lxd.processor.utils.log.Log;

/* compiled from: LxdFileObserver */
/* renamed from: com.samsung.android.lxd.g */
public class C0951g extends FileObserver {
    /* renamed from: a */
    private static final String f2995a = "g";
    /* renamed from: b */
    private C0950a f2996b;

    /* compiled from: LxdFileObserver */
    /* renamed from: com.samsung.android.lxd.g$a */
    public interface C0950a {
        /* renamed from: a */
        void mo693a();
    }

    public C0951g(String str, C0950a c0950a) {
        super(str, 4092);
        this.f2996b = c0950a;
    }

    public void onEvent(int i, String str) {
        if (i == 4 || i == 8 || i == 16 || i == 64 || i == 128 || i == 256 || i == 512 || i == 1024 || i == 2048) {
            this.f2996b.mo693a();
            return;
        }
        String str2 = f2995a;
        str = new StringBuilder();
        str.append("event type :");
        str.append(i);
        Log.m3857i(str2, str.toString());
    }
}
