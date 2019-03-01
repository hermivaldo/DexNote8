package com.samsung.android.lxd;

import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Build.VERSION;
import android.os.Bundle;
import com.samsung.android.lxd.a.d;
import com.samsung.android.lxd.a.l;
import com.samsung.android.lxd.a.n;
import com.samsung.android.lxd.a.o;
import com.samsung.android.lxd.fragment.CardViewFragment;
import com.samsung.android.lxd.fragment.ImageListFragment;
import com.samsung.android.lxd.processor.ExecutionType;
import com.samsung.android.lxd.processor.utils.log.Log;
import java.io.File;

public class ListActivity extends h {
    private static final String b = "ListActivity";
    private String c = null;

    protected void onCreate(Bundle bundle) {
        i();
        super.onCreate(bundle);
        this.a = "004";
        if (ExecutionType.QUICK_GUI.ordinal() == getIntent().getIntExtra("executionType", -1)) {
            getIntent().removeExtra("executionType");
            a(ExecutionType.QUICK_GUI, getIntent().getStringExtra("configId"));
        }
        if (o.e((Context) this)) {
            setContentView(R.layout.activity_list);
        } else {
            setContentView(R.layout.activity_list_tablet);
        }
        N();
    }

    protected void onActivityResult(int i, int i2, Intent intent) {
        String str = b;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("onActivityResult: requestCode: ");
        stringBuilder.append(i);
        stringBuilder.append(", resultCode: ");
        stringBuilder.append(i2);
        Log.d(str, stringBuilder.toString());
        if (i != 6) {
            super.onActivityResult(i, i2, intent);
            return;
        }
        String a = a(intent);
        if (!a.equalsIgnoreCase(n.a().a(-1))) {
            l.a(this.a, String.valueOf(2301), a);
        }
    }

    public void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        N();
    }

    public void r() {
        a(true);
    }

    public void y() {
        Log.d(b, "onImageFileUpdated");
        N();
    }

    protected void onResume() {
        super.onResume();
        Log.d(b, "onResume");
        N();
    }

    protected void onPause() {
        super.onPause();
        Log.d(b, "onPause");
        E();
    }

    public void a(boolean z) {
        if (c()) {
            D();
            if (o.f() || o.d((Context) this)) {
                float f = (float) o.m(this).x;
                findViewById(R.id.imageList).getLayoutParams().width = (int) (0.4375f * f);
                findViewById(R.id.details).getLayoutParams().width = (int) (f * 0.5625f);
            }
            this.c = getIntent().getStringExtra("configId");
            if (this.c != null) {
                getIntent().removeExtra("configId");
            }
            ((ImageListFragment) getFragmentManager().findFragmentById(R.id.imageList)).a(this.c).a(z).d();
            d.a(this, (CardViewFragment) getFragmentManager().findFragmentById(R.id.cardView));
        }
    }

    public void N() {
        a(false);
    }

    private String a(Intent intent) {
        if (intent == null) {
            Log.e(b, "REQUEST_GET_UNZIP_PATH_TO_MYFILES error! : data is NULL!");
            return n.a().a(70000);
        }
        int intExtra = intent.getIntExtra("status", -1);
        String stringExtra = intent.getStringExtra("extractedFolderName");
        String stringExtra2 = intent.getStringExtra("lod_extract_msg");
        String a = n.a().a(intent.getIntExtra("error_type", -1));
        String str = b;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Unzip result code : ");
        stringBuilder.append(intExtra);
        stringBuilder.append(", unzip path :");
        stringBuilder.append(stringExtra);
        stringBuilder.append(", error msg : ");
        stringBuilder.append(stringExtra2);
        stringBuilder.append(", error string from code : ");
        stringBuilder.append(a);
        Log.i(str, stringBuilder.toString());
        if (stringExtra == null) {
            return n.a().a(70001);
        }
        if (intExtra != 0) {
            if (VERSION.SDK_INT != 28) {
                a = stringExtra2;
            }
            return a;
        }
        a = "";
        try {
            File file = new File(stringExtra);
            if (file.listFiles() != null) {
                File[] listFiles = file.listFiles();
                int length = listFiles.length;
                int i = 0;
                while (i < length) {
                    File file2 = listFiles[i];
                    if (!file2.getName().substring(file2.getName().lastIndexOf(46) + 1).equalsIgnoreCase("img")) {
                        i++;
                    } else if (file2.isFile()) {
                        a = file2.getAbsolutePath();
                    } else {
                        stringExtra = b;
                        StringBuilder stringBuilder2 = new StringBuilder();
                        stringBuilder2.append("extractedFileName is not a file ! : ");
                        stringBuilder2.append(file2);
                        Log.e(stringExtra, stringBuilder2.toString());
                        return n.a().a(70002);
                    }
                }
            }
        } catch (Exception e) {
            stringExtra2 = b;
            StringBuilder stringBuilder3 = new StringBuilder();
            stringBuilder3.append("extractedFileName EXCEPTION! ");
            stringBuilder3.append(e);
            Log.e(stringExtra2, stringBuilder3.toString());
        }
        if (a.isEmpty()) {
            Log.e(b, "unzip imagePath from myFiles : null");
            return n.a().a(70003);
        }
        Intent intent2 = new Intent(this, CreateActivity.class);
        intent2.putExtra("imagePath", a);
        intent2.putExtra("fromMyFilesUnzip", true);
        startActivity(intent2);
        return n.a().a(-1);
    }
}
