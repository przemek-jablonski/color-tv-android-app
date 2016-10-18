package com.android.szparag.colortv.backend.models;

import com.google.gson.annotations.SerializedName;

/**
 * Created by ciemek on 18/10/2016.
 */

public class MovieGroup {

    @SerializedName("videos")
    private Movie[] movies;

    public Movie[] getMovies() {
        return movies;
    }
}
