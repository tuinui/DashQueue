package com.telecorp.dashqueue.ui.main.contract;

import android.support.annotation.Nullable;

import com.telecorp.dashqueue.api.TelecorpApiInterface;
import com.telecorp.dashqueue.api.model.HospitalItem;
import com.telecorp.dashqueue.utils.SafeCollectionUtils;
import com.telecorp.dashqueue.utils.schedulers.BaseSchedulerProvider;

import java.util.ArrayList;

import javax.inject.Inject;

import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;

/**
 * Created by Saran on 14/10/2560.
 */

public class MainActivityPresenter implements MainActivityContract.Presenter {

    @Inject
    BaseSchedulerProvider mSchedulerProvider;
    @Inject
    TelecorpApiInterface mApi;

    @Nullable
    private MainActivityContract.View mView;
    private ArrayList<HospitalItem> mDatas;

    @Inject
    public MainActivityPresenter(TelecorpApiInterface mApi, BaseSchedulerProvider schedulerProvider) {
        this.mApi = mApi;
        this.mSchedulerProvider = schedulerProvider;
    }


    @Override
    public void subscribe(MainActivityContract.View view) {
        mView = view;
        if (!SafeCollectionUtils.isEmpty(mDatas)) {
            loadData();
        }
    }

    @Override
    public void unsubscribe() {
        mView = null;
    }

    @Override
    public void loadData() {
        if (null != mView) {
            mView.showLoading(true);
        }

        mApi.getHospital()
                .subscribeOn(mSchedulerProvider.io())
                .observeOn(mSchedulerProvider.ui())
                .subscribe(new Consumer<ArrayList<HospitalItem>>() {
                    @Override
                    public void accept(ArrayList<HospitalItem> hospitalItems) throws Exception {
                        if (null != mView) {
                            mView.showLoading(false);
                            mDatas = hospitalItems;
                            mView.bindData(hospitalItems);
                        }
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        if (null != mView) {
                            mView.showLoading(false);
                            mView.showErrorNetwork(String.valueOf(throwable.getCause()));
                        }
                    }
                }, new Action() {
                    @Override
                    public void run() throws Exception {
                        if (null != mView) {
                            mView.showLoadingDataFinish();
                        }
                    }
                });
    }
}
