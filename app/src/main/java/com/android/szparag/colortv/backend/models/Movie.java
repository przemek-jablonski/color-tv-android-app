package com.android.szparag.colortv.backend.models;

import io.realm.RealmModel;
import io.realm.annotations.PrimaryKey;
import io.realm.annotations.RealmClass;

/**
 * Created by ciemek on 17/10/2016.
 */

@RealmClass
public class Movie implements RealmModel {

    @PrimaryKey
    private String  videoId;
    private String  title;
    private String  description;
    private int     durationInMinutes;
    private float   rating;
    private String  viewsCount;
    private String  thumbnailUrl;

    public String getVideoId() {
        return this.videoId;
    }

    public String getTitle() {
        return this.title;
    }

    public String getDescription() {
        return this.description;
    }

    public int getDurationInMinutes() {
        return this.durationInMinutes;
    }

    public float getRating() {
        return this.rating;
    }

    public String getViewsCount() {
        return this.viewsCount;
    }

    public String getThumbnailUrl() {
        return this.thumbnailUrl;
    }
}
