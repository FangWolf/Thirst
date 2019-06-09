package com.fangwolf.module_main.ui;

import android.os.Bundle;
import android.view.View;

import com.alibaba.android.arouter.launcher.ARouter;
import com.fangwolf.library_base.base.BaseActivity;
import com.fangwolf.library_base.router.RouterActivityPath;
import com.fangwolf.library_base.utils.SPUtils;
import com.fangwolf.library_base.widget.RxTimer;
import com.fangwolf.module_main.R;
import com.fangwolf.module_main.databinding.MainActivitySplashBinding;
import com.hyphenate.EMCallBack;
import com.hyphenate.chat.EMClient;
import com.orhanobut.logger.Logger;

/**
 * @Auther 獠牙血狼
 * @Date 2019/3/1
 * @Desc 启动页
 */
public class SplashActivity extends BaseActivity<MainActivitySplashBinding> {
    private RxTimer rxTimer;
    // TODO: 2019/3/25
    private boolean canAutoLogin = false;
    private String userName = "";
    private String user = "";
    private String pass = "";

    @Override
    protected int setLayoutID() {
        return R.layout.main_activity_splash;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        if (!SPUtils.getInstance().getString("SESSION").isEmpty()) {
            canAutoLogin = true;
            userName = SPUtils.getInstance().getString("USER_NAME");
            switch (userName) {
                case "123456":
                    user = "0001";
                    pass = "123456";
                    break;
                case "654321":
                    user = "0002";
                    pass = "654321";
                    break;
                default:
                    user = "0003";
                    pass = "215216";
                    break;

            }
        }
    }

    @Override
    protected void initData(Bundle savedInstanceState) {
        rxTimer = new RxTimer(1, 3, new RxTimer.TimerCall() {
            @Override
            public void onTick(long t) {
                BD.tvTime.setText(String.valueOf(t));
            }

            @Override
            public void onFinish() {
                needToLogin();
            }
        });
        rxTimer.start();
    }

    private void needToLogin() {
        if (canAutoLogin) {
            // TODO: 2019/4/16 环信账号和用户账号没有绑定
            EMClient.getInstance().login(user, pass, new EMCallBack() {
                @Override
                public void onSuccess() {
                    Logger.e("onSuccess");
                }

                @Override
                public void onError(int i, String s) {
                    Logger.e("onError" + s);
                }

                @Override
                public void onProgress(int i, String s) {
                    Logger.e("onProgress" + s);
                }
            });
            ARouter.getInstance()
                    .build(RouterActivityPath.Main.MAIN)
                    .navigation();
        } else {
            ARouter.getInstance()
                    .build(RouterActivityPath.Login.LOGIN)
                    .navigation();
        }
        finish();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        RxTimer.destoryTimer(rxTimer);
    }

    @Override
    public void onClick(View view) {

    }
}
