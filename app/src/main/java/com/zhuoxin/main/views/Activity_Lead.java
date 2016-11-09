package com.zhuoxin.main.views;

import android.content.Intent;
import android.os.Bundle;


import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

import adapter.Adapter_Lead;

/**
 * Created by Administrator on 2016/10/18.
 * 系统引导界面
 */

public class Activity_Lead extends AppCompatActivity {
    ViewPager mVp;
    ImageView mImg[];//四个点
    ImageView mRes[];//viewpager存放图片的位置
    int mImgId[] = {R.mipmap.welcome, R.mipmap.wy, R.mipmap.bd, R.mipmap.small};//图片资源

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_lead );
        //加载组件
        mVp = (ViewPager) findViewById( R.id.viewP_lead );
        //初始化数据源
        mImg = new ImageView[4];
        mRes = new ImageView[4];
        mImg[0] = (ImageView) findViewById( R.id.img_lead1 );
        mImg[1] = (ImageView) findViewById( R.id.img_lead2 );
        mImg[2] = (ImageView) findViewById( R.id.img_lead3 );
        mImg[3] = (ImageView) findViewById( R.id.img_lead4 );
        mImg[0].setImageResource( R.mipmap.lead_selected );
        for (int i = 0; i < 4; i++) {
            //初始化显示图片的组件
            mRes[i] = new ImageView( this );
//            根据下标设置内容
            mRes[i].setImageResource( mImgId[i] );
        }
        //适配器
        Adapter_Lead adapter = new Adapter_Lead( mRes );
        mVp.setAdapter( adapter );
        //刷新适配器
        adapter.notifyDataSetChanged();
        //设置viewpager的滑动事件
        mVp.setOnPageChangeListener( new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
//滑动中
            }

            @Override
            public void onPageSelected(int position) {
//选中时
                for (int i = 0; i < mImg.length; i++) {
//                    初始时设置为未选择状态
                    mImg[i].setImageResource( R.mipmap.lead_default );
                }
                //设置当前所在页面为选中状态
                mImg[position].setImageResource( R.mipmap.lead_selected );
                if (position == 3) {
                    //当处于第四个页面时
                    new Thread( new Runnable() {
                        @Override
                        public void run() {
                            try {
                                //休眠3秒自动跳转至logo界面
                                Thread.sleep( 3000 );
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                            //刷新主线程
                            runOnUiThread( new Runnable() {
                                @Override
                                public void run() {
//                                    跳转
                                    startActivity( new Intent( Activity_Lead.this, Activity_Logo.class ) );

                                    finish();
                                }
                            } );
                        }

                    } ) {
                    }.start();
                }


            }

            @Override
            public void onPageScrollStateChanged(int state) {
//滑动状态改变
            }
        } );
    }

}

