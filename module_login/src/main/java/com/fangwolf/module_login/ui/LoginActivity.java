package com.fangwolf.module_login.ui;

import android.os.Bundle;
import android.view.View;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.alibaba.android.arouter.utils.TextUtils;
import com.fangwolf.library_base.base.BaseActivity;
import com.fangwolf.library_base.router.RouterActivityPath;
import com.fangwolf.library_base.utils.SPUtils;
import com.fangwolf.library_base.utils.ToastUtils;
import com.fangwolf.module_login.R;
import com.fangwolf.module_login.databinding.LoginActivityLoginBinding;
import com.hyphenate.EMCallBack;
import com.hyphenate.chat.EMClient;
import com.orhanobut.logger.Logger;

import cn.bmob.v3.BmobUser;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.SaveListener;
import cn.bmob.v3.listener.UpdateListener;

/**
 * @Auther 獠牙血狼
 * @Date 2019/3/1
 * @Desc 登录
 */
@Route(path = RouterActivityPath.Login.LOGIN)
public class LoginActivity extends BaseActivity<LoginActivityLoginBinding> {
    private String user = "";
    private String pass = "";

    @Override
    protected int setLayoutID() {
        return R.layout.login_activity_login;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        setonClickListener(BD.btnBack, BD.btnRegister, BD.btnLogin, BD.btnForget);
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
                    .withBoolean("isForget", false)
                    .navigation();
        } else if (i == R.id.btn_login) {
            beforeLogin(BD.etUserName.getText().toString().trim(), BD.etPassWord.getText().toString().trim());
        } else if (i == R.id.btn_forget) {
            ARouter.getInstance()
                    .build(RouterActivityPath.Login.REGISTER)
                    .withBoolean("isForget", true)
                    .navigation();
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
        showLoading();
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
        dismissLoading();
        switch (BD.etUserName.getText().toString().trim()) {
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
        if (e == null) {
            SPUtils.getInstance().put("SESSION", bmobUser.getSessionToken());
            SPUtils.getInstance().put("USER_NAME", BD.etUserName.getText().toString().trim());
            ARouter.getInstance()
                    .build(RouterActivityPath.Main.MAIN)
                    .navigation();
            finish();
        } else {
            ToastUtils.showShort(e.getMessage());
        }
    }
}
