package com.asuprojects.testesqlite3.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.asuprojects.testesqlite3.database.BancoSQLite3;
import com.asuprojects.testesqlite3.database.TabelaUsuario;
import com.asuprojects.testesqlite3.model.Usuario;

public class UsuarioDAO {

    private BancoSQLite3 banco;

    public UsuarioDAO(Context context) {
        this.banco = new BancoSQLite3(context);
    }

    public long insert(Usuario usuario){
        SQLiteDatabase db = banco.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(TabelaUsuario.COLUNA_USUARIO, usuario.getUsuario());
        values.put(TabelaUsuario.COLUNA_SENHA, usuario.getSenha());
        values.put(TabelaUsuario.COLUNA_PERGUNTA, usuario.getPergunta());
        values.put(TabelaUsuario.COLUNA_RESPOSTA, usuario.getResposta());

        long idSalvo = db.insertOrThrow(TabelaUsuario.TABELA_NOME, null, values);
        return idSalvo;

    }

    public Usuario findBy(String usuarioLogin){
        SQLiteDatabase db = banco.getReadableDatabase();

        StringBuilder query = new StringBuilder();
        query.append("SELECT * FROM ")
                .append(TabelaUsuario.TABELA_NOME)
                .append(" WHERE login = ? ;");

        Cursor cursor = db.rawQuery(query.toString(), new String[]{usuarioLogin});
        cursor.moveToFirst();

        Usuario usuario = new Usuario();

        if(cursor != null){
            long id = cursor.getLong(cursor.getColumnIndexOrThrow(TabelaUsuario.COLUNA_ID));
            String login = cursor.getString(cursor.getColumnIndexOrThrow(TabelaUsuario.COLUNA_USUARIO));
            String senha = cursor.getString(cursor.getColumnIndexOrThrow(TabelaUsuario.COLUNA_SENHA));
            String pergunta = cursor.getString(cursor.getColumnIndexOrThrow(TabelaUsuario.COLUNA_PERGUNTA));
            String resposta = cursor.getString(cursor.getColumnIndexOrThrow(TabelaUsuario.COLUNA_RESPOSTA));

            usuario.set_id(id);
            usuario.setUsuario(login);
            usuario.setSenha(senha);
            usuario.setPergunta(pergunta);
            usuario.setResposta(resposta);
        }
        cursor.close();

        return usuario;
    }
}
