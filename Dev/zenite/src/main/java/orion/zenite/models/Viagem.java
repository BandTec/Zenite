package orion.zenite.models;


import javax.persistence.*;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Entity
@Table(name="tblDadosViagem")
public class Viagem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="idDadosViagem")
    private int id;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="idCarro")
    private Carro carro;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="idLinha")
    private Linha linha;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="idMotorista")
    private Motorista motorista;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="idFiscal")
    private Fiscal fiscal;

    @Column(nullable = false)
    private LocalTime horaSaida;

    private LocalTime horaChegada;

    private int qtdPassageiros;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public Fiscal getFiscal() {
        return fiscal;
    }

    public void setFiscal(Fiscal fiscal) {
        this.fiscal = fiscal;
    }

    public LocalTime getHoraSaida() {
        return horaSaida;
    }

    public void setHoraSaida(LocalTime horaSaida) {
        this.horaSaida = horaSaida;
    }

    public LocalTime getHoraChegada() {
        return horaChegada;
    }

    public void setHoraChegada(LocalTime horaChegada) {
        this.horaChegada = horaChegada;
    }

    public int getQtdPassageiros() {
        return qtdPassageiros;
    }

    public void setQtdPassageiros(int qtdPassageiros) {
        this.qtdPassageiros = qtdPassageiros;
    }
}
