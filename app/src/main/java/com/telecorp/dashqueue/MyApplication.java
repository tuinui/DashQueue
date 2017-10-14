package com.telecorp.dashqueue;

import android.app.Activity;
import android.app.Application;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.telecorp.dashqueue.di.AppInjector;

import javax.inject.Inject;

import dagger.android.AndroidInjector;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.HasActivityInjector;

/**
 * Created by Saran on 13/10/2560.
 */

public class MyApplication extends Application implements HasActivityInjector{
    @Inject
    DispatchingAndroidInjector<Activity> dispatchingAndroidInjector;
    @Override
    public void onCreate() {
        super.onCreate();
        Fresco.initialize(this);
        AppInjector.init(this);
    }



    @Override
    public AndroidInjector<Activity> activityInjector() {
        return dispatchingAndroidInjector;
    }
}
