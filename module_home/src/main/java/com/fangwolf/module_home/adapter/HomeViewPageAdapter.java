package com.fangwolf.module_home.adapter;


import android.content.Context;
import android.os.Bundle;

import com.fangwolf.module_home.bean.CategoryBean;
import com.fangwolf.module_home.ui.fragment.CategoryFragment;

import java.util.List;
import java.util.Stack;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

/**
 * @Auther 獠牙血狼
 * @Date 2019/3/27
 * @Desc 首页的viewPage适配器
 */
public class HomeViewPageAdapter extends FragmentPagerAdapter {
    private Stack<Fragment> fragStack = new Stack<>();
    private List<CategoryBean> categoryList;
    private Context context;

    public HomeViewPageAdapter(FragmentManager fm, ViewPager viewPager, List<CategoryBean> categoryList) {
        super(fm);
        this.context = viewPager.getContext();
        viewPager.setOffscreenPageLimit(1);
        viewPager.setAdapter(this);
        this.categoryList = categoryList;
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
        return categoryList == null ? 0 : categoryList.size();
    }

    private void initFrag() {
        fragStack.clear();
        for (int i = 0; i < categoryList.size(); i++) {
            Bundle bundle = new Bundle();
            bundle.putInt("index", i);
            bundle.putInt("id", categoryList.get(i).getId());
            bundle.putString("name", categoryList.get(i).getName());
            fragStack.add(Fragment.instantiate(context, CategoryFragment.class.getName(), bundle));
        }
    }

    //避免反复滑动后出现空白页
//    @Override
//    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
//    }
}
