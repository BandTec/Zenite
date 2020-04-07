package orion.zenite.models;

import javax.persistence.*;

@Entity
@Table(name="tblTipoDispositivo")
public class TipoDispositivo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idTipoDispositivo")
    private int id;

    @Column(nullable = false, length = 10)
    private String descricao;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
}
