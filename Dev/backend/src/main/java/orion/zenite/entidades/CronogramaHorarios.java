package orion.zenite.entidades;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "tblCronogramaHorarios")
public class CronogramaHorarios {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idCronogramaHorarios;

    @ManyToOne//(cascade = CascadeType.ALL)
    @JoinColumn(name="idCarro")
    private Carro carro;

    @ManyToOne//(cascade = CascadeType.ALL)
    @JoinColumn(name="idLinha")
    private Linha linha;

    @ManyToOne//(cascade = CascadeType.ALL)
    @JoinColumn(name="idMotorista")
    private Motorista motorista;

    @Column
    private LocalDateTime horaPrevistaSaida;

    @Column
    private LocalDateTime horaPrevistaChegada;

    @Column(columnDefinition = "int default '1'")
    private int viagemStatus; //1 = aguardando, 2 = em viagem e 3 = finalizada

    @ManyToOne//(cascade = CascadeType.ALL)
    @JoinColumn(name="idCronograma")
    private Cronograma cronograma;

    public Integer getIdCronogramaHorarios() {
        return idCronogramaHorarios;
    }

    public void setIdCronogramaHorarios(Integer idCronogramaHorarios) {
        this.idCronogramaHorarios = idCronogramaHorarios;
    }

    public Carro getCarro() {
        return carro;
    }

    public void setCarro(Carro carro) {
        this.carro = carro;
    }

    public Linha getLinha() {
        return linha;
    }

    public void setLinha(Linha linha) {
        this.linha = linha;
    }

    public Motorista getMotorista() {
        return motorista;
    }

    public void setMotorista(Motorista motorista) {
        this.motorista = motorista;
    }

    public LocalDateTime getHoraPrevistaSaida() {
        return horaPrevistaSaida;
    }

    public void setHoraPrevistaSaida(LocalDateTime horaPrevistaSaida) {
        this.horaPrevistaSaida = horaPrevistaSaida;
    }

    public LocalDateTime getHoraPrevistaChegada() {
        return horaPrevistaChegada;
    }

    public void setHoraPrevistaChegada(LocalDateTime horaPrevistaChegada) {
        this.horaPrevistaChegada = horaPrevistaChegada;
    }

    public int getViagemStatus() {
        return viagemStatus;
    }

    public void setViagemStatus(int viagemStatus) {
        this.viagemStatus = viagemStatus;
    }

    public Integer getCronograma() {
        return cronograma.getIdCronograma();
    }

    public Cronograma pegarCronograma() {
        return cronograma;
    }

    public void setCronograma(Cronograma cronograma) {
        this.cronograma = cronograma;
    }
}
