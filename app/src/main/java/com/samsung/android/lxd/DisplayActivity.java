package com.samsung.android.lxd;

import android.app.Activity;
import android.app.FragmentTransaction;
import android.content.ComponentName;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.SharedPreferences.Editor;
import android.content.res.Configuration;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.style.StyleSpan;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import com.samsung.android.lxd.a.h;
import com.samsung.android.lxd.a.o;
import com.samsung.android.lxd.a.p;
import com.samsung.android.lxd.fragment.j;
import com.samsung.android.lxd.processor.ExecutionType;
import com.samsung.android.lxd.processor.Processor.OpenContainerInfo;
import com.samsung.android.lxd.processor.config.SystemConfigManager;
import com.samsung.android.lxd.processor.config.SystemConfigType;
import com.samsung.android.lxd.processor.network.audio.NetworkAudioType;
import com.samsung.android.lxd.processor.network.display.INetworkDisplay;
import com.samsung.android.lxd.processor.network.display.NetworkDisplayFactory;
import com.samsung.android.lxd.processor.network.display.NetworkDisplayType;
import com.samsung.android.lxd.processor.network.display.NetworkScreenType;
import com.samsung.android.lxd.processor.utils.log.Log;
import com.samsung.android.view.SemWindowManager;

public class DisplayActivity extends a {
    private static final String b = "DisplayActivity";
    private String c = null;
    private com.samsung.android.lxd.a.a d = null;
    private com.samsung.android.lxd.fragment.a e = null;
    private ExecutionType f = null;
    private b g = null;
    private String h = null;
    private p i = null;
    private i j = null;
    private boolean k = false;

    private abstract class b {
        protected int a() {
            return 0;
        }

        protected void a(Bundle bundle) {
        }

        protected boolean a(int i, int i2, KeyEvent keyEvent) {
            return false;
        }

        protected boolean a(int i, KeyEvent keyEvent) {
            if (i != 1006) {
                switch (i) {
                    case 24:
                    case 25:
                        break;
                    default:
                        return true;
                }
            }
            return false;
        }

        protected boolean a(String str) {
            return false;
        }

        protected int b() {
            return 0;
        }

        protected void b(String str) {
        }

        protected boolean b(int i, KeyEvent keyEvent) {
            if (i != 1006) {
                switch (i) {
                    case 24:
                    case 25:
                        break;
                    default:
                        return true;
                }
            }
            return false;
        }

        protected int c() {
            return 0;
        }

        protected boolean d() {
            return false;
        }

        protected boolean e() {
            return true;
        }

        protected boolean f() {
            return false;
        }

        protected void g() {
        }

        protected void h() {
        }

        protected void i() {
        }

        protected void j() {
        }

        protected void k() {
        }

        protected boolean l() {
            return false;
        }

        protected boolean m() {
            return false;
        }

        protected boolean n() {
            return false;
        }

        protected boolean o() {
            return false;
        }

        protected boolean p() {
            return false;
        }

        private b() {
        }

        /* synthetic */ b(DisplayActivity displayActivity, AnonymousClass1 anonymousClass1) {
            this();
        }

        protected boolean a(MotionEvent motionEvent) {
            return DisplayActivity.this.a(motionEvent);
        }

        protected boolean b(MotionEvent motionEvent) {
            return DisplayActivity.this.b(motionEvent);
        }

        protected boolean a(KeyEvent keyEvent) {
            return keyEvent.getDeviceId() == -1;
        }
    }

    private class a extends b {
        private a c;

        private class a {
            private j b;

            /* synthetic */ a(a aVar, AnonymousClass1 anonymousClass1) {
                this();
            }

            private a() {
                this.b = null;
            }

            private void a() {
                this.b = j.a().b();
                FragmentTransaction beginTransaction = DisplayActivity.this.getFragmentManager().beginTransaction();
                beginTransaction.replace(R.id.virtualKey, this.b);
                beginTransaction.commit();
            }

            private void b() {
                this.b.b();
            }
        }

        protected int a() {
            return R.id.baseCanvas;
        }

        protected int b() {
            return R.layout.activity_display_cli;
        }

        protected boolean d() {
            return true;
        }

        protected boolean e() {
            return false;
        }

        protected boolean f() {
            return true;
        }

        protected void j() {
        }

        protected void k() {
        }

        private a() {
            super(DisplayActivity.this, null);
            this.c = new a(this, null);
        }

        /* synthetic */ a(DisplayActivity displayActivity, AnonymousClass1 anonymousClass1) {
            this();
        }

        protected int c() {
            return super.e();
        }

