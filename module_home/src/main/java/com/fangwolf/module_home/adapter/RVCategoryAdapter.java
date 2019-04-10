package com.fangwolf.module_home.adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.fangwolf.module_home.R;
import com.fangwolf.module_home.bean.GeneralBean;

import java.util.List;

import androidx.annotation.Nullable;

/**
 * @Auther 獠牙血狼
 * @Date 2019/3/26
 * @Desc 首页的通用adapter
 */
public class RVCategoryAdapter extends BaseQuickAdapter<GeneralBean, BaseViewHolder> {

    public RVCategoryAdapter(int layoutResId, @Nullable List<GeneralBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, GeneralBean item) {
        helper.setText(R.id.tv_title, item.getTitle())
                .setText(R.id.tv_level, String.format(mContext.getString(R.string.home_app_level), item.getLevel()))
                .setText(R.id.tv_description, item.getDescription());
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
