package com.example.exam2.net;

import com.example.exam2.bean.PayBean;

import io.reactivex.Observable;
import retrofit2.http.GET;

public interface ApiService {
    String baseUrl = "http://yun918.cn/";

    @GET("ks/jiekou1.json")
    Observable<PayBean> getData();
}
