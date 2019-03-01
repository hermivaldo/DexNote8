package com.samsung.android.lxd;

import android.content.Context;
import com.samsung.android.lxd.a.l;
import com.samsung.android.lxd.a.o;
import com.samsung.android.lxd.processor.ExecutionType;
import com.samsung.android.lxd.processor.utils.log.Log;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;

/* compiled from: SAContainerDataHelper */
public class i {
    private static final String a = "i";
    private static i b;
    private Timer c = null;
    private long d = 0;
    private String e = null;

    public static synchronized i a() {
        i iVar;
        synchronized (i.class) {
            if (b == null) {
                b = new i();
            }
            iVar = b;
        }
        return iVar;
    }

    public void a(Context context, String str, ExecutionType executionType) {
        this.e = str;
        String str2 = this.e;
        str = String.valueOf(102);
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(executionType == ExecutionType.CLI ? "CLI;" : "GUI;");
        stringBuilder.append(o.h(context));
        stringBuilder.append(";");
        l.a(str2, str, stringBuilder.toString());
    }

    public void b() {
        this.d = System.currentTimeMillis();
    }

    public void c() {
        k();
    }

    public void d() {
        j();
        if (this.d == 0) {
            this.d = System.currentTimeMillis();
        }
    }

    public void e() {
        this.c = new Timer();
        this.c.schedule(new TimerTask() {
            public void run() {
                i.this.k();
            }
        }, 1800000);
    }

    public void a(String str) {
        int indexOf = str.indexOf(";");
        try {
            l.a(this.e, str.substring(0, indexOf), str.substring(indexOf + 1, str.length() - 1));
        } catch (IndexOutOfBoundsException e) {
            str = a;
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("onMonitoringNotified: ");
            stringBuilder.append(e.toString());
            Log.d(str, stringBuilder.toString());
        }
    }

    public void f() {
        l.a("012", String.valueOf(1203));
    }

    public void g() {
        l.a("012", String.valueOf(1202));
    }

    public void a(int i) {
        l.a("016", String.valueOf(i + 1600));
    }

    public void h() {
        l.a("019", String.valueOf(1901));
    }

    public void i() {
        l.a("019", String.valueOf(1903));
    }

    private void j() {
        if (this.c != null) {
            this.c.cancel();
            this.c = null;
        }
    }

    private void k() {
        if (this.d != 0) {
            l.a(this.e, String.valueOf(103), TimeUnit.MILLISECONDS.toMinutes(System.currentTimeMillis() - this.d));
            this.d = 0;
            j();
        }
    }
}
