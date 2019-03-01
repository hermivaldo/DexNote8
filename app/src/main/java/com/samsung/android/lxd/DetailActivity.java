package com.samsung.android.lxd;

import android.app.ActionBar;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.samsung.android.lxd.a.j;
import com.samsung.android.lxd.a.l;
import com.samsung.android.lxd.a.o;
import com.samsung.android.lxd.fragment.CardViewFragment;
import com.samsung.android.lxd.fragment.CardViewFragment.a;
import com.samsung.android.lxd.fragment.CardViewFragment.b;
import com.samsung.android.lxd.fragment.TextButtonFragment;
import com.samsung.android.lxd.fragment.TextViewFragment;
import com.samsung.android.lxd.processor.ExecutionType;
import com.samsung.android.lxd.processor.config.SystemConfig;
import com.samsung.android.lxd.processor.config.SystemConfigHelper;
import com.samsung.android.lxd.processor.config.SystemConfigManager;
import com.samsung.android.lxd.processor.config.SystemConfigType;
import com.samsung.android.lxd.processor.utils.log.Log;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class DetailActivity extends h {
    private static final String b = "DetailActivity";
    private String c = null;
    private SystemConfig d = null;

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        if (o.d((Context) this)) {
            finish();
            onBackPressed();
            return;
        }
        setContentView(R.layout.activity_detail);
        this.a = "009";
        ActionBar actionBar = getActionBar();
        if (actionBar != null) {
            actionBar.setTitle(R.string.detail_toolbar_title);
        }
        this.c = getIntent().getStringExtra("configId");
        this.d = SystemConfigManager.load(Long.parseLong(this.c));
        Q();
    }

    protected void onResume() {
        super.onResume();
        Log.d(b, "onResume");
        Q();
    }

    protected void onPause() {
        super.onPause();
        Log.d(b, "onPause");
        E();
    }

    public void y() {
        Q();
    }

    public void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        Q();
    }

    public void onBackPressed() {
        l.a(this.a, String.valueOf(901));
        super.b();
    }

    private boolean N() {
        return new File(this.d.get(SystemConfigType.IMAGE_PATH)).isFile() ^ 1;
    }

    private void O() {
        ArrayList arrayList = new ArrayList();
        arrayList.add(o.a(getApplicationContext(), this.d.get(SystemConfigType.IMAGE_PATH)));
        List arrayList2 = new ArrayList();
        arrayList2.add(this.c);
        ((CardViewFragment) getFragmentManager().findFragmentById(R.id.cardView)).a(b.WITH_TEXT_BUTTON_PHONE).a((int) R.string.run_image).a(arrayList, arrayList2).a(false).b(true).a(new a() {
            public void a(int i) {
                l.a(DetailActivity.this.a, String.valueOf(903));
                DetailActivity.this.a(ExecutionType.GUI, DetailActivity.this.c);
            }
        }).b();
        ((RelativeLayout) findViewById(R.id.baseLayout)).setVisibility(0);
        ((LinearLayout) findViewById(R.id.faultImageLayout)).setVisibility(4);
        ((TextViewFragment) getFragmentManager().findFragmentById(R.id.nameTextView)).a(getString(R.string.detail_name)).b(this.d.get(SystemConfigType.IMAGE_NAME)).a(true).c();
        ((TextViewFragment) getFragmentManager().findFragmentById(R.id.descTextView)).a(getString(R.string.description)).b(this.d.get(SystemConfigType.IMAGE_DESC)).a(true).c();
        TextViewFragment textViewFragment = (TextViewFragment) getFragmentManager().findFragmentById(R.id.imageSizeTextView);
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(this.d.get(SystemConfigType.IMAGE_SIZE));
        stringBuilder.append(" ");
        stringBuilder.append(getString(R.string.gb));
        textViewFragment.b(stringBuilder.toString()).c();
        ((TextViewFragment) getFragmentManager().findFragmentById(R.id.useShareFolder)).b(getString(SystemConfigHelper.isConfigOptionOn(this.d, SystemConfigType.SHARE_FOLDER) ? R.string.share_folder_enabled : R.string.share_folder_disabled)).a(8).c();
        ((LinearLayout) findViewById(R.id.play_button)).setOnClickListener(new j() {
            public void onClick(View view) {
                super.onClick(view);
                l.a(DetailActivity.this.a, String.valueOf(904));
                DetailActivity.this.runTerminalMode();
            }
        });
        ((LinearLayout) findViewById(R.id.edit_button)).setOnClickListener(new j() {
            public void onClick(View view) {
                super.onClick(view);
                l.a(DetailActivity.this.a, String.valueOf(906));
                DetailActivity.this.editImage();
            }
        });
        ((LinearLayout) findViewById(R.id.delete_button)).setOnClickListener(new j() {
            public void onClick(View view) {
                super.onClick(view);
                l.a(DetailActivity.this.a, String.valueOf(905));
                DetailActivity.this.deleteImage();
            }
        });
    }

    private void P() {
        ArrayList arrayList = new ArrayList();
        arrayList.add(o.a(getApplicationContext(), this.d.get(SystemConfigType.IMAGE_PATH)));
        List arrayList2 = new ArrayList();
        arrayList2.add(this.c);
        ((CardViewFragment) getFragmentManager().findFragmentById(R.id.cardView)).a(b.WITH_UNMOUNT_IMAGE).a((int) R.string.unmount_button).a(arrayList, arrayList2).a(false).b(true).a(new a() {
            public void a(int i) {
                DetailActivity.this.f(DetailActivity.this.c);
            }
        }).b();
        ((RelativeLayout) findViewById(R.id.baseLayout)).setVisibility(4);
        ((LinearLayout) findViewById(R.id.faultImageLayout)).setVisibility(0);
        TextView textView = (TextView) findViewById(R.id.imageDetail);
        textView.setText(R.string.see_image_details);
        textView.setOnClickListener(new j() {
            public void onClick(View view) {
                super.onClick(view);
                DetailActivity.this.a(new File(DetailActivity.this.d.get(SystemConfigType.IMAGE_PATH)));
            }
        });
        ((TextButtonFragment) getFragmentManager().findFragmentById(R.id.browseButton)).a((int) R.string.browse).a(new j() {
            public void onClick(View view) {
                super.onClick(view);
                DetailActivity.this.g(DetailActivity.this.c);
            }
        }).b(20).b();
    }

    private void Q() {
        if (c()) {
            D();
        }
        if (N()) {
            P();
        } else {
            O();
        }
    }

    public void runTerminalMode() {
        a(ExecutionType.CLI, this.c);
    }

    public void editImage() {
        Intent intent = new Intent(this, EditActivity.class);
        intent.putExtra("configId", this.c);
        startActivity(intent);
    }

    public void deleteImage() {
        deleteImage(this.c);
    }
}
