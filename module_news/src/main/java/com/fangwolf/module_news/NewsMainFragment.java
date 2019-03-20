package com.fangwolf.module_news;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.fangwolf.library_base.router.RouterFragmentPath;

import androidx.fragment.app.Fragment;

/**
 * @Auther 獠牙血狼
 * @Date 2019/3/20
 * @Desc 新闻
 */
@Route(path = RouterFragmentPath.News.MAIN)
public class NewsMainFragment extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.news_fragment_news_main, container, false);
    }

}
