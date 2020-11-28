package com.newsapp.duraimurugan.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.newsapp.duraimurugan.R;
import com.newsapp.duraimurugan.Utility.MyProgressDialog;

public class NewsDetailActivity extends AppCompatActivity {
String Newsweburl;
    Context context;
    WebView webView;
    MyProgressDialog myProgressDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_detail);
        context=NewsDetailActivity.this;
        webView=findViewById(R.id.details_webview);

        myProgressDialog = new MyProgressDialog(context, "Please wait..");
        myProgressDialog.show();
        Bundle url = getIntent().getExtras();
        if (url != null) {
            Newsweburl = getIntent().getStringExtra("url");

            loadWebview(Newsweburl);
        }
    }

    private void loadWebview(String newsweburl) {
        webView.getSettings().setJavaScriptEnabled(true);
        webView.loadUrl(newsweburl);

        webView.setWebViewClient(new WebViewClient() {
            @Override
            public void onPageFinished(WebView view, String url) {
                try {
                    if (null != myProgressDialog && myProgressDialog.isShowing())
                        myProgressDialog.dismiss();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                super.onPageFinished(view, url);
            }

            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                if(!((Activity) context).isFinishing())
                {
                    myProgressDialog.show();
                }
                super.onPageStarted(view, url, favicon);
            }


            @Override
            public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {
                try {
                    if (null != myProgressDialog && myProgressDialog.isShowing())
                        myProgressDialog.dismiss();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                super.onReceivedError(view, errorCode, description, failingUrl);

            }
        });

    }
}