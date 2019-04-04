package com.asuprojects.firebaseteste;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class AutenticacaoActivity extends AppCompatActivity {

    private FirebaseAuth auth = FirebaseAuth.getInstance();

    private EditText usuarioEmail;
    private EditText usuarioSenha;
    private TextView users;
    private Button btnCadastro;

    private EditText loginUsuarioEmail;
    private EditText loginUsuarioSenha;
    private Button btnLogin;
    private Button btnLogout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_autenticacao);

        if(getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        usuarioEmail = findViewById(R.id.usuario_email);
        usuarioSenha = findViewById(R.id.usuario_senha);
        btnCadastro = findViewById(R.id.btnCadastro);
        users = findViewById(R.id.textListaUsuariosCadastrados);

        loginUsuarioEmail = findViewById(R.id.loginUsuarioEmail);
        loginUsuarioSenha = findViewById(R.id.loginUsuarioSenha);
        btnLogin = findViewById(R.id.btnLogin);
        btnLogout = findViewById(R.id.btnLogout);

        //Verrifica usuario logado
        if(auth.getCurrentUser() != null) {
            FirebaseUser u = auth.getCurrentUser();
            String uUid = u.getUid();
            String email = u.getEmail();
            users.setText(email + "\n" + uUid);
        } else {
            users.setText("Nenhum usuario logado");
        }

        btnCadastro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = usuarioEmail.getText().toString();
                String senha = usuarioSenha.getText().toString();

                if(auth.getCurrentUser() != null) {
                    if (!email.isEmpty() || !senha.isEmpty()) {
                        /* Salva Usuario e autentica no Firebase , permanece logado */
                        auth.createUserWithEmailAndPassword(email, senha)
                                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                    @Override
                                    public void onComplete(@NonNull Task<AuthResult> task) {
                                        if(task.isSuccessful()) {
                                            Toast.makeText(AutenticacaoActivity.this, "Usuario Cadastrado com Sucesso", Toast.LENGTH_SHORT).show();
                                        } else {
                                            Toast.makeText(AutenticacaoActivity.this, "Falha no cadastro", Toast.LENGTH_SHORT).show();
                                        }
                                    }
                                });
                    } else {
                        Toast.makeText(AutenticacaoActivity.this, "Informe email e senha...", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(AutenticacaoActivity.this, "Necessario estar logado para cadastrar usuario", Toast.LENGTH_SHORT).show();
                }



            }
        });

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String usuarioEmail = loginUsuarioEmail.getText().toString().trim();
                String usuarioSenha = loginUsuarioSenha.getText().toString().trim();
                if(!usuarioEmail.isEmpty() || !usuarioSenha.isEmpty()) {
                    /* Faz a autenticacao no Firebase */
                    auth.signInWithEmailAndPassword(usuarioEmail, usuarioSenha)
                            .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if(task.isSuccessful()) {
                                        Toast.makeText(AutenticacaoActivity.this, "Logado com sucesso", Toast.LENGTH_SHORT).show();
                                        AuthResult result = task.getResult();
                                        FirebaseUser user = result.getUser();
                                        preencheUsuarioLogado(user);
                                    } else {
                                        Toast.makeText(AutenticacaoActivity.this, "Usuario inexistente ou dados invalidos", Toast.LENGTH_SHORT).show();
                                    }

                                }
                            });
                }
            }
        });

        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /* Faz o Logout */
                auth.signOut();
                users.setText("Nenhum usuario logado");
            }
        });

    }

    private void preencheUsuarioLogado(FirebaseUser user) {
        users.setText(user.getEmail() + "\n" + user.getUid());
    }

}
