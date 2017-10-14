package com.telecorp.dashqueue.base;

/**
 * Created by Saran on 14/10/2560.
 */

public interface BasePresenter<V extends BaseView> {

    public void subscribe(V view);

    public void unsubscribe();


}
