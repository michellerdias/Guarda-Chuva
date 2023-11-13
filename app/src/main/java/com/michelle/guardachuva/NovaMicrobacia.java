package com.michelle.guardachuva;

import static androidx.constraintlayout.motion.widget.Debug.getLocation;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Handler;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import java.util.HashMap;
import java.util.Map;
import android.widget.EditText;
import android.widget.TextView;


public class NovaMicrobacia extends AppCompatActivity {

    private EditText editNomeProprietario, editCpf, editCep, editRua, editNumero, editAltura, editAngulo, editLargura, editLatitude, editLongitude;
    private Button btCalcular, btSalvar, btLocalizacao;
    private static final int REQUEST_LOCATION_PERMISSION = 1;
    private Geolocalizacao geolocalizacao;
    private TextView resultado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nova_microbacia);

        editNomeProprietario = findViewById(R.id.editNomeProprietario);
        editCpf = findViewById(R.id.editCpf);
        editCep = findViewById(R.id.editCep);
        editRua = findViewById(R.id.editRua);
        editNumero = findViewById(R.id.editNumero);
        btCalcular = findViewById(R.id.btCalcular);
        editAltura = findViewById(R.id.editAltura);
        editLargura = findViewById(R.id.editLargura);
        editAngulo = findViewById(R.id.editAngulo);
        editLatitude = findViewById(R.id.editLatitude);
        editLongitude = findViewById(R.id.editLongitude);
        resultado = findViewById(R.id.volumeMicrobacia);
        btSalvar = findViewById(R.id.btSalvar);
        btLocalizacao = findViewById(R.id.btLocalizacao);
        geolocalizacao = new Geolocalizacao(this);

        btCalcular.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    // Obtendo os valores dos EditText
                    double altura = Double.parseDouble(editAltura.getText().toString());
                    double angulo = Double.parseDouble(editAngulo.getText().toString());
                    double largura = Double.parseDouble(editLargura.getText().toString());

                    // Instanciando a classe Calculo
                    Calculo calc = new Calculo(angulo, altura, largura);

                    // Formatando o resultado para duas casas decimais
                    String resultadoFormatado = String.format("%.2f", calc.getVolume());

                    // Exibindo o resultado no TextView
                    resultado.setText(resultadoFormatado + " m³");
                } catch (NumberFormatException e) {
                    // Trate exceções aqui, por exemplo, campos vazios ou caracteres inválidos
                    Snackbar.make(view, "Por favor, forneça valores válidos.", Snackbar.LENGTH_SHORT).show();
                }
            }
        });

        btSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nomeProprietario = editNomeProprietario.getText().toString();
                String cpf = editCpf.getText().toString();
                String cep = editCep.getText().toString();
                String rua = editRua.getText().toString();
                String numero = editNumero.getText().toString();
                String altura = editAltura.getText().toString();
                String largura = editLargura.getText().toString();
                String angulo = editAngulo.getText().toString();
                String resultadoText = resultado.getText().toString();

                // Verifique se os campos não estão vazios
                if (!nomeProprietario.isEmpty() && !cpf.isEmpty() && !cep.isEmpty() &&
                        !rua.isEmpty() && !numero.isEmpty() && !altura.isEmpty() && !largura.isEmpty() &&
                        !angulo.isEmpty() &&  !resultadoText.isEmpty()) {
                    FirebaseFirestore db = FirebaseFirestore.getInstance();
                    DocumentReference microbaciaRef = db.collection("microbacias").document();

                    // Crie um mapa de dados
                    Map<String, Object> microbacia = new HashMap<>();
                    microbacia.put("nomeProprietario", nomeProprietario);
                    microbacia.put("cpf", cpf);
                    microbacia.put("cep", cep);
                    microbacia.put("rua", rua);
                    microbacia.put("numero", numero);
                    microbacia.put("altura", altura);
                    microbacia.put("largura", largura);
                    microbacia.put("angulo", angulo);
                    microbacia.put("volume", resultadoText);

                    // Adicione os dados ao Firestore
                    microbaciaRef.set(microbacia)
                            .addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void aVoid) {
                                    Snackbar snackbar = Snackbar.make(view, "Microbacia cadastrada!", Snackbar.LENGTH_LONG);
                                    View snackbarView = snackbar.getView();
                                    snackbarView.setBackgroundColor(getResources().getColor(R.color.azulclaro));
                                    snackbar.show();

                                    // Adicione um atraso de 2 segundos antes de iniciar a MenuActivity
                                    new Handler().postDelayed(new Runnable() {
                                        @Override
                                        public void run() {
                                            // Retorne para a MenuActivity após o sucesso
                                            Intent intent = new Intent(NovaMicrobacia.this, MainActivity.class);
                                            startActivity(intent);
                                            finish(); // Impede que o usuário volte para esta Activity pressionando o botão de voltar.
                                        }
                                    }, 2000); // 2000 milissegundos = 2 segundos
                                }
                            })
                            .addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    Snackbar snackbar = Snackbar.make(view, "Tente novamente.", Snackbar.LENGTH_LONG);
                                    View snackbarView = snackbar.getView();
                                    snackbarView.setBackgroundColor(getResources().getColor(R.color.vermelho));
                                    snackbar.show();
                                }
                            });
                }
            }
        });

        btLocalizacao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (checkLocationPermission()) {
                    getLocation();
                } else {
                    solicitarPermissoes();
                }
            }
        });
    }

    private boolean checkLocationPermission() {
        return ContextCompat.checkSelfPermission(
                this, android.Manifest.permission.ACCESS_FINE_LOCATION) ==
                PackageManager.PERMISSION_GRANTED;
    }

    private void solicitarPermissoes() {
        ActivityCompat.requestPermissions(this,
                new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION},
                REQUEST_LOCATION_PERMISSION);
    }

    private void getLocation() {
        Location location = geolocalizacao.getLocation();

        if (location != null) {
            double latitude = location.getLatitude();
            double longitude = location.getLongitude();

            editLatitude.setText(String.valueOf(latitude));
            editLongitude.setText(String.valueOf(longitude));
        } else {
            // Caso a localização seja nula
            View rootView = findViewById(android.R.id.content);
            Snackbar snackbar = Snackbar.make(rootView, "Permissão negada pelo usuário. Por favor, conceda acesso à sua localização para prosseguir.", Snackbar.LENGTH_LONG);
            View snackbarView = snackbar.getView();
            snackbarView.setBackgroundColor(getResources().getColor(R.color.vermelho));
            snackbar.show();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == REQUEST_LOCATION_PERMISSION) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                // Permissão concedida após solicitação
                getLocation();
            } else {
                // Permissão negada pelo usuário
                View rootView = findViewById(android.R.id.content);
                Snackbar snackbar = Snackbar.make(rootView, "Permissão negada pelo usuário. Por favor, conceda ao aplicativo o acesso à sua localização para prosseguir.", Snackbar.LENGTH_LONG);
                View snackbarView = snackbar.getView();
                snackbarView.setBackgroundColor(getResources().getColor(R.color.vermelho));
                snackbar.show();
            }
        }
    }
}
