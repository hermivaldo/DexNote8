package com.samsung.android.lxd.a;

import android.os.FileObserver;
import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

/* compiled from: RecursiveFileObserver */
public class k {
    private final Map<String, FileObserver> a = new HashMap();
    private String b;
    private int c;
    private a d;

    /* compiled from: RecursiveFileObserver */
    public interface a {
        void a(int i, File file);
    }

    /* compiled from: RecursiveFileObserver */
    private class b extends FileObserver {
        private String b;

        public b(String str, int i) {
            super(str, i);
            this.b = str;
        }

        public void onEvent(int i, String str) {
            File file;
            if (str == null) {
                file = new File(this.b);
            } else {
                file = new File(this.b, str);
            }
            int i2 = i & 4095;
            if (i2 == 8 || i2 == 16) {
                if (k.this.a(file)) {
                    k.this.a(file.getAbsolutePath());
                }
            } else if (i2 == 1024) {
                k.this.b(this.b);
            }
            k.this.a(i, file);
        }
    }

    public k(String str, int i, a aVar) {
        this.b = str;
        this.c = ((i | 1024) | 16) | 8;
        this.d = aVar;
    }

    private void a(String str) {
        synchronized (this.a) {
            if (this.a.containsKey(str)) {
                return;
            }
            FileObserver bVar = new b(str, this.c);
            bVar.startWatching();
            this.a.put(str, bVar);
        }
    }

    public void a() {
        Stack stack = new Stack();
        stack.push(this.b);
        while (!stack.empty()) {
            String str = (String) stack.pop();
            a(str);
            File[] listFiles = new File(str).listFiles();
            if (listFiles != null) {
                for (File file : listFiles) {
                    if (a(file)) {
                        stack.push(file.getAbsolutePath());
                    }
                }
            }
        }
    }

    private boolean a(File file) {
        return (!file.isDirectory() || file.getName().equals(".") || file.getName().equals("..")) ? false : true;
    }

    private void b(String str) {
        synchronized (this.a) {
            FileObserver fileObserver = (FileObserver) this.a.remove(str);
            if (fileObserver != null) {
                fileObserver.stopWatching();
            }
        }
    }

    public void b() {
        synchronized (this.a) {
            for (FileObserver stopWatching : this.a.values()) {
                stopWatching.stopWatching();
            }
            this.a.clear();
        }
    }

    private void a(int i, File file) {
        if (this.d != null) {
            this.d.a(i & 4095, file);
        }
    }
}
