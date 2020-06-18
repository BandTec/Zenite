package orion.zenite.entidades.Dashboard;

import java.util.List;
import java.util.Optional;

public class DashboardGeral {
    private List<DadosLinha> dadosLinha;
    private Optional<OperandoParado> operandoParado;
    private Optional<Integer> carrosNaoAlocados;
    private Optional<Integer> tempoMedioViagemHora;
    private List<ViagemPeriodo> tempoMedioViagemPeriodo;

    public List<DadosLinha> getDadosLinha() {
        return dadosLinha;
    }

    public void setDadosLinha(List<DadosLinha> dadosLinha) {
        this.dadosLinha = dadosLinha;
    }

    public Optional<OperandoParado> getOperandoParado() {
        return operandoParado;
    }

    public void setOperandoParado(Optional<OperandoParado> operandoParado) {
        this.operandoParado = operandoParado;
    }

    public Optional<Integer> getCarrosNaoAlocados() {
        return carrosNaoAlocados;
    }

    public void setCarrosNaoAlocados(Optional<Integer> carrosNaoAlocados) {
        this.carrosNaoAlocados = carrosNaoAlocados;
    }

    public Optional<Integer> getTempoMedioViagemHora() {
        return tempoMedioViagemHora;
    }

    public void setTempoMedioViagemHora(Optional<Integer> tempoMedioViagemHora) {
        this.tempoMedioViagemHora = tempoMedioViagemHora;
    }

    public List<ViagemPeriodo> getTempoMedioViagemPeriodo() {
        return tempoMedioViagemPeriodo;
    }

    public void setTempoMedioViagemPeriodo(List<ViagemPeriodo> tempoMedioViagemPeriodo) {
        this.tempoMedioViagemPeriodo = tempoMedioViagemPeriodo;
    }
}
