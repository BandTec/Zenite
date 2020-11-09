package orion.zenite.modelos;

import java.time.LocalDateTime;

public class Horarios {

    private LocalDateTime horaPrevistaSaida;

    private LocalDateTime horaPrevistaChegada;

    private Integer motoristaId;


    public Integer getMotoristaId() {
        return motoristaId;
    }

    public void setMotoristaId(Integer motoristaId) {
        this.motoristaId = motoristaId;
    }

    public LocalDateTime getHoraPrevistaChegada() {
        return horaPrevistaChegada;
    }

    public void setHoraPrevistaChegada(LocalDateTime horaPrevistaChegada) {
        this.horaPrevistaChegada = horaPrevistaChegada;
    }

    public LocalDateTime getHoraPrevistaSaida() {
        return horaPrevistaSaida;
    }

    public void setHoraPrevistaSaida(LocalDateTime horaPrevistaSaida) {
        this.horaPrevistaSaida = horaPrevistaSaida;
    }
}