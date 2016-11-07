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
import fragment.Favorite;
import utils.SqlUtils;

/**
 * Created by Administrator on 2016/11/7.
 */

public class Activity_Favorite extends Activity implements View.OnClickListener {
    WebView mWeb;
    Context mContext;
    ArrayList<Source> list = new ArrayList<>();
    //    ArrayList<Source> list1 = new ArrayList<>();
    int i;
    ImageView mBack;
    ImageView mPopu;

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

        mPopu.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showPopupWindow( view );

            }
        } );
        list = Favorite.getList();
        Log.e( "====", "favorite====" + list.size() );
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
        i = intent.getIntExtra( "i", -1 );
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


    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.img_web_back:
                startActivity( new Intent( Activity_Favorite.this, Activity_Menu.class ) );
                finish();
                break;
        }
    }

    public void showPopupWindow(View view) {
        View contentView = LayoutInflater.from( mContext ).inflate( R.layout.popu_delete, null );
        TextView textView = (TextView) contentView.findViewById( R.id.txt_popu_delete );
        textView.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                String summary = list.get( i ).getSummary();
//                String icon = list.get( i ).getIcon();
//                String link = list.get( i ).getLink();
                int nid = list.get( i ).getNid();
//                String stamp = list.get( i ).getStamp();
//                String title = list.get( i ).getTitle();
//                int type = list.get( i ).getType();
                new SqlUtils( mContext ).delete( nid );
                Toast.makeText( mContext, "已取消", Toast.LENGTH_SHORT ).show();

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
