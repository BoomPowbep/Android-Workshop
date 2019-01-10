package com.mickaeldebalme.android.testttttt.Fragments;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mickaeldebalme.android.testttttt.Adapters.PlanetAdapter;
import com.mickaeldebalme.android.testttttt.Models.Planet;
import com.mickaeldebalme.android.testttttt.R;
import com.mickaeldebalme.android.testttttt.activities.PlanetViewActivity;
import com.mickaeldebalme.android.testttttt.listeners.PlanetListener;

import java.util.ArrayList;
import java.util.List;

public class PlanetListFragment extends Fragment implements PlanetListener {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.planet_list_fragment, container, false);

        List<Planet> planetes = new ArrayList<>();
        planetes.add(new Planet("https://upload.wikimedia.org/wikipedia/commons/thumb/d/da/Internet2.jpg/1200px-Internet2.jpg", "Terre", "La terre youpi"));
        planetes.add(new Planet("https://upload.wikimedia.org/wikipedia/commons/thumb/d/da/Internet2.jpg/1200px-Internet2.jpg", "Mars", "Mars 123"));
        planetes.add(new Planet("https://upload.wikimedia.org/wikipedia/commons/thumb/d/da/Internet2.jpg/1200px-Internet2.jpg", "Venus", "Venus trop bien"));
        planetes.add(new Planet("https://upload.wikimedia.org/wikipedia/commons/thumb/d/da/Internet2.jpg/1200px-Internet2.jpg", "Terre", "La terre youpi"));
        planetes.add(new Planet("https://upload.wikimedia.org/wikipedia/commons/thumb/d/da/Internet2.jpg/1200px-Internet2.jpg", "Mars", "Mars 123"));
        planetes.add(new Planet("https://upload.wikimedia.org/wikipedia/commons/thumb/d/da/Internet2.jpg/1200px-Internet2.jpg", "Venus", "Venus trop bien"));
        planetes.add(new Planet("https://upload.wikimedia.org/wikipedia/commons/thumb/d/da/Internet2.jpg/1200px-Internet2.jpg", "Terre", "La terre youpi"));
        planetes.add(new Planet("https://upload.wikimedia.org/wikipedia/commons/thumb/d/da/Internet2.jpg/1200px-Internet2.jpg", "Mars", "Mars 123"));
        planetes.add(new Planet("https://upload.wikimedia.org/wikipedia/commons/thumb/d/da/Internet2.jpg/1200px-Internet2.jpg", "Venus", "Venus trop bien"));

        RecyclerView recyclerView = view.findViewById(R.id.list_planets);
        PlanetAdapter adapter = new PlanetAdapter(planetes, this);
        //RecyclerView.LayoutManager manager = new GridLayoutManager(this, 3);
        RecyclerView.LayoutManager manager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(manager);
        recyclerView.setAdapter(adapter);

        return view;
    }

    /**
     * Change de fragment vers la planète sélectionnée
     *
     * @param planet La planète sur laquelle on doit ouvrir l'activité
     */
    @Override
    public void onSelect(Planet planet) {
        PlanetDetailFragment fragment = new PlanetDetailFragment();

        FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();

        transaction.replace(R.id.fragment_container, fragment);
        transaction.addToBackStack(null);

        transaction.commit();
    }

    /**
     * Share une planète
     *
     * @param planet Planète à partager
     */
    @Override
    public void onShare(Planet planet) {
        Intent sendIntent = new Intent();
        sendIntent.setAction(Intent.ACTION_SEND);
        sendIntent.putExtra(Intent.EXTRA_TEXT, "La planète " + planet.getNom() + " est géniale.");
        sendIntent.setType("text/plain");
        startActivity(sendIntent);
    }
}
