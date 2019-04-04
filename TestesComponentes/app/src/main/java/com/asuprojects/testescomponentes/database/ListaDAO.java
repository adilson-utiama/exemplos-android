package com.asuprojects.testescomponentes.database;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import com.asuprojects.testescomponentes.recyclerview.ListaItens;

import java.util.List;

@Dao
public interface ListaDAO {

    @Insert
    long salvaListaItens(ListaItens itens);

    @Query("SELECT * FROM lista_item")
    List<ListaItens> getAllLists();

    @Query("DELETE FROM lista_item")
    void deleteAll();
}
