package com.android.szparag.colortv.adapters;

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

        ((MovieViewHolder) holder).textDuration.setText(Integer.toString(item.getDurationInMinutes()));
        ((MovieViewHolder) holder).textRating.setText(Float.toString(item.getRating()));
        ((MovieViewHolder) holder).textViews.setText(item.getViewsCount());
        ((MovieViewHolder) holder).textTitle.setText(item.getTitle());
        ((MovieViewHolder) holder).textDescription.setText(item.getDescription());
        ((MovieViewHolder) holder).textButton.setText("klikaj tutej se");

        Picasso
                .with(((MovieViewHolder) holder).textDuration.getContext())
                .load(item.getThumbnailUrl().substring(0, item.getThumbnailUrl().lastIndexOf('?')))
//                .placeholder(R.color.thumbnail_placeholder_white)
                .into(((MovieViewHolder) holder).imageMovie);

        ((MovieViewHolder) holder).getBackgroundImage().reuse();
    }

    @Override
    public int getItemCount() {
        return super.getItemCount();
    }

    public class MovieViewHolder extends ParallaxViewHolder {

        ParallaxImageView imageMovie;

        TextView    textDuration;
        TextView    textRating;
        TextView    textViews;

        TextView    textTitle;
        TextView    textDescription;

        Button      textButton;

        public MovieViewHolder(View itemView) {
            super(itemView);

            //todo: use butterknife here
            imageMovie = ((ParallaxImageView) itemView.findViewById(R.id.recycler_item_movie_image));

            textDuration = (TextView) itemView.findViewById(R.id.recycler_item_movie_duration);
            textRating = (TextView) itemView.findViewById(R.id.recycler_item_movie_rating);
            textViews = (TextView) itemView.findViewById(R.id.recycler_item_movie_views);

            textTitle = (TextView) itemView.findViewById(R.id.recycler_item_movie_title);
            textDescription = (TextView) itemView.findViewById(R.id.recycler_item_movie_description);

            textButton = (Button) itemView.findViewById(R.id.recycler_item_movie_button);


            //fixme:java.lang.NullPointerException: Attempt to invoke virtual method 'void com.yayandroid.parallaxrecyclerview.ParallaxImageView.setListener(com.yayandroid.parallaxrecyclerview.ParallaxImageView$ParallaxImageListener)' on a null object reference
            if (recyclerOnPosClickListener != null) {
                textButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        recyclerOnPosClickListener.OnPosClick(view, getLayoutPosition());
                    }
                });
            }

        }

        @Override
        public int getParallaxImageId() {
            return R.id.recycler_item_movie_image;
        }

    }


}
