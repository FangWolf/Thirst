package com.fangwolf.library_base.router;

/**
 * @Auther 獠牙血狼
 * @Date 2019/3/1
 * @Desc 用于组件开发中，ARouter多Fragment跳转的统一路径注册
 * * * * 在这里注册添加路由路径，需要清楚的写好注释，标明功能界面
 */
public class RouterFragmentPath {
    /**
     * 我的组件
     */
    public static class Mine {
        private static final String MINE = "/Mine";
        /*我的页面*/
        public static final String PAGE_MINE = MINE + "/mine";
    }
}
