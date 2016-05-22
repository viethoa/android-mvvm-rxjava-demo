package com.viethoa.mvvm.BaseApplications.modules.Network;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.viethoa.mvvm.BuildConfig;
import com.viethoa.mvvm.Components.networks.MVVMApiService;
import com.viethoa.mvvm.Components.networks.MVVMConfig;

import java.util.Date;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import retrofit.ErrorHandler;
import retrofit.RequestInterceptor;
import retrofit.RestAdapter;
import retrofit.converter.GsonConverter;

/**
 * Created by VietHoa on 17/01/16.
 */
@Module
public class NetworkModule {

    @Provides
    @Singleton
    Gson provideGsonParser(DateConverter dateConverter) {
        return new GsonBuilder()
                .registerTypeAdapter(Date.class, dateConverter)
                .create();
    }

    @Provides
    @Singleton
    GsonConverter provideGsonConverter(Gson gson) {
        return new GsonConverter(gson);
    }

    @Provides
    @Singleton
    ErrorHandler provideErrorHandler() {
        return new BaseErrorHandler();
    }

    @Provides
    @Singleton
    RequestInterceptor provideRequestInterceptor(BaseRequestInterceptor requestInterceptor) {
        return requestInterceptor;
    }

    @Provides
    @Singleton
    RestAdapter provideRestAdapter(ErrorHandler errorHandler, RequestInterceptor requestInterceptor, GsonConverter gsonConverter) {
        return new RestAdapter.Builder()
                .setConverter(gsonConverter)
                .setErrorHandler(errorHandler)
                .setEndpoint(MVVMConfig.getBaseUrl())
                .setRequestInterceptor(requestInterceptor)
                .setLogLevel(BuildConfig.DEBUG ? RestAdapter.LogLevel.FULL : RestAdapter.LogLevel.NONE)
                .build();
    }

    @Provides
    @Singleton
    MVVMApiService provideGenesisService(RestAdapter restAdapter) {
        return restAdapter.create(MVVMApiService.class);
    }
}
