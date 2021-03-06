package com.asuprojects.exemploandroidroom.repository;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;

import com.asuprojects.exemploandroidroom.dao.TarefaDao;
import com.asuprojects.exemploandroidroom.database.TarefaRoomDatabase;
import com.asuprojects.exemploandroidroom.models.Tarefa;

import java.util.List;

public class TarefaRepository {

    private TarefaDao tarefaDao;
    private LiveData<List<Tarefa>> tarefas;

    public TarefaRepository(Application application){
        TarefaRoomDatabase db = TarefaRoomDatabase.getDatabase(application);
        tarefaDao = db.getTarefaDao();
        tarefas = tarefaDao.listarTodos();
    }

    public LiveData<List<Tarefa>> getTodasTarefas() {
        return tarefas;
    }


    public void adiciona (Tarefa tarefa) {
        new insertAsyncTask(tarefaDao).execute(tarefa);
    }

    private static class insertAsyncTask extends AsyncTask<Tarefa, Void, Void> {
        private TarefaDao asyncTaskDao;
        insertAsyncTask(TarefaDao dao) {
            asyncTaskDao = dao;
        }
        @Override
        protected Void doInBackground(final Tarefa... params) {
            asyncTaskDao.adiciona(params[0]);
            return null;
        }
    }

    public void deleta(Tarefa tarefa){
        tarefaDao.remove(tarefa);
    }

    public void atualiza(Tarefa tarefa){
        tarefaDao.atualiza(tarefa);
    }
}
