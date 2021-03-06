package com.wave_chtj.example.keepservice;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.face_chtj.base_iotutils.KLog;
import com.face_chtj.base_iotutils.keeplive.BaseIotUtils;
import com.face_chtj.base_iotutils.keeplive.IntentWrapper;
import com.face_chtj.base_iotutils.notify.OnNotifyLinstener;
import com.face_chtj.base_iotutils.notify.NotifyUtils;
import com.wave_chtj.example.R;
import com.wave_chtj.example.base.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class KeepServiceActivity extends BaseActivity {
    private static final String TAG="KeepServiceActivity";
    @BindView(R.id.btn_start)
    Button btnStart;
    @BindView(R.id.btn_white)
    Button btnWhite;
    @BindView(R.id.btn_stop)
    Button btnStop;

    @Override
    protected void onCreate(Bundle b) {
        super.onCreate(b);
        setContentView(R.layout.activity_keep_service);
        ButterKnife.bind(this);
        //①初始化后台保活Service
        BaseIotUtils.initSerice(TraceServiceImpl.class, BaseIotUtils.DEFAULT_WAKE_UP_INTERVAL);
    }


    //④防止华为机型未加入白名单时按返回键回到桌面再锁屏后几秒钟进程被杀
    public void onBackPressed() {
        IntentWrapper.onBackPressed(this);
    }

    @OnClick({R.id.btn_start, R.id.btn_white, R.id.btn_stop})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            //②开启服务
            case R.id.btn_start:
                TraceServiceImpl.sShouldStopService = false;
                BaseIotUtils.startServiceMayBind(TraceServiceImpl.class);
                NotifyUtils.getInstance("10")
                        .setOnNotifyLinstener(new OnNotifyLinstener() {
                            @Override
                            public void enableStatus(boolean isEnable) {
                                KLog.e(TAG,"isEnable="+isEnable);
                            }
                        })
                        .setNotifyParam(R.drawable.ic_launcher,R.drawable.app_img
                                ,"BaseIotUtils"
                                ,"about"
                                ,"a baseiotutils:serialPort,Rxbus,DownloadManager....!"
                                ,"oh my god!"
                                ,"2020-3-18"
                                ,false
                                ,true)
                        .exeuNotify();
                break;
            //处理白名单
            case R.id.btn_white:
                IntentWrapper.whiteListMatters(this, "轨迹跟踪服务的持续运行");
                break;
            //③关闭服务
            case R.id.btn_stop:
                TraceServiceImpl.stopService();
                NotifyUtils.getInstance("10").closeNotify();
                break;
        }
    }
}
