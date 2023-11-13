package com.michelle.guardachuva;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Login extends AppCompatActivity {
    private FirebaseAuth mAuth;
    private EditText email, senha, email_registro, senha_registro, nome_registro;
    private Button btLogin, btRegistar;
    private TextView txtRegistrar;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        email = findViewById(R.id.email);
        senha = findViewById(R.id.senha);
        btLogin = findViewById(R.id.btLogin);
        btRegistar = findViewById(R.id.btRegistrar);
        email_registro = findViewById(R.id.email_registro);
        senha_registro = findViewById(R.id.senha_registro);
        txtRegistrar = findViewById(R.id.registration);

        // Inicialize o Firebase Authentication
        mAuth = FirebaseAuth.getInstance();

        // Botão CriarConta para mostrar a página de cadastro
        txtRegistrar.setOnClickListener(view -> ClickTxtRegistrar());

        // Botão Login
        btLogin.setOnClickListener(view -> {
            if (email.getText().toString().trim().isEmpty() || senha.getText().toString().trim().isEmpty()) {
                Snackbar snackbar = Snackbar.make(view, "Por favor, preencha os campos com seu e-mail e senha", Snackbar.LENGTH_LONG);
                View snackbarView = snackbar.getView();
                snackbarView.setBackgroundColor(getResources().getColor(R.color.vermelho));
                snackbar.show();
            } else {
                // Obtenha o e-mail e a senha inseridos pelos usuários
                String userEmail = email.getText().toString().trim();
                String userSenha = senha.getText().toString().trim();

                // Faça login com o Firebase Authentication
                mAuth.signInWithEmailAndPassword(userEmail, userSenha)
                        .addOnCompleteListener(Login.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    // O login foi bem-sucedido
                                    FirebaseUser user = mAuth.getCurrentUser();
                                    // Redireciona o usuário para a Main Activity
                                    Intent intent = new Intent(Login.this, MainActivity.class);
                                    startActivity(intent);
                                } else {
                                    // O login falhou
                                    Snackbar snackbar = Snackbar.make(view, "Falha no login. Verifique o e-mail e a senha.", Snackbar.LENGTH_LONG);
                                    View snackbarView = snackbar.getView();
                                    snackbarView.setBackgroundColor(getResources().getColor(R.color.vermelho));
                                    snackbar.show();
                                }
                            }
                        });
            }
        });
    }

    // Método para abrir a página de Cadastro e checar o registro
    private void ClickTxtRegistrar() {
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this);
        LayoutInflater inflater = getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.registration, null);
        dialogBuilder.setView(dialogView);

        email_registro = dialogView.findViewById(R.id.email_registro);
        senha_registro = dialogView.findViewById(R.id.senha_registro);
        nome_registro = dialogView.findViewById(R.id.nome_registro);
        btRegistar = dialogView.findViewById(R.id.btRegistrar);

        // Crie o AlertDialog
        final AlertDialog dialog = dialogBuilder.create();

        btRegistar.setOnClickListener(view -> {
            String nome = nome_registro.getText().toString().trim();
            String email = email_registro.getText().toString().trim();
            String senha = senha_registro.getText().toString().trim();

            if (nome.isEmpty() || email.isEmpty() || senha.isEmpty()) {
                Snackbar snackbar = Snackbar.make(view, "Por favor, preencha todos os campos.", Snackbar.LENGTH_LONG);
                View snackbarView = snackbar.getView();
                snackbarView.setBackgroundColor(getResources().getColor(R.color.vermelho));
                snackbar.show();
            }

            // Registro com Firebase Authentication
            mAuth.createUserWithEmailAndPassword(email, senha)
                    .addOnCompleteListener(Login.this, task -> {
                        if (task.isSuccessful()) {
                            // Registro bem-sucedido
                            FirebaseUser user = mAuth.getCurrentUser();

                            // Redireciona o usuário para a Main Activity
                            Intent intent = new Intent(Login.this, MainActivity.class);
                            startActivity(intent);

                            // Fecha o diálogo de registro
                            dialog.dismiss();
                        } else {
                            // Registro falhou
                            Snackbar snackbar = Snackbar.make(view, "O Cadastro falhou. Tente novamente.", Snackbar.LENGTH_LONG);
                            View snackbarView = snackbar.getView();
                            snackbarView.setBackgroundColor(getResources().getColor(R.color.vermelho));
                            snackbar.show();
                        }
                    });
        });

        // Exiba o diálogo de registro
        dialog.show();
    }
}