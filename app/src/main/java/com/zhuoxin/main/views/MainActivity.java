package com.zhuoxin.main.views;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {
    public static final String PREFS_NAME = "config";
    public static final String IS_FIRST = "first";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SharedPreferences preferences = this.getSharedPreferences(PREFS_NAME, MODE_PRIVATE);
        boolean flag = preferences.getBoolean(IS_FIRST, true);
        if (flag) {
            startActivity(new Intent(MainActivity.this, Activity_Lead.class));
            SharedPreferences.Editor editor = preferences.edit();
            editor.putBoolean(IS_FIRST, false);
            editor.commit();
            this.finish();
        } else {
            startActivity(new Intent(MainActivity.this, Activity_Logo.class));
            this.finish();
        }
    }
}
