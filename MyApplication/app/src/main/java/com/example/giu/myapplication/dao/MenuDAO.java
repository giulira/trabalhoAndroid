package com.example.giu.myapplication.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.view.View;

import com.example.giu.myapplication.model.Cardapio;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by Giu on 15/04/2017.
 */

public class MenuDAO {

    private DBOpenHelper banco;
    public static final String TABELA_CARDAPIO = "cardapio";
    public static final String COLUNA_ID = "id";
    public static final String COLUNA_PRATO = "prato";
    public static final String COLUNA_DESCRICAO = "descricao";
    public static final String COLUNA_PERIODO = "periodo";

    public MenuDAO(Context context){

        banco = new DBOpenHelper(context);
    }

    public List<Cardapio> getAll() {
        List<Cardapio> cardapios = new LinkedList<Cardapio>();
        String query = "SELECT * FROM "+TABELA_CARDAPIO+"  ORDER BY "+COLUNA_PERIODO;

        SQLiteDatabase db = banco.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        Cardapio cardapio = null;

        if(cursor.moveToFirst()) {

            do {
                cardapio = new Cardapio();
                cardapio.setId(cursor.getInt(cursor.getColumnIndex(COLUNA_ID)));
                cardapio.setDescricao(cursor.getString(cursor.getColumnIndex(COLUNA_DESCRICAO)));
                cardapio.setPrato(cursor.getString(cursor.getColumnIndex(COLUNA_PRATO)));
                cardapio.setPeriodo(cursor.getString(cursor.getColumnIndex(COLUNA_PERIODO)));
                cardapios.add(cardapio);
            } while (cursor.moveToNext());
        }
        return cardapios;
    }

    public String add(Cardapio cardapio){
        long resultado;
        SQLiteDatabase db = banco.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUNA_PRATO, cardapio.getPrato());
        values.put(COLUNA_DESCRICAO, cardapio.getDescricao());
        values.put(COLUNA_PERIODO, cardapio.getPeriodo());
        resultado = db.insert(TABELA_CARDAPIO, null, values);

        if(resultado == -1){
            return "Erro ao inserir registro";

        }else {
            return "Registro inserido com sucesso";
        }
    }

    public void excluir(int id){
        String coluna = COLUNA_ID +"="+ id;
        SQLiteDatabase db = banco.getWritableDatabase();
        db.delete(TABELA_CARDAPIO, coluna, null);
    }

    public void update(Cardapio cardapio){
        SQLiteDatabase db = banco.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUNA_PRATO, cardapio.getPrato());
        values.put(COLUNA_DESCRICAO, cardapio.getDescricao());
        values.put(COLUNA_PERIODO, cardapio.getPeriodo());
        db.update(TABELA_CARDAPIO, values, COLUNA_ID +"="+cardapio.getId(), null);

    }
}
