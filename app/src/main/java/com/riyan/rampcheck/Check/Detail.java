package com.riyan.rampcheck.Check;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;

import com.riyan.rampcheck.R;
import com.riyan.rampcheck.Util.URLs;

public class Detail extends AppCompatActivity {
WebView webView;
String id_rampcheck;
ProgressBar progressBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        webView = (WebView) findViewById(R.id.rampcheckweb);
        progressBar = (ProgressBar)findViewById(R.id.progress);
        Intent intent = getIntent();
        id_rampcheck= intent.getExtras().getString("id_rampcheck");
        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webView.loadUrl(URLs.DETAIL_CHECK+id_rampcheck);
        webView.setWebViewClient(new myWebclient());
    }
    public class myWebclient extends WebViewClient {
        @Override
        public void onPageFinished(WebView view, String url) {
            super.onPageFinished(view, url);
            progressBar.setVisibility(View.GONE);
            webView.setVisibility(View.VISIBLE);
        }

        @Override
        public void onPageStarted(WebView view, String url, Bitmap favicon) {
            super.onPageStarted(view, url, favicon);
        }

        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            view.loadUrl(url);
            return super.shouldOverrideUrlLoading(view, url);
        }
    }
}