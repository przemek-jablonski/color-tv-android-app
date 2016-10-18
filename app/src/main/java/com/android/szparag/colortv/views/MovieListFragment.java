package com.android.szparag.colortv.views;

import android.app.Activity;
import android.app.Application;
import android.support.v4.app.Fragment;

import com.android.szparag.colortv.views.contracts.MovieListBaseView;

import java.io.InputStream;

/**
 * Created by ciemek on 17/10/2016.
 */

public class MovieListFragment extends Fragment implements MovieListBaseView {

    @Override
    public String getPackageName() {
        return getPackageName();
    }

    @Override
    public InputStream getRawResource(int rawResId) {
        return getContext().getResources().openRawResource(rawResId);
    }

    @Override
    public Application getApplication() {
        return getApplication();
    }

    @Override
    public Fragment getAndroidView() {
        return this;
    }
}
