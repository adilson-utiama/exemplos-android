package com.asuprojects.testesqlite3.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class BancoSQLite3 extends SQLiteOpenHelper {

    private static final int VERSAO = 1;
    private static final String NOME_BANCO = "financas";

    public BancoSQLite3(Context context) {
        super(context, NOME_BANCO, null, VERSAO);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CRIAR_TABELA_DESPESAS);
        db.execSQL(CRIAR_TABELA_USUARIO);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL(DELETAR_TABELA_DESPESAS);
        db.execSQL(DELETAR_TABELA_USUARIO);
        onCreate(db);
    }


    private static final String CRIAR_TABELA_DESPESAS =
            "CREATE TABLE " + TabelaDespesa.NOME_TABELA + " (" +
                    TabelaDespesa.COLUNA_ID + " INTEGER PRIMARY KEY," +
                    TabelaDespesa.COLUNA_DESCRICAO + " TEXT, " +
                    TabelaDespesa.COLUNA_CATEGORIA + " TEXT, " +
                    TabelaDespesa.COLUNA_DATA + " TEXT, " +
                    TabelaDespesa.COLUNA_VALOR + " REAL )";

    private static final String DELETAR_TABELA_DESPESAS =
            "DROP TABLE IF EXISTS " + TabelaDespesa.NOME_TABELA;

    private static final String CRIAR_TABELA_USUARIO =
            "CREATE TABLE " + TabelaUsuario.TABELA_NOME + " (" +
                    TabelaUsuario.COLUNA_ID + " INTEGER PRIMARY KEY," +
                    TabelaUsuario.COLUNA_USUARIO + " TEXT, " +
                    TabelaUsuario.COLUNA_SENHA + " TEXT, " +
                    TabelaUsuario.COLUNA_PERGUNTA + " TEXT, " +
                    TabelaUsuario.COLUNA_RESPOSTA + " TEXT )";

    private static final String DELETAR_TABELA_USUARIO =
            "DROP TABLE IF EXISTS " + TabelaUsuario.TABELA_NOME;
}
