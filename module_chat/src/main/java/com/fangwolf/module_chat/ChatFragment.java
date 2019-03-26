package com.fangwolf.module_chat;


import android.view.View;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.fangwolf.library_base.base.BaseFragment;
import com.fangwolf.library_base.router.RouterFragmentPath;
import com.fangwolf.module_chat.databinding.ChatFragmentChatBinding;

/**
 * @Auther 獠牙血狼
 * @Date 2019/3/20
 * @Desc 聊天
 */
@Route(path = RouterFragmentPath.Chat.MAIN)
public class ChatFragment extends BaseFragment<ChatFragmentChatBinding> {

    @Override
    public int getLayoutId() {
        return R.layout.chat_fragment_chat;
    }

    @Override
    public void initView() {

    }

    @Override
    public void initData() {

    }

    @Override
    public void onClick(View view) {

    }
}
