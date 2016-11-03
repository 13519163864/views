package com.zhuoxin.main.views;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.TypedValue;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.shitou.googleplay.lib.randomlayout.StellarMap;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by Administrator on 2016/10/18.
 */

public class Activity_Logo extends AppCompatActivity {
    FrameLayout mFly;
    ArrayList<String> mList = new ArrayList<>();
    StellarMap stellarMap;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_main );
        mFly = (FrameLayout) findViewById( R.id.activity_main );
        for (int i = 0; i < 3; i++) {
            mList.add( "新闻列表" );
            mList.add( "家国天下" );
            mList.add( "娱乐八卦" );
            mList.add( "明星轶事" );
            mList.add( "古今奇谈" );
            mList.add( "家长里短" );
            mList.add( "乱七八糟" );
            mList.add( "罗里吧嗦" );
            mList.add( "野史怪诞" );
            mList.add( "我爱天宇" );
            mList.add( "尽在新闻列表" );
            mList.add( "军事" );
            mList.add( "喜洋洋大战光头强" );
            mList.add( "灰太狼请熊大熊二吃蜂蜜" );
        }
        stellarMap = new StellarMap( this );
        // 1.设置内部的TextView距离四周的内边距
        int padding = 15;
        new Thread( new Runnable() {
            @Override
            public void run() {




                stellarMap.setAdapter( new StellarMapAdapter() );
                // 设置默认显示第几组的数据
                stellarMap.setGroup( 0, true );// 这里默认显示第0组
                // 设置x和y方向上的显示的密度
                stellarMap.setRegularity( 15, 15 );// 如果值设置的过大，有可能造成子View摆放比较稀疏

                // 把fragment显示至界面,new出fragment对象
                mFly.addView( stellarMap );
                try {

                    Thread.sleep( 3000 );
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                runOnUiThread( new Runnable() {
                    @Override
                    public void run() {
                    startActivity( new Intent( Activity_Logo.this,Activity_Menu.class ) );
                        overridePendingTransition( R.anim.set,R.anim.exit );
                        finish();
                    }
                } );
            }
        } ).start();



//        mImg = (ImageView) findViewById( R.id.img_main );
//        Animation loadAnimation = AnimationUtils.loadAnimation( this,
//                R.anim.alpha );
//        mImg.startAnimation( loadAnimation );
//        loadAnimation.setAnimationListener( new Animation.AnimationListener() {
//            @Override
//            public void onAnimationStart(Animation animation) {
//
//            }
//
//            @Override
//            public void onAnimationEnd(Animation animation) {
//                startActivity( new Intent( Activity_Logo.this, Activity_Menu.class ) );
//                overridePendingTransition( R.anim.set, R.anim.exit );
//                finish();
//            }
//
//            @Override
//            public void onAnimationRepeat(Animation animation) {
//
//            }
//        } );


//
//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                try {
//                    Thread.sleep(3000);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//                runOnUiThread(new Runnable() {
//                    @Override
//                    public void run() {
//
//                    }
//                });
//            }
//        }) {
//        }.start();
    }

    class StellarMapAdapter implements StellarMap.Adapter {
        /**
         * 返回多少组数据
         */
        @Override
        public int getGroupCount() {
            return 3;
        }

        /**
         * 每组多少个数据
         */
        @Override
        public int getCount(int group) {
            return 14;
        }

        /**
         * group: 当前是第几组 position:是当前组的position
         */
        @Override
        public View getView(int group, int position, View convertView) {
            final TextView textView = new TextView( Activity_Logo.this );
            // 根据group和组中的position计算出对应的在list中的位置
            int listPosition = group * getCount( group ) + position;
            textView.setText( mList.get( listPosition ) );

            // 1.设置随机的字体大小(随机大小)
            Random random = new Random();
            textView.setTextSize( TypedValue.COMPLEX_UNIT_SP,
                    random.nextInt( 15 ) + 14 );// 14-29
            // 2.上色，设置随机的字体颜色
            // 如果三原色的值过大会偏白色，过小会偏黑色，所以应该随机一个中间的颜色的值
            int red = random.nextInt( 150 ) + 50;// 50-199
            int green = random.nextInt( 150 ) + 50;// 50-199
            int blue = random.nextInt( 150 ) + 50;// 50-199
            int textColor = Color.rgb( red, green, blue );// 在rgb三原色的基础上混合出一种新的颜色
            textView.setTextColor( textColor );
            return textView;
        }

        /**
         * 虽然定义了，但是并没有什么乱用
         */
        @Override
        public int getNextGroupOnPan(int group, float degree) {
            return 0;
        }

        /**
         * 当前组缩放完成之后下一组加载哪一组的数据 group： 表示当前是第几组
         */
        @Override
        public int getNextGroupOnZoom(int group, boolean isZoomIn) {
            // 0->1->2->0
            return (group + 1) % getGroupCount();
        }
    }
}
