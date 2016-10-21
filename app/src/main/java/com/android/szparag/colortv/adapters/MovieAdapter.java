package com.android.szparag.colortv.adapters;

import android.graphics.Bitmap;
import android.net.Uri;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.szparag.colortv.R;
import com.android.szparag.colortv.backend.models.Movie;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Transformation;
import com.yayandroid.parallaxrecyclerview.ParallaxImageView;
import com.yayandroid.parallaxrecyclerview.ParallaxViewHolder;


/**
 * Created by ciemek on 20/10/2016.
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
        Movie item = items.get(position);

        ((MovieViewHolder) holder).textRating.setText(Float.toString(item.getRating()));
        ((MovieViewHolder) holder).textViews.setText(item.getViewsCount());
        ((MovieViewHolder) holder).textTitle.setText(item.getTitle());
        ((MovieViewHolder) holder).textDescription.setText(item.getDescription());
        ((MovieViewHolder) holder).textDuration.setText(Integer.toString(item.getDurationInMinutes())+"m");

        Picasso
                .with(((MovieViewHolder) holder).imageMovie.getContext())
                .load(item.getThumbnailUrl())
                .into(((MovieViewHolder) holder).imageMovie);
    }

    @Override
    public int getItemCount() {
        return super.getItemCount();
    }

    public class MovieViewHolder extends RecyclerView.ViewHolder {

        ImageView imageMovie;

        TextView    textDuration;
        TextView    textRating;
        TextView    textViews;

        TextView    textTitle;
        TextView    textDescription;

        Button      textButton;

        public MovieViewHolder(View itemView) {
            super(itemView);

            //todo: use butterknife here
            imageMovie = ((ImageView) itemView.findViewById(R.id.recycler_item_movie_image));

            textDuration = (TextView) itemView.findViewById(R.id.recycler_item_movie_duration);
            textRating = (TextView) itemView.findViewById(R.id.recycler_item_movie_rating);
            textViews = (TextView) itemView.findViewById(R.id.recycler_item_movie_views);

            textTitle = (TextView) itemView.findViewById(R.id.recycler_item_movie_title);
            textDescription = (TextView) itemView.findViewById(R.id.recycler_item_movie_description);

            textButton = (Button) itemView.findViewById(R.id.recycler_item_movie_button);


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
