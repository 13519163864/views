package com.zhuoxin.main.views;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.Window;

import view.ActionBarView;

/**
 * Created by Administrator on 2016/11/1.
 */

public class BaseActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        getWindow().requestFeature( Window.FEATURE_NO_TITLE );
    }

    public void initActinBar(String content) {
        ActionBarView actionBarView = (ActionBarView) findViewById( R.id.action );
        actionBarView.setListener( content );

    }
}
