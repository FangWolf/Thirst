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
                helper.setImageResource(R.id.iv_logo, R.mipmap.img_video_android);
                break;
            case "55":
                helper.setImageResource(R.id.iv_logo, R.mipmap.img_video_java);
                break;
            case "66":
                helper.setImageResource(R.id.iv_logo, R.mipmap.img_video_flutter);
                break;
            default:
                break;
        }
        switch (item.getRecommendStart()) {
            case 1:
                helper.setImageResource(R.id.iv_star, R.mipmap.img_stars_1);
                break;
            case 2:
                helper.setImageResource(R.id.iv_star, R.mipmap.img_stars_2);
                break;
            case 3:
                helper.setImageResource(R.id.iv_star, R.mipmap.img_stars_3);
                break;
            case 4:
                helper.setImageResource(R.id.iv_star, R.mipmap.img_stars_4);
                break;
            case 5:
                helper.setImageResource(R.id.iv_star, R.mipmap.img_stars_5);
                break;
        }
    }
}
