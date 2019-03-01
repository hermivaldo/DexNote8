package com.samsung.android.lxd.a;

import android.annotation.SuppressLint;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.ClipboardManager.OnPrimaryClipChangedListener;
import android.text.TextUtils;
import com.samsung.android.content.clipboard.SemClipboardManager;
import com.samsung.android.content.clipboard.data.SemClipData;
import com.samsung.android.content.clipboard.data.SemHtmlClipData;
import com.samsung.android.content.clipboard.data.SemTextClipData;
import com.samsung.android.lxd.LxdApplication;
import com.samsung.android.lxd.processor.utils.log.Log;

/* compiled from: ClipBoardHelper */
public class a {
    private static final String a = "a";
    private static a b;
    private ClipboardManager c = ((ClipboardManager) LxdApplication.a().getSystemService("clipboard"));
    private SemClipboardManager d = ((SemClipboardManager) LxdApplication.a().getSystemService("semclipboard"));
    private boolean e;
    private int f;
    private OnPrimaryClipChangedListener g;

    public static synchronized a a() {
        a aVar;
        synchronized (a.class) {
            if (b == null) {
                b = new a();
            }
            aVar = b;
        }
        return aVar;
    }

    @SuppressLint({"WrongConstant"})
    private a() {
        if (this.d != null) {
            this.e = this.d.isEnabled();
            String str = a;
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("sem clipboard enabled: ");
            stringBuilder.append(this.e);
            Log.i(str, stringBuilder.toString());
        } else {
            this.e = false;
            Log.i(a, "sem clipboard is null: ");
        }
        this.g = new OnPrimaryClipChangedListener() {
            /* JADX WARNING: Failed to process nested try/catch */
            /* JADX WARNING: Missing block: B:7:?, code:
            r3 = com.samsung.android.lxd.a.a.a(r3.a).getPrimaryClip().getItemAt(0).getText().toString();
     */
            /* JADX WARNING: Missing block: B:8:0x004a, code:
            if (r3 != null) goto L_0x004c;
     */
            /* JADX WARNING: Missing block: B:9:0x004c, code:
            r0 = com.samsung.android.lxd.a.a.f();
            r1 = new java.lang.StringBuilder();
            r1.append("sendCutText: ");
            r1.append(r3);
            com.samsung.android.lxd.processor.utils.log.Log.d(r0, r1.toString());
     */
            /* JADX WARNING: Missing block: B:10:0x0064, code:
            com.samsung.android.lxd.processor.Processor.getInstance().sendCutText(r3);
     */
            /* Code decompiled incorrectly, please refer to instructions dump. */
            public void onPrimaryClipChanged() {
                Log.d(a.a, "onPrimaryClipChanged");
                if (LxdApplication.a().getPackageName().equals(a.this.c.getPrimaryClip().getDescription().getLabel())) {
                    Log.d(a.a, "onPrimaryClipChanged: ignore");
                }
                try {
                } catch (NullPointerException unused) {
                }
            }
        };
    }

    public CharSequence b() {
        CharSequence charSequence = null;
        if (!c()) {
            return null;
        }
        if (this.e) {
            SemClipData latestClip = this.d.getLatestClip(this.f);
            if (this.f == 1) {
                charSequence = ((SemTextClipData) latestClip).getText();
            } else if (this.f == 4) {
                charSequence = ((SemHtmlClipData) latestClip).getPlainText();
            }
        } else {
            charSequence = this.c.getPrimaryClip().getItemAt(0).getText();
        }
        return charSequence;
    }

    public boolean c() {
        boolean z = false;
        this.f = 0;
        if (this.e) {
            if (this.d.getCount() <= 0) {
                return false;
            }
            String str;
            StringBuilder stringBuilder;
            if (this.d.getLatestClip(1) != null) {
                this.f = 1;
            } else {
                if (this.d.getLatestClip(4) != null) {
                    this.f = 4;
                }
                str = a;
                stringBuilder = new StringBuilder();
                stringBuilder.append("hasText: ");
                stringBuilder.append(this.d.getCount());
                stringBuilder.append(", type:  ");
                stringBuilder.append(this.f);
                Log.d(str, stringBuilder.toString());
                return z;
            }
            z = true;
            str = a;
            stringBuilder = new StringBuilder();
            stringBuilder.append("hasText: ");
            stringBuilder.append(this.d.getCount());
            stringBuilder.append(", type:  ");
            stringBuilder.append(this.f);
            Log.d(str, stringBuilder.toString());
            return z;
        } else if (!this.c.hasPrimaryClip() || this.c.getPrimaryClipDescription().getMimeTypeCount() <= 0) {
            return false;
        } else {
            return true;
        }
    }

    public void a(CharSequence charSequence) {
        if (!TextUtils.isEmpty(charSequence)) {
            a(charSequence.toString());
        }
    }

    public void a(String str) {
        String str2 = a;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("setPrimaryClip: ");
        stringBuilder.append(str);
        Log.d(str2, stringBuilder.toString());
        this.c.setPrimaryClip(ClipData.newPlainText(LxdApplication.a().getPackageName(), str));
    }

    public void d() {
        Log.d(a, "addPrimaryClipChangedListener");
        try {
            this.c.addPrimaryClipChangedListener(this.g);
        } catch (Exception e) {
            String str = a;
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("addPrimaryClipChangedListener: ");
            stringBuilder.append(e.toString());
            Log.e(str, stringBuilder.toString());
        }
    }

    public void e() {
        Log.d(a, "removePrimaryClipChangedListener");
        try {
            this.c.removePrimaryClipChangedListener(this.g);
        } catch (Exception e) {
            String str = a;
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("removePrimaryClipChangedListener: ");
            stringBuilder.append(e.toString());
            Log.e(str, stringBuilder.toString());
        }
    }
}
