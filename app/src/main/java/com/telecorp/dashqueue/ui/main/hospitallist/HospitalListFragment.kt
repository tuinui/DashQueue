package com.telecorp.dashqueue.ui.main.hospitallist

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v4.app.ActivityCompat
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.SearchView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.telecorp.dashqueue.R
import com.telecorp.dashqueue.api.TelecorpApiInterface
import com.telecorp.dashqueue.api.model.HospitalItem
import com.telecorp.dashqueue.base.BaseFragment
import com.telecorp.dashqueue.custom.GenericOnItemClickListener
import com.telecorp.dashqueue.di.Injectable
import com.telecorp.dashqueue.ui.loginauthen.LoginAuthenActivityStarter
import com.telecorp.dashqueue.ui.main.hospitallist.contract.HospitalListContract
import com.telecorp.dashqueue.ui.main.hospitallist.contract.HospitalListPresenter
import com.telecorp.dashqueue.ui.main.hospitallist.recycler.HospitalRecyclerAdapter
import com.telecorp.dashqueue.utils.schedulers.BaseSchedulerProvider
import kotlinx.android.synthetic.main.fragment_hospital_list.*
import javax.inject.Inject


/**
 * Created by Saran on 4/11/2560.
 */
class HospitalListFragment : BaseFragment(), HospitalListContract.View, Injectable, SearchView.OnQueryTextListener {


    private val mSwipeRefreshLayout: SwipeRefreshLayout by lazy {
        swiperefreshlayout_hospital_list
    }
    private val mRecyclerView: RecyclerView by lazy {
        recyclerview_hospital_list
    }
    private val mSearchView: SearchView by lazy {
        searchview_hospital_list
    }

    private val mPresenter: HospitalListPresenter by lazy { HospitalListPresenter(mApi, mSchedulerProvider) }
    @Inject
    lateinit var mApi: TelecorpApiInterface
    @Inject
    lateinit var mSchedulerProvider: BaseSchedulerProvider

    private val mAdapter = HospitalRecyclerAdapter(object : GenericOnItemClickListener<HospitalItem> {
        override fun onItemClick(context: Context, data: HospitalItem) {
            LoginAuthenActivityStarter.startWithFlags(context, data, Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK)
            ActivityCompat.finishAffinity(activity)
        }
    })

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater?.inflate(R.layout.fragment_hospital_list, container, false)
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
        initSearchView()
    }

    private fun initSearchView() {

        mSearchView.setOnQueryTextListener(this)
    }

    private fun initView() {
        mSwipeRefreshLayout.setOnRefreshListener { refreshData() }

        mRecyclerView.layoutManager = LinearLayoutManager(context)
        mRecyclerView.adapter = mAdapter
    }


    override fun showLoading(loading: Boolean) {
        mSwipeRefreshLayout.isRefreshing = loading
    }

    override fun bindData(items: List<HospitalItem>) {
        mAdapter.replaceData(items)
    }

    override fun showErrorNetwork(error: String) {
        Toast.makeText(context, error, Toast.LENGTH_LONG).show()
    }

    override fun showLoadingDataFinish() {
        Toast.makeText(context, "Finish!", Toast.LENGTH_LONG).show()
    }


    private fun refreshData() {
        mPresenter.loadData()
    }

    override fun onResume() {
        super.onResume()
        mPresenter.subscribe(this)
    }

    override fun onQueryTextSubmit(query: String?): Boolean {
        query?.let { mAdapter.filterList(it) }
        return false
    }

    override fun onQueryTextChange(newText: String?): Boolean {
        newText?.let { mAdapter.filterList(it) }
        return false
    }

    override fun onPause() {
        super.onPause()
        mPresenter.unsubscribe()
    }


    companion object {
        fun newInstance(): HospitalListFragment {
            return HospitalListFragment()
        }
    }
}