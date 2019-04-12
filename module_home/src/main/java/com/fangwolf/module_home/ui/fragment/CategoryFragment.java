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
import com.fangwolf.module_home.sundries.ApiUtils;
import com.fangwolf.module_home.sundries.GlideImageLoader;
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

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

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
                        doYouLike(view, position);
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
        switch (index) {
            case 0:
                ApiUtils.getRecommend(firstNumber, new ApiUtils.RecommendListener() {
                    @Override
                    public void success(List<RecommendBean> list) {
                        if (list.size() > 0) {
                            recommendList.addAll(list);
                            firstNumber += 10;
                            adapter.notifyDataSetChanged();
                        } else {
                            BD.refreshLayout.finishLoadMore();
                            ToastUtils.showShort("已加载全部内容");
                        }
                    }
                });
                break;
            case 1:
            case 2:
                ApiUtils.getHotAndNews(firstNumber, new ApiUtils.HotAndNewsListener() {
                    @Override
                    public void success(List<HotAndNewsBean> list) {
                        if (list.size() > 0) {
                            hotAndNewsList.addAll(list);
                            firstNumber += 10;
                            adapter.notifyDataSetChanged();
                        } else {
                            BD.refreshLayout.finishLoadMore();
                            ToastUtils.showShort("已加载全部内容");
                        }
                    }
                });
                break;
            default:
                ApiUtils.getDefault(firstNumber, new ApiUtils.DefaultListener() {
                    @Override
                    public void success(List<GeneralBean> list) {
                        if (list.size() > 0) {
                            generalList.addAll(list);
                            firstNumber += 10;
                            adapter.notifyDataSetChanged();
                        } else {
                            BD.refreshLayout.finishLoadMore();
                            ToastUtils.showShort("已加载全部内容");
                        }

                    }
                });
                break;
        }
    }

    /**
     * 点击热门和推荐的喜欢或者不喜欢
     *
     * @param view
     * @param position
     */
    private void doYouLike(View view, final int position) {
        final String tip;
        if (view.getId() == R.id.btn_like) {
            tip = "感谢你的喜欢";
            hotAndNewsList.get(position).setVoteNumber(hotAndNewsList.get(position).getVoteNumber() + 1);
        } else {
            tip = "我们会继续努力";
            hotAndNewsList.get(position).setVoteNumber(hotAndNewsList.get(position).getVoteNumber() - 1);
        }
        ApiUtils.likeIt(hotAndNewsList, position, new ApiUtils.IsLikeListener() {
            @Override
            public void success() {
                adapter.notifyItemChanged(position);
                ToastUtils.showShort(tip);
            }
        });
    }

    @Subscribe
    public void refresh(RefreshEvent event) {
        if (event.index == index && isVisible()) {
            BD.recyclerView.scrollToPosition(0);
            BD.refreshLayout.autoRefresh();
        }
    }
}
