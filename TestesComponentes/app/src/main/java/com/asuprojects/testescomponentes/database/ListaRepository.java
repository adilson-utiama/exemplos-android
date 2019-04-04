package com.asuprojects.testescomponentes.database;

import android.content.Context;
import android.os.AsyncTask;

import com.asuprojects.testescomponentes.recyclerview.ListaItens;

import java.util.List;
import java.util.concurrent.ExecutionException;

public class ListaRepository {

    private ListaDAO listaDAO;

    public ListaRepository(Context ctx) {
        ItemRoomDatabase database = ItemRoomDatabase.getDatabase(ctx);
        listaDAO = database.getListaDAO();
    }

    public List<ListaItens> getAllLists() {
        return listaDAO.getAllLists();
    }

    public void deleteAll(){
        listaDAO.deleteAll();
    }

    public long salvaLista(ListaItens itens) {
        try {
            return new asyncSalvaLista(listaDAO).execute(itens).get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        return 0;
    }


    private static class asyncSalvaLista extends AsyncTask<ListaItens, Void, Long> {

        private ListaDAO asyncDAO;

        public asyncSalvaLista(ListaDAO dao) {
            asyncDAO = dao;
        }

        @Override
        protected Long doInBackground(ListaItens... listaItens) {
            return asyncDAO.salvaListaItens(listaItens[0]);
        }
    }
}
