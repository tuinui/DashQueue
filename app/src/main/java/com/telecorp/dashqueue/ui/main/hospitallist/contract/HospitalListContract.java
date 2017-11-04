package com.telecorp.dashqueue.ui.main.hospitallist.contract;

import com.telecorp.dashqueue.api.model.HospitalItem;
import com.telecorp.dashqueue.base.BasePresenter;
import com.telecorp.dashqueue.base.BaseView;

import java.util.List;

/**
 * Created by Saran on 14/10/2560.
 */

public class HospitalListContract {

    public interface View extends BaseView{
        void showLoading(boolean loading);
        void bindData(List<HospitalItem> items);
        void showErrorNetwork(String error);
        void showLoadingDataFinish();
    }


    public interface Presenter extends BasePresenter<View>{
        void loadData();
    }
}
