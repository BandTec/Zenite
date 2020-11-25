package orion.zenite.modelos;

public class CronogramaLinha {

    private String horarioPrevisto;

    private String horarioRealizado;

    private String nomeMotorista;

    private Boolean atrasado;

    private String horarioAntigo;


    public String getHorarioPrevisto() {
        return horarioPrevisto;
    }

    public void setHorarioPrevisto(String horarioPrevisto) {
        this.horarioPrevisto = horarioPrevisto;
    }

    public String getHorarioRealizado() {
        return horarioRealizado;
    }

    public void setHorarioRealizado(String horarioRealizado) {
        this.horarioRealizado = horarioRealizado;
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
