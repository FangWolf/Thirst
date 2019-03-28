package com.fangwolf.library_base.base;


import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.databinding.ViewDataBinding;

/**
 * @Auther 獠牙血狼
 * @Date 2019/3/28
 * @Desc 懒加载fragment
 */
public abstract class LazyFragment<BindingType extends ViewDataBinding> extends BaseFragment<BindingType> {
    private boolean isViewInited = false;
    private boolean isInited = false;

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (isVisibleToUser) {
            initData();
        }
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        isViewInited = true;
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void initData() {
        if ((getUserVisibleHint()) && isViewInited) {
            requestData();
        }
    }

    public void requestData() {
        if (isInited) return;
        isInited = true;
        lazyInit();
    }

    protected abstract void lazyInit();


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        isViewInited = false;
        isInited = false;
    }

}
