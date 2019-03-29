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
 * @Desc 推荐的adapter
 */
public class RVRecommendAdapter extends BaseQuickAdapter<TestBean, BaseViewHolder> {

    public RVRecommendAdapter(int layoutResId, @Nullable List<TestBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, TestBean item) {
        helper.setText(R.id.tv_time, item.a + ":" + item.a + ":" + item.a)
                .setText(R.id.tv_title, item.b)
                .setText(R.id.tv_viewer_number, mContext.getResources().getString(R.string.home_viewer_number, item.c));
    }
}
