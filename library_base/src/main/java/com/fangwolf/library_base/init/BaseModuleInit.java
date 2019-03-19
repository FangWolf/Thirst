package com.fangwolf.library_base.init;

import android.app.Application;

import com.alibaba.android.arouter.launcher.ARouter;
import com.fangwolf.library_base.BuildConfig;
import com.fangwolf.library_base.utils.Utils;
import com.orhanobut.logger.AndroidLogAdapter;
import com.orhanobut.logger.Logger;

/**
 * @Auther 獠牙血狼
 * @Date 2019/3/1
 * @Desc 基础库初始化操作
 */
public class BaseModuleInit implements IModuleInit {
    @Override
    public boolean onInitAhead(Application application) {
        //开启打印日志
        Logger.addLogAdapter(new AndroidLogAdapter());
        //初始化阿里路由框架
        if (BuildConfig.DEBUG) {
            ARouter.openLog();     // 打印日志
            ARouter.openDebug();   // 开启调试模式(如果在InstantRun模式下运行，必须开启调试模式！线上版本需要关闭,否则有安全风险)
        }
        ARouter.init(application); // 尽可能早，推荐在Application中初始化
        Utils.init(application);
        Logger.e("基础层初始化 -- onInitAhead");
        return false;
    }

    @Override
    public boolean onInitBehind(Application application) {
        Logger.e("基础层初始化 -- onInitBehind");
        return false;
    }
}
