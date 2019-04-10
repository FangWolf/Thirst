package com.fangwolf.module_home.bean;

import cn.bmob.v3.BmobObject;

/**
 * @Auther 獠牙血狼
 * @Date 2019/4/10
 * @Desc 热门和最新bean
 */
public class HotAndNewsBean extends BmobObject {
    private String title;
    private String description;
    private int voteNumber;
    private String videoId;
    private String categoryId;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getVoteNumber() {
        return voteNumber;
    }

    public void setVoteNumber(int voteNumber) {
        this.voteNumber = voteNumber;
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
