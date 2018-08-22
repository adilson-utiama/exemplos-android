package com.asuprojects.exemploandroidroom.dao;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.asuprojects.exemploandroidroom.models.Tarefa;

import java.util.List;

@Dao
public interface TarefaDao {

    @Insert
    void adiciona(Tarefa tarefa);

    @Update
    void atualiza(Tarefa tarefa);

    @Delete
    void remove(Tarefa tarefa);

    @Query("DELETE FROM tabela_tarefa")
    void deleteAll();

    @Query("SELECT * FROM tabela_tarefa ORDER BY prioridade ASC")
    LiveData<List<Tarefa>> listarTodos();
}
