package com.orion.autenticacao.models;

public class Fiscal extends Funcionario {
    private Gerente supervisor;

    public Fiscal(String senha, String email, boolean isLogged, int id, String nome, String cpf, String dataNascimento, String ddd, String numeroTelefone, Endereco endereco, Gerente supervisor) {
        super(senha, email, isLogged, id, nome, cpf, dataNascimento, ddd, numeroTelefone, endereco);
        this.supervisor = supervisor;
    }

    public Gerente getSupervisor() {
        return supervisor;
    }

    public void setSupervisor(Gerente supervisor) {
        this.supervisor = supervisor;
    }
}
