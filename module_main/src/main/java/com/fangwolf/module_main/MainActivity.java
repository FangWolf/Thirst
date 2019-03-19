package com.fangwolf.module_main;

import android.os.Bundle;
import android.view.View;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.fangwolf.library_base.base.BaseActivity;
import com.fangwolf.library_base.router.RouterActivityPath;

import androidx.core.content.ContextCompat;
import me.majiajie.pagerbottomtabstrip.NavigationController;
import me.majiajie.pagerbottomtabstrip.PageNavigationView;

@Route(path = RouterActivityPath.Main.PAGE_MAIN)
public class MainActivity extends BaseActivity {
    PageNavigationView pagerBottomTab;

    @Override
    protected int setLayoutID() {
        return R.layout.main_activity_main;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        pagerBottomTab = findViewById(R.id.pager_bottom_tab);
        initBottomTab();
    }

    @Override
    protected void initData(Bundle savedInstanceState) {

    }

    @Override
    public void onClick(View view) {

    }

    /**
     * 底部导航栏
     */
    private void initBottomTab() {
        NavigationController navigationController = pagerBottomTab.material()
                .addItem(R.mipmap.ic_launcher, "首页")
                .addItem(R.mipmap.ic_launcher, "工作")
                .addItem(R.mipmap.ic_launcher, "发布")
                .addItem(R.mipmap.ic_launcher, "消息")
                .addItem(R.mipmap.ic_launcher, "我的")
                .build();
    }
}
