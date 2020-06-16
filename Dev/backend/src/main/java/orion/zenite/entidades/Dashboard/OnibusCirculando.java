package orion.zenite.entidades.Dashboard;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "vwQtdOnibusCirculandoPorPeriodo")
public class OnibusCirculando {
    @Id
    @Column(name = "periodo")
    private String periodo;

    @Column(name = "qtd_carros")
    private Integer qtdCarros;

    public String getPeriodo() {
        return periodo;
    }

    public void setPeriodo(String periodo) {
        this.periodo = periodo;
    }

    public Integer getQtdCarros() {
        return qtdCarros;
    }

    public void setQtdCarros(Integer qtdCarros) {
        this.qtdCarros = qtdCarros;
    }
}
