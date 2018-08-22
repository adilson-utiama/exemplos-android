package com.asuprojects.exemploandroidroom.database;

import android.arch.persistence.db.SupportSQLiteDatabase;
import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.arch.persistence.room.TypeConverters;
import android.content.Context;
import android.os.AsyncTask;
import android.support.annotation.NonNull;

import com.asuprojects.exemploandroidroom.dao.TarefaDao;
import com.asuprojects.exemploandroidroom.models.Tarefa;
import com.asuprojects.exemploandroidroom.models.enums.Prioridade;
import com.asuprojects.exemploandroidroom.models.enums.TarefaStatus;

import java.util.Calendar;

@Database(entities = { Tarefa.class }, version = 1)
@TypeConverters({ DateTypeConverter.class, PrioridadeConverter.class, TarefaStatusConverter.class })
public abstract class TarefaRoomDatabase extends RoomDatabase{

    private static TarefaRoomDatabase INSTANCIA;

    public abstract TarefaDao getTarefaDao();

    public static TarefaRoomDatabase getDatabase(final Context context){
        if(INSTANCIA == null){
            synchronized (TarefaRoomDatabase.class){
                if(INSTANCIA == null){
                    INSTANCIA = Room.databaseBuilder(context.getApplicationContext(),
                            TarefaRoomDatabase.class, "tarefas_db")
                            .addCallback(roomDatabaseCallback)
                            .build();
                }
            }
        }
        return INSTANCIA;
    }


    private static RoomDatabase.Callback roomDatabaseCallback =
            new RoomDatabase.Callback(){

                @Override
                public void onOpen (@NonNull SupportSQLiteDatabase db){
                    super.onOpen(db);
                    new PopulateDbAsync(INSTANCIA).execute();
                }
            };

    //Popula banco em processo assincrono
    private static class PopulateDbAsync extends AsyncTask<Void, Void, Void> {

        private final TarefaDao dao;

        PopulateDbAsync(TarefaRoomDatabase db) {
            dao = db.getTarefaDao();
        }

        @Override
        protected Void doInBackground(final Void... params) {
            dao.deleteAll();
            Tarefa tarefa = new Tarefa();
            tarefa.setTitulo("Primeira Tarefa");
            tarefa.setDescricao("Descrição da Primeira Tarefa");
            tarefa.setDataIncluida(Calendar.getInstance().getTime());
            tarefa.setPrioridade(Prioridade.MIDDLE);
            tarefa.setStatus(TarefaStatus.NEW);
            dao.adiciona(tarefa);

            Tarefa tarefa2 = new Tarefa();
            tarefa2.setTitulo("Segunda Tarefa");
            tarefa2.setDescricao("Descrição da Segunda Tarefa");
            tarefa2.setDataIncluida(Calendar.getInstance().getTime());
            tarefa2.setPrioridade(Prioridade.HIGH);
            tarefa2.setStatus(TarefaStatus.DONE);
            dao.adiciona(tarefa2);
            return null;
        }
    }

}
