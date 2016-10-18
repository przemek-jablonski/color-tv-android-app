package com.android.szparag.colortv.presenters;

import com.android.szparag.colortv.presenters.contracts.MovieListBasePresenter;
import com.android.szparag.colortv.views.contracts.BaseAndroidView;
import com.android.szparag.colortv.views.contracts.MovieListBaseView;

/**
 * Created by ciemek on 17/10/2016.
 */

public class MovieListPresenter implements MovieListBasePresenter<MovieListBaseView> {

    MovieListBaseView view;

    @Override
    public void setView(MovieListBaseView view) {
        this.view = view;
    }


}
