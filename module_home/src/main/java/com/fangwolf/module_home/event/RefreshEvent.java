package com.fangwolf.module_home.event;

/**
 * @Auther 獠牙血狼
 * @Date 2019/3/28
 * @Desc 点击分类名称，进行刷新
 */
public class RefreshEvent {
    public int index;

    public RefreshEvent(int index) {
        this.index = index;
    }
}
