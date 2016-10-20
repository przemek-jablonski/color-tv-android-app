package com.android.szparag.colortv.presenters.contracts;

import com.android.szparag.colortv.backend.models.MovieGroup;
import com.android.szparag.colortv.backend.models.realm.RealmMovieGroup;
import com.android.szparag.colortv.views.contracts.BaseAndroidView;
import com.android.szparag.colortv.views.contracts.ThreeButtonsBaseView;

/**
 * Created by ciemek on 17/10/2016.
 */

public interface MovieListBasePresenter<T extends BaseAndroidView> extends BasePresenter<T> {

    void setView(T view);

    void populateViewWithMovies(int movieGroupId);

    RealmMovieGroup getMovieGroup(int movieGroupId);

}
