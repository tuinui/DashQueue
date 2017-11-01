package com.telecorp.dashqueue.base;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.telecorp.dashqueue.R;

import activitystarter.ActivityStarter;

/**
 * Created by Saran on 1/11/2560.
 */

public class BaseActivity extends AppCompatActivity {

    private ProgressDialog mProgressDialog;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityStarter.fill(this);
    }

    @Override
    // This is optional, only when we want to keep arguments changes in case of rotation etc.
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        ActivityStarter.save(this);
    }


    public void showLoading() {
        if (isFinishing()) {
            return;
        }
        if (null == mProgressDialog) {
            mProgressDialog = getLoadingProgressDialog(this);
        }

        if (!mProgressDialog.isShowing()) {
            mProgressDialog.show();
        }
    }

    public void dismissLoading() {
        if (isFinishing()) {
            return;
        }

        if (null != mProgressDialog && mProgressDialog.isShowing()) {
            mProgressDialog.dismiss();
        }
    }
    private ProgressDialog getLoadingProgressDialog(Context context) {
        ProgressDialog dialog = new ProgressDialog(context);

        dialog.setTitle("");
        dialog.setMessage(getString(R.string.Loading));
        dialog.setCancelable(true);
        return dialog;
    }

}
