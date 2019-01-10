package com.mickaeldebalme.android.testttttt.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.mickaeldebalme.android.testttttt.Models.Planet;
import com.mickaeldebalme.android.testttttt.R;
import com.squareup.picasso.Picasso;

public class PlanetViewActivity extends AppCompatActivity {

    private Planet mPlanet;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_planet_view);

    }


}
