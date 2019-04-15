package com.fangwolf.module_news.adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.fangwolf.module_news.R;
import com.fangwolf.module_news.bean.DataBean;

import java.util.List;

import androidx.annotation.Nullable;

/**
 * @Auther 獠牙血狼
 * @Date 2019/4/15
 * @Desc 分类adapter
 */
public class CategoryAdapter extends BaseQuickAdapter<DataBean.ResultsBean, BaseViewHolder> {
    public CategoryAdapter(int layoutResId, @Nullable List<DataBean.ResultsBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, DataBean.ResultsBean item) {
        helper.setText(R.id.tv_desc, item.getDesc())
                .setText(R.id.tv_vu, String.format(mContext.getString(R.string.news_vu), item.getWho(), item.getPublishedAt().substring(0,10)));

    }
}
