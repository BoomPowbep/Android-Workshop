package com.mickaeldebalme.android.newsletter.activities;

import android.os.Bundle;
import android.view.Window;

import com.mickaeldebalme.android.newsletter.databases.DatabaseHelper;
import com.mickaeldebalme.android.newsletter.fragments.ArticlesListFragment;
import com.mickaeldebalme.android.newsletter.network.NetworkHelper;
import com.mickaeldebalme.android.newsletter.R;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

/**
 * Classe MainActivity
 * @author mickaeldebalme
 */
public class MainActivity extends AppCompatActivity {

    /**
     * A la création de l'activité
     * @param savedInstanceState Bundle
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        DatabaseHelper.init(this);
        NetworkHelper.init(this);

        this.displayArticlesList();
    }

    /**
     * Lance l'affichage de la liste des articles
     */
    private void displayArticlesList() {
        ArticlesListFragment fragment = new ArticlesListFragment();

        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();

        transaction.replace(R.id.fragment_container, fragment);
        transaction.addToBackStack(null);

        transaction.commit();
    }
}
