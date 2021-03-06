package com.wave_chtj.example.network;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.face_chtj.base_iotutils.KLog;
import com.face_chtj.base_iotutils.network.NetListenerUtils;
import com.face_chtj.base_iotutils.network.NetTypeInfo;
import com.face_chtj.base_iotutils.network.OnNetChangeLinstener;
import com.wave_chtj.example.R;

/**
 * Create on 2020/1/3
 * author chtj
 * desc 网络检测
 */
public class NetChangeAty extends AppCompatActivity {
    private static final String TAG = "NetChangeAty";
    private TextView tvStatus;
    private TextView tvType;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_network);

        tvStatus = findViewById(R.id.tvStatus);
        tvType = findViewById(R.id.tvType);

    }

    //开始监听
    public void startLinstener(View view) {
        NetListenerUtils.getInstance().registerReceiver();
        NetListenerUtils.getInstance().setOnNetChangeLinstener(new OnNetChangeLinstener() {
            @Override
            public void changed(NetTypeInfo type, boolean isNormal) {
                KLog.e(TAG, "network type=" + type.name() + ",isNormal=" + isNormal);
                tvType.setText("" + type.name());
                tvStatus.setText("" + isNormal);
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        NetListenerUtils.getInstance().unRegisterReceiver();
    }
}
