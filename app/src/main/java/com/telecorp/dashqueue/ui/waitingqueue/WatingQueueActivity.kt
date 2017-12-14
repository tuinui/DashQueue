package com.telecorp.dashqueue.ui.waitingqueue

import activitystarter.Arg
import android.os.Bundle
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.Toolbar
import android.widget.Toast
import com.marcinmoskala.activitystarter.argExtra
import com.telecorp.dashqueue.R
import com.telecorp.dashqueue.api.TelecorpApiInterface
import com.telecorp.dashqueue.api.model.HospitalItem
import com.telecorp.dashqueue.api.model.LoginAuthenResponseModel
import com.telecorp.dashqueue.base.BaseActivity
import com.telecorp.dashqueue.di.Injectable
import com.telecorp.dashqueue.ui.queue.contract.QueueActivityContract
import com.telecorp.dashqueue.ui.queue.contract.QueueActivityPresenter
import com.telecorp.dashqueue.ui.queue.recycler.QueueDetailClickListener
import com.telecorp.dashqueue.ui.queue.recycler.QueueRecyclerAdapter
import com.telecorp.dashqueue.ui.queue.recycler.model.QueueItemEntity
import com.telecorp.dashqueue.utils.getDeviceImei
import com.telecorp.dashqueue.utils.getDeviceModelName
import com.telecorp.dashqueue.utils.schedulers.BaseSchedulerProvider
import kotlinx.android.synthetic.main.activity_queue.*
import kotlinx.android.synthetic.main.view_main_appbar.*
import javax.inject.Inject

/**
 * Created by Saran on 7/12/2560.
 */
class WatingQueueActivity : BaseActivity(), Injectable, QueueActivityContract.View {


    @Inject
    lateinit var mApi: TelecorpApiInterface
    @Inject
    lateinit var mSchedulerProvider: BaseSchedulerProvider
    @get:Arg
    var mHospitalData: HospitalItem? by argExtra()
    @get:Arg(optional = true)
    var mLoginAuthenData: LoginAuthenResponseModel? by argExtra()

    private val mSwipeRefreshLayout: SwipeRefreshLayout by lazy {
        swiperefreshlayout_queue
    }
    private val mRecyclerView: RecyclerView by lazy {
        recyclerview_queue
    }
    private val mToolbar: Toolbar by lazy {
        toolbar_main
    }
    private val mLinearLayoutManager: LinearLayoutManager by lazy {
        LinearLayoutManager(this)
    }
    private val mAdapter: QueueRecyclerAdapter by lazy {
        QueueRecyclerAdapter(object : QueueDetailClickListener {
            override fun onQueueListClick() {
                mPresenter.onQueueListClick()
            }

            override fun onProfileClick() {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }
        })
    }

    private lateinit var mPresenter: QueueActivityPresenter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_queue)
        mPresenter = QueueActivityPresenter(mHospitalData, mLoginAuthenData, mApi, mSchedulerProvider)
        initToolbar()
        initRecyclerView()
        initView()

    }


    private fun initRecyclerView() {
        mRecyclerView.layoutManager = mLinearLayoutManager
        mRecyclerView.adapter = mAdapter
    }

    private fun initView() {

        mSwipeRefreshLayout.setOnRefreshListener { mPresenter.refreshData(getDeviceModelName(), getDeviceImei()) }
    }

    private fun initToolbar() {
        mToolbar.setTitle(R.string.Waiting_Queue)
        mToolbar.setNavigationIcon(R.drawable.ic_arrow_back_white_24dp)
        mToolbar.setNavigationOnClickListener { onBackPressed() }
    }

    override fun showLoading(loading: Boolean) {
        mSwipeRefreshLayout.isRefreshing = loading
    }

    override fun showErrorNetwork(error: String) {
        Toast.makeText(this, error, Toast.LENGTH_LONG).show()
    }

    override fun bindData(items: List<QueueItemEntity>) {
        mAdapter.replaceData(items.filter { data -> data.itemViewType == QueueItemEntity.ITEM })
    }

    override fun onResume() {
        super.onResume()
        mPresenter.subscribe(this)
    }

    override fun onPause() {
        super.onPause()
        mPresenter.unsubscribe()
    }

    override fun showLoadingDataFinish() {
//DO NOTHING
    }

    override fun openHospitalListActivity() {
        //DO NOTHING
    }

    override fun showProfileActivity() {
        //DO NOTHING
    }

    override fun showQueueListActivity(filter: List<QueueItemEntity>) {
        //DO NOTHING
    }
}