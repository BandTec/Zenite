package orion.zenite.models;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="tblDispositivo")
public class Dispositivo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="idDispositivo")
    private int id;

    @Column(name = "codigoDispositivo", nullable = false)
    private String codigo;

    @ManyToOne
    @JoinColumn(name="fkTipo")
    private TipoDispositivo tipoDispositivo;

     /*@JsonIgnore
    @OneToOne(mappedBy = "dispositivo")
    private Carro carro;
      */

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public TipoDispositivo getTipoDispositivo() {
        return tipoDispositivo;
    }

    public void setTipoDispositivo(TipoDispositivo tipoDispositivo) {
        this.tipoDispositivo = tipoDispositivo;
    }
}
