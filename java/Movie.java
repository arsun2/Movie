package com.example.sunaustin8.movies;

import android.os.Parcel;
import android.os.Parcelable;
import android.test.suitebuilder.TestSuiteBuilder;

/**
 * Created by sunaustin8 on 3/2017.
 */
public class Movie implements Parcelable {

    private String poster_path;
    private boolean adult;
    private String overview;
    private int[] genre_ids;
    private String id;
    private String original_title;
    private String original_language;
    private String title;
    private String backdrop_path;
    private double popularity;
    private int vote_count;
    private boolean video;
    private double vote_average;


    public String getPoster_path() {
        return poster_path;
    }

    public boolean getAdult() {
        return adult;
    }

    public String getOverview() {
        return overview;
    }

    public int[] getGenre_ids() {
        return genre_ids;
    }

    public String getId() {
        return id;
    }

    public String getOriginal_title() {
        return original_title;
    }

    public String getOriginal_language() {
        return original_language;
    }

    public String getTitle() {
        return title;
    }

    public String getBackdrop_path() {
        return backdrop_path;
    }

    public double getPopularity() {
        return popularity;
    }

    public int getVote_count() {
        return vote_count;
    }

    public boolean getVideo() {
        return video;
    }

    public double getVote_average() {
        return vote_average;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.overview);
        dest.writeString(this.title);
        dest.writeString(this.backdrop_path);
        dest.writeString(this.id);
        dest.writeString(this.original_language);
        dest.writeString(this.original_title);
        dest.writeString(this.poster_path);
    }

    public Movie() {
    }

    protected Movie(Parcel in) {
        this.overview = in.readString();
        this.title = in.readString();
        this.backdrop_path = in.readString();
        this.id = in.readString();
        this.original_language = in.readString();
        this.original_title = in.readString();
        this.poster_path = in.readString();
    }

    public static final Creator<Movie> CREATOR = new Creator<Movie>() {
        @Override
        public Movie createFromParcel(Parcel parcel) {
            return new Movie(parcel);
        }

        @Override
        public Movie[] newArray(int size) {
            return new Movie[size];
        }
    };
}
