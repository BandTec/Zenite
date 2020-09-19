package orion.zenite.entidades;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.validator.constraints.br.CPF;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="tblFiscal")
public class Fiscal  {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idFiscal")
    private int id;

    @Column(name = "nomeFiscal", length = 100, nullable = false)
    private String nome;

    @CPF
    @Column(name = "cpf", length = 14, nullable = false, unique = true)
    private String cpf;

    @Column(name = "dtNasc", nullable = false)
    private LocalDate dataNascimento;

    @Column(name = "telefone", length = 15, nullable = false)
    private String numeroTelefone;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="fkEndereco")
    private Endereco endereco;

    @Column(length = 20, nullable = false, unique = true)
    private String registroFiscal;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="fkConta")
    private Conta conta;

    @JsonIgnore
    @OneToMany(mappedBy = "fiscal", cascade = CascadeType.REMOVE)
    private List<Viagem> viagem;

    @JsonIgnore
    @OneToMany(mappedBy = "fiscal", cascade = CascadeType.REMOVE)
    private List<FiscalLinha> fiscalLinha;

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

    public String getRegistroFiscal() {
        return registroFiscal;
    }

    public void setRegistroFiscal(String registroFiscal) {
        this.registroFiscal = registroFiscal;
    }

  /*  public List getLinhasId() {
        ArrayList linhasId = new ArrayList();
        for (FiscalLinha linha : fiscalLinha) {
            linhasId.add(linha.getIdLinha());
        }
        return linhasId;
    }

   */

    public List getLinhas() {
        ArrayList linhas = new ArrayList();
        for (FiscalLinha fl : fiscalLinha) {
            Linha linha = fl.getLinha();
            linhas.add(linha);
        }
        return linhas;
    }
}
