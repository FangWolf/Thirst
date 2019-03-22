package com.fangwolf.module_login.functions.login;

import android.os.Bundle;
import android.view.View;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.fangwolf.library_base.base.BaseActivity;
import com.fangwolf.library_base.router.RouterActivityPath;
import com.fangwolf.module_login.R;
import com.fangwolf.module_login.databinding.ActivityRegisterBinding;

/**
 * @Auther 獠牙血狼
 * @Date 2019/3/22
 * @Desc 注册
 */
@Route(path = RouterActivityPath.Login.REGISTER)
public class RegisterActivity extends BaseActivity<ActivityRegisterBinding> {

    @Override
    protected int setLayoutID() {
        return R.layout.activity_register;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        setonClickListener(BD.btnBack, BD.btnRegister);

    }

    @Override
    protected void initData(Bundle savedInstanceState) {

    }

    @Override
    public void onClick(View view) {
        int i = view.getId();
        if (i == R.id.btn_back) {
            finish();
        } else if (i == R.id.btn_register) {
            ARouter.getInstance()
                    .build(RouterActivityPath.Main.MAIN)
                    .navigation();
        }

    }
}
