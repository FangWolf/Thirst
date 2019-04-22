package com.fangwolf.module_chat;

import android.content.Context;
import android.content.Intent;

import com.fangwolf.module_chat.event.MessageEvent;
import com.hyphenate.EMMessageListener;
import com.hyphenate.chat.EMClient;
import com.hyphenate.chat.EMCmdMessageBody;
import com.hyphenate.chat.EMMessage;
import com.hyphenate.chat.EMOptions;
import com.hyphenate.easeui.Constant;
import com.hyphenate.easeui.EaseUI;
import com.hyphenate.easeui.model.EaseNotifier;
import com.hyphenate.util.EMLog;
import com.orhanobut.logger.Logger;

import org.greenrobot.eventbus.EventBus;

import java.util.List;

/**
 * @Auther 獠牙血狼
 * @Date 2019/4/22
 * @Desc 环信工具
 */
public class EaseUtils {
    private static EaseUtils easeUtils;
    private EaseUI easeUI;
    private EMOptions options;
    protected  EMMessageListener messageListener;
    protected static final String TAG = "EaseUtils";

    public synchronized static EaseUtils getInstance() {
        if (easeUtils == null) {
            easeUtils = new EaseUtils();
        }
        return easeUtils;
    }

    public void init(Context context) {
        easeUI = EaseUI.getInstance();
        options = new EMOptions();
        easeUI.init(context, options);
        setEaseUIProviders();
        setGlobalListeners();
    }

    /**
     * 设置UI相关
     */
    private void setEaseUIProviders() {
        //声音震动
        easeUI.setSettingsProvider(new EaseUI.EaseSettingsProvider() {
            @Override
            public boolean isMsgNotifyAllowed(EMMessage message) {
                return false;
            }

            @Override
            public boolean isMsgSoundAllowed(EMMessage message) {
                return false;
            }

            @Override
            public boolean isMsgVibrateAllowed(EMMessage message) {
                return false;
            }

            @Override
            public boolean isSpeakerOpened() {
                return false;
            }
        });

        //通知提示
        easeUI.getNotifier().setNotificationInfoProvider(new EaseNotifier.EaseNotificationInfoProvider() {
            @Override
            public String getDisplayedText(EMMessage message) {
                return message.getUserName();
            }

            @Override
            public String getLatestText(EMMessage message, int fromUsersNum, int messageNum) {
                return null;
            }

            @Override
            public String getTitle(EMMessage message) {
                return null;
            }

            @Override
            public int getSmallIcon(EMMessage message) {
                return 0;
            }

            @Override
            public Intent getLaunchIntent(EMMessage message) {
                return null;
            }
        });

    }

    /**
     * 全局监听
     */
    private void setGlobalListeners() {
        registerMessageListener();
    }

    /**
     * 消息监听
     */
    private void registerMessageListener() {
        messageListener = new EMMessageListener() {
            @Override
            public void onMessageReceived(List<EMMessage> messages) {
                for (EMMessage message : messages) {
                    EMLog.d(TAG, "onMessageReceived id : " + message.getMsgId());
                    // 判断一下是否是会议邀请
                    String confId = message.getStringAttribute(Constant.MSG_ATTR_CONF_ID, "");
                    if (!"".equals(confId)) {
                        String password = message.getStringAttribute(Constant.MSG_ATTR_CONF_PASS, "");
                        String extension = message.getStringAttribute(Constant.MSG_ATTR_EXTENSION, "");
//                        goConference(confId, password, extension);
                        Logger.e("***confId***");
                    }
                    // in background, do not refresh UI, notify it in notification bar
                    if (!easeUI.hasForegroundActivies()) {
                        getNotifier().notify(message);
                        EventBus.getDefault().post(new MessageEvent(message));
                    }
                }
            }

            @Override
            public void onCmdMessageReceived(List<EMMessage> messages) {
            }

            @Override
            public void onMessageRead(List<EMMessage> messages) {
            }

            @Override
            public void onMessageDelivered(List<EMMessage> message) {
            }

            @Override
            public void onMessageRecalled(List<EMMessage> messages) {
            }

            @Override
            public void onMessageChanged(EMMessage message, Object change) {
                EMLog.d(TAG, "change:");
                EMLog.d(TAG, "change:" + change);
            }
        };

        EMClient.getInstance().chatManager().addMessageListener(messageListener);
    }

    /**
     * get instance of EaseNotifier
     *
     * @return
     */
    public EaseNotifier getNotifier() {
        return easeUI.getNotifier();
    }
}
