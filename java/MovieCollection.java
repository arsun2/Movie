package com.example.sunaustin8.movies;

/**
 * Created by sunaustin8 on 3/21/17.
 */

public class MovieCollection {

    private int page;
    private Movie[] results;
    private int total_resultants;
    private int total_pages;


    public int getPage() {
        return page;
    }

    public Movie[] getResults() {
        return results;
    }

    public int getTotal_resultants() {
        return total_resultants;
    }

    public int getTotal_pages() {
        return total_pages;
    }

    /**
     * Returns a string with all of the titles of the movies in a MovieCollection
     *
     * @return a string with all of the titles of the Movies in a MovieCollection separated
     * by a newline character
     */
    public String printAll() {
        String allAnswer = "";
        for (int x = 0; x < results.length; x++) {
            allAnswer += results[x].getTitle() + "\n";
        }
        return allAnswer;
    }

    /**
     * Return a string of all movies that has a vote_average that exceeds the parameter
     *
     * @param vote_average - threshold to compare to with the vote_average
     *                     of each Movie in a MovieCollection
     * @return a string list of the title of all movies that exceed the parameter, each
     * one separated by a newline character.
     */
    public String printByVoteAverage(double vote_average) {
        String voteAnswer = "";
        for (int i = 0; i < results.length; i++) {
            if (results[i].getVote_average() > vote_average) {
                voteAnswer += results[i].getTitle() + "\n";
            }
        }
        return voteAnswer;
    }
}