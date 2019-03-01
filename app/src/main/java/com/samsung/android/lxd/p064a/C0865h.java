package com.samsung.android.lxd.p064a;

import android.content.Context;
import android.media.MediaScannerConnection;
import android.media.MediaScannerConnection.OnScanCompletedListener;
import android.net.Uri;
import android.os.Handler;
import android.os.Handler.Callback;
import android.os.Message;
import com.samsung.android.lxd.C0888d;
import com.samsung.android.lxd.C0888d.C0887a;
import com.samsung.android.lxd.LxdApplication;
import com.samsung.android.lxd.p064a.C0872k.C0870a;
import com.samsung.android.lxd.processor.utils.log.Log;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

/* compiled from: MediaScanHelper */
/* renamed from: com.samsung.android.lxd.a.h */
public class C0865h {
    /* renamed from: a */
    private static final String f2686a = "h";
    /* renamed from: b */
    private static Context f2687b;
    /* renamed from: c */
    private static C0865h f2688c;
    /* renamed from: d */
    private static C0872k f2689d;
    /* renamed from: e */
    private static C0872k f2690e;
    /* renamed from: g */
    private static Handler f2691g = new Handler(new C08633());
    /* renamed from: f */
    private final C0887a f2692f = new C13451(this);

    /* compiled from: MediaScanHelper */
    /* renamed from: com.samsung.android.lxd.a.h$3 */
    static class C08633 implements Callback {
        C08633() {
        }

        public boolean handleMessage(Message message) {
            String d = C0865h.f2686a;
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("handleMessage: ");
            stringBuilder.append(message.what);
            Log.m3853d(d, stringBuilder.toString());
            switch (message.what) {
                case 1:
                    C0865h.m3404c(C0877o.m3458a());
                    break;
                case 2:
                    if (C0877o.m3497d() != null) {
                        C0865h.m3404c(C0877o.m3497d());
                        break;
                    }
                    break;
                default:
                    break;
            }
            return true;
        }
    }

    /* compiled from: MediaScanHelper */
    /* renamed from: com.samsung.android.lxd.a.h$4 */
    static class C08644 implements OnScanCompletedListener {
        C08644() {
        }

        public void onScanCompleted(String str, Uri uri) {
            String d = C0865h.f2686a;
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("onScanCompleted(");
            stringBuilder.append(str);
            stringBuilder.append(", ");
            stringBuilder.append(uri.toString());
            stringBuilder.append(")");
            Log.m3853d(d, stringBuilder.toString());
        }
    }

    /* compiled from: MediaScanHelper */
    /* renamed from: com.samsung.android.lxd.a.h$1 */
    class C13451 implements C0887a {
        /* renamed from: a */
        final /* synthetic */ C0865h f4422a;

        C13451(C0865h c0865h) {
            this.f4422a = c0865h;
        }

        /* renamed from: a */
        public void mo697a(String str) {
            String d = C0865h.f2686a;
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("onExtSdcardEvent: ");
            stringBuilder.append(str);
            Log.m3853d(d, stringBuilder.toString());
            if ("android.intent.action.MEDIA_MOUNTED".equals(str) != null) {
                this.f4422a.m3408g();
            } else {
                this.f4422a.m3409h();
            }
        }
    }

    /* compiled from: MediaScanHelper */
    /* renamed from: com.samsung.android.lxd.a.h$2 */
    static class C13462 implements C0870a {
        C13462() {
        }

        /* renamed from: a */
        public void mo698a(int i, File file) {
            String d = C0865h.f2686a;
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("event type :");
            stringBuilder.append(Integer.toHexString(i));
            stringBuilder.append(", ");
            stringBuilder.append(file.getAbsolutePath());
            Log.m3853d(d, stringBuilder.toString());
            if (i == 64 || i == 128 || i == 256 || i == 512 || i == 1024) {
                C0865h.m3403b(file.getAbsolutePath().contains(C0877o.m3458a()) ? 1 : 2);
            }
        }
    }

    /* renamed from: a */
    public static synchronized C0865h m3397a() {
        C0865h c0865h;
        synchronized (C0865h.class) {
            if (f2688c == null) {
                f2687b = LxdApplication.m3344a();
                f2689d = C0865h.m3401b(C0877o.m3458a());
                f2688c = new C0865h();
            }
            c0865h = f2688c;
        }
        return c0865h;
    }

    /* renamed from: e */
    private void m3406e() {
        m3407f();
        f2689d.m3428a();
    }

    /* renamed from: f */
    private void m3407f() {
        f2689d.m3429b();
    }

    /* renamed from: g */
    private void m3408g() {
        m3409h();
        if (C0877o.m3497d() != null) {
            f2690e = C0865h.m3401b(C0877o.m3497d());
            f2690e.m3428a();
        }
    }

    /* renamed from: h */
    private void m3409h() {
        if (f2690e != null) {
            f2690e.m3429b();
            f2690e = null;
        }
    }

    /* renamed from: b */
    public void m3410b() {
        m3406e();
        m3408g();
        C0888d.m3552a().m3554a(this.f2692f);
    }

    /* renamed from: c */
    public void m3411c() {
        m3407f();
        m3409h();
        C0888d.m3552a().m3556b(this.f2692f);
    }

    /* renamed from: b */
    private static C0872k m3401b(String str) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(str);
        stringBuilder.append("LoD_Share");
        return new C0872k(stringBuilder.toString(), 4032, new C13462());
    }

    /* renamed from: b */
    private static boolean m3403b(int i) {
        f2691g.removeMessages(i);
        f2691g.sendMessageDelayed(f2691g.obtainMessage(i), 1000);
        return true;
    }

    /* renamed from: c */
    private static void m3404c(String str) {
        List arrayList = new ArrayList();
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(str);
        stringBuilder.append("LoD_Share");
        arrayList.add(stringBuilder.toString());
        str = f2686a;
        stringBuilder = new StringBuilder();
        stringBuilder.append("scanMediaFile: ");
        stringBuilder.append(arrayList.toString());
        Log.m3853d(str, stringBuilder.toString());
        MediaScannerConnection.semScanDirectories(f2687b, (String[]) arrayList.toArray(new String[arrayList.size()]), new C08644());
    }
}
