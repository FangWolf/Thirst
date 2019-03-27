package com.fangwolf.module_home.ui.fragment;


import android.content.Context;
import android.graphics.Color;
import android.view.View;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.fangwolf.library_base.base.BaseFragment;
import com.fangwolf.library_base.router.RouterFragmentPath;
import com.fangwolf.module_home.R;
import com.fangwolf.module_home.adapter.HomeViewPageAdapter;
import com.fangwolf.module_home.databinding.HomeFragmentHomeBinding;
import com.fangwolf.module_home.sundries.ScaleTransitionPagerTitleView;

import net.lucode.hackware.magicindicator.ViewPagerHelper;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.CommonNavigator;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.CommonNavigatorAdapter;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.IPagerIndicator;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.IPagerTitleView;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.indicators.BezierPagerIndicator;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.titles.SimplePagerTitleView;

import java.util.ArrayList;
import java.util.List;

/**
 * @Auther 獠牙血狼
 * @Date 2019/3/20
 * @Desc 首页
 */
@Route(path = RouterFragmentPath.Home.MAIN)
public class HomeFragment extends BaseFragment<HomeFragmentHomeBinding> {
    private List<String> mDataList = new ArrayList<>();

    @Override
    public int getLayoutId() {
        return R.layout.home_fragment_home;
    }

    @Override
    public void initView() {
        mDataList.add("推荐");
        mDataList.add("热门");
        mDataList.add("最新");
        mDataList.add("Android");
        mDataList.add("Java");
        BD.viewPager.setAdapter(new HomeViewPageAdapter(getFragmentManager(), BD.viewPager, mDataList));
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
        commonNavigator.setAdapter(new CommonNavigatorAdapter() {
            @Override
            public int getCount() {
                return mDataList == null ? 0 : mDataList.size();
            }

            @Override
            public IPagerTitleView getTitleView(Context context, final int index) {
                SimplePagerTitleView simplePagerTitleView = new ScaleTransitionPagerTitleView(context);
                simplePagerTitleView.setText(mDataList.get(index));
                simplePagerTitleView.setTextSize(18);
                simplePagerTitleView.setNormalColor(Color.GRAY);
                simplePagerTitleView.setSelectedColor(Color.BLACK);
                simplePagerTitleView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        BD.viewPager.setCurrentItem(index);
                    }
                });
                return simplePagerTitleView;
            }

            @Override
            public IPagerIndicator getIndicator(Context context) {
                BezierPagerIndicator indicator = new BezierPagerIndicator(context);
                indicator.setColors(Color.parseColor("#ff4a42"), Color.parseColor("#fcde64"), Color.parseColor("#73e8f4"), Color.parseColor("#00ff80"), Color.parseColor("#c683fe"));
                return indicator;
            }
        });
        BD.magicIndicator.setNavigator(commonNavigator);
        ViewPagerHelper.bind(BD.magicIndicator, BD.viewPager);
    }

}
