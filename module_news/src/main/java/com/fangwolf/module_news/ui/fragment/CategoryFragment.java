package com.fangwolf.module_news.ui.fragment;

import android.view.View;
import android.view.ViewGroup;

import com.alibaba.android.arouter.launcher.ARouter;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.fangwolf.library_base.base.LazyFragment;
import com.fangwolf.library_base.router.RouterActivityPath;
import com.fangwolf.library_base.utils.ToastUtils;
import com.fangwolf.module_news.R;
import com.fangwolf.module_news.adapter.CategoryAdapter;
import com.fangwolf.module_news.bean.DataBean;
import com.fangwolf.module_news.databinding.NewsFragmentCategoryBinding;
import com.fangwolf.module_news.net.DataService;
import com.scwang.smartrefresh.header.PhoenixHeader;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * @Auther 獠牙血狼
 * @Date 2019/4/13
 * @Desc 分类的fragment
 */
public class CategoryFragment extends LazyFragment<NewsFragmentCategoryBinding> {
    private CategoryAdapter adapter;
    private List<DataBean.ResultsBean> list;
    private String type;
    private int page;
    private Retrofit retrofit;
    private DataService service;
    private Call<DataBean> call;
    private boolean isWelfare = false;

    @Override
    public int getLayoutId() {
        return R.layout.news_fragment_category;
    }

    @Override
    public void initView() {
        type = getArguments().getString("type", "all");
        if (type.equals("全部")) {
            type = "all";
        }
        if (type.equals("福利")) {
            isWelfare = true;
        }
        page = 1;
        initApi();
    }

    @Override
    public void initData() {
        initRV();
    }

    @Override
    protected void onLazyLoad() {
        initRL();
    }

    @Override
    public void onClick(View v) {

    }

    /**
     * 初始化retrofit网络请求
     */
    private void initApi() {
        retrofit = new Retrofit.Builder()
                .baseUrl("http://gank.io/api/")
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        service = retrofit.create(DataService.class);
    }

    private void initRV() {
        list = new ArrayList<>();
        adapter = new CategoryAdapter(R.layout.news_item_category, list, isWelfare);
        if (isWelfare) {
            GridLayoutManager layoutManager = new GridLayoutManager(getContext(), 2);
            BD.recyclerView.setLayoutManager(layoutManager);
        } else {
            LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
            BD.recyclerView.setLayoutManager(layoutManager);
        }
        BD.recyclerView.setAdapter(adapter);
        adapter.setEmptyView(getLayoutInflater().inflate(R.layout.rv_empty, (ViewGroup) BD.recyclerView.getParent(), false));
        adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                ARouter.getInstance()
                        .build(RouterActivityPath.News.WATCH_ARTICLE)
                        .withString("url", list.get(position).getUrl())
                        .navigation();
            }
        });
    }

    private void initRL() {
        BD.refreshLayout.setRefreshHeader(new PhoenixHeader(getContext()));
        BD.refreshLayout.setEnableRefresh(true);
        BD.refreshLayout.setEnableLoadMore(true);
        BD.refreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                page = 0;
                list.clear();
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


    private void loadData() {
        call = service.getCall(type, "15", String.valueOf(page));
        call.enqueue(new Callback<DataBean>() {
            @Override
            public void onResponse(Call<DataBean> call, Response<DataBean> response) {
                list.addAll(response.body().getResults());
                page++;
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<DataBean> call, Throwable t) {
                ToastUtils.showShort(t.getMessage());
            }
        });
    }
}
