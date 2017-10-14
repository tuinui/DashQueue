package com.telecorp.dashqueue.ui.main;

import com.telecorp.dashqueue.di.scope.ActivityScope;
import com.telecorp.dashqueue.ui.main.contract.MainActivityContract;
import com.telecorp.dashqueue.ui.main.contract.MainActivityPresenter;

import dagger.Binds;
import dagger.Module;

/**
 * Created by Saran on 14/10/2560.
 */

@Module
public abstract class MainActivityModule {

    @ActivityScope
    @Binds
    abstract MainActivityContract.Presenter bindsPresenter(MainActivityPresenter presenter);
}
