package com.orion.autenticacao;

public class Usuario {

    private String login;
    private String senha, nome;
    private boolean isLogged;

    public Usuario(String login, String senha, String nome, boolean isLogged) {
        this.login = login;
        this.senha = senha;
        this.nome = nome;
        this.isLogged = isLogged;
    }

    @Override
    public String toString() {
        return "com.example.demo.Usuario: " +
                "login='" + login + '\'' +
                ", senha='" + senha + '\'' +
                ", nome='" + nome + '\'' +
                ", Está logado?" + (isLogged ? "Sim" : "Não") +
                '}';
    }

    //getters e setters
    public void setLogin(String login) {
        this.login = login;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setLogged(boolean logged) {
        isLogged = logged;
    }

    public String getLogin() {
        return login;
    }

    public String getSenha() {
        return senha;
    }

    public String getNome() {
        return nome;
    }

    public boolean isLogged() {
        return isLogged;
    }


}
