package com.samsung.android.lxd;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.DialogInterface;
import android.content.DialogInterface.OnCancelListener;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.SemSystemProperties;
import com.samsung.android.lxd.a.i;
import com.samsung.android.lxd.a.l;
import com.samsung.android.lxd.a.o;
import com.samsung.android.lxd.fragment.a;
import com.samsung.android.lxd.fragment.h;
import com.samsung.android.lxd.processor.config.SystemConfigHelper;
import com.samsung.android.lxd.processor.utils.log.Log;
import java.io.File;
import java.util.ArrayList;

public class EntryActivity extends a {
    private static final String b = "EntryActivity";
    private boolean c = false;
    private boolean d = false;
    private boolean e = false;
    private String f = "linux_on_dex.LoD_image";
    private String g = "linux_on_dex.LoD_image_P_initial";
    private String h = SystemConfigHelper.CONFIG_OPTION_ON;

    public boolean g() {
        return false;
    }

    public boolean h() {
        return false;
    }

    protected void onCreate(Bundle bundle) {
        String str;
        super.onCreate(bundle);
        if (bundle != null) {
            this.e = bundle.getBoolean("permissionRequested");
            str = b;
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("Restore permissionRequested: ");
            stringBuilder.append(this.e);
            Log.d(str, stringBuilder.toString());
        }
        setContentView(R.layout.activity_entry);
        if (!o.m()) {
            l.a("017", String.valueOf(1701));
            Log.e(b, "non SupportedBinary: ");
            a.a().a((a) this).a((int) R.string.update_your_software_title).b((int) R.string.update_your_software_body).c((int) R.string.ok).a(new OnClickListener() {
                public void onClick(DialogInterface dialogInterface, int i) {
                    Log.e(EntryActivity.b, "onClick SupportedBinary: ");
                    o.p();
                }
            }).a(false).e();
        } else if (o.n()) {
            Log.i(b, "needToUpdate App");
            a.a().a((a) this).a((int) R.string.update_application_title).b((int) R.string.update_application_body).c((int) R.string.ok).a(new OnClickListener() {
                public void onClick(DialogInterface dialogInterface, int i) {
                    Log.e(EntryActivity.b, "onClick SupportedBinary: ");
                    StringBuilder stringBuilder;
                    Intent intent;
                    try {
                        stringBuilder = new StringBuilder();
                        stringBuilder.append("market://details?id=");
                        stringBuilder.append(EntryActivity.this.getPackageName());
                        intent = new Intent("android.intent.action.VIEW", Uri.parse(stringBuilder.toString()));
                        intent.setFlags(268468224);
                        EntryActivity.this.startActivity(intent);
                    } catch (ActivityNotFoundException unused) {
                        stringBuilder = new StringBuilder();
                        stringBuilder.append("http://play.google.com/store/apps/details?id=");
                        stringBuilder.append(EntryActivity.this.getPackageName());
                        intent = new Intent("android.intent.action.VIEW", Uri.parse(stringBuilder.toString()));
                        intent.setFlags(268468224);
                        EntryActivity.this.startActivity(intent);
                    }
                    o.p();
                }
            }).a(false).e();
        } else if (o.B()) {
            l.a("014", String.valueOf(1401));
            Log.i(b, "Reset data cause samsung account removed");
            a.a().a((a) this).a((int) R.string.reset_title).b((int) R.string.reset_body).c((int) R.string.ok).a(new OnClickListener() {
                public void onClick(DialogInterface dialogInterface, int i) {
                    String O = EntryActivity.b;
                    StringBuilder stringBuilder = new StringBuilder();
                    stringBuilder.append("reset request: ");
                    stringBuilder.append(o.C());
                    Log.d(O, stringBuilder.toString());
                    EntryActivity.this.S();
                }
            }).a(false).e();
        } else {
            S();
        }
    }

    protected void onSaveInstanceState(Bundle bundle) {
        String str = b;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("onSaveInstanceState: ");
        stringBuilder.append(this.e);
        Log.d(str, stringBuilder.toString());
        bundle.putBoolean("permissionRequested", this.e);
        super.onSaveInstanceState(bundle);
    }

