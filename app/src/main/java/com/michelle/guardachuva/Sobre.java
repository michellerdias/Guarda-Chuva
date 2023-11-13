package com.michelle.guardachuva;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class Sobre extends AppCompatActivity {

    private CardView desenvolvedora;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sobre);

        desenvolvedora = findViewById(R.id.desenvolvedora);

        desenvolvedora.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Sobre.this, Michelle.class);
                startActivity(i);
            }
        });
    }
}