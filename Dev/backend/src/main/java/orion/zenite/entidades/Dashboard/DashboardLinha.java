package orion.zenite.entidades.Dashboard;

import java.util.List;

public class DashboardLinha {

    private List<ViagemMotorista> viagemMotorista;
    private List<OnibusCirculando> onibusCirculando;
    private List<ViagemPeriodoLinha> viagemPeriodoLinha;
    private List<TempoMedioViagemDiaDaSemana> tempoMedioViagemDiaDaSemana;
    private Integer onibusAlocados;
    private Integer motoristasAlocados;
    private String fiscalResponsavel;
    private String numeroLinha;
    private Integer idLinha;

    public Integer getIdLinha() {
        return idLinha;
    }

    public void setIdLinha(Integer idLinha) {
        this.idLinha = idLinha;
    }

    public List<ViagemMotorista> getViagemMotorista() {
        return viagemMotorista;
    }

    public void setViagemMotorista(List<ViagemMotorista> viagemMotorista) {
        this.viagemMotorista = viagemMotorista;
    }

    public List<OnibusCirculando> getOnibusCirculando() {
        return onibusCirculando;
    }

    public void setOnibusCirculando(List<OnibusCirculando> onibusCirculando) {
        this.onibusCirculando = onibusCirculando;
    }

    public List<ViagemPeriodoLinha> getViagemPeriodoLinha() {
        return viagemPeriodoLinha;
    }

    public void setViagemPeriodoLinha(List<ViagemPeriodoLinha> viagemPeriodoLinha) {
        this.viagemPeriodoLinha = viagemPeriodoLinha;
    }

    public List<TempoMedioViagemDiaDaSemana> getTempoMedioViagemDiaDaSemana() {
        return tempoMedioViagemDiaDaSemana;
    }

    public void setTempoMedioViagemDiaDaSemana(List<TempoMedioViagemDiaDaSemana> tempoMedioViagemDiaDaSemana) {
        this.tempoMedioViagemDiaDaSemana = tempoMedioViagemDiaDaSemana;
    }

    public Integer getOnibusAlocados() {
        return onibusAlocados;
    }

    public void setOnibusAlocados(Integer onibusAlocados) {
        this.onibusAlocados = onibusAlocados;
    }

    public Integer getMotoristasAlocados() {
        return motoristasAlocados;
    }

    public void setMotoristasAlocados(Integer motoristasAlocados) {
        this.motoristasAlocados = motoristasAlocados;
    }

    public String getFiscalResponsavel() {
        return fiscalResponsavel;
    }

    public void setFiscalResponsavel(String fiscalResponsavel) {
        this.fiscalResponsavel = fiscalResponsavel;
    }

    public String getNumeroLinha() {
        return numeroLinha;
    }

    public void setNumeroLinha(String numeroLinha) {
        this.numeroLinha = numeroLinha;
    }
}
