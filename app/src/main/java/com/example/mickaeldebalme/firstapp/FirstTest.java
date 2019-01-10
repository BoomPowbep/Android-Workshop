package com.example.mickaeldebalme.firstapp;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class FirstTest extends AppCompatActivity {

    int index = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        System.out.println("onCreate > " + index++);
        setContentView(R.layout.activity_first_test);
    }

    @Override
    protected void onStart() {
        super.onStart();
        System.out.println("onStart > " + index++);
    }

    @Override
    protected void onResume() {
        super.onResume();
        System.out.println("onResume > " + index++);
    }

    @Override
    protected void onPause() {
        super.onPause();
        System.out.println("onPause > " + index++);
    }

    @Override
    protected void onStop() {
        super.onStop();
        System.out.println("onStop > " + index++);
    }

    // Destruction de l'app en mémoire ram : sauvegarder les données
    @Override
    protected void onDestroy() {
        super.onDestroy();
        System.out.println("onDestroy > " + index++);
    }
}
