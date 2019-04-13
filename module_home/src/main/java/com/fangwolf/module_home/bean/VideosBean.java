package com.fangwolf.module_home.bean;

import cn.bmob.v3.BmobObject;

/**
 * @Auther 獠牙血狼
 * @Date 2019/4/13
 * @Desc 视频bean
 */
public class VideosBean extends BmobObject {
    private String videoId;
    private String categoryId;
    private String title;
    private String detail;
    private String videoURL;

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

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public String getVideoURL() {
        return videoURL;
    }

    public void setVideoURL(String videoURL) {
        this.videoURL = videoURL;
    }
}
