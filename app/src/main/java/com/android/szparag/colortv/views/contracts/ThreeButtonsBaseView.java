package com.android.szparag.colortv.views.contracts;


import android.view.View;

import com.android.szparag.colortv.ColorTVApplication;
import com.android.szparag.colortv.backend.models.Movie;

/**
 * Created by ciemek on 17/10/2016.
 */

public interface ThreeButtonsBaseView extends BaseAndroidView {

    void onClickButtonDataset(int movieGroupId);

    void hideMovieTextView();
    void showMovieTextView();
    void updateMovieTextView(Movie movie);

}
