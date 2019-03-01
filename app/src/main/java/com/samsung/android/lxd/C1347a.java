package com.samsung.android.lxd;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.Rect;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnSystemUiVisibilityChangeListener;
import android.view.ViewGroup;
import android.view.ViewTreeObserver.OnGlobalLayoutListener;
import android.view.Window;
import com.samsung.android.lxd.C0951g.C0950a;
import com.samsung.android.lxd.fragment.C0936a;
import com.samsung.android.lxd.fragment.C1369c;
import com.samsung.android.lxd.fragment.C1371e;
import com.samsung.android.lxd.fragment.C1371e.C0940a;
import com.samsung.android.lxd.p064a.C0854b;
import com.samsung.android.lxd.p064a.C0874l;
import com.samsung.android.lxd.p064a.C0877o;
import com.samsung.android.lxd.p064a.C0884p;
import com.samsung.android.lxd.p064a.C0885q;
import com.samsung.android.lxd.processor.ExecutionType;
import com.samsung.android.lxd.processor.Processor;
import com.samsung.android.lxd.processor.Processor.OpenContainerInfo;
import com.samsung.android.lxd.processor.config.SystemConfig;
import com.samsung.android.lxd.processor.config.SystemConfigManager;
import com.samsung.android.lxd.processor.config.SystemConfigType;
import com.samsung.android.lxd.processor.utils.log.Log;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map.Entry;

/* compiled from: BaseActivity */
/* renamed from: com.samsung.android.lxd.a */
public abstract class C1347a extends Activity implements C0889e {
    /* renamed from: b */
    private static final String f4423b = "a";
    /* renamed from: a */
    public String f4424a = null;
    /* renamed from: c */
    private ArrayList<C0951g> f4425c = new ArrayList();
    /* renamed from: d */
    private Processor f4426d = null;
    /* renamed from: e */
    private C0851a f4427e = C0851a.INVALID;
    /* renamed from: f */
    private boolean f4428f = true;
    /* renamed from: g */
    private boolean f4429g = true;
    /* renamed from: h */
    private boolean f4430h = false;
    /* renamed from: i */
    private boolean f4431i = false;
    /* renamed from: j */
    private boolean f4432j = false;
    /* renamed from: k */
    private Runnable f4433k = null;
    /* renamed from: l */
    private int f4434l = 0;
    /* renamed from: m */
    private C0936a f4435m = null;
    /* renamed from: n */
    private C0884p f4436n = null;

    /* compiled from: BaseActivity */
    /* renamed from: com.samsung.android.lxd.a$1 */
    class C08421 implements OnSystemUiVisibilityChangeListener {
        /* renamed from: a */
        final /* synthetic */ C1347a f2614a;

        C08421(C1347a c1347a) {
            this.f2614a = c1347a;
        }

        public void onSystemUiVisibilityChange(int i) {
            this.f2614a.m6155a(this.f2614a.getWindow());
        }
    }

    /* compiled from: BaseActivity */
    /* renamed from: com.samsung.android.lxd.a$7 */
    class C08497 implements OnClickListener {
        /* renamed from: a */
        final /* synthetic */ C1347a f2645a;

        C08497(C1347a c1347a) {
            this.f2645a = c1347a;
        }

        public void onClick(DialogInterface dialogInterface, int i) {
            C0874l.m3432a("011", String.valueOf(1103));
        }
    }

    /* compiled from: BaseActivity */
    /* renamed from: com.samsung.android.lxd.a$8 */
    class C08508 implements OnClickListener {
        /* renamed from: a */
        final /* synthetic */ C1347a f2646a;

        C08508(C1347a c1347a) {
            this.f2646a = c1347a;
        }

        public void onClick(DialogInterface dialogInterface, int i) {
            C0874l.m3432a("011", String.valueOf(1102));
            C0874l.m3433a("011", String.valueOf(1101), this.f2646a.f4432j ? 0 : 1);
            this.f2646a.m6121P();
            if (this.f2646a.f4432j) {
                C0877o.m3515i();
            }
        }
    }

    /* compiled from: BaseActivity */
    /* renamed from: com.samsung.android.lxd.a$a */
    public enum C0851a {
        INVALID,
        CREATED,
        STARTED,
        RESUMED,
        PAUSED,
        STOPPED,
        RESTARTED,
        DESTROYED
    }

    /* compiled from: BaseActivity */
    /* renamed from: com.samsung.android.lxd.a$6 */
    class C13376 implements C0950a {
        /* renamed from: a */
        final /* synthetic */ C1347a f4414a;

        /* compiled from: BaseActivity */
        /* renamed from: com.samsung.android.lxd.a$6$1 */
        class C08481 implements Runnable {
            /* renamed from: a */
            final /* synthetic */ C13376 f2644a;

            C08481(C13376 c13376) {
                this.f2644a = c13376;
            }

            public void run() {
                this.f2644a.f4414a.mo1396y();
            }
        }

        C13376(C1347a c1347a) {
            this.f4414a = c1347a;
        }

        /* renamed from: a */
        public void mo693a() {
            C0877o.m3470a(new C08481(this));
        }
    }

