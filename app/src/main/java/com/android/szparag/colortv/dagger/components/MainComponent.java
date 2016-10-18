package com.android.szparag.colortv.dagger.components;

import com.android.szparag.colortv.ColorTVApplication;
import com.android.szparag.colortv.dagger.modules.ColorTVModule;
import com.android.szparag.colortv.presenters.MovieListPresenter;
import com.android.szparag.colortv.presenters.ThreeButtonsPresenter;
import com.android.szparag.colortv.views.MovieListFragment;
import com.android.szparag.colortv.views.ThreeButtonsFragment;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by ciemek on 17/10/2016.
 */

@Singleton
@Component(modules = ColorTVModule.class)
public interface MainComponent {

    //presenters:
    void inject(MovieListPresenter injectionTarget);
    void inject(ThreeButtonsPresenter injectionTarget);

    //views:
    void inject(MovieListFragment injectionTarget);
    void inject(ThreeButtonsFragment injectionTarget);

    void inject(ColorTVApplication injectionTarget);

}
