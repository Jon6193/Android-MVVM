package com.jonathanpetitfrere.mvvm.di;

import com.facebook.stetho.okhttp3.StethoInterceptor;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.jonathanpetitfrere.mvvm.BuildConfig;
import com.jonathanpetitfrere.mvvm.repository.api.UserApi;
import com.jonathanpetitfrere.mvvm.repository.api.model.User;

import java.util.List;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import io.reactivex.Flowable;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * @author jpetit
 */

@Module
public class ApiModule {

    @Singleton
    @Provides
    OkHttpClient.Builder provideOkHttpClientBuilder() {
        return new OkHttpClient.Builder();
    }

    @Singleton
    @Provides
    OkHttpClient provideOkHttpClient(OkHttpClient.Builder okHttpClientBuilder) {
        if(BuildConfig.DEBUG) {
            okHttpClientBuilder
                    .addNetworkInterceptor(new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
                    .addNetworkInterceptor(new StethoInterceptor());
        }

        return okHttpClientBuilder.build();
    }

    @Singleton
    @Provides
    Gson provideGson() {
        return new GsonBuilder()
                .create();
    }

    @Singleton
    @Provides
    Retrofit.Builder provideRetrofitBuilder(OkHttpClient okHttpClient, Gson gson) {
        return new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(okHttpClient);
    }

    @Singleton
    @Provides
    UserApi provideUserApi(Retrofit.Builder retrofitBuilder) {
        return retrofitBuilder.baseUrl("http://google.com")
                .build()
                .create(UserApi.class);
    }
}
