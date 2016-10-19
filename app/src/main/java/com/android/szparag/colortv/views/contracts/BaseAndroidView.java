package com.android.szparag.colortv.views.contracts;

import android.app.Activity;
import android.app.Application;
import android.support.v4.app.Fragment;

import com.android.szparag.colortv.ColorTVApplication;

import java.io.InputStream;

/**
 * Created by ciemek on 17/10/2016.
 */

public interface BaseAndroidView {

    String getPackageName();

    InputStream getRawResource(int rawResId);

    ColorTVApplication getAndroidApplication();

    Fragment getAndroidView();

}
