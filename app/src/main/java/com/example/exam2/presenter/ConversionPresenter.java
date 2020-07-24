package com.example.exam2.presenter;

import com.example.exam2.base.BasePresenter;
import com.example.exam2.bean.PayBean;
import com.example.exam2.model.ConversionModel;
import com.example.exam2.net.ConversionCallBack;
import com.example.exam2.view.ConversionView;

public class ConversionPresenter extends BasePresenter<ConversionView> implements ConversionCallBack {
    private ConversionModel conversionModel;

    @Override
    protected void initModel() {
        conversionModel = new ConversionModel();
        addModel(conversionModel);
    }

    public void getData() {
        conversionModel.getData(this);
    }

    @Override
    public void onSuccess(PayBean payBean) {
        mView.setData(payBean);
    }

    @Override
    public void onFail(String error) {
        mView.showToast(error);
    }
}
