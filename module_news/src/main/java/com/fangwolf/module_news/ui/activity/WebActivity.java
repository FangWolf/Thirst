package com.fangwolf.module_news.ui.activity;

import android.os.Bundle;
import android.view.View;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.fangwolf.library_base.base.BaseActivity;
import com.fangwolf.library_base.router.RouterActivityPath;
import com.fangwolf.library_base.utils.ToastUtils;
import com.fangwolf.module_news.R;
import com.fangwolf.module_news.databinding.NewsActivityWebBinding;

/**
 * @Auther 獠牙血狼
 * @Date 2019/4/15
 * @Desc webView
 */
@Route(path = RouterActivityPath.News.WATCH_ARTICLE)
public class WebActivity extends BaseActivity<NewsActivityWebBinding> {
    private String url;

    @Override
    protected int setLayoutID() {
        return R.layout.news_activity_web;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        BD.webView.getSettings().setSupportZoom(true);
        BD.webView.getSettings().setBuiltInZoomControls(true);
        url = getIntent().getStringExtra("url");
        ToastUtils.showLong("努力加载中...");
    }

    @Override
    protected void initData(Bundle savedInstanceState) {
        BD.webView.loadUrl(url);
    }

    @Override
    public void onClick(View v) {

    }
}
