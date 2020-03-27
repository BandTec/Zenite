package orion.zenite.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.validator.constraints.br.CPF;
import orion.zenite.models.Conta;
import orion.zenite.models.Dispositivo;
import orion.zenite.models.Endereco;

import javax.persistence.*;
import java.time.LocalDate;

public class FiscalRequest {

    private int id;

    private int idConta;

    private String nome;

    private String email;

    private String senha;

    @CPF
    private String cpf;

    private LocalDate dataNascimento;

    private String numeroTelefone;

    private Endereco endereco;

    private String registroFiscal;

    private Dispositivo dispositivo;

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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public LocalDate getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(LocalDate dataNascimento) {
        this.dataNascimento = dataNascimento;
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

    public String getRegistroFiscal() {
        return registroFiscal;
    }

    public void setRegistroFiscal(String registroFiscal) {
        this.registroFiscal = registroFiscal;
    }

    public Dispositivo getDispositivo() {
        return dispositivo;
    }

    public void setDispositivo(Dispositivo dispositivo) {
        this.dispositivo = dispositivo;
    }

    public int getIdConta() {
        return idConta;
    }

    public void setIdConta(int idConta) {
        this.idConta = idConta;
    }
}
