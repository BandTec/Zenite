package orion.zenite.models;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="tblNivel")
public class Nivel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idNivel")
    private int id;

    @Column(name="descricao", nullable = false)
    private String tipoNivel;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTipoNivel() {
        return tipoNivel;
    }

    public void setTipoNivel(String tipoNivel) {
        this.tipoNivel = tipoNivel;
    }
}
