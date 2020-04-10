package orion.zenite.entidades;

import org.hibernate.validator.constraints.br.CPF;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name="tblGerente")
public class Gerente{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idGerente")
    private int id;

    @Column(name = "nomeGerente", length = 100, nullable = false)
    private String nome;

    @CPF
    @Column(name = "cpf", length = 14, nullable = false, unique = true)
    private String cpf;

    @Column(name = "dtNasc", nullable = false)
    private LocalDate dataNascimento;

    @Column(name = "telefone", length = 11, nullable = false)
    private String numeroTelefone;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="fkEndereco")
    private Endereco endereco;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="fkConta")
    private Conta conta;

    public Conta getConta() {
        return conta;
    }

    public void setConta(Conta conta) {
        this.conta = conta;
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
}
