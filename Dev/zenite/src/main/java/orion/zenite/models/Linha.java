package orion.zenite.models;


import javax.persistence.*;

@Entity
@Table(name="tblLinha")
public class Linha {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idLinha")
    private int id;

    @Column(length = 7, nullable = false)
    private String numero;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "fkPontoIda")
    private PontoFinal pontoIda;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "fkPontoVolta")
    private PontoFinal pontoVolta;

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public PontoFinal getPontoIda() {
        return pontoIda;
    }

    public void setPontoIda(PontoFinal pontoIda) {
        this.pontoIda = pontoIda;
    }

    public PontoFinal getPontoVolta() {
        return pontoVolta;
    }

    public void setPontoVolta(PontoFinal pontoVolta) {
        this.pontoVolta = pontoVolta;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
