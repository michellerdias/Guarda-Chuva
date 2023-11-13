package com.michelle.guardachuva;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;

public class Michelle extends AppCompatActivity {

    private CardView github, linkedin;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_michelle);

        github = findViewById(R.id.github);
        linkedin = findViewById(R.id.linkedin);

        github.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Abra o github em um navegador da web
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://github.com/michellerdias")));
            }
        });

        linkedin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Abra o linkedin em um navegador da web
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.linkedin.com/in/michellerenatadias/")));
            }
        });
    }
}