package com.fangwolf.module_chat.ui.activity;

import android.os.Bundle;

import com.fangwolf.module_chat.R;
import com.hyphenate.easeui.EaseConstant;
import com.hyphenate.easeui.ui.EaseBaseActivity;
import com.hyphenate.easeui.ui.EaseChatFragment;

/**
 * @Auther 獠牙血狼
 * @Date 2019/4/17
 * @Desc 聊天的对话页面
 */
public class ChatActivity extends EaseBaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.chat_activity_chat);
        //new出EaseChatFragment或其子类的实例
        EaseChatFragment chatFragment = new EaseChatFragment();
        //传入参数
        Bundle args = new Bundle();
        args.putInt(EaseConstant.EXTRA_CHAT_TYPE, EaseConstant.CHATTYPE_SINGLE);
        args.putString(EaseConstant.EXTRA_USER_ID, getIntent().getStringExtra("id"));
        chatFragment.setArguments(args);
        getSupportFragmentManager().beginTransaction().add(R.id.container, chatFragment).commit();
    }
    // TODO: 2019/4/17 把 EaseBaseActivity onResume中的通知注释了就能用，因为没有注册通知还
}
