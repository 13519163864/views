package com.zhuoxin.main.views;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

/**
 * Created by Administrator on 2016/10/18.
 */

public class Activity_Logo extends AppCompatActivity {
    ImageView mImg;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mImg = (ImageView) findViewById(R.id.img_main);
        Animation loadAnimation = AnimationUtils.loadAnimation(this,
                R.anim.lode);
        mImg.startAnimation(loadAnimation);
        loadAnimation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                startActivity(new Intent(Activity_Logo.this, Activity_Menu.class));
                overridePendingTransition(R.anim.set, R.anim.exit);
                finish();
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });

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
}
