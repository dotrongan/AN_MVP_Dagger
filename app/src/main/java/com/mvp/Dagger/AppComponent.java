package com.mvp.Dagger;

import android.content.SharedPreferences;

import com.mvp.BaseApp;
import com.mvp.Pattern.ParseDataTheThao247;
import com.mvp.Pattern.ParseDataTheThao24h;

import dagger.Component;

/**
 * Created by ANDT on 10/11/2016.
 */

@Component (modules = {AppModule.class})
public interface AppComponent {
    SharedPreferences sharedPreferences();
    void inject(ParseDataTheThao24h parseDataTheThao24h);
    void inject(ParseDataTheThao247 parseDataTheThao247);
}
