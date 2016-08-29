package com.example.aliciamaclennan.popflicks;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.InputStream;
import java.util.List;

/**
 * Created by amac on 8/27/16.
 */
public class MovieAdapter extends ArrayAdapter<Movie> {

    private Context mContext;

    public MovieAdapter(Context context, List<Movie> movies) {
        super(context, 0, movies);
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View listItemView = convertView;
        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.movie_list_item, parent, false);
        }


        Movie currentMovie = getItem(position);

        // Find the TextView with view ID title
        TextView titleView = (TextView) listItemView.findViewById(R.id.title);
        String title = currentMovie.getTitle();
        titleView.setText(title);


//        // Find the ImageView with view ID title
//        ImageView posterView = (ImageView) listItemView.findViewById(R.id.movie_thumbnail);
//        String image = currentMovie.getImage();
//        posterView.set
//        currentMovie.setImage();


        ViewHolder holder = new ViewHolder();
        holder.imageview = (ImageView) listItemView.findViewById(R.id.movie_thumbnail);

        listItemView.setTag(holder);

        holder.imageview.setImageResource(R.drawable.ic_pop_action);
        new DownloadImageTask(holder.imageview).execute(currentMovie.getImage());

    // Return the list item view that is now showing the appropriate data
    return listItemView;

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
