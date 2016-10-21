package com.android.szparag.colortv.views.contracts;

import android.support.v4.app.Fragment;

import com.android.szparag.colortv.ColorTVApplication;

import java.io.InputStream;

/**
 * Created by ciemek on 17/10/2016.
 */

/**
 * Base interface for views based on Android framework.
 * Provides handy hooks for presenters or any other objects that are not a part of Android SDK.
 */
public interface BaseAndroidView {

    String getPackageName();

    InputStream getRawResource(int rawResId);

    ColorTVApplication getAndroidApplication();

    Fragment getAndroidView();

}
