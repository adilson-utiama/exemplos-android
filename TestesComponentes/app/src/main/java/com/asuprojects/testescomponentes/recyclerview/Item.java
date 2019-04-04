package com.asuprojects.testescomponentes.recyclerview;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.PrimaryKey;

import java.io.Serializable;

@Entity(tableName = "itens")
public class Item implements Serializable {

    @PrimaryKey(autoGenerate = true)
    private long id;

    private String nome;
    private boolean checado;

    @ForeignKey(
            entity = ListaItens.class,
            parentColumns = "id",
            childColumns = "listaId",
            onDelete = ForeignKey.NO_ACTION
    )
    private long listaId;

    public Item() {
        checado = false;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public boolean isChecado() {
        return checado;
    }

    public void setChecado(boolean checado) {
        this.checado = checado;
    }

    public void setListaId(long listaId) {
        this.listaId = listaId;
    }

    public long getListaId() {
        return listaId;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("ID: ").append(getId()).append(" : ")
                .append("NOME: ").append(getNome());
        return builder.toString();
    }
}
