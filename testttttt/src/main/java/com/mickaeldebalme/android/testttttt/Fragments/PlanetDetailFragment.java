package com.mickaeldebalme.android.testttttt.Fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.mickaeldebalme.android.testttttt.Models.Planet;
import com.mickaeldebalme.android.testttttt.R;
import com.squareup.picasso.Picasso;

public class PlanetDetailFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.planet_details_fragment, container, false);

        Bundle b = getActivity().getIntent().getExtras();
        if(b != null) {
            Planet planet = b.getParcelable("planet");

            TextView apvTitle = view.findViewById(R.id.apv_title);
            TextView apvDesc = view.findViewById(R.id.apv_desc);
            ImageView apvImage = view.findViewById(R.id.apv_image);

            apvTitle.setText(planet.getNom());
            apvDesc.setText(planet.getDescription());

            Picasso.get()
                    .load(planet.getImage())
                    .fit()
                    .into(apvImage);
        }

        return view;
    }
}
