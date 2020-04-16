package orion.zenite.dto;

import java.time.LocalDateTime;
import java.time.LocalTime;

public class ViagemDto {

    private int viagemId;

    private int carroId;

    private int linhaId;

    private int motoristaId;

    private int fiscalId;

    private int qtdPassageiros;

    private LocalDateTime horaSaida;

    private LocalDateTime horaChegada;

    public int getViagemId() {
        return viagemId;
    }

    public void setViagemId(int viagemId) {
        this.viagemId = viagemId;
    }

    public int getCarroId() {
        return carroId;
    }

    public void setCarroId(int carroId) {
        this.carroId = carroId;
    }

    public int getLinhaId() {
        return linhaId;
    }

    public void setLinhaId(int linhaId) {
        this.linhaId = linhaId;
    }

    public int getMotoristaId() {
        return motoristaId;
    }

    public void setMotoristaId(int motoristaId) {
        this.motoristaId = motoristaId;
    }

    public int getFiscalId() {
        return fiscalId;
    }

    public void setFiscalId(int fiscalId) {
        this.fiscalId = fiscalId;
    }

    public int getQtdPassageiros() {
        return qtdPassageiros;
    }

    public void setQtdPassageiros(int qtdPassageiros) {
        this.qtdPassageiros = qtdPassageiros;
    }

    public LocalDateTime getHoraSaida() {
        return horaSaida;
    }

    public void setHoraSaida(LocalDateTime horaSaida) {
        this.horaSaida = horaSaida;
    }

    public LocalDateTime getHoraChegada() {
        return horaChegada;
    }

    public void setHoraChegada(LocalDateTime horaChegada) {
        this.horaChegada = horaChegada;
    }
}
