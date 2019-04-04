package com.asuprojects.testescomponentes.database;

import android.arch.lifecycle.LiveData;
import android.content.Context;
import android.os.AsyncTask;

import com.asuprojects.testescomponentes.recyclerview.Item;

import java.util.List;

public class ItemRepository {

    private ItemDAO itemDAO;
    private List<Item> listaItens;

    public ItemRepository(Context ctx) {
        ItemRoomDatabase db = ItemRoomDatabase.getDatabase(ctx);
        itemDAO = db.getItemDAO();
        listaItens = itemDAO.listaAll();
    }

    public List<Item> getListaItens(){
        return this.listaItens;
    }

    public List<Item> getItensPorListaId(long listaId) {
        return itemDAO.findItensByListaId(listaId);
    }

    public void adicionaItem(Item item) {
        new insertAsyncTask(itemDAO).execute(item);
    }

    public void removeItem(Item item) {
        new removeAsyncTask(itemDAO).execute(item);
    }


    private static class insertAsyncTask extends AsyncTask<Item, Void, Void> {
        private ItemDAO asyncTaskDao;
        insertAsyncTask(ItemDAO dao) {
            asyncTaskDao = dao;
        }
        @Override
        protected Void doInBackground(final Item... params) {
            asyncTaskDao.adiciona(params[0]);
            return null;
        }
    }

    private static class removeAsyncTask extends AsyncTask<Item, Void, Void> {
        private ItemDAO asyncTaskDao;
        removeAsyncTask(ItemDAO dao) {
            asyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(Item... items) {
            asyncTaskDao.remove(items[0]);
            return null;
        }
    }
}
