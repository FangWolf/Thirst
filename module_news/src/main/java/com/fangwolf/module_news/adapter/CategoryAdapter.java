package com.fangwolf.module_news.adapter;

import android.widget.ImageView;

import com.bumptech.glide.Glide;
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
    private boolean isWelfare;

    public CategoryAdapter(int layoutResId, @Nullable List<DataBean.ResultsBean> data, boolean isWelfare) {
        super(layoutResId, data);
        this.isWelfare = isWelfare;
    }

    @Override
    protected void convert(BaseViewHolder helper, DataBean.ResultsBean item) {
        if (isWelfare) {
            helper.setGone(R.id.tv_desc, false)
                    .setGone(R.id.tv_vu, false)
                    .setGone(R.id.iv_mz, true);
            Glide.with(mContext)
                    .load(item.getUrl())
                    .into((ImageView) helper.getView(R.id.iv_mz));
        } else {
            helper.setGone(R.id.iv_mz, false)
                    .setGone(R.id.tv_desc, true)
                    .setGone(R.id.tv_vu, true)
                    .setText(R.id.tv_desc, item.getDesc())
                    .setText(R.id.tv_vu, String.format(mContext.getString(R.string.news_vu), item.getWho(), item.getPublishedAt().substring(0, 10)));
        }
    }
}
