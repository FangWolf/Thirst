package com.fangwolf.module_home.ui.fragment;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

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
    private int categoryId;
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
        categoryId = bundle.getInt("id");
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
        adapter.setEmptyView(getLayoutInflater().inflate(R.layout.rv_empty, (ViewGroup) BD.recyclerView.getParent(), false));
        BD.recyclerView.setLayoutManager(layoutManager);
        BD.recyclerView.setAdapter(adapter);
        adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                String videoId = null;
                if (categoryId == 11) {
                    videoId = recommendList.get(position).getVideoId();
                } else if (categoryId == 22 || categoryId == 33) {
                    videoId = hotAndNewsList.get(position).getVideoId();
                } else {
                    videoId = generalList.get(position).getVideoId();
                }
                ARouter.getInstance()
                        .build(RouterActivityPath.Home.WATCH_VIDEO)
                        .withString("videoId", videoId)
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
        imgList.add("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1560408836&di=ef2bbe3b1b1ec1306c37c290bfe265c6&imgtype=jpg&er=1&src=http%3A%2F%2Fs11.51cto.com%2Fimages%2F201810%2F29%2F7a1dacafe32ea49b784334dbd86abb91.png");
        imgList.add("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1559813839341&di=f147a1ce3a4eb80881ed0a051bb4da26&imgtype=0&src=http%3A%2F%2Fimg.haokecheng.com%2Fupimages%2F4B%2FF6%2F83%2F70%2F1E5AFE1B.jpg");
        imgList.add("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1559814020422&di=f1133fcdf080a70f878a9923faeccf24&imgtype=0&src=http%3A%2F%2Ft.elecfans.com%2FPublic%2FUpload%2FComImg%2F20181119%2F5bf2490f14c33.jpg");
        Banner banner = view.findViewById(R.id.banner);
        banner.setImages(imgList)
                .setImageLoader(new GlideImageLoader())
                .isAutoPlay(true)
                .setDelayTime(3000)
                .setIndicatorGravity(BannerConfig.RIGHT)
                .start();
//                .setOnBannerListener(new OnBannerListener() {
//                    @Override
//                    public void OnBannerClick(int position) {
//                        ToastUtils.showShort(position);
//                    }
//                })
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
                ApiUtils.getDefault(categoryId, firstNumber, new ApiUtils.DefaultListener() {
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