    /* compiled from: BaseActivity */
    /* renamed from: com.samsung.android.lxd.a$9 */
    class C13389 implements C0940a {
        /* renamed from: a */
        final /* synthetic */ C1347a f4415a;

        C13389(C1347a c1347a) {
            this.f4415a = c1347a;
        }

        /* renamed from: a */
        public void mo694a(boolean z) {
            String M = C1347a.f4423b;
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("onCheckBoxChanged: ");
            stringBuilder.append(z);
            Log.m3853d(M, stringBuilder.toString());
            this.f4415a.f4432j = z;
        }
    }

    /* renamed from: A */
    public void mo699A() {
    }

    /* renamed from: B */
    public void mo1285B() {
    }

    /* renamed from: C */
    public void mo1286C() {
    }

    /* renamed from: H */
    protected boolean mo1287H() {
        return true;
    }

    /* renamed from: I */
    protected boolean mo1288I() {
        return false;
    }

    /* renamed from: J */
    protected boolean mo1289J() {
        return true;
    }

    /* renamed from: K */
    protected boolean mo1290K() {
        return false;
    }

    /* renamed from: L */
    protected void mo1291L() {
    }

    /* renamed from: b */
    public void mo703b(String str, int i) {
    }

    /* renamed from: c */
    public void mo705c(String str, int i) {
    }

    /* renamed from: c */
    public void mo706c(String str, boolean z) {
    }

    /* renamed from: d */
    public void mo708d(String str, boolean z) {
    }

    /* renamed from: e */
    public void mo711e(String str, boolean z) {
    }

    /* renamed from: h */
    public boolean mo713h(String str) {
        return false;
    }

    /* renamed from: i */
    public void mo714i(String str) {
    }

    /* renamed from: j */
    public void mo715j(String str) {
    }

    /* renamed from: k */
    public void mo716k(String str) {
    }

    /* renamed from: l */
    public void mo717l(String str) {
    }

    /* renamed from: m */
    public void mo1281m(String str) {
    }

    /* renamed from: n */
    public void mo718n(String str) {
    }

    /* renamed from: o */
    public void mo719o(String str) {
    }

    /* renamed from: o */
    public boolean mo1298o() {
        return false;
    }

    /* renamed from: r */
    public void mo1398r() {
    }

    /* renamed from: t */
    public void mo721t() {
    }

    /* renamed from: u */
    public void mo722u() {
    }

    /* renamed from: v */
    public void mo723v() {
    }

    /* renamed from: w */
    public void mo724w() {
    }

    /* renamed from: x */
    public void mo725x() {
    }

    /* renamed from: y */
    public void mo1396y() {
    }

    /* renamed from: z */
    public void mo726z() {
    }

    /* renamed from: a */
    private void m6126a(C0851a c0851a) {
        this.f4427e = c0851a;
    }

    /* renamed from: a */
    public void mo702a() {
        String str = f4423b;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(getClass().getSimpleName());
        stringBuilder.append(" [");
        stringBuilder.append(this.f4427e);
        stringBuilder.append("]");
        Log.m3853d(str, stringBuilder.toString());
    }

    /* renamed from: b */
    public boolean m6173b() {
        if (!(this.f4427e == C0851a.CREATED || this.f4427e == C0851a.STARTED || this.f4427e == C0851a.RESTARTED)) {
            if (this.f4427e != C0851a.RESUMED) {
                return false;
            }
        }
        return true;
    }

    /* renamed from: c */
    public boolean mo707c() {
        if (!(this.f4427e == C0851a.STARTED || this.f4427e == C0851a.RESTARTED)) {
            if (this.f4427e != C0851a.RESUMED) {
                return false;
            }
        }
        return true;
    }

    /* renamed from: d */
    public boolean mo709d() {
        return this.f4427e == C0851a.PAUSED;
    }

    /* renamed from: e */
    public void m6183e() {
        this.f4429g = false;
    }

    /* renamed from: f */
    public void m6186f() {
        this.f4430h = true;
    }

    /* renamed from: g */
    public boolean mo1292g() {
        if (!C0877o.m3442B()) {
            if (!mo1304h()) {
                return false;
            }
        }
        Log.m3857i(f4423b, "moveToMainActivity: ");
        Intent intent = new Intent(LxdApplication.m3344a(), C0877o.m3524l());
        intent.addFlags(32768);
        intent.addFlags(268435456);
        LxdApplication.m3344a().startActivity(intent);
        return true;
    }

    /* renamed from: h */
    public boolean mo1304h() {
        return C0877o.m3447G() ^ 1;
    }

    /* renamed from: i */
    public void m6193i() {
        this.f4428f = false;
    }

