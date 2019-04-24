package com.fangwolf.module_mine.ui;


import android.view.View;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.fangwolf.library_base.base.BaseFragment;
import com.fangwolf.library_base.router.RouterFragmentPath;
import com.fangwolf.module_mine.R;
import com.fangwolf.module_mine.databinding.MineFragmentMineBinding;

/**
 * @Auther 獠牙血狼
 * @Date 2019/3/4
 * @Desc 我的页面
 */
@Route(path = RouterFragmentPath.Mine.MAIN)
public class MineFragment extends BaseFragment<MineFragmentMineBinding> {

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
