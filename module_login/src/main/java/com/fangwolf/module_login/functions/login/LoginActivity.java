package com.fangwolf.module_login.functions.login;

import android.os.Bundle;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.fangwolf.library_base.router.RouterActivityPath;
import com.fangwolf.module_login.R;

import androidx.appcompat.app.AppCompatActivity;

/**
 * @Auther 獠牙血狼
 * @Date 2019/3/1
 * @Desc 登录
 */
@Route(path = RouterActivityPath.Login.PAGE_LOGIN)
public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_activity_login);
    }
}
