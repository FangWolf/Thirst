package com.fangwolf.module_chat.ui.fragment;

import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;

import com.fangwolf.module_chat.R;
import com.fangwolf.module_chat.ui.activity.ChatActivity;
import com.hyphenate.chat.EMConversation;
import com.hyphenate.easeui.Constant;
import com.hyphenate.easeui.ui.EaseConversationListFragment;

/**
 * @Auther 獠牙血狼
 * @Date 2019/4/23
 * @Desc 会话列表
 */
public class ConversationListFragment extends EaseConversationListFragment {

    setTitleBarClickListener listener;

    @Override
    protected void initView() {
        super.initView();
        titleBar = getView().findViewById(R.id.title_bar);
        titleBar.setLeftLayoutClickListener(null);
        titleBar.setRightLayoutClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.OnRightClickListener(v);
            }
        });
    }

    @Override
    protected void setUpView() {
        super.setUpView();
        conversationListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                EMConversation conversation = conversationListView.getItem(position);
                String username = conversation.conversationId();
                startActivity(new Intent(getActivity(), ChatActivity.class).putExtra(Constant.EXTRA_USER_ID, username));
            }
        });
    }

    public void setListener(setTitleBarClickListener listener) {
        this.listener = listener;
    }

    interface setTitleBarClickListener {
        void OnRightClickListener(View v);
    }
}
