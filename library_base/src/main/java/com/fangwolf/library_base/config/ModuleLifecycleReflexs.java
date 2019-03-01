package com.fangwolf.library_base.config;

/**
 * @Auther 獠牙血狼
 * @Date 2019/3/1
 * @Desc 组件生命周期反射类名管理，在这里注册需要初始化的组件，通过反射动态调用各个组件的初始化方法
 * * * * 注意：以下模块中初始化的Module类不能被混淆
 */
public class ModuleLifecycleReflexs {
    private static final String BaseInit = "com.fangwolf.library_base.init.BaseModuleInit";
    //壳模块
    private static final String MainInit = "com.fangwolf.module_main.MainModuleInit";
    //登录验证模块
    private static final String LoginInit = "com.fangwolf.module_login.LoginModuleInit";

    public static String[] initModuleNames = {BaseInit, MainInit, LoginInit};
}
