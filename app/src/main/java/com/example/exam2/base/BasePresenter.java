package com.example.exam2.base;

import java.util.ArrayList;

public abstract class BasePresenter<V extends BaseView> {
    public V mView;
    private ArrayList<BaseModel> models = new ArrayList<>();

    public BasePresenter() {
        initModel();
    }

    protected abstract void initModel();

    public void bindView(V view) {
        this.mView = view;
    }

    public void addModel(BaseModel model) {
        models.add(model);
    }

    public void destroy() {
        mView=null;
        for (int i = 0; i < models.size(); i++) {
            BaseModel baseModel = models.get(i);
            baseModel.dispose();
        }
    }
}
