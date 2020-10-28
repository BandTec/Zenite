package orion.zenite.modelos;

import orion.zenite.entidades.Linha;

import java.time.LocalDate;
import java.util.List;

public class CronogramaLista {

    private LocalDate dataCronograma;

    private Integer linhaId;

    private List<Horarios> horarios;

    public LocalDate getDataCronograma() {
        return dataCronograma;
    }

    public void setDataCronograma(LocalDate dataCronograma) {
        this.dataCronograma = dataCronograma;
    }

    public Integer getLinhaId() {
        return linhaId;
    }

    public void setLinhaId(Integer linhaId) {
        this.linhaId = linhaId;
    }

    public List<Horarios> getHorarios() {
        return horarios;
    }

    public void setHorarios(List<Horarios> horarios) {
        this.horarios = horarios;
    }
}


