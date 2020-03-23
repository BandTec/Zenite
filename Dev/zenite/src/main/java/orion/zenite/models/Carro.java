package orion.zenite.models;

import javax.persistence.*;
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

    @OneToMany(mappedBy = "carro")
    private List<CarroLinha> carroLinhas;

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

    public List<CarroLinha> getCarroLinhas() {
        return carroLinhas;
    }

    public void setCarroLinhas(List<CarroLinha> carroLinhas) {
        this.carroLinhas = carroLinhas;
    }
}
