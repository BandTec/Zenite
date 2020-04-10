package orion.zenite.entidades;

import javax.persistence.*;

@Entity
@Table(name="tblAdmin")
public class Administrador {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idAdmin")
    private int id;

    @Column(length = 100, nullable = false)
    private String nome;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="fkConta")
    private Conta conta;

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

    public Conta getConta() {
        return conta;
    }

    public void setConta(Conta conta) {
        this.conta = conta;
    }
}
