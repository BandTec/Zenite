package orion.zenite.entidades;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "tblCronogramaHorariosAlterados")
public class CronogramaHorariosAlterados {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idCronogramaHorariosAlterados;

    @Column
    private LocalDateTime novaHoraPrevistaSaida;

    @Column
    private LocalDateTime novaHoraPrevistaChegada;

    @ManyToOne//(cascade = CascadeType.ALL)
    @JoinColumn(name="idCronograma")
    private CronogramaHorarios cronogramaHorarios;

    public Integer getIdCronogramaHorariosAlterados() {
        return idCronogramaHorariosAlterados;
    }

    public void setIdCronogramaHorariosAlterados(Integer idCronogramaHorariosAlterados) {
        this.idCronogramaHorariosAlterados = idCronogramaHorariosAlterados;
    }

    public LocalDateTime getNovaHoraPrevistaSaida() {
        return novaHoraPrevistaSaida;
    }

    public void setNovaHoraPrevistaSaida(LocalDateTime novaHoraPrevistaSaida) {
        this.novaHoraPrevistaSaida = novaHoraPrevistaSaida;
    }

    public LocalDateTime getNovaHoraPrevistaChegada() {
        return novaHoraPrevistaChegada;
    }

    public void setNovaHoraPrevistaChegada(LocalDateTime novaHoraPrevistaChegada) {
        this.novaHoraPrevistaChegada = novaHoraPrevistaChegada;
    }

    public CronogramaHorarios getCronogramaHorarios() {
        return cronogramaHorarios;
    }

    public void setCronogramaHorarios(CronogramaHorarios cronogramaHorarios) {
        this.cronogramaHorarios = cronogramaHorarios;
    }
}
