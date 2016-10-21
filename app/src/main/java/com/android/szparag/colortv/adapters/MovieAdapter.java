package com.android.szparag.colortv.adapters;

import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.szparag.colortv.R;
import com.android.szparag.colortv.backend.models.Movie;
import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;


/**
 * Created by ciemek on 20/10/2016.
 */

/**
 *  Implementation of BaseAdapter, with generic of Movie object.
 *  Handles more high-level RecyclerView.Adapter's calls, rest of the methods are handled in
 *  BaseAdapter<T> superclass.
 */
public class MovieAdapter extends BaseAdapter<Movie> {


    public MovieAdapter(@Nullable RecyclerOnPosClickListener clickListener) {
        super(clickListener);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new MovieViewHolder(
                LayoutInflater
                        .from(parent.getContext())
                        .inflate(R.layout.recycler_item_movie, parent, false)
        );
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        onBindMovieViewHolder(((MovieViewHolder) holder), position);
    }

    private void onBindMovieViewHolder(MovieViewHolder holder, int position) {
        Movie item = items.get(position);

        holder.textRating.setText(Float.toString(item.getRating()));
        holder.textViews.setText(item.getViewsCount());
        holder.textTitle.setText(item.getTitle());
        holder.textDescription.setText(item.getDescription());
        holder.textDuration.setText(Integer.toString(item.getDurationInMinutes())+"m");
        holder.textDuration.setText(Integer.toString(item.getDurationInMinutes())+"m");
        Picasso.with(holder.imageMovie.getContext())
                .load(item.getThumbnailUrl())
                .into(holder.imageMovie);
    }


    public class MovieViewHolder extends ViewHolder {

        @BindView(R.id.recycler_item_movie_button)      Button      textButton;
        @BindView(R.id.recycler_item_movie_image)       ImageView   imageMovie;
        @BindView(R.id.recycler_item_movie_duration)    TextView    textDuration;
        @BindView(R.id.recycler_item_movie_rating)      TextView    textRating;
        @BindView(R.id.recycler_item_movie_views)       TextView    textViews;
        @BindView(R.id.recycler_item_movie_title)       TextView    textTitle;
        @BindView(R.id.recycler_item_movie_description) TextView    textDescription;

        public MovieViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);

            if (recyclerOnPosClickListener != null) {
                textButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        recyclerOnPosClickListener.OnPosClick(view, getLayoutPosition());
                    }
                });
            }
        }
    }

}
