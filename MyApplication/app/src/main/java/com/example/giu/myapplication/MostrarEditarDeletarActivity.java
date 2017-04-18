package com.example.giu.myapplication;

import android.app.Activity;
import android.content.Intent;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.giu.myapplication.dao.MenuDAO;
import com.example.giu.myapplication.model.Cardapio;

import java.util.List;

public class MostrarEditarDeletarActivity extends Activity {
private List<Cardapio> cardapios;
private Spinner spCardapio;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mostar_editar_deletar);
        MenuDAO dao = new MenuDAO(this);
        cardapios = dao.getAll();

        spCardapio = (Spinner) findViewById(R.id.spCardapio);
        ArrayAdapter<Cardapio> adapter = new ArrayAdapter<Cardapio>(getApplicationContext(), R.layout.cardapio_spinner_item, cardapios);
        adapter.setDropDownViewResource(R.layout.cardapio_spinner_item);
        spCardapio.setAdapter(adapter);

        Button btnOk =  (Button) findViewById(R.id.bteditar);
        btnOk.setOnClickListener(oncli);
    }

    private View.OnClickListener oncli = new View.OnClickListener() {
        @Override
        public void onClick(View view) {

            Intent intent = new Intent(MostrarEditarDeletarActivity.this, EditarActivity.class);
            Cardapio cardapio = new Cardapio();
            cardapio = (Cardapio)spCardapio.getSelectedItem();
            String id = String.valueOf(cardapio.getId());
            String prato = cardapio.getPrato();
            String descricao = cardapio.getDescricao();
            String periodo = cardapio.getPeriodo();

            Bundle bundle = new Bundle();

        bundle.putString("id", id);
        bundle.putString("prato", prato);
        bundle.putString("descricao", descricao);
        bundle.putString("periodo", periodo);
        intent.putExtras(bundle);
        startActivity(intent);

        }
    };

    public void excluir(View v) {
        MenuDAO dao = new MenuDAO(this);
        dao.excluir(((Cardapio)spCardapio.getSelectedItem()).getId());
        startActivity(new Intent(this, ListarCardapioActivity.class));
        finish();
    }

    public void editar(View v) {
        Cardapio cardapio = new Cardapio();
        cardapio = (Cardapio)spCardapio.getSelectedItem();
        startActivity(new Intent(MostrarEditarDeletarActivity.this, EditarActivity.class));
        finish();
    }

    public void voltar(View view){
        startActivity(new Intent(this, MainActivity.class));
        finish();
    }
}

