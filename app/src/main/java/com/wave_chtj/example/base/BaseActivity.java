package com.wave_chtj.example.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.face_chtj.base_iotutils.KLog;
import com.wave_chtj.example.util.AppManager;


/**
 * Create on 2019/10/12
 * author chtj
 * desc $
 */
public class BaseActivity extends AppCompatActivity {
    private static final String TAG="BaseActivity";
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //将继承BaseActivity的Activity添加到堆栈 统一管理
        AppManager.getAppManager().addActivity(this);
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        KLog.d(TAG,"onDestroy");
    }
}
