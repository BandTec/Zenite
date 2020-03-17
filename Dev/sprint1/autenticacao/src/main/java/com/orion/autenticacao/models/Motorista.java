package com.orion.autenticacao.models;

public class Motorista extends Funcionario {
    private String cnh;

    public Motorista(String senha, String email, boolean isLogged, int id, String nome, String cpf, String dataNascimento, String ddd, String numeroTelefone, Endereco endereco, String cnh) {
        super(senha, email, isLogged, id, nome, cpf, dataNascimento, ddd, numeroTelefone, endereco);
        this.cnh = cnh;
    }

    public String getCnh() {
        return cnh;
    }

    public void setCnh(String cnh) {
        this.cnh = cnh;
    }
}
