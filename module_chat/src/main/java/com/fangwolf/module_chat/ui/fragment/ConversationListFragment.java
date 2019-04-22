package com.fangwolf.module_chat.ui.fragment;


import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;

import com.fangwolf.module_chat.event.MessageEvent;
import com.fangwolf.module_chat.ui.activity.ChatActivity;
import com.hyphenate.chat.EMConversation;
import com.hyphenate.easeui.ui.EaseConversationListFragment;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;


/**
 * @Auther 獠牙血狼
 * @Date 2019/4/17
 * @Desc 消息列表fragment
 */
public class ConversationListFragment extends EaseConversationListFragment {

    @Override
    protected void setUpView() {
        super.setUpView();
        EventBus.getDefault().register(this);
        conversationListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                EMConversation conversation = conversationListView.getItem(position);
                String username = conversation.conversationId();
                startActivity(new Intent(getActivity(), ChatActivity.class).putExtra("id", username));
            }
        });
    }

    @Subscribe
    public void getMessage(MessageEvent event) {
        refresh();
    }
}
