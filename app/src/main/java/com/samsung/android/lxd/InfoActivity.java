package com.samsung.android.lxd;

import android.app.ActionBar;
import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Menu;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebResourceResponse;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import com.samsung.android.lxd.a.c;
import com.samsung.android.lxd.a.f;
import com.samsung.android.lxd.a.o;
import com.samsung.android.lxd.processor.LxdException;
import com.samsung.android.lxd.processor.utils.log.Log;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Locale;

public class InfoActivity extends a {
    private static final String b = "InfoActivity";

    public boolean onCreateOptionsMenu(Menu menu) {
        return true;
    }

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        f fVar = (f) getIntent().getSerializableExtra("infoActivityType");
        if (fVar == null) {
            throw new LxdException("No info type received!");
        }
        setContentView(R.layout.activity_info);
        WebView webView = (WebView) findViewById(R.id.webViewLayout);
        CharSequence charSequence = "";
        String str = "";
        switch (fVar) {
            case DISCLAIMER:
                str = "disclaimerNotice";
                charSequence = getString(R.string.disclaimer);
                break;
            case PRIVACY_POLICY:
                str = "privacyPolicyNotice";
                charSequence = getString(R.string.privacy_policy);
                break;
            case OPEN_SOURCE_LICENCES:
                str = "openSourceNotice";
                charSequence = getString(R.string.open_source_licenses_capital);
                break;
            default:
                Log.e(b, "Info Type Error !");
                break;
        }
        if (fVar == f.PRIVACY_POLICY) {
            a(webView);
        } else {
            a(webView, str);
        }
        ActionBar actionBar = getActionBar();
        if (actionBar != null) {
            actionBar.setTitle(charSequence);
        }
    }

    private void a(WebView webView) {
        String format;
        String str;
        Object a = c.a((Context) this);
        if (TextUtils.isEmpty(a) || !"kr".equalsIgnoreCase(a)) {
            String format2 = String.format("https://static.bada.com/contents/legal/%1$s/%2$s/globalpp.html", new Object[]{a, Locale.getDefault().getISO3Language()});
            format = String.format("https://static.bada.com/contents/legal/%1$s/%2$s/globalpp.html", new Object[]{a, "default"});
            str = format2;
        } else {
            str = "https://static.bada.com/contents/legal/kor/kor/globalpp.html";
            format = "https://static.bada.com/contents/legal/kor/kor/globalpp.html";
        }
        String str2 = b;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("firstURL: ");
        stringBuilder.append(str);
        Log.i(str2, stringBuilder.toString());
        str2 = b;
        stringBuilder = new StringBuilder();
        stringBuilder.append("secondUrl: ");
        stringBuilder.append(format);
        Log.i(str2, stringBuilder.toString());
        webView.getSettings().setJavaScriptEnabled(true);
        webView.loadUrl(str);
        webView.setWebViewClient(new WebViewClient() {
            public void onReceivedError(WebView webView, WebResourceRequest webResourceRequest, WebResourceError webResourceError) {
                String url = webView.getUrl();
                String N = InfoActivity.b;
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append("url onReceivedError: ");
                stringBuilder.append(url);
                Log.i(N, stringBuilder.toString());
                if (str.equals(url)) {
                    webView.loadUrl(format);
                } else if (format.equals(url)) {
                    webView.loadUrl("https://static.bada.com/contents/legal/global/default/globalpp.html");
                }
                super.onReceivedError(webView, webResourceRequest, webResourceError);
            }

            public void onReceivedHttpError(WebView webView, WebResourceRequest webResourceRequest, WebResourceResponse webResourceResponse) {
                String url = webView.getUrl();
                String N = InfoActivity.b;
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append("url onReceivedHttpError");
                stringBuilder.append(url);
                Log.i(N, stringBuilder.toString());
                if (str.equals(url)) {
                    webView.loadUrl(format);
                } else if (format.equals(url)) {
                    webView.loadUrl("https://static.bada.com/contents/legal/global/default/globalpp.html");
                }
                super.onReceivedHttpError(webView, webResourceRequest, webResourceResponse);
            }
        });
    }

    private void a(WebView webView, String str) {
        try {
            webView.loadData(URLEncoder.encode(String.format("<html><head></head><meta name='viewport' content='width=device-width, user-scalable=yes'/><body><pre>%s</pre></body></html>", new Object[]{o.d(getApplicationContext(), str)}), "utf-8").replaceAll("\\+", "%20"), "text/html", "utf-8");
            WebSettings settings = webView.getSettings();
            settings.setUseWideViewPort(true);
            settings.setSupportZoom(true);
            settings.setBuiltInZoomControls(true);
            settings.setDisplayZoomControls(false);
            settings.setLoadWithOverviewMode(true);
            settings.setDefaultFontSize(13);
        } catch (UnsupportedEncodingException e) {
            String str2 = b;
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("UnsupportedEncodingException:");
            stringBuilder.append(e.toString());
            Log.e(str2, stringBuilder.toString());
        }
    }
}
