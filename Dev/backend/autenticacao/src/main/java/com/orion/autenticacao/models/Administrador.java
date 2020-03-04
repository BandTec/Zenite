package com.orion.autenticacao.models;

public class Administrador extends Conta{

    private int id;
    private String nome;

    public Administrador(String senha, String email,
                         boolean isLogged, int id,
                         String nome) {
        super(senha, email, isLogged);
        this.id = id;
        this.nome = nome;
    }
}