        protected boolean a(String str) {
            String N = DisplayActivity.b;
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("handleContainerError : ");
            stringBuilder.append(str);
            Log.i(N, stringBuilder.toString());
            Object obj = (str.hashCode() == -1256120667 && str.equals("normalExit")) ? null : -1;
            if (obj != null) {
                return DisplayActivity.this.U();
            }
            DisplayActivity.this.runOnUiThread(new Runnable() {
                public void run() {
                    DisplayActivity.this.P();
                }
            });
            return true;
        }

        protected void g() {
            DisplayActivity.this.i.a((int) R.string.shortcut_control_and_function_keys, q(), DisplayActivity.this, new com.samsung.android.lxd.a.p.a() {
                public void a() {
                    DisplayActivity.this.j.f();
                }
            });
        }

        protected void h() {
            o.b(DisplayActivity.this.getCurrentFocus(), 0);
        }

        protected void a(Bundle bundle) {
            DisplayActivity.this.getWindow().setSoftInputMode(18);
        }

        protected void i() {
            DisplayActivity.this.getWindow().getDecorView().setBackgroundColor(-16777216);
            DisplayActivity.this.getWindow().setStatusBarColor(DisplayActivity.this.getColor(R.color.LightTheme.dialog_background_contents_area_background));
            this.c.a();
        }

        protected boolean a(int i, KeyEvent keyEvent) {
            return i == 82 ? false : super.a(i, keyEvent);
        }

        protected boolean b(int i, KeyEvent keyEvent) {
            return i == 82 ? false : super.b(i, keyEvent);
        }

        protected boolean a(KeyEvent keyEvent) {
            if (keyEvent.getSource() == 8194) {
                return true;
            }
            return super.a(keyEvent);
        }

        protected void b(String str) {
            this.c.b();
        }

        private String q() {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(DisplayActivity.this.getResources().getString(R.string.shortcut_control_keys).replaceAll("KEY_NAME", "Ctrl"));
            stringBuilder.append("\n\n");
            stringBuilder.append(DisplayActivity.this.getResources().getString(R.string.shortcut_function_keys).replaceAll("KEY_NAME", "Fn"));
            return stringBuilder.toString();
        }
    }

    private class c extends b {
        protected int a() {
            return R.id.glCanvas;
        }

        protected int b() {
            return R.layout.activity_display_gui;
        }

        protected int c() {
            return 0;
        }

        protected boolean l() {
            return true;
        }

        protected boolean m() {
            return true;
        }

        protected boolean n() {
            return true;
        }

        protected boolean o() {
            return true;
        }

        private c() {
            super(DisplayActivity.this, null);
        }

        /* synthetic */ c(DisplayActivity displayActivity, AnonymousClass1 anonymousClass1) {
            this();
        }

        protected boolean a(String str) {
            return DisplayActivity.this.U();
        }

        protected boolean d() {
            return o.d(DisplayActivity.this) || o.e();
        }

        protected boolean p() {
            return o.y();
        }

        public boolean a(MotionEvent motionEvent) {
            try {
                super.a(motionEvent);
                Thread.sleep(20);
            } catch (Exception e) {
                String N = DisplayActivity.b;
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append("INPUT_INTERVAL_CALIBRATION exception!");
                stringBuilder.append(e);
                Log.e(N, stringBuilder.toString());
            }
            return true;
        }

        protected void a(Bundle bundle) {
            DisplayActivity.this.e();
            DisplayActivity.this.f();
        }

        protected boolean a(int i, int i2, KeyEvent keyEvent) {
            if (i != 0 || keyEvent.getDeviceId() != -1) {
                return false;
            }
            DisplayActivity.this.a(i, keyEvent);
            return true;
        }

        protected boolean b(MotionEvent motionEvent) {
            try {
                super.b(motionEvent);
                Thread.sleep(20);
            } catch (Exception e) {
                String N = DisplayActivity.b;
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append("INPUT_INTERVAL_CALIBRATION exception!");
                stringBuilder.append(e);
                Log.e(N, stringBuilder.toString());
            }
            return true;
        }
    }

    protected boolean H() {
        return false;
    }

    protected boolean I() {
        return false;
    }

    protected boolean J() {
        return false;
    }

    public boolean o() {
        return true;
    }

