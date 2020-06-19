package orion.zenite.entidades.Dashboard;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "vwTempoMedioViagemPorDiaSemana")
public class TempoMedioViagemDiaDaSemana {
    @Id
    @Column(name = "Id")
    private String id;

    @Column(name = "id_linha")
    private Integer idLinha;

    @Column(name = "tempo_viagem_periodo")
    private String tempoViagemPeriodo;

    @Column(name = "dia_semana")
    private String diaSemana;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getIdLinha() {
        return idLinha;
    }

    public void setIdLinha(Integer idLinha) {
        this.idLinha = idLinha;
    }

    public String getTempoViagemPeriodo() {
        return tempoViagemPeriodo;
    }

    public void setTempoViagemPeriodo(String tempoViagemPeriodo) {
        this.tempoViagemPeriodo = tempoViagemPeriodo;
    }

    public String getDiaSemana() {
        return diaSemana;
    }

    public void setDiaSemana(String diaSemana) {
        this.diaSemana = diaSemana;
    }
}
