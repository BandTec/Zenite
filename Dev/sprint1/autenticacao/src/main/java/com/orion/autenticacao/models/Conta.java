package com.orion.autenticacao.models;

public abstract class Conta {

    private String senha;
    private String email;
    private boolean isLogged;

    public Conta(String senha, String email, boolean isLogged) {
        this.senha = senha;
        this.email = email;
        this.isLogged = isLogged;
    }

    @Override
    public String toString() {
        return "Usuario: " +
                "senha='" + senha + '\'' +
                ", email='" + email + '\'' +
                ", Está logado?" + (isLogged ? "Sim" : "Não") +
                '}';
    }

    //getters e setters

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setLogged(boolean logged) {
        isLogged = logged;
    }

    public String getSenha() {
        return senha;
    }

    public String getEmail() {
        return email;
    }

    public boolean isLogged() {
        return isLogged;
    }

    public boolean logar(String email, String senha) {
        if(this.senha.equals(senha) && this.email.equals(email)){
            setLogged(true);
            return true;
        } else {
            return false;
        }
    }

}
