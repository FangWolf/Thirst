package com.fangwolf.module_home.adapter;


import android.content.Context;
import android.os.Bundle;

import com.fangwolf.module_home.bean.CategoryBean;
import com.fangwolf.module_home.ui.fragment.CategoryFragment;
import com.fangwolf.module_home.ui.fragment.CommentFragment;
import com.fangwolf.module_home.ui.fragment.IntroductionFragment;

import java.util.List;
import java.util.Stack;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

/**
 * @Auther 獠牙血狼
 * @Date 2019/3/27
 * @Desc 看视频页码的viewPage适配器
 */
public class VPWatchVideoAdapter extends FragmentPagerAdapter {
    private Stack<Fragment> fragStack = new Stack<>();
    private Context context;

    public VPWatchVideoAdapter(FragmentManager fm, ViewPager viewPager) {
        super(fm);
        this.context = viewPager.getContext();
        viewPager.setOffscreenPageLimit(1);
        viewPager.setAdapter(this);
        initFrag();
    }

    private void initFrag() {
        fragStack.clear();
        fragStack.add(Fragment.instantiate(context, IntroductionFragment.class.getName()));
        fragStack.add(Fragment.instantiate(context, CommentFragment.class.getName()));
    }

    @Override
    public int getCount() {
        return 2;
    }

    @Override
    public Fragment getItem(int position) {
        return fragStack.get(position);
    }

    //避免反复滑动后出现空白页
//    @Override
//    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
//    }
}
