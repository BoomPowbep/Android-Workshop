package com.mickaeldebalme.android.newsletter.Activities;

import android.os.Bundle;
import android.view.Window;

import com.mickaeldebalme.android.newsletter.Fragments.ArticlesListFragment;
import com.mickaeldebalme.android.newsletter.R;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);//will hide the title
        getSupportActionBar().hide(); //hide the title bar
        setContentView(R.layout.activity_main);

        this.displayArticlesList();
    }

    private void displayArticlesList() {
        ArticlesListFragment fragment = new ArticlesListFragment();

        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();

        transaction.replace(R.id.fragment_container, fragment);
        transaction.addToBackStack(null);

        transaction.commit();
    }
}
