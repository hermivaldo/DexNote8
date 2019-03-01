package com.samsung.android.lxd;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import com.samsung.android.lxd.a.d;
import com.samsung.android.lxd.a.j;
import com.samsung.android.lxd.a.l;
import com.samsung.android.lxd.a.o;
import com.samsung.android.lxd.a.p;
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

public class CreateActivity extends b {
    private static final String c = "CreateActivity";
    private a d;
    private CommitButtonFragment e = null;
    private CommitButtonFragment f = null;
    private EditTextFragment g;
    private EditTextFragment h;
    private ImageSizeSeekBarFragment i;
    private SwitchFragment j;
    private boolean k;
    private boolean l;
    private TextView m;
    private TextView n;
    private p o;
    private boolean p;

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_create);
        S();
        ActionBar actionBar = getActionBar();
        if (actionBar != null) {
            actionBar.setTitle(R.string.select_image_toolbar_title);
        }
        boolean z = true;
        if (getIntent().getStringExtra("imagePath") != null) {
            this.k = true;
            this.a = "008";
        } else {
            this.k = false;
            this.a = "005";
        }
        this.g = (EditTextFragment) getFragmentManager().findFragmentById(R.id.nameEditText);
        this.h = (EditTextFragment) getFragmentManager().findFragmentById(R.id.descEditText);
        this.i = (ImageSizeSeekBarFragment) getFragmentManager().findFragmentById(R.id.imageSizeSeekbar);
        this.e = (CommitButtonFragment) getFragmentManager().findFragmentById(R.id.negativeButton);
        this.f = (CommitButtonFragment) getFragmentManager().findFragmentById(R.id.positiveButton);
        this.j = (SwitchFragment) getFragmentManager().findFragmentById(R.id.shareFolderSwitch);
        this.m = (TextView) findViewById(R.id.browseOrImageDetail);
        this.n = (TextView) findViewById(R.id.downloadFromServer);
        this.l = getIntent().getBooleanExtra("fromMyFilesUnzip", false);
        this.o = new p();
        if (bundle == null || !bundle.getBoolean("VERIFIED_IMAGE")) {
            z = false;
        }
        this.p = z;
        Q();
        a((RelativeLayout) findViewById(R.id.activityLayout), (ScrollView) findViewById(R.id.scrollView));
    }

    protected void onSaveInstanceState(Bundle bundle) {
        bundle.putBoolean("VERIFIED_IMAGE", this.p);
        super.onSaveInstanceState(bundle);
    }

    protected void onStop() {
        super.onStop();
    }

    private void Q() {
        R();
        boolean z = false;
        if (this.k) {
            if (this.l && !this.p) {
                a(getIntent().getStringExtra("imagePath"), true);
            }
            this.m.setText(R.string.see_image_details);
            this.m.setOnClickListener(new j() {
                public void onClick(View view) {
                    super.onClick(view);
                    l.a(CreateActivity.this.a, String.valueOf(804));
                    CreateActivity.this.a(new File(CreateActivity.this.getIntent().getStringExtra("imagePath")));
                }
            });
        } else {
            this.m.setText(R.string.browse);
            this.m.setOnClickListener(new j() {
                public void onClick(View view) {
                    super.onClick(view);
                    l.a(CreateActivity.this.a, String.valueOf(502));
                    CreateActivity.this.g(null);
                }
            });
            this.n.setVisibility(0);
            if (d.a()) {
                this.n.setOnClickListener(new j() {
                    public void onClick(View view) {
                        super.onClick(view);
                        l.a(CreateActivity.this.a, String.valueOf(503));
                        CreateActivity.this.q();
                    }
                });
            } else {
                this.n.setEnabled(false);
                this.n.setTextColor(getResources().getColor(R.color.LightTheme.primary_dim, null));
            }
            ((TextView) findViewById(R.id.imageInfo)).setTextColor(getResources().getColor(R.color.LightTheme.no_item_text, null));
            ((TextView) findViewById(R.id.descText)).setTextColor(getResources().getColor(R.color.LightTheme.no_item_text, null));
        }
        this.f.a(false).a(getString(R.string.create_button)).a(17).a(new j() {
            public void onClick(View view) {
                super.onClick(view);
                CreateActivity.this.a(true);
                l.a(CreateActivity.this.a, String.valueOf(801));
            }
        }).c();
        EditTextFragment a = this.g.a(getString(R.string.name)).c(getString(R.string.name_empty)).d(o.c((int) KeycodeConstants.KEYCODE_F10)).f(getString(R.string.name_hint)).b((int) KeycodeConstants.KEYCODE_F10).a(this.f).a(this.k);
        if (this.k && !O()) {
            z = true;
        }
        a.b(z).f();
        this.h.a(getString(R.string.description)).d(o.c(250)).f(getString(R.string.description_hint)).b(250).a(this.k).f();
        this.i.a(getIntent().getStringExtra("imagePath")).a(this.k).c();
        this.j.a(getString(R.string.share_folder_information)).a(true).a(8).e();
        this.e.a(true).a(getString(R.string.popup_cancel)).a(17).a(new j() {
            public void onClick(View view) {
                super.onClick(view);
                if (CreateActivity.this.k) {
                    l.a(CreateActivity.this.a, String.valueOf(802));
                } else {
                    l.a(CreateActivity.this.a, String.valueOf(501));
                }
                CreateActivity.this.U();
            }
        }).c();
    }

    private void R() {
        b bVar;
        CardViewFragment.a anonymousClass10;
        ArrayList arrayList = new ArrayList();
        if (this.k) {
            arrayList.add(o.a(getApplicationContext(), getIntent().getStringExtra("imagePath")));
            bVar = b.WITH_UNMOUNT_IMAGE;
            anonymousClass10 = new CardViewFragment.a() {
                public void a(int i) {
                    l.a(CreateActivity.this.a, String.valueOf(803));
                    a.a().a(CreateActivity.this).a((int) R.string.unmount_image_popup_title).b((int) R.string.unmount_image_popup_message).c((int) R.string.unmount_image).a(new OnClickListener() {
                        public void onClick(DialogInterface dialogInterface, int i) {
                            Intent intent = new Intent(CreateActivity.this, CreateActivity.class);
                            intent.addFlags(67108864);
                            CreateActivity.this.startActivity(intent);
                        }
                    }).d(R.string.popup_cancel).e();
                }
            };
        } else {
            arrayList.add("");
            bVar = b.TEXT_ONLY;
            anonymousClass10 = null;
        }
        ((CardViewFragment) getFragmentManager().findFragmentById(R.id.cardView)).a(bVar).a(anonymousClass10).a(arrayList).a(false).b(true).b();
    }

    private void S() {
        if (this.f != null && this.e != null) {
            this.f.c();
            this.e.c();
        }
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
            a(false);
        } else {
            b(z);
        }
    }

    public void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        S();
        R();
    }

    public boolean f(String str, boolean z) {
        super.f(str, z);
        a.a().a((a) this).a((int) R.string.unexpected_error).b((int) R.string.oops_create_message).a(false).c((int) R.string.ok).a(new OnClickListener() {
            public void onClick(DialogInterface dialogInterface, int i) {
                CreateActivity.this.finish();
            }
        }).e();
        return true;
    }

    private void a(boolean z) {
        String stringExtra = getIntent().getStringExtra("imagePath");
        File file = new File(stringExtra);
        final int g = o.g(stringExtra);
        int intValue = Integer.valueOf(this.i.a()).intValue();
        String str = c;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("requestResizeImage: ");
        stringBuilder.append(g);
        stringBuilder.append(" => ");
        stringBuilder.append(intValue);
        stringBuilder.append(", initialRequest: ");
        stringBuilder.append(z);
        Log.d(str, stringBuilder.toString());
        if (!a(z, file.getAbsolutePath(), g, intValue)) {
            new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {
                public void run() {
                    if (CreateActivity.this.b(true)) {
                        CreateActivity.this.a(g);
                    }
                }
            }, 50);
        }
    }

    private void a(int i) {
        Log.d(c, "saveSettings");
        try {
            HashMap hashMap = new HashMap();
            hashMap.put("configId", Long.toString(b(i)));
            a((Activity) this, hashMap);
        } catch (LxdException e) {
            String str = c;
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("saveSettings: ");
            stringBuilder.append(e.toString());
            Log.e(str, stringBuilder.toString());
            if (o.o()) {
                T();
                return;
            }
            throw e;
        }
    }

    public void b(String str) {
        if (this.k && this.l) {
            this.o.a((int) R.string.app_name, (int) R.string.wait, (Context) this, null);
            super.b(str);
        }
    }

    public void b(String str, int i) {
        if (this.k && this.l) {
            this.o.a();
            a(getIntent().getStringExtra("imagePath"), i == 0, null, str, true);
        }
    }

    public void a(String str, String str2, String str3, boolean z) {
        this.p = true;
        super.a(str, str2, str3, z);
    }

    private boolean b(boolean z) {
        super.N();
        int a = o.a(getIntent().getStringExtra("imagePath"), Integer.valueOf(this.i.a()).intValue(), z);
        String str = c;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("checkImageInfo: ");
        stringBuilder.append(a);
        Log.d(str, stringBuilder.toString());
        switch (a) {
            case 0:
                return true;
            case 1:
                this.d = a.a().a((a) this).a((int) R.string.oops_title).b((int) R.string.oops_create_message).c((int) R.string.ok).a(new OnClickListener() {
                    public void onClick(DialogInterface dialogInterface, int i) {
                        CreateActivity.this.d.dismiss();
                        CreateActivity.this.a(CreateActivity.this);
                    }
                }).e();
                break;
            case 2:
                this.d = a.a().a((a) this).a((int) R.string.image_not_found_title).b((int) R.string.image_not_found_create_message).c((int) R.string.browse).a(new OnClickListener() {
                    public void onClick(DialogInterface dialogInterface, int i) {
                        CreateActivity.this.d.dismiss();
                        CreateActivity.this.g(null);
                    }
                }).d(R.string.popup_cancel).b(new OnClickListener() {
                    public void onClick(DialogInterface dialogInterface, int i) {
                        CreateActivity.this.d.dismiss();
                        CreateActivity.this.a(CreateActivity.this);
                    }
                }).e();
                break;
            case 3:
                T();
                break;
        }
        return false;
    }

    private void T() {
        this.d = a.a().a((a) this).a((int) R.string.insufficient_storage_title).b((int) R.string.insufficient_storage_create_message).c((int) R.string.ok).a(new OnClickListener() {
            public void onClick(DialogInterface dialogInterface, int i) {
                CreateActivity.this.d.dismiss();
                o.b(CreateActivity.this.getCurrentFocus(), 0);
            }
        }).e();
    }

    private long b(int i) {
        String str = c;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("set IMAGE_NAME : ");
        stringBuilder.append(this.g.b());
        Log.d(str, stringBuilder.toString());
        str = c;
        stringBuilder = new StringBuilder();
        stringBuilder.append("set IMAGE_DESC : ");
        stringBuilder.append(this.h.b());
        Log.d(str, stringBuilder.toString());
        str = c;
        stringBuilder = new StringBuilder();
        stringBuilder.append("set IMAGE_PATH : ");
        stringBuilder.append(getIntent().getStringExtra("imagePath"));
        Log.d(str, stringBuilder.toString());
        str = c;
        stringBuilder = new StringBuilder();
        stringBuilder.append("set IMAGE_SIZE : ");
        stringBuilder.append(this.i.a());
        stringBuilder.append(", cur: ");
        stringBuilder.append(i);
        Log.d(str, stringBuilder.toString());
        str = c;
        stringBuilder = new StringBuilder();
        stringBuilder.append("set SHARED_DIR : ");
        stringBuilder.append(this.j.d());
        Log.d(str, stringBuilder.toString());
        str = c;
        stringBuilder = new StringBuilder();
        stringBuilder.append("set IMAGE_VER  : ");
        stringBuilder.append(getIntent().getStringExtra("imageVersion"));
        Log.d(str, stringBuilder.toString());
        SystemConfig systemConfig = new SystemConfig();
        systemConfig.set(SystemConfigType.IMAGE_NAME, this.g.b());
        systemConfig.set(SystemConfigType.IMAGE_DESC, this.h.b());
        systemConfig.set(SystemConfigType.IMAGE_PATH, getIntent().getStringExtra("imagePath"));
        systemConfig.set(SystemConfigType.IMAGE_SIZE, Integer.toString(i));
        systemConfig.set(SystemConfigType.SHARE_FOLDER, this.j.d() ? SystemConfigHelper.CONFIG_OPTION_ON : SystemConfigHelper.CONFIG_OPTION_OFF);
        systemConfig.set(SystemConfigType.IMAGE_VERSION, getIntent().getStringExtra("imageVersion"));
        return SystemConfigManager.save(systemConfig);
    }

    private void U() {
        if (this.k) {
            a.a().a((a) this).a((int) R.string.exit_creation_popup_title).b((int) R.string.exit_creation_popup_msg).c((int) R.string.continue_setup).d(R.string.ok).b(new OnClickListener() {
                public void onClick(DialogInterface dialogInterface, int i) {
                    CreateActivity.this.a(CreateActivity.this);
                }
            }).e();
        } else {
            a((Activity) this);
        }
    }

    public void onBackPressed() {
        U();
    }
}
