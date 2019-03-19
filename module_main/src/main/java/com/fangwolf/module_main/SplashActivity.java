package com.fangwolf.module_main;

import android.os.Bundle;
import android.view.View;

import com.fangwolf.library_base.base.BaseActivity;
import com.fangwolf.library_base.widget.RxTimer;
import com.fangwolf.module_main.databinding.MainActivitySplashBinding;

/**
 * @Auther 獠牙血狼
 * @Date 2019/3/1
 * @Desc 启动页
 */
public class SplashActivity extends BaseActivity<MainActivitySplashBinding> {
    private RxTimer rxTimer;

    @Override
    protected int setLayoutID() {
        return R.layout.main_activity_splash;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
    }

    @Override
    protected void initData(Bundle savedInstanceState) {
        rxTimer.bindAct(this);
        rxTimer = new RxTimer(3, 1, new RxTimer.TimerCall() {
            @Override
            public void onTick(long t) {
                BD.tvTime.setText(String.valueOf(t));
            }

            @Override
            public void onFinish() {

            }
        });
        rxTimer.start();
    }

    @Override
    public void onClick(View view) {

    }
}
