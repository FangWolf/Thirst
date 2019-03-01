package com.fangwolf.library_base.debug;

import android.app.Application;

import com.fangwolf.library_base.config.ModuleLifecycleConfig;
import com.orhanobut.logger.AndroidLogAdapter;
import com.orhanobut.logger.Logger;

/**
 * @Auther 獠牙血狼
 * @Date 2019/3/1
 * @Desc 单组件运行的application
 */
public class DebugApp extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        //初始化组件(靠前)
        ModuleLifecycleConfig.getInstance().initModuleAhead(this);
        //初始化组件(靠后)
        ModuleLifecycleConfig.getInstance().initModuleLow(this);
    }
}
