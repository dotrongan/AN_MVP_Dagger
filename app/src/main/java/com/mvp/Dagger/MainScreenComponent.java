package com.mvp.Dagger;

import com.mvp.MainActivity;

import dagger.Component;

/**
 * Created by ANDT on 10/11/2016.
 */

@Component (modules = MainScreenModule.class,dependencies = AppComponent.class)
public interface MainScreenComponent {
    void inject(MainActivity mainActivity);
}
