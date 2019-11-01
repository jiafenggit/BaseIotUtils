package com.wave_chtj.example;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.Window;
import android.view.WindowManager;

import com.chtj.base_iotutils.bus.RxBus;
import com.chtj.base_iotutils.keepservice.BaseIotUtils;
import com.chtj.base_iotutils.screen_adapta.activitylifecycle.SCREEN_TYPE;
import com.wave_chtj.example.base.BaseActivity;
import com.wave_chtj.example.entitiy.InitFinish;
import com.wave_chtj.example.entitiy.InitReady;

import io.reactivex.functions.Consumer;

/**
 * Create on 2019/10/16
 * author chtj
 * desc $
 */
public class StartPageAty extends BaseActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);              //去掉TITLE
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);            //设为全屏

        startActivity(new Intent(StartPageAty.this,FeaturesOptionAty.class));
        finish();
    }
}