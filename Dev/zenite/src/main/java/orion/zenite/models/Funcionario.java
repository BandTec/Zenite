package orion.zenite.models;

import java.util.Date;

public abstract class Funcionario extends Conta {

    private int id;
    private String nome;
    private String cpf;
    private String dataNascimento;
    private String numeroTelefone;
    private Endereco endereco;

    public Funcionario(int id, String senha, String email, Nivel nivel, String nome, String cpf, String dataNascimento, String numeroTelefone, Endereco endereco) {
        super(senha, email, nivel);
        this.id = id;
        this.nome = nome;
        this.cpf = cpf;
        this.dataNascimento = dataNascimento;
        this.numeroTelefone = numeroTelefone;
        this.endereco = endereco;
    }

    public Funcionario(String senha, String email, Nivel nivel, String nome, String cpf, String dataNascimento, String numeroTelefone, Endereco endereco) {
        super(senha, email, nivel);
        this.nome = nome;
        this.cpf = cpf;
        this.dataNascimento = dataNascimento;
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
