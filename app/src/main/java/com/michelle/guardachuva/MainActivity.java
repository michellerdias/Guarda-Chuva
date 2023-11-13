package com.michelle.guardachuva;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.annotation.SuppressLint;
import android.util.Log;
import com.michelle.guardachuva.R;

import android.os.Bundle;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private static CardView novaMicrobacia, microbaciasCadastradas, tutorial, sobre;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        novaMicrobacia = findViewById(R.id.novaMicrobacia);
        microbaciasCadastradas = findViewById(R.id.microbaciasCadastradas);
        tutorial = findViewById(R.id.tutorial);
        sobre = findViewById(R.id.sobre);

        novaMicrobacia.setOnClickListener(this);
        microbaciasCadastradas.setOnClickListener(this);
        tutorial.setOnClickListener(this);
        sobre.setOnClickListener(this);
    }


    @Override
    public void onClick(View view) {
        Intent i;
        if (view.getId() == R.id.novaMicrobacia){
            i = new Intent(this, NovaMicrobacia.class);
            startActivity(i);
        }
        if (view.getId() == R.id.microbaciasCadastradas){
            i = new Intent(this, MicrobaciasCadastradas.class);
            startActivity(i);
        }
        if (view.getId() == R.id.tutorial){
            i = new Intent(this, Tutorial.class);
            startActivity(i);
        }
        if (view.getId() == R.id.sobre){
            i = new Intent(this, Sobre.class);
            startActivity(i);
        }
    }
}