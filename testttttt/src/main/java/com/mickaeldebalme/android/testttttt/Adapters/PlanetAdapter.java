package com.mickaeldebalme.android.testttttt.Adapters;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.mickaeldebalme.android.testttttt.Models.Planet;
import com.mickaeldebalme.android.testttttt.R;
import com.mickaeldebalme.android.testttttt.activities.MainActivity;
import com.mickaeldebalme.android.testttttt.activities.PlanetViewActivity;
import com.mickaeldebalme.android.testttttt.listeners.PlanetListener;
import com.squareup.picasso.Picasso;

import java.util.List;

public class PlanetAdapter extends RecyclerView.Adapter<PlanetAdapter.MyViewHolder> {

    private List<Planet> planetes;
    private PlanetListener listener;

    public PlanetAdapter(List<Planet> planetes, PlanetListener listener) {
        this.planetes = planetes;
        this.listener = listener;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());

        View view = inflater.inflate(R.layout.planet, viewGroup, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int i) {
        myViewHolder.bindItem(planetes.get(i));
    }

    @Override
    public int getItemCount() {
        return planetes.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        TextView mTitle, mDescription;
        ImageView mImage;
        ImageView mShare;
        View view;

        MyViewHolder(View itemView) {
            super(itemView);
            this.view = itemView;
            mTitle = itemView.findViewById(R.id.planet_title);
            mDescription = itemView.findViewById(R.id.planet_desc);
            mImage = itemView.findViewById(R.id.planet_image);
            mShare = itemView.findViewById(R.id.share);
        }

        private void bindItem(final Planet planet) {

            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onSelect(planet);
                }
            });

            mShare.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onShare(planet);
                }
            });

            mTitle.setText(planet.getNom());
            mDescription.setText(planet.getDescription());
            Picasso.get()
                    .load(planet.getImage())
                    .fit()
                    .into(mImage);
        }
    }
}
