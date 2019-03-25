package com.fangwolf.module_login.functions.login;

import android.os.Bundle;
import android.view.View;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.alibaba.android.arouter.utils.TextUtils;
import com.fangwolf.library_base.base.BaseActivity;
import com.fangwolf.library_base.router.RouterActivityPath;
import com.fangwolf.library_base.utils.ToastUtils;
import com.fangwolf.library_base.widget.Loading;
import com.fangwolf.module_login.R;
import com.fangwolf.module_login.databinding.LoginActivityLoginBinding;
import com.google.android.material.snackbar.Snackbar;

import java.util.List;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.BmobUser;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;
import cn.bmob.v3.listener.SaveListener;

/**
 * @Auther 獠牙血狼
 * @Date 2019/3/1
 * @Desc 登录
 */
@Route(path = RouterActivityPath.Login.LOGIN)
public class LoginActivity extends BaseActivity<LoginActivityLoginBinding> {
    private Loading loading;

    @Override
    protected int setLayoutID() {
        return R.layout.login_activity_login;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        setonClickListener(BD.btnBack, BD.btnRegister, BD.btnLogin, BD.btnForget);
        loading = new Loading(this);
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
                    .build(RouterActivityPath.Login.REGISTER)
                    .navigation();
        } else if (i == R.id.btn_login) {
            beforeLogin(BD.etUserName.getText().toString().trim(), BD.etPassWord.getText().toString().trim());
        } else if (i == R.id.btn_forget) {

        }
    }

    private void beforeLogin(String userName, String passWord) {
        if (TextUtils.isEmpty(userName) || TextUtils.isEmpty(passWord)) {
            ToastUtils.showShort("请输入完整");
        } else {
            Login(userName, passWord);
        }
    }

    private void Login(String userName, String passWord) {
        loading.show();
        BmobUser user = new BmobUser();
        user.setUsername(userName);
        user.setPassword(passWord);
        user.login(new SaveListener<BmobUser>() {
            @Override
            public void done(BmobUser bmobUser, BmobException e) {
                afterLogin(bmobUser, e);
            }
        });
    }

    private void afterLogin(BmobUser bmobUser, BmobException e) {
        loading.dismiss();
        if (e == null) {
            ARouter.getInstance()
                    .build(RouterActivityPath.Main.MAIN)
                    .navigation();
            finish();
        } else {
            ToastUtils.showShort(e.getMessage());
        }
    }
}
