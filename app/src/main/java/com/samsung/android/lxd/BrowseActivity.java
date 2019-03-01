package com.samsung.android.lxd;

import android.app.ActionBar;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import com.samsung.android.lxd.a.l;
import com.samsung.android.lxd.a.o;
import com.samsung.android.lxd.a.p;
import com.samsung.android.lxd.fragment.FileListFragment;
import com.samsung.android.lxd.fragment.SearchViewFragment;
import com.samsung.android.lxd.fragment.a;
import com.samsung.android.lxd.processor.utils.log.Log;

public class BrowseActivity extends a {
    private static final String b = "BrowseActivity";
    private FileListFragment c;
    private String d;
    private String e;
    private p f;
    private boolean g;

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.a = "006";
        setContentView(R.layout.activity_browse);
        this.c = (FileListFragment) getFragmentManager().findFragmentById(R.id.fileList);
        this.d = getIntent().getStringExtra("configId");
        this.e = "";
        if (getIntent().getBooleanExtra("searchSdCard", false)) {
            this.c.a();
        }
        ActionBar actionBar = getActionBar();
        if (actionBar != null) {
            actionBar.setTitle(R.string.select_image_toolbar_title);
        }
        this.g = false;
        this.f = new p();
    }

    public void b(String str) {
        String str2 = b;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("handleGetImageVersion: ");
        stringBuilder.append(str);
        stringBuilder.append(", request: ");
        stringBuilder.append(this.g);
        Log.d(str2, stringBuilder.toString());
        if (!this.g) {
            this.g = true;
            this.f.a((int) R.string.app_name, (int) R.string.wait, (Context) this, null);
            this.e = str;
            super.b(this.e);
        }
    }

    public void c(String str) {
        String str2 = b;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("handleRebaseImage: ");
        stringBuilder.append(this.e);
        stringBuilder.append(", request: ");
        stringBuilder.append(this.g);
        Log.d(str2, stringBuilder.toString());
        if (!this.g) {
            this.g = true;
            this.f.a((int) R.string.app_name, (int) R.string.wait, (Context) this, null);
            o.a("lxd_loader/cli_payload", "/data/lxd/lxd_loader");
            this.e = str;
            super.c(this.e);
        }
    }

    public void b(String str, int i) {
        String str2 = b;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("onImageVersionReceived: ");
        stringBuilder.append(i);
        stringBuilder.append(", ");
        stringBuilder.append(str);
        Log.d(str2, stringBuilder.toString());
        if (this.g) {
            this.f.a();
            boolean z = false;
            this.g = false;
            String str3;
            if (o.t()) {
                if (i == 5) {
                    a.a().a((a) this).a((int) R.string.rebase_image_title).b((int) R.string.rebase_image_body).c((int) R.string.ok).a(new OnClickListener() {
                        public void onClick(DialogInterface dialogInterface, int i) {
                            BrowseActivity.this.c(BrowseActivity.this.e);
                        }
                    }).e();
                } else {
                    str3 = this.e;
                    if (i == 0) {
                        z = true;
                    }
                    a(str3, z, this.d, str);
                }
                return;
            }
            str3 = this.e;
            if (i == 0) {
                z = true;
            }
            a(str3, z, this.d, str);
        }
    }

    public void e(String str, boolean z) {
        String str2 = b;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("onImageRebased: ");
        stringBuilder.append(z);
        stringBuilder.append(", ");
        stringBuilder.append(str);
        Log.d(str2, stringBuilder.toString());
        if (this.g) {
            this.f.a();
            this.g = false;
            a(this.e, z, this.d);
        }
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_browse, menu);
        this.c.a(this.d).a(menu).h();
        ((SearchViewFragment) getFragmentManager().findFragmentById(R.id.searchViewFragment)).a(this.c.f()).a(50).a(menu).a();
        return super.onCreateOptionsMenu(menu);
    }

    public boolean onOptionsItemSelected(MenuItem menuItem) {
        switch (menuItem.getItemId()) {
            case R.id.actionSearch:
                l.a(this.a, String.valueOf(602));
                ((SearchViewFragment) getFragmentManager().findFragmentById(R.id.searchViewFragment)).a(this.c.f()).a();
                break;
            case R.id.actionView:
                this.c.a(this.d).g().h();
                break;
        }
        return super.onOptionsItemSelected(menuItem);
    }

    public void m(String str) {
        ((SearchViewFragment) getFragmentManager().findFragmentById(R.id.searchViewFragment)).a(str).a();
    }

    public void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        this.c.h();
    }

    protected void onNewIntent(Intent intent) {
        if ((intent.getFlags() & 67108864) != 0) {
            recreate();
        } else {
            super.onNewIntent(intent);
        }
    }

    public boolean f(String str, boolean z) {
        Log.i(b, "onNetworkServiceError: ");
        super.f(str, z);
        return N();
    }

    public void onBackPressed() {
        l.a(this.a, String.valueOf(601));
        super.onBackPressed();
    }

    private boolean N() {
        Log.i(b, "handleError: ");
        this.f.a();
        this.g = false;
        if (c()) {
            this.f.a((int) R.string.unexpected_error, LxdApplication.a().getString(R.string.try_again_create), (Context) this, new p.a() {
                public void a() {
                    BrowseActivity.this.finish();
                }
            });
        } else {
            finish();
        }
        return true;
    }
}
