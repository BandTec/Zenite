package orion.zenite.entidades.Dashboard;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "vwTempoDeViagemPorPeriodo")
public class ViagemPeriodoLinha {

    @Column(name = "id_linha")
    private Integer idLinha;

    @Id
    @Column(name = "periodo")
    private String periodo;

    @Column(name = "tempo_viagem_periodo")
    private Integer tempoViagemPeriodo;

    public Integer getIdLinha() {
        return idLinha;
    }

    public void setIdLinha(Integer idLinha) {
        this.idLinha = idLinha;
    }

    public String getPeriodo() {
        return periodo;
    }

    public void setPeriodo(String periodo) {
        this.periodo = periodo;
    }

    public Integer getTempoViagemPeriodo() {
        return tempoViagemPeriodo;
    }

    public void setTempoViagemPeriodo(Integer tempoViagemPeriodo) {
        this.tempoViagemPeriodo = tempoViagemPeriodo;
    }
}
