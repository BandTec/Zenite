package orion.zenite.entidades;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

@Entity
@Table(name="tblCarroLinha")
@IdClass(CarroLinhaId.class)
public class CarroLinha {
    @Id
    private int idLinha;

    @Id
    private int idCarro;

    // @EmbeddedId
    // MotoristaCarroId id;

    @ManyToOne(cascade = CascadeType.ALL)
    @MapsId("idLinha")
    @JoinColumn(name = "idLinha")
    private Linha linha;

    @JsonIgnore
    @ManyToOne(cascade = CascadeType.ALL)
    @MapsId("idCarro")
    @JoinColumn(name = "idCarro")
    private Carro carro;

    public int getIdLinha() {
        return idLinha;
    }

    public void setIdLinha(int idLinha) {
        this.idLinha = idLinha;
    }

    public int getIdCarro() {
        return idCarro;
    }

    public void setIdCarro(int idCarro) {
        this.idCarro = idCarro;
    }

    public Linha getLinha() {
        return linha;
    }

    public void setLinha(Linha linha) {
        this.linha = linha;
    }

    public Carro getCarro() {
        return carro;
    }

    public void setCarro(Carro carro) {
        this.carro = carro;
    }
}
