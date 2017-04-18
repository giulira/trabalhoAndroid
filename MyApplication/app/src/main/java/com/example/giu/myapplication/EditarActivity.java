package com.example.giu.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;

import com.example.giu.myapplication.dao.MenuDAO;
import com.example.giu.myapplication.model.Cardapio;

public class EditarActivity extends AppCompatActivity {

    private EditText tilIDEdit;
    private EditText tilNomePratoEdit;
    private EditText tilDescricaoEdit;
    private EditText tilPeriodoEdit;
    private Spinner spCardapio;
    String id = "";
    String periodo = "";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editar);

        tilIDEdit = (EditText) findViewById(R.id.tilIDEdit);
        tilNomePratoEdit = (EditText) findViewById(R.id.tilNomePratoEdit);
        tilDescricaoEdit = (EditText) findViewById(R.id.tilDescricaoEdit);
        tilPeriodoEdit = (EditText) findViewById(R.id.tilPeriodoEdit);


            Intent intent = getIntent();
            Bundle bundle = intent.getExtras();
            id = bundle.getString("id");
            String prato = bundle.getString("prato");
            String descricao = bundle.getString("descricao");
            periodo = bundle.getString("periodo");

        tilIDEdit.setText(id);
        if(!prato.equals(tilNomePratoEdit)) {
            tilNomePratoEdit.setText(prato);
        }

       if(!descricao.equals(tilDescricaoEdit)) {
           tilDescricaoEdit.setText(descricao);
       }

       tilPeriodoEdit.setText(periodo);


    }

    public void update(View v){

        MenuDAO dao = new MenuDAO(this);
        Cardapio c = new Cardapio();
        c.setId(Integer.valueOf(id));
        c.setPeriodo(periodo);
        c.setPrato(tilNomePratoEdit.getText().toString());
        c.setDescricao(tilDescricaoEdit.getText().toString());
        dao.update(c);
        startActivity(new Intent(this, ListarCardapioActivity.class));
        finish();
    }
}
