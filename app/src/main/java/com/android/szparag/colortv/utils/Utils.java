package com.android.szparag.colortv.utils;

import android.app.Activity;
import android.support.v4.app.Fragment;

import com.android.szparag.colortv.ColorTVApplication;
import com.android.szparag.colortv.dagger.components.MainComponent;

/**
 * Created by ciemek on 17/10/2016.
 */

public class Utils {

    public static MainComponent getDagger(Activity activity) throws ClassCastException {
        return ((ColorTVApplication) activity.getApplication()).getDaggerMainComponent();
    }

    public static MainComponent getDagger(Fragment fragment) throws ClassCastException {
        return getDagger(fragment.getActivity());
    }

}
