package com.fangwolf.module_main;

import android.os.Bundle;
import android.view.View;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.fangwolf.library_base.base.BaseActivity;
import com.fangwolf.library_base.router.RouterActivityPath;
import com.fangwolf.library_base.router.RouterFragmentPath;
import com.fangwolf.module_main.databinding.MainActivityMainBinding;

import java.util.ArrayList;
import java.util.List;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import me.majiajie.pagerbottomtabstrip.NavigationController;
import me.majiajie.pagerbottomtabstrip.listener.OnTabItemSelectedListener;

@Route(path = RouterActivityPath.Main.MAIN)
public class MainActivity extends BaseActivity<MainActivityMainBinding> {
    private List<Fragment> mFragments;

    @Override
    protected int setLayoutID() {
        return R.layout.main_activity_main;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        initFragment();
        initBottomTab();
    }

    @Override
    protected void initData(Bundle savedInstanceState) {

    }

    @Override
    public void onClick(View view) {

    }

    private void initFragment() {
//ARouter拿到多Fragment(这里需要通过ARouter获取，不能直接new,因为在组件独立运行时，宿主app是没有依赖其他组件，所以new不到其他组件的Fragment)
        Fragment homeFragment = (Fragment) ARouter.getInstance().build(RouterFragmentPath.Home.MAIN).navigation();
        Fragment newsFragment = (Fragment) ARouter.getInstance().build(RouterFragmentPath.News.MAIN).navigation();
        Fragment chatFragment = (Fragment) ARouter.getInstance().build(RouterFragmentPath.Chat.MAIN).navigation();
        Fragment mineFragment = (Fragment) ARouter.getInstance().build(RouterFragmentPath.Mine.MAIN).navigation();
        mFragments = new ArrayList<>();
        mFragments.add(homeFragment);
        mFragments.add(newsFragment);
        mFragments.add(chatFragment);
        mFragments.add(mineFragment);
        if (homeFragment != null) {
            //默认选中第一个
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            transaction.add(R.id.fl_root, homeFragment);
            transaction.commitAllowingStateLoss();
        }
    }

    /**
     * 底部导航栏
     */
    private void initBottomTab() {
        NavigationController navigationController = BD.pagerBottomTab.material()
                .addItem(R.mipmap.ic_launcher, "首页")
                .addItem(R.mipmap.ic_launcher, "资讯")
                .addItem(R.mipmap.ic_launcher, "发布")
                .addItem(R.mipmap.ic_launcher, "讨论")
                .addItem(R.mipmap.ic_launcher, "我的")
                .build();
        //底部按钮的点击事件监听
        navigationController.addTabItemSelectedListener(new OnTabItemSelectedListener() {
            @Override
            public void onSelected(int index, int old) {
                Fragment currentFragment = mFragments.get(index);
                if (currentFragment != null) {
                    FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                    transaction.replace(R.id.fl_root, currentFragment);
                    transaction.commitAllowingStateLoss();
                }
            }

            @Override
            public void onRepeat(int index) {
            }
        });
    }
}
