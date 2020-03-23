package orion.zenite.models;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

@Entity
@Table(name="tblMotoristaCarro")
@IdClass(MotoristaCarroId.class)
public class MotoristaCarro {

    @Id
    private int idMotorista;

    @Id
    private int idCarro;

   // @EmbeddedId
   // MotoristaCarroId id;

    @JsonIgnore
    @ManyToOne
    @MapsId("idMotorista")
    @JoinColumn(name = "idMotorista")
    private Motorista motorista;

    @ManyToOne
    @MapsId("idCarro")
    @JoinColumn(name = "idCarro")
    private Carro carro;

    public int getIdMotorista() {
        return idMotorista;
    }

    public void setIdMotorista(int idMotorista) {
        this.idMotorista = idMotorista;
    }

    public int getIdCarro() {
        return idCarro;
    }

    public void setIdCarro(int idCarro) {
        this.idCarro = idCarro;
    }

    public Motorista getMotorista() {
        return motorista;
    }

    public void setMotorista(Motorista motorista) {
        this.motorista = motorista;
    }

    public Carro getCarro() {
        return carro;
    }

    public void setCarro(Carro carro) {
        this.carro = carro;
    }
}
