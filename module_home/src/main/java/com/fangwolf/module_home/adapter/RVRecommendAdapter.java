package com.fangwolf.module_home.adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.fangwolf.module_home.R;
import com.fangwolf.module_home.bean.RecommendBean;

import java.util.List;

import androidx.annotation.Nullable;

/**
 * @Auther 獠牙血狼
 * @Date 2019/3/29
 * @Desc 推荐的adapter
 */
public class RVRecommendAdapter extends BaseQuickAdapter<RecommendBean, BaseViewHolder> {

    public RVRecommendAdapter(int layoutResId, @Nullable List<RecommendBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, RecommendBean item) {
        helper.setText(R.id.tv_title, item.getTitle());
        switch (item.getCategoryId()) {
            case "44":
                break;
            case "55":
                break;
            case "66":
                break;
            default:
                break;
        }
        switch (item.getRecommendStart()) {
            case 1:
                break;
            case 2:
                break;
            case 3:
                break;
            case 4:
                break;
            case 5:
                break;
        }
    }
}
