package com.example.sunaustin8.movies;

/**
 * Created by sunaustin8 on 4/2/17.
 */

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.squareup.picasso.Picasso;

import static android.R.attr.author;

public class DetailActivity extends AppCompatActivity {

    private TextView mTitleTextView;
    private TextView mIdTextView;
    private TextView mBodyTextView;
    private TextView mOriginalTextView;
    private ImageView mImageView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        // get references to the views.
        mTitleTextView = (TextView) findViewById(R.id.detailTextView);
        mIdTextView = (TextView) findViewById(R.id.idTextView);
        mBodyTextView = (TextView) findViewById(R.id.bodyTextView);
        mImageView = (ImageView) findViewById(R.id.detailImageView);
        mOriginalTextView = (TextView) findViewById(R.id.originalView);

        Intent intent = getIntent();

        Movie movie = intent.getParcelableExtra(MovieAdapter.MOVIE);

        mTitleTextView.setText(movie.getTitle());
        mIdTextView.setText("id: " + movie.getId());
        mBodyTextView.setText(movie.getOverview());
        mOriginalTextView.setText(movie.getOriginal_title());
        Picasso.with(mImageView.getContext()).load("https://image.tmdb.org/t/p/w185_and_h278_bestv2" +movie.getPoster_path()).into(mImageView);
    }
}