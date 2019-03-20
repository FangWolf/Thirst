package com.fangwolf.library_base.router;

/**
 * @Auther 獠牙血狼
 * @Date 2019/3/1
 * @Desc 用于组件开发中，ARouter多Fragment跳转的统一路径注册
 * * * * 在这里注册添加路由路径，需要清楚的写好注释，标明功能界面
 */
public class RouterFragmentPath {
    /**
     * 首页组件
     */
    public static class Home {
        private static final String HOME_BASE = "/Home";
        /*首页页面*/
        public static final String MAIN = HOME_BASE + "/home";
    }

    /**
     * 新闻组件
     */
    public static class News {
        private static final String NEWS_BASE = "/News";
        /*新闻页面*/
        public static final String MAIN = NEWS_BASE + "/news";
    }

    /**
     * 聊天组件
     */
    public static class Chat {
        private static final String CHAT_BASE = "/Chat";
        /*我的页面*/
        public static final String MAIN = CHAT_BASE + "/chat";
    }

    /**
     * 我的组件
     */
    public static class Mine {
        private static final String MINE_BASE = "/Mine";
        /*我的页面*/
        public static final String MAIN = MINE_BASE + "/mine";
    }
}
