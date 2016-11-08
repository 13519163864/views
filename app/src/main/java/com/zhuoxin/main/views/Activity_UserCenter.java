package com.zhuoxin.main.views;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
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

import java.io.File;
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
        mIcon.setOnClickListener( this );
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
            edit.commit();
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

    File file;

    @Override
    public void onClick(View view) {

        switch (view.getId()) {
            case R.id.img_user_center_back:
                final FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                transaction.replace( R.id.framlayout_main, new CenterFragment() );
                transaction.commit();
                break;
            case R.id.img_user_icon:
                AlertDialog.Builder builder = new AlertDialog.Builder( this );
                builder.setTitle( "请选择操作" );
                final View inflate = LayoutInflater.from( this ).inflate( R.layout.dialog, null );
                TextView camera = (TextView) inflate.findViewById( R.id.txt_gialog_camera );
                TextView pic = (TextView) inflate.findViewById( R.id.txt_gialog_pic );
                camera.setOnClickListener( new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
//调系统相机拍照
                        //拍照意图
                        Intent intent = new Intent( MediaStore.ACTION_IMAGE_CAPTURE );
                        //获取保存照片路径
                        String path = Environment.getExternalStorageDirectory().getPath();
                        //文件
                        file = new File( path + File.separator + System.currentTimeMillis() + ".jpg" );
                        //设置保存路径
                        intent.putExtra( MediaStore.EXTRA_OUTPUT, Uri.fromFile( file ) );
                        startActivityForResult( intent, 1 );
                    }
                } );
                pic.setOnClickListener( new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent( Intent.ACTION_PICK );
                        //设置类型
                        intent.setType( "image/*" );
                        startActivityForResult( intent, 2 );

                    }
                } );

                builder.setView( inflate );
                builder.show();
                break;
        }
    }

    //使用意图剪切照片剪切从图库中获取的照片
    public void crop(Uri uri) {
        Intent intent = new Intent();
        //设置要剪切的资源文件及类型
        intent.setDataAndType( uri, "image/*" );
        //设置剪切
        intent.setAction( "com.android.camera.action.CROP" );
        //开启剪切
        intent.putExtra( "crop", "true" );
        //设置裁剪框比例
        intent.putExtra( "aspectX", 1 );
        intent.putExtra( "aspectY", 1 );
        //设置裁剪后输出的照片大小
        intent.putExtra( "outputX", 200 );
        intent.putExtra( "outputY", 200 );
        //设置剪切圆形图片
        intent.putExtra( "circleCrop","true" );
        //设置返回数据
        intent.putExtra( "return-data", true );
        startActivityForResult( intent, 3 );
    }

    public void cropFromCamera(File file) {
        Intent intent = new Intent();
        //设置要剪切的资源文件及类型
        intent.setDataAndType( Uri.fromFile( file ), "image/*" );
        //设置剪切
        intent.setAction( "com.android.camera.action.CROP" );
        //开启剪切
        intent.putExtra( "crop", "true" );
        //设置裁剪框比例
        intent.putExtra( "aspectX", 1 );
        intent.putExtra( "aspectY", 1 );
        //设置裁剪后输出的照片大小
        intent.putExtra( "outputX", 200 );
        intent.putExtra( "outputY", 200 );
        //设置剪切圆形图片
        intent.putExtra( "circleCrop","true" );
        //设置返回数据
        intent.putExtra( "return-data", true );
        startActivityForResult( intent, 3 );
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult( requestCode, resultCode, data );
        if (resultCode == RESULT_OK) {
            switch (requestCode) {
                case 1:
                    cropFromCamera( file );


                    //接收相机拍照的两种方式
//                    Bitmap bitmap = BitmapFactory.decodeFile( file.getPath() );
//                    Bitmap bitmap = data.getParcelableExtra( "data" );
//                    mIcon.setImageBitmap( bitmap );
                    break;
                case 2:
//                    Bitmap bitmap1 = data.getParcelableExtra( "data" );
//                    mIcon.setImageBitmap( bitmap1 );
                    //通过内容提供者获取
//                    ContentResolver resolver = getContentResolver();
//                    Uri uri = data.getData();
//
//                    String[] array = {MediaStore.Images.Media.DATA};
//
//                    Cursor cursor = resolver.query( uri, array, null, null, null );
//                    cursor.moveToFirst();
//                    String path = cursor.getString( cursor.getColumnIndex( array[0] ) );
//                    cursor.close();
//                    Bitmap bitmap1 = BitmapFactory.decodeFile( path );
//                    mIcon.setImageBitmap( bitmap1 );
                    crop( data.getData() );
                    break;
                case 3:
                    Bitmap bitmap = data.getParcelableExtra( "data" );
                    mIcon.setImageBitmap( bitmap );
                    break;

            }
        }
    }
}
