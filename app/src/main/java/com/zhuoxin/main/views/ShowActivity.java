package com.zhuoxin.main.views;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

/**
 * Created by Administrator on 2016/11/14.
 */

public class ShowActivity extends AppCompatActivity {
    ImageView imageView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.show );

    }

    @Override
    public void onContentChanged() {
        super.onContentChanged();
        imageView = (ImageView) findViewById( R.id.img_show );
        Bitmap bitmap = this.getIntent().getParcelableExtra( "img" );
        imageView.setImageBitmap( bitmap );
    }
}
