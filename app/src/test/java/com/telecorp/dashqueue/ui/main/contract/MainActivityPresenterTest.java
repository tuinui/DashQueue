package com.telecorp.dashqueue.ui.main.contract;

import com.telecorp.dashqueue.api.TelecorpApiInterface;
import com.telecorp.dashqueue.api.model.HospitalItem;
import com.telecorp.dashqueue.utils.schedulers.ImmediateSchedulerProvider;

import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;

import io.reactivex.Observable;
import io.reactivex.functions.Consumer;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Created by Saran on 14/10/2560.
 */

public class MainActivityPresenterTest {


    private static ArrayList<HospitalItem> DATAS;

    @Mock
    private TelecorpApiInterface mApi;



    @Mock
    private MainActivityContract.View mView;

    /**
     * {@link ArgumentCaptor} is a powerful Mockito API to capture argument values and use them to
     * perform further actions or assertions on them.
     */
    @Captor
    private ArgumentCaptor<Consumer<ArrayList<HospitalItem>>> mLoadCompleteCallbackCaptor;
    private MainActivityPresenter mPresenter;
    private ImmediateSchedulerProvider mSchedulerProvider;


    @Before
    public void setupTasksPresenter() {
        // Mockito has a very convenient way to inject mocks by using the @Mock annotation. To
        // inject the mocks in the test the initMocks method needs to be called.
        MockitoAnnotations.initMocks(this);
        mSchedulerProvider = new ImmediateSchedulerProvider();
        // Get a reference to the class under test
        mPresenter = new MainActivityPresenter(mApi,mSchedulerProvider);
        mPresenter.subscribe(mView);


        // We start the tasks to 3, with one active and two completed
        DATAS = new ArrayList<>();
        DATAS.add(HospitalItem.getMock("a;sldfh;alsdkfj"));
    }

    @Test
    public void loadData_Completed() throws Exception {
        when(mApi.getHospital()).thenReturn(Observable.just(DATAS));
        mPresenter.loadData();
        verify(mApi).getHospital();

        // Then progress indicator is shown
        verify(mView).showLoading(true);
        // Then progress indicator is hidden and all tasks are shown in UI
        verify(mView).showLoading(false);
        verify(mView).showLoadingDataFinish();
    }

    @Test
    public void loadData_Error() throws Exception {
        Exception exception = new Exception();
        when(mApi.getHospital()).thenReturn(Observable.<ArrayList<HospitalItem>>error(exception));
        mPresenter.loadData();
        verify(mApi).getHospital();
        verify(mView).showLoading(true);
        verify(mView).showErrorNetwork(String.valueOf(exception.getCause()));
    }
}