package orion.zenite.entidades;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class MotoristaCarroId implements Serializable {

    @Column(name = "idMotorista")
    int idMotorista;

    @Column(name = "idCarro")
    int idCarro;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MotoristaCarroId that = (MotoristaCarroId) o;
        return idMotorista == that.idMotorista &&
                idCarro == that.idCarro;
    }

    @Override
    public int hashCode() {
        return Objects.hash(idMotorista, idCarro);
    }

    public int getIdMotorista() {
        return idMotorista;
    }

    public void setIdMotorista(int idMotorista) {
        this.idMotorista = idMotorista;
    }

    public int getIdCarro() {
        return idCarro;
    }

    public void setIdCarro(int idCarro) {
        this.idCarro = idCarro;
    }
}
