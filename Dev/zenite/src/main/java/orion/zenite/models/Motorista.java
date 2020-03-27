package orion.zenite.models;


import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.validator.constraints.br.CPF;
import orion.zenite.dto.MotoristaRequest;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
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

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name="fkConta")
    private Conta conta;

    @Column(length = 11, nullable = false, unique = true)
    private String cnh;

    @JsonIgnore
    @OneToMany(mappedBy = "motorista")
    List<MotoristaCarro> motoristaCarroList;

    public Motorista() {};

    public Motorista(MotoristaRequest motorista) {
        Nivel nivelAdm = new Nivel();
        nivelAdm.setId(4);

        Conta novaConta = new Conta();
        novaConta.setEmail(motorista.getEmail());
        novaConta.setSenha(motorista.getSenha());
        novaConta.setNivel(nivelAdm);

        this.conta = novaConta;
        this.nome = motorista.getNome();
        this.id = motorista.getId();
        this.cpf = motorista.getCpf();
        this.dataNascimento = motorista.getDataNascimento();
        this.numeroTelefone = motorista.getNumeroTelefone();
        this.endereco = motorista.getEndereco();
        this.cnh = motorista.getCnh();

        List<MotoristaCarro> listaMC = new ArrayList<>();
        for(Carro c : motorista.getListaCarros()){
            MotoristaCarro mc = new MotoristaCarro();
            mc.setCarro(c);
            listaMC.add(mc);
        }
        this.motoristaCarroList = listaMC;
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

    public void setConta(Conta conta) {
        this.conta = conta;
    }

    public Conta getConta() {
        return conta;
    }

    public String getEmail() {
        return conta.getEmail();
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

    public List getCarrosId() {
        ArrayList carrosId = new ArrayList();
        for (MotoristaCarro carro : motoristaCarroList) {
            carrosId.add(carro.getIdCarro());
        }
        return carrosId;
    }

    public void setMotoristaCarroList(List<MotoristaCarro> motoristaCarroList) {
        this.motoristaCarroList = motoristaCarroList;
    }
}
