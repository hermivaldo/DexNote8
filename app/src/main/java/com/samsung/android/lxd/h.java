package com.samsung.android.lxd;

import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.os.Bundle;
import com.samsung.android.lxd.a.l;
import com.samsung.android.lxd.a.o;
import com.samsung.android.lxd.a.p;
import com.samsung.android.lxd.fragment.a;
import com.samsung.android.lxd.processor.ExecutionType;
import com.samsung.android.lxd.processor.config.SystemConfig;
import com.samsung.android.lxd.processor.config.SystemConfigManager;
import com.samsung.android.lxd.processor.config.SystemConfigType;
import com.samsung.android.lxd.processor.utils.Utils;
import com.samsung.android.lxd.processor.utils.log.Log;

/* compiled from: RunActivity */
public class h extends a {
    private static final String b = "h";
    private boolean c = false;
    private ExecutionType d = ExecutionType.INVALID;
    private String e = null;
    private p f = null;

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.f = new p();
    }

    public void a(ExecutionType executionType, String str) {
        if (p(str) || o.t()) {
            String str2 = b;
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("handleGetImageVersion: ");
            stringBuilder.append(this.c);
            Log.d(str2, stringBuilder.toString());
            if (!this.c) {
                this.c = true;
                this.f.a((int) R.string.app_name, (int) R.string.wait, (Context) this, null);
                this.d = executionType;
                this.e = str;
                super.a(executionType, str);
                return;
            }
            return;
        }
        b(executionType, str);
    }

    public void b(String str, int i) {
        String str2 = b;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("onImageVersion result: ");
        stringBuilder.append(this.c);
        stringBuilder.append(", imageVersion: ");
        stringBuilder.append(str);
        Log.d(str2, stringBuilder.toString());
        if (this.c) {
            this.f.a();
            this.c = false;
            if (i == 0) {
                SystemConfig systemConfig = new SystemConfig();
                systemConfig.set(SystemConfigType.IMAGE_VERSION, str);
                SystemConfigManager.update(Long.parseLong(this.e), systemConfig);
            }
            if (!o.t()) {
                b(this.d, this.e);
            } else if (i == 4) {
                a.a().a((a) this).a((int) R.string.incorrect_image_title).b((int) R.string.incorrect_image_body).c((int) R.string.ok).a(new OnClickListener() {
                    public void onClick(DialogInterface dialogInterface, int i) {
                        h.this.finish();
                    }
                }).e();
            } else if (i == 0 && (!o.h(str) || str.contains("gui") || (this.d == ExecutionType.CLI && str.contains("cli")))) {
                b(this.d, this.e);
            } else {
                a.a().a((a) this).a((int) R.string.rebase_image_title).b((int) R.string.rebase_image_body).c((int) R.string.ok).a(new OnClickListener() {
                    public void onClick(DialogInterface dialogInterface, int i) {
                        h.this.c(SystemConfigManager.load(Long.parseLong(h.this.e)).get(SystemConfigType.IMAGE_PATH));
                    }
                }).e();
            }
        }
    }

    public void c(String str) {
        String str2 = b;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("handleRebaseImage: ");
        stringBuilder.append(this.c);
        Log.d(str2, stringBuilder.toString());
        if (!this.c) {
            this.c = true;
            this.f.a((int) R.string.app_name, (int) R.string.wait, (Context) this, null);
            o.a(this.d == ExecutionType.CLI ? "lxd_loader/cli_payload" : "lxd_loader/gui_payload", "/data/lxd/lxd_loader");
            super.c(str);
        }
    }

    public void e(String str, boolean z) {
        str = b;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("onImageRebased: ");
        stringBuilder.append(this.c);
        Log.d(str, stringBuilder.toString());
        if (this.c) {
            this.f.a();
            this.c = false;
            b(this.d, this.e);
        }
    }

    private void b(ExecutionType executionType, String str) {
        if (p(str)) {
            c(executionType, str);
        } else {
            d(executionType, str);
        }
    }

    private boolean p(String str) {
        boolean z = LxdApplication.a().getSharedPreferences("prefs", 0).getBoolean(String.valueOf("imageUpdatePopup"), false);
        str = SystemConfigManager.load(Long.parseLong(str)).get(SystemConfigType.IMAGE_VERSION);
        String str2 = b;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("ImageVersion to run : ");
        stringBuilder.append(str);
        stringBuilder.append(" , ");
        stringBuilder.append(z);
        Log.i(str2, stringBuilder.toString());
        if (z) {
            return false;
        }
        if (str == null || Utils.getImageVersionNumber(str) < 16) {
            return true;
        }
        return false;
    }

    private void c(final ExecutionType executionType, final String str) {
        boolean commit = LxdApplication.a().getSharedPreferences("prefs", 0).edit().putBoolean(String.valueOf("imageUpdatePopup"), true).commit();
        a.a().a((a) this).a((int) R.string.linux_image_update).b((int) R.string.linux_image_update_desc).c((int) R.string.ok).b(true).a(false).a(new OnClickListener() {
            public void onClick(DialogInterface dialogInterface, int i) {
                h.this.d(executionType, str);
            }
        }).e();
        String str2 = b;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("show linux image update popup : ");
        stringBuilder.append(commit);
        Log.i(str2, stringBuilder.toString());
    }

    private void d(final ExecutionType executionType, final String str) {
        switch (executionType) {
            case GUI:
            case QUICK_GUI:
                if (!(o.d((Context) this) || o.e())) {
                    l.a(this.a, String.valueOf(907));
                    a.a().a((a) this).a((int) R.string.run_lod_container).b((int) R.string.run_gui_msg_not_in_dex_mode).c((int) R.string.ok).e();
                    return;
                }
            case CLI:
                break;
            default:
                String str2 = b;
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append("Not supported type ");
                stringBuilder.append(executionType);
                stringBuilder.append(" for runImage!");
                Log.e(str2, stringBuilder.toString());
                break;
        }
        if (o.a((Context) this)) {
            o.a(this, getString(R.string.popup_title_continue), getString(R.string.connect_on_low_memory), new OnClickListener() {
                public void onClick(DialogInterface dialogInterface, int i) {
                    h.this.e(executionType, str);
                }
            }, true, null);
            return;
        }
        e(executionType, str);
    }

    private void e(ExecutionType executionType, String str) {
        Intent intent = new Intent(this, DisplayActivity.class);
        intent.putExtra("configId", str);
        intent.putExtra("executionType", executionType);
        startActivity(intent);
    }
}
