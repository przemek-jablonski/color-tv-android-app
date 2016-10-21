package com.android.szparag.colortv.views.contracts;

import com.android.szparag.colortv.backend.models.Movie;

import java.util.List;

/**
 * Created by ciemek on 17/10/2016.
 */

public interface MovieListBaseView extends BaseAndroidView {

    int getMovieGroupIndex();

    void buildRecycler();

    void updateRecycler(List<Movie> movies);
}
