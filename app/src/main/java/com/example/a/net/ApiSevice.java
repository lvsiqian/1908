package com.example.a.net;

import com.example.a.Pbean;

import io.reactivex.Flowable;
import retrofit2.http.GET;

public interface ApiSevice {
    String purl="http://yun918.cn/live/";
    @GET("xsxcjfsj.json")
    Flowable<Pbean> getData();
}
