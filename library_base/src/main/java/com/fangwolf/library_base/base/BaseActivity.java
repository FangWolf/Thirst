package com.fangwolf.library_base.base;

import android.os.Bundle;
import android.view.View;

import com.fangwolf.library_base.widget.Loading;
import com.orhanobut.logger.Logger;

import org.greenrobot.eventbus.EventBus;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;

/**
 * @Auther 獠牙血狼
 * @Date 2019/3/1
 * @Desc 基础activity
 */
public abstract class BaseActivity<BindingType extends ViewDataBinding> extends AppCompatActivity implements View.OnClickListener {
    public BindingType BD;
    private Loading loading;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        int layoutId = setLayoutID();
        if (layoutId != 0) {
            BD = DataBindingUtil.setContentView(this, layoutId);
        } else {
            Logger.e("layout ID is null");
        }
        initView(savedInstanceState);
        initData(savedInstanceState);
    }

    protected abstract int setLayoutID();

    protected abstract void initView(Bundle savedInstanceState);

    protected abstract void initData(Bundle savedInstanceState);

    /**
     * 注册EventBus监听
     */
    public void registerEventBus() {
        if (!EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().register(this);
        }
    }

    /**
     * 批量设置监听
     *
     * @param views
     */
    protected void setonClickListener(View... views) {
        if (views != null && views.length > 0) {
            for (View view : views) {
                view.setOnClickListener(this);
            }
        }
    }

    /**
     * 隐藏控件
     *
     * @param v
     */
    public void gone(@NonNull View... v) {
        for (int i = 0; i < v.length; i++) {
            v[i].setVisibility(View.GONE);
        }
    }

    /**
     * 不显示控件
     *
     * @param v
     */
    public void invisible(@NonNull View... v) {
        for (int i = 0; i < v.length; i++) {
            v[i].setVisibility(View.INVISIBLE);
        }
    }

    /**
     * 显示控件
     *
     * @param v
     */
    public void visible(@NonNull View... v) {
        for (int i = 0; i < v.length; i++) {
            v[i].setVisibility(View.VISIBLE);
        }
    }

    /**
     * 初始化loading
     */
    private void initLoading() {
        if (loading == null) {
            loading = new Loading(this);
        }
    }

    /**
     * 显示loading
     */
    public void showLoading() {
        initLoading();
        if (!loading.isShowing()) {
            loading.show();
        }
    }

    /**
     * 取消loading
     */
    public void dismissLoading() {
        if (loading != null && loading.isShowing()) {
            loading.dismiss();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().unregister(this);
        }
        if (loading != null && loading.isShowing()) {
            loading.dismiss();
            loading = null;
        }
    }
}
