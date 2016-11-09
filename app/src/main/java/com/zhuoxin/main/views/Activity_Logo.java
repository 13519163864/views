package com.zhuoxin.main.views;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.TypedValue;
import android.view.View;
import android.widget.TextView;

import com.special.animatedrandomlayout.activity.ColorUtil;
import com.special.animatedrandomlayout.activity.ToastUtil;
import com.special.animatedrandomlayout.random_layout.AnimatedRandomLayout;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

/**
 * Created by Administrator on 2016/10/18.
 * 程序logo界面
 */

public class Activity_Logo extends AppCompatActivity {
    //    FrameLayout mFly;
//    ArrayList<String> mList = new ArrayList<>();
//    StellarMap stellarMap;
    List<String> list;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_main );
        /*
        * 导入第三方jar包AnimatedRandomLayout
        * 随机的显示用户自定义的内容,大小颜色位置都是随机的
        *
        *
        * */
        //耗时操作在子线程中操作
        new Thread( new Runnable() {
            @Override
            public void run() {
                //获取AnimatedRandomLayout组件
                AnimatedRandomLayout layout = (AnimatedRandomLayout) findViewById( R.id.animation );
                //定义随机显示内容
                String[] str = {"你是不是感觉自己萌萌哒", "你是不是喜欢我啊", "明星八卦", "古今奇谈", "野史怪谈", "家长里短", "感觉不会再爱了", "蓝瘦香菇", "喜洋洋爱上了灰太狼", "红太狼和村长私奔了", "超人大战奥特曼", "你能把我怎样", "我就喜欢看你看不惯我又拿我没办法的逼样"};
                list = new ArrayList<>();
//                将数组放入集合
                list = Arrays.asList( str );

//设置循环时间,也就是每次显示的时间间隔
                layout.setLooperDuration( 50 );
                //设置动画显示的最大时间
                layout.setDefaultDruation( 5000 );
                //设置每次显示的数量
                layout.setItemShowCount( 2 );
//        layout.setRegularity(15, 15);


                layout.setOnCreateItemViewListener( new AnimatedRandomLayout.OnCreateItemViewListener() {
                    @Override
                    public int getCount() {
                        return list.size();
                    }

                    @Override
                    public View createItemView(int position, View convertView) {
                        final TextView textView = new TextView( getApplicationContext() );
                        //1.设置文本数据
                        int listPosition = position;
                        textView.setText( list.get( listPosition ) + "" );
                        //2.设置随机的字体
                        Random random = new Random();
                        textView.setTextSize( TypedValue.COMPLEX_UNIT_SP, random.nextInt( 8 ) + 24 );//14-21
                        //3.上色，设置随机字体颜色
                        textView.setTextColor( ColorUtil.randomColor() );
                        //4.设置点击事件
                        textView.setOnClickListener( new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                ToastUtil.showToast( getApplicationContext(), textView.getText().toString() );
                            }
                        } );

                        return textView;
                    }
                } );
//开启动画布局
                layout.start();
                try {
//                    开启后设置系统休眠5秒,也就是动画显示时间
                    Thread.sleep( 5000 );
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                runOnUiThread( new Runnable() {
                    @Override
                    public void run() {
                        //动画即logo界面显示完毕之后跳转至系统的主界面
                        startActivity( new Intent( Activity_Logo.this, Activity_Menu.class ) );
                        //加载动画
                        overridePendingTransition( R.anim.set, R.anim.exit );
                        finish();
                    }
                } );
            }
        } ).start();


//        mFly = (FrameLayout) findViewById( R.id.activity_main );
//        for (int i = 0; i < 3; i++) {
//            mList.add( "新闻列表" );
//            mList.add( "家国天下" );
//            mList.add( "娱乐八卦" );
//            mList.add( "明星轶事" );
//            mList.add( "古今奇谈" );
//            mList.add( "家长里短" );
//            mList.add( "乱七八糟" );
//            mList.add( "罗里吧嗦" );
//            mList.add( "野史怪诞" );
//            mList.add( "我爱天宇" );
//            mList.add( "尽在新闻列表" );
//            mList.add( "军事" );
//            mList.add( "喜洋洋大战光头强" );
//            mList.add( "灰太狼请熊大熊二吃蜂蜜" );
//        }
//        stellarMap = new StellarMap( this );
//        // 1.设置内部的TextView距离四周的内边距
//        int padding = 15;
//        new Thread( new Runnable() {
//            @Override
//            public void run() {
//
//
//
//
//                stellarMap.setAdapter( new StellarMapAdapter() );
//                // 设置默认显示第几组的数据
//                stellarMap.setGroup( 0, true );// 这里默认显示第0组
//                // 设置x和y方向上的显示的密度
//                stellarMap.setRegularity( 15, 15 );// 如果值设置的过大，有可能造成子View摆放比较稀疏
//
//                // 把fragment显示至界面,new出fragment对象
//                mFly.addView( stellarMap );
//                try {
//
//                    Thread.sleep( 3000 );
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//                runOnUiThread( new Runnable() {
//                    @Override
//                    public void run() {
//                    startActivity( new Intent( Activity_Logo.this,Activity_Menu.class ) );
//                        overridePendingTransition( R.anim.set,R.anim.exit );
//                        finish();
//                    }
//                } );
//            }
//        } ).start();
//


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

//    class StellarMapAdapter implements StellarMap.Adapter {
//        /**
//         * 返回多少组数据
//         */
//        @Override
//        public int getGroupCount() {
//            return 3;
//        }
//
//        /**
//         * 每组多少个数据
//         */
//        @Override
//        public int getCount(int group) {
//            return 14;
//        }
//
//        /**
//         * group: 当前是第几组 position:是当前组的position
//         */
//        @Override
//        public View getView(int group, int position, View convertView) {
//            final TextView textView = new TextView( Activity_Logo.this );
//            // 根据group和组中的position计算出对应的在list中的位置
//            int listPosition = group * getCount( group ) + position;
//            textView.setText( mList.get( listPosition ) );
//
//            // 1.设置随机的字体大小(随机大小)
//            Random random = new Random();
//            textView.setTextSize( TypedValue.COMPLEX_UNIT_SP,
//                    random.nextInt( 15 ) + 14 );// 14-29
//            // 2.上色，设置随机的字体颜色
//            // 如果三原色的值过大会偏白色，过小会偏黑色，所以应该随机一个中间的颜色的值
//            int red = random.nextInt( 150 ) + 50;// 50-199
//            int green = random.nextInt( 150 ) + 50;// 50-199
//            int blue = random.nextInt( 150 ) + 50;// 50-199
//            int textColor = Color.rgb( red, green, blue );// 在rgb三原色的基础上混合出一种新的颜色
//            textView.setTextColor( textColor );
//            return textView;
//        }

//        /**
//         * 虽然定义了，但是并没有什么乱用
//         */
//        @Override
//        public int getNextGroupOnPan(int group, float degree) {
//            return 0;
//        }
//
//        /**
//         * 当前组缩放完成之后下一组加载哪一组的数据 group： 表示当前是第几组
//         */
//        @Override
//        public int getNextGroupOnZoom(int group, boolean isZoomIn) {
//            // 0->1->2->0
//            return (group + 1) % getGroupCount();
//        }
//    }
}
