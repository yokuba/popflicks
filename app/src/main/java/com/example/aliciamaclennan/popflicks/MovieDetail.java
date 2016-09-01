package com.example.aliciamaclennan.popflicks;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.InputStream;

/**
 * Created by amac on 8/30/16.
 */
public class MovieDetail extends FragmentActivity {

    TextView movieTitle;
    TextView moviePlot;
    ImageView moviePoster;
    TextView movieRelease;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getIntent();
        setContentView(R.layout.movie_detail);

        movieTitle = (TextView) findViewById(R.id.title);
        moviePlot = (TextView) findViewById(R.id.description);
        moviePoster = (ImageView) findViewById(R.id.movie_thumbnail);
        movieRelease = (TextView) findViewById(R.id.release_date);

        Intent intent = getIntent();
        String title = intent.getStringExtra("Movie");
        String description = intent.getStringExtra("Plot");
        String image = intent.getStringExtra("Image");
        String release = intent.getStringExtra("Release");
        movieTitle.setText(title);
        moviePlot.setText(description);
        movieRelease.setText(release);

        ViewHolder holder = new ViewHolder();
        holder.imageview = moviePoster;
        holder.imageview.setTag(holder);

        holder.imageview.setImageResource(R.drawable.ic_pop_action);
        new DownloadImageTask(holder.imageview).execute(image);


    }





    static class ViewHolder {
        public ImageView imageview;


    }



    private class DownloadImageTask extends AsyncTask<String, Void, Bitmap> {
        ImageView bmImage;

        public DownloadImageTask(ImageView bmImage) {
            this.bmImage = bmImage;
        }

        protected Bitmap doInBackground(String... urls) {
            String urldisplay = "https://image.tmdb.org/t/p/w60_and_h90_bestv2" + urls[0];
            Bitmap mIcon = null;
            try {
                InputStream in = new java.net.URL(urldisplay).openStream();
                mIcon = BitmapFactory.decodeStream(in);
            } catch (Exception e) {
                Log.e("Error", e.getMessage());
                e.printStackTrace();
            }
            return mIcon;
        }

        protected void onPostExecute(Bitmap result) {
            bmImage.setImageBitmap(result);
        }


    }

}
