package com.android.szparag.colortv.adapters;

import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.android.szparag.colortv.R;
import com.android.szparag.colortv.backend.models.Movie;

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
                        .inflate(
                                R.layout.recycler_item_movie,
                                parent,
                                false
                        )
        );
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        Movie item = items.get(position);
        ((MovieViewHolder) holder).textDuration.setText(Integer.toString(item.getDurationInMinutes()));
        ((MovieViewHolder) holder).textRating.setText(Float.toString(item.getRating()));
        ((MovieViewHolder) holder).textViews.setText(item.getViewsCount());
        ((MovieViewHolder) holder).textTitle.setText(item.getTitle());
        ((MovieViewHolder) holder).textDescription.setText(item.getDescription());
        ((MovieViewHolder) holder).textButton.setText("klikaj tutej se");
    }

    @Override
    public int getItemCount() {
        return super.getItemCount();
    }

    public class MovieViewHolder extends RecyclerView.ViewHolder {

        TextView textDuration;
        TextView textRating;
        TextView textViews;
        TextView textTitle;
        TextView textDescription;
        TextView textButton;

        public MovieViewHolder(View itemView) {
            super(itemView);

            textDuration = (TextView) itemView.findViewById(R.id.recycler_item_movie_duration);
            textRating = (TextView) itemView.findViewById(R.id.recycler_item_movie_rating);
            textViews = (TextView) itemView.findViewById(R.id.recycler_item_movie_views);
            textTitle = (TextView) itemView.findViewById(R.id.recycler_item_movie_title);
            textDescription = (TextView) itemView.findViewById(R.id.recycler_item_movie_description);
            textButton = (TextView) itemView.findViewById(R.id.recycler_item_movie_button);

            if (recyclerOnPosClickListener != null) {
                itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        recyclerOnPosClickListener.OnPosClick(view, getLayoutPosition());
                    }
                });
            }

        }

    }


}
