package com.fangwolf.module_chat.ui.fragment;


import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.fangwolf.library_base.base.BaseFragment;
import com.fangwolf.library_base.router.RouterFragmentPath;
import com.fangwolf.library_base.utils.ToastUtils;
import com.fangwolf.module_chat.R;
import com.fangwolf.module_chat.databinding.ChatFragmentChatBinding;
import com.fangwolf.module_chat.ui.AddFriendDialog;
import com.hyphenate.easeui.widget.EaseTitleBar;

/**
 * @Auther 獠牙血狼
 * @Date 2019/3/20
 * @Desc 聊天
 */
@Route(path = RouterFragmentPath.Chat.MAIN)
public class ChatFragment extends BaseFragment<ChatFragmentChatBinding> {
    ConversationListFragment fragment;
    AddFriendDialog addFriendDialog;

    @Override
    public int getLayoutId() {
        return R.layout.chat_fragment_chat;
    }

    @Override
    public void initView() {
        addFriendDialog = new AddFriendDialog(getContext());
        fragment = new ConversationListFragment();
        getChildFragmentManager().beginTransaction()
                .add(R.id.fl_root, fragment)
                .commit();
    }

    @Override
    public void initData() {
        fragment.setListener(new ConversationListFragment.setTitleBarClickListener() {
            @Override
            public void OnRightClickListener(View v) {
                addFriendDialog.show();
            }
        });
    }

    @Override
    public void onClick(View view) {

    }
}
