package com.asuprojects.exemplonavigationdrawer;

import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.MenuItem;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private Toolbar toolbar;
    private DrawerLayout drawerLayout;
    private NavigationView navigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        drawerLayout = findViewById(R.id.drawerLayout);

        ActionBarDrawerToggle toggle =
                new ActionBarDrawerToggle(this,
                        drawerLayout, toolbar,
                        R.string.open_drawer, R.string.close_drawer);

        drawerLayout.addDrawerListener(toggle);

        toggle.syncState();

        navigationView = findViewById(R.id.navigationView);
        navigationView.setNavigationItemSelectedListener(this);

    }

    @Override
    public void onBackPressed() {
        //Ao apertar o botao de 'voltar', verifica se o menu de navegacao esta aberto
        //e fecha o menu navegacao para voltar a tela de fundo
        //evitando que saia do aplicativo
        if (drawerLayout.isDrawerOpen(Gravity.START)) {
            drawerLayout.closeDrawer(Gravity.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_inbox: {
                Toast.makeText(this, "Menu Inbox", Toast.LENGTH_SHORT).show();
                break;
            }
            case R.id.menu_starred: {
                Toast.makeText(this, "Menu Favoritos", Toast.LENGTH_SHORT).show();
                break;
            }
            case R.id.menu_sent_email: {
                Toast.makeText(this, "Menu Emails Enviados", Toast.LENGTH_SHORT).show();
                break;
            }
            case R.id.menu_trash: {
                Toast.makeText(this, "Menu Lixeira", Toast.LENGTH_SHORT).show();
                break;
            }
            case R.id.menu_spam: {
                Toast.makeText(this, "Menu Spam", Toast.LENGTH_SHORT).show();
                break;
            }
        }

        drawerLayout.closeDrawer(GravityCompat.START);

        return true;
    }
}
