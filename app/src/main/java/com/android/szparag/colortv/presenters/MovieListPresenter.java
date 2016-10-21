package com.android.szparag.colortv.presenters;

import com.android.szparag.colortv.backend.models.Movie;
import com.android.szparag.colortv.backend.models.realm.RealmMovieGroup;
import com.android.szparag.colortv.presenters.contracts.MovieListBasePresenter;
import com.android.szparag.colortv.utils.Utils;
import com.android.szparag.colortv.views.contracts.MovieListBaseView;

import javax.inject.Inject;

import io.realm.Realm;

/**
 * Created by ciemek on 17/10/2016.
 */

public class MovieListPresenter implements MovieListBasePresenter<MovieListBaseView> {


    @Inject
    Realm realm;

    private MovieListBaseView view;
    private RealmMovieGroup movieGroup;


    @Override
    public void setView(MovieListBaseView view) {
        this.view = view;

        Utils.getDagger(view.getAndroidView()).inject(this);
    }


    @Override
    public void populateViewWithMovies(int movieGroupId) {
        view.buildRecycler();
        movieGroup = queryMovieGroup(movieGroupId);
        view.updateRecycler(queryMovieGroup(movieGroupId).getMovies());
    }

    @Override
    public Movie queryMovieFromGroup(int movieGroupId, int moviePosition) {
        return queryMovieGroup(movieGroupId).getMovies().get(moviePosition);
    }

    @Override
    public Movie queryMovieFromGroup(int moviePosition) {
        return movieGroup.getMovies().get(moviePosition);
    }

    @Override
    public RealmMovieGroup queryMovieGroup(int movieGroupId) {
        return realm.where(RealmMovieGroup.class).equalTo("groupId", movieGroupId-1).findFirst();
    }
}
