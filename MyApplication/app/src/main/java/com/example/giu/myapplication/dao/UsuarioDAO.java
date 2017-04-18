package com.example.giu.myapplication.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.provider.Settings;

import com.example.giu.myapplication.model.Cardapio;
import com.example.giu.myapplication.model.Usuario;

/**
 * Created by Giu on 17/04/2017.
 */

public class UsuarioDAO {
    private DBOpenHelper banco;
    public static final String TABELA_USUARIO= "usuario";
    public static final String COLUNA_ID = "id";
    public static final String COLUNA_LOGIN = "login";
    public static final String COLUNA_SENHA = "senha";
    public static final String COLUNA_CONECTADO = "conectado";

    public UsuarioDAO(Context context){
        this.banco =  new DBOpenHelper(context);

    }

    public void add(Usuario u){
        long resultado;
        SQLiteDatabase db = banco.getWritableDatabase();
        ContentValues values = new ContentValues();


        values.put(COLUNA_CONECTADO, u.getConectado());
        values.put(COLUNA_SENHA, u.getSenha());
        values.put(COLUNA_LOGIN, u.getLogin());
        resultado = db.insert(TABELA_USUARIO, null, values);
        System.out.println(resultado);
    }


    public Usuario getBy(String login) {

       SQLiteDatabase db = banco.getReadableDatabase();
        String colunas[] = { COLUNA_LOGIN, COLUNA_SENHA, COLUNA_CONECTADO};
        String where = "login = '" + login+"'";
        Cursor cursor = db.query(true, TABELA_USUARIO, colunas, where, null, null, null, null, null);
        Usuario u = new Usuario();
        if(cursor != null) {
            cursor.moveToFirst();
            u = new Usuario();
            u.setLogin(cursor.getString(cursor.getColumnIndex(COLUNA_LOGIN)));
            u.setSenha(cursor.getString(cursor.getColumnIndex(COLUNA_SENHA)));
            u.setConectado(cursor.getInt(cursor.getColumnIndex(COLUNA_CONECTADO)));
        }
        return u;

    }

    public void updatedisconnect(Usuario u){
            String where = COLUNA_ID +"="+u.getId();
            SQLiteDatabase db = banco.getWritableDatabase();
            ContentValues values = new ContentValues();
            values.put(COLUNA_LOGIN, u.getLogin());
            values.put(COLUNA_SENHA, u.getSenha());
            values.put(COLUNA_CONECTADO, u.getConectado());
            db.update(TABELA_USUARIO, values, where, null);


    }
}
