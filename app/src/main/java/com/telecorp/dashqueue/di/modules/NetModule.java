package com.telecorp.dashqueue.di.modules;

import android.app.Application;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.telecorp.dashqueue.api.TelecorpApiInterface;
import com.telecorp.dashqueue.utils.schedulers.BaseSchedulerProvider;
import com.telecorp.dashqueue.utils.schedulers.SchedulerProvider;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.Cache;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Saran on 12/10/2560.
 */

@Module
public class NetModule {

    String mBaseUrl = "http://telecorp.co.th/QCore/api/";
    // http://telecorp.co.th/QCore/api/hospital


    public NetModule() {
        ;
    }


    @Provides
    @Singleton
    Gson provideGson() {
        GsonBuilder gsonBuilder = new GsonBuilder();
        return gsonBuilder.create();
    }

    @Provides
    @Singleton
    Cache provideOkHttpCache(Application application) {
        int cacheSize = 10 * 1024 * 1024; // 10 MiB
        return new Cache(application.getCacheDir(), cacheSize);
    }


    @Provides
    @Singleton
    OkHttpClient provideOkHttpClient(Cache cache) {
        OkHttpClient.Builder client = new OkHttpClient.Builder();
        client.cache(cache);
        return client.build();
    }

    @Provides
    @Singleton
    Retrofit provideRetrofit(Gson gson, OkHttpClient okHttpClient) {
        return new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .baseUrl(mBaseUrl)
                .client(okHttpClient)
                .build();
    }

    @Provides
    @Singleton
    public TelecorpApiInterface providesTelecorpApiInterface(Retrofit retrofit) {
        return retrofit.create(TelecorpApiInterface.class);
    }


    @Provides
    @Singleton
    public BaseSchedulerProvider provideSchedulerProvider() {
        return SchedulerProvider.getInstance();
    }
}
