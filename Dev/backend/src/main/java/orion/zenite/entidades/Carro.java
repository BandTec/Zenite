package orion.zenite.entidades;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="tblCarro")
public class Carro {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="idCarro")
    private int id;

    @Column(name="numeroCarro")
    private String numero;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="fkDispositivo", referencedColumnName = "idDispositivo")
    private Dispositivo dispositivo;

    @JsonIgnore
    @OneToMany(mappedBy = "carro", cascade = CascadeType.REMOVE)
    private List<CarroLinha> carroLinhas;

    @JsonIgnore
    @OneToMany(mappedBy = "carro", cascade = CascadeType.REMOVE)
    private List<MotoristaCarro> motoristaCarro;

    @JsonIgnore
    @OneToMany(mappedBy = "carro", cascade = CascadeType.REMOVE)
    private List<Viagem> viagem;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public Dispositivo getDispositivo() {
        return dispositivo;
    }

    public void setDispositivo(Dispositivo dispositivo) {
        this.dispositivo = dispositivo;
    }

    public List getLinhasId() {
        ArrayList linhasId = new ArrayList();
        for (CarroLinha carro : carroLinhas) {
            linhasId.add(carro.getIdLinha());
        }
        return linhasId;
    }
}
