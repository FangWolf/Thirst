package com.fangwolf.library_base.init;

import android.app.Application;

/**
 * @Auther 獠牙血狼
 * @Date 2019/3/1
 * @Desc 动态配置Application，有需要初始化的组件实现该接口，统一在主app的Application中初始化
 */
public interface IModuleInit {
    //初始化优先的
    boolean onInitAhead(Application application);

    //初始化靠后的
    boolean onInitBehind(Application application);
}
