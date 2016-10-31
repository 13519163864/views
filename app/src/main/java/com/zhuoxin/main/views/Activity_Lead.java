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
 */

public class Activity_Lead extends AppCompatActivity {
    ViewPager mVp;
    ImageView mImg[];//四个点
    ImageView mRes[];
    int mImgId[] = {R.mipmap.welcome, R.mipmap.wy, R.mipmap.bd, R.mipmap.small};

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_lead );
        mVp = (ViewPager) findViewById( R.id.viewP_lead );
        mImg = new ImageView[4];
        mRes = new ImageView[4];
        mImg[0] = (ImageView) findViewById( R.id.img_lead1 );
        mImg[1] = (ImageView) findViewById( R.id.img_lead2 );
        mImg[2] = (ImageView) findViewById( R.id.img_lead3 );
        mImg[3] = (ImageView) findViewById( R.id.img_lead4 );
        mImg[0].setImageResource( R.mipmap.lead_selected );
        for (int i = 0; i < 4; i++) {
            mRes[i] = new ImageView( this );
            mRes[i].setImageResource( mImgId[i] );
        }
        Adapter_Lead adapter = new Adapter_Lead( mRes );
        mVp.setAdapter( adapter );
        adapter.notifyDataSetChanged();
        mVp.setOnPageChangeListener( new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {

                for (int i = 0; i < mImg.length; i++) {
                    mImg[i].setImageResource( R.mipmap.lead_default );
                }
                mImg[position].setImageResource( R.mipmap.lead_selected );
                if (position == 3) {
                    new Thread( new Runnable() {
                        @Override
                        public void run() {
                            try {
                                Thread.sleep( 3000 );
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                            runOnUiThread( new Runnable() {
                                @Override
                                public void run() {
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

            }
        } );
    }

}

