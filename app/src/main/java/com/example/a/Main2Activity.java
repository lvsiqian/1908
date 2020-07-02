package com.example.a;

import android.content.Intent;
import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import androidx.appcompat.app.AppCompatActivity;

public class Main2Activity extends AppCompatActivity {

    private WebView web;
    private long starttime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        //记录开始的时间
        starttime = System.currentTimeMillis();
        initView();
    }

    private void initView() {
        web = (WebView) findViewById(R.id.web);
        String link = getIntent().getStringExtra("link");
        web.loadUrl(link);
        web.setWebViewClient(new WebViewClient());
    }
    //屏幕下面的返回的监听
    @Override
    public void onBackPressed() {
        //结束的时间
        long endtime = System.currentTimeMillis();
        //记录时间
        long l = endtime - starttime;
        //把数据返回主界面
        Intent intent = new Intent();
        intent.putExtra("time", l);
        setResult(200,intent);
        super.onBackPressed();
    }
}
