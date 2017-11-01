package com.telecorp.dashqueue

import android.app.Activity
import android.app.Application
import com.marcinmoskala.kotlinpreferences.PreferenceHolder
import com.telecorp.dashqueue.di.AppInjector
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasActivityInjector
import javax.inject.Inject

/**
 * Created by Saran on 13/10/2560.
 */

class MyApplication : Application(), HasActivityInjector {
    @Inject
    lateinit var dispatchingAndroidInjector: DispatchingAndroidInjector<Activity>

    override fun onCreate() {
        super.onCreate()
        AppInjector.init(this)
        PreferenceHolder.setContext(applicationContext)
    }


    override fun activityInjector(): AndroidInjector<Activity>? {
        return dispatchingAndroidInjector
    }
}
