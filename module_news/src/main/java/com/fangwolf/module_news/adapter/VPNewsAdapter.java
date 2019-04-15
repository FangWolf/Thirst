package com.fangwolf.module_news.adapter;


import android.content.Context;
import android.os.Bundle;

import com.fangwolf.module_news.ui.fragment.CategoryFragment;

import java.util.List;
import java.util.Stack;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

/**
 * @Auther 獠牙血狼
 * @Date 2019/3/28
 * @Desc 资讯的viewPage适配器
 */

public class VPNewsAdapter extends FragmentPagerAdapter {
    private Stack<Fragment> fragStack = new Stack<>();
    private List<String> typeList;
    private Context context;

    public VPNewsAdapter(FragmentManager fm, ViewPager viewPager, List<String> typeList) {
        super(fm);
        this.context = viewPager.getContext();
        viewPager.setOffscreenPageLimit(1);
        viewPager.setAdapter(this);
        this.typeList = typeList;
        initFrag();
    }

    @Override
    public Fragment getItem(int i) {
        if (i + 1 > fragStack.size()) {
            initFrag();
        }
        return fragStack.get(i);
    }

    @Override
    public int getCount() {
        return typeList == null ? 0 : typeList.size();
    }

    private void initFrag() {
        fragStack.clear();
        for (int i = 0; i < typeList.size(); i++) {
            Bundle bundle = new Bundle();
            bundle.putInt("index", i);
            bundle.putString("type", typeList.get(i));
            fragStack.add(Fragment.instantiate(context, CategoryFragment.class.getName(), bundle));
        }
    }

    //避免反复滑动后出现空白页
//    @Override
//    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
//    }
}
