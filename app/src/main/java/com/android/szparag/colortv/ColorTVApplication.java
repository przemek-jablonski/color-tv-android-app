package com.android.szparag.colortv;

import android.app.Application;

import com.android.szparag.colortv.dagger.components.DaggerMainComponent;
import com.android.szparag.colortv.dagger.components.MainComponent;
import com.android.szparag.colortv.dagger.modules.ColorTVModule;
import com.facebook.stetho.Stetho;
import com.squareup.picasso.Picasso;

import io.realm.Realm;
import io.realm.RealmConfiguration;

/**
 * Created by ciemek on 17/10/2016.
 */

public class ColorTVApplication extends Application {

    private MainComponent daggerMainComponent;

    @Override
    public void onCreate() {
        super.onCreate();

        //initializing dagger dependency injection
        daggerMainComponent = DaggerMainComponent
                .builder()
                .colorTVModule(new ColorTVModule(this))
                .build();


        //initializing realm mobile database
        Realm.init(this);
        Realm.setDefaultConfiguration(new RealmConfiguration.Builder()
                .name("movies.realm")
                .deleteRealmIfMigrationNeeded()
                .build()
        );

        Stetho.initializeWithDefaults(this);

        Picasso.with(this).setLoggingEnabled(true);
        Picasso.with(this).setLoggingEnabled(true);
    }

    public MainComponent getDaggerMainComponent() {
        return daggerMainComponent;
    }
}
