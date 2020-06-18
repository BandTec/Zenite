package orion.zenite.entidades.Dashboard;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "vwOnibusCirculando")
public class OperandoParado {

    @Id
    @Column(name = "qtdOnibusCirculando")
    private int qtdOnibusCirculando;

    @Column(name = "qtdOnibusParado")
    private int qtdOnibusParado;

    public int getOnibusCirculando() {
        return qtdOnibusCirculando;
    }

    public void setOnibusCirculando(int onibusCirculando) {
        this.qtdOnibusCirculando = onibusCirculando;
    }

    public int getOnibusParado() {
        return qtdOnibusParado;
    }

    public void setOnibusParado(int onibusParado) {
        this.qtdOnibusParado = onibusParado;
    }
}
