package com.orion.autenticacao.models;

import java.util.List;

public class Linha {

    private String numero;
    private Ponto pontoIda;
    private Ponto pontoVolta;
    private List<Funcionario> funcionarios;

    public Linha(String numero, Ponto pontoIda,
                 Ponto pontoVolta) {
        this.numero = numero;
        this.pontoIda = pontoIda;
        this.pontoVolta = pontoVolta;
        this.funcionarios = funcionarios;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public Ponto getPontoIda() {
        return pontoIda;
    }

    public void setPontoIda(Ponto pontoIda) {
        this.pontoIda = pontoIda;
    }

    public Ponto getPontoVolta() {
        return pontoVolta;
    }

    public void setPontoVolta(Ponto pontoVolta) {
        this.pontoVolta = pontoVolta;
    }

    public List<Funcionario> getFuncionarios() {
        return funcionarios;
    }

    public void setFuncionarios(Funcionario funcionario) {
        this.funcionarios.add(funcionario);
    }
}
