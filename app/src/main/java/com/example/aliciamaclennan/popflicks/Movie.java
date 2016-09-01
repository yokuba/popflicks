package com.example.aliciamaclennan.popflicks;

/**
 * Created by amac on 8/27/16.
 */
public class Movie {

    private String mTitle;

    private String mDescription;

    private String mRelease;

    private String mPoster;


    public Movie(String title, String description, String release, String poster) {
        mTitle = title;
        mDescription = description;
        mRelease = release;
        mPoster = poster;
    }

    public String getTitle() { return mTitle; }
    public String getDescription() {
        return mDescription;
    }
    public String getRelease() { return mRelease; }
    public String getImage() { return mPoster; }
}

