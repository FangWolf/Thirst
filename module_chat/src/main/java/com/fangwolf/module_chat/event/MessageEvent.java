package com.fangwolf.module_chat.event;

import com.hyphenate.chat.EMMessage;

/**
 * @Auther 獠牙血狼
 * @Date 2019/4/22
 * @Desc 收到新消息
 */
public class MessageEvent {
    public EMMessage message;

    public MessageEvent(EMMessage message) {
        this.message = message;
    }
}
