package com.fangwolf.module_chat.ui.fragment;


import android.content.Context;
import android.graphics.Color;
import android.view.View;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.fangwolf.library_base.base.BaseFragment;
import com.fangwolf.library_base.router.RouterFragmentPath;
import com.fangwolf.module_chat.R;
import com.fangwolf.module_chat.databinding.ChatFragmentChatBinding;

import net.lucode.hackware.magicindicator.ViewPagerHelper;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.CommonNavigator;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.CommonNavigatorAdapter;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.IPagerIndicator;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.IPagerTitleView;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.indicators.LinePagerIndicator;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.titles.ClipPagerTitleView;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentPagerAdapter;

/**
 * @Auther 獠牙血狼
 * @Date 2019/3/20
 * @Desc 聊天
 */
@Route(path = RouterFragmentPath.Chat.MAIN)
public class ChatFragment extends BaseFragment<ChatFragmentChatBinding> {
    private List<String> mDataList;
    private ConversationListFragment conversationListFragment;
    private ContactListFragment contactListFragment;

    @Override
    public int getLayoutId() {
        return R.layout.chat_fragment_chat;
    }

    @Override
    public void initView() {
        conversationListFragment = new ConversationListFragment();
        contactListFragment = new ContactListFragment();
        mDataList = new ArrayList<>();
        mDataList.add("会话");
        mDataList.add("通讯录");
        BD.viewPager.setAdapter(new FragmentPagerAdapter(getChildFragmentManager()) {
            @NonNull
            @Override
            public Fragment getItem(int position) {
                if (position == 0) {
                    return conversationListFragment;
                } else {
                    return contactListFragment;
                }
            }

            @Override
            public int getCount() {
                return mDataList.size();
            }
        });
        initMagicIndicator();
    }

    @Override
    public void initData() {
    }

    @Override
    public void onClick(View view) {

    }

    private void initMagicIndicator() {
        BD.magicIndicator.setBackgroundColor(Color.WHITE);
        CommonNavigator commonNavigator = new CommonNavigator(getActivity());
        commonNavigator.setSkimOver(true);
        commonNavigator.setAdjustMode(true);
        commonNavigator.setBackgroundColor(Color.parseColor("#75C5F0"));
        commonNavigator.setAdapter(new CommonNavigatorAdapter() {
            @Override
            public int getCount() {
                return mDataList == null ? 0 : mDataList.size();
            }

            @Override
            public IPagerTitleView getTitleView(Context context, final int index) {
                ClipPagerTitleView clipPagerTitleView = new ClipPagerTitleView(context);
                clipPagerTitleView.setText(mDataList.get(index));
                clipPagerTitleView.setTextColor(Color.parseColor("#85D5F0"));
                clipPagerTitleView.setClipColor(Color.WHITE);
                clipPagerTitleView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        BD.viewPager.setCurrentItem(index);
                    }
                });
                return clipPagerTitleView;
            }

            @Override
            public IPagerIndicator getIndicator(Context context) {
                LinePagerIndicator indicator = new LinePagerIndicator(context);
                indicator.setColors(Color.WHITE);
                return indicator;
            }
        });
        BD.magicIndicator.setNavigator(commonNavigator);
        ViewPagerHelper.bind(BD.magicIndicator, BD.viewPager);
    }
}
