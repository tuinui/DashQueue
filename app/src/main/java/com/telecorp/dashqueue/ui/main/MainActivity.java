package com.telecorp.dashqueue.ui.main;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.telecorp.dashqueue.R;
import com.telecorp.dashqueue.api.TelecorpApiInterface;
import com.telecorp.dashqueue.api.model.HospitalItem;
import com.telecorp.dashqueue.base.BaseActivity;
import com.telecorp.dashqueue.custom.GenericOnItemClickListener;
import com.telecorp.dashqueue.di.Injectable;
import com.telecorp.dashqueue.test.AnagramGenerator;
import com.telecorp.dashqueue.ui.loginauthen.LoginAuthenActivityStarter;
import com.telecorp.dashqueue.ui.main.contract.MainActivityContract;
import com.telecorp.dashqueue.ui.main.contract.MainActivityPresenter;
import com.telecorp.dashqueue.ui.main.recycler.HospitalRecyclerAdapter;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends BaseActivity implements MainActivityContract.View, Injectable {
    @Inject
    TelecorpApiInterface mApi;

    @BindView(R.id.swiperefreshlayout_main)
    SwipeRefreshLayout mSwipeRefreshLayout;
    @BindView(R.id.recyclerview_main)
    RecyclerView mRecyclerView;

    @Inject
    MainActivityPresenter mPresenter;
    private LinkedList<String> mList = new LinkedList<>();


    private HospitalRecyclerAdapter mAdapter = new HospitalRecyclerAdapter(new GenericOnItemClickListener<HospitalItem>() {
        @Override
        public void onItemClick(@NotNull Context context, HospitalItem data) {
            LoginAuthenActivityStarter.start(context,data);
        }
    });



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        initView();
    }

    private void initView() {
        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                refreshData();
            }
        });

        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setAdapter(mAdapter);
    }

    private void refreshData() {
        mPresenter.loadData();
    }

    @Override
    protected void onResume() {
        super.onResume();
        mPresenter.subscribe(this);
    }

    @Override
    protected void onPause() {
        super.onPause();
        mPresenter.unsubscribe();
    }

    @Override
    public void onBackPressed() {
//        super.onBackPressed();
        testAnagram();
    }

    private void testAnagram() {
        List<String> dictionaries = new ArrayList<>();
        dictionaries.add("LOOP");
        dictionaries.add("POOL");
        dictionaries.add("POLO");
        dictionaries.add("STOP");
        dictionaries.add("POST");
        Toast.makeText(this, new AnagramGenerator(dictionaries).getAnagrams("OSPT").toString(), Toast.LENGTH_LONG).show();
    }

    @Override
    public void showLoading(boolean loading) {
        mSwipeRefreshLayout.setRefreshing(loading);
    }

    @Override
    public void bindData(List<HospitalItem> items) {
        mAdapter.replaceDatas(items);
    }

    @Override
    public void showErrorNetwork(String error) {
        Toast.makeText(this, error, Toast.LENGTH_LONG).show();
    }

    @Override
    public void showLoadingDataFinish() {
        Toast.makeText(this, "Finish!!!", Toast.LENGTH_LONG).show();
    }


}
