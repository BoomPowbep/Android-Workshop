package com.example.mickaeldebalme.firstapp;

import android.content.Intent;
import android.net.Uri;
import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class SecondActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        System.out.println("SecondActivity onCreate");

        // Récupération du click sur le bouton
        findViewById(R.id.button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // Appel
                //Uri numero = Uri.parse("tel:0621151356");
                //Intent appeler = new Intent(Intent.ACTION_CALL, numero);
                //startActivity(appeler);

                // Ouverture d'URL
                Uri chemin = Uri.parse("http://www.google.fr");
                Intent naviguer = new Intent(Intent.ACTION_VIEW, chemin);
                startActivity(naviguer);

                // Changement d'activité
                //Intent intent = new Intent(SecondActivity.this, FirstTest.class);
                //startActivity(intent);
            }
        });
    }
}
