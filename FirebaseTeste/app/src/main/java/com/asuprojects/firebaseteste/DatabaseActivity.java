package com.asuprojects.firebaseteste;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.*;
import com.asuprojects.firebaseteste.model.Produto;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.*;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;

public class DatabaseActivity extends AppCompatActivity {

    private DatabaseReference reference =
            FirebaseDatabase.getInstance().getReference();
    private DatabaseReference produtos;
    private FirebaseAuth auth = FirebaseAuth.getInstance();

    private TextView textProdutos;

    private EditText inputProdutoNome;
    private EditText inputProdutoDescricao;
    private EditText inputProdutoPreco;
    private Button btnAdicionar;
    private TextView logado;

    private EditText inputPesquisa;
    private ImageButton btnPesquisa;

    private EditText inputPrecoMinimo;
    private EditText inputPrecoMaximo;
    private ImageButton btnPesquisaPreco;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_database);

        if(getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        inputProdutoNome = findViewById(R.id.editProdutoNome);
        inputProdutoDescricao = findViewById(R.id.editProdutoDescricao);
        inputProdutoPreco = findViewById(R.id.editProdutoPreco);
        btnAdicionar = findViewById(R.id.btnAdicionar);

        inputPesquisa = findViewById(R.id.editPesquisaNome);
        btnPesquisa = findViewById(R.id.imageButtonPesquisa);

        inputPrecoMinimo = findViewById(R.id.editPrecoInicial);
        inputPrecoMaximo = findViewById(R.id.editPrecoFinal);
        btnPesquisaPreco = findViewById(R.id.imageButtonPesquisaPreco);

        textProdutos = findViewById(R.id.text_produtos);
        logado = findViewById(R.id.textLogado);

        produtos = reference.child("produtos");

        if(auth.getCurrentUser() != null) {
            logado.setText("Logado");
        } else {
            logado.setText("Nenhum usuario logado");
        }

        btnPesquisaPreco.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pesquisarProdutoPeloPreco();
            }
        });

        btnPesquisa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pesquisarProdutoPeloNome();
            }
        });

        produtos.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                GenericTypeIndicator<HashMap<String, Produto>> type = new GenericTypeIndicator<HashMap<String, Produto>>() {};

                if(dataSnapshot.getValue() != null) {
                    HashMap<String, Produto> value = dataSnapshot.getValue(type);
                    Collection<Produto> list = value.values();
                    textProdutos.setText(list.toString());

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        btnAdicionar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                adicionarProduto();
            }
        });
    }

    private void pesquisarProdutoPeloPreco() {
        String minimo = inputPrecoMinimo.getText().toString().trim();
        String maximo = inputPrecoMaximo.getText().toString().trim();

        if(auth.getCurrentUser() != null) {
            if(verificaValores(minimo, maximo)) {

                double valorMinimo = Double.parseDouble(minimo);
                double valorMaximo = Double.parseDouble(maximo);

                Query preco = produtos.orderByChild("preco").startAt(valorMinimo).endAt(valorMaximo);
                preco.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        GenericTypeIndicator<HashMap<String, Produto>> type = new GenericTypeIndicator<HashMap<String, Produto>>() {};
                        if(dataSnapshot.getValue() != null) {
                            HashMap<String, Produto> value = dataSnapshot.getValue(type);
                            Collection<Produto> list = value.values();
                            textProdutos.setText(list.toString());
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
            } else {
                Toast.makeText(this, "Preencha os valores corretamente", Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(this, "Necessario estar logado", Toast.LENGTH_SHORT).show();
        }

    }

    private boolean verificaValores(String minimo, String maximo) {
        boolean vazio = false;
        if(minimo.isEmpty()) {
            vazio =  false;
        } else if(maximo.isEmpty()) {
            vazio = false;
        } else {
            vazio = true;
        }
        return vazio;
    }

    private void adicionarProduto() {
        String nome = inputProdutoNome.getText().toString().trim();
        String descricao = inputProdutoDescricao.getText().toString().trim();
        String preco = inputProdutoPreco.getText().toString().trim();

        if(auth.getCurrentUser() != null ){
            if(!nome.isEmpty() || !descricao.isEmpty() || !preco.isEmpty()) {
                double precoDouble = Double.parseDouble(preco);
                Produto produto = new Produto(nome, descricao, precoDouble);
                produtos.push().setValue(produto);

                limparCampos();
            } else {
                Toast.makeText(DatabaseActivity.this, "Preencha todos os campos", Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(DatabaseActivity.this, "Necessario estar logado para adicionar produtos", Toast.LENGTH_SHORT).show();
        }
    }

    private void pesquisarProdutoPeloNome() {
        String pesquisaNome = inputPesquisa.getText().toString().trim();
        if(!pesquisaNome.isEmpty()) {
            Query pesquisaPorNome = produtos.orderByChild("nome").equalTo(pesquisaNome);
            pesquisaPorNome.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    if(dataSnapshot.exists()) {
                        textProdutos.setText(dataSnapshot.getValue().toString());
                    } else {
                        textProdutos.setText("Nenhum produto encontrado");
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });
        }
    }

    private void limparCampos() {
        inputProdutoNome.setText("");
        inputProdutoDescricao.setText("");
        inputProdutoPreco.setText("");
        inputProdutoNome.setFocusable(true);
    }
}