    public void onCreate(Bundle bundle) {
        Log.d(b, "OnCreate");
        this.d = com.samsung.android.lxd.a.a.a();
        this.c = getIntent().getStringExtra("configId");
        this.f = (ExecutionType) getIntent().getSerializableExtra("executionType");
        this.g = this.f == ExecutionType.CLI ? new a(this, null) : new c(this, null);
        this.i = new p();
        this.j = i.a();
        this.g.a(bundle);
        super.onCreate(bundle);
        this.a = "001";
        this.j.a(this, this.a, this.f);
        if (this.g.e()) {
            getWindow().setFlags(1024, 1024);
        }
        DesktopReceiver.a().a((Activity) this);
        DiagnosticReceiver.a().a(this);
        SemWindowManager.getInstance().requestMetaKeyEvent(new ComponentName(this, DisplayActivity.class), true);
        setContentView(this.g.b());
        View view = (View) NetworkDisplayFactory.setInstance(S(), (INetworkDisplay) findViewById(this.g.a()));
        if (this.g.m()) {
            view.setFocusableInTouchMode(true);
            view.requestFocus();
        }
        if (!Q()) {
            R();
        } else if (!f.d()) {
            String str = b;
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("openContainer: ");
            stringBuilder.append(this.f);
            Log.d(str, stringBuilder.toString());
            this.i.a((int) R.string.app_name, (int) R.string.wait, (Context) this, null);
            b(SystemConfigManager.load(Long.parseLong(this.c)).get(SystemConfigType.IMAGE_PATH));
        }
        this.g.i();
    }

