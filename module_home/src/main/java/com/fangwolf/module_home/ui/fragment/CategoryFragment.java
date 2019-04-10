package com.fangwolf.module_home.ui.fragment;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;

import com.alibaba.android.arouter.launcher.ARouter;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.fangwolf.library_base.base.BaseFragment;
import com.fangwolf.library_base.router.RouterActivityPath;
import com.fangwolf.library_base.utils.ToastUtils;
import com.fangwolf.module_home.R;
import com.fangwolf.module_home.adapter.RVCategoryAdapter;
import com.fangwolf.module_home.adapter.RVHotAndNewAdapter;
import com.fangwolf.module_home.adapter.RVRecommendAdapter;
import com.fangwolf.module_home.bean.GeneralBean;
import com.fangwolf.module_home.bean.HotAndNewsBean;
import com.fangwolf.module_home.bean.RecommendBean;
import com.fangwolf.module_home.databinding.HomeFragmentCategoryBinding;
import com.fangwolf.module_home.event.RefreshEvent;
import com.fangwolf.module_home.sundries.GlideImageLoader;
import com.orhanobut.logger.Logger;
import com.scwang.smartrefresh.header.PhoenixHeader;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.listener.OnBannerListener;

import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;
import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;

/**
 * @Auther 獠牙血狼
 * @Date 2019/3/27
 * @Desc 首页分类的fragment
 * * * * index:viewPage页码
 */
public class CategoryFragment extends BaseFragment<HomeFragmentCategoryBinding> {
    private List<String> imgList;
    private List<RecommendBean> recommendList;
    private List<HotAndNewsBean> hotAndNewsList;
    private List<GeneralBean> generalList;
    private BaseQuickAdapter adapter;
    private String name;
    private int id;
    private int index;
    private int firstNumber = 0;

    @Override
    public int getLayoutId() {
        return R.layout.home_fragment_category;
    }

    @Override
    public void initView() {
        registerEventBus();
        Bundle bundle = getArguments();
        index = bundle.getInt("index");
        id = bundle.getInt("id");
        name = bundle.getString("name");
        initRV();
    }

    @Override
    public void initData() {
        initRL();
    }

    @Override
    public void onClick(View view) {

    }

