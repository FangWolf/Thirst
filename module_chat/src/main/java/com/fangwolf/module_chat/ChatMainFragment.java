package com.fangwolf.module_chat;


import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.fangwolf.library_base.router.RouterFragmentPath;

/**
 * @Auther 獠牙血狼
 * @Date 2019/3/20
 * @Desc 聊天
 */
@Route(path = RouterFragmentPath.Chat.MAIN)
public class ChatMainFragment extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.chat_fragment_chat_main, container, false);
    }

}
