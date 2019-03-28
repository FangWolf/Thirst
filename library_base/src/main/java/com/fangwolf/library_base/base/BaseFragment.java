package com.fangwolf.library_base.base;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.fangwolf.library_base.widget.Loading;

import org.greenrobot.eventbus.EventBus;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.fragment.app.Fragment;

/**
 * @Auther 獠牙血狼
 * @Date 2019/3/1
 * @Desc 基础fragment
 */
public abstract class BaseFragment<BindingType extends ViewDataBinding> extends Fragment implements View.OnClickListener {
    private View view;
    public BindingType BD;
    private Loading loading;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        int layoutId = getLayoutId();
        if (layoutId != 0) {
            BD = DataBindingUtil.inflate(inflater, getLayoutId(), container, false);
            view = BD.getRoot();
        }
        initView();
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initData();
    }

    public abstract int getLayoutId();

    public abstract void initView();

    public abstract void initData();

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
            loading = new Loading(getActivity());
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
    public void onDestroy() {
        super.onDestroy();
        if (EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().unregister(this);
        }
    }
}
