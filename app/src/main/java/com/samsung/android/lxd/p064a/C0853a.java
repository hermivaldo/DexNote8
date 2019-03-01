package com.samsung.android.lxd.p064a;

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
/* renamed from: com.samsung.android.lxd.a.a */
public class C0853a {
    /* renamed from: a */
    private static final String f2657a = "a";
    /* renamed from: b */
    private static C0853a f2658b;
    /* renamed from: c */
    private ClipboardManager f2659c = ((ClipboardManager) LxdApplication.m3344a().getSystemService("clipboard"));
    /* renamed from: d */
    private SemClipboardManager f2660d = ((SemClipboardManager) LxdApplication.m3344a().getSystemService("semclipboard"));
    /* renamed from: e */
    private boolean f2661e;
    /* renamed from: f */
    private int f2662f;
    /* renamed from: g */
    private OnPrimaryClipChangedListener f2663g;

    /* compiled from: ClipBoardHelper */
    /* renamed from: com.samsung.android.lxd.a.a$1 */
    class C08521 implements OnPrimaryClipChangedListener {
        /* renamed from: a */
        final /* synthetic */ C0853a f2656a;

        C08521(C0853a c0853a) {
            this.f2656a = c0853a;
        }

        public void onPrimaryClipChanged() {
            /* JADX: method processing error */
/*
Error: java.lang.NullPointerException
	at jadx.core.dex.visitors.regions.ProcessTryCatchRegions.searchTryCatchDominators(ProcessTryCatchRegions.java:75)
	at jadx.core.dex.visitors.regions.ProcessTryCatchRegions.process(ProcessTryCatchRegions.java:45)
	at jadx.core.dex.visitors.regions.RegionMakerVisitor.postProcessRegions(RegionMakerVisitor.java:63)
	at jadx.core.dex.visitors.regions.RegionMakerVisitor.visit(RegionMakerVisitor.java:58)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:31)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:17)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:14)
	at jadx.core.ProcessClass.process(ProcessClass.java:34)
	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:282)
	at jadx.api.JavaClass.decompile(JavaClass.java:62)
	at jadx.api.JadxDecompiler.lambda$appendSourcesSave$0(JadxDecompiler.java:200)
	at jadx.api.JadxDecompiler$$Lambda$8/1201484275.run(Unknown Source)
*/
            /*
            r3 = this;
            r0 = com.samsung.android.lxd.p064a.C0853a.f2657a;
            r1 = "onPrimaryClipChanged";
            com.samsung.android.lxd.processor.utils.log.Log.m3853d(r0, r1);
            r0 = com.samsung.android.lxd.LxdApplication.m3344a();	 Catch:{ NullPointerException -> 0x0033 }
            r0 = r0.getPackageName();	 Catch:{ NullPointerException -> 0x0033 }
            r1 = r3.f2656a;	 Catch:{ NullPointerException -> 0x0033 }
            r1 = r1.f2659c;	 Catch:{ NullPointerException -> 0x0033 }
            r1 = r1.getPrimaryClip();	 Catch:{ NullPointerException -> 0x0033 }
            r1 = r1.getDescription();	 Catch:{ NullPointerException -> 0x0033 }
            r1 = r1.getLabel();	 Catch:{ NullPointerException -> 0x0033 }
            r0 = r0.equals(r1);	 Catch:{ NullPointerException -> 0x0033 }
            if (r0 == 0) goto L_0x0033;	 Catch:{ NullPointerException -> 0x0033 }
        L_0x0029:
            r0 = com.samsung.android.lxd.p064a.C0853a.f2657a;	 Catch:{ NullPointerException -> 0x0033 }
            r1 = "onPrimaryClipChanged: ignore";	 Catch:{ NullPointerException -> 0x0033 }
            com.samsung.android.lxd.processor.utils.log.Log.m3853d(r0, r1);	 Catch:{ NullPointerException -> 0x0033 }
            return;
        L_0x0033:
            r3 = r3.f2656a;	 Catch:{ NullPointerException -> 0x006b }
            r3 = r3.f2659c;	 Catch:{ NullPointerException -> 0x006b }
            r3 = r3.getPrimaryClip();	 Catch:{ NullPointerException -> 0x006b }
            r0 = 0;	 Catch:{ NullPointerException -> 0x006b }
            r3 = r3.getItemAt(r0);	 Catch:{ NullPointerException -> 0x006b }
            r3 = r3.getText();	 Catch:{ NullPointerException -> 0x006b }
            r3 = r3.toString();	 Catch:{ NullPointerException -> 0x006b }
            if (r3 == 0) goto L_0x0064;	 Catch:{ NullPointerException -> 0x006b }
        L_0x004c:
            r0 = com.samsung.android.lxd.p064a.C0853a.f2657a;	 Catch:{ NullPointerException -> 0x006b }
            r1 = new java.lang.StringBuilder;	 Catch:{ NullPointerException -> 0x006b }
            r1.<init>();	 Catch:{ NullPointerException -> 0x006b }
            r2 = "sendCutText: ";	 Catch:{ NullPointerException -> 0x006b }
            r1.append(r2);	 Catch:{ NullPointerException -> 0x006b }
            r1.append(r3);	 Catch:{ NullPointerException -> 0x006b }
            r1 = r1.toString();	 Catch:{ NullPointerException -> 0x006b }
            com.samsung.android.lxd.processor.utils.log.Log.m3853d(r0, r1);	 Catch:{ NullPointerException -> 0x006b }
        L_0x0064:
            r0 = com.samsung.android.lxd.processor.Processor.getInstance();	 Catch:{ NullPointerException -> 0x006b }
            r0.sendCutText(r3);	 Catch:{ NullPointerException -> 0x006b }
        L_0x006b:
            return;
            */
            throw new UnsupportedOperationException("Method not decompiled: com.samsung.android.lxd.a.a.1.onPrimaryClipChanged():void");
        }
    }

