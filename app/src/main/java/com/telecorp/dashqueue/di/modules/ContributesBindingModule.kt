package com.telecorp.dashqueue.di.modules

import com.telecorp.dashqueue.di.scope.ActivityScope
import com.telecorp.dashqueue.di.scope.ServiceScope
import com.telecorp.dashqueue.push.MyFirebaseInstanceIDService
import com.telecorp.dashqueue.ui.loginauthen.LoginAuthenActivity
import com.telecorp.dashqueue.ui.main.MainActivity
import com.telecorp.dashqueue.ui.main.FragmentBuilderModule
import com.telecorp.dashqueue.ui.profile.ProfileActivity
import com.telecorp.dashqueue.ui.queue.QueueActivity
import com.telecorp.dashqueue.ui.waitingqueue.WatingQueueActivity

import dagger.Module
import dagger.android.ContributesAndroidInjector

/**
 * We want Dagger.Android to create a Subcomponent which has a parent Component of whichever module ContributesBindingModule is on,
 * in our case that will be AppComponent. The beautiful part about this setup is that you never need to tell AppComponent that it is going to have all these subcomponents
 * nor do you need to tell these subcomponents that AppComponent exists.
 * We are also telling Dagger.Android that this generated SubComponent needs to include the specified modules and be aware of a scope annotation @ActivityScoped
 * When Dagger.Android annotation processor runs it will create 4 subcomponents for us.
 */
@Module
abstract class ContributesBindingModule {
    @ActivityScope
    @ContributesAndroidInjector(modules = arrayOf(FragmentBuilderModule::class))
    abstract fun provideMainActivity(): MainActivity

    @ActivityScope
    @ContributesAndroidInjector
    abstract fun provideLoginAuthenActivity(): LoginAuthenActivity

    @ActivityScope
    @ContributesAndroidInjector(modules = arrayOf(FragmentBuilderModule::class))
    abstract fun provideProfileActivity(): ProfileActivity

    @ActivityScope
    @ContributesAndroidInjector
    abstract fun provideWatingQueueActivity(): WatingQueueActivity

    @ServiceScope
    @ContributesAndroidInjector
    abstract fun provideFirebaseInstanceIDService(): MyFirebaseInstanceIDService

    @ActivityScope
    @ContributesAndroidInjector
    abstract fun provideQueuectivity(): QueueActivity


}
