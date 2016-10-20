package com.mvp;

import android.app.Application;

import com.mvp.Dagger.AppComponent;
import com.mvp.Dagger.AppModule;
import com.mvp.Dagger.DaggerAppComponent;

/**
 * Created by ANDT on 10/11/2016.
 */

public class BaseApp extends Application {

    private static AppComponent appComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        appComponent = DaggerAppComponent.builder().appModule(new AppModule(this)).build();
    }

    public static AppComponent getAppComponent() {
        return appComponent;
    }
}
