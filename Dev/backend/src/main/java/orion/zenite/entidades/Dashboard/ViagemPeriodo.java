package orion.zenite.entidades.Dashboard;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "vwTempoMedioViagemPeriodo")
public class ViagemPeriodo {

    @Id
    @Column(name = "tempoMedioViagemPeriodo")
    private Integer tempoMedio;

    @Column(name = "periodo")
    private String periodo;

    public Integer getTempoMedio() {
        return tempoMedio;
    }

    public void setTempoMedio(Integer tempoMedio) {
        this.tempoMedio = tempoMedio;
    }

    public String getPeriodo() {
        return periodo;
    }

    public void setPeriodo(String periodo) {
        this.periodo = periodo;
    }
}
