package com.example.aliciamaclennan.popflicks;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;

/**
 * Created by amac on 8/30/16.
 */




    public class SortActivity  extends Fragment {


    private static final String LOG_TAG = SortActivity.class.getName();

    Intent intent;
    private static final int MOVIE_LOADER_ID = 1;
    private MovieAdapter mAdapter;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);



    }
}

