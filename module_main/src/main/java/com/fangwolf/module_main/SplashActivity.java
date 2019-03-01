package com.fangwolf.module_main;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.alibaba.android.arouter.launcher.ARouter;
import com.fangwolf.library_base.base.BaseActivity;
import com.fangwolf.library_base.router.RouterActivityPath;
import com.fangwolf.module_main.R;

/**
 * @Auther 獠牙血狼
 * @Date 2019/3/1
 * @Desc 启动页
 */
public class SplashActivity extends BaseActivity {
    Button btnStart;

    @Override
    protected int setLayoutID() {
        return R.layout.main_activity_splash;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        btnStart = findViewById(R.id.btn_test);
        setonClickListener(btnStart);

    }

    @Override
    protected void initData(Bundle savedInstanceState) {

    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.btn_test) {
            ARouter.getInstance().build(RouterActivityPath.Login.PAGE_LOGIN).navigation();
        }

    }
}
