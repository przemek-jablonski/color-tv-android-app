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
import io.realm.RealmResults;

/**
 * Created by ciemek on 17/10/2016.
 */

public class ThreeButtonsPresenter implements ThreeButtonsBasePresenter<ThreeButtonsBaseView> {

    private ThreeButtonsBaseView view;

    @Inject
    Gson gson;

    @Inject
    Realm realm;

    @Override
    public void setView(ThreeButtonsBaseView view) {
        this.view = view;

        Utils.getDagger(view.getAndroidView()).inject(this);

        long videosCount = realm.where(Movie.class).count();

        //todo: always will be videosCount == 8 (eg. every time updating realm), fix it
        if (videosCount != 8) {
            Log.e("TROL", "videos count different than 9 (is " + videosCount + ")");
            realm.executeTransaction(new Realm.Transaction() {
                @Override
                public void execute(Realm realm) {

                    Log.e("TROL", "transaction begins");

                    //debug!
                    if (realm.where(Movie.class).findAll().deleteAllFromRealm()
                            && realm.where(RealmMovieGroup.class).findAll().deleteAllFromRealm()) {
                        Log.e("TROL", "deleted all from realm");
                    }

                    MovieGroup[] realmMovieGroupsFromJson = readVideosFromJson();

                    for (int groupCount = 0; groupCount < realmMovieGroupsFromJson.length; ++groupCount) {

                        RealmMovieGroup realmRealmMovieGroup = realm.createObject(RealmMovieGroup.class, groupCount);

                        for (int movieCount = 0; movieCount < realmMovieGroupsFromJson[groupCount].getMovies().length; ++movieCount) {
                            Movie realmMovie = realm.copyToRealmOrUpdate(realmMovieGroupsFromJson[groupCount].getMovies()[movieCount]);
                            realmRealmMovieGroup.addVideo(movieCount, realmMovie);
                        }
                    }
                }
            });
        } else {
            Log.e("TROL", "videos count is " + videosCount);
        }

        RealmResults<RealmMovieGroup> mg = realm.where(RealmMovieGroup.class).findAll();
        RealmResults<Movie> m = realm.where(Movie.class).findAll();

        Log.e("TROL", "m(4).views:" + m.get(4).getViewsCount());

        Log.e("TROL", "method ends, total items count in realm:"
            + realm.where(Movie.class).count() + ".");



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

    //todo: brief documentation

    @Override
    public void killRealm() {
        //fixme: what if screen rotates? presenter is injected as singleton, how realm will be recreated on screen recreation?
        realm.close();
    }
}
