package com.asuprojects.testesqlite3.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.CursorIndexOutOfBoundsException;
import android.database.sqlite.SQLiteDatabase;
import android.support.annotation.NonNull;

import com.asuprojects.testesqlite3.converters.BigDecimalConverter;
import com.asuprojects.testesqlite3.converters.CalendarConverter;
import com.asuprojects.testesqlite3.database.BancoSQLite3;
import com.asuprojects.testesqlite3.database.TabelaDespesa;
import com.asuprojects.testesqlite3.helper.CategoriaUtil;
import com.asuprojects.testesqlite3.model.Categoria;
import com.asuprojects.testesqlite3.model.Despesa;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class DespesaDAO {

    private BancoSQLite3 banco;

    public DespesaDAO(Context context) {
        this.banco = new BancoSQLite3(context);
    }

    public List<Despesa> getAll() {
        SQLiteDatabase db = banco.getReadableDatabase();
        List<Despesa> despesas = new ArrayList<>();

        String query = "SELECT * FROM  " + TabelaDespesa.NOME_TABELA + ";";
        Cursor cursor = db.rawQuery(query, null);
        cursor.moveToFirst();
        for (int i = 0; i < cursor.getCount(); i++) {
            Despesa despesa = despesaFrom(cursor);
            despesas.add(despesa);
            cursor.moveToNext();
        }

        return despesas;
    }

    public Despesa findOne(long id) {
        SQLiteDatabase db = banco.getReadableDatabase();

        String selection = TabelaDespesa.COLUNA_ID + " = ?";
        String[] args = {String.valueOf(id)};

        Cursor cursor = db.query(TabelaDespesa.NOME_TABELA,
                null,
                selection,
                args,
                null,
                null,
                null);

        Despesa despesa = null;
        if (cursor != null) {
            cursor.moveToFirst();
            despesa = despesaFrom(cursor);
        } else {
            return null;
        }
        cursor.close();

        return despesa;
    }


    public long insertOrUpdate(Despesa despesa) {
        SQLiteDatabase db = banco.getWritableDatabase();
        ContentValues values = despesaParaContentValues(despesa);
        if (despesa.get_id() != 0) {
            return update(despesa);
        } else {
            return db.insertOrThrow(TabelaDespesa.NOME_TABELA, null, values);
        }

    }


    public long update(Despesa despesa) {
        SQLiteDatabase db = banco.getWritableDatabase();
        ContentValues values = despesaParaContentValues(despesa);
        int updateId = db.update(TabelaDespesa.NOME_TABELA,
                values,
                " _id = ? ",
                new String[]{String.valueOf(despesa.get_id())});

        return updateId;
    }

    public long delete(long id) {
        SQLiteDatabase db = banco.getWritableDatabase();

        int idDeletado = db.delete(TabelaDespesa.NOME_TABELA,
                " _id = ?",
                new String[]{String.valueOf(id)});

        return idDeletado;
    }


    public void close() {
        this.banco.close();
    }

    private ContentValues despesaParaContentValues(Despesa despesa) {
        ContentValues values = new ContentValues();
        values.put(TabelaDespesa.COLUNA_DESCRICAO, despesa.getDescricao());
        values.put(TabelaDespesa.COLUNA_CATEGORIA, despesa.getCategoriaDespesa().getDescricao());
        values.put(TabelaDespesa.COLUNA_DATA, CalendarConverter.toStringFormatada(despesa.getData()));
        values.put(TabelaDespesa.COLUNA_VALOR, despesa.getValor().doubleValue());

        return values;
    }

    @NonNull
    private Despesa despesaFrom(Cursor cursor) {
        Despesa despesa = new Despesa();

        try {
            long id = cursor.getLong(cursor.getColumnIndexOrThrow(TabelaDespesa.COLUNA_ID));
            despesa.set_id(id);

        } catch (CursorIndexOutOfBoundsException e) {
            e.printStackTrace();
        }


        String descricao = cursor.getString(cursor.getColumnIndexOrThrow(TabelaDespesa.COLUNA_DESCRICAO));
        despesa.setDescricao(descricao);

        String categoriaString = cursor.getString(cursor.getColumnIndexOrThrow(TabelaDespesa.COLUNA_CATEGORIA));
        Categoria categoria = CategoriaUtil.getCategoriaFrom(categoriaString);
        despesa.setCategoriaDespesa(categoria);

        String dataString = cursor.getString(cursor.getColumnIndexOrThrow(TabelaDespesa.COLUNA_DATA));
        Calendar data = CalendarConverter.toCalendar(dataString);
        despesa.setData(data);

        String valorString = cursor.getString(cursor.getColumnIndexOrThrow(TabelaDespesa.COLUNA_VALOR));
        BigDecimal valor = BigDecimalConverter.toBigDecimal(valorString);
        despesa.setValor(valor);

        return despesa;
    }
}
