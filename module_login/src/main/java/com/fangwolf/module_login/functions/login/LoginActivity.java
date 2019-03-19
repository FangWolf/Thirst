package com.fangwolf.module_login.functions.login;

import android.os.Bundle;
import android.view.View;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.fangwolf.library_base.base.BaseActivity;
import com.fangwolf.library_base.router.RouterActivityPath;
import com.fangwolf.module_login.R;
import com.fangwolf.module_login.databinding.LoginActivityLoginBinding;

/**
 * @Auther 獠牙血狼
 * @Date 2019/3/1
 * @Desc 登录
 */
@Route(path = RouterActivityPath.Login.LOGIN)
public class LoginActivity extends BaseActivity<LoginActivityLoginBinding> {

    @Override
    protected int setLayoutID() {
        return R.layout.login_activity_login;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        setonClickListener(BD.btnBack, BD.btnRegister, BD.btnLogin);
    }

    @Override
    protected void initData(Bundle savedInstanceState) {

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_back:
                finish();
                break;
            case R.id.btn_register:
                break;
            case R.id.btn_login:
                break;
        }
    }
}
