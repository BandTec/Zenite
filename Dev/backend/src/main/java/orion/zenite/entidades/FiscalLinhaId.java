package orion.zenite.entidades;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class FiscalLinhaId implements Serializable {

    @Column(name="idFiscal")
    private int idFiscal;

    @Column(name="idLinha")
    private int idLinha;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FiscalLinhaId that = (FiscalLinhaId) o;
        return idFiscal == that.idFiscal &&
                idLinha == that.idLinha;
    }

    @Override
    public int hashCode() {
        return Objects.hash(idFiscal, idLinha);
    }

    public int getIdFiscal() {
        return idFiscal;
    }

    public void setIdFiscal(int idFiscal) {
        this.idFiscal = idFiscal;
    }

    public int getIdLinha() {
        return idLinha;
    }

    public void setIdLinha(int idLinha) {
        this.idLinha = idLinha;
    }
}
