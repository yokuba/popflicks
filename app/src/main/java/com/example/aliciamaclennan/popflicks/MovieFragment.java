//package com.example.aliciamaclennan.popflicks;
//
//import android.database.Cursor;
//import android.os.Bundle;
//import android.support.v4.app.Fragment;
//import android.support.v4.app.LoaderManager;
//import android.support.v4.content.Loader;
//
//import java.util.List;
//
///**
// * Created by amac on 9/9/16.
// */
//public abstract class MovieFragment extends Fragment implements LoaderManager.LoaderCallbacks<Cursor> {
//
//        @Override
//        public Loader<Cursor> onCreateLoader(int position, Bundle bundle) {
//            // Create a new loader for the given URL
//            return new MovieLoader(this, MOVIE_REQUEST_URL + NOW_PLAYING);
//        }
//
//    @Override
//    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
//
//    }
//
//    @Override
//    public void onLoaderReset(Loader<Cursor> loader) {
//
//    }
//}