    /* renamed from: a */
    public static synchronized C0853a m3348a() {
        C0853a c0853a;
        synchronized (C0853a.class) {
            if (f2658b == null) {
                f2658b = new C0853a();
            }
            c0853a = f2658b;
        }
        return c0853a;
    }

    @SuppressLint({"WrongConstant"})
    private C0853a() {
        if (this.f2660d != null) {
            this.f2661e = this.f2660d.isEnabled();
            String str = f2657a;
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("sem clipboard enabled: ");
            stringBuilder.append(this.f2661e);
            Log.m3857i(str, stringBuilder.toString());
        } else {
            this.f2661e = false;
            Log.m3857i(f2657a, "sem clipboard is null: ");
        }
        this.f2663g = new C08521(this);
    }

    /* renamed from: b */
    public CharSequence m3352b() {
        CharSequence charSequence = null;
        if (!m3353c()) {
            return null;
        }
        if (this.f2661e) {
            SemClipData latestClip = this.f2660d.getLatestClip(this.f2662f);
            if (this.f2662f == 1) {
                charSequence = ((SemTextClipData) latestClip).getText();
            } else if (this.f2662f == 4) {
                charSequence = ((SemHtmlClipData) latestClip).getPlainText();
            }
        } else {
            charSequence = this.f2659c.getPrimaryClip().getItemAt(0).getText();
        }
        return charSequence;
    }

    /* renamed from: c */
    public boolean m3353c() {
        boolean z = false;
        this.f2662f = 0;
        if (this.f2661e) {
            if (this.f2660d.getCount() <= 0) {
                return false;
            }
            String str;
            StringBuilder stringBuilder;
            if (this.f2660d.getLatestClip(1) != null) {
                this.f2662f = 1;
            } else {
                if (this.f2660d.getLatestClip(4) != null) {
                    this.f2662f = 4;
                }
                str = f2657a;
                stringBuilder = new StringBuilder();
                stringBuilder.append("hasText: ");
                stringBuilder.append(this.f2660d.getCount());
                stringBuilder.append(", type:  ");
                stringBuilder.append(this.f2662f);
                Log.m3853d(str, stringBuilder.toString());
                return z;
            }
            z = true;
            str = f2657a;
            stringBuilder = new StringBuilder();
            stringBuilder.append("hasText: ");
            stringBuilder.append(this.f2660d.getCount());
            stringBuilder.append(", type:  ");
            stringBuilder.append(this.f2662f);
            Log.m3853d(str, stringBuilder.toString());
            return z;
        } else if (!this.f2659c.hasPrimaryClip() || this.f2659c.getPrimaryClipDescription().getMimeTypeCount() <= 0) {
            return false;
        } else {
            return true;
        }
    }

    /* renamed from: a */
    public void m3350a(CharSequence charSequence) {
        if (!TextUtils.isEmpty(charSequence)) {
            m3351a(charSequence.toString());
        }
    }

    /* renamed from: a */
    public void m3351a(String str) {
        String str2 = f2657a;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("setPrimaryClip: ");
        stringBuilder.append(str);
        Log.m3853d(str2, stringBuilder.toString());
        this.f2659c.setPrimaryClip(ClipData.newPlainText(LxdApplication.m3344a().getPackageName(), str));
    }

    /* renamed from: d */
    public void m3354d() {
        Log.m3853d(f2657a, "addPrimaryClipChangedListener");
        try {
            this.f2659c.addPrimaryClipChangedListener(this.f2663g);
        } catch (Exception e) {
            String str = f2657a;
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("addPrimaryClipChangedListener: ");
            stringBuilder.append(e.toString());
            Log.m3855e(str, stringBuilder.toString());
        }
    }

    /* renamed from: e */
    public void m3355e() {
        Log.m3853d(f2657a, "removePrimaryClipChangedListener");
        try {
            this.f2659c.removePrimaryClipChangedListener(this.f2663g);
        } catch (Exception e) {
            String str = f2657a;
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("removePrimaryClipChangedListener: ");
            stringBuilder.append(e.toString());
            Log.m3855e(str, stringBuilder.toString());
        }
    }
}
