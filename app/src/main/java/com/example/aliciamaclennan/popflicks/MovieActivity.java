package com.example.aliciamaclennan.popflicks;

import android.app.LoaderManager;
import android.app.LoaderManager.LoaderCallbacks;
import android.content.Context;
import android.content.Intent;
import android.content.Loader;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.GridView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MovieActivity extends AppCompatActivity
                implements LoaderCallbacks<List<Movie>> {

    private static final String LOG_TAG = MovieActivity.class.getName();
    private static final String MOVIE_REQUEST_URL =
        "https://api.themoviedb.org/3/movie/popular?api_key=" + BuildConfig.THE_MOVIE_DB_API_KEY + "&append_to_release=videos";

    private static final int MOVIE_LOADER_ID = 1;
    private MovieAdapter mAdapter;
    private TextView mEmptyStateTextView;

    @Override
protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Find a reference to the {@link ListView} in the layout
        GridView movieListView = (GridView) findViewById(R.id.movie_list);

        mEmptyStateTextView = (TextView) findViewById(R.id.empty_view);
        movieListView.setEmptyView(mEmptyStateTextView);

        // Create a new adapter that takes an empty list of movies as input
        mAdapter = new MovieAdapter(this, new ArrayList<Movie>());

        // Set the adapter on the {@link ListView}
        // so the list can be populated in the user interface
        movieListView.setAdapter(mAdapter);


        // Get a reference to the ConnectivityManager to check state of network connectivity
        ConnectivityManager connMgr = (ConnectivityManager)
        getSystemService(Context.CONNECTIVITY_SERVICE);

        // Get details on the currently active default data network
        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();

        // If there is a network connection, fetch data
        if (networkInfo != null && networkInfo.isConnected()) {
        // Get a reference to the LoaderManager, in order to interact with loaders.
        LoaderManager loaderManager = getLoaderManager();

        // Initialize the loader. Pass in the int ID constant defined above and pass in null for
        // the bundle. Pass in this activity for the LoaderCallbacks parameter (which is valid
        // because this activity implements the LoaderCallbacks interface).
        loaderManager.initLoader(MOVIE_LOADER_ID, null, this);
        } else {
        // Otherwise, display error
        // First, hide loading indicator so error message will be visible
        View loadingIndicator = findViewById(R.id.loading_indicator);
        loadingIndicator.setVisibility(View.GONE);

        // Update empty state with no connection error message
        mEmptyStateTextView.setText(R.string.no_internet_connection);
        }
    }

@Override
public Loader<List<Movie>> onCreateLoader(int i, Bundle bundle) {
        // Create a new loader for the given URL
        return new MovieLoader(this, MOVIE_REQUEST_URL);
        }

    @Override
    public void onLoadFinished(Loader<List<Movie>> loader, List<Movie> movies) {
        View loadingIndicator = findViewById(R.id.loading_indicator);
        loadingIndicator.setVisibility(View.GONE);

        // Set empty state text to display "No movies"
        mEmptyStateTextView.setText(R.string.no_movies);

        // Clear the adapter of previous movie data
        mAdapter.clear();

        // If there is a valid list of Movies, then add them to the adapter's
        // data set. This will trigger the ListView to update.
        if (movies != null && !movies.isEmpty()) {
            mAdapter.addAll(movies);
        }

    }
    @Override
    public void onLoaderReset(Loader<List<Movie>> loader) {
        // Loader reset, so we can clear out our existing data.
        mAdapter.clear();
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            Intent settingsIntent = new Intent(this, MovieFragment.class);
            startActivity(settingsIntent);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
