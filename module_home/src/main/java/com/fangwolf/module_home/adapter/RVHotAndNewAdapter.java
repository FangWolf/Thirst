package com.fangwolf.module_home.adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.fangwolf.module_home.R;
import com.fangwolf.module_home.bean.HotAndNewsBean;

import java.util.List;

import androidx.annotation.Nullable;

/**
 * @Auther 獠牙血狼
 * @Date 2019/3/29
 * @Desc 热门和最新适配器
 */
public class RVHotAndNewAdapter extends BaseQuickAdapter<HotAndNewsBean, BaseViewHolder> {
    public RVHotAndNewAdapter(int layoutResId, @Nullable List<HotAndNewsBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, HotAndNewsBean item) {
        helper.setText(R.id.tv_title, item.getTitle())
                .setText(R.id.tv_description, item.getDescription())
                .setText(R.id.tv_vote_number, String.valueOf(item.getVoteNumber()))
                .addOnClickListener(R.id.btn_like, R.id.btn_dislike);
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
    }
}
