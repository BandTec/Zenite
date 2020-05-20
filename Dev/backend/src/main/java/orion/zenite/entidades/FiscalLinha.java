package orion.zenite.entidades;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

@Entity
@Table(name="tblFiscalLinha")
@IdClass(FiscalLinhaId.class)
public class FiscalLinha {

    @Id
    private int idFiscal;

    @Id
    private int idLinha;

    @JsonIgnore
    @ManyToOne
    @MapsId("idFiscal")
    @JoinColumn(name = "idFiscal")
    private Fiscal fiscal;

    @JsonIgnore
    @ManyToOne
    @MapsId("idLinha")
    @JoinColumn(name = "idLinha")
    private Linha linha;

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

    public Fiscal getFiscal() {
        return fiscal;
    }

    public void setFiscal(Fiscal fiscal) {
        this.fiscal = fiscal;
    }

    public Linha getLinha() {
        return linha;
    }

    public void setLinha(Linha linha) {
        this.linha = linha;
    }
}
