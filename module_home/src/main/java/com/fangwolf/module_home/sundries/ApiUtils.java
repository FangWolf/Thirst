package com.fangwolf.module_home.sundries;

import com.fangwolf.library_base.utils.ToastUtils;
import com.fangwolf.module_home.bean.CategoryBean;
import com.fangwolf.module_home.bean.GeneralBean;
import com.fangwolf.module_home.bean.HotAndNewsBean;
import com.fangwolf.module_home.bean.RecommendBean;
import com.fangwolf.module_home.bean.VideosBean;

import java.util.List;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;
import cn.bmob.v3.listener.UpdateListener;

/**
 * @Auther 獠牙血狼
 * @Date 2019/4/10
 * @Desc 访问远程数据库工具
 */
public class ApiUtils {
    private static CategoryListener categoryListener;
    private static RecommendListener recommendListener;
    private static HotAndNewsListener hotAndNewsListener;
    private static DefaultListener defaultListener;

    private static IsLikeListener isLikeListener;
    private static VideoListener videoListener;

    /**
     * 首页分类
     *
     * @param listener
     */
    public static void getCategory(CategoryListener listener) {
        categoryListener = listener;
        BmobQuery<CategoryBean> categoryBmobQuery = new BmobQuery<>();
        categoryBmobQuery.findObjects(new FindListener<CategoryBean>() {
            @Override
            public void done(List<CategoryBean> list, BmobException e) {
                if (e == null) {
                    categoryListener.success(list);
                } else {
                    ToastUtils.showShort(e.getMessage());
                }
            }
        });
    }

    /**
     * 获取推荐
     *
     * @param firstNumber
     * @param listener
     */
    public static void getRecommend(int firstNumber, RecommendListener listener) {
        recommendListener = listener;
        BmobQuery<RecommendBean> recommendBmobQuery = new BmobQuery<>();
        recommendBmobQuery
                .setSkip(firstNumber)
                .setLimit(10)
                .findObjects(new FindListener<RecommendBean>() {
                    @Override
                    public void done(List<RecommendBean> list, BmobException e) {
                        if (e == null) {
                            recommendListener.success(list);
                        } else {
                            ToastUtils.showShort(e.getMessage());
                        }
                    }
                });
    }

    /**
     * 获取热门或最新
     *
     * @param firstNumber
     * @param listener
     */
    public static void getHotAndNews(int firstNumber, HotAndNewsListener listener) {
        hotAndNewsListener = listener;
        BmobQuery<HotAndNewsBean> hotAndNewsBmobQuery = new BmobQuery<>();
        hotAndNewsBmobQuery
                .setSkip(firstNumber)
                .setLimit(10)
                .findObjects(new FindListener<HotAndNewsBean>() {
                    @Override
                    public void done(List<HotAndNewsBean> list, BmobException e) {
                        if (e == null) {
                            hotAndNewsListener.success(list);
                        } else {
                            ToastUtils.showShort(e.getMessage());
                        }
                    }
                });
    }

    /**
     * 获取其他的通用分类
     *
     * @param firstNumber
     * @param listener
     */
    public static void getDefault(int categoryId, int firstNumber, DefaultListener listener) {
        defaultListener = listener;
        BmobQuery<GeneralBean> GeneralBeanBmobQuery = new BmobQuery<>();
        GeneralBeanBmobQuery
                .addWhereEqualTo("categoryId", String.valueOf(categoryId))
                .setSkip(firstNumber)
                .setLimit(10)
                .findObjects(new FindListener<GeneralBean>() {
                    @Override
                    public void done(List<GeneralBean> list, BmobException e) {
                        if (e == null) {
                            defaultListener.success(list);
                        } else {
                            ToastUtils.showShort(e.getMessage());
                        }
                    }
                });
    }

    public static void likeIt(List<HotAndNewsBean> list, int position, IsLikeListener likeListener) {
        isLikeListener = likeListener;
        list.get(position).update(list.get(position).getObjectId(), new UpdateListener() {
            @Override
            public void done(BmobException e) {
                if (e == null) {
                    isLikeListener.success();
                } else {
                    ToastUtils.showShort("更新失败：" + e.getMessage());
                }
            }

        });
    }

    /**
     * 查找视频
     *
     * @param videoId
     */
    public static void getVideo(String videoId, VideoListener listener) {
        videoListener = listener;
        BmobQuery<VideosBean> videosBmobQuery = new BmobQuery<>();
        videosBmobQuery
                .addWhereEqualTo("videoId", videoId)
                .findObjects(new FindListener<VideosBean>() {
                    @Override
                    public void done(List<VideosBean> list, BmobException e) {
                        if (e == null) {
                            videoListener.success(list);
                        } else {
                            ToastUtils.showShort(e.getMessage());
                        }
                    }
                });

    }

    public interface CategoryListener {
        void success(List<CategoryBean> list);
    }

    public interface RecommendListener {
        void success(List<RecommendBean> list);
    }

    public interface HotAndNewsListener {
        void success(List<HotAndNewsBean> list);
    }

    public interface DefaultListener {
        void success(List<GeneralBean> list);
    }

    public interface IsLikeListener {
        void success();
    }

    public interface VideoListener {
        void success(List<VideosBean> list);
    }

}
