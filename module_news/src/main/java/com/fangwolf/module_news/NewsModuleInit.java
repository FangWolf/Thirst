package com.fangwolf.module_news;

import android.app.Application;

import com.fangwolf.library_base.init.IModuleInit;
import com.orhanobut.logger.Logger;

/**
 * @Auther 獠牙血狼
 * @Date 2019/3/1
 * @Desc 新闻组件初始化操作
 */
public class NewsModuleInit implements IModuleInit {
    @Override
    public boolean onInitAhead(Application application) {
        Logger.e("新闻模块初始化 -- onInitAhead");
        return false;
    }

    @Override
    public boolean onInitBehind(Application application) {
        Logger.e("新闻模块初始化 -- onInitBehind");
        return false;
    }
}
