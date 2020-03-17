package com.orion.autenticacao.models;

public class Gerente extends Funcionario{
    public Gerente(String senha, String email, boolean isLogged, int id, String nome, String cpf, String dataNascimento, String ddd, String numeroTelefone, Endereco endereco) {
        super(senha, email, isLogged, id, nome, cpf, dataNascimento, ddd, numeroTelefone, endereco);
    }

    public void analisarRelatorio() {

    }
}