    protected void onResume() {
        super.onResume();
        if (this.c) {
            this.c = false;
            S();
        } else if (this.d) {
            this.d = false;
            P();
        }
    }

    private void P() {
        if (R()) {
            Log.d(b, "secure folder is proper");
            U();
            return;
        }
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(o.a());
        stringBuilder.append("LoD");
        File e = o.e(new File(stringBuilder.toString()));
        if (Q()) {
            Log.d(b, "apply P initial policy");
            if (b()) {
                Log.i(b, "need to confirm to use secure folder");
                a.a().a((a) this).a((int) R.string.existing_lod_name_title).a(o.b(getString(R.string.existing_lod_name_message, new Object[]{e.getName(), getString(R.string.existing_internal_storage_lod_path, new Object[]{e.getName()})}), getString(R.string.existing_internal_storage_lod_path, new Object[]{e.getName()}))).c((int) R.string.existing_lod_name_continue_button).a(new OnClickListener() {
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Log.i(EntryActivity.b, "making secure folder");
                        SemSystemProperties.set(EntryActivity.this.g, EntryActivity.this.h);
                        EntryActivity.this.U();
                    }
                }).d(R.string.existing_lod_name_close_app_button).b(new OnClickListener() {
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Log.i(EntryActivity.b, "making secure folder is canceled");
                        o.p();
                    }
                }).a(new OnCancelListener() {
                    public void onCancel(DialogInterface dialogInterface) {
                        Log.i(EntryActivity.b, "making secure folder is canceled");
                        o.p();
                    }
                }).e();
            } else {
                this.d = true;
            }
            return;
        }
        final String parent = e.getParent();
        Log.i(b, "need to rename secure folder");
        CharSequence b = o.b(getString(R.string.rename_lod_name_message, new Object[]{e.getName(), getString(R.string.internal_storage_lod)}), getString(R.string.internal_storage_lod));
        if (b()) {
            a.a().a((a) this).a((int) R.string.rename_lod_name_title).a(b).c((int) R.string.ok).a(new OnClickListener() {
                public void onClick(DialogInterface dialogInterface, int i) {
                    String O = EntryActivity.b;
                    StringBuilder stringBuilder = new StringBuilder();
                    stringBuilder.append("startMyFilesForRename: ");
                    stringBuilder.append(parent);
                    Log.i(O, stringBuilder.toString());
                    Intent intent = new Intent("samsung.myfiles.intent.action.LAUNCH_MY_FILES");
                    intent.putExtra("samsung.myfiles.intent.extra.START_PATH", parent);
                    try {
                        EntryActivity.this.startActivityForResult(intent, 9);
                    } catch (ActivityNotFoundException e) {
                        O = EntryActivity.b;
                        stringBuilder = new StringBuilder();
                        stringBuilder.append("Launch myFiles failed!: ");
                        stringBuilder.append(e.toString());
                        Log.e(O, stringBuilder.toString());
                    }
                }
            }).a(new OnCancelListener() {
                public void onCancel(DialogInterface dialogInterface) {
                    Log.i(EntryActivity.b, "rename request canceled");
                    o.p();
                }
            }).e();
        } else {
            this.d = true;
        }
    }

    private boolean Q() {
        return SemSystemProperties.getInt("ro.product.first_api_level", -1) >= 28;
    }

    private boolean R() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(o.a());
        stringBuilder.append("LoD");
        File file = new File(stringBuilder.toString());
        if (file.exists()) {
            File e = o.e(file);
            String str = b;
            StringBuilder stringBuilder2 = new StringBuilder();
            stringBuilder2.append("realFile name: ");
            stringBuilder2.append(e.getName());
            stringBuilder2.append(", dir: ");
            stringBuilder2.append(e.isDirectory());
            Log.i(str, stringBuilder2.toString());
            String f;
            if (Q()) {
                if (e.isDirectory() && e.getName().equalsIgnoreCase(file.getName())) {
                    f = o.f(e);
                    if (f != null && f.contains("lxd_share_data_file")) {
                        return true;
                    }
                }
            } else if (e.isDirectory() && e.getName().equals(file.getName())) {
                f = o.f(file);
                if (f != null && f.contains("lxd_share_data_file")) {
                    return true;
                }
            }
            return false;
        }
        if (Q()) {
            boolean mkdir = file.mkdir();
            String str2 = b;
            StringBuilder stringBuilder3 = new StringBuilder();
            stringBuilder3.append("make LoD Secure Folder: ");
            stringBuilder3.append(mkdir);
            Log.i(str2, stringBuilder3.toString());
            SemSystemProperties.set(this.g, this.h);
        } else {
            SemSystemProperties.set(this.f, this.h);
        }
        return true;
    }

    private void S() {
        if (o.G()) {
            P();
            return;
        }
        if (!o.j() || android.support.v4.app.a.a(this, "android.permission.WRITE_EXTERNAL_STORAGE")) {
            N();
        } else {
            T();
        }
    }

    private void T() {
        Log.i(b, "showRationaleDialog: ");
        ArrayList arrayList = new ArrayList();
        arrayList.add("android.permission.WRITE_EXTERNAL_STORAGE");
        new h().a(arrayList).a((a) this).a(new OnCancelListener() {
            public void onCancel(DialogInterface dialogInterface) {
                Log.i(EntryActivity.b, "canceled");
                o.p();
            }
        }).d(R.string.popup_cancel).b(new OnClickListener() {
            public void onClick(DialogInterface dialogInterface, int i) {
                Log.i(EntryActivity.b, "cancel cancel");
                o.p();
            }
        }).c((int) R.string.settings_capital).a(new OnClickListener() {
            public void onClick(DialogInterface dialogInterface, int i) {
                Log.i(EntryActivity.b, "click app permission settings");
                Intent intent = new Intent("android.settings.APPLICATION_DETAILS_SETTINGS");
                intent.addCategory("android.intent.category.DEFAULT");
                intent.setData(Uri.fromParts("package", EntryActivity.this.getPackageName(), null));
                EntryActivity.this.startActivityForResult(intent, 8);
            }
        }).e();
    }

    public void N() {
        if (!this.e) {
            this.e = true;
            requestPermissions(new String[]{"android.permission.WRITE_EXTERNAL_STORAGE"}, 2);
        }
    }

    private void U() {
        if (o.g()) {
            a((Activity) this);
            finish();
            return;
        }
        startActivity(new Intent(this, OobeActivity.class));
        finish();
    }

    protected void onActivityResult(int i, int i2, Intent intent) {
        String str = b;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("onActivityResult: ");
        stringBuilder.append(i);
        stringBuilder.append(", ");
        stringBuilder.append(i2);
        Log.d(str, stringBuilder.toString());
        if (i != 3) {
            switch (i) {
                case 8:
                    this.c = true;
                    return;
                case 9:
                    this.d = true;
                    return;
                default:
                    super.onActivityResult(i, i2, intent);
                    return;
            }
        }
        if (i2 == -1) {
            a((Activity) this);
        } else {
            String str2 = b;
            StringBuilder stringBuilder2 = new StringBuilder();
            stringBuilder2.append("SAMSUNG ACCOUNT SIGN fail: ");
            stringBuilder2.append(i2);
            stringBuilder2.append(", reason: ");
            stringBuilder2.append(intent != null ? intent.getStringExtra("error_message") : "");
            Log.i(str2, stringBuilder2.toString());
        }
        finish();
    }

    public void onRequestPermissionsResult(int i, String[] strArr, int[] iArr) {
        String str = b;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("onRequestPermissionsResult: : ");
        stringBuilder.append(i);
        stringBuilder.append(", ");
        stringBuilder.append(iArr.length);
        Log.i(str, stringBuilder.toString());
        if (i != 2) {
            String str2 = b;
            StringBuilder stringBuilder2 = new StringBuilder();
            stringBuilder2.append("onRequestPermissionsResult Error!");
            stringBuilder2.append(i);
            Log.e(str2, stringBuilder2.toString());
            return;
        }
        this.e = false;
        o.k();
        if (iArr.length <= 0 || iArr[0] != 0) {
            finish();
        } else {
            P();
        }
    }
}
