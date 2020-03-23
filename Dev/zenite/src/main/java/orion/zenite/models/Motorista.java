package orion.zenite.models;


import org.hibernate.validator.constraints.br.CPF;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name="tblMotorista")
public class Motorista {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idMotorista")
    private int id;

    @Column(name = "nomeMotorista", length = 100, nullable = false)
    private String nome;

    @CPF
    @Column(name = "cpf", length = 14, nullable = false, unique = true)
    private String cpf;

    @Column(name = "dtNasc", nullable = false)
    private LocalDate dataNascimento;

    @Column(name = "telefone", length = 11, nullable = false)
    private String numeroTelefone;

    @ManyToOne
    @JoinColumn(name="fkEndereco")
    private Endereco endereco;

    @ManyToOne
    @JoinColumn(name="fkConta")
    private Conta conta;

    @Column(length = 11, nullable = false, unique = true)
    private String cnh;

    @OneToMany(mappedBy = "motorista")
    List<MotoristaCarro> motoristaCarroList;

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

    public Conta getConta() {
        return conta;
    }

    public void setConta(Conta conta) {
        this.conta = conta;
    }

    public String getCnh() {
        return cnh;
    }

    public void setCnh(String cnh) {
        this.cnh = cnh;
    }

    public List<MotoristaCarro> getMotoristaCarroList() {
        return motoristaCarroList;
    }

    public void setMotoristaCarroList(List<MotoristaCarro> motoristaCarroList) {
        this.motoristaCarroList = motoristaCarroList;
    }
}
