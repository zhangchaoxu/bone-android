package com.idogfooding.bone.rx;

import rx.Subscriber;

/**
 * 为RxBus使用的Subscriber, 主要提供next事件的try,catch
 * <p/>
 * Created by YoKeyword on 16/7/20.
 */
public abstract class RxBusSubscriber<T> extends Subscriber<T> {

    @Override
    public void onNext(T t) {
        try {
            onEvent(t);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onCompleted() {
    }

    @Override
    public void onError(Throwable e) {
        e.printStackTrace();
    }

    protected abstract void onEvent(T t);
}
