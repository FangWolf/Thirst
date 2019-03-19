package com.fangwolf.library_base.router;

/**
 * @Auther 獠牙血狼
 * @Date 2019/3/1
 * @Desc 用于组件开发中，ARouter单Activity跳转的统一路径注册
 * * *  在这里注册添加路由路径，需要清楚的写好注释，标明功能界面
 */
public class RouterActivityPath {
    /**
     * 登录注册组件
     */
    public static class Login {
        private static final String LOGIN = "/Login";
        /*登录页面*/
        public static final String PAGE_LOGIN = LOGIN + "/login";
    }

    /**
     * 壳的组件
     */
    public static class Main {
        private static final String MAIN = "/Main";
        /*登录页面*/
        public static final String PAGE_MAIN = MAIN + "/main";
    }

}
