package com.telecorp.dashqueue.ui.main;

import com.telecorp.dashqueue.ui.main.hospitallist.HospitalListFragment;
import com.telecorp.dashqueue.ui.main.yourprofile.YourProfileFragment;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

/**
 * Created by Saran on 14/10/2560.
 */

@Module
public abstract class FragmentBuilderModule {



    @ContributesAndroidInjector
    abstract HospitalListFragment contributeRepoFragment();

    @ContributesAndroidInjector
    abstract YourProfileFragment contributeUserFragment();
}
