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
        private static final String LOGIN_BASE = "/Login";
        //登录页面
        public static final String LOGIN = LOGIN_BASE + "/login";
        //注册
        public static final String REGISTER = LOGIN_BASE + "/register";
    }

    /**
     * 壳的组件
     */
    public static class Main {
        private static final String MAIN_BASE = "/Main";
        //主页
        public static final String MAIN = MAIN_BASE + "/main";
    }

}
