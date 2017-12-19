package com.telecorp.dashqueue.base

import activitystarter.ActivityStarter
import android.app.ProgressDialog
import android.content.Context
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import com.google.firebase.iid.FirebaseInstanceId
import com.telecorp.dashqueue.R
import com.telecorp.dashqueue.push.MyFirebaseInstanceIDService

/**
 * Created by Saran on 1/11/2560.
 */

open class BaseActivity : AppCompatActivity() {

    private var mProgressDialog: ProgressDialog? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d("BaseActivity", "Refreshed token: " + FirebaseInstanceId.getInstance()?.token)
        ActivityStarter.fill(this)
    }

    override// This is optional, only when we want to keep arguments changes in case of rotation etc.
    fun onSaveInstanceState(outState: Bundle?) {
        super.onSaveInstanceState(outState)
        ActivityStarter.save(this)
    }


    fun showLoading() {
        if (isFinishing) {
            return
        }
        if (null == mProgressDialog) {
            mProgressDialog = getLoadingProgressDialog(this)
        }

        if (!mProgressDialog!!.isShowing) {
            mProgressDialog!!.show()
        }
    }

    fun dismissLoading() {
        if (isFinishing) {
            return
        }

        if (null != mProgressDialog && mProgressDialog!!.isShowing) {
            mProgressDialog!!.dismiss()
        }
    }

    private fun getLoadingProgressDialog(context: Context): ProgressDialog {
        val dialog = ProgressDialog(context)

        dialog.setTitle("")
        dialog.setMessage(getString(R.string.Loading))
        dialog.setCancelable(true)
        return dialog
    }

}
