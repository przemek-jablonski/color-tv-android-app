package com.android.szparag.colortv.views;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.android.szparag.colortv.ColorTVApplication;
import com.android.szparag.colortv.R;
import com.android.szparag.colortv.activites.MovieListActivity;
import com.android.szparag.colortv.presenters.contracts.ThreeButtonsBasePresenter;
import com.android.szparag.colortv.utils.Utils;
import com.android.szparag.colortv.views.contracts.ThreeButtonsBaseView;

import java.io.InputStream;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.android.szparag.colortv.utils.Constants.MOVIE_GROUP_INDEX_1;
import static com.android.szparag.colortv.utils.Constants.MOVIE_GROUP_INDEX_2;
import static com.android.szparag.colortv.utils.Constants.MOVIE_GROUP_INDEX_3;
import static com.android.szparag.colortv.utils.Constants.MOVIE_ID_REQUEST;
import static com.android.szparag.colortv.utils.Constants.MOVIE_ID_RESPONSE_OK;
import static com.android.szparag.colortv.utils.Constants.MOVIE_LIST_INTENT_EXTRA_KEY;


public class ThreeButtonsFragment extends Fragment implements ThreeButtonsBaseView {

    @Inject
    ThreeButtonsBasePresenter presenter;

    @BindView(R.id.button_dataset_1)
    Button      buttonDataset1;

    @BindView(R.id.button_dataset_2)
    Button      buttonDataset2;

    @BindView(R.id.button_dataset_3)
    Button      buttonDataset3;

    @BindView(R.id.textview_movie_id)
    TextView    textViewMovieId;

    @OnClick(R.id.button_dataset_1)
    void onClickButtonDataset1() {
        onClickButtonDataset(MOVIE_GROUP_INDEX_1);
    }

    @OnClick(R.id.button_dataset_2)
    void onClickButtonDataset2() {
        onClickButtonDataset(MOVIE_GROUP_INDEX_2);
    }

    @OnClick(R.id.button_dataset_3)
    void onClickButtonDataset3() {
        onClickButtonDataset(MOVIE_GROUP_INDEX_3);
    }


    //android lifecycle callbacks:
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_three_buttons, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Utils.getDagger(this).inject(this);
        ButterKnife.bind(this, getView());
        presenter.setView(this);
        hideMovieTextView();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == MOVIE_ID_REQUEST && resultCode == MOVIE_ID_RESPONSE_OK) {
            showMovieTextView();
            updateMovieTextView(data.getStringExtra(MOVIE_LIST_INTENT_EXTRA_KEY));
        }
    }

    @Override
    public void onStop() {
        super.onStop();
        hideMovieTextView();
    }


    //base view methods implementations:
    @Override
    public void onClickButtonDataset(int movieGroupId) {
        Intent movieListIntent = new Intent(getActivity(), MovieListActivity.class);
        movieListIntent.putExtra(MOVIE_LIST_INTENT_EXTRA_KEY, movieGroupId);
        startActivityForResult(movieListIntent, MOVIE_ID_REQUEST);
    }

    @Override
    public void hideMovieTextView() {
        textViewMovieId.setVisibility(View.GONE);
    }

    @Override
    public void showMovieTextView() {
        textViewMovieId.setVisibility(View.VISIBLE);
    }

    @Override
    public void updateMovieTextView(String text) {
        textViewMovieId.setText(getString(R.string.textview_movie_selected, text));
    }

    //BaseAndroidView methods implementations:
    @Override
    public InputStream getRawResource(int rawResId) {
        return getContext().getResources().openRawResource(rawResId);
    }

    @Override
    public String getPackageName() {
        return getActivity().getPackageName();
    }

    @Override
    public ColorTVApplication getAndroidApplication() {
        return ((ColorTVApplication) getActivity().getApplication());
    }

    @Override
    public Fragment getAndroidView() {
        return this;
    }


}
