package com.android.szparag.colortv.views.contracts;



/**
 * Created by ciemek on 17/10/2016.
 */

public interface ThreeButtonsBaseView extends BaseAndroidView {

    void onClickButtonDataset(int movieGroupId);

    void hideMovieTextView();
    void showMovieTextView();
    void updateMovieTextView(String text);

}
