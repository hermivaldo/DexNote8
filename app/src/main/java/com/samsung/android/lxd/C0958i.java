package com.samsung.android.lxd;

import android.content.Context;
import com.samsung.android.lxd.p064a.C0874l;
import com.samsung.android.lxd.p064a.C0877o;
import com.samsung.android.lxd.processor.ExecutionType;
import com.samsung.android.lxd.processor.utils.log.Log;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;

/* compiled from: SAContainerDataHelper */
/* renamed from: com.samsung.android.lxd.i */
public class C0958i {
    /* renamed from: a */
    private static final String f3007a = "i";
    /* renamed from: b */
    private static C0958i f3008b;
    /* renamed from: c */
    private Timer f3009c = null;
    /* renamed from: d */
    private long f3010d = 0;
    /* renamed from: e */
    private String f3011e = null;

    /* compiled from: SAContainerDataHelper */
    /* renamed from: com.samsung.android.lxd.i$1 */
    class C09571 extends TimerTask {
        /* renamed from: a */
        final /* synthetic */ C0958i f3006a;

        C09571(C0958i c0958i) {
            this.f3006a = c0958i;
        }

        public void run() {
            this.f3006a.m3841k();
        }
    }

    /* renamed from: a */
    public static synchronized C0958i m3838a() {
        C0958i c0958i;
        synchronized (C0958i.class) {
            if (f3008b == null) {
                f3008b = new C0958i();
            }
            c0958i = f3008b;
        }
        return c0958i;
    }

    /* renamed from: a */
    public void m3843a(Context context, String str, ExecutionType executionType) {
        this.f3011e = str;
        String str2 = this.f3011e;
        str = String.valueOf(102);
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(executionType == ExecutionType.CLI ? "CLI;" : "GUI;");
        stringBuilder.append(C0877o.m3512h(context));
        stringBuilder.append(";");
        C0874l.m3434a(str2, str, stringBuilder.toString());
    }

    /* renamed from: b */
    public void m3845b() {
        this.f3010d = System.currentTimeMillis();
    }

    /* renamed from: c */
    public void m3846c() {
        m3841k();
    }

    /* renamed from: d */
    public void m3847d() {
        m3840j();
        if (this.f3010d == 0) {
            this.f3010d = System.currentTimeMillis();
        }
    }

    /* renamed from: e */
    public void m3848e() {
        this.f3009c = new Timer();
        this.f3009c.schedule(new C09571(this), 1800000);
    }

    /* renamed from: a */
    public void m3844a(String str) {
        int indexOf = str.indexOf(";");
        try {
            C0874l.m3434a(this.f3011e, str.substring(0, indexOf), str.substring(indexOf + 1, str.length() - 1));
        } catch (IndexOutOfBoundsException e) {
            str = f3007a;
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("onMonitoringNotified: ");
            stringBuilder.append(e.toString());
            Log.m3853d(str, stringBuilder.toString());
        }
    }

    /* renamed from: f */
    public void m3849f() {
        C0874l.m3432a("012", String.valueOf(1203));
    }

    /* renamed from: g */
    public void m3850g() {
        C0874l.m3432a("012", String.valueOf(1202));
    }

    /* renamed from: a */
    public void m3842a(int i) {
        C0874l.m3432a("016", String.valueOf(i + 1600));
    }

    /* renamed from: h */
    public void m3851h() {
        C0874l.m3432a("019", String.valueOf(1901));
    }

    /* renamed from: i */
    public void m3852i() {
        C0874l.m3432a("019", String.valueOf(1903));
    }

    /* renamed from: j */
    private void m3840j() {
        if (this.f3009c != null) {
            this.f3009c.cancel();
            this.f3009c = null;
        }
    }

    /* renamed from: k */
    private void m3841k() {
        if (this.f3010d != 0) {
            C0874l.m3433a(this.f3011e, String.valueOf(103), TimeUnit.MILLISECONDS.toMinutes(System.currentTimeMillis() - this.f3010d));
            this.f3010d = 0;
            m3840j();
        }
    }
}
