package com.fangwolf.module_home.bean;

import cn.bmob.v3.BmobObject;

/**
 * @Auther 獠牙血狼
 * @Date 2019/3/27
 * @Desc 分类信息
 */
public class CategoryBean extends BmobObject {
    private int id;
    private String name;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
