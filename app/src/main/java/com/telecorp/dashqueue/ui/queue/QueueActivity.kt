package com.telecorp.dashqueue.ui.queue

import activitystarter.Arg
import android.content.Intent
import android.os.Bundle
import android.support.v4.app.ActivityCompat
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.app.AlertDialog
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.Toolbar
import android.view.MenuItem
import android.widget.Toast
import com.marcinmoskala.activitystarter.argExtra
import com.telecorp.dashqueue.R
import com.telecorp.dashqueue.api.TelecorpApiInterface
import com.telecorp.dashqueue.api.model.HospitalItem
import com.telecorp.dashqueue.api.model.LoginAuthenResponseModel
import com.telecorp.dashqueue.base.BaseActivity
import com.telecorp.dashqueue.di.Injectable
import com.telecorp.dashqueue.ui.main.MainActivity
import com.telecorp.dashqueue.ui.queue.contract.QueueActivityContract
import com.telecorp.dashqueue.ui.queue.contract.QueueActivityPresenter
import com.telecorp.dashqueue.ui.queue.recycler.QueueRecyclerAdapter
import com.telecorp.dashqueue.ui.queue.recycler.model.QueueItemEntity
import com.telecorp.dashqueue.utils.getDeviceImei
import com.telecorp.dashqueue.utils.getDeviceModelName
import com.telecorp.dashqueue.utils.pref.MyPreferencesHolder
import com.telecorp.dashqueue.utils.schedulers.BaseSchedulerProvider
import kotlinx.android.synthetic.main.activity_queue.*
import kotlinx.android.synthetic.main.view_main_appbar.*
import javax.inject.Inject

/**
 * Created by Saran on 4/11/2560.
 */

class QueueActivity : BaseActivity(), Toolbar.OnMenuItemClickListener, Injectable, QueueActivityContract.View {


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
        QueueRecyclerAdapter()
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

    override fun onResume() {
        super.onResume()
        mPresenter.subscribe(this)
    }

    override fun onPause() {
        super.onPause()
        mPresenter.unsubscribe()
    }

    private fun initView() {

        mSwipeRefreshLayout.setOnRefreshListener { mPresenter.refreshData(getDeviceModelName(), getDeviceImei()) }
    }


    override fun showLoading(loading: Boolean) {
        mSwipeRefreshLayout.isRefreshing = loading
    }

    override fun showErrorNetwork(error: String) {
        Toast.makeText(this, error, Toast.LENGTH_LONG).show()
    }

    override fun showLoadingDataFinish() {
        Toast.makeText(this, "finish", Toast.LENGTH_LONG).show()
    }

    override fun bindData(items: ArrayList<QueueItemEntity>) {
        mAdapter.replaceData(items)
    }

    private fun initRecyclerView() {
        mRecyclerView.layoutManager = mLinearLayoutManager
        mRecyclerView.adapter = mAdapter
    }

    private fun initToolbar() {
        mToolbar.setTitle(R.string.Queue)
        mToolbar.inflateMenu(R.menu.menu_queue)
        mToolbar.setOnMenuItemClickListener(this)
    }


    private fun showConfirmLogout() {
        AlertDialog.Builder(this)
                .setTitle(R.string.Reject_Queue)
                .setMessage(R.string.Are_you_sure_you_want_to_stop_receiving_notification)
                .setPositiveButton(R.string.Confirm) { dialog, which ->

                    mPresenter.requestLogout()
                }
                .setNegativeButton(R.string.Cancel, { dialog, which ->
                    dialog.dismiss()
                }).create().show()
    }



    override fun openHospitalListActivity() {
        MyPreferencesHolder.appTokenModel = null
        val intent = Intent(this, MainActivity::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK)
        startActivity(intent)
        ActivityCompat.finishAffinity(this)
    }

    override fun onMenuItemClick(item: MenuItem?): Boolean {
        item?.let {
            val id = item.itemId;
            if (id == R.id.menuitem_queue_logout) {
                showConfirmLogout()
                return true
            }
        }

        return false
    }


}