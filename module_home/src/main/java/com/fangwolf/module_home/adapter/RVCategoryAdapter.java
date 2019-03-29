package com.fangwolf.module_home.adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.fangwolf.module_home.R;
import com.fangwolf.module_home.bean.TestBean;

import java.util.List;

import androidx.annotation.Nullable;

/**
 * @Auther 獠牙血狼
 * @Date 2019/3/26
 * @Desc 首页的adapter
 */
public class RVCategoryAdapter extends BaseQuickAdapter<TestBean, BaseViewHolder> {

    public RVCategoryAdapter(int layoutResId, @Nullable List<TestBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, TestBean item) {
        helper.setText(R.id.tv_title, item.a)
                .setText(R.id.tv_price, item.b)
                .setText(R.id.tv_level, item.c)
                .setText(R.id.tv_number, item.d)
        ;
    }
}
