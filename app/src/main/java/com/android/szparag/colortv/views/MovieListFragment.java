package com.android.szparag.colortv.views;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.test.mock.MockApplication;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.android.szparag.colortv.ColorTVApplication;
import com.android.szparag.colortv.R;
import com.android.szparag.colortv.adapters.MovieAdapter;
import com.android.szparag.colortv.adapters.RecyclerOnPosClickListener;
import com.android.szparag.colortv.backend.models.Movie;
import com.android.szparag.colortv.backend.models.MovieGroup;
import com.android.szparag.colortv.backend.models.realm.RealmMovieGroup;
import com.android.szparag.colortv.presenters.contracts.MovieListBasePresenter;
import com.android.szparag.colortv.utils.Constants;
import com.android.szparag.colortv.utils.Utils;
import com.android.szparag.colortv.views.contracts.MovieListBaseView;

import java.io.InputStream;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A placeholder fragment containing a simple view.
 */
public class MovieListFragment extends Fragment implements MovieListBaseView {

    @Inject
    MovieListBasePresenter presenter;

    @BindView(R.id.recycler_movie_list)
    RecyclerView recyclerMovieList;


    private MovieAdapter adapter;


    public static MovieListFragment newInstance(int movieGroupId) {
        MovieListFragment fragment = new MovieListFragment();

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

        Utils.getDagger(this).inject(this);
        ButterKnife.bind(this, getView());

//        textViewFragmentTest.setText(Integer.toString(getMovieGroupIndex()));

        presenter.setView(this);

        presenter.populateViewWithMovies(getMovieGroupIndex());
    }


    @Override
    public void buildRecycler() {
        recyclerMovieList.setLayoutManager(
                new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false)
        );

        recyclerMovieList.setHasFixedSize(true); //todo: check if this should be 'true'
//        recyclerMovieList.addItemDecoration();

        adapter = new MovieAdapter(new RecyclerOnPosClickListener() {
            @Override
            public void OnPosClick(View v, int position) {
                getActivity().finish();
            }
        });
        recyclerMovieList.setAdapter(adapter);
    }

    @Override
    public void updateRecycler(List<Movie> movies) {
        adapter.updateItems(movies);
    }

    @Override
    public int getMovieGroupIndex() {
        return getArguments().getInt(Constants.MOVIE_LIST_INTENT_EXTRA_KEY);
    }

    @Override
    public String getPackageName() {
        return getActivity().getPackageName();
    }

    @Override
    public InputStream getRawResource(int rawResId) {
        return getResources().openRawResource(rawResId);
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
