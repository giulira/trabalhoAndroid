package com.example.giu.myapplication;


import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.view.View;
import android.widget.TextView;

import com.example.giu.myapplication.dao.MenuDAO;
import com.example.giu.myapplication.model.Cardapio;

import java.util.List;

public class ListarCardapioActivity extends AppCompatActivity {

    private TextView listaCardapio;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listar_cardapio);
        listaCardapio = (TextView ) findViewById(R.id.listaCardapio);
        carregarListaCardapio();
    }

    private void carregarListaCardapio(){
        listaCardapio.setText("");
        StringBuilder sb = new StringBuilder();
        MenuDAO dao = new MenuDAO(this);
        List<Cardapio> cardapios =  dao.getAll();

        for (Cardapio c : cardapios){
            sb = new StringBuilder(listaCardapio.getText());
            sb.append("\n");
            sb.append(c.getPrato());
            sb.append("\n");
            sb.append(c.getDescricao());
            sb.append("\n");
            sb.append(c.getPeriodo());
            sb.append("\n");
            listaCardapio.setText(sb.toString());
        }
    }

    public void voltar(View view){
        startActivity(new Intent(this, MainActivity.class));
        finish();
    }

}