    protected void attachBaseContext(Context context) {
        super.attachBaseContext(C1347a.m6124a(context));
    }

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        m6126a(C0851a.CREATED);
        this.f4426d = Processor.getInstance();
        this.f4436n = new C0884p();
        setRequestedOrientation(getRequestedOrientation());
        m6155a(getWindow());
        bundle = getActionBar();
        if (bundle != null) {
            if (this.f4429g) {
                bundle.setDisplayHomeAsUpEnabled(this.f4428f);
            } else {
                bundle.hide();
            }
        }
        getWindow().getDecorView().setOnSystemUiVisibilityChangeListener(new C08421(this));
        this.f4434l = getResources().getConfiguration().densityDpi;
        bundle = f4423b;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("onCreate: ");
        stringBuilder.append(getRequestedOrientation());
        stringBuilder.append(", dpi: ");
        stringBuilder.append(this.f4434l);
        Log.m3853d(bundle, stringBuilder.toString());
    }

    public void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        if (configuration.densityDpi != this.f4434l) {
            String str = f4423b;
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("onConfigurationChanged: densityUpdate old: ");
            stringBuilder.append(this.f4434l);
            stringBuilder.append(", new: ");
            stringBuilder.append(configuration.densityDpi);
            Log.m3857i(str, stringBuilder.toString());
            recreate();
            return;
        }
        C1347a.m6124a((Context) this);
    }

    protected void onRestart() {
        super.onRestart();
        m6126a(C0851a.RESTARTED);
        m6155a(getWindow());
    }

    protected void onStart() {
        super.onStart();
        m6126a(C0851a.STARTED);
    }

    protected void onResume() {
        super.onResume();
        m6126a(C0851a.RESUMED);
        m6122Q();
        mo1292g();
    }

    protected void onPause() {
        super.onPause();
        m6126a(C0851a.PAUSED);
    }

    protected void onStop() {
        super.onStop();
        m6126a(C0851a.STOPPED);
    }

    public void onBackPressed() {
        C0877o.m3489b(getCurrentFocus(), 0);
        super.onBackPressed();
    }

    public int getRequestedOrientation() {
        return C0877o.m3490c((Context) this);
    }

    public boolean onKeyDown(int i, KeyEvent keyEvent) {
        String str = f4423b;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("onKeyDown: ");
        stringBuilder.append(keyEvent.toString());
        Log.m3853d(str, stringBuilder.toString());
        if (C0877o.m3499d((Context) this) && C0899f.m3589b() == 1) {
            if (i != 4) {
                if (i != 111) {
                }
            } else if (keyEvent.getDeviceId() != -1) {
            }
            i = f4423b;
            StringBuilder stringBuilder2 = new StringBuilder();
            stringBuilder2.append("moveTaskToBack: ");
            stringBuilder2.append(keyEvent.toString());
            Log.m3853d(i, stringBuilder2.toString());
            moveTaskToBack(true);
            return true;
        }
        return super.onKeyDown(i, keyEvent);
    }

    public void onWindowFocusChanged(boolean z) {
        String str = f4423b;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("onWindowFocusChanged: ");
        stringBuilder.append(z);
        Log.m3853d(str, stringBuilder.toString());
        super.onWindowFocusChanged(z);
        m6155a(getWindow());
    }

    /* renamed from: a */
    public boolean m6169a(MotionEvent motionEvent) {
        return this.f4426d.handleGenericMotion(motionEvent);
    }

    /* renamed from: a */
    public boolean m6168a(int i, KeyEvent keyEvent) {
        if (!this.f4426d.handleKeyDown(i, keyEvent)) {
            if (!super.onKeyDown(i, keyEvent)) {
                return false;
            }
        }
        return true;
    }

    /* renamed from: b */
    public boolean m6174b(int i, KeyEvent keyEvent) {
        if (!this.f4426d.handleKeyUp(i, keyEvent)) {
            if (!super.onKeyUp(i, keyEvent)) {
                return false;
            }
        }
        return true;
    }

    /* renamed from: b */
    public boolean m6175b(MotionEvent motionEvent) {
        return this.f4426d.handleTouchEvent(motionEvent);
    }

    /* renamed from: a */
    public void m6159a(final String str) {
        this.f4433k = new Runnable(this) {
            /* renamed from: b */
            final /* synthetic */ C1347a f2598b;

            public void run() {
                this.f2598b.f4426d.getDebugLog(str);
            }
        };
        mo1303N();
    }

    /* renamed from: b */
    public void mo1279b(final String str) {
        this.f4433k = new Runnable(this) {
            /* renamed from: b */
            final /* synthetic */ C1347a f2613b;

            public void run() {
                this.f2613b.f4426d.getImageVersion(str);
            }
        };
        mo1303N();
    }

    /* renamed from: c */
    public void mo1280c(String str) {
        this.f4426d.rebaseImage(str);
    }

    /* renamed from: d */
    public void m6180d(final String str) {
        this.f4433k = new Runnable(this) {
            /* renamed from: b */
            final /* synthetic */ C1347a f2616b;

            public void run() {
                this.f2616b.f4426d.getImageMinSize(str);
            }
        };
        mo1303N();
    }

    /* renamed from: a */
    public void m6161a(final String str, final int i, final boolean z) {
        this.f4433k = new Runnable(this) {
            /* renamed from: d */
            final /* synthetic */ C1347a f2620d;

            public void run() {
                this.f2620d.f4426d.resizeImage(str, i, z);
            }
        };
        mo1303N();
    }

    /* renamed from: a */
    public void m6157a(final OpenContainerInfo openContainerInfo) {
        this.f4433k = new Runnable(this) {
            /* renamed from: b */
            final /* synthetic */ C1347a f2622b;

            public void run() {
                this.f2622b.f4426d.openContainer(openContainerInfo);
            }
        };
        mo1303N();
    }

    /* renamed from: j */
    public void mo1294j() {
        this.f4426d.closeContainer();
    }

    /* renamed from: k */
    public void mo1295k() {
        this.f4426d.pauseContainer();
    }

    /* renamed from: l */
    public void mo1296l() {
        this.f4426d.resumeContainer();
    }

    /* renamed from: m */
    public void m6201m() {
        this.f4426d.startContainer();
    }

    /* renamed from: n */
    public void mo1297n() {
        this.f4426d.stopContainer();
    }

    /* renamed from: p */
    public boolean m6207p() {
        if (C0877o.m3517i("com.osp.app.signin")) {
            return false;
        }
        startActivityForResult(C0877o.m3538w(), 3);
        return true;
    }

    /* renamed from: q */
    public void m6208q() {
        String str = f4423b;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("isWifi: ");
        stringBuilder.append(C0877o.m3444D());
        Log.m3853d(str, stringBuilder.toString());
        startActivity(new Intent(this, WebViewActivity.class));
    }

    /* renamed from: e */
    public void m6184e(String str) {
        m6163a(str, false);
    }

    /* renamed from: a */
    public void m6163a(String str, boolean z) {
        File file = new File(str);
        String str2 = f4423b;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("checkImageFile isFile? ");
        stringBuilder.append(file.isFile());
        Log.m3853d(str2, stringBuilder.toString());
        if (!file.isFile()) {
            mo1397a(z);
        } else if (m6139p(str)) {
            m6133b(z);
        } else {
            mo1279b(str);
        }
    }

    /* renamed from: a */
    public void m6164a(String str, boolean z, String str2) {
        m6165a(str, z, str2, null);
    }

    /* renamed from: a */
    public void m6165a(String str, boolean z, String str2, String str3) {
        m6166a(str, z, str2, str3, false);
    }

    /* renamed from: a */
    public void m6166a(String str, boolean z, String str2, String str3, boolean z2) {
        String str4 = f4423b;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("getImageVersionResult path: ");
        stringBuilder.append(str);
        stringBuilder.append(", secure path: ");
        stringBuilder.append(C0877o.m3485b());
        Log.m3857i(str4, stringBuilder.toString());
        if (!z) {
            m6138h(str, z2);
        } else if (!C0877o.m3500d(str) || str.startsWith(C0877o.m3485b())) {
            mo1394a(str, str3, str2, z2);
        } else {
            z = f4423b;
            StringBuilder stringBuilder2 = new StringBuilder();
            stringBuilder2.append("non secured path: ");
            stringBuilder2.append(str);
            stringBuilder2.append(", secure path: ");
            stringBuilder2.append(C0877o.m3485b());
            Log.m3857i(z, stringBuilder2.toString());
            m6132b(str, str3, str2, z2);
        }
    }

    /* renamed from: a */
    public void mo1394a(String str, String str2, String str3, boolean z) {
        if (!z) {
            if (str3 == null) {
                str3 = new Intent(this, CreateActivity.class);
                str3.putExtra("imagePath", str);
                str3.putExtra("imageVersion", str2);
                str3.addFlags(268435456);
                str3.addFlags(67108864);
                startActivity(str3);
            } else {
                SystemConfig systemConfig = new SystemConfig();
                systemConfig.set(SystemConfigType.IMAGE_PATH, str);
                systemConfig.set(SystemConfigType.IMAGE_VERSION, str2);
                SystemConfigManager.update(Long.parseLong(str3), systemConfig);
                str = new Intent(this, ListActivity.class);
                str.putExtra("configId", str3);
                str.addFlags(67108864);
                startActivity(str);
            }
        }
    }

    /* renamed from: a */
    public void m6153a(Activity activity) {
        m6154a(activity, null);
    }

    /* renamed from: a */
    public void m6154a(Activity activity, HashMap<String, String> hashMap) {
        Intent intent = new Intent(activity, ListActivity.class);
        intent.addFlags(67108864);
        if (hashMap != null) {
            for (String str : hashMap.keySet()) {
                String str2 = f4423b;
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append("putExtra, key: ");
                stringBuilder.append(str);
                stringBuilder.append(", value: ");
                stringBuilder.append((String) hashMap.get(str));
                Log.m3853d(str2, stringBuilder.toString());
                intent.putExtra(str, (String) hashMap.get(str));
            }
        }
        startActivity(intent);
    }

    /* renamed from: a */
    public void mo1307a(ExecutionType executionType, String str) {
        mo1279b(SystemConfigManager.load(Long.parseLong(str)).get(SystemConfigType.IMAGE_PATH));
    }

    /* renamed from: a */
    public void m6155a(Window window) {
        if (C0877o.m3499d((Context) this) || C0877o.m3503e()) {
            window.getDecorView().setSystemUiVisibility((this.f4430h ? 2 : 0) | 5124);
        }
    }

    /* renamed from: a */
    public void m6158a(File file) {
        ArrayList arrayList = new ArrayList();
        arrayList.add(file);
        m6167a(arrayList);
    }

    /* renamed from: a */
    public void m6167a(ArrayList<File> arrayList) {
        this.f4435m = C1369c.m6284j().m6286a((ArrayList) arrayList).m3757a(this).m3753a((int) R.string.details).m3766c((int) R.string.ok).m3755a(new OnClickListener(this) {
            /* renamed from: a */
            final /* synthetic */ C1347a f2623a;

            {
                this.f2623a = r1;
            }

            public void onClick(DialogInterface dialogInterface, int i) {
                this.f2623a.f4435m.dismiss();
            }
        }).m3770e();
    }

    public void deleteImage(final String str) {
        final C0936a a = C0936a.m3752a();
        a.m3757a(this).m3753a((int) R.string.delete_popup_title).m3762b((int) R.string.delete_unmount_message).m3769d(R.string.popup_delete).m3763b(new OnClickListener(this) {
            /* renamed from: c */
            final /* synthetic */ C1347a f2629c;

            public void onClick(DialogInterface dialogInterface, int i) {
                i = new File(C0877o.m3492c(SystemConfigManager.load(Long.parseLong(str)).get(SystemConfigType.IMAGE_PATH)));
                if (i.isFile() != null) {
                    i.delete();
                }
                SystemConfigManager.remove(Long.parseLong(str));
                if (this.f2629c.getComponentName().getClassName().equals(ListActivity.class.getName()) == null) {
                    dialogInterface = new Intent(this.f2629c, ListActivity.class);
                    dialogInterface.addFlags(67108864);
                    this.f2629c.startActivity(dialogInterface);
                } else {
                    a.dismiss();
                }
                this.f2629c.mo1398r();
            }
        }).m3766c((int) R.string.popup_unmount).m3755a(new OnClickListener(this) {
            /* renamed from: c */
            final /* synthetic */ C1347a f2626c;

            public void onClick(DialogInterface dialogInterface, int i) {
                SystemConfigManager.remove(Long.parseLong(str));
                dialogInterface = C1347a.f4423b;
                i = new StringBuilder();
                i.append(this.f2626c.getComponentName().getClassName());
                i.append(", ");
                i.append(ListActivity.class.getName());
                Log.m3853d(dialogInterface, i.toString());
                if (this.f2626c.getComponentName().getClassName().equals(ListActivity.class.getName()) == null) {
                    this.f2626c.m6153a(this.f2626c);
                } else {
                    a.dismiss();
                }
                this.f2626c.mo1398r();
            }
        }).m3770e();
    }

    /* renamed from: f */
    public void m6187f(final String str) {
        final C0936a a = C0936a.m3752a();
        a.m3757a(this).m3753a((int) R.string.unmount_image_popup_title).m3762b((int) R.string.unmount_image_popup_message).m3769d(R.string.popup_cancel).m3766c((int) R.string.unmount_image).m3755a(new OnClickListener(this) {
            /* renamed from: c */
            final /* synthetic */ C1347a f2632c;

            public void onClick(DialogInterface dialogInterface, int i) {
                SystemConfigManager.remove(Long.parseLong(str));
                if (this.f2632c.getComponentName().getClassName().equals(ListActivity.class.getName()) == null) {
                    this.f2632c.m6153a(this.f2632c);
                } else {
                    a.dismiss();
                }
                this.f2632c.mo1398r();
            }
        }).m3770e();
    }

    /* renamed from: g */
    public void m6189g(String str) {
        m6160a(str, 0);
    }

    /* renamed from: a */
    public void m6160a(final String str, final int i) {
        if (C0877o.m3497d() == null) {
            m6131b(str, i, false);
        } else {
            C0936a.m3752a().m3757a(this).m3753a((int) R.string.browse_image_title).m3762b((int) R.string.browse_image_body).m3766c((int) R.string.internal_storage_capital).m3755a(new OnClickListener(this) {
                /* renamed from: c */
                final /* synthetic */ C1347a f2639c;

                public void onClick(DialogInterface dialogInterface, int i) {
                    this.f2639c.m6131b(str, i, false);
                }
            }).m3769d(R.string.sd_card_capital).m3763b(new OnClickListener(this) {
                /* renamed from: c */
                final /* synthetic */ C1347a f2636c;

                /* compiled from: BaseActivity */
                /* renamed from: com.samsung.android.lxd.a$3$1 */
                class C08441 implements OnClickListener {
                    /* renamed from: a */
                    final /* synthetic */ C08453 f2633a;

                    C08441(C08453 c08453) {
                        this.f2633a = c08453;
                    }

                    public void onClick(DialogInterface dialogInterface, int i) {
                        this.f2633a.f2636c.m6131b(str, i, true);
                    }
                }

                public void onClick(DialogInterface dialogInterface, int i) {
                    C0936a.m3752a().m3757a(this.f2636c).m3753a((int) R.string.images_on_sd_card_popup_title).m3758a(this.f2636c.getText(R.string.images_on_sd_card_popup_message)).m3766c((int) R.string.popup_continue).m3769d(R.string.popup_cancel).m3755a(new C08441(this)).m3770e();
                }
            }).m3770e();
        }
    }

    /* renamed from: b */
    private void m6131b(final String str, final int i, final boolean z) {
        this.f4436n.m3547a((int) R.string.app_name, (int) R.string.wait, (Context) this, null);
        C0877o.m3471a(new Runnable(this) {
            /* renamed from: d */
            final /* synthetic */ C1347a f2643d;

            public void run() {
                Intent intent = new Intent(this.f2643d, BrowseActivity.class);
                if (str != null) {
                    intent.putExtra("configId", str);
                }
                if (z) {
                    intent.putExtra("searchSdCard", true);
                }
                intent.addFlags(i);
                this.f2643d.startActivityForResult(intent, 7);
            }
        }, 50, true);
    }

    /* renamed from: N */
    private void mo1303N() {
        if (C0899f.m3595e() && this.f4433k != null) {
            this.f4433k.run();
            this.f4433k = null;
        }
    }

    /* renamed from: s */
    public void mo720s() {
        String str = f4423b;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(getClass().getSimpleName());
        stringBuilder.append(" onServiceOpened: PendingRequest: ");
        stringBuilder.append(this.f4433k != null);
        Log.m3853d(str, stringBuilder.toString());
        mo1303N();
    }

    /* renamed from: b */
    public void mo704b(String str, boolean z) {
        String str2 = f4423b;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("onDebugLogReceived: ");
        stringBuilder.append(z);
        stringBuilder.append(", ");
        stringBuilder.append(str);
        Log.m3853d(str2, stringBuilder.toString());
        m6137g(str, z);
    }

    /* renamed from: f */
    public boolean mo712f(String str, boolean z) {
        this.f4433k = null;
        return false;
    }

    protected void onNewIntent(Intent intent) {
        if ("android.intent.action.SEARCH".equals(intent.getAction())) {
            mo1281m(intent.getStringExtra("query"));
        }
    }

    /* renamed from: D */
    public void m6143D() {
        m6144E();
        for (Entry value : SystemConfigManager.loadAll().entrySet()) {
            String str = ((SystemConfig) value.getValue()).get(SystemConfigType.IMAGE_PATH);
            C0951g c0951g = new C0951g(str, new C13376(this));
            c0951g.startWatching();
            this.f4425c.add(c0951g);
            String str2 = f4423b;
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("startImageFileMonitor! ");
            stringBuilder.append(str);
            Log.m3853d(str2, stringBuilder.toString());
        }
    }

    /* renamed from: E */
    public void m6144E() {
        Iterator it = this.f4425c.iterator();
        while (it.hasNext()) {
            ((C0951g) it.next()).stopWatching();
        }
        this.f4425c.clear();
        Log.m3853d(f4423b, "stopImageFileMonitor!");
    }

    /* renamed from: F */
    public void mo700F() {
        this.f4426d.startMonitoring();
    }

    /* renamed from: G */
    public void mo701G() {
        this.f4426d.stopMonitoring();
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_more_options, menu);
        if (mo1287H()) {
            menu.findItem(R.id.settings).setVisible(true);
        }
        if (mo1288I()) {
            menu.findItem(R.id.contact_us).setVisible(true);
        }
        if (mo1289J()) {
            menu.findItem(R.id.error_report).setVisible(true);
        }
        if (mo1290K()) {
            menu.findItem(R.id.shortcuts).setVisible(true);
        }
        return super.onCreateOptionsMenu(menu);
    }

    public boolean onOptionsItemSelected(MenuItem menuItem) {
        String str = f4423b;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("onOptionsItemSelected: ");
        stringBuilder.append(menuItem.getItemId());
        Log.m3853d(str, stringBuilder.toString());
        switch (menuItem.getItemId()) {
            case 16908332:
                onBackPressed();
                break;
            case R.id.contact_us:
                C0874l.m3432a(this.f4424a, String.valueOf(2));
                C0854b.m3357a((Activity) this);
                break;
            case R.id.error_report:
                C0874l.m3432a(this.f4424a, String.valueOf(3));
                mo1306O();
                break;
            case R.id.settings:
                C0874l.m3432a(this.f4424a, String.valueOf(1));
                startActivity(new Intent(this, SettingsActivity.class));
                break;
            case R.id.shortcuts:
                C0874l.m3432a("012", String.valueOf(1201));
                mo1291L();
                break;
            default:
                break;
        }
        return super.onOptionsItemSelected(menuItem);
    }

    protected void onActivityResult(int i, int i2, Intent intent) {
        String str = f4423b;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("onActivityResult: ");
        stringBuilder.append(i);
        stringBuilder.append(", ");
        stringBuilder.append(i2);
        Log.m3853d(str, stringBuilder.toString());
        if (i != 7) {
            super.onActivityResult(i, i2, intent);
            return;
        }
        Log.m3853d(f4423b, "open Browse activity");
        this.f4436n.m3546a();
    }

    /* renamed from: O */
    private void mo1306O() {
        Log.m3853d(f4423b, "selectErrorReport: ");
        if (C0877o.m3513h()) {
            m6121P();
            return;
        }
        this.f4432j = false;
        C1371e.m6298j().m6299a(new C13389(this)).m3757a(this).m3759a(true).m3753a((int) R.string.error_report_popup_title).m3769d(R.string.popup_cancel).m3766c((int) R.string.report_error).m3755a(new C08508(this)).m3763b(new C08497(this)).m3770e();
    }

    /* renamed from: P */
    private void m6121P() {
        String str = f4423b;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("processErrorReport: ");
        stringBuilder.append(this.f4431i);
        Log.m3853d(str, stringBuilder.toString());
        if (!this.f4431i) {
            this.f4431i = true;
            this.f4436n.m3547a((int) R.string.app_name, (int) R.string.wait, (Context) this, null);
            LxdApplication.m3345a("LoD_Logs");
            StringBuilder stringBuilder2 = new StringBuilder();
            stringBuilder2.append(LxdApplication.m3344a().getFilesDir().getAbsolutePath());
            stringBuilder2.append(File.separator);
            stringBuilder2.append("LoD_Logs");
            m6159a(stringBuilder2.toString());
        }
    }

    /* renamed from: g */
    private void m6137g(String str, boolean z) {
        if (this.f4431i) {
            this.f4431i = false;
            this.f4436n.m3546a();
            String str2 = f4423b;
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("processErrorReport: ");
            stringBuilder.append(z);
            stringBuilder.append(", ");
            stringBuilder.append(str);
            Log.m3857i(str2, stringBuilder.toString());
            StringBuilder stringBuilder2 = new StringBuilder();
            stringBuilder2.append(LxdApplication.m3344a().getFilesDir().getAbsolutePath());
            stringBuilder2.append(File.separator);
            stringBuilder2.append("LoD_Logs");
            str2 = stringBuilder2.toString();
            File file = new File(str2);
            List arrayList = new ArrayList();
            C0877o.m3469a(file, arrayList);
            if (z && str != null) {
                File file2 = new File(str);
                if (file.getAbsolutePath().equals(file2.getAbsolutePath()) == null) {
                    try {
                        Log.m3853d(f4423b, "search nst log folder");
                        C0877o.m3469a(file2, arrayList);
                    } catch (String str3) {
                        z = f4423b;
                        stringBuilder = new StringBuilder();
                        stringBuilder.append("fail to find nst log: ");
                        stringBuilder.append(str3.toString());
                        Log.m3855e(z, stringBuilder.toString());
                    }
                }
            }
            str3 = f4423b;
            z = new StringBuilder();
            z.append("files: ");
            z.append(arrayList.toString());
            Log.m3853d(str3, z.toString());
            z = new StringBuilder();
            z.append(str2);
            z.append(".zip");
            File file3 = new File(z.toString());
            try {
                C0885q.m3550a(arrayList, file3);
            } catch (boolean z2) {
                str2 = f4423b;
                stringBuilder = new StringBuilder();
                stringBuilder.append("zip exception: ");
                stringBuilder.append(z2.toString());
                Log.m3857i(str2, stringBuilder.toString());
                z2.printStackTrace();
            }
            str3 = C0877o.m3496d(file3);
            z2 = f4423b;
            stringBuilder2 = new StringBuilder();
            stringBuilder2.append("Uris: ");
            stringBuilder2.append(str3.toString());
            Log.m3853d(z2, stringBuilder2.toString());
            z2 = new StringBuilder();
            z2.append(getString(R.string.error_report_mail_subject));
            z2.append(C0877o.m3463a(new SimpleDateFormat("[MM/dd, HH:mm]")));
            z2.append(" (");
            z2.append(C0877o.m3537v());
            z2.append(")");
            C0877o.m3480a(new String[]{"lod.sec@samsung.com"}, z2.toString(), getString(R.string.error_report_mail_content), str3);
        }
    }

    /* renamed from: a */
    private static Context m6124a(Context context) {
        Configuration configuration = new Configuration(context.getResources().getConfiguration());
        String str = f4423b;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("updateFontScaleResources: ");
        stringBuilder.append(configuration.fontScale);
        Log.m3853d(str, stringBuilder.toString());
        Object obj = 1;
        if (configuration.fontScale < 0.9f) {
            configuration.fontScale = 0.9f;
        } else if (configuration.fontScale > 1.3f) {
            configuration.fontScale = 1.3f;
        } else {
            obj = null;
        }
        return obj != null ? context.createConfigurationContext(configuration) : context;
    }

    /* renamed from: Q */
    private void m6122Q() {
        try {
            final View childAt = ((ViewGroup) findViewById(16908290)).getChildAt(0);
            childAt.getViewTreeObserver().addOnGlobalLayoutListener(new OnGlobalLayoutListener(this) {
                /* renamed from: b */
                final /* synthetic */ C1347a f2593b;
                /* renamed from: c */
                private int f2594c = null;

                public void onGlobalLayout() {
                    Rect rect = new Rect();
                    childAt.getWindowVisibleDisplayFrame(rect);
                    int height = rect.height();
                    if (height != this.f2594c) {
                        int height2 = childAt.getRootView().getHeight();
                        if (height2 - height > height2 / 4) {
                            Log.m3853d(C1347a.f4423b, "onSoftInputShown");
                            this.f2593b.mo1285B();
                        } else {
                            Log.m3853d(C1347a.f4423b, "onSoftInputHidden");
                            this.f2593b.mo1286C();
                        }
                    }
                    this.f2594c = height;
                }
            });
        } catch (Exception e) {
            String str = f4423b;
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("Failed to add soft input listener : ");
            stringBuilder.append(e.toString());
            Log.m3855e(str, stringBuilder.toString());
        }
    }

    /* renamed from: p */
    private boolean m6139p(String str) {
        for (Entry value : SystemConfigManager.loadAll().entrySet()) {
            if (((SystemConfig) value.getValue()).get(SystemConfigType.IMAGE_PATH).equalsIgnoreCase(str)) {
                return true;
            }
        }
        return false;
    }

    /* renamed from: a */
    private void mo1397a(final boolean z) {
        C0936a.m3752a().m3757a(this).m3753a((int) R.string.image_not_found_title).m3762b((int) R.string.image_not_found_create_message).m3759a(z ^ 1).m3766c((int) R.string.browse).m3755a(new OnClickListener(this) {
            /* renamed from: a */
            final /* synthetic */ C1347a f2599a;

            {
                this.f2599a = r1;
            }

            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
                this.f2599a.m6160a((String) null, 67108864);
            }
        }).m3769d(R.string.popup_cancel).m3763b(new OnClickListener(this) {
            /* renamed from: b */
            final /* synthetic */ C1347a f2596b;

            public void onClick(DialogInterface dialogInterface, int i) {
                if (z != 0) {
                    this.f2596b.m6123R();
                    return;
                }
                dialogInterface.dismiss();
                dialogInterface = new Intent(this.f2596b, ListActivity.class);
                dialogInterface.addFlags(67108864);
                this.f2596b.startActivity(dialogInterface);
            }
        }).m3770e();
    }

    /* renamed from: b */
    private void m6133b(final boolean z) {
        C0936a.m3752a().m3757a(this).m3759a(z ^ 1).m3753a((int) R.string.image_already_in_use_popup_title).m3762b((int) R.string.image_already_in_use_popup_message).m3766c((int) R.string.ok).m3755a(new OnClickListener(this) {
            /* renamed from: b */
            final /* synthetic */ C1347a f2601b;

            public void onClick(DialogInterface dialogInterface, int i) {
                if (z != 0) {
                    this.f2601b.m6123R();
                } else {
                    dialogInterface.dismiss();
                }
            }
        }).m3770e();
    }

    /* renamed from: b */
    private void m6132b(String str, String str2, String str3, final boolean z) {
        final String str4 = str;
        final String str5 = str2;
        final String str6 = str3;
        final boolean z2 = z;
        C0936a.m3752a().m3757a(this).m3759a(z ^ 1).m3753a((int) R.string.non_secured_path).m3758a(getText(R.string.non_secured_path_content)).m3766c((int) R.string.popup_continue).m3769d(R.string.popup_cancel).m3755a(new OnClickListener(this) {
            /* renamed from: e */
            final /* synthetic */ C1347a f2608e;

            public void onClick(DialogInterface dialogInterface, int i) {
                this.f2608e.mo1394a(str4, str5, str6, z2);
            }
        }).m3763b(new OnClickListener(this) {
            /* renamed from: b */
            final /* synthetic */ C1347a f2603b;

            public void onClick(DialogInterface dialogInterface, int i) {
                if (z != 0) {
                    this.f2603b.m6123R();
                } else {
                    dialogInterface.dismiss();
                }
            }
        }).m3770e();
    }

    /* renamed from: h */
    private void m6138h(String str, final boolean z) {
        int i;
        int i2;
        if (C0877o.m3456a(str, 0, true) != null) {
            i = R.string.image_not_found_title;
            i2 = R.string.image_not_found_create_message;
        } else {
            i = R.string.incorrect_image_title;
            i2 = R.string.incorrect_image_body;
        }
        C0936a.m3752a().m3757a(this).m3753a(i).m3762b(i2).m3759a(z ^ 1).m3766c((int) R.string.ok).m3755a(new OnClickListener(this) {
            /* renamed from: b */
            final /* synthetic */ C1347a f2610b;

            public void onClick(DialogInterface dialogInterface, int i) {
                if (z != 0) {
                    this.f2610b.m6123R();
                } else {
                    dialogInterface.dismiss();
                }
            }
        }).m3770e();
    }

    /* renamed from: R */
    private void m6123R() {
        C0936a.m3752a().m3757a(this).m3759a(false).m3753a((int) R.string.cannot_create_container).m3758a(getText(R.string.cannot_create_container_desc)).m3766c((int) R.string.ok).m3755a(new OnClickListener(this) {
            /* renamed from: a */
            final /* synthetic */ C1347a f2611a;

            {
                this.f2611a = r1;
            }

            public void onClick(DialogInterface dialogInterface, int i) {
                this.f2611a.m6153a(this.f2611a);
            }
        }).m3770e();
    }
}
