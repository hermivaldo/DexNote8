package com.samsung.android.lxd;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.os.Bundle;
import android.webkit.DownloadListener;
import android.webkit.URLUtil;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import com.samsung.android.lxd.a.d;
import com.samsung.android.lxd.a.l;
import com.samsung.android.lxd.a.o;
import com.samsung.android.lxd.a.p;
import com.samsung.android.lxd.fragment.b;
import com.samsung.android.lxd.processor.utils.log.Log;

public class WebViewActivity extends a {
    private static final String b = "WebViewActivity";
    private WebView c = null;
    private p d = null;
    private String e = null;
    private String f = null;
    private boolean g = false;

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.d = new p();
        this.d.a((int) R.string.app_name, (int) R.string.wait, (Context) this, null);
        this.a = "020";
        setContentView(R.layout.activity_web_view);
        this.c = (WebView) findViewById(R.id.webView);
        WebSettings settings = this.c.getSettings();
        settings.setBuiltInZoomControls(true);
        settings.setDisplayZoomControls(false);
        settings.setJavaScriptEnabled(true);
        settings.setLoadWithOverviewMode(true);
        settings.setSupportZoom(true);
        settings.setUseWideViewPort(true);
        this.c.setWebChromeClient(new WebChromeClient() {
            public void onProgressChanged(WebView webView, int i) {
                if (i == 100) {
                    WebViewActivity.this.d.a();
                } else if (!WebViewActivity.this.d.b()) {
                    WebViewActivity.this.d.a((int) R.string.app_name, (int) R.string.wait, WebViewActivity.this, null);
                }
            }
        });
        this.c.setWebViewClient(new WebViewClient() {
            public boolean shouldOverrideUrlLoading(WebView webView, String str) {
                webView.loadUrl(str);
                return true;
            }
        });
        this.c.setDownloadListener(new DownloadListener() {
            public void onDownloadStart(String str, String str2, String str3, String str4, long j) {
                WebViewActivity.this.e = str;
                WebViewActivity.this.f = str3;
                WebViewActivity.this.O();
            }
        });
        String H = o.H();
        if (H.isEmpty()) {
            this.c.loadUrl("https://webview.linuxondex.com");
            return;
        }
        String str = b;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Debug url : ");
        stringBuilder.append(H);
        Log.i(str, stringBuilder.toString());
        this.c.loadUrl(H);
    }

    public void onBackPressed() {
        if (this.c.canGoBack()) {
            this.c.goBack();
        } else {
            super.b();
        }
    }

    protected void onActivityResult(int i, int i2, Intent intent) {
        String str = b;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("onActivityResult: ");
        stringBuilder.append(i);
        stringBuilder.append(", ");
        stringBuilder.append(i2);
        Log.d(str, stringBuilder.toString());
        if (i != 4) {
            super.onActivityResult(i, i2, intent);
        } else if (i2 == -1) {
            P();
        }
    }

    private void O() {
        final b j = b.j();
        j.h(R.string.wifi_only_checkbox_message).a((a) this).a((int) R.string.image_download).b((int) R.string.image_download_msg).c((int) R.string.popup_continue).a(new OnClickListener() {
            public void onClick(DialogInterface dialogInterface, int i) {
                WebViewActivity.this.g = j.k();
                l.a(WebViewActivity.this.a, String.valueOf(2101), WebViewActivity.this.g ? "Wi-Fi only" : "Wi-Fi only uncheck");
                String N = WebViewActivity.b;
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append("User click on [Wi-Fi only] : ");
                stringBuilder.append(WebViewActivity.this.g);
                Log.i(N, stringBuilder.toString());
                if (!WebViewActivity.this.g || o.D()) {
                    WebViewActivity.this.P();
                    return;
                }
                o.E();
                WebViewActivity.this.startActivityForResult(o.F(), 4);
            }
        }).d(R.string.popup_cancel).b(new OnClickListener() {
            public void onClick(DialogInterface dialogInterface, int i) {
                l.a(WebViewActivity.this.a, String.valueOf(2102));
                dialogInterface.dismiss();
            }
        }).e();
    }

    private void P() {
        boolean commit = LxdApplication.a().getSharedPreferences("prefs", 0).edit().putString(String.valueOf("downloadFile"), URLUtil.guessFileName(this.e, this.f, null)).putString(String.valueOf("downloadUrl"), this.e).putString(String.valueOf("downloadPos"), this.f).putBoolean(String.valueOf("downloadWifiOnly"), this.g).commit();
        o.I();
        d.a(true);
        String str = b;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("save DownloadInfo ret : ");
        stringBuilder.append(commit);
        Log.i(str, stringBuilder.toString());
        a((Activity) this);
    }
}
