package orion.zenite.modelos;

public class CronogramaLinha {

    private String horarioSaida;

    private String horarioChegada;

    private String nomeMotorista;

    private Boolean atrasado;

    private String horarioAntigo;


    public String getHorarioSaida() {
        return horarioSaida;
    }

    public void setHorarioSaida(String horarioSaida) {
        this.horarioSaida = horarioSaida;
    }

    public String getHorarioChegada() {
        return horarioChegada;
    }

    public void setHorarioChegada(String horarioChegada) {
        this.horarioChegada = horarioChegada;
    }

    public String getNomeMotorista() {
        return nomeMotorista;
    }

    public void setNomeMotorista(String nomeMotorista) {
        this.nomeMotorista = nomeMotorista;
    }

    public Boolean getAtrasado() {
        return atrasado;
    }

    public void setAtrasado(Boolean atrasado) {
        this.atrasado = atrasado;
    }

    public String getHorarioAntigo() {
        return horarioAntigo;
    }

    public void setHorarioAntigo(String horarioAntigo) {
        this.horarioAntigo = horarioAntigo;
    }

}
