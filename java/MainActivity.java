

package com.example.sunaustin8.movies;

import android.content.Context;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.sunaustin8.movies.Movie;
import com.example.sunaustin8.movies.MovieAPI;
import com.example.sunaustin8.movies.MovieAdapter;
import com.example.sunaustin8.movies.MovieCollection;
import com.example.sunaustin8.movies.R;
import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;
import com.squareup.picasso.Picasso;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.Charset;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    public static final String urlString = "https://api.themoviedb.org/3/movie/popular?api_key=";

    private RecyclerView mRecyclerView;
    private MovieAdapter movieAdapter;
    private ArrayList<Movie> moviesList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));

        movieAdapter = new MovieAdapter(moviesList);
        mRecyclerView.setAdapter(movieAdapter);

        try {
            URL url = new URL(urlString + MovieAPI.getKey());
            //fills ArrayList of movies by getting them on a background thread
            new MovieAsyncTask(this).execute(url);

        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }

    //placeholder for the Movie portion of UI
    public class MovieAsyncTask extends AsyncTask<URL, Void, Movie[]> {

        Context context;

        public MovieAsyncTask(Context context) {
            this.context = context;
        }

        @Override
        protected Movie[] doInBackground(URL... params) {
            try {
                URL url = params[0];
                URLConnection connection = url.openConnection();
                connection.connect();

                InputStream inStream = connection.getInputStream();
                InputStreamReader inStreamReader = new InputStreamReader(inStream, Charset.forName("UTF-8"));

                Gson gson = new Gson();
                MovieCollection movieCollection = gson.fromJson(inStreamReader, MovieCollection.class);
                return movieCollection.getResults();

            } catch (IOException e) {
                Log.d("NewsAsyncTask", "failed to get data from network", e);
                return null;
            }
        }

        @Override
        protected void onPostExecute(Movie[] movies) {

            //pop message to the UI if unsuccessful in retrieving movies
            if (movies == null) {
                Toast.makeText(context, "Failed to get network data", Toast.LENGTH_LONG).show();
                return;
            }
            moviesList.clear();
            for (Movie movie : movies) {
                Log.d("MOVIES", movie.getTitle());

                moviesList.add(movie);
            }
            movieAdapter.notifyDataSetChanged();
        }
    }
}