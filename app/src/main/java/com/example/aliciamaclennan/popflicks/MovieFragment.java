package com.example.aliciamaclennan.popflicks;

import android.app.LoaderManager;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by amac on 8/30/16.
 */




public class MovieFragment  extends AppCompatActivity implements LoaderManager.LoaderCallbacks<List<Movie>> {


    private static final String LOG_TAG = MovieActivity.class.getName();
    private static final String HIGHEST_RATED = "/top_rated";
    private static final String POPULAR = "/popular";
    private static final String NOW_PLAYING = "/now_playing";
    private static final String MOVIE_REQUEST_URL =
            "https://api.themoviedb.org/3/movie" + NOW_PLAYING + "?api_key=" + BuildConfig.THE_MOVIE_DB_API_KEY;

    private static final int MOVIE_LOADER_ID = 1;
    private MovieAdapter mAdapter;
    private TextView mEmptyStateTextView;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Spinner spinner = (Spinner) findViewById(R.id.spinner);
        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.search_array, android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        spinner.setAdapter(adapter);

        // Find a reference to the {@link ListView} in the layout
        final GridView movieListView = (GridView) findViewById(R.id.movie_list);

        mEmptyStateTextView = (TextView) findViewById(R.id.empty_view);
        movieListView.setEmptyView(mEmptyStateTextView);

        // Create a new adapter that takes an empty list of movies as input
        mAdapter = new MovieAdapter(this, new ArrayList<Movie>());


        movieListView.setAdapter(mAdapter);
        movieListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Movie movie = mAdapter.getItem(position);
                Intent detailIntent = new Intent(MovieFragment.this, MovieDetail.class);
                String  title = movie.getTitle();
                String description = movie.getDescription();
                String image = movie.getImage();
                String release = movie.getRelease();

                detailIntent.putExtra("Movie", title);
                detailIntent.putExtra("Plot", description);
                detailIntent.putExtra("Image", image);
                detailIntent.putExtra("Release", release);
                startActivity(detailIntent);

            }
        });

        // Get a reference to the ConnectivityManager to check state of network connectivity
        ConnectivityManager connMgr = (ConnectivityManager)
                getSystemService(Context.CONNECTIVITY_SERVICE);

        // Get details on the currently active default data network
        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();

        // If there is a network connection, fetch data
        if (networkInfo != null && networkInfo.isConnected()) {
            // Get a reference to the LoaderManager, in order to interact with loaders.
            android.app.LoaderManager loaderManager = getLoaderManager();

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
    public android.content.Loader<List<Movie>> onCreateLoader(int i, Bundle bundle) {
        // Create a new loader for the given URL
        return new MovieLoader(this, MOVIE_REQUEST_URL);
    }

    @Override
    public void onLoadFinished(android.content.Loader<List<Movie>> loader, List<Movie> movies) {
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
    public void onLoaderReset(android.content.Loader<List<Movie>> loader) {
        // Loader reset, so we can clear out our existing data.
        mAdapter.clear();
    }
       // setContentView(R.layout.settings_activity);
}

//    @Override
//    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//
//    }


//    public static class MovieDetail extends PreferenceFragment {
//
//    }



