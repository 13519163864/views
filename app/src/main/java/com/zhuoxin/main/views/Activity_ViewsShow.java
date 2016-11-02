package com.zhuoxin.main.views;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;

import java.util.ArrayList;

import entry.Source;
import fragment.CenterFragment;

/**
 * Created by Administrator on 2016/10/19.
 */

public class Activity_ViewsShow extends Activity implements View.OnClickListener {
    WebView mWeb;
    ;
    ArrayList<Source> list = new ArrayList<>();
    //    ArrayList<Source> list1 = new ArrayList<>();
    int i;
    ImageView mBack;
//    public static final String PATH = "http://118.244.212.82:9092/newsClient/path/news_list?ver=1&subid=1&dir=1&nid=1&stamp=20140321&cnt=20";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.views_show_layout );
        mWeb = (WebView) findViewById( R.id.webView );
        mWeb.getSettings().setJavaScriptEnabled( true );
        mBack = (ImageView) findViewById( R.id.img_web_back );
        mBack.setOnClickListener( this );
        list = new CenterFragment().getList();
        Log.e( "===", "-------------" + list.size() );
        //设置手机客户端的显示样式
        WebSettings settings = mWeb.getSettings();
        settings.setJavaScriptEnabled( true );
//设置自动适应屏幕大小WebSettings.LayoutAlgorithm.NARROW_COLUMNS尽可能在一行中完整的显示WebSettings.LayoutAlgorithm.NORMAL不添加任何样式
        //WebSettings.LayoutAlgorithm.SINGLE_COLUMN在一行中显示,自动调整大小
        settings.setLayoutAlgorithm( WebSettings.LayoutAlgorithm.SINGLE_COLUMN );
        //设置webview推荐使用窗口
        settings.setUseWideViewPort( true );
        //自适应大小,
        settings.setLoadWithOverviewMode( true );
        //设置任意缩放
        settings.setSupportZoom( true );
        //设置放大缩小按钮显示
        settings.setBuiltInZoomControls( true );
        //设置调整窗口
        settings.setSupportMultipleWindows( true );
        //设置控制器
        settings.setDisplayZoomControls( true );

        Intent intent = this.getIntent();
        i = intent.getIntExtra( "i", -1 ) - 1;
        //加载网页
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
//        Task task = new Task();
//        task.setListener( this );
//
//        task.execute( PATH );


    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.img_web_back:
                startActivity( new Intent( Activity_ViewsShow.this, Activity_Menu.class ) );
                finish();
                break;
        }
    }

//
//    @Override
//    public void getAllData(Source source) {
////        list.clear();
//        list.add( source );

//    }

//    @Override
//    public boolean onKeyDown(int keyCode, KeyEvent event) {
//        if (keyCode== KeyEvent.KEYCODE_BACK) {
//            mWeb.goBack();
//            return  true;
//        }
//        return super.onKeyDown( keyCode, event );
//    }

//    @Override
//    public void getResource(Source source) {
////        list.clear();
//        list.add( source );
//    }

}