    protected void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
        bundle.putBoolean("forcePauseState", this.k);
    }

    protected void onRestoreInstanceState(Bundle bundle) {
        super.onRestoreInstanceState(bundle);
        if (bundle != null) {
            this.k = bundle.getBoolean("forcePauseState");
        }
    }

    public void onConfigurationChanged(Configuration configuration) {
        Log.d(b, "onConfigurationChanged");
        super.onConfigurationChanged(configuration);
        String str = b;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Dex Mode: ");
        stringBuilder.append(o.d((Context) this));
        Log.d(str, stringBuilder.toString());
        if (this.e != null && o.d((Context) this)) {
            this.e.dismissAllowingStateLoss();
            this.e = null;
        }
    }

    protected void onDestroy() {
        Log.d(b, "onDestroy");
        super.onDestroy();
    }

    public boolean g() {
        if (!super.g()) {
            return false;
        }
        Log.i(b, "SA is removed, cleanContainer");
        P();
        return true;
    }

    protected void onResume() {
        String str = b;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("onResume: ");
        stringBuilder.append(this.k);
        Log.d(str, stringBuilder.toString());
        super.onResume();
        if (Q()) {
            o.r();
            if (!f.d()) {
                return;
            }
            if (this.k) {
                Log.e(b, "can't resumeContainer force pause state ");
                return;
            }
            Log.d(b, "resumeContainer");
            l();
        }
    }

    protected void onPause() {
        Log.d(b, "onPause");
        super.onPause();
        this.g.h();
    }

    protected void onStop() {
        Log.d(b, "onStop");
        if (!isFinishing() && f.d()) {
            Log.d(b, "pauseContainer");
            k();
        }
        super.onStop();
    }

    public void onBackPressed() {
        if (this.f == ExecutionType.CLI) {
            this.j.g();
        }
        a(new Runnable() {
            public void run() {
                DisplayActivity.this.P();
                super.b();
            }
        });
    }

    public void t() {
        Log.d(b, "Container started successfully!");
        m();
    }

    public boolean h(String str) {
        String str2 = b;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("onContainerError: ");
        stringBuilder.append(str);
        Log.i(str2, stringBuilder.toString());
        return this.g.a(str);
    }

    public void i(String str) {
        String str2 = b;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Protocol started successfully!");
        stringBuilder.append(str);
        Log.d(str2, stringBuilder.toString());
        this.i.a();
        if (f.a() || !Q()) {
            k();
        }
        DesktopReceiver.a().a(this.f);
        if (this.g.l()) {
            this.d.d();
        }
        if (this.g.n()) {
            h.a().b();
        }
        if (this.g.p()) {
            DiagnosticReceiver.a().b();
        }
        if (this.g.o() && this.c != null) {
            Editor edit = getSharedPreferences("prefs", 0).edit();
            edit.putString("configId", this.c);
            edit.apply();
            this.c = null;
        }
        V();
    }

    public void v() {
        V();
    }

    public void j(String str) {
        Log.d(b, "Audio connected successfully!");
    }

    public void k(String str) {
        Log.d(b, "Audio disconnected successfully!");
    }

    public void b(String str, int i) {
        String str2 = b;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("onImageVersionReceived : ");
        stringBuilder.append(str);
        stringBuilder.append(", result : ");
        stringBuilder.append(i);
        Log.i(str2, stringBuilder.toString());
        if (i == 0) {
            this.h = str;
            O();
            return;
        }
        U();
    }

    public void c(String str, final int i) {
        String str2 = b;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("onMemoryUsageReceived!: ");
        stringBuilder.append(str);
        stringBuilder.append(", stage: ");
        stringBuilder.append(i);
        Log.i(str2, stringBuilder.toString());
        if (i >= 1 && i <= 3) {
            StringBuilder stringBuilder2 = new StringBuilder();
            stringBuilder2.append(getString(R.string.memory_waring_popup_body_top));
            stringBuilder2.append(" ");
            stringBuilder2.append(str);
            stringBuilder2.append("MB.\n\n");
            str = stringBuilder2.toString();
            int length = str.length();
            int i2 = R.string.memory_waring_popup_title1;
            StringBuilder stringBuilder3;
            switch (i) {
                case 1:
                    stringBuilder3 = new StringBuilder();
                    stringBuilder3.append(str);
                    stringBuilder3.append(getText(R.string.memory_waring_popup_body1));
                    str = stringBuilder3.toString();
                    break;
                case 2:
                    i2 = R.string.memory_waring_popup_title2;
                    stringBuilder3 = new StringBuilder();
                    stringBuilder3.append(str);
                    stringBuilder3.append(getText(R.string.memory_waring_popup_body2));
                    str = stringBuilder3.toString();
                    break;
                case 3:
                    Log.i(b, ", force pause:: 2131624102");
                    i2 = R.string.memory_waring_popup_title3;
                    str = getString(R.string.memory_waring_popup_body3);
                    this.k = true;
                    k();
                    length = 0;
                    break;
            }
            int length2 = str.length();
            StringBuilder stringBuilder4 = new StringBuilder();
            stringBuilder4.append(str);
            stringBuilder4.append("\n\n");
            stringBuilder4.append(getString(i == 3 ? R.string.memory_waring_popup_body_bottom3 : R.string.memory_waring_popup_body_bottom));
            CharSequence spannableString = new SpannableString(stringBuilder4.toString());
            spannableString.setSpan(new StyleSpan(1), length, length2, 0);
            final com.samsung.android.lxd.fragment.a a = com.samsung.android.lxd.fragment.a.a();
            a.a((a) this).a(i2).a(spannableString).c((int) R.string.ok).a(new OnClickListener() {
                public void onClick(DialogInterface dialogInterface, int i) {
                    DisplayActivity.this.j.a(i);
                    a.dismiss();
                }
            }).a(false).e();
        }
    }

    public void n() {
        super.n();
    }

    public void l() {
        super.l();
        this.j.d();
    }

    public void k() {
        super.k();
        this.j.e();
    }

    public void j() {
        super.j();
        this.j.c();
    }

    public boolean f(String str, boolean z) {
        super.f(str, z);
        String str2 = b;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("onNetworkServiceError: ");
        stringBuilder.append(str);
        Log.i(str2, stringBuilder.toString());
        return U();
    }

    protected boolean K() {
        return this.g.f();
    }

    protected void L() {
        this.g.g();
    }

    public boolean onKeyDown(int i, KeyEvent keyEvent) {
        if (!this.g.a(i, keyEvent)) {
            return super.onKeyDown(i, keyEvent);
        }
        if (i != 4 || !this.g.a(keyEvent)) {
            return a(i, keyEvent);
        }
        Log.d(b, "VIRTUAL BACK KEY DOWN");
        a(new Runnable() {
            public void run() {
                DisplayActivity.this.P();
            }
        });
        return true;
    }

    public boolean onKeyUp(int i, KeyEvent keyEvent) {
        if (!this.g.b(i, keyEvent)) {
            return super.onKeyUp(i, keyEvent);
        }
        if (i == 4 && keyEvent.getDeviceId() == -1) {
            return true;
        }
        return b(i, keyEvent);
    }

    public boolean onKeyMultiple(int i, int i2, KeyEvent keyEvent) {
        return !this.g.a(i, i2, keyEvent) ? super.onKeyMultiple(i, i2, keyEvent) : true;
    }

    public boolean onTouchEvent(MotionEvent motionEvent) {
        return !this.g.b(motionEvent) ? super.onTouchEvent(motionEvent) : true;
    }

    public boolean onGenericMotionEvent(MotionEvent motionEvent) {
        if (getActionBar() == null || ((float) getActionBar().getHeight()) < motionEvent.getY()) {
            return !this.g.a(motionEvent) ? super.onGenericMotionEvent(motionEvent) : true;
        } else {
            Log.d(b, "skip event from ActionBar");
            return super.onGenericMotionEvent(motionEvent);
        }
    }

    public void l(String str) {
        String str2 = b;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("onCutTextReceived ");
        stringBuilder.append(str);
        Log.d(str2, stringBuilder.toString());
        this.d.a(str);
    }

    public void n(String str) {
        this.j.a(str);
    }

    public void B() {
        this.g.j();
    }

    public void C() {
        this.g.k();
    }

    public void o(String str) {
        this.g.b(str);
    }

    public int getRequestedOrientation() {
        return this.g.c();
    }

    private void O() {
        NetworkScreenType T = T();
        String str = b;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("openContainer: ");
        stringBuilder.append(this.f);
        stringBuilder.append(", NetworkScreenSize: ");
        stringBuilder.append(T);
        Log.d(str, stringBuilder.toString());
        a(new OpenContainerInfo(this.c, this.h, this.f, S(), NetworkAudioType.PULSE, T));
        this.j.b();
    }

    private void P() {
        o.b(getCurrentFocus(), 0);
        n();
        j();
        DesktopReceiver.a().b();
        if (this.g.l()) {
            this.d.e();
        }
        if (this.g.n()) {
            h.a().c();
        }
        if (this.g.p()) {
            DiagnosticReceiver.a().c();
        }
        finish();
    }

    private boolean Q() {
        Log.d(b, "isVMRunnable");
        return this.g.d();
    }

    private void a(final Runnable runnable) {
        if (this.e != null) {
            this.e.dismiss();
            this.e = null;
        }
        this.e = com.samsung.android.lxd.fragment.a.a().a((a) this).a((int) R.string.lod_container_shut_down_title).b((int) R.string.confirm_container_shutdown).c((int) R.string.ok).a(new OnClickListener() {
            public void onClick(DialogInterface dialogInterface, int i) {
                DisplayActivity.this.e.dismiss();
                runnable.run();
            }
        }).d(R.string.popup_cancel).b(new OnClickListener() {
            public void onClick(DialogInterface dialogInterface, int i) {
                DisplayActivity.this.e.dismiss();
            }
        }).e();
    }

    private void R() {
        this.j.h();
        if (this.e != null) {
            this.e.dismiss();
            this.e = null;
        }
        this.e = com.samsung.android.lxd.fragment.a.a().a((a) this).b((int) R.string.undock_phone_notice_msg_popup).f(0).c((int) R.string.close_app).a(new OnClickListener() {
            public void onClick(DialogInterface dialogInterface, int i) {
                Log.i(DisplayActivity.b, "click close app");
                DisplayActivity.this.j.i();
                o.p();
            }
        }).a(false).e();
    }

    private NetworkDisplayType S() {
        switch (this.f) {
            case CLI:
                return NetworkDisplayType.BASE;
            case GUI:
            case QUICK_GUI:
                return NetworkDisplayType.GLES;
            default:
                return NetworkDisplayType.INVALID;
        }
    }

    private NetworkScreenType T() {
        NetworkScreenType networkScreenType = NetworkScreenType.RESOLUTION_16_9;
        if (o.g((Context) this)) {
            return NetworkScreenType.RESOLUTION_16_10_STANDALONE;
        }
        float f = (float) o.m(this).x;
        float f2 = (float) o.m(this).y;
        float f3 = 0.0f;
        if (f2 != 0.0f) {
            f3 = f / f2;
        }
        String str = b;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("openContainer: ratio: ");
        stringBuilder.append(f3);
        stringBuilder.append(", width: ");
        stringBuilder.append(f);
        stringBuilder.append(", height: ");
        stringBuilder.append(f2);
        Log.i(str, stringBuilder.toString());
        double d = (double) f3;
        if (d > 2.33d) {
            Log.i(b, "use 21:9 resolution");
            return NetworkScreenType.RESOLUTION_21_9_MONITOR;
        } else if (d > 1.77d) {
            Log.i(b, "use 16:9 resolution");
            return NetworkScreenType.RESOLUTION_16_9;
        } else if (d < 1.6d) {
            return networkScreenType;
        } else {
            networkScreenType = NetworkScreenType.RESOLUTION_16_10;
            Log.i(b, "use 16:10 resolution");
            return networkScreenType;
        }
    }

    private boolean U() {
        if (c()) {
            this.i.a((int) R.string.running_container_error_title, getString(R.string.running_container_error_message), (Context) this, new com.samsung.android.lxd.a.p.a() {
                public void a() {
                    DisplayActivity.this.P();
                }
            });
        } else {
            P();
        }
        return true;
    }

    private void V() {
        if (o.y()) {
            if (o.n(this)) {
                F();
            } else {
                G();
            }
        }
    }
}
