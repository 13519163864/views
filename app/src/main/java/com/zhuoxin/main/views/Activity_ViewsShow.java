package com.zhuoxin.main.views;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import java.util.ArrayList;

import entry.Source;
import interFace.OnResourceListener;
import utils.Task;

/**
 * Created by Administrator on 2016/10/19.
 */

public class Activity_ViewsShow extends Activity implements OnResourceListener {
    WebView mWeb;
    ArrayList<Source> list = new ArrayList<>();
    int i;
    public static final String PATH = "http://118.244.212.82:9092/newsClient/path/news_list?ver=1&subid=1&dir=1&nid=1&stamp=20140321&cnt=20";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.views_show_layout );
        mWeb = (WebView) findViewById( R.id.webView );
        Task task = new Task();
        task.setListener( this );

        task.execute( PATH );

    }


    @Override
    public void getAllData(Source source) {
        list.add( source );
        Intent intent = this.getIntent();
        mWeb.getSettings().setJavaScriptEnabled( true );

        i = intent.getIntExtra( "i", -1 );

        mWeb.loadUrl( list.get( i ).getLink() );
        Log.e( "===", "-------------" + list.get( i ).getLink() );
        mWeb.setWebViewClient( new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl( url );
                return true;
                //true用webview打开,否则由第三方浏览器打开

            }
        } );
    }

    @Override
    public void getResource(Source source) {

    }

}
