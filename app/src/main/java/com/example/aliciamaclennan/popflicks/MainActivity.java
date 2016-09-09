package com.example.aliciamaclennan.popflicks;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (savedInstanceState == null) {
            Intent gridIntent = new Intent(this, MovieActivity.class);
            startActivity(gridIntent);
            finish();
        }
    }
}
