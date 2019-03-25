package com.fangwolf.module_login.functions.login;

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
import com.fangwolf.module_login.databinding.ActivityRegisterBinding;

import cn.bmob.v3.BmobUser;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.SaveListener;

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
            beforeRegister(BD.etUserName.getText().toString().trim(),
                    BD.etPassWord.getText().toString().trim(),
                    BD.etPassWordCheck.getText().toString().trim());
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
            ARouter.getInstance()
                    .build(RouterActivityPath.Main.MAIN)
                    .navigation();
            SPUtils.getInstance().put("SESSION", bmobUser.getSessionToken());
        } else {
            ToastUtils.showShort(e.getMessage());
        }
    }
}
