package com.fangwolf.module_chat.ui;

import android.content.Context;
import android.os.Bundle;
import android.view.View;

import com.fangwolf.library_base.base.BaseDialog;
import com.fangwolf.library_base.utils.ToastUtils;
import com.fangwolf.module_chat.R;
import com.fangwolf.module_chat.databinding.ChatAddFriendDialogBinding;
import com.hyphenate.chat.EMClient;
import com.hyphenate.exceptions.HyphenateException;

/**
 * @Auther 獠牙血狼
 * @Date 2019/6/7
 * @Desc 加好友弹窗
 */
public class AddFriendDialog extends BaseDialog<ChatAddFriendDialogBinding> implements View.OnClickListener {
    private String friendName = "";

    public AddFriendDialog(Context context) {
        super(context);
    }

    @Override
    public int setLayoutID() {
        return R.layout.chat_add_friend_dialog;
    }

    @Override
    public void initView() {
        BD.btnClose.setOnClickListener(this);
        BD.btnAdd.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btn_close) {
            dismiss();
        } else if (v.getId() == R.id.btn_add) {
            friendName = BD.etFriendName.getText().toString().trim();
            addFriend();
        }
    }

    @Override
    public void dismiss() {
        super.dismiss();
        BD.etFriendName.setText("");
    }

    /**
     * 添加好友
     */
    private void addFriend() {
        if (friendName.isEmpty()) {
            ToastUtils.showShort("请输入好友昵称");
        } else {
            try {
                EMClient.getInstance().contactManager().addContact(friendName, "fangwolf");
                ToastUtils.showShort("好友请求已发送");
                dismiss();
            } catch (HyphenateException e) {
                e.printStackTrace();
            }
        }
    }
}
