package com.zhuoxin.main.views;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import entry.CmtListInfo;
import entry.Source;
import entry.UriInfo;
import fragment.CenterFragment;
import interFace.OnLoadResponseListener;
import utils.HttpUtils;

/**
 * Created by Administrator on 2016/11/8.
 * 显示评论界面
 */

public class Activity_Commit extends AppCompatActivity implements OnLoadResponseListener {
    static ArrayList<CmtListInfo> list = new ArrayList<>();
    ArrayList<Source> mList = new ArrayList<>();
    int position;
    RequestQueue requestQueue;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        Intent intent = getIntent();
        position = intent.getIntExtra( "position", -1 );

        mList = new CenterFragment().getList();
//
        requestQueue = Volley.newRequestQueue( this );
        SharedPreferences cmtList = this.getSharedPreferences( "cmtList", MODE_PRIVATE );
        String data = cmtList.getString( "data", null );
        Log.e( "======", "评论内容数据源====" + mList.size() + "下标=====" + position + "data====" + data );

        new HttpUtils().CmtList( UriInfo.BaseUrl + UriInfo.CMT_REPERT, this, requestQueue, mList.get( position ).getNid(), data );

//        SharedPreferences cmtInfo = this.getSharedPreferences( "cmtInfo", MODE_PRIVATE );
//        String stamp = cmtInfo.getString( "stamp", null );
//        Log.e( "====", "显示评论" + stamp );

    }

    @Override
    public void getResponse(String succuful) {

    }

    @Override
    public void getCmtList(String data) {
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
                list.add( new CmtListInfo( uid, content, stamp, cid, portrait ) );
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        Log.e( "=====", "++++++++" + list.size() );
    }
}
