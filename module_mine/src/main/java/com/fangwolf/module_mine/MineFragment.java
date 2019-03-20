package com.fangwolf.module_mine;


import android.view.View;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.fangwolf.library_base.base.BaseFragment;
import com.fangwolf.library_base.router.RouterFragmentPath;

/**
 * @Auther 獠牙血狼
 * @Date 2019/3/4
 * @Desc 我的页面
 */
@Route(path = RouterFragmentPath.Mine.MAIN)
public class MineFragment extends BaseFragment {


    @Override
    public int getLayoutId() {
        return R.layout.mine_fragment_mine;
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
