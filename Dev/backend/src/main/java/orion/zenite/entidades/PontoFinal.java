package orion.zenite.entidades;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="tblPontoFinal")
public class PontoFinal {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idPontoFinal")
    private int id;

    @Column(name = "nomeTerminal", length = 80, nullable = false)
    private String nome;

    @JsonIgnore
    @OneToMany(mappedBy = "pontoIda", cascade = CascadeType.REMOVE)
    private List<Linha> linhasIda;

    @JsonIgnore
    @OneToMany(mappedBy = "pontoVolta", cascade = CascadeType.REMOVE)
    private List<Linha> linhasVolta;

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

    public Integer getTotalLinhas() {
        Integer linhasTotal= 0;
        linhasTotal += linhasIda.size() + linhasVolta.size();
        return linhasTotal;
    }

    @Override
    public String toString() {
        return "PontoFinal{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                '}';
    }
}
