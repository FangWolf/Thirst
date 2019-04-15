package com.fangwolf.module_news.net;

import com.fangwolf.module_news.bean.DataBean;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * @Auther 獠牙血狼
 * @Date 2019/4/14
 * @Desc 获取数据
 */
public interface DataService {
    @GET("data/{category}/{number}/{page}")
    Call<DataBean> getCall(@Path("category") String category,
                           @Path("number") String number,
                           @Path("page") String page);
}
