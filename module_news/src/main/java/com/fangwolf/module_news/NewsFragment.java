package com.fangwolf.module_news;


import android.view.View;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.fangwolf.library_base.base.BaseFragment;
import com.fangwolf.library_base.router.RouterFragmentPath;
import com.fangwolf.module_news.databinding.NewsFragmentNewsBinding;

/**
 * @Auther 獠牙血狼
 * @Date 2019/3/20
 * @Desc 新闻
 */
@Route(path = RouterFragmentPath.News.MAIN)
public class NewsFragment extends BaseFragment<NewsFragmentNewsBinding> {

    @Override
    public int getLayoutId() {
        return R.layout.news_fragment_news;
    }

    @Override
    public void initView() {

    }

    @Override
    public void initData() {

    }

    @Override
    public void onClick(View view) {

    }
}
