package com.samsung.android.lxd.a;

import android.content.Context;
import android.media.MediaScannerConnection;
import android.media.MediaScannerConnection.OnScanCompletedListener;
import android.net.Uri;
import android.os.Handler;
import android.os.Handler.Callback;
import android.os.Message;
import com.samsung.android.lxd.LxdApplication;
import com.samsung.android.lxd.d;
import com.samsung.android.lxd.d.a;
import com.samsung.android.lxd.processor.utils.log.Log;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

/* compiled from: MediaScanHelper */
public class h {
    private static final String a = "h";
    private static Context b;
    private static h c;
    private static k d;
    private static k e;
    private static Handler g = new Handler(new Callback() {
        public boolean handleMessage(Message message) {
            String d = h.a;
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("handleMessage: ");
            stringBuilder.append(message.what);
            Log.d(d, stringBuilder.toString());
            switch (message.what) {
                case 1:
                    h.c(o.a());
                    break;
                case 2:
                    if (o.d() != null) {
                        h.c(o.d());
                        break;
                    }
                    break;
            }
            return true;
        }
    });
    private final a f = new a() {
        public void a(String str) {
            String d = h.a;
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("onExtSdcardEvent: ");
            stringBuilder.append(str);
            Log.d(d, stringBuilder.toString());
            if ("android.intent.action.MEDIA_MOUNTED".equals(str)) {
                h.this.g();
            } else {
                h.this.h();
            }
        }
    };

    public static synchronized h a() {
        h hVar;
        synchronized (h.class) {
            if (c == null) {
                b = LxdApplication.a();
                d = b(o.a());
                c = new h();
            }
            hVar = c;
        }
        return hVar;
    }

    private void e() {
        f();
        d.a();
    }

    private void f() {
        d.b();
    }

    private void g() {
        h();
        if (o.d() != null) {
            e = b(o.d());
            e.a();
        }
    }

    private void h() {
        if (e != null) {
            e.b();
            e = null;
        }
    }

    public void b() {
        e();
        g();
        d.a().a(this.f);
    }

    public void c() {
        f();
        h();
        d.a().b(this.f);
    }

    private static k b(String str) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(str);
        stringBuilder.append("LoD_Share");
        return new k(stringBuilder.toString(), 4032, new k.a() {
            public void a(int i, File file) {
                String d = h.a;
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append("event type :");
                stringBuilder.append(Integer.toHexString(i));
                stringBuilder.append(", ");
                stringBuilder.append(file.getAbsolutePath());
                Log.d(d, stringBuilder.toString());
                if (i == 64 || i == 128 || i == 256 || i == 512 || i == 1024) {
                    h.b(file.getAbsolutePath().contains(o.a()) ? 1 : 2);
                }
            }
        });
    }

    private static boolean b(int i) {
        g.removeMessages(i);
        g.sendMessageDelayed(g.obtainMessage(i), 1000);
        return true;
    }

    private static void c(String str) {
        List arrayList = new ArrayList();
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(str);
        stringBuilder.append("LoD_Share");
        arrayList.add(stringBuilder.toString());
        str = a;
        stringBuilder = new StringBuilder();
        stringBuilder.append("scanMediaFile: ");
        stringBuilder.append(arrayList.toString());
        Log.d(str, stringBuilder.toString());
        MediaScannerConnection.semScanDirectories(b, (String[]) arrayList.toArray(new String[arrayList.size()]), new OnScanCompletedListener() {
            public void onScanCompleted(String str, Uri uri) {
                String d = h.a;
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append("onScanCompleted(");
                stringBuilder.append(str);
                stringBuilder.append(", ");
                stringBuilder.append(uri.toString());
                stringBuilder.append(")");
                Log.d(d, stringBuilder.toString());
            }
        });
    }
}
