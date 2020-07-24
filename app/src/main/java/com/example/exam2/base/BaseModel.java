package com.example.exam2.base;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

public abstract class BaseModel {
    ///这也是一个容器,Rxjava专门写的用来放Disposable,调用dispose()可以把容器所有的
    //Disposable 的网络请求和订阅关系取消
    private CompositeDisposable mDisposable;

    //由M层自己把网络和订阅关系保存起来，便于取消
    public void addDisposable(Disposable disposable) {
        if (mDisposable == null) {
            synchronized (BaseModel.class) {
                if (mDisposable == null) {
                    mDisposable = new CompositeDisposable();
                }
            }
        }
        //把disposable添加到mDisposable容器中
        mDisposable.add(disposable);
    }

    //由M层自己把网络和订阅关系取消
    public void dispose() {
        mDisposable.dispose();
    }

    //移除disposable
    public void removeDispose(Disposable disposable) {
        if (disposable != null) {
            mDisposable.remove(disposable);
        }
    }
}
