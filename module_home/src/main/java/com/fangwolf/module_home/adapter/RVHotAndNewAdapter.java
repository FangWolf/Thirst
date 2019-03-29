package com.fangwolf.module_home.adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.fangwolf.module_home.R;
import com.fangwolf.module_home.bean.TestBean;

import java.util.List;

import androidx.annotation.Nullable;

/**
 * @Auther 獠牙血狼
 * @Date 2019/3/29
 * @Desc 热门和最新适配器
 */
public class RVHotAndNewAdapter extends BaseQuickAdapter<TestBean, BaseViewHolder> {
    public RVHotAndNewAdapter(int layoutResId, @Nullable List<TestBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, TestBean item) {
        helper.setText(R.id.tv_title, item.a)
                .setText(R.id.tv_like_number, item.b)
                .addOnClickListener(R.id.btn_like, R.id.btn_dislike);
    }
}
