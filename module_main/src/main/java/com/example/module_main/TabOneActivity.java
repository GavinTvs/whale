package com.example.module_main;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.example.basic.constant.ActivityPath;

import retrofit2.http.Path;

@Route(path = ActivityPath.NATIVE_MAIN)
public class TabOneActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tab_one);
    }
}
