package com.michelle.guardachuva;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;

import java.util.ArrayList;

public class MicrobaciasCadastradas extends AppCompatActivity {

    RecyclerView recyclerView;
    ArrayList<Microbacia> microbaciaArrayList;
    MyAdapter myAdapter;
    FirebaseFirestore db;
    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_microbacias_cadastradas);

        progressDialog = new ProgressDialog(this);
        progressDialog.setCancelable(false);
        progressDialog.setMessage("Recuperando Dados...");
        progressDialog.show();

        recyclerView = findViewById(R.id.recyclerview);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        db = FirebaseFirestore.getInstance();
        microbaciaArrayList = new ArrayList<>();
        myAdapter = new MyAdapter(MicrobaciasCadastradas.this, microbaciaArrayList);

        recyclerView.setAdapter(myAdapter);

        EventChangeListener();

    }

    private void EventChangeListener() {

        db.collection("microbacias").orderBy("nomeProprietario", Query.Direction.ASCENDING)
                .addSnapshotListener((value, error) -> {

                    if (error != null){

                        if(progressDialog.isShowing())
                            progressDialog.dismiss();
                        Log.e("Firestore error", error.getMessage());
                        return;
                    }

                    for(DocumentChange dc : value.getDocumentChanges()){

                        if(dc.getType() == DocumentChange.Type.ADDED){

                            microbaciaArrayList.add(dc.getDocument().toObject(Microbacia.class));
                        }

                        myAdapter.notifyDataSetChanged();

                        if(progressDialog.isShowing())
                            progressDialog.dismiss();
                    }
                });
    }
}