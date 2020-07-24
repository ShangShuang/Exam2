package com.example.exam2.model;

import com.example.exam2.base.BaseModel;
import com.example.exam2.bean.PayBean;
import com.example.exam2.net.ApiService;
import com.example.exam2.net.ConversionCallBack;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class ConversionModel extends BaseModel {
    public void getData(ConversionCallBack conversionCallBack) {
        Retrofit retrofit = new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .baseUrl(ApiService.baseUrl)
                .build();
        ApiService apiService = retrofit.create(ApiService.class);
        Observable<PayBean> observable = apiService.getData();
        observable
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<PayBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        addDisposable(d);
                    }

                    @Override
                    public void onNext(PayBean payBean) {
                        conversionCallBack.onSuccess(payBean);
                    }

                    @Override
                    public void onError(Throwable e) {
                        conversionCallBack.onFail(e.getMessage());
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
}
