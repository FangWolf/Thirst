package com.fangwolf.module_chat;

import android.app.Application;

import com.fangwolf.library_base.init.IModuleInit;
import com.hyphenate.chat.EMClient;
import com.hyphenate.chat.EMOptions;
import com.orhanobut.logger.Logger;

/**
 * @Auther 獠牙血狼
 * @Date 2019/3/1
 * @Desc 聊天组件初始化操作
 */
public class ChatModuleInit implements IModuleInit {
    @Override
    public boolean onInitAhead(Application application) {
        Logger.e("聊天模块初始化 -- onInitAhead");
        initEaseUI(application);
        return false;
    }

    @Override
    public boolean onInitBehind(Application application) {
        Logger.e("聊天模块初始化 -- onInitBehind");
        return false;
    }

    private void initEaseUI(Application application) {
        EMOptions options = new EMOptions();
        // 是否自动将消息附件上传到环信服务器，默认为True是使用环信服务器上传下载，如果设为 false，需要开发者自己处理附件消息的上传和下载
        options.setAutoTransferMessageAttachments(true);
        // 是否自动下载附件类消息的缩略图等，默认为 true 这里和上边这个参数相关联
        options.setAutoDownloadThumbnail(true);
        //初始化
        EMClient.getInstance().init(application.getApplicationContext(), options);
    }
}
