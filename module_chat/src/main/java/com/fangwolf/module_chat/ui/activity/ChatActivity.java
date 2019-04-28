package com.fangwolf.module_chat.ui.activity;

import android.os.Bundle;

import com.fangwolf.module_chat.R;
import com.hyphenate.easeui.Constant;
import com.hyphenate.easeui.EaseConstant;
import com.hyphenate.easeui.ui.EaseChatFragment;

import androidx.appcompat.app.AppCompatActivity;

/**
 * @Auther 獠牙血狼
 * @Date 2019/4/28
 * @Desc 聊天页面
 */
public class ChatActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.chat_activity_chat);
        EaseChatFragment chatFragment = new EaseChatFragment();
        Bundle args = new Bundle();
        args.putInt(EaseConstant.EXTRA_CHAT_TYPE, EaseConstant.CHATTYPE_SINGLE);
        args.putString(EaseConstant.EXTRA_USER_ID, getIntent().getStringExtra(Constant.EXTRA_USER_ID));
        chatFragment.setArguments(args);

        getSupportFragmentManager().beginTransaction()
                .add(R.id.root, chatFragment)
                .commit();

    }
}
