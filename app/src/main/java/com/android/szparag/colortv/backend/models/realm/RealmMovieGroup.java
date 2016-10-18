package com.android.szparag.colortv.backend.models.realm;

import com.android.szparag.colortv.backend.models.Movie;

import java.util.List;

import io.realm.RealmList;
import io.realm.RealmModel;
import io.realm.annotations.PrimaryKey;
import io.realm.annotations.RealmClass;

/**
 * Created by ciemek on 18/10/2016.
 */

@RealmClass
public class RealmMovieGroup implements RealmModel {

    @PrimaryKey
    private int         groupId;
    private RealmList<Movie> movies;


    public void addVideo(int videoNumber, Movie movie) {
        movies.add(videoNumber, movie);
    }

    public int getGroupId() {
        return this.groupId;
    }

    public RealmList<Movie> getMovies() {
        return this.movies;
    }
}
