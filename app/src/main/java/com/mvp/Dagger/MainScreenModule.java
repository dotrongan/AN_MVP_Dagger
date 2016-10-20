package com.mvp.Dagger;

import com.mvp.MainActivity;
import com.mvp.Model.ListDataModel;
import com.mvp.Pattern.ParseDataFactory;
import com.mvp.Presenter.ListDataPresenter;

import dagger.Module;
import dagger.Provides;

/**
 * Created by ANDT on 10/11/2016.
 */

@Module
public class MainScreenModule {
    private final MainActivity mView;

    public MainScreenModule(MainActivity mainActivity) {
        this.mView = mainActivity;
    }

    @Provides
    ListDataPresenter listDataPresenter() {
        ListDataPresenter presenter = new ListDataPresenter(mView);
        ListDataModel dataModel = new ListDataModel(presenter);
        dataModel.setParseFactory(new ParseDataFactory());
        presenter.setModel(dataModel);
        return presenter;
    }

}
