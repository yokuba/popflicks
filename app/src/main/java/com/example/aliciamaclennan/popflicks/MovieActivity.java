package com.example.aliciamaclennan.popflicks;

import android.app.ListActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;

public class MovieActivity extends ListActivity

    {

        String[] itemname ={
                "Fight Club",
                "The Lobster",
                "Gravity",
                "2001: Space Odyssey",
                "The Apartment",
                "Alien",
                "The King and I",
                "The Bridge"
        };

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main);

            this.setListAdapter(new ArrayAdapter<String>(
                    this, R.layout.movie_list,
                    R.id.empty_view,itemname));
        }
}
