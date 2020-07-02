package com.example.a.model;

import com.example.a.Pbean;
import com.example.a.net.ApiSevice;
import com.example.a.net.ResultCallBack;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.subscribers.ResourceSubscriber;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class Mianmodel {
    public void getdata(ResultCallBack<Pbean> pbeanResultCallBack) {
        new Retrofit.Builder()
                .baseUrl(ApiSevice.purl)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build()
                .create(ApiSevice.class)
                .getData()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new ResourceSubscriber<Pbean>() {
                    @Override
                    public void onNext(Pbean pbean) {
                        pbeanResultCallBack.onsuccess(pbean);
                    }

                    @Override
                    public void onError(Throwable t) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });

    }
}
