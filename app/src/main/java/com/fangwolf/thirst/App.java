package com.fangwolf.thirst;

import android.app.Application;

import com.fangwolf.library_base.config.ModuleLifecycleConfig;

/**
 * @Auther 獠牙血狼
 * @Date 2019/3/1
 * @Desc 宿主app
 */
public class App extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        //初始化组件（靠前）
        ModuleLifecycleConfig.getInstance().initModuleAhead(this);
        //初始化组件（靠后）
        ModuleLifecycleConfig.getInstance().initModuleLow(this);
    }
}
