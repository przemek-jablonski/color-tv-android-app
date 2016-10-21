package com.android.szparag.colortv.presenters;

import android.util.Log;

import com.android.szparag.colortv.R;
import com.android.szparag.colortv.backend.models.Movie;
import com.android.szparag.colortv.backend.models.MovieGroup;
import com.android.szparag.colortv.backend.models.realm.RealmMovieGroup;
import com.android.szparag.colortv.presenters.contracts.ThreeButtonsBasePresenter;
import com.android.szparag.colortv.utils.Utils;
import com.android.szparag.colortv.views.contracts.ThreeButtonsBaseView;
import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.StringWriter;
import java.io.Writer;

import javax.inject.Inject;

import io.realm.Realm;

import static com.android.szparag.colortv.utils.Constants.COLORTV_LOG_TAG;
import static com.android.szparag.colortv.utils.Constants.JSON_MOVIES_COUNT_PREDICTED;
import static com.android.szparag.colortv.utils.Constants.JSON_MOVIES_GROUP_COUNT_PREDICTED;

/**
 * Created by ciemek on 17/10/2016.
 */

public class ThreeButtonsPresenter implements ThreeButtonsBasePresenter<ThreeButtonsBaseView> {

    @Inject
    Gson gson;

    @Inject
    Realm realm;

    private ThreeButtonsBaseView view;


    @Override
    public void setView(ThreeButtonsBaseView view) {
        this.view = view;

        Utils.getDagger(view.getAndroidView()).inject(this);

        if (realm.where(Movie.class).count() != JSON_MOVIES_COUNT_PREDICTED) {
            realm.executeTransaction(new Realm.Transaction() {
                @Override
                public void execute(Realm realm) {
                    purgeRealm();

                    MovieGroup[] realmMovieGroupsFromJson = readVideosFromJson();

                    if (!populateRealm(realmMovieGroupsFromJson)) {
                        Log.e(COLORTV_LOG_TAG, "Realm not populated correctly");
                    }
                }
            });
        }
    }

    @Override
    public boolean purgeRealm() {
        return (realm.where(Movie.class).findAll().deleteAllFromRealm()
                && realm.where(RealmMovieGroup.class).findAll().deleteAllFromRealm()) ? true : false;
    }

    @Override
    public boolean populateRealm(MovieGroup[] movieGroupsFromJson) {
        for (int groupCount = 0; groupCount < movieGroupsFromJson.length; ++groupCount) {

            RealmMovieGroup realmRealmMovieGroup = realm.createObject(RealmMovieGroup.class, groupCount);

            for (int movieCount = 0; movieCount < movieGroupsFromJson[groupCount].getMovies().length; ++movieCount) {
                Movie realmMovie = realm.copyToRealmOrUpdate(movieGroupsFromJson[groupCount].getMovies()[movieCount]);
                realmRealmMovieGroup.addVideo(movieCount, realmMovie);
            }
        }

        return (realm.where(Movie.class).count() == JSON_MOVIES_COUNT_PREDICTED
                && realm.where(RealmMovieGroup.class).count() == JSON_MOVIES_GROUP_COUNT_PREDICTED) ? true : false;
    }

    //todo: make async task with this or whatever
    private MovieGroup[] readVideosFromJson() {
        try {
            return deserializeVideosJson(getMoviesJsonFile().toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }


    private Writer getMoviesJsonFile() throws IOException {
        InputStream inputStream = view.getRawResource(R.raw.data);

        Writer writer = new StringWriter();
        char[] buffer = new char[1024];
        try {
            Reader reader = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"));
            int n;
            while ((n = reader.read(buffer)) != -1) {
                writer.write(buffer, 0, n);
            }
        } finally {
            inputStream.close();
        }

        return writer;
    }


    private MovieGroup[] deserializeVideosJson(String fullJsonString) {

        String[] separatedMovieGroupsJsonString = fullJsonString.split("[\\r\\n]+");
        MovieGroup[] movies = new MovieGroup[separatedMovieGroupsJsonString.length];

        for (int i = 0; i < separatedMovieGroupsJsonString.length; ++i) {
            movies[i] = gson.fromJson(separatedMovieGroupsJsonString[i], MovieGroup.class);
        }

        return movies;
    }


    @Override
    public void killRealm() {
        //todo: what if screen rotates? presenter is injected as singleton, how realm will be recreated on screen recreation?
        realm.close();
    }
}
