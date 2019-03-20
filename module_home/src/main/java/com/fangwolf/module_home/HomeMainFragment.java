package com.fangwolf.module_home;


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
 * @Desc 首页
 */
@Route(path = RouterFragmentPath.Home.MAIN)
public class HomeMainFragment extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.home_fragment_home_main, container, false);
    }

}
