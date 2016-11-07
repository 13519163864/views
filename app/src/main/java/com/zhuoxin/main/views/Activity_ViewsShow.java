package com.zhuoxin.main.views;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import entry.Source;
import fragment.CenterFragment;
import utils.SqlUtils;

/**
 * Created by Administrator on 2016/10/19.
 */

public class Activity_ViewsShow extends Activity implements View.OnClickListener {
    WebView mWeb;
    Context mContext;
    ArrayList<Source> list = new ArrayList<>();
    //    ArrayList<Source> list1 = new ArrayList<>();
    int i;
    ImageView mBack;
    ImageView mPopu;
//    public static final String PATH = "http://118.244.212.82:9092/newsClient/path/news_list?ver=1&subid=1&dir=1&nid=1&stamp=20140321&cnt=20";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.views_show_layout );
        mWeb = (WebView) findViewById( R.id.webView );
        mWeb.getSettings().setJavaScriptEnabled( true );
        mBack = (ImageView) findViewById( R.id.img_web_back );
        mBack.setOnClickListener( this );
        mPopu = (ImageView) findViewById( R.id.img_web_favorite );

        mContext = this;
//        final PopupWindow popupWindow = new PopupWindow();
//
//        //设置view
//        popupWindow.setContentView( this.getLayoutInflater().inflate( R.layout.popu, null, false ) );
//        popupWindow.setWidth( 100 );
//        popupWindow.setHeight( 300 );
        mPopu.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showPopupWindow( view );

            }
        } );


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
    //popupwindow下拉列表
    public void showPopupWindow(View view) {
        View contentView = LayoutInflater.from( mContext ).inflate( R.layout.popu, null );
        TextView textView = (TextView) contentView.findViewById( R.id.txt_popu );
        textView.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String summary = list.get( i ).getSummary();
                String icon = list.get( i ).getIcon();
                String link = list.get( i ).getLink();
                int nid = list.get( i ).getNid();
                String stamp = list.get( i ).getStamp();
                String title = list.get( i ).getTitle();
                int type = list.get( i ).getType();
                new SqlUtils(mContext).inSert( nid,title,summary,stamp,icon,link,type );
                Toast.makeText( mContext, "已收藏,请到侧拉列表中查看", Toast.LENGTH_SHORT ).show();

            }
        } );
        PopupWindow popupWindow = new PopupWindow( contentView, ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT, true );
        popupWindow.setTouchable( true );
        popupWindow.setTouchInterceptor( new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                Log.e( "mengdd", "onTOUCH" );
                return false;
            }
        } );
        popupWindow.setBackgroundDrawable( getResources().getDrawable( R.mipmap.tt ) );
        popupWindow.showAsDropDown( view );
    }
}
