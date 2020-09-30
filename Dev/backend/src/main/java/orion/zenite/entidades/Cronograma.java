package orion.zenite.entidades;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "tblCronograma")
public class Cronograma {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idCronograma;

    private LocalDate dataCronograma;

    @ManyToOne
    @JoinColumn(name="fkFiscal")
    private Fiscal fiscal;

    public Integer getIdCronograma() {
        return idCronograma;
    }

    public void setIdCronograma(Integer idCronograma) {
        this.idCronograma = idCronograma;
    }

    public LocalDate getDataCronograma() {
        return dataCronograma;
    }

    public void setDataCronograma(LocalDate dataCronograma) {
        this.dataCronograma = dataCronograma;
    }

    public Fiscal getFiscal() {
        return fiscal;
    }

    public void setFiscal(Fiscal fiscal) {
        this.fiscal = fiscal;
    }
}
