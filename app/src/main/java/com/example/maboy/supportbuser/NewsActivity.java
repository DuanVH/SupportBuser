package com.example.maboy.supportbuser;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;

public class NewsActivity extends AppCompatActivity implements View.OnClickListener {

    private ImageView ivBack;
    private WebView wvNews;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.news);
        initViews();

        // Khi click vào link thì sẽ không bị văng ra khỏi app của mình
        wvNews.setWebViewClient(new WebViewClient());

        // load trang web
        loadNews();
    }

    private void loadNews() {
        String url = "timbus.vn/article.aspx";
        wvNews.loadUrl("http://" + url);

        WebSettings webSettings = wvNews.getSettings();
        webSettings.setBuiltInZoomControls(true);
        webSettings.setDisplayZoomControls(false);
        webSettings.setJavaScriptEnabled(true);
    }

    private void initViews() {
        ivBack = (ImageView) findViewById(R.id.iv_back_news);
        wvNews = (WebView) findViewById(R.id.wv_news);

        ivBack.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_back_news:
                onBackPressed();
                break;
        }
    }

}
