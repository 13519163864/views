package com.zhuoxin.main.views;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import adapter.Adapter_loginLog;
import entry.LoginLog;
import entry.UriInfo;
import fragment.CenterFragment;
import interFace.OnLoadResponseListener;
import utils.HttpUtils;

/**
 * Created by Administrator on 2016/11/4.
 */

public class Activity_UserCenter extends AppCompatActivity implements OnLoadResponseListener, View.OnClickListener {
    RequestQueue requestQueue;
    static ArrayList<LoginLog> mList = new ArrayList<>();
    ImageView mIcon;
    TextView mName;
    TextView mInte;
    TextView mComnum;
    ListView mLst;
    int integration;
    String uid;
    String portrait;
    int comnum;
    ImageView mBack;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.user_center );


        initView();
        SharedPreferences signIn = this.getSharedPreferences( "signIn", MODE_PRIVATE );
        String token = signIn.getString( "token", null );
        Log.e( "=======", "用户中心token" + token );
        requestQueue = Volley.newRequestQueue( this );
        new HttpUtils().UserCenter( UriInfo.BaseUrl + UriInfo.UserCenter, this, requestQueue, token );


    }

    public void initView() {
        mIcon = (ImageView) findViewById( R.id.img_user_icon );
        mName = (TextView) findViewById( R.id.txt_user_center_userName );
        mInte = (TextView) findViewById( R.id.txt_user_center_jifen );
        mComnum = (TextView) findViewById( R.id.txt_user_center_tongji );
        mLst = (ListView) findViewById( R.id.lst_user_center_log );
        mBack = (ImageView) findViewById( R.id.img_user_center_back );
        mBack.setOnClickListener( this );
    }

    SharedPreferences userInfo;

    @Override
    public void getResponse(String succuful) {
        Log.e( "===", "UserCenter----" + succuful );
        try {
            userInfo = this.getSharedPreferences( "UserInfo", MODE_PRIVATE );
            SharedPreferences.Editor edit = userInfo.edit();
            JSONObject jsonObject = new JSONObject( succuful );
            String message = jsonObject.getString( "message" );
            edit.putString( "message", message );
            Log.e( "===", "message" + message );
            int status = jsonObject.getInt( "status" );
            edit.putInt( "status", status );
            Log.e( "===", "status" + status );

            JSONObject object = jsonObject.optJSONObject( "data" );

            uid = object.getString( "uid" );
            edit.putString( "uid", uid );
            Log.e( "===", "uid" + uid );

            integration = object.getInt( "integration" );
            Log.e( "===", "integration" + integration );
            edit.putInt( "integration", integration );
            JSONArray loginlog = object.getJSONArray( "loginlog" );
            Log.e( "===", "loginlog" + loginlog );
            for (int i = 0; i < loginlog.length(); i++) {
                JSONObject loginlogJSONObject = loginlog.getJSONObject( i );
                String address = loginlogJSONObject.getString( "address" );
                int device = loginlogJSONObject.getInt( "device" );
                String time = loginlogJSONObject.getString( "time" );
                mList.add( new LoginLog( time, address, device ) );
            }
            Log.e( "====", "mlist===++" + mList );
            comnum = object.getInt( "comnum" );
            edit.putInt( "comnum", comnum );
            Log.e( "===", "comnum" + comnum );

            portrait = object.getString( "portrait" );
            edit.putString( "portrait", portrait );
            edit.commit();
            Log.e( "===", "portrait" + portrait );
            Glide.with( this ).load( portrait ).into( mIcon );
            mName.setText( uid );
            Adapter_loginLog adapter = new Adapter_loginLog( mList, this );
            mLst.setAdapter( adapter );

            mInte.setText( "积分: " + integration + "" );
            mComnum.setText( "跟帖数统计: " + comnum + "" );
//            Log.e( "=======","==="+message+"-_"+status+"-------"+uid+"--------"+integration+"--------"+loginlog+"--------"+comnum+"--------"+portrait );

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.img_user_center_back:
                FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                transaction.replace( R.id.framlayout_main, new CenterFragment() );
                transaction.commit();
                break;
        }
    }
}
