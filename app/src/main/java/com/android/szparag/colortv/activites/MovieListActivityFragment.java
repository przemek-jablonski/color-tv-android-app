package com.android.szparag.colortv.activites;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.android.szparag.colortv.R;
import com.android.szparag.colortv.utils.Constants;

/**
 * A placeholder fragment containing a simple view.
 */
public class MovieListActivityFragment extends Fragment {

    public static MovieListActivityFragment newInstance(int movieGroupId) {
        MovieListActivityFragment fragment = new MovieListActivityFragment();

        Bundle fragmentBundle = new Bundle();
        int m = movieGroupId;
        fragmentBundle.putInt(Constants.MOVIE_LIST_INTENT_EXTRA_KEY, movieGroupId);
        fragment.setArguments(fragmentBundle);

        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_movie_list, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        int m = getArguments().getInt(Constants.MOVIE_LIST_INTENT_EXTRA_KEY);

        ((TextView) getView().findViewById(R.id.textview_fragment_test)).setText(
                Integer.toString(m)
        );
    }
}
