package com.fangwolf.module_chat.ui.fragment;

import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;

import com.fangwolf.module_chat.R;
import com.fangwolf.module_chat.ui.activity.ChatActivity;
import com.hyphenate.EMMessageListener;
import com.hyphenate.chat.EMClient;
import com.hyphenate.chat.EMConversation;
import com.hyphenate.chat.EMMessage;
import com.hyphenate.easeui.Constant;
import com.hyphenate.easeui.EaseUI;
import com.hyphenate.easeui.model.EaseNotifier;
import com.hyphenate.easeui.ui.EaseConversationListFragment;
import com.orhanobut.logger.Logger;

import java.util.List;

/**
 * @Auther 獠牙血狼
 * @Date 2019/4/23
 * @Desc 会话列表
 */
public class ConversationListFragment extends EaseConversationListFragment {
    EaseUI easeUI;
    EMMessageListener messageListener;
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
        initEaseUI();
    }

    /**
     * 设置easeUI
     */
    public void initEaseUI() {
        easeUI = EaseUI.getInstance();
        //全局消息监听
        registerMessageListener();
    }

    /**
     * 收到消息监听
     */
    private void registerMessageListener() {
        messageListener = new EMMessageListener() {
            @Override
            public void onMessageReceived(List<EMMessage> messages) {
                for (EMMessage message : messages) {
                    Logger.e("收到了：" + message.getBody().toString());
                    // in background, do not refresh UI, notify it in notification bar
                    if (!EaseUI.getInstance().hasForegroundActivies()) {
                        EaseNotifier notifier = EaseUI.getInstance().getNotifier();
                        conversationListView.refresh();
                        //通知
//                        notifier.setNotificationInfoProvider(new EaseNotifier.EaseNotificationInfoProvider() {
//                            @Override
//                            public String getDisplayedText(EMMessage message) {
//                                return null;
//                            }
//
//                            @Override
//                            public String getLatestText(EMMessage message, int fromUsersNum, int messageNum) {
//                                return null;
//                            }
//
//                            @Override
//                            public String getTitle(EMMessage message) {
//                                return null;
//                            }
//
//                            @Override
//                            public int getSmallIcon(EMMessage message) {
//                                return 0;
//                            }
//
//                            @Override
//                            public Intent getLaunchIntent(EMMessage message) {
//                                //点击通知，跳转到好友消息页面
////                                Intent intent = new Intent(appContext, ConversationListActivity.class);
//                                //xml中设置了singleTask模式，避免点击通知跳转好友消息页面后返回桌面再次击通知跳转好友消息页面会重复生成页面
//                                return null;
//                            }
//                        });
                        notifier.notify(message);
                    }
                }
            }

            @Override
            public void onCmdMessageReceived(List<EMMessage> list) {

            }

            @Override
            public void onMessageRead(List<EMMessage> list) {

            }

            @Override
            public void onMessageDelivered(List<EMMessage> list) {

            }

            @Override
            public void onMessageRecalled(List<EMMessage> list) {

            }

            @Override
            public void onMessageChanged(EMMessage emMessage, Object o) {

            }
        };
        EMClient.getInstance().chatManager().addMessageListener(messageListener);
    }

    public void setListener(setTitleBarClickListener listener) {
        this.listener = listener;
    }

    interface setTitleBarClickListener {
        void OnRightClickListener(View v);
    }
}
