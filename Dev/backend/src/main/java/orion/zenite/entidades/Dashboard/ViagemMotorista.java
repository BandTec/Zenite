package orion.zenite.entidades.Dashboard;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "vwTempoViagemPorMotorista")
public class ViagemMotorista {


    @Column(name = "mediaViagemPorMotorista")
    int mediaViagemPorMotorista;

    @Id
    @Column(name = "nome")
    String nome;

    @Column(name = "idLinha")
    int idLinha;

    public Integer getMediaViagemPorMotorista() {
        return mediaViagemPorMotorista;
    }

    public void setMediaViagemPorMotorista(Integer mediaViagemPorMotorista) {
        this.mediaViagemPorMotorista = mediaViagemPorMotorista;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Integer getIdLinha() {
        return idLinha;
    }

    public void setIdLinha(Integer idLinha) {
        this.idLinha = idLinha;
    }
}
