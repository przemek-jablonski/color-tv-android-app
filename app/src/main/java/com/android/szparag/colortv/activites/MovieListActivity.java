package com.android.szparag.colortv.activites;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.android.szparag.colortv.R;
import com.android.szparag.colortv.views.MovieListFragment;

import static com.android.szparag.colortv.utils.Constants.MOVIE_LIST_INTENT_EXTRA_KEY;

public class MovieListActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_list);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        getSupportFragmentManager().beginTransaction().add(
                R.id.activity_movie_list_fragment_placeholder,
                MovieListFragment.newInstance(getIntent().getIntExtra(MOVIE_LIST_INTENT_EXTRA_KEY, -1))
        ).commit();
    }

}
