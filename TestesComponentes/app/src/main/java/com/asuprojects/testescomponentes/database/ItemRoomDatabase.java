package com.asuprojects.testescomponentes.database;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

import com.asuprojects.testescomponentes.recyclerview.Item;
import com.asuprojects.testescomponentes.recyclerview.ListaItens;

@Database(entities = { Item.class, ListaItens.class }, version = 1)
public abstract class ItemRoomDatabase extends RoomDatabase {

    private static ItemRoomDatabase INSTANCIA;

    public abstract ItemDAO getItemDAO();
    public abstract ListaDAO getListaDAO();

    public static ItemRoomDatabase getDatabase(final Context context){
        if(INSTANCIA == null){
            synchronized (ItemRoomDatabase.class){
                if(INSTANCIA == null){
                    INSTANCIA = Room.databaseBuilder(context,
                            ItemRoomDatabase.class, "itens_db")
                            .allowMainThreadQueries()
                            .build();
                }
            }
        }
        return INSTANCIA;
    }
}
