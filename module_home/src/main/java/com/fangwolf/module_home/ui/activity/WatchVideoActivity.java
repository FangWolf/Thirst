package com.fangwolf.module_home.ui.activity;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.transition.Transition;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.DecelerateInterpolator;
import android.widget.ImageView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.fangwolf.library_base.base.BaseActivity;
import com.fangwolf.library_base.router.RouterActivityPath;
import com.fangwolf.module_home.R;
import com.fangwolf.module_home.adapter.VPWatchVideoAdapter;
import com.fangwolf.module_home.databinding.HomeActivityWatchVideoBinding;
import com.fangwolf.module_home.sundries.ScaleTransitionPagerTitleView;
import com.jaeger.library.StatusBarUtil;
import com.shuyu.gsyvideoplayer.GSYVideoManager;
import com.shuyu.gsyvideoplayer.utils.OrientationUtils;

import net.lucode.hackware.magicindicator.ViewPagerHelper;
import net.lucode.hackware.magicindicator.buildins.UIUtil;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.CommonNavigator;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.CommonNavigatorAdapter;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.IPagerIndicator;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.IPagerTitleView;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.indicators.LinePagerIndicator;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.titles.SimplePagerTitleView;

import java.util.ArrayList;
import java.util.List;

import androidx.core.content.ContextCompat;
import androidx.core.view.ViewCompat;

/**
 * @Auther 獠牙血狼
 * @Date 2019/3/29
 * @Desc 看视频
 */
@Route(path = RouterActivityPath.Home.WATCH_VIDEO)
public class WatchVideoActivity extends BaseActivity<HomeActivityWatchVideoBinding> {
    private List<String> mDataList;
    private String url = "http://9890.vod.myqcloud.com/9890_4e292f9a3dd011e6b4078980237cc3d3.f20.mp4";
    private OrientationUtils orientationUtils;
    private boolean isTransition;
    private Transition transition;
    public final static String IMG_TRANSITION = "IMG_TRANSITION";
    public final static String TRANSITION = "TRANSITION";

    @Override
    protected int setLayoutID() {
        return R.layout.home_activity_watch_video;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        StatusBarUtil.setColor(this, ContextCompat.getColor(this, R.color.white));
        initMagicIndicator();
        BD.viewPager.setAdapter(new VPWatchVideoAdapter(getSupportFragmentManager(), BD.viewPager));
    }

    @Override
    protected void initData(Bundle savedInstanceState) {
        isTransition = getIntent().getBooleanExtra(TRANSITION, false);
        initVideo();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (orientationUtils != null)
            orientationUtils.releaseListener();
    }

    @Override
    public void onClick(View view) {

    }

    @Override
    public void onBackPressed() {
        //先返回正常状态
//        if (orientationUtils.getScreenType() == ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE) {
//            BD.videoPlayer.getFullscreenButton().performClick();
//            return;
//        }
        //释放所有
        BD.videoPlayer.setVideoAllCallBack(null);
        GSYVideoManager.releaseAllVideos();
        super.onBackPressed();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                finish();
                overridePendingTransition(R.anim.abc_fade_in, R.anim.abc_fade_out);
            }
        }, 500);
    }

    private void initMagicIndicator() {
        mDataList = new ArrayList<>();
        mDataList.add("介绍");
        mDataList.add("评论");
        BD.magicIndicator.setBackgroundColor(Color.WHITE);
        CommonNavigator commonNavigator = new CommonNavigator(this);
        commonNavigator.setScrollPivotX(0.8f);
        commonNavigator.setAdjustMode(true);
        commonNavigator.setAdapter(new CommonNavigatorAdapter() {
            @Override
            public int getCount() {
                return mDataList == null ? 0 : mDataList.size();
            }

            @Override
            public IPagerTitleView getTitleView(Context context, final int index) {
                SimplePagerTitleView simplePagerTitleView = new ScaleTransitionPagerTitleView(context);
                simplePagerTitleView.setText(mDataList.get(index));
                simplePagerTitleView.setTextSize(18);
                simplePagerTitleView.setNormalColor(Color.parseColor("#616161"));
                simplePagerTitleView.setSelectedColor(Color.parseColor("#f57c00"));
                simplePagerTitleView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        BD.viewPager.setCurrentItem(index);
                    }
                });
                return simplePagerTitleView;
            }

            @Override
            public IPagerIndicator getIndicator(Context context) {
                LinePagerIndicator indicator = new LinePagerIndicator(context);
                indicator.setStartInterpolator(new AccelerateInterpolator());
                indicator.setEndInterpolator(new DecelerateInterpolator(1.6f));
                indicator.setYOffset(UIUtil.dip2px(context, 39));
                indicator.setLineHeight(UIUtil.dip2px(context, 2));
                indicator.setColors(Color.parseColor("#f57c00"));
                return indicator;
            }
        });
        BD.magicIndicator.setNavigator(commonNavigator);
        ViewPagerHelper.bind(BD.magicIndicator, BD.viewPager);
    }

    private void initVideo() {
        //设置视频url
        BD.videoPlayer.setUp(url, true, "测试视频");
        //增加封面
        ImageView imageView = new ImageView(this);
        imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
        imageView.setImageResource(R.mipmap.ic_launcher_round);
        BD.videoPlayer.setThumbImageView(imageView);
        //设置旋转
        orientationUtils = new OrientationUtils(this, BD.videoPlayer);
        //设置全屏按键功能,这是使用的是选择屏幕，而不是全屏
        BD.videoPlayer.getFullscreenButton().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BD.videoPlayer.startWindowFullscreen(WatchVideoActivity.this, false, true);
            }
        });
        //设置返回键
        BD.videoPlayer.getBackButton().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
    }

    private void initTransition() {
        postponeEnterTransition();
        ViewCompat.setTransitionName(BD.videoPlayer, IMG_TRANSITION);
        addTransitionListener();
        startPostponedEnterTransition();
    }

    private boolean addTransitionListener() {
        transition = getWindow().getSharedElementEnterTransition();
        if (transition != null) {
            transition.addListener(new Transition.TransitionListener() {
                @Override
                public void onTransitionStart(Transition transition) {

                }

                @Override
                public void onTransitionEnd(Transition transition) {
                    BD.videoPlayer.startPlayLogic();
                    transition.removeListener(this);
                }

                @Override
                public void onTransitionCancel(Transition transition) {

                }

                @Override
                public void onTransitionPause(Transition transition) {

                }

                @Override
                public void onTransitionResume(Transition transition) {

                }
            });
            return true;
        }
        return false;
    }
}
