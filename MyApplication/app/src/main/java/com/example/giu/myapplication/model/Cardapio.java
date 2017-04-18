package com.example.giu.myapplication.model;

/**
 * Created by Giu on 15/04/2017.
 */

public class Cardapio {

    private int id;
    private String prato;
    private String descricao;
    private String periodo;

    public Cardapio(){

    }

    public Cardapio(int id, String prato, String descricao, String periodo) {
        this.id = id;
        this.prato = prato;
        this.descricao = descricao;
        this.periodo = periodo;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPrato() {
        return prato;
    }

    public void setPrato(String prato) {
        this.prato = prato;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getPeriodo() {
        return periodo;
    }

    public void setPeriodo(String periodo) {
        this.periodo = periodo;
    }

    @Override
    public String toString() {
        return " "+getPrato();
    }
}
