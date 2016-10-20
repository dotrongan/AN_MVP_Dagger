package com.mvp.Dagger;

import android.app.Application;
import android.content.Context;

import com.androidquery.AQuery;
import com.mvp.BaseApp;
import com.mvp.Pattern.ParseDataTheThao24h;

import dagger.Module;
import dagger.Provides;

/**
 * Created by ANDT on 10/11/2016.
 */

@Module
public class AppModule {
    private final Context app;

    public AppModule(Context app) {
        this.app = app;
    }

    @Provides
    public Application provideApplication() {
        return (BaseApp)app;
    }

    @Provides
    public Context provideContext() {
        return this.app;
    }

    @Provides
    AQuery provideAQuery() {
        AQuery aQuery = new AQuery(app);
        return aQuery;
    }

}
