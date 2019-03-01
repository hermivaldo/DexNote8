package com.samsung.android.lxd;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.Rect;
import android.net.Uri;
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
import com.samsung.android.lxd.a.b;
import com.samsung.android.lxd.a.l;
import com.samsung.android.lxd.a.o;
import com.samsung.android.lxd.a.p;
import com.samsung.android.lxd.a.q;
import com.samsung.android.lxd.fragment.c;
import com.samsung.android.lxd.fragment.e;
import com.samsung.android.lxd.processor.ExecutionType;
import com.samsung.android.lxd.processor.Processor;
import com.samsung.android.lxd.processor.Processor.OpenContainerInfo;
import com.samsung.android.lxd.processor.config.SystemConfig;
import com.samsung.android.lxd.processor.config.SystemConfigManager;
import com.samsung.android.lxd.processor.config.SystemConfigType;
import com.samsung.android.lxd.processor.utils.log.Log;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map.Entry;

/* compiled from: BaseActivity */
public abstract class a extends Activity implements e {
    private static final String b = "a";
    public String a = null;
    private ArrayList<g> c = new ArrayList();
    private Processor d = null;
    private a e = a.INVALID;
    private boolean f = true;
    private boolean g = true;
    private boolean h = false;
    private boolean i = false;
    private boolean j = false;
    private Runnable k = null;
    private int l = 0;
    private com.samsung.android.lxd.fragment.a m = null;
    private p n = null;

    /* compiled from: BaseActivity */
    public enum a {
        INVALID,
        CREATED,
        STARTED,
        RESUMED,
        PAUSED,
        STOPPED,
        RESTARTED,
        DESTROYED
    }

    public void A() {
    }

    public void B() {
    }

    public void C() {
    }

    protected boolean H() {
        return true;
    }

    protected boolean I() {
        return false;
    }

    protected boolean J() {
        return true;
    }

    protected boolean K() {
        return false;
    }

    protected void L() {
    }

    public void b(String str, int i) {
    }

    public void c(String str, int i) {
    }

    public void c(String str, boolean z) {
    }

    public void d(String str, boolean z) {
    }

    public void e(String str, boolean z) {
    }

    public boolean h(String str) {
        return false;
    }

    public void i(String str) {
    }

    public void j(String str) {
    }

    public void k(String str) {
    }

    public void l(String str) {
    }

    public void m(String str) {
    }

    public void n(String str) {
    }

    public void o(String str) {
    }

    public boolean o() {
        return false;
    }

    public void r() {
    }

    public void t() {
    }

    public void u() {
    }

    public void v() {
    }

    public void w() {
    }

    public void x() {
    }

    public void y() {
    }

    public void z() {
    }

    private void a(a aVar) {
        this.e = aVar;
    }

    public void a() {
        String str = b;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(getClass().getSimpleName());
        stringBuilder.append(" [");
        stringBuilder.append(this.e);
        stringBuilder.append("]");
        Log.d(str, stringBuilder.toString());
    }

    public boolean b() {
        return this.e == a.CREATED || this.e == a.STARTED || this.e == a.RESTARTED || this.e == a.RESUMED;
    }

    public boolean c() {
        return this.e == a.STARTED || this.e == a.RESTARTED || this.e == a.RESUMED;
    }

    public boolean d() {
        return this.e == a.PAUSED;
    }

    public void e() {
        this.g = false;
    }

    public void f() {
        this.h = true;
    }

    public boolean g() {
        if (!o.B() && !h()) {
            return false;
        }
        Log.i(b, "moveToMainActivity: ");
        Intent intent = new Intent(LxdApplication.a(), o.l());
        intent.addFlags(32768);
        intent.addFlags(268435456);
        LxdApplication.a().startActivity(intent);
        return true;
    }

    public boolean h() {
        return o.G() ^ 1;
    }

    public void i() {
        this.f = false;
    }