    private void initRV() {
        recommendList = new ArrayList<>();
        hotAndNewsList = new ArrayList<>();
        generalList = new ArrayList<>();
        RecyclerView.LayoutManager layoutManager;
        switch (index) {
            case 0:
                adapter = new RVRecommendAdapter(R.layout.home_item_category_recommend, recommendList);
                layoutManager = new GridLayoutManager(getContext(), 2);
                View headView = getHeaderView();
                adapter.addHeaderView(headView);
                break;
            case 1:
            case 2:
                adapter = new RVHotAndNewAdapter(R.layout.home_item_hot_and_new, hotAndNewsList);
                layoutManager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
                adapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
                    @Override
                    public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                        ToastUtils.showShort(position);
                    }
                });
                break;
            default:
                adapter = new RVCategoryAdapter(R.layout.home_item_category, generalList);
                layoutManager = new LinearLayoutManager(getContext());
                break;
        }
        BD.recyclerView.setLayoutManager(layoutManager);
        BD.recyclerView.setAdapter(adapter);
        adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                ARouter.getInstance()
                        .build(RouterActivityPath.Home.WATCH_VIDEO)
                        .withInt("position", position)
                        .navigation();
            }
        });
    }

    /**
     * rv头
     *
     * @return
     */
    private View getHeaderView() {
        View view = getLayoutInflater().inflate(R.layout.home_head, (ViewGroup) BD.recyclerView.getParent(), false);
        imgList = new ArrayList<>();
        imgList.add("https://ss1.bdstatic.com/70cFuXSh_Q1YnxGkpoWK1HF6hhy/it/u=1500389290,3668597033&fm=26&gp=0.jpg");
        imgList.add("https://ss3.bdstatic.com/70cFv8Sh_Q1YnxGkpoWK1HF6hhy/it/u=2422983490,2140954611&fm=26&gp=0.jpg");
        imgList.add("https://ss1.bdstatic.com/70cFvXSh_Q1YnxGkpoWK1HF6hhy/it/u=915593634,1630526971&fm=26&gp=0.jpg");
        Banner banner = view.findViewById(R.id.banner);
        banner.setImages(imgList)
                .setImageLoader(new GlideImageLoader())
                .isAutoPlay(true)
                .setDelayTime(3000)
                .setIndicatorGravity(BannerConfig.RIGHT)
                .setOnBannerListener(new OnBannerListener() {
                    @Override
                    public void OnBannerClick(int position) {
                        ToastUtils.showShort(position);
                    }
                })
                .start();
        return view;
    }

    private void initRL() {
        BD.refreshLayout.setRefreshHeader(new PhoenixHeader(getContext()));
        BD.refreshLayout.setEnableRefresh(true);
        BD.refreshLayout.setEnableLoadMore(true);
        BD.refreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                firstNumber = 0;
                recommendList.clear();
                hotAndNewsList.clear();
                generalList.clear();
                loadData();
                BD.refreshLayout.finishRefresh();
            }
        });
        BD.refreshLayout.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                loadData();
                BD.refreshLayout.finishLoadMore();
            }
        });
        BD.refreshLayout.autoRefresh();
        BD.refreshLayout.autoLoadMore();
    }

    public void loadData() {
        Random r = new Random();
        switch (index) {
            case 0:
                BmobQuery<RecommendBean> recommendBmobQuery = new BmobQuery<>();
                recommendBmobQuery
                        .setSkip(firstNumber)
                        .setLimit(10)
                        .findObjects(new FindListener<RecommendBean>() {
                            @Override
                            public void done(List<RecommendBean> list, BmobException e) {
                                if (e == null) {
                                    if (list.size() > 0) {
                                        recommendList.addAll(list);
                                        firstNumber += 10;
                                        adapter.notifyDataSetChanged();
                                    } else {
                                        BD.refreshLayout.finishLoadMore();
                                        ToastUtils.showShort("已加载全部内容");
                                    }
                                } else {
                                    ToastUtils.showShort(e.getMessage());
                                }
                            }
                        });
                break;
            case 1:
            case 2:
                BmobQuery<HotAndNewsBean> hotAndNewsBmobQuery = new BmobQuery<>();
                hotAndNewsBmobQuery
                        .setSkip(firstNumber)
                        .setLimit(10)
                        .findObjects(new FindListener<HotAndNewsBean>() {
                            @Override
                            public void done(List<HotAndNewsBean> list, BmobException e) {
                                if (e == null) {
                                    Logger.e("wolf:"+list.size());
                                    if (list.size() > 0) {
                                        hotAndNewsList.addAll(list);
                                        firstNumber += 10;
                                        adapter.notifyDataSetChanged();
                                    } else {
                                        BD.refreshLayout.finishLoadMore();
                                        ToastUtils.showShort("已加载全部内容");
                                    }
                                } else {
                                    ToastUtils.showShort(e.getMessage());
                                }
                            }
                        });
                break;
            default:
                BmobQuery<GeneralBean> GeneralBeanBmobQuery = new BmobQuery<>();
                GeneralBeanBmobQuery
                        .setSkip(firstNumber)
                        .setLimit(10)
                        .findObjects(new FindListener<GeneralBean>() {
                            @Override
                            public void done(List<GeneralBean> list, BmobException e) {
                                if (e == null) {
                                    Logger.e("wolf:"+list.size());
                                    if (list.size() > 0) {
                                        generalList.addAll(list);
                                        firstNumber += 10;
                                        adapter.notifyDataSetChanged();
                                    } else {
                                        BD.refreshLayout.finishLoadMore();
                                        ToastUtils.showShort("已加载全部内容");
                                    }
                                } else {
                                    ToastUtils.showShort(e.getMessage());
                                }
                            }
                        });
                break;
        }
    }

    @Subscribe
    public void refresh(RefreshEvent event) {
        if (event.index == index && isVisible()) {
            BD.recyclerView.scrollToPosition(0);
            BD.refreshLayout.autoRefresh();
        }
    }
}
