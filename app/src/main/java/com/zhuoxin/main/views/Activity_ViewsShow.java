package com.zhuoxin.main.views;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
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

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import cn.sharesdk.framework.ShareSDK;
import cn.sharesdk.onekeyshare.OnekeyShare;
import entry.CmtListInfo;
import entry.Source;
import entry.UriInfo;
import fragment.CenterFragment;
import interFace.OnLoadResponseListener;
import utils.HttpUtils;
import utils.SqlUtils;

/**
 * Created by Administrator on 2016/10/19.
 * 加载链接 webview
 */

public class Activity_ViewsShow extends Activity implements View.OnClickListener, OnLoadResponseListener {
    WebView mWeb;
    Context mContext;
    //数据源
    ArrayList<Source> list = new ArrayList<>();
    int i;
    ImageView mBack;
    ImageView mPopu;
    TextView mCmtNum;
    List<Integer> mList = new ArrayList<>();
    RequestQueue requestQueue;
    static ArrayList<CmtListInfo> CmtList = new ArrayList<>();
    ArrayList<Source> sources;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.views_show_layout );
        mWeb = (WebView) findViewById( R.id.webView );
        mWeb.getSettings().setJavaScriptEnabled( true );
        mBack = (ImageView) findViewById( R.id.img_web_back );
        mBack.setOnClickListener( this );
        mPopu = (ImageView) findViewById( R.id.img_web_favorite );
        mCmtNum = (TextView) findViewById( R.id.txt_web_repert );
        mCmtNum.setOnClickListener( this );
        mContext = this;
        mPopu.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showPopupWindow( view );

            }
        } );
        sources = new SqlUtils( mContext ).checkNews();

        list = new CenterFragment().getList();
        Log.e( "===", "-------------" + list.size() );
        requestQueue = Volley.newRequestQueue( this );
        Log.e( "========", "list的长度" + list.size() );


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
        //获取
        new HttpUtils().CmtNum( UriInfo.BaseUrl + UriInfo.CMT_NUM, this, requestQueue, list.get( i ).getNid() );

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
                startActivity( new Intent( Activity_ViewsShow.this, Activity_Menu.class ) );
                finish();
                break;
            case R.id.txt_web_repert:
//                携带数据跳转,将当前页面的下标传递给跳转界面,以便于获取数据源,
                Intent intent = new Intent( Activity_ViewsShow.this, Activity_Commit.class );
                //因为本页面使用的是xlistview给下标-1,所以传递时需给下标加+1,否则数据获取不完整
                intent.putExtra( "position", i + 1 );
                startActivityForResult( intent, 1 );
                break;
        }
    }

    public int getI() {
        return i;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult( requestCode, resultCode, data );
    }

    //popupwindow下拉列表
    public void showPopupWindow(View view) {
        //加载布局
        View contentView = LayoutInflater.from( mContext ).inflate( R.layout.popu, null );
        //加载组件,为收藏功能
        TextView textView = (TextView) contentView.findViewById( R.id.txt_popu );
        //加入收藏设置点击事件
        textView.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //position被点击时拿到当前新闻的信息
                String summary = list.get( i ).getSummary();
                String icon = list.get( i ).getIcon();
                String link = list.get( i ).getLink();
                String nid = list.get( i ).getNid();
                String stamp = list.get( i ).getStamp();
                String title = list.get( i ).getTitle();
                String type = list.get( i ).getType();
                //将当前收藏新闻的信息调用数据库插入方法插入数据库中

                for (int j = 0; j < sources.size(); j++) {
                    Log.e( "===", "source" + sources );
                    if (sources.get( j ).getNid().equals( list.get( i ).getNid() )) {
                        Toast.makeText( mContext, "已存在", Toast.LENGTH_SHORT ).show();
                    } else {

                        new SqlUtils( mContext ).inSert( nid, title, summary, stamp, icon, link, type );
                        //插入之后toast提醒用户
                        Toast.makeText( mContext, "已收藏,请到侧拉列表中查看", Toast.LENGTH_SHORT ).show();
                    }
                }

            }
        } );
        //设置一键分享
        TextView share = (TextView) contentView.findViewById( R.id.txt_popu_share );
        share.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //初始化ShareSDK
                ShareSDK.initSDK( mContext );
                //实例对象
                OnekeyShare onekeyShare = new OnekeyShare();
                //关闭sso授权
                onekeyShare.disableSSOWhenAuthorize();
                //onekeyShare.
                //设置分享时的图片
                onekeyShare.setImageUrl( list.get( i ).getIcon() );
                //设置分享内容
                onekeyShare.setText( list.get( i ).getSummary() );
                //设置分享标题
                onekeyShare.setTitle( list.get( i ).getTitle() );
                //设置分享链接
                onekeyShare.setTitleUrl( list.get( i ).getLink() );
                //设置显示
                onekeyShare.show( mContext );
            }
        } );
        //popupwindow
        PopupWindow popupWindow = new PopupWindow( contentView, ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT, true );
        //popupwindow点击事件
        popupWindow.setTouchable( true );
        popupWindow.setTouchInterceptor( new View.OnTouchListener() {
            //点击事件监听
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                Log.e( "mengdd", "onTOUCH" );
                return false;
            }
        } );
        //设置popupwindow背景图片,若不设置则不可点击
        popupWindow.setBackgroundDrawable( getResources().getDrawable( R.mipmap.tt ) );
        //设置popupwindow显示类型
        popupWindow.showAsDropDown( view );
    }

    @Override
    public void getResponse(String succuful) {
        //获取评论数量
        Log.e( "===", "评论数量" + succuful );
        try {
            //使用SharedPreferences保存评论数量
            SharedPreferences cmtList = this.getSharedPreferences( "cmtList", MODE_PRIVATE );
            SharedPreferences.Editor edit = cmtList.edit();
            JSONObject jsonObject = new JSONObject( succuful );
            String message = jsonObject.getString( "message" );
            edit.putString( "message", message );
            int status = jsonObject.getInt( "status" );
            edit.putInt( "status", status );
            String data = jsonObject.getString( "data" );
            edit.putString( "data", data );
            edit.commit();
            Log.e( "====", "数据" + data );

            //将评论数量显示在组件中
            mCmtNum.setText( "跟帖: " + data + "" );
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }


    @Override
    public void getCmtList(String data) {


    }

}
