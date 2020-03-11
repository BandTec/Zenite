package com.orion.autenticacao.models;

public class Onibus {

    private String numero;
    private Linha Linha;

    public Onibus(String numero, Linha linha) {
        this.numero = numero;
        Linha = linha;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public Linha getLinha() {
        return Linha;
    }

    public void setLinha(Linha linha) {
        Linha = linha;
    }
}
