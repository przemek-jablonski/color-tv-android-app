package com.android.szparag.colortv.dagger.modules;

import android.app.Application;
import android.content.Context;

import com.android.szparag.colortv.ColorTVApplication;
import com.android.szparag.colortv.presenters.MovieListPresenter;
import com.android.szparag.colortv.presenters.ThreeButtonsPresenter;
import com.android.szparag.colortv.presenters.contracts.MovieListBasePresenter;
import com.android.szparag.colortv.presenters.contracts.ThreeButtonsBasePresenter;
import com.android.szparag.colortv.views.contracts.ThreeButtonsBaseView;
import com.google.gson.Gson;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import io.realm.Realm;

/**
 * Created by ciemek on 17/10/2016.
 */

@Module
public class ColorTVModule {

    private Application application;

    public ColorTVModule(Application application) {
        this.application = application;
    }

    @Provides
    @Singleton
    Context provideContext() {
        return application;
    }

    @Provides
    @Singleton
    ColorTVApplication provideColorTVApplication() throws ClassCastException {
        return ((ColorTVApplication) application);
    }

    //todo: remove redundant imports

    @Provides
    @Singleton
    Gson provideGson() {
        return new Gson();
    }

    @Provides
    @Singleton
    Realm provideRealm() {
        return Realm.getDefaultInstance();
    }

    @Provides
    @Singleton
    ThreeButtonsBasePresenter provideThreeButtonsPresenter() {
        return new ThreeButtonsPresenter();
    }

    @Provides
    @Singleton
    MovieListBasePresenter provideMovieListPresenter() {
        return new MovieListPresenter();
    }

}
