package com.android.szparag.colortv.presenters.contracts;

import com.android.szparag.colortv.backend.models.Movie;
import com.android.szparag.colortv.backend.models.realm.RealmMovieGroup;
import com.android.szparag.colortv.views.contracts.BaseAndroidView;

/**
 * Created by ciemek on 17/10/2016.
 */

public interface MovieListBasePresenter<T extends BaseAndroidView> extends BasePresenter<T> {

    void setView(T view);

    void populateViewWithMovies(int movieGroupId);

    Movie queryMovieFromGroup(int movieGroupId, int moviePosition);
    Movie queryMovieFromGroup(int moviePosition);

    RealmMovieGroup queryMovieGroup(int movieGroupId);

}
