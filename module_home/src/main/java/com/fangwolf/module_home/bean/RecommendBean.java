package com.fangwolf.module_home.bean;

import cn.bmob.v3.BmobObject;

/**
 * @Auther 獠牙血狼
 * @Date 2019/4/5
 * @Desc 推荐bean
 */
public class RecommendBean extends BmobObject {
    private String title;
    private int recommendStart;
    private String videoId;
    private String categoryId;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getRecommendStart() {
        return recommendStart;
    }

    public void setRecommendStart(int recommendStart) {
        this.recommendStart = recommendStart;
    }

    public String getVideoId() {
        return videoId;
    }

    public void setVideoId(String videoId) {
        this.videoId = videoId;
    }

    public String getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }
}
