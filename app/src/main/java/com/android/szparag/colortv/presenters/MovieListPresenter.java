package com.android.szparag.colortv.presenters;

import com.android.szparag.colortv.backend.models.Movie;
import com.android.szparag.colortv.backend.models.realm.RealmMovieGroup;
import com.android.szparag.colortv.presenters.contracts.MovieListBasePresenter;
import com.android.szparag.colortv.utils.Utils;
import com.android.szparag.colortv.views.contracts.MovieListBaseView;

import java.util.LinkedList;

import javax.inject.Inject;

import io.realm.Realm;
import io.realm.RealmList;

/**
 * Created by ciemek on 17/10/2016.
 */

public class MovieListPresenter implements MovieListBasePresenter<MovieListBaseView> {

    @Inject
    Realm realm;

    MovieListBaseView view;

    //TODO: CARDS INSTEAD OF RECYCLER (main android website)
    //TODO: PARALLAX IMAGES

    @Override
    public void setView(MovieListBaseView view) {
        this.view = view;

        Utils.getDagger(view.getAndroidView()).inject(this);
    }

    @Override
    public void populateViewWithMovies(int movieGroupId) {
        view.buildRecycler();
        RealmMovieGroup group = getMovieGroup(movieGroupId -1);
        RealmList<Movie> movies = group.getMovies();

        view.updateRecycler(movies);
    }

    @Override
    public RealmMovieGroup getMovieGroup(int movieGroupId) {
        int count = (int) realm.where(RealmMovieGroup.class).count();
        int validCount = ((int) realm.where(RealmMovieGroup.class).equalTo("groupId", movieGroupId).count());
        return realm.where(RealmMovieGroup.class).equalTo("groupId", movieGroupId).findFirst();
    }
}
