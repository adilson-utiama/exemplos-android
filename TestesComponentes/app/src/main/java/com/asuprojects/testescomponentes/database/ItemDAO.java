package com.asuprojects.testescomponentes.database;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import com.asuprojects.testescomponentes.recyclerview.Item;

import java.util.List;

@Dao
public interface ItemDAO {

    @Query("SELECT * FROM itens")
    List<Item> listaAll();

    @Query("SELECT * FROM itens WHERE listaId = :id")
    List<Item> findItensByListaId(long id);

    @Insert
    void adiciona(Item item);

    @Delete
    void remove(Item item);
}
