package com.samsung.android.lxd;

import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.os.Bundle;
import com.samsung.android.lxd.fragment.C0936a;
import com.samsung.android.lxd.p064a.C0874l;
import com.samsung.android.lxd.p064a.C0877o;
import com.samsung.android.lxd.p064a.C0884p;
import com.samsung.android.lxd.processor.ExecutionType;
import com.samsung.android.lxd.processor.config.SystemConfig;
import com.samsung.android.lxd.processor.config.SystemConfigManager;
import com.samsung.android.lxd.processor.config.SystemConfigType;
import com.samsung.android.lxd.processor.utils.Utils;
import com.samsung.android.lxd.processor.utils.log.Log;

/* compiled from: RunActivity */
/* renamed from: com.samsung.android.lxd.h */
public class C1459h extends C1347a {
    /* renamed from: b */
    private static final String f4965b = "h";
    /* renamed from: c */
    private boolean f4966c = false;
    /* renamed from: d */
    private ExecutionType f4967d = ExecutionType.INVALID;
    /* renamed from: e */
    private String f4968e = null;
    /* renamed from: f */
    private C0884p f4969f = null;

    /* compiled from: RunActivity */
    /* renamed from: com.samsung.android.lxd.h$1 */
    class C09521 implements OnClickListener {
        /* renamed from: a */
        final /* synthetic */ C1459h f2997a;

        C09521(C1459h c1459h) {
            this.f2997a = c1459h;
        }

        public void onClick(DialogInterface dialogInterface, int i) {
            this.f2997a.finish();
        }
    }

    /* compiled from: RunActivity */
    /* renamed from: com.samsung.android.lxd.h$2 */
    class C09532 implements OnClickListener {
        /* renamed from: a */
        final /* synthetic */ C1459h f2998a;

        C09532(C1459h c1459h) {
            this.f2998a = c1459h;
        }

