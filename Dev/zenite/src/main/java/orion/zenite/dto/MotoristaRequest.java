package orion.zenite.dto;

import org.hibernate.validator.constraints.br.CPF;
import orion.zenite.models.Carro;
import orion.zenite.models.Dispositivo;
import orion.zenite.models.Endereco;

import java.time.LocalDate;
import java.util.List;

public class MotoristaRequest {

    private int id;

    private String nome;

    private String email;

    private String senha;

    @CPF
    private String cpf;

    private LocalDate dataNascimento;

    private String numeroTelefone;

    private Endereco endereco;

    private String cnh;

    private List<Carro> listaCarros;

    public List<Carro> getListaCarros() {
        return listaCarros;
    }

    public void setListaCarros(List<Carro> listaCarros) {
        this.listaCarros = listaCarros;
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

    public String getCnh() {
        return cnh;
    }

    public void setCnh(String cnh) {
        this.cnh = cnh;
    }
}
