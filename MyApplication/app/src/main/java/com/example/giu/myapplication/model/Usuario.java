package com.example.giu.myapplication.model;

/**
 * Created by Giu on 17/04/2017.
 */

public class Usuario {
    private int id;
    private String login;
    private String senha;
    private int conectado;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public int getConectado() {
        return conectado;
    }

    public void setConectado(int conectado) {
        this.conectado = conectado;
    }
}
