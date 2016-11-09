package com.zhuoxin.main.views;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

/**
 * 程序主入口,判断是否第一次进入
 */

public class MainActivity extends AppCompatActivity {
    //定义保存数据的文件名,保存在data/data/xxxx/shar_per目录下
    public static final String PREFS_NAME = "config";
    //定义静态常量判断是否第一次进入
    public static final String IS_FIRST = "first";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        //获得SharedPreferences对象
        SharedPreferences preferences = this.getSharedPreferences( PREFS_NAME, MODE_PRIVATE );
        //默认为第一次进入
        boolean flag = preferences.getBoolean( IS_FIRST, true );
        if (flag) {
            //第一次进入跳转至引导界面
            startActivity( new Intent( MainActivity.this, Activity_Lead.class ) );
            //获得编辑器对象
            SharedPreferences.Editor editor = preferences.edit();
            //改变状态

            editor.putBoolean( IS_FIRST, false );
//            提交编辑器,此时文件开始创建
            editor.commit();
            this.finish();
        } else {
            //不是第一次进入则跳转至logo界面
            startActivity( new Intent( MainActivity.this, Activity_Logo.class ) );
            this.finish();
        }
    }
}
