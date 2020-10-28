package orion.zenite.entidades;


import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="tblLinha")
public class Linha {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idLinha")
    private int id;

    @Column(length = 7, nullable = false)
    private String numero;

    @ManyToOne
    @JoinColumn(name = "fkPontoIda")
    private PontoFinal pontoIda;

    @ManyToOne
    @JoinColumn(name = "fkPontoVolta")
    private PontoFinal pontoVolta;

    @JsonIgnore
    @OneToMany(mappedBy = "linha", cascade = CascadeType.REMOVE)
    private List<CarroLinha> carroLinhas;

    @JsonIgnore
    @OneToMany(mappedBy = "linha", cascade = CascadeType.REMOVE)
    private List<Viagem> viagem;

    @JsonIgnore
    @OneToMany(mappedBy = "linha", cascade = CascadeType.REMOVE)
    private List<FiscalLinha> fiscalLinha;

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public PontoFinal getPontoIda() {
        return pontoIda;
    }

    public void setPontoIda(PontoFinal pontoIda) {
        this.pontoIda = pontoIda;
    }

    public PontoFinal getPontoVolta() {
        return pontoVolta;
    }

    public void setPontoVolta(PontoFinal pontoVolta) {
        this.pontoVolta = pontoVolta;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Integer getFiscalId(){
        if(fiscalLinha.isEmpty()){
            return null;
        }
        return fiscalLinha.get(0).getIdFiscal();
    }

    public String getFiscal(){
        if(fiscalLinha.isEmpty()){
            return null;
        }
        Fiscal f = fiscalLinha.get(0).getFiscal();
        return f.getNome();
    }

    public String getFiscalNumero(){
        if(fiscalLinha.isEmpty()){
            return null;
        }
        Fiscal f = fiscalLinha.get(0).getFiscal();
        return f.getNumeroTelefone();
    }

    public List getCarrosId() {
        ArrayList carrosId = new ArrayList();
        for (CarroLinha carro : carroLinhas) {
            carrosId.add(carro.getIdCarro());
        }
        return carrosId;
    }

    public List getCarros() {
        ArrayList carros = new ArrayList();
        for (CarroLinha carro : carroLinhas) {
            Carro onibus = carro.getCarro();
            String nome = String.format("%s - %s",
                    onibus.getNumero(), onibus.getPlaca()
            );
            carros.add(nome);
        }
        return carros;
    }
}
