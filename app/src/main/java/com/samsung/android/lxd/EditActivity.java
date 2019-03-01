package com.samsung.android.lxd;

import android.app.ActionBar;
import android.app.Activity;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.res.Configuration;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import com.samsung.android.lxd.a.j;
import com.samsung.android.lxd.a.o;
import com.samsung.android.lxd.fragment.CardViewFragment;
import com.samsung.android.lxd.fragment.CardViewFragment.b;
import com.samsung.android.lxd.fragment.CommitButtonFragment;
import com.samsung.android.lxd.fragment.EditTextFragment;
import com.samsung.android.lxd.fragment.ImageSizeSeekBarFragment;
import com.samsung.android.lxd.fragment.SwitchFragment;
import com.samsung.android.lxd.fragment.a;
import com.samsung.android.lxd.processor.LxdException;
import com.samsung.android.lxd.processor.config.SystemConfig;
import com.samsung.android.lxd.processor.config.SystemConfigHelper;
import com.samsung.android.lxd.processor.config.SystemConfigManager;
import com.samsung.android.lxd.processor.config.SystemConfigType;
import com.samsung.android.lxd.processor.network.channel.pty.internal.KeycodeConstants;
import com.samsung.android.lxd.processor.utils.log.Log;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;

public class EditActivity extends b {
    private static final String c = "EditActivity";
    private a d;
    private CommitButtonFragment e = null;
    private CommitButtonFragment f = null;
    private EditTextFragment g;
    private EditTextFragment h;
    private ImageSizeSeekBarFragment i;
    private String j;
    private SwitchFragment k;
    private SystemConfig l;
    private TextView m;

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_edit);
        ActionBar actionBar = getActionBar();
        if (actionBar != null) {
            actionBar.setTitle(R.string.edit_toolbar_title);
        }
        this.j = getIntent().getStringExtra("configId");
        this.l = SystemConfigManager.load(Long.parseLong(this.j));
        this.i = (ImageSizeSeekBarFragment) getFragmentManager().findFragmentById(R.id.imageSizeSeekbar);
        this.g = (EditTextFragment) getFragmentManager().findFragmentById(R.id.nameEditText);
        this.h = (EditTextFragment) getFragmentManager().findFragmentById(R.id.descEditText);
        this.e = (CommitButtonFragment) getFragmentManager().findFragmentById(R.id.negativeButton);
        this.f = (CommitButtonFragment) getFragmentManager().findFragmentById(R.id.positiveButton);
        this.k = (SwitchFragment) getFragmentManager().findFragmentById(R.id.shareFolderSwitch);
        this.m = (TextView) findViewById(R.id.imageDetail);
        R();
        Q();
        a((RelativeLayout) findViewById(R.id.activityLayout), (ScrollView) findViewById(R.id.scrollView));
    }

    protected void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
        bundle.putString("configId", this.j);
    }

    protected void onRestoreInstanceState(Bundle bundle) {
        super.onRestoreInstanceState(bundle);
        if (bundle != null) {
            this.j = (String) bundle.get("configId");
        }
    }

    public boolean f(String str, boolean z) {
        super.f(str, z);
        a.a().a((a) this).a((int) R.string.unexpected_error).b((int) R.string.oops_edit_message).a(false).c((int) R.string.ok).a(new OnClickListener() {
            public void onClick(DialogInterface dialogInterface, int i) {
                EditActivity.this.finish();
            }
        }).e();
        return true;
    }

    private void Q() {
        ArrayList arrayList = new ArrayList();
        arrayList.add(o.a(getApplicationContext(), this.l.get(SystemConfigType.IMAGE_PATH)));
        ((CardViewFragment) getFragmentManager().findFragmentById(R.id.cardView)).a(b.TEXT_ONLY).a(arrayList).a(false).b(true).b();
    }

    private void R() {
        this.m.setText(R.string.see_image_details);
        this.m.setOnClickListener(new j() {
            public void onClick(View view) {
                super.onClick(view);
                EditActivity.this.a(new File(EditActivity.this.l.get(SystemConfigType.IMAGE_PATH)));
            }
        });
        this.f.a(true).a(getString(R.string.save)).a(17).a(new j() {
            public void onClick(View view) {
                super.onClick(view);
                EditActivity.this.b(true);
            }
        }).c();
        this.g.a(getString(R.string.name)).b(this.l.get(SystemConfigType.IMAGE_NAME)).c(getString(R.string.name_empty)).d(o.c((int) KeycodeConstants.KEYCODE_F10)).f(getString(R.string.name_hint)).b((int) KeycodeConstants.KEYCODE_F10).a(this.f).f();
        this.h.a(getString(R.string.description)).b(this.l.get(SystemConfigType.IMAGE_DESC)).d(o.c(250)).f(getString(R.string.description_hint)).b(250).f();
        this.i.a(this.l.get(SystemConfigType.IMAGE_PATH)).c();
        this.k.a(getString(R.string.share_folder_information)).a(true).a(8).e();
        this.e.a(true).a(getString(R.string.popup_cancel)).a(17).a(new j() {
            public void onClick(View view) {
                super.onClick(view);
                EditActivity.this.T();
            }
        }).c();
    }

    public void c(String str, boolean z) {
        String str2 = c;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("onImageMinSizeReceived: ");
        stringBuilder.append(z);
        stringBuilder.append(", ");
        stringBuilder.append(str);
        Log.d(str2, stringBuilder.toString());
        this.i.a(str, z);
    }

    public void d(String str, boolean z) {
        String str2 = c;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("onImageSizeUpdated: ");
        stringBuilder.append(z);
        stringBuilder.append(", ");
        stringBuilder.append(str);
        Log.d(str2, stringBuilder.toString());
        if (z) {
            b(false);
        } else {
            a(z);
        }
    }

    private void b(boolean z) {
        String str = this.l.get(SystemConfigType.IMAGE_PATH);
        File file = new File(str);
        final int g = o.g(str);
        int intValue = Integer.valueOf(this.i.a()).intValue();
        String str2 = c;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("requestResizeImage: ");
        stringBuilder.append(g);
        stringBuilder.append(" => ");
        stringBuilder.append(intValue);
        stringBuilder.append(", initialRequest: ");
        stringBuilder.append(z);
        Log.d(str2, stringBuilder.toString());
        if (!a(z, file.getAbsolutePath(), g, intValue)) {
            new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {
                public void run() {
                    if (EditActivity.this.a(true)) {
                        EditActivity.this.a(g);
                    }
                }
            }, 50);
        }
    }

    private void a(int i) {
        Log.d(c, "saveSettings");
        try {
            b(i);
            HashMap hashMap = new HashMap();
            hashMap.put("configId", this.j);
            a((Activity) this, hashMap);
        } catch (LxdException e) {
            String str = c;
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("saveSettings: ");
            stringBuilder.append(e.toString());
            Log.e(str, stringBuilder.toString());
            if (o.o()) {
                S();
                return;
            }
            throw e;
        }
    }

    protected boolean a(boolean z) {
        super.N();
        int a = o.a(this.l.get(SystemConfigType.IMAGE_PATH), Integer.valueOf(this.i.a()).intValue(), z);
        String str = c;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("checkImageInfo: ");
        stringBuilder.append(a);
        Log.d(str, stringBuilder.toString());
        switch (a) {
            case 0:
                return true;
            case 1:
                this.d = a.a().a((a) this).a((int) R.string.oops_title).b((int) R.string.oops_edit_message).c((int) R.string.ok).a(new OnClickListener() {
                    public void onClick(DialogInterface dialogInterface, int i) {
                        EditActivity.this.d.dismiss();
                        EditActivity.this.a(EditActivity.this);
                    }
                }).e();
                break;
            case 2:
                super.d();
                return false;
            case 3:
                S();
                break;
        }
        return false;
    }

    private void S() {
        this.d = a.a().a((a) this).a((int) R.string.insufficient_storage_title).b((int) R.string.insufficient_storage_edit_message).c((int) R.string.ok).a(new OnClickListener() {
            public void onClick(DialogInterface dialogInterface, int i) {
                EditActivity.this.d.dismiss();
                o.b(EditActivity.this.getCurrentFocus(), 0);
            }
        }).e();
    }

    private void b(int i) {
        String str = c;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("set IMAGE_NAME   : ");
        stringBuilder.append(this.g.b());
        Log.d(str, stringBuilder.toString());
        str = c;
        stringBuilder = new StringBuilder();
        stringBuilder.append("set IMAGE_DESC   : ");
        stringBuilder.append(this.h.b());
        Log.d(str, stringBuilder.toString());
        str = c;
        stringBuilder = new StringBuilder();
        stringBuilder.append("set IMAGE_SIZE   : ");
        stringBuilder.append(this.i.a());
        stringBuilder.append(", cur: ");
        stringBuilder.append(i);
        Log.d(str, stringBuilder.toString());
        str = c;
        stringBuilder = new StringBuilder();
        stringBuilder.append("set SHARE_FOLDER : ");
        stringBuilder.append(this.k.d());
        Log.d(str, stringBuilder.toString());
        SystemConfig systemConfig = new SystemConfig();
        systemConfig.set(SystemConfigType.IMAGE_NAME, this.g.b());
        systemConfig.set(SystemConfigType.IMAGE_DESC, this.h.b());
        systemConfig.set(SystemConfigType.IMAGE_SIZE, Integer.toString(i));
        systemConfig.set(SystemConfigType.SHARE_FOLDER, this.k.d() ? SystemConfigHelper.CONFIG_OPTION_ON : SystemConfigHelper.CONFIG_OPTION_OFF);
        SystemConfigManager.update(Long.parseLong(this.j), systemConfig);
    }

    private void T() {
        if (U()) {
            final a a = a.a();
            if (!this.f.b()) {
                a.c(true);
            }
            a.a((a) this).a((int) R.string.save_changes_pupup_title).b((int) R.string.save_changes_pupup_message).d(R.string.popup_cancel).c((int) R.string.popup_save).a(new OnClickListener() {
                public void onClick(DialogInterface dialogInterface, int i) {
                    a.dismiss();
                    EditActivity.this.b(true);
                }
            }).b(new OnClickListener() {
                public void onClick(DialogInterface dialogInterface, int i) {
                    super.d();
                }
            }).e();
            return;
        }
        super.d();
    }

    public void onBackPressed() {
        T();
    }

    public void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        Q();
        if (this.f != null && this.e != null) {
            this.f.c();
            this.e.c();
        }
    }

    private boolean U() {
        return (this.l.get(SystemConfigType.IMAGE_NAME).equals(this.g.b()) && this.l.get(SystemConfigType.IMAGE_DESC).equals(this.h.b()) && this.l.get(SystemConfigType.IMAGE_SIZE).equals(this.i.a()) && SystemConfigHelper.isConfigOptionOn(this.l, SystemConfigType.SHARE_FOLDER) == this.k.d()) ? false : true;
    }
}
