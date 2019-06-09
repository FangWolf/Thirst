package com.fangwolf.module_login.ui;

import android.os.Bundle;
import android.view.View;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.alibaba.android.arouter.utils.TextUtils;
import com.fangwolf.library_base.base.BaseActivity;
import com.fangwolf.library_base.router.RouterActivityPath;
import com.fangwolf.library_base.utils.ActivityUtils;
import com.fangwolf.library_base.utils.SPUtils;
import com.fangwolf.library_base.utils.ToastUtils;
import com.fangwolf.module_login.R;
import com.fangwolf.module_login.databinding.LoginActivityRegisterBinding;

import cn.bmob.v3.BmobUser;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.SaveListener;
import cn.bmob.v3.listener.UpdateListener;

/**
 * @Auther 獠牙血狼
 * @Date 2019/3/22
 * @Desc 注册
 */
@Route(path = RouterActivityPath.Login.REGISTER)
public class RegisterActivity extends BaseActivity<LoginActivityRegisterBinding> {
    private boolean isForget;

    @Override
    protected int setLayoutID() {
        return R.layout.login_activity_register;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        setonClickListener(BD.btnBack, BD.btnRegister);
    }

    @Override
    protected void initData(Bundle savedInstanceState) {
        isForget = getIntent().getBooleanExtra("isForget", false);
        if (isForget) {
            gone(BD.llSet);
            visible(BD.llReset);
            BD.tvTitle.setText("重置密码");
            BD.btnRegister.setText("重置");
        }
    }

    @Override
    public void onClick(View view) {
        int i = view.getId();
        if (i == R.id.btn_back) {
            finish();
        } else if (i == R.id.btn_register) {
            if (isForget) {
                resetPassword(BD.etUserName.getText().toString().trim(),
                        BD.etOldPass.getText().toString().trim(),
                        BD.etNewPass.getText().toString().trim());
            } else {
                beforeRegister(BD.etUserName.getText().toString().trim(),
                        BD.etPassWord.getText().toString().trim(),
                        BD.etPassWordCheck.getText().toString().trim());
            }
        }

    }

    private void resetPassword(String userName, String oldPass, String newPass) {
        if (TextUtils.isEmpty(userName) || TextUtils.isEmpty(oldPass) || TextUtils.isEmpty(newPass)) {
            ToastUtils.showShort("请输入完整");
        } else {
            BmobUser user = new BmobUser();
            user.setUsername(userName);
            user.updateCurrentUserPassword(oldPass, newPass, new UpdateListener() {
                @Override
                public void done(BmobException e) {
                    if (e == null) {
                        ToastUtils.showShort("密码重置成功");
                        finish();
                    } else {
                        ToastUtils.showShort(e.getMessage());
                    }
                }
            });
        }
    }

    /**
     * 注册前的检查
     *
     * @param userName
     * @param passWord
     * @param checkPass
     */
    private void beforeRegister(String userName, String passWord, String checkPass) {
        if (TextUtils.isEmpty(userName) || TextUtils.isEmpty(passWord) || TextUtils.isEmpty(checkPass)) {
            ToastUtils.showShort("请输入完整");
        } else if (!passWord.equals(checkPass)) {
            ToastUtils.showShort("密码输入不一致");
        } else {
            Register(userName, passWord);
        }
    }

    /**
     * 注册
     *
     * @param userName
     * @param passWord
     */
    private void Register(String userName, String passWord) {
        BmobUser user = new BmobUser();
        user.setUsername(userName);
        user.setPassword(passWord);
        user.signUp(new SaveListener<BmobUser>() {
            @Override
            public void done(BmobUser bmobUser, BmobException e) {
                afterRegister(bmobUser, e);
            }
        });
    }

    /**
     * 注册后的操作
     *
     * @param bmobUser
     * @param e
     */
    private void afterRegister(BmobUser bmobUser, BmobException e) {
        if (e == null) {
            SPUtils.getInstance().put("SESSION", bmobUser.getSessionToken());
            // TODO: 2019/6/9 注意，新注册的号没有环信！！！ 
            ARouter.getInstance()
                    .build(RouterActivityPath.Main.MAIN)
                    .navigation();
            ActivityUtils.finishAllActivities();
        } else {
            ToastUtils.showShort(e.getMessage());
        }
    }
}
