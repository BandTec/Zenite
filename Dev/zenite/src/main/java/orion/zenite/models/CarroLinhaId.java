package orion.zenite.models;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class CarroLinhaId implements Serializable {

    @Column(name = "idCarro")
    private int idCarro;

    @Column(name = "idLinha")
    private int idLinha;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CarroLinhaId that = (CarroLinhaId) o;
        return idCarro == that.idCarro &&
                idLinha == that.idLinha;
    }

    @Override
    public int hashCode() {
        return Objects.hash(idCarro, idLinha);
    }

    public int getIdCarro() {
        return idCarro;
    }

    public void setIdCarro(int idCarro) {
        this.idCarro = idCarro;
    }

    public int getIdLinha() {
        return idLinha;
    }

    public void setIdLinha(int idLinha) {
        this.idLinha = idLinha;
    }
}
