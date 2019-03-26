package com.fangwolf.module_home.ui;


import android.view.View;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.fangwolf.library_base.base.BaseFragment;
import com.fangwolf.library_base.router.RouterFragmentPath;
import com.fangwolf.library_base.utils.ToastUtils;
import com.fangwolf.module_home.R;
import com.fangwolf.module_home.adapter.HomeAdapter;
import com.fangwolf.module_home.bean.TestBean;
import com.fangwolf.module_home.databinding.HomeFragmentHomeBinding;
import com.fangwolf.module_home.sundries.GlideImageLoader;
import com.youth.banner.BannerConfig;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import androidx.recyclerview.widget.LinearLayoutManager;

/**
 * @Auther 獠牙血狼
 * @Date 2019/3/20
 * @Desc 首页
 */
@Route(path = RouterFragmentPath.Home.MAIN)
public class HomeFragment extends BaseFragment<HomeFragmentHomeBinding> {
    private List<String> imgList;
    private List<TestBean> list;
    private HomeAdapter adapter;

    @Override
    public int getLayoutId() {
        return R.layout.home_fragment_home;
    }

    @Override
    public void initView() {
        initRv();
    }


    @Override
    public void initData() {
        initBanner();
        loadData();

    }

    @Override
    public void onClick(View view) {

    }

    private void initBanner() {
        imgList = new ArrayList<>();
        imgList.add("https://ss1.bdstatic.com/70cFuXSh_Q1YnxGkpoWK1HF6hhy/it/u=1500389290,3668597033&fm=26&gp=0.jpg");
        imgList.add("https://ss3.bdstatic.com/70cFv8Sh_Q1YnxGkpoWK1HF6hhy/it/u=2422983490,2140954611&fm=26&gp=0.jpg");
        imgList.add("https://ss1.bdstatic.com/70cFvXSh_Q1YnxGkpoWK1HF6hhy/it/u=915593634,1630526971&fm=26&gp=0.jpg");
        BD.banner.setImages(imgList)
                .setImageLoader(new GlideImageLoader())
                .isAutoPlay(true)
                .setDelayTime(3000)
                .setIndicatorGravity(BannerConfig.CENTER)
                .start();
    }

    private void initRv() {
        list = new ArrayList<>();
        adapter = new HomeAdapter(R.layout.home_item_home, list);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        BD.recyclerView.setAdapter(adapter);
        BD.recyclerView.setLayoutManager(layoutManager);
        adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                ToastUtils.showShort(position);
            }
        });

    }

    private void loadData() {
        Random r = new Random();
        for (int i = 0; i < 15; i++) {
            list.add(new TestBean(
                    r.nextInt(999), r.nextInt(999), r.nextInt(999), r.nextInt(999),
                    r.nextInt(999), r.nextInt(999), r.nextInt(999), r.nextInt(999),
                    r.nextInt(999), r.nextInt(999), r.nextInt(999), r.nextInt(999), r.nextInt(999)));
        }
        adapter.notifyDataSetChanged();
    }
}
