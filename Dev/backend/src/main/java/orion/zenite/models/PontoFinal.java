package orion.zenite.models;

import javax.persistence.*;

@Entity
@Table(name="tblPontoFinal")
public class PontoFinal {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idPontoFinal")
    private int id;

    @Column(name = "nomeTerminal", length = 80, nullable = false)
    private String nome;

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

    @Override
    public String toString() {
        return "PontoFinal{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                '}';
    }
}
