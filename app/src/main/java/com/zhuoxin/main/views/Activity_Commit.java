package com.zhuoxin.main.views;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import adapter.AdapterCommit;
import entry.CmtListInfo;
import entry.Source;
import entry.UriInfo;
import fragment.CenterFragment;
import interFace.OnLoadResponseListener;
import me.maxwin.view.XListView;
import utils.HttpUtils;

/**
 * Created by Administrator on 2016/11/8.
 * 显示评论界面
 */

public class Activity_Commit extends AppCompatActivity implements OnLoadResponseListener, View.OnClickListener, XListView.IXListViewListener {
    static ArrayList<CmtListInfo> list = new ArrayList<>();
    ArrayList<Source> mList = new ArrayList<>();
    int position;
    RequestQueue requestQueue;
    EditText commit;
    ImageView mCom;
    XListView xLst;
    Context mContext;
    Handler handler;
    String token;
    String ctx;
    String data;
    String cnt;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.commit );
        //显示评论内容的布局
        handler = new Handler();
        xLst = (XListView) findViewById( R.id.xLst_commit );
        //上拉加载
        xLst.setPullLoadEnable( true );
        //下拉刷新
        xLst.setPullRefreshEnable( true );
        //设置监听
        cnt = String.valueOf( 5 );
        xLst.setXListViewListener( this );

        //填写评论的对话框
        commit = (EditText) findViewById( R.id.edt_commit );
        //发表评论的对话框
        mCom = (ImageView) findViewById( R.id.img_commit );
        mContext = this;

        //获取上一页面跳转所携带的数据,通过意图获取
        Intent intent = getIntent();
        //获取position
        position = intent.getIntExtra( "position", -1 );
        //获取新闻列表数据源
        mList = new CenterFragment().getList();
//请求队列
        requestQueue = Volley.newRequestQueue( this );
        //获得SharedPreferences对象
        SharedPreferences cmtList = this.getSharedPreferences( "cmtList", MODE_PRIVATE );
        //拿取data,即评论数量
        data = cmtList.getString( "data", null );
        Log.e( "======", "评论内容数据源====" + mList.size() + "下标=====" + position + "data====" + data + "shuju ====" + list );
//请求评论内容
        new HttpUtils().CmtList( UriInfo.BaseUrl + UriInfo.CMT_REPERT, this, requestQueue, mList.get( position ).getNid(), "1", data );

        SharedPreferences signIn = this.getSharedPreferences( "signIn", MODE_PRIVATE );
        token = signIn.getString( "token", null );
        ctx = commit.getText().toString();
        //发布评论

        mCom.setOnClickListener( this );

    }

    @Override
    public void getResponse(String succuful) {

    }

    @Override
    public void getCmtList(String data) {
        //获取服务器返回的评论内容并解析
        Log.e( "===", "评论内容" + data );
        try {
            Log.e( "====", "解析" );
            JSONObject jsonObject = new JSONObject( data );
            String message = jsonObject.getString( "message" );
            int status = jsonObject.getInt( "status" );
            JSONArray array = jsonObject.getJSONArray( "data" );
            SharedPreferences cmtInfo = this.getSharedPreferences( "cmtInfo", MODE_PRIVATE );
            SharedPreferences.Editor edit = cmtInfo.edit();
            for (int j = 0; j < array.length(); j++) {
                JSONObject object = array.getJSONObject( j );
                String uid = object.getString( "uid" );
                String content = object.getString( "content" );
                String stamp = object.getString( "stamp" );
                String cid = object.getString( "cid" );
                String portrait = object.getString( "portrait" );
                Log.e( "====", "用户名===" + uid );
                edit.putString( "uid", uid );
                edit.putString( "content", content );
                edit.putString( "stamp", stamp );
                edit.putString( "cid", cid );
                edit.putString( "portrait", portrait );
                edit.commit();
                //将所得内容添加至集合作为数据源
                list.add( new CmtListInfo( uid, content, stamp, cid, portrait ) );
                AdapterCommit adapter = new AdapterCommit( this );
                adapter.setData( list );
                xLst.setAdapter( adapter );
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        Log.e( "=====", "++++++++" + list.size() );
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.img_commit:

                new HttpUtils().CmtPublish( UriInfo.BaseUrl + UriInfo.PUBLISH_COMMENT, this, requestQueue, mList.get( position ).getNid(), token, ctx );

                break;
        }
    }

    @Override
    public void onRefresh() {
        handler.postDelayed( new Runnable() {
            @Override
            public void run() {
                stop();
            }
        }, 2000 );
    }

    public void stop() {
        xLst.stopLoadMore();
        xLst.stopRefresh();
        SimpleDateFormat format = new SimpleDateFormat( "yyyy-MM-dd HH:mm:ss" );
        String date = format.format( new Date( System.currentTimeMillis() ) );
        xLst.setRefreshTime( date );
    }

    @Override
    public void onLoadMore() {
        new HttpUtils().CmtList( UriInfo.BaseUrl + UriInfo.CMT_REPERT, this, requestQueue, mList.get( position ).getNid(), "2", data );

        handler.postDelayed( new Runnable() {
            @Override
            public void run() {
                stop();
            }
        }, 2000 );
    }

    public String getCnt() {
        cnt += 1;
        return cnt;
    }
}
