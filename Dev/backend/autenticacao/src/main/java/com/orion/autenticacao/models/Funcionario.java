package com.orion.autenticacao.models;

import java.util.Date;

public abstract class Funcionario extends Conta {

    private int id;
    private String nome;
    private String cpf;
    private String dataNascimento;
    private String ddd;
    private String numeroTelefone;
    private Endereco endereco;


    public Funcionario(String senha, String email, boolean isLogged, int id, String nome, String cpf, String dataNascimento, String ddd, String numeroTelefone, Endereco endereco) {
        super(senha, email, isLogged);
        this.id = id;
        this.nome = nome;
        this.cpf = cpf;
        this.dataNascimento = dataNascimento;
        this.ddd = ddd;
        this.numeroTelefone = numeroTelefone;
        this.endereco = endereco;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(String dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public String getDdd() {
        return ddd;
    }

    public void setDdd(String ddd) {
        this.ddd = ddd;
    }

    public String getNumeroTelefone() {
        return numeroTelefone;
    }

    public void setNumeroTelefone(String numeroTelefone) {
        this.numeroTelefone = numeroTelefone;
    }

    public Endereco getEndereco() {
        return endereco;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }
}
