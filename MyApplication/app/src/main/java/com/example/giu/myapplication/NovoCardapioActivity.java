package com.example.giu.myapplication;

import android.content.Intent;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.example.giu.myapplication.dao.MenuDAO;
import com.example.giu.myapplication.model.Cardapio;

public class NovoCardapioActivity extends AppCompatActivity {

    private TextInputLayout tilNomePrato;
    private TextInputLayout tilDescricao;
    private TextInputLayout tilPeriodo;
    public final static int CODE_NOVO_CARDAPIO = 1002;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_novo_cardapio);
        tilNomePrato = (TextInputLayout) findViewById(R.id.tilNomePrato);
        tilDescricao = (TextInputLayout) findViewById(R.id.tilDescricao);
        tilPeriodo = (TextInputLayout) findViewById(R.id.tilPeriodo);
    }

    public void cadastrar(View v) {
        MenuDAO dao = new MenuDAO(this);
        Cardapio cardapio = new Cardapio();

        cardapio.setPrato(tilNomePrato.getEditText().getText().toString());
        cardapio.setDescricao(tilDescricao.getEditText().getText().toString());
        cardapio.setPeriodo(tilPeriodo.getEditText().getText().toString());
        dao.add(cardapio);
        retornaParaTelaAnterior();

    }

    public void retornaParaTelaAnterior() {

        startActivity(new Intent(this, ListarCardapioActivity.class));
        finish();
    }
}
