package com.fangwolf.module_main;

import android.app.Application;

import com.fangwolf.library_base.init.IModuleInit;
import com.orhanobut.logger.Logger;

/**
 * @Auther 獠牙血狼
 * @Date 2019/3/1
 * @Desc 壳组件初始化操作
 */
public class MainModuleInit implements IModuleInit {
    @Override
    public boolean onInitAhead(Application application) {
        Logger.e("壳模块初始化 -- onInitAhead");
        return false;
    }

    @Override
    public boolean onInitBehind(Application application) {
        Logger.e("壳模块初始化 -- onInitBehind");
        return false;
    }
}