    protected void attachBaseContext(Context context) {
        super.attachBaseContext(a(context));
    }

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        a(a.CREATED);
        this.d = Processor.getInstance();
        this.n = new p();
        setRequestedOrientation(getRequestedOrientation());
        a(getWindow());
        ActionBar actionBar = getActionBar();
        if (actionBar != null) {
            if (this.g) {
                actionBar.setDisplayHomeAsUpEnabled(this.f);
            } else {
                actionBar.hide();
            }
        }
        getWindow().getDecorView().setOnSystemUiVisibilityChangeListener(new OnSystemUiVisibilityChangeListener() {
            public void onSystemUiVisibilityChange(int i) {
                a.this.a(a.this.getWindow());
            }
        });
        this.l = getResources().getConfiguration().densityDpi;
        String str = b;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("onCreate: ");
        stringBuilder.append(getRequestedOrientation());
        stringBuilder.append(", dpi: ");
        stringBuilder.append(this.l);
        Log.d(str, stringBuilder.toString());
    }

    public void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        if (configuration.densityDpi != this.l) {
            String str = b;
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("onConfigurationChanged: densityUpdate old: ");
            stringBuilder.append(this.l);
            stringBuilder.append(", new: ");
            stringBuilder.append(configuration.densityDpi);
            Log.i(str, stringBuilder.toString());
            recreate();
            return;
        }
        a((Context) this);
    }

    protected void onRestart() {
        super.onRestart();
        a(a.RESTARTED);
        a(getWindow());
    }

    protected void onStart() {
        super.onStart();
        a(a.STARTED);
    }

    protected void onResume() {
        super.onResume();
        a(a.RESUMED);
        Q();
        g();
    }

    protected void onPause() {
        super.onPause();
        a(a.PAUSED);
    }

    protected void onStop() {
        super.onStop();
        a(a.STOPPED);
    }

    public void onBackPressed() {
        o.b(getCurrentFocus(), 0);
        super.onBackPressed();
    }

    public int getRequestedOrientation() {
        return o.c((Context) this);
    }

    public boolean onKeyDown(int i, KeyEvent keyEvent) {
        String str = b;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("onKeyDown: ");
        stringBuilder.append(keyEvent.toString());
        Log.d(str, stringBuilder.toString());
        if (!o.d((Context) this) || f.b() != 1 || (i == 4 ? keyEvent.getDeviceId() == -1 : i == 111)) {
            return super.onKeyDown(i, keyEvent);
        }
        String str2 = b;
        StringBuilder stringBuilder2 = new StringBuilder();
        stringBuilder2.append("moveTaskToBack: ");
        stringBuilder2.append(keyEvent.toString());
        Log.d(str2, stringBuilder2.toString());
        moveTaskToBack(true);
        return true;
    }

    public void onWindowFocusChanged(boolean z) {
        String str = b;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("onWindowFocusChanged: ");
        stringBuilder.append(z);
        Log.d(str, stringBuilder.toString());
        super.onWindowFocusChanged(z);
        a(getWindow());
    }

    public boolean a(MotionEvent motionEvent) {
        return this.d.handleGenericMotion(motionEvent);
    }

    public boolean a(int i, KeyEvent keyEvent) {
        return this.d.handleKeyDown(i, keyEvent) || super.onKeyDown(i, keyEvent);
    }

    public boolean b(int i, KeyEvent keyEvent) {
        return this.d.handleKeyUp(i, keyEvent) || super.onKeyUp(i, keyEvent);
    }

    public boolean b(MotionEvent motionEvent) {
        return this.d.handleTouchEvent(motionEvent);
    }

    public void a(final String str) {
        this.k = new Runnable() {
            public void run() {
                a.this.d.getDebugLog(str);
            }
        };
        N();
    }

    public void b(final String str) {
        this.k = new Runnable() {
            public void run() {
                a.this.d.getImageVersion(str);
            }
        };
        N();
    }

    public void c(String str) {
        this.d.rebaseImage(str);
    }

    public void d(final String str) {
        this.k = new Runnable() {
            public void run() {
                a.this.d.getImageMinSize(str);
            }
        };
        N();
    }

    public void a(final String str, final int i, final boolean z) {
        this.k = new Runnable() {
            public void run() {
                a.this.d.resizeImage(str, i, z);
            }
        };
        N();
    }

    public void a(final OpenContainerInfo openContainerInfo) {
        this.k = new Runnable() {
            public void run() {
                a.this.d.openContainer(openContainerInfo);
            }
        };
        N();
    }

    public void j() {
        this.d.closeContainer();
    }

    public void k() {
        this.d.pauseContainer();
    }

    public void l() {
        this.d.resumeContainer();
    }

    public void m() {
        this.d.startContainer();
    }

    public void n() {
        this.d.stopContainer();
    }

    public boolean p() {
        if (o.i("com.osp.app.signin")) {
            return false;
        }
        startActivityForResult(o.w(), 3);
        return true;
    }

    public void q() {
        String str = b;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("isWifi: ");
        stringBuilder.append(o.D());
        Log.d(str, stringBuilder.toString());
        startActivity(new Intent(this, WebViewActivity.class));
    }

    public void e(String str) {
        a(str, false);
    }

    public void a(String str, boolean z) {
        File file = new File(str);
        String str2 = b;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("checkImageFile isFile? ");
        stringBuilder.append(file.isFile());
        Log.d(str2, stringBuilder.toString());
        if (!file.isFile()) {
            a(z);
        } else if (p(str)) {
            b(z);
        } else {
            b(str);
        }
    }

    public void a(String str, boolean z, String str2) {
        a(str, z, str2, null);
    }

    public void a(String str, boolean z, String str2, String str3) {
        a(str, z, str2, str3, false);
    }

    public void a(String str, boolean z, String str2, String str3, boolean z2) {
        String str4 = b;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("getImageVersionResult path: ");
        stringBuilder.append(str);
        stringBuilder.append(", secure path: ");
        stringBuilder.append(o.b());
        Log.i(str4, stringBuilder.toString());
        if (!z) {
            h(str, z2);
        } else if (!o.d(str) || str.startsWith(o.b())) {
            a(str, str3, str2, z2);
        } else {
            String str5 = b;
            StringBuilder stringBuilder2 = new StringBuilder();
            stringBuilder2.append("non secured path: ");
            stringBuilder2.append(str);
            stringBuilder2.append(", secure path: ");
            stringBuilder2.append(o.b());
            Log.i(str5, stringBuilder2.toString());
            b(str, str3, str2, z2);
        }
    }

    public void a(String str, String str2, String str3, boolean z) {
        if (!z) {
            if (str3 == null) {
                Intent intent = new Intent(this, CreateActivity.class);
                intent.putExtra("imagePath", str);
                intent.putExtra("imageVersion", str2);
                intent.addFlags(268435456);
                intent.addFlags(67108864);
                startActivity(intent);
            } else {
                SystemConfig systemConfig = new SystemConfig();
                systemConfig.set(SystemConfigType.IMAGE_PATH, str);
                systemConfig.set(SystemConfigType.IMAGE_VERSION, str2);
                SystemConfigManager.update(Long.parseLong(str3), systemConfig);
                Intent intent2 = new Intent(this, ListActivity.class);
                intent2.putExtra("configId", str3);
                intent2.addFlags(67108864);
                startActivity(intent2);
            }
        }
    }

    public void a(Activity activity) {
        a(activity, null);
    }

    public void a(Activity activity, HashMap<String, String> hashMap) {
        Intent intent = new Intent(activity, ListActivity.class);
        intent.addFlags(67108864);
        if (hashMap != null) {
            for (String str : hashMap.keySet()) {
                String str2 = b;
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append("putExtra, key: ");
                stringBuilder.append(str);
                stringBuilder.append(", value: ");
                stringBuilder.append((String) hashMap.get(str));
                Log.d(str2, stringBuilder.toString());
                intent.putExtra(str, (String) hashMap.get(str));
            }
        }
        startActivity(intent);
    }

    public void a(ExecutionType executionType, String str) {
        b(SystemConfigManager.load(Long.parseLong(str)).get(SystemConfigType.IMAGE_PATH));
    }

    public void a(Window window) {
        if (o.d((Context) this) || o.e()) {
            window.getDecorView().setSystemUiVisibility((this.h ? 2 : 0) | 5124);
        }
    }

    public void a(File file) {
        ArrayList arrayList = new ArrayList();
        arrayList.add(file);
        a(arrayList);
    }

    public void a(ArrayList<File> arrayList) {
        this.m = c.j().a((ArrayList) arrayList).a(this).a((int) R.string.details).c((int) R.string.ok).a(new OnClickListener() {
            public void onClick(DialogInterface dialogInterface, int i) {
                a.this.m.dismiss();
            }
        }).e();
    }

    public void deleteImage(final String str) {
        final com.samsung.android.lxd.fragment.a a = com.samsung.android.lxd.fragment.a.a();
        a.a(this).a((int) R.string.delete_popup_title).b((int) R.string.delete_unmount_message).d(R.string.popup_delete).b(new OnClickListener() {
            public void onClick(DialogInterface dialogInterface, int i) {
                File file = new File(o.c(SystemConfigManager.load(Long.parseLong(str)).get(SystemConfigType.IMAGE_PATH)));
                if (file.isFile()) {
                    file.delete();
                }
                SystemConfigManager.remove(Long.parseLong(str));
                if (a.this.getComponentName().getClassName().equals(ListActivity.class.getName())) {
                    a.dismiss();
                } else {
                    Intent intent = new Intent(a.this, ListActivity.class);
                    intent.addFlags(67108864);
                    a.this.startActivity(intent);
                }
                a.this.r();
            }
        }).c((int) R.string.popup_unmount).a(new OnClickListener() {
            public void onClick(DialogInterface dialogInterface, int i) {
                SystemConfigManager.remove(Long.parseLong(str));
                String M = a.b;
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append(a.this.getComponentName().getClassName());
                stringBuilder.append(", ");
                stringBuilder.append(ListActivity.class.getName());
                Log.d(M, stringBuilder.toString());
                if (a.this.getComponentName().getClassName().equals(ListActivity.class.getName())) {
                    a.dismiss();
                } else {
                    a.this.a(a.this);
                }
                a.this.r();
            }
        }).e();
    }

    public void f(final String str) {
        final com.samsung.android.lxd.fragment.a a = com.samsung.android.lxd.fragment.a.a();
        a.a(this).a((int) R.string.unmount_image_popup_title).b((int) R.string.unmount_image_popup_message).d(R.string.popup_cancel).c((int) R.string.unmount_image).a(new OnClickListener() {
            public void onClick(DialogInterface dialogInterface, int i) {
                SystemConfigManager.remove(Long.parseLong(str));
                if (a.this.getComponentName().getClassName().equals(ListActivity.class.getName())) {
                    a.dismiss();
                } else {
                    a.this.a(a.this);
                }
                a.this.r();
            }
        }).e();
    }

    public void g(String str) {
        a(str, 0);
    }

    public void a(final String str, final int i) {
        if (o.d() == null) {
            b(str, i, false);
        } else {
            com.samsung.android.lxd.fragment.a.a().a(this).a((int) R.string.browse_image_title).b((int) R.string.browse_image_body).c((int) R.string.internal_storage_capital).a(new OnClickListener() {
                public void onClick(DialogInterface dialogInterface, int i) {
                    a.this.b(str, i, false);
                }
            }).d(R.string.sd_card_capital).b(new OnClickListener() {
                public void onClick(DialogInterface dialogInterface, int i) {
                    com.samsung.android.lxd.fragment.a.a().a(a.this).a((int) R.string.images_on_sd_card_popup_title).a(a.this.getText(R.string.images_on_sd_card_popup_message)).c((int) R.string.popup_continue).d(R.string.popup_cancel).a(new OnClickListener() {
                        public void onClick(DialogInterface dialogInterface, int i) {
                            a.this.b(str, i, true);
                        }
                    }).e();
                }
            }).e();
        }
    }

    private void b(final String str, final int i, final boolean z) {
        this.n.a((int) R.string.app_name, (int) R.string.wait, (Context) this, null);
        o.a(new Runnable() {
            public void run() {
                Intent intent = new Intent(a.this, BrowseActivity.class);
                if (str != null) {
                    intent.putExtra("configId", str);
                }
                if (z) {
                    intent.putExtra("searchSdCard", true);
                }
                intent.addFlags(i);
                a.this.startActivityForResult(intent, 7);
            }
        }, 50, true);
    }

    private void N() {
        if (f.e() && this.k != null) {
            this.k.run();
            this.k = null;
        }
    }

    public void s() {
        String str = b;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(getClass().getSimpleName());
        stringBuilder.append(" onServiceOpened: PendingRequest: ");
        stringBuilder.append(this.k != null);
        Log.d(str, stringBuilder.toString());
        N();
    }

    public void b(String str, boolean z) {
        String str2 = b;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("onDebugLogReceived: ");
        stringBuilder.append(z);
        stringBuilder.append(", ");
        stringBuilder.append(str);
        Log.d(str2, stringBuilder.toString());
        g(str, z);
    }

    public boolean f(String str, boolean z) {
        this.k = null;
        return false;
    }

    protected void onNewIntent(Intent intent) {
        if ("android.intent.action.SEARCH".equals(intent.getAction())) {
            m(intent.getStringExtra("query"));
        }
    }

    public void D() {
        E();
        for (Entry value : SystemConfigManager.loadAll().entrySet()) {
            String str = ((SystemConfig) value.getValue()).get(SystemConfigType.IMAGE_PATH);
            g gVar = new g(str, new com.samsung.android.lxd.g.a() {
                public void a() {
                    o.a(new Runnable() {
                        public void run() {
                            a.this.y();
                        }
                    });
                }
            });
            gVar.startWatching();
            this.c.add(gVar);
            String str2 = b;
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("startImageFileMonitor! ");
            stringBuilder.append(str);
            Log.d(str2, stringBuilder.toString());
        }
    }

    public void E() {
        Iterator it = this.c.iterator();
        while (it.hasNext()) {
            ((g) it.next()).stopWatching();
        }
        this.c.clear();
        Log.d(b, "stopImageFileMonitor!");
    }

    public void F() {
        this.d.startMonitoring();
    }

    public void G() {
        this.d.stopMonitoring();
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_more_options, menu);
        if (H()) {
            menu.findItem(R.id.settings).setVisible(true);
        }
        if (I()) {
            menu.findItem(R.id.contact_us).setVisible(true);
        }
        if (J()) {
            menu.findItem(R.id.error_report).setVisible(true);
        }
        if (K()) {
            menu.findItem(R.id.shortcuts).setVisible(true);
        }
        return super.onCreateOptionsMenu(menu);
    }

    public boolean onOptionsItemSelected(MenuItem menuItem) {
        String str = b;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("onOptionsItemSelected: ");
        stringBuilder.append(menuItem.getItemId());
        Log.d(str, stringBuilder.toString());
        switch (menuItem.getItemId()) {
            case 16908332:
                onBackPressed();
                break;
            case R.id.contact_us:
                l.a(this.a, String.valueOf(2));
                b.a((Activity) this);
                break;
            case R.id.error_report:
                l.a(this.a, String.valueOf(3));
                O();
                break;
            case R.id.settings:
                l.a(this.a, String.valueOf(1));
                startActivity(new Intent(this, SettingsActivity.class));
                break;
            case R.id.shortcuts:
                l.a("012", String.valueOf(1201));
                L();
                break;
        }
        return super.onOptionsItemSelected(menuItem);
    }

    protected void onActivityResult(int i, int i2, Intent intent) {
        String str = b;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("onActivityResult: ");
        stringBuilder.append(i);
        stringBuilder.append(", ");
        stringBuilder.append(i2);
        Log.d(str, stringBuilder.toString());
        if (i != 7) {
            super.onActivityResult(i, i2, intent);
            return;
        }
        Log.d(b, "open Browse activity");
        this.n.a();
    }

    private void O() {
        Log.d(b, "selectErrorReport: ");
        if (o.h()) {
            P();
            return;
        }
        this.j = false;
        e.j().a(new com.samsung.android.lxd.fragment.e.a() {
            public void a(boolean z) {
                String M = a.b;
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append("onCheckBoxChanged: ");
                stringBuilder.append(z);
                Log.d(M, stringBuilder.toString());
                a.this.j = z;
            }
        }).a(this).a(true).a((int) R.string.error_report_popup_title).d(R.string.popup_cancel).c((int) R.string.report_error).a(new OnClickListener() {
            public void onClick(DialogInterface dialogInterface, int i) {
                l.a("011", String.valueOf(1102));
                l.a("011", String.valueOf(1101), a.this.j ? 0 : 1);
                a.this.P();
                if (a.this.j) {
                    o.i();
                }
            }
        }).b(new OnClickListener() {
            public void onClick(DialogInterface dialogInterface, int i) {
                l.a("011", String.valueOf(1103));
            }
        }).e();
    }

    private void P() {
        String str = b;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("processErrorReport: ");
        stringBuilder.append(this.i);
        Log.d(str, stringBuilder.toString());
        if (!this.i) {
            this.i = true;
            this.n.a((int) R.string.app_name, (int) R.string.wait, (Context) this, null);
            LxdApplication.a("LoD_Logs");
            StringBuilder stringBuilder2 = new StringBuilder();
            stringBuilder2.append(LxdApplication.a().getFilesDir().getAbsolutePath());
            stringBuilder2.append(File.separator);
            stringBuilder2.append("LoD_Logs");
            a(stringBuilder2.toString());
        }
    }

    private void g(String str, boolean z) {
        String str2;
        if (this.i) {
            this.i = false;
            this.n.a();
            String str3 = b;
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("processErrorReport: ");
            stringBuilder.append(z);
            stringBuilder.append(", ");
            stringBuilder.append(str);
            Log.i(str3, stringBuilder.toString());
            StringBuilder stringBuilder2 = new StringBuilder();
            stringBuilder2.append(LxdApplication.a().getFilesDir().getAbsolutePath());
            stringBuilder2.append(File.separator);
            stringBuilder2.append("LoD_Logs");
            str3 = stringBuilder2.toString();
            File file = new File(str3);
            List arrayList = new ArrayList();
            o.a(file, arrayList);
            if (z && str != null) {
                File file2 = new File(str);
                if (!file.getAbsolutePath().equals(file2.getAbsolutePath())) {
                    try {
                        Log.d(b, "search nst log folder");
                        o.a(file2, arrayList);
                    } catch (Exception e) {
                        str2 = b;
                        stringBuilder = new StringBuilder();
                        stringBuilder.append("fail to find nst log: ");
                        stringBuilder.append(e.toString());
                        Log.e(str2, stringBuilder.toString());
                    }
                }
            }
            str = b;
            StringBuilder stringBuilder3 = new StringBuilder();
            stringBuilder3.append("files: ");
            stringBuilder3.append(arrayList.toString());
            Log.d(str, stringBuilder3.toString());
            stringBuilder3 = new StringBuilder();
            stringBuilder3.append(str3);
            stringBuilder3.append(".zip");
            File file3 = new File(stringBuilder3.toString());
            try {
                q.a(arrayList, file3);
            } catch (IOException e2) {
                str3 = b;
                stringBuilder = new StringBuilder();
                stringBuilder.append("zip exception: ");
                stringBuilder.append(e2.toString());
                Log.i(str3, stringBuilder.toString());
                e2.printStackTrace();
            }
            Uri d = o.d(file3);
            str2 = b;
            stringBuilder2 = new StringBuilder();
            stringBuilder2.append("Uris: ");
            stringBuilder2.append(d.toString());
            Log.d(str2, stringBuilder2.toString());
            stringBuilder3 = new StringBuilder();
            stringBuilder3.append(getString(R.string.error_report_mail_subject));
            stringBuilder3.append(o.a(new SimpleDateFormat("[MM/dd, HH:mm]")));
            stringBuilder3.append(" (");
            stringBuilder3.append(o.v());
            stringBuilder3.append(")");
            o.a(new String[]{"lod.sec@samsung.com"}, stringBuilder3.toString(), getString(R.string.error_report_mail_content), d);
        }
    }

    private static Context a(Context context) {
        Configuration configuration = new Configuration(context.getResources().getConfiguration());
        String str = b;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("updateFontScaleResources: ");
        stringBuilder.append(configuration.fontScale);
        Log.d(str, stringBuilder.toString());
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

    private void Q() {
        try {
            final View childAt = ((ViewGroup) findViewById(16908290)).getChildAt(0);
            childAt.getViewTreeObserver().addOnGlobalLayoutListener(new OnGlobalLayoutListener() {
                private int c = 0;

                public void onGlobalLayout() {
                    Rect rect = new Rect();
                    childAt.getWindowVisibleDisplayFrame(rect);
                    int height = rect.height();
                    if (height != this.c) {
                        int height2 = childAt.getRootView().getHeight();
                        if (height2 - height > height2 / 4) {
                            Log.d(a.b, "onSoftInputShown");
                            a.this.B();
                        } else {
                            Log.d(a.b, "onSoftInputHidden");
                            a.this.C();
                        }
                    }
                    this.c = height;
                }
            });
        } catch (Exception e) {
            String str = b;
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("Failed to add soft input listener : ");
            stringBuilder.append(e.toString());
            Log.e(str, stringBuilder.toString());
        }
    }

    private boolean p(String str) {
        for (Entry value : SystemConfigManager.loadAll().entrySet()) {
            if (((SystemConfig) value.getValue()).get(SystemConfigType.IMAGE_PATH).equalsIgnoreCase(str)) {
                return true;
            }
        }
        return false;
    }

    private void a(final boolean z) {
        com.samsung.android.lxd.fragment.a.a().a(this).a((int) R.string.image_not_found_title).b((int) R.string.image_not_found_create_message).a(z ^ 1).c((int) R.string.browse).a(new OnClickListener() {
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
                a.this.a(null, 67108864);
            }
        }).d(R.string.popup_cancel).b(new OnClickListener() {
            public void onClick(DialogInterface dialogInterface, int i) {
                if (z) {
                    a.this.R();
                    return;
                }
                dialogInterface.dismiss();
                Intent intent = new Intent(a.this, ListActivity.class);
                intent.addFlags(67108864);
                a.this.startActivity(intent);
            }
        }).e();
    }

    private void b(final boolean z) {
        com.samsung.android.lxd.fragment.a.a().a(this).a(z ^ 1).a((int) R.string.image_already_in_use_popup_title).b((int) R.string.image_already_in_use_popup_message).c((int) R.string.ok).a(new OnClickListener() {
            public void onClick(DialogInterface dialogInterface, int i) {
                if (z) {
                    a.this.R();
                } else {
                    dialogInterface.dismiss();
                }
            }
        }).e();
    }

    private void b(String str, String str2, String str3, final boolean z) {
        final String str4 = str;
        final String str5 = str2;
        final String str6 = str3;
        final boolean z2 = z;
        com.samsung.android.lxd.fragment.a.a().a(this).a(z ^ 1).a((int) R.string.non_secured_path).a(getText(R.string.non_secured_path_content)).c((int) R.string.popup_continue).d(R.string.popup_cancel).a(new OnClickListener() {
            public void onClick(DialogInterface dialogInterface, int i) {
                a.this.a(str4, str5, str6, z2);
            }
        }).b(new OnClickListener() {
            public void onClick(DialogInterface dialogInterface, int i) {
                if (z) {
                    a.this.R();
                } else {
                    dialogInterface.dismiss();
                }
            }
        }).e();
    }

    private void h(String str, final boolean z) {
        int i;
        int i2;
        if (o.a(str, 0, true) != 0) {
            i = R.string.image_not_found_title;
            i2 = R.string.image_not_found_create_message;
        } else {
            i = R.string.incorrect_image_title;
            i2 = R.string.incorrect_image_body;
        }
        com.samsung.android.lxd.fragment.a.a().a(this).a(i).b(i2).a(z ^ 1).c((int) R.string.ok).a(new OnClickListener() {
            public void onClick(DialogInterface dialogInterface, int i) {
                if (z) {
                    a.this.R();
                } else {
                    dialogInterface.dismiss();
                }
            }
        }).e();
    }

    private void R() {
        com.samsung.android.lxd.fragment.a.a().a(this).a(false).a((int) R.string.cannot_create_container).a(getText(R.string.cannot_create_container_desc)).c((int) R.string.ok).a(new OnClickListener() {
            public void onClick(DialogInterface dialogInterface, int i) {
                a.this.a(a.this);
            }
        }).e();
    }
}
