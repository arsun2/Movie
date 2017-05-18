package com.example.sunaustin8.movies;

import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import static android.R.id.message;
import static android.provider.AlarmClock.EXTRA_MESSAGE;
import android.net.Uri;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import static com.example.sunaustin8.movies.R.styleable.RecyclerView;

/**
 * Created by sunaustin8 on 3/21/17.
 */

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.ViewHolder> {

    public static final String TITLE = "TITLE";
    public static final String ID = "ID";
    public static final String BODY = "BODY";
    public static final String IMG_URL = "IMG_URL";
    public static final String JSON = "JSON";
    public static final String MOVIE = "MOVIE";

    ArrayList<Movie> movies;

    public MovieAdapter(ArrayList<Movie> movies) {
        this.movies = movies;
    }

    /** Called to fill screen with views until entire screen is filled.
     * Then it recycles after that
     * @param parent the parent object which is the RecyclerView
     * @param viewType different types of views in our parent which is our RecyclerView
     * @return New ViewHolder that we have allocated
     */
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        final View movieListItem = LayoutInflater.from(parent.getContext()).
                inflate(R.layout.movie_list_item, parent, false);
        return new ViewHolder(movieListItem);
    }

    /** this method updates a ViewHolder every time it needs to hold data for a
     * different move. This updates everything in the RecyclerView
     * @param holder ViewHolder that has the View we need to update in the UI
     * @param position the index of current movie in ArrayList of movies
     */
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final Movie movie = movies.get(position);

        holder.titleView.setText(movie.getTitle());
        String movieString = "" + movie.getVote_average();
        holder.voteView.setText("Vote average: " + movieString);
        Picasso.with(holder.imageView.getContext())
                .load("https://image.tmdb.org/t/p/w185_and_h278_bestv2" + movie.getPoster_path()).into(holder.imageView);

        // Launches the DetailActivity for each movie when each View is clicked
        holder.view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), DetailActivity.class);
                intent.putExtra(MOVIE, movie);
                v.getContext().startActivity(intent);
            }
        });
    }

    //class for the Adapter that links references to all the subviews and widgets
    public static class ViewHolder extends RecyclerView.ViewHolder {
        public View view;
        public TextView titleView;
        public TextView voteView;
        public ImageView imageView;

        public ViewHolder(View itemView) {
            super(itemView);
            view = itemView;
            titleView = (TextView) itemView.findViewById(R.id.titleTextView);
            voteView = (TextView) itemView.findViewById(R.id.voteTextView);
            imageView = (ImageView) itemView.findViewById(R.id.imageView);
        }
    }

    @Override
    public int getItemCount() {
        return movies.size();
    }

}

