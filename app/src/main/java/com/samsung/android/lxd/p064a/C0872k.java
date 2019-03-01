package com.samsung.android.lxd.p064a;

import android.os.FileObserver;
import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

/* compiled from: RecursiveFileObserver */
/* renamed from: com.samsung.android.lxd.a.k */
public class C0872k {
    /* renamed from: a */
    private final Map<String, FileObserver> f2702a = new HashMap();
    /* renamed from: b */
    private String f2703b;
    /* renamed from: c */
    private int f2704c;
    /* renamed from: d */
    private C0870a f2705d;

    /* compiled from: RecursiveFileObserver */
    /* renamed from: com.samsung.android.lxd.a.k$a */
    public interface C0870a {
        /* renamed from: a */
        void mo698a(int i, File file);
    }

    /* compiled from: RecursiveFileObserver */
    /* renamed from: com.samsung.android.lxd.a.k$b */
    private class C0871b extends FileObserver {
        /* renamed from: a */
        final /* synthetic */ C0872k f2700a;
        /* renamed from: b */
        private String f2701b;

        public C0871b(C0872k c0872k, String str, int i) {
            this.f2700a = c0872k;
            super(str, i);
            this.f2701b = str;
        }

        public void onEvent(int i, String str) {
            File file;
            if (str == null) {
                file = new File(this.f2701b);
            } else {
                file = new File(this.f2701b, str);
            }
            int i2 = i & 4095;
            if (i2 == 8 || i2 == 16) {
                if (this.f2700a.m3425a(file)) {
                    this.f2700a.m3423a(file.getAbsolutePath());
                }
            } else if (i2 == 1024) {
                this.f2700a.m3427b(this.f2701b);
            }
            this.f2700a.m3420a(i, file);
        }
    }

    public C0872k(String str, int i, C0870a c0870a) {
        this.f2703b = str;
        this.f2704c = ((i | 1024) | 16) | 8;
        this.f2705d = c0870a;
    }

    /* renamed from: a */
    private void m3423a(String str) {
        synchronized (this.f2702a) {
            if (this.f2702a.containsKey(str)) {
                return;
            }
            FileObserver c0871b = new C0871b(this, str, this.f2704c);
            c0871b.startWatching();
            this.f2702a.put(str, c0871b);
        }
    }

    /* renamed from: a */
    public void m3428a() {
        Stack stack = new Stack();
        stack.push(this.f2703b);
        while (!stack.empty()) {
            String str = (String) stack.pop();
            m3423a(str);
            File[] listFiles = new File(str).listFiles();
            if (listFiles != null) {
                for (File file : listFiles) {
                    if (m3425a(file)) {
                        stack.push(file.getAbsolutePath());
                    }
                }
            }
        }
    }

    /* renamed from: a */
    private boolean m3425a(File file) {
        return (!file.isDirectory() || file.getName().equals(".") || file.getName().equals("..")) ? false : true;
    }

    /* renamed from: b */
    private void m3427b(String str) {
        synchronized (this.f2702a) {
            FileObserver fileObserver = (FileObserver) this.f2702a.remove(str);
            if (fileObserver != null) {
                fileObserver.stopWatching();
            }
        }
    }

    /* renamed from: b */
    public void m3429b() {
        synchronized (this.f2702a) {
            for (FileObserver stopWatching : this.f2702a.values()) {
                stopWatching.stopWatching();
            }
            this.f2702a.clear();
        }
    }

    /* renamed from: a */
    private void m3420a(int i, File file) {
        if (this.f2705d != null) {
            this.f2705d.mo698a(i & 4095, file);
        }
    }
}
