package orion.zenite.entidades;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "tblCronograma")
public class Cronograma {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idCronograma")
    private int idCronograma;

    @ManyToOne
    @JoinColumn(name="fkMotorista")
    private Motorista motorista;

    @ManyToOne
    @JoinColumn(name="fkLinha")
    private Linha linha;

    @Column(name="hora_prevista_saida")
    private LocalDateTime horaPrevistaSaida;

    @Column(name="hora_prevista_chegada")
    private LocalDateTime horaPrevistaChegada;

    @Column(name="data_cronograma")
    private LocalDate dataCronograma;

    @ManyToOne
    @JoinColumn(name="fkFiscal")
    private Fiscal fiscal;

    @Column(name="viagem_status")
    private int viagemStatus;

    public int getIdCronograma() {
        return idCronograma;
    }

    public void setIdCronograma(int idCronograma) {
        this.idCronograma = idCronograma;
    }

    public Motorista getMotorista() {
        return motorista;
    }

    public void setMotorista(Motorista motorista) {
        this.motorista = motorista;
    }

    public Linha getLinha() {
        return linha;
    }

    public void setLinha(Linha linha) {
        this.linha = linha;
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

    public LocalDate getDataCronograma() {
        return dataCronograma;
    }

    public void setDataCronograma(LocalDate dataCronograma) {
        this.dataCronograma = dataCronograma;
    }

    public Fiscal getFiscal() {
        return fiscal;
    }

    public void setFiscal(Fiscal fiscal) {
        this.fiscal = fiscal;
    }
}
