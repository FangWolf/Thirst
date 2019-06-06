package com.fangwolf.module_main.ui;

import android.os.Bundle;
import android.view.View;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.fangwolf.library_base.base.BaseActivity;
import com.fangwolf.library_base.router.RouterActivityPath;
import com.fangwolf.library_base.router.RouterFragmentPath;
import com.fangwolf.module_main.R;
import com.fangwolf.module_main.databinding.MainActivityMainBinding;

import java.util.ArrayList;
import java.util.List;

import me.majiajie.pagerbottomtabstrip.NavigationController;
import me.majiajie.pagerbottomtabstrip.listener.OnTabItemSelectedListener;

@Route(path = RouterActivityPath.Main.MAIN)
public class MainActivity extends BaseActivity<MainActivityMainBinding> {
    private List<Fragment> mFragments;
    private PostDialog postDialog;
    private NavigationController navigationController;
    private Fragment homeFragment;
    private Fragment newsFragment;
    private Fragment chatFragment;
    private Fragment mineFragment;

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
        postDialog = new PostDialog();

    }

    @Override
    public void onClick(View view) {

    }

    private void initFragment() {
        //ARouter拿到多Fragment(这里需要通过ARouter获取，不能直接new,因为在组件独立运行时，宿主app是没有依赖其他组件，所以new不到其他组件的Fragment)
        homeFragment = (Fragment) ARouter.getInstance().build(RouterFragmentPath.Home.MAIN).navigation();
        newsFragment = (Fragment) ARouter.getInstance().build(RouterFragmentPath.News.MAIN).navigation();
        chatFragment = (Fragment) ARouter.getInstance().build(RouterFragmentPath.Chat.MAIN).navigation();
        mineFragment = (Fragment) ARouter.getInstance().build(RouterFragmentPath.Mine.MAIN).navigation();
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
        navigationController = BD.pagerBottomTab.material()
                .addItem(R.mipmap.ic_home, "首页")
                .addItem(R.mipmap.ic_news, "资讯")
//                .addItem(R.mipmap.ic_post, "发布")
                .addItem(R.mipmap.ic_chat, "讨论")
                .addItem(R.mipmap.ic_mine, "我的")
                .build();
        //底部按钮的点击事件监听
        navigationController.addTabItemSelectedListener(new OnTabItemSelectedListener() {
            @Override
            public void onSelected(int index, int old) {
//                switch (index) {
//                    case 0:
//                    case 1:
//                    case 3:
//                    case 4:
//                        changePage(index);
//                        break;
//                    case 2:
//                        postDialog.show(getSupportFragmentManager(), "post");
//                        navigationController.setSelect(old);
//                        break;
//                }
                changePage(index);
            }

            @Override
            public void onRepeat(int index) {
//                if (index == 2) {
//                    postDialog.show(getSupportFragmentManager(), "post");
//                }
            }
        });
    }

    /**
     * 切换页面
     * 中间一个是发布弹窗
     *
     * @param index
     */
    private void changePage(int index) {
//        if (index > 2) {
//            index -= 1;
//        }
        Fragment currentFragment = mFragments.get(index);
        if (currentFragment != null) {
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            transaction.replace(R.id.fl_root, currentFragment);
            transaction.commitAllowingStateLoss();
        }
    }
}
