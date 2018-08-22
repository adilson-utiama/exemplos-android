package com.asuprojects.exemploandroidroom.models;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

import com.asuprojects.exemploandroidroom.models.enums.Prioridade;
import com.asuprojects.exemploandroidroom.models.enums.TarefaStatus;

import java.io.Serializable;
import java.util.Date;

@Entity(tableName = "tabela_tarefa")
public class Tarefa implements Serializable{

    @PrimaryKey(autoGenerate = true)
    @NonNull
    private long id;

    @NonNull
    private String titulo;
    @NonNull
    private String descricao;
    @NonNull
    private Date dataIncluida;
    @NonNull
    private TarefaStatus status;
    @NonNull
    private Prioridade prioridade;

    @NonNull
    public long getId() {
        return id;
    }

    public void setId(@NonNull long id) {
        this.id = id;
    }

    @NonNull
    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(@NonNull String titulo) {
        this.titulo = titulo;
    }

    @NonNull
    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(@NonNull String descricao) {
        this.descricao = descricao;
    }

    @NonNull
    public Date getDataIncluida() {
        return dataIncluida;
    }

    public void setDataIncluida(@NonNull Date dataIncluida) {
        this.dataIncluida = dataIncluida;
    }

    @NonNull
    public TarefaStatus getStatus() {
        return status;
    }

    public void setStatus(@NonNull TarefaStatus status) {
        this.status = status;
    }

    @NonNull
    public Prioridade getPrioridade() {
        return prioridade;
    }

    public void setPrioridade(@NonNull Prioridade prioridade) {
        this.prioridade = prioridade;
    }
}
