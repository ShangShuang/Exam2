package com.example.exam2.net;

import com.example.exam2.bean.PayBean;

public interface ConversionCallBack {
    void onSuccess(PayBean payBean);

    void onFail(String error);
}
