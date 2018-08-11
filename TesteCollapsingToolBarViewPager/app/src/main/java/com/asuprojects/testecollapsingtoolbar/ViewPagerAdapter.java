package com.asuprojects.testecollapsingtoolbar;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.util.Log;

import java.util.ArrayList;

public class ViewPagerAdapter extends FragmentStatePagerAdapter {

    private ArrayList<Pessoa> lista;

    public ViewPagerAdapter(FragmentManager fm) {
        super(fm);
        this.lista = new ArrayList<>();
    }

    public void setListaPessoa(ArrayList<Pessoa> lista){
        this.lista = lista;
    }

    @Override
    public Fragment getItem(int position) {
        Log.i("TESTE", "Posicao: " + position);
        return PessoaFragment.newInstance(position, this.lista);
    }

    @Override
    public int getCount() {
        return this.lista.size();
    }
}
