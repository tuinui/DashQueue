package com.telecorp.dashqueue.di.components;

import android.app.Application;

import com.telecorp.dashqueue.MyApplication;
import com.telecorp.dashqueue.di.modules.ContributesBindingModule;
import com.telecorp.dashqueue.di.modules.AppModule;
import com.telecorp.dashqueue.di.modules.NetModule;

import javax.inject.Singleton;

import dagger.BindsInstance;
import dagger.Component;
import dagger.android.AndroidInjectionModule;

/**
 * Created by Saran on 13/10/2560.
 */
@Singleton
@Component(modules={AndroidInjectionModule.class,AppModule.class,NetModule.class, ContributesBindingModule.class})
public interface AppComponent {
    @Component.Builder
    interface Builder {
        @BindsInstance
        Builder application(Application application);

        AppComponent build();
    }

    void inject(MyApplication githubApp);
}