        public void onClick(DialogInterface dialogInterface, int i) {
            this.f2998a.mo1280c(SystemConfigManager.load(Long.parseLong(this.f2998a.f4968e)).get(SystemConfigType.IMAGE_PATH));
        }
    }

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.f4969f = new C0884p();
    }

    /* renamed from: a */
    public void mo1307a(ExecutionType executionType, String str) {
        if (m7086p(str) || C0877o.m3535t()) {
            String str2 = f4965b;
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("handleGetImageVersion: ");
            stringBuilder.append(this.f4966c);
            Log.m3853d(str2, stringBuilder.toString());
            if (!this.f4966c) {
                this.f4966c = true;
                this.f4969f.m3547a((int) R.string.app_name, (int) R.string.wait, (Context) this, null);
                this.f4967d = executionType;
                this.f4968e = str;
                super.mo1307a(executionType, str);
                return;
            }
            return;
        }
        m7082b(executionType, str);
    }

    /* renamed from: b */
    public void mo703b(String str, int i) {
        String str2 = f4965b;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("onImageVersion result: ");
        stringBuilder.append(this.f4966c);
        stringBuilder.append(", imageVersion: ");
        stringBuilder.append(str);
        Log.m3853d(str2, stringBuilder.toString());
        if (this.f4966c) {
            this.f4969f.m3546a();
            this.f4966c = false;
            if (i == 0) {
                SystemConfig systemConfig = new SystemConfig();
                systemConfig.set(SystemConfigType.IMAGE_VERSION, str);
                SystemConfigManager.update(Long.parseLong(this.f4968e), systemConfig);
            }
            if (!C0877o.m3535t()) {
                m7082b(this.f4967d, this.f4968e);
            } else if (i == 4) {
                C0936a.m3752a().m3757a((C1347a) this).m3753a((int) R.string.incorrect_image_title).m3762b((int) R.string.incorrect_image_body).m3766c((int) R.string.ok).m3755a(new C09521(this)).m3770e();
            } else if (i != 0 || (C0877o.m3514h(str) != 0 && str.contains("gui") == 0 && (this.f4967d != ExecutionType.CLI || str.contains("cli") == null))) {
                C0936a.m3752a().m3757a((C1347a) this).m3753a((int) R.string.rebase_image_title).m3762b((int) R.string.rebase_image_body).m3766c((int) R.string.ok).m3755a(new C09532(this)).m3770e();
            } else {
                m7082b(this.f4967d, this.f4968e);
            }
        }
    }

    /* renamed from: c */
    public void mo1280c(String str) {
        String str2 = f4965b;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("handleRebaseImage: ");
        stringBuilder.append(this.f4966c);
        Log.m3853d(str2, stringBuilder.toString());
        if (!this.f4966c) {
            this.f4966c = true;
            this.f4969f.m3547a((int) R.string.app_name, (int) R.string.wait, (Context) this, null);
            C0877o.m3479a(this.f4967d == ExecutionType.CLI ? "lxd_loader/cli_payload" : "lxd_loader/gui_payload", "/data/lxd/lxd_loader");
            super.mo1280c(str);
        }
    }

    /* renamed from: e */
    public void mo711e(String str, boolean z) {
        str = f4965b;
        z = new StringBuilder();
        z.append("onImageRebased: ");
        z.append(this.f4966c);
        Log.m3853d(str, z.toString());
        if (this.f4966c != null) {
            this.f4969f.m3546a();
            this.f4966c = null;
            m7082b(this.f4967d, this.f4968e);
        }
    }

    /* renamed from: b */
    private void m7082b(ExecutionType executionType, String str) {
        if (m7086p(str)) {
            m7083c(executionType, str);
        } else {
            m7084d(executionType, str);
        }
    }

    /* renamed from: p */
    private boolean m7086p(String str) {
        boolean z = LxdApplication.m3344a().getSharedPreferences("prefs", 0).getBoolean(String.valueOf("imageUpdatePopup"), false);
        str = SystemConfigManager.load(Long.parseLong(str)).get(SystemConfigType.IMAGE_VERSION);
        String str2 = f4965b;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("ImageVersion to run : ");
        stringBuilder.append(str);
        stringBuilder.append(" , ");
        stringBuilder.append(z);
        Log.m3857i(str2, stringBuilder.toString());
        if (z) {
            return false;
        }
        if (str == null || Utils.getImageVersionNumber(str) < 16) {
            return true;
        }
        return false;
    }

    /* renamed from: c */
    private void m7083c(final ExecutionType executionType, final String str) {
        boolean commit = LxdApplication.m3344a().getSharedPreferences("prefs", 0).edit().putBoolean(String.valueOf("imageUpdatePopup"), true).commit();
        C0936a.m3752a().m3757a((C1347a) this).m3753a((int) R.string.linux_image_update).m3762b((int) R.string.linux_image_update_desc).m3766c((int) R.string.ok).m3764b(true).m3759a(false).m3755a(new OnClickListener(this) {
            /* renamed from: c */
            final /* synthetic */ C1459h f3001c;

            public void onClick(DialogInterface dialogInterface, int i) {
                this.f3001c.m7084d(executionType, str);
            }
        }).m3770e();
        String str2 = f4965b;
        executionType = new StringBuilder();
        executionType.append("show linux image update popup : ");
        executionType.append(commit);
        Log.m3857i(str2, executionType.toString());
    }

    /* renamed from: d */
    private void m7084d(final ExecutionType executionType, final String str) {
        switch (executionType) {
            case GUI:
            case QUICK_GUI:
                if (!(C0877o.m3499d((Context) this) || C0877o.m3503e())) {
                    C0874l.m3432a(this.a, String.valueOf(907));
                    C0936a.m3752a().m3757a((C1347a) this).m3753a((int) R.string.run_lod_container).m3762b((int) R.string.run_gui_msg_not_in_dex_mode).m3766c((int) R.string.ok).m3770e();
                    return;
                }
            case CLI:
                break;
            default:
                String str2 = f4965b;
                str = new StringBuilder();
                str.append("Not supported type ");
                str.append(executionType);
                str.append(" for runImage!");
                Log.m3855e(str2, str.toString());
                break;
        }
        if (C0877o.m3474a((Context) this)) {
            C0877o.m3467a(this, getString(R.string.popup_title_continue), getString(R.string.connect_on_low_memory), new OnClickListener(this) {
                /* renamed from: c */
                final /* synthetic */ C1459h f3004c;

                public void onClick(DialogInterface dialogInterface, int i) {
                    this.f3004c.m7085e((ExecutionType) executionType, str);
                }
            }, true, null);
        } else {
            m7085e(executionType, str);
        }
    }

    /* renamed from: e */
    private void m7085e(ExecutionType executionType, String str) {
        Intent intent = new Intent(this, DisplayActivity.class);
        intent.putExtra("configId", str);
        intent.putExtra("executionType", executionType);
        startActivity(intent);
    }
}